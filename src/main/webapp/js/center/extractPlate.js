//删除检材
function deleteExtractBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    $("#identification").val("1");
    $("#param1").val(id);
    $().confirmExtractOperation("确认删除吗?");
}
//查看
function viewExtractBtn(obj) {

    var id = $("input[name='id']", $(obj).parent().parent()).val();
    var boardNo = $("input[name='boardNo']", $(obj).parent().parent()).val();
    var state = $("input[name='state']", $(obj).parent().parent()).val();
    var holeNum = $("input[name='holeNum']", $(obj).parent().parent()).val();
    var kitName = $("input[name='kitName']", $(obj).parent().parent()).val();
    var deviceType = $("input[name='deviceType']", $(obj).parent().parent()).val();
    var extractMethod = $("input[name='extractMethod']", $(obj).parent().parent()).val();
    var extractDatetime = $("input[name='extractDatetime']", $(obj).parent().parent()).val();
    var pcrPlateName = $("input[name='pcrPlateName']", $(obj).parent().parent()).val();
    var createPerson = $("input[name='createPerson']", $(obj).parent().parent()).val();
    var createDatetime = $("input[name='createDatetime']", $(obj).parent().parent()).val();

    location.href = "../../center/sample/viewSampleBoard.html?entity.extractPlateId=" + id
        + "&boardNoName=" + boardNo + "&entity.elution=03&elutionName=50&tabValue=extractTab&stage=tq&holeNum="+holeNum;
    /*$("span[name='boardNoModelExt']", "#sampleExtractModal").text(isFullReturn(boardNo));
    var stateName = "";
    if (state == "0") {
        stateName = "未开始";
    }else if (state == "1") {
        stateName = "开始";
    }else if (state == "2") {
        stateName = "结束";
    }
    $("span[name='stateModelExt']", "#sampleExtractModal").text(isFullReturn(stateName));
    $("span[name='holeNumModelExt']", "#sampleExtractModal").text(isFullReturn(holeNum));
    $("span[name='kitNameModelExt']", "#sampleExtractModal").text(isFullReturn(kitName));
    $("span[name='deviceTypeModelExt']", "#sampleExtractModal").text(isFullReturn(deviceType));
    $("span[name='extractMethodModelExt']", "#sampleExtractModal").text(isFullReturn(extractMethod));
    $("span[name='extractDatetimeModelExt']", "#sampleExtractModal").text(isFullReturn(extractDatetime));
    $("span[name='pcrPlateNameModelExt']", "#sampleExtractModal").text(isFullReturn(pcrPlateName));
    $("span[name='createPersonModelExt']", "#sampleExtractModal").text(isFullReturn(createPerson));
    $("span[name='createDatetimeModelExt']", "#sampleExtractModal").text(isFullReturn(createDatetime));

    $("#sampleExtractModal").modal('show');*/
}

