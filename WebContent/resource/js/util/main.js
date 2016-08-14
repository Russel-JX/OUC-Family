/*require.config({
	baseUrl:'js',
	paths:{
		"jquery":"jquery-2.1.0",
	}	
});

require(['jquery'],function($){	
	alert('1234');
	$(function(){
		alert('ahaha');
	});
	
});
*/
/*require(['math'],function(math){	
	alert(math.add(1,1));
});*/
/*require(['jquery','bootstrap'],function($,bootstrap){
	
	$(function(){
		alert(12);
		$('#dj').on('click',function(){
			$('#myModal').modal('show');
		});
	});
});*/

require.config({
	paths:{
		'jquery':'jquery-2.1.0',
		'index':'index',
		"bootstrap":'bootstrap'},
	shim:{
		
		'bootstrap':{
			deps:['jquery']
		},
	},
});

require(['index'],function(index){
	
	
});