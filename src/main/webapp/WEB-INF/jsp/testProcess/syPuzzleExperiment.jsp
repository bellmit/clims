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
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <link rel="stylesheet" href="<%=path%>/css/pcrExperiment.css">
    <style>
        .noPadding {
            padding: 0px !important;
        }

        .noBorder {
            border: none !important;
            border-radius: 0px;
        }

        .kong {
            position: static;
        }

        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
            margin-top: 0px !important;
        }

        .popover {
            background: #ffe8e8 !important;;
            color: #ff5a56 !important;;
            border: 1px dashed #ff5a56 !important;;
            font-weight: 600 !important;
        }

        .popover .arrow {
            border-top-color: #ff5a56 !important;
        }

        .popover .arrow::after {

            border-top-color: #ffe8e8 !important;;
        }
    </style>
</head>
<body>
<jsp:include page="testProgressBar.jsp"/>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>可拼接PCR记录</div>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>PCR板</label>
                            <div class="select">
                                <select class="form-control" id="boardNo" name="boardNo">
                                    <option disabled selected value="0">请选择</option>
                                    <c:forEach items="${syList}" var="sy">
                                        <option value="${sy.boardNo}"
                                                <c:if test="${sy.boardNo eq boardNo}">selected</c:if>>${sy.boardNo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 noPadding" style="height:635px;">
        <div class="row Modular noBorder">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading red" style="height: 54.6px">
                        <div style="margin-top: 5px">电泳阶段</div>
                    </div>
                    <div class="panel-body" style="padding-bottom: 0px">
                        <div class="kong">
                            <ol class="clearfix">
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
                                    <%--<li>${list.temp}</li>--%>
                                    <c:forEach items="${tempList.list}" var="list" varStatus="s">
                                        <li>
                                            <c:choose>
                                                <c:when test="${list.samplePostion eq 'A01' || list.samplePostion eq 'B01'
                                            || list.samplePostion eq 'E12'  || list.samplePostion eq 'F12' }">
                                                    <div class="kongBlue">Ladder</div>
                                                </c:when>
                                                <c:when test="${list.samplePostion eq 'C01' || list.samplePostion eq 'G12' }">
                                                    <div class="kongBlue">9947</div>
                                                </c:when>
                                                <c:when test="${list.samplePostion eq 'D01' || list.samplePostion eq 'H12' }">
                                                    <div class="kongBlue">yin</div>
                                                </c:when>
                                                <c:when test="${not empty list.sampleNo}"><div class="kongGreen"></div></c:when>
                                                <c:otherwise><div></div></c:otherwise>
                                            </c:choose>
                                            <input type="hidden" name="id" value="${list.id}"/>
                                            <input type="hidden" name="syId" value="${list.syId}"/>
                                            <input type="hidden" name="sampleId" value="${list.sampleId}"/>
                                            <input type="hidden" name="sampleNo" value="${list.sampleNo}"/>
                                            <c:forEach items="${sampleTypeList}" var="typelist">
                                                <c:if test="${ typelist.dictCode eq list.sampleType }">
                                                    <input type="hidden" name="sampleType" value="${typelist.dictName}"/>
                                                </c:if>
                                            </c:forEach>
                                            <input type="hidden" name="samplePosition" value="${list.samplePostion}"/>
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
    <div class="col-md-4 noPadding">
        <div class="row Modular noBorder">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading blue">
                        <div>待电泳的检材</div>
                        <button class="btn btn-blue-border checkAll" data-toggle="popover" data-placement="top"
                                data-content='选中检材放入到左侧电泳板中' data-trigger="focus">全选
                        </button>
                    </div>
                    <div class="panel-body" style="height: 570px;border-left: 1px solid #d5d5d5;padding-bottom: 0px">
                        <div>
                            <div class="row">
                                <c:forEach items="${labSySampleList}" var="labSySample" varStatus="em">
                                    <div class="col-md-6 nopadding">
                                        <label class="custom-control checkbox-inline nopadding">
                                            <input class="custom-control-input sampleNo" type="checkbox" name="checkAll" value="${labSySample.sampleNo}"
                                                   samplePostion="" sampleId="${labSySample.sampleId}" sampleName="${labSySample.sampleName}" sampleType="${labSySample.sampleType}">
                                            <span class="custom-control-label">${labSySample.sampleName}</span>
                                        </label>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row" style="padding: 20px 0">
    <div class="col-md-12">
        <button class="btn btn-yellow btn-lang center-block buildTask" data-toggle="popover" data-placement="top"
                data-content='电泳完成点击生成任务' data-trigger="focus">生成任务
        </button>
    </div>
