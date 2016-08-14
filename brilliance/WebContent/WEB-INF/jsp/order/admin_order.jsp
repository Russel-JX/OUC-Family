<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>订单管理</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<script src="<%=rootPath%>/js/order/order.js"></script>

<style type="text/css">
#orderInfoTbl_wrapper .row{margin-right:0px !important;margin-left:0px !important}
.row{
	margin-top:10px;
}

.compose-rating{
          height: 25px;
          overflow: hidden;
      }
      .rating-title{
          float: left;
          width: 30%;
          color: #999;
          -moz-text-align-last: justify;
          text-align: justify;
      }

      .rating-content{
          overflow: hidden;
          zoom: 1;
          height: 23px;
          float: right;
          width: 70%;
      }

      .rating-star{
          display: inline-block;
          vertical-align: middle;
          position: relative;
          height: 23px;
          width: 120px;
          background-position: 0 0;
          margin-left: 10px;
          top: -3px;
      }

      .star-1 a{
          width: 20%;
          z-index: 6;
      }

      .rating-star a{
          background: url(<%=rootPath%>/images/star-marks.png) no-repeat;
         line-height: 23px;
         height: 23px;
         margin: 0;
         position: absolute;
         top: 0;
         left: 0;
         text-indent: -999em;
         outline: 0;
         background-position: 1000px 1000px;
     }

     .rating-star{
         background: url(<%=rootPath%>/images/star-marks.png) no-repeat;
         display: inline-block;
         vertical-align: middle;
         position: relative;
         height: 23px;
         width: 120px;
         background-position: 0 0;
         margin-left: 10px;
         top: -3px;
     }

     .star-2 a{
         width: 40%;
         z-index: 5;
     }

     .star-3 a{
         width: 60%;
         z-index: 4;
     }

     .star-4 a{
         width: 80%;
         z-index: 3;
     }

     .star-5 a{
         width: 100%;
         z-index: 2;
     }

     .star-6 a{
         width: 0%;
         z-index: 1;
     }

     .star-6 .rating-cur{
      background-position: 0 -58px;
     }
</style>	
</head>
		
<script type="text/javascript">
var inputStr = "<div class='input-group' ><input type='text' style='width:130px;' class='form-control input-sm'/> <span class='input-group-addon'><a href='javascript:void(0);' onclick='saveSerialNo(this);' ><span class='glyphicon glyphicon-ok'></span></a>&nbsp; <a href='javascript:void(0);' onclick='cancelSave(this);'> <span class='glyphicon glyphicon-remove'></span></a></span></div>";
$(document).ready(function(){
	  $('#submitBtn').click(function(){
		  buildDatables();
		  
		  $('#orderInfoTbl tr').each(function(){
				var tdObj = $(this).find('td:eq(2)');
				tdObj.click(function (event) {
					var cellVal = $(this).html();
					if(isNull(cellVal)){
						$(this).html(inputStr);
						//$(this).css("width","200px");
						//$(selfObj).css("width","150px");
					}else{
						var elementObj = (event.target)?event.target:event.srcElement;
						var element = elementObj.tagName;
						var val = elementObj.innerHTML;
						//var serNo = $(this).html();
						if(!isNull(val)){
							$(this).css("width","220px");
							$($(this).children()[0]).css("display","block");
						}
					}
				});
				});
	  });
	
});

function cancelSave(selfObj){
	var div = $($(selfObj).parent().parent());
	//children()[0] --jsDOM对象
	$($(div[0]).children()[0]).val('');
	div.css("display","none");
}

function saveSerialNo(selfObj){
	var textObj = $($(selfObj).parent().prev());
	var ordertdObj = $(selfObj).parent().parent().parent().prev().prev();
	var currentTdObj= $(selfObj).parent().parent().parent();
	//alert(textObj.val()+"|"+ordertdObj.html());
	$.ajax({
		type: "POST",
		url: "../page/order/updateExpressSerNo",
		dataType: "json",
		success: function(jsonData){
			if(jsonData.success){
				showSuccessAlert(jsonData.message.messageContent);
				$(selfObj).parent().parent().parent().html(textObj.val());
			}
		},
		data:{expressSerNo:textObj.val(),orderCode:ordertdObj.html()}
	});
}