$(function(){
    'use strict';

    //删除样本板
    $("#deleteExtractBtn").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要删除的板！");
            $("#msg-model").modal();
            return false;
        }
        $("#identification").val("2");
        $().confirmExtractOperation("确认删除吗?");
    });

    $.fn.confirmExtractOperation = function (obj) {
        $("#confirmContent").text(obj);
        $("#confirm-model").modal();
    }

    $("#confirmBtn").on("click", function (){
        var identification = $("#identification").val();
        if (identification == "1") {
            var sampleArr = new Array();
            var sample = {};
            sample.id = $("#param1").val();
            sampleArr.push(sample);

            deleteExtractPlate(sampleArr);
        }else if (identification == "2") {
            deleteExtractPlate(extractPlate());
        }
    });

    function checkCount() {
        var checkCount = 0;
        $("tr", "#extractPlateVoListTbody").each(function () {
            var checkBox = $("input[name='extractBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }
    function checkCount2() {
        var checkCount = 0;
        $("tr", "#sampleTableVoListTbody").each(function () {
            var checkBox = $("input[name='labelBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }

    function extractPlate() {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#extractPlateVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='extractBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.id = $("input[name='id']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }
    function deleteExtractPlate(obj) {
        $.ajax({
            url:"../../center/sample/deleteExtractPlate.html",
            type:"post",
            data:JSON.stringify(obj),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    //location.href = "../../center/sample/extractPlateList.html";
                    $().getList();
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    //导出CSV文件
    $("#exportCSV").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportExtract("csv");
    });
    //导出上样文件
    $("#exportSampleFile").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportExtract("sampleFile");
    });
    //导出DNA提取样本表
    $("#exportExtractSampleTable").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportExtract("table");
    });
    //导出提取样本记录表
    $("#exportExtractSampleRecord").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportExtract("record");
    });

    function exportExtract(obj) {
        var record;
        //提取
        var recordExtractArr = new Array();
        recordExtractArr = extractPlate();

        var listLength = recordExtractArr.length;
        if (listLength > 0) {
            var extractId = "";
            for (var i = 0; i < listLength; i++) {
                extractId = recordExtractArr[i].id;
                if (extractId !== null || extractId !== 'undefined' || extractId !== '') {
                    if (obj == "csv") {
                        var url = "../../center/sample/exportCSVFile.html?entity.extractPlateId=" + extractId;
                    }else if (obj == "sampleFile") {
                        var url = "../../center/sample/exportSampleFile.html?entity.extractPlateId=" + extractId;
                    }else if (obj == "table") {
                        var url = "../../center/sample/exportExtractSampleTable.html?entity.extractPlateId=" + extractId;
                    }else if (obj == "record"){
                        var url = "../../center/sample/exportExtractSampleRecord.html?entity.extractPlateId=" + extractId;
                    }
                    generateRecord(url);
                }
            }
        }
    }

    function generateRecord(url) {
        var fileFrame = document.createElement("iframe");
        fileFrame.src = url;//文件路径
        fileFrame.style.display = "none";
        document.body.appendChild(fileFrame);
    }

    //创建扩增表
    $("#createPcrBtn").on("click", function (){
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要创建的板！");
            $("#msg-model").modal();
            return false;
        }
        tabSwitch();
        createPcrPlate("NO");
    });
    //拼板并创建扩增表
    $("#spellCreatePcrBtn").on("click", function (){
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要拼的板！");
            $("#msg-model").modal();
            return false;
        }
        if (checkCount() == 1) {
            $("#modelContent").text("至少选择2个板拼！");
            $("#msg-model").modal();
            return false;
        }

        if (isPass96() > 96) {
            $("#modelContent").text("拼板孔数大于96！");
            $("#msg-model").modal();
            return false;
        }

        tabSwitch();
        createPcrPlate("YES");
    });

    function isPass96() {
        var count = 0;
        $("tr", "#extractPlateVoListTbody").each(function () {
            var checkBox = $("input[name='extractBox']", $(this)).is(":checked");
            if (checkBox) {
                var holeNum = $("input[name='holeNum']", $(this)).val();
                count = count + Number(holeNum);
            }
        });

        return count;
    }

    function tabSwitch () {
        $('.active-ul li').each(function(key,val){
            if ($(this).index() == 2) {
                $(this).addClass('active-li').siblings().removeClass('active-li');
                $("#display-sy01").hide();
                $("#display-sy02").hide();
                $("#display-sy03").show();
                $("#display-sy04").hide();
                $("#textBlock").show();
                $("#btnBlock").hide();
                $("#tabValue").val("pcrTab");
            }
        });
    }

    function createPcrPlate(obj) {
        $.ajax({
            url:"../../center/sample/spellOrCreatePcrList.html?isSpell=" + obj,
            type:"post",
            data:JSON.stringify(pcrPlate()),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    //location.href = "../..//center/sample/pcrPlateList.html";
                    $().getList();
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    function pcrPlate() {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#extractPlateVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='extractBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.extractPlateId = $("input[name='id']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

});

