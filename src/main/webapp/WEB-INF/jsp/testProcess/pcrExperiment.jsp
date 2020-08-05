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
    </style>
    <%--<jsp:include page="testProgressBar.jsp"/>--%>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>扩增记录详情</div>
            </div>
            <div class="panel-body">
                <%-- <c:forEach items="${labPcrInfolist}" var="pcrinfo" varStatus="s">--%>
                <div class="row">
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>扩增板号</label>
                            <input type="text" name="boardNo" id="boardNo" value="${boardNo}" class="form-control"
                                   placeholder="请输入扩增板号">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>扩增仪</label>
                            <div class="select">
                                <%--<select name="pcrInstrument" value="${labPcrInfo.pcrInstrument}" class="form-control">--%>
                                <select name="pcrInstrument" value="${labPcrInfo.pcrInstrument}" class="form-control">
                                    <option value="">请选择</option>
                                    <c:forEach items="${panelListKZY}" var="system" varStatus="s">
                                        <option value="${system.equipmentName}"
                                                <c:if test="${labPcrInfo.pcrInstrument eq system.equipmentName}">selected</c:if>>
                                                ${system.equipmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>操作人</label>
                            <input type="text" name="pcrPerson1" value="${operateUser}" class="form-control"
                                   placeholder="请输入操作人">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>操作时间</label>
                            <input type="text" id="pcrEndDatetime" name="pcrEndDatetime" value="${pcrexperimentDate}"
                                   class="form-control form_datetime" placeholder="请输入操作时间">
                        </div>
                    </div>
                    <%--<div class="col-md-4 seachInputBox">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>测序仪</label>--%>
                            <%--<div class="select">--%>
                                <%--<select name="pcrProgram" value="${labPcrInfo.pcrProgram}" class="form-control">--%>
                                    <%--<option value="">请选择</option>--%>
                                    <%--<c:forEach items="${equipmentNameInfo}" var="pcrInstrument" varStatus="pi">--%>
                                        <%--<option value="${pcrInstrument.equipmentName}"--%>
                                                <%--<c:if test="${labPcrInfo.pcrProgram eq pcrInstrument.equipmentName}">selected</c:if>>${pcrInstrument.equipmentName}</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue noBorder">
                <div>选择试剂盒</div>
                <button class="btn btn-blue-border" data-toggle="modal" data-target="#kitPopBox" onclick="addEcho()">添加
                </button>
            </div>
            <div class="row kitBox">
                <div class="col-md-8">
                    <div class="row" id="PkKit">
                        <c:if test="${labPcrInfo.batchb != null || labPcrInfo.validity != null}">
                            <div class="col-md-12">
                                <div class="col-md-2">
                                    <div class="btnCheck">${labPcrInfo.pcrReagent}</div>
                                    <input type="hidden" class="tagsId">
                                </div>
                                <div class="col-md-5">
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label noPadding">批号:</label>
                                        <div class="col-md-9 noPadding">
                                            <input type="text" class="form-control" name="batchb"
                                                   readonly="readonly" value="${labPcrInfo.batchb}">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-5">
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label noPadding">有效期:</label>
                                        <div class="col-md-9 noPadding">
                                            <input type="text" class="form-control" name="validity"
                                                   readonly="readonly" value="${labPcrInfo.validity}">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="kitPopBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">选择试剂盒</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <ul class="btn-checkbox clearfix">
                        <c:forEach items="${reagentInfoList}" var="panlist">
                            <c:set var="flagKit" value="true"></c:set>
                            <c:if test="${panelKit == null}">
                                <li class="pull-left checkedKit" panelid="${panlist.id}"
                                    batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                    termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">${panlist.reagentName}</li>
                                <c:set var="flagKit" value="false"></c:set>
                            </c:if>
                            <c:forEach items="${panelKit}" var="list">
                                <c:if test="${panlist.id eq list}">
                                    <li class="pull-left checkedKit active" panelid="${panlist.id}"
                                        batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                        termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">${panlist.reagentName}</li>
                                    <c:set var="flagKit" value="false"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flagKit}">
                                <li class="pull-left checkedKit" panelid="${panlist.id}"
                                    batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                    termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">${panlist.reagentName}</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-blue btn-lang" onclick="addKit()" data-dismiss="modal">确认</button>
                <button class="btn btn-blue-border btn-lang" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="col-md-4 seachInputBox">
    <div class="form-group">
        <label>扩增体系</label>
        <div class="select">
            <input type="hidden" name="pcrSystemHidden" value="${labPcrInfo.pcrSystem}">
            <select name="pcrSystem" value="${labPcrInfo.pcrSystem}" class="form-control">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12" style="height:635px;background: #fff;border-radius: 5px;padding-bottom: 63px">
        <div class="panel panel-default">
            <div class="panel-heading red">
                <div>扩增阶段(图形展示)</div>
                <span class="tools">
                      <button class="btn btn-yellow-border btn-sm pull-right tabBtn">列表展示</button>
                      <button class="btn btn-yellow btn-sm pull-right" style="margin-left: 15px">图形展示</button>
                    <%--<button class="btn btn-red btn-sm pull-right">复检3</button>--%>
                 </span>
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
                                <span>检材类型:</span><span id="span_SampleName"></span>
                            </div>
                            <div class="col-md-12">
                                <span> Primer Set:</span><input id="span_Primer" type="text" value="">
                            </div>
                            <div class="col-md-12">
                                <span> Master Mix:</span><input id="span_Buffer" type="text">
                            </div>
                           <%-- <div class="col-md-12">
                                <span> TaqE ul:</span><input id="span_TaqE" type="text">
                            </div>--%>
                            <div class="col-md-12">
                                <span> DNA:</span><input id="span_Template" type="text">
                            </div>
                            <div class="col-md-12">
                                <span> H₂O:</span><input id="span_H₂O" type="text">
                            </div>
                           <%-- <div class="col-md-12">
                                <span> MgCl₂ ul:</span><input id="span_MgCl₂" type="text">
                            </div>
                            <div class="col-md-12">
                                <span> dNTP ul:</span><input id="span_dNTP" type="text">
                            </div>--%>
                            <input type="hidden" id="span_id" name="id" value=""/>
                            <input type="hidden" id="span_pcrId" name="pcrId" value=""/>
                            <input type="hidden" id="span_sampleId" name="sampleId" value=""/>
                        </div>
                    </div>
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
                                <%--<li>${list.temp}</li>--%>
                                <li><c:if test="${s.index+1 < 10}">0</c:if>${s.index+1}</li>
                                <c:forEach items="${tempList.list}" var="list" varStatus="s">
                                    <li>
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

                                            <c:when test="${list.samplePostion eq 'A01' || list.samplePostion eq 'A07' }">
                                                <div class="kongBlue">Ladder</div>
                                            </c:when>
                                            <c:when test="${list.samplePostion eq 'B01' || list.samplePostion eq 'B07' }">
                                                <div class="kongBlue">9947</div>
                                            </c:when>
                                            <c:when test="${list.samplePostion eq 'C01' || list.samplePostion eq 'C07' }">
                                                <div class="kongBlue">yin</div>
                                            </c:when>
                                            <c:when test="${not empty list.sampleNo}">
                                                <div class="kongGreen" name="zxc"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div></div>
                                            </c:otherwise>
                                        </c:choose>
                                        <input type="hidden" name="id" value="${list.id}"/>
                                        <input type="hidden" name="pcrId" value="${list.pcrId}"/>
                                        <input type="hidden" name="sampleId" value="${list.sampleId}"/>
                                        <input type="hidden" name="sampleNo" value="${list.sampleNo}"/>
                                        <c:forEach items="${sampleTypeList}" var="typelist">
                                            <c:if test="${ typelist.dictCode eq list.sampleType }">
                                                <input type="hidden" name="sampleType" value="${typelist.dictName}"/>
                                            </c:if>
                                        </c:forEach>
                                        <input type="hidden" name="samplePosition" value="${list.samplePostion}"/>
                                        <input type="hidden" name="primer" value="${list.primer}"/>
                                        <input type="hidden" name="buffer" value="${list.buffer}"/>
                                        <input type="hidden" name="taqe" value="${list.taqe}"/>
                                        <input type="hidden" name="template" value="${list.template}"/>
                                        <input type="hidden" name="h2o" value="${list.h2o}"/>
                                        <input type="hidden" name="mgcl2" value="${list.mgcl2}"/>
                                        <input type="hidden" name="dntp" value="${list.dntp}"/>
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
<div class="row Modular " style="display: none">
    <div class="col-md-12" style="padding-bottom: 63px">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>列表展示</div>
                <button class="btn btn-yellow btn-sm pull-right ">列表展示</button>
                <button class="btn btn-yellow-border btn-sm pull-right tabBtn">图形展示</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th style='width:8%'>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" name="checkAll" checked>
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th style='width:6.1%'>位置</th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th style='width:8.5%'>检材类型</th>
                        <th>Primer Set</th>
                        <th>Master Mix</th>
                        <%--<th>TaqE ul</th>--%>
                        <th>DNA</th>
                        <th>H₂O</th>
                        <%--<th>MgCl₂ ul</th>--%>
                        <%--<th>dNTP ul</th>--%>
                        <th style="width:70px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="samplelist_tbody">
                    <c:forEach items="${labPcrSampleList}" var="sample" varStatus="s">
                        <tr id="trr" value="${sample.samplePostion}">
                            <td>
                                <label class="custom-control checkbox-inline">
                                    <input class="custom-control-input" type="checkbox" name="box" checked><span
                                        class="custom-control-label"></span>
                                </label>
                            </td>
                            <td>
                                <input name="samplePostion" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.samplePostion}"
                                       readonly="readonly">
                            </td>
                            <td title="${sample.sampleNo}">
                                <input name="sampleNo" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.sampleNo}"
                                       readonly="readonly">
                            </td>
                            <td title="${sample.sampleName}">
                                <input name="sampleName" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.sampleName}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <select class="form-control small" disabled name="sampleType">
                                    <c:forEach items="${sampleTypeList}" var="list">
                                        <option value="${list.dictCode}"
                                                <c:if test="${sample.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input name="primer" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.primer}">
                            </td>
                            <td>
                                <input name="buffer" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.buffer}">
                            </td>
                            <%--<td>
                                <input name="taqe" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.taqe}">
                            </td>--%>
                            <td>
                                <input name="template" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.template}">
                            </td>

                            <td>
                                <input name="h2o" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.h2o}">
                            </td>
                           <%-- <td>
                                <input name="mgcl2" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.mgcl2}">
                            </td>
                            <td>
                                <input name="dntp" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.dntp}">
                            </td>--%>
                            <td>
                                <input type="hidden" name="id" value="${sample.id}"/>
                                <input type="hidden" name="sampleId" value="${sample.sampleId}"/>
                                <button type="button" name="deleteBtn"
                                        class="btn-icon btn-icon-red btn-icon-shanchu-red"> 删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12">
        <input type="hidden" name="pcrId" id="pcrId" value="${pcrId}">
        <input type="hidden" name="taskId" id="taskId" value="${taskId}">
        <input type="hidden" name="operateType" id="operateType" value="${operateType}">
        <button class="btn btn-blue" type="button" id="saveBtn" value="0">保存</button>
        <button class="btn btn-blue" type="button" name="record" id="record">生成记录表</button>
        <button class="btn btn-yellow" type="button" id="saveNextBtn">完成并进入下一步</button>
        <input type="hidden" name="pcrReagent" value="${labPcrInfo.pcrReagent}">
        <%--<button class="btn btn-blue-border" type="button">返回</button>--%>
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
        $(".btn-checkbox").on("click", 'li', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active").siblings().removeClass("active")
            }
        })
        //完成并保存
        $("#saveNextBtn").on("click", function () {
            if ($("#saveBtn").val() == "1") {
                //列表保存
                saveFormAndNext1();
            } else if ($('#span_id').val() != "") {
                //图形保存  修改时的保存
//                saveFormAndNext3();
                //图形保存  默认保存全部
                saveFormAndNext2();
            } else {
                //图形保存  默认保存全部
                saveFormAndNext2();
            }

        });

        /*列表保存*/
        function saveFormAndNext1() {
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }
            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(params()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    location.href = "<%=path%>/center/syExperiment?pcrId=" + $("#pcrId").val();
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        /*图形保存*/
        function saveFormAndNext2() {
            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }
            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(params()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    location.href = "<%=path%>/center/syExperiment?pcrId=" + $("#pcrId").val();
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                }
            });
        }

        function saveFormAndNext3() {
            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }
            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(graph()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    location.href = "<%=path%>/center/syExperiment?pcrId=" + $("#pcrId").val();
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                }
            });
        }
