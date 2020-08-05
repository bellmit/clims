function deleteClick(obj) {
    var id = $("input[name='id']", $(obj).parent().parent()).val();
    var sampleLocationSort = $("input[name='sampleLocationSort']", $(obj).parent().parent()).val();
    $("#identification").val("3");
    $("#param1").val(id);
    $("#param2").val(sampleLocationSort);
    $().confirmOperationSample("确认删除吗?");
}

function deletePlate(obj, str) {
    $.ajax({
        url:"../../center/sample/deleteSampleInfo.html",
        type:"post",
        data:JSON.stringify(obj),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        success: function (data) {
            if(data.success){
//                        document.location.reload();
                /*var position;
                var holeNum = $("#holeNum").val();
                if (holeNum == "24") {
                    position = "#position-24";
                }else if (holeNum == "96"){
                    position = "#position-96";
                }*/
                if (str == "1") {
                    var tabindex = $("#tabindex").val();
                    //寻找下一个位置
                    $("#position-24").find('.ul-li-click li').each(function(key,val){
                        var tab = $(this).attr("tabindex");
                        var num = Number(tabindex);
                        if (Number(tab) == num) {
                            $(this).removeClass();
                            $("input[name='sampleNoModel']").val("");
                            $("input[name='sampleNoModel']").focus();
                            var elutionName = $("#elutionName").val();
                            $(this).children("p:nth-child(1)").text(elutionName);
                            $(this).children("p:nth-child(2)").text('');
                            $("select[name='samplePlateSortModel']").empty();
                            $("select[name='sampleLocationSortModel']").empty();
                            $("input[name='samplePlateLocationModel']").val("");
                            $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
                            $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
                            $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
                            $("select[name='samplePropertyModel']").prop('selectedIndex', 0);
                            return false;
                        }
                    })
                }else {
//                            document.location.reload();
                    $('.position-table-show').click();
                    var sampleLocationSort = "";
                    for (var i=0;i<obj.length;i++) {
                        sampleLocationSort = obj[i].sampleLocationSort;
                        $("#position-24").find('.ul-li-click li').each(function(key,val){
                            var tab = $(this).attr("tabindex");
                            if (Number(sampleLocationSort) == Number(tab)) {
                                $(this).removeClass();
                                $("input[name='sampleNoModel']").val("");
                                var elutionName = $("#elutionName").val();
                                $(this).children("p:nth-child(1)").text(elutionName);
                                $(this).children("p:nth-child(2)").text('');
                                $("select[name='samplePlateSortModel']").empty();
                                $("select[name='sampleLocationSortModel']").empty();
                                $("input[name='samplePlateLocationModel']").val("");
                                $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
                                $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
                                $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
                                $("select[name='samplePropertyModel']").prop('selectedIndex', 0);
                                return false;
                            }
                        })
                    }
                }
            }else {
                alert("操作失败!");
            }
        },
        error:function(data){
            alert("操作失败!");
        }
    });
}

