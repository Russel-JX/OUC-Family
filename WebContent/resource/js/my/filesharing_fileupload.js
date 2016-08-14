require_init();

define(
		[ 'mockjax','bootEdit','jit','bootstrapSelect','Jfileupload', 'jquery.ui.widget', 'bootstrap', 'jquery' ],
		function(mockjax,bootEdit,it,bootS,jfu,juiw,boot,$){
			/****************fileupload.js多文件上传时，其实发送多个ajax请求，为每个文件的上传都发送一次ajax请求**********
			 * IE10以下不支持多文件上传
			 */
			$(document).ready(function() {
					//mockjax模拟ajax相应
					$.mockjax({
						url:"/mockEdit",
						responseTime:200/*,
						responseText:{vv:"I'm a string from fake server!"}*/
					});
					
					//要删除的文件元素
					var file_to_delete = -1;
					$("#uploaded-files").on("click",".btn-delete",function(){
						$("#deleteModal").modal({
							'backdrop':true,
							'keyboard':true,
							'show':true
						});
						file_to_delete = $(this).attr("id");
					});
					//删除文件
					$("#deleting").on("click",function(){
						//要删除的文件ID
						var id = file_to_delete;
						//alert("deleting id="+id+"这一行是： "+$("tr[class='"+id+"']").html());
						$.ajax({
							url:'http://localhost:8080/iZone/remove.do?id='+id,
							type:'post',
							dataType:'json',
							cache:false,
							beforeSend:function(xhr){
								$("#loading").show();
							},
							complete:function (XMLHttpRequest, textStatus) {
								$("#loading").hide();
							},
							success:function(backData,status){
								//删除这一行（每行tr，使用文件的id作为class属性值标识）
								$("tr").remove("tr[class='"+id+"']");
								$("#deleteModal").modal("hide");
							},
							error:function(XmlHttpRequest){
								alert('delete failed!!!');
							}
						});
					});
					
					
					//开启可编辑配置
					function enableEditable(){
						//只有文件真实名称、文件类型和文件描述的单元格可编辑
						$("#uploaded-files .editable-cell").editable({//#uploaded-files td:not(.down)
							type:'text',
							url:'http://localhost:8080/iZone/edit.do',//修改文件描述。/mockEdit
							mode:'inline',
							toggle:'dblclick',
							title:'Enter new value'
						});
					}
				
					//**开启selectpicker功能配置
				 	$('.selectpicker').selectpicker({
		            });
				 	
				 	//点击此区域时，也出现选择文件窗口，在前台增加待上传的文件
				 	$('#dropzone').dblclick(function(){
				        $(this).parent().find('input').click();
				    });
				
				    //文件上传地址
				   	var url = 'http://localhost:8080/iZone/upload.do';
				    //全局变量。前台已添加的文件列表数据源(数组)
				    var fileList=[];
				    
				    //初始化，主要是设置上传参数，以及事件处理方法(回调函数)
				    $('#fileupload').fileupload({
				    	//multipart:false,//是否多文件上传。默认为true
				        autoUpload: false,//是否自动上传。默认为true。这里设置为添加文件后，手动点击按钮上传！
				        url: url,//上传地址
				        dataType: 'json',//text
				        /*add: function(e,data){
				        	//data.submit() method must be called,or file will not be uploaded!
				        	data.submit();
				        },*/
				        send: function(e,data){//上传请求
				        	//上传时，显示进度条
				        	
				        },
				        /*
				         * 设置文件上传完毕事件的回调函数；多文件上传时，当使用默认的方式上传时（data.submit()），每个文件的上传都会回调；
				         * 使用send时，只多个文件只发送一次ajax请求，并只执行一次次回调函数。
				         */
				        done: function (e, data) {
				        	//多个文件集中提示上传状态
				        	var file_names = '';
				        	//***************************java中写一个对象，封装返回的json的格式。如成功与否，数据，其他消息...**********************
				        	//********************IE中报错，文件最大上传大小的错误；谷歌没有******************
				        	
							///var downloadButton = "<button type='button' class='btn btn-primary'><i class='glyphicon glyphicon-download'></i>download</button>";
				            $.each(data.result, function (index, file) {
				                //$('<p/>').text(file.fileName).appendTo('#files');
				                $("#uploaded-files").append(
				                        $('<tr/>')
				                        .append($('<td/>').text(file.fileName))
				                        .append($('<td/>').text(file.fileRealName))
				                        .append($('<td/>').text(file.fileType))
				                        .append($('<td/>').text(file.uploadedDate))
				                        .append($('<td/>').text(file.fileFrom))
				                        .append($('<td/>').text(file.fileDescription))
				                        //.append($('<td/>').html(downloadButton))
				                        .append($('<td class="down" />').html("<a class='btn btn-primary btn-download' href='http://localhost:8080/iZone/download.do?fileName="+file.fileName+"&fileRealName="+file.fileRealName+"&fileType="+file.fileType+"'><i class='glyphicon glyphicon-download'></i></a>"+
				                        		"<a class='btn btn-warning' style='margin-left:10%;' href='http://localhost:8080/iZone/remove.do?id="+file.id+"'><i class='glyphicon glyphicon-remove'></i></a>"))
				                );
				                file_names = "File Name:"+file.fileName+"<br />"+file_names;
				            });
				            
				            uploadStatus("'label label-success'",file_names,"Upload Success!");
				            //alert("文件名集合是："+file_names);
				        },
				        //success, abort or error
				        always:function(e,data){
				        	//清空前端的文件列表
				        	fileList = [];
				        },
				        fail:function(e,data){
				        	//上传失败提示
				        	uploadStatus("'label label-danger'",'Oops! File upload Failed,please check the size of your files','');
				        },
				        progressall: function (e, data) {//设置上传进度事件的回调函数
				        	//alert(33);
				            var progress = parseInt(data.loaded / data.total * 100, 10);//parseInt(A,B);把字符串A,按照多少进制(B进制)转成10进制的数字。
				            $('#progress .progress-bar').css(
				                'width',
				                progress + '%'
				            );
				        },
				        dropZone: $('#dropzone')
				    });
				    
				    //上传状态显示
				    function uploadStatus(divClass,fileNames,status){
				    	//var divClass = 'class="label label-'+status;
				    	$('#upload_status').html("<div class="+divClass+" style='width:100%;'>"+fileNames+status+"</div>");
				    	 //上传成功，渐变提示；并将进度条初始化
			            $('#upload_status').fadeIn(1500,function(){
			            	$('#progress').addClass('progress-bar-hide');
			            	$('#progress .progress-bar').css('width',0 + '%');
			            });
			            $('#upload_status').fadeOut(2000);
				    }
				    
				    //****用户通过点击input按钮或拖拽文件添加文件后，回调此函数（将按钮绑定点击事件，提交已经添加的文件）***
				    //上传时，无需前段验证（因为文件类型必选一个，文件描述可为空）
				    $('#fileupload').bind('fileuploadadd', function (e, data) {
				    	/*$("#upload").on('click', function () {
				            data.submit();
				        });*/
				    	
				    	//向前台的文件列表变量中添加新的文件
				    	for(var i = 0; i < data.files.length; i++){
				            fileList.push(data.files[i]);
				        }
				    });
				    //文件上传时，传递其他表单数据
				   /* $('#fileupload').bind('fileuploadstart', function (e, data) {
				    	$('#progress').removeClass('progress-bar-hide');
				    	var file_upload_type = $('#file_upload_type').val();
				    	var file_description = $('#file_description').val();
				        data.formData = {
				        					file_upload_type: file_upload_type,
				        					file_description:file_description
				        				};
				    });*/
				    
				    //点击下载按钮下载
				    /*$(".btn-download").on('click',function(){
				    	alert('yeah');
				    	
				    });*/
				    
				    //手动直接上传（不用使用在add方法的毁掉函数中，使用data.submit方法）
				    $("#send").on('click', function () {
				    	//有待上传的文件，隐藏进度条
				    	if(fileList.length!=0){
				    		$('#progress').removeClass('progress-bar-hide');
				    	}
				    	var file_upload_type = $('#file_upload_type').val();
				    	var file_description = $('#file_description').val();
				    	//上传文件和其他白哦单数据（文件类型和文件描述）
				    	$('#fileupload').fileupload('send',{
				    											files:fileList,
				    											formData:{
												        					file_upload_type: file_upload_type,
												        					file_description:file_description
				    													 }
				    										}
				    								);
			        });
				    //回车触发请求
				    //$("#keywords").keypress('13',searchFile(1,4));
			    	
				    //默认当前页为高亮的页
				    //var hightLightPage = parseInt($("ul .active").children().text());
				    //总页数
			    	var totalPages=0;
			    	//每页记录数
			    	var pageSize = 3;
				    //当前中间的页号(第六个数字)
			    	var curMiddlePage;
			    	//获取当前点击的页号（初始化为1）
			    	var curPage=1;
			    	//当前最小的页号
			    	var curMinPage;
			    	//当前最大的页号
			    	var curMaxPage;
				    //alert(hightPage);
					//***打开页面，首先加载指定类型下的文档
					searchFile(1,0);
					 //按钮为灰色或高亮时，取消点击事件
				    function stopClickEvent(currentElement){
				    	//alert("当前元素的class是："+$(currentElement).attr("class"));
				    	if($(currentElement).hasClass("disabled")||$(currentElement).hasClass("active")){
				    		return false;//已经失效或已经选中，则停止ajax查询
				    	}else{
				    		return true;
				    	}
				    }
				    /*分页按钮统一控制（查询和显示样式控制）        P.S.:***高亮的页就是当前页***
				     * 1.默认情况下，点击任意分页按钮都将：
				     * 		1.1.ajax查询数据
				     * 		1.2.重新排列页号
				     * 		1.3.控制显示样式(按钮变灰与否)
				     * 2.灰色按钮（class="disabled"）或高亮按钮（class="active"），点击时不能ajax查询（return false），也不用做任何操作。
				     * 3.已经是第一页或最后一页时，相应的上一页、第一页和下一页、最后一页为灰色（class="disabled"）
				     * 4.变化文档类型和输入框查询ajax时
				     * 		4.1ajax查询数据
				     * 		4.2恢复到初始样式（页号：1(高亮) 2 3 4...）
				     */
				    $("#pagination-file").on("click",".pagination li",function(e){//当页号是1和totalPages时，使其失效；否则，有效
				    	//如果按钮为disabled,则不再ajax查询（break，跳出）。
				    	if(stopClickEvent(this)==false){
				    		return false;
				    	};
				    	
				    	//所选择的页数，即当前页。（如选择带数字的页，则this去取；否则点击first、previous、next、last时，当前页通过高亮的页去取）
				    	//curPage = ($(this).hasClass("pageNumber-li")==true)?parseInt($(this).children().text()):parseInt($("ul .active").children().text());
				    	if($(this).hasClass("pageNumber-li")==true){
				    		//alert("nnn");
				    		curPage = parseInt($(this).children().text());
				    	}else{
				    		//alert("mmm");
				    		switch($(this).attr("id")){
				    			case "first":curPage = 1; break;
				    			case "previous":curPage =  parseInt($("ul .active").children().text())-1; break;
				    			case "next":curPage =  parseInt($("ul .active").children().text())+1; break;
				    			case "last":curPage = totalPages; break;
				    			default:alert("Front end error occured!Please refresh the page.");
				    		}
				    	}
				    	
				    	//alert("===当前页是===："+curPage);
				    	//查询数据
				    	searchFile(curPage,1);
				    	
				    	//***改变页号
				    	PaginationChange(curPage);
				    	//***样式控制
				    	pageButtonStyleControl(curPage);
				    	
				    });
				    //按文件类型查询
				    $("#file_type").on("change",function(){
				    	//alert("changed!");
				    	searchFile(1,0);
				    });
				    //模糊查询
				    $("#go").on("click",function(){
				    	//alert("fuzzying!");
				    	searchFile(1,0);
				    });
				    function pageButtonStyleControl(curPage){
				    	//已经是第一页了
				    	if(curPage<=1){
				    		$("#first").addClass("disabled");
				    		$("#previous").addClass("disabled");
				    	}else{
				    		if($("#first").hasClass("disabled")){
				    			$("#first").removeClass("disabled");
					    		$("#previous").removeClass("disabled");
				    		}
				    	}
				    	//已经是最后一页了
				    	if(curPage>=totalPages){
				    		$("#last").addClass("disabled");
				    		$("#next").addClass("disabled");
				    	}else{
				    		if($("#last").hasClass("disabled")){
				    			$("#last").removeClass("disabled");
					    		$("#next").removeClass("disabled");
				    		}
				    		
				    	}
				    }
				    //通过改变文档类型或输入框模糊查询等初始化查询的方式，控制分页按钮
				    function originalPageControl(queryMode){
				    	//初始化分页按钮为最初样式
				    	if(queryMode==0){
							//alert("初始化分页元素");
							//$(".pagination").replaceWith(paginationElement);
							$(".pagination").replaceWith('<ul class="pagination pagination-lg">'+
									'<li id="first" class="disabled" title="first"><a href="#">◄◄</a></li>'+
									'<li id="previous" class="disabled" title="previous"><a href="#">◄</a></li>'+
									'<li class="pageNumber-li active"><a href="#" class="pageNumber">1</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">2</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">3</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">4</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">5</a></li>'+
									'<li class="pageNumber-li" id="middel-page-li"><a id="middel-page"  class="pageNumber" href="#">6</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">7</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">8</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">9</a></li>'+
									'<li class="pageNumber-li"><a href="#" class="pageNumber">10</a></li>'+
									'<li class="disabled"><a href="#" style="hover:">...</a></li>'+
									'<li id="next" title="next"><a href="#">►</a></li>'+
									'<li id="last" title="last"><a href="#">►►</a></li>'+
								'</ul>');
						}
						
						//初始化，所有页、first、previous、next和last按钮号可见
				    	$(".pagination li").css("display","");
				    	$("#first").css("display","");
				    	$("#previous").css("display","");
				    	$("#next").css("display","");
				    	$("#last").css("display","");
						//*************************************当所有记录的页数小于10时，将多出的隐藏
						for(var i=totalPages;i<10;i++){
					    	$($(".pageNumber-li").get(i)).css("display","none");
					    }
						//如果只有一页，first、previous、next和last按钮变灰
						if(totalPages==1){
							$("#first").addClass("disabled");
							$("#previous").addClass("disabled");
							$("#last").addClass("disabled");
						    $("#next").addClass("disabled");
						}
				    }
				    /*分页查询
				     * curPage:选择的页号
				     * queryMode:0：点击分页按钮的查询
				     * 		     1：文档类型或输入框模糊的查询
				     */
				    function searchFile(curPage,queryMode){
				    	//所选择的文件类型
				    	var fileType = $("#file_type").val();
				    	//输入的关键字
				    	var keywords = $("#keywords").val();
				    	//alert(fileType+"--"+keywords+"pageSize--"+pageSize);
				    	$.ajax({
				    		url:'http://localhost:8080/iZone/search.do',
							type:'post',
							dataType:'json',
							cache:false,
							data:{
								file_type:fileType,
								key_words:keywords,
								cur_page:curPage,
								page_size:pageSize
							},
							beforeSend:function(xhr){
								$("#loading").show();
							},
							complete:function (XMLHttpRequest, textStatus) {
								$("#loading").hide();
							},
							success:function(backData,status){
								totalPages = backData.totalPages;
								//alert("共几页："+totalPages);
								
								//清除非表头已有的行
								$("tr").remove("[class!=table-header]");
								
								$.each(backData.data, function (index, file) {
									//alert(index);
									$("#uploaded-files").append(
					                        $('<tr class="'+file.id+'" />')
					                        .append($('<td/>').text(file.fileName))
					                        .append($('<td class="editable-cell" data-pk="'+file.id+'" data-name="fileRealName" />').text(file.fileRealName))
					                        .append($('<td class="editable-cell" data-pk="'+file.id+'" data-name="fileType" />').text(file.fileType))
					                        .append($('<td/>').text(file.uploadedDate))
					                        .append($('<td/>').text(file.fileFrom))
					                        .append($('<td class="editable-cell" data-pk="'+file.id+'" data-name="fileDescription" />').text(file.fileDescription))
					                        .append($('<td class="down" />').html("<a class='btn btn-primary btn-download' href='http://localhost:8080/iZone/download.do?fileName="+file.fileName+"&fileRealName="+file.fileRealName+"&fileType="+file.fileType+"'><i class='glyphicon glyphicon-download'></i></a>"+
					                        		"<button class='btn btn-warning btn-delete' id='"+file.id+"' style='margin-left:10%;'><i class='glyphicon glyphicon-remove'></i></button>"))
					                );
					            });
								//开启可编辑配置
								enableEditable();
								//alert("将要替换的分页元素是： "+paginationElement);
								//初始化分页样式
								originalPageControl(queryMode);
							},
							error:function(XmlHttpRequest){
								//alert('error!!!');
							}
				    		
				    	});
				    }
				    
				    //页号变换
				    function PaginationChange(curPage){
				    	//之前高亮的消失
				    	$(".pagination li").removeClass("active");
				    	//高亮选中的页号
				    	//如果实际页数小于10个，则不用变换
				    	if(totalPages<10){
				    		$.each($(".pageNumber"),function(index,item){
					    		if(parseInt($(this).text())==curPage){
					    			$(this).parent().addClass("active");
					    		}
				    		});	
				    		return false;
				    	}
				    	
				    	curMiddlePage = parseInt($("#middel-page").text());
				    	curMinPage = curMiddlePage-5;
				    	curMaxPage = curMiddlePage+4;
				    	
				    	var distance = curPage-curMiddlePage;
				    	//alert("==="+distance+"curMinPage-distance="+(curMinPage-distance));
				    	//变换所有页号
				    	if(curMinPage+distance<=0){//1,2,3,4,5,
				    		//alert("<=0");
				    		$.each($(".pageNumber"),function(index,item){
				    			$(this).text(index+1);
				    		});	
				    	}else if(curMaxPage+distance>=totalPages){//...totalPages-2,totalPages-1,totalPages
				    		//alert(">=totalPages");
				    		$.each($(".pageNumber"),function(index,item){
				    			$(this).text(index+totalPages-9);
				    		});	
				    	}else{//4,5,6,7,8,9,...
				    		//alert("else");
				    		$.each($(".pageNumber"),function(index,item){
				    			$(this).text(parseInt($(this).text())+distance);
				    			//alert($(this).text());
				    		});	
				    	}
				    	//高亮选中的页号
				    	$.each($(".pageNumber"),function(index,item){
				    		if(parseInt($(this).text())==curPage){
				    			$(this).parent().addClass("active");
				    		}
			    		});	
				    }
				    
			});
		}
);