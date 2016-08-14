require.config({
		baseUrl : '/iZone/resource/js/util',
		paths : {
			jquery : 'jquery-1.9.1.min',
			jqueryform:'jquery.form',
			bootstrap : 'bootstrap.min',
			datetimepicker : 'bootstrap-datetimepicker',
			datetimepicker_zh : 'bootstrap-datetimepicker.zh',
			datatables:'jquery.dataTables.min',
			toExeclAndPdf:'TableTools.min',
			editable:'bootstrap-editable'
		},
		shim :{
				'bootstrap':{
					deps:['jquery'],
			},
				'datetimepicker':{
					deps:['jquery','datatables'],
					exports:'datetimepicker',
			},
				'datetimepicker_zh':{
					deps:['jquery','datetimepicker'],
			},
				'toExeclAndPdf':{
					deps:['jquery'],
			},
				'editable':{
					deps:['bootstrap','jquery'],
			},
				'jqueryform':{
					deps:['jquery'],
			}
		}
	});
define(['bootstrap','datatables','jquery','jqueryform','datetimepicker','datetimepicker_zh','toExeclAndPdf','editable'],function(boot,dt,$,jf,dp,dt_zh,execl,editable){
	var fun = $(function() {
		//插件datatimepicker的配置
		$('.form_date').datetimepicker({
			format: 'yyyy-MM-dd',
			language:'zh',
			weekStart: 1,
			todayBtn: 1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		}); 
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
		
		//-----------------------Employee management 相关的操作---------------------------------
		var employeeManagementTable;
		
		//点击这个按钮之后，项后台服务器请求加载所有employee的数据，ajax加载
		$('#employeeManagementToggle').on('click',function(event){
			//alert('click');
			event.preventDefault();
			$(this).tab('show');
			//alert('before dataTables init');
			//增加遮罩曾
			addZheZhaoCeng();
			$.ajax({
				url:'../employee/findAllEmployee',
				type:'post',
				dataType:'json',
				cache:false,
//				beforeSend:function (XMLHttpRequest) {
//					//增加遮罩曾
//					addZheZhaoCeng();
//				},
				success:function(data){
					//将者遮罩层消除
					removeZheZhaoCeng();
					if(data.value == "fail"){
						alert("fail");
					}else{
						initEmployeeTable(data);
					}
				},
				error:function(XmlHttpRequest){
					//将者遮罩层消除
					removeZheZhaoCeng();
					alert('error : '+XmlHttpRequest.status);
				}
			});
		});
		
		//增加新员工操作
		$('#createNewEmployeeOperate').on('click',function(){
			//在这里可以加上一层遮罩,表明正在想数据库增加数据，然后等增加成功后将遮罩去除*****
			//$('body').append("<div class='zhezhaoing'></div>");//增加遮罩
			var createNewEmployeeFormData = $('#createNewEmployeeForm').serialize();
			//alert(createNewEmployeeFormData);
			
			//增加遮罩曾
			addZheZhaoCeng();
			//将者遮罩层消除
			//removeZheZhaoCeng();
			$.ajax({
				url:'../employee/createNewEmployee',
				type:'post',
				data:createNewEmployeeFormData,
				cache:false,
				dataType:'json',
				success:function(data){
					//将者遮罩层消除
					//alert(data.value);
					removeZheZhaoCeng();
					if(data.value == 'success'){
						$('#resetCreateNewEmployeeForm').trigger('click');
					}
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					//将者遮罩层消除
					removeZheZhaoCeng();
					alert('error');
				}
			});
		});
		
//		$('#addNewEmployeeOperate').on('click',function(){
//			$('#createANewEmployee').modal('show');
//		});
		
		$('#saveNewEmployeeOperate').on('click',function(){
			$('#createANewEmployee').modal('hide');
			//save 操作在这里
		});
		
		$('#addNewEmployeeOperate').on('click',function(){
			$('#newEmployee').modal('show');
		});
		
		$('#addNewEmployee').on('click',function(){
			var createNewEmployeeFormData = $('#newEmployeeForm').serialize();
			//alert(createNewEmployeeFormData);
			
			//增加遮罩曾
			addZheZhaoCeng();
			
			$.ajax({
				url:'../employee/createNewEmployee',
				type:'post',
				data:createNewEmployeeFormData,
				cache:false,
				dataType:'json',
//				beforeSend:function(XmlHttpRequest){
//					//增加遮罩层
//					addZheZhaoCeng();
//				},
				success:function(data){
					//将者遮罩层消除
					removeZheZhaoCeng();
					$('#newEmployee').modal('hide');
					//销毁原先的表格
					employeeManagementTable.fnDestroy();
					//重新抓取数据
					$.ajax({
						url:'../employee/findAllEmployee',
						type:'post',
						dataType:'json',
						cache:false,
						success:function(datadata){
							if(datadata.value == "fail"){
								alert("fail");
							}else{
								initEmployeeTable(datadata);
							}
						},
						error:function(XmlHttpRequest){
							alert('error : '+XmlHttpRequest.status);
						}
					});
				},
				error:function(XMLHttpRequest,textStatus,errorThrown){
					//将者遮罩层消除
					removeZheZhaoCeng();
					alert('error');
				}
			});
		});
		
		$('#deleteEmployeeOperate').on('click',function(){
			//var rowSelected = getSelectedRow(employeeManagementTable);
			//deleteRow(employeeManagementTable,rowSelected);
			var empIds = getSelectedEmpIds(employeeManagementTable);
			if(empIds == ""){
				alert('No rows selected.');
			}else{
				
				//增加遮罩曾
				addZheZhaoCeng();
				
        		$.ajax({
        			url:'../employee/confirmDelete',
        			type:'post',
        			data:{'empId':empIds},
        			cache:false,
        			dataType:'json',
        			success:function(data){
        				//移除遮罩
        				removeZheZhaoCeng();
        				if(data.value == "success"){
        					alert('Delete successfully.');
        					employeeManagementTable.fnDestroy(false);
        					//重新抓取数据
        					$.ajax({
        						url:'../employee/findAllEmployee',
        						type:'post',
        						dataType:'json',
        						cache:false,
        						success:function(datadata){
        							if(datadata.value == "fail"){
        								alert("fail");
        							}else{
        								initEmployeeTable(datadata);
        							}
        						},
        						error:function(XmlHttpRequest){
        							alert('error : '+XmlHttpRequest.status);
        						}
        					});
        				}else if(data.value == "fail"){
        					alert('Delete failed');
        				}
        			},
        			error: function(XMLHttpRequest, textStatus, errorThrown) {
        				//移除遮罩
        				removeZheZhaoCeng();
                        alert(XMLHttpRequest.status);
                        alert(XMLHttpRequest.readyState);
                        alert(textStatus);
                    }
        		});
			}
		});
		
		$('#uploadEmployeeExeclOperate').on('click',function(){
			$('#chooseUploadEmployeeInput').val();
			$('#uploadEmployeeExecl').modal('show');
		});
		
		/*$('#uploadEmployeeExecl').on('shown.bs.modal', function (e) {
			
		});*/
		
		//选择上传员工信息的操作
		$('#uploadEmployeeExeclBrowse').on('click',function(){
//			var value = document.getElementById("chooseUploadEmployeeExecl").click();
			$('#chooseUploadEmployeeExecl').trigger('click');
		});
		
		var uploadEmployeeExeclDataTable;
		//触发上传文件操作
		$('#chooseUploadEmployeeExecl').change(function(){
			//增加遮罩
			addZheZhaoCeng();
			
			$('#chooseUploadEmployeeInput').val($(this).val());
			
			var form = $("form[id='uploadEmployeeExeclForm']");
			var options = {
					url:"../employee/uploadEmployeeExecl",
					type:'post',
					dataType:'json',   
					clearForm: true ,       
			        resetForm: true ,//reset the form after successful submit 
			        clearForm: true,//clear all form fields after successful submit
					success:function(data){//成功返回后
						//移除遮罩
						removeZheZhaoCeng();
						initUpLoadEmployeeExeclDataTable(data);
					},
					error: function(XmlHttpRequest, textStatus, errorThrown){  
						//移除遮罩
						removeZheZhaoCeng();
                        alert( "error : "+XmlHttpRequest.status);  
                        $('#chooseUploadEmployeeInput').val();
                        $('#chooseUploadEmployeeExecl').val();
                        uploadEmployeeExeclDataTable.fnDestroy();//destroy错误的table
                    }  
			};
			form.ajaxSubmit(options);//采用jquery.form的ajax提交方法,重写回调函数
		});
		
		//初始话上传execl表格
		function initUpLoadEmployeeExeclDataTable(data){
			uploadEmployeeExeclDataTable = $('#uploadEmployeeExeclTable').dataTable({
				"aoColumns":[
				               {
				            	   "mData":"empID",
				            	   "sTitle":"EmpID",
				               },
				               {
				            	   "mData":"emp_CN_name",
				            	   "sTitle":"Emp_CN_name",
				               },
				               {
				            	   "mData":"emp_EN_name",
				            	   "sTitle":"Emp_EN_name",
				               },
				               {
				            	   "mData":"empType",
				            	   "sTitle":"EmpType",
				               },
				               {
				            	   "mData":"engageDate",
				            	   "sTitle":"EngageDate",
				               }
				              ],
				"aaData":data,
				"bRetrieve": true,
				"bDestroy": true,
				"bSort":false,
				"bFilter":false,
				"bLengthChange":false,
				"bInfo":false
			});
		}
		
		//确认文件上传操作
		$('#confirmUploadEmployeeExecl').on('click',function(){
			
			//增加遮罩曾
			addZheZhaoCeng();
			
			$.ajax({
				url:'../employee/confirmUploadEmployeeExecl',
				type:'post',
				dataType:'json',
				cache:false,
				success:function(data){
					//将者遮罩层消除
					removeZheZhaoCeng();
					if(data.value == "success"){
						$('#uploadEmployeeExecl').modal('hide');
						employeeManagementTable.fnDestroy();
						//重新抓取数据
						$.ajax({
							url:'../employee/findAllEmployee',
							type:'post',
							dataType:'json',
							cache:false,
							success:function(datadata){
								if(datadata.value == "fail"){
									alert("fail");
								}else{
									initEmployeeTable(datadata);
								}
							},
							error:function(XmlHttpRequest){
								alert('error : '+XmlHttpRequest.status);
							}
						});
					}else if(data.value == "fail"){
						alert('fail');
					}else if(data.value == "serverError"){
						alert('Server error');
					}else{
						alert('Other Error');
					}
				},
				error:function(XmlHttpRequest){
					//将者遮罩层消除
					removeZheZhaoCeng();
					alert('error : '+XmlHttpRequest.status);
				}
			});
		});
		
		function initEmployeeTable(data){
			employeeManagementTable = $('#employeeManagementTable').dataTable({
				"bDestory":true,
				"bRetrieve":true,
				"bProcessing": true,
				//"bAutoWidth": false,
		   		"sPaginationType": "full_numbers",
			    "bScrollCollapse":true,
		   		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		   		"oLanguage": {
		            "sLengthMenu": "Display _MENU_ records per page",
		            "sZeroRecords": "Nothing found - sorry",
		            "sInfo": "Now displayed is from  _START_ to _END_,the total no. is _TOTAL_.",
		            "sInfoEmpty": "Showing 0 to 0 of 0 records",
		            "sInfoFiltered": "(filtered from _MAX_ total records)"
		   		},
		   		"sDom": 'T<"clear">lfrtip',
				"oTableTools": {
					"sSwfPath": "/iZone/resource/js/util/swf/copy_csv_xls_pdf.swf",
					"aButtons": [
									{
										"sExtends":"collection",
										"sButtonText": "Save",
										"aButtons":    [ 
											{
												"sExtends":"xls",
												"sButtonText":"save as xls",
												"mColumns": [ 0, 1, 2 ,3 ,4],
												"sCharSet": "utf8" 
											},
											{
												"sExtends":"pdf",
												"sButtonText":"save as pdf",
												"mColumns": [ 0, 1, 2 ,3 ,4],
												"sCharSet": "utf8" ,
//												"fnClick":function(){alert(1);},
												"fnCellRender":function(sValue,iColumn,nTr,iDataIndex){
													//alert('sValue='+sValue+',iColumn='+iColumn+',nTr='+nTr+',iDataIndex='+iDataIndex);
													//sValue是cell的值，iColumn是列号,从0开始，nTr是js的Row对象，iDataIndex是该行是第几条记录
													if(iColumn == 1){
														return encodeURI(sValue,'utf-8');
													}
													return sValue;
												},
											} 
										],
										
									},
									{
										"sExtends":"print",
										"sButtonText":"print",
										"mColumns": [0, 1, 2 ,3 ,4],
										"fnCellRender":function(sValue,iColumn,nTr,iDataIndex){
											//alert('sValue='+sValue+',iColumn='+iColumn+',nTr='+nTr+',iDataIndex='+iDataIndex);
											//sValue是cell的值，iColumn是列号,从0开始，nTr是js的Row对象，iDataIndex是该行是第几条记录
											return sValue;
										},
									}
								],
				},
				//对一个表格进行初始化定义用"aoColumnDefs" or "aoColumns"
				"aaData":data,
				"aoColumnDefs":[
				                {
				                	"mData": "empID",
				                	"mRender":function(data,type,row){
				                		return "<a href='javascript:;' class='viewEmployeeClass'>"+data+"</a>";
				                	},
				                	"sTitle":"EmpId",
				                	//"asSorting":['asc'],
				                	"aTargets":[0]
				                 },
				                {
				                	 "mData": "emp_CN_name",
				                	 "sType":"string",
					                 //"sWidth":"10%",
					                 "sTitle":"Emp_CN_name",
					                 //"asSorting":['asc'],
					                 "sDefaultContent":"No CN_name",
					                 "aTargets":[1]
				                 },
				                {
				                	 "mData": "emp_EN_name",
					                // "sWidth":"10%",
					                 "sTitle":"Emp_EN_name",
					                // "asSorting":['asc'],
					                 "sDefaultContent": "No EN-name",
					                 "aTargets":[2]
				                 },
				                 {
				                	 "mData": "empType",
					                // "sWidth":"10%",
					                 "sTitle":"EmpType",
					                 "sDefaultContent": "No Type",
					                 "aTargets":[3]
				                 },
				                {
					                 "mData": "engageDate",
					                 "sTitle":"EngageDate",
					                 "aTargets":[4]
					            },
					             {
					            	 "mData":null,
					            	 "sTitle":"Operation",
					            	 "sType":"html",
					            	 "bSearchable": false,
					            	 "bSortable": false,
					            	 "mRender":function(data,type,row){
					            		 return "<div class='btn-group btn-group-sm'>"+
									"<button class='btn btn-primary editEmployeeClass' type='button' >Edit</button>"+
									"<button class='btn btn-warning deleteEmployeeClass' type='button' >Delete</button>"+
									"</div>";
					            	 },
					            	 "aTargets":[5]
					             }
				                ],
		        "fnInitComplete": function( oSettings ){
		        	//alert('dataTable init complete .');
		        	$('.editEmployeeClass').on('click',function(e){
		        		e.preventDefault();
		        		var empId = $(this).parent().parent().siblings().first().children().first().html();
		        		
		        		//增加遮罩曾
		    			addZheZhaoCeng();
		    			
		        		$.ajax({
		        			url:'../employee/editEmployee',
		        			type:'post',
		        			data:{"empId":empId},
		        			cache:false,
		        			dataType:'json',
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
				    			if(data.value == "fail"){
				    				alert('Server fail');
				    			}else if(data.value != "fail"){
				    				//添加editEMployee模态框的显示数据
			        				$('#edit_empId').val(data.empID);
			        				$('#edit_emp_CN_name').val(data.emp_CN_name);
			        				$('#edit_emp_EN_name').val(data.emp_EN_name);
			        				$('#edit_engageDate').val(data.engageDate);
			        				var empType = data.empType;
			        				$("#edit_empType option[value='"+empType+"']").prop('selected',true);
			        				$('#editEmployee').modal('show');
				    			}
		        			},
		        			error:function(XmlHttpRequest){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				alert('error : '+XmlHttpRequest.status);
		        			}
		        		});
		        	});
		        	$('#saveEditEmployee').on('click',function(){
		        		
		        		//ajax异步传输更改相应信息
		        		var editEmployeeFormData = $('#editEmployeeForm').serialize();
		        		
		        		//增加遮罩
		        		addZheZhaoCeng();
		        		
		        		$.ajax({
		        			url:'../employee/updateEmployeeInfo',
		        			type:'post',
		        			data:editEmployeeFormData,
		        			dataType:'json',
		        			cache:false,
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
				    			$('#editEmployee').modal('hide');
				    			//销毁原先的表格
								employeeManagementTable.fnDestroy();
								//重新抓取数据
								$.ajax({
									url:'../employee/findAllEmployee',
									type:'post',
									dataType:'json',
									cache:false,
									success:function(datadata){
										if(datadata.value == "fail"){
											alert("fail");
										}else{
											initEmployeeTable(datadata);
										}
									},
									error:function(XmlHttpRequest){
										alert('error : '+XmlHttpRequest.status);
									}
								});
		        			},
		        			error:function(XmlHttpRequest){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				alert('error:'+XmlHttpRequest.status);
		        			}
		        		});
		        	});
		        	
		        	//删除单个员工操作
		        	$('.deleteEmployeeClass').on('click',function(e){
		        		e.preventDefault();
		        		var empId = $(this).parent().parent().siblings().first().children().first().html();//用来存储要删除的empId信息
		        		//alert(empId);
		        		//增加遮罩
		        		addZheZhaoCeng();
		        		
		        		$.ajax({
		        			url:'../employee/deleteEmployee',
		        			type:'post',
		        			data:{"empId":empId},
		        			cache:false,
		        			dataType:'json',
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				//alert(data);
		        				//添加editEMployee模态框的显示数据
				    			if(data.value == "fail"){
				    				alert('fail');
				    			}else{
				    				$('#delete_empId').text(data.empID);
			        				$('#delete_emp_CN_name').text(data.emp_CN_name);
			        				$('#delete_emp_EN_name').text(data.emp_EN_name);
			        				$('#delete_engageDate').text(data.engageDate);
			        				$('#delete_empType').text(data.empType);
			        				$('#deleteEmployee').modal('show');
				    			}
		        			},
		        			error:function(XmlHttpRequest){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				alert('error : '+XmlHttpRequest.status);
		        			}
		        		});
		        	});
		        	//确认删除单个员工操作
		        	$('#confirmDeleteEmployee').on('click',function(){
		        		var empId = $('#delete_empId').html();
		        		//增加遮罩
		        		addZheZhaoCeng();
		        		$.ajax({
		        			url:'../employee/confirmDelete',
		        			data:{'empId':empId},
		        			type:'post',
		        			cache:false,
		        			dataType:'json',
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				if(data.value == "success"){
		        					$('#deleteEmployee').modal('hide');
		        					//销毁原有表格
		        					employeeManagementTable.fnDestroy(false);
		        					//重新抓取数据
		        					$.ajax({
		        						url:'../employee/findAllEmployee',
		        						type:'post',
		        						dataType:'json',
		        						cache:false,
		        						success:function(datadata){
		        							if(datadata.value == "fail"){
		        								alert("fail");
		        							}else{
		        								initEmployeeTable(datadata);
		        							}
		        						},
		        						error:function(XmlHttpRequest){
		        							alert('error : '+XmlHttpRequest.status);
		        						}
		        					});
		        				}else if(data.value == "fail"){
		        					alert('delete fail');
		        				}
		        			},
		        			error: function(XMLHttpRequest, textStatus, errorThrown) {
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		                        alert(XMLHttpRequest.status);
		                        alert(XMLHttpRequest.readyState);
		                        alert(textStatus);
		                    }
		        		});
		        	});
		        	$('.viewEmployeeClass').on('click',function(){
		        		var empId = $(this).text();
		        		//增加遮罩
		        		addZheZhaoCeng();
		        		$.ajax({
		        			url:'../employee/viewEmployee',
		        			type:'post',
		        			data:{"empId":empId},
		        			cache:false,
		        			dataType:'json',
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
				    			if(data.value == "fail"){
				    				alert("Server error");
				    			}else{
				    				//添加editEMployee模态框的显示数据
				    				$('#view_empId').text(data.empID);
			        				$('#view_emp_CN_name').text(data.emp_CN_name);
			        				$('#view_emp_EN_name').text(data.emp_EN_name);
			        				$('#view_engageDate').text(data.engageDate);
			        				$('#view_empType').text(data.empType);
			        				$('#viewEmployee').modal('show');
				    			}
		        			},
		        			error:function(XmlHttpRequest){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				alert('error : '+XmlHttpRequest.status);
		        			}
		        		});
		        	});
		        	
		        	this.$('tr').on('dblclick',function(){
		        		var empId = $(this).children().first().text();
		        		
		        		//增加遮罩
		        		addZheZhaoCeng();
		        		$.ajax({
		        			url:'../employee/viewEmployee',
		        			type:'post',
		        			data:{"empId":empId},
		        			cache:false,
		        			dataType:'json',
		        			success:function(data){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
				    			if(data.value == "fail"){
				    				alert('Server error');
				    			}else{
				    				//添加editEMployee模态框的显示数据
			        				$('#view_empId').text(data.empID);
			        				$('#view_emp_CN_name').text(data.emp_CN_name);
			        				$('#view_emp_EN_name').text(data.emp_EN_name);
			        				$('#view_engageDate').text(data.engageDate);
			        				$('#view_empType').text(data.empType);
			        				$('#viewEmployee').modal('show');
				    			}
		        			},
		        			error:function(XmlHttpRequest){
		        				//将者遮罩层消除
				    			removeZheZhaoCeng();
		        				alert('error : '+XmlHttpRequest.status);
		        			}
		        		});
		        	});
		        	this.$('tr').on('click',function(){
		        		$(this).toggleClass('rowselected');
		        	});
		        },
			});
		};
		
		//--------------------------------------------------------
		
		//-------------------Interview Management 相关--------------
		var interviewManagementTable;
		$('#interviewManagementToggle').on('click',function(event){
			event.preventDefault();
			$(this).tab('show');
			
			var tableDiv = document.createElement('div');
			tableDiv.setAttribute('class','overflow: auto; width: 90%;');
			$(this).append(tableDiv);
			
			interviewManagementTable = $('#interviewManagementTable').dataTable({
				"bDestory":true,
				"bRetrieve":true,
				"bProcessing": true,
				"sScrollX": "900px",
				"sScrollXInner": "150%",
			    "bScrollCollapse":true,
		   		"sPaginationType": "full_numbers",
		   		"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
		   		"oLanguage": {
		            "sLengthMenu": "Display _MENU_ records per page",
		            "sZeroRecords": "Nothing found - sorry",
		            "sInfo": "Now displayed is from  _START_ to _END_,the total no. is _TOTAL_.",
		            "sInfoEmpty": "Showing 0 to 0 of 0 records",
		            "sInfoFiltered": "(filtered from _MAX_ total records)"
		   		},
				"sAjaxSource":"/iZone/resource/other/interviewJson.json",
				"aoColumnDefs":[
				                {
				                	"mData":"id",
				                	"sTitle":"S. No.",
				                	"sWidth":"5%",
				                	"aTargets":[0]
				                },
				                {
				                	"mData":"candidateFullName",
				                	"sTitle":"Candidate Full Name",
				                	"sWidth":"5%",
				                	"aTargets":[1]
				                },
				                {
				                	"mData":"skill",
				                	"sTitle":"Skill",
				                	"sWidth":"5%",
				                	"aTargets":[2]
				                },
				                {
				                	"mData":"finalInterviewStatus",
				                	"sTitle":"Final Interview Status",
				                	"sWidth":"5%",
				                	"aTargets":[3]
				                },
				                {
				                	"mData":"currentLocation",
				                	"sTitle":"Current Location",
				                	"sWidth":"5%",
				                	"aTargets":[4]
				                },
				                {
				                	"mData":"preferredLocation",
				                	"sTitle":"Preferred Location",
				                	"sWidth":"5%",
				                	"aTargets":[5]
				                },
				                {
				                	"mData":"currentCompany",
				                	"sTitle":"Current Company",
				                	"sWidth":"5%",
				                	"aTargets":[6]
				                },
				                {
				                	"mData":"experienceInYears",
				                	"sTitle":"Experience in years",
				                	"sWidth":"5%",
				                	"aTargets":[7]
				                },
				                {
				                	"mData":"newGrade",
				                	"sTitle":"New Grade",
				                	"sWidth":"5%",
				                	"aTargets":[8]
				                },
				                {
				                	"mData":"bU",
				                	"sTitle":"BU",
				                	"sWidth":"5%",
				                	"aTargets":[9]
				                },
				                {
				                	"mData":"email",
				                	"sTitle":"Email ID",
				                	"aTargets":[10]
				                },
				                {
				                	"mData":"phone",
				                	"sTitle":"Phone",
				                	"aTargets":[11]
				                },
				                {
				                	"mData":"roundDateNo1",
				                	"sTitle":"1st round Date",
				                	"aTargets":[12]
				                },
				                {
				                	"mData":"interview1Panel",
				                	"sTitle":"Interview1 Panel",
				                	"aTargets":[13]
				                },
				                {
				                	"mData":"interview1Mode",
				                	"sTitle":"Interview1 Mode",
				                	"aTargets":[14]
				                },
				                {
				                	"mData":"roundStatusNo1",
				                	"sTitle":"1st Round Status",
				                	"aTargets":[15]
				                },
				                {
				                	"mData":"roundDateNo2",
				                	"sTitle":"2nd round Date",
				                	"aTargets":[16]
				                },
				                {
				                	"mData":"interview2Panel",
				                	"sTitle":"Interview2 Panel",
				                	"aTargets":[17]
				                },
				                {
				                	"mData":"interview2Mode",
				                	"sTitle":"Interview2 Mode",
				                	"aTargets":[18]
				                },
				                {
				                	"mData":"roundStatusNo2",
				                	"sTitle":"2nd round Status",
				                	"aTargets":[19]
				                },
				                {
				                	"mData":"roundDateNo3",
				                	"sTitle":"3rd round Date",
				                	"aTargets":[20]
				                },
				                {
				                	"mData":"interview3Panel",
				                	"sTitle":"Interview3 Panel",
				                	"aTargets":[21]
				                },
				                {
				                	"mData":"interview3Mode",
				                	"sTitle":"Interview 3 Mode",
				                	"aTargets":[22]
				                },
				                {
				                	"mData":"roundStatusNo3",
				                	"sTitle":"3rd round Status",
				                	"aTargets":[23]
				                },
				                {
				                	"mData":null,
				                	"sTitle":"Operation",
				                	"mRender":function(data,type,row){
					            		 return "<div class='btn-group btn-group-sm'>"+
									"<button class='btn btn-primary editInterviewClass' type='button' >Edit</button>"+
									"<button class='btn btn-warning deleteInterviewClass' type='button' >Delete</button>"+
									"</div>";
					            	 },
				                	"aTargets":[24]
				                }
				                
				 ],
				 "fnInitComplete": function( oSettings ){
					 //alert('dataTable init complete .');
					 $(this).fnAdjustColumnSizing();
				 }
			});
			
			
			$('#addNewInterviewOperate').on('click',function(){
				$('#newInterview').modal('show');
			});
			
			$('#saveNewInterviewOperate').on('click',function(){
				$('#newInterview').modal('hide');
				//save 操作在这里
			});
			
			$('#deleteInterviewOperate').on('click',function(){
				var rowSelected = getSelectedRow(interviewManagementTable);
				deleteRow(interviewManagementTable, rowSelected);
			});
		});
		
		//---------------------------------------------------------
		//---------------------------------------------------------
		function getSelectedEmpIds(dataTable){
			var empIds = "";
			dataTable.$("tr[class*='rowselected']").each(function(){
				empIds += $(this).children().first().text() + ",";
			});
			if(empIds != ""){
				empIds = empIds.substring(0, empIds.length-1);//去掉最后一个逗号
			}
			return empIds;
		}
		
		function getSelectedRow(dataTable){
			//alert(resumeManagementTable.prop('id'));
			//返回一组被标记为选中的行，jQuery对象
			return dataTable.$("tr[class*='rowselected']");
		}
		//执行删除操作
		function deleteRow(dataTable,rowSelected){
			if(rowSelected.length != 0){
				var deleteRowFlag = window.confirm('Ensure to delete the data?');
				if(deleteRowFlag){
					for(var i=0;i<rowSelected.length;i++){
						dataTable.fnDeleteRow(rowSelected[i]);
					}
					dataTable.fnDraw();
				}
			}else{
				alert('No row selected');
			}
		}
		
	//----------------------------report 代码相关------------------------------------------
		var col_control = 0;
		var columns = [
		               {
		            	   "mData":"col1",
		            	   "sTitle":"col1",
		            	   "bSortable":false
		               },
		               {
		            	   "mData":"col2",
		            	   "sTitle":"col2",
		            	   "bSortable":false
		               },
		               {
		            	   "mData":"col3",
		            	   "sTitle":"col3",
		            	   "bSortable":false
		               },
		               {
		            	   "mData":"col4",
		            	   "sTitle":"col4",
		            	   "bSortable":false
		               }
		              ];
		var jsonData = [
                          {
	                          "col1":"cell11",
                        	  "col2":"cell12",
                        	  "col3":"cell13",
                        	  "col4":"cell14"
                          },
                          {
                        	  "col1":"cell21",
                        	  "col2":"cell22",
                        	  "col3":"cell23",
                        	  "col4":"cell24"
                          },
                          {
                        	  "col1":"cell31",
                        	  "col2":"cell32",
                        	  "col3":"cell33",
                        	  "col4":"cell34"
                          }
                      ];
		var reportTable;

		//该变量标识的是点击确认在那个列上进行操作
		var theadTdPos = new Array();
		//该变量是作为上一个变量的副本用于删除列的时候使用
		var theadTdPosCopy = new Array();
		
		//初始化dataTable
		function initDataTable(columns,jsonData){
			reportTable = $('#reportTable').dataTable({
				"aoColumns":columns,
				"aaData":jsonData,
				"bRetrieve": true,
				"bDestroy": true,
				"bSort":false,
				"sScrollX": "100%",
				"sScrollXInner": "110%",
				"sDom": 'T<"clear">lfrtip',
				"oTableTools": {
					"sSwfPath": "/iZone/resource/js/util/swf/copy_csv_xls_pdf.swf",
					"aButtons": [
									{
										"sExtends":"collection",
										"sButtonText": "Save",
										"aButtons":    [ 
											{
												"sExtends":"xls",
												"sButtonText":"save as xls",
												"mColumns": [ 0, 1, 2 ,3 ,4 ,5 ,6 ,7 ,8],
												"sCharSet": "utf8" 
											},
											{
												"sExtends":"pdf",
												"sButtonText":"save as pdf",
												"mColumns": [ 0, 1, 2 ,3 ,4 ,5 ,6 ,7 ,8],
												"sCharSet": "utf8" ,
//												"fnClick":function(){alert(1);},
												"fnCellRender":function(sValue,iColumn,nTr,iDataIndex){
													//alert('sValue='+sValue+',iColumn='+iColumn+',nTr='+nTr+',iDataIndex='+iDataIndex);
													//sValue是cell的值，iColumn是列号,从0开始，nTr是js的Row对象，iDataIndex是该行是第几条记录
													if(iColumn == 1){
														return encodeURI(sValue,'utf-8');
													}
													return sValue;
												},
											} 
										],
										
									},
									{
										"sExtends":"print",
										"sButtonText":"print",
										"mColumns": [0, 1, 2 ,3 ,4 ,5 ,6 ,7 ,8],
										"fnCellRender":function(sValue,iColumn,nTr,iDataIndex){
											//alert('sValue='+sValue+',iColumn='+iColumn+',nTr='+nTr+',iDataIndex='+iDataIndex);
											//sValue是cell的值，iColumn是列号,从0开始，nTr是js的Row对象，iDataIndex是该行是第几条记录
											if(iColumn == 9){
												return '';
											}
											return sValue;
										},
									}
								],
				},
				"fnInitComplete": function(oSettings, json) {
					this.$('tbody tr').on('click',function(){
						$(this).toggleClass('rowselected');
					});
				},
			});
		}
		
		$('#reportTableDiv').delegate('thead tr th','click',function(){
			$(this).toggleClass('colselected');
			//获得点击的thead的位置
			th = findTheadTdPos($(this));
			var i = isExistInPos(th,theadTdPos);
			if(i != null){//如果在pos数组中存在的话，则表明之前已经点击过，应删除，不存在则加入
				removeFromPos(i,theadTdPos);
			}else{
				theadTdPos.push(th);
			}
			theadTdPosCopy = theadTdPos;
		});
		//修改thead中的东西
		var col_name_copy = '';
		$('#reportTableDiv').delegate('thead tr th','dblclick',function(e){
			e.preventDefault();
			var temp = $(this).children();
			if(temp.length == 0){
				col_name_copy = $(this).html();
			}else{
				col_name_copy = temp.val();
			}
			$(this).html("<input type='text'class='form-control' value='"+col_name_copy+"'/>");
			temp.first().focus();
		});
		$('#reportTableDiv').delegate('thead tr th input','blur',function(e){
			//先检查是否与其余的列名重复，不重复则通过，否则提示列名重复
			var col_name = $(this).val();
			var col_pos = findTheadTdPos($(this).parent());
			//alert('col_pos = '+col_pos);
			if(!isColumnNameExist(col_name,col_pos)){
				//修改对应位置的相应信息，columns和jsonData
				$(this).parent().html(col_name);
				for(var i in columns){
					if(columns[i].sTitle == col_name_copy){
						columns[i].sTitle = col_name;
						columns[i].mData = col_name;
					}
				}
				for(var i in jsonData){
					var value = jsonData[i][col_name_copy];
					delete jsonData[i][col_name_copy];
					jsonData[i][col_name] = value;
				}
				col_name_copy = '';
				initDataTable(columns,jsonData);
			}else{
				alert('Column name has existed.Please enter an different column name.');
				$(this).parent().html(col_name_copy);
			}
			
		});
		$('#reportTableDiv').delegate('thead tr th input','keyup',function(e){
			var eventCode = e.keyCode;
			if(eventCode == 13){
				//先检查是否与其余的列名重复，不重复则通过，否则提示列名重复
				var col_name = $(this).val();
				var col_pos = findTheadTdPos($(this).parent());
				//alert('col_pos = '+col_pos);
				if(!isColumnNameExist(col_name,col_pos)){
					//修改对应位置的相应信息，columns和jsonData
					$(this).parent().html(col_name);
					for(var i in columns){
						if(columns[i].sTitle == col_name_copy){
							columns[i].sTitle = col_name;
							columns[i].mData = col_name;
						}
					}
					for(var i in jsonData){
						var value = jsonData[i][col_name_copy];
						delete jsonData[i][col_name_copy];
						jsonData[i][col_name] = value;
					}
					col_name_copy = '';
					initDataTable(columns,jsonData);
				}else{
					alert('Column name has existed.Please enter an different column name.');
					$(this).parent().html(col_name_copy);
				}
			}
		});
		//修改tbody中的td中的数据
		$('#reportTableDiv').delegate('tbody tr td','dblclick',function(){
			//row_index,visible_col_index,all_index[0,1,1]
			var cell_value = $(this).html();
			$(this).html("<input type='text'class='form-control' value='"+cell_value+"'/>");
			$(this).children().first().focus();
		});
		$('#reportTableDiv').delegate('tbody tr td input','blur',function(){
			var posArray = findTbodyTdPos($(this).parent().get(0));
			var new_value = $(this).val();
			//对数据源jsonData进行相应的处理
			jsonData[posArray[0]][columns[posArray[1].sTitle]] = new_value;
			$(this).parent().html(new_value);
			initDataTable(columns,jsonData);
		});
		
		$('#reportTableDiv').delegate('tbody tr td input','keyup',function(event){
			var eventCode =  event.keyCode;
			if(eventCode == 13){
				var posArray = findTbodyTdPos($(this).parent().get(0));
				var new_value = $(this).val();
				//对数据源jsonData进行相应的处理
				jsonData[posArray[0]][columns[posArray[1].sTitle]] = new_value;
				$(this).parent().html(new_value);
				initDataTable(columns,jsonData);
			}
		});
		
		//sTitle 与 mData想对应相一致。
		function isColumnNameExist(col_name,col_pos){
			//"mData":"new_col_name"+col_control,"sTitle":"new_col_name"+col_control
			for(var i in columns){
				if(i != col_pos){
					if(columns[i].sTitle == col_name){
						return true;
					}
				}
			}
			return false;
		}
		
		$('#addNewReportOperate').on('click',function(){
			$('#addReport').modal('show');
			alert('init datatable');
			//初始化表格
			//window.setTimeout("initDataTable(columns,jsonData)",0,500);
			initDataTable(columns,jsonData);
		});
		//给表格tbody中的行绑定切换选中不选中的事件
