<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>管理员登录</title>
    <meta name="generator" content="Bootply" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="<%=rootPath%>/css/bootstrap.css" rel="stylesheet">

        <!-- CSS code from Bootply.com editor -->
        
        <style type="text/css">
            .modal-footer {   border-top: 0px; }
            body {
            	/*background-color:rgba(0,0,0,0.5);*/
            	background-image: url(<%=rootPath%>/images/admin_bg.jpg);
            	background-size:cover;
            	background-attachment:fixed;
            }
            #loginModal {
            	max-width:400px;
           		margin:10% auto;
           		background-color:white;
           		padding:20px;
           		-moz-border-radius: 15px;
			    -webkit-border-radius: 15px;
			    border-radius:15px; 
			    -moz-box-shadow:15px 10px 5px #999;
			    -webkit-box-shadow:15px 10px 5px #999; 
			    box-shadow:15px 10px 5px #999; 
            }
            #loginBtn {
            	background-color:rgba(45, 190, 161, 0.95);
            } 
            #loginBtn:hover {
            	background-color:rgba(45, 190, 161, 0.6);
            }
            #regist{
            	margin-top:8px;
            	font-size:18px;
            }
        </style>
    </head>
    
    <!-- HTML code from Bootply.com editor -->
    
<body>

	<div class="container">
		<div id="loginModal" class="modal-dialog">
	      <form class="form-signin" role="form" method="post" action="admin_login">
	        <h2 class="text-center">管理员登录</h2>
	        <div class="form-group">
	        	<input type="text"  name="name" class="form-control input-lg"  placeholder="用户名" required autofocus>
	        </div>
	        <div class="form-group">
	        	<input type="password" name="password" class="form-control input-lg" placeholder="密码"required>
	        </div>
	        <span class="help-block"><strong style="color:red"><c:out value="${msg}"></c:out></strong></span>
	        <div class="form-group">
	          <button id="loginBtn" class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
	        </div>
	      </form>
		</div>
    </div> <!-- /container -->


	<!--login modal-->
	<div id="loginModal" class="modal" tabindex="-1" role="dialog" aria-hidden="true">
	  <div class="modal-dialog">
		  <div class="modal-content">
		      <div class="modal-header">
		          <h1 class="text-center">登录</h1>
		      </div>
		      <div class="modal-body">
		          <form class="form col-md-12 center-block" method="post" action="login">
		            <div class="form-group">
		              <input type="text"  name="mobile" class="form-control input-lg" placeholder="手机号码">
		            </div>
		            <div class="form-group">
		              <input type="password" name="password" class="form-control input-lg" placeholder="密码">
		            </div>
		            <span class="help-block"><strong style="color:red"><c:out value="${msg}"></c:out></strong></span>
		            <div class="form-group">
		              <button class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
		              <!-- <span class="pull-right"><a href="forwardToRegister" >注册</a></span><span><a href="#">帮助?</a></span> -->
		            </div>
		          </form>
		      </div>
		      <div class="modal-footer">
		      </div>
		  </div>
	  </div>
	</div>
<!-- JavaScript jQuery code from Bootply.com editor -->

<script type='text/javascript'>

$(document).ready(function() {

});


</script>
        
</body>
</html>