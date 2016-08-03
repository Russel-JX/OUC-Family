<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../jsp/common/header.jsp"%>
<title>货运公司信息</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>

<style type="text/css">
body{
	/*background-color:rgba(0,0,0,0.2);*/
	margin:0;
}
#express_table_wrapper .row{margin-right:0px !important;margin-left:0px !important}
.row{
	margin-top:10px;
}
</style>
</head>
<SCRIPT type="text/javascript">
$(document).ready(function() {

});



function buildDataTable(){
	
	$('#expressinfo').empty().append('<table id="express_table"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/getDateList",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var dataList = jsonData.data.dataList;
				var dTable = $('#express_table').dataTable( {
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
			        "aoColumnDefs":[{"aTargets": [8],
			        	          "mData": null,
			        	          "mRender": function (data) {
			        	        	  return '<a href="#" id="order" >下单</a>';
			        	          }
			        	}],
			        "aoColumns":[
                                    {"sTitle": "货运公司编号","bSearchable": true, "bVisible": false},
                                    {"sTitle": "货运公司名称"},
                                    {"sTitle": "免费电话"},
                                    {"sTitle": "手机号码"},
                                    {"sTitle": "客服电话"},
                                    {"sTitle": "评价"},
                                    {"sTitle": "费用"},
                                    {"sTitle": "送达天数"},
                                    {"sTitle": "操作"}
			                     ],
                    "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		        	    var province = $('#f_province').find('option:selected').text();
		        		var city = $('#f_city').find('option:selected').text();
		        		var area = $('#f_area').find('option:selected').text();
		        		var fromAddr = province+','+city+','+area;
		        		
		        		 var provinceId = $('#f_province').val();
			        	 var cityId = $('#f_city').val();
			        	 var areaId = $('#f_area').val();
			        	 
		        		
		        		province = $('#t_province').find('option:selected').text();
		        		city = $('#t_city').find('option:selected').text();
		        		area = $('#t_area').find('option:selected').text();
		        		var toAddr = province+','+city+','+area;
		        		
		        		var t_provinceId = $('#t_province').val();
			        	var t_cityId = $('#t_city').val();
			        	var t_areaId = $('#t_area').val();
			        	//$('#expressName').val(aData[1]);
			        	//$('#expressCode').val(aData[0]);
		        		//var values = fromAddr+"&toAddr="+toAddr+"&expressCode="+aData[0]+"&expressName="+aData[1]
		        		  //        +"&charge="+aData[6]+"&deliverDays="+aData[7]+"&fromId="+fromId+"&toAddrId="+toAddrId);
				        var values = provinceId+"&"+cityId+"&"+areaId+"&"+t_provinceId+"&"+t_cityId+"&"+t_areaId+"&"+aData[0]+"&"+aData[1]+"&"+aData[6]+"&"+aData[7]+"&"+fromAddr+"&"+toAddr;
				        //var values = new Array(aData[1],aData[0]);
				        //values.push(args)
				        //var values={'expressName':aData[1],'expressCode':aData[0]};
		        	 $('td:eq(8)', nRow).html("<a href='JavaScript:void();' onclick='addOrder(\""+values+"\");'>下单</a>");
		         }
			    });
				
				
				}
			},
			//data:{f_province:'320000',f_city:'320500',f_area:'320505',t_province:'310000',t_city:'310100',t_area:'310105'}
			data:{f_province:$('#f_province').val(),f_city:$('#f_city').val(),f_area:$('#f_area').val(),t_province:$('#t_province').val(),t_city:$('#t_city').val(),t_area:$('#t_area').val()}
		});
}


$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

$(function(){
    $('#express_table').each(function(){
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



</SCRIPT>
<body>

<div id="container" class="container">

<div class="panel panel-default">
	<div class="panel-heading">新增货运公司</div>
	<div class="panel-body">
	
	<div class="form-group">
	    <label for="expressCode" class="col-sm-2 control-label">货运公司代码</label>
	    <div class="col-sm-3">
	      <label id="expressCode" class="col-sm-2 control-label">${ }</label>
	    </div>
	    <label for="expressName" class="col-sm-2 control-label">货运公司名称</label>
	    <div class="col-sm-4">
		<input type="text" id="expressName" name="expressName" class="form-control" value=""/>
		</div>
   </div>
  
		
			
	<div class="form-group">
		<label for="telephoneNo" class="col-sm-2 control-label">客服电话</label>
		<div class="col-sm-2">
		<input type="text" class="form-control" id="telephoneNo" name="telephoneNo" placeholder="请输入电话"/>
		</div>
		<label for="serviceNo" class="col-sm-2 control-label">服务号码</label>
		<div class="col-sm-2">
		<input type="text" class="form-control" id="serviceNo" name="serviceNo" placeholder="请输入公司服务号码"/>
		</div>
	</div>
	            
	            
   <div class="form-group">
       <label for="fromAddr" class="col-sm-2 control-label">手机号码</label>
       <div class="col-sm-2">
       <input type="text" id="f_mobileNo" maxlength="11" id="f_mobileNo" class="form-control"/>
       </div>
    </div>
	    
	    
	</div>
</div>


<div class="panel panel-default">
 <div class="panel-heading">查询</div>
 <div class="panel-body">
 
    <div class="row">
        <div class="col-md-1">始发地</div>
        <div class="col-md-2" id="provinceDiv" style="max-width:200px;"></div>
		<div class="col-md-2" id="cityDiv" style="max-width:200px;"></div>
		<div class="col-md-2" id="areaDiv" style="max-width:200px;"></div>	    
		<div class="col-md-5"></div>    
    </div>
    <div class="row">
        <div class="col-md-1">目的地</div>
        <div class="col-md-2" style="max-width:200px;" id="provinceDiv1"></div>
		<div class="col-md-2" style="max-width:200px;" id="cityDiv1"></div>
		<div class="col-md-2" style="max-width:200px;" id="areaDiv1"></div>	    
		<div class="col-md-5"></div>	    
    </div>
    <div class="row">
        <div class="col-md-1"><button type="button" class="btn btn-primary" onclick="buildDataTable();">查询</button></div>
    </div>

	<hr/>
	
    <div id="expressinfo" class="table-responsive"></div>
    
	
    </div>
  </div>
  
  
  
  <div id="addExpress" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog">
		  <div class="modal-content">
		      <div class="modal-header">
		          <h1 class="text-center">新增</h1>
		      </div>
		      <div class="modal-body">
		          <form class="form col-md-12 center-block" method="post" action="saveExpress">
		            <div class="form-group">
		              <input type="text"  name="mobile" class="form-control input-lg" placeholder="手机号码">
		            </div>
		            <div class="form-group">
		              <input type="password" name="password" class="form-control input-lg" placeholder="密码">
		            </div>
		            <span class="help-block"><strong style="color:red"><c:out value="${msg}"></c:out></strong></span>
		            <div class="form-group">
		              <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
		              <span class="pull-right"><a href="forwardToRegister" >注册</a></span><span><a href="#">帮助?</a></span>
		            </div>
		          </form>
		      </div>
		      <div class="modal-footer">
		      </div>
		  </div>
	  </div>
	</div>
</div>


</body>
<%@ include file="../jsp/common/footer.jsp"%>
</html>
