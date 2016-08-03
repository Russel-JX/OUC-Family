<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
    <head>
        <title>管理员注册</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<%=rootPath%>/css/bootstrap.css">
        
        <style>

        </style>
        
        
        <script type="text/javascript" src="<%=rootPath%>/js/jquery-1.11.0.min.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/js/jquery.form-validator.min.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/js/security.js"></script>
		<script type="text/javascript" src="<%=rootPath%>/js/territory.js"></script>
    </head>
<body>
    <div class="container">
    	<div class="panel panel-default" style="margin-top:30px;">
    		<div class="panel-heading">管理员注册</div>
			<div class="panel-body">
			    <form class="form-horizontal" role="form"  id="register" action="adminRegister" method="post">
			        <div class="form-group">
			            <label for="inputName" class="col-sm-2 control-label" >
			            <strong style="color:red">*</strong>
			                                      用户名
			            </label>
			            <div class="col-xs-3">
			                <input type="text" class="form-control" id="inputName" name="name" placeholder="用户名" data-validation="custom" length="50" data-validation-error-msg="用户名">
			            </div>
			        </div>
			        
			        <div class="form-group">
			            <label for="inputPassword" class="col-sm-2 control-label">
			            <strong style="color:red">*</strong>	
			                                      密码
			            </label>
			            <div class="col-xs-3">
			                <input type="password" class="form-control" id="inputPassword" name="pass_confirmation" placeholder="密码" data-validation="length" data-validation-length="6-20" data-validation-error-msg="密码长度6-20位字符">
			            </div>
			        </div>
			        <div class="form-group">
			            <label for="inputRePSW" class="col-sm-2 control-label">
			            <strong style="color:red">*</strong>
			                                     确认密码
			            </label>
			            <div class="col-xs-3">
			                <input type="password" class="form-control" id="inputRePSW" name="pass" placeholder="确认密码" data-validation="confirmation" data-validation-error-msg="两次输入密码不一致">
			            </div>
			        </div>
			        <div class="form-group">
			        	<div class="col-md-1">
			        	</div>
			        	
			        	<div class="col-md-1">
			        		<button type="submit" id="submitBtn" class="btn btn-primary" onclick="submitForm();">注册</button>
			        	</div>
			        	<div class="col-md-10">
			        		<a href="backToAdminLogin" class="btn btn-primary">返回</a>
			        	</div>
			        </div>
			        
			        <input type="hidden" id="address" name="address"/>
			        <input type="hidden" id="userId" name="userId" value="${userId}"/>
			        <input type="hidden" id="source" name="source" value="${source}"/>
			    </form>
			</div>
    	</div>
    </div>
</body>
</html>
<script type="text/javascript">
    $(document).ready(function(){
        $.validate({
            modules: 'security'
        });
        /* $('#inputMobile').ajaxValidator({
        	type:"post",
        	dataType: "json",
        	url: "page/mobileExists",
        	data:{"mobile" : $('#inputMobile').val()},
        	success : function(jsonData.success){
        		if(!isNull(jsonData.message)){
        			return jsonData.message;
        		}
            }
        }); */
        loadAddr();
    });
    
    function loadAddr(){
	    var names = new Array('province','city','area');
	
	    loadAreasDropDown($('#addressDiv'),names);
    }
    
    function submitForm(){
    	var province = $('#province').find('option:selected').text();
    	var city = $('#city').find('option:selected').text();
    	var area = $('#area').find('option:selected').text();
    	$("#address").val(province+" "+city+" "+area);
    	$('#submitBtn').click();
    }
</script>