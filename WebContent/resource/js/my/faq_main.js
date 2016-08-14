function require_init()
{
	require.config(
			
			
	{
		baseUrl: "/iZone/resource/js/util/",
		
		paths :
		{
			"jquery" : "jQuery-1.10.2",
			"bootstrap" : "bootstrap",
			"bootstrapSelect":"bootstrap-select.min",
			"dt":"jquery.dataTables.min"
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
			"dt":
			{
				deps:['jquery']
			}
		}
	});
	
}