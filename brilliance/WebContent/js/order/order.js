/**
 * Author Owens.Mao
 */

function showDetail(orderCode,userId){
	$.ajax({
		type: "POST",
		url: "/brilliance/page/order/show.json",
		dataType: "json",
		success: function(jsonData){
			if(jsonData.success){
				alert(jsonData.data.order);
				if(!isNull(jsonData.data.order)){
					
					populateDetailModal(jsonData);
					$("#detailModal").modal("show");
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
			}else{
				showErrorAlert(jsonData.message.messageContent);
			}
		},
		data:{orderCode:orderCode,userId:userId}//orderCode=10000&userId=10000
	});
	
	function populateDetailModal(jsonData){
		if(jsonData.data.order!=null){//订单信息不为空,填充订单信息
			var order = jsonData.data.order;
			//刷新订单详细
			$("#orderCode").text(getStringVal(order.orderCode));
			$("#createtime").text(getStringVal(order.createTimeStr));
			$("#expressCode").val(getStringVal(order.expressCode));
			$("#expressName").text(getStringVal(order.expressName));
			$("#amount").text(getStringVal(order.amount));
			$("#statusID").text(getStatusDesc(order.status));
			$("#source").text(getSourceDesc(order.source));
			
			if(order.status==4){//判断状态是否为已经收货,显示评价区域
				var evalution = jsonData.data.evalution;
				if(!isNull(jsonData.data.evalution)){//有评价信息
					//显示评价信息
					$("#evalutionInfo").val(getStringVal(evalution.extraInfo)).attr("disabled",true);
					$("#score").val(getStringVal(evalution.score));
					$("#overall").css("width",((parseInt(evalution.score)*20)+"%"));
					//禁用评价功能
					$(".rating-star a").unbind("click").css("cursor","none");
					$("#confirmBtn").attr("disabled",true);
				}else{//清空评价信息,以便评价
					$("#evalutionInfo").val("").attr("disabled",false);
					$("#score").val("0");
					$("#overall").css("width","0%");
					//绑定评价事件
					$(".rating-star a").on("click",evalutionFunc).css("cursor","hand");
					$("#confirmBtn").attr("disabled",false);
				}
				//显示评价区域
				$("#accordionTwo").show();
				//显示评价按钮
				$("#confirmBtn").show();
			}else{
				//隐藏评价区域
				$("#accordionTwo").hide();
				//隐藏评价按钮
				$("#confirmBtn").hide();
			}
		}
	}
	
	function evalutionFunc(){
		var score = $(this).text();
		$("#overall").css("width",((parseInt(score)*20)+"%"));
		$("#score").val(score);
	}
}

$(function(){
	$("#confirmBtn").on("click",function(){
		if($("#score").val()=="0"){
			showWarningAlert("提交评价之前,请对该订单进行星级打分!");
			return ;
		};
		
		$.ajax({
			type: "POST",
			url: "/brilliance/page/evalution/add.json",
			dataType: "json",
			success: function(jsonData){
				if(jsonData.success){
					showSuccessAlert(jsonData.message.messageContent);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#detailModal").modal("hide");
			},
			data:{orderCode:$("#orderCode").text(),expressCode:$("#expressCode").val(),score:$("#score").val(),extraInfo:$("#evalutionInfo").val()}
		});
		$("#confirmBtn").attr("disabled",true);
	});
});

function getStatusDesc(status){
	if(status==1){
		return "新增单";
	}else if(status==2){
		return "处理中";
	}else if(status==3){
		return "已发货";
	}else{
		return "已收货";
	}
}

function getSourceDesc(source){
	if(source==1){
		return "手工录入";
	}else{
		return "系统下单";
	}
}
