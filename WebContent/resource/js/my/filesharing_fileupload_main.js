function require_init()
{
	require.config(
			
			
	{
		baseUrl: "/iZone/resource/js/util/",
		
		paths :
		{
			"jquery" : "jQuery-1.10.2",
			"bootstrap" : "bootstrap",
			"jquery.ui.widget":"jquery.ui.widget",
			"Jfileupload":"jquery.fileupload",
			"bootstrapSelect":"bootstrap-select.min",
			"jit":"jquery.iframe-transport",
			"bootEdit":"bootstrap-editable",
			"mockjax":"jquery.mockjax",
		},
		
		shim :
		{
			'bootstrap' :
			{
				deps : ['jquery' ]
			},
			"bootstrapSelect":
			{
				deps:['jquery','bootstrap']
			},
			"jit":
			{
				deps:['jquery']
			},
			"bootEdit":
			{
				deps:['jquery','bootstrap']
			},
			"mockjax":
			{
				deps:['jquery']
			}
		
		}
	});
	
}