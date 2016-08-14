<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<title>派送范围数据导入</title>
<head>
</head>
<body>
<div class="container">
<div class="panel panel-default">
	<div class="panel-heading">派送范围数据导入</div>
</div>
<div id="warningAlert" class="alert alert-danger" style="display:none"></div>
<form method="post" id="form1" action="deliverAreasUpload" onsubmit="return haveFile();" enctype="multipart/form-data">
  <div class="form-group">
    <label for="fileUpload">请选择文件</label>
    <input type="file" id="fileUpload" name="fileUpload">
    <p class="help-block">请选择excel类型的文件，文件大小不能超过5M.</p>
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