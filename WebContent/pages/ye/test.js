require_init();

define(
		[ 'jquery',	'datatables', 'datatableTool' ],
		function() {
			$(document).ready( function () {
				$('#report_table').dataTable( {
					"sDom": '<"H"Tfl>t<"F"ip>',
			        "oTableTools": {
			            "sSwfPath": "/iZone/resource/js/util/swf/copy_csv_xls_pdf.swf"
			        },
			        "aoColumns":[ {
						"sTitle" : "Engine",
						"mData" : "engine"
					}, {
						"sTitle" : "Browser",
						"mData" : "browser"
					}, {
						"sTitle" : "Platform",
						"mData" : "platform"
					}, {
						"sTitle" : "Version",
						"mData" : "version"
					}, {
						"sTitle" : "Grade",
						"mData" : "grade"
					} ],
			    } );
			});
		});