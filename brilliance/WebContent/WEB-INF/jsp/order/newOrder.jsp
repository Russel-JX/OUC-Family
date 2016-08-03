<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/header.jsp"%>
<title>新增订单</title>
<link rel="stylesheet" href="<%=rootPath%>/css/newOrder.css"/>
<SCRIPT src="<%=rootPath%>/js/order/newOrder.js" type="text/javascript"></SCRIPT>
<head>
<style type="text/css">
</style>
</head>
<body>
<div class="container">
 <form id="orderForm" class="form-horizontal" role="form"  action="saveOrder" method="post">
     
	<div class="panel panel-default">
	<div class="panel-heading">快递公司名称:<c:out value="${orderInfo.expressName}"/></div>
	</div>

<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-toggle="collapse"
							data-parent="#accordion" href="#collapsePost"> 常用<strong>发货地址</strong> </a>
						<a class="panel-title pull-right add-post-address" data-toggle="modal"
						data-target="#post-modal" style="cursor:pointer;">使用新的发货地址</a>
					</h4>
					
				</div>
				<div id="collapsePost" class="panel-collapse collapse in">
					<div class="panel-body">
						<div class="post-address-record">
							 <c:forEach var="postAddressInfo" items="${postAddressInfos}" varStatus="k">
							 	<div>
								    <label class="alert alert-success post-address-label"><input type="radio" name="fromAddress" />${postAddressInfo.addressDetail}</label>
								    <div class="address_hide">
								    	<div><c:out value="${postAddressInfo.id}"></c:out></div><!-- id -->
								    	<div><c:out value="${postAddressInfo.postName}"></c:out></div><!-- postName -->
								    	<div><c:out value="${postAddressInfo.companyName}"></c:out></div><!-- companyName -->
								    	<div><c:out value="${postAddressInfo.streetName}"></c:out></div><!-- streetName -->
								    	<div><c:out value="${postAddressInfo.tailAddress}"></c:out></div><!-- tailAddress -->
								    	<div><c:out value="${postAddressInfo.mobile}"></c:out></div><!-- mobile -->
								    	<div><c:out value="${postAddressInfo.addressDetail}"></c:out></div><!-- addressDetail -->
								    	
								    	<div><c:out value="${postAddressInfo.postName}"></c:out></div><!-- postName -->
								    </div>
									<div class="btn-group btn-group-lg pull-right">
									  <button type="button" class="btn btn-info btn-post-address-show" >查看</button>
									  <button type="button" class="btn btn-warning btn-post-address-update" >修改</button>
									  <button type="button" class="btn btn-danger post-address-delete" data-toggle="modal" data-target="#post-delete-modal">删除</button>
									</div><br> 
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>
			</div>
			
			<!-- 常用收货地址	开始 -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4 class="panel-title">
						<a data-toggle="collapse" data-toggle="collapse"
							data-parent="#accordion" href="#collapseDeliver"> 常用<strong>收货地址</strong> </a>
					</h4>
					
				</div>
				<div id="collapseDeliver" class="panel-collapse collapse in">
					<div class="panel-body">
						<div class="deliver-address-record">
							 <c:forEach var="deliverAddressInfo" items="${deliverAddressInfos}" varStatus="k">
							 	<div>
								    <label class="alert alert-success deliver-address-label"><input type="radio" name="toAddress" />${deliverAddressInfo.addressDetail}</label>
								    <div class="address_hide">
								    	<div><c:out value="${deliverAddressInfo.id}"></c:out></div><!-- id -->
								    	<div><c:out value="${deliverAddressInfo.deliverName}"></c:out></div><!-- deliverName -->
								    	<div><c:out value="${deliverAddressInfo.companyName}"></c:out></div><!-- companyName -->
								    	<div><c:out value="${deliverAddressInfo.streetName}"></c:out></div><!-- streetName -->
								    	<div><c:out value="${deliverAddressInfo.tailAddress}"></c:out></div><!-- tailAddress -->
								    	<div><c:out value="${deliverAddressInfo.mobile}"></c:out></div><!-- mobile -->
								    	<div><c:out value="${deliverAddressInfo.telephone}"></c:out></div><!-- zipCode+'-'+officeNo+'-'+extendNo==telephone -->
								    </div>
									<div class="btn-group btn-group-lg pull-right">
									  <button type="button" class="btn btn-danger deliver-address-delete" data-toggle="modal" data-target="#deliver-delete-modal">删除</button>
									</div><br> 
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>
			</div>
			<!-- 常用收货地址	结束 -->

			<!-- 发货地址modal	开始 -->
			<div id="post-modal" class="modal fade"  tabindex="-1">
				<div class="modal_wrapper">
					<div class="modal-dialog">
						<div class="modal-content" >
							<div class="hide" id="address_id"></div>
							<div class="hide" id="addressDetail"></div>
							<div class="modal-header text-center">
								<button id="btn_post_cancel" type="button" class="close" data-dismiss="modal"
							aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
								<h3 id="contact_label" class="modal-title">发件人信息</h3>
							</div>
							<div class="modal-body input_disable">
								<div class="form-group">
									<label for="f_Contact" class="col-sm-3 control-label">发件联系人</label>
									<div class="col-sm-3">
										<input type="text" id="f_Contact" name="f_Contact"
											class="form-control" value="" />
									</div>
								</div>
								<div class="form-group">
									<label for="f_companyName" class="col-sm-3 control-label">公司/个人名称</label>
									<div class="col-sm-9">
										<input type="text" id="f_companyName" name="f_companyName"
											class="form-control" value="" />
									</div>
								</div>
								<div class="form-group">
									<label for="fromAddr" class="col-sm-3 control-label">详细发货地址</label>
									<label for="f_streetName" id="fromAddr_label"
										class="col-sm-4 control-label"> <c:out
											value="${orderInfo.from_addr}" />
									</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" id="f_streetName"
											name="f_streetName" placeholder="请输入街道/路名" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-2">
									</div>
									<div class="col-sm-8">
										<input type="text" class="form-control" id="f_houseNo"
											name="f_houseNo" placeholder="请输入厂区名/大楼名/小区名及门牌号" />
									</div>
									<div class="col-sm-2">
									</div>
								</div>
								<div class="form-group">
									<label for="fromAddr" class="col-sm-3 control-label">手机号码</label>
									<div class="col-sm-6">
										<input type="text" id="f_mobileNo" maxlength="11"
											id="f_mobileNo" class="form-control" />
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<p></p>
								<button id="post_cancel" type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" id="add-confirm" class="btn btn-primary add-confirm">保存</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 发货地址modal	结束 -->
			
			<!-- 快递详情summary modal	开始 -->
			<div id="summary-modal" class="modal fade"  tabindex="-1">
				<div class="modal_wrapper" style="text-align:left;">
					<div class="modal-dialog" >
						<div class="modal-content" >
							<div class="modal-header text-center">
								<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
								<h3 id="summary_label" class="modal-title">订单确认</h3>
							</div>
							<div class="modal-body summary-modal-body">
								<div class="list-group">
								  <a href="#" class="list-group-item active">
								    <span>发件人信息</span>
								  </a>
								  <a href="#" class="list-group-item list-group-item-info"><span>发件联系人</span><span id="f_summary_Contact"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>公司/个人名称</span><span id="f_summary_companyName"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>详细发货地址</span><span id="f_summary_detailAddress"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>手机号码</span><span id="f_summary_mobileNo" class="pull-right">张三</span></a>
								</div>
								<div class="list-group">
								  <a href="#" class="list-group-item active">
								    <span>收件人信息</span>
								  </a>
								  <a href="#" class="list-group-item list-group-item-info"><span>收件联系人</span><span id="t_summary_Contact"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>公司/个人名称</span><span id="t_summary_companyName"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>详细收货地址</span><span id="t_summary_detailAddress"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>手机号码</span><span id="t_summary_mobileNo" class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>电话号码</span><span id="t_summary_telephone" class="pull-right">张三</span></a>
								</div>
								<div class="list-group">
								  <a href="#" class="list-group-item active">
								    <span>货物信息</span>
								  </a>
								  <a href="#" class="list-group-item list-group-item-info"><span>货物名称</span><span id="summary_cargoName"  class="pull-right">张三</span></a>
								  <a href="#" class="list-group-item list-group-item-info"><span>包装总件数</span><span id="summary_charge"  class="pull-right">张三</span></a>
								</div>
							</div>
							<div class="modal-footer">
								<p></p>
								<button id="summary_cancel" type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" id="order-confirm" class="btn btn-primary order-confirm">确认</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 快递详情summary modal	结束 -->
			
