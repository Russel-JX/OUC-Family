<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<title>快递公司信息</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%=rootPath%>/css/ajaxfileupload.css" /> --%>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<script src="<%=rootPath%>/js/ajaxfileupload.js" type="text/javascript" ></script>

</head>
<SCRIPT type="text/javascript">
$(document).ready(function() {
	var dTable = null;
	buildDataTable();
	
	//要修改的公司(data，非元素)
	var editRow = null;
	//要修改的公司行号
	var editRowNum = -2;
	//要删除的公司id
	var expressId_to_delete = -1;
	//要删除的公司
	var express_to_delete = null;
	//新增快递公司
	$(".btn-new-express").on("click",function(){
		if(!formIsValidate()){
			return false;
		}
		$.ajax({
			type: "POST",
			url: "../page/express/saveExpress",
			dataType: "json",
			data:{name:$("#name").val(),postId:$("#postId").val(),telephone:$("#telephone").val(),expressMobile:$("#expressMobile").val(),serviceLine:$("#serviceLine").val()},
			success: function(jsonData){
				if(jsonData.success){
					dTable.fnAddData(jsonData.data.newExpress);
					emptyForm();
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#new-express-modal").modal("hide");
			}
		});
	});
	//修改快递公司
	$("body").on("click",".btn-edit-express",function(){
		editRow = $("#express_table").dataTable().fnGetData($(this).closest("tr").get(0));
		editRowNum = dTable.fnGetPosition($(this).closest("tr").get(0));
		$("#name").val(editRow.name);
		$("#postId").val(editRow.postId);
		$("#telephone").val(editRow.telephone);
		$("#expressMobile").val(editRow.mobile);
		$("#serviceLine").val(editRow.serviceLine);
		
		$("#express-logo").attr("src",".."+editRow.logoUrl);
	});
	//修改确认
	$(".btn-edit-express-confirm").on("click",function(){
		if(!editRow){
			alert("请选择要修改的快递公司！");
			return false;
		}
		if(!formIsValidate()){
			return false;
		}
		$.ajax({
			type: "POST",
			url: "../page/express/editExpress",
			dataType: "json",
			data:{id:editRow.id,name:$("#name").val(),postId:$("#postId").val(),telephone:$("#telephone").val(),expressMobile:$("#expressMobile").val(),serviceLine:$("#serviceLine").val()},
			success: function(jsonData){
				if(jsonData.success){
					//alert(jsonData.data.editedExpress.name+"--"+dTable.fnGetPosition(editRow));
					dTable.fnUpdate(jsonData.data.editedExpress,editRowNum);
					emptyForm();
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
			}
		});
	});
	//删除快递公司
	$("body").on("click",".btn-express-delete",function(){
		expressId_to_delete = $(this).attr("name");
		express_to_delete = $(this).closest("tr").get(0);
	});
	//删除确认
	$(".delete-express-confirm").on("click",function(){
		if(expressId_to_delete==-1){
			alert("删除失败！");
			return false;
		}
		$.ajax({
			type: "POST",
			url: "../page/express/removeExpress",
			dataType: "json",
			data:{id:expressId_to_delete},
			success: function(jsonData){
				if(jsonData.success){
					//前台删除一行
					dTable.fnDeleteRow(express_to_delete);
					if(expressId_to_delete==editRow.id){
						emptyForm();
					}
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#delete-express-modal").modal("hide");
			}
		});
	});
	//修改/新增前检验
	function formIsValidate(){
		if(($("#name").val())&&($("#telephone").val())){
			return true;
		}else{
			alert("请填写快递公司名称和免费电话！");
			return false;
		}
	}
	//清除form
	function emptyForm(){
		$("#name").val("");
		$("#postId").val("");
		$("#telephone").val("");
		$("#expressMobile").val("");
		$("#serviceLine").val("");
	}
	//上传logo
	$(".btn-logo-upload").on("click",function(){
		if(!editRow){
			alert("请选择要上传Logo的快递公司！");
			return false;
		}
		if(!formIsValidate()){
			return false;
		}
		/* $("#expressId").val(editRow.id);
		$("#logoForm").submit(); */
		
		
		
		//异步上传图片
		$.ajaxFileUpload({
			url:"../page/express/expressLogoUpload",
			secureuri:false,
			fileElementId:"fileUpload",
			dataType: "json",
			data:{"expressId":editRow.id},
			success: function (jsonData, status)
				{
					if(jsonData.success){//上传成功
						$("#express-logo").attr("src",".."+jsonData.data.editedExpress.logoUrl);
					
						//***上传成功，修改当前修改的这条express对应的html中的元数据***
						dTable.fnUpdate(jsonData.data.editedExpress,editRowNum);
						
						
						/* //要显示或修改的图片元素
						var picIMG = $(".pic_show[name='"+picName+"']");
						//下载按钮
						var downEle = $("#"+picDown);
						//异步修改更改过的图片title和href
						downEle.attr("href","user/upload!downLoadImage.action?abc="+data.wl_pictureFileName+"&abc_decoded="+data.newFileName+"");
						
						//alert(""+picDown+"downEle是： "+downEle+"ID是： "+downEle.attr("id")+"图片下载的href是："+downEle.attr("href"));
						
						//alert(picIMG.attr("name")+"--"+picIMG.attr("title"));
						//图片路径
						var picIMG_src =  "images/uploaded/"+data.newFileName;
						//alert(picIMG_src);
						//设置其src属性
						picIMG.attr("title",data.wl_pictureFileName);
						picIMG.attr("src",picIMG_src);
						
						//设置下载图片的超链接路径为修改后的路径 */
						
						
					}else{//上传失败
						//uploadErrorEle.html("<font color='red'>"+data.msgBack+"</font>");
					}
					
				},
			//当上传的图片大小超过struts.xml中的<constant name="struts.multipart.maxSize" value="10485760">时，执行
			error: function (data, status, e)
				{
					alert("请上传 PNG 格式的图片！大小不超过 2M ！");
					return false;
					//uploadErrorEle.html("<font color='red'>请上传大小低于2M的图片</font>");
				}	
		});
	});
	
	
	function buildDataTable(){
		
		$('#expressinfo').empty().append('<table id="express_table"  class="table table-striped table-bordered table-hover datatable"></table>');
		$.ajax({
			type: "POST",
			url: "../page/express/getAllExplst",
			dataType: "json",
			success: function(jsonData){
				//console.log(jsonData);
				if(jsonData.success){
					//console.log("get provinces success");
					var lstExpress = jsonData.data.lstExpress;
					dTable = $('#express_table').dataTable( {
						"bProcessing": true,
				        "bPaginate":true,
				        "bSort": true,
				        "sDom": "<'row'<'col-6'f><'col-6'l>r>t<'row'<'col-6'i><'col-6'p>>",
				        "oLanguage": {
				            "sSearch": "查找:",
				            "sZeroRecords": "没数据",
				            "sLengthMenu": "每页 _MENU_ 条",
				            "sNext": "下页",
				            "sInfo": "_START_ - _END_ of _TOTAL_",
				            "sInfoEmpty": "_START_ - _END_ of _TOTAL_"
				          },
				        "sPaginationType": "bootstrap",
				        "aaData":lstExpress,
				        "aoColumnDefs":
				        	[{"aTargets": [6],
				        		"mData": null,
				        		"mRender": function (data) {
				        			return '<button type="button" class="btn btn-warning btn-edit-express">修改</button>';
				        		}
				        	 },
				        	 {"aTargets": [7],
					        		"mData": null,
					        		"mRender": function (data) {
					        			return '<button type="button" class="btn btn-danger btn-express-delete" data-toggle="modal" data-target="#delete-express-modal" name="'+data+'">刪除</button>';
					        		}
					         }
				        	],
				        
				        "aoColumns":[
	                                    {"sTitle": "快递公司名称","mData":"name","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "快递公司查询代号","mData":"postId","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "免费电话","mData":"telephone","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "手机号码","mData":"mobile","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "客服电话","mData":"serviceLine","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "评价","mData":"evaluation","sDefaultContent":"","bSearchable": true, "bVisible": true},
	                                    {"sTitle": "修改","mData":"id","sDefaultContent":"","bSearchable": false, "bVisible": true},
	                                    {"sTitle": "刪除","mData":"id","sDefaultContent":"","bSearchable": false, "bVisible": true}
				                     ],
	                    "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {}
				    });
					}
				}
			});
	}
});

$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

</SCRIPT>
<body>

<div id="container" class="container">
	<div class="panel panel-default">
		<div class="panel-heading">新增|修改 快递公司</div>
		<div style="padding:20px;">
				<div class="panel-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label text-center">快递公司名称</label>
						<div class="col-sm-3">
							<input type="text" id="name" name="name" class="form-control"
								value="" />
						</div>
						<label for="postId" class="col-sm-2 control-label text-center">快递公司查询代号</label>
						<div class="col-sm-4 controls">
							<input type="text" id="postId" name="postId" class="form-control"
								value="" />
						</div>
					</div>
					<div class="form-group" style="margin-top:40px;">
						<label for="telephone" class="col-sm-2 control-label text-center" style="">免费电话</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="telephone"
								name="telephone" placeholder="请输入公司服务号码" />
						</div>
					</div>
					<div class="form-group">
						<label for="expressMobile" class="col-sm-2 control-label text-center">手机号码</label>
						<div class="col-sm-2">
							<input type="text" id="expressMobile" maxlength="11"
								id="expressMobile" class="form-control" />
						</div>
						<label for="serviceLine" class="col-sm-2 control-label text-center">客服电话</label>
						<div class="col-sm-2">
							<input type="text" class="form-control" id="serviceLine"
								name="serviceLine" placeholder="请输入电话" />
						</div>
					</div>
					<div class="form-group col-sm-12">
						<button type="button" class="btn btn-primary btn-new-express">新增</button>
						<button type="button"
							class="btn btn-warning btn-edit-express-confirm">修改</button>
					</div>
				</div>
				<div class="list-group-item row" >
					<div class="col-sm-2" style=""></div>
					<div class="col-sm-5" style="float: left;">
						<form name="logoForm" action="../page/express/expressLogoUpload" method="post" enctype="multipart/form-data">
						<div class="form-group">
							<input type="hidden" id="expressId" name="expressId" />
							<label for="fileUpload">快递公司头像</label><input type="file" id="fileUpload" name="fileUpload" />
							<p class="help-block">请选择PNG类型的图片，图片尺寸为 100 x 100.</p>
							<button type="button" class="btn btn-default btn-logo-upload">上传</button>
						</div>
						</form>
					</div>
					<div class="col-sm-5">
						<img id="express-logo" width="100px;" height="100px;" src="" alt="图片不存在" />
					</div>
				</div>
			</div>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading">快递公司信息</div>
		<div class="panel-body">
			<!-- <div class="row text-center">
				<div class="col-md-1"><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new-express-modal">新增</button></div>
			</div>
			<hr/> -->
			<div id="expressinfo" class="table-responsive"></div>
		</div>
	</div>
	
			<!-- 删除快递公司	Modal开始 -->
	<div id="delete-express-modal" class="modal fade"  tabindex="-1">
				<div class="modal_wrapper">
					<div class="modal-dialog">
						<div class="modal-content" >
							<div class="modal-header text-center">
								<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"><span class="glyphicon glyphicon-remove"></span></button>
								<h3 class="modal-title">警告</h3>
							</div>
							<div class="modal-body text-center">
								<h4>确认删除此快递公司？</h4>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
								<button type="button" class="btn btn-primary delete-express-confirm">删除</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- 删除快递公司	Modal结束 -->
	
</div>




</body>
<%@ include file="../../jsp/common/footer.jsp"%>
</html>
