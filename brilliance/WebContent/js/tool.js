/**
 * Author Owens.Mao
 */

$(function() {
	var successAlert = '<div id="successAlert" class="alert alert-success" style="position:fixed;top:200px;left:30%;width:40%;z-index:9999;display:none"></div>';
	var warningAlert = '<div id="warningAlert" class="alert alert-warning" style="position:fixed;top:200px;left:30%;width:40%;z-index:9999;display:none"></div>';
	var errorAlert = '<div id="errorAlert" class="alert alert-danger" style="position:fixed;top:200px;left:30%;width:40%;z-index:9999;display:none"></div>';
	$("body").append(successAlert);
	$("body").append(warningAlert);
	$("body").append(errorAlert);

	/*$(document).ajaxError(function(event, request, settings) {
		showErrorAlert("由于网络问题请求失败,请重试!");
	});*/

});

function isNull(obj) {
	if (typeof obj == 'undefined' || obj == null || obj == ""
			|| obj.length == 0) {
		return true;
	}
	return false;
}

function getStringVal(obj) {
	return isNull(obj) ? "" : obj;
}

function closeAlert(alertID) {
	$("#" + alertID).hide();
}

function closeAlert2(alertID) {
	$("#" + alertID).hide();
	window.location.href="../admin_login";
}

//提示成功
function showSuccessAlert(msg) {
	if (isNull(msg)) {
		$("#successAlert").text("操作成功").alert().show();
	} else {
		$("#successAlert").text(msg).alert().show();
	}
	setTimeout(function() {
		closeAlert("successAlert")
	}, 3000);// 3秒自动隐藏
}
//提示错误
function showErrorAlert(msg) {
	if (isNull(msg)) {
		$("#errorAlert").text("操作失败").alert().show();
	} else {
		$("#errorAlert").text(msg).alert().show();
	}
	setTimeout(function() {
		closeAlert("errorAlert")
	}, 3000);// 3秒自动隐藏
}

//提示警告
function showWarningAlert(msg) {
	if (isNull(msg)) {
		$("#warningAlert").text("操作不合法").alert().show();
	} else {
		$("#warningAlert").text(msg).alert().show();
	}
	setTimeout(function() {
		closeAlert("warningAlert")
	}, 3000);// 3秒自动隐藏
} 

//验证是否登录
function isLogedIn(jsonData){
	if(jsonData.message.messageCode=="00001"){
		$("#warningAlert").text("用户没有操作权限，请登陆后操作").alert().show();
		setTimeout(function() {
			closeAlert2("warningAlert")
		}, 3000);// 3秒自动隐藏
		return true;
	}
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