</div>
<div class="row Modular " style="padding-bottom: 60px">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>生成电泳板</div>
            </div>
            <div class="panel-body"  style="display: none" id="GENERATION_TASK">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>板号</th>
                        <th>实验人</th>
                        <th>检材数</th>
                        <th>实验</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>01</td>
                        <td><input type="text" class="form-control" id="boardNos"></td>
                        <td id="operateUser">${operateUser}</td>
                        <td>${labSySampleListSize}</td>
                        <td>
                            <button class="btn btn-sm btn-blue-border inenterExperiment">进入实验</button>
                        </td>
                        <td>
                            <button type="button" name="deleteBtn" class="btn-icon btn-icon-red btn-icon-shanchu-red"> 删除
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12" style="text-align: center">
        <input type="hidden" name="syId" id="syId" value="${syId}">
        <input type="hidden" name="taskId" id="taskId" value="${taskId}">
        <input type="hidden" name="task" id="task" value="${task}">
        <input type="hidden" name="many" id="many" value="${many}">
        <input type="hidden" name="operateType" id="operateType" value="${operateType}">
        <input type="hidden" name="pcrexperimentDate" id="pcrexperimentDate" value="${pcrexperimentDate}">
    </div>
</div>
<div class="modal fade" id="recordModal" aria-hidden="true" data-backdrop="static"
     data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">
                    消息提示
                </h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal tasi-form">
                    <div class="form-group m-bot20"></div>
                    <div class="form-group m-bot20">
                        <div class="col-md-12 text-center">
                            <h3 class="alert alert-success"><Strong>保存成功！</Strong></h3>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <i class="fa fa-hand-o-right"></i>
                <button class="btn btn-default btn-sm" type="button" id="finishedBtn"><i
                        class="fa fa-list-alt"></i> 完成
                </button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        $('[data-toggle="popover"]').popover()
        $('[data-toggle="popover"]').popover('show')
        $("#saveNextBtn").on("click", function () {
            location.href = "<%=path%>/center/syExperiment";
        });
        $(".popover").click(function () {
            $(this).prev().popover('destroy')
        })

        $(".inenterExperiment").on("click",function () {
            var boar = $("#boardNos").val();
            if(boar == undefined || boar == null || boar == "" || boar.trim() == ""){
                alert("填写版号")
            }else{
                saveForm();
            }
        })

        /** 删除扩增检材实验记录 */
        $("button[name='deleteBtn']", "#samplelist_tbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent()).val();
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabPcrSample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if (data.success || data.success == true || data.success == "true") {
                            location.href = "<%=path%>/center/refreshPcrExperiment?pcrId=" + data.pcrId;
                        }
                    }
                });
            }
        });

        $("#finishedBtn").on("click", function () {
            $("#recordModal").modal('hide');
        });

        /** 验证必填项 */
        function checkInputValidation() {
            var checkCount = 0;
            var $sampleTR = $("tr", "#samplelist_tbody");
            var sampleCnt = $sampleTR.length;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            }

            if (checkCount <= 0) {
                alert("至少选中一个检材！");
                return false;
            }

            return true;
        }

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh',
            weekStart: 1,
            todayBtn: 1,
            minView: "month",
            autoclose: true,
            todayHighlight: true,
            forceParse: 0,
            showMeridian: 1
        }).on('changeDate', function (ev) {
            $(this).datetimepicker('hide');
        });


        $(".checkAll").click(function () {
            if ($("input[type='checkbox']:checked").length == $("input[type='checkbox']").length) {
                $("input[type='checkbox']").prop("checked", false)
            } else {
                $("input[type='checkbox']").prop("checked", true)
            }
            $(this).popover('destroy')
        })

        $(".buildTask").click(function(){
            $(this).popover('destroy')
            $("#GENERATION_TASK")[0].style.display = "block";
            if($("select[name='boardNo'] option:selected").val() == 0){
                $("#boardNos").attr("readonly", false);
            }else{
                $("#boardNos").attr("readonly",true);
                $("#boardNos").val($("select[name='boardNo'] option:selected").val());
            }

        })

        /** 保存电泳实验记录 */
        $("#saveBtn").on("click", function () {
            saveForm();
        });

        function saveForm() {
            $.ajax({
                url: "<%=path%>/center/saveSyInfo?operateType=" + $("#operateType").val() + "&taskId=" + $("#taskId").val() + "&syEndDatetime=" + $("#pcrexperimentDate").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#syId").val(data.syId);
                        saveFormSample();
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        function param() {
            var sample = {};
            sample.boardNo = $("#boardNos").val();
            sample.syPerson1 = $("#operateUser").text();
            return sample;
        }

        function saveFormSample() {
           $.ajax({
                url: "<%=path%>/center/saveSyExperiment?operateType=" + $("#operateType").val() + "&taskId=" + $("#task").val() + "&syId=" + $("#syId").val(),
                type: "post",
                data: JSON.stringify(paramSample()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#operateType").val("2")
                        // $("#recordModal").modal('show');
                        location.href = "<%=path%>/center/enterExperiment?taskId=" + $("#task").val() + "&taskStage=3";
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        function paramSample() {
            var sampleArr = new Array();
            var $sampleNo = $(".hidden").find(".sampleNo");
            var sampleCnt = $sampleNo.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $sampleNo.get(i);
                if (checkBox) {
                    sample.sampleNo = $sampleNo.get(i).value;
                    sample.syId = $("#syId").val();
                    sample.sampleId = $sampleNo.get(i).getAttribute("sampleId");
                    sample.samplePostion = $(".kongGreen").siblings("input[name='sampleId'][value='"+ sample.sampleId+"']").siblings("input[name='samplePosition']").val();
                    sample.sampleName = $sampleNo.get(i).getAttribute("sampleName");
                    sample.sampleType = $sampleNo.get(i).getAttribute("sampleType");
                    sampleArr.push(sample);
                }
            }
            console.log(sampleArr)
            return sampleArr;
        }

        if($("#many").val() == "1"){
            alert("请重新选板");
            location.href = "<%=path%>/center/reinspectionSyExperiment?taskId=" + $("#taskId").val();
        }
        //通过板号获取数据
        $("#boardNo").change(function () {
            location.href = "<%=path%>/center/syPuzzleData?taskId=" + $("#taskId").val() + "&boardNo=" + $("#boardNo").val();
        })

    })
    $("li").find("div").click(function () {
        if (!$(this).attr("class")) {
            if ($("input[type='checkbox']:checked").length > 0) {
                var thisIndex = 9 - $(this).parent().index()
                var ulIndex = ($(this).parents("ul").index() + 1) * 9
                $(".kong").find("li").each(function (i) {
                    if (i >= ulIndex - thisIndex && $(this).children("div").attr("class") == null && $("input[type='checkbox']:checked").length != 0 && $(this).index() != 0) {
                        $(this).children("div").addClass("kongGreen").siblings("input[name='sampleId']").val($("input[type='checkbox']:checked").eq(0).attr("sampleId"))
                        $("input[type='checkbox']:checked").eq(0).prop("checked", false).parents(".col-md-6").addClass("hidden")
                    }
                })
            } else {
                alert("请选择检材")
            }
        } else {
            if ($("input[type='checkbox'][sampleid='" + $(this).siblings("input[name='sampleId']").val() + "']").length > 0) {
                $(this).removeClass("kongGreen")
                $("input[type='checkbox'][sampleid='" + $(this).siblings("input[name='sampleId']").val() + "']").parents(".col-md-6").removeClass("hidden")
            } else {
                alert("该检材不是本次添加")
            }
        }
    })
    function addKongGreen(addGreen) {
        if($(addGreen).hasClass("kongGreen")){
            alert("此孔已有检材");
        }else{
            $(addGreen).addClass("kongGreen");
            var $sampleNo = $(".sampleNo");
            var sampleCnt = $sampleNo.length;
            for (var i = 0; i < sampleCnt; i++) {
                if($sampleNo.get(i).checked){
                    var samplePostion = $sampleNo.get(i).getAttribute("samplePostion");
                    if(samplePostion == null || samplePostion == undefined || samplePostion == ""){
                        $sampleNo.get(i).setAttribute("samplePostion",$(addGreen).attr("value"))
                        return
                    }
                }
            }
        }
    }
</script>
</body>

</html>