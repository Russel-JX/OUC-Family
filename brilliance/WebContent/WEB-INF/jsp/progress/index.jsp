<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE HTML >
<html>
	<head>
		<title>进度查询</title>
		<style>
			.form-group{
				margin-right:15px;
			}
		</style>
		<script type="text/javascript" src="<%=contextPath%>/js/progress/progress.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/js/jquery.iframe-post-form.js"></script>
	</head>
	<body>
		<div class="container">
			<div id="messageAlert" class="alert alert-danger" style="display:none"></div>
			  
			 <div class="panel panel-default">
				  <div class="panel-heading">快递进度查询</div>
				  <div class="panel-body">
		  			<form role="form" style="padding:20px 0px;">

						  <div class="form-group row" >
							 <label for="exampleInputEmail2" class="col-sm-2 control-label">快递公司</label>
							 <div class="col-sm-2" id="expressItems">
							 <!-- 
								 <select class="form-control" id="typeVal" style="width:150px">
								     <option value="">--选择--</option>
						             <option value="shunfeng">顺丰</option>
						             <option value="yuantong">圆通</option>
						             <option value="shentong">申通</option>
						             <option value="zhongtong">中通</option>
						             <option value="yunda">韵达</option>
								 </select>
								  -->
							 </div>
						   </div>
						   
<!-- 						   <div class="form-group row">
						      <div class="btn-group">
							      <button type="button" name="typeval" value="" class="btn btn-default dropdown-toggle" data-toggle="dropdown">-选择-<span class="caret"></span></button>
							      <ul class="dropdown-menu" role="menu">
							        <li><a href="javascript:void(0)" typeval="shunfeng">顺丰</a></li>
							        <li><a href="javascript:void(0)" typeval="yuantong">圆通</a></li>
							        <li><a href="javascript:void(0)" typeval="shentong">申通</a></li>
							        <li><a href="javascript:void(0)" typeval="zhongtong">中通</a></li>
							        <li><a href="javascript:void(0)" typeval="yunda">韵达</a></li>
							      </ul>
						      </div>
						  </div> -->
						  
						  <div class="form-group row">
						    <label  for="exampleInputPassword2" class="col-sm-2 control-label">快递单号</label>
						    <div class="col-sm-2">
						    	<input id="postid"  type="text" class="form-control" placeholder="请输入您的快递单号">
						    </div>
						  </div>
						  <div class="row">
							  <div class="col-sm-8">
							  	<button id="searchBtn" type="button" class="btn btn-primary">查询</button>
							  </div>
						  </div>
						</form>
						<hr/>
						<table class="table table-hover" id="progressDetail" style="display:none">
							<thead>
						        <tr>
						          <th>时间</th>
						          <th>地点和跟踪进度</th>
						        </tr>
					        </thead>
					      	<tbody>
					      		
					      	</tbody>
						</table>
				  </div>
			</div>

			  
			  
			

		</div>
		<%@ include file="../common/footer.jsp"%>
	</body>
</html>