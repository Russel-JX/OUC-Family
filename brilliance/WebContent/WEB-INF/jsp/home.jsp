<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../jsp/common/header.jsp"%>
<title>货运公司信息</title>
<head>
<link rel="stylesheet" href="<%=rootPath%>/css/dataTables.bootstrap.css"/>
<link rel="stylesheet" href="<%=rootPath%>/css/jquery.dataTables_themeroller.css"/>

<SCRIPT src="<%=rootPath%>/js/territory.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/jquery.dataTables.min.js" type="text/javascript"></SCRIPT>
<SCRIPT src="<%=rootPath%>/js/dataTables.bootstrap.js" type="text/javascript"></SCRIPT>
<%-- <SCRIPT src="<%=rootPath%>/js/bootstrap.min.js" type="text/javascript"></SCRIPT>--%>
<SCRIPT src="<%=rootPath%>/js/tool.js" type="text/javascript"></SCRIPT> 

<style type="text/css">
body{
	/*background-color:rgba(0,0,0,0.2);*/
	margin:0;
}

.td11{
    overflow:hidden;
    text-overflow: ellipsis;
    white-space:nowrap;
    width:20px;
}


#express_table_wrapper .row{margin-right:0px !important;margin-left:0px !important}
.row{
	margin-top:10px;
}
</style>
</head>
<SCRIPT type="text/javascript">
$(document).ready(function() {
	//loadProviceDropdown();
	//buildDataTable();
	
	//是否是跳转回来的
	var returnFormOrderFlag = $("#returnFormOrderFlag").val();
	var selectedFProvice = $("#f_provinceId").val();
	var selectedTProvice = $("#t_provinceId").val();
	var selectedFCity = $("#f_cityId").val();
	var selectedTCity = $("#t_cityId").val();
	var selectedFArea = $("#f_areaId").val();
	var selectedTArea = $("#t_areaId").val();
	
	var contents = new Array($('#provinceDiv'),$('#cityDiv'),$('#areaDiv'));
	var names = new Array('f_province','f_city','f_area');
	var contents1 = new Array($('#provinceDiv1'),$('#cityDiv1'),$('#areaDiv1'));
	var names1 = new Array('t_province','t_city','t_area');
	
	if(returnFormOrderFlag){
		loadTerritoryDropDownBack(contents,names,selectedFProvice,selectedFCity,selectedFArea);
		loadTerritoryDropDownBack(contents1,names1,selectedTProvice,selectedTCity,selectedTArea);
		buildDataTable();
	}else{
		loadTerritoryDropDown(contents,names);
		loadTerritoryDropDown(contents1,names1);
	}
	
	
	//buildDataTable();
});