//		$('#reportTable').delegate('tbody tr','click',function(){
//			$(this).toggleClass('rowselected');
//		});
		
		//获得点击的thead的位置，传入一个jQuery的th对象
		function findTheadTdPos(th){
			return th.get(0).cellIndex;
		}
		//获得tbody单元格的位置,传入一个node的td对象返回的是一个数组
		/*
		 * {int}: If nNode is given as a TR, 
		 * then a single index is returned, 
		 * or if given as a cell, 
		 * an array of [row index, column index (visible), 
		 * column index (all)] is given.
		 */
		function findTbodyTdPos(td){
			return reportTable.fnGetPosition(td);
		}
		
		//在某列之前增加一列
		$('#addColumnBefore').on('click',function(){
			if(theadTdPos.length == 0){
				$('#addFirstColumn').trigger('click');
			}else {
				//若选中多列，那么则加载列号最小的那一列前面
				var pos = theadTdPos[0];
				columns = initAddColumn(pos,0,false,false);
				//console.log(columns);
				jsonData = initJsonData(pos,false,false);
				//console.log(jsonData);
				newDataTablePrepare(columns,jsonData);
			}
		});
		
		//在某列之后增加一列
		$('#addColumnAfter').on('click',function(){
			if(theadTdPos.length == 0){
				$('#addLastColumn').trigger('click');
			}else{
				//
				var pos = theadTdPos[0];
				columns = initAddColumn(pos,1,false,false);
				jsonData = initJsonData(pos,false,false);
				newDataTablePrepare(columns,jsonData);
			}
		});
		
		//新列加在最前面
		$('#addFirstColumn').on('click',function(){
			columns = initAddColumn(theadTdPos,0,true,false);
			jsonData = initJsonData(theadTdPos,true,false);
			newDataTablePrepare(columns,jsonData);
		});
		
		//新列加在最后面
		$('#addLastColumn').on('click',function(){
			columns = initAddColumn(theadTdPos,0,false,true);	
			jsonData = initJsonData(theadTdPos,false,true);
			newDataTablePrepare(columns,jsonData);
		});
		
		//删除某一列,或者某几列
		$('#deleteColumn').on('click',function(){
			//判断是否已选中列
			if(theadTdPosCopy.length == 0){
				alert('No column selected.')
			}else{
				//alert(columns.length);
				theadTdPosCopy = theadTdPosCopy.sort();
				var col_name_delete = '';
				for(var i = 0 ;i<theadTdPosCopy.length;i++){
					//取出待删除的列的位置
					var col_num_delete = theadTdPosCopy[i];
					//取出要删除的列名
					col_name_delete = columns[col_num_delete-i].sTitle;
					columns.splice(col_num_delete-i, 1);
					//先删除对应的列
					//再删除对应的列的相应行数据
					for(var j in jsonData){
						delete jsonData[j][col_name_delete];
					}
				}
				theadTdPosCopy = [];
				newDataTablePrepare(columns,jsonData);
			}
		});
		
		//删除行，支持多行删除
		$('#deleteRow').on('click',function(e){
			e.preventDefault();
			var rowSelected = getSelectedRow(reportTable);
			if(rowSelected.length == 0){
				alert('No row selected.');
			}else{
				for(var i=0;i<rowSelected.length;i++){
					var trPos = findTbodyTdPos(rowSelected[i]);
					jsonData.splice(trPos-i,1);
				}
				newDataTablePrepare(columns,jsonData);
			}
		});
		
		//增加行
		$('#addRow').on('click',function(){
			var newRow = new Object();
			for(var i in columns){
				var attributeName = columns[i].mData;
				newRow[attributeName] = "new cell";
			}
			jsonData.push(newRow);
			newDataTablePrepare(columns,jsonData);
		});
		
		//新的DataTable初始化前的一些处理
		function newDataTablePrepare(columns,jsonData){
			theadTdPos = [];
			theadTdPosCopy = [];
			reportTable.fnClearTable();
			reportTable.fnDestroy();
			$('#reportTable thead').remove();
			initDataTable(columns, jsonData);
		}
		
		//addColumn before and after 
		//对表格列的增加
		function initAddColumn(theadTdPos,shift,first,last){
			col_control = col_control + 1;
			var new_column_name = "new_col" + col_control;//isColumnNameExist(col_name,col_pos)
			while(isColumnNameExist(new_column_name,columns.length)){
				col_control = col_control + 1;
				new_column_name = "new_col" + col_control;
			}
			var newColumnDef = {"mData":new_column_name,"sTitle":new_column_name,"bSortable":false};
			var tempColumns = new Array();
			if(!first && !last){//放在某列的前一列或后一列
				for(var i=0;i<columns.length;i++){
					if(i == theadTdPos && shift != 1){//加=在列之前
						tempColumns.push(newColumnDef);
						tempColumns.push(columns[i]);
					}else if(i == theadTdPos && shift == 1){//加在列之后
						tempColumns.push(columns[i]);
						tempColumns.push(newColumnDef);
					}else{
						tempColumns.push(columns[i]);
					}
				}
			}else if(first && !last){//放在最前面
				tempColumns.push(newColumnDef);
				for(var i=0;i<columns.length;i++){
					tempColumns.push(columns[i]);
				}
			}else if(!first && last){//放在最后面
				for(var i=0;i<columns.length;i++){
					tempColumns.push(columns[i]);
				}
				tempColumns.push(newColumnDef);
			}
			return tempColumns;
		}
		
		//对新增加的列进行值得初始化
		function initJsonData(threadTdPos,first,last){
			var new_col_name = "new_col"+col_control;
			for(var i=0;i<jsonData.length;i++){
				jsonData[i][new_col_name] = "new cell";
			}
			return jsonData;
		}
		
		//检测已选择的列中是否已存在这次点击的列
		function isExistInPos(th,theadTdPos){
			for (var i in theadTdPos){
				if(th == theadTdPos[i]){
					return i;
				}
			}
			return null;
		}
		
		//移除已存在的列位置
		function removeFromPos(th,theadTdPos){
			//删除数组中的已存在的元素
			theadTdPos.splice(th,1);
		}
		
		//print功能按下esc键后自定义功能
