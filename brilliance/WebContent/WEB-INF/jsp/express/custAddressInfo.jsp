<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../jsp/common/admin_header.jsp"%>
<title>附近的快递信息</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>
<style type="text/css">
		body, html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";font-size:14px;}
		#l-map{height:300px;width:100%;}
		#r-result{width:100%;}
		
		/**地址提示放在最上层！**/	
		.tangram-suggestion-main{z-index: 9999;}
		.col-sm-4, .col-sm-6, .col-sm-10{padding-left:0px !important}
		
        #express_table_wrapper .row{margin-right:0px !important;margin-left:0px !important}
	</style>
<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/territory.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/tool.js" type="text/javascript"></SCRIPT>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vQKsPsU4CmEfIeBfM13F4RrD"></script>
	
</head>
<SCRIPT type="text/javascript">
$(document).ready(function() {
	initDate();
	
	$(".btn-custAddr-add").on("click",function(){
		$(".modal-title-storeandregion").text("新增常用快递员 - 门店和配送范围");
		$(".tempContainerTrigger").attr("disabled",false);
		$(".custAddr-edit-confirm").hide();
		$(".custAddr-edit-last-confirm").hide();
		$(".custAddr-edit-next-confirm").hide();
		$(".custAddr-add-confirm").show();
		$("#modal-custAddr-add").modal("show");
	});
	$(".btn-custAddr-addRegion").on("click",function(){
		$(".modal-title-region").text("新增常用快递员 - 配送范围");
		$(".custAddr-edit-confirmRegion").hide();
		$(".custAddr-edit-last-confirmRegion").hide();
		$(".custAddr-edit-next-confirmRegion").hide();
		$(".custAddr-add-confirmRegion").show();
		$("#modal-custAddr-addRegion").modal("show");
	});
	//发布
	$(".btn-custAddr-publish").on("click",function(){
		//$("#modal-custAddr-publish").modal("show");
		var val=$('input:radio[name="addressId"]:checked').val();
		var check_boxes = $("input[name='addressId']:checked");
		if(check_boxes.length<=0){ alert('请至少选择一条记录进行发布！');return;}
		
		if(confirm("确定要发布这些记录吗？")){
			var dropIds = '';
            check_boxes.each(function(index,item){
                dropIds += $(this).val()+","; 
            });
            $.ajax({
            	type: "POST",
            	url: "../page/favouriteAddress/publishAddrData.json",
    			dataType: "json",
                data:{'ids':dropIds},
                success: function(jsonData){
    				if(jsonData.success){
    					alert("发布成功！");
    				}else{
    					showErrorAlert("发布失败!");
    				}
    			}
            });
        }
        return false;
	});
	//发布确认
	$(".custAddr-publish-confirm").on("click",function(){
		$.ajax({
			type: "POST",
			url: "../page/favouriteAddress/publishAddrData.json",
			dataType: "json",
			data:
				{	
					tailAddress:$(this).val(),
					city:$("#cityId-new").find('option:selected').text(),//城市汉字名。如“苏州市”
					output:"json"
				},
			success: function(jsonData){
				if(jsonData.status==0){//成功
					//获取经纬度
					$(locationInfo)[0].value = jsonData.result.location.lng;
					$(locationInfo)[1].value = jsonData.result.location.lat;
					$(".custAddrValidMsg").css("color","green").text("此地址有效！");
				}else{//status非0：地址不存在
					$(".custAddrValidMsg").css("color","red").text("此地址为无效地址，请重新输入有效地址！");
				}
			},
			error: function(){
				$(".custAddrValidMsg").css("color","red").text("地址验证请求失败，请重试！");
			}
		});
	});
	//显示modal时，绑定建议input
	$('#modal-custAddr-addRegion').on('show.bs.modal', function (e) {
		ac = new BMap.Autocomplete(
				{"input" : "tailAddress-newRegion"
				,"location" : map
		});
	});
	//隐藏modal时，清除提示信息和动态DOM
	$('#modal-custAddr-add,#modal-custAddr-addRegion').on('hidden.bs.modal', function (e) {
		$(".custHotNameValidMsg").text('');
		$(".custAddrValidMsg").text('');
		
		//清除之前数据
		$("#tempAddrContainer").find(".newCustAddrDiv").remove();
		
		//上一个下一个按钮恢复
		$(".operation-btn").attr("disabled",false);
		
		//重新加载数据
		buildDataTable(getQueryParam());
	});
	
	//获取选择的查询条件
	function getQueryParam(){
		var code=$("#expressItems").val();
		var hotName=$("#hotName").val();
		var tailAddress=$("#tailAddress").val();
		var provinceId = $("#provinceId").val();
		var cityId = $("#cityId").val();
		var areaId = $("#areaId").val();
		var dataType = $("#dataTypeSelect").val();
		var archiveflag = $("#archiveSelect").val();
		var source = $("#sourceSelect").val();
		var createdBy = $("#createdBySelect").val();
		
		var param = {expressCode:code,hotName:hotName,tailAddress:tailAddress,provinceId:provinceId,cityId:cityId,areaId:areaId,dataType:dataType,archiveflag:archiveflag,source:source,createdBy:createdBy};
		return param;
	}
	
	var count=0;
	//加号按钮
	$(".newCustAddr-add").on("click",function(){
		addTempAddrDomController("add","");
	});
	
	//动态添加临时地址DOM（包括添加的空DOM和修改时填充的数据）
	function addTempAddrDomController(addOrUpdate,addressInfo){
		if(addOrUpdate=="add"){
			addTempAddrDom(addOrUpdate,addressInfo);
			return false;
		}
		if(addOrUpdate=="update"){
			//标记要的修改关键字为后台传过来的。
			$("#storeAddressHotName-new").data("originalHotNameValue",addressInfo[0].hotName);
			$("#storeAddressHotName-new").data("originalExpressValue",addressInfo[0].expressCode);
			//删除第一个为门店的地址
			/* var addressInfo = $.grep( addressInfo, function(n,i){
				  if(i!=0){
					  return true; 
				  }
			}); */
			for(var i=1;i<addressInfo.length;i++){
				//alert("id="+addressInfo[i].id);
				addTempAddrDom(addOrUpdate,addressInfo[i]);
			}
		}
		
		
	}
	function addTempAddrDom(addOrUpdate,addressInfo){
		count++;
		var tempLocation_long = "tempLocation-new-long"+count;
		var tempLocation_lati = "tempLocation-new-lati"+count;
		var tempAddr = "tailAddress-new-temp"+count;
		var tempProvince = "provinceDiv-temp"+count;
		var tempCity = "cityDiv-temp"+count;
		var tempArea = "areaDiv-temp"+count;
		var addrIdentify = "tailAddress-new"+count;
		var hotNameIdentify = "hotName-new"+count;
		$("#tempAddrContainer").append(
			'<div class="form-group newCustAddrDiv">'+
				'<div class="row">'+
					'<input type="hidden" class="longArray" id='+tempLocation_long+' name='+tempLocation_long+' value='+(addOrUpdate=="add"?"":addressInfo.longitude)+' >'+
					'<input type="hidden" class="latiArray" id='+tempLocation_lati+' name='+tempLocation_lati+' value='+(addOrUpdate=="add"?"":addressInfo.latitude)+' >'+
					'<input type="hidden" class="hotNameIsValid storeHotNameisValid" />'+
					'<input type="hidden" class="id" value='+(addOrUpdate=="add"?"":addressInfo.id)+' >'+
					'<input type="hidden" class="addressId" value='+(addOrUpdate=="add"?"":addressInfo.addressId)+' >'+
					'<div class="col-sm-2"></div>'+
					'<label  class="col-sm-2 control-label text-center" style=""><input type="checkbox" name="tailAddresses-new-temp" id='+tempAddr+' ></label>'+
					'<div class="col-sm-2">'+
						'<input type="text" class="form-control hotNameArray hotNameArray_region" id='+hotNameIdentify+' name='+hotNameIdentify+' placeholder="如：小区/楼盘/商厦/路名" value='+(addOrUpdate=='add'?'':addressInfo.hotName)+' >'+
					'</div>'+
					'<!--'+
					'<div class="col-sm-5 tempAddrInfo">'+
						'<div class="col-sm-4" id='+tempProvince+'></div>'+
						'<div class="col-sm-4" id='+tempCity+'></div>'+
						'<div class="col-sm-4" id='+tempArea+'></div>'+
					'</div>'+
					'-->'+
					'<div class="col-sm-4">'+
					 	'<input type="text" class="form-control suggestionAddr" id='+addrIdentify+' name='+addrIdentify+' placeholder="请输入配送范围，如苏州市吴中区桂花新村四号楼" />'+
					'</div>'+
					'<!-- <label for='+hotNameIdentify+' class="col-sm-2 control-label text-center">关键词'+count+'</label> -->'+
				'</div>'+
			'</div>'				
		);
		var contents = new Array($("#"+tempProvince),$('#'+tempCity),$('#'+tempArea));
		var names = new Array('provinceId-new'+count,'cityId-new'+count,'areaId-new'+count);
		
		loadTerritoryDropDown(contents,names);
		//给每个动态生成的文本框，增加事件
		ac = new BMap.Autocomplete(
				{"input" : addrIdentify
				,"location" : $("#cityId-new").find("option:selected").text()
		});
		//建议地址
		$("#tailAddress-new"+count).val((addOrUpdate=="add"?"":addressInfo.tailAddress));
		//隐藏域标识
		if(addOrUpdate=="update"){
			$(".storeHotNameisValid").val("Y");
		}/* else if(addOrUpdate=="add"){
			$(".storeHotNameisValid").val("N");
		} */
		//是修改的。前台记录原始值。用于关键字验证。规避原来的关键字在修改时，不修改，数据库中也存在，造成前端验证失败。
		if(addOrUpdate=="update"){
			$("#"+hotNameIdentify).data("originalHotNameValue",addressInfo.hotName);
		}
		
		
	}
	
	//修改配送范围或门店和配送范围 - 填充值
	$(".btn-custAddr-edit").on("click",function(){
		//判断修改的门店和派送范围，还是单独的配送范围
		//var val=$('input:[name="addressId"]:checked').val();
		var check_boxes = $("input[name='addressId']:checked");
		if(check_boxes.length > 1||check_boxes.length == 0){ alert('请选择一条记录，进行修改！');return;}
		
		var dataType = $(check_boxes[0]).siblings("input").val();
		var addressId = $(check_boxes[0]).val();
		
		var url="";
		var flag="firstfill";//第一次填充：firstfill
		if(dataType=="1"){//门店
			$(".modal-title-storeandregion").text("修改常用快递员 - 门店和配送范围");
			$(".custAddr-add-confirm").hide();
			$(".tempContainerTrigger").attr("disabled","disabled");
			$(".custAddr-edit-last-confirm").show();
			$(".custAddr-edit-next-confirm").show();
			$("#tempAddrContainer").show();
			$(".custAddr-edit-confirm").show();
			$("#modal-custAddr-add").modal("show");
			
			url="../page/favouriteAddress/getPackedCustAddrInfo.json";
		
		}else if(dataType=="2"){//配送范围
			$(".modal-title-region").text("修改常用快递员 - 配送范围");
			$(".custAddr-add-confirmRegion").hide();
			$(".custAddr-edit-confirmRegion").show();
			$(".custAddr-edit-last-confirmRegion").show();
			$(".custAddr-edit-next-confirmRegion").show();
			$("#modal-custAddr-addRegion").modal("show");
			
			url="../page/favouriteAddress/getCustAddrInfo.json";
		}
		
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			async:false,
			data:{addressId:addressId,dataType:dataType},
			success: function(jsonData){
				if(jsonData.success){
					var info= jsonData.data.lstAddr;
					if(jsonData.data.lstAddr.length > 0){
						if(dataType=="1"){
							fillPackedModal(info);
							disabledBtn(flag,dataType,jsonData.data.isTop,jsonData.data.isEnd);
						}else if(dataType=="2"){
							fillRegionModal(info[0]);
							disabledBtn(flag,dataType,jsonData.data.isTop,jsonData.data.isEnd);
						}
					}
					
				}else{
					showErrorAlert(jsonData.message.messageContent);
				}
				//$("#delete-express-modal").modal("hide");
			}
		});
	});
	
	//回车让建议input失去焦点
	$(window).keyup(function(e){
		if(e.keyCode==13){
			if($(':focus').is(".suggestionAddr,#tailAddress-new,#tailAddress-newRegion")){
				$(':focus').blur();
			}
			if($(':focus').is(".hotNameArray")){
				$(':focus').blur();
			}
		}
	});
	
	//详细地址异步验证
	$("body").on("focusout",".suggestionAddr,#tailAddress-new,#tailAddress-newRegion",function(){
		//当前的建议框
		var currentAddrIdentify = $(this);
		var locationInfo = $(currentAddrIdentify).parent().siblings("input");
		//清除经纬度信息
		$(locationInfo)[0].value = "";
		$(locationInfo)[1].value = "";
		$.ajax({
			type: "POST",
			url: "../page/favouriteAddress/custAddrValidation",
			dataType: "json",
			//后台拼接http://api.map.baidu.com/geocoder/v2/?address=&city=&output=&ak=
			data:
				{	
					tailAddress:$(this).val(),//详细地址信息。如“苏州市高新区管委会” 或 “高新区管委会”
					city:$("#cityId-new").find('option:selected').text(),//城市汉字名。如“苏州市”
					output:"json"
				},
			success: function(jsonData){
				if(jsonData.status==0){//成功
					//获取经纬度
					$(locationInfo)[0].value = jsonData.result.location.lng;
					$(locationInfo)[1].value = jsonData.result.location.lat;
					$(".custAddrValidMsg").css("color","green").text("此地址有效！");
				}else{//status非0：地址不存在
					$(".custAddrValidMsg").css("color","red").text("此地址为无效地址，请重新输入有效地址！");
				}
			},
			error: function(){
				$(".custAddrValidMsg").css("color","red").text("地址验证请求失败，请重试！");
			}
		});
	});
	
	//关键字重复验证
	$("body").on("focusout",".hotNameArray,#regionAddressHotName-new",function(){
		//不填时，不验证
		if($(this).val()){
			//单独增加派送范围的关键字，不验证此项
			if($(this).attr("id")!="regionAddressHotName-new"){
				//先验证和之前输入的关键字是否有重复
				if(hotNameValidateFrontEnd($(this))==false){
					return false;
				}
			}
			
			var expressCode = $(this).parent().siblings(".expressComp").find("select").find('option:selected').val();
			
			if($(this).hasClass("hotNameArray")){
				if(!$("#expressItems-new").find('option:selected').val()){
					alert("请先选择快递总公司！");
					return false;
				}
			}
			if($(this).attr("id")=="regionAddressHotName-new"){
				if(!$("#expressItems-newRegion").find('option:selected').val()){
					alert("请先选择快递总公司！");
					return false;
				}
			}
			
			//后台返回的要修改的关键字。值不变且公司不变，不验证。
			//alert("原来关键字： "+$(this).data("originalHotNameValue")+" 改后的关键字： "+$(this).val()+" 原来公司代号： "+$("#storeAddressHotName-new").data("originalExpressValue")+" 后来公司： "+$("#expressItems-new").find('option:selected').val());
			if(($(this).data("originalHotNameValue")==$(this).val())&&($("#storeAddressHotName-new").data("originalExpressValue")==$("#expressItems-new").find('option:selected').val())){
				return false;
			}
			
			hotNameValidateBackEnd(expressCode,$(this));
		}
		
	});
	//快递公司改变，也验证关键字有效性
	$("#expressItems-new,#expressItems-newRegion").on("change",function(){
		var expressCode = $(this).find('option:selected').val();
		//单独门店
		if($(this).attr("id")=="expressItems-newRegion"){
			if($("#regionAddressHotName-new").val()){
				hotNameValidateBackEnd(expressCode,$(this).parent().siblings(".hotN").find("input"));
			}
		}else{//门店和派送范围
			var hotNameArray = $(".hotNameArray");
			
			if($("#tempAddrContainer").is(":visible")){
				//要进行验证的关键字元素
				var toValidateHotNames=[];
				for(var i=0;i<hotNameArray.length;i++){
					//后台返回的要修改的关键字。值不变且公司不变，不验证。
					//alert("原来关键字： "+$(hotNameArray[i]).data("originalHotNameValue")+" 改后的关键字： "+hotNameArray[i].value+" 原来公司代号： "+$("#storeAddressHotName-new").data("originalExpressValue")+" 后来公司： "+$("#expressItems-new").find('option:selected').val());
					if(($(hotNameArray[i]).data("originalHotNameValue")==hotNameArray[i].value)&&($("#storeAddressHotName-new").data("originalExpressValue")==$("#expressItems-new").find('option:selected').val())){
						continue;
					}
					toValidateHotNames[i] = hotNameArray[i];
					//alert("要验证的hotname: "+$(hotNameArray[i]).val());
					/* //只要有一个配送范围,即验证
					if(hotNameArray[i].value){
						hotNameValidateBackEnd(expressCode,$(".hotNameArray"));
						break;
					} */
				}
				if(toValidateHotNames.length>0){
					hotNameValidateBackEnd(expressCode,toValidateHotNames);
				}
				
			}
		}
		
		
		/* //存在配送地址时
		if($(".hotNameArray").is("input")){
			
		} */
		//$(currentHotName).parent().siblings(".hotNameIsValid").val("N");
	});
	//前台验证关键字是否重复
	function hotNameValidateFrontEnd(hotNameObj){
		var hotNameArray = $(".hotNameArray");
		for(var i=0;i<hotNameArray.length;i++){
			//alert("hotNameArray[i].value="+hotNameArray[i].value+"||"+"hotNameObj.value="+hotNameObj.val());
			if(hotNameObj.attr("id")==hotNameArray[i].id){
				continue;
			}
			if(hotNameArray[i].value==hotNameObj.val()){
				alert(hotNameObj.val()+" 关键字输入重复！");
				$("#"+hotNameObj.attr("id")).parent().siblings(".hotNameIsValid").val("N");
				return false;
				break;
			}
		}
		return true;
	}
	//后台验证关键字
	function hotNameValidateBackEnd(expressCode,hotNameObj){
		var hotNameArray = [];
		var hotNameIdentifyArray = [];
		$.each(hotNameObj, function(index,item){
			//alert("item.value"+item.value);
			if(item.value){
				hotNameArray[index] = item.value;
				hotNameIdentifyArray[index] = item.id;
				//alert("要验证的关键字是： "+hotNameArray[index]);
			}
		});
		
		$.ajax({
			type: "POST",
			url: "../page/favouriteAddress/validateHotName",
			dataType: "json",
			data:
				{	
					expressCode:expressCode,
					hotNameArray:hotNameArray,
					hotNameIdentifyArray:hotNameIdentifyArray
				},
			success: function(jsonData){
				if(jsonData.data.invalidHotNames!=""){
					$(".custHotNameValidMsg").css("color","red").text(jsonData.data.invalidHotNames+"关键字已存在，请重新输入关键字！");
					var invalidList = jsonData.data.invalidList;
					for(var i=0;i<invalidList.length;i++){
						$("#"+invalidList[i]).parent().siblings(".hotNameIsValid").val("N");
					}
				}else{
					$(".custHotNameValidMsg").css("color","green").text(jsonData.data.validHotNames+"关键字有效！");
					var validList = jsonData.data.validList;
					for(var i=0;i<validList.length;i++){
						$("#"+validList[i]).parent().siblings(".hotNameIsValid").val("Y");
					}
				}
			},
			error: function(){
				$(".custHotNameValidMsg").css("color","red").text("关键字验证请求失败，请重试！");
			}
		});
	}
	
	//新增门店和派送范围，或修改门店和配送范围 - 确认
	$(".custAddr-add-confirm,.custAddr-edit-confirm,.custAddr-edit-last-confirm,.custAddr-edit-next-confirm").on("click",function(){
		if(!formIsValidateAdd()){
			return false;
		}
		var url="";
		var flag = "";//新增：add，修改：update，上一条：last，下一条：next;
		var data = null;
		//var nextOperation="done";//下一步操作。done：结束；next：下一条记录；last：上一条记录
		
		//表明是新增操作
		if($(".custAddr-edit-confirm").is(":hidden")){
			//新增门店
			if($("#tempAddrContainer").is(":hidden")){
				flag="add";
			}else{//新增门店和配送范围
				flag="add";
			}
			url = "../page/favouriteAddress/editCustAddrpacked";
		}
		//表明是修改操作
		if($(".custAddr-add-confirm").is(":hidden")){
			url = "../page/favouriteAddress/editCustAddrpacked";
			if($(this).hasClass("custAddr-edit-last-confirm")){
				flag = "last";
			}else if($(this).hasClass("custAddr-edit-next-confirm")){
				flag = "next";
			}else if($(this).hasClass("custAddr-edit-confirm")){
				flag="update";
			}
		}
		data = getRequestData(flag);
		
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data:data,
			success: function(jsonData){
				if(jsonData.success){
					//清除之前数据
					$("#tempAddrContainer").find(".newCustAddrDiv").remove();
					//alert("操作成功！");
					if(flag=="add"){//修改
						alert("操作成功！");
						$("#modal-custAddr-add").modal("hide");
					}
					if(flag=="last"||flag=="next"){//修改并上/下一条
						if(jsonData.data.lstAddr.length > 0){
							fillPackedModal(jsonData.data.lstAddr);
							disabledBtn(flag,'1',jsonData.data.isTop,jsonData.data.isEnd);
							return false;
						}/* else{
							alert("已经是第一条或最后一条记录了");
							return false;
						} */
					}
					if(flag=="update"){
						alert("修改成功！");
						emptyForm();
						$("#modal-custAddr-add").modal("hide");
					}
					//buildDataTable();
					//$("#modal-custAddr-add").modal("hide");
					/* if(flag=="update"){//修改
						if(jsonData.data.lstAddr.length>0){
							fillPackedModal(jsonData.data.lstAddr,jsonData.data.isTop,jsonData.data.isEnd);
							return false;
						}else{
							if(nextOperation!="done"){
								alert("已经是第一条或最后一条记录了");
							}
						}
					} */
					
				}else{
					alert("后台操作有错误！");
				}
			},
			error: function(){
				alert("操作失败！");
			}
		});
	});
	//新增/修改 派送范围 - 确认
	$(".custAddr-add-confirmRegion,.custAddr-edit-confirmRegion,.custAddr-edit-next-confirmRegion,.custAddr-edit-last-confirmRegion").on("click",function(){
		if(!formIsValidateAddRegion()){
			return false;
		}
		var url="";
		var flag = 0;//0：新增派送范围，1修改派送范围
		var nextOperation="done";//下一步操作。done：结束；next：下一条记录；last：上一条记录
		
		if($(".custAddr-edit-confirmRegion").is(":hidden")){
			flag=0;
			url = "../page/favouriteAddress/addCustAddrRegion";
		}
		//表明是修改操作
		if($(".custAddr-add-confirmRegion").is(":hidden")){
			url = "../page/favouriteAddress/editCustAddrRegion";
			flag=1;
			if($(this).hasClass("custAddr-edit-next-confirmRegion")){
				nextOperation = "next";
			}else if($(this).hasClass("custAddr-edit-last-confirmRegion")){
				nextOperation = "last";
			}else if($(this).hasClass("custAddr-edit-confirmRegion")){
				nextOperation="update";
			}
		}
		
		$.ajax({
			type: "POST",
			url: url,
			dataType: "json",
			data:{
				flag:flag,
				nextOperation:nextOperation,
				serialId:$("#serialId").val(),
				regionAddressId:$("#regionAddressId-new").val(),
				regionParentId:$("#regionParentId-new").val(),
				regionAddressHotName_new:$("#regionAddressHotName-new").val(),
				regionExpressCode:$("#expressItems-newRegion").find('option:selected').val(),
				regionContactName:$("#contact-newRegion").val(),//storeAddress
				regionMobile:$("#mobile-newRegion").val(),
				regionOfficeNo:$("#officeNo-newRegion").val(),
				regionAddressPCA:$("#provinceId-newRegion").find('option:selected').text()+" "+$("#cityId-newRegion").find('option:selected').text()+" "+$("#areaId-newRegion").find('option:selected').text(),
				regionAddressProvinceId:$("#provinceId-newRegion").val(),
				regionAddressCityId:$("#cityId-newRegion").val(),
				regionAddressAreaId:$("#areaId-newRegion").val(),
				tailAddress_newRegion:$("#tailAddress-newRegion").val(),
				regionTailAddress_longt:$("#regionTailAddress-longt").val(),
				regionTailAddress_lati:$("#regionTailAddress-lati").val()
			},
			success: function(jsonData){
				if(jsonData.success){
					//showSuccessAlert("操作成功！");
					if(flag==1){
						if(nextOperation=="update"){
							alert("修改成功！");
							emptyFormRegion();
							$("#modal-custAddr-addRegion").modal("hide");
							return false;
						}
						if(jsonData.data.lstAddr.length>0){
							fillRegionModal(jsonData.data.lstAddr[0]);
							disabledBtn(nextOperation,'2',jsonData.data.isTop,jsonData.data.isEnd);
							return false;
						}/*else{
							if(nextOperation!="done"){
								alert("已经是第一条或最后一条记录了");
								return false;
							}
						}*/
					}else if(flag==0){
						alert("操作成功！");
						$("#modal-custAddr-addRegion").modal("hide");
					}
					emptyFormRegion();
					//buildDataTable();
					//$("#modal-custAddr-addRegion").modal("hide");
				}else{
					alert("操作有错误！");
				}
			},
			error: function(){
				alert("操作失败！");
			}
		});
	});
	
	//修改时填充modal 
	function fillRegionModal(info){
		$("#serialId").val(info.id);
		$("#regionAddressId-new").val(info.addressId);
		$("#regionParentId-new").val(info.parentId);
		$("#expressItems-newRegion").val(info.expressCode);
		$("#regionAddressHotName-new").val(info.hotName);
		$("#contact-newRegion").val(info.contactName);
		$("#mobile-newRegion").val(info.mobile);
		$("#officeNo-newRegion").val(info.officeNo);
		$("#provinceId-newRegion").val(info.provinceId);
		$("#provinceId-newRegion").change();
		$("#cityId-newRegion").val(info.cityId);
		$("#cityId-newRegion").change();
		$("#areaId-newRegion").val(info.areaId);
		$("#tailAddress-newRegion").val(info.tailAddress);
		
		$("#regionTailAddress-longt").val(info.longitude);
		$("#regionTailAddress-lati").val(info.latitude);
		//$("#modal-custAddr-edit").modal("show");
	}
	/*填充门店和配送范围Modal
	@param info.地址记录数组。
	//@param flag.操作的动作。next:下一条；last：上一条；firstfill:第一次填充Modal。
	@param isEnd.是否是最后一条数据
	@param isTop.是否是第一条数据
	*/
	function fillPackedModal(info){
		//门店
		$("#serialId").val(info[0].id);
		$("#addressId-new").val(info[0].addressId);
		$("#expressItems-new").val(info[0].expressCode);
		$("#storeAddressHotName-new").val(info[0].hotName);
		
		$("#storeHotName").val("Y");
		
		$("#contact-new").val(info[0].contactName);
		$("#mobile-new").val(info[0].mobile);
		$("#officeNo-new").val(info[0].officeNo);
		$("#provinceId-new").val(info[0].provinceId);
		$("#provinceId-new").change();
		$("#cityId-new").val(info[0].cityId);
		$("#cityId-new").change();
		$("#areaId-new").val(info[0].areaId);
		$("#tailAddress-new").val(info[0].tailAddress);
		
		$("#storeTailAddress-longt").val(info[0].longitude);
		$("#storeTailAddress-lati").val(info[0].latitude);
		
			//第一次加载地址：firstfill.
			/*if(flag=="firstfill"){
				$(".custAddr-edit-last-confirm").attr("disabled","disabled");
			}*/
		//最后一条或第一条，将下一个或上一个按钮失效
		/*if(isTop==true){
			$(".custAddr-edit-last-confirm").attr("disabled","disabled");
			$(".custAddr-edit-next-confirm").attr("disabled",false);
		}
		if(isEnd==true){
			$(".custAddr-edit-next-confirm").attr("disabled","disabled");
			$(".custAddr-edit-last-confirm").attr("disabled",false);
		}*/
		
		//配送范围
		addTempAddrDomController("update",info);
		//$("#modal-custAddr-edit").modal("show");
	}
	
	function disabledBtn(flag,dataType,isTop,isEnd){
		if('1' == dataType){
			$(".custAddr-edit-next-confirm").attr("disabled",isEnd);
			$(".custAddr-edit-last-confirm").attr("disabled",isTop);
		}else if('2' == dataType){
			$(".custAddr-edit-next-confirmRegion").attr("disabled",isEnd);
			$(".custAddr-edit-last-confirmRegion").attr("disabled",isTop);
		}
	}
	
	//发送请求data
	function getRequestData(flag){
		var data={
				flag:flag,
				//门店信息
				id:$("#serialId").val(),
				addressInfoId:$("#addressId-new").val(),
				storeAddressHotName_new:$("#storeAddressHotName-new").val(),
				expressCode:$("#expressItems-new").find('option:selected').val(),
				contactName:$("#contact-new").val(),//storeAddress
				mobile:$("#mobile-new").val(),
				officeNo:$("#officeNo-new").val(),
				storeAddressPCA:$("#provinceId-new").find('option:selected').text()+" "+$("#cityId-new").find('option:selected').text()+" "+$("#areaId-new").find('option:selected').text(),
				storeAddressProvinceId:$("#provinceId-new").val(),
				storeAddressCityId:$("#cityId-new").val(),
				storeAddressAreaId:$("#areaId-new").val(),
				tailAddress_new:$("#tailAddress-new").val(),
				storeTailAddress_longt:$("#storeTailAddress-longt").val(),
				storeTailAddress_lati:$("#storeTailAddress-lati").val()
		};
		//临时地址DOM可见，并且存在数据
		if(!$("#tempAddrContainer").is(":hidden")){
			if($(".suggestionAddr").length>0){
				//var addrInfoArray = [];
				//var provinceIdArray = [];
				//var cityIdArray = [];
				//var areaIdArray = [];
				var idArray = [];
				var addressIdArray = [];
				var suggestionAddrArray = [];
				var longArray = []; 
				var latiArray = []; 
				var hotNameArray = [];
				
				/* $.each($(".tempAddrInfo"), function(index,item){
					provinceIdArray[index] = $("#provinceId-new"+(index+1)).val();
					cityIdArray[index] = $("#cityId-new"+(index+1)).val();
					areaIdArray[index] = $("#areaId-new"+(index+1)).val();
					addrInfoArray[index] = $("#provinceId-new"+(index+1)).find('option:selected').text()+" "+$("#cityId-new"+(index+1)).find('option:selected').text()+" "+$("#areaId-new"+(index+1)).find('option:selected').text();
				}); */
				$.each($(".id"), function(index,item){
					idArray[index] = item.value;
				});
				$.each($(".addressId"), function(index,item){
					addressIdArray[index] = item.value;
				});
				$.each($(".suggestionAddr"), function(index,item){
					suggestionAddrArray[index] = item.value;
				});
				$.each($(".longArray"), function(index,item){
					longArray[index] = item.value;
				});
				$.each($(".latiArray"), function(index,item){
					latiArray[index] = item.value;
				});
				$.each($(".hotNameArray_region"), function(index,item){
					hotNameArray[index] = item.value;
				});
				data['idArray'] = idArray;
				data['addressIdArray'] = addressIdArray;
				data['hotNameArray'] = hotNameArray;
				data['suggestionAddrArray'] = suggestionAddrArray;
				//data['provinceIdArray'] = provinceIdArray;
				//data['cityIdArray'] = cityIdArray;
				//data['areaIdArray'] = areaIdArray;
				//data['addrInfoArray'] = addrInfoArray;
				data['longArray'] = longArray;
				data['latiArray'] = latiArray;
			}
		}
		return data;
	}
	
	
	//门店和派送范围新增检验
	function formIsValidateAdd(){
		//门店表单非空（快递公司、门店关键字、联系人、手机号码）
		if(!($("#expressItems-new").find('option:selected').val()&&$("#storeAddressHotName-new").val()&&$("#contact-new").val())){
			alert("请确保 快递公司、关键字和联系人 信息填写完整！");
			return false;
		}
		if(!($("#mobile-new").val()||$("#officeNo-new").val())){
			alert("座机号和手机号 请至少填写一项");
			return false;
		}
		if(!($("#provinceId-new").find('option:selected').val()&&$("#cityId-new").find('option:selected').val()&&$("#areaId-new").find('option:selected').val())){
			alert("请选择 省市和区县 信息");
			return false;
		}
		//表单有效性 - 门店地址和配送范围地址有效
		var longArray= $(".longArray");
		if(!$("#storeTailAddress-longt").val()){
			alert("请填写有效的门店地址！");
			return false;
		}
		for(var i=0;i<longArray.length;i++){
			if(!longArray[i].value){
				alert("请填写有效的派送范围地址！");
				return false;
			}
		}
		//表单有效性 - 关键字不重复(为N的为无效关键字)
		//验证用户填写的临时关键互不重复
		/* $.each($(".hotNameArray"),function(){
			
		}); */
		var hotNames = $(".storeHotNameisValid");
		for(var i=0;i<hotNames.length;i++){
			//alert($(hotNames[i]).val());
			if($(hotNames[i]).val()!="Y"){
				alert("请确保关键字不为空且有效！");
				return false;
				break;
			}
		}
		/* $.each($(".storeHotNameisValid"), function(index,item){
			if(item.value!="Y"){
				alert("请确保关键字不为空且有效！");
				return false;
			}
		}); */
		return true;
	}
	//派送范围新增检验
	function formIsValidateAddRegion(){
		if(!($("#expressItems-newRegion").find('option:selected').val()&&$("#regionAddressHotName-new").val()&&$("#contact-newRegion").val())){
			alert("请确保 快递公司、关键字和联系人 信息填写完整！");
			return false;
		}
		if(!($("#mobile-newRegion").val()||$("#officeNo-newRegion").val())){
			alert("座机号和手机号 请至少填写一项");
			return false;
		}
		if(!($("#provinceId-newRegion").find('option:selected').val()&&$("#cityId-newRegion").find('option:selected').val()&&$("#areaId-newRegion").find('option:selected').val())){
			alert("请选择 省市和区县 信息");
			return false;
		}
		if(!$("#regionTailAddress-longt").val()){
			alert("请填写有效的配送范围地址！");
			return false;
		}
		if($(".regionHotNameisValid").val()=='N'){
			alert("请确保关键字有效！");
			return false;
		}
		return true;
	}
	
	//修改/新增前检验
	function formIsValidate(){
		if($("#tailAddress-new").val()){
			return true;
		}else{
			alert("请填写详细地址！");
			return false;
		}
	}
	
	//清除
	function emptyForm(){
		$("#contact-new").val("");
		$("#storeAddressHotName-new").val("");
		$("#mobile-new").val("");
		$("#officeNo-new").val("");
		$("#tailAddress-new").val("");
		$("#provinceId-new").val('');
		$("#provinceId-new").change();
		$("#storeTailAddress-longt").val('');
		$("#storeTailAddress-lati").val('');
		$("#serialId").val('');
		$("#expressItems-new").val('');
		
		//清除
		$(".custHotNameValidMsg").text('');
		$(".custAddrValidMsg").text('');
		
		//删除动态创建的建议地址
		$(".newCustAddrDiv").remove();
		//隐藏临时地址
		$("#tempAddrContainer").hide();
	}
	//清除formRegion
	function emptyFormRegion(){
		$("#contact-newRegion").val("");
		$("#regionAddressHotName-new").val("");
		$("#mobile-newRegion").val("");
		$("#officeNo-newRegion").val("");
		$("#tailAddress-newRegion").val("");
		$("#provinceId-newRegion").val('');
		$("#provinceId-newRegion").change();
		$("#regionTailAddress-longt").val('');
		$("#regionTailAddress-lati").val('');
		$("#serialId").val('');
		$("#expressItems-newRegion").val('');
		
		//清除form
		$(".custHotNameValidMsg").text('');
		$(".custAddrValidMsg").text('');
	}

	//查询
	$(".btn-custAddr-search").on("click",function(){
		buildDataTable(getQueryParam());
	});
	
	
	//删除确认
	$(".btn-custAddr-delete,.btn-custAddr-forceDelete").on("click",function(){
		var val=$('input:radio[name="addressId"]:checked').val();
		var check_boxes = $("input[name='addressId']:checked");
		if(check_boxes.length<=0){ alert('请至少选择一条记录！');return;}
		
		var alertBeforeDelete = "您确定要删除吗？";
		var deleteType = "";
		if($(this).hasClass("btn-custAddr-forceDelete")){
			alertBeforeDelete = "您确定要强制删除吗？";
			deleteType = "forceDelete";
		}else{
			deleteType = "normalDelete";
		}
		
		if(confirm(alertBeforeDelete)){
			var dropIds = '';
            check_boxes.each(function(index,item){
                dropIds += $(this).val()+","; 
            });
            $.ajax({
            	type: "POST",
            	url: "../page/favouriteAddress/deleteCustAddrInfo.json",
    			dataType: "json",
                data:{'ids':dropIds,'deleteType':deleteType},
                success: function(jsonData){
    				if(jsonData.success){
    					var undelete = jsonData.data.undelete;
    					var undeleteHotNames=[];
    					//强制删除
    					if(!undelete){
    						showSuccessAlert("删除成功!");
    						buildDataTable();
    						return false;
    					}
    					$.each(check_boxes,function(index,item){
    						if(undelete[index]==$(this).val()){
    							undeleteHotNames +=$($(this).parent().parent().parent().siblings("td")[1]).text()+",";
    						}
    					});
    					//普通Admin点击强制删除
    					if(jsonData.data.messageType=="warning"){
    						alert(jsonData.data.msg);
    						return false;
    					}
    					if(undelete.length==0){
    						showSuccessAlert("删除成功!");
        					buildDataTable();
    					}else{
    						alert("部分记录删除失败！以下地址已被用户收藏，不能删除! \n"+undeleteHotNames);
    						buildDataTable();
    					}
    					
    				}else{
    					showErrorAlert("删除失败！您没有权限操作此项！");
    				}
    			}
            });
        }
        return false;
	});
	
	//删除临时详细地址<input type="checkbox" class="col-sm-1 control-label text-center tempCheck" name='+tempAddr+' id='+tempAddr+' Style="zoom: 200%">'
	$(".newTempCustAddr-delete").on("click",function(){
		//var val=$('input:radio[name="addressId"]:checked').val();
		var check_boxes = $("input[name='tailAddresses-new-temp']:checked");
		if(check_boxes.length<=0){ alert('请至少选择一个临时地址！');return;}
		
		if(confirm('您确定要删除吗？')){
			$(check_boxes).parent().parent().parent().remove();
        }
        return false;
	});
	
	//新增门店或配送范围触发
	$(".tempContainerTrigger").on("click",function(){
		//trigger配送范围显示与隐藏
		if($("#tempAddrContainer").is(":hidden")){
			$("#tempAddrContainer").show();
		}else{
			$("#tempAddrContainer").hide();
		}
	});
	
	//全选框
	$("body").on("change","#selectAll",function(){
		if($(this).is(":checked")){
			$("input[name='addressId']").prop("checked",true);
		}else{
			$("input[name='addressId']").prop("checked",false);
		}
	});
	
	//门店和配送范围，限定百度place suggestion的区域
	$("#cityId-new,#cityId-newRegion").on("change",function(){
		ac = new BMap.Autocomplete(
				{"input" : $(this).parent().parent().siblings("div").find("div").find("input").attr("id")
				,"location" : $(this).find('option:selected').text()
			});
	});
	
	//百度搜索框关键词提示
	function G(id) {
		return document.getElementById(id);
	}

	var map = new BMap.Map("l-map");
	map.centerAndZoom("北京",12);                   // 初始化地图,设置城市和地图级别。

	var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
		{"input" : "tailAddress-new"
		,"location" : ""
	});

	ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
	var str = "";
		var _value = e.fromitem.value;
		var value = "";
		if (e.fromitem.index > -1) {
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
		
		value = "";
		if (e.toitem.index > -1) {
			_value = e.toitem.value;
			value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		}    
		str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
		G("searchResultPanel").innerHTML = str;
	});

	var myValue;
	ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
	var _value = e.item.value;
		myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
		G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
		
		//setPlace(); 
	});
	/* function setPlace(){
		map.clearOverlays();    //清除地图上所有覆盖物
		function myFun(){
			var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
			map.centerAndZoom(pp, 18);
			map.addOverlay(new BMap.Marker(pp));    //添加标注
		}
		var local = new BMap.LocalSearch(map, { //智能搜索
		  onSearchComplete: myFun
		});
		local.search(myValue);
	} */
	
	
	
});

