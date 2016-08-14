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
</head>
<script type="text/javascript">
$(document).ready(function(){
	  $('#submitBtn').click(function(){
		  searchAddr();
	  });
});

function searchAddr(){
	alert(2);
		/* $.ajax({
			type: "GET",
			url: "http://api.map.baidu.com/place/search?query=上街镇新峰15号&region=福州市&output=json&src=iGate",
			dataType: "jsonp",
			jsonp: 'ballback',
			beforeSend: function(){ 
				alert(3);
			},
			success: function(json){
				alert(json);
			}
		}); */
		var url= "http://api.map.baidu.com/place/search?jsonpcallback=?&query=上街镇新峰15号&region=福州市&output=json&src=iGate";
		$.getJSON(url, function(data){
			alert("Symbol:"+data.results);
		});
}

function fdx(){
	alert('callback|');
}
</script>

<body>
	<div class="container">
		<div class="panel panel-default">
			<div class="panel-heading">地址反向查询</div>
		  	<div class="panel-body">
		  		<div class="form-group row">
			       <label for="expressCode" class="col-sm-2 control-label">关键词</label>
			       <div class="col-sm-2">
			           <input type="text" id="keyWord" name="keyWord" class="form-control" placeholder="请输入搜索地址的关键词"/>
				   </div>
				</div>
				<div class="form-group row">       
				   <label for="t_contact" class="col-sm-2 control-label">城市</label>
				   <div class="col-sm-2">
				        <input type="text" id="city" name="city" class="form-control" placeholder="请输入城市名"/>
				    </div>
			    </div>
			    <div class="row">
				    <div class="col-sm-8">
					    <button type="button" id="submitBtn" name="submitBtn" class="btn btn-primary" >查询</button>
					</div>
			    </div>
			  
			  	<hr/>

			    <div id="userInfoDiv" class="table-responsive"></div>
			    
		  	</div>
		</div>
	</div>
<%@ include file="../common/footer.jsp"%>
</body>
</html>