function buildDatables(){
	$('#orderInfoDiv').empty().append('<table id="orderInfoTbl"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/showOrders",
		dataType: "json",
		async:false,
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var dataList = jsonData.data.orders;
				$('#orderInfoTbl').dataTable( {
					"bProcessing": true,
			        "bPaginate":true,
			        "bSort": true,
			        "sDom": "<'row'<'col-6'f><'col-6'l>r>t<'row'<'col-6'i><'col-6'p>>",
			        "oLanguage": {
			            "sSearch": "查找:",
			            "sZeroRecords": "没数据",
			            "sLengthMenu": "每页 _MENU_ 条",
			            "sNext": "下页",
			            "sInfo": "_START_ - _END_ of _TOTAL_",
			            "sInfoEmpty": "_START_ - _END_ of _TOTAL_"
			          },
			        "sPaginationType": "bootstrap",
			        "aaData":dataList,
			        "aoColumns":[
                                    {"sTitle": "订单编号","mData":"orderCode","sDefaultContent":""},
                                    {"sTitle": "用户名","mData":"userId","sDefaultContent":""},
                                    {"sTitle": "快递单号","mData":"expressSerialNO","sDefaultContent":""},
                                    {"sTitle": "快递公司","mData":"expressInfo.name","sDefaultContent":""},
                                    {"sTitle": "金额","mData":"amount","sDefaultContent":""},
                                    {"sTitle": "来源","mData":"source","sDefaultContent":""},
                                    {"sTitle": "下单日期","mData":"createTime","sDefaultContent":""},
                                    {"sTitle": "状态","mData":"status","sDefaultContent":""},
                                    {"sTitle": "操作","mData":null},
                                    {"sTitle": "hidden","mData":"id","bSearchable": true, "bVisible": false}
			                     ],
			        "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			        	 var time = aData.createTime;
			        	 var d =new Date(time).format('yyyy-MM-dd');
			        	 
			        	
			        	 $('td:eq(6)', nRow).html(d);
			        	 
			        	 //$('td:eq(8)', nRow).html("<a href='javascript:void(0)' id='order' >查看</a>");
			        	 $('td:eq(8)', nRow).html("<a href='JavaScript:void();' onclick='showDetail(\""+aData.orderCode+"\",\""+aData.userId+"\");'>查看</a>");
			        	 if(aData.source == '1'){
			        		 $('td:eq(5)', nRow).html("手工录入");
			        	 }else if(aData.source == '2'){
			        		 $('td:eq(5)', nRow).html("系统下单");
			        	 }
			        	 
			        	 if(aData.status == '1'){
			        		 $('td:eq(7)', nRow).html("新增单");
			        	 }else if(aData.status == '2'){
			        		 $('td:eq(7)', nRow).html("处理中");
			        	 }else if(aData.status == '3'){
			        		 $('td:eq(7)', nRow).html("配送中");
			        		 var select = '<select id="orderStatus" onchange="saveStatus(this,\''+aData.orderCode+'\');" name="orderStatus"><option value="3">配送中</option><option value="4">已收货</option></select>';
			        		 $('td:eq(7)', nRow).html(select).css('width','170px');
			        	 }else if(aData.status == '4'){
			        		 $('td:eq(7)', nRow).html("已收货");
			        		 $('td:eq(8)', nRow).html("<a href='JavaScript:void();' onclick='showDetail(\""+aData.orderCode+"\",\""+aData.userId+"\");'>评价</a>");
			        	 }
			         }
			    });
				
				}
			},
			//data:{expressName:'1003',t_contact:'test',cargoName:'hhh'}
			data:{expressCode:$('#expressItems').val(),t_contact:$('#t_contact').val(),cargoName:$('#cargoName').val()}
		});
}


function saveStatus(selfObj,orderCode){
	//alert(selfObj.value+"|"+id);
	$.ajax({
		type: "POST",
		url: "../page/order/updateStatus",
		dataType: "json",
		success: function(jsonData){
			if(jsonData.success){
				showSuccessAlert(jsonData.message.messageContent);
			}
		},
		data:{status:selfObj.value,orderCode:orderCode}
	});
}




$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