$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );

var dTable = null;

function initDate(){
	var contents = new Array($('#provinceDiv'),$('#cityDiv'),$('#areaDiv'));
	var names = new Array('provinceId-new','cityId-new','areaId-new');
	
	loadTerritoryDropDown(contents,names);
	
	var contents1 = new Array($('#provinceDiv1'),$('#cityDiv1'),$('#areaDiv1'));
	var names1 = new Array('provinceId','cityId','areaId');
	
	loadTerritoryDropDown(contents1,names1);
	
	var contentsRegion = new Array($('#provinceDivRegion'),$('#cityDivRegion'),$('#areaDivRegion'));
	var namesRegion = new Array('provinceId-newRegion','cityId-newRegion','areaId-newRegion');
	
	loadTerritoryDropDown(contentsRegion,namesRegion);
	
	buildDataTable();
}


function buildDataTable(param){
	
	$('#expressinfo').empty().append('<table id="express_table"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/favouriteAddress/getCustAddrlist",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var lstExpress = jsonData.data.lstAddr;
				dTable = $('#express_table').dataTable( {
					"aLengthMenu":[[100, 50, 25, 10], [100, 50, 25, 10]],
					"iDisplayLength":100,
					"iDisplayStart":0,//sDisplayLength*3,//显示的记录从哪儿开始
					
					/* 使用datatables的fnCreatedRow 方法创建新行；fnDrawCallback 方法重新渲染表格（不用再次查询数据库） */
					
					"bProcessing": true,
			        "bPaginate":true,
			        "bSort": false,
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
			        "aoColumns":[
									{"sTitle": "<input type='checkbox' name='selectAll' id='selectAll' ><label for='selectAll'>全选</label>","mData":"","sDefaultContent":"","bSearchable": true, "bVisible": true},	
                                    {"sTitle": "快递公司名称","mData":"expressName","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "关键词","mData":"hotName","sDefaultContent":"","bSearchable": false, "bVisible": true},
                                    {"sTitle": "联系人","mData":"contactName","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "手机","mData":"mobile","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "座机号码","mData":"officeNo","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "详细地址","mData":"addressDetail","sDefaultContent":"","bSearchable": true, "bVisible": true},
                                    {"sTitle": "类型","mData":"dataType","sDefaultContent":"","bSearchable": false, "bVisible": true},
                                    {"sTitle": "expressCode","mData":"expressCode","sDefaultContent":"","bSearchable": false, "bVisible": false},
                                    {"sTitle": "addressId","mData":"addressId","sDefaultContent":"","bSearchable": false, "bVisible": false},
                                    {"sTitle": "parentId","mData":"parentId","sDefaultContent":"","bSearchable": false, "bVisible": false},
                                    {"sTitle": "archiveFlag","mData":"archiveFlag","sDefaultContent":"","bSearchable": false, "bVisible": false}
                                    ,{"sTitle": "id","mData":"id","sDefaultContent":"","bSearchable": false, "bVisible": false}
                                    
			                     ],
                    "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                    	$('td:eq(0)', nRow).html(
                    			'<div class="checkbox">'+
                    				'<label>'+
                    					'<input type="checkbox" name="addressId" id="addressId" value="'+aData.addressId+'">'+
                    					'<input type="hidden" name="dataType" id="dataType" value="'+aData.dataType+'">'+
                    				'</label>'+
                    			'</div>'
                    	);
                    	$('td:eq(7)', nRow).html(
                    			'<div class="dataType">'+
                    				'<label>'+
                    				(aData.dataType==1?"门店":"配送范围")+
                    				'</label>'+
                    			'</div>'
                    	);
                    }
			    });
				}
			},
			data:param
		});
}


