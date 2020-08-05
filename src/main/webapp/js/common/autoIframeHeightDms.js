$(function(){
	autoIframeWidth();
});
function autoIframeHeight(){
	var bodyHeight = $(document.body).height();
	$('#mainer_box').height(bodyHeight-118);
	$('#database_box').height(bodyHeight);
}
$(function(){
	autoIframeHeight();
});
$(window).resize(function(){
	autoIframeHeight();
});
function autoIframeWidth(){
	var bodyWidth = $(document.body).width();
	$('.widthsa').width(bodyWidth-197);
	//$().width(bodyWidth);
	if(bodyWidth <= 1024){
		$('.table_three').css({width:'44.98%'});
		$('.table_four').css({width:'44.98%'});
	}else if(bodyWidth <= 1280){
		$('.table_three').css({width:'45.22%'});
		$('.table_four').css({width:'45.22%'});
	}else if(bodyWidth <= 1366){
		$('.table_three').css({width:'45.27%'});
		$('.table_four').css({width:'45.27%'});
	}else{
		$('.table_three').css({width:'45.3%'});	
		$('.table_four').css({width:'45.3%'});
	};
	if ($.browser.msie && ($.browser.version == "6.0")){
		if (document.documentElement.clientWidth < document.documentElement.offsetWidth-4){
 			$('.positionA').width(bodyWidth-214);
		};
	};
	
};
$(function(){
	autoIframeWidth();
});
$(window).resize(function(){
	autoIframeWidth();
});
//tabÇÐ»»
$(function(){
	$('.table_two re:odd').css({color:'#e7e7ff'})
	$('.box_four ul li a').hover(function(){
		$(this).addClass('clickss').parent().siblings().children().removeClass('clickss');								  
	},function(){
		$('.box_four ul li a').removeClass('clickss');		
	})
	$('.box_four ul li a').first().addClass('clicks');
	//$('.boxs div').css({height:'300px'})
	$('.fides').eq(0).show().siblings().hide()
	$('.box_four ul li a').click(function(){
		$(this).addClass('clicks').parent().siblings().children().removeClass('clicks');
		var ex=$('.box_four ul li a').index(this);
		$('.fides').eq(ex).show().siblings().hide()
	})	   
})




