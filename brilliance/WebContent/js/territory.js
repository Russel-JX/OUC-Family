function loadTerritoryDropDown(containerObjs,names){
	if (names.length == containerObjs.length && containerObjs.length < 4){
		var provinceStr = "<select id="+names[0]+" name="+names[0]+" onchange='loadCities(this,\""+names[1]+"\",\""+names[2]+"\");' class='form-control'></select>";
		var cityStr = "<select id="+names[1]+" name="+names[1]+" onchange='loadareas(this,\""+names[2]+"\");' class='form-control'><option value=''>请选择....</option></select>";
		var areaStr = "<select id="+names[2]+" name="+names[2]+" class='form-control'><option value=''>请选择....</option></select>";
		containerObjs[0].html(provinceStr);
		containerObjs[1].html(cityStr);
		containerObjs[2].html(areaStr);
		
		loadProvinces(names);
	}
	
}

function loadTerritoryDropDownSingleDiv(containerObj,names){
	var provinceStr = "<select id="+names[0]+" name="+names[0]+" onchange='loadCities(this,\""+names[1]+"\",\""+names[2]+"\");' class='form-control'></select>";
	var cityStr = "<select id="+names[1]+" name="+names[1]+" onchange='loadareas(this,\""+names[2]+"\");' class='form-control'><option value=''>请选择....</option></select>";
	var areaStr = "<select id="+names[2]+" name="+names[2]+" class='form-control'><option value=''>请选择....</option></select>";
	containerObj.html(provinceStr+cityStr+areaStr);
	//containerObjs[1].html(cityStr);
	//containerObjs[2].html(areaStr);
	
	loadProvinces(names);
	
}

function loadAreasDropDown(containerObj,names){
	var provinceStr = "<select id="+names[0]+" name="+names[0]+" onchange='loadCities(this,\""+names[1]+"\",\""+names[2]+"\");' class='form-control'></select>";
	var cityStr = "<select id="+names[1]+" name="+names[1]+" onchange='loadareas(this,\""+names[2]+"\");' class='form-control'><option value=''>请选择....</option></select>";
	var areaStr = "<select id="+names[2]+" name="+names[2]+" class='form-control'><option value=''>请选择....</option></select>";
	
	containerObj.html(provinceStr+cityStr+areaStr);
	loadProvinces(names);
	
}


function loadProvinces(names){
	var province = $("#"+names[0]);
	$.ajax({
		type: "POST",
		url: "../restful/getProvincesFromCach",
		async:false,
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				province.empty();
				
				var provinces = jsonData.data.provinces;
				var html = '<option value="">请选择....</option>';
				$.each(provinces,function(index,item){
					html += '<option value="'+item.provinceId+'">'+item.province+'</option>';
				});
				province.append(html);
				}
			}
		});
}

function loadCities(selObj,city,area){
	var cityObj = $("#"+city);
	var areaObj = $("#"+area);
	var tmp = $(selObj).val();
	if(''==tmp){
		cityObj.empty();
		cityObj.append('<option value="">请选择....</option>');
		areaObj.empty();
		areaObj.append('<option value="">请选择....</option>');
	}else{
		$.ajax({
			type: "POST",
			url: "../restful/getCities",
			dataType: "json",
			async:false,
			success: function(jsonData){
				//console.log(jsonData);
				var flag = false;
				if(jsonData.success){
					//console.log("get cities success");
					cityObj.empty();
					var cities = jsonData.data.cities;
					flag = cities.length ==1;
					var html = '<option value="">请选择....</option>';
					$.each(cities,function(index,item){
						html += '<option value="'+item.cityId+'">'+item.city+'</option>';
					});
					cityObj.append(html);
					}
				    if (flag){
				    	loadareas(cityObj,area);
				    }else{
				    	areaObj.empty();
				    	areaObj.append('<option value="">请选择....</option>');
				    	loadareas(cityObj,area);
				    	
				    }
				},
				data:{provinceId:$(selObj).val()}
			});
	}
	
}


