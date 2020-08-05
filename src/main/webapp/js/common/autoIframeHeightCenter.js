$(function(){
  //左侧收缩及显示
	$('.positions').click(function(){
		if($('.sidebar').css('left') < '0'){
			$('.sidebar').animate({left: '0px'}, "slow");
			$('.positions').removeClass('positions_two');
			//$('.leis_s').removeClass('leis_s_click');
			var bodyWidth = $(document.body).width();
			var bodyHeight = $(document.body).height();
			var width_two = bodyWidth-185;
			var height_two = bodyHeight-4;
			$('.index_ifrme').animate({width:width_two}, "slow");
			$('#mainerif_two').animate({width:width_two}, "slow");
			
		}else{
			$('.sidebar').animate({left: '-180'}, "slow");
			$('.positions').addClass('positions_two');
			//$('.leis_s').addClass('positions_two');
			var bodyWidth = $(document.body).width();
			var bodyHeight = $(document.body).height();
			var width_one = bodyWidth-10;
			var height_one = bodyHeight-10;
			$('.index_ifrme').animate({width:width_one}, "slow");
			$('#mainerif_two').animate({width:width_one}, "slow");
		}
	});
	$('.sidebar ul li a').click(function(){
		$('.sidebar ul li a').removeClass('clicks');
		$(this).addClass('clicks').parent().siblings().children().removeClass('clicks');
	});
	$('.sidebar ul li a').hover(function(){
		$('.sidebar ul li a').removeClass('clickss');
		$(this).addClass('clickss').parent().siblings().children().removeClass('clickss');
	},function(){
		$('.sidebar ul li a').removeClass('clickss');
	});
});



$(function(){
	autoIframeWidth();
});
function autoIframeHeight(){
	var bodyHeight = $(document.body).height();
	var bodyWidth = $(document.body).width();
	$('.index_ifrme').width(bodyWidth-195);
	$('.index_ifrme').width(bodyWidth-195);
	$('#mainerifs').height(bodyHeight);
	$('#mainerif_two').height(bodyHeight-138);
	$('#mainerif_two').width(bodyWidth-195);
	
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
	}else if(bodyWidth <= 1280){
	}else if(bodyWidth <= 1366){
	}else{
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
$(function(){
	$('.nav li a').eq(0).addClass('clicks');
	$('.nav li a').hover(function(){
  		$(this).addClass('clicks').parent().siblings().children().removeClass('clicks');
	},function(){
		$('.nav li a').removeClass('clicks');
	});
	$('.nav li a').click(function(){
  		$(this).addClass('clickss').parent().siblings().children().removeClass('clickss');
	});
})