function fnGetSelected(oTable) {
    var aReturn = new Array();
    var aTrs = oTable.fnGetNodes();
    for (var i = 0; i < aTrs.length; i++) {
        if ($(aTrs[i]).hasClass('row_selected')) {
        	
            aReturn.push(aTrs[i]);
        }
        alert(aTrs[i]);
    }
    return aReturn;
}


</SCRIPT>
<body>

	<!-- 地图展示画面 -->
	<div id="l-map" style="display:none;"></div>
	

<div id="container" class="container">
<form class="form-signin" id="form1" role="form" method="post" action="">
	<div class="panel panel-default">
		<div class="panel-heading">快递公司搜索</div>
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
							<label for="tailAddress" class="col-sm-2 control-label text-center" style="">详细地址</label>
							<div class="col-sm-2">
								<input type="text" class="form-control" id="tailAddress"
									name="tailAddress" placeholder="请输入详细地址" />
							</div>
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
					<div class="form-group">
						<div class="row">
							<label for="dataTypeSelect" class="col-sm-2 control-label text-center" style="">门店/配送范围</label>
							<div class="col-sm-2">
								<select id="dataTypeSelect" name="dataTypeSelect" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<option value="1">门店</option>
									<option value="2">配送范围</option>
								</select>
							</div>
							<label for="archiveSelect" class="col-sm-2 control-label text-center" style="">是否发布</label>
							<div class="col-sm-2">
								<select id="archiveSelect" name="archiveSelect" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<option value="1">已发布</option>
									<option value="2">未发布</option>
								</select>
							</div>
							<c:if test="${sessionScope.isAdmin==1}">
								<label for="sourceSelect" class="col-sm-2 control-label text-center" style="">数据来源</label>
								<div class="col-sm-2">
									<select id="sourceSelect" name="sourceSelect" class="form-control" style="width: 150px">
										<option value="">--请选择--</option>
										<option value="1">手机端</option>
										<option value="2">电脑端</option>
										<option value="3">数据库导入</option>
										<option value="4">文件导入</option>
									</select>
								</div>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<div class="row">
						<c:if test="${sessionScope.isAdmin==1}">
							<label for="createdBySelect" class="col-sm-2 control-label text-center" style="">创建人</label>
							<div class="col-sm-2">
								<select id="createdBySelect" name="createdBySelect" class="form-control" style="width: 150px">
									<option value="">--请选择--</option>
									<c:forEach var="adminInfo" items="${adminlst}">
					                   <option value="${adminInfo.accountId}">${adminInfo.name}</option>
					               	</c:forEach>
								</select>
							</div>
							</c:if>
						</div>
					</div>
					<div class="form-group col-sm-12 text-center">
						<button type="button" class="btn btn-lg btn btn-primary btn-custAddr-search">查询</button>
					</div>
				</div>
			</div>
	</div>
	
	
	<div class="panel panel-default">
		<div class="panel-heading">快递公司信息</div>
		<div class="panel-body">
			<div class="well well-lg">
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-add" data-toggle="modal" >新增</button>
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-addRegion" data-toggle="modal" >新增配送范围</button>
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-edit">修改</button>
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-delete" >删除</button>
				<c:if test="${sessionScope.isAdmin==1}">
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-forceDelete" >强制删除</button>
				</c:if>
				<button type="button" class="btn btn-lg btn-primary btn-custAddr-publish" >发布</button>
			</div>
			<hr>
			<div id="expressinfo" class="table-responsive"></div>
		</div>
	</div>
