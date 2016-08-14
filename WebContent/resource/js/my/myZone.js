require.config({
		baseUrl : '/iZone/resource/js/util',
		paths : {
			jquery : 'jquery-1.9.1.min',
			bootstrap : 'bootstrap.min',
			datetimepicker : 'bootstrap-datetimepicker',
			datetimepicker_zh : 'bootstrap-datetimepicker.zh',
			datatables:'jquery.dataTables.min',
			ckeditor:'ckeditor',
			ckeditor_config:'config',
			ckeditor_lang_en:'en',
			semantic:'semantic.min'
		},
		shim :{
				'bootstrap':{
					deps:['jquery']
			},
				'datetimepicker':{
					deps:['jquery','datatables'],
					exports:'datetimepicker'
			},
				'datetimepicker_zh':{
					deps:['jquery','datetimepicker']
			},
				'semantic':{
					deps:['jquery']
			}
		}
	});
define(['bootstrap','datatables','jquery','datetimepicker','datetimepicker_zh'],function(boot,dt,$,dp,dt_zh){
	var now = new Date();
	var new_show = true;
	var edit_show = false;
	var new_allselected = true;
	var edit_allselected = true;
	var replyForm_show = false;
	
	var fun = $(function() {
		
		//------------------------------遮罩层相关--------------------------
		function addZheZhaoCeng(){
			//增加遮罩层
			var width=document.body.clientWidth;
			var height = document.body.clientHeight;
			$('.zhezhao').prop("style","display:block;width:"+width+";height:"+height);
			//alert(111);
		}
		function removeZheZhaoCeng(){
			//将遮罩层消除
			$('.zhezhao').prop("style","display:none");
		}
		
		//------------------------------------------------------------------------
		
		$('#createActivity1').on('click',function(e){
			e.preventDefault();
			$(this).tab('show');
		});
		$('#mySaySayToggle').on('click',function(e){
			e.preventDefault();
			$(this).tab('show');
		});
		$('#startActivityToggle').on('click',function(e){
			e.preventDefault();
			
			//首先先移除原有的li对象，然后在动态添加新的li对象
			$('.friends ul li').remove();
			
			//增加遮罩层
			addZheZhaoCeng();
			$.ajax({
				url:'../employee/findAllEmployee',
				type:'post',
				dataType:'json',
				cache:false,
				success:function(data){
					//移除遮罩
					removeZheZhaoCeng();
					
					//alert(data[1].empID);
					for(var i=0;i<data.length;i++){
						var employeeLi = createALiOfEmployee(data[i],i);
						//alert(employeeLi.innerHTML);
						$('.friends ul').append($(employeeLi));
					}
				},
				error:function(XmlHttpRequest){
					//移除遮罩
					removeZheZhaoCeng();
					alert('error ： '+XmlHttpRequest.readyState);
				}
			});
			$(this).tab('show');
		});
		//给class为friends的div增加employee的li标签
		function createALiOfEmployee(employeeJson,index){
			var left,top;
			if(index % 2==0){//说明是第n行第一项
				left = 30 + 'px';
			}else{//说明是第n行的第二项
				left = 220 +'px';
			}
			top = Math.floor(index/2)*70+'px';
			var li = document.createElement('li');
			//li.style.cssText("position:absolute;left:"+left+";top:"+top+";");
			li.style.position='absolute';
			li.style.left=left;
			li.style.top=top;
			var checkBox = document.createElement('input');
			checkBox.type = "checkbox";
			checkBox.innerHTML = "&nbsp;";
			var img = document.createElement("img");
			img.src = employeeJson.emp_head_portrait;
			//img.alt = "";
			img.title = employeeJson.empID;
			img.name = employeeJson.empID;
//			img.style.width = '30px';
//			img.style.height = '30px';
			var span = document.createElement("span");
			span.innerHTML = employeeJson.emp_EN_name;
			span.className = "new_user_name";
			li.appendChild(checkBox);
			li.appendChild(img);
			li.appendChild(span);
			return li;
			/*
			<li style="position: absolute; left: 210px; top: 0px;"><input
				type="checkbox" /> &nbsp; <img
				src="/iZone/resource/img/favicon.ico" title="EmpId:100001"
				name="100001" /> <span class="new_user_name">yeqi12312</span></li>
			 */
		}
		$('#activityManagementToggle').on('click',function(e){
			e.preventDefault();
			$(this).tab('show');
		});
		$('#albumToggle').on('click',function(e){
			e.preventDefault();
			$(this).tab('show');
		});
		$('#activity').on('click',function(){
			$(this).dropdown();
		});
		
		//插件datatimepicker的配置
		$('.form_datetime').datetimepicker({
			language: 'zh',
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
			showMeridian: 1,
			startDate:now//开始时间必须是今天之后的某一天
		});
		
		//---------------------activityManagement表格中的关于dataTables的js事件------------------------------------
		//datatables的基本配置在这里，可以定制dataTables的相关属性
		var acitivityManagementTable;
		
		acitivityManagementTable = $('#activityManagementTable').dataTable({
			"sPaginationType": "full_numbers",
	   		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
	   		"oLanguage": {
	            "sLengthMenu": "Display _MENU_ records per page",
	            "sZeroRecords": "Nothing found - sorry",
	            "sInfo": "Now displayed is from  _START_ to _END_,the total no. is _TOTAL_.",
	            "sInfoEmpty": "Showing 0 to 0 of 0 records",
	            "sInfoFiltered": "(filtered from _MAX_ total records)"
	   		},
			"fnInitComplete":function(oSetting){
				$('.editActivityClass').on('click',function(e){
	        		$('#editEmployee').modal('show');
	        		
	        	});
				$('.viewActivityClass').on('click',function(){
					$('#viewActivity').modal('show');
				});
	        	$('#saveEditActivity').on('click',function(){
	        		$('#editEmployee').modal('hide');
	        	});
	        	$('.deleteActivityClass').on('click',function(){
	        		$('#deleteEmployee').modal('show');
	        	});
	        	$('#confirmDeleteActivity').on('click',function(){
	        		$('#deleteEmployee').modal('hide');
	        	});
	        	this.$('tr').on('dblclick',function(){
	        		$('#viewActivity').modal('show');
	        	});
	        	this.$('tr').on('click',function(){
	        		$(this).toggleClass('rowselected');
	        	});
			}
		});
		//删除datatable中的一行数据
		
		$('#saveNewActivityOperate').on('click',function(){
			$('#newActivity').modal('hide');
			//save 操作在这里
		});
		
		$('#addNewActivityOperate').on('click',function(){
			$('#newActivity').modal('show');
		});
		
		$('#deleteActivityOperate').on('click',function(){
			
			var rowSelected = getSelectedRow(acitivityManagementTable);
			if(rowSelected.length != 0){
				var deleteRowFlag = window.confirm('Ensure to delete the data?');
				if(deleteRowFlag){
					for(var i=0;i<rowSelected.length;i++){
						acitivityManagementTable.fnDeleteRow(rowSelected[i]);
					}
					acitivityManagementTable.fnDraw();
				}
			}else{
				alert('No row selected');
			}
		});
		function getSelectedRow(dataTable){
			//alert(resumeManagementTable.prop('id'));
			//返回一组被标记为选中的行，jQuery对象
			return dataTable.$("tr[class*='rowselected']");
		}
		//------------------------------------------------------------------------------------------------------
		
		//---------------------startAnNewActivity标签页的js事件------------------------------------
		//点击@按钮之后的搜索框出现与消失事件
		$('#new_aite').on('click',function(){
			if(new_show == true){
				$('#new_allfriends').slideDown('slow',function(){
					$(this).toggle(new_show);
					new_show = false;
				});
			}else{
				$('#new_allfriends').slideUp('slow',function(){
					$(this).toggle(new_show);
					new_show = true;
				});
			}
		});
		
		//确认所@的人的confirm按钮事件
		$('#new_confirm').on('click',function(){
			var member_names = '';
			var membersId = '';
			$('.new_user_name').each(function(){
				var checked = $(this).siblings().eq(0).prop('checked');
				//alert(hasChecked);
				if(checked){
					member_names += '@'+$(this).html()+';';
					membersId += $(this).prop('title')+",";
				}
				$('#new_members').val(member_names);
				$('#new_membersId').val(membersId.substring(0, membersId.length-1));
			});
			if(!new_show){
				$('#new_allfriends').slideUp("slow",function(){
					$(this).toggle(new_show);
					new_show = true;
				});
			}
		});
		//搜索框右边删除按钮点击事件，清楚搜索框内搜索信息
		$('#new_close').on('click',function(){
			$('#new_search').val('');
		});
		//全选按钮事件
		$('#new_allselected').on('click',function(){
			if(new_allselected){
				$('.new_user_name').each(function(){
					$(this).siblings().eq(0).prop('checked',true);
					new_allselected = false;
				});
			}else{
				$('.new_user_name').each(function(){
					$(this).siblings().eq(0).prop('checked',false);
					new_allselected = true;
				});
			}
		});
		
		$('#newActivitySubmit').on('click',function(){
			var formData = $('#newActivityForm').serialize();
			//增加遮罩
			addZheZhaoCeng();
			
			$.ajax({
				url:'../activity/createNewActivity',
				data:formData,
				type:'post',
				cache:false,
				success:function(data){
					alert(data);
				},
				error:function(xmlHttprequest){
					alert('error :'+xmlHttprequest.status);
				}
			});
		});
		
		//------------------------------------------------------------------------------------------------------
		
		//---------------------editActivity模态框的js事件------------------------------------
		//点击edit按钮之后通过js将edit的修改模态框显示。
		$('#edit').on('click',function(){
			$('#editActivity').modal('toggle');
			
			var ids = $('#hidden_edit_members').text();
			var idArray = ids.split(';');
			//循环太浪费时间和效率了，应该寻找更加好的方法
			$('.edit_user_name').each(function(){
				for(var i=0;i<=idArray.length-2;i++){
					var id = idArray[i];
					var temp_obj = $(this).siblings();
					var needComparedId = temp_obj.eq(1).prop('id');
					//alert(id+': :'+needComparedId);
					if(id == needComparedId){
						temp_obj.eq(0).prop('checked',true);
					}
				}
			});
		});
		//点击@按钮之后的搜索框出现与消失事件
		$('#edit_aite').on('click',function(){
			if(edit_show == true){
				$('#edit_allfriends').slideDown('slow',function(){
					//alert('1');
					$(this).toggle(edit_show);
					edit_show = false;
				});
			}else{
				$('#edit_allfriends').slideUp('slow',function(){
					//alert('2');
					$(this).toggle(edit_show);
					edit_show = true;
				});
			}
		});
		
		//确认所@的人的confirm按钮事件
		$('#edit_confirm').on('click',function(){
			var member_names = '';
			$('.edit_user_name').each(function(){
				var checked = $(this).siblings().eq(0).prop('checked');
				//alert(hasChecked);
				if(checked){
					member_names += '@'+$(this).text()+';';
				}
				$('#edit_members').val(member_names);
			});
			if(!edit_show){
				$('#edit_allfriends').slideUp("slow",function(){
					$(this).toggle(edit_show);
					edit_show = true;
				});
			}
		});
		//搜索框右边删除按钮点击事件，清楚搜索框内搜索信息
		$('#edit_close').on('click',function(){
			$('#edit_search').val('');
		});
		//全选按钮事件
		$('#edit_allselected').on('click',function(){
			if(edit_allselected){
				$('.edit_user_name').each(function(){
					$(this).siblings().eq(0).prop('checked',true);
					edit_allselected = false;
				});
			}else{
				$('.edit_user_name').each(function(){
					$(this).siblings().eq(0).prop('checked',false);
					edit_allselected = true;
				});
			}
		});
		
		$('#saveEditActivity').on('click',function(){
			$('#editActivity').modal('hide');
		});
		
		//------------------------------------------------------------------------------------------------------
		
		//-------------------viewActivity模态框内的js事件--------------------------------------------
		
		//在viewActivity模态框中点击response按钮之后的事件
		$('.toggleComment').on('click',function(){
			var temp_obj = $(this).children().last();
			var attribute = temp_obj.hasClass('glyphicon-chevron-down');
			if(attribute){
				temp_obj.removeClass('glyphicon-chevron-down');
				temp_obj.addClass('glyphicon-chevron-up');
			}else{
				temp_obj.addClass('glyphicon-chevron-down');
				temp_obj.removeClass('glyphicon-chevron-up');
			}
		});
		//关于用户评论模块相关JS
		$('div').delegate('a.reply','click',function(){
			var commentJustClick;
			if(!replyForm_show){
				//如果真的话，说明是第一次点击，应该展开回复框
				commentJustClick = $(this);
				$(this).parent().after("" +
						"<form class='ui reply form'>" +
							"<div class='field'>" +
								"<textarea style='width:400px;height:60px;resize:none;'></textarea>" +
							"</div>" +
							"<div class='ui button teal submit labeled icon'>" +
								"<i class='icon edit'></i> " +
								"<span class='responseTo'>Response</span>" +
							"</div>" +
						"</form>");
				replyForm_show = true;
				$('.responseTo').on('click',function(){
					//var responseContent = commentJustClick.parent().siblings().last().children().eq(0).children().eq(0).html().trim();
					var responseContent = commentJustClick.parent().parent().children().last().children().first().children().first().val();
					//alert(responseContent);
					//删除回复框
					var replyFormNode = commentJustClick.parent().siblings().last();
					//replyFormNode.empty();
					replyFormNode.remove();
					replyForm_show = false;
					//添加内容
					if(responseContent != "" && responseContent != null){
						commentJustClick.parent().parent().parent().after("" +
								"<div class='comment'>" +
									"<a class='avatar'> " +
										"<img src='/iZone/resource/img/head_portrait3.jpg'>" +
									"</a>" +
									"<div class='content'>" +
										"<a class='author'>Dodo" +
										"</a>" +
										"<div class='metadata'>" +
											"<span class='date'>20 mins ago</span>" +
										"</div>" +
										"<div class='text'>" +
											responseContent +
										"</div>" +
										"<div class='actions'>" +
											"<a class='reply'>Response</a>" +
											"<a class='delete'>Delete</a>" +
										"</div>" +
									"</div>" +
								"</div>");
					}
				});
			}else{
				var replyFormNode = commentJustClick.parent().siblings().last();
				//replyFormNode.empty();
				replyFormNode.remove();
				replyForm_show = false;
			}
		});
		
		
		//----replyForm_show----------------------------------------------------------------------
		
		//----------------------------photo测试dataTables的例子相关--------------------
	});
	return {
		fun :fun 
	};
});
