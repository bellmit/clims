$(function() {
	initdatepicker_cn();

	$("form").Validform({
		tiptype : 1,
		label : ".label",
		ajaxPost : true,
		beforeCheck : function(curform) {

			var measurementUnit = $("input[name='amount']").val();
		//	if(measurementUnit!=''){
			if (!isMeasurementUnitNull($("select[name='measurementUnit']"), $("input[name=amount]")))
				return false;

			if(!doubleValidate(measurementUnit)){
				return false;
			}
		//	}
		},
		callback : function(data) {
			if (data.status == "y") {
				setTimeout(function() {
					$.Hidemsg(); // 公用方法关闭信息提示框;显示方法是$.Showmsg("message goes
					// here.");
				}, 100);

				var obj = $("#sampleTable_dp  tbody", window.dialogArguments.document);

				updateSampleListDp(data, obj);
				$("input[name='id']").val(data.sampleId);

				
				alert("保存成功")
			}
		}
	});

	$("#unitType1").change(function() {
		initMeasurementUint();
	});
	
	$("#unitType2").change(function() {
		initMeasurementUint();
	});

	initMeasurementUint();

	$.extend($.Datatype, {
		"float" : /^[0-9]+\.{0,1}[0-9]{0,2}$/
	});

});

function initSampleType() {
	if ($("input[name='sampleType'][checked]").val() == '02') {
		$("#type02").show();
		$("#type01").hide();
	} else if ($("input[name='sampleType'][checked]").val() == '01') {
		$("#type01").show();
		$("#type02").hide();
	}
}

function initDrugUsed() {
	if ($("input[name='drugUsed'][checked]").val() == '02') {
		$("#drugUsedSpan").show();
	} else if ($("input[name='drugUsed'][checked]").val() == '01') {
		$("#drugUsedSpan").hide();
	}
}

/** 其他选项设置start*/
$(function() {
	//颜色
	$("select[name='color']").change(function (){
		setSelectOtherValue('color');
	});
	setSelectOtherValue('color');
	
	//包装
	$("select[name='packing']").change(function (){
		setSelectOtherValue('packing');
	});
	setSelectOtherValue('packing');
	
	//检材名称
	$("select[name='sampleName']").change(function (){
		setSelectOtherValue('sampleName');
	});
	setSelectOtherValue('sampleName');
	
	//初检结果
	$("select[name='firstResult']").change(function (){
		setSelectOtherValue('firstResult');
	});
	setSelectOtherValue('firstResult');
	
	//性状
	$("select[name='sampleCharacter']").change(function (){
		setSelectOtherValue('sampleCharacter');
	});
	setSelectOtherValue('sampleCharacter');
	
	//检测项目
	$("input[name='testItems']").click(function (){
		setCheckBoxOtherValue('testItems');
	});
	setCheckBoxOtherValue('testItems');
});
/** 其他选项设置end*/


