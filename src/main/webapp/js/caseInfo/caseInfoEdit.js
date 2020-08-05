// 刷新委托列表
function updateAllConsignationData(list, obj) {
	var inhtml = "";
	obj.empty();
	for ( var i = 0; i < list.length; i++) {

		var consignationSn = strSubString(list[i].consignationSn, 0, 10);
		if (list[i].status == null||list[i].status==''||list[i].status=='01') {
			list[i].status = "未提交";
		}
		var createOrg = strSubString(list[i].createOrg, 0, 18);
		inhtml += "<tr class='tr_colors'><input type='hidden' name='consignationInfoId' value='" + list[i].consignationId + "' /><td>"
				+ list[i].major + "</td><td>" + list[i].status + "</td><td><a  onclick='showDialog(\"consignInfoEdit.html?consignationId="
				+ list[i].consignationId + "\",\"850px\",\"600px\")' style='color:blue'>编辑</a>  "
				+ "<a  onclick='delConsignation(\"delConsignInfo.html?consignationId=" + list[i].consignationId + "\")' "
				+ "style='color:blue'>删除 </a></td></tr>";

	}
	inhtml += "";
	obj.append(inhtml);
}

//
// 删除被鉴定人信息
function delMemberInfo(url, memberId) {
	if (confirm("确定要删除该人员信息?")) {
		$.ajax({
			url :  url,
			type : "GET",
			dataType : 'json',
			data : {
				"memberId" : memberId
			},
			success : function(data) {
				var status = data.status;
				if (status == "y") {
					var list = data.memberList;
					var obj = $("#allMemberTable tbody");
					alert("删除成功!")
					updateAllMemeberData(list, obj);

				} else {
					alert("删除失败!")
				}
			},
			error : function(data) {
				alert("error")
			}
		});
	} else {
		return;
	}
}

// 刷新被鉴定人信息列表
function updateAllMemeberData(list, obj) {
	var inhtml = "";
	obj.empty();
	for ( var i = 0; i < list.length; i++) {
		var name = strSubString(list[i].name, 0, 10);
		var identity = strSubString(list[i].identityName, 0, 5);
		var idCard = strSubString(list[i].idCard, 0, 18);
		var country = strSubString(list[i].countryName, 0, 5);
		var nation = strSubString(list[i].nationName, 0, 5);
		var phone = strSubString(list[i].phone, 0, 12);
		inhtml += "<tr class='tr_colors'><input type='hidden' name='memberInfoId' value='" + list[i].id + "' />" + "<td title='" + list[i].name + "'>" + name
				+ "</td>" + "<td title='" + list[i].idCard + "'>" + idCard + "</td>"
				+ "<td title='" + list[i].country + "'>" + country + "</td>" + "<td title='" + list[i].nation + "'>" + nation + "</td>"
			//	+ "<td title='" + strSubString(list[i].birthpalce, 0, 12) + "'>" + strSubString(list[i].birthpalce, 0, 12) + "</td>" 
				+ "<td title='" + list[i].phone + "'>" + phone + "</td>" + "<td ><a  onclick='showDialog(\"memberInfoEdit.html?memberId="
				+ list[i].memberId + "\",\"750px\",\"500px\")' style='color:blue'>编辑</a>  "
				+ "<a  onclick='delMemberInfo(\"delMemberInfo.html\",\"" + list[i].memberId + "\")' style='color:blue'>删除 </a></td></tr>";
	}
	inhtml += "";
	obj.append(inhtml);
}

// 删除委托信息
function delConsignation(url, consignationId) {

	if (confirm("确定要删除该委托信息?")) {// alert(url)
		$.ajax({
			url :  url,
			type : "GET",
			dataType : 'json',
			data : {
				"consignationId" : consignationId
			},
			success : function(data) {
				var status = data.status;
				if (status == "y") {
					var list = data.allConsignationList;
					var obj = $("#allConsignationListTable tbody");
					var major = data.major;
					alert("删除成功!")
					updateAllConsignationData(list, obj, major);

				} else {
					alert("删除失败!")
				}
			},
			error : function(data) {
				alert("error")
			}
		});
	} else {
		return;
	}
}