function buildDataTable(){
	
	$('#expressinfo').empty().append('<table id="express_table"  class="table table-striped table-bordered table-hover datatable"></table>');
	$.ajax({
		type: "POST",
		url: "../page/getDateList",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				var dataList = jsonData.data.dataList;
				var dTable = $('#express_table').dataTable( {
					"bProcessing": true,
			        "bPaginate":true,
			        "bAutoWidth": false,
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
			        "aoColumnDefs":[{"aTargets": [8],
			        	          "mData": null,
			        	          "mRender": function (data) {
			        	        	  return '<a href="#" id="order" >下单</a>';
			        	          }
			        	}],
			        "aoColumns":[
                                    {"sTitle": "货运公司编号","mData":"expressCode","sDefaultContent":"","bSearchable": true, "bVisible": false},
                                    {"sTitle": "货运公司名称","mData":"name","sDefaultContent":"","sWidth": '15%'},
                                    {"sTitle": "免费电话","mData":"teltphone","sDefaultContent":"","sWidth": '15%'},
                                    {"sTitle": "手机号码","mData":"mobile","sDefaultContent":"","bSearchable": true, "bVisible": false},
                                    {"sTitle": "客服电话","mData":"serviceLine","sDefaultContent":"","bSearchable": true, "bVisible": false},
                                    {"sTitle": "评价","mData":"evaluation","sDefaultContent":""},
                                    {"sTitle": "费用","mData":"charge","sDefaultContent":""},
                                    {"sTitle": "送达天数","mData":"deliverDays","sDefaultContent":""},
                                    {"sTitle": "操作"}
			                     ],
			       /* "fnInitComplete": function() {
			                    	 this.fnAdjustColumnSizing(true);
			                   	 }, */
                    "fnRowCallback":function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
		        	    var province = $('#f_province').find('option:selected').text();
		        		var city = $('#f_city').find('option:selected').text();
		        		var area = $('#f_area').find('option:selected').text();
		        		var fromAddr = province+','+city+','+area;
		        		
		        		 var provinceId = $('#f_province').val();
			        	 var cityId = $('#f_city').val();
			        	 var areaId = $('#f_area').val();
			        	 
		        		
		        		province = $('#t_province').find('option:selected').text();
		        		city = $('#t_city').find('option:selected').text();
		        		area = $('#t_area').find('option:selected').text();
		        		var toAddr = province+','+city+','+area;
		        		
		        		var t_provinceId = $('#t_province').val();
			        	var t_cityId = $('#t_city').val();
			        	var t_areaId = $('#t_area').val();
			        	//$('#expressName').val(aData[1]);
			        	//$('#expressCode').val(aData[0]);
		        		//var values = fromAddr+"&toAddr="+toAddr+"&expressCode="+aData[0]+"&expressName="+aData[1]
		        		  //        +"&charge="+aData[6]+"&deliverDays="+aData[7]+"&fromId="+fromId+"&toAddrId="+toAddrId);
				        var values = provinceId+"&"+cityId+"&"+areaId+"&"+t_provinceId+"&"+t_cityId+"&"+t_areaId+"&"+aData.expressCode+"&"+aData.name+"&"+aData.charge+"&"+aData.deliverDays+"&"+fromAddr+"&"+toAddr;
				        //var values = new Array(aData[1],aData[0]);
				        //values.push(args)
				        //var values={'expressName':aData[1],'expressCode':aData[0]};
		        	 $('td:eq(5)', nRow).html("<a href='JavaScript:;' onclick='placeOrderControl(\""+values+"\");'>下单</a>");
		        	 //$('td:eq(1)', nRow).addClass("td11");
		        	 
        			 
		        	 var phoneNum = aData.telephone;
		        	 var shotNum ="";
		        	 if(!isNull(phoneNum)){
		        		 if (phoneNum.length > 20){
		        			 shotNum =  phoneNum.substring(0,20);
		        			 $('td:eq(1)', nRow).html(shotNum);
		        			 //$('td:eq(1)', nRow).attr("data-toggle","tooltip");
		        			 //$('td:eq(1)', nRow).attr("data-original-title",phoneNum);
				        	 $('td:eq(1)', nRow).attr("title",phoneNum);
		        			 //$('td:eq(1)', nRow).tooltip('show');
		        			 //$('td:eq(1)', nRow).addClass("overColumn");
		        		 }
		        	 } 
		         }
			    });
				
				
				}
			},
			//data:{f_province:'320000',f_city:'320500',f_area:'320505',t_province:'310000',t_city:'310100',t_area:'310105'}
			//data:{f_province:$('#f_province').val()?$('#f_province').val():$('#f_provinceId').val(),f_city:$('#f_city').val()?$('#f_city').val():$('#f_cityId').val(),f_area:$('#f_area').val()?$('#f_area').val():$('#f_areaId').val(),t_province:$('#t_province').val()?$('#t_province').val():$('#t_provinceId').val(),t_city:$('#t_city').val()?$('#t_city').val():$('#t_cityId').val(),t_area:$('#t_area').val()?$('#t_area').val():$('#t_areaId').val()}
			data:{f_province:($('#f_province').val()!='')?$('#f_province').val():$('#f_provinceId').val(),f_city:($('#f_city').val()!='')?$('#f_city').val():$('#f_cityId').val(),f_area:($('#f_area').val()!='')?$('#f_area').val():$('#f_areaId').val(),t_province:($('#t_province').val()!='')?$('#t_province').val():$('#t_provinceId').val(),t_city:($('#t_city').val()!='')?$('#t_city').val():$('#t_cityId').val(),t_area:($('#t_area').val()!='')?$('#t_area').val():$('#t_areaId').val()}
		});
}
//下单控制
function placeOrderControl(values){
	if(!validateLogin()){//未登录
		
	}else{//已登录
		addOrder(values);
	}
}


