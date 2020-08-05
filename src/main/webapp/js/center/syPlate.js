//删除检材
function deleteSyBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    $("#identification").val("1");
    $("#param1").val(id);
    $().confirmSyOperation("确认删除吗?");
}
//查看
function viewSyBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    var boardNo = $("input[name='boardNo']", $(obj).parent().parent()).val();
    var testDatetime = $("input[name='testDatetime']", $(obj).parent().parent()).val();
    var state = $("input[name='state']", $(obj).parent().parent()).val();
    var holeNum = $("input[name='holeNum']", $(obj).parent().parent()).val();
    var firstInstrumentNum = $("input[name='firstInstrumentNum']", $(obj).parent().parent()).val();
    var molecularWeightMarker = $("input[name='molecularWeightMarker']", $(obj).parent().parent()).val();
    var mixingRatio = $("input[name='mixingRatio']", $(obj).parent().parent()).val();
    var sySystem = $("input[name='sySystem']", $(obj).parent().parent()).val();
    var environmentTemperature = $("input[name='environmentTemperature']", $(obj).parent().parent()).val();
    var denaturationCondition = $("input[name='denaturationCondition']", $(obj).parent().parent()).val();
    var createPerson = $("input[name='createPerson']", $(obj).parent().parent()).val();
    var createDatetime = $("input[name='createDatetime']", $(obj).parent().parent()).val();

    location.href = "../../center/sample/viewSampleBoard.html?entity.syPlateId=" + id
        + "&boardNoName=" + boardNo + "&entity.elution=03&elutionName=50&tabValue=syTab&stage=sy&holeNum="+holeNum;
   /* $("span[name='boardNoModelSy']", "#sampleSyModal").text(isFullReturn(boardNo));
    $("span[name='testDatetimeModelSy']", "#sampleSyModal").text(isFullReturn(testDatetime));
    var stateName = "";
    if (state == "0") {
        stateName = "未开始";
    }else if (state == "1") {
        stateName = "开始";
    }else if (state == "2") {
        stateName = "结束";
    }
    $("span[name='stateModelSy']", "#sampleSyModal").text(isFullReturn(stateName));
    $("span[name='firstInstrumentNumModelSy']", "#sampleSyModal").text(isFullReturn(firstInstrumentNum));
    $("span[name='molecularWeightMarkerModelSy']", "#sampleSyModal").text(isFullReturn(molecularWeightMarker));
    $("span[name='mixingRatioModelSy']", "#sampleSyModal").text(isFullReturn(mixingRatio));
    $("span[name='sySystemModelSy']", "#sampleSyModal").text(isFullReturn(sySystem));
    $("span[name='environmentTemperatureModelSy']", "#sampleSyModal").text(isFullReturn(environmentTemperature));
    $("span[name='denaturationConditionModelSy']", "#sampleSyModal").text(isFullReturn(denaturationCondition));
    $("span[name='createPersonModelSy']", "#sampleSyModal").text(isFullReturn(createPerson));
    $("span[name='createDatetimeModelSy']", "#sampleSyModal").text(isFullReturn(createDatetime));

    $("#sampleSyModal").modal('show');*/
}

$(function(){
    'use strict';

    //导出STR电泳样本表
    $("#exportSySampleRecord").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportSy("record");
    });

    function exportSy(obj) {
        var record;
        var recordSyArr = new Array();
        recordSyArr = syPlate();

        var listLength = recordSyArr.length;
        if (listLength > 0) {
            var syId = "";
            for (var i = 0; i < listLength; i++) {
                syId = recordSyArr[i].id;
                if (syId !== null || syId !== 'undefined' || syId !== '') {
                    if (obj == "record"){
                        var url = "../../center/sample/exportSySampleRecord.html?entity.syPlateId=" + syId;
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
    //保存电泳信息
    $("#saveSyBtn").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要保存的板！");
            $("#msg-model").modal();
            return false;
        }
        saveSyPlate(syPlate());
    });

    function saveSyPlate(params) {
        $.ajax({
            url:"../../center/sample/saveSyPlate.html",
            type:"post",
            data:JSON.stringify(params),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
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

    //删除扩增信息
    $("#deleteSyBtn").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要删除的板！");
            $("#msg-model").modal();
            return false;
        }
        $("#identification").val("2");
        $().confirmSyOperation("确认删除吗?");
    });

    function checkCount() {
        var checkCount = 0;
        $("tr", "#syPlateVoListTbody").each(function () {
            var checkBox = $("input[name='syBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }

    $.fn.confirmSyOperation = function (obj) {
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

            deleteSyPlate(sampleArr);
        }else if (identification == "2") {
            deleteSyPlate(syPlate());
        }
    });


    function deleteSyPlate(obj) {
        $.ajax({
            url:"../../center/sample/deleteSyPlate.html",
            type:"post",
            data:JSON.stringify(obj),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
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

    function syPlate() {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#syPlateVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='syBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.id = $("input[name='id']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

});

