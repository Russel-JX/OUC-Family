/**
 * 
 */
var cl;
$(document).ready(function(){
	    
	    $('#show_photo').addClass('hidden');
	
	    $("#ablum_list").delegate("div a img","click",function(){
			$('#ablum_show').addClass('hidden');
			$('#show_photo').removeClass('hidden');
			});
	
	$('#create_ablum').on('click',function(){
		alert($('#ablum_name').val());
		alert($('#ablum_description').val());
		$('#ablum_list').append('<div class="col-lg-3 col-md-4 col-xs-6 thumb"><a class="thumbnail" href="javascript:void(0);"> <img class="img-responsive" src="/iZone/resource/img/400x300.gif"></a><div class="caption" style="margin-top:0px"><h3 class="text-center">'+$('#ablum_name').val()+'</h3></div></div>');
		$('#myModal').modal('hide');
	});	

	
	$('#test').on('click',function(){
		
		alert($('#ablum_list').find('div').length);
	});
	
	$('#creat_ablum_interface').on('click',function(){
		$('#myModal').modal('show');
		
	});
});