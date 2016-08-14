<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<title>常用快递导入</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/bootstrap.css"/>
</head>
<body>
<div class="container">
<div class="panel panel-default">
	<div class="panel-heading">常用快递导入</div>
</div>
<div id="warningAlert" class="alert alert-danger" style="display:none"></div>
<form method="post" id="form1" action="" enctype="multipart/form-data">
  <div class="form-group">
    <label for="fileUpload">请选择文件</label>
    <input type="file" id="fileUpload" name="fileUpload">
    <p class="help-block">请选择excel类型的文件，文件大小不能超过5M.</p>
    <c:if test="${sessionScope.hasInvalidFile==true}">
    	<p class="text-success"><strong><a href="exportInvalidCustAddr">${msg}</a></strong></p>
	</c:if>
	 <c:if test="${sessionScope.hasInvalidFile!=true}">
    	 <p class="text-success"><strong>${msg}</strong></p>
	</c:if>
  </div>
  
  <div class="form-group">
  <div class="row">
     <div class="col-sm-6">
	  <button type="button" id="fileBtn" class="btn btn-lg btn-primary" onclick="fileimport();">导入文件</button>
	  </div>
	  
  </div>
  </div>
  <div class="form-group">
  <div class="row">
	  <div class="col-sm-6">
	  <p class="text-success"><strong>${msg1}</strong></p>
		  <button type="button" id="dbBtn" class="btn btn-lg btn-primary" onclick="DBimport();">数据库导入</button>
	  </div>
  </div>
  </div>
</form>
</div>

</body>
<%@ include file="../../jsp/common/footer.jsp"%>
</html>
<script type="text/javascript" src="<%=rootPath%>/js/tool.js"></script>
<script type="text/javascript">

function haveFile(){
	if(isNull($("#fileUpload").val())){
		showWarningAlert("请选择上传文件。");
		return false;
	}
	return true;
}

function fileimport(){
	if(!haveFile()) return;
	$("#form1").attr("action","importCustAddr");
	$(":button").attr("disabled",true);
	$("#form1").submit();
}


function DBimport(){
	$("#form1").attr("action","importFrmDB");
	$(":button").attr("disabled",true);
	$("#form1").submit();
}
</script>