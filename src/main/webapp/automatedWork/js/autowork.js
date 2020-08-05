$(function () {

    var url = location.href.split("?")[0].split("/").pop();
    console.log(url)
    $('.side-menu-Ulitem li a').each(function (key, val) {
        var href = $(this).attr("href").split("?")[0].split("/").pop();
        console.log(href)
        // console.log($(this).attr("href"));
        if (href == url) {
            // #EBEBED
            console.log("111")
            $(this).parent().css("background", "#EBEBED");
        }
        if(url == "addSample.html"){
            console.log("一层判断")
           if(href == "sampleTableList.html"){
               console.log("二层判断");
            $(this).parent().css("background", "#EBEBED");
           }
        }
    })


    var tagIndex = 0;
    // 实验记录管理 顶部导航 各管理表切换事件
    $(".active-ul li").click(function () {
        tagIndex = $(this).index();
        $(this).addClass('active-li').siblings().removeClass('active-li');
        console.log($(this).index());

        if (tagIndex == 0) {
            $("#display-sy01").show();
            $("#display-sy02").hide();
            $("#display-sy03").hide();
            $("#display-sy04").hide();
            $("#textBlock").show();
            $("#btnBlock").hide();
            $("#tabValue").val("sampleTableTab");
            $("#boardNo").val("");
            $("#sampleNo").val("");
            $().getList();
        } else if (tagIndex == 1) {
            $("#display-sy01").hide();
            $("#display-sy02").show();
            $("#display-sy03").hide();
            $("#display-sy04").hide();
            $("#textBlock").show();
            $("#btnBlock").hide();
            $("#tabValue").val("extractTab");
            $("#boardNo").val("");
            $("#sampleNo").val("");
            $().getList();
        } else if (tagIndex == 2) {
            $("#display-sy01").hide();
            $("#display-sy02").hide();
            $("#display-sy03").show();
            $("#display-sy04").hide();
            $("#textBlock").show();
            $("#btnBlock").hide();
            $("#tabValue").val("pcrTab");
            $("#boardNo").val("");
            $("#sampleNo").val("");
            $().getList();
        } else if (tagIndex == 3) {
            $("#display-sy01").hide();
            $("#display-sy02").hide();
            $("#display-sy03").hide();
            $("#display-sy04").show();
            $("#textBlock").hide();
            $("#btnBlock").show();
            $("#tabValue").val("syTab");
            $("#boardNo").val("");
            $("#sampleNo").val("");
            $().getList();
        }
    })

    // 实验记录管理 顶部导航 全部 已生成 未生成
    $(".table-btn-tag-ul li").click(function () {
        console.log(tagIndex);
        console.log($(this).index())
        $(this).addClass('active-tag').siblings().removeClass('active-tag');
        //样本表管理
        if (tagIndex == 0 || tagIndex == 1 || tagIndex == 2 || tagIndex == 3) {
            if ($(this).index() == 0) {
                //获取全部列表
                $("#statusType").val("");
                $().getList();
            } else if ($(this).index() == 1) {
                //获取已完成列表
                $("#statusType").val("1");
                $().getList();
            } else if ($(this).index() == 2) {
                //获取未完成列表
                $("#statusType").val("0");
                $().getList();
            }
        }
    })

    //查询
    $("#searchBtn").on("click", function(){
        $().getList();
    })


    //根据点击tab获取不同管理下的全部、已完成及未完成列表数据
    $.fn.getList = function() {
        var tabValue = $("#tabValue").val();
        if ("sampleTableTab" == tabValue) {
            //样本表管理
            getSampleTable();
        }else if ("extractTab" == tabValue) {
            //提取管理
            getExtractPlate();
        }else if ("pcrTab" == tabValue) {
            //扩增管理
            getPcrPlate();
        }else if ("syTab" == tabValue) {
            //电泳管理
            getSyPlate();
        }
    }

    function sampleInfo() {
        var sample = {};

        sample.boardNo = $("#boardNo").val();
        sample.isCreate = $("#statusType").val();

        return sample;
    }

    //获取样本表列表数据
    function getSampleTable() {
        $("input[name='allSampleTableChecked']").attr("checked",false);
        var param = {
            "entity": sampleInfo(),
            "sampleNo": $("#sampleNo").val()
        }

        var page = $("#page").val();
        if (isFull(page)) {
            page = 1;
        }

        $.ajax({
            url:"../../center/sample/getSampleTableList.html?page=" + page,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    /*$("#sampleTableVoListThead").empty();
                    var headStr = "";
                    headStr += "<tr>";
                    headStr += "<th><label class='allLabel'><input type='checkbox' name='allChecked' id='allChecked' onclick='setAll()'/>序号</label></th>";
                    headStr += "<th>板名</th>";
                    headStr += "<th>孔数</th>";
                    headStr += "<th>默认预设体积</th>";
                    headStr += "<th>提取表名称</th>";
                    headStr += "<th>创建者</th>";
                    headStr += "<th>创建时间</th>";
                    headStr += "<th>操作</th>";
                    headStr += "</tr>";
                    $("#sampleTableVoListThead").append(headStr);*/

                    $("#sampleTableVoListTbody").empty();
                    var len = data.sampleList.length;
                    var htmlStr = "";
                    var sample;
                    for(var i = 0; i < len; i++){
                        sample = data.sampleList[i];
                        htmlStr += "<tr>";
                        htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                        htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                        htmlStr += "<input type='hidden' name='holeNum' value='"+sample.entity.holeNum+"'/>";
                        htmlStr += "<input type='hidden' name='elutionDefault' value='"+sample.entity.elutionDefault+"'/>";
                        htmlStr += "<input type='hidden' name='elutionDefaultName' value='"+sample.elutionDefaultName+"'/>";
                        htmlStr += "<input type='hidden' name='createPerson' value='"+sample.entity.createPerson+"'/>";
                        htmlStr += "<input type='hidden' name='createDatetime' value='"+getLocalTime(new Date(sample.entity.createDatetime))+"'/>";
                        htmlStr += "<label class='labelBox'><input type='checkbox' name='allSampleTableBox' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                        if (isFull(sample.entity.boardNo)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.boardNo +"</td>";
                        }
                        if (isFull(sample.entity.holeNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.holeNum +"</td>";
                        }
                        if (isFull(sample.elutionDefaultName)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.elutionDefaultName +"</td>";
                        }
                        if (isFull(sample.entity.extractPlateName)) {
                            htmlStr += "<td>未提交</td>";
                        }else {
                            htmlStr += "<td style='color: green; font-weight: bold'>已提交</td>";
                        }
                        if (isFull(sample.entity.createPerson)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.createPerson +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.createDatetime)) +"</td>";
                        htmlStr += "<td>";
                        htmlStr += "<span class='exportBtn' onclick= 'exportBtn(this)'>";
                        htmlStr += "<img src='../../automatedWork/img/1990.png' alt=''>导出24孔样本表";
                        htmlStr += "</span>";
                        if (sample.entity.isCreate == "1") {
                            htmlStr += "<span class='addSampleBtn' onclick= 'addSampleBtn(this)'>";
                            htmlStr += "<img src='../../automatedWork/img/see.png' alt=''>查看检材";
                            htmlStr += "</span>";
                        }else {
                            htmlStr += "<span class='addSampleBtn' onclick= 'addSampleBtn(this)'>";
                            htmlStr += "<img src='../../automatedWork/img/1123.png' alt=''>添加检材";
                            htmlStr += "</span>";
                        }
                        htmlStr += "</td>";
                        htmlStr +="</tr>";
                    }
                    $("#sampleTableVoListTbody").append(htmlStr);

                    assignment(data.pageInfo, "getSampleTableList");
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    //获取提取表列表数据
    function getExtractPlate() {
        $("input[name='allExtractChecked']").attr("checked",false);
        var param = {
            "entity": sampleInfo(),
            "sampleNo": $("#sampleNo").val()
        }

        var page = $("#page").val();
        if (isFull(page)) {
            page = 1;
        }

        $.ajax({
            url:"../../center/sample/getExtractPlateList.html?page=" + page,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    $("#extractPlateVoListTbody").empty();
                    var len = data.sampleList.length;
                    var htmlStr = "";
                    var sample;
                    for(var i = 0; i < len; i++){
                        sample = data.sampleList[i];
                        htmlStr += "<tr>";
                        htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                        htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                        htmlStr += "<input type='hidden' name='state' value='"+sample.entity.state+"'/>";
                        htmlStr += "<input type='hidden' name='holeNum' value='"+sample.entity.holeNum+"'/>";
                        htmlStr += "<input type='hidden' name='kitName' value='"+sample.entity.kitName+"'/>";
                        htmlStr += "<input type='hidden' name='deviceType' value='"+sample.entity.deviceType+"'/>";
                        htmlStr += "<input type='hidden' name='extractMethod' value='"+sample.entity.extractMethod+"'/>";
                        htmlStr += "<input type='hidden' name='extractDatetime' value='"+getLocalTime(new Date(sample.entity.extractDatetime))+"'/>";
                        htmlStr += "<input type='hidden' name='pcrPlateName' value='"+sample.entity.pcrPlateName+"'/>";
                        htmlStr += "<input type='hidden' name='createPerson' value='"+sample.entity.createPerson+"'/>";
                        htmlStr += "<input type='hidden' name='createDatetime' value='"+getLocalTime(new Date(sample.entity.createDatetime))+"'/>";
                        htmlStr += "<label class='labelBox'><input type='checkbox' name='extractBox' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                        if (isFull(sample.entity.boardNo)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.boardNo +"</td>";
                        }
                        if (isFull(sample.entity.state)) {
                            htmlStr += "<td></td>";
                        }else {
                            if (sample.entity.state == "1") {
                                htmlStr += "<td><img src='../../automatedWork/img/load.png' alt=''>开始</td>";
                            }else if (sample.entity.state == "2") {
                                htmlStr += "<td><img src='../../automatedWork/img/wc.png' alt=''>结束</td>";
                            }else {
                                htmlStr += "<td>未开始</td>";
                            }
                        }
                        if (isFull(sample.entity.holeNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.holeNum +"</td>";
                        }
                        if (isFull(sample.entity.kitName)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.kitName +"</td>";
                        }
                        if (isFull(sample.entity.deviceType)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.deviceType +"</td>";
                        }
                        if (isFull(sample.entity.extractMethod)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.extractMethod +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.extractDatetime)) +"</td>";
                       /* if (isFull(sample.entity.pcrPlateName)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.pcrPlateName +"</td>";
                        }*/
                        if (isFull(sample.entity.createPerson)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.createPerson +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.createDatetime)) +"</td>";
                        htmlStr += "<td>";
                        htmlStr += "<span onclick= 'viewExtractBtn(this)'>";
                        htmlStr += "<img src='../../automatedWork/img/see.png' alt=''>查看并编辑";
                        htmlStr += "</span>";
                        if (sample.entity.isCreate == "1") {

                        }else {
                            htmlStr += "<span onclick= 'deleteExtractBtn(this)'>";
                            htmlStr += "<img src='../../automatedWork/img/tra.png' alt=''>删除";
                            htmlStr += "</span>";
                        }
                        htmlStr += "</td>";
                        htmlStr +="</tr>";
                    }
                    $("#extractPlateVoListTbody").append(htmlStr);

                    assignment(data.pageInfo, "getExtractPlateList");
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    //获取扩增表列表数据
    function getPcrPlate() {
        $("input[name='allPcrChecked']").attr("checked",false);
        var param = {
            "entity": sampleInfo(),
            "sampleNo": $("#sampleNo").val()
        }

        var page = $("#page").val();
        if (isFull(page)) {
            page = 1;
        }

        $.ajax({
            url:"../../center/sample/getPcrPlateList.html?page=" + page,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    $("#pcrPlateVoListTbody").empty();
                    var len = data.sampleList.length;
                    var htmlStr = "";
                    var sample;
                    for(var i = 0; i < len; i++){
                        sample = data.sampleList[i];
                        htmlStr += "<tr>";
                        htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                        htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                        htmlStr += "<input type='hidden' name='state' value='"+sample.entity.state+"'/>";
                        htmlStr += "<input type='hidden' name='holeNum' value='"+sample.entity.holeNum+"'/>";
                        htmlStr += "<input type='hidden' name='pcrDatetime' value='"+getLocalTime(new Date(sample.entity.pcrDatetime))+"'/>";
                        htmlStr += "<input type='hidden' name='testSystem' value='"+sample.entity.testSystem+"'/>";
                        htmlStr += "<input type='hidden' name='pcrSystemTrace' value='"+sample.entity.pcrSystemTrace+"'/>";
                        htmlStr += "<input type='hidden' name='pcrSystemConstant' value='"+sample.entity.pcrSystemConstant+"'/>";
                        htmlStr += "<input type='hidden' name='pcrRunNum' value='"+sample.entity.pcrRunNum+"'/>";
                        htmlStr += "<input type='hidden' name='reagentBatch' value='"+sample.entity.reagentBatch+"'/>";
                        htmlStr += "<input type='hidden' name='pcrInstrumentNum' value='"+sample.entity.pcrInstrumentNum+"'/>";
                        htmlStr += "<input type='hidden' name='createPerson' value='"+sample.entity.createPerson+"'/>";
                        htmlStr += "<input type='hidden' name='createDatetime' value='"+getLocalTime(new Date(sample.entity.createDatetime))+"'/>";
                        htmlStr += "<label class='labelBox'><input type='checkbox' name='pcrBox' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                        if (isFull(sample.entity.boardNo)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.boardNo +"</td>";
                        }
                        if (isFull(sample.entity.state)) {
                            htmlStr += "<td></td>";
                        }else {
                            if (sample.entity.state == "1") {
                                htmlStr += "<td><img src='../../automatedWork/img/load.png' alt=''>开始</td>";
                            }else if (sample.entity.state == "2") {
                                htmlStr += "<td><img src='../../automatedWork/img/wc.png' alt=''>结束</td>";
                            }else {
                                htmlStr += "<td>未开始</td>";
                            }
                        }
                        if (isFull(sample.entity.holeNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.holeNum +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.pcrDatetime)) +"</td>";
                        if (isFull(sample.entity.testSystem)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.testSystem +"</td>";
                        }
                        if (isFull(sample.entity.pcrSystemTrace)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.pcrSystemTrace +"</td>";
                        }
                        if (isFull(sample.entity.pcrSystemConstant)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.pcrSystemConstant +"</td>";
                        }
                        if (isFull(sample.entity.pcrRunNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.pcrRunNum +"</td>";
                        }
                        if (isFull(sample.entity.reagentBatch)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.reagentBatch +"</td>";
                        }
                        if (isFull(sample.entity.pcrInstrumentNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.pcrInstrumentNum +"</td>";
                        }
                        if (isFull(sample.entity.syPlateName)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.syPlateName +"</td>";
                        }
                        if (isFull(sample.entity.createPerson)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.createPerson +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.createDatetime)) +"</td>";
                        htmlStr += "<td>";
                        htmlStr += "<span onclick= 'viewPcrBtn(this)'>";
                        htmlStr += "<img src='../../automatedWork/img/see.png' alt=''>查看并编辑";
                        htmlStr += "</span>";
                        if (sample.entity.isCreate == "1") {

                        }else {
                            htmlStr += "<span onclick= 'deletePcrBtn(this)'>";
                            htmlStr += "<img src='../../automatedWork/img/tra.png' alt=''>删除";
                            htmlStr += "</span>";
                        }
                        htmlStr += "</td>";
                        htmlStr +="</tr>";
                    }
                    $("#pcrPlateVoListTbody").append(htmlStr);

                    assignment(data.pageInfo, "getPcrPlateList");
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    //获取电泳表列表数据
    function getSyPlate() {
        $("input[name='allSyChecked']").attr("checked",false);
        var param = {
            "entity": sampleInfo(),
            "sampleNo": $("#sampleNo").val()
        }

        var page = $("#page").val();
        if (isFull(page)) {
            page = 1;
        }

        $.ajax({
            url:"../../center/sample/getSyPlateList.html?page=" + page,
            type:"post",
            data:JSON.stringify(param),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
                    $("#syPlateVoListTbody").empty();
                    var len = data.sampleList.length;
                    var htmlStr = "";
                    var sample;
                    for(var i = 0; i < len; i++){
                        sample = data.sampleList[i];
                        htmlStr += "<tr>";
                        htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                        htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                        htmlStr += "<input type='hidden' name='testDatetime' value='"+getLocalTime(new Date(sample.entity.testDatetime))+"'/>";
                        htmlStr += "<input type='hidden' name='state' value='"+sample.entity.state+"'/>";
                        htmlStr += "<input type='hidden' name='holeNum' value='"+sample.entity.holeNum+"'/>";
                        htmlStr += "<input type='hidden' name='firstInstrumentNum' value='"+sample.entity.firstInstrumentNum+"'/>";
                        htmlStr += "<input type='hidden' name='molecularWeightMarker' value='"+sample.entity.molecularWeightMarker+"'/>";
                        htmlStr += "<input type='hidden' name='mixingRatio' value='"+sample.entity.mixingRatio+"'/>";
                        htmlStr += "<input type='hidden' name='sySystem' value='"+sample.entity.sySystem+"'/>";
                        htmlStr += "<input type='hidden' name='environmentTemperature' value='"+sample.entity.environmentTemperature+"'/>";
                        htmlStr += "<input type='hidden' name='denaturationCondition' value='"+sample.entity.denaturationCondition+"'/>";
                        htmlStr += "<input type='hidden' name='createPerson' value='"+sample.entity.createPerson+"'/>";
                        htmlStr += "<input type='hidden' name='createDatetime' value='"+getLocalTime(new Date(sample.entity.createDatetime))+"'/>";
                        htmlStr += "<label class='labelBox'><input type='checkbox' name='syBox' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                        if (isFull(sample.entity.boardNo)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.boardNo +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.testDatetime)) +"</td>";
                        if (isFull(sample.entity.state)) {
                            htmlStr += "<td></td>";
                        }else {
                            if (sample.entity.state == "1") {
                                htmlStr += "<td><img src='../../automatedWork/img/load.png' alt=''>开始</td>";
                            }else if (sample.entity.state == "2") {
                                htmlStr += "<td><img src='../../automatedWork/img/wc.png' alt=''>结束</td>";
                            }else {
                                htmlStr += "<td>未开始</td>";
                            }
                        }
                        if (isFull(sample.entity.firstInstrumentNum)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.firstInstrumentNum +"</td>";
                        }
                        if (isFull(sample.entity.molecularWeightMarker)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.molecularWeightMarker +"</td>";
                        }
                        if (isFull(sample.entity.mixingRatio)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.mixingRatio +"</td>";
                        }
                        if (isFull(sample.entity.sySystem)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.sySystem +"</td>";
                        }
                        if (isFull(sample.entity.environmentTemperature)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.environmentTemperature +"</td>";
                        }
                        if (isFull(sample.entity.denaturationCondition)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.denaturationCondition +"</td>";
                        }
                        if (isFull(sample.entity.createPerson)) {
                            htmlStr += "<td></td>";
                        }else {
                            htmlStr += "<td>"+ sample.entity.createPerson +"</td>";
                        }
                        htmlStr += "<td>"+ getLocalTime(new Date(sample.entity.createDatetime)) +"</td>";
                        htmlStr += "<td>";
                        htmlStr += "<span onclick= 'viewSyBtn(this)'>";
                        htmlStr += "<img src='../../automatedWork/img/see.png' alt=''>查看并编辑";
                        htmlStr += "</span>";
                        if (sample.entity.isCreate == "1") {

                        }else {
                            htmlStr += "<span onclick= 'deleteSyBtn(this)'>";
                            htmlStr += "<img src='../../automatedWork/img/tra.png' alt=''>删除";
                            htmlStr += "</span>";
                        }
                        htmlStr += "</td>";
                        htmlStr +="</tr>";
                    }
                    $("#syPlateVoListTbody").append(htmlStr);

                    assignment(data.pageInfo, "getSyPlateList");
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

    function getLocalTime(date) {
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
    }

    function assignment (pageInfo, list) {
        $("#page").val(pageInfo.page);
        $("#pageCount").val(pageInfo.pageCount);
        $("#allRecordCount").val(pageInfo.allRecordCount);
        $("#actionName").val(list);
        $().getKkpager();
    }

    // 实验按钮样式切换
    $(".ed-sp-click span").click(function () {
        $(".ed-sp-click span").removeClass('active-ed-sp');
        $(this).addClass('active-ed-sp')
    })
    $(".ExperimentDetails").click(function () {
        window.location.href = "./ExperimentDetails.html"
    })
})