//
function isMeasurementUnitNull(selectedUnit, amountDp) {

	if (amountDp.val() != '' && selectedUnit.val() == '') {
		alert("请选择计量单位!")
		return false;
	}
	if (amountDp.val() == '') {
		selectedUnit.removeAttr("datatype");
	}
	return true;
}

// 初始化重量单位
function initMeasurementUint() {
	var selectedUnit = $("#unitType1 option:selected");
	var otherMeasurementUnitSpan = $("#otherMeasurementUnitSpan1");
	var flag = "其他";
	var otherMeasurementUnitInput = $("#otherMeasurementUnit1");
	setOtherMeasurementUnit(selectedUnit, otherMeasurementUnitSpan, flag, otherMeasurementUnitInput);

	var selectedUnit2 = $("#unitType2 option:selected");
	var otherMeasurementUnitSpan2 = $("#otherMeasurementUnitSpan2");
	var otherMeasurementUnitInput2 = $("#otherMeasurementUnit2");
	setOtherMeasurementUnit(selectedUnit2, otherMeasurementUnitSpan2, flag, otherMeasurementUnitInput2);
}

// 设置重量单位
function setOtherMeasurementUnit(selectedUnit, otherMeasurementUnitSpan, flag, otherMeasurementUnitInput) {
	if (selectedUnit.text() == flag) {
		otherMeasurementUnitSpan.show();
		otherMeasurementUnitInput.attr("datatype", "*");
	} else {
		otherMeasurementUnitSpan.hide();
		otherMeasurementUnitInput.removeAttr("datatype");
	}
}

// 删除检材信息
function delSample(url, sampleId, major) {
	var caseId = $("input[name='caseId']").val();
	var consignationId = $("input[name='consignationId']").val();

	if (confirm("确定要删除该样本信息?")) {
		$.ajax({
			url :  url,
			type : "GET",
			dataType : 'json',
			data : {
				"sampleId" : sampleId,
				"caseId" : caseId,
				"consignationId" : consignationId,
				"major" : major,
				"caseBaseId" : $("input[name='baseId']").val()
			},
			success : function(data) {
				var status = data.status;
				if (status == "y") {
					var list = data.sampleList;
					var obj = $("#sampleTable_dp tbody");
					alert("删除成功!")
					updateSampleListDp(data, obj);

				} else {
					alert("删除失败!")
				}
			},
			error : function(data) {
				alert("error")
			}
		});
	} else {
		return;
	}
}

