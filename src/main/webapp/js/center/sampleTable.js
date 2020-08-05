//添加检材
function addSampleBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    var boardNo = $("input[name='boardNo']", $(obj).parent().parent()).val();
    var elution = $("input[name='elutionDefault']", $(obj).parent().parent()).val();
    var elutionName = $("input[name='elutionDefaultName']", $(obj).parent().parent()).val();
    var holeNum = $("input[name='holeNum']", $(obj).parent().parent()).val();
    location.href = "../../center/sample/addSample.html?entity.sampleTableId=" + id
        + "&entity.boardNo=" + boardNo+ "&entity.elution=" + elution + "&holeNum=" + holeNum + "&elutionName=" + elutionName;
}
//导出24孔样本表
function exportBtn(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    location.href = "../../center/sample/exportSampleTable.html?entity.sampleTableId=" + id;
}

$(function(){
    'use strict';

    //导入文件
    $("#browserBtn").on('click', function () {
        $("#upFile").click();
    })

    //导入文件
    $("#saveSampleTableBtn").on('click', function () {
        var elutionModel = $("select[name='elutionModel']", "#importSampleTableModal").val();
        if (elutionModel == "") {
            $("#modelContent").text("洗脱体积不能为空！");
            $("#msg-model").modal();
            return false;
        }

        var fileValue = $("#textfield").val();
        if (fileValue == "") {
            $("#modelContent").text("请选择上传文件！");
            $("#msg-model").modal();
            return false;
        }

        checkFileSuffix();

        $.ajaxFileUpload({
            cache:false,
            url:"../../center/sample/uploadSampleTable.html?elution=" + elutionModel,
            secureuri:false,
            fileElementId:["upFile","upFile"],
            type : 'post',
            dataType: 'json',
            success: function (data) {
                if(data.success || data.success == true || data.success == "true") {
                    $("#importSampleTableModal").modal('hide');
                    $().getList();
                }else{
                    $("#modelContent").text("上传失败！");
                    $("#msg-model").modal();
                }
            },
            error: function (data,status,e) {
                alert(e);
            }
        });
    })

    //检查上传文件后缀
    $("#upFile").change(function () {
        $("#textfield").val($(this).val());
        checkFileSuffix();
    });

    //检查多个上传文件后缀
    function checkFileSuffix() {
        var fileName = "";
        var str = document.getElementById("upFile");
        for(var i=0; i< str.files.length;i++){
            fileName = str.files[i].name;
            //获得文件后缀名
            var photoExt = fileName.substr(fileName.lastIndexOf(".")).toLowerCase();
            if (photoExt == '.xls' || photoExt == '.xlsx') {

            } else {
                $("#modelContent").text("请上传后缀名为xls或xlsx的文件！");
                $("#msg-model").modal();
                $("#textfield").val("");
                return false;
            }
        }

        return true;
    }

    //没有创建样本板时，还原样本板号
    $("#closeSampleBtn").on('click', function () {
        var sampleBoardNo = $("#sampleBoardNo").val();
        var type = $("#content").text();
        console.log(type);
        var flag;
        if (type=="修改样本板"){
           flag="modify";
        }else {
            flag="create";
        }

        $.ajax({
            url:"../../center/sample/createOrReturnNewBoardNo.html?operate=return"+"&flag="+flag,
            type:"post",
            data:{},
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            async : false,
            success: function (data) {
                if(data.success){
                    $("#sampleModal").modal('hide');
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    })

    $("select[name='deviceTypeModel']").change(function(){
        var deviceType = $("select[name='deviceTypeModel'] :checked").val();
        if (deviceType == '24道') {
            $("input[name='kitNameModel']").val("BK试剂盒");
        }
        if (deviceType == '96道') {
            $("input[name='kitNameModel']").val("WD试剂盒");
        }
    });

    //创建DNA提取表
    $("#createSampleTableBtn").on("click", function (){
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要提交的板！");
            $("#msg-model").modal();
            return false;
        }
        $("input[name='isSpell']").val("NO");
        $("#extractModal").modal('show');
    });
    //拼板并创建DNA提取表
    $("#spellCreateSampleTableBtn").on("click", function (){
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

        $("input[name='isSpell']").val("YES");
        $("#extractModal").modal('show');
    });

    function isPass96() {
        var count = 0;
        $("tr", "#sampleTableVoListTbody").each(function () {
            var checkBox = $("input[name='allSampleTableBox']", $(this)).is(":checked");
            if (checkBox) {
                var holeNum = $("input[name='holeNum']", $(this)).val();
                count = count + Number(holeNum);
            }
        });

        return count;
    }

    //创建提取
    $("#createExtractBtn").on("click", function (){
        $('.active-ul li').each(function(key,val){
            if ($(this).index() == 1) {
                $(this).addClass('active-li').siblings().removeClass('active-li');
                $("#display-sy01").hide();
                $("#display-sy02").show();
                $("#display-sy03").hide();
                $("#display-sy04").hide();
                $("#textBlock").show();
                $("#btnBlock").hide();
                $("#tabValue").val("extractTab");
            }
        });
        createExtractPlate($("input[name='isSpell']").val());
    });

    function createExtractPlate(obj) {
        $.ajax({
            url:"../../center/sample/spellOrCreateExtractList.html?isSpell=" + obj,
            type:"post",
            data:JSON.stringify(extractPlate()),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    alert("提交成功。");
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

    function extractPlate() {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#sampleTableVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='allSampleTableBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.sampleTableId = $("input[name='id']", $(this)).val();
                sample.entity = entity();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

    function entity() {
        var entity = {};

        entity.kitName = $("input[name='kitNameModel']").val();
        entity.deviceType = $("select[name='deviceTypeModel'] :checked").val();

        return entity;
    }

    //跳转添加检材页面
    $(".addSampleBtn", "#sampleTableVoListTbody").on("click", function () {
        var id = $("input[name='id']", $(this).parent().parent()).val();
        var boardNo = $("input[name='boardNo']", $(this).parent().parent()).val();
        var elution = $("input[name='elutionDefault']", $(this).parent().parent()).val();
        var elutionName = $("input[name='elutionDefaultName']", $(this).parent().parent()).val();
        var holeNum = $("input[name='holeNum']", $(this).parent().parent()).val();
        location.href = "../../center/sample/addSample.html?entity.sampleTableId=" + id
            + "&entity.boardNo=" + boardNo+ "&entity.elution=" + elution + "&holeNum=" + holeNum + "&elutionName=" + elutionName;
    });

    //导出样本表
    $(".exportBtn", "#sampleTableVoListTbody").on("click", function () {
        var id = $("input[name='id']", $(this).parent().parent()).val();
        location.href = "../../center/sample/exportSampleTable.html?entity.sampleTableId=" + id;
    });

    function sampleTable(obj) {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#sampleTableVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='allSampleTableBox']", $(this)).is(":checked");

            if (checkBox) {
                sample.id = $("input[name='id']", $(this)).val();
                sample.boardNo = $("input[name='boardNo']", $(this)).val();
                sample.holeNum = $("input[name='holeNum']", $(this)).val();
                if (obj == "multiple") {
                    sample.elutionDefault = $("select[name='elutionMultipleModel'] :checked").val();
                }else {
                    sample.elutionDefault =  $("input[name='elutionDefault']", $(this)).val();
                }
                sample.createPerson = $("input[name='createPerson']", $(this)).val();
                sample.createDatetime = $("input[name='createDatetime']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

    function checkCount() {
        var checkCount = 0;
        $("tr", "#sampleTableVoListTbody").each(function () {
            var checkBox = $("input[name='allSampleTableBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }
   /* function checkIsCreate() {
        var checkCount = 1;
        $("tr", "#sampleTableVoListTbody").each(function () {
            /!*var checkBox = $("input[name='allSampleTableBox']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }*!/
            /!*var x=  $("input[name='isCreate']", $(this)).value();
            console.log(x);*!/
            /!*var x=  document.getElementById("isCreate");//获取提取表名称是否为空
            console.log(x);*!/
           /!* if (x==null||x==""){
                checkCount=0;
            }*!/
        });

        return checkCount;
    }*/

    function confirmOperation (obj) {
        $("#confirmContent").text(obj);
        $("#confirm-model").modal();
    }

    $("#confirmBtn").on("click", function (){
        $.ajax({
            url:"../../center/sample/deleteSampleTable.html",
            type:"post",
            data:JSON.stringify(sampleTable("single")),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    //location.href = "../../center/sample/sampleTableList.html";
                    $().getList();
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    });

    //删除样本板
    $("#deleteSampleTableBtn").on("click", function (){
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要删除的板！");
            $("#msg-model").modal();
            return false;
        }

        confirmOperation("确认删除吗?");
    });

    //修改样本板
    $("#editSampleTableBtn").on("click", function () {
        edit();
    });

    function edit() {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要修改的板！");
            $("#msg-model").modal();
            return false;
        }

        $("input[name='boardNoModel']", "#sampleModal").attr("readonly","readonly");

        var sampleArr = new Array();
        if (checkCount() == 1) {
            sampleArr = sampleTable("single");
            sampleRow(sampleArr[0], "edit");
        }else {
            $("#sampleMultipleModal").modal('show');
        }
    }
    //导入样本板
    $("#importSampleTableBtn").on("click", function () {
        $("select[name='elutionModel']", "#importSampleTableModal").val("");
        $("#textfield").val("");
        $("#upFile").val("");
        $("#importSampleTableModal").modal('show');
    });

    //创建样本板
    $("#addSampleTableBtn").on("click", function () {
        addSampleRow("add");
        $($("input[name='boardNoModel']")).removeAttr("readonly");
    });

    function addSampleRow(obj) {
        var sample = {};
        sample.id = "";
        getNewBoardNo();
        sample.boardNo = $("#sampleBoardNo").val();
        sample.holeNum = "";
        sample.elutionDefault = "";
        sample.createPerson = $("input[name='loginName']").val();
        sample.createDatetime = formatDateTime;
        sampleRow(sample, "add");
    }

    function getNewBoardNo(){
        $.ajax({
            url:"../../center/sample/createOrReturnNewBoardNo.html?operate=create"+"&flag="+"create",
            type:"post",
            data:{},
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            async : false,
            success: function (data) {
                if(data.success){
                    $("#sampleBoardNo").val(data.boardNo);
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    $("input[name='boardNoModel']").on('blur',function(){
        var operateType = $("input[name='sampleOperateType']", "#sampleModal").val();
        if (operateType == "add") {
            isExist();
        }
    })

    function isExist () {
        var boardNo = $("input[name='boardNoModel']").val();
        var boardNoArr = $("input[name='boardNo'][value='"+boardNo+"']","#sampleTableVoListTbody");

        if(boardNoArr.length > 0) {
            $("#modelContent").text("此板名已存在，请重新填写！");
            $("#msg-model").modal();
            $("input[name='boardNoModel']").val(nowDate() + "-");
            $("input[name='boardNoModel']").focus();
            return false;
        }
    }
    //获取当前日期转换成年月日
    function nowDate() {
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var day = date.getDate();
        if (month < 10) {
            month = "0" + month;
        }
        if (day < 10) {
            day = "0" + day;
        }
        return year + "-" + month + "-" + day;
    }

    var formatDateTime = function () {
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h=h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        minute = minute < 10 ? ('0' + minute) : minute;
        var second=date.getSeconds();
        second=second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };

    function sampleRow(sample, operateType) {
        $("div.has-error", "#sampleModal").addClass("hide");
        $("input[name='boardNoModel']", "#sampleModal").val(sample.boardNo);
        if (sample.holeNum == "") {
            $("select[name='holeNumModel']", "#sampleModal").prop('selectedIndex', 0);
        } else {
            $("select[name='holeNumModel']", "#sampleModal").val(sample.holeNum);
        }
        if (sample.elutionDefault == "") {
            $("select[name='elutionDefaultModel']", "#sampleModal").prop('selectedIndex', 3);
        } else {
            $("select[name='elutionDefaultModel']", "#sampleModal").val(sample.elutionDefault);
        }
        $("input[name='createPersonModel']", "#sampleModal").val(sample.createPerson);
        $("input[name='createDatetimeModel']", "#sampleModal").val(sample.createDatetime);
        $("input[name='sampleOperateType']", "#sampleModal").val(operateType);
        $("input[name='idModel']", "#sampleModal").val(sample.id);

        if (operateType == "add") {
            $("#content").text("创建样本板");
        }else {
            $("#content").text("修改样本板");
        }
        $("#sampleModal").modal('show');
    }

    //保存单个板信息
    $("#saveSampleBtn").on("click", function () {
        if(!checkInputValids()) {
            return false;
        }
        saveSampleTable(getParams());

    });

    //保存多个个板信息
    $("#saveMultipleBtn").on("click", function () {
        saveSampleTable(sampleTable("multiple"));
    });

    function saveSampleTable(params) {
        $.ajax({
            url:"../../center/sample/saveSampleTable.html",
            type:"post",
            data:JSON.stringify(params),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    //location.href = "../../center/sample/sampleTableList.html";
                    $("#sampleModal").modal('hide');
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

    function getParams(){
        var sampleArr = new Array();
        var sampleTable = {};

        sampleTable.id = $("input[name='idModel']").val();
        sampleTable.boardNo = $("input[name='boardNoModel']").val();
        sampleTable.holeNum = $("select[name='holeNumModel'] :checked").val();
        sampleTable.elutionDefault = $("select[name='elutionDefaultModel'] :checked").val();
        sampleTable.createPerson = $("input[name='createPersonModel']").val();
        sampleTable.createDatetime = $("input[name='createDatetimeModel']").val();

        sampleArr.push(sampleTable);

        return sampleArr;
    }
//提取时导出CSV文件
    $("#exportCSV2").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要导出的板！");
            $("#msg-model").modal();
            return false;
        }
        /*if (checkIsCreate() <= 0) {
            $("#modelContent").text("已提交提取任务才支持导出CSV！");
            $("#msg-model").modal();
            return false;
        }*/

        exportExtract2("csv");
    });
    //导出csv
    function exportExtract2(obj) {
        var recordExtractArr = new Array();
        recordExtractArr = extractPlate();
        console.log(recordExtractArr);
        var listLength = recordExtractArr.length;
        if (listLength > 0) {
            var extractId = "";
            for (var i = 0; i < listLength; i++) {
                extractId = recordExtractArr[i].sampleTableId;
                if (extractId !== null || extractId !== 'undefined' || extractId !== '') {
                    if (obj == "csv") {
                        var url = "../../center/sample/exportCSVFile2.html?entity.extractPlateId=" + extractId;
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
    //判断必填项
    function checkInputValids(){
        var boardNoModel = $("input[name='boardNoModel']").val();
        if(boardNoModel == ""){
            $("#modelContent").text("请输入板名！");
            $("#msg-model").modal();
            $("input[name='boardNoModel']").focus();
            return false;
        }
        var holeNumModel = $("select[name='holeNumModel']").val();
       /* if(holeNumModel == ""){
            $("#modelContent").text("请选择孔位！");
            $("#msg-model").modal();
            $("select[name='boardNoModel']").focus();
            return false;
        }*/
        var elutionDefaultModel = $("select[name='elutionDefaultModel']").val();
        if(elutionDefaultModel == ""){
            $("#modelContent").text("请选择默认体积！");
            $("#msg-model").modal();
            $("select[name='elutionDefaultModel']").focus();
            return false;
        }
        var createPersonModel = $("input[name='createPersonModel']").val();
        if(createPersonModel == ""){
            $("#modelContent").text("请输入创建者！");
            $("#msg-model").modal();
            $("input[name='createPersonModel']").focus();
            return false;
        }
        var createDatetimeModel = $("input[name='createDatetimeModel']").val();
        if(createDatetimeModel == ""){
            $("#modelContent").text("请输入创建时间！");
            $("#msg-model").modal();
            $("input[name='createDatetimeModel']").focus();
            return false;
        }

        return true;
    }

    laydate.render({
        elem: '.form_datetime', //指定元素
        showBottom: false
    });
});

