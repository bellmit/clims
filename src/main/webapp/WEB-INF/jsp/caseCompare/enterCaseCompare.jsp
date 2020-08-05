<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2018/12/19
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .Modular .panel-body {
            padding: 15px 0px;
        }

        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
            margin-top: 0px !important;
            text-align: right;
        }

        .sameType,
        #kinshipTab {
            border: none;
            padding-left: 30px;
            display: inline-block;
        }

        .sameType li a,
        #kinshipTab li a {
            background: #f0f0f0;
            color: #535353;
            position: relative;
        }

        .sameType li a span,
        #kinshipTab li a span {
            position: absolute;
            top: -8px;
            background: #eaeaea;
            color: #989898;
        }

        .sameType li + li a,
        #kinshipTab li + li a {
            margin-left: 20px;
        }

        .sameType > li.active > a,
        .sameType > li.active > a:focus,
        .sameType > li.active > a:hover,
        #kinshipTab > li.active > a,
        #kinshipTab > li.active > a:focus,
        #kinshipTab > li.active > a:hover {
            background: #0c81f5;
            color: #fff;
        }

        .sameType > li.active > a span,
        #kinshipTab > li.active > a span {
            background: #ffb72c;
            color: #fff;
        }

        .table {
            border-collapse: collapse;
        }

        .table tbody tr td .custom-control-label {
            color: #707070 !important;
        }

        .noPadding {
            padding: 0px !important;
        }

        .noMargin {
            margin: 0px !important;
        }

        .paddingLeft {
            padding-left: 53px !important;
        }

        .tableFont {
            font-weight: 600 !important;
            color: #000 !important;
            text-align: center;
        }

        .tableBtnBox:nth-child(1) {
            border-top: none;
        }

        .tableBtnBox {
            border-top: 10px solid #eee;
        }

        .tableBtn {
            color: #fff;
            width: 43px;
            height: 100%;
            display: inline-block;
            text-align: center;
            height: 39.6px;
            line-height: 39.6px;
            margin-right: 10px;
            font-weight: 600;
        }

        .tableBtnRed > td:nth-child(2) > div {
            background: #f3504a;
        }

        .tableBtnRed > td:nth-child(N+2) {
            background: #fef3f3 !important;
            color: #f4625c !important;
        }

        .tableBtnBlue > td:nth-child(2) > div {
            background: #0c81f5;
        }

        .tableBtnBlue > td:nth-child(N+2) {
            background: #eaf4ff !important;
            color: #0c81f5 !important;
        }

        .tableBtnYellow > td:nth-child(2) > div {
            background: #ffb72c;
        }

        .tableBtnYellow > td:nth-child(N+2) {
            background: #fff4e1 !important;
            color: #ffb72c !important;
        }

        .tableBtnGreen > td:nth-child(2) > div {
            background: #1bb29b;
        }

        .tableBtnGreen > td:nth-child(N+2) {
            background: #e3fffb !important;
            color: #1bb29b !important;
        }

        .checkbox-slider--default input + span:before {
            content: "";
            height: 16px;
            width: 30px;
            border-radius: 50px;
            background: #e8e8e8;
            box-shadow: none;
            transition: background .2s ease-out;
            outline: none;
        }

        .checkbox-slider--default input + span:after {
            height: 16px;
            width: 16px;
            border-radius: 50%;
            position: absolute;
            left: 0;
            top: 0;
            display: block;
            background: #fff;
            transition: margin-left .1s ease-in-out;
            text-align: center;
            font-weight: 700;
            content: "";
        }

        .checkbox-slider--a-rounded input:checked + span:after,
        .checkbox-slider--default input:checked + span:after {
            background: #fff;
            border: 1px solid transparent;
            background-clip: content-box;
        }

        .checkbox-slider--a-rounded input:checked + span:before,
        .checkbox-slider--default input:checked + span:before {
            background: #1bb29b;
            border: 1px solid transparent;
            background-clip: content-box;
        }

        .checkbox-slider input:checked + span:after,
        .checkbox-slider--default input:checked + span:after {
            margin-left: 14px;
            content: "";
        }

        .checkbox-slider input[type="checkbox"]:focus + *:before,
        .checkbox-slider--default input[type="checkbox"]:focus + *:before,
        .checkbox-toggle input[type="checkbox"]:focus + *:before {
            outline: none;
        }

        .checkbox-slider--default input + span {
            padding-left: 20px;
        }

        #relativeComparisonBox .custom-control-label::before {
            background-image: none;
            border-radius: 50%;
        }

        #relativeComparisonBox .custom-control-input:checked ~ .custom-control-label::before {
            color: #fff;
            background-color: #50c987;
            background-image: url(<%=path%>/img/check.svg);
            background-size: 11px;
            background-repeat: no-repeat;
            background-position: 2px;
        }

        #look .modal-dialog {
            top: 50px;
        }

        #look .modal-body {
            padding: 0px;
            position: relative;
        }

        #look .closeBtn {
            position: absolute;
            width: 40px;
            height: 40px;
            color: #fff;
            background: #e7e7e7;
            text-align: center;
            line-height: 40px;
            top: -50px;
            right: 0px;
            border-radius: 50%;
            font-size: 25px;
            cursor: pointer;
        }

        #look .table {
            margin-bottom: 0px;
        }

        #look .table > thead > tr > th {
            border: none;
            background: #298de5;
            color: #fff;
            padding-left: 40px;
        }

        #look .table > thead > tr:nth-child(1) > th {
            line-height: 72px;
        }

        #look .table > thead > tr:nth-child(1) > th:nth-child(3) {
            background: #0060b5;
            line-height: 36px;
            text-align: center;
        }

        #look .table > thead > tr:nth-child(2) > th {
            background-color: #0074d9;
        }

        #look .table > tbody > tr > td {
            color: #000;
            font-weight: 600;
            padding-left: 40px;
            border-right: 1px solid #c1ccda;
        }

        #look .table > tbody > tr:nth-child(4n+1) {
            background: #ffd6d5;
        }

        #look .table > tbody > tr:nth-child(4n+2) {
            background: #e2efff;
        }

        #look .table > tbody > tr:nth-child(4n+3) {
            background: #eee1ff;
        }

        #look .table > tbody > tr:nth-child(4n+4) {
            background: #ffffff;
        }

        #look .table > tbody > tr:nth-last-child(1) {
            background: #fff7ed;
        }

        #look .table > tbody > tr:nth-last-child(1) td {
            color: #e27e00;
        }

        .btn-icon-comparison-red {
            background-position: -230px -156px;
        }

        .green {
            background: rgba(0, 128, 0, .5) !important;
        }

        .footSelect {
            white-space: nowrap;
        }

        .footSelect > .select {
            display: inline-block;
            width: 136px;
        }

        .footerSelect > .col-md-3, .footerSelect > col-md-1, .footerSelect > button {
            /*top: 20px;*/
        }

        .footerSelect > .col-md-1 {
            margin-left: 24px;
        }

        .footerSelect > button {
            margin-right: 14px;
            margin-top: 20px;
        }

        .submitWarehouseNum {
            height: 33px;
            line-height: 33px;
            margin-right: 20px;
            font-size: 16px;
            font-weight: 600;
        }

        .btn {
            margin-right: 10px;
        }

        .form-group {
            margin-bottom: 0px;
            display: flex;
            width: 100%;
        }

        .form-group label {
            width: 40%;
            text-align: right;
            margin-right: 10px;
        }

        .form-group span {
            width: 100%;
        }

        .Modular .panel-default > .panel-heading > div {
            font-size: 16px;
            font-family: "微软雅黑";
        }

        .seachInputBox {
            line-height: 34px;
        }

        .seachButtonBox {
            height: 34px;
            line-height: 34px;
        }

        @media print {
            html, body {
                height: inherit;
            }
        }

    </style>
