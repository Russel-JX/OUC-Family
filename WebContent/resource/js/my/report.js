/**
 * @author NaDa Mar 27, 2014 for: report.html
 */

require_init();

define(
		[ 'datatables', 'bootstrap', 'editable', 'jquery', 'datatableTool',
				'zero' ],
		function() {

			var oTable;
			
			var table_manage;
			
			var table_manage_tr;

			var isCreateTable = false;

			var _params = [ {
				"sTitle" : "Title1",
				"mData" : "title1"
			}, {
				"sTitle" : "Title2",
				"mData" : "title2"
			}, {
				"sTitle" : "Title3",
				"mData" : "title3"
			}, {
				"sTitle" : "Title4",
				"mData" : "title4"
			}, {
				"sTitle" : "Title5",
				"mData" : "title5"
			} ];

			var params = [ {
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
			} ];

			// 页面加载函数
			var init = $(function() {

				table_manage=$('#report_management_table').dataTable();

				$('#select_all').on('click', function() {
					if ($(this).prop('checked')) {
						$('#report_table tbody tr').addClass('trselected');
					} else {
						$('#report_table tbody tr').removeClass('trselected');
					}
				});

				$('#create_select_all').on('click', function() {
					if ($(this).prop('checked')) {
						$('#report_table tbody tr').addClass('trselected');
					} else {
						$('#report_table tbody tr').removeClass('trselected');
					}
				});

				$('#create_table_view').on('hidden.bs.modal', function(e) {
					$('#create_exit_btn').click();
				});

				$('#table_view').on('hidden.bs.modal', function(e) {
					$('#table_view_exit').click();
				});

				$('#report_tab a[href="#table_management"]').click();

				$('#table_view_exit').on('click', function() {
					oTable.fnDestroy(true);
				});

				$('#table_view_return').on('click', function() {
					$('#table_view_exit').click();
				});

				$('#create_exit_btn').on('click', function() {
					isCreateTable = false;
					oTable.fnDestroy(true);
					_params = [ {
						"sTitle" : "Title1",
						"mData" : "title1"
					}, {
						"sTitle" : "Title2",
						"mData" : "title2"
					}, {
						"sTitle" : "Title3",
						"mData" : "title3"
					}, {
						"sTitle" : "Title4",
						"mData" : "title4"
					}, {
						"sTitle" : "Title5",
						"mData" : "title5"
					} ];
				});

				$('#create_return_btn').on('click', function() {
					$('#create_exit_btn').click();
				});

				$('#create_table_btn').on('click', function() {
					isCreateTable = true;
					$('#create_select_all').prop('checked', false);
					datatables_init('create_table_show', _params, 1);
				});
				
				$('#report_management_table button.btn-danger').on('click',function() {
					table_manage_tr=$('#report_management_table tbody tr').index($(this).parent().parent());
					$('#table_delete').modal('show');
				});
				
				$('#del_table_btn').on('click',function(){
					table_manage.fnDeleteRow(table_manage_tr);
				});
				
				$('#report_management_table button.btn-primary').on('click',function() {
					var title = $(this).parent().siblings().eq(0).text();
					$('#table_title').text(title);
					$('#table_view').modal('show');
					datatables_init('table_show', params, 1);
				});

				$('#accountmenu').on('mouseenter', function() {
					$('#accountdrop').dropdown('toggle');
				});

				$('#accountdrop').on('mouseleave', function() {
					$('#accountdrop').dropdown('toggle');
				});

				$('#des_table_btn').on('click', function() {
					oTable.fnDestroy(true);
					$('#btn_report_destory').hide();
					$('#btn_report_save').hide();
				});

				$('#create_delete_row').on(
						'click',
						function() {
							if (fnGetSeletedRow(oTable).length == 0) {
								$('#del_row h4').text(
										'Please at least select one row!');
								$('#del_row button').eq(1).hide();
								$('#del_row').modal('show');
							} else {
								$('#del_row h4').text('Delete these rows?');
								$('#del_row button').eq(1).show();
								$('#del_row').modal('show');
							}
						});

				$('#delete_row').on(
						'click',
						function() {
							if (fnGetSeletedRow(oTable).length == 0) {
								$('#del_row h4').text(
										'Please at least select one row!');
								$('#del_row button').eq(1).hide();
								$('#del_row').modal('show');
							} else {
								$('#del_row h4').text('Delete these rows?');
								$('#del_row button').eq(1).show();
								$('#del_row').modal('show');
							}
						});

				$('#del_row_btn').on('click', function() {
					var tr = fnGetSeletedRow(oTable);
					if (tr.length != 0) {
						for ( var i = 0; i < tr.length; i++) {
							oTable.fnDeleteRow(tr[i]);
						}
					}
					if (isCreateTable) {
						$('#create_select_all').prop('checked', false);
					} else {
						$('#select_all').prop('checked', false);
					}
				});

				$('#del_col_btn').on('click', function() {
					var col_num = fnGetSeletedCol(oTable);
					if (isCreateTable) {
						_params.splice(col_num, 1);
						oTable.fnDestroy(true);
						datatables_init('create_table_show', _params, 1);
					} else {
						params.splice(col_num, 1);
						oTable.fnDestroy(true);
						datatables_init('table_show', params, 1);
					}
				});

				$('#add_col_btn').on('click', function() {
					var titleName = $('#inputColName').val();
					if (isCreateTable) {
						_params.push({
							'sTitle' : titleName,
							'mData' : titleName
						});
						oTable.fnDestroy(true);
						datatables_init('create_table_show', _params, 0);
					} else {
						params.push({
							'sTitle' : titleName,
							'mData' : titleName
						});
						oTable.fnDestroy(true);
						datatables_init('table_show', params, 0);
					}
				});

				$('#create_add_row').on('click', function() {
					var th_num = $('#report_table th').length;
					var titles = [];
					for ( var i = 0; i < th_num; i++) {
						titles.push(_params[i].mData);
					}
					var data = createObject(titles, 'Enter new data');
					oTable.fnAddData(data, true);
					oTable.$('td').editable({
						type : 'text',
						title : 'Enter new value',
						emptytext : '',
						toggle : 'dblclick',
						value : ' ',
						mode : 'inline'
					});
				});

				$('#add_row').on('click', function() {
					var th_num = $('#report_table th').length;
					var titles = [];
					for ( var i = 0; i < th_num; i++) {
						titles.push(params[i].mData);
					}
					var data = createObject(titles, 'Enter new data');
					oTable.fnAddData(data, true);
					oTable.$('td').editable({
						type : 'text',
						title : 'Enter new value',
						emptytext : '',
						toggle : 'dblclick',
						value : ' ',
						mode : 'inline'
					});
				});

			});

			/*
			 * 自定义函数
			 */
			var fnGetSeletedRow = function(oTableLocal) {
				return oTableLocal.$('tr.trselected');
			};

			var fnGetSeletedCol = function(oTableLocal) {
				var th_num = $('#report_table th').length;
				var th = $('#report_table th');
				for ( var i = 0; i < th_num; i++) {
					if (th.eq(i).hasClass('sorting_desc')) {
						return i;
					} else if (th.eq(i).hasClass('sorting_asc')) {
						return i;
					}
				}

			};

			var createObject = function(titles, value) {
				var obj = new Object();
				for ( var i = 0; i < titles.length; i++) {
					obj[titles[i]] = value;
				}
				return obj;
			};

			var datatables_init = function(id, init_params, flag) {
				var _targets = new Array();
				var th_num = init_params.length;
				for ( var i = 0; i < th_num; i++) {
					_targets.push(i);
				}
				$('#' + id + '').append('<table class="table table-bordered " id="report_table"></table>');
				oTable = $('#report_table').dataTable(
					{
						"sPaginationType" : "full_numbers",
						"sDom" : 'T<"clear">lfrtip',
						"bProcessing" : true,
						"sAjaxSource" : id == 'table_show' ? '/iZone/resource/other/1.txt'
								: null,
						"aoColumns" : init_params,
						"bRetrieve" : true,
						"aoColumnDefs" : [ {
							"mData" : null,
							"sDefaultContent" : " ",
							"aTargets" : _targets
						} ],
						"oTableTools" : {
							"sSwfPath" : "/iZone/resource/js/util/swf/copy_csv_xls_pdf.swf",
							"aButtons" : [ "copy", "print", "xls" ],
						},
						"fnInitComplete" : function() {
							this.$('td').editable({
								type : 'text',
								title : 'Enter new value',
								emptytext : '',
								toggle : 'dblclick',
								mode : 'inline'
							});
							$('#report_table th').editable({
								type : 'text',
								title : 'Enter new value',
								emptytext : '',
								toggle : 'dblclick',
								mode : 'inline'
							});
							if (flag = 1) {
								this.delegate('tbody tr', 'click',function() {
									$(this).toggleClass(
											'trselected');
								});
							}
						},
					});
			};
			return {

			};
		});
