define(['jquery','bootstrap'],function($,bootstrap){
	$('#employee_id').on('blur',function(){
		var id=$('#employee_id').val().trim();
		if(id.length==0||id.length>8 ||isNaN(id)){
			alert('please input validate employee_id');
		}else{
			alert('employee_id is '+id);
		};
	}).on('focus',function(){
		
		
	});
	$("#login").on('click',function(){
		
		var docheight=$(document).height();
		var docwidth=$(document).width();
		alert(docwidth);
		alert(docheight);
		$('body').append("<div id='greybackground' ></div>");
		$("#greybackground").css({
			
			"opacity":"0.5",
			"height":docheight,
			"width":docwidth
		});
	});
});


/*
$("#login").click(function() {
	$('header').addClass('hidden');
	$("#auth-wrapper").fadeIn("slow", function() {
		// alert("æˆ‘è¢«å¼¹å‡ºæ�¥äº†");
	});
	// èŽ·å�–é¡µé�¢æ–‡æ¡£çš„é«˜åº¦
	var docheight = $(document).height();
	// è¿½åŠ ä¸€ä¸ªå±‚ï¼Œä½¿èƒŒæ™¯å�˜ç�°
	$("body").append("<div id='greybackground'></div>");
	$("#greybackground").css({
		"opacity" : "0.5",
		"height" : docheight
	});
	return false;
});*/