// 更新检材信息列表
function updateSampleListDp(data, obj) {
	var list = data.sampleList;
	var major = data.major;
	obj.empty();

	var inhtml = "";
	for ( var i = 0; i < list.length; i++) {

		var sampleType = strSubString(list[i].sampleTypeName, 0, 10);
		var amount = strSubString(list[i].amount, 0, 15);
		var measurementUnit = strSubString(list[i].measurementUnitName, 0, 18);
		var remark = strSubString(list[i].remark, 0, 15);
		var sampleName = strSubString(list[i].sampleNameName, 0, 15);
//alert(major)
		if (major == '01') {
			inhtml += "<tr class='tr_colors'><input type='hidden' name='sampleId' value='" + list[i].sampleId + "' /><td >" + sampleType
					+ "</td><td>" + strSubString(list[i].count, 0, 15)
					+ "</td><td>" + strSubString(list[i].colorName, 0, 15)
					+ "</td><td>" + strSubString(list[i].sampleCharacterName, 0, 15)
					+ "</td><td>" + (amount + " " + measurementUnit)

			//		+ "</td><td>" + (strSubString(list[i].acceptedAmount, 0, 15) + " " + measurementUnit)

					+ "</td><td>" + strSubString(list[i].packingName, 0, 15)
					+ "</td><td>" + strSubString(list[i].firstResultName, 0, 15)
					+ "</td><td>" + remark
					+ "</td><td><a  onclick='showDialog(\"sampleEdit.html?major=" + list[i].major + "&sampleId=" + list[i].sampleId
					+ "\",\"750px\",\"500px\")' style='color:blue'>编辑</a>  " + "<a  onclick='delSample(\"delSample.html\",\""
					+ list[i].sampleId + "\",\"" + list[i].major + "\")' style='color:blue'>删除 </a></td></tr>";
		} else if (major == '02'){
			inhtml += "<tr class='tr_colors'><input type='hidden' name='sampleId' value='" + list[i].sampleId + "' /><td >" + sampleName
			+ "</td><td>" + strSubString(list[i].testItemsName, 0, 15)
			+ "</td><td>" + (amount + " " + measurementUnit)
			+ "</td><td>" + strSubString(list[i].packingName, 0, 15)
			+ "</td><td>" + remark
			+ "</td><td><a  onclick='showDialog(\"sampleEdit.html?major=" + list[i].major + "&sampleId=" + list[i].sampleId
			+ "\",\"750px\",\"500px\")' style='color:blue'>编辑</a>  " + "<a  onclick='delSample(\"delSample.html\",\""
			+ list[i].sampleId + "\",\"" + list[i].major + "\")' style='color:blue'>删除 </a></td></tr>";
		} else if (major == '03')  {
			inhtml += "<tr class='tr_colors'><input type='hidden' name='sampleId' value='" + list[i].sampleId + "' /><td >" + sampleName
					+ "</td><td>" + (amount + " " + measurementUnit) 
					+ "</td><td>" + strSubString(list[i].sampleCharacterName, 0, 15)
					+ "</td><td>" + remark
					+ "</td><td><a  onclick='showDialog(\"sampleEdit.html?major=" + list[i].major + "&sampleId=" + list[i].sampleId
					+ "\",\"750px\",\"500px\")' style='color:blue'>编辑</a>  " + "<a  onclick='delSample(\"delSample.html\",\""
					+ list[i].sampleId + "\",\"" + list[i].major + "\")' style='color:blue'>删除 </a></td></tr>";
		}else if (major == '04') {
			inhtml += "<tr class='tr_colors'><input type='hidden' name='sampleId' value='" + list[i].sampleId + "' /><td >" + strSubString(list[i].hospital, 0, 38)
			+ "</td><td>" + (amount + " " + measurementUnit) + "</td><td>" + remark
			+ "</td><td><a  onclick='showDialog(\"sampleEdit.html?major=" + list[i].major + "&sampleId=" + list[i].sampleId
			+ "\",\"750px\",\"500px\")' style='color:blue'>编辑</a>  " + "<a  onclick='delSample(\"delSample.html\",\""
			+ list[i].sampleId + "\",\"" + list[i].major + "\")' style='color:blue'>删除 </a></td></tr>";
		}
//alert(inhtml)
	}

	inhtml += "";
	obj.append(inhtml);
}

$(function() {
	initdatepicker_cn();
	$("input[name='birthdate']").datepicker();

	$("input[name='idCard']").blur(function() {
		twoSelOne($("input[name='idCard']"), $("input[name='noIdReason']"));
	});

	$("input[name='noIdReason']").blur(function() {
		twoSelOne($("input[name='idCard']"), $("input[name='noIdReason']"));
	});

});

/** 二选一 */
function twoSelOne(obj1, obj2) {
	if (obj1.val() == '' && obj2.val() == '') {
		obj1.attr("datatype", "*");
		obj2.attr("datatype", "*");
		obj1.parent().prev().addClass("red");
		obj2.parent().prev().addClass("red");
	}
	if (obj1.val() != '' && obj2.val() == '') {
		obj1.attr("datatype", "*");
		obj1.parent().prev().addClass("red");
		obj2.removeAttr("datatype");
		obj2.parent().prev().removeClass("red");
	}
	if (obj1.val() == '' && obj2.val() != '') {
		obj2.attr("datatype", "*");
		obj2.parent().prev().addClass("red");
		obj1.removeAttr("datatype");
		obj1.parent().prev().removeClass("red");
	}
}



