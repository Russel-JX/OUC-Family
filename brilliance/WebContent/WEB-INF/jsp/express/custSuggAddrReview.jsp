<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../common/admin_header.jsp"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE HTML >
<html>
<head>
<title>快递信息审核</title>
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
</style>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>

<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/territory.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="<%=rootPath%>/js/tool.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	initDate();
	$('.btn-user-search').click(function(){
		buildDatables();
	});
	  
	
	//全选框
	$("body").on("change","#selectAll",function(){
		//alert(4);
		if($(this).is(":checked")){
			//$("input[name='id']").prop("checked",true);
			$(".custsuggaddr-checkbox0").prop("checked",true);
			//alert(5);
		}else{
			$("input[name='id']").prop("checked",false);
			//alert(6);
		}
	});	  
	
	//未开始的可选择
	$("body").on("click",".checkbox",function(e){
		e.stopPropagation();
		if(!$(this).hasClass("custsuggaddr-checkbox0")){
			alert("已通过或已拒绝的地址，不能操作！");
			return false;
		}
	});
	
	//通过
	$(".btn-custSuggAddr-pass").on("click",function(){
		var check_boxes = $("input[name='id']:checked");
		if(check_boxes.length<=0){ alert('请至少选择一条快递信息！');return;}
		
		if(confirm("您确定要将这批快递信息审核通过吗？")){
			var dropIds = '';
            check_boxes.each(function(index,item){
                dropIds += $(this).val()+","; 
            });
            $.ajax({
            	type: "POST",
            	url: "../page/custSuggestAddrReivew/reviewCustSuggAddr",
    			dataType: "json",
                data:{reviewType:"PASS",'suggestId':dropIds},
                success: function(jsonData){
    				if(jsonData.success){
    					alert("操作成功！");
    					buildDatables();
    				}else{
    					showErrorAlert("审核失败！");
    				}
    			},
    			error:function(){
    				alert("操作失败！");
    			}
            });
        }
        return false;
	});
	
	//拒绝按钮modal
	$(".btn-custSuggAddr-reject").on("click",function(){
		var check_boxes = $("input[name='id']:checked");
		if(check_boxes.length > 1||check_boxes.length == 0){ alert('请选择一条记录，进行拒绝！');return;}
		
		$("#hiddenId").val($(check_boxes[0]).val());
		$("#modal-custSuggAddr-reject").modal("show");
	});
	
	//确认拒绝
	$(".custSuggAddr-reject-confirm").on("click",function(){
		$.ajax({
			type: "POST",
			url: "../page/custSuggestAddrReivew/reviewCustSuggAddr",
			dataType: "json",
			async:false,
			data:{reviewType:"REJECT",suggestId:$("#hiddenId").val(),memo:$("#memo").val()},
			success: function(jsonData){
				if(jsonData.success){
					alert("操作成功！");
					buildDatables();
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				$("#modal-custSuggAddr-reject").modal("hide");
			},
			error:function(){
				alert("操作失败！");
			}
		});
	});
	
	//自动填充拒绝理由
	$("#rejectMemo").on("change",function(){
		$("#memo").val($(this).val());
	});
	
	  
});

function initDate(){
	var contents1 = new Array($('#provinceDiv1'),$('#cityDiv1'),$('#areaDiv1'));
	var names1 = new Array('provinceId','cityId','areaId');
	
	loadTerritoryDropDown(contents1,names1);
	
	buildDatables();
}


function buildDatables(){																						
	$('#custSuggAddrInfoDiv').empty().append('<table id="custSuggAddrInfoTable"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/custSuggestAddrReivew/showAllCustSuggAddr",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var dataList = jsonData.data.lstAddr;
				$('#custSuggAddrInfoTable').dataTable( {
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
			        "aaData":dataList,
			        "aoColumns":[
									{"sTitle": "<input type='checkbox' name='selectAll' id='selectAll' ><label for='selectAll'>全选</label>","mData":"","sDefaultContent":"","bSortable":false,"bSearchable": true, "bVisible": true},
                                    {"sTitle": "审核<br>状态","mData":"status","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "快递<br>公司","mData":"expressName","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "关键字","mData":"hotName","sDefaultContent": ""},
                                    {"sTitle": "详细地址","mData":"addressDetail","sDefaultContent": ""},
                                    {"sTitle": "联系人","mData":"contactName","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "手机","mData":"mobile","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "座机号码","mData":"officeNo","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "审核人","mData":"reviewerName","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "审核理由","mData":"reviewMemo","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "提供时间","mData":"createTime","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "hidden","mData":"id","bSearchable": true, "bVisible": false}
			                     ],
			        "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
			        	$('td:eq(0)', nRow).html(
                    			'<div class="checkbox">'+
                    				'<label>'+
                    					'<input type="checkbox" name="id" id="id" class="checkbox custsuggaddr-checkbox'+aData.status+'" value="'+aData.id+'">'+
                    				'</label>'+
                    			'</div>'
                    	);
			        	var statusText = "";
			        	switch(aData.status){
			        		case "0":statusText = "<font color='blue'>未开始</font>"; break;
			        		case "1":statusText = "<font color='gray'>已拒绝</font>";break;
			        		case "2":statusText = "<font color='green'>已通过</font>";break;
			        	}
			        	$('td:eq(1)', nRow).html(
			        			'<div class="status">'+
	                				'<label>'+statusText+'</label>'+
	                			'</div>'
                    	);
			        	
			        	/* //标记可操作的快递信息
			        	if(aData.status=="0"){
			        		$('td:eq(0)', nRow).append("<input type='hidden' class='operatable'/>"); 
			        		//$(nRow).css("background-color","red");
			        	} */
			        	
			        	
			        	var time = aData.createTime;
			        	var d="";
			        	 if(!isNull(time)){
			        		 d =new Date(time).format('yyyy-MM-dd');
			        	 }
			        	$('td:eq(10)', nRow).html(d);
			         }
			    });
				
				}
			},
			data:{
					expressCode:$('#expressItems').val(),hotName:$('#hotName').val(),status:$('#statusSelect').val(),createdBy:$('#createdBySelect').val(),
					provinceId:$('#provinceId').val(),cityId:$('#cityId').val(),areaId:$('#areaId').val()
				}
		});
}