</head>
<div class="modal fade popBox" id="relativeComparisonBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     data-keyboard="false">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">亲缘样本信息</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover table-bordered bigTable noMargin">
                    <thead>
                    <tr>
                        <th>匹配目标</th>
                        <th>样本</th>
                        <th style="width: 15%;">匹配率</th>
                        <th style="width: 15%;">匹配基因座数</th>
                        <th style="width: 15%;">比对模式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="relativeComparisonTbody">
                    <tr>
                        <td>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="radio" name="rcRadio" value="0">
                                <span class="custom-control-label">父亲</span>
                            </label>
                        </td>
                        <td>
                            <div class="select">
                                <select class="form-control" id="ComparedToCaseSampleFID" name="ComparedToCaseSampleF">
                                    <option value=''>请选择...</option>
                                    <c:forEach items="${auditedGeneList}" var="list" varStatus="s">
                                        <option value="${list.sampleNo}">${list.sampleNo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td colspan="4"></td>
                    </tr>
                    <tr>
                        <td>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="radio" name="rcRadio" value="1">
                                <span class="custom-control-label">母亲</span>
                            </label>
                        </td>
                        <td>
                            <div class="select">
                                <select class="form-control" id="ComparedToCaseSampleMID" name="ComparedToCaseSampleM">
                                    <option value=''>请选择...</option>
                                    <c:forEach items="${auditedGeneList}" var="list" varStatus="s">
                                        <option value="${list.sampleNo}">${list.sampleNo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td colspan="4"></td>
                    </tr>
                    <tbody id="newAddZTbodyId">
                    <tr>
                        <td>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="radio" name="rcRadio" value="2" checked>
                                <span class="custom-control-label">子女</span>
                            </label>
                        </td>
                        <td>
                            <div class="select">
                                <select class="form-control" id="ComparedToCaseSampleZID" name="ComparedToCaseSampleZ">
                                    <option value=''>请选择...</option>
                                    <c:forEach items="${auditedGeneList}" var="list" varStatus="s">
                                        <option value="${list.sampleNo}">${list.sampleNo}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="totalPi" onmouseover="this.title=this.value"
                                   readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="matchCount"
                                   onmouseover="this.title=this.value" readonly>
                        </td>
                        <td>
                            <input type="text" class="form-control" name="matchModeName"
                                   onmouseover="this.title=this.value" readonly>
                        </td>
                        <td>
                            <button class="btn-red btn addChildren">添加</button>
                        </td>
                        <input name="panelNameF" type="hidden">
                        <input name="panelNameM" type="hidden">
                        <input name="panelNameC" type="hidden">
                        <input name="sampleNameF" type="hidden">
                        <input name="sampleNameM" type="hidden">
                        <input name="sampleNameC" type="hidden">
                        <input name="sampleNoF" type="hidden">
                        <input name="sampleNoM" type="hidden">
                        <input name="sampleNoC" type="hidden">
                        <input name="sampleIdF" type="hidden">
                        <input name="sampleIdM" type="hidden">
                        <input name="sampleIdC" type="hidden">
                        <input name="singleMatchMode" type="hidden">
                        <input name="singlePopulationID" type="hidden">
                        <input name="singleSameCount" type="hidden">
                        <input name="singleDiffCount" type="hidden">
                    </tr>
                    </tbody>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button class="btn btn-yellow" id="matchBtn">比对</button>
                <button class="btn btn-blue" id="addCompareToResultId" disabled>保存</button>
                <button class="btn btn-blue-border" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="look" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
     data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span aria-hidden="true" data-dismiss="modal" aria-label="Close" class="closeBtn">&times;</span>
                <table class="table">
                    <thead>
                    <tr>
                        <th rowspan="2">序号</th>
                        <th rowspan="2">基因座</th>
                        <th colspan="2">等位基因</th>
                        <th rowspan="2">LR</th>
                        <th rowspan="2">其他</th>
                    </tr>
                    <tr>
                        <th id="referenceNo"></th>
                        <th id="matchNo"></th>
                    </tr>
                    </thead>
                    <tbody id="viewMatchTBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<body>


<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>本案比对设置</div>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>案件编号</label>
                            <span class="caseNo">${limsCaseInfo.caseNo}</span>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>案件名称</label>
                            <span class="caseName">${limsCaseInfo.caseName}</span>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>检验人</label>
                            <span>${limsCaseInfo.acceptorName}</span>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>种群名称</label>
                            <select class="form-control" id="relativePopulation" name="relativePopulation">
                                <c:forEach items="${raceList}" var="race" varStatus="r">
                                    <option value="${race.id}">${race.raceName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>同一匹配下限</label>
                            <%--${minSameCaseCount}--%>
                            <input type="text" name="minSameCaseCount" class="form-control minSameCaseCount"
                                   placeholder="13" value="">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>混合匹配下限</label>
                            <%--${minSameMixCount}--%>
                            <input type="text" name="minSameMixCount" class="form-control minSameMixCount"
                                   placeholder="13" value="13">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>混合容差上限</label>
                            <%--${minSameMixCount}--%>
                            <input type="text" name="minSameMixCount" class="form-control minSameMixCount"
                                   placeholder="1" value="1">
                        </div>
                    </div>
                    <div class="col-md-2 seachInputBox DYnone">
                        <div class="form-group seachButtonBox">
                            <button class="btn btn-blue afreshMatchBtn" type="button" id="afreshMatchBtn">重新本案比对
                            </button>
                        </div>
                    </div>
                    <div class="col-md-2 seachInputBox DYnone">
                        <div class="form-group seachButtonBox">
                            <input class="btn btn-red" type=button name='button_export' title='打印1' onclick=preview(1)
                                   value="打印">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>亲缘匹配下限</label>
                            <%--${minSameRelationCount}--%>
                            <input type="text" id="relativeSameCount" name="relativeSameCount"
                                   value="" class="form-control relativeSameCount"
                                   placeholder="13">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>亲缘容差上限</label>
                            <%--${halfDiffCount}--%>
                            <input type="text" id="halfDiffCount" name="halfDiffCount" value=""
                                   class="form-control halfDiffCount"
                                   placeholder="1">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--startprint1-->
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>同一比中(${fn:length(matchedGroupList)})</div>
            </div>
            <div class="panel-body">
                <div>
                    <input type="hidden" class="caseId" value="${caseId}">
                    <input type="hidden" class="acceptDatetime" value="${acceptDatetime}">
                    <input type="hidden" class="consignmentId" value="${consignmentId}">
                    <%--<span class="pull-right submitWarehouseNum">提交入库数：<span>0</span></span>--%>

                    <%--<button type="button" name="submitWarehouse" class="btn btn-yellow-border pull-right"--%>
                    <%--style="margin-right: 20px;">提交入库--%>
                    <%--</button>--%>
                    <div class="sameTypeContent" class="tab-content">
                        <div class="tab-pane fade in active" id="ratio">
                            <table class="table table-hover table-bordered bigTable noMargin">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">组</th>
                                    <th>检材编号</th>
                                    <th>检材名称</th>
                                    <th>panel</th>
                                    <th>匹配数</th>
                                    <th>LR</th>
                                    <th class="DYnone">有效检材</th>
                                    <th class="DYnone">
                                        <label class="custom-control checkbox-inline">
                                            <input class="custom-control-input" type="checkbox" name="allChecked"
                                                   id="allChecked">
                                            <span class="custom-control-label" style="font-weight: 600">入库操作</span>
                                        </label>
                                    </th>
                                    <th class="DYnone">操作</th>
                                </tr>
                                </thead>
                                <tbody id="matchedGroupTBody">
                                <c:forEach items="${matchedGroupList}" var="matchedGroup" varStatus="group">
                                    <c:forEach items="${matchedGroup.matchedSampleList}" var="matchedSample"
                                               varStatus="matched">
                                        <c:if test="${matched.index eq 0}">
                                            <c:if test="${matchedGroup.referenceType eq '1'}"><tr class="tableBtnBox tableBtnRed"></c:if>
                                            <c:if test="${matchedGroup.referenceType eq '2'}"><tr class="tableBtnBox tableBtnBlue"></c:if>
                                            <c:if test="${matchedGroup.referenceType eq '3'}"><tr class="tableBtnBox tableBtnYellow"></c:if>
                                            <c:if test="${matchedGroup.referenceType eq '4'}"><tr class="tableBtnBox tableBtnGreen"></c:if>
                                            <td rowspan="${fn:length(matchedGroup.matchedSampleList)}"
                                                class="tableFont">
                                                <input type="hidden" name="groupId"
                                                       value="${matchedGroup.groupId}">${group.count}
                                            </td>
                                            <td class="noPadding">
                                                <div class="tableBtn">
                                                    <c:if test="${matchedGroup.referenceType eq '1'}">事主</c:if>
                                                    <c:if test="${matchedGroup.referenceType eq '2'}">嫌疑人</c:if>
                                                    <c:if test="${matchedGroup.referenceType eq '3'}">其他人</c:if>
                                                    <c:if test="${matchedGroup.referenceType eq '4'}">检材</c:if>
                                                </div>
                                                <input type="hidden" name="sampleId"
                                                       value="${matchedSample.sampleId}">
                                                    <%--<a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${matchedSample.auditedGeneId}">${matchedSample.sampleNo}</a>--%>
                                                    ${matchedSample.sampleNo}
                                            </td>
                                            <td>${matchedSample.sampleName}</td>
                                            <td>${matchedSample.panelName}</td>
                                            <td>${matchedSample.sameCount}</td>
                                            <td>
                                                <input type="hidden" name="geneInfo" value='${matchedSample.geneInfo}'/>
                                                <c:choose>
                                                    <c:when test="${null eq matchedSample.totalProbability}">
                                                        -- --
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${matchedSample.totalProbability}
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td class="DYnone">
                                                <div class="checkbox checkbox-slider--default noMargin">
                                                    <label>
                                                        <input type="checkbox" name="rememberMe"
                                                               checked=""><span></span>
                                                    </label>
                                                </div>
                                            </td>
                                            <td class="DYnone">
                                                <label class="custom-control checkbox-inline">
                                                    <input class="custom-control-input" type="checkbox" name="box">
                                                    <span class="custom-control-label">入库操作</span>
                                                </label>
                                            </td>
                                            <td class="DYnone">
                                                <input type="hidden" name="panelId" value="${matchedSample.panelId}">
                                                <input type="hidden" name="referenceId"
                                                       value="${matchedSample.referenceId}">
                                                <input type="hidden" name="sampleNo" value="${matchedSample.sampleNo}">
                                                <input type="hidden" name="boardNo" value="${matchedSample.boardNo}">
                                                <input type="hidden" name="sameCount"
                                                       value="${matchedSample.sameCount}">
                                                <input type="hidden" name="totalProbability"
                                                       value="${matchedSample.totalProbability}">
                                            </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${matched.index gt 0}">
                                            <tr>
                                                <td class="paddingLeft">
                                                    <input type="hidden" name="sampleId"
                                                           value="${matchedSample.sampleId}">
                                                        <%--<a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${matchedSample.auditedGeneId}">${matchedSample.sampleNo}</a>--%>
                                                        ${matchedSample.sampleNo}
                                                </td>
                                                <td>${matchedSample.sampleName}</td>
                                                <td>${matchedSample.panelName}</td>
                                                <td><c:if
                                                        test="${not empty matchedSample.sameCount}">${matchedSample.sameCount-1}</c:if></td>
                                                <td>
                                                    <input type="hidden" name="geneInfo"
                                                           value='${matchedSample.geneInfo}'/>
                                                    <c:choose>
                                                        <c:when test="${null eq matchedSample.totalProbability}">
                                                            -- --
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${matchedSample.totalProbability}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td class="DYnone">
                                                    <div class="checkbox checkbox-slider--default noMargin">
                                                        <label>
                                                            <input type="checkbox" name="rememberMe"
                                                                   checked=""><span></span>
                                                        </label>
                                                    </div>
                                                </td>
                                                <td class="DYnone">
                                                    <label class="custom-control checkbox-inline">
                                                        <input class="custom-control-input" type="checkbox" name="box">
                                                        <span class="custom-control-label">入库操作</span>
                                                    </label>
                                                </td>
                                                <td class="DYnone">
                                                    <input type="hidden" name="panelId"
                                                           value="${matchedSample.panelId}">
                                                    <input type="hidden" name="referenceId"
                                                           value="${matchedSample.referenceId}">
                                                    <input type="hidden" name="sampleNo"
                                                           value="${matchedSample.sampleNo}">
                                                    <input type="hidden" name="boardNo"
                                                           value="${matchedSample.boardNo}">
                                                    <input type="hidden" name="sameCount"
                                                           value="${matchedSample.sameCount}">
                                                    <input type="hidden" name="totalProbability"
                                                           value="${matchedSample.totalProbability}">
                                                    <input type="hidden" name="comparePopulationId"
                                                           value="${matchedSample.comparePopulationId}">
                                                    <input type="hidden" name="matchLimit"
                                                           value="${matchedSample.matchLimit}">
                                                    <input type="hidden" name="tolerance"
                                                           value="${matchedSample.tolerance}">
                                                    <button type="button"
                                                            class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"
                                                            name="viewMatched">查看
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div style="float: left">亲缘比中(${relationCount})</div>
                <button class="btn btn-yellow-border DYnone" data-toggle="modal"
                        data-target="#relativeComparisonBox">添加亲缘比对
                </button>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <%--<div class="row">--%>
                    <%--<div class="col-md-4 seachInputBox">--%>
                    <%--<div class="form-group seachButtonBox">--%>
                    <%----%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <div style="width: 100%">
                        <%--<span class="pull-right submitWarehouseNum">提交入库数：<span>0</span></span>--%>
                        <%--<button class="btn btn-yellow-border pull-right" style="margin-right: 20px;"--%>
                        <%--name="relationRubmitWarehouse">提交入库--%>
                        <%--</button>--%>
                        <div id="kinshipContent" class="tab-content">
                            <div class="tab-pane fade in active" id="kinship">
                                <table class="table table-hover table-bordered bigTable noMargin">
                                    <thead>
                                    <tr>
                                        <th style="text-align: center;">组</th>
                                        <th>检材编号</th>
                                        <th>检材名称</th>
                                        <th>人员关系</th>
                                        <th>panel</th>
                                        <th>匹配数</th>
                                        <th>累计PI值</th>
                                        <th class="DYnone">匹配下限</th>
                                        <th class="DYnone">
                                            <label class="custom-control checkbox-inline">
                                                <input class="custom-control-input" type="checkbox"
                                                       id="raletionAllCheck">
                                                <span class="custom-control-label" style="font-weight: 600">入库操作</span>
                                            </label>
                                        </th>
                                        <th class="DYnone">操作</th>
                                    </tr>
                                    </thead>
                                    <tbody id="raletionID">
                                    <c:if test="${fn:length(compareRelativeResultList) gt 0}">
                                        <c:forEach items="${compareRelativeResultList}" var="compareResultInfo"
                                                   varStatus="c">
                                            <tr>
                                                <input type="hidden" id="relativeRowCount"
                                                       value="${compareRelativeResultList.size()}">
                                            <tr>
                                                <td>${c.count}</td>
                                                <td colspan="4">
                                                    <input type="hidden" name="caseIdHidden"
                                                           value="${compareResultInfo.caseId}">
                                                    <input type="hidden" name="fatherSampleNoHidden"
                                                           value="${compareResultInfo.fatherSampleNo}">
                                                    <input type="hidden" name="motherSampleNoHidden"
                                                           value="${compareResultInfo.motherSampleNo}">
                                                    <input type="hidden" name="childSampleNoHidden"
                                                           value="${compareResultInfo.childSampleNo}">
                                                    <input type="hidden" name="matchRelationLimit"
                                                           value="${compareResultInfo.matchLimit}">
                                                    <input type="hidden" name="compareRelationPopulationId"
                                                           value="${compareResultInfo.comparePopulationId}">
                                                    <input type="hidden" name="toleranceRelation"
                                                           value="${compareResultInfo.tolerance}">
                                                    <button type='button'
                                                            class='btn-icon btn-icon-yellow btn-icon-chakan-red'
                                                            name="viewMatchRelation">查看明细
                                                    </button>
                                                </td>
                                                <td>${compareResultInfo.compareGeneSum}</td>
                                                <td>${compareResultInfo.compareTotalProbability}</td>
                                                <td>${compareResultInfo.matchLimit}</td>
                                                <td>${compareResultInfo.comparePopulationName}</td>
                                            </tr>
                                            <c:if test="${not empty compareResultInfo.fatherSampleNo}">
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                            <%--
                                                            <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.fatherGeneId}">
                                                                    ${compareResultInfo.fatherSampleNo}
                                                            </a>--%>
                                                            ${compareResultInfo.fatherSampleNo}
                                                    </td>
                                                    <td title="${compareResultInfo.fatherSampleName}">
                                                        <c:if test="${fn:length(compareResultInfo.fatherSampleName) gt 14}">${fn:substring(compareResultInfo.fatherSampleName,0,13)} ...</c:if>
                                                        <c:if test="${fn:length(compareResultInfo.fatherSampleName) lt 15}">${compareResultInfo.fatherSampleName}</c:if>
                                                    </td>
                                                    <c:if test="${compareResultInfo.bacgroundF eq 'green'}">
                                                    <td style='background:rgba(0, 128, 0, .5);'>父<c:if
                                                            test="${compareResultInfo.referenceId eq '0'}">(A)</c:if></td>
                                                    </c:if>
                                                    <c:if test="${compareResultInfo.bacgroundF eq 'red'}">
                                                    <td style='background:rgba(255, 0, 0, .5);'>父<c:if
                                                            test="${compareResultInfo.referenceId eq '0'}">(A)</c:if></td>
                                                    </c:if>
                                                    <td>${compareResultInfo.reagentNameF}</td>
                                                    <td>${compareResultInfo.compareGeneSum}</td>
                                                    <td>-- --</td>
                                                    <td class="DYnone">-- --</td>
                                                    <td class="DYnone">
                                                        <input type="hidden" name="sampleId"
                                                               value="${compareResultInfo.fatherSampleId}">
                                                        <label class=‘custom-control checkbox-inline’>
                                                            <input class='custom-control-input' type='checkbox'
                                                                   name="raletionCheck">
                                                            <span class='custom-control-label'>入库操作</span>
                                                        </label>
                                                    </td>
                                                    <td class="DYnone">
                                                        <a class='btn-icon btn-icon-yellow btn-icon-chakan-yellow'
                                                           href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.fatherGeneId}">
                                                            查看
                                                        </a>
                                                    </td>
                                                <tr>
                                            </c:if>
                                            <c:if test="${not empty compareResultInfo.motherSampleNo}">
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                            <%--
                                                            <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.motherGeneId}">
                                                                    ${compareResultInfo.motherSampleNo}
                                                            </a>
                                                            --%>
                                                            ${compareResultInfo.motherSampleNo}
                                                    </td>
                                                    <td title="${compareResultInfo.motherSampleName}">
                                                        <c:if test="${fn:length(compareResultInfo.motherSampleName) gt 14}">${fn:substring(compareResultInfo.motherSampleName,0,13)} ...</c:if>
                                                        <c:if test="${fn:length(compareResultInfo.motherSampleName) lt 15}">${compareResultInfo.motherSampleName}</c:if>
                                                    </td>
                                                    <c:if test="${compareResultInfo.bacgroundM eq 'green'}">
                                                    <td style='background:rgba(0, 128, 0, .5);'>母<c:if
                                                            test="${compareResultInfo.referenceId eq '1'}">(A)</c:if></td>
                                                    </c:if>
                                                    <c:if test="${compareResultInfo.bacgroundM eq 'red'}">
                                                    <td style='background:rgba(255, 0, 0, .5);'>母<c:if
                                                            test="${compareResultInfo.referenceId eq '1'}">(A)</c:if></td>
                                                    </c:if>
                                                    <td>${compareResultInfo.reagentNameM}</td>
                                                    <td>${compareResultInfo.compareGeneSum}</td>
                                                    <td>-- --</td>
                                                    <td class="DYnone">-- --</td>
                                                    <td class="DYnone">
                                                        <input type="hidden" name="sampleId"
                                                               value="${compareResultInfo.motherSampleId}">
                                                        <label class=‘custom-control checkbox-inline’>
                                                            <input class='custom-control-input' type='checkbox'
                                                                   name="raletionCheck">
                                                            <span class='custom-control-label'>入库操作</span>
                                                        </label>
                                                    </td>
                                                    <td class="DYnone">
                                                        <a class='btn-icon btn-icon-yellow btn-icon-chakan-yellow'
                                                           href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.motherGeneId}">
                                                            查看
                                                        </a>
                                                    </td>
                                                <tr>
                                            </c:if>
                                            <c:if test="${not empty compareResultInfo.childSampleNo}">
                                                <tr>
                                                    <td></td>
                                                    <td>
                                                            <%--
                                                            <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.childGeneId}">
                                                                    ${compareResultInfo.childSampleNo}
                                                            </a>
                                                            --%>
                                                            ${compareResultInfo.childSampleNo}
                                                    </td>
                                                    <td title="${compareResultInfo.childSampleName}">
                                                        <c:if test="${fn:length(compareResultInfo.childSampleName) gt 14}">${fn:substring(compareResultInfo.childSampleName,0,13)} ...</c:if>
                                                        <c:if test="${fn:length(compareResultInfo.childSampleName) lt 15}">${compareResultInfo.childSampleName}</c:if>
                                                    </td>
                                                    <td>子<c:if
                                                            test="${compareResultInfo.referenceId eq '2'}">(A)</c:if></td>
                                                    <td>${compareResultInfo.reagentNameC}</td>
                                                    <td>${compareResultInfo.compareGeneSum}</td>
                                                    <td>-- --</td>
                                                    <td class="DYnone">-- --</td>
                                                    <td class="DYnone">
                                                        <input type="hidden" name="sampleId"
                                                               value="${compareResultInfo.childSampleId}">
                                                        <label class=‘custom-control checkbox-inline’>
                                                            <input class='custom-control-input' type='checkbox'
                                                                   name="raletionCheck">
                                                            <span class='custom-control-label'>入库操作</span>
                                                        </label>
                                                    </td>
                                                    <td class="DYnone">
                                                        <a class='btn-icon btn-icon-yellow btn-icon-chakan-yellow'
                                                           href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${compareResultInfo.childGeneId}">
                                                            查看
                                                        </a>
                                                    </td>
                                                <tr>
                                            </c:if>
                                            <input type="hidden" name="caseId" value="${compareResultInfo.caseId}">
                                            <input type="hidden" name="fatherSampleNo"
                                                   value="${compareResultInfo.fatherSampleNo}">
                                            <input type="hidden" name="motherSampleNo"
                                                   value="${compareResultInfo.motherSampleNo}">
                                            <input type="hidden" name="childSampleNo"
                                                   value="${compareResultInfo.childSampleNo}">
                                            <input type="hidden" name="relativeMatchCount"
                                                   value="${compareResultInfo.compareGeneSum}">
                                            <input type="hidden" name="relativeTotalProbability"
                                                   value="${compareResultInfo.compareTotalProbability}">
                                            <input type="hidden" name="relativePopulationId"
                                                   value="${compareResultInfo.comparePopulationId}">
                                            <input type="hidden" name="relativeReferenceId"
                                                   value="${compareResultInfo.referenceId}">
                                            <input type="hidden" name="relativeSameCount"
                                                   value="${compareResultInfo.matchLimit}">
                                            <input type="hidden" name="relativeDiffCount"
                                                   value="${compareResultInfo.tolerance}">
                                            <input type="hidden" name="relativeMatchMode"
                                                   value="${compareResultInfo.matchMode}">
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>未比中检材(${fn:length(notMatchedGeneList)})</div>
            </div>
            <div class="panel-body">
                <div>
                    <input type="hidden" class="caseId" value="${caseId}">
                    <input type="hidden" class="acceptDatetime" value="${acceptDatetime}">
                    <input type="hidden" class="consignmentId" value="${consignmentId}">
                    <%--<span class="pull-right submitWarehouseNum">提交入库数：<span>0</span></span>--%>

                    <%--<button type="button" name="submitWarehouse" class="btn btn-yellow-border pull-right"--%>
                    <%--style="margin-right: 20px;">提交入库--%>
                    <%--</button>--%>
                    <div class="sameTypeContent" class="tab-content">
                        <div class="tab-pane fade in active" id="noRatio">
                            <table class="table table-hover table-bordered bigTable noMargin">
                                <thead>
                                <tr>
                                    <th>检材编号</th>
                                    <th>检材名称</th>
                                    <th>检材类型</th>
                                    <th class="DYnone">
                                        <label class="custom-control checkbox-inline">
                                            <input class="custom-control-input" type="checkbox" id="notMatchedCheck"
                                                   name="notMatchedCheck">
                                            <span class="custom-control-label" style="font-weight: 600">入库操作</span>
                                        </label>
                                    </th>
                                    <th class="DYnone">
                                        <label class="custom-control checkbox-inline">
                                            <input class="custom-control-input" type="checkbox" id="invalidCheck"
                                                   name="invalidCheck">
                                            <span class="custom-control-label" style="font-weight: 600">无效检材</span>
                                        </label>
                                    </th>
                                    <th class="DYnone">操作</th>
                                </tr>
                                </thead>
                                <tbody id="notMatchedGeneTBody">
                                <c:forEach items="${notMatchedGeneList}" var="notMatched" varStatus="s">
                                    <tr>
                                        <td>${notMatched.sampleNo}
                                            <c:choose>
                                                <c:when test="${notMatched.geneType eq '4'}">
                                                    <span style="color:blue;font-size: 5px;">   (混合)</span>
                                                </c:when>
                                                <c:otherwise>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${notMatched.sampleName}</td>
                                        <td>${notMatched.sampleTypeName}</td>
                                        <td class="DYnone">
                                            <input type="hidden" name="sampleId" value="${notMatched.sampleId}">
                                            <input type="hidden" name="geneType" value="${notMatched.geneType}">
                                            <label class="custom-control checkbox-inline">
                                                <input class="custom-control-input" type="checkbox"
                                                       name="notMatchedBox">
                                                <span class="custom-control-label">入库操作</span>
                                            </label>
                                        </td>
                                        <td class="DYnone">
                                            <input type="hidden" name="invalidGeneId"
                                                   value="${notMatched.auditedGeneId}">
                                            <label class="custom-control checkbox-inline">
                                                <input class="custom-control-input" type="checkbox"
                                                       name="invalidBox">
                                                <span class="custom-control-label">无效检材</span>
                                            </label>
                                        </td>
                                        <td class="DYnone">
                                            <input type="hidden" name="notMatchedGeneId"
                                                   value="${notMatched.auditedGeneId}">
                                            <button type="button" name="notMatched"
                                                    class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                                查看
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
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>未检出检材(${fn:length(notDetectedList)})</div>
            </div>
            <div class="panel-body">
                <div>
                    <input type="hidden" class="caseId" value="${caseId}">
                    <input type="hidden" class="acceptDatetime" value="${acceptDatetime}">
                    <input type="hidden" class="consignmentId" value="${consignmentId}">
                    <%--<span class="pull-right submitWarehouseNum">提交入库数：<span>0</span></span>--%>

                    <%--<button type="button" name="submitWarehouse" class="btn btn-yellow-border pull-right"--%>
                    <%--style="margin-right: 20px;">提交入库--%>
                    <%--</button>--%>
                    <div class="sameTypeContent" class="tab-content">
                        <div class="tab-pane fade in active" id="noCheckOut">
                            <table class="table table-hover table-bordered bigTable noMargin">
                                <thead>
                                <tr>
                                    <th>检材编号</th>
                                    <th>检材名称</th>
                                    <th>检材类型</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="notDetectedGeneTBody">
                                <c:forEach items="${notDetectedList}" var="notDetected" varStatus="s">
                                    <tr>
                                        <td>${notDetected.sampleNo}</td>
                                        <td>${notDetected.sampleName}</td>
                                        <td>${notDetected.sampleTypeName}</td>
                                        <td>
                                            <input type="hidden" name="notDetectedGeneId"
                                                   value="${notDetected.auditedGeneId}">
                                            <button type="button" name="notDetected"
                                                    class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                                查看
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
        </div>
    </div>
