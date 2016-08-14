<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% String rootPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=rootPath%>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=rootPath%>/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/jquery.form-validator.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/file.dev.js"></script>
<script type="text/javascript" src="<%=rootPath%>/js/security.js"></script>
<%-- <script type="text/javascript" src="<%=rootPath%>/js/tool.js"></script> --%>

	<style type="text/css">
		.pointer{
			cursor:pointer;
		}
		#no-bg:hover{
			background-color:#fff;
		}
		.no-bg{
			color:#000;
		}
	
		.modal {
			width: 100%;
			position: fixed;
			text-align: center;
			margin: 0px auto;
			top: 0px;
			left: 0px;
			bottom: 0px;
			right: 0px;
			z-index: 1050;
		}
		
		.modal_wrapper {
			display: table;
			overflow: auto;
			overflow-y: scroll;
			height: 100%;
			-webkit-overflow-scrolling: touch;
			outline: 0;
			text-align: center;
			margin: 0px auto;
		}
		
		.modal-dialog {
			margin-top: 0px;
			display: table-cell;
			vertical-align: middle;
			margin: 0px 20px;
		}
	</style>
</head>
<div class="container">
	<div class="row">
		<div class="col-md-3"><img src="<%=rootPath%>/images/logo.png" class="img-rounded" width="130px" height="130px"></div>
		<div class="col-md-9">
		<!-- <img src="<%=rootPath%>/images/bg1.png" class="img-rounded"> -->
		<ul class="nav nav-pills pull-right">
		   <li>
		   		<a id="no-bg" class="no-bg">您好，欢迎来到戴宗网！</a>
		   </li>
		   <c:if test="${sessionScope.userInfo.loginTimes!=null}">
		    	<li class="user-login-status">
				    <a href="#" class="active">
				    	<c:if test="${sessionScope.userInfo.name!=''}">
				    		<span>${sessionScope.userInfo.name}</span>
				    	</c:if>
				      	<c:if test="${sessionScope.userInfo.name==''}">
				    		<span>${sessionScope.userInfo.mobileNO}</span>
				    	</c:if>
				    </a>
				    <input type="hidden" id="logedIn" name="logedIn" value="YES">
				 </li>
				 <li><a id="no-bg">您已登录 ${sessionScope.userInfo.loginTimes} 次</a></li>
				 <li><a id="btn-signOff" class="pointer" data-toggle="modal" data-target="#signoff-modal">注销</a></li>
		    </c:if>
		    <c:if test="${sessionScope.userInfo.loginTimes==null}">
		    	<li>
				    <a id="btn-login" class="pointer" data-toggle="modal" data-target="#login-modal"> 登录</a>
				</li>
				<li>
				    <a id="btn-regist" href="<%=rootPath%>/page/forwardToRegister">免费注册</a>
				</li>
		    	<li class="list-group user-login-status">
				    <input type="hidden" id="logedIn" name="logedIn" value="NO">
				 </li>
		    </c:if>
		    
		  <li id="download">
		   <a href="<%=rootPath%>/download/brilliance.apk" download="brilliance.apk">下载Android客户端</a>
		  </li>
		</ul>
		</div>
	</div>