</script>

<body>
	<div class="container">
		<form class="form-signin" id="form1" role="form" method="post" action="">
	<div class="panel panel-default">
		<div class="panel-heading">快递信息审核</div>
		<div style="padding:20px;">
				<div class="panel-body">
					<div class="form-group">
						<div class="row">
							<label for="expressCode" class="col-sm-2 control-label text-center">快递公司</label>
							<div class="col-sm-2">
								<select id="expressItems" name="expressCode" class="form-control"
									style="width: 150px">
									<option value="">--请选择--</option>
									<c:forEach var="expressInfo" items="${expresslst}">
					                   <option value="${expressInfo.expressCode}">${expressInfo.name}</option>
					               </c:forEach>
								</select>
							</div>
							<label for="hotName" class="col-sm-2 control-label text-center" style="">关键词</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="hotName"
									name="hotName" placeholder="如：小区/楼盘/商厦/路名" />
							</div>
							<label for="statusSelect" class="col-sm-2 control-label text-center" style="">审核状态</label>
							<div class="col-sm-2">
								<select id="statusSelect" name="statusSelect" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<option value="0">未开始</option>
									<option value="2">已通过</option>
									<option value="1">已拒绝</option>
								</select>
							</div>
							<%-- <c:if test="${sessionScope.isAdmin==1}">
							<label for="createdBySelect" class="col-sm-2 control-label text-center" style="">创建人</label>
							<div class="col-sm-2">
								<select id="createdBySelect" name="createdBySelect" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<c:forEach var="adminInfo" items="${adminlst}">
					                   <option value="${adminInfo.accountId}">${adminInfo.name}</option>
					               	</c:forEach>
								</select>
							</div>
							</c:if> --%>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
							<label for="provinceDv" class="col-sm-2 control-label text-center" style="">省份</label>
							<div class="col-sm-2" id="provinceDiv1">
							</div>
							<label for="cityDv" class="col-sm-2 control-label text-center" style="">城市</label>
							<div class="col-sm-2" id="cityDiv1">
							</div>
							<label for="areaDv" class="col-sm-2 control-label text-center" style="">地区</label>
							<div class="col-sm-2" id="areaDiv1">
							</div>
						</div>
					</div>
					<div class="form-group col-sm-12 text-center">
						<button type="button" class="btn btn-lg btn btn-primary btn-user-search">查询</button>
					</div>
				</div>
			</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading">用户推荐快递公司信息</div>
		<div class="panel-body">
			<div class="well well-lg">
				<button type="button" class="btn btn-lg btn-primary btn-custSuggAddr-pass" data-toggle="modal" >通过</button>
				<button type="button" class="btn btn-lg btn-primary btn-custSuggAddr-reject" data-toggle="modal">拒绝</button>
			</div>
			<hr>
			<div id="custSuggAddrInfoDiv" class="table-responsive"></div>
		</div>
	</div>
</form>
	</div>
	
	<!-- 拒绝快递公司	Modal开始 -->
	<div id="modal-custSuggAddr-reject" class="modal fade" tabindex="-1">
		<div class="modal_wrapper">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h3 class="modal-title">警告</h3>
					</div>
					<div class="modal-body text-center">
						<h4>请填写拒绝理由</h4>
						<input type="hidden" id="hiddenId" name="hiddenId" />
						<div style="margin-left:20%;margin-right:20%;">
							<div class="form-group" >
								<select id="rejectMemo" name="rejectMemo" class="form-control">
									<option value="">--请选择拒绝理由（可选）--</option>
									<option>此快递信息库中已有</option>
									<option>快递地址不存在</option>
									<option>联系人手机号码不存在</option>
									<option>联系人座机号码不存在</option>
								</select>
							</div>
							<div class="form-group">
								<textarea id="memo" rows="3"  class="form-control" placeholder="请输入拒绝理由..."></textarea>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<button type="button"
							class="btn btn-primary custSuggAddr-reject-confirm">确认拒绝</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 拒绝快递公司	Modal结束 -->
<%@ include file="../common/footer.jsp"%>
</body>
</html>