</div>
<div class="row Modular" style="padding-bottom: 105px;">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>混合检材(${fn:length(mixGroupList)})</div>
            </div>
            <div class="panel-body">
                <%--<div class="row">--%>

                <%--<div class="col-md-2 seachInputBox">--%>
                <%--<div class="form-group">--%>
                <%--<label>种群</label>--%>
                <%--<select class="form-control samePopulation" name="samePopulation">--%>
                <%--<c:forEach items="${raceList}" var="race" varStatus="r">--%>
                <%--<option value="${race.id}">${race.raceName}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-1 seachInputBox">--%>
                <%--<div class="form-group seachButtonBox">--%>
                <%--<button class="btn btn-blue afreshMatchBtn" type="button">重新比对</button>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div>
                    <input type="hidden" class="caseId" value="${caseId}">
                    <input type="hidden" class="acceptDatetime" value="${acceptDatetime}">
                    <input type="hidden" class="consignmentId" value="${consignmentId}">
                    <%--<span class="pull-right submitWarehouseNum">提交入库数：<span>0</span></span>--%>

                    <%--<button type="button" name="submitWarehouse" class="btn btn-yellow-border pull-right"--%>
                    <%--style="margin-right: 20px;">提交入库--%>
                    <%--</button>--%>
                    <div class="sameTypeContent" class="tab-content">
                        <div class="tab-pane fade in active" id="mixing">
                            <table class="table table-hover table-bordered bigTable noMargin">
                                <thead>
                                <tr>
                                    <th>组</th>
                                    <th>检材编号</th>
                                    <th>检材名称</th>
                                    <th>检材类型</th>
                                    <th class="DYnone">
                                        <label class="custom-control checkbox-inline">
                                            <input class="custom-control-input" type="checkbox" id="mixAllCheck"
                                                   name="mixAllCheck">
                                            <span class="custom-control-label" style="font-weight: 600">入库操作</span>
                                        </label>
                                    </th>
                                    <th class="DYnone">操作</th>
                                </tr>
                                </thead>
                                <tbody id="mixGroupTBody">
                                <c:forEach items="${mixGroupList}" var="matchedGroup" varStatus="group">
                                    <c:forEach items="${matchedGroup.mixMatchedSampleList}" var="matchedSample"
                                               varStatus="matched">
                                        <c:if test="${matched.index eq 0}">
                                            <tr class="tableBtnRed">
                                                <td rowspan="${fn:length(matchedGroup.mixMatchedSampleList)}"
                                                    class="tableFont">
                                                    <input type="hidden" name="groupId"
                                                           value="${matchedGroup.groupId}" class="groupId">${group.count}
                                                </td>
                                                <td class="paddingLeft">
                                                    <input type="hidden" name="sampleId"
                                                           value="${matchedSample.sampleId}">
                                                    <input type="hidden" name="sampleNo"  class="sampleNo"
                                                           value="${matchedSample.sampleNo}">
                                                        ${matchedSample.sampleNo}
                                                </td>
                                                <td>${matchedSample.sampleName}</td>
                                                <td>${matchedSample.sampleTypeName}</td>
                                                <td class="DYnone">
                                                    <input type="hidden" name="sampleId"
                                                           value="${matchedSample.sampleId}">
                                                    <label class="custom-control checkbox-inline">
                                                        <input class="custom-control-input" type="checkbox"
                                                               name="mixBox">
                                                        <span class="custom-control-label">入库操作</span>
                                                    </label>
                                                </td>
                                                <td class="DYnone">
                                                        <%-- <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"
                                                                 >查看
                                                         </button>--%>
                                                    <button type="button" name="viewMixResult"
                                                            class="btn-icon btn-icon-red btn-icon-comparison-red">
                                                        进入混合分析
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${matched.index gt 0}">
                                            <tr id="matchedSampleId">
                                                <td class="paddingLeft">
                                                    <input type="hidden" name="sampleId"
                                                           value="${matchedSample.sampleId}">
                                                        <%--<a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${matchedSample.auditedGeneId}">${matchedSample.sampleNo}</a>--%>
                                                        ${matchedSample.sampleNo}
                                                </td>
                                                <td>${matchedSample.sampleName}</td>
                                                <td>${matchedSample.sampleTypeName}</td>
                                                <td class="DYnone">
                                                    <input type="hidden" name="sampleId"
                                                           value="${matchedSample.sampleId}">
                                                    <label class="custom-control checkbox-inline">
                                                        <input class="custom-control-input" type="checkbox"
                                                               name="mixBox">
                                                        <span class="custom-control-label">入库操作</span>
                                                    </label>
                                                </td>
                                                <%--<td class="DYnone">--%>
                                                        <%--&lt;%&ndash;<button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"--%>
                                                                 <%--data-target="#look">查看--%>
                                                        <%--</button>&ndash;%&gt;--%>
                                                    <%--<button type="button" name="viewMixResult"--%>
                                                            <%--class="btn-icon btn-icon-red btn-icon-comparison-red">--%>
                                                        <%--查看比对详情--%>
                                                    <%--</button>--%>
                                                <%--</td>--%>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--endprint1-->