</form>	
	<!-- 新增/修改快递公司门店和配送范围	Modal开始 -->
	<div id="modal-custAddr-add" class="modal fade" tabindex="-1" data-backdrop="static">
		<div class="modal_wrapper">
			<div class="modal-dialog" style="width:900px;">
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h3 class="modal-title modal-title-storeandregion">新增常用快递员 - 门店和配送范围</h3>
					</div>
					<div class="modal-body text-center custAddr-new-modal-body">
						
						<div class="form-group">
							<div class="row">
								<input type="hidden" id="addressId-new" />
								<label for="expressCode-new" class="col-sm-2 control-label text-center">快递公司</label>
								<div class="col-sm-4 expressComp">
									<select id="expressItems-new" name="expressCode-new" class="form-control"
										style="width: 130px">
										<option value="">--请选择--</option>
										<c:forEach var="expressInfo" items="${expresslst}">
						                   <option value="${expressInfo.expressCode}">${expressInfo.name}</option>
						               </c:forEach>
									</select>
								</div>
								<label for="storeAddressHotName-new" class="col-sm-2 control-label text-center" style="">关键词</label>
								<input type="hidden" id="storeHotName" class="hotNameIsValid storeHotNameisValid" />
								<div class="col-sm-4 hotN">
									<input type="text" class="form-control hotNameArray" id="storeAddressHotName-new" 
										name="storeAddressHotName-new" placeholder="如：小区/楼盘/商厦/路名" />
								</div>
							</div>
						</div>
						<div class="form-group">
						    <div class="row">
						    	<label for="contact-new" class="col-sm-2 control-label text-center" style="">联系人</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="contact-new"
										name="contact-new" placeholder="请输入联系人姓名" />
								</div>
								<label for="mobile-new" class="col-sm-2 control-label text-center" style="">手　　机</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="mobile-new"
										name="mobile-new" placeholder="请输入手机号码" />
								</div>
						    </div>
						</div>
						<div class="form-group">
						    <div class="row">
								<label for="officeNo-new" class="col-sm-2 control-label text-center" style="">座机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="officeNo-new"
										name="officeNo-new" placeholder="请输入座机号码" />
								</div>
						    </div>
						</div>
						
						<div class="form-group">
						    <div class="row">
								<label for="tailAddress-new" class="col-sm-2 control-label text-center" style="">门店地址</label>
								<div class="col-sm-6">
										<div class="col-sm-4" id="provinceDiv"></div>
										<div class="col-sm-4" id="cityDiv"></div>
										<div class="col-sm-4" id="areaDiv"></div>
								</div>
								<div class="col-sm-4">
								
								 	<input type="hidden" class="form-control" id="storeTailAddress-longt" name="storeTailAddress-longt" />
								 	<input type="hidden" class="form-control" id="storeTailAddress-lati" name="storeTailAddress-lati" />
								 	<div>
								 		<input type="text" class="form-control" id="tailAddress-new" name="tailAddress-new" placeholder="请输入门店详细地址，如苏州市吴中区桂花新村四号楼" />
								 	</div>
								</div>
							</div>
						</div>
						
						<!-- 动态添加详细地址和关键词输入框 -->
						<div class="form-group">
								<div class="col-sm-12">
									<button type="button" class="btn btn-primary pull-left tempContainerTrigger">添加配送范围</button>
								</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-2" id="provinceDiv"></div>
								<div class="col-sm-2">
									<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;display:none;"></div>
								</div>
								<div class="col-sm-2 custHotNameValidMsg"></div>
								<div class="col-sm-4 custAddrValidMsg"></div>
							</div>
						</div>
						<!-- 经纬度信息 -->
						<div>
							<input type="hidden" id="longitude" name="longitude">
							<input type="hidden" id="latitude" name="latitude">
							<input type="hidden" id="serialId" name="serialId">
						</div>
						
						<!-- 动态添加的临时DOM -->
						<div id="tempAddrContainer" style="display:none;">
							<div class="form-group">
								<div class="row text-center">
									<div class="col-sm-2">
										<button type="button" class="btn btn-primary newCustAddr-add"><span class="glyphicon glyphicon-plus"></span></button>
										<button type="button" class="btn btn-primary newTempCustAddr-delete"><span class="glyphicon glyphicon-minus"></span></button>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="row text-center">
									<div  class="col-sm-2"></div>
									<label class="col-sm-2 control-label text-center" style="">选择</label>
									<label class="col-sm-2 control-label text-center" style="">关键字</label>
									<!-- 
									<div class="col-sm-5">
										<label class="col-sm-4 control-label text-center" style="">省份</label>
										<label class="col-sm-4 control-label text-center" style="">城市</label>
										<label class="col-sm-4 control-label text-center" style="">区县</label>
									</div>
									 -->
									<label class="col-sm-4 control-label text-center" style="">范围地址</label>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-primary custAddr-add-confirm">确定</button>
						<button type="button"
							class="btn btn-primary custAddr-edit-confirm">确定</button>
						<button type="button"
							class="btn btn-primary operation-btn custAddr-edit-last-confirm">保存并上一个</button>
						<button type="button"
							class="btn btn-primary operation-btn custAddr-edit-next-confirm">保存并下一个</button>
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增/修改快递公司门店和配送范围	Modal结束 -->
	
	<!-- 新增/修改配送范围	Modal开始 -->
	<div id="modal-custAddr-addRegion" class="modal fade" tabindex="-1" data-backdrop="static">
		<div class="modal_wrapper">
			<div class="modal-dialog" style="width:800px;">
				<div class="modal-content">
					<div class="modal-header text-center">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">
							<span class="glyphicon glyphicon-remove"></span>
						</button>
						<h3 class="modal-title modal-title-region">新增常用快递员 - 配送范围</h3>
					</div>
					<div class="modal-body text-center custAddr-addRegion-modal-body">
						<div class="form-group">
							<div class="row">
								<input type="hidden" id="regionAddressId-new" name="regionAddressId-new" />
								<input type="hidden" id="regionParentId-new" name="regionParentId-new" />
								<label for="expressItems-newRegion" class="col-sm-2 control-label text-center">快递公司</label>
								<div class="col-sm-4 expressComp">
									<select id="expressItems-newRegion" name="expressItems-newRegion" class="form-control"
										style="width: 130px">
										<option value="">--请选择--</option>
										<c:forEach var="expressInfo" items="${expresslst}">
						                   <option value="${expressInfo.expressCode}">${expressInfo.name}</option>
						               </c:forEach>
									</select>
								</div>
								<label for="regionAddressHotName-new" class="col-sm-2 control-label text-center">关键词</label>
								<input type="hidden" class="hotNameIsValid regionHotNameisValid" />
								<div class="col-sm-4 hotN">
									<input type="text" class="form-control" id="regionAddressHotName-new"
										name="regionAddressHotName-new" placeholder="如：小区/楼盘/商厦/路名" />
								</div>
							</div>
						</div>
						<div class="form-group">
						    <div class="row">
						    <label for="contact-newRegion" class="col-sm-2 control-label text-center">联系人</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="contact-newRegion"
										name="contact-newRegion" placeholder="请输入联系人姓名" />
								</div>
								
								<label for="mobile-newRegion" class="col-sm-2 control-label text-center">手机</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="mobile-newRegion"
										name="mobile-newRegion" placeholder="请输入手机号码" />
								</div>
						    </div>
						</div>
						
						<div class="form-group">
							<div class="row">
								<label for="officeNo-newRegion" class="col-sm-2 control-label text-center">座机号码</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="officeNo-newRegion"
										name="officeNo-newRegion" placeholder="请输入座机号码" />
								</div>
							</div>
						</div>
						
						<div class="form-group">
						    <div class="row">
								<label for="tailAddress-newRegion" class="col-sm-2 control-label text-center" style="">详细地址</label>
								<div class="col-sm-6">
										<div class="col-sm-4" id="provinceDivRegion"></div>
										<div class="col-sm-4" id="cityDivRegion"></div>
										<div class="col-sm-4" id="areaDivRegion"></div>
								</div>
								<div class="col-sm-4">
									<input type="hidden" class="form-control" id="regionTailAddress-longt" name="regionTailAddress-longt" />
									<input type="hidden" class="form-control" id="regionTailAddress-lati" name="regionTailAddress-lati" />
									<div>
								 		<input type="text" class="form-control" id="tailAddress-newRegion" name="tailAddress-newRegion" placeholder="请输入详细地址，如苏州市吴中区桂花新村四号楼" />
								 	<div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="row">
								<div class="col-sm-2" id="provinceDiv"></div>
								<div class="col-sm-5">
									<div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto;display:none;"></div>
								</div>
								<div class="col-sm-2 custHotNameValidMsg"></div>
								<div class="col-sm-4 custAddrValidMsg"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button"
							class="btn btn-primary custAddr-add-confirmRegion">确定</button>
						<button type="button"
							class="btn btn-primary operation-btn custAddr-edit-last-confirmRegion">保存并上一个</button>
						<button type="button"
							class="btn btn-primary operation-btn custAddr-edit-next-confirmRegion">保存并下一个</button>
						<button type="button"
							class="btn btn-primary custAddr-edit-confirmRegion">确定</button>
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增/修改配送范围	Modal结束 -->
	
	<!-- 删除快递公司	Modal开始 -->
	<div id="modal-custAddr-delete" class="modal fade" tabindex="-1">
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
						<h4>确认删除该条数据？</h4>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">取消</button>
						<button type="button"
							class="btn btn-primary custAddr-delete-confirm">删除</button>
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