$(function () {

    //模态框消失触发事件
    function modelHide() {
        /*var position;
        if (holeNum == "24") {
            position = "#position-24";
        }else if (holeNum == "96"){
            position = "#position-96";
        }*/

        var tabindex = $("#tabindex").val();
        if (tabindex == 24 || tabindex == 96) {
            var id = $("input[name='idModel']").val();
            if(id == undefined || id == null || id == ""){
                return true;
            }else{
                $("#modelContent").text("已是最后一个孔位！");
                $("#msg-model").modal();
                return false;
            }
        }

        //寻找下一个位置
        $("#position-24").find('.ul-li-click li').each(function(key,val){
            var tab = $(this).attr("tabindex");
            var num = Number(tabindex) + 1;
            if (Number(tab) == num) {
                $(this).click();
            }
        })

        //判断是否存在相同检材编号
        checkSampleNo();
    }

    //修改样本板
    $("#editBtn").on("click", function () {
        edit();
    });

    function edit() {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要修改的检材！");
            $("#msg-model").modal();
            return false;
        }

        $("#sampleMultipleModal").modal('show');
    }

    //保存多个个板信息
    $("#saveMultipleBtn").on("click", function () {
        saveInfo(getSampleInfo("multiple"));
    });

    function saveInfo(params) {
        var tabValue = $("#tabValue").val();
        var stage = "";
        if ("extractTab" == tabValue) {
            stage = "tq";
        }else if ("pcrTab" == tabValue) {
            stage = "kz";
        }else if ("syTab" == tabValue) {
            stage = "sy";
        }else {
            stage = "qt";
        }

        $.ajax({
            url:"../../center/sample/saveSampleInfo.html?stage=" + stage,
            type:"post",
            data:JSON.stringify(params),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
//                        document.location.reload();
                    $("#sampleMultipleModal").modal('hide');
                    /*var position;
                    var holeNum = $("#holeNum").val();
                    if (holeNum == "24") {
                        position = "#position-24";
                    }else if (holeNum == "96"){
                        position = "#position-96";
                    }*/
                    $('.position-table-show').click();
                    var sampleLocationSort = "";
                    var elutionName = $("select[name='elutionMultipleModel'] :checked").text();
                    for (var i=0;i<params.length;i++) {
                        sampleLocationSort = params[i].sampleLocationSort;
                        $("#position-24").find('.ul-li-click li').each(function(key,val){
                            var tab = $(this).attr("tabindex");
                            if (Number(sampleLocationSort) == Number(tab)) {
                                $(this).removeClass();
                                $(this).children("p:nth-child(1)").text(elutionName);
                                if (Number(elutionName) > 50) {
                                    $(this).addClass("li-background-2");
                                }else {
                                    $(this).addClass("li-background");
                                }
                                $(this).children("p:nth-child(6)").text(params[i].id);
                                return false;
                            }
                        })
                    }
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    function selectElution (obj) {
        $("select[name='elutionModel'] option").each(function() {
            if($(this).text() == obj) {
                $("select[name='elutionModel']").val($(this).val());
            }
        })
    }

    //首次进入时默认选中孔的位置
    checkPositon();

    function checkPositon() {
        var arr = [
            1,2,3,4,5,6,7,8,9,10,11,12,
            13,14,15,16,17,18,19,20,21,22,23,24,
            25,26,27,28,29,30,31,32,33,34,35,36,
            37,38,39,40,41,42,43,44,45,46,47,48,
            49,50,51,52,53,54,55,56,47,48,49,60,
            61,62,63,64,65,66,67,68,69,70,71,72,
            73,74,75,76,77,78,79,80,81,82,83,84,
            85,86,87,88,89,90,91,92,93,94,95,96
        ];

        //默认第一个空的位置

        for (var i = 0;i< arr.length;i++) {
            var flag = true;
            $("#position-24").find('.ul-li-click li').each(function(key,val){
                var tab = $(this).attr("tabindex");
                var id = $(this).children("p:nth-child(6)").text();
                if(id == undefined || id == null || id == ""){
                    if (arr[i] == Number(tab)) {
                        if (Number(tab) == 24 || Number(tab) == 96) {
                            if(id == undefined || id == null || id == ""){
                                checkEvent(this);
                            }else{
                                $("#modelContent").text("已是最后一个孔位！");
                                $("#msg-model").modal();
                            }
                            flag = false;
                        }else {
                            if(id == undefined || id == null || id == ""){
                                checkEvent(this);
                                flag = false;
                            }
                        }
                        return false;
                    }
                }
            })

            if (!flag) {
                return false;
            }
        }
    }

    var positionText;
    var holeNum = $("#holeNum").val();
    var elutionName = $("#elutionName").val();
    // 孔位选择 添加检材事件
    $('.ul-li-click li').click(function(){
        checkEvent(this);
    })

    function checkEvent(obj) {
        $('.ul-li-click li').removeClass("four");
        $(obj).addClass("four")
        $(obj).siblings().removeClass("four");
        positionText =  $(obj).children("p:nth-child(11)").text();

        var elutionName = $(obj).children("p:nth-child(1)").text()+"";
        selectElution(elutionName);

        var sampleNo = $(obj).children("p:nth-child(2)").text();
        $("input[name='sampleNoModel']").val(sampleNo);
        $("input[name='sampleNoModel']").focus();

        var samplePlateSort = $(obj).children("p:nth-child(3)").text();
        $("select[name='samplePlateSortModel']").empty();
        $("select[name='samplePlateSortModel']").append("<option value='"+samplePlateSort+"'>"+samplePlateSort+"</option>");

        var sampleLocationSort = $(obj).children("p:nth-child(4)").text();
        $("select[name='sampleLocationSortModel']").empty();
        $("select[name='sampleLocationSortModel']").append("<option value='"+sampleLocationSort+"'>"+sampleLocationSort+"</option>");

        var samplePlateLocation = $(obj).children("p:nth-child(5)").text();
        $("input[name='samplePlateLocationModel']").val(samplePlateLocation);

        var id = $(obj).children("p:nth-child(6)").text();
        $("input[name='idModel']").val(id);

        var preExperimentalMethod = $(obj).children("p:nth-child(7)").text();
        if (typeof(preExperimentalMethod != undefined) && preExperimentalMethod != null && preExperimentalMethod != "") {
            $("select[name='preExperimentalMethodModel']").val(preExperimentalMethod);
        }else {
            $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
        }

        var confirmatoryMethod = $(obj).children("p:nth-child(8)").text();
        if (typeof(confirmatoryMethod != undefined) && confirmatoryMethod != null && confirmatoryMethod != "") {
            $("select[name='confirmatoryMethodModel']").val(confirmatoryMethod);
        }else {
            $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
        }

        var sampleTransferMethod = $(obj).children("p:nth-child(9)").text();
        if (typeof(sampleTransferMethod != undefined) && sampleTransferMethod != null && sampleTransferMethod != "") {
            $("select[name='sampleTransferMethodModel']").val(sampleTransferMethod);
        }else {
            $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
        }

        var sampleProperty = $(obj).children("p:nth-child(10)").text();
        if (typeof(sampleProperty != undefined) && sampleProperty != null && sampleProperty != "") {
            $("select[name='samplePropertyModel']").val(sampleProperty);
        }else {
            if (Number(elutionName) > 50) {
                $("select[name='samplePropertyModel']").prop('selectedIndex', 2);
            }else {
                $("select[name='samplePropertyModel']").prop('selectedIndex', 1);
            }
        }

        var tabindex = $(obj).attr("tabindex");
        $("input[name='tabindex']").val(tabindex);
        console.log(positionText)
    }

    //查看是否存在相同检材编号
    checkSampleNo();

    function checkSampleNo() {
        /*var holeNum = $("#holeNum").val();
        var position;
        if (holeNum == "24") {
            position = "#position-24";
        }else if (holeNum == "96"){
            position = "#position-96";
        }*/

        $("#position-24").find('.ul-li-click li').each(function(key,val){
            var elutionName = $(this).children("p:nth-child(1)").text();
            var thisSampleNo = $(this).children("p:nth-child(2)").text();
            var thisTab = $(this).attr("tabindex");
            if (typeof(thisSampleNo != undefined) && thisSampleNo != null && thisSampleNo != "") {
                var flag = false;
                $("#position-24").find('.ul-li-click li').each(function(key,val){
                    var sampleNo = $(this).children("p:nth-child(2)").text();
                    var tab = $(this).attr("tabindex");
                    if (typeof(sampleNo != undefined) && sampleNo != null && sampleNo != "" && thisTab != tab) {
                        if(thisSampleNo == sampleNo) {
                            flag = true;
                            $(this).removeClass();
                            $(this).addClass("li-background-3");
                        }
                    }
                })
                //如果不存在相同编号判断属于那种背景色
                if (!flag) {
                    $(this).removeClass();
                    if (Number(elutionName) > 50) {
                        $(this).addClass("li-background-2");
                    }else {
                        $(this).addClass("li-background");
                    }
                }
            }
        })
    }

    click("position-24-btn");
    click("position-48-btn");
    click("position-96-btn");

    /* position();

     function position() {
     if (holeNum == "24") {
     $(".position-24-btn").addClass("style-li");
     }else if (holeNum == "96") {
     $(".btn-targ-ul li").click();
     }
     }*/

    /*// 孔位切换按钮事件 24 48 96
     $('.btn-targ-ul li').click(function () {
     $(this).addClass("style-li").siblings().removeClass("style-li");
     })*/

    // 孔位模板切换事件
    function click(classId){
        $("."+classId).click(function(){
            /*var text = $(this).text();
             $(".btn-targ-ul li").each(function(){
             if(text == $(this).text()){
             $(this).addClass("style-li")
             }
             })*/
            if(classId == "position-24-btn"){
                $('.display-style').css('display','none');
                $('.HolePosition').css('display','block');
                $("#position-24").css("display","block");
                $("#position-48").css("display","none");
                $("#position-96").css("display","none");
                $("input[name='sampleNoModel']").focus();
            }else if(classId == "position-48-btn"){
                $('.display-style').css('display','none');
                $('.HolePosition').css('display','block');
                $("#position-24").css("display","none");
                $("#position-48").css("display","block");
                $("#position-96").css("display","none");
                $("input[name='sampleNoModel']").focus();
            }else if(classId == "position-96-btn"){
                $('.display-style').css('display','none');
                $('.HolePosition').css('display','block');
                $("#position-24").css("display","none");
                $("#position-48").css("display","none");
                $("#position-96").css("display","block");
                $("input[name='sampleNoModel']").focus();

                getAllBoard();
            }
        })
    }

    function getAllBoard() {

        var param = {
            "entity": sampleInfo(),
            "holeNum":$("#holeNum").val(),
            "elutionName":$("#elutionName").val()
        }

        var tabValue = $("#tabValue").val();
        var stage = "";
        if ("extractTab" == tabValue) {
            stage = "tq";
        }else if ("pcrTab" == tabValue) {
            stage = "kz";
        }else if ("syTab" == tabValue) {
            stage = "sy";
        }else {
            stage = "qt";
        }

        $.ajax({
            url:"../../center/sample/getAllBoardList.html?stage=" + stage,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    $("#allBoard").empty();
                    var len = data.allTempList.length;
                    var htmlStr = "";
                    var sample;
                    var list;
                    for(var i = 0; i < len; i++){
                        sample = data.allTempList[i];
                        var listLen = sample.list.length;
                        htmlStr += '<div class="col-lg-3 col-md-3 col-sm-3 col-xs-3 padding-0">';
                        htmlStr += '<div class="">';
                        htmlStr += '<ul class="ul-li-click style-96-li">';
                        for (var j = 0; j < listLen; j++) {
                            list = sample.list[j];
                            if (isFull(list.entity.id)) {
                                htmlStr += "<li>";
                            }else {
                                if (Number(list.elutionName) > 50) {
                                    htmlStr += '<li class="li-background-2">';
                                }else {
                                    htmlStr += '<li class="li-background">';
                                }
                            }
                            htmlStr += '<p>'+ list.elutionName +'</p>';
                            if (isFull(list.entity.sampleNo)) {
                                htmlStr += "<p></p>";
                            }else {
                                htmlStr += '<p>'+ list.entity.sampleNo +'</p>';
                            }
                            htmlStr += '<p>'+ list.entity.sampleLocationSort + '/' + list.entity.samplePlateLocation +'</p>';
                            if (j < 3) {
                                htmlStr += '<span class="posi-span">'+ (3*i+j+1) +'</span>';
                            }
                            htmlStr += '</li>';
                        }
                        htmlStr += '</ul>';
                        htmlStr += '</div>';
                        htmlStr += '</div>';
                    }
                    $("#allBoard").append(htmlStr);
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    function isFull (str) {
        if(str == undefined || str == null || str == ""){
            return true;
        }
    }

    // 表格 孔位切换事件
    $(".position-table-show").click(function(){
        tableClick("tableList");
    })


    function tableClick(obj) {
        $("#allChecked").attr("checked",false);
        var param = {
            "entity": sampleInfo(),
            "holeNum":$("#holeNum").val()
        }
        var tabValue = $("#tabValue").val();
        var stage = "";
        if ("extractTab" == tabValue) {
            stage = "tq";
        }else if ("pcrTab" == tabValue) {
            stage = "kz";
        }else if ("syTab" == tabValue) {
            stage = "sy";
        }else {
            stage = "qt";
        }

        $.ajax({
            url:"../../center/sample/getSampleInfoList.html?stage=" + stage,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    $("#sampleInfoVoListTbody").empty();
                    var len = data.sampleInfoVoList.length;
                    var htmlStr = "";
                    var sample;
                    for(var i = 0; i < len; i++){
                        sample = data.sampleInfoVoList[i];
                        htmlStr += "<tr>";
                        htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                        htmlStr += "<input type='hidden' name='sampleTableId' value='"+sample.entity.sampleTableId+"'/>";
                        htmlStr += "<input type='hidden' name='extractPlateId' value='"+sample.entity.extractPlateId+"'/>";
                        htmlStr += "<input type='hidden' name='pcrPlateId' value='"+sample.entity.pcrPlateId+"'/>";
                        htmlStr += "<input type='hidden' name='syPlateId' value='"+sample.entity.syPlateId+"'/>";
                        htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                        htmlStr += "<input type='hidden' name='elution' value='"+sample.entity.elution+"'/>";
                        htmlStr += "<input type='hidden' name='sampleNo' value='"+sample.entity.sampleNo+"'/>";
                        htmlStr += "<input type='hidden' name='samplePlateSort' value='"+sample.entity.samplePlateSort+"'/>";
                        htmlStr += "<input type='hidden' name='sampleLocationSort' value='"+sample.entity.sampleLocationSort+"'/>";
                        htmlStr += "<input type='hidden' name='samplePlateLocation' value='"+sample.entity.samplePlateLocation+"'/>";
                        htmlStr += "<input type='hidden' name='preExperimentalMethod' value='"+sample.entity.preExperimentalMethod+"'/>";
                        htmlStr += "<input type='hidden' name='confirmatoryMethod' value='"+sample.entity.confirmatoryMethod+"'/>";
                        htmlStr += "<input type='hidden' name='sampleTransferMethod' value='"+sample.entity.sampleTransferMethod+"'/>";
                        htmlStr += "<input type='hidden' name='sampleProperty' value='"+sample.entity.sampleProperty+"'/>";
                        htmlStr += "<label class='labelBox'><input type='checkbox' name='box' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                        htmlStr += "<td>"+ sample.entity.sampleNo +"</td>";
                        htmlStr += "<td>"+ sample.elutionName +"</td>";
                        if ("extractTab" == tabValue) {
                            htmlStr += "<td>"+ sample.entity.extractPlateSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.extractLocationSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.extractPlateLocation +"</td>";
                        }else if ("pcrTab" == tabValue) {
                            htmlStr += "<td>"+ sample.entity.pcrPlateSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.pcrLocationSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.pcrPlateLocation +"</td>";
                        }else if ("syTab" == tabValue) {
                            htmlStr += "<td>"+ sample.entity.syPlateSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.syLocationSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.syPlateLocation +"</td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.samplePlateSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.sampleLocationSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.samplePlateLocation +"</td>";
                        }
                        htmlStr += "<td>"+ sample.confirmatoryMethodName +"</td>";
                        htmlStr += "<td>"+ sample.preExperimentalMethodName +"</td>";
                        htmlStr += "<td>"+ sample.sampleTransferMethodName +"</td>";
                        var sampleTable = data.sampleTable;
                        if ("1" == sampleTable.isCreate) {

                        }else {
                            htmlStr += "<td>";
                            htmlStr += "<span onclick= 'deleteClick(this)'>";
                            htmlStr += "<span>";
                            htmlStr += "<img src='../../automatedWork/img/tra.png' alt=''>";
                            htmlStr += "</span><span>删除</span></span>";
                            htmlStr += "</td>";
                        }
                        htmlStr +="</tr>";
                    }
                    $("#sampleInfoVoListTbody").append(htmlStr);

                    if (obj == "tableList") {
                        $('.HolePosition').css('display','none');
                        $('.display-style').css('display','block');
//                            $('.btn-targ-ul li').removeClass("style-li");
                    }else {
                        $("#sampleMultipleModal").modal('hide');
                    }
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    function sampleInfo() {
        var sample = {};

        var tabValue = $("#tabValue").val();
        if ("extractTab" == tabValue) {
            sample.extractPlateId = $("#extractPlateId").val();
        }else if ("pcrTab" == tabValue) {
            sample.pcrPlateId = $("#pcrPlateId").val();
        }else if ("syTab" == tabValue) {
            sample.syPlateId = $("#syPlateId").val();
        }else {
            sample.sampleTableId = $("#sampleTableId").val();
        }
//            sample.boardNo = $("#boardNo").val();
        sample.elution = $("#elution").val();

        return sample;
    }

   /* var tabValue = $("#tabValue").val();
    if (tabValue != undefined && tabValue != null && tabValue != "") {
        if ("extractTab" == tabValue) {
            $(".position-24-btn").addClass("hide");
        }else if ("pcrTab" == tabValue) {
            $(".position-24-btn").addClass("hide");
        }else if ("syTab" == tabValue) {
            $(".position-24-btn").addClass("hide");
        }
    }*/

    //返回上一页
    $("#backBtn").on("click", function () {
        location.href = "../../center/sample/sampleTableList.html?tabValue=" + $("#tabValue").val();
    });

    function getSampleInfo(obj) {
        var sampleArr = new Array();
        var sample = {};
        $("tr", "#sampleInfoVoListTbody").each(function () {
            sample = {};
            var checkBox = $("input[name='box']", $(this)).is(":checked");

            if (checkBox) {
                sample.id = $("input[name='id']", $(this)).val();
                if (obj == "multiple") {
                    sample.elution = $("select[name='elutionMultipleModel'] :checked").val();
                }else {
                    sample.elution = $("select[name='elution']", $(this)).val();
                }
                sample.sampleTableId = $("#sampleTableId").val();
                sample.extractPlateId = $("#extractPlateId").val();
                sample.pcrPlateId = $("#pcrPlateId").val();
                sample.syPlateId = $("#syPlateId").val();
                sample.boardNo = $("input[name='boardNo']", $(this)).val();
                sample.sampleNo = $("input[name='sampleNo']", $(this)).val();
                sample.samplePlateSort = $("input[name='samplePlateSort']", $(this)).val();
                sample.sampleLocationSort = $("input[name='sampleLocationSort']", $(this)).val();
                sample.samplePlateLocation = $("input[name='samplePlateLocation']", $(this)).val();
                sample.preExperimentalMethod = $("input[name='preExperimentalMethod']", $(this)).val();
                sample.confirmatoryMethod = $("input[name='confirmatoryMethod']", $(this)).val();
                sample.sampleTransferMethod = $("input[name='sampleTransferMethod']", $(this)).val();
                sample.sampleProperty = $("input[name='sampleProperty']", $(this)).val();

                sampleArr.push(sample);
            }
        });

        return sampleArr;
    }

    function checkCount() {
        var checkCount = 0;
        $("tr", "#sampleInfoVoListTbody").each(function () {
            var checkBox = $("input[name='box']", $(this)).is(":checked");
            if (checkBox) {
                checkCount++;
            }
        });

        return checkCount;
    }

    //删除样本板
    $("#deleteBtn").on("click", function () {
        if (checkCount() <= 0) {
            $("#modelContent").text("请选择要删除的检材！");
            $("#msg-model").modal();
            return false;
        }

        $("#identification").val("2");
        $().confirmOperationSample("确认删除吗?");
    });

    // 添加检材保存按钮事件
    $("#saveBtn").click(function(){
        console.log(positionText);
        if(positionText == undefined || positionText == null || positionText == ""){
            $("#promptModel-error").modal();
            return false;
        }else{
            var id = $("input[name='idModel']").val();
            //判断保存的孔位是否已经存在检材信息
            if (id == undefined || id == null || id == "") {
                saveSample();
            }else {
                $("#addModel").modal('show');
            }
        }
    })

    $("#confirmBtn").click(function(){
        $("#addModel").modal('hide');
        saveSample();
    });

    function saveSample() {
        var sampleNo = $("input[name='sampleNoModel']").val();
        if(sampleNo == undefined || sampleNo == null || sampleNo == ""){
            $("#modelContent").text("请选择检材放置孔位！");
            $("#msg-model").modal();
            return false;
        }

        var sample = {};

        sample.sampleNo = $("input[name='sampleNoModel']").val();
        sample.boardNo = $("#boardNo").val();

        var param = {
            "entity": sample
        }

        $.ajax({
            url:"../../center/sample/getSampleInfoList.html",
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    var len = data.sampleInfoVoList.length;
                    if (len > 0) {
                        $("#tipsModel").modal('show');
                    }else {
                        saveSampleInfo("noSpilt");
                    }
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    $("#noBtn").on('click', function () {
        $("#tipsModel").modal('hide');
        saveSampleInfo("noSpilt");
    })

    $("#yesBtn").on("click", function(){
        $("#tipsModel").modal('hide');
        var sampleNoModel = $("input[name='sampleNoModel']").val();
        $("input[name='spiltSampleNoModel']").val(sampleNoModel + "-");
        $("#splitModel").modal('show');
    });

    $("#saveSpiltBtn").on("click", function(){
        $("#splitModel").modal('hide');
        saveSampleInfo("spilt");
    });

    $("#delBtn").click(function(){
        $("#identification").val("1");
        $().confirmOperationSample("确认删除吗?");
    })

    $.fn.confirmOperationSample = function (obj) {
        $("#confirmContent").text(obj);
        $("#confirm-model").modal();
    }

    $('body').on('click' , '#confirmBtn' , function() {
        var identification = $("#identification").val();
        if (identification == "1") {
            var id = $("input[name='idModel']").val();
            if (id != 'undefined' && id != null && id != "") {
                var sampleArr = new Array();
                var sample = {};
                sample.id = id;
                sampleArr.push(sample);

                deletePlate(sampleArr,'1');
            }
        }else if (identification == "2") {
            deletePlate(getSampleInfo("single"),"2");
        }else if (identification == "3") {
            var sampleArr = new Array();
            var sample = {};
            sample.id = $("#param1").val();
            sample.sampleLocationSort = $("#param2").val();
            sampleArr.push(sample);

            deletePlate(sampleArr, "2");
        }
    })

    function saveSampleInfo(obj) {
        var tabValue = $("#tabValue").val();
        var stage = "";
        if ("extractTab" == tabValue) {
            stage = "tq";
        }else if ("pcrTab" == tabValue) {
            stage = "kz";
        }else if ("syTab" == tabValue) {
            stage = "sy";
        }else {
            stage = "qt";
        }

        $.ajax({
            url:"../../center/sample/saveSampleInfo.html?stage=" + stage,
            type:"post",
            data:JSON.stringify(getParams(obj)),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    /*var position;
                    if (holeNum == "24") {
                        position = "#position-24";
                    }else if (holeNum == "96"){
                        position = "#position-96";
                    }*/
                    var sampleInfo = data.sampleInfo;
                    if (sampleInfo != null) {
                        $("#position-24").find('.ul-li-click li').each(function(key,val){
                            if($(this).children("p:nth-child(11)").text() == positionText) {
                                $(this).removeClass();
                                $(this).children("p:nth-child(1)").text(sampleInfo.elutionName);
                                var elution = $(this).children("p:nth-child(1)").text();
                                if (Number(elution) > 50) {
                                    $(this).addClass("li-background-2");
                                }else {
                                    $(this).addClass("li-background");
                                }
                                $(this).children("p:nth-child(2)").text(sampleInfo.sampleNo);
                                $(this).children("p:nth-child(3)").text(sampleInfo.samplePlateSort);
                                $(this).children("p:nth-child(4)").text(sampleInfo.sampleLocationSort);
                                $(this).children("p:nth-child(5)").text(sampleInfo.samplePlateLocation);
                                $(this).children("p:nth-child(6)").text(sampleInfo.id);
                                $(this).children("p:nth-child(7)").text(sampleInfo.preExperimentalMethod);
                                $(this).children("p:nth-child(8)").text(sampleInfo.confirmatoryMethod);
                                $(this).children("p:nth-child(9)").text(sampleInfo.sampleTransferMethod);
                                $(this).children("p:nth-child(10)").text(sampleInfo.sampleProperty);
                                $("input[name='idModel']").val(data.id);
                            }
                        })
                    }
                    //模态框消失
                    modelHide();
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    document.onkeydown = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            $("#saveBtn").click();
            e.preventDefault();
        }
    }

    function getParams(obj){
        var sampleArr = new Array();
        var sample = {};

        sample.id = $("input[name='idModel']").val();
        sample.sampleTableId = $("#sampleTableId").val();
        sample.extractPlateId = $("#extractPlateId").val();
        sample.pcrPlateId = $("#pcrPlateId").val();
        sample.syPlateId = $("#syPlateId").val();
        sample.boardNo = $("#boardNo").val();
        if ("spilt" == obj) {
            sample.sampleNo = $("input[name='spiltSampleNoModel']").val();
        }else {
            sample.sampleNo = $("input[name='sampleNoModel']").val();
        }
        sample.elution = $("select[name='elutionModel'] :checked").val();
        sample.elutionName = $("select[name='elutionModel'] :checked").text();
        var tabValue = $("#tabValue").val();
        if ("extractTab" == tabValue) {
            sample.extractPlateSort = $("select[name='samplePlateSortModel'] :checked").val();
            sample.extractLocationSort = $("select[name='sampleLocationSortModel'] :checked").val();
            sample.extractPlateLocation = $("input[name='samplePlateLocationModel']").val();
        }else if ("pcrTab" == tabValue) {
            sample.pcrPlateSort = $("select[name='samplePlateSortModel'] :checked").val();
            sample.pcrLocationSort = $("select[name='sampleLocationSortModel'] :checked").val();
            sample.pcrPlateLocation = $("input[name='samplePlateLocationModel']").val();
        }else if ("syTab" == tabValue) {
            sample.syPlateSort = $("select[name='samplePlateSortModel'] :checked").val();
            sample.syLocationSort = $("select[name='sampleLocationSortModel'] :checked").val();
            sample.syPlateLocation = $("input[name='samplePlateLocationModel']").val();
        }else {
            sample.samplePlateSort = $("select[name='samplePlateSortModel'] :checked").val();
            sample.sampleLocationSort = $("select[name='sampleLocationSortModel'] :checked").val();
            sample.samplePlateLocation = $("input[name='samplePlateLocationModel']").val();
        }
        sample.preExperimentalMethod = $("select[name='preExperimentalMethodModel'] :checked").val();
        sample.confirmatoryMethod = $("select[name='confirmatoryMethodModel'] :checked").val();
        sample.sampleTransferMethod = $("select[name='sampleTransferMethodModel'] :checked").val();
        sample.sampleProperty = $("select[name='samplePropertyModel'] :checked").val();

        sampleArr.push(sample);
        return sampleArr;
    }

})