<div class="row btn-box footerSelect">
    <div class="col-md-4 seachInputBox">
        <div class="form-group footSelect">
            <label>检验人:</label>
            <div class="select">
                <select name="titleOne" id="titleOne" class="form-control">
                    <option value="" selected="">请选择</option>

                    <c:forEach items="${titlesList}" var="titlesList">
                        <%--<c:choose>
                            <c:when test="${titlesList.dictName=='主检法医师'}">
                                <option value="${titlesList.dictName}" selected="selected">${titlesList.dictName}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${titlesList.dictName}" <c:if test="${examiner.titleOne eq titlesList.dictName}">selected</c:if>>${titlesList.dictName}</option>
                            </c:otherwise>
                        </c:choose>--%>
                        <option value="${titlesList.dictName}"
                                <c:if test="${examiner.titleOne eq titlesList.dictName}">selected</c:if>>${titlesList.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="select">
                <select id="first" name="inspector1" value="${examiner.inspector1}"
                        class="form-control">
                    <option value="" selected="">请选择</option>
                    <c:forEach items="${amPersonalInfoList}" var="amPersonalInfo">
                        <option value="${amPersonalInfo.entity.personalId}"
                                <c:if test="${examiner.inspector1 eq amPersonalInfo.entity.personalId}">selected</c:if> >
                                ${amPersonalInfo.entity.fullName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-4 seachInputBox">
        <div class="form-group footSelect">
            <label>复核人:</label>
            <div class="select">
                <select name="titleTwo" id="titleTwo" class="form-control">
                    <option value="" selected="">请选择</option>
                    <c:forEach items="${titlesList}" var="titlesList">
                        <option value="${titlesList.dictName}"
                                <c:if test="${examiner.titleTwo eq titlesList.dictName}">selected</c:if>>${titlesList.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="select">
                <select id="second" name="inspector2" value="${examiner.inspector2}"
                        class="form-control">
                    <option value="" selected="">请选择</option>
                    <c:forEach items="${amPersonalInfoList}" var="amPersonalInfo">
                        <option value="${amPersonalInfo.entity.personalId}"
                                <c:if test="${examiner.inspector2 eq amPersonalInfo.entity.personalId}">selected</c:if> >
                                ${amPersonalInfo.entity.fullName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-4 seachInputBox">
        <div class="form-group footSelect">
            <label>授权签字人审核:</label>
            <div class="select">
                <select name="titleThree" id="titleThree" class="form-control">
                    <option value="titleOne" selected="">请选择</option>
                    <c:forEach items="${titlesList}" var="titlesList">
                        <option value="${titlesList.dictName}"
                                <c:if test="${examiner.titleThree eq titlesList.dictName}">selected</c:if>>${titlesList.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="select">
                <select id="third" name="inspector3" value="${examiner.inspector3}"
                        class="form-control">
                    <option value="" selected="">请选择</option>
                    <c:forEach items="${amPersonalInfoList}" var="amPersonalInfo">
                        <option value="${amPersonalInfo.entity.personalId}"
                                <c:if test="${examiner.inspector3 eq amPersonalInfo.entity.personalId}">selected</c:if> >
                                ${amPersonalInfo.entity.fullName}
                        </option>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-4">&nbsp;</div>
    <div class="col-md-6 seachInputBox" style="width: auto;padding: 0 15px;">
        <div class="form-group seachButtonBox">
            <button class="btn btn-blue" type="button" id="saveBtn" >保存比对结果</button>
            <button class="btn btn-yellow" type="button" id="generateInstrument">生成鉴定书/报告</button>
            <button type="button" name="submitWarehouse" class="btn btn-green pull-right" style="margin-right: 20px;">
                提交入库
            </button>
        </div>
    </div>
    <div class="col-md-2">&nbsp;</div>
</div>
</div>
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/relativeComparison.js"></script>
<script>
    // $("button[name='viewMatchRelation']").on("click", function(){
    //     alert(111);
    //     console.log($(this).parent())
    //     // location.href ="<%=path%>/center/viewRelationCompareResult?" + getParameter($(this).parent());
    //
    // });
    function getLocationValue(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)return decodeURI(r[2]);
        return null;
    }
   //$(".caseName").text(getLocationValue("caseName"));
   //$(".caseNo").text(getLocationValue("caseNo"));
    // var id = getLocationValue("id"); //直接获取url中的字段
    function preview(oper) {
        $(".DYnone").css("display", "none");
        if (oper < 10) {
            bdhtml = window.document.body.innerHTML;//获取当前页的html代码
            sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
            eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
            prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html
            prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
            window.document.body.innerHTML = prnhtml;
            window.print();
            window.document.body.innerHTML = bdhtml;
            $(".DYnone").css("display", "block");
        } else {
            window.print();
            $(".DYnone").css("display", "block");
        }

    }

    var baseurl = "${ctx}";
    $(function () {
        $(".sameTypeContent").find("tbody").find(".custom-control-input").change(function () {
            if ($(".sameTypeContent").find("tbody").find(".custom-control-input:checked").length == 0) {
                $(".sameType").siblings(".submitWarehouseNum").find("span").html(0)
            } else {
                $(".sameType").siblings(".submitWarehouseNum").find("span").html($(".sameTypeContent").find("tbody").find(".custom-control-input:checked").length)
            }
        })
        $("#kinshipContent").find("tbody").find(".custom-control-input").change(function () {
            if ($("#kinshipContent").find("tbody").find(".custom-control-input:checked").length == 0) {
                $("#kinshipTab").siblings(".submitWarehouseNum").find("span").html(0)
            } else {
                $("#kinshipTab").siblings(".submitWarehouseNum").find("span").html($("#kinshipContent").find("tbody").find(".custom-control-input:checked").length)
            }
        })
        $('#relativeComparisonBox').on('hide.bs.modal', function () {
            var caseId = $(".caseId").val();
            var consignmentId = $(".consignmentId").val();
            var matchLimit = $(".minSameCaseCount").val();
            var url = "<%=path%>/center/enterCaseCompare?caseId=" + caseId + "&consignmentId=" + consignmentId
                    + "&matchLimit=" + matchLimit.trim();
            location.href = url;
        });

        //查看未比中检材基因型
        $("button[name='notMatched']", "#notMatchedGeneTBody").on("click", function () {
            var notMatchedGeneId = $("input[name='notMatchedGeneId']", $(this).parent()).val();

            location.href = "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" + notMatchedGeneId;
        });

        //查看未检出检材基因型
        $("button[name='notDetected']", "#notDetectedGeneTBody").on("click", function () {
            var notDetectedGeneId = $("input[name='notDetectedGeneId']", $(this).parent()).val();

            location.href = "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" + notDetectedGeneId;
        });

        //同一比对
        //给全选的复选框添加事件
        $("#allChecked").click(function () {
            // this 全选的复选框
            var allChecked = this.checked;
            //获取name=box的复选框 遍历输出复选框
            $("input[name=box]").each(function () {
                this.checked = allChecked;
                $(this).change()
            });
        });

        //给name=box的复选框绑定单击事件
        $("input[name=box]").click(function () {
            //获取选中复选框长度
            var length = $("input[name=box]:checked").length;
            //未选中的长度
            var len = $("input[name=box]").length;
            if (length == len) {
                $("#allChecked").get(0).checked = true;
            } else {
                $("#allChecked").get(0).checked = false;
            }
        });

        //未比中
        //给全选的复选框添加事件
        $("#notMatchedCheck").click(function () {
            // this 全选的复选框
            var notMatchedCheck = this.checked;
            //获取name=box的复选框 遍历输出复选框
            $("input[name=notMatchedBox]").each(function () {
                this.checked = notMatchedCheck;
                $(this).change()
            });
        });

        //给name=box的复选框绑定单击事件
        $("input[name=notMatchedBox]").click(function () {
            //获取选中复选框长度
            var length = $("input[name=notMatchedBox]:checked").length;
            //未选中的长度
            var len = $("input[name=notMatchedBox]").length;
            if (length == len) {
                $("#notMatchedCheck").get(0).checked = true;
            } else {
                $("#notMatchedCheck").get(0).checked = false;
            }
        });

        //无效检材
        //给全选的复选框添加事件
        $("#invalidCheck").click(function () {
            // this 全选的复选框
            var invalidCheck = this.checked;
            //获取name=box的复选框 遍历输出复选框
            $("input[name=invalidBox]").each(function () {
                this.checked = invalidCheck;
                $(this).change()
            });
        });

        //给name=box的复选框绑定单击事件
        $("input[name=invalidBox]").click(function () {
            //获取选中复选框长度
            var length = $("input[name=invalidBox]:checked").length;
            //未选中的长度
            var len = $("input[name=invalidBox]").length;
            if (length == len) {
                $("#invalidCheck").get(0).checked = true;
            } else {
                $("#invalidCheck").get(0).checked = false;
            }
        });

        //混合比对
        //给全选的复选框添加事件
        $("#raletionAllCheck").click(function () {
            // this 全选的复选框
            var raletionAllCheck = this.checked;
            //获取name=box的复选框 遍历输出复选框
            $("input[name=raletionCheck]").each(function () {
                this.checked = raletionAllCheck;
                $(this).change()
            });
        });

        //给name=box的复选框绑定单击事件
        $("input[name=raletionCheck]").click(function () {
            //获取选中复选框长度
            var length = $("input[name=raletionCheck]:checked").length;
            //未选中的长度
            var len = $("input[name=raletionCheck]").length;
            if (length == len) {
                $("#raletionAllCheck").get(0).checked = true;
            } else {
                $("#raletionAllCheck").get(0).checked = false;
            }
        });

        //亲缘比对
        //给全选的复选框添加事件
        $("#mixAllCheck").click(function () {
            // this 全选的复选框
            var mixAllCheck = this.checked;
            //获取name=box的复选框 遍历输出复选框
            $("input[name=mixBox]").each(function () {
                this.checked = mixAllCheck;
                $(this).change()
            });
        });

        //给name=box的复选框绑定单击事件
        $("input[name=mixBox]").click(function () {
            //获取选中复选框长度
            var length = $("input[name=mixBox]:checked").length;
            //未选中的长度
            var len = $("input[name=mixBox]").length;
            if (length == len) {
                $("#mixAllCheck").get(0).checked = true;
            } else {
                $("#mixAllCheck").get(0).checked = false;
            }
        });

        //同一、混合提交入库
        $("button[name='submitWarehouse']").on("click", function () {
            submitWarehouse();
        })

        //亲缘提交入库
        $("button[name='relationRubmitWarehouse']").on("click", function () {
            submitWarehouse();
        })

        function submitWarehouse() {
            var sampleArr = new Array();
            var sample;

            //同一比对
            var checkCount = 0;
            $("tr", "#matchedGroupTBody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;

                    sample.sampleId = $("input[name='sampleId']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            //未比中
            var notMatchedCheckCount = 0;
            $("tr", "#notMatchedGeneTBody").each(function () {
                sample = {};
                var checkBox = $("input[name='notMatchedBox']", $(this)).is(":checked");
                if (checkBox) {
                    notMatchedCheckCount++;

                    sample.sampleId = $("input[name='sampleId']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            //混合比对
            var mixCheckCount = 0;
            $("tr", "#mixGroupTBody").each(function () {
                sample = {};
                var checkBox = $("input[name='mixBox']", $(this)).is(":checked");
                if (checkBox) {
                    mixCheckCount++;

                    sample.sampleId = $("input[name='sampleId']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            //亲缘比对
            var raletionCheckCount = 0;
            $("tr", "#raletionID").each(function () {
                sample = {};
                var checkBox = $("input[name='raletionCheck']", $(this)).is(":checked");
                if (checkBox) {
                    raletionCheckCount++;

                    sample.sampleId = $("input[name='sampleId']", $(this)).val();

                    sampleArr.push(sample);
                }
            });
            if ((checkCount + notMatchedCheckCount + mixCheckCount + raletionCheckCount) <= 0) {
                alert("请选择要入库的检材!");
                return false;
            }

            $.ajax({
                url: "<%=path%>/center/cacheSampleInfo",
                type: "post",
                data: JSON.stringify(sampleArr),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        var caseId = $(".caseId").val();
                        var consignmentId = $(".consignmentId").val();

                        location.href = "<%=path%>/center/warehouseCompare?caseId=" + caseId + "&consignmentId=" + consignmentId;
                    } else {
                        alert("提交入库失败!");
                    }
                },
                error: function (data) {
                    alert("提交入库失败!");
                }
            });
        }

    });

    $("button[name='viewMixResult']").on("click", function () {
        var caseId = $(".caseId").val();
        var consignmentId = $(".consignmentId").val();
        var mixMatchLimit = $(".minSameMixCount").val();
        var groupId = $(".groupId").val();

        var text = $(this).parent().siblings("td").eq("1").text();

        console.log(text);
        console.log("结束");
        /*if(isNaN(mixMatchLimit)){*/
        // if ("" == mixMatchLimit) {
        //     alert("请输入混合匹配下限!");
        //     $(".mixSameCaseCount").focus();
        //     return
        // }
        location.href = baseurl + "/center/details?caseId=" + caseId + "&consignmentId=" + consignmentId + "&mixMatchLimit=" + mixMatchLimit.trim()+ "&groupId=" + groupId+ "&sampleNo=" + text.trim();

    })


</script>
</body>

</html>