</div>

    <!-- Static navbar -->
    <div class="navbar navbar-default navbar-static-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
              <li class="active"><a href="<%=rootPath%>/page/home.html">发快递</a></li>
		      <li  class="dropdown">
		          <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">订单管理操作 <b class="caret"></b></a>
		          <ul class="dropdown-menu">
		            <li><a href="<%=rootPath%>/page/order">我的订单</a></li>
		          </ul>
			  </li>
		      <!-- <li><a href=javascript:void(0)#">评价管理</a></li> 
		      <li><a href="javascript:void(0)">积分管理</a></li>-->
		      <li><a href="<%=rootPath%>/page/progress">快递进度查询</a></li>
		      <li><a href="<%=rootPath%>/page/favouriteAddrss/customizationAddrss">常用快递录入</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
    <!-- 注销提示	开始 -->
	<div id="signoff-modal" class="modal" >
		<div class="modal_wrapper">
			<div class="modal-dialog">
				<form action="<%=rootPath%>/page/signoff" method="post">
					<div class="modal-content">
						<div class="modal-header text-center">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
						</div>
						<div class="modal-body text-center">
							<h4>您确定要离开吗？</h4>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
							<button type="submit" class="btn btn-primary signoff-confirm">是的</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 注销提示	结束 -->
    
    <!-- 登录框 -->
	<div id="login-modal" class="modal fade" tabindex="-1">
		<div class="modal_wrapper">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header text-center">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
					<h2 class="modal-title">登录</h2>
				</div>
				<div class="modal-body input_disable">
					<div class="form-group">
						<div class="col-sm-3">
						</div>
						<div class="col-sm-6 input-group input-group-lg">
					        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span><input type="text" id="mobile"  name="mobile" class="form-control input-lg enterKeyLogin" value="" placeholder="手机号码" required>
						</div>
					</div>
					<div class="form-group" style="margin-bottom:0;">
						<div class="col-sm-3">
						</div>
						<div class="col-sm-6 input-group input-group-lg">
				        	<span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span><input type="password" id="password" name="password" class="form-control input-lg enterKeyLogin" value="" placeholder="密码" required>
				        </div>
			        </div>
			        <div class="form-group" style="margin-bottom:0;">
						<div class="col-sm-3">
						</div>
						<div id="warningInfo" class="col-sm-6">
							
				        </div>
			        </div>
				</div>
				<div class="modal-footer" style="margin-bottom:0;">
					<div class="form-group">
						<div class="col-sm-1">
						</div>
						<div class="col-sm-5">
							<button id="loginBtn" class="btn btn-primary btn-lg btn-block btn-internal-login">登录</button>
						</div>
				        <div class="col-sm-5">
				        	<a href="forwardToRegister" ><button id="regist" class="btn btn-default btn-lg btn-block" >注册</button></a>
						</div>
						<div class="col-sm-1">
						</div>
				    </div>
		      	</div>
			</div>
		</div>
		</div>
	</div>

<script>
	// change menu color
	$(function() {
		var href = window.location.href;
		console.log(href);
		var nav = href.substring(href.lastIndexOf('/') + 1);
		console.log(nav);
		$('.navbar-collapse .nav li').removeClass('active');
		switch (nav) {
		case 'home.html':
			$('.navbar-collapse .nav>li:eq(0)').addClass('active');
			break;
		case 'order':
			$('.navbar-collapse .nav>li:eq(1)').addClass('active');
			break;
		case 'progress':
			$('.navbar-collapse .nav>li:eq(2)').addClass('active');
			break;
		}
	});
	
	
</script>
<script type="text/javascript">
	//验证是否登录
	function validateLogin(){
		if($("#logedIn").val()=="YES"){
			return true;
		}
		$("#login-modal").modal({
			'backdrop':'static',
			'keyboard':true,
			'show':true
		});
		return false;
	}
	
	
	//登陆后改变header用户状态
	function userStatusChange(userInfo){
		var name = userInfo.name;
		if(name==''){
			name = userInfo.mobileNO;
		}
		$(".user-login-status").replaceWith("<li class='user-login-status'>"+
			    "<a href='#' class='active'>"+
			      "<span>"+name+"</span>"+
			    "</a>"+
				    "<input type='hidden' id='logedIn' name='logedIn' value='YES'>"+
				 "</li>"+
				 "<li><a id='no-bg'>您已登录 "+userInfo.loginTimes+" 次</a></li>"
				 );

		//添加注销
		$("#download").before('<li><a id="btn-signOff" class="pointer"  data-toggle="modal" data-target="#signoff-modal">注销</a></li>');
		
	} 
	
	$(function() {
		
		//登陆框出现时，设置用户名为焦点
		$('#login-modal').on('shown.bs.modal', function (e) {
			$("#mobile").focus();
		});
		$('#login-modal').on('hide.bs.modal', function (e) {
			$("#warningInfo").empty();
			$("#mobile").blur();
		});
		//焦点在用户名或密码上时，回车登录
		$(window).keydown(function(e){
			if(e.keyCode==13){
				if($(':focus').is("input")){
					ajaxLogin();
				}
			}
		});
		$(".btn-internal-login").on("click",function(){
			ajaxLogin();
		});
		
		function ajaxLogin(){
			$.ajax({
				type: "POST",
				url: "../page/internalLogin",
				dataType: "json",
				cache:false,
				data:{mobile:$("#mobile").val(),password:$("#password").val()},
				success: function(jsonData){
					if(jsonData.messageType=="info"){
						//登陆后改变header用户状态
						userStatusChange(jsonData.data.userInfo);
						$("#btn-login").css("display","none");
						$("#btn-regist").css("display","none");
						$("#login-modal").modal("hide");
					}else if(jsonData.messageType=="warning"){
						$("#warningInfo").html("<span style='font-weight:bold;color:red;'>"+jsonData.message.messageContent+"</span>");
					}
				}
			});
		}
	});
</script>
    
    