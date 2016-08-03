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

<script type="text/javascript">
	
</script>

 <style type="text/css">
 		.pointer{
			cursor:pointer;
		}
	 /* Modal居中css */
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

<!-- 百度统计 -->
<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F6d9bdfbfaeda343ea3d1759a94aef181' type='text/javascript'%3E%3C/script%3E"));
</script>

<div class="container">
	<div class="row">
		<div class="col-md-3"><img src="<%=rootPath%>/images/logo.png" class="img-rounded" width="130px" height="130px"></div>
		<div class="col-md-3"></div>
		<div class="col-md-1"></div>
		<div class="col-md-5">
		<%-- <img src="<%=rootPath%>/images/bg1.png" class="img-rounded"> --%>
		<ul class="nav nav-pills">
		   <%-- <li>
		    <a href="<%=rootPath%>/page/admin_index"> 登录</a>
		  </li>
		   <li>
		    <a href="<%=rootPath%>/page/forwardToAdminRegister">注册</a>
		  </li>--%>
		  <li class=" list-group">
		    <a href="#" class="active list-group-item">
		      <span class="badge pull-right"> admin </span>
		      ${sessionScope.adminInfo.name}
		    </a>
		  </li>
		  <li><a href="<%=rootPath%>/page/signoffAdmin" >管理员注销</a></li>
		  <li>
		   <a href="<%=rootPath%>/download/brilliance.apk" download="brilliance.apk">下载Android客户端</a>
		  </li>
		</ul>
		</div>
	</div>
	<%-- <div class="row">
		<nav class="navbar navbar-default navbar-static-top" role="navigation">
			  <div >
				  <ul class="nav navbar-nav">
				      <li><a href="<%=rootPath%>/page/home.html">首页</a></li>
				      <li><a href="<%=rootPath%>/page/userInfo">用户管理</a></li>
				      <li><a href="<%=rootPath%>/page/order">订单管理</a></li>
				      <!-- <li><a href=javascript:void(0)#">评价管理</a></li> 
				      <li><a href="javascript:void(0)">积分管理</a></li>-->
				      <li><a href="<%=rootPath%>/page/progress">快递进度查询</a></li>
				      <li  class="dropdown">
				          <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">数据操作  <b class="caret"></b></a>
				           <ul class="dropdown-menu">
					           <li><a href="<%=rootPath%>/page/forwardToAreas">派送范围数据导入</a></li>
					           <li><a href="<%=rootPath%>/page/forwardToExpressDeliver">价格和配送天数导入</a></li>
					           <li><a href="#">货运公司数据录入</a></li>
				           </ul>
				      
				      
				      </li>
				  </ul>
			  </div>
		</nav>
	</div> --%>
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
		      <li class="active dropdown">
		      	  <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">用户管理  <b class="caret"></b></a>
		      	  <ul class="dropdown-menu">
		            <li><a href="<%=rootPath%>/page/userInfo">用户查询</a></li>
		            <li><a href="<%=rootPath%>/page/forwardToUserStatistics">用户统计</a></li>
		          </ul>	  
		      </li>
		      <!-- <li><a href="<%=rootPath%>/page/order">订单管理</a></li> -->
		      <li  class="dropdown">
		          <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">订单管理操作  <b class="caret"></b></a>
		          <ul class="dropdown-menu">
		            <li><a href="<%=rootPath%>/page/admin_order">我的订单</a></li>
		            <li><a href="<%=rootPath%>/page/orderProcess">订单处理</a></li>
		          </ul>
			  </li>
		      <!-- <li><a href=javascript:void(0)#">评价管理</a></li> 
		      <li><a href="javascript:void(0)">积分管理</a></li>-->
		      <li><a href="<%=rootPath%>/page/admin_progress">快递进度查询</a></li>
		      <li  class="dropdown">
		          <a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown">数据操作  <b class="caret"></b></a>
		          <ul class="dropdown-menu">
		          	<c:if test="${sessionScope.isAdmin==1}">
		          		<li><a href="<%=rootPath%>/page/forwardToAreas">派送范围数据导入</a></li>
			            <li><a href="<%=rootPath%>/page/forwardToExpressDeliver">价格和配送天数导入</a></li>
			            <li><a href="<%=rootPath%>/page/dataUpload/forwardToImportCustAddr">常用快递导入</a></li>
					</c:if>
		            <li><a href="<%=rootPath%>/page/forwardToExpressManage">快递公司管理</a></li>
		            <li><a href="<%=rootPath%>/page/forwardToCustTempAddrImport">常用快递录入</a></li>
		            <li><a href="<%=rootPath%>/page/forwardToCustSuggAddrReview">用户推荐快递审核</a></li>
		          </ul>
			  </li>
			  <li><a href="<%=rootPath%>/page/DiaryInfo/forwardToSearchDiary">戴宗日记管理</a></li>
			  <%-- <li><a href="<%=rootPath%>/page/trackAddress">地址查询</a></li> --%>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>
    
    
    <script>
    	// change menu color
    	$(function(){
    		var href = window.location.href;
    		console.log(href);
    		var nav = href.substring(href.lastIndexOf('/')+1);
    		console.log(nav);
    		$('.navbar-collapse .nav li').removeClass('active');
    		switch(nav){
    			case 'userInfo':
    				$('.navbar-collapse .nav>li:eq(0)').addClass('active');
    				break;
    			case 'forwardToUserStatistics':
    				$('.navbar-collapse .nav>li:eq(0)').addClass('active');
    				break; 
    			case 'admin_order':
    				$('.navbar-collapse .nav>li:eq(1)').addClass('active');
    				break;
    			case 'orderProcess':
    				$('.navbar-collapse .nav>li:eq(1)').addClass('active');
    				break;
    			case 'admin_progress':
    				$('.navbar-collapse .nav>li:eq(2)').addClass('active');
    				break; 
    			case 'forwardToAreas':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break; 
    			case 'forwardToExpressDeliver':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break; 
    			case 'forwardToImportCustAddr':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break; 
    			case 'forwardToExpressManage':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break; 
    			case 'forwardToCustTempAddrImport':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break;
    			case 'forwardToCustSuggAddrReview':
    				$('.navbar-collapse .nav>li:eq(3)').addClass('active');
    				break; 
    			case 'forwardToSearchDiary':
    				$('.navbar-collapse .nav>li:eq(4)').addClass('active');
    				break; 
    		}
    		
    	});
    </script>
    
    