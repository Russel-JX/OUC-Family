<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/admin_header.jsp"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE HTML >
<html>
<head>
<title>戴宗日记管理</title>
<style>
body {
	margin:0;
}
body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
#userInfoTbl_wrapper .row {
	margin-right:0px !important;
	margin-left:0px !important
}
.form-group{
    margin-right:15px;
}
.datepicker{
    z-index:2000 !important;
}
</style>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables.min.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/summernote.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/summernote-bs2.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/summernote-bs3.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/font-awesome.min.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/datepicker3.css"/>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/bootstrap-datepicker.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/summernote.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/summernote-zh-CN.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/territory.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="<%=rootPath%>/js/tool.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	var dataTables = null;//datatables Object
	initData();
	
	$('#diaryStartDateDiv,#diaryEndDateDiv,#diaryDetailDateDiv,#diaryPreviewDateDiv').datepicker({
		format: "yyyy-mm-dd",
		language:'cn'
	});
	
	$('.summernote').summernote({
		height: 200,
		lang: 'zh-CN'
	});
	
	/* $('.summernote').summernote({
		onImageUpload:function(files,editor, $editable){
			var f = files;
			var e = editor;
			var ed = $editable;
			//alert("files=");
			console.log('image upload:', files, editor, $editable);
		}
	}); */
	
	$('.btn-diary-search').click(function(){
		buildDatables();
	});
	  
	
	//全选框
	$("body").on("change","#selectAll",function(){
		if($(this).is(":checked")){
			$("input[name='id']").prop("checked",true);
		}else{
			$("input[name='id']").prop("checked",false);
		}
	});	  
	
	//删除确认
	$(".btn-diary-remove").on("click",function(){
		var check_boxes = $("input[name='id']:checked");
		if(check_boxes.length<=0){ alert('请至少选择一条记录！');return;}
		var alertBeforeDelete = "您确定要删除吗这些日记吗？";
		
		if(confirm(alertBeforeDelete)){
			var dropIds = '';
            check_boxes.each(function(index,item){
                dropIds += $(this).val()+","; 
            });
            $.ajax({
            	type: "POST",
            	url: "../DiaryInfo/deleteDiaryInfo",
    			dataType: "json",
                data:{'ids':dropIds},
                success: function(jsonData){
                	if(isLogedIn(jsonData)==true){
    					return;
    				}
    				if(jsonData.success){
    					showSuccessAlert("删除成功!");
			            var deletedRow = null;
    					for(var i=0;i<check_boxes.length;i++){
    						deletedRow = $(check_boxes[i]).parent().parent().parent().parent()[0];
    						if(i==(check_boxes.length-1)){
    							dataTables.fnDeleteRow(deletedRow,null,true);
    						}else{
    							dataTables.fnDeleteRow(deletedRow,null,false);
    						}
    						
    					}
    					//删除表格后，自动draw，不用手动页码跳转
    					//returnToOldPage(dataTables,"remove",deletedRow.length);
    				}else{
    					showErrorAlert("删除失败!");
    				}
    			},
    			error: function(){
    				alert("操作失败！");
    			}
            });
        }
        return false;
	});
	
	//新增日记
	$(".btn-diary-add").on("click",function(){
		$(".modal-title").html("新增日记");
		$(".btn-diary-submit-confirm,.btn-diary-preview").css("display",true);
		$(".btn-diary-modify-confirm").css("display","none");
		$("#modal-diary-detail").modal("show");
		
	});
	
	//新增确认
	$(".btn-diary-submit-confirm").on("click",function(){
		$.ajax({
			type: "POST",
			url: "../DiaryInfo/saveDiary",
			dataType: "json",
			async:false,
			data:{
					id:$("#diaryDetailId").val(),
					diaryTitle:$("#diaryDetailTitle").val(),
					author:$("#diaryDetailAuthor").val(),
					source:$("#diaryDetailSource").val(),
					diaryType:$("#diaryDetailType").val(),
					diaryDate:$("#diaryDetailDate").val(),
					content:$('.summernote').code()
				},
			success: function(jsonData){
				if(isLogedIn(jsonData)==true){
					return;
				}
				if(jsonData.success){
					showSuccessAlert("新增成功!");
					var newRowData = jsonData.data.newDiaryInfo;
					dataTables.fnAddData(newRowData);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#modal-diary-detail").modal("hide");
			},
			error:function(){
				alert("操作失败！");
			}
		});
	});
	
	//预览
	$(".btn-diary-preview").on('click',function(){
		$("#diaryPreviewTitle").val($("#diaryDetailTitle").val());
		$("#diaryPreviewAuthor").val($("#diaryDetailAuthor").val());
		$("#diaryPreviewSource").val($("#diaryDetailSource").val());
		//$("#diaryPreviewType").val($("#diaryDetailType").val());
		
		$("#diaryPreviewType").find('option:selected').text($("#diaryDetailType").find('option:selected').text());
		$("#diaryPreviewType").find('option:selected').val($("#diaryDetailType").find('option:selected').val());
		var newDate = new Date($('#diaryDetailDateDiv').datepicker("getDate")).format('yyyy-MM-dd')
		$('#diaryPreviewDate').val(newDate);
		$(".summernotePreview").html($('.summernote').code());
		$("#modal-diary-preview").modal("show");
	});
	
	//新增预览确认
	$(".btn-diary-preview-confirm").on("click",function(){
		$.ajax({
			type: "POST",
			url: "../DiaryInfo/saveDiary",
			dataType: "json",
			async:false,
			data:{
					diaryTitle:$("#diaryPreviewTitle").val(),
					author:$("#diaryPreviewAuthor").val(),
					source:$("#diaryPreviewSource").val(),
					diaryType:$("#diaryPreviewType").val(),
					diaryDate:$("#diaryPreviewDate").val(),
					content:$('.summernotePreview').code()
				},
			success: function(jsonData){
				if(isLogedIn(jsonData)==true){
					return;
				}
				if(jsonData.success){
					showSuccessAlert("新增成功!");
					var newRowData = jsonData.data.newDiaryInfo;
					dataTables.fnAddData(newRowData);
					returnToOldPage(dataTables,"add",null);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#modal-diary-preview").modal("hide");
				//$("#modal-diary-detail").modal("hide");
			},
			error:function(){
				alert("操作失败！");
			}
		});
	});
	
	//双击行选择修改
	$('#diaryInfoTable tbody').on("dblclick","tr",function(){
		if($(this).hasClass("selected")){
			$(this).removeClass("selected");
		}else{
			$("#diaryInfoTable tr.selected").removeClass("selected");
			$(this).addClass("selected");
		}
		$(".modal-title-detail").html("修改日记");
		$(".btn-diary-modify-confirm").css("display",true);
		$(".btn-diary-preview,.btn-diary-submit-confirm").css("display","none");
		//记录id
		var diaryId = $(this).find("[name='id']").val();
		//选中的行
		var thisRowData = dataTables.fnGetData(this);
		updateingRowIndex = dataTables.fnGetPosition(this);
		$("#diaryDetailId").val(diaryId);
		$("#diaryDetailTitle").val(thisRowData.diaryTitle);
		$("#diaryDetailAuthor").val(thisRowData.author);
		$("#diaryDetailSource").val(thisRowData.source);
		$("#diaryDetailType").val(thisRowData.diaryType);
		$('#diaryDetailDateDiv').datepicker("update",new Date(thisRowData.diaryDate).format('yyyy-MM-dd'));
		$('.summernote').code(thisRowData.content);
		$("#modal-diary-detail").modal("show");
	});
	
	
	//正在做修改的行索引
	var updateingRowIndex = null;
	//修改日记
	$(".btn-diary-edit").on("click",function(){
		$(".modal-title-detail").html("修改日记");
		$(".btn-diary-modify-confirm").css("display",true);
		$(".btn-diary-preview,.btn-diary-submit-confirm").css("display","none");
		
		var check_boxes = $("input[name='id']:checked");
		if(check_boxes.length > 1||check_boxes.length == 0){ alert('请选择一条记录，进行修改！');return;}
		
		//前端获取要修改的日记
		var thisDiary = $(check_boxes[0]);
		var diaryId = $(check_boxes[0]).val();
		var thisRow = thisDiary.parent().parent().parent().parent()[0];
		updateingRowIndex = dataTables.fnGetPosition(thisRow);
		//alert("正在修改的行是："+updateingRowIndex);
		var thisRowData = dataTables.fnGetData(thisRow);
		$("#diaryDetailId").val(diaryId);
		$("#diaryDetailTitle").val(thisRowData.diaryTitle);
		$("#diaryDetailAuthor").val(thisRowData.author);
		$("#diaryDetailSource").val(thisRowData.source);
		$("#diaryDetailType").val(thisRowData.diaryType);
		$('#diaryDetailDateDiv').datepicker("update",new Date(thisRowData.diaryDate).format('yyyy-MM-dd'));
		$('.summernote').code(thisRowData.content);
		$("#modal-diary-detail").modal("show");
		
	});
	
	//修改确认
	$(".btn-diary-modify-confirm").on("click",function(){
		$.ajax({
			type: "POST",
			url: "../DiaryInfo/modifyDiary",
			dataType: "json",
			async:false,
			data:{
					id:$("#diaryDetailId").val(),
					diaryTitle:$("#diaryDetailTitle").val(),
					author:$("#diaryDetailAuthor").val(),
					source:$("#diaryDetailSource").val(),
					diaryType:$("#diaryDetailType").val(),
					diaryDate:$("#diaryDetailDate").val(),
					content:$('.summernote').code()
				},
			success: function(jsonData){
				if(isLogedIn(jsonData)==true){
					return;
				}
				if(jsonData.success){
					showSuccessAlert("修改成功!");
					var updatedRowData = jsonData.data.updatedDiaryInfo;
					
					dataTables.fnUpdate(updatedRowData,updateingRowIndex,undefined,false,true);//数据改变后，不draw表格，否则会回到第一页。
					//dataTables.fnUpdate(updatedRowData,updateingRowIndex);
					returnToOldPage(dataTables,"update",null);
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#modal-diary-detail").modal("hide");
			},
			error:function(){
				alert("操作失败！");
			}
		});
	});
	
	//****获取当前操作的页码索引，（未完成：修改还是返回原来页码，新增返回到最后一页，删除返回到原来一页或前一页）****
	function getCurrentPageIndex(dataTables){//,actionType
		var settings = dataTables.fnSettings();
		//_iDisplayStart:当前页的第一条记录的index;_iDisplayLength:每页显示几条
		return settings._iDisplayStart/settings._iDisplayLength;
	}
	
	//返回到之前操作的页码
	function returnToOldPage(dataTables,actionType,effectedNumber){
		var settings = dataTables.fnSettings();
		//_iDisplayStart:当前页的第一条记录的index;_iDisplayLength:每页显示几条
		var displayStart = settings._iDisplayStart;
		var displayLength = settings._iDisplayLength;
		var totalNumber = settings.aoData.length;
		var oldPageIndex = displayStart/displayLength;
		switch(actionType){
			case "add":oldPageIndex = Math.ceil(totalNumber/displayLength)-1;break;
			case "update":break;
			case "remove":
				if(displayStart+displayLength>=totalNumber){
					var lastPageRecordNumber = totalNumber%displayLength;
					if(lastPageRecordNumber==effectedNumber){
						oldPageIndex = oldPageIndex-1;
					}
				}
				break;
			default:break;
		}
		dataTables.fnPageChange(oldPageIndex);
	}
	
	function buildDatables(){
		var queryString = [
		                   	{"name":"diaryTitle","value":$('#diaryTitle').val()},
		                   	{"name":"author","value":$('#author').val()},
		                   	{"name":"source","value":$('#source').val()},
		                   	{"name":"diaryStartDate","value":$('#diaryStartDate').val()},
		                   	{"name":"diaryEndDate","value":$('#diaryEndDate').val()},
		                   	{"name":"diaryType","value":$('#diaryType').val()},
		                  ];
		dataTables = $('#diaryInfoTable').dataTable( {
						"aLengthMenu":[[10, 25, 50, 100], [10, 25, 50, 100]],
						"iDisplayLength":10,//每页几条数据
						"iDisplayStart":0,//sDisplayLength*3,//显示的记录从哪儿开始
						
						//"bAutoWidth ":true,
						"bDestroy":true,
						"bProcessing": true,
				        "bPaginate":true,
				        "bSort": true,
				        "sDom": "<'row'<'col-7'f><'col-7'l>r>t<'row'<'col-7'i><'col-7'p>>",
				        "oLanguage": {
				            "sSearch": "查找:",
				            "sZeroRecords": "没数据",
				            "sLengthMenu": "每页 _MENU_ 条",
				            "sNext": "下页",
				            "sInfo": "_START_ - _END_ of _TOTAL_",
				            "sInfoEmpty": "_START_ - _END_ of _TOTAL_"
				          },
				        "sPaginationType": "bootstrap",
				        "fnServerParams":function (aoData){
				        	$.merge(aoData, queryString);
				        },
				        //"bServerSide": true,
				        "sAjaxSource":"../DiaryInfo/getDiaryList",
				        "sAjaxDataProp":"data.lstDiary", 
				        "aoColumns":[
										{"sTitle": "<input type='checkbox' name='selectAll' id='selectAll' ><label for='selectAll'>全选</label>","mData":"","sDefaultContent":"","bSortable":false,"bSearchable": true, "bVisible": true},
										{"sTitle": "日记日期","mData":"diaryDate","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "日记标题","mData":"diaryTitle","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "作者","mData":"author","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "来源","mData":"source","sDefaultContent": ""},
	                                    {"sTitle": "日记类型","mData":"diaryType","sDefaultContent": ""},
	                                    {"sTitle": "是否有效","mData":"deleteFlag","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "hidden","mData":"id","bSearchable": true, "bVisible": false}
				                     ],
				        "fnDrawCallback":function(oSettings){//
				        	//每次重新渲染表格后，回到之前操作的页面
				        },
				        "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
				        	$('td:eq(0)', nRow).html(
	                    			'<div class="checkbox">'+
	                    				'<label>'+
	                    					'<input type="checkbox" name="id" class="checkbox diaryInfo-checkbox'+aData.status+'" value="'+aData.id+'">'+
	                    				'</label>'+
	                    			'</div>'
	                    	);
				        	var diaryTypeText = "";
				        	switch(aData.diaryType){
				        		case "0":diaryTypeText = "日记"; break;
				        		case "1":diaryTypeText = "新闻";break;
				        		case "2":diaryTypeText = "漫画";break;
				        		case "3":diaryTypeText = "其他";break;
				        	}
				        	$('td:eq(5)', nRow).html(
				        			'<div class="status">'+
		                				'<label>'+diaryTypeText+'</label>'+
		                			'</div>'
	                    	);
				        	
				        	var deleteFlagText = "";
				        	switch(aData.deleteFlag){
				        		case "0":deleteFlagText = "无效"; break;
				        		case "1":deleteFlagText = "有效";break;
				        	}
				        	$('td:eq(6)', nRow).html(
				        			'<div>'+
		                				'<label>'+deleteFlagText+'</label>'+
		                			'</div>'
	                    	);
				        	
				        	/* //标记可操作的快递信息
				        	if(aData.status=="0"){
				        		$('td:eq(0)', nRow).append("<input type='hidden' class='operatable'/>"); 
				        		//$(nRow).css("background-color","red");
				        	} */
				        	
				        	
				        	var time = aData.diaryDate;
				        	var d="";
				        	if(!isNull(time)){
				        		d =new Date(time).format('yyyy-MM-dd');
				        	}
				        	$('td:eq(1)', nRow).html(d);
				         }
				    });
	}
	
	function initData(){
		buildDatables();
	}
	  
});






</script>

<body>
	<div class="container">
		<form class="form-signin" id="form1" role="form" method="post" action="">
	<div class="panel panel-default">
		<div class="panel-heading">戴宗日记查询</div>
		<div style="padding:20px;">
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
							<label for="diaryTitle" class="col-sm-2 control-label text-center">日记标题</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="diaryTitle" name="diaryTitle" placeholder="请输入日记标题" />
							</div>
							<label for="author" class="col-sm-2 control-label text-center" style="">作者</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="author" name="author" placeholder="请输入作者名" />
							</div>
							<label for="source" class="col-sm-2 control-label text-center" style="">来源</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="source" name="source" placeholder="请输入文章来源" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<label for="diaryType" class="col-sm-2 control-label text-center" style="">日记类型</label>
							<div class="col-sm-2">
								<select id="diaryType" name="diaryType" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<option value="0">日记</option>
									<option value="1">新闻</option>
									<option value="2">漫画</option>
									<option value="3">其他</option>
								</select>
							</div>
							<label for="diaryStartDateDiv" class="col-sm-2 control-label text-center" style="">开始日期</label>
							 <div class="col-sm-2">
					         	<div class="input-group date" id="diaryStartDateDiv" >
						           <input type="text" id="diaryStartDate" name="diaryStartDate" class="form-control" value="" placeholder="请输入日记时间 " />
						           <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						       </div>
					       	</div>
					       	<label for="diaryDateEndDiv" class="col-sm-2 control-label text-center" style="">结束日期</label>
							 <div class="col-sm-2">
					         	<div class="input-group date" id="diaryEndDateDiv" >
						           <input type="text" id="diaryEndDate" name="diaryEndDate" class="form-control" value="" placeholder="请输入日记时间 " />
						           <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
						       </div>
					       	</div>
							
							<!-- <label for="deleteFlag" class="col-sm-2 control-label text-center" style="">是否有效</label>
							<div class="col-sm-2">
								<select id="deleteFlag" name="deleteFlag" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<option value="0">无效</option>
									<option value="1">有效</option>
								</select>
							</div> -->
						</div>
					</div>
					<div class="form-group col-sm-12 text-center">
						<button type="button" class="btn btn-lg btn btn-primary btn-diary-search">查询</button>
					</div>
				</div>
			</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">戴宗日记管理(双击可直接修改哦)</div>
		<div class="panel-body">
			<div class="well well-lg">
				<button type="button" class="btn btn-lg btn-primary btn-diary-add" data-toggle="modal" >写日记</button>
				<button type="button" class="btn btn-lg btn-primary btn-diary-edit" data-toggle="modal" >修改日记</button>
				<button type="button" class="btn btn-lg btn-primary btn-diary-remove" data-toggle="modal">删除日记</button>
			</div>
			<hr>
			<div id="diaryInfoDiv" class="table-responsive">
				<table id="diaryInfoTable"  class="table table-striped table-bordered table-hover datatable"></table>
			</div>
		</div>
	</div>
</form>
	</div>
	
	
	<!-- 新增|修改日记	Modal开始 -->
	<div id="modal-diary-detail" class="modal fade" tabindex="-1" data-keyboard="true" data-backdrop="static">
		<div class="modal_wrapper" style="width:1250px;">
			<div class="modal-dialog" >
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h3 class="modal-title modal-title-detail">新增日记</h3>
					</div>
					<div class="modal-body text-center" style="padding-bottom:0px;">
						<input type="hidden" id="hiddenId" name="hiddenId" />
						<div class="panel-body" style="padding-bottom:0px;">
							<div class="form-group">
								<div class="row">
									<input type="hidden" id="diaryDetailId" name="diaryDetailId" />
									<label for="diaryDetailTitle" class="col-sm-1 control-label pull-left">标题</label>
									<div class="col-sm-3">
							           <input type="text" id="diaryDetailTitle" name="diaryDetailTitle" class="form-control" placeholder="请输入日记标题"/>
								   	</div>
								   	<label for="diaryDetailAuthor" class="col-sm-2 control-label">责任编辑</label>
									<div class="col-sm-2">
							           <input type="text" id="diaryDetailAuthor" name="diaryDetailAuthor" class="form-control" placeholder="请输入作者"/>
								   	</div>
								   	<label for="diaryDetailDateDiv" class="col-sm-2 control-label pull-left" style="">日期</label>
									<div class="col-sm-2">
							        	<div class="input-group date" id="diaryDetailDateDiv" >
								        	<input type="text" id="diaryDetailDate" name="diaryDetailDate" class="form-control" placeholder="请输入日记时间 " />
								            <span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
								        </div>
							       	</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="diaryDetailType" class="col-sm-1 control-label pull-left">日记类型</label>
									<div class="col-sm-3">
										<select id="diaryDetailType" name="diaryDetailType" class="form-control">
											<option value="">--请选择--</option>
											<option value="0">日记</option>
											<option value="1">新闻</option>
											<option value="2">漫画</option>
											<option value="3">其他</option>
										</select>
									</div>
									<input type="hidden" id="diaryTypeName" name="diaryTypeName" />
									<label for="diaryDetailSource" class="col-sm-1 control-label">来源</label>
									<div class="col-sm-3">
							           <input type="text" id="diaryDetailSource" name="diaryDetailSource" class="form-control" placeholder="请输入来源"/>
								   	</div>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:0px;">
								<label for="content" class="control-label">正文</label>
								<div class="summernote" ></div>
								<input type="hidden" id="content" name="content" />
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top:0px;padding-top:0px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary btn-diary-preview">预览</button>
						<button type="button" class="btn btn-primary btn-diary-submit-confirm">确定</button>
						<button type="submit" class="btn btn-primary btn-diary-modify-confirm">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增|拒绝日记	Modal结束 -->
	
	<!-- 新增日记 - 预览	Modal开始 -->
	<div id="modal-diary-preview" class="modal fade" tabindex="-1" data-backdrop="static">
		<div class="modal_wrapper" style="width:1150px;">
			<div class="modal-dialog" >
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h3 class="modal-title">新增日记 - 预览</h3>
					</div>
					<div class="modal-body text-center" style="padding-bottom:0px;">
						<div class="panel-body" style="padding-bottom:0px;">
							<div class="form-group">
								<div class="row">
									<label for="diaryPreviewTitle" class="col-sm-1 control-label pull-left">标题</label>
									<div class="col-sm-3">
							           <input type="text" disabled id="diaryPreviewTitle" name="diaryPreviewTitle" class="form-control" placeholder="请输入日记标题"/>
								   	</div>
								   	<label for="diaryPreviewAuthor" class="col-sm-2 control-label">责任编辑</label>
									<div class="col-sm-2">
							           <input type="text" disabled id="diaryPreviewAuthor" name="diaryPreviewAuthor" class="form-control" placeholder="请输入作者"/>
								   	</div>
								   	<label for="diaryPreviewDateDiv" class="col-sm-2 control-label pull-left" style="">日期</label>
									<div class="col-sm-2">
										<input type="text" disabled id="diaryPreviewDate" name="diaryPreviewDate" value="${diaryDate}" class="form-control" placeholder="请输入日记时间 " />
							       	</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row">
									<label for="diaryPreviewType" class="col-sm-1 control-label pull-left">日记类型</label>
									<div class="col-sm-3">
										<select id="diaryPreviewType" name="diaryPreviewType" class="form-control">
											<option value="${diaryType}" selected="selected">${diaryTypeName}</option>
										</select>
									</div>
									<label for="diaryPreviewSource" class="col-sm-1 control-label">来源</label>
									<div class="col-sm-3">
							           <input type="text" disabled id="diaryPreviewSource" name="diaryPreviewSource" class="form-control" placeholder="请输入来源"/>
								   	</div>
								</div>
							</div>
							<div class="form-group" style="padding-bottom:0px;">
								<label for="content" class="control-label">正文</label>
								<div class="summernotePreview"></div>
								<input type="hidden" id="contentPreview" name="content" />
							</div>
						</div>
					</div>
					<div class="modal-footer" style="margin-top:0px;padding-top:0px;">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
						<button type="submit" class="btn btn-primary btn-diary-preview-confirm">确定</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增日记 - 预览	Modal结束 -->
<%@ include file="../common/footer.jsp"%>
</body>
</html>