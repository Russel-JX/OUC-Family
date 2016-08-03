/**
 * Author Russel
 */


$(function(){
	var addressAddID=-1;
	var addressAddEle = null;
	var addressDeleteID=-2;
	var addressDeleteEle = null;
	var addOrUpdateFlag = "add";
	
	var selectedPostAddress = null;//被选择的将要作为订单的发货地址
	var selectedPostAddressID = null;
	
	var this_btn = null;//点中的“修改”或“添加发货地址”按钮
	var address_hide = null;//对应的地址信息div
	var addresses = null;//对应的地址信息
	//选择的发货地址
//	$("body").on("click",".post-address-label",function(){
//		selectedPostAddress = $(this).siblings("div")[0];
//		selectedPostAddressID = $($($(this).siblings("div")[0]).children()[0]).text();
//		//alert($($(selectedPostAddress).children()[4]).text());
//	});
	//增加或修改发货地址
	$(".add-confirm").on("click",function(e){
		//console.log(addressDeleteID+"=="+addressDeleteEle.html());fromAddr
		//前端验证...
			
		//所填地址
		if(addOrUpdateFlag=="add"){
			$.ajax({
				type: "POST",
				url: "../page/address/savePostAddress",
				dataType: "json",
				data:{expressCode:$("#expressCode").val(),f_Contact:$("#f_Contact").val(),f_companyName:$("#f_companyName").val(),fromAddr:$("#fromAddr").val(),f_provinceId:$("#f_provinceId").val(),f_cityId:$("#f_cityId").val(),f_areaId:$("#f_areaId").val(),f_streetName:$("#f_streetName").val(),f_houseNo:$("#f_houseNo").val(),f_mobileNo:$("#f_mobileNo").val()},
				success: function(jsonData){
					if(jsonData.success){
						//页面添加新地址
						addAddress(jsonData.data.postAddressInfo);
					}else{
						showErrorAlert(jsonData.message.messageContent);
					}
					$("#post-modal").modal("hide");
				}
			});
		}else if(addOrUpdateFlag=="update"){
			addOrUpdateFlag = "add";
			$.ajax({
				type: "POST",
				url: "../page/address/updateAddress",
				dataType: "json",
				data:{id:$(addresses[0]).text(),expressCode:$("#expressCode").val(),f_Contact:$("#f_Contact").val(),f_companyName:$("#f_companyName").val(),f_streetName:$("#f_streetName").val(),f_houseNo:$("#f_houseNo").val(),f_mobileNo:$("#f_mobileNo").val(),fromAddr:$("#fromAddr").val(),f_provinceId:$("#f_provinceId").val(),f_cityId:$("#f_cityId").val(),f_areaId:$("#f_areaId").val()},
				success: function(jsonData){
					if(jsonData.success){
						//跟新DOM地址
						updateNewAddress(this_btn,jsonData);
					}else{
						showErrorAlert(jsonData.message.messageContent);
					}
					$("#post-modal").modal("hide");
				}
			});
		}
	});
	//跟新更新后的隐藏发货地址
	function updateNewAddress(this_btn,jsonData){
		var new_address_hide = $(this_btn).parent().siblings("div");
		var new_address_label = $(this_btn).parent().siblings("label");
		var new_addresses = $(new_address_hide).children();
		$(new_addresses[0]).text(jsonData.data.postAddressInfo.id);
		$(new_addresses[1]).text(jsonData.data.postAddressInfo.postName);
		$(new_addresses[2]).text(jsonData.data.postAddressInfo.companyName);
		$(new_addresses[3]).text(jsonData.data.postAddressInfo.streetName);
		$(new_addresses[4]).text(jsonData.data.postAddressInfo.itailAddressd);
		$(new_addresses[5]).text(jsonData.data.postAddressInfo.mobile);
		$(new_addresses[6]).text(jsonData.data.postAddressInfo.addressDetail);
		
		$(new_address_label).html('<input type="radio" name="fromAddress" />'+jsonData.data.postAddressInfo.addressDetail);
		
		
	}
	
	
	//删除发货地址警告
	$("body").on("click",".post-address-delete",function(){
		addressDeleteEle = $(this).parent().parent();
		addressDeleteID= $($(this).parent().siblings("div").children()[0]).text();
		//如果刪除的发货地址是，刚才勾选的发货地址。
//		alert(addressDeleteID+"|"+selectedPostAddressID);
		if(addressDeleteID==selectedPostAddressID){
			selectedPostAddress = null;
		}
	});
	$(".post-delete-confirm").on("click",function(){
		//console.log(addressDeleteID+"=="+addressDeleteEle.html());
		$.ajax({
			type: "POST",
			url: "../page/address/deletePostAddress",
			dataType: "json",
			success: function(jsonData){
				if(jsonData.success){
					//页面删除元素
					$(addressDeleteEle).remove();
//					showSuccessAlert(jsonData.message.messageContent);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#post-delete-modal").modal("hide");
			},
			data:{addressCode:addressDeleteID}
		});
	});
	//增加发货地址
	$(".add-post-address").on("click",function(){
		addOrUpdateFlag = "add";
	});
	//查看发货地址详细
	$("body").on("click",".btn-post-address-show",function(){
		this_btn = this;
		address_hide = $(this_btn).parent().siblings("div");
		addresses = $(address_hide).children();
		setModal(this_btn,addresses);
	});
	//修改发货地址详细
	$("body").on("click",".btn-post-address-update",function(){
		addOrUpdateFlag = "update";
		this_btn = this;
		address_hide = $(this_btn).parent().siblings("div");
		addresses = $(address_hide).children();//addresses是第一手的后台而来的参数。
		setModal(this_btn,addresses);
	});
//	$('#post-modal').on('show.bs.modal', function (e) {
//		//所点击的按钮元素
//		var this_btn = e.relatedTarget;
//		setModal(this_btn);
//		//alert($(e.relatedTarget).attr("class"));
//	});
//	$('#post-modal').on('shown.bs.modal', function (e) {
//		$(".add-confirm").val("添加ggg");
//	});
//	$('#post-modal').on('hidden.bs.modal', function (e) {
//		$(".add-confirm").val("添加fff");
//	});
	function setModal(this_btn,addresses){
		$("#post-modal *").attr("disabled",false);
		$("#address_id").val($(addresses[0]).text());
		$("#f_Contact").val($(addresses[1]).text());
		$("#f_companyName").val($(addresses[2]).text());
		$("#f_streetName").val($(addresses[3]).text());
		$("#f_houseNo").val($(addresses[4]).text());
		$("#f_mobileNo").val($(addresses[5]).text());
		if($(this_btn).attr("class").indexOf("btn-post-address-show")>-1){
			$("#post-modal *").attr("disabled","disabled");
			$("#post_cancel").attr("disabled",false);
			$("#btn_post_cancel").attr("disabled",false);
		}
		if($(this_btn).attr("class").indexOf("btn-post-address-update")>-1){
			$(".add-confirm").val("修改");
		}
		$('#post-modal').modal('show');
		//console.log($(addresses[0]).text());
	}
	function addAddress(postAddressInfoJson){
		//将以选中的置为未选中。。。
		$("input:radio[name='fromAddress']").attr("checked","");
		//新地址
		var newAddressDiv = '<div>'+
						    '<label class="alert alert-success post-address-label"><input type="radio" name="fromAddress" checked="checked" />'+postAddressInfoJson.addressDetail+'</label>'+
						    '<div class="address_hide">'+
						    	'<div>'+postAddressInfoJson.id+'</div>'+
						    	'<div>'+postAddressInfoJson.postName+'</div>'+
						    	'<div>'+postAddressInfoJson.companyName+'</div>'+
						    	'<div>'+postAddressInfoJson.streetName+'</div>'+
						    	'<div>'+postAddressInfoJson.tailAddress+'</div>'+
						    	'<div>'+postAddressInfoJson.mobile+'</div>'+
						    	'<div>'+postAddressInfoJson.addressDetail+'</div>'+
						    	'<div>'+postAddressInfoJson.postName+'</div>'+
						    '</div>'+
							'<div class="btn-group btn-group-lg pull-right">'+
							  '<button type="button" class="btn btn-info btn-post-address-show">查看</button>'+
							  '<button type="button" class="btn btn-warning btn-post-address-update">修改</button>'+
							  '<button type="button" class="btn btn-danger post-address-delete" data-toggle="modal" data-target="#post-delete-modal">删除</button>'+
							'</div><br>'+
						'</div>';
		//页面增加元素
		$(".post-address-record").prepend(newAddressDiv);
	}
	//监听被选中的发货label
//	$("input[name='fromAddress']").on("change",function(e){
//		alert("YES"+e.target);
//	});
	//获取用户选择的发货label的信息
	function getCurrentPostAddress(){
		var selectedPostLabel = $("input:radio[name='fromAddress']:checked").parent();
		var hidePostAddress = $(selectedPostLabel).siblings("div")[0];
		var selectedPostAddressIDiv = $(hidePostAddress).children();
		
		var selectedPostAddressInfo = new PostAddressInfo( $(selectedPostAddressIDiv[7]).text(),$(selectedPostAddressIDiv[2]).text(),$(selectedPostAddressIDiv[4]).text(),$(selectedPostAddressIDiv[3]).text(),$(selectedPostAddressIDiv[5]).text());
//		alert(selectedPostAddressInfo.fPostName+"|"+selectedPostAddressInfo.fCompanyName+"|"+selectedPostAddressInfo.fHouseNo+"|"+selectedPostAddressInfo.fStreetName+"|"+selectedPostAddressInfo.fMobileNo);
		
		return selectedPostAddressInfo;
	}
	//发货地址类
	function PostAddressInfo(fPostName,fCompanyName,fHouseNo,fStreetName,fMobileNo){
		this.fPostName = fPostName;
		this.fCompanyName = fCompanyName;
		this.fHouseNo = fHouseNo;
		this.fStreetName = fStreetName;
		this.fMobileNo = fMobileNo;
	}
	
	/**
	 * 收货地址
	 */
	var selectedDeliverAddressEle=null;//选择的收货地址元素
	var newDeliverAddressFlag=true;//是否是新的收货地址
	var deliverAddresses=null;
	
	var deliverAddressDeleteEle=null;
	var deliverAddressDeleteID=-2;

	//选择的收货地址
	$(".deliver-address-label").on("change",function(){
		selectedDeliverAddressEle = $(this).siblings("div")[0];
		deliverAddresses = $(selectedDeliverAddressEle).children();
		var telephone = ($(deliverAddresses[6]).text()).split("-");
//		alert(telephone[0]+"--"+telephone[1]+"--"+telephone[2]);
		$("#t_Contact").val($(deliverAddresses[1]).text());
		$("#t_companyName").val($(deliverAddresses[2]).text());
		$("#t_streetName").val($(deliverAddresses[3]).text());
		$("#t_houseNo").val($(deliverAddresses[4]).text());
		$("#t_mobileNo").val($(deliverAddresses[5]).text());
		$("#zipCode").val(telephone[0]);
		$("#officeNo").val(telephone[1]);
		$("#extendNo").val(telephone[2]);
	});
	//查看收货地址详细
	$(".btn-deliver-address-show").on("click",function(){
		this_btn = this;
		address_hide = $(this_btn).parent().siblings("div");
		addresses = $(address_hide).children();
		setModal(this_btn,addresses);
	});
	//删除收货地址警告
	$(".deliver-address-delete").on("click",function(){
		deliverAddressDeleteEle = $(this).parent().parent();
		deliverAddressDeleteID= $($(this).parent().siblings("div").children()[0]).text();
	});
	$(".deliver-delete-confirm").on("click",function(){
		//console.log(addressDeleteID+"=="+addressDeleteEle.html());
		$.ajax({
			type: "POST",
			url: "../page/address/deleteDeliverAddress",
			dataType: "json",
			success: function(jsonData){
				if(jsonData.success){
					//页面删除元素
					$(deliverAddressDeleteEle).remove();
//					showSuccessAlert(jsonData.message.messageContent);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#deliver-delete-modal").modal("hide");
			},
			data:{addressCode:deliverAddressDeleteID}
		});
	});
	//"确认发货"前的检查
	$("#ok").on("click",function(){
		if(!postAddressValid()){
			alert("请选择发货地址！");
			return false;
		}
		//检查收件人信息是否完整...
		if(!deliverAddressValid()){
			alert("请选择收货地址 或 填写新的收货地址！");
			return false;
		}
		//检验货物信息是否完整
		if(!validateCargo()){
			alert("请填写货物基本信息！");
			return false;
		}
		//发货地址
		var selectedPostAddressInfo = getCurrentPostAddress();
		//弹出summary
		fillSummaryModal(selectedPostAddressInfo);
	});
	//确认发货
	$("#order-confirm").on("click",function(){
		var selectedPostAddressInfo = getCurrentPostAddress();
		
		//判断是已有的收货地址 还是 新的收货地址
		newDeliverAddressFlag = judgeNewDeliverAddress();
		
		var orderForm = document.getElementById("orderForm");
		orderForm.method = "post";
		orderForm.action = "saveOrder?newDeliverAddressFlag="+newDeliverAddressFlag+"&fPostName="+selectedPostAddressInfo.fPostName+"&fCompanyName="+selectedPostAddressInfo.fCompanyName+"&fHouseNo="+selectedPostAddressInfo.fHouseNo+"&fStreetName="+selectedPostAddressInfo.fStreetName+"&fMobileNo="+selectedPostAddressInfo.fMobileNo;
		orderForm.submit();
	});
	function createOrder(){
		
	} 
	//summary-modal中赋值。选中的label的值为Post，收件人信息为Deliver
	function fillSummaryModal(currentPostAddressInfo){
		$('#summary-modal').modal('show');
		
		$("#f_summary_Contact").text(currentPostAddressInfo.fPostName);
		$("#f_summary_companyName").text(currentPostAddressInfo.fCompanyName);
		$("#f_summary_detailAddress").text($("#fromAddr").val()+","+currentPostAddressInfo.fStreetName+","+currentPostAddressInfo.fHouseNo);
		$("#f_summary_mobileNo").text(currentPostAddressInfo.fMobileNo);
		
		$("#t_summary_Contact").text($("#t_Contact").val());
		$("#t_summary_companyName").text($("#t_companyName").val());
		$("#t_summary_detailAddress").text($("#toAddr").val()+","+$("#t_streetName").val()+","+$("#t_houseNo").val());
		$("#t_summary_mobileNo").text($("#t_mobileNo").val());
		$("#t_summary_telephone").text($("#zipCode").val()+"-"+$("#officeNo").val()+"-"+$("#extendNo").val());
		
		$("#summary_cargoName").text($("#cargoName").val());
		$("#summary_charge").text($("#charge").val());
	}
	//验证是否选择了发货地址
	function postAddressValid(){
		if($("input:radio[name='fromAddress']:checked").length>0){
			return true;
		}
		return false;
	}
	
	//验证是否使用了收货地址(联系人、街道、门牌号、手机号码必填)
	function deliverAddressValid(){
		if($("#t_Contact").val()&&$("#t_streetName").val()&&$("#t_houseNo").val()&&$("#t_mobileNo").val()){
			return true;
		}
		return false;
	}
	//检验货物信息是否完整
	function validateCargo(){
		if($("#cargoName").val()&&$("#charge").val()){
			return true;
		}
		return false;
	}
	//判断是否是新的收货地址
	function judgeNewDeliverAddress(){
		if(deliverAddresses){//已选择了已有地址
			if(($("#t_Contact").val()==$(deliverAddresses[1]).text())&&($("#t_companyName").val()==$(deliverAddresses[2]).text())&&
					($("#t_streetName").val()==$(deliverAddresses[3]).text())&&($("#t_houseNo").val()&&$(deliverAddresses[4]).text())&&
					($("#t_mobileNo").val()==$(deliverAddresses[5]).text())&&(($("#zipCode").val()+"-"+$("#officeNo").val()+"-"+$("#extendNo").val())==$(deliverAddresses[6]).text())){
				newDeliverAddressFlag = false; 
			}else{
				newDeliverAddressFlag = true;
			}
		}
		return newDeliverAddressFlag;
	}
	
	//返回按钮
	$("#pre").on("click",function(){
		var orderForm = document.getElementById("orderForm");
		orderForm.method = "post";
		orderForm.action = "returnHome";
		orderForm.submit();
	});
});





