require_init();

define(
		[ 'dt','bootstrapSelect', 'bootstrap', 'jquery' ],
		function(dt,bootS,boot,$){
			/****************fileupload.js多文件上传时，其实发送多个ajax请求，为每个文件的上传都发送一次ajax请求**********
			 * IE10以下不支持多文件上传
			 */
			$(document).ready(function() {
				/*
				 * public FAQ
				 */
				/************？？？？？？？？？？？？？？？？？？session中的用户
				 * 先使用111代替
				 */
				var user = 2;
				
				$('#publicfaq_table').dataTable({
					'sPaginationType' : 'full_numbers',
				});
				//**开启selectpicker功能配置
			 	$('.selectpicker').selectpicker({
			 		//header:"xxxxxxxx",
			 		showSubtext:true,
			 		//title:"Please role that question is asked for",
			 		
	            });
			 	//开启popover功能配置
			 	$('.pop').popover({
			 		"trigger":"manual",//trigger popover to show or hide by hand
			 		"html":true//enable insert html element into the DOM
			 	});
			 	//获得焦点时，隐藏提示
			 	$(".questionName").focusin(function(){
			 		$('.pop').popover('hide');
			 	});
			 	//问题提交按钮
			 	$("#submit").on("click",function(){
			 		//验证问题标题是否填写
			 		//alert($("#questionName").val());
			 		//问题标题
			 		var questionName = $("#questionName").val();
			 		if(questionName==''){
			 			$('.pop').popover('show');
			 			return false;
			 		}
			 		//问题回答者角色
			 		var questionTo = $("#questionTo").val();
			 		//问题描述
			 		var questionDescription = $("#questionDescription").val();
			 		raiseQuestion(user,questionTo,questionName,questionDescription);
			 	});
			 	
			 	//异步提交问题
			 	function raiseQuestion(questionFrom,questionTo,questionName,questionDescription){
			 		$.ajax({
			    		url:'http://localhost:8080/iZone/raiseQuestion.do',
						type:'post',
						dataType:'json',
						cache:false,
						data:{
							questin_from:questionFrom,
							question_to:questionTo,
							question_name:questionName,
							question_description:questionDescription
						},
						beforeSend:function(xhr){
							$("#loading").show();
						},
						complete:function (XMLHttpRequest, textStatus) {
							$("#loading").hide();
						},
						success:function(backData,status){
							//清空输入框内容
							$("#questionName").val("");
							$("#questionDescription").val("");
							//获得焦点
							//$("#questionName").focus();
						},
						error:function(XmlHttpRequest){
							alert('error!!!');
						}
			    	});
			 	}
			 	
			 	
			 	/*
			 	 * OA FAQ
			 	 */
			 	//
			 	$('#oafaq_table').dataTable({
					'sPaginationType' : 'full_numbers',//显示全部分页组件按钮
					//'sAjaxSource':'',//ajax url
					"aaData":[
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.1","name":"Mary","age":"35","address":"SSTT","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.2","name":"Who","age":"90","address":"Suzhou","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.4","name":"Emily","age":"35","address":"Huaian","operation":"<a class='btn btn-primary btn-download' title='view'><i class='glyphicon glyphicon-eye-open'></i></a>"+
				                        		"<a class='btn btn-warning' title='delete' style='margin-left:10%;' href=''><i class='glyphicon glyphicon-remove'></i></a>"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	},
							 	{
							 		"check":"<input type='checkbox'>","S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu","operation":"ffff"
							 	}
							 ],
							 "aoColumns":[
							    {"sTitle":"Select","mData":"check"},
							    {"sTitle":"S/N","mData":"S/N"},
							    {"sTitle":"Question Name","mData":"name"},
							    {"sTitle":"Status","mData":"age"},
							    {"sTitle":"Raise Date","mData":"address"},
							    {"sTitle":"Operation","mData":"operation"}
							 ]
				});
			});
		}
);