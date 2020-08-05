<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医DNA实验室管理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <link rel="stylesheet" href="<%=path%>/css/pcrExperiment.css">
    <style>
        .Modular {
            background: none !important;
        }

        .kitBox > div {
            padding: 0 55px;
            padding-left: 70px;
        }

        .kitBox > div > div {
            height: 66px;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            margin-bottom: 20px;
        }

        .kitBox > div > div,
        .deviceBox > div > div {
            /*height: 170px;*/
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            margin-bottom: 20px;
        }

        .kitBox > div > div .col-md-12 {
            margin: 15px 0;
        }

        .btnCheck {
            height: 34px;
            width: 67px;
            line-height: 34px;
            text-align: center;
            background: #0c81f5;
            border-radius: 50px;
            color: #fff;
            position: relative;
        }

        .btnCheck::before {
            font: normal normal normal 14px/1 FontAwesome;
            content: "\f058";
            color: #50c987;
            position: absolute;
            right: -2px;
            top: -5px;
        }

        .noPadding {
            padding: 0px !important;
        }

        .noBorder {
            border: none !important;
        }

        .btn-checkbox > li {
            margin-left: 20px !important;
        }

        .btn-box {
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -15px !important;
            text-align: right;
            z-index: 11;
            box-shadow: 0px 0px 10px 5px #ebebeb;
        }

        #holePosition {
            height: 610px;
            /*padding-bottom: 65px;*/
        }

        .panel-heading .btn {
            border-radius: 0px !important;
        }

        .btn:hover,
        .btn:active,
        .btn:focus {
            outline: none !important;
        }

        #kitPopBox .modal-title {
            text-align: center;
            color: #0c81f5;
            font-weight: 600;
        }

        #kitPopBoxTwe .modal-title {
            text-align: center;
            color: #0c81f5;
            font-weight: 600;
        }

        .modal-header {
            background: #f5f5f5;
        }

        @media (min-width: 768px) {
            .modal-sm {
                width: 410px !important;
            }
        }

        #kitPopBox .modal-content {
            border-radius: 0px;
            border: none;
        }

        #kitPopBox .modal-body {
            max-height: 300px;
            overflow-y: auto;
        }

        #kitPopBox .btn-checkbox > li {
            margin-left: 15px;
        }

        #kitPopBoxTwe .btn-checkbox > li {
            margin-left: 15px;
        }

        #kitPopBox .btn-checkbox > li + li {
            margin-left: 15px;
        }

        #kitPopBoxTwe .btn-checkbox > li + li {
            margin-left: 15px;
        }

        #kitPopBox .modal-footer {
            text-align: center;
        }

        #kitPopBoxTwe .modal-footer {
            text-align: center;
            position: fixed;
            width: 100%;
            background: #fff;
        }
        .kongYellow{
            background: #ffb802 !important;
            border-color: #ffb802 !important;
        }
        #currentList{
            width: 100%;
            height: 100%;
        }
        #currentList .col-md-6{
            margin-top: 25px;
            text-align: center;
        }
        #currentList .col-md-6 span:first-child{
            display: inline-block;
            width: 115px;
            text-align: right;
            font-size: 17px;
            color: #8b8b8b;
            margin-right: 10px;
            font-weight: 600;
        }
        #currentList .col-md-6 span:first-child:after{
            content: ":";
            display: inline-block;
        }
        #currentList .col-md-6 span:last-child{
            display: inline-block;
            width: 160px;
            text-align: left;
            font-size: 17px;
        }
        #currentList .col-md-6:first-child{
            color: #fda228;
            font-size:25px;
            font-weight: 600;
        }
        .relativeLi{
            position: relative;
            margin:0 10px;
            height: 33px !important;
            line-height: 33px !important;
            margin-top: 12px !important;
            width: 100px !important;
            margin: 12px 3px !important;
        }
        input.jiancaiNum{
            font-size: 10px;
            position: absolute;
            bottom: 0;
            background: none;
            border: none;
            left: 50%;
        }
        #holePosition > div:nth-child(1){
            padding-left: 1300px !important;
        }
        .kong{
            width: 1300px !important;
        }
        .kong ul li div{
            width: 100px !important;
            height: 30px !important;
            margin: 0 4px !important;
            border-radius:0 !important;
            line-height: 30px !important;
        }
        .kong ol li{
            height: 40px !important;
            line-height: 40px !important;

        }
        .kong ul:nth-child(2n){
            border: none;
        }
        .kong ul li:nth-child(2n-1){
            border: none;
        }
        .kong ol li:nth-child(2n-1){
            border: none;
        }
        .jiancaiNo{
            width: 100px;
            height: 30px;
            border: none;
            background: none;
            text-align: center;
            white-space: nowrap;
            font-size: 8px;
        }
    </style>
    <jsp:include page="testProgressBar.jsp"/>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12" style="height:635px;background: #fff;border-radius: 5px;padding-bottom: 63px">
        <div class="panel panel-default">
            <div class="panel-heading red">
                <div>电泳阶段</div>
                <div>
                    <input type="text" class="form-control" placeholder="请输入板号" id="boardNo">
                </div>
                <button class="btn btn-yellow inenterExperiment">生成任务</button>
            </div>
            <div class="panel-body">
                <div class="row" style="position: relative" id="holePosition">
                    <div>
                        <div class="row" id="graph">
                            <div class="col-md-12">
                                当前选中位置:<span id="span_SamplePosition"></span>
                            </div>
                            <div class="col-md-12">
                                <span>检材编号:</span><span id="span_SampleNo"></span>
                            </div>
                            <div class="col-md-12">
                                <span>检材名称:</span><span id="span_SampleName"></span>
                            </div>
                            <input type="hidden" id="span_id" name="id" value=""/>
                            <input type="hidden" id="span_pcrId" name="pcrId" value=""/>
                            <input type="hidden" id="span_sampleId" name="sampleId" value=""/>
                        </div>
                    </div>
                    <div class="kong">
                        <ol class="clearfix" style="margin-top: -24px;">
                            <li></li>
                            <li>A</li>
                            <li>B</li>
                            <li>C</li>
                            <li>D</li>
                            <li>E</li>
                            <li>F</li>
                            <li>G</li>
                            <li>H</li>
                        </ol>
                        <c:forEach items="${tempList}" var="tempList" varStatus="s">
                            <ul class="clearfix">
                                <li><c:if test="${s.index+1 < 10}">0</c:if>${s.index+1}</li>
                                <c:forEach items="${tempList.list}" var="list" varStatus="s">
                                    <li class="relativeLi">
                                        <c:choose>
                                            <%--<c:when test="${list.samplePostion eq 'A01' || list.samplePostion eq 'B01'--%>
                                            <%--|| list.samplePostion eq 'E12'  || list.samplePostion eq 'F12' }">--%>
                                                <%--<div class="kongBlue">Ladder</div>--%>
                                            <%--</c:when>--%>
                                            <%--<c:when test="${list.samplePostion eq 'C01' || list.samplePostion eq 'G12' }">--%>
                                                <%--<div class="kongBlue">9947</div>--%>
                                            <%--</c:when>--%>
                                            <%--<c:when test="${list.samplePostion eq 'D01' || list.samplePostion eq 'H12' }">--%>
                                                <%--<div class="kongBlue">yin</div>--%>
                                            <%--</c:when>--%>
                                            <c:when test="${list.samplePostion eq 'A01'|| list.samplePostion eq 'A07'}">
                                                <div class="kongBlue">Ladder</div>
                                            </c:when>
                                            <c:when test="${list.samplePostion eq 'B01' || list.samplePostion eq 'B07' }">
                                                <div class="kongBlue">9947</div>
                                            </c:when>
                                            <c:when test="${list.samplePostion eq 'C01' || list.samplePostion eq 'C07' }">
                                                <div class="kongBlue">yin</div>
                                            </c:when>

                                            <c:otherwise>
                                                <div id="otherwise">
                                                    <input type="text" class="jiancaiNo" name="jiancaiNo"/>
                                                    <input type="hidden" name="samplePostion" value="${list.samplePostion}"/>
                                                    <input type="hidden" name="sampleId" value="${list.sampleId}"/>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>


