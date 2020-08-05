	//是否显示 start
	function headZhwmxqIsBlood_tr_isShow(){
			if($("#headZhwmxqIsBlood").val()=='0'){
				$(".headZhwmxqIsBlood_tr").hide();
			}else{
				$(".headZhwmxqIsBlood_tr").show();
			}	
	}
	
	function headNshzhIsHurt_tr_isShow(){
			if($("#headNshzhIsHurt").val()=='0'){
				$(".headNshzhIsHurt_tr").hide();
			}else{
				$(".headNshzhIsHurt_tr").show();
			}	
	}
	
	function headNshzhIsBlood_tr_isShow(){
			if($("#headNshzhIsBlood").val()=='0'){
				$(".headNshzhIsBlood_tr").hide();
			}else{
				$(".headNshzhIsBlood_tr").show();
			}	
	}
	function headNshzhIsMalacia_tr_isShow(){
		if($("#headNshzhIsMalacia").val()=='0'){
			$(".headNshzhIsMalacia_tr").hide();
		}else{
			$(".headNshzhIsMalacia_tr").show();
		}	
	}
	
	function headNshIsBlood_tr_isShow(){
		if($("#headNshIsBlood").val()=='0'){
			$(".headNshIsBlood_tr").hide();
		}else{
			$(".headNshIsBlood_tr").show();
		}	
	}
	
	function heartGzhdmLeftbranch_span_isShow(){
//alert($("#heartGzhdmLeftbranch").attr('checked'))
		if($("#heartGzhdmLeftbranch").attr('checked')==false){
			$("#heartGzhdmLeftbranch").val('0');
			$(".heartGzhdmLeftbranch_span").hide();
		}else{
			$("#heartGzhdmLeftbranch").val('1');
			$(".heartGzhdmLeftbranch_span").show();
		}	
	}
	
	function heartGzhdmLeftcircumflex_span_isShow(){

		if($("#heartGzhdmLeftcircumflex").attr('checked')==false){
			$("#heartGzhdmLeftcircumflex").val('0');
			$(".heartGzhdmLeftcircumflex_span").hide();
		}else{
			$("#heartGzhdmLeftcircumflex").val('1');
			$(".heartGzhdmLeftcircumflex_span").show();
		}	
	}
	
	function heartGzhdmRightbranch_span_isShow(){

		if($("#heartGzhdmRightbranch").attr('checked')==false){
			$("#heartGzhdmRightbranch").val('0');
			$(".heartGzhdmRightbranch_span").hide();
		}else{
			$("#heartGzhdmRightbranch").val('1');
			$(".heartGzhdmRightbranch_span").show();
		}	
	}
	//是否显示 end

	$(document).ready(function() {
		headZhwmxqIsBlood_tr_isShow();
		$("#headZhwmxqIsBlood").change(function() {
			headZhwmxqIsBlood_tr_isShow();
		});
		
		headNshzhIsHurt_tr_isShow();
		$("#headNshzhIsHurt").change(function() {
			headNshzhIsHurt_tr_isShow();
		});
		
		headNshzhIsBlood_tr_isShow();
		$("#headNshzhIsBlood").change(function() {
			headNshzhIsBlood_tr_isShow();
		});
		
		headNshzhIsMalacia_tr_isShow();
		$("#headNshzhIsMalacia").change(function() {
			headNshzhIsMalacia_tr_isShow();
		});
		
		headNshIsBlood_tr_isShow();
		$("#headNshIsBlood").change(function() {
			headNshIsBlood_tr_isShow();
		});
		
		heartGzhdmLeftbranch_span_isShow();
		$("#heartGzhdmLeftbranch").click(function() {
			heartGzhdmLeftbranch_span_isShow();
		});
		
		heartGzhdmLeftcircumflex_span_isShow();
		$("#heartGzhdmLeftcircumflex").click(function() {
			heartGzhdmLeftcircumflex_span_isShow();
		});
		
		heartGzhdmRightbranch_span_isShow();
		$("#heartGzhdmRightbranch").click(function() {
			heartGzhdmRightbranch_span_isShow();
		});
		
		/** 剩余字数start*/
		$("#headZhwmxqBloodLocation").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#headZhwmxqBloodRemarks").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#headNshzhHurtLocation").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#headNshzhHurtRemarks").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});

		$("#headNshzhBloodLocation").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#headNshzhBloodRemarks").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#headNshzhMalaciaLocation").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#headNshzhMalaciaRemarks").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});

		$("#headNshBloodLocation").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#headNshBloodRemarks").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#headRemarks").charCount({
			allowed : 100,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#heartLvw").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#heartRvw").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#heartRemarks").charCount({
			allowed : 100,
			warning : 10,
			counterText : '剩余字数: '
		});

		
		$("#lungLT").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#lungLD").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#lungRT").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#lungRM").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#lungRD").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		
		$("#kidney").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#liver").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});		
		$("#spleen").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});		
		$("#pancreas").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});	
		$("#renicapsule").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#thyroid").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});		
		$("#uterus").charCount({
			allowed : 20,
			warning : 10,
			counterText : '剩余字数: '
		});
		$("#lymphonodus").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});	
		$("#skin").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});	
		$("#remarks").charCount({
			allowed : 30,
			warning : 10,
			counterText : '剩余字数: '
		});			
		/** 剩余字数end*/
	});