//		$('body').on('keydown',function(event){
//			var eventCode = event.keyCode;
//			var flag = $(this).hasClass('DTTT_Print');
//			if(eventCode == 27 && flag){
//				$('#addNewReportOperate').trigger('click');
//			}
//		});
		
	//------------------------------------------------------------------------------------
	});
	
	return {
		fun :fun,
	};
});

//				"aoColumnDefs":[
//				    {
//				    	"mRender":function(data, type, row){
//				    		return 'young'+data ;
//				    	},
//				    	"aTargets":[1],
//				    	"sClass":"center",
//				    	"sWidth":"8%"
//				    },{
//				    	"mRender" :function(data, type, row){
//				    		if(data == 0){
//				    			return "male";
//				    		}else if(data == 1){
//				    			return "female";
//				    		}else{
//				    			return "secret";
//				    		}
//				    	},
//				    	"aTargets":[4],
//				    	"sClass":"center",
//				    	"bSortable":true
//				    }
//				],
//		        "aoColumns": [
//		            { "mData": "EmpId" ,"sClass":"center"},
//		            { "mData": "Emp_CN_name" ,"sClass":"center"},
//		            { "mData": "Emp_EN_name" ,"sClass":"center"},
//		            { "mData": "EmpGroup" ,"sClass":"center"},
//		            { "mData": "Gender" },
//		            { "mData": "Designation" },
//		            { "mData": "EngageDate" ,"sClass":"center"},
//		            { "mData": "Location" },
//		            { "mData": "Email","bSortable":false}
//		        ],
		       /* "fnFooterCallback":function(nRow, aaData, iStart, iEnd, aiDisplay){
		        	var versionTotal = 0 ;
		        	var versionPage = 0;
		        	//计算所有记录
		        	for(var i=0;i<aaData.length;i++){
		        		versionTotal += aaData[i][3];
		        	}
		        	//计算当前这一页的记录
		        	for(var i=iStart;i<iEnd;i++){
		        		versionPage += aaData[aiDisplay[i]][3];
		        	}
		        	
		        	//alert('nRow:'+nRow);
		        	var nCells = nRow.getElementsByTagName('th');
		        	alert(nCells);
		        	nCells[1].innerHTML = 'versionTotal:'+versionTotal+',versionPage:'+versionPage;
				 }*/
		        /*"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		            // Append the grade to the default row class name 
		           //alert(nRow+' : '+aData.browser+' : '+iDisplayIndex);
		        },*/
		        /*
		        "fnFooterCallback": function ( nRow, aaData, iStart, iEnd, aiDisplay ) {
		        	var versionTotal = 0 ;
		        	var versionPage = 0;
		        	//计算所有记录
		        	for(var i=0;i<aaData.length;i++){
		        		versionTotal += aaData[i][2];
		        	}
		        	//计算当前这一页的记录
		        	for(var i=iStart;i<iEnd;i++){
		        		versionPage += aaData[aiDisplay[i]][2];
		        	}
		        	
		        	//alert('nRow:'+nRow);
		        	var nCells = nRow.getElementsByTagName('th');
		        	alert(nCells);
		        	nCells[1].innerHTML = 'versionTotal:'+versionTotal+',versionPage:'+versionPage;
		        },*/

			//初始化dataTable的表格样式
			/*fnInitComplete函数代表的是，dataTable初始化完成之后调用
			"fnInitComplete": function( oSettings ){
		     },
			 */
			/*
			 "fnFooterCallback":function(nRow, aaData, iStart, iEnd, aiDisplay){
			 }
			 */
			/*每一行绘好后会调一次这个函数，行的回调函数，进行一次额外的操作
			"fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		            // Append the grade to the default row class name 
		           alert(nRow+' : '+aData.browser+' : '+iDisplayIndex);
		        },
			 */
			/*每一次重绘表格之后的回调函数，意思是指，每一次重回之后都会掉一次这个回调函数，我们可以做一些额外的
			 事情
			 "fnDrawCallback": function( oSettings ) {
	             alert( 'DataTables has redrawn the table' );
	          },
			 */
			/*定义显示多少条数据菜单的内容，后面是显示的内容，前面是传输的值
			 "aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]]
			 */
			/*aoColumnDefs中可以定义一些表格初始化的格式，可以指定某些列或者某一列的格式，比如说：
			列的类型-"sType":"string/date/numeric/html
			 显示位置-"sClass":"center",是否是可排序的"bSortable":true/false,是否可见"bVisible":true/false
			其中："mRender"可以用于对表格中的数据在显示之前进行一些额外的处理，
			"aTargets":[0,1,2....],指定的是该定义适用的列，可定义负数，如-1，代表倒数第一列，-2代表倒数第二列以此类推。。。
			 "aoColumnDefs":[
				    {
				    	"mRender":function(data, type, row){
				    		return data + ''+'extra info.';
				    	},
				    	"aTargets":[1],
				    	"sClass":"center",
				    	//"bSortable":false,
				    	"bVisible":true
				    },
				    {
				    	"bSortable":false,
				    	"aTargets":[0,3]
				    }
				],
			 */
			/*设置向服务器提交的方式为POST
			"sServerMethod": "POST",
			 */
			/*额外的向服务器发送一些数据，数据以key，value的形式
			 "fnServerParams": function ( aoData ) {
		          aoData.push( { "name": "more_data", "value": "my_value" } );
		     },
			 */
			/*单击表格单元格自动将表格内容作为搜索条件进行搜索
			 "fnInitComplete": function () {
		            var that = this;
		            this.$('td').click( function () {
		                that.fnFilter( this.innerHTML );
		            });
		        },
		    */
	         /*定义表格有横向滚动条
	         "sScrollX": "100%",
	         "sScrollXInner": "110%",
	         "bScrollCollapse": true
	         */
			/*定义表格纵向滚动条
				"sScrollY": "200px",
		        "bScrollCollapse": true
			 */
			/*加上jquery的UI界面
			"bJQueryUI": true,
			 */
			/*换一个分页的样式，可以和bJQueryUI关键字连用，将表格样式变得更加好看
			"bJQueryUI": true,
   		"sPaginationType": "full_numbers"
			 */
			/*动态提示语言，可以改变datatable的默认提示信息
			"oLanguage": {
           "sLengthMenu": "Display _MENU_ records per page",
           "sZeroRecords": "Nothing found - sorry",
           "sInfo": "Showing _START_ to _END_ of _TOTAL_ records",
           "sInfoEmpty": "Showing 0 to 0 of 0 records",
           "sInfoFiltered": "(filtered from _MAX_ total records)"
   		 }
			 */
			/*
				"EmpId":100000,
				"Emp_CN_name":"老高",
				"Emp_EN_name":"Old Gao",
				"EmpGroup":"MacDonald",
				"Designation":"LV3 Support",
				"EngageDate":2013-09-21,
				"Location":"Su zhou",
				"Email":"old.gao@igate.com"
			 */
			//前台ajax动态的向后台去调数据
		