<!-- 收件人信息	开始 -->
<div class="panel panel-default">
  <div class="panel-heading">收件人信息</div>
  <div class="panel-body">
    <div class="form-group">
        <label for="t_Contact" class="col-sm-2 control-label">收件联系人</label>
        <div class="col-sm-2">
            <input type="text" id="t_Contact" name="t_Contact" class="form-control"/>
        </div>
        <label for="t_companyName" class="col-sm-2 control-label">公司/个人名称</label>
        <div class="col-sm-4">
            <input type="text" id="t_companyName" name="t_companyName" class="form-control"/>
        </div>
    </div>

            
    <div class="form-group">
        <label for="toAddr" class="col-sm-2 control-label">详细收货地址</label>
        <label for="t_streetName" id="toAddr_label" class="col-sm-2 control-label">
             <c:out value="${orderInfo.to_addr}"/>
        </label>
        <div class="col-sm-2">
            <input type="text" id="t_streetName" name="t_streetName" class="form-control" placeholder="请输入街道/路名"/>
        </div>
        <div class="col-sm-4">
            <input type="text" id="t_houseNo" name="t_houseNo" class="form-control" placeholder="请输入厂区名/大楼名/小区名及门牌号"/>
        </div>
    </div>
            
    <div class="form-group">
     <label for="t_mobileNo" class="col-sm-2 control-label">手机号码</label>
         <div class="col-sm-2">
             <input type="text" id="t_mobileNo" name="t_mobileNo" class="form-control"/>
         </div>
     </div>
     <div class="form-group">
         <label for="zipCode" class="col-sm-2 control-label">电话号码</label>
     <div class="col-sm-2">
         <input type="text" id="zipCode" name="zipCode" class="form-control"/>
     </div>
     <label for="officeNo" class="col-sm-1 control-label">-</label>
     <div class="col-sm-2">
         <input type="text" id="officeNo" name="officeNo" class="form-control"/>
     </div>
     <label for="extendNo" class="col-sm-1 control-label">-</label>
     <div class="col-sm-2">
         <input type="text" id="extendNo" name="extendNo" class="form-control"/>
     </div>
    </div>
  </div>