function loadareas(selObj,area){
	var areaObj = $("#"+area);
	var tmp = $(selObj).val();
	if(''==tmp){
		areaObj.empty();
		areaObj.append('<option value="">请选择....</option>');
	}else{
		$.ajax({
			type: "POST",
			url: "../restful/getAreas",
			dataType: "json",
			async:false,
			success: function(jsonData){
				//console.log(jsonData);
				if(jsonData.success){
					//console.log("get areas success");
					areaObj.empty();
					var areas = jsonData.data.areas;
					var html = '<option value="">请选择....</option>';
					$.each(areas,function(index,item){
						html += '<option value="'+item.areaId+'">'+item.area+'</option>';
					});
					areaObj.append(html);
					}
				},
				data:{cityId:$(selObj).val()}
			});
	}
	
}

/**
 * 由订单页返回
 */
function loadTerritoryDropDownBack(containerObjs,names,selectedProvice,selectedCity,selectedArea){
	if (names.length == containerObjs.length && containerObjs.length < 4){
		var provinceStr = "<select id="+names[0]+" name="+names[0]+" onchange='loadCities(this,\""+names[1]+"\",\""+names[2]+"\");' class='form-control'></select>";
		var cityStr = "<select id="+names[1]+" name="+names[1]+" onchange='loadareas(this,\""+names[2]+"\");' class='form-control'><option value=''>请选择....</option></select>";
		var areaStr = "<select id="+names[2]+" name="+names[2]+" class='form-control'><option value=''>请选择....</option></select>";
		containerObjs[0].html(provinceStr);
		containerObjs[1].html(cityStr);
		containerObjs[2].html(areaStr);
		
		loadProvincesBack(names,selectedProvice);
		loadCitiesBack(selectedProvice,selectedCity,names[1],names[2]);
		loadareasBack(selectedCity,selectedArea,names[2]);
	}
	
}


function loadProvincesBack(names,selectedProvice){
	var province = $("#"+names[0]);
	$.ajax({
		type: "POST",
		url: "../restful/getProvincesFromCach",
		async:false,
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get provinces success");
				province.empty();
				
				var provinces = jsonData.data.provinces;
				var html = '<option value="">请选择....</option>';
				
				$.each(provinces,function(index,item){
					if(item.provinceId==selectedProvice){
						html += '<option value="'+item.provinceId+'" selected="selected">'+item.province+'</option>';
					}else{
						html += '<option value="'+item.provinceId+'">'+item.province+'</option>';
					}
					
				});
				province.append(html);
				}
			}
		});
}

function loadCitiesBack(selectedProvice,selectedCity,city,area){//selObj:前面的省份ID
	var cityObj = $("#"+city);
	var areaObj = $("#"+area);
	$.ajax({
		type: "POST",
		url: "../restful/getCities",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			var flag = false;
			if(jsonData.success){
				//console.log("get cities success");
				cityObj.empty();
				var cities = jsonData.data.cities;
				/*flag = cities.length ==1;*/
				var html = '';
				$.each(cities,function(index,item){
					if(item.cityId==selectedCity){
						html += '<option value="'+item.cityId+'" selected="selected">'+item.city+'</option>';
					}else{
						html += '<option value="'+item.cityId+'">'+item.city+'</option>';
					}
				});
				cityObj.append(html);
				}
			   /* if (flag){
			    	loadareas(cityObj,area);
			    }else{
			    	areaObj.empty();
			    	areaObj.append('<option value="">请选择....</option>');
			    	loadareas(cityObj,area);
			    	
			    }*/
			},
			data:{provinceId:selectedProvice}
		});
}


function loadareasBack(selectedCity,selectedArea,area){
	var areaObj = $("#"+area);
	$.ajax({
		type: "POST",
		url: "../restful/getAreas",
		dataType: "json",
		success: function(jsonData){
			//console.log(jsonData);
			if(jsonData.success){
				//console.log("get areas success");
				areaObj.empty();
				var areas = jsonData.data.areas;
				var html = '';
				$.each(areas,function(index,item){
					if(item.areaId==selectedArea){
						html += '<option value="'+item.areaId+'" selected="selected">'+item.area+'</option>';
					}else{
						html += '<option value="'+item.areaId+'">'+item.area+'</option>';
					}
				});
				areaObj.append(html);
				}
			},
			data:{cityId:selectedCity}
		});
}

