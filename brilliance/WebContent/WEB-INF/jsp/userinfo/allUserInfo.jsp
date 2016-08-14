<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/admin_header.jsp"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE HTML >
<html>
<head>
<title>进度查询</title>
<style>
body {
	margin:0;
}
#userInfoTbl_wrapper .row {
	margin-right:0px !important;
	margin-left:0px !important
}
.form-group{
    margin-right:15px;
}
</style>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/territory.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="<%=rootPath%>/js/tool.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	  initDate();
	  $('.btn-user-search').click(function(){
		  buildDatables();
	  });
	  
});

function initDate(){
	var contents1 = new Array($('#provinceDiv1'),$('#cityDiv1'),$('#areaDiv1'));
	var names1 = new Array('provinceId','cityId','areaId');
	
	loadTerritoryDropDown(contents1,names1);
	
	buildDatables();
}


function buildDatables(){																						
	$('#userInfoDiv').empty().append('<table id="userInfoTbl"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/userinfo/showAllUsers",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var dataList = jsonData.data.userLst;
				$('#userInfoTbl').dataTable( {
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
                                    {"sTitle": "用户编号","mData":"userId","sDefaultContent": ""},
                                    {"sTitle": "用户名","mData":"name","sDefaultContent": ""},
                                    {"sTitle": "手机号码","mData":"mobileNO","sDefaultContent": ""},
                                    {"sTitle": "Email","mData":"email","sDefaultContent": ""},
                                    {"sTitle": "地址","mData":"address","sDefaultContent": ""},
                                    {"sTitle": "登陆次数","mData":"loginTimes","sDefaultContent": ""},
                                    {"sTitle": "积分","mData":"credits","sDefaultContent": ""},
                                    {"sTitle": "创建时间","mData":"createTime","sDefaultContent": ""},
                                    {"sTitle": "状态","mData":"status","sDefaultContent": ""},
                                    {"sTitle": "hidden","mData":"id","bSearchable": true, "bVisible": false}
			                     ],
			        "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			        	 var time = aData.createTime;
			        	 var d="";
			        	 if(!isNull(time)){
			        		 d =new Date(time).format('yyyy-MM-dd');
			        	 }
			        	 
			        	 $('td:eq(7)', nRow).html(d);
			        	 
			        	 if(aData.status == '1'){
			        		 $('td:eq(8)', nRow).html("正常");
			        	 }else if(aData.status == '0'){
			        		 $('td:eq(8)', nRow).html("注销");
			        	 }
			         }
			    });
				
				}
			},
			data:{
				genderSelect:$('#genderSelect').val(),provinceId:$('#provinceId').val(),
				cityId:$('#cityId').val(),areaId:$('#areaId').val()
				}
		});
}

</script>

<body>
	<div class="container">
		<form class="form-signin" id="form1" role="form" method="post" action="">
	<div class="panel panel-default">
		<div class="panel-heading">用户统计</div>
		<div style="padding:20px;">
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
							<label for="genderSelect" class="col-sm-2 control-label text-center">性别</label>
							<div class="col-sm-2">
								<select id="genderSelect" name="genderSelect" class="form-control"
									style="width: 150px">
									<option value="">--请选择--</option>
									<option value="male">男</option>
									<option value="female">女</option>
								</select>
							</div>
							<label for="provinceDv" class="col-sm-2 control-label text-center" style="">省份</label>
							<div class="col-sm-2" id="provinceDiv1">
							</div>
							<label for="cityDv" class="col-sm-2 control-label text-center" style="">城市</label>
							<div class="col-sm-2" id="cityDiv1">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<label for="areaDv" class="col-sm-2 control-label text-center" style="">地区</label>
							<div class="col-sm-2" id="areaDiv1">
							</div>
						</div>
					</div>
					<div class="form-group col-sm-12 text-center">
						<button type="button" class="btn btn-lg btn btn-primary btn-user-search">查询</button>
					</div>
				</div>
			</div>
	</div>
	
	<div id="userInfoDiv" class="table-responsive"></div>
	
</form>
	</div>
<%@ include file="../common/footer.jsp"%>
</body>
</html>