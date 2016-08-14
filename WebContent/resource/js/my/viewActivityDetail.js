require.config({
		baseUrl : '/iZone/resource/js/util',
		paths : {
			jquery : 'jquery-1.9.1.min',
			bootstrap : 'bootstrap.min',
			datatables:'jquery.dataTables.min',
			ckeditor:'ckeditor',
			ckeditor_config:'config',
			ckeditor_lang_en:'en',
		},
		shim :{
				'bootstrap':{
					deps:['jquery']
			}
		}
	});
define(['bootstrap','jquery'],function(boot,$){
	
	var fun = $(function(){
		var replyCommentDiv_show = false;
		$('.addAComment').on('click',function(){
			var comment = $('#comment').val();
			var head_portrait_src = "/iZone/resource/img/head_portrait1\.jpg";//这里可以是一个ajaxcall，获得用户头像的地址
			var reply_date = new Date().toLocaleString();
			var reply_person = "Apple";
			var replied_person = "Banana";
			if(comment != "" && comment != null){
				createAComment($(".comment-list"),head_portrait_src,replied_person,reply_person,reply_date,comment);
				$('#comment').val('');
			}
		});	
		
		var commentJustClick = null;
		
		$('.comment-list').delegate("button[class*='replyComment']",'click',function(){
			//首先slidedown一个可以添加评论的框框
			//如果输入评论框没有显示的话，则显示
			//alert(replyCommentDiv_show);
//			alert(commentJustClick);
//			alert(replyCommentDiv_show);
			if(!replyCommentDiv_show){
				commentJustClick = $(this);
				$(this).parent().slideDown("slow",function(){
					$(this).append(
						"<div style='width: 750px; height: auto; margin-top:10px;' >"+
							"<form style='margin: 0 auto;'>"+
								"<div style='width: 700px; margin-bottom:5px;' class='pull-left addReplyCommentDiv'>"+
									"<textarea style='width: 700px; height: 80px; resize: none;'"+
										"class='replyCommentContent'></textarea>"+
									"<button type='button' class='btn btn-primary btn-sm addReplyComment'>"+
										"<span class='glyphicon glyphicon-pencil'></span>Add reply comment"+
									"</button>"+
								"</div>"+
							"</form>"+
						"</div>"
					);
				});
				replyCommentDiv_show = true;
				$('.replyCommentContent').focus();
				//获得下滑出现的div的信息
				var addReplyCommentDiv = $('.addReplyCommentDiv').get(0);
				//激活body上的鼠标单击事件
				$('body').on('mousedown',function(e){
					 e.preventDefault();
					 e = e || window.event;
					    if(e.button==0 || e.button==1){
					    	//验证鼠标点击的位置是否在生成的评论div中
					    	if(isMouseClickOutsideTheCommentDiv(e,addReplyCommentDiv)){
					    		commentJustClick.parent().children().last().remove();
					    		
					    		//每次评论的div关闭后，都要讲这两个数据进行初始化
								replyCommentDiv_show = false;
								commentJustClick = null;
								
								//解绑body上的mousedown事件
								$('body').unbind('mousedown');
					    	};
					    }/*else if(e.button==2){
					    	alert('mouse right click');
					    }*/
				});
				
			}else{
				commentJustClick.parent().children().last().remove();
				//每次评论的div关闭后，都要讲这两个数据进行初始化
				replyCommentDiv_show = false;
				commentJustClick = null;
				//评论框消失后就要解绑事件
				$('body').unbind('mousedown');
			}
		});
		
		$('.comment-list').delegate("button[class*='addReplyComment']",'click',function(){//button[class*='deleteComment']
			//这里是向数据库ajax异步增加评论的操作
			//想页面追加一个评论
			var comment = $('.replyCommentContent').val();
			var head_portrait_src = "/iZone/resource/img/head_portrait2\.jpg";//这里可以是一个ajaxcall，获得用户头像的地址
			var reply_date = new Date().toLocaleString();
			var reply_person = "Apple";
			var replied_person = "Banana";
			var replyCommentFormDivNode = $(this).parent().parent().parent();
			var temp_media_body = replyCommentFormDivNode.parent().parent().parent();
			var position ;
			//找出评论所存放的位置
			if(temp_media_body.hasClass('media-body')){
				position = temp_media_body;
			}else{
				position = replyCommentFormDivNode.parent();
			}
			if(comment != "" && comment != null){
				createAComment(position,head_portrait_src,replied_person,reply_person,reply_date,comment);
				$('#replyCommentContent').val('');
			}
			//移除增加的回复品评论的框
			replyCommentFormDivNode.remove();
			//每次评论的div关闭后，都要讲这两个数据进行初始化
			replyCommentDiv_show = false;
			commentJustClick = null;
			//评论框消失后就要解绑事件
			$('body').unbind('mousedown');
		});
		
		//为每一个删除按钮设定一个事件
		$('.comment-list').delegate("button[class*='deleteComment']",'click',function(){//button[class*='deleteComment']
			if(confirm('Do you confirm to delete the comment(s)?')){
				var nodeNeedToDelete = $(this).parent().parent();
				nodeNeedToDelete.remove();
			}
		});
		
		//判断鼠标点击的时候是否已经超过评论区域
		function isMouseClickOutsideTheCommentDiv(e,addReplyCommentDiv){
			//这里可得到鼠标X坐标
	    	var wx = e.pageX;
	    	//这里可以得到鼠标Y坐标
	    	var wy = e.pageY;
	    	var d_left = addReplyCommentDiv.offsetLeft;//divz左边距网页最左端的举例
	    	var d_top = addReplyCommentDiv.offsetTop;//
	    	var d_width = addReplyCommentDiv.clientWidth;//div宽度
	    	var d_height = addReplyCommentDiv.clientHeight;//div高度
	    	//alert(wx + '_' + wy + '_' + d_left + '_' + d_width + '_' + d_top + '_' + d_height);
	    	if(wx < d_left || wx > (d_left + d_width) || wy < d_top  || wy > (d_top + d_height)){
	    	   	return true;
	    	}else{
	    		return false;
	    	}
		}
		//一个方法用来生成一个comment的div
		function createAComment(position,head_portrait_src,replied_person,reply_person,reply_date,comment){
			position.append("<div class='media'>"+
				"<a class='pull-left' href='#'> " +
					"<img class='media-object'"+
						"src='"+head_portrait_src +"'"+
						"alt='user head portrait'"+
						"style='width: 64px;'>"+
				"</a>"+
				"<div class='media-body'>"+
					"<h4 class='media-heading'>" + "At " +
						"<span class='reply_date'>"+reply_date+"</span>"+" , "+
						"<span class='replied_person'>"+replied_person+"</span>"+
						"  replied to  "+
						"<span class='reply_person'>"+reply_person+"</span>"+ " : "+
					"</h4>"+
					"<span class='comment_content'>"+
						comment+"&nbsp;"+
						"</span>"+
					"<br/>"+
					"<button type='button' class='btn btn-primary btn-xs replyComment'>Reply</button>"+"&nbsp;"+
					"<button type='button' class='btn btn-warning btn-xs deleteComment'>Delete</button>"+
				"</div>"+
			"</div>");
		}
	});
	return {
		fun :fun 
	};
});
