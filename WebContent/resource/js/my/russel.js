require_init();

define(
		[ 'datatables', 'bootstrap', 'editable', 'jquery' ],
		function(){
			$(document).ready(function() {
				$("#png").mouseover(function(){
					$(this).css("background-color","red");
				});
				/* //initial datatable
				var otable = $("#example").dataTable();
				//get data of certain row
				var firstRowData = otable._("tr");//get all rows.Every row data is an element of the Array.
				
				$("#temp").append("first row data is :"+firstRowData[0]+"first row first cell data is :"+firstRowData[0][0]); */
				
				/*$("#example").dataTable({
					"aaData":[
					 	["101","Tom",24],
					 	["102","Jack",44],
					 	["103","Rose",10]  
					 ],
					 "aoColumns":[
					    {"sTitle":"S/N"},
					    {"sTitle":"name"},
					    {"sTitle":"age"}
					 ]
					
				});*/
				$("#example").dataTable({
					//"bDestroy": true,
					"aaSorting":[[0,"desc"],[2,"asc"]],//Sorting by columns's value(First has the highest priority)
					"aaSortingFixed":[[2,"asc"]],//specify certain column has the highest priority to sort.
					"aaData":[
					 	{
					 		"S/N":"No.1","name":"Mary","age":"35","address":"SSTT"
					 	},
					 	{
					 		"S/N":"No.2","name":"Who","age":"90","address":"Suzhou"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.4","name":"Emily","age":"35","address":"Huaian"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	},
					 	{
					 		"S/N":"No.3","name":"Bob","age":"35","address":"Jiangsu"
					 	}
					 ],
					 "aoColumns":[
					    {"sTitle":"S/N","mData":"S/N"},
					    {"sTitle":"Name","mData":"name"},
					    {"sTitle":"Age","mData":"age"},
					    {"sTitle":"Address","mData":"address"}
					 ]
					
				});

			});
		}
);