//删除检材
function deletePcrBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    $("#identification").val("1");
    $("#param1").val(id);
    $().confirmPcrOperation("确认删除吗?");
}
//查看
function viewPcrBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    var boardNo = $("input[name='boardNo']", $(obj).parent().parent()).val();
    var state = $("input[name='state']", $(obj).parent().parent()).val();
    var holeNum = $("input[name='holeNum']", $(obj).parent().parent()).val();
    var pcrDatetime = $("input[name='pcrDatetime']", $(obj).parent().parent()).val();
    var testSystem = $("input[name='testSystem']", $(obj).parent().parent()).val();
    var pcrSystemTrace = $("input[name='pcrSystemTrace']", $(obj).parent().parent()).val();
    var pcrSystemConstant = $("input[name='pcrSystemConstant']", $(obj).parent().parent()).val();
    var pcrRunNum = $("input[name='pcrRunNum']", $(obj).parent().parent()).val();
    var reagentBatch = $("input[name='reagentBatch']", $(obj).parent().parent()).val();
    var pcrInstrumentNum = $("input[name='pcrInstrumentNum']", $(obj).parent().parent()).val();
    var createPerson = $("input[name='createPerson']", $(obj).parent().parent()).val();
    var createDatetime = $("input[name='createDatetime']", $(obj).parent().parent()).val();

    location.href = "../../center/sample/viewSampleBoard.html?entity.pcrPlateId=" + id
        + "&boardNoName=" + boardNo + "&entity.elution=03&elutionName=50&tabValue=pcrTab&stage=kz&holeNum="+holeNum;
   /* $("span[name='boardNoModelPcr']", "#samplePcrModal").text(isFullReturn(boardNo));
    var stateName = "";
    if (state == "0") {
        stateName = "未开始";
    }else if (state == "1") {
        stateName = "开始";
    }else if (state == "2") {
        stateName = "结束";
    }
    $("span[name='stateModelPcr']", "#samplePcrModal").text(isFullReturn(stateName));
    $("span[name='pcrDatetimeModelPcr']", "#samplePcrModal").text(isFullReturn(pcrDatetime));
    $("span[name='testSystemModelPcr']", "#samplePcrModal").text(isFullReturn(testSystem));
    $("span[name='pcrSystemTraceModelPcr']", "#samplePcrModal").text(isFullReturn(pcrSystemTrace));
    $("span[name='pcrSystemConstantModelPcr']", "#samplePcrModal").text(isFullReturn(pcrSystemConstant));
    $("span[name='pcrRunNumModelPcr']", "#samplePcrModal").text(isFullReturn(pcrRunNum));
    $("span[name='reagentBatchModelPcr']", "#samplePcrModal").text(isFullReturn(reagentBatch));
    $("span[name='pcrInstrumentNumModelPcr']", "#samplePcrModal").text(isFullReturn(pcrInstrumentNum));
    $("span[name='createPersonModelPcr']", "#samplePcrModal").text(isFullReturn(createPerson));
    $("span[name='createDatetimeModelPcr']", "#samplePcrModal").text(isFullReturn(createDatetime));

    $("#samplePcrModal").modal('show');*/
}

$(function(){
    'use strict';

    //导出PCR扩增样本表
    $("#exportPcrSampleRecord").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportPcr("record");
    });

    //导出DNA检验记录扩增作业单
    $("#exportPcrWork").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        exportPcr("work");
    });

    function exportPcr(obj) {
        var record;
        var recordPcrArr = new Array();
        recordPcrArr = pcrPlate();

        var listLength = recordPcrArr.length;
        if (listLength > 0) {
            var pcrId = "";
            for (var i = 0; i < listLength; i++) {
                pcrId = recordPcrArr[i].id;
                if (pcrId !== null || pcrId !== 'undefined' || pcrId !== '') {
                    if (obj == "work") {
                        var url = "../../center/sample/exportWorkFile.html?entity.pcrPlateId=" + pcrId;
                    }else if (obj == "record"){
                        var url = "../../center/sample/exportPcrSampleRecord.html?entity.pcrPlateId=" + pcrId;
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

    //删除扩增信息
    $("#deletePcrBtn").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要删除的板！");
            $("#msg-model").modal();
            return false;
        }
        $("#identification").val("2");
        $().confirmPcrOperation("确认删除吗?");
    });

    function checkCount() {
        var checkCount = 0;
        $("tr", "#pcrPlateVoListTbody").each(function () {
            var checkBox = $("input[name='pcrBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }

    $.fn.confirmPcrOperation = function (obj) {
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

            deletePcrPlate(sampleArr);
        }else if (identification == "2") {
            deletePcrPlate(pcrPlate());
        }
    });

    function deletePcrPlate(obj) {
        $.ajax({
            url:"../../center/sample/deletePcrPlate.html",
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

    function pcrPlate() {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#pcrPlateVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='pcrBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.id = $("input[name='id']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

    //创建电泳表
    $("#createSyBtn").on("click", function (){
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要创建的板！");
            $("#msg-model").modal();
            return false;
        }
        tabSwitch ();
        createSyPlate("NO");
    });

    //拼板并创建扩增表
    $("#spellCreateSyBtn").on("click", function (){
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
        createSyPlate("YES");
    });

    function isPass96() {
        var count = 0;
        $("tr", "#pcrPlateVoListTbody").each(function () {
            var checkBox = $("input[name='pcrBox']", $(this)).is(":checked");
            if (checkBox) {
                var holeNum = $("input[name='holeNum']", $(this)).val();
                count = count + Number(holeNum);
            }
        });

        return count;
    }

    function tabSwitch () {
        $('.active-ul li').each(function(key,val){
            if ($(this).index() == 3) {
                $(this).addClass('active-li').siblings().removeClass('active-li');
                $("#display-sy01").hide();
                $("#display-sy02").hide();
                $("#display-sy03").hide();
                $("#display-sy04").show();
                $("#textBlock").hide();
                $("#btnBlock").show();
                $("#tabValue").val("syTab");
            }
        });
    }

    function createSyPlate(obj) {
        $.ajax({
            url:"../../center/sample/spellOrCreateSyList.html?isSpell=" + obj,
            type:"post",
            data:JSON.stringify(syPlate()),
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
        $("tr", "#pcrPlateVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='pcrBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.pcrPlateId = $("input[name='id']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

});