function addOrder(values){
	 var tmp = values.split("&");
	$('#f_provinceId').val(tmp[0]);
	$('#f_cityId').val(tmp[1]);
	$('#f_areaId').val(tmp[2]);
	$('#t_provinceId').val(tmp[3]);
	$('#t_cityId').val(tmp[4]);
	$('#t_areaId').val(tmp[5]);
	$('#expressCode').val(tmp[6]);
	$('#expressName').val(tmp[7]);
	$('#charge').val(tmp[8]);
	$('#deliverDays').val(tmp[9]);
	$('#fromAddr').val(tmp[10]);
	$('#toAddr').val(tmp[11]);
	
	$('#submitBtn').click(); 
	//$('#makeOrder').modal();
	
}


</SCRIPT>
<body>

<div id="container" class="container">


<div class="panel panel-default">
 <div class="panel-heading">查询</div>
 <div class="panel-body">
 
 
 
<form id="form" action="newOrder" method="post" >
    <div class="row">
        <div class="col-md-1">始发地</div>
        <div class="col-md-2" id="provinceDiv" style="max-width:200px;"></div>
		<div class="col-md-2" id="cityDiv" style="max-width:200px;"></div>
		<div class="col-md-2" id="areaDiv" style="max-width:200px;"></div>	    
		<div class="col-md-5"></div>    
    </div>
    <div class="row">
        <div class="col-md-1">目的地</div>
        <div class="col-md-2" style="max-width:200px;" id="provinceDiv1"></div>
		<div class="col-md-2" style="max-width:200px;" id="cityDiv1"></div>
		<div class="col-md-2" style="max-width:200px;" id="areaDiv1"></div>	    
		<div class="col-md-5"></div>	    
    </div>
    <div class="row">
        <div class="col-md-1"><button type="button" class="btn btn-primary" onclick="buildDataTable();">搜索</button></div>
    </div>

	<hr/>
	
    <div id="expressinfo" class="table-responsive"></div>
    
    <input type="submit" id="submitBtn" style="display:none"/>
    <input type="hidden" id="fromAddr" name="fromAddr" value=""/>
    <input type="hidden" id="toAddr" name="toAddr" value=""/>
    <input type="hidden" id="charge" name="charge" value=""/>
    <input type="hidden" id="deliverDays" name="deliverDays" value=""/>
    <input type="hidden" id="f_provinceId" name="f_provinceId" value="${f_provinceId}"/>
    <input type="hidden" id="f_cityId" name="f_cityId" value="${f_cityId}"/>
    <input type="hidden" id="f_areaId" name="f_areaId" value="${f_areaId}"/>
    <input type="hidden" id="t_provinceId" name="t_provinceId" value="${t_provinceId}"/>
    <input type="hidden" id="t_cityId" name="t_cityId" value="${t_cityId}"/>
    <input type="hidden" id="t_areaId" name="t_areaId" value="${t_areaId}"/>
    <input type="hidden" id="expressName" name="expressName" value=""/>
    <input type="hidden" id="expressCode" name="expressCode" value=""/>
    <input type="hidden" id="returnFormOrderFlag" name="returnFormOrderFlag" value="${returnFormOrderFlag}"/>
    </form>
    </div>
  </div>   
  
  
  
</div>

<script type="text/javascript">
	/* $(document).ready(function(){
			$.ajax({
	    		url:'http://api.share.baidu.com/getnum?http://localhost:8888/brilliance/page/home.jsp&callback=bdShare.fn._getShare&type=share',
				type:'get',
				dataType:'jsonp',
				cache:false,
				data:{
				},
				beforeSend:function(xhr){
				},
				complete:function (XMLHttpRequest, textStatus) {
				},
				success:function(backData,status){
					alert(backData.num[0]);
				},
				error:function(XmlHttpRequest){
				}
	    		
	    	});
		}); */
	</script>


</body>
<%@ include file="../jsp/common/footer.jsp"%>
</html>