</div>


<div class="panel panel-default">
  <div class="panel-heading">货物基本信息</div>
  <div class="panel-body">
    <div class="form-group">
        <label for="cargoName" class="col-sm-2 control-label">货物名称</label>
        <div class="col-sm-5">
            <input type="text" id="cargoName" name="cargoName"  class="form-control" value=""/>
        </div>
     </div>       
     <div class="form-group">
         <label for="charge" class="col-sm-2 control-label">包装总件数</label>
         <div class="col-sm-2">
               <input type="text" id="charge" name="charge" class="form-control" value="1"/> 
         </div>
         <p class="form-control-static">件</p>
     </div>
            
            <div class="form-group">
                <label for="comment" class="col-sm-2 control-label">注意事项</label>
                <div class="col-sm-5">
                    <textarea class="form-control" rows="3" id="comment" name="comment" value=""></textarea>
                </div>
            </div>
     
  </div>
</div>

     
     <div class="row">
	     <div class="col-md-6">
	     <button type="button" id="pre" class="btn btn-primary">返回</button>
	     </div>
         <div class="col-md-6">
         <button type="button" id="ok"  class="btn btn-primary"> 发   货 </button>
         </div>
     </div>
     
     
     <input type="hidden" id="fromAddr" name="fromAddr" value="${orderInfo.from_addr}"/>
    <input type="hidden" id="toAddr" name="toAddr" value="${orderInfo.to_addr}"/>
    <input type="hidden" id="deliverDays" name="deliverDays" value="${orderInfo.deliverDays}"/>
    <input type="hidden" id="f_provinceId" name="f_provinceId" value="${orderInfo.f_provinceId}"/>
    <input type="hidden" id="f_cityId" name="f_cityId" value="${orderInfo.f_cityId}"/>
    <input type="hidden" id="f_areaId" name="f_areaId" value="${orderInfo.f_areaId}"/>
    <input type="hidden" id="t_provinceId" name="t_provinceId" value="${orderInfo.t_provinceId}"/>
    <input type="hidden" id="t_cityId" name="t_cityId" value="${orderInfo.t_cityId}"/>
    <input type="hidden" id="t_areaId" name="t_areaId" value="${orderInfo.t_areaId}"/>
    <input type="hidden" id="expressName" name="expressName" value="${orderInfo.expressName}"/>
    <input type="hidden" id="expressCode" name="expressCode" value="${orderInfo.expressCode}"/>
 </form>
</div>
	<!-- 删除发货地址警告 -->
	<div id="post-delete-modal" class="modal fade"  tabindex="-1">
				<div class="modal_wrapper">
					<div class="modal-dialog">
						<div class="modal-content" >
							<div class="modal-header text-center">
								<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
								<h3 class="modal-title">警告</h3>
							</div>
							<div class="modal-body text-center">
								<h4>确认删除本发货条地址？</h4>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary post-delete-confirm">删除</button>
							</div>
						</div>
					</div>
				</div>
			</div>
	<!-- 删除收货地址警告 -->
	<div id="deliver-delete-modal" class="modal fade"  tabindex="-1">
				<div class="modal_wrapper">
					<div class="modal-dialog">
						<div class="modal-content" >
							<div class="modal-header text-center">
								<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
								<h3 class="modal-title">警告</h3>
							</div>
							<div class="modal-body text-center">
								<h4>确认删除本条收货地址？</h4>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary deliver-delete-confirm">删除</button>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
<%@ include file="../../jsp/common/footer.jsp"%>
</html>