//        列表input赋值
        $('input[name="primer"]').change(function(){
            console.log($(this).val());
        })
        //Primer ul赋值
        $("#span_Primer").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='primer']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='primer']").val($(this).val())
        })
        $("tbody").find("input[name='primer']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='primer']").val($(this).val())
        })
        //Buffer ul赋值
        $("#span_Buffer").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='buffer']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='buffer']").val($(this).val())
        })
        $("tbody").find("input[name='buffer']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='buffer']").val($(this).val())
        })
        //TaqE ul赋值
        $("#span_TaqE").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='taqe']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='taqe']").val($(this).val())
        })
        $("tbody").find("input[name='taqe']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='taqe']").val($(this).val())
        })
        //模板 ul赋值
        $("#span_Template").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='template']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='template']").val($(this).val())
        })
        $("tbody").find("input[name='template']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='template']").val($(this).val())
        })
        //H₂O ul赋值
        $("#span_H₂O").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='h2o']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='h2o']").val($(this).val())
        })
        $("tbody").find("input[name='h2o']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='h2o']").val($(this).val())
        })
        //MgCl₂ ul赋值
        $("#span_MgCl₂").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='mgcl2']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='mgcl2']").val($(this).val())
        })
        $("tbody").find("input[name='mgcl2']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='mgcl2']").val($(this).val())
        })
        //dNTP ul赋值
        $("#span_dNTP").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").siblings("input[name='dntp']").val($(this).val())
            $("tbody").find("input[name='sampleNo'][value='" + $("#span_SampleNo").html() + "']").parents("tr").find("input[name='dntp']").val($(this).val())
        })
        $("tbody").find("input[name='dntp']").change(function () {
            $("li").find("input[name='sampleNo'][value='" + $(this).parents("tr").find("input[name='sampleNo']").val() + "']").siblings("input[name='dntp']").val($(this).val())
        })