$(function(){
    $('#orderInfoTbl').each(function(){
        var datatable = $(this);
        // SEARCH - Add the placeholder for Search and Turn this into in-line formcontrol
        var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
        search_input.attr('placeholder', 'Search')
        search_input.addClass('form-control input-small')
        search_input.css('width', '250px')
 
        // SEARCH CLEAR - Use an Icon
        var clear_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] a');
        clear_input.html('<i class="icon-remove-circle icon-large"></i>')
        clear_input.css('margin-left', '5px')
 
        // LENGTH - Inline-Form control
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
        length_sel.addClass('form-control input-small')
        length_sel.css('width', '75px')
 
        // LENGTH - Info adjust location
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_info]');
        length_sel.css('margin-top', '18px')
    });
});
</script>
	
	<body>
	<div class="container">
		<div class="panel panel-default">
		  <div class="panel-heading">订单管理</div>
		  	<div class="panel-body">

			   <div class="form-group row">
			       <label for="expressCode" class="col-sm-2 control-label">快递公司</label>
			       <div class="col-sm-2">
			           <select id="expressItems" name="expressCode" class="form-control" style="width:150px">
			               <c:forEach var="expressInfo" items="${expresslst}">
			                   <option value="${expressInfo.expressCode}">${expressInfo.name}</option>
			               </c:forEach>
			           </select>
			       </div>
			   </div>
			   
			   <div class="form-group row">    
			       <label for="t_contact" class="col-sm-2 control-label">收件人</label>
			       <div class="col-sm-2">
			           <input type="text" id="t_contact" name="t_contact" class="form-control" value="" placeholder="请输入收件人" />
			       </div>
			  </div>
	
			  <div class="form-group row">
			       <label for="cargoName" class="col-sm-2 control-label">货物名称</label>
			       <div class="col-sm-2">
			           <input type="text" id="cargoName" name="cargoName" class="form-control" value="" placeholder="请输入货物名称"/>
			       </div>
			       <label for="cargoName" class="col-sm-2 control-label"></label>			       
			  </div>
			  
		  	  <div class="row">
		  	  	<div class="col-md-1">
			       	<button type="button" id="submitBtn" name="submitBtn" class="btn btn-primary" >查询</button>
			     </div>
			  </div>
		  	  <hr/>
		  	 <div id="orderInfoDiv" class="table-responsive"></div>
	</div>
	</div>
          
		<!-- 详单详情弹出框 -->
		<div class="modal fade" id="detailModal">
           <div class="modal-dialog">
               <div class="modal-content">
                   <div class="modal-header">
                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       <h4 class="modal-title">查看订单</h4>
                   </div>
                   <div class="modal-body" style="height:600px;overflow-y:auto;overflow-x:hidden;">
                       <div class="panel-group" id="accordion">
                           <div class="panel panel-default">
                               <div class="panel-heading">
                                   <h4 class="panel-title">
                                       <a data-toggle="collapse" style="width:100%;text-decoration: none;" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                           <div>订单详情</div>
                                       </a>
                                   </h4>
                               </div>
                               <div id="collapseOne" class="panel-collapse collapse in">
                                   <div class="panel-body">
                                       <div class="list-group">
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">订单编号:</span><span id="orderCode">1</span></a>
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">下单时间:</span><span id="createtime">2014-02-20 10:10:10</span></a>
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">快递公司:</span><span id="expressName">顺风快递</span></a>
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">费用:</span>&#65509;<span id="amount">10</span></a>
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">来源:</span><span id="source">系统下单</span></a>
                                           <a href="javascript:void(0)" class="list-group-item"><span class="myTitle">状态:</span><span id="statusID">已收货</span></a>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                       <div class="panel-group" id="accordionTwo">
                           <div class="panel panel-default">
                               <div class="panel-heading">
                                   <h4 class="panel-title">
                                       <a data-toggle="collapse" style="width:100%;text-decoration: none;" data-toggle="collapse" data-parent="#accordionTwo" href="#collapseTwo">
                                           <div>评价详情</div>
                                       </a>
                                   </h4>
                               </div>
                               <div id="collapseTwo" class="panel-collapse collapse in">
                                   <div class="panel-body">
                                       <div class="compose-rating">
                                           <div class="rating-title">总评</div>
                                           <div class="rating-content">
                                               <span>&nbsp;:</span>
                                               <span class="rating-star">
                                                   <span class="star-1"><a href="#" data-star-value="1" hidefocus="true">1</a></span>
                                                   <span class="star-2"><a href="#" data-star-value="2" hidefocus="true">2</a></span>
                                                   <span class="star-3"><a href="#" data-star-value="3" hidefocus="true" >3</a></span>
                                                   <span class="star-4"><a href="#" data-star-value="4" hidefocus="true">4</a></span>
                                                   <span class="star-5"><a href="#" data-star-value="5" hidefocus="true">5</a></span>
                                                   <span class="star-6"><a href="#" data-star-value="5" hidefocus="true" id="overall" class="rating-cur" style="width:0%">6</a></span>
                                               </span>
                                           </div>
                                       </div>
                                       <div>
                                           <input type="hidden" id="score" value="0"/>
                                           <textarea id="evalutionInfo" class="form-control" placeholder="亲，您的评价信息对我们提升服务有很大帮助哦!"></textarea>
                                       </div>
                                   </div>
                               </div>
                           </div>
                       </div>
                   </div>
                   <div class="modal-footer">
                       <div style="margin:0 auto;">
                           <div style="width:50%;float:left;text-align:center">
                           		<input type="hidden" id="expressCode" value=""/>
                               <button type="button" class="btn btn-primary" id="confirmBtn">提交评价</button>
                           </div>
                           <div style="width:50%;float:right;text-align:center">
                               <button type="button" class="btn btn-inverse" data-toggle="tooltip" title="关闭弹出框" data-dismiss="modal">关闭</button>
                           </div>
                       </div>
                   </div>
               </div><!-- /.modal-content -->
           </div><!-- /.modal-dialog -->
       </div><!-- /.modal -->
	</div>
	<%@ include file="../common/footer.jsp"%>
	</body>
</html>