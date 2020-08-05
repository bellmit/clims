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
    <style>
        .tabBox {
            padding: 30px 0 20px;
            border-bottom: 1px solid #f2f2f2;
            text-align: center;
        }

        #myTab {
            border: none;
            display: inline-block;
        }

        #myTab li:nth-child(1) a {
            border-radius: 50px 0 0 50px;
            border-left: 1px solid #0c81f5;
        }

        #myTab li:nth-last-child(1) a {
            border-radius: 0 50px 50px 0;
            border-right: 1px solid #0c81f5;
        }

        #myTab li {
            border: none;
        }

        #myTab li a {
            background: #fff;
            color: #0c81f5;
            border: none;
            position: relative;
            border-radius: 0px;
            border-top: 1px solid #0c81f5;
            border-bottom: 1px solid #0c81f5;
        }

        #myTab li + li {
            margin-left: -2px;
        }

        #myTab li + li a {
            border-left: 1px solid #0c81f5;
        }

        #myTab > li.active > a,
        #myTab > li.active > a:focus,
        #myTab > li.active > a:hover,
        #myTab li a:hover {
            background: #0c81f5;
            color: #fff;
        }

        #myTabContent {
            padding-bottom: 60px;
        }

        .extractMethod {
            margin-left: 15px !important;
            height: 135px;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            margin-bottom: 20px;

        }

        .extractMethod > div {
            width: auto;
            min-width: 50%;
            /*width: 20%;*/
            /*height: 305px;*/
            margin: 10px 0;
        }

        .extractMethod > div > div {
            /*height: 100%;*/
            /*border: 1px solid #f6f6f6;*/
            border-radius: 5px;
            /*background: #fafafa;*/
        }

        .extractMethodTit {
            /*height: 60px;*/
            /*padding: 17px;*/
            text-align: left;
            /*line-height: 60px;*/
            padding-bottom: 0px;
            /*background: #f8f8f8;*/
            color: #5c5c5c;
            font-weight: 600;
            font-size: 12px;
        }

        .extractMethodTit span {
            /*height: 100%;*/
            width: 20px;
            display: inline-block;
            /*background: #fff0dd;*/
            color: #ff9200;
            float: left;
        }

        .extractMethodMain {
            height: 245px;
            padding: 12px;
            overflow-y: auto;
            border-top: 1px solid #f6f6f6;
        }

        .checkbox-inline, .radio-inline {
            padding-left: 0px;
        }

        .form-control {
            border-radius: 0px !important;
        }

        .select:after {
            content: "";
            width: 12px;
            height: 12px;
            border-bottom: 2px solid #d0d0d0;
            border-right: 2px solid #d0d0d0;
            transform: rotate(45deg);
            position: absolute;
            right: 7px !important;
            top: 0%;
            pointer-events: none;
            margin-top: 8px;
        }

        .tools span {
            color: #000;
            display: inline-block;
            margin-right: 10px;
        }

        .tools {
            margin-left: 20px;
        }

        .tools input {
            width: 145px;
            display: inline-block;
            color: #296fff;
        }

        .btn-box button + button {
            margin-left: 10px;
        }

        .kitBox > div > div,
        .kitBoxTwe > div > div,
        .deviceBox > div > div {
            height: 135px;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            margin-bottom: 20px;
        }

        .deviceBoxTwe > div > div {
            height: 135px;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            margin-bottom: 20px;
        }

        .kitBox > div > div .col-md-12 {
            margin-top: 15px;
        }

        .kitBoxTwe > div > div .col-md-12 {
            margin-top: 15px;
        }

        .noBorder {
            border: none !important;
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

        .col-md-3.control-label {
            height: 34px;
            line-height: 34px;
        }

        .noPadding {
            padding: 0px !important;
        }

        .deviceBox > div > div ul {
            text-align: center;
            padding-top: 25px;
            padding-left: 120px;
        }

        .deviceBoxTwe > div > div ul {
            text-align: center;
            padding-top: 25px;
            /*padding-left: 120px;*/
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

        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
            margin-top: 0px !important;
        }

        .btn-checkbox > li {
            margin-left: 40px;
        }

        .col-md-3.control-label,
        .col-md-4.control-label {
            height: 34px;
            line-height: 34px;
        }

        .removeCherk {
            line-height: 34px !important;
            font-size: 25px !important;
            color: #ff5a56;
            cursor: pointer;
        }
    </style>
</head>
<body>
<jsp:include page="testProgressBar.jsp"/>
<div class="col-md-12 tabBox">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active conventionalRoutine">
            <a href="#conventional" data-toggle="tab">常规提取记录表</a>
        </li>
        <li class="fineSpotSpot">
            <a href="#fineSpot" data-toggle="tab">精斑提取记录表</a>
        </li>
        <li class="specialSpecial">
            <a href="#special" data-toggle="tab">特殊提取记录表</a>
        </li>
        <li class="otherOther">
            <a href="#other" data-toggle="tab">其他提取记录表</a>
        </li>
    </ul>
</div>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="conventional">
        <div class="row Modular">
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading red" style="height: 55px;padding-top: 15px;">
                        <div>提取方法</div>
                    </div>
                    <div class="row extractMethod">
                        <c:forEach items="${extractTestMethodList}" var="method" varStatus="em">
                            <div class="col-md-6">
                                <div>
                                    <div class="extractMethodTit"><span>${method.dictCode}</span>${method.dictName}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading blue noBorder">
                        <div>试剂名称</div>
                        <button class="btn btn-blue-border" data-toggle="modal" data-target="#kitPopBox">添加</button>
                    </div>
                    <div class="row kitBox">
                        <div class="col-md-12">
                            <div class="row" id="PkKit">
                                <c:forEach items="${selectList}" var="selectPanel">
                                    <div class="col-md-12">
                                        <div class="col-md-2">
                                            <div class="btnCheck">${selectPanel.reagentName}</div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group row">
                                                <label class="col-md-3 control-label noPadding">批号:</label>
                                                <div class="col-md-9 noPadding">
                                                    <input type="text" class="form-control"
                                                           value="${selectPanel.batchNumber}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group row">
                                                <label class="col-md-4 control-label noPadding">有效期:</label>
                                                <div class="col-md-8 noPadding">
                                                    <input type="text" class="form-control"
                                                           value="<fmt:formatDate value='${selectPanel.validityTime}' pattern='yyyy-MM-dd'/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-1">
                                            <i class="fa fa-times-circle removeCherk" aria-hidden="true"></i>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading yellow noBorder" style="height: 55px;padding-top: 15px;">
                        <div>选择设备</div>
                    </div>
                    <div class="row deviceBox">
                        <div class="col-md-12">
                            <div class="form-group">
                                <ul class="btn-checkbox clearfix">
                                    <c:forEach items="${equipmentNameInfoList}" var="equipmentNameInfo">
                                        <c:set var="flag" value="true"></c:set>
                                        <c:if test="${equipment == null}">
                                            <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                class="pull-left equipment">${equipmentNameInfo.equipmentName}</li>
                                            <c:set var="flag" value="false"></c:set>
                                        </c:if>
                                        <c:forEach items="${equipment}" var="list">
                                            <c:if test="${equipmentNameInfo.equipmentNo eq list}">
                                                <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                    class="pull-left equipment active">${equipmentNameInfo.equipmentName}</li>
                                                <c:set var="flag" value="false"></c:set>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag}">
                                            <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                class="pull-left equipment">${equipmentNameInfo.equipmentName}</li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row Modular ">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading yellow">
                        <div>提取记录表</div>
                        <span class="tools pull-right">
                            <span>提取人</span>
                            <input type="text" class="form-control" id="routine_extract_extractPerson1"
                                   value="${routine_extract_extractPerson1}">
                        </span>
                        <span class="tools pull-right">
                            <span>结束时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="routine_extract_extractionExperimentEndDate"
                                   value="${routine_extract_extractionExperimentDate}">
                        </span>
                        <span class="tools pull-right">
                            <span>提取时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="routine_extract_extractionExperimentDate"
                                   value="${routine_extract_extractionExperimentDate}">
                            <span>至</span>
                        </span>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered bigTable">
                            <thead>
                            <tr>
                                <th rowspan="2" width="55px">序号</th>
                                <%--<th rowspan="2" style='width:6.1%'>位置</th>--%>
                                <th rowspan="2">检材编号</th>
                                <th rowspan="2">检材名称</th>
                                <th rowspan="2" width="120px">检材类型</th>
                                <%--<th rowspan="2" width="100px">转移方法</th>
                                <th rowspan="2">提取部位</th>--%>
                                <th rowspan="2" width="75px">方法</th>
                                <th colspan="5">使用仪器</th>
                                <th rowspan="2" width="70px">操作</th>
                            </tr>
                            <tr>
                                <th width="75px">离</th>
                                <th width="75px">浴</th>
                                <th width="75px">干</th>
                                <th width="75px">摇</th>
                                <th width="75px">振</th>
                            </tr>
                            </thead>
                            <tbody id="routine_extract">
                            <input type="hidden" name="routine_extract_extractId" id="routine_extract_extractId"
                                   value="${routine_extract_extractId}">
                            <input type="hidden" name="operateType" id="routine_extract_operateType"
                                   value="${operateType}">
                            <c:forEach items="${labExtractSampleListRoutine}" var="routine" varStatus="s">

                                <tr>
                                    <td>${s.index+1}</td>
                                        <%--<td>--%>
                                        <%--<input name="samplePostion" type="text" class="form-control small"--%>
                                        <%--onmouseover="this.title=this.value" value="${routine.samplePostion}">--%>
                                        <%--</td>--%>
                                    <td title="${sample.sampleNo}">
                                        <input name="sampleNo" type="text" class="form-control small"
                                               onmouseover="this.title=this.value" value="${routine.sampleNo}"
                                               readonly="readonly">
                                    </td>
                                    <td title="${sample.sampleName}">
                                        <input name="sampleName" type="text" class="form-control small"
                                               onmouseover="this.title=this.value" value="${routine.sampleName}"
                                               readonly="readonly">
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" disabled name="sampleType">
                                                <c:forEach items="${sampleTypeList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                        <%--<td>
                                            <div class="select">
                                                <select class="form-control small" name="transferMethod">
                                                    <c:forEach items="${extractMethodList}" var="list">
                                                        <option value="${list.dictCode}"
                                                                <c:if test="${routine.transferMethod eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <input name="extractPart" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${routine.extractPart}">
                                        </td>--%>
                                    <td>
                                        <div class="select">
                                            <select class="form-control small" name="extractMethod">
                                                <c:forEach items="${extractTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.extractMethod eq list.dictCode}">selected</c:if>>${list.dictCode}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" id="leave" name="leave">
                                                <option value="0" selected>无</option>
                                                <c:forEach items="${leaveTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.leave eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" id="bath" name="bath">
                                                <option value="0" selected>无</option>
                                                <c:forEach items="${bathTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.bath eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" id="dry" name="dry">
                                                <option value="0" selected>无</option>
                                                <c:forEach items="${dryTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.dry eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" id="shake" name="shake">
                                                <option value="0" selected>无</option>
                                                <c:forEach items="${shakeTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.shake eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" id="earthquake" name="earthquake">
                                                <option value="0" selected>无</option>
                                                <c:forEach items="${earthquakeTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${routine.earthquake eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <input type="hidden" name="id" value="${routine.id}"/>
                                        <input type="hidden" name="sampleId" value="${routine.sampleId}"/>
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
    </div>
    <div class="tab-pane fade" id="fineSpot">
        <div class="row Modular">
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading blue noBorder">
                        <div>试剂名称</div>
                        <button class="btn btn-blue-border" data-toggle="modal" data-target="#kitPopBoxTwe">添加</button>
                    </div>
                    <div class="row kitBoxTwe">
                        <div class="col-md-12">
                            <div class="row" id="PkKitTwe">
                                <c:forEach items="${selectListTwe}" var="selectPanel">
                                    <div class="col-md-12">
                                        <div class="col-md-2">
                                            <div class="btnCheck">${selectPanel.reagentName}</div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group row">
                                                <label class="col-md-3 control-label noPadding">批号:</label>
                                                <div class="col-md-9 noPadding">
                                                    <input type="text" class="form-control"
                                                           value="${selectPanel.batchNumber}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group row">
                                                <label class="col-md-4 control-label noPadding">有效期:</label>
                                                <div class="col-md-8 noPadding">
                                                    <input type="text" class="form-control"
                                                           value="<fmt:formatDate value='${selectPanel.validityTime}' pattern='yyyy-MM-dd'/>">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-1">
                                            <i class="fa fa-times-circle removeCherk" aria-hidden="true"></i>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading yellow noBorder" style="height: 55px;padding-top: 15px;">
                        <div>选择设备</div>
                    </div>
                    <div class="row deviceBox">
                        <div class="col-md-12">
                            <div class="form-group">
                                <ul class="btn-checkbox clearfix">
                                    <c:forEach items="${equipmentNameInfoList}" var="equipmentNameInfo">
                                        <c:set var="flag" value="true"></c:set>
                                        <c:if test="${equipmentTwe == null}">
                                            <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                class="pull-left equipmentTwe">${equipmentNameInfo.equipmentName}</li>
                                            <c:set var="flag" value="false"></c:set>
                                        </c:if>
                                        <c:forEach items="${equipmentTwe}" var="list">
                                            <c:if test="${equipmentNameInfo.equipmentNo eq list}">
                                                <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                    class="pull-left equipmentTwe active">${equipmentNameInfo.equipmentName}</li>
                                                <c:set var="flag" value="false"></c:set>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${flag}">
                                            <li equipment_no="${equipmentNameInfo.equipmentNo}"
                                                class="pull-left equipmentTwe">${equipmentNameInfo.equipmentName}</li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row Modular ">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading yellow">
                        <div>提取记录表</div>
                        <span class="tools pull-right">
                            <span>提取人</span>
                            <input type="text" class="form-control" id="spot_extract_extractPerson1"
                                   value="${spot_extract_extractPerson1}">
                        </span>
                        <span class="tools pull-right">
                            <span>结束时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="spot_extract_extractionExperimentEndDate"
                                   value="${spot_extract_extractionExperimentDate}">
                        </span>
                        <span class="tools pull-right">
                            <span>提取时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="spot_extract_extractionExperimentDate"
                                   value="${spot_extract_extractionExperimentDate}">
                            <span>至</span>
                        </span>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered bigTable" style="margin-top: 0px">
                            <thead>
                            <tr>
                                <th rowspan="2" style="width: 55px">序号</th>
                                <th rowspan="2" style="width: 12%">检材编号</th>
                                <th rowspan="2" style="width: 10%">检材名称</th>
                                <th rowspan="2" style="width: 8%">检材类型</th>
                                <th rowspan="2" style="width: 8%">离心机</th>
                                <th colspan="3" style="text-align: center;width: 24%">一次消化</th>
                                <th rowspan="2">涂片镜检结果操作</th>
                            </tr>
                            <tr>
                                <th>TES</th>
                                <th>10%SDS</th>
                                <th style="width: 10%;">10mg/ml P.K</th>
                            </tr>
                            </thead>
                            <tbody id="spot_extract">
                            <input type="hidden" name="spot_extract_extractId" id="spot_extract_extractId"
                                   value="${spot_extract_extractId}">
                            <input type="hidden" name="operateType" id="spot_extract_operateType"
                                   value="${operateType}">
                            <c:forEach items="${labExtractSampleListSpot}" var="spot" varStatus="s">
                                <tr>
                                    <td>${s.index+1}</td>
                                    <td><input type="text" class="form-control" readonly name="sampleNo"
                                               value="${spot.sampleNo}"></td>
                                    <td><input type="text" class="form-control" name="sampleName" readonly
                                               value="${spot.sampleName}"></td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control" disabled name="sampleType">
                                                <c:forEach items="${sampleTypeList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${spot.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <select class="form-control" name="leave">
                                            <option value="0" selected>无</option>
                                            <c:forEach items="${leaveTestMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${spot.leave eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select></td>
                                    <td><input type="text" class="form-control" name="TES" value="${spot.TES}"></td>
                                    <td><input type="text" class="form-control" name="SDS" value="${spot.SDS}"></td>
                                    <td><input type="text" class="form-control" name="PK" value="${spot.PK}"></td>
                                    <td>
                                        <label class="custom-control checkbox-inline">
                                            <c:if test="${spot.isOK == true}">
                                                <input class="custom-control-input" name="isOK" checked value="1"
                                                       type="checkbox">
                                            </c:if>
                                            <c:if test="${spot.isOK == false}">
                                                <input class="custom-control-input" name="isOK" value="1"
                                                       type="checkbox">
                                            </c:if>
                                            <span class="custom-control-label">是否检见精子</span>
                                        </label>
                                        <label class="custom-control checkbox-inline" style="margin-left: 0px">
                                            <c:if test="${spot.isTwe == true}">
                                                <input class="custom-control-input" name="isTwe" checked
                                                       value="${spot.sampleNo}" sampleName="${spot.sampleName}"
                                                       sampleType="${spot.sampleType}" type="checkbox">
                                            </c:if>
                                            <c:if test="${spot.isTwe == false}">
                                                <input class="custom-control-input" name="isTwe"
                                                       value="${spot.sampleNo}" sampleName="${spot.sampleName}"
                                                       sampleType="${spot.sampleType}" type="checkbox">
                                            </c:if>
                                            <span class="custom-control-label">是否进行二次消化</span>
                                        </label>
                                        <input type="hidden" name="spot_extract_id" value="${spot.id}"/>
                                        <input type="hidden" name="spot_extract_sampleId" value="${spot.sampleId}"/>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <table class="table table-hover table-bordered bigTable" style="margin-top: 0px">
                            <thead>
                            <tr>
                                <%--<th rowspan="2" style="width: 55px">序号</th>--%>
                                <th rowspan="2" style="width: 12%">检材编号</th>
                                <th rowspan="2" style="width: 10%">检材名称</th>
                                <th colspan="5" style="text-align: center;width: 40%">二次消化</th>
                                <th rowspan="2">纯化方法</th>
                            </tr>
                            <tr>
                                <th>TES</th>
                                <th>10%SDS</th>
                                <th>chelex</th>
                                <th style="width: 10%">10mg/ml P.K</th>
                                <th>IM DTT</th>
                            </tr>
                            </thead>
                            <tbody id="spot_extractTwe">
                            <c:forEach items="${labExtractSampleListSpot}" var="spot" varStatus="s">
                                <c:if test="${spot.isTwe == true}">
                                    <tr style="" class="isSpot_extractTwe">
                                            <%--<td>${s.index+1}</td>--%>
                                        <td><input type="text" class="form-control" readonly name="sampleNo"
                                                   value="${spot.sampleNo}"></td>
                                        <td><input type="text" class="form-control" name="sampleName" readonly
                                                   value="${spot.sampleName}"></td>
                                        <td><input type="text" class="form-control" name="TES" value="${spot.TES1}">
                                        </td>
                                        <td><input type="text" class="form-control" name="SDS" value="${spot.SDS1}">
                                        </td>
                                        <td><input type="text" class="form-control" name="chelex"
                                                   value="${spot.chelex}"></td>
                                        <td><input type="text" class="form-control" name="PK" value="${spot.PK1}"></td>
                                        <td><input type="text" class="form-control" name="IMDTT" value="${spot.IMDTT}">
                                        </td>
                                        <td>
                                            <div class="select">
                                                <select class="form-control" name="purification" hidden>
                                                    <c:forEach items="${purificationMethodList}" var="list">
                                                        <option value="${list.dictCode}"
                                                                <c:if test="${spot.purification eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            <c:forEach items="${purificationMethodList}" var="list">
                                <input type="hidden" class="form-control purifications" name="purifications"
                                       value="${list.dictCode}" dictName="${list.dictName}">
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="special">
        <div class="row Modular">
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading red">
                        <div>提取方法</div>
                    </div>
                    <div class="row extractMethod">
                        <c:forEach items="${extractTestMethodList}" var="method" varStatus="em">
                            <div class="col-md-2">
                                <div>
                                    <div class="extractMethodTit"><span>${method.dictCode}</span>${method.dictName}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="row Modular ">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading yellow">
                        <div>提取记录表</div>
                        <%--<span class="tools pull-right">--%>
                        <%--<span>工作站提取板号</span>--%>
                        <%--<input type="text" class="form-control" readonly>--%>
                        <%--</span>--%>
                        <span class="tools pull-right">
                            <span>提取人</span>
                            <input type="text" class="form-control" id="special_extract_extractPerson1"
                                   value="${special_extract_extractPerson1}">
                        </span>
                        <span class="tools pull-right">
                            <span>结束时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="special_extract_extractionExperimentEndDate"
                                   value="${special_extract_extractionExperimentDate}">
                        </span>
                         <span class="tools pull-right">
                            <span>提取时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="special_extract_extractionExperimentDate"
                                   value="${special_extract_extractionExperimentDate}">
                             <span>至</span>
                        </span>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered bigTable">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th style="width: 250px">检材编号</th>
                                <th style="width: 300px;">检材名称</th>
                                <th>提取方法</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="special_extract">
                            <input type="hidden" name="special_extract_extractId" id="special_extract_extractId"
                                   value="${special_extract_extractId}">
                            <input type="hidden" name="operateType" id="special_extract_operateType"
                                   value="${operateType}">
                            <c:forEach items="${labExtractSampleListSpecial}" var="special" varStatus="s">

                                <tr>
                                    <td>${s.index+1}</td>
                                    <td><input type="text" class="form-control" name="sampleNo" readonly
                                               value="${special.sampleNo}"></td>
                                    <td><input type="text" class="form-control" name="sampleName" readonly
                                               value="${special.sampleName}"></td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control small" name="extractMethod">
                                                <c:forEach items="${extractTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${special.extractMethod eq list.dictCode}">selected</c:if>>${list.dictCode}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red">删除
                                        </button>
                                        <input type="hidden" name="special_extract_id" value="${special.id}"/>
                                        <input type="hidden" name="special_extract_sampleId"
                                               value="${special.sampleId}"/>
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
    <div class="tab-pane fade" id="other">
        <div class="row Modular">
            <div class="col-md-3">
                <div class="panel panel-default">
                    <div class="panel-heading red">
                        <div>提取方法</div>
                    </div>
                    <div class="row extractMethod">
                        <c:forEach items="${extractTestMethodList}" var="method" varStatus="em">
                            <div class="col-md-2">
                                <div>
                                    <div class="extractMethodTit"><span>${method.dictCode}</span>${method.dictName}
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <div class="row Modular ">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading yellow">
                        <div>提取记录表</div>
                        <span class="tools pull-right">
                            <span>提取人</span>
                            <input type="text" class="form-control" id="other_extract_extractPerson1"
                                   value="${other_extract_extractPerson1}">
                        </span>
                        <span class="tools pull-right">
                            <span>结束时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="other_extract_extractionExperimentEndDate"
                                   value="${other_extract_extractionExperimentDate}">
                        </span>
                        <span class="tools pull-right">
                            <span>提取时间</span>
                            <input type="text" class="form-control form_datetime"
                                   id="other_extract_extractionExperimentDate"
                                   value="${other_extract_extractionExperimentDate}">
                            <span>至</span>
                        </span>
                    </div>
                    <div class="panel-body">
                        <table class="table table-hover table-bordered bigTable">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>检材编号</th>
                                <th>检材名称</th>
                                <th>操作方法</th>
                                <th>描述</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="other_extract">
                            <input type="hidden" name="other_extract_extractId" id="other_extract_extractId"
                                   value="${other_extract_extractId}">
                            <input type="hidden" name="operateType" id="other_extract_operateType"
                                   value="${operateType}">
                            <c:forEach items="${labExtractSampleListOther}" var="other" varStatus="s">
                                <tr>
                                    <td>${s.index+1}</td>
                                    <td><input type="text" class="form-control" readonly name="sampleNo"
                                               value="${other.sampleNo}"></td>
                                    <td><input type="text" class="form-control" readonly name="sampleName"
                                               value="${other.sampleName}"></td>
                                    <td>
                                        <div class="select">
                                            <select class="form-control small" name="extractMethod">
                                                <c:forEach items="${extractTestMethodList}" var="list">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${other.extractMethod eq list.dictCode}">selected</c:if>>${list.dictCode}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </td>
                                    <td><input type="text" class="form-control" name="extractString"
                                               value="${other.extractString}"></td>
                                    <td>
                                        <button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red">删除
                                        </button>
                                        <input type="hidden" name="other_extract_id" value="${other.id}"/>
                                        <input type="hidden" name="other_extract_sampleId" value="${other.sampleId}"/>
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
<div class="row btn-box">
    <div class="col-md-12" style="text-align: center">
        <input type="hidden" name="taskId" id="taskId" value="${taskId}">
        <button class="btn btn-blue btn-lang" type="button" value="1" id="saveBtn">暂存</button>
        <button class="btn btn-green btn-lang" type="button" id="recordTable">生成记录表</button>
        <button class="btn btn-yellow btn-lang" type="button" id="saveNextBtn">完成</button>
    </div>
