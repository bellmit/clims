
$(".addChildren").click(function () {
   var newTr =$(this).parents("tr").clone(true)
    newTr.find("td").last().html("<button class='btn btn-red removeChildren'>删除</button>")
    newTr.find("td").eq(2).children("input").val('')
    newTr.find("td").eq(3).children("input").val('')
    newTr.find("td").eq(4).children("input").val('')
    $("#newAddZTbodyId").append(newTr)
})
$("#newAddZTbodyId").on("click",".removeChildren",function () {
    $(this).parents("tr").remove()
})
function addRelationZID(){
    var appRowVictim = "";
    var zListObj = document.getElementById("ComparedToCaseSampleZID");
    appRowVictim+=" <tr>";
    appRowVictim+=" <td class='control-label col-md-2' style='width: 12%'>";
    appRowVictim+=" <input type='radio'  name='rcRadio'  value='2'>子：</td>";
    appRowVictim+="<td class='col-md-4'><select id='newAddRelationCompareToZID' name='ComparedToCaseSampleZ' class='form-control small' style='font-size: 13px'>";
    appRowVictim+="<option  value=''>请选择...</option>";
    for(var i=0; i<zListObj.options.length; i++){
        if(zListObj.options[i].value != "")
            appRowVictim+="<option value='"+zListObj.options[i].value+"'>"+ zListObj.options[i].text+"</option>";
    }
    appRowVictim+= "</select></td>";
    appRowVictim+="<td class='col-md-2'><input name='totalPi' class='form-control input-sm' onmouseover='this.title=this.value' readonly></td>";
    appRowVictim+="<td class='col-md-2'><input name = 'matchCount' class='form-control input-sm' onmouseover='this.title=this.value' readonly></td>";
    appRowVictim+="<td class='col-md-2'><input name='matchModeName' class='form-control input-sm' onmouseover='this.title=this.value' readonly></td>";
    appRowVictim+="<input type='hidden' name='panelNameF' >";
    appRowVictim+="<input type='hidden' name='panelNameM' >";
    appRowVictim+="<input type='hidden' name='panelNameC' >";
    appRowVictim+="<input type='hidden' name='sampleNameF' >";
    appRowVictim+="<input type='hidden' name='sampleNameM' >";
    appRowVictim+="<input type='hidden' name='sampleNameC' >";
    appRowVictim+="<input type='hidden' name='sampleNoF' >";
    appRowVictim+="<input type='hidden' name='sampleNoM' >";
    appRowVictim+="<input type='hidden' name='sampleNoC' >";
    appRowVictim+="<input type='hidden' name='sampleIdF' >";
    appRowVictim+="<input type='hidden' name='sampleIdM' >";
    appRowVictim+="<input type='hidden' name='sampleIdC' >";
    appRowVictim+="<input type='hidden' name='singleMatchMode' >";
    appRowVictim+="<input type='hidden' name='singleSameCount'  >";
    appRowVictim+="<input type='hidden' name='singleDiffCount'  >";
    appRowVictim+="<input type='hidden' name='singlePopulationID' >";
    appRowVictim+="<td><button id='deleteRelationZID' class='btn btn-primary btn-sm' onclick = 'closeTr(this);'><i class='fa fa-trash-o'></i>删 除</button></td>"
    appRowVictim+="</tr>";
    $("#newAddZTbodyId").append(appRowVictim);
}


