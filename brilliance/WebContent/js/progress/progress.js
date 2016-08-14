/**
 * Author Owens.Mao
 */

//var expressList = {
//	yuantong:'http://baidu.kuaidi100.com/query?type=yuantong&postid=',
//	shentong:'http://baidu.kuaidi100.com/query?type=shentong&postid=',
//	shunfeng:'http://baidu.kuaidi100.com/query?type=shunfeng&postid=',
//	zhongtong:'http://baidu.kuaidi100.com/query?type=zhongtong&postid=',
//	yunda:'http://baidu.kuaidi100.com/query?type=yunda&postid='
//};
//function progressDetail(type,postid){
//	var url = expressList[type]+postid;
//	$.getJSON(url,function(jsonData){
//		//{"status":"403","message":"快递公司参数异常：单号格式错误"}
//		if(jsonData.message!="ok"){//查询失败
//			$(".alert").html(jsonData.message).alert().show();
//			return;
//		}
//		$("tbody").empty();
//		$("#progressDetail").show();
//		$.each(jsonData.data,function(index,item){
//			$("tbody").append("<tr><td>"+item.time+"</td><td>"+item.context+"</td></tr>");
//		});
//		if(jsonData.data==null || josnData.data.length==0){
//			$("tbody").append("<tr><td colspan='2'>暂无快递数据</td></tr>");
//		}
//	});
//}

$(document).ready(function(){
	//init dropdown of express list
	initExpressItems();
	
	$("#searchBtn").on("click",function(){
		var postid = $("#postid").val();
		var type = $('#typeVal').val();
		if(type==""){
			$("#messageAlert").html("请选择快递公司!").alert().show();
			return;
		}
		if(postid==""){
			$("#messageAlert").html("请输入快递单号!").alert().show();
			return;
		}
		$("#messageAlert").hide();
		var url = "/brilliance/page/progress/detail.json";
		//获取快递进度数据
		$.getJSON(url, {code:type,postid:postid,source:"PC"}, function(jsonData){
			if(jsonData.message!="ok"){//查询失败
				$("#messageAlert").html(jsonData.message).alert().show();
				return;
			}
			$("tbody").empty();
			$.each(jsonData.data,function(index,item){
				$("tbody").append("<tr><td>"+item.time+"</td><td>"+item.context+"</td></tr>");
			});
			if(jsonData.data==null || jsonData.data.length==0){
				$("tbody").append("<tr><td colspan='2'>暂无快递数据</td></tr>");
			}
			$("#progressDetail").show();
		});

	});
	/*
	//注册选中快递公司事件
	$(".dropdown-menu a").on("click",function(){
		$(":input[name='typeval']").text($(this).text()).val($(this).attr("typeval"));;
		$(":input[name='type']").val($(this).attr("typeval"));
	})*/
});


function initExpressItems(){
	var str = '<select  id="typeVal" class="form-control" style="width:150px;"><option value="">请选择...</option>';
	$("#expressItems").empty();
	var tmp;
	$.ajax({
		type: "POST",
		url: "../restful/express/getAllExplst",
		dataType: "json",
		success: function(jsonData){
			var dataList = jsonData.data.lstExpress;
			var html;
			$.each(dataList,function(index,item){
					html += '<option value="'+item.postId+'|'+item.expressCode+'">'+item.name+'</option>';
				});
				tmp  = str+html+'</select>';
				$("#expressItems").html(tmp);
		}
		});
}