<script>
    $(function () {
        $(".btn-checkbox").on("click", 'li', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active").siblings().removeClass("active")
            }
        })

        //生成电泳任务
        $(".inenterExperiment").on("click", function () {
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "" || typeof(boardNo) == "undefined") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            var labSyInfo = GetLabSyInfo();

            $.ajax({
                url: "<%=path%>/handwork/selectBoardNoIsExist",
                type: "post",
                data: JSON.stringify(labSyInfo),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        saveForm();
                    } else {
                        alert("该板已存在")
                    }
                }
            });

            /*$.ajax({
                url: "<%=path%>/handwork/selectBoardNoIsExist?boardNo=" + boardNo,
                type: "post",
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:function(data){
                    if(data.success=="重复"){
                        alert("该板已存在");
                    }else if(data.success || data.success == true || data.success == "true") {
                        saveForm();
                    }
                }
            });*/
        })

        //生成任务保存
        function saveForm() {

            var labSyInfo = GetLabSyInfo();
            var labSySampleList = GetLabSySample();

            var params = {
                "labSyInfo": labSyInfo,
                "labSySampleList": labSySampleList
            };

            $.ajax({
                url: "<%=path%>/handwork/saveSyInfo",
                type: "post",
                data: JSON.stringify(params),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/handwork/syExperiment?syId=" + data.syId;
                    }
                }
            });
        }

        function GetLabSySample() {
            var sampleArr = new Array();
            var $sampleUL =  $(".clearfix").find(".relativeLi");
            var sampleCnt = $sampleUL.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};

                sample.sampleId = $("input[name='sampleId']", $sampleUL.get(i)).val();
                sample.samplePostion = $("input[name='samplePostion']", $sampleUL.get(i)).val();

                if (sample.sampleId != null && typeof(sample.sampleId) != "undefined" && sample.sampleId != "") {
                    sampleArr.push(sample);
                }

            }

            return sampleArr;
        }

        function GetLabSyInfo() {
            var labSyInfo = {};

            labSyInfo.boardNo = $("#boardNo").val();
            labSyInfo.syStage = "0";

            return labSyInfo;
        }


        /** 验证必填项 */
        function checkInputValidation() {
            var $sampleUL =  $(".clearfix").find(".relativeLi");
            var sampleCnt = $sampleUL.length;
            var sampleId = "";
            var flag = false;
            for (var i = 0; i < sampleCnt; i++) {
                sampleId = $("input[name='sampleId']", $sampleUL.get(i)).val();

                if (sampleId != null && typeof(sampleId) != "undefined" && sampleId != "") {
                    flag = true;
                    break;
                }

            }

            if (!flag) {
                alert("至少添加一种检材！");
                return false;
            }

            return true;
        }

        //鼠标移除查询检材
        $(".jiancaiNo").on("change", function () {
            var sampleNo = $("input[name='jiancaiNo']", $(this).parent()).val();
            var samplePostion = $("input[name='samplePostion']", $(this).parent()).val();

            var $this = $(this);
            if (sampleNo == null || typeof(sampleNo) == "undefined" || sampleNo == ""){
                alert("请输入检材编号！")
            }else {
                var sampleId = "";
                $.ajax({
                    url: "<%=path%>/handwork/selectSampleNo?&sampleNo=" + sampleNo,
                    type: "post",
                    data: {},
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        if (data.success || data.success == true || data.success == "true") {
                            $('#span_SampleNo').html(sampleNo);
                            $('#span_SampleName').html(data.limsSampleInfoDna.sampleName);
                            $('#span_SamplePosition').html(samplePostion);

                            $("input[name='sampleId']", $this.parent()).val(data.limsSampleInfoDna.sampleId);
                        } else {
                            alert("没有该检材信息！");
                        }
                    },
                    error: function (e) {
                        alert("操作失败！");
                    }
                });
            }
        })

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh',
            weekStart: 1,
            todayBtn: 1,
            minView: "hour",
            autoclose: true,
            todayHighlight: true,
            forceParse: 0,
            showMeridian: 1
        }).on('changeDate', function (ev) {
            $(this).datetimepicker('hide');
        });
    });

    function addKit() {
        var kit = $(".checkedKit");
        var act = $(".active");
        var list = [];
        var listOf = [];
        var pkKit = "";
        for (var i = 0; i < kit.length; i++) {
            for (var j = 0; j < act.length; j++) {
                if (kit.get(i) == act.get(j)) {
                    list.push(kit.get(i).getAttribute("batchNumber"));
                    listOf.push(kit.get(i).getAttribute("termOfValidity"));
                }
            }
        }
        for (var i = 0; i < list.length; i++) {
            pkKit += "<div class=\"col-md-12\">\n" +
                "                <div class=\"col-md-2\">\n" +
                "                <div class=\"btnCheck\">Pk</div>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-5\">\n" +
                "                <div class=\"form-group row\">\n" +
                "                <label class=\"col-md-3 control-label noPadding\">批号:</label>\n" +
                "            <div class=\"col-md-9 noPadding\">\n" +
                "                <input name='batchb' type=\"text\" class=\"form-control\" readonly='' value=\"" + list[i] + "\">\n" +
                "                </div>\n" +
                "                </div>\n" +
                "                </div>\n" +
                "                <div class=\"col-md-5\">\n" +
                "                <div class=\"form-group row\">\n" +
                "                <label class=\"col-md-3 control-label noPadding\">有效期:</label>\n" +
                "            <div class=\"col-md-9 noPadding\">\n" +
                "                <input name='validity' type=\"text\" class=\"form-control\" readonly='' value=\"" + listOf[i] + "\">\n" +
                "                </div>\n" +
                "                </div>\n" +
                "                </div>\n" +
                "                </div>"
        }
        // for(var selectKit of list){
        //
        // }
        $("#PkKit").empty()
        $("#PkKit").append(pkKit);
    }
    // 输入检材编号,回车跳转到下一行
    $(".jiancaiNo").keyup(function(event){
        if(event.keyCode ==13){

            if ($(this).parents(".relativeLi").next().find(".jiancaiNo").length != 0){
                $(this).parents(".relativeLi").next().find(".jiancaiNo").focus()
            } else {
                $(this).parents(".clearfix").next().find(".jiancaiNo").eq(0).focus()
            }
        }
    });
</script>
</body>

</html>