$(function () {
    //亲缘比对明细
    $("button[name='viewMatchRelation']", "#raletionID").on("click", function(){
        console.log($(this).parent())
        location.href = baseurl + "/center/viewRelationCompareResult?" + getParameter($(this).parent());

    });
    function getParameter(obj){
        var fatherSampleNo = $("input[name='fatherSampleNoHidden']", obj).val();
        var motherSampleNo = $("input[name='motherSampleNoHidden']", obj).val();
        var childSampleNo = $("input[name='childSampleNoHidden']", obj).val();
        var comparePopulationId = $("input[name='compareRelationPopulationId']", obj).val();
        var matchLimit = $("input[name='matchRelationLimit']", obj).val();
        var tolerance = $("input[name='toleranceRelation']", obj).val();
        var caseId = $("input[name='caseIdHidden']", obj).val();

        return "fatherSampleNo=" + fatherSampleNo + "&motherSampleNo=" + motherSampleNo + "&childSampleNo=" + childSampleNo
            + "&comparePopulationId=" + comparePopulationId + "&matchLimit=" + matchLimit + "&tolerance=" + tolerance + "&caseId=" + caseId;
    }

    function viewMatched(obj){
        var referenceId = $("input[name='referenceId']", obj).val();
        var sampleNo = $("input[name='sampleNo']", obj).val();
        var comparePopulationId = $("input[name='comparePopulationId']", obj).val();
        var matchLimit = $("input[name='matchLimit']", obj).val();
        var tolerance = $("input[name='tolerance']", obj).val();

        return "referenceId=" + referenceId + "&sampleNo=" + sampleNo + "&comparePopulationId=" + comparePopulationId
            + "&matchLimit=" + matchLimit + "&tolerance=" + tolerance ;
    }

    //同一查看明细
    $("button[name='viewMatched']", "#matchedGroupTBody").on("click",function(){

        var referenceId = $("input[name='referenceId']", $(this).parent()).val();
        if (referenceId == "" || referenceId == null && typeof (referenceId) == "undefined") {
            alert("请先保存比对结果！");
            return false;
        }

        location.href = baseurl + "/center/viewCompareResult?" + viewMatched($(this).parent());

    });

    //重新比对
    $("#afreshMatchBtn").on("click",function(){
        var caseId = $(".caseId").val();
        var consignmentId = $(".consignmentId").val();
        var matchLimit = $(".minSameCaseCount").val();
        var mixMatchLimit = $(".minSameMixCount").val();

        var relativeSameCount = $(".relativeSameCount").val();
        var halfDiffCount = $(".halfDiffCount").val();
        if(isNaN(matchLimit)){
            alert("请输入数字!");
            $(".minSameCaseCount").focus();
            return false;
        }
        if(isNaN(mixMatchLimit)){
            alert("请输入数字!");
            $(".minSameMixCount").focus();
            return false;
        }
        var url = baseurl + "/center/enterCaseCompare?caseId="+ caseId + "&consignmentId=" + consignmentId + "&matchLimit=" +
            matchLimit.trim() + "&mixMatchLimit=" + mixMatchLimit.trim() + "&relativeSameCount=" + relativeSameCount.trim() +
            "&halfDiffCount=" + halfDiffCount.trim() + "&isAgainMatch=1";
        location.href=url;
    });

    $("#generateInstrument").on("click", function(){
        generateInstrument(2);
    });

    function generateInstrument(obj) {
        var examiner = {};

        var inspector1 = $("#first option:selected").val()
        var inspector2 = $("#second option:selected").val()
        var inspector3 = $("#third option:selected").val()
        var caseId = $(".caseId").val();
        if(inspector1 == null || inspector1 == ''){
            alert("请选择检验人");
            return false
        }
        if(inspector2 == null || inspector2 == ''){
            alert("请选择检查人");
            return false
        }
        if(inspector3 == null || inspector3 == ''){
            alert("请选择授权签字人");
            return false
        }
        if (inspector1 == inspector2 || inspector1 == inspector3 || inspector2 == inspector3) {
            alert("检验人重复,请重新选择")
            return false;
        }

        examiner.inspector1 = inspector1;
        examiner.inspector2 = inspector2;
        examiner.inspector3 = inspector3;
        examiner.caseId = caseId;

        //法医师职称
        var titleOne = $("#titleOne option:selected").val()
        var titleTwo = $("#titleTwo option:selected").val()
        var titleThree = $("#titleThree option:selected").val()
        if(titleOne == null || titleOne == ''){
            alert("请选择检验人职称");
            return false
        }
        if(titleTwo == null || titleTwo == ''){
            alert("请选择检查人职称");
            return false
        }
        if(titleThree == null || titleThree == ''){
            alert("请选择授权签字人职称");
            return false
        }
        examiner.titleOne = titleOne;
        examiner.titleTwo = titleTwo;
        examiner.titleThree = titleThree;

        $.ajax({
            url : baseurl + "/center/saveExaminer",
            type:"post",
            contentType:  "application/json; charset=utf-8",
            data : JSON.stringify(examiner),
            dataType : "json",
            success : function(data) {
                if(data.success || data.success == true || data.success == "true") {
                    if (obj == 1) {
                        //保存比对结果
                        saveResult();
                    }else {
                        location.href= baseurl + "/center/generateTestimonial?caseId=" + caseId
                    }
                }
            },
            error: function(e){
                alert(e);
            }
        });

    }

    function updateInvalidStatus (matchAuditGeneList) {

        var params = {
            "matchAuditGeneList": matchAuditGeneList
        };
        alert(JSON.stringify(params))
        $.ajax({
            url : baseurl + "/center/updateInvalidStatus",
            type:"post",
            contentType:  "application/json; charset=utf-8",
            data : JSON.stringify(params),
            dataType : "json",
            success : function(data) {
                if(data.success || data.success == true || data.success == "true") {

                }
            },
            error: function(e){
                alert(e);
            }
        });
    }

    //保存比对结果
    $("#saveBtn").on("click",function(){
        //保存检验人
        generateInstrument(1);

    });

    function saveResult() {

        var flag = true;
        //把无效检材更改成未检出状态
        var matchAuditGeneList = GetMatchAuditGene();
        if(matchAuditGeneList.length > 0) {
            $.ajax({
                url : baseurl + "/center/updateInvalidStatus",
                type:"post",
                contentType:  "application/json; charset=utf-8",
                data : JSON.stringify(matchAuditGeneList),
                dataType : "json",
                success : function(data) {
                    if(data.success || data.success == true || data.success == "true") {
                        window.location.reload()
                    }else{
                        flag = false;
                    }
                },
                error: function(e){
                    flag = false;
                    alert(e);
                }
            });
        }

        if (!flag) {
            alert("更新无效检材失败!");
            return false;
        }

        var matchLimit = $(".minSameCaseCount").val();
        if(isNaN(matchLimit)){
            alert("请输入数字!");
            $("#matchLimitTxt").val("");
            $("#matchLimitTxt").focus();
            return false;
        }
        var relativeSameCount = $(".relativeSameCount").val();
        if(isNaN(relativeSameCount)){
            alert("请输入数字!");
            $(".relativeSameCount").val("");
            $(".relativeSameCount").focus();
            return false;
        }

        var limsConsignmentInfo = GetLimsConsignmentInfo();
        var compareSameResultList = GetCompareSameResult();
        var compareRelativeResultList = GetCompareRelativeResult();
        var mixMatchedGroupList = mixMatchedGroup();

        if(compareSameResultList.length == 0 && compareRelativeResultList.length == 0 && mixMatchedGroupList.length == 0){
            // alert("没有比中检材，无法保存!");
            return true;
        }

        var params = {
            "minSameCaseCount": $(".minSameCaseCount").val(),
            "minSameMixCount": $(".minSameMixCount").val(),
            "relativeSameCount": $(".relativeSameCount").val(),
            "halfDiffCount": $(".halfDiffCount").val(),
            "limsConsignmentInfo": limsConsignmentInfo,
            "compareSameResultList": compareSameResultList,
            "compareRelativeResultList": null
        };

        $.ajax({
            url : baseurl + "/center/saveComparisonResult",
            type:"post",
            contentType:  "application/json; charset=utf-8",
            data : JSON.stringify(params),
            dataType : "json",
            success : function(data) {
                history.go(0)
                var sameFlag = false;
                if(data.sameFlag || data.sameFlag == true || data.sameFlag == "true") {
                    sameFlag = true;
                    history.go(0)
                }else {
                    alert("同一比对结果保存失败!");
                }

                var relationFlag = false;
                if(data.relationFlag || data.relationFlag == true || data.relationFlag == "true") {
                    relationFlag = true;
                    history.go(0)
                }else {
                    alert("亲缘比对结果保存失败!");
                }

                if (sameFlag && relationFlag) {
                    var caseId = $(".caseId").val();
                    var consignmentId = $(".consignmentId").val();
                    var url = baseurl + "/center/enterCaseCompare?caseId="+ caseId + "&consignmentId=" + consignmentId
                        + "&matchLimit=" + matchLimit.trim();
                    location.href=url;
                    history.go(0)

                }
            },
            error: function(e){
                alert(e);
            }
        });
    }

    function GetMatchAuditGene(){
        var matchAuditGeneArr = new Array();
        var matchAuditGene;
        $("tr", "#notMatchedGeneTBody").each(function () {
            matchAuditGene = {};
            var checkBox = $("input[name='invalidBox']", $(this)).is(":checked");
            if (checkBox) {
                matchAuditGene.auditedGeneId = $("input[name='invalidGeneId']", $(this)).val();
                matchAuditGene.invalidStatus = "1";
                matchAuditGeneArr.push(matchAuditGene);
            }
        });
        return matchAuditGeneArr;
    }

    function GetLimsConsignmentInfo(){
        var limsConsignmentInfo = {};

        limsConsignmentInfo.caseId = $(".caseId").val();
        limsConsignmentInfo.consignmentId = $(".consignmentId").val();

        return limsConsignmentInfo;
    }

    function GetCompareRelativeResult() {
        var compareRelativeResultArr = new Array();
        var compareRelativeResult;

        var $compareRelativeResultTR = $("tr", "#raletionID");
        var compareRelativeResultCnt = $compareRelativeResultTR.length;
        for (var i = 0; i < compareRelativeResultCnt; i++) {
            compareRelativeResult = {};

            compareRelativeResult.caseId = $("input[name='caseId']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.fatherSampleNo = $("input[name='fatherSampleNo']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.motherSampleNo = $("input[name='motherSampleNo']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.childSampleNo = $("input[name='childSampleNo']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.compareGeneSum = $("input[name='relativeMatchCount']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.compareTotalProbability = $("input[name='relativeTotalProbability']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.comparePopulationId = $("input[name='relativePopulationId']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.referenceId = $("input[name='relativeReferenceId']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.matchLimit =  $("input[name='relativeSameCount']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.tolerance = $("input[name='relativeDiffCount']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.matchMode = $("input[name='relativeMatchMode']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.minSameCaseCount = $("input[name='minSameCaseCount']", $compareRelativeResultTR.get(i)).val();
            compareRelativeResult.minSameMixCount = $("input[name='minSameMixCount']", $compareRelativeResultTR.get(i)).val();


            compareRelativeResultArr.push(compareRelativeResult);
        }

        return compareRelativeResultArr;
    }

    function GetCompareSameResult(){
        var compareSameResultArr = new Array();

        var $compareSameResultTR = $("tr", "#matchedGroupTBody");
        var compareSameResultCnt = $compareSameResultTR.length;
        var compareSameResult;
        for (var i = 0; i < compareSameResultCnt; i++) {
            compareSameResult = {};
            compareSameResult.caseId = $(".caseId").val();
            compareSameResult.panelId = $("input[name='panelId']", $compareSameResultTR.get(i)).val();
            compareSameResult.referenceId = $("input[name='referenceId']", $compareSameResultTR.get(i)).val();
            compareSameResult.sampleNo = $("input[name='sampleNo']", $compareSameResultTR.get(i)).val();
            compareSameResult.compareBoardNo = $("input[name='boardNo']", $compareSameResultTR.get(i)).val();
            compareSameResult.compareGeneSum = $("input[name='sameCount']", $compareSameResultTR.get(i)).val();
            compareSameResult.compareTotalProbability = $("input[name='totalProbability']", $compareSameResultTR.get(i)).val();
            compareSameResult.comparePopulationId = $("select[name='samePopulation'] option:selected").val();
            compareSameResult.matchLimit = $(".minSameCaseCount").val();
            compareSameResult.tolerance = "";

            compareSameResultArr.push(compareSameResult);
        }

        return compareSameResultArr;
    }

    function mixMatchedGroup(){
        var mixMatchedSamplesArr = new Array();

        var $mixMatchedSamplesArrTR = $("tr", "#mixGroupTBody");
        var mixMatchedSamplesArrCnt = $mixMatchedSamplesArrTR.length;
        var mixMatchedSamples;
        for (var i = 0; i < mixMatchedSamplesArrCnt; i++) {
            mixMatchedSamples = {};
            mixMatchedSamples.sampleId = $("input[name='sampleId']", $mixMatchedSamplesArrTR.get(i)).val();

            mixMatchedSamplesArr.push(mixMatchedSamples);
        }

        return mixMatchedSamplesArr;
    }

    //比对
    $("#matchBtn").on("click",function(){
        if($(".relativeSameCount").val() <0) {
            alert("匹配下限不能小于0");
            return false;
        }

        if($("#relativePopulation").val == "") {
            alert("请选择种族!");
            return false;
        }

        var Fval = $("#ComparedToCaseSampleFID").val();
        var Mval = $("#ComparedToCaseSampleMID").val();
        var Zval = document.getElementsByName("ComparedToCaseSampleZ");

        var chooseZ = false;
        if(Fval== '' &&  Mval== ''){
            alert("请选择父样本或母亲样本!");
            return false;
        }

        for(var i=0;i<Zval.length;i++) {
            if(Zval[i].value == "" || Zval[i].value == null) {
                alert("子样本不能为空，请选择!");
                return false;
            }
        }

        var tempZ = "";
        var zBarcodeStr ="";
        for(var i=0;i<Zval.length;i++) {
            if(Zval[i].value == Fval || Zval[i].value == Mval || Fval == Mval ||  Zval[i].value == tempZ) {
                alert("比对样本不能重复，请选择!");
                return false;
            }
            tempZ = Zval[i].value;
            zBarcodeStr += tempZ + ",";
        }

        var relationCompare = GetRelativeCompare(zBarcodeStr);

        $.ajax({
            url : baseurl + "/center/realtionCompareTo",
            type:"post",
            contentType:  "application/json; charset=utf-8",
            data : JSON.stringify(relationCompare),
            dataType : "json",
            success : function(data) {
                if(data.length > 0) {
                    var $trs=$("tr","#newAddZTbodyId");
                    for(var i=0;i < $trs.length;i++){
                        var $tr = $trs[i];
                        $("input[name='totalPi']",$tr).val(data[i].totalPi);
                        $("input[name='matchCount']",$tr).val(data[i].matchCount);
                        $("input[name='matchModeName']",$tr).val(data[i].matchModeName);
                        $("input[name='singlePopulationID']",$tr).val( $("#relativePopulation").find("option:selected").val());
                        $("input[name='singleSameCount']",$tr).val( $(".relativeSameCount").val());
                        $("input[name='singleDiffCount']",$tr).val( $(".halfDiffCount").val());
                        $("input[name='singleMatchMode']",$tr).val(data[i].matchMode);
                        $("input[name='panelNameF']",$tr).val(data[i].panelNameF);
                        $("input[name='panelNameM']",$tr).val(data[i].panelNameM);
                        $("input[name='panelNameC']",$tr).val(data[i].panelNameC);
                        $("input[name='sampleNameF']",$tr).val(data[i].sampleNameF);
                        $("input[name='sampleNameM']",$tr).val(data[i].sampleNameM);
                        $("input[name='sampleNameC']",$tr).val(data[i].sampleNameC);
                        $("input[name='sampleNoF']",$tr).val(data[i].sampleNoF);
                        $("input[name='sampleNoM']",$tr).val(data[i].sampleNoM);
                        $("input[name='sampleNoC']",$tr).val(data[i].sampleNoC);
                        $("input[name='sampleIdF']",$tr).val(data[i].sampleIdF);
                        $("input[name='sampleIdM']",$tr).val(data[i].sampleIdM);
                        $("input[name='sampleNoC']",$tr).val(data[i].sampleNoC);
                    }
                }else {
                    alert("保存失败!");
                }
            },
            error: function(e){
                alert(e);
            }
        });

        $("#addCompareToResultId").prop("disabled",false);

    });
    // 先获取已有列表中最后一个下标值
    var indexNum = $("#raletionID").find("td[colspan='4']").last().prev().html()
    //添加
    var number;
    if (indexNum) {
        number = indexNum;
    }else{
        number = 0;
    }
    // var number=0;
    $("#addCompareToResultId").on("click", function(){
        var Zval = document.getElementsByName("ComparedToCaseSampleZ");
        for(var i=0;i<Zval.length;i++) {

            if(Zval[i].value == "" || Zval[i].value == null) {
                alert("子样本不能为空，请选择!");
                return false;
            }
        }

        var totalPi = document.getElementsByName("totalPi");
        for(var i=0;i<totalPi.length;i++) {

            if(totalPi[i].value == "" || totalPi[i].value == null) {
                alert("匹配概率不能为空，请选择!");
                return false;
            }
        }

        var matchCount = document.getElementsByName("matchCount");
        for(var i=0;i<matchCount.length;i++) {

            if(matchCount[i].value == "" || matchCount[i].value == null) {
                alert("匹配基因座数不能为空，请选择!");
                return false;
            }
        }

        var matchModeName = document.getElementsByName("matchModeName");
        for(var i=0;i<matchModeName.length;i++) {

            if(matchModeName[i].value == "" || matchModeName[i].value == null) {
                alert("比对模式不能为空，请选择!");
                return false;
            }
        }

        var $trs=$("tr","#newAddZTbodyId");
        for(var i=0;i<$trs.length;i++){

            var $tr=$trs[i];
            var matchMode = $("input[name='singleMatchMode']",$tr).val();
            var refrenceId = $("input[name='rcRadio']:checked").val();
            var relationF = "", relationM ="", relationC = "";
            if(refrenceId == '0')
                relationF = "(A)";
            else if(refrenceId == '1')
                relationM = "(A)";
            else if(refrenceId == '2')
                relationC = "(A)";

            var backgroudM = "", backgroudF = "";
            if(matchMode == '0' || matchMode == '4' || matchMode == '-1' || matchMode == '-2' ||matchMode == '-3') {
                backgroudM = "rgba(255, 0, 0, .5)";
                backgroudF = "rgba(255, 0, 0, .5)";
            }else if(matchMode == '1') {
                backgroudM = "rgba(0, 128, 0, .5)";
                backgroudF = "rgba(0, 128, 0, .5)";
            }else if(matchMode == '2') {
                backgroudM = "rgba(255, 0, 0, .5)";
                backgroudF = "rgba(0, 128, 0, .5)";
            }else if(matchMode == '3') {
                backgroudM = "rgba(0, 128, 0, .5)";
                backgroudF = "rgba(255, 0, 0, .5)";
            }

            var strTemp="";

            strTemp+="<tr><td>"+ (++number) + "</td>";
            strTemp+="<td colspan = '4'>"+ "<button type='button' class='btn-icon btn-icon-yellow btn-icon-chakan-red' name='viewMatchRelation' >查看明细</button>"+"</td>";
            strTemp+= "<td>"+	$("input[name='matchCount']",$tr).val()	+"</td>";
            strTemp+= "<td>"+	$("input[name='totalPi']",$tr).val() 	+"</td>";
            strTemp+= "<td>"+	$(".relativeSameCount").val()	+"</td>";
            strTemp+= "<td>"+	$("select[name='raceInfoSelectPopulation']").find("option:selected").text()	+"</td>";
            strTemp+= "</tr>";

            if($("#ComparedToCaseSampleFID").val() != "") {
                strTemp+="<tr><td></td>";
                strTemp+= "<td>" + $("#ComparedToCaseSampleFID").val() + "</td>";
                strTemp+= "<td>"+	$("input[name='sampleNameF']",$tr).val()	+"</td>";
                strTemp+= "<td style='background:" + backgroudF + "'>父" + relationF + "</td>";
                strTemp+= "<td>"+	$("input[name='panelNameF']",$tr).val()	+"</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td><label class=‘custom-control checkbox-inline’>";
                strTemp+= "<input class='custom-control-input' type='checkbox'>";
                strTemp+= "<span class='custom-control-label'>入库操作</span>";
                strTemp+= "</label></td>";
                // strTemp+= "<td><button type='button' class='btn-icon btn-icon-yellow btn-icon-chakan-yellow' " +
                //     "data-toggle='modal' data-target='#look'>查看</button>";
                // strTemp+= "</td>";
                strTemp+= "</tr>";
            }

            if($("#ComparedToCaseSampleMID").val() != "") {
                strTemp+="<tr><td></td>";
                strTemp+= "<td>" + $("#ComparedToCaseSampleMID").val() + "</td>";
                strTemp+= "<td>"+	$("input[name='sampleNameM']",$tr).val()	+"</td>";
                strTemp+= "<td style='background:" + backgroudM + "'>母 " + relationM + "</td>";
                strTemp+= "<td>"+	$("input[name='panelNameM']",$tr).val()	+"</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td>-- --</td>";
                strTemp+= "<td><label class=‘custom-control checkbox-inline’>";
                strTemp+= "<input class='custom-control-input' type='checkbox'>";
                strTemp+= "<span class='custom-control-label'>入库操作</span>";
                strTemp+= "</label></td>";
                // strTemp+= "<td><button type='button' class='btn-icon btn-icon-yellow btn-icon-chakan-yellow' " +
                //     "data-toggle='modal' data-target='#look'>查看</button>";
                // strTemp+= "</td>";
                strTemp+= "</tr>";
            }
            strTemp+="<tr><td></td>";
            strTemp+= "<td>" + $("select[name='ComparedToCaseSampleZ']",$tr).val() + "</td>";
            strTemp+= "<td>"+	$("input[name='sampleNameC']",$tr).val()	+"</td>";
            strTemp+= "<td>子" + relationC + "</td>";
            strTemp+= "<td>"+	$("input[name='panelNameC']",$tr).val()	+"</td>";
            strTemp+= "<td>-- --</td>";
            strTemp+= "<td>-- --</td>";
            strTemp+= "<td>-- --</td>";
            strTemp+= "<td><label class=‘custom-control checkbox-inline’>";
            strTemp+= "<input class='custom-control-input' type='checkbox'>";
            strTemp+= "<span class='custom-control-label'>入库操作</span>";
            strTemp+= "</label></td>";
            // strTemp+= "<td><button type='button' class='btn-icon btn-icon-yellow btn-icon-chakan-yellow' " +
            //     "data-toggle='modal' data-target='#look'>查看</button>";
            // strTemp+= "</td>";

            strTemp+= "<input type='hidden' name= 'caseId' value= " + $(".caseId").val() + ">";
            strTemp+= "<input type='hidden' name= 'fatherSampleNo' value=" + $("#ComparedToCaseSampleFID").val() +">";
            strTemp+= "<input type='hidden' name= 'motherSampleNo' value=" + $("#ComparedToCaseSampleMID").val() +">";
            strTemp+= "<input type='hidden' name= 'childSampleNo' value=" + $("select[name='ComparedToCaseSampleZ']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativeMatchCount' value=" + $("input[name='matchCount']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativeTotalProbability' value=" + $("input[name='totalPi']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativePopulationId' value=" + $("input[name='singlePopulationID']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativeReferenceId' value=" + $("input[name='rcRadio']:checked").val()  +">";
            strTemp+= "<input type='hidden' name= 'relativeSameCount' value=" +  $("input[name='singleSameCount']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativeDiffCount' value=" +  $("input[name='singleDiffCount']",$tr).val() +">";
            strTemp+= "<input type='hidden' name= 'relativeMatchMode' value=" +  $("input[name='singleMatchMode']",$tr).val() +">";
            strTemp+= "</tr>";

            $("#raletionID").append(strTemp);

            var relativeSameCount = $(".relativeSameCount").val();
            if(isNaN(relativeSameCount)){
                alert("请输入数字!");
                $(".relativeSameCount").val("");
                $(".relativeSameCount").focus();
                return false;
            }

            var compareRelativeResultArr = new Array();
            var compareRelativeResult = {};
            compareRelativeResult.caseId = $(".caseId").val();
            compareRelativeResult.fatherSampleNo = $("#ComparedToCaseSampleFID").val();
            compareRelativeResult.motherSampleNo = $("#ComparedToCaseSampleMID").val();
            compareRelativeResult.childSampleNo = $("select[name='ComparedToCaseSampleZ']",$tr).val();
            compareRelativeResult.compareGeneSum = $("input[name='matchCount']",$tr).val();
            compareRelativeResult.compareTotalProbability = $("input[name='totalPi']",$tr).val();
            compareRelativeResult.comparePopulationId = $("input[name='singlePopulationID']",$tr).val();
            compareRelativeResult.referenceId =$("input[name='rcRadio']:checked").val();
            compareRelativeResult.matchLimit = $(".relativeSameCount").val();
            compareRelativeResult.tolerance = $(".halfDiffCount").val();
            compareRelativeResult.matchMode = $("input[name='singleMatchMode']",$tr).val();
            compareRelativeResult.minSameCaseCount = $("input[name='singleSameCount']",$tr).val();
            compareRelativeResult.tolerance = $("input[name='singleDiffCount']",$tr).val();


            compareRelativeResultArr.push(compareRelativeResult);

            var compareRelativeResultList = compareRelativeResultArr;

            var params = {
                "minSameCaseCount": $(".minSameCaseCount").val(),
                "minSameMixCount": $(".minSameMixCount").val(),
                "relativeSameCount": $(".relativeSameCount").val(),
                "halfDiffCount": $(".halfDiffCount").val(),
                "compareRelativeResultList": compareRelativeResultList
            };

            $.ajax({
                url : baseurl + "/center/saveComparisonResult",
                type:"post",
                contentType:  "application/json; charset=utf-8",
                data : JSON.stringify(params),
                dataType : "json",
                success : function(data) {
                    var sameFlag = false;
                    if(data.sameFlag || data.sameFlag == true || data.sameFlag == "true") {
                        sameFlag = true;
                    }else {
                        alert("同一比对结果保存失败!");
                    }

                    var relationFlag = false;
                    if(data.relationFlag || data.relationFlag == true || data.relationFlag == "true") {
                        relationFlag = true;
                    }else {
                        alert("亲缘比对结果保存失败!");
                    }

                    if (sameFlag && relationFlag) {
                        var caseId = $(".caseId").val();
                        var consignmentId = $(".consignmentId").val();
                        var url = baseurl + "/center/enterCaseCompare?caseId="+ caseId + "&consignmentId=" + consignmentId
                            + "&matchLimit=" + matchLimit.trim();
                        location.href=url;

                    }
                },
                error: function(e){
                    alert(e);
                }
            });

        }

        $("#relationCount").html(number);

        $("tr:gt(0)","#newAddZTbodyId").remove();
        $('#ComparedToCaseSampleFID').val("");
        $('#ComparedToCaseSampleMID').val("");
        $('#ComparedToCaseSampleZID').val("");
        $("input[name='totalPi']").val("");
        $("input[name='matchCount']").val("");
        $("input[name='matchModeName']").val("");
        $("#addCompareToResultId").prop("disabled", true);

    });

    function GetRelativeCompare (zBarcodeStr) {
        var relationCompare = {};

        relationCompare.matchRelationLimit = $(".relativeSameCount").val();
        relationCompare.halfDiffCount = $(".halfDiffCount").val();
        relationCompare.populationName = $("#relativePopulation").find("option:selected").text();
        relationCompare.populationId = $("#relativePopulation").find("option:selected").val();
        relationCompare.refrenceId = $("input[name='rcRadio']:checked").val();
        relationCompare.fatherSampleNo = $("select[name='ComparedToCaseSampleF']").find("option:selected").val();
        relationCompare.motherSampleNo = $("select[name='ComparedToCaseSampleM']").find("option:selected").val();
        relationCompare.childSampleNoStr = zBarcodeStr;

        return relationCompare;
    }
});
