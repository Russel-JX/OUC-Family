<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<title>地址信息导入</title>
<head>
</head>
<body>
<div class="container">
<div class="panel panel-default">
	<div class="panel-heading">地址信息导入</div>
</div>
<div id="warningAlert" class="alert alert-danger" style="display:none"></div>
<form method="post" id="form1" action="importCustAddress" onsubmit="return haveFile();" enctype="multipart/form-data">
  <div class="form-group">
    <label for="fileUpload">请选择文件</label>
    <input type="file" id="fileUpload" name="fileUpload">
    <p class="help-block">请选择excel类型的文件，文件大小不能超过2M.</p>
  </div>
  
 <p class="text-success"><strong>${msg}</strong></p>
  
  <button type="submit" class="btn btn-default">Submit</button>
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

</script>