//        $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
//            e.target // 激活的标签页
//            e.relatedTarget // 前一个激活的标签页
//        })

        /** 获取检材信息 */
        $(".kongGreen").on("click", function () {
            $(".kongYellow").removeClass("kongYellow")
            $(this).addClass("kongYellow")
            var id = $("input[name='id']", $(this).parent()).val();
            $('#span_id').val(id);
            var pcrId = $("input[name='pcrId']", $(this).parent()).val();
            $('#span_pcrId').val(pcrId);
            var sampleId = $("input[name='sampleId']", $(this).parent()).val();
            $('#span_sampleId').val(sampleId);
            var sampleNo = $("input[name='sampleNo']", $(this).parent()).val();
            $('#span_SampleNo').html(sampleNo);
            var sampleType = $("input[name='sampleType']", $(this).parent()).val();
            $('#span_SampleName').html(sampleType);
            var samplePosition = $("input[name='samplePosition']", $(this).parent()).val();
            $('#span_SamplePosition').html(samplePosition);
            var primer = $("input[name='primer']", $(this).parent()).val();
            $('#span_Primer').val(primer);
            var buffer = $("input[name='buffer']", $(this).parent()).val();
            $('#span_Buffer').val(buffer);
            var taqe = $("input[name='taqe']", $(this).parent()).val();
            $('#span_TaqE').val(taqe);
            var template = $("input[name='template']", $(this).parent()).val();
            $('#span_Template').val(template);
            var h2o = $("input[name='h2o']", $(this).parent()).val();
            $('#span_H₂O').val(h2o);
            var mgcl2 = $("input[name='mgcl2']", $(this).parent()).val();
            $('#span_MgCl₂').val(mgcl2);
            var dntp = $("input[name='dntp']", $(this).parent()).val();
            $('#span_dNTP').val(dntp);
            var aa = $("select[name='pcrSystem']").val();
            qwe(aa);
        });

        /** 清空检材信息 */
        $(".kongBlue").on("click", function () {
            $(".kongYellow").removeClass("kongYellow")
            $('#span_id').val("");
            $('#span_pcrId').val("");
            $('#span_sampleId').val("");
            $('#span_SampleNo').html("");
            $('#span_SampleName').html("");
            $('#span_SamplePosition').html("");
            $('#span_Primer').val("");
            $('#span_Buffer').val("");
            $('#span_TaqE').val("");
            $('#span_Template').val("");
            $('#span_H₂O').val("");
            $('#span_MgCl₂').val("");
            $('#span_dNTP').val("");
        });

        /** 删除扩增检材实验记录 */
        $("button[name='deleteBtn']", "#samplelist_tbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            var sampleId = $(that).find("input[name='sampleId']").val()
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabPcrSample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if (data.length == undefined) {
                            that.remove()
                            var delCircle = $("input[value="+sampleId+"]").parents("li")
                            $(delCircle).find("div").attr('name','').removeClass()
                            $(delCircle).find("input").val('')
                        }
                        if (data.success || data.success == true || data.success == "true") {
                            location.href = "<%=path%>/center/refreshPcrExperiment?pcrId=" + data.pcrId;
                        }
                    }
                });
            }
        });

        /** 保存扩增实验记录 */
        $("#saveBtn").on("click", function () {
            if ($("#saveBtn").val() == "1") {
                //列表保存
                saveForm1();
            } else if ($('#span_id').val() != "") {
                //图形保存  修改时的保存
//                saveForm3();
                saveForm2();
            } else {
                //图形保存  默认保存全部
                saveForm2();

            }
        });

        /*图形保存*/
        function saveForm2() {
            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(params()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    $("#recordModal").modal('show');
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                }
            });
        }

        function saveForm3() {
            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(graph()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    $("#recordModal").modal('show');
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                }
            });
        }

        /**保存扩增实验样本信息*/
        function graph() {
            var samplerArr = new Array();
            var sampler = {};
            sampler.id = $('#span_id').val();
            sampler.pcrId = $('#span_pcrId').val();
            sampler.sampleId = $('#span_sampleId').val();
            sampler.samplePostion = $('#span_SamplePosition').html();
            sampler.primer = $('#span_Primer').val();
            sampler.buffer = $('#span_Buffer').val();
            sampler.taqe = $('#span_TaqE').val();
            sampler.template = $('#span_Template').val();
            sampler.h2o = $('#span_H₂O').val();
            sampler.mgcl2 = $('#span_MgCl₂').val();
            sampler.dntp = $('#span_dNTP').val();
            samplerArr.push(sampler);
            return samplerArr;
        }

        /*列表保存*/
        function saveForm1() {
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            $.ajax({
                url: "<%=path%>/center/savePcrInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&pcrEndDatetime=" + $("#pcrEndDatetime").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        $.ajax({
                            url: "<%=path%>/center/savePcrExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val(),
                            type: "post",
                            data: JSON.stringify(params()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    $("#recordModal").modal('show');
                                } else {
                                    alert("保存失败！");
                                }
                            },
                            error: function (e) {
                                alert("保存失败！");
                            }
                        });
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        $("#finishedBtn").on("click", function () {
            $("#recordModal").modal('hide');
            location.href = "<%=path%>/center/refreshPcrExperiment?pcrId=" + $("#pcrId").val();
        });

        /**保存扩增任务信息*/
        function param() {
            var pcr = {};
            var $sampleTR = $("tr", "#samplelist_tbody");
            var sampleCnt = $sampleTR.length;
            var checkCount = 0;
            for (var i = 0; i < sampleCnt; i++) {
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            }
            pcr.pcrId = $("#pcrId").val();
            pcr.sampleCount = checkCount;
            pcr.pcrPerson1 = $("input[name='pcrPerson1']").val();
            pcr.pcrReagent = $("input[name='pcrReagent']").val();
//            pcr.pcrReagent = $("#kitPopBox").find(".active").html();
            pcr.pcrInstrument = $("select[name='pcrInstrument'] option:selected").val();
            pcr.pcrProgram = $("select[name='pcrProgram'] option:selected").val();
            pcr.pcrSystem = $("select[name='pcrSystem'] option:selected").val();
            pcr.batchb = $("input[name='batchb']").val();
            pcr.validity = $("input[name='validity']").val();
            console.log(pcr)
            return pcr;
        }

        /**保存扩增实验样本信息*/
        function params() {
            var sampleArr = new Array();
            var $sampleTR = $("tr", "#samplelist_tbody");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    sample.id = $("input[name='id']", $sampleTR.get(i)).val();
                    sample.pcrId = $("#pcrId").val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sample.samplePostion = $("input[name='samplePostion']", $sampleTR.get(i)).val();
                    sample.primer = $("input[name='primer']", $sampleTR.get(i)).val();
                    sample.buffer = $("input[name='buffer']", $sampleTR.get(i)).val();
                    sample.taqe = $("input[name='taqe']", $sampleTR.get(i)).val();
                    sample.template = $("input[name='template']", $sampleTR.get(i)).val();
                    sample.h2o = $("input[name='h2o']", $sampleTR.get(i)).val();
                    sample.mgcl2 = $("input[name='mgcl2']", $sampleTR.get(i)).val();
                    sample.dntp = $("input[name='dntp']", $sampleTR.get(i)).val();
                    sampleArr.push(sample);
                }
            }

            return sampleArr;
        }

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

        $("input[name='checkAll']").change(function () {
            if ($(this).is(":checked")) {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", false)
            }
        })
        $("tbody").find("input[type='checkbox']").change(function () {
            if ($(this).parents("tbody").find("input[type='checkbox']:checked").length == $(this).parents("tbody").find("input[type='checkbox']").length) {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", false)
            }
        })
        $(".tabBtn").click(function () {
            if ($(this).html() == "列表展示") {
                $(this).parents(".Modular").fadeOut("slow", function () {
                    $(".tabBtn").eq(1).parents(".Modular").fadeIn("slow")
                    $("#saveBtn").val(1)
                })
            } else {
                $(this).parents(".Modular").fadeOut("slow", function () {
                    $(".tabBtn").eq(0).parents(".Modular").fadeIn("slow")
                    $("#saveBtn").val(0)
//                    $("#graph").find(".col-md-12").each(function () {
//                        $(this).children("span").eq(1).html("")
//                        $(this).children("input").val("")
//                    })
                })
            }
        })

        //生成记录表
        $("#record").on("click", function () {
            if (!confirm("是否生成扩增记录表！")) {
                return false;
            }

            var pcrId = $("input[name='pcrId']", $(this).parent()).val();
            if (pcrId == '' || pcrId == null || pcrId == undefined || pcrId == "") {
                alert("先保存后再生成记录表");
                return false;
            }
            location.href = "<%=path%>/center/pcrRecordBook?pcrId=" + pcrId;
        });

        $("#pcrReagent").on("change", function () {
            var pcrReagent = $(".batchNumber")
            for (var i = 0; i < pcrReagent.length; i++) {
                if (pcrReagent[i].getAttribute("panelid") == $("#pcrReagent").val()) {
                    $("#batchb").val(pcrReagent[i].value);
                    $("#validity").val(pcrReagent[i].getAttribute("validity"));
                }
            }
        });

    });

    function addKit() {
        var kit = $(".checkedKit");
        var act = $(".active");
        var reagentList = [];
        var list = [];
        var listOf = [];
        var pkKit = "";
        var listIDs = [];
        for (var i = 0; i < kit.length; i++) {
            for (var j = 0; j < act.length; j++) {
                if (kit.get(i) == act.get(j)) {
                    reagentList.push(kit.get(i).getAttribute("reagentName"));
                    list.push(kit.get(i).getAttribute("batchNumber"));
                    listOf.push(kit.get(i).getAttribute("termOfValidity"));
                    listIDs.push(kit.get(i).getAttribute("panelid"));
                }
            }
        }
        for (var i = 0; i < list.length; i++) {
            pkKit += "<div class=\"col-md-12\">\n" +
                    "                <div class=\"col-md-2\">\n" +
                    "                <div class=\"btnCheck\">" + reagentList[i] + "</div>\n" +
                    "                <input type=\"hidden\" class=\"tagsId\" readonly='' value=\"" + listIDs[i] + "\">\n" +
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

        $("input[name='pcrReagent']").val($("#kitPopBox").find(".active").html());

        getPcrSystem();
    }

    getPcrSystem();


    function getPcrSystem() {
        var pcrReagent = $("input[name='pcrReagent']").val();
        if (typeof(pcrReagent) == "undefined" && pcrReagent == null && pcrReagent =="") {
            return false;
        }
        var parameter = {};

        parameter.panelName = pcrReagent;

        var pcrSystem = $("input[name='pcrSystemHidden']").val();

        $.ajax({
            url: "<%=path%>/handwork/selectPcrSystem?operate=pcrSystem",
            type: "post",
            data: JSON.stringify(parameter),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (data.success || data.success == true || data.success == "true") {
                    $("select[name='pcrSystem']").empty();
                    var inhtml = "";
                    inhtml += "<option value=''>请选择</option>";
                    function uniq(len){
                        var temp = []; //一个新的临时数组
                        for(var i = 0; i < len; i++){
                            if(temp.indexOf(experimentalParameterList[i].parameterName) == -1){
                                temp.push(experimentalParameterList[i].parameterName);
                            }
                        }
                        return temp;
                    }
                    var experimentalParameterList = data.experimentalParameterList;
                    var len = experimentalParameterList.length;
                    for(var i=0;i<uniq(len).length;i++){
                        if (pcrSystem == uniq(len)[i]) {
                            inhtml += "<option value='" + uniq(len)[i] + "' selected>" + uniq(len)[i] + "</option>";
                        }else {
                            inhtml += "<option value='" + uniq(len)[i] + "'>" + uniq(len)[i] + "</option>";
                        }
                    }
                    $("select[name='pcrSystem']").append(inhtml);
                }
            }
        });
    }
    $("select[name='pcrSystem']").change('click',function(){
        var pcrSystem = $("select[name='pcrSystem'] option:selected").val();
        qwe(pcrSystem);
    })
    function qwe(pcrSystem) {
        if (pcrSystem == "") {
            alert("请选择扩增体系！");
            return;
        }

        var pcrReagent = $("input[name='pcrReagent']").val();
        var parameter = {};

        parameter.panelName = pcrReagent;
        parameter.parameterName = pcrSystem;

        $.ajax({
            url: "<%=path%>/handwork/selectPcrSystem",
            type: "post",
            data: JSON.stringify(parameter),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                if (data.success || data.success == true || data.success == "true") {
                    var experimentalParameterList = data.experimentalParameterList;
                    var a = experimentalParameterList.length;
                    if($("#span_SampleName").text() ==""){
                        //alert(11);

                    }else if($("#span_SampleName").text() =="脱落细胞"){
                        for( var i =0; i <a; i++){
                            if(experimentalParameterList[i].remark == "1"){
                                var mapParameter = experimentalParameterList[i].parameterValue;
                                var $trs=$("tr","#samplelist_tbody");
                                if(typeof(mapParameter)!="undefined" && mapParameter!=0 && mapParameter != null && mapParameter != ""){

                                    $trs.each(function(){
                                        var param = parameter[0];
                                        //图像
                                        $("#span_Primer").val(JSON.parse(mapParameter).Primer);
                                        $("#span_Buffer").val(JSON.parse(mapParameter).Buffer)
                                        $("#span_TaqE").val(JSON.parse(mapParameter).TaqE)
                                        $("#span_Template").val(JSON.parse(mapParameter).Template)
                                        $("#span_H₂O").val(JSON.parse(mapParameter).H2O)
                                        $("#span_MgCl₂").val(JSON.parse(mapParameter).MgCl2)
                                        $("#span_dNTP").val(JSON.parse(mapParameter).dNTP)
                                        //列表
                                        $("input[name='primer']").val(JSON.parse(mapParameter).Primer);
                                        $("input[name='buffer']").val(JSON.parse(mapParameter).Buffer);
                                        $("input[name='taqe']").val(JSON.parse(mapParameter).TaqE);
                                        $("input[name='template']").val(JSON.parse(mapParameter).Template);
                                        $("input[name='h2o']").val(JSON.parse(mapParameter).H2O);
                                        $("input[name='mgcl2']").val(JSON.parse(mapParameter).MgCl2);
                                        $("input[name='dntp']").val(JSON.parse(mapParameter).dNTP);
                                    })
                                }
                            }
                        }

                    }else{
                        for( var i =0; i <a; i++){
                            if(experimentalParameterList[i].remark != "1"){
                                var mapParameter = experimentalParameterList[i].parameterValue;
                                var $trs=$("tr","#samplelist_tbody");
                                if(typeof(mapParameter)!="undefined" && mapParameter!=0 && mapParameter != null && mapParameter != ""){

                                    $trs.each(function(){
                                        var param = parameter[0];
                                        //图像
                                        $("#span_Primer").val(JSON.parse(mapParameter).Primer);
                                        $("#span_Buffer").val(JSON.parse(mapParameter).Buffer)
                                        $("#span_TaqE").val(JSON.parse(mapParameter).TaqE)
                                        $("#span_Template").val(JSON.parse(mapParameter).Template)
                                        $("#span_H₂O").val(JSON.parse(mapParameter).H2O)
                                        $("#span_MgCl₂").val(JSON.parse(mapParameter).MgCl2)
                                        $("#span_dNTP").val(JSON.parse(mapParameter).dNTP)
                                        //列表
                                        $("input[name='primer']").val(JSON.parse(mapParameter).Primer);
                                        $("input[name='buffer']").val(JSON.parse(mapParameter).Buffer);
                                        $("input[name='taqe']").val(JSON.parse(mapParameter).TaqE);
                                        $("input[name='template']").val(JSON.parse(mapParameter).Template);
                                        $("input[name='h2o']").val(JSON.parse(mapParameter).H2O);
                                        $("input[name='mgcl2']").val(JSON.parse(mapParameter).MgCl2);
                                        $("input[name='dntp']").val(JSON.parse(mapParameter).dNTP);
                                    })
                                }
                            }
                        }
                    }
                }
            }
        });

    }

    function addEcho(){
        var kit = $(".checkedKit");
        var has = $("#PkKit .tagsId");
        var has_label = $("#PkKit .btnCheck");
        for (var j = 0; j < kit.length; j++) {
            $(kit[j]).removeClass('active');
            for (var i = 0; i < has.length; i++) {
                var val = $(has[i]).val();
                var text = $(has_label[i]).text();
                if($(kit[j]).attr("panelid") == val || $(kit[j]).attr("reagentname") == text ){
                    $(kit[j]).addClass('active');
                }
            }
        }
    }
</script>
</body>

</html>