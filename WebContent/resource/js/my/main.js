function require_init()
{
	require.config(
			
			
	{
		baseUrl: "/iZone/resource/js/util/",
		
		paths :
		{
			"jquery" : "jQuery-1.10.2",
			"bootstrap" : "bootstrap",
			"datatables" : "jquery.dataTables",
			"editable" : "bootstrap-editable",
			"datatableTool":"TableTools.min",
			"zero":"ZeroClipboard",
		},
		
		shim :
		{
			'bootstrap' :
			{
				deps : ['jquery' ]
			},
			'editable' :
			{
				deps : ['jquery','datatables','bootstrap' ]
			},
			'datatables':{
				deps:['jquery','bootstrap']
			},
			'datatableTool':{
				deps:['datatables']
			}
		
		}
	});
	
}