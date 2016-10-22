/**
 * @author NaDa Mar 18, 2014
 */

require_init();

define([ 'bootstrap', 'jquery' ], function(bootstrap, $) {

	var popoudiv = function() {
		var screenwidth, screenheight, mytop, getPosLeft, getPosTop;
		screenwidth = $(window).width();
		screenheight = $(window).height();
		// 获取滚动条距顶部的偏移
		mytop = $(document).scrollTop();
		// 计算弹出层的left
		getPosLeft = screenwidth / 2 - 260;
		// 计算弹出层的top
		getPosTop = screenheight / 2 - 150;
		// css定位弹出层
		$("#auth-wrapper").css({
			"left" : getPosLeft,
			"top" : getPosTop
		});

		// 当浏览器窗口大小改变时...
		$(window).resize(function() {
			screenwidth = $(window).width();
			screenheight = $(window).height();
			mytop = $(document).scrollTop();
			getPosLeft = screenwidth / 2 - 260;
			getPosTop = screenheight / 2 - 150;
			$("#auth-wrapper").css({
				"left" : getPosLeft,
				"top" : getPosTop + mytop
			});
		});

		// 当拉动滚动条时...
		$(window).scroll(function() {
			screenwidth = $(window).width();
			screenheight = $(window).height();
			mytop = $(document).scrollTop();
			getPosLeft = screenwidth / 2 - 260;
			getPosTop = screenheight / 2 - 150;
			$("#auth-wrapper").css({
				"left" : getPosLeft,
				"top" : getPosTop + mytop
			});
		});

		// 点击链接弹出窗口
		$("#login").click(function() {
			$('header').addClass('hidden');
			$("#auth-wrapper").fadeIn("slow", function() {
				// alert("我被弹出来了");
			});
			// 获取页面文档的高度
			var docheight = $(document).height();
			// 追加一个层，使背景变灰
			$("body").append("<div id='greybackground'></div>");
			$("#greybackground").css({
				"opacity" : "0.5",
				"height" : docheight
			});
			return false;
		});

		// 点击关闭按钮
		$("#login_closeBtn").click(function() {
			$("#auth-wrapper").hide();
			// 删除变灰的层
			$("#greybackground").remove();
			$('header').removeClass('hidden');
			$("#empid_input").popover('hide');
			$("#pwd_input").popover('hide');
			$('#login_btn').popover('hide');
			return false;
		});
	};

	// 加载完成函数
	var init = $(function() {
		popoudiv();
		$('#accountmenu').on('mouseenter', function() {
			$('#accountdrop').dropdown('toggle');
		});
		$('#accountdrop').on('mouseleave', function() {
			$('#accountdrop').dropdown('toggle');
		});

		$('#empid_input').on('blur', function() {
			var id = $('#empid_input').val().trim();
			if (id.length == 0 || id.length > 8 || isNaN(id)) {
				$("#empid_input").popover('show');
			} else {
				$("#empid_input").popover('hide');
			}
		}).on('focus', function() {
			var id = $('#empid_input').val().trim();
			if (id.length == 0 || id.length > 8 || isNaN(id)) {
				$("#empid_input").popover('show');
			} else {
				$("#empid_input").popover('hide');
			}
		});

		$('#pwd_input').on('blur', function() {
			var pwd = $('#pwd_input').val().trim();
			if (pwd.length == 0) {
				$("#pwd_input").popover('show');
			} else {
				$("#pwd_input").popover('hide');
			}
		}).on('focus', function() {
			var pwd = $('#pwd_input').val().trim();
			if (pwd.length == 0) {
				$("#pwd_input").popover('show');
			} else {
				$("#pwd_input").popover('hide');
			}
		});
		// 登陆按钮事件
		$('#login_btn').on('click', function() {
			if (login_check()) {
				$.ajax({
					type : "POST",
					url : "/iZone/firstPage/login",
					cache : false,
					dataType : "JSON",
					data : {
						"empID" : $('#empid_input').val(),
						"password" : $('#pwd_input').val()
					},
					success : function(msg) {
						switch (parseInt(msg.type)) {
						case 0:
							alert("登陆成功");
							var emp=msg.value;
							$('#login_closeBtn').click();
							$('#login_before').addClass('hidden');
							$('#login_after').removeClass('hidden');
							$('#emp_info_name').text(emp.emp_EN_name);
							$('#emp_info_head').prop('src',emp.emp_head_portrait);
							break;
						case 1:
							alert("用户名不正确！");
							break;
						case 2:
							alert("密码不正确！");
							break;
						default:
							break;
						}
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						alert("登陆失败，请刷新页面重试\n错误!:" + textStatus);
						alert(XMLHttpRequest.status);
						alert(XMLHttpRequest.readyState);
						alert(textStatus);
						alert(errorThrown);
					},
				});
			} else {

			}
		});

		// 用户注销
		$('#info_exit').on('click', function() {
			// 需要清除COOKIE,然后重新载入主页

			// 以下测试
			$('#login_after').addClass('hidden');
			$('#login_before').removeClass('hidden');
		});
	});

	// 登陆验证模块
	var login_check = function() {
		var id = $('#empid_input').val().trim();
		var pwd = $('#pwd_input').val().trim();
		if (id.length == 0 || id.length > 8 || isNaN(id)) {
			$("#empid_input").popover('show');
			return false;
		} else {
			$("#empid_input").popover('hide');
		}

		if (pwd.length == 0) {
			$("#pwd_input").popover('show');
			return false;
		} else {
			$("#pwd_input").popover('hide');
		}

		return true;
	};

	return {
		init : init,
		popoudiv : popoudiv,
		login_check : login_check
	};
});
