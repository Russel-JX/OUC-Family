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
<link rel="stylesheet" href="<%=rootPath%>/css/datepicker.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/datepicker3.css"/>

	
	<body>
	<div class="container">
		<div class="panel panel-default">
		  <div class="panel-heading">订单管理</div>
		  	<div class="panel-body">

			   <div class="form-group row">
			       <label for="orderStatus" class="col-sm-2 control-label">订单状态</label>
			       <div class="col-sm-2">
			           <select id="orderStatus" name="orderStatus" class="form-control" style="width:160px;">
			               <option value="">请选择...</option>
			               <c:forEach var="item" items="${map}">
			                   <option value="${item.key}">${item.value}</option>
			               </c:forEach>
			           </select>
			       </div>
			   </div>
			   
			   <div class="form-group row">    
			       <label for="startDate" class="col-sm-2 control-label">开始时间</label>
			       <div class="col-sm-2">
			           <div class="input-group date" id="startDateDiv" >
				           <input type="text" id="startDate" name="startDate" class="form-control" value="" placeholder="请输入开始时间 " />
				           <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
				       </div>
			       </div>
			  </div>
	
			  <div class="form-group row">
			       <label for="endDate" class="col-sm-2 control-label">结束时间</label>
			       <div class="col-sm-2">
				       <div class="input-group date" id="endDateDiv" >
				           <input type="text" id="endDate" name="endDate" class="form-control" value="" placeholder="请输入结束时间 "/>
				           <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
				       </div>
			      </div>
			  
		  	  
		  	 
	</div>
	
	
	<div class="modal fade" id="detailModal">
           <div class="modal-dialog">
               <div class="modal-content">
                   <div class="modal-header">
                       <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                       <h4 class="modal-title">查看订单</h4>
                   </div>
                   <div class="modal-body" style="height:400px;overflow-y:auto;overflow-x:hidden;">
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
                       
                   </div>
                   <div class="modal-footer">
                       <div style="margin:0 auto;">
                           <div class="col-sm-3">
                               <button type="button" class="btn btn-inverse" data-toggle="tooltip" title="关闭弹出框" data-dismiss="modal">关闭</button>
                           </div>
                       </div>
                   </div>
               </div><!-- /.modal-content -->
           </div><!-- /.modal-dialog -->
       </div><!-- /.modal -->
       
       
	<div class="form-group row">
		  	  	<div class="col-md-2">
			       	<button type="button" id="submitBtn" name="submitBtn" class="btn btn-primary" >查询</button>
			     </div>
			  </div>
			  
	 <hr/>
		  	 <div id="orderInfoDiv" class="table-responsive"></div>
	</div>
          
		
	</div>
	<%@ include file="../common/footer.jsp"%>
	</body>
</html>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/bootstrap-datepicker.js" type="text/javascript"></SCRIPT>
<script src="<%=rootPath%>/js/order/order.js"></script>

<style type="text/css">
#orderInfoTbl_wrapper .row{margin-right:0px !important;margin-left:0px !important}
.row{
	margin-top:10px;
}
</style>	
</head>
		
<script type="text/javascript">
$(document).ready(function(){
	
	$('#startDateDiv').datepicker({format: "yyyy-mm-dd"});
	$('#endDateDiv').datepicker({format: "yyyy-mm-dd"});
	
	  $('#submitBtn').click(function(){
		  var startDt = new Date($('#startDate').val()).getTime();
		  var endDt = new Date($('#endDate').val()).getTime();
		  if(startDt >endDt){
			  showErrorAlert("开始时间不能小于结束时间！");
			  return;
		  }
		  buildDatables();
		  /* $('#orderInfoTbl tr').each(function(){
				var tdObj = $(this).find('td:eq(2)');
				tdObj.click(function () {
					var cellVal = $(this).html();
					if(isNull(cellVal)){
						$(this).html("<input type='text' class='form-control input-sm' onchange=\'addIcon(this);\'/> <span style='display:none'><a href='#' ><span class='glyphicon glyphicon-ok'></span></a><a href='#' > <span class='glyphicon glyphicon-remove'></span></a><a href='#' ></a></span>");
					}
				});
				}); */
	  });
});



/* function addIcon(selfObj){
	$(selfObj).next().css("display","block");
} */
    
function buildDatables(){
	$('#orderInfoDiv').empty().append('<table id="orderInfoTbl"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/showOrdersForAdmin",
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
			        	 
			        	 $('td:eq(8)', nRow).html("<a href='JavaScript:void();' onclick='showDetail(\""+aData.orderCode+"\",\""+aData.userId+"\");'>查看</a>");
			        	 if(aData.source == '1'){
			        		 $('td:eq(5)', nRow).html("手工录入");
			        	 }else if(aData.source == '2'){
			        		 $('td:eq(5)', nRow).html("系统下单");
			        	 }
			        	 
			        	 if(aData.status == '1'){
			        		 //$('td:eq(7)', nRow).html("新增单");
			        		 //var select = '<select id="orderStatus" onchange="saveStatus(this,\''+aData.orderCode+'\');" name="orderStatus"><option value="1">新增单 </option><option value="2">处理中 </option><option value="3">配送中 </option></select><a href="javascript:void(0);" onclick="saveSerialNo(this);" ><span class="glyphicon glyphicon-ok"></span></a><a href="javascript:void(0);" onclick="cancelSave(this);"> <span class="glyphicon glyphicon-remove"></span></a>';
			        		 var select = '<select id="orderStatus" onchange="saveStatus(this,\''+aData.orderCode+'\');" name="orderStatus"><option value="1">新增单 </option><option value="2">处理中 </option><option value="3">配送中 </option></select>';
			        		 $('td:eq(7)', nRow).html(select).css('width','170px');
			        	 }else if(aData.status == '2'){
			        		 //$('td:eq(7)', nRow).html("处理中");
			        		 //var select = '<select id="orderStatus" onchange="saveStatus(this,\''+aData.orderCode+'\');" name="orderStatus"><option value="2">处理中 </option><option value="3">配送中 </option></select><a href="javascript:void(0);" onclick="saveSerialNo(this);" ><span class="glyphicon glyphicon-ok"></span></a><a href="javascript:void(0);" onclick="cancelSave(this);"> <span class="glyphicon glyphicon-remove"></span></a>';
			        		 var select = '<select id="orderStatus" onchange="saveStatus(this,\''+aData.orderCode+'\');" name="orderStatus"><option value="2">处理中 </option><option value="3">配送中 </option></select>';
			        		 $('td:eq(7)', nRow).html(select).css('width','170px');
			        	 }else if(aData.status == '3'){
			        		 $('td:eq(7)', nRow).html("配送中");
			        	 }else if(aData.status == '4'){
			        		 $('td:eq(7)', nRow).html("已收货");
			        	 }
			         }
			    });
				
				}
			},
			data:{orderStatus:$('#orderStatus').val(),startDate:$('#startDate').val(),endDate:$('#endDate').val()}
		});
}


function saveStatus(selfObj,orderCode){
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