</div>
<div class="modal fade" id="kitPopBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">试剂名称</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <ul class="btn-checkbox clearfix addUl">
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
<div class="modal fade" id="kitPopBoxTwe" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabelTwe">试剂名称</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <ul class="btn-checkbox clearfix">
                        <c:forEach items="${reagentInfoList}" var="panlist">
                            <c:set var="flagKit" value="true"></c:set>
                            <c:if test="${panelKitTwe == null}">
                                <li class="pull-left checkedKitTwe" panelid="${panlist.id}"
                                    batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                    termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">${panlist.reagentName}</li>
                                <c:set var="flagKit" value="false"></c:set>
                            </c:if>
                            <c:forEach items="${panelKitTwe}" var="list">
                                <c:if test="${panlist.id eq list}">
                                    <li class="pull-left checkedKitTwe active" panelid="${panlist.id}"
                                        batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                        termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">${panlist.reagentName}</li>
                                    <c:set var="flagKit" value="false"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:if test="${flagKit}">
                                <li class="pull-left checkedKitTwe" panelid="${panlist.id}"
                                    batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                    termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>}">${panlist.reagentName}</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-blue btn-lang" onclick="addKitTwe()" data-dismiss="modal">确认</button>
                <button class="btn btn-blue-border btn-lang" data-dismiss="modal">取消</button>
            </div>
        </div>
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

        //生成记录表
        $("#recordTable").on("click", function () {
            var btnval = $("#saveBtn").val();
            if (!confirm("是否生成记录表！")) {
                return false;
            }
            var routine_extract_extractId = $("#routine_extract_extractId").val();
            var spot_extract_extractId = $("#spot_extract_extractId").val();
            var special_extract_extractId = $("#special_extract_extractId").val();
            var other_extract_extractId = $("#other_extract_extractId").val();
            if(btnval == 1){
                if(routine_extract_extractId == "" || routine_extract_extractId == null){
                    alert("请先保存后再生成记录表")
                    return false;
                }
                location.href = "<%=path%>/center/recordTableSD?routineExtractExtractId=" + routine_extract_extractId + "&btnval=" + btnval;
            }else if(btnval == 2){
                if(spot_extract_extractId == "" || spot_extract_extractId == null){
                    alert("请先保存后再生成记录表")
                    return false;
                }
                location.href = "<%=path%>/center/recordTableSD?routineExtractExtractId=" + spot_extract_extractId + "&btnval=" + btnval;
            }else if(btnval == 3){
                if(special_extract_extractId == "" || special_extract_extractId == null){
                    alert("请先保存后再生成记录表")
                    return false;
                }
                location.href = "<%=path%>/center/recordTableSD?routineExtractExtractId=" + special_extract_extractId + "&btnval=" + btnval;
            }else if(btnval == 4){
                if(other_extract_extractId == "" || other_extract_extractId == null){
                    alert("请先保存后再生成记录表")
                    return false;
                }
                location.href = "<%=path%>/center/recordTableSD?routineExtractExtractId=" + other_extract_extractId + "&btnval=" + btnval;
            }
        })

        $(".conventionalRoutine").on("click", function () {
            $("#saveBtn").val(1);
            $("#extractionExperimentDate").text($("#routine_extract_extractionExperimentDate").val())
        })
        $(".fineSpotSpot").on("click", function () {
            $("#saveBtn").val(2);
            $("#extractionExperimentDate").text($("#spot_extract_extractionExperimentDate").val());
        })
        $(".specialSpecial").on("click", function () {
            $("#saveBtn").val(3);
            $("#extractionExperimentDate").text($("#special_extract_extractionExperimentDate").val());
        })
        $(".otherOther").on("click", function () {
            $("#saveBtn").val(4);
            $("#extractionExperimentDate").text($("#other_extract_extractionExperimentDate").val());
        })

        $("input[name='isTwe']", $("tr", "#spot_extract")).on("click", function () {

            if ($(this).is(":checked") && $(".isSpot_extractTwe").length != 0) {
                for (var j = 0; j < $(".isSpot_extractTwe").length; j++) {
                    if ($(this).val() == $("input[name='sampleNo']", $(".isSpot_extractTwe").get(j)).val()) {
                        $(".isSpot_extractTwe").get(j).style.display = "";
                    } else if ($(this).is(":checked")) {
                        htmlsave = "<tr>\n" +
                                "                        <td><input type=\"text\" class=\"form-control\" readonly name=\"sampleNo\" value=\"" + $(this).val() + "\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"sampleName\" readonly value=\"" + $(this).attr("sampleName") + "\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"TES\" value=\"\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"SDS\" value=\"\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"chelex\" value=\"\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"PK\" value=\"\"></td>\n" +
                                "                            <td><input type=\"text\" class=\"form-control\" name=\"IMDTT\" value=\"\"></td>\n" +
                                "                            <td>\n" +
                                "                            <div class=\"select\">\n" +
                                "                            <select class=\"form-control\" name=\"purification\">\n"
                        var purifications = $(".purifications");
                        var purificationsCode = new Array();
                        var purificationsName = new Array();
                        for (var i = 0; i < purifications.length; i++) {
                            purificationsCode.push($(purifications.get(i)).val());
                            purificationsName.push($(purifications.get(i)).attr("dictName"));
                        }


                        for (i in purificationsCode) {
                            if (purificationsCode[i] == $(this).attr("sampleType")) {
                                htmlsave += '<option selected value="' + purificationsCode[i] + '">' + purificationsName[i] + '</option>'
                            } else {
                                htmlsave += '<option value="' + purificationsCode[i] + '">' + purificationsName[i] + '</option>'
                            }
                        }

                        htmlsave += "</select>\n" +
                                "                        </div>\n" +
                                "                        </td>\n" +
                                "                        </tr>"
                        $("#spot_extractTwe").append(htmlsave);

                    }
                }
            } else if (!$(this).is(":checked") && $(".isSpot_extractTwe").length != 0) {
                for (var j = 0; j < $(".isSpot_extractTwe").length; j++) {
                    if ($(this).val() == $("input[name='sampleNo']", $(".isSpot_extractTwe").get(j)).val()) {
                        $(".isSpot_extractTwe").get(j).style.display = "none";
                    } else {
                        if (!$(this).is(":checked")) {
                            var $sampleTRTwe = $("tr", "#spot_extractTwe");
                            var sampleCntTwe = $sampleTRTwe.length;
                            var $sampleTR = $("tr", "#spot_extract");
                            for (var i = 0; i < sampleCntTwe; i++) {
                                if ($(this).val() == $("input[name='sampleNo']", $sampleTRTwe.get(i)).val()) {
                                    $sampleTRTwe.get(i).remove();
                                }
                            }
                        }
                    }
                }
            } else if ($(this).is(":checked")) {

                htmlsave = "<tr>\n" +
                        "                        <td><input type=\"text\" class=\"form-control\" readonly name=\"sampleNo\" value=\"" + $(this).val() + "\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"sampleName\" readonly value=\"" + $(this).attr("sampleName") + "\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"TES\" value=\"\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"SDS\" value=\"\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"chelex\" value=\"\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"PK\" value=\"\"></td>\n" +
                        "                            <td><input type=\"text\" class=\"form-control\" name=\"IMDTT\" value=\"\"></td>\n" +
                        "                            <td>\n" +
                        "                            <div class=\"select\">\n" +
                        "                            <select class=\"form-control\" name=\"purification\">\n"
                var purifications = $(".purifications");
                var purificationsCode = new Array();
                var purificationsName = new Array();
                for (var i = 0; i < purifications.length; i++) {
                    purificationsCode.push($(purifications.get(i)).val());
                    purificationsName.push($(purifications.get(i)).attr("dictName"));
                }


                for (i in purificationsCode) {
                    if (purificationsCode[i] == $(this).attr("sampleType")) {
                        htmlsave += '<option selected value="' + purificationsCode[i] + '">' + purificationsName[i] + '</option>'
                    } else {
                        htmlsave += '<option value="' + purificationsCode[i] + '">' + purificationsName[i] + '</option>'
                    }
                }

                htmlsave += "</select>\n" +
                        "                        </div>\n" +
                        "                        </td>\n" +
                        "                        </tr>"
                $("#spot_extractTwe").append(htmlsave);

            } else {
                if (!$(this).is(":checked")) {
                    var $sampleTRTwe = $("tr", "#spot_extractTwe");
                    var sampleCntTwe = $sampleTRTwe.length;
                    var $sampleTR = $("tr", "#spot_extract");
                    for (var i = 0; i < sampleCntTwe; i++) {
                        if ($(this).val() == $("input[name='sampleNo']", $sampleTRTwe.get(i)).val()) {
                            $sampleTRTwe.get(i).remove();
                        }
                    }
                }
            }
        })

        $(".btn-checkbox").on("click", 'li', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active")
            }
        })

        /** 删除提取检材实验记录 */
        $("button[name='deleteBtn']", "#routine_extract").on("click", function () {
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabExtractSample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if(data.length == undefined) {
                            that.remove()
                        }
                    }
                });
            }
        });

        var taskId = $("#taskId").val();
        /** 保存提取实验记录 */
        $("#saveBtn").on("click", function () {
            saveForm(0);
        });

        function saveForm(obj) {
            var btnval = $("#saveBtn").val();
            var urls;
            if (btnval == 1) {
                //试剂盒添加
                var kitact = $(".active");
                var kitchecked = $(".checkedKit");
                var kits = "";
                for (var i = 0; i < kitact.length; i++) {
                    for (var j = 0; j < kitchecked.length; j++) {
                        if (kitact.get(i) == kitchecked.get(j)) {
                            kits += kitact.get(i).getAttribute("panelid");
                            kits += ",";
                        }
                    }
                }
                //设备添加
                var act = $(".active");
                var equ = $(".equipment");
                var equipments = "";
                for (var i = 0; i < equ.length; i++) {
                    for (var j = 0; j < act.length; j++) {
                        if (equ.get(i) == act.get(j)) {
                            equipments += equ.get(i).getAttribute("equipment_no");
                            equipments += ",";
                        }
                    }
                }
                urls = "<%=path%>/center/saveManualExtractionExperiment?operateType=" + $("#routine_extract_operateType").val() + "&taskId=" + taskId + "&extractDatetimes=" + $("#routine_extract_extractionExperimentDate").val() + "&extractPerson1=" + $("#routine_extract_extractPerson1").val()
                        + "&equipment=" + equipments + "&kit=" + kits + "&extractEndDatetimes=" + $("#routine_extract_extractionExperimentEndDate").val() + "&extractStage=" + obj;
            } else if (btnval == 2) {
                //试剂盒添加
                var kitact = $(".active");
                var kitchecked = $(".checkedKitTwe");
                var kits = "";
                for (var i = 0; i < kitact.length; i++) {
                    for (var j = 0; j < kitchecked.length; j++) {
                        if (kitact.get(i) == kitchecked.get(j)) {
                            kits += kitact.get(i).getAttribute("panelid");
                            kits += ",";
                        }
                    }
                }
                //设备添加
                var act = $(".active");
                var equ = $(".equipmentTwe");
                var equipments = "";
                for (var i = 0; i < equ.length; i++) {
                    for (var j = 0; j < act.length; j++) {
                        if (equ.get(i) == act.get(j)) {
                            equipments += equ.get(i).getAttribute("equipment_no");
                            equipments += ",";
                        }
                    }
                }
                urls = "<%=path%>/center/saveManualExtractionExperiment?operateType=" + $("#spot_extract_operateType").val() + "&taskId=" + taskId + "&extractDatetimes=" + $("#spot_extract_extractionExperimentDate").val() + "&extractPerson1=" + $("#spot_extract_extractPerson1").val()
                        + "&equipment=" + equipments + "&kit=" + kits + "&extractEndDatetimes=" + $("#spot_extract_extractionExperimentEndDate").val() + "&extractStage=" + obj;
                ;
            } else if (btnval == 3) {
                urls = "<%=path%>/center/saveManualExtractionExperiment?operateType=" + $("#special_extract_operateType").val() + "&taskId=" + taskId + "&extractDatetimes=" + $("#special_extract_extractionExperimentDate").val() + "&extractPerson1="
                        + $("#special_extract_extractPerson1").val() + "&extractEndDatetimes=" + $("#special_extract_extractionExperimentEndDate").val() + "&extractStage=" + obj;
            } else if (btnval == 4) {
                urls = "<%=path%>/center/saveManualExtractionExperiment?operateType=" + $("#other_extract_operateType").val() + "&taskId=" + taskId + "&extractDatetimes=" + $("#other_extract_extractionExperimentDate").val() + "&extractPerson1="
                        + $("#other_extract_extractPerson1").val() + "&extractEndDatetimes=" + $("#other_extract_extractionExperimentEndDate").val() + "&extractStage=" + obj;
            }

            $.ajax({
                url: urls,
                type: "post",
                data: JSON.stringify(param(btnval)),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        taskId = data.taskId;
                        $("#taskId").val(taskId);
                        if (btnval == 1 && $("#routine_extract_operateType").val() != 2) {
                            $("#routine_extract_operateType").val("2")
                            $("#routine_extract_extractId").val(data.extractId);
                            var $sampleTR = $("tr", "#routine_extract");
                            var sampleCnt = $sampleTR.length;
                            for (var j = 0; j < data.idList.length; j++) {
                                for (var i = 0; i < sampleCnt; i++) {
                                    if ($("input[name='sampleNo']", $sampleTR.get(i)).val() == data.idList[j].sampleNo) {
                                        $("input[name='id']", $sampleTR.get(i)).val(data.idList[j].id)
                                    }
                                }
                            }
                        } else if (btnval == 2 && $("#spot_extract_operateType").val() != 2) {
                            $("#spot_extract_operateType").val("2")
                            $("#spot_extract_extractId").val(data.extractId);
                            var $sampleTR = $("tr", "#spot_extract");
                            var sampleCnt = $sampleTR.length;
                            for (var j = 0; j < data.idList.length; j++) {
                                for (var i = 0; i < sampleCnt; i++) {
                                    if ($("input[name='sampleNo']", $sampleTR.get(i)).val() == data.idList[j].sampleNo) {
                                        $("input[name='spot_extract_id']", $sampleTR.get(i)).val(data.idList[j].id)
                                    }
                                }
                            }
                        } else if (btnval == 3 && $("#special_extract_operateType").val() != 2) {
                            $("#special_extract_operateType").val("2")
                            $("#special_extract_extractId").val(data.extractId);
                            var $sampleTR = $("tr", "#special_extract");
                            var sampleCnt = $sampleTR.length;
                            for (var j = 0; j < data.idList.length; j++) {
                                for (var i = 0; i < sampleCnt; i++) {
                                    if ($("input[name='sampleNo']", $sampleTR.get(i)).val() == data.idList[j].sampleNo) {
                                        $("input[name='special_extract_id']", $sampleTR.get(i)).val(data.idList[j].id)
                                    }
                                }
                            }
                        } else if (btnval == 4 && $("#other_extract_operateType").val() != 2) {
                            $("#other_extract_operateType").val("2")
                            $("#other_extract_extractId").val(data.extractId);
                            var $sampleTR = $("tr", "#other_extract");
                            var sampleCnt = $sampleTR.length;
                            for (var j = 0; j < data.idList.length; j++) {
                                for (var i = 0; i < sampleCnt; i++) {
                                    if ($("input[name='sampleNo']", $sampleTR.get(i)).val() == data.idList[j].sampleNo) {
                                        $("input[name='other_extract_id']", $sampleTR.get(i)).val(data.idList[j].id)
                                    }
                                }
                            }
                        }
                        $("#recordModal").modal('show');
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
            <%--location.href = "<%=path%>/center/enterManualExperiment?taskId=" + $("#taskId").val() + "&taskStage=1";--%>
        });

        function param(btnval) {
            var sampleArr = new Array();
            if (btnval == 1) {
                var $sampleTR = $("tr", "#routine_extract");
                var sampleCnt = $sampleTR.length;
                var sample;
                for (var i = 0; i < sampleCnt; i++) {
                    sample = {};
                    // var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                    // if (checkBox) {
                    sample.id = $("input[name='id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    /* sample.transferMethod = $("select[name='transferMethod'] option:selected", $sampleTR.get(i)).val();
                     sample.extractPart = $("input[name='extractPart']", $sampleTR.get(i)).val();*/
                    sample.extractMethod = $("select[name='extractMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.leave = $("select[name='leave'] option:selected", $sampleTR.get(i)).val();
                    sample.bath = $("select[name='bath'] option:selected", $sampleTR.get(i)).val();
                    sample.dry = $("select[name='dry'] option:selected", $sampleTR.get(i)).val();
                    sample.shake = $("select[name='shake'] option:selected", $sampleTR.get(i)).val();
                    sample.earthquake = $("select[name='earthquake'] option:selected", $sampleTR.get(i)).val();
                    sample.sampleType = $("select[name='sampleType'] option:selected", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                    // }
                }
                return sampleArr;
            } else if (btnval == 2) {
                var $sampleTR = $("tr", "#spot_extract");
                var $sampleTRTwe = $("tr", "#spot_extractTwe");
                var sampleCnt = $sampleTR.length;
                var sampleCntTwe = $sampleTRTwe.length;
                var sample;
                for (var i = 0; i < sampleCnt; i++) {
                    sample = {};
                    var checkBox = $("input[name='isTwe']", $sampleTR.get(i)).is(":checked");
                    // if (checkBox) {
                    sample.id = $("input[name='spot_extract_id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='spot_extract_sampleId']", $sampleTR.get(i)).val();
                    /*sample.transferMethod = $("select[name='transferMethod'] option:selected", $sampleTR.get(i)).val();
                     sample.extractPart = $("input[name='extractPart']", $sampleTR.get(i)).val();*/
                    sample.extractMethod = $("select[name='extractMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.samplePostion = $("input[name='samplePostion']", $sampleTR.get(i)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.sampleType = $("select[name='sampleType'] option:selected", $sampleTR.get(i)).val();
                    sample.leave = $("select[name='leave'] option:selected", $sampleTR.get(i)).val();
                    var extractStringS = $("input[name='TES']", $sampleTR.get(i)).val() + "}," + $("input[name='SDS']", $sampleTR.get(i)).val() + "}," +
                            $("input[name='PK']", $sampleTR.get(i)).val() + "}," + $("input[name='isOK']", $sampleTR.get(i)).is(":checked") + "}," +
                            $("input[name='isTwe']", $sampleTR.get(i)).is(":checked") + "},";
                    if (checkBox) {
                        for (var j = 0; j < sampleCntTwe; j++) {
                            if ($("input[name='sampleNo']", $sampleTR.get(i)).val() == $("input[name='sampleNo']", $sampleTRTwe.get(j)).val()) {
                                extractStringS += $("input[name='TES']", $sampleTRTwe.get(j)).val() + "}," + $("input[name='SDS']", $sampleTRTwe.get(j)).val() + "}," +
                                        $("input[name='chelex']", $sampleTRTwe.get(j)).val() + "}," + $("input[name='PK']", $sampleTRTwe.get(j)).val() + "}," +
                                        $("input[name='IMDTT']", $sampleTRTwe.get(j)).val() + "}," + $("select[name='purification'] option:selected", $sampleTRTwe.get(j)).val();
                            }
                        }
                    }
                    sample.extractString = extractStringS;
                    sampleArr.push(sample);
                    // }
                }
                return sampleArr;
            } else if (btnval == 3) {
                var $sampleTR = $("tr", "#special_extract");
                var sampleCnt = $sampleTR.length;
                var sample;
                for (var i = 0; i < sampleCnt; i++) {
                    sample = {};
                    // var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                    // if (checkBox) {
                    sample.id = $("input[name='special_extract_id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='special_extract_sampleId']", $sampleTR.get(i)).val();
                    sample.extractMethod = $("select[name='extractMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                    // }
                }
                return sampleArr;
            } else if (btnval == 4) {
                var $sampleTR = $("tr", "#other_extract");
                var sampleCnt = $sampleTR.length;
                var sample;
                for (var i = 0; i < sampleCnt; i++) {
                    sample = {};
                    // var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                    // if (checkBox) {
                    sample.id = $("input[name='other_extract_id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='other_extract_sampleId']", $sampleTR.get(i)).val();
                    sample.extractMethod = $("select[name='extractMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.extractString = $("input[name='extractString']", $sampleTR.get(i)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                    // }
                }
                return sampleArr;
            }
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

        /**向下填充转移方法 */
        $("a[name='transferMethodBtn']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var transferMethod = $("select[name='transferMethod']", $curTR).val();
            if (transferMethod != "") {
                evaluateChangeMethod($(this), transferMethod);
            }
        });

        function evaluateChangeMethod(obj, transferMethod) {
            var totalRows = $("tr", "#samplelist_tbody").length;
            var curIdx = $(obj).parents("tr").index();

            var startIdx = curIdx + 1;
            for (var i = startIdx; i < totalRows; i++) {
                $("select[name='transferMethod']", "tbody tr:eq(" + i + ")").val(transferMethod);
            }
        }

        /**向下填充提取实验方法 */
        $("a[name='extractMethodBtn']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var extractMethod = $("select[name='extractMethod']", $curTR).val();
            if (extractMethod != "") {
                evaluateChangeExtractMethod($(this), extractMethod);
            }
        });

        function evaluateChangeExtractMethod(obj, extractMethod) {
            var totalRows = $("tr", "#samplelist_tbody").length;
            var curIdx = $(obj).parents("tr").index();

            var startIdx = curIdx + 1;
            for (var i = startIdx; i < totalRows; i++) {
                $("select[name='extractMethod']", "tbody tr:eq(" + i + ")").val(extractMethod);
            }
        }

        $("#saveNextBtn").on("click", function () {
            saveForm(1);
        });

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            language: 'zh',
            weekStart: 1,
            todayBtn: 1,
            //minView: "month",
            startView: 2,
            autoclose: true,
            todayHighlight: true,
            forceParse: 0,
            showMeridian: 0,
            minView:0,  //0表示可以选择小时、分钟   1只可以选择小时
            minuteStep:10 //分钟间隔1分钟
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
    })
    function addKit() {
        var kit = $(".checkedKit");
        var act = $(".active");
        var reagentList = [];
        var list = [];
        var listOf = [];
        for (var i = 0; i < kit.length; i++) {
            for (var j = 0; j < act.length; j++) {
                if (kit.get(i) == act.get(j)) {
                    reagentList.push(kit.get(i).getAttribute("reagentName"));
                    list.push(kit.get(i).getAttribute("batchNumber"));
                    listOf.push(kit.get(i).getAttribute("termOfValidity"));
                }
            }
        }
        $("#PkKit").empty()
        for (var i = 0; i < list.length; i++) {
            var pkKit = '<div class="col-md-12">';
            pkKit += '<div class="col-md-2"><div class="btnCheck">' + reagentList[i] + '</div></div>'
            pkKit += '<div class="col-md-4">'
            pkKit += '<div class="form-group row">'
            pkKit += '<label class="col-md-3 control-label noPadding">批号:</label>'
            pkKit += '<div class="col-md-9 noPadding">'
            pkKit += '<input type="text" class="form-control" readonly="" value="' + list[i] + '">'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '<div class="col-md-4">'
            pkKit += '<div class="form-group row">'
            pkKit += '<label class="col-md-4 control-label noPadding">有效期:</label>'
            pkKit += '<div class="col-md-8 noPadding">'
            pkKit += '<input type="text" class="form-control" readonly="" value="' + listOf[i] + '">'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '<div class="col-md-1"><i class="fa fa-times-circle removeCherk" aria-hidden="true"></i></div>'
            pkKit += '</div>'
            $("#PkKit").append(pkKit);
        }
        $(".addUl").find("li.active").removeClass("active");
    }
    $("#myTabContent").on("click", ".removeCherk", function () {
        $("#kitPopBox").find(".active:contains('" + $(this).parents(".col-md-12").eq(0).find(".btnCheck").html() + "')").removeClass("active")
        $(this).parents(".col-md-12").eq(0).remove()
    })
    function addKitTwe() {
        var kit = $(".checkedKitTwe");
        var act = $(".active");
        var reagentList = [];
        var list = [];
        var listOf = [];
        for (var i = 0; i < kit.length; i++) {
            for (var j = 0; j < act.length; j++) {
                if (kit.get(i) == act.get(j)) {
                    reagentList.push(kit.get(i).getAttribute("reagentName"));
                    list.push(kit.get(i).getAttribute("batchNumber"));
                    listOf.push(kit.get(i).getAttribute("termOfValidity"));
                }
            }
        }
        $("#PkKitTwe").empty()
        for (var i = 0; i < list.length; i++) {
            var pkKit = '<div class="col-md-12">';
            pkKit += '<div class="col-md-2"><div class="btnCheck">' + reagentList[i] + '</div></div>'
            pkKit += '<div class="col-md-4">'
            pkKit += '<div class="form-group row">'
            pkKit += '<label class="col-md-3 control-label noPadding">批号:</label>'
            pkKit += '<div class="col-md-9 noPadding">'
            pkKit += '<input type="text" class="form-control" readonly="" value="' + list[i] + '">'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '<div class="col-md-4">'
            pkKit += '<div class="form-group row">'
            pkKit += '<label class="col-md-4 control-label noPadding">有效期:</label>'
            pkKit += '<div class="col-md-8 noPadding">'
            pkKit += '<input type="text" class="form-control" readonly="" value="' + listOf[i] + '">'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '</div>'
            pkKit += '<div class="col-md-1"><i class="fa fa-times-circle removeCherk" aria-hidden="true"></i></div>'
            pkKit += '</div>'
            $("#PkKitTwe").append(pkKit);
        }
    }
</script>
</body>

</html>