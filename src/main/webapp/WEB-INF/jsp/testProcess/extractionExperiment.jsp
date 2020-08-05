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
        .btn-red:hover {
            width: 100%;
        }

        #videoBox a {
            padding-top: 10px;
            padding-bottom: 10px
        }

        #videoBox button {
            padding: 10px 26px;
        }

        .bu {
            background: #fddddb;
            color: #fc5a56;
            padding: 5px;
            border-radius: 50%;
            font-size: 10px;
            font-weight: 600;
        }

        .extractMethod {
            margin:0 15px !important;
            height: auto;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
        }

        .extractMethod > div {
            width: auto;
            /*min-width: 50%;*/
            /*width: 20%;*/
            /*height: 305px;*/
            /*margin: 10px 0;*/
        }
        .extractMethod .col-md-2{
            height: auto;
            min-height: 0;
        }

        .extractMethod > div > div {
            /*height: 100%;*/
            /*border: 1px solid #f6f6f6;*/
            border-radius: 5px;
            /*background: #fafafa;*/
        }

        .extractMethodTit {
            height: 40px;
            text-align: left;
            line-height: 40px;
            padding-bottom: 0px;
            background: #f8f8f8;
            color: #5c5c5c;
            font-size: 16px;
            margin: 10px 0;
            padding-right: 10px;
        }

        .extractMethodTit span {
            height: 100%;
            width: 30px;
            display: inline-block;
            background: #fff0dd;
            font-weight: bold;
            color: #ff9200;
            float: left;
            text-align: center;
            margin-right: 10px;
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
            /*color: #296fff;*/
            display: inline-block;
            margin-right: 10px;
        }

        .tools + .tools {
            margin-left: 20px;
        }

        .tools + .tools span {
            color: #000;
        }

        .tools input {
            width: 200px;
            display: inline-block;
            color: #296fff;
        }

        .btn-box button + button {
            margin-left: 10px;
        }

        .kitBox > div > div,
        .deviceBox > div > div {
            height: auto;
            border: 1px dashed #dbdbdb;
            overflow-y: auto;
            /*margin-bottom: 20px;*/
        }

        .kitBox > div > div .col-md-12 {
            margin: 15px 0;
        }

        .kitBox > div > div .col-md-12 > div {
            padding-right: 0px;
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

        .btn-checkbox > li {
            margin-left: 40px;
        }

        .col-md-3.control-label,
        .col-md-4.control-label {
            height: 34px;
            line-height: 34px;
        }

        .noPadding {
            padding: 0px !important;
        }

        .deviceBox > div {
            padding-left: 0px;
        }

        .deviceBox > div > div ul {
            text-align: center;
            padding-top: 15px;
            /*padding-left: 60px;*/
        }

        #kitPopBox .modal-content {
            border-radius: 0px;
            border: none;
        }

        #kitPopBox .modal-body {
            max-height: 300px;
            overflow-y: auto;
        }

        #kitPopBox .modal-title {
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

        #kitPopBox .btn-checkbox > li {
            margin-left: 15px;
        }

        #kitPopBox .btn-checkbox > li + li {
            margin-left: 15px;
        }

        #kitPopBox .modal-footer {
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

        .removeCherk {
            line-height: 34px !important;
            font-size: 25px !important;
            color: #ff5a56;
            cursor: pointer;
        }
        table .select{
            width:75%!important;
            display: inline-block;
        }
        table a{
            float: right;
        }
        /*.extractMethodTit{*/
            /*margin-left: -200px;*/
        /*}*/
    </style>
</head>
<body>
<%--<jsp:include page="testProgressBar.jsp"/>--%>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading red" style="border: none">
                <div>提取方法</div>
            </div>
            <div class="row extractMethod">
                <c:forEach items="${extractTestMethodList}" var="method" varStatus="em">
                    <div class="col-md-2">
                        <div>
                            <c:if test="${method.dictName == '博坤工作站(磁珠法)'}">
                                <div class="extractMethodTit"><span>${method.dictCode}</span>${method.dictName}</div>
                            </c:if>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue noBorder">
                <div>试剂名称</div>
                <%--<button class="btn btn-blue-border" data-toggle="modal" data-target="#kitPopBox" onclick="addEcho()">添加</button>--%>
            </div>
            <div class="row kitBox">
                <div class="col-md-12">
                    <div class="row" id="PkKit">
                        <%--<c:forEach items="${selectList}" var="selectPanel">--%>
                            <div class="col-md-12">
                                <div class="col-md-3">
                                        <div class="select">
                                            <p class="panelid" style="display: none"></p>
                                            <select class="selchang form-control">
                                                <option value="">请选择试剂</option>
                                                <c:forEach items="${reagentInfoList}" var="panlist">
                                                    <%--<option value="${panlist.reagentName}">${panlist.reagentName}</option>--%>
                                                    <c:set var="flagKit" value="true"></c:set>
                                                    <c:if test="${panelKit == null}">
                                                        <option class="pull-left checkedKit" panelid="${panlist.id}"
                                                                batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                                                termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>" value="${panlist.reagentName}">${panlist.reagentName}</option>
                                                        <c:set var="flagKit" value="false"></c:set>
                                                    </c:if>
                                                    <c:forEach items="${panelKit}" var="list">
                                                        <c:if test="${panlist.id eq list}">
                                                            <option class="pull-left checkedKit active" panelid="${panlist.id}"
                                                                    batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                                                    termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>" value="${panlist.reagentName}" selected>${panlist.reagentName}</option>
                                                            <c:set var="flagKit" value="false"></c:set>
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${flagKit}">
                                                        <option class="pull-left checkedKit" panelid="${panlist.id}"
                                                                batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"
                                                                termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>" value="${panlist.reagentName}">${panlist.reagentName}</option>
                                                    </c:if>
                                                </c:forEach>

                                            </select>

                                        </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label noPadding" style="text-align: right">批号:</label>
                                        <div class="col-md-8 noPadding" style="margin-left: 10px;">
                                            <c:if test="${panelKit == null}">
                                                <input type="text" class="form-control ph" value="${panlist.batchNumber}">
                                            </c:if>
                                            <c:forEach items="${reagentInfoList}" var="panlist">
                                                <c:forEach items="${panelKit}" var="list">
                                                    <c:if test="${panlist.id eq list}">
                                                        <%--<option class="pull-left checkedKit active" panelid="${panlist.id}"--%>
                                                                <%--batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"--%>
                                                                <%--termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>" value="${panlist.reagentName}" selected>${panlist.reagentName}</option>--%>
                                                        <input type="text" class="form-control ph" value="${panlist.batchNumber}">
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>



                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-4">
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label noPadding" style="text-align: right">有效期:</label>
                                        <div class="col-md-8 noPadding" style="margin-left: 10px;">
                                            <c:if test="${panelKit == null}">
                                                <input type="text" class="form-control yxdata" value="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">
                                            </c:if>
                                            <c:forEach items="${reagentInfoList}" var="panlist">

                                                <c:forEach items="${panelKit}" var="list">
                                                    <c:if test="${panlist.id eq list}">
                                                        <%--<option class="pull-left checkedKit active" panelid="${panlist.id}"--%>
                                                        <%--batchNumber="${panlist.batchNumber}" reagentName="${panlist.reagentName}"--%>
                                                        <%--termOfValidity="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>" value="${panlist.reagentName}" selected>${panlist.reagentName}</option>--%>
                                                        <input type="text" class="form-control yxdata" value="<fmt:formatDate value='${panlist.validityTime}' pattern='yyyy-MM-dd'/>">
                                                    </c:if>
                                                </c:forEach>
                                            </c:forEach>

                                        </div>
                                    </div>
                                </div>
                                <%--<div class="col-md-1">--%>
                                    <%--<i class="fa fa-times-circle removeCherk" aria-hidden="true"></i>--%>
                                <%--</div>--%>
                            </div>
                        <%--</c:forEach>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow noBorder">
                <div>选择设备</div>
            </div>
            <div class="row deviceBox">
                <div class="col-md-12">
                    <div class="form-group" style="margin-left: 15px">
                        <ul class="btn-checkbox clearfix">
                            <c:forEach items="${equipmentNameInfoList}" var="equipmentNameInfo">
                                <c:set var="flag" value="true"></c:set>
                                <c:if test="${equipment == null}">
                                    <li equipment_no="${equipmentNameInfo.id}"
                                        class="pull-left equipment active">${equipmentNameInfo.equipmentName}</li>
                                    <c:set var="flag" value="false"></c:set>
                                </c:if>
                                <c:forEach items="${equipment}" var="list">
                                    <c:if test="${equipmentNameInfo.id eq list}">
                                        <li equipment_no="${equipmentNameInfo.id}"
                                            class="pull-left equipment active">${equipmentNameInfo.equipmentName}</li>
                                        <c:set var="flag" value="false"></c:set>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${flag}">
                                    <li equipment_no="${equipmentNameInfo.id}"
                                        class="pull-left equipment active">${equipmentNameInfo.equipmentName}</li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular " style="padding-bottom: 65px">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>提取记录详情</div>
                  <span class="tools pull-right">
                    <span>操作人</span>
                    <input type="text" class="form-control" id="operateUser" name="operateUser" value="${operateUser}">
                 </span>
                <span class="tools pull-right">
                    <span>提取时间</span>
                    <input type="text" class="form-control form_datetime" id="extractionExperimentDates"
                           name="extractionExperimentDates" value="${extractionExperimentDate}">
                    <%--<span>至 结束时间</span>
                     <input type="text" class="form-control form_datetime" id="extractionDate"
                            name="extractionDate" value="${extractionDates}">--%>
                 </span>
                <span class="tools pull-right">
                    <span>提取板号</span>
                    <input type="text" class="form-control" name="boardNo" id="boardNo" value="${boardNo}" readonly>
                 </span>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th rowspan="2" style='width:100px'>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" checked type="checkbox" name="checkAll">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th rowspan="2" style='width:90px'>位置</th>
                        <th rowspan="2" style="width: 150px;">检材编号</th>
                        <th rowspan="2">检材名称</th>
                        <th rowspan="2" style='width:155px'>检材类型</th>
                        <%-- <th rowspan="2" style='width:8%'>转移方法</th>
                         <th rowspan="2" style='width:7%'>提取部位</th>--%>
                        <th rowspan="2" style='width:105px'>方法</th>
                        <th colspan="5" style='width:200px'>使用仪器</th>
                        <th rowspan="2" style="width:70px;">操作</th>
                    </tr>
                    <tr>
                        <th width="75px">离1</th>
                        <th width="75px">离2</th>
                        <th width="75px">浴1</th>
                        <th width="75px">浴2</th>
                        <th width="75px">干1</th>
                        <th width="75px">干2</th>
                        <%--<th width="75px">摇</th>--%>
                        <th width="75px">振</th>
                    </tr>
                    </thead>
                    <tbody id="samplelist_tbody">
                    <c:forEach items="${labExtractSampleList}" var="sample" varStatus="s">
                        <tr>
                            <td>
                                <label class="custom-control checkbox-inline">
                                    <input class="custom-control-input" type="checkbox" checked name="box"><span
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
                                <div class="select">
                                    <select class="form-control" disabled name="sampleType">
                                        <c:forEach items="${sampleTypeList}" var="list">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${sample.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                                <%--<td>
                                    <div class="select">
                                        <select class="form-control small" name="transferMethod">
                                            <c:forEach items="${extractMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${sample.transferMethod eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                        &lt;%&ndash;<a href="#" name="transferMethodBtn" title="向下填充">&ndash;%&gt;
                                        &lt;%&ndash;<img border="0" src="<%=path%>/image/field_Down.bmp"&ndash;%&gt;
                                        &lt;%&ndash;style="cursor:pointer;margin-left:3px;margin-top:5px;">&ndash;%&gt;
                                        &lt;%&ndash;</a>&ndash;%&gt;
                                </td>
                                <td>
                                    <input name="extractPart" type="text" class="form-control small"
                                           onmouseover="this.title=this.value" value="${sample.extractPart}">
                                </td>--%>
                            <td>
                                <div class="select">
                                    <select class="form-control small Eselect" name="extractMethod" disabled>
                                        <c:forEach items="${extractTestMethodList}" var="list">
                                            <c:choose>
                                                <c:when test="${empty sample.extractMethod}">
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${'A' eq list.dictCode}">selected</c:if>>${list.dictCode}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${list.dictCode}"
                                                            <c:if test="${sample.extractMethod eq list.dictCode}">selected</c:if>>${list.dictCode}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                                    <a href="#" name="extractMethodBtn" title="向下填充">
                                    <img border="0" src="<%=path%>/image/field_Down.bmp"
                                    style="cursor:pointer;margin-left:3px;margin-top:5px;">
                                    </a>
                            </td>
                            <td>
                                <%--<div class="select">--%>
                                    <%--<select class="form-control" id="leave" name="leave">--%>
                                        <%--<option value="0" selected>无</option>--%>
                                        <%--<c:forEach items="${leaveTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                                    <%--<c:if test="${sample.leave eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                    <div class="inputdiv">
                                        <c:if test="${ sample.leave== 1}">
                                            <input type="checkbox" id="leave" disabled name="leave" value="1" checked>
                                        </c:if>
                                        <c:if test="${ sample.leave== 0}">
                                            <input type="checkbox" id="leave" disabled name="leave" value="0">
                                        </c:if>

                                            <%--离1--%>
                                    </div>
                                <%--<a href="#" name="extractMethodBtnli" title="向下填充">--%>
                                    <%--<img border="0" src="<%=path%>/image/field_Down.bmp"--%>
                                         <%--style="cursor:pointer;margin-left:3px;margin-top:5px;">--%>
                                <%--</a>--%>
                            </td>
                            <td>
                                <%--<div class="select">--%>
                                    <%--<select class="form-control" id="bath" name="bath">--%>
                                        <%--<option value="0" selected>无</option>--%>
                                        <%--<c:forEach items="${bathTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                                    <%--<c:if test="${sample.bath eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<a href="#" name="extractMethodBtnyu" title="向下填充">--%>
                                    <%--<img border="0" src="<%=path%>/image/field_Down.bmp"--%>
                                         <%--style="cursor:pointer;margin-left:3px;margin-top:5px;">--%>
                                <%--</a>--%>
                                    <div class="inputdiv">
                                        <c:if test="${ sample.leaveTwo== 1}">
                                            <input type="checkbox" id="leaveTwo" disabled name="leaveTwo" value="1" checked>
                                        </c:if>
                                        <c:if test="${ sample.leaveTwo== 0}">
                                            <input type="checkbox" id="leaveTwo" disabled name="leaveTwo" value="0" >
                                        </c:if>
                                            <%--离2--%>
                                    </div>
                            </td>
                            <td>
                                <%--<div class="select">--%>
                                    <%--<select class="form-control" id="dry" name="dry">--%>
                                        <%--<option value="0" selected>无</option>--%>
                                        <%--<c:forEach items="${dryTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                                    <%--<c:if test="${sample.dry eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<a href="#" name="extractMethodBtngan" title="向下填充">--%>
                                    <%--<img border="0" src="<%=path%>/image/field_Down.bmp"--%>
                                         <%--style="cursor:pointer;margin-left:3px;margin-top:5px;">--%>
                                <%--</a>--%>
                                    <div class="inputdiv">
                                        <c:if test="${ sample.bath== 1}">
                                            <input type="checkbox" id="bath" disabled name="bath" value="1" checked>
                                        </c:if>
                                        <c:if test="${ sample.bath== 0}">
                                            <input type="checkbox" id="bath" disabled name="bath" value="0" >
                                        </c:if>

                                            <%--浴1--%>
                                    </div>
                            </td>
                            <td>
                                <%--<div class="select">--%>
                                    <%--<select class="form-control" id="shake" name="shake">--%>
                                        <%--<option value="0" selected>无</option>--%>
                                        <%--<c:forEach items="${shakeTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                                    <%--<c:if test="${sample.shake eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<a href="#" name="extractMethodBtnyao" title="向下填充">--%>
                                    <%--<img border="0" src="<%=path%>/image/field_Down.bmp"--%>
                                         <%--style="cursor:pointer;margin-left:3px;margin-top:5px;">--%>
                                <%--</a>--%>

                                    <div class="inputdiv">
                                        <c:if test="${ sample.bathTwo== 1}">
                                            <input type="checkbox" id="bathTwo" disabled name="bathTwo" value="1" checked>
                                        </c:if>
                                        <c:if test="${ sample.bathTwo== 0}">
                                            <input type="checkbox" id="bathTwo" disabled name="bathTwo" value="0" >
                                        </c:if>
                                            <%--离2--%>
                                    </div>
                            </td>
                            <td>
                                <%--<div class="select">--%>
                                    <%--<select class="form-control" id="earthquake" name="earthquake">--%>
                                        <%--<option value="0" selected>无</option>--%>
                                        <%--<c:forEach items="${earthquakeTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                                    <%--<c:if test="${sample.earthquake eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</select>--%>
                                <%--</div>--%>
                                <%--<a href="#" name="extractMethodBtnzhen" title="向下填充">--%>
                                    <%--<img border="0" src="<%=path%>/image/field_Down.bmp"--%>
                                         <%--style="cursor:pointer;margin-left:3px;margin-top:5px;">--%>
                                <%--</a>--%>
                                    <div class="inputdiv">
                                            <%--<select class="form-control" id="dry" name="dry">--%>
                                            <%--<option value="0" selected>无</option>--%>
                                            <%--<c:forEach items="${dryTestMethodList}" var="list">--%>
                                            <%--<option value="${list.dictCode}"--%>
                                            <%--<c:if test="${routine.dry eq list.dictCode}">selected</c:if>>${list.dictName}</option>--%>
                                            <%--</c:forEach>--%>
                                            <%--</select>--%>
                                        <c:if test="${ sample.dry== 1}">
                                            <input type="checkbox" id="dry" disabled name="dry" value="1" checked>
                                        </c:if>
                                        <c:if test="${ sample.dry== 0}">
                                            <input type="checkbox" id="dry" disabled name="dry" value="0" >
                                        </c:if>
                                            <%--干1--%>
                                    </div>
                            </td>
                            <td>

                                <div class="inputdiv">
                                    <c:if test="${ sample.bath== 1}">
                                        <input type="checkbox" id="bath" disabled name="bath" value="1" checked>
                                    </c:if>
                                    <c:if test="${ sample.bath== 0}">
                                        <input type="checkbox" id="bath" disabled name="bath" value="0" >
                                    </c:if>

                                        <%--干2--%>
                                </div>
                            </td>

                            <td>
                                <div class="inputdiv">
                                    <c:if test="${ sample.earthquake== 1}">
                                        <input type="checkbox" id="earthquake" disabled name="earthquake" value="1" checked>
                                    </c:if>
                                    <c:if test="${ sample.earthquake== 0}">
                                        <input type="checkbox" id="earthquake" disabled name="earthquake" value="0" >
                                    </c:if>
                                        <%--振--%>
                                </div>
                            </td>


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
    <div class="col-md-12" style="text-align: center">
        <input type="hidden" name="extractId" id="extractId" value="${extractId}">
        <input type="hidden" name="operateType" id="operateType" value="${operateType}">
        <button class="btn btn-blue btn-lang" type="button" id="saveBtn">保存</button>
        <button class="btn btn-green btn-lang" type="button" id="recordTable">生成记录表</button>
        <button class="btn btn-yellow btn-lang" type="button" id="saveNextBtn">完成并进入下一步</button>
        <%--<button class="btn btn-blue-border btn-lang" type="button">返回</button>--%>
    </div>
</div>
<%--<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>提取方法</div>
            </div>
            <div class="row extractMethod">
                <c:forEach items="${extractTestMethodList}" var="method" varStatus="em">
                    <div class="col-md-2">
                        <div>
                            <div class="extractMethodTit">${method.dictCode}、${method.dictName}</div>
                            <div class="extractMethodMain">
                                    ${method.dictDesc}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>--%>
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
<div class="modal fade" id="kitPopBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">试剂名称</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <ul class="btn-checkbox clearfix">
                        <select>
                            <c:forEach items="${reagentInfoList}" var="panlist">
                                <%--<option value="${panlist.reagentName}">${panlist.reagentName}</option>--%>
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

                        </select>

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
<%--<div class="row Modular">--%>
<%--<div class="col-md-12">--%>
<%--<div class="panel panel-default">--%>
<%--<div class="panel-body">--%>
<%--<div class="form-group seachButtonBox" style="height: 40px;">--%>
<%--<div style="float: right;height: 40px;">--%>
<%--<input type="hidden" name="extractId" id="extractId" value="${extractId}">--%>
<%--<input type="hidden" name="operateType" id="operateType" value="${operateType}">--%>
<%--<button class="btn btn-blue" type="button" id="saveBtn">保存</button>--%>
<%--<button class="btn btn-blue" type="button">生成记录表</button>--%>
<%--<button class="btn btn-yellow" type="button" id="saveNextBtn">完成并进入下一步</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $(".Eselect").change(function(){
            //alert($(this).val());
            $(this).parents("tr").find("td .inputdiv").each(function(){
                $(this).find("input").each(function(){
                    $(this).attr("checked",false);
                })
            })
            if($(this).val()=="A"){
                var a = [5,7,9,11];
                for(var i=0 ; i< a.length;i++){
                    var s = a[i];
                    $(this).parents("tr").find("td").eq(s).find(".inputdiv input").each(function(){
                        $(this).attr("checked",true);
                    })
                }
            }
            if($(this).val()=="B"){
                var a = [8,11];
                for(var i=0 ; i< a.length;i++){
                    var s = a[i];
                    $(this).parents("tr").find("td").eq(s).find(".inputdiv input").each(function(){
                        $(this).attr("checked",true);
                    })
                }
            }
            if($(this).val()=="C"){
                var a = [7,11];
                for(var i=0 ; i< a.length;i++){
                    var s = a[i];
                    $(this).parents("tr").find("td").eq(s).find(".inputdiv input").each(function(){
                        $(this).attr("checked",true);
                    })
                }
            }
            if($(this).val()=="D"){
                var a = [5,6,7,8,9,10,11];
                for(var i=0 ; i< a.length;i++){
                    var s = a[i];
                    $(this).parents("tr").find("td").eq(s).find(".inputdiv input").each(function(){
                        $(this).attr("checked",false);
                    })
                }
            }
//            if($(this).val()=="D"){
//                $(this).parents("tr").find("td").eq(9).find(".inputdiv input").each(function(){
//                    $(this).attr("checked",true);
//                })
//            }
        })


        $(".btn-checkbox").on("click", 'li', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active")
            }
        })
        $(".btn-checkbox").on("click", 'option', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active")
            }
        })
        /** 删除提取检材实验记录 */
        $("button[name='deleteBtn']", "#samplelist_tbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabExtractSample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if(data.length == undefined || data.length == 0) {
                            that.remove()
                        }
                        if (data.success || data.success == true || data.success == "true") {
                            location.href = "<%=path%>/center/refreshExtractionExperiment?extractId=" + data.extractId;
                        }
                    }
                });
            }
        });

        /** 保存提取实验记录 */
        $("#saveBtn").on("click", function () {
            saveForm();
        });

        function saveForm() {
            debugger;
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }
            /*var extractionDate = $("#extractionDate").val();
            if (extractionDate == null || extractionDate == "") {
                alert("请输入结束时间！");
                $("#extractionDate").focus();
                return false;
            }*/

            //试剂盒添加
            // var kitact = $(".active");
            // var kitchecked = $(".checkedKit");
            // for (var i = 0; i < kitact.length; i++) {
            //     for (var j = 0; j < kitchecked.length; j++) {
            //         if (kitact.get(i) == kitchecked.get(j)) {
            //             kits += kitact.get(i).getAttribute("panelid");
            //             kits += ",";
            //         }
            //     }
            // }
            var kits = $(".panelid").text()+",";

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

            $.ajax({
                url: "<%=path%>/center/saveExtractionExperiment?boardNo=" + boardNo + "&operateType=" + $("#operateType").val() + "&extractDatetimes=" + $("#extractionExperimentDates").val() + "&extractPerson1=" + $("#operateUser").val() + "&equipment=" + equipments + "&kit=" + kits + "&extractionDate=" + $("#extractionDate").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#extractId").val(data.extractId);
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
            location.href = "<%=path%>/center/refreshExtractionExperiment?extractId=" + $("#extractId").val();
            ;
        });

        function param() {
            var sampleArr = new Array();
            var $sampleTR = $("tr", "#samplelist_tbody");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    sample.id = $("input[name='id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sample.transferMethod = $("select[name='transferMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.extractPart = $("input[name='extractPart']", $sampleTR.get(i)).val();
                    sample.extractMethod = $("select[name='extractMethod'] option:selected", $sampleTR.get(i)).val();
                    sample.samplePostion = $("input[name='samplePostion']", $sampleTR.get(i)).val();

                    sample.leave = $("select[name='leave'] option:selected", $sampleTR.get(i)).val();
                    sample.bath = $("select[name='bath'] option:selected", $sampleTR.get(i)).val();
                    sample.dry = $("select[name='dry'] option:selected", $sampleTR.get(i)).val();
                    sample.shake = $("select[name='shake'] option:selected", $sampleTR.get(i)).val();
                    sample.earthquake = $("select[name='earthquake'] option:selected", $sampleTR.get(i)).val();

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
                $curTR.nextAll().find("select[name='extractMethod']").val(extractMethod)
                //evaluateChangeExtractMethod($(this), extractMethod);
            }
        });
        /**向下填充离 */
        $("a[name='extractMethodBtnli']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var leave = $("select[name='leave']", $curTR).val();
            if (leave != "") {
                $curTR.nextAll().find("select[name='leave']").val(leave)
            }
        });
        /**向下填充浴 */
        $("a[name='extractMethodBtnyu']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var bath = $("select[name='bath']", $curTR).val();
            if (bath != "") {
                $curTR.nextAll().find("select[name='bath']").val(bath)
            }
        });
        /**向下填充干 */
        $("a[name='extractMethodBtngan']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var dry = $("select[name='dry']", $curTR).val();
            if (dry != "") {
                $curTR.nextAll().find("select[name='dry']").val(dry)
            }
        });
        /**向下填充摇 */
        $("a[name='extractMethodBtnyao']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var shake = $("select[name='shake']", $curTR).val();
            if (shake != "") {
                $curTR.nextAll().find("select[name='shake']").val(shake)
            }
        });
        /**向下填充振 */
        $("a[name='extractMethodBtnzhen']", "#samplelist_tbody").on("click", function () {
            var $curTR = $(this).parents("tr");

            var earthquake = $("select[name='earthquake']", $curTR).val();
            if (earthquake != "") {
                $curTR.nextAll().find("select[name='earthquake']").val(earthquake)
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
            saveFormAndNext();
            <%--location.href = "<%=path%>/center/pcrExperiment?extractId=" + $("#extractId").val();--%>
        });


        function saveFormAndNext() {
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

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

            $.ajax({
                url: "<%=path%>/center/saveExtractionExperiment?boardNo=" + boardNo + "&operateType=" + $("#operateType").val() + "&extractDatetimes=" + $("#extractionExperimentDates").val() + "&extractPerson1=" + $("#operateUser").val() + "&equipment=" + equipments + "&kit=" + kits + "&extractionDate=" + $("#extractionDate").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#extractId").val(data.extractId);
                        $("#recordModal").modal('show');
                        location.href = "<%=path%>/center/pcrExperiment?extractId=" + $("#extractId").val();
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh',
            todayBtn: 1,
            minView: "month",
            autoclose: true,
            todayHighlight: true,
            forceParse: 0,
            showMeridian: 1
//            minView:0,  //0表示可以选择小时、分钟   1只可以选择小时
//            minuteStep:10 //分钟间隔1分钟
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


        //生成记录表
        $("#recordTable").on("click", function () {
            if (!confirm("是否生成记录表！")) {
                return false;
            }
            var extractId = $("#extractId").val();
            if (extractId == '' || extractId == null || extractId == undefined || extractId == "") {
                alert("先保存后再生成记录表");
                return false;
            }
            location.href = "<%=path%>/center/recordTable?extractId=" + extractId;
        })
    })
    $(".selchang").on("change",function () {
        var a = $(this).val();
        $(".selchang").find("option").each(function () {
            if($(this).val()==a){
                $(".ph").val($(this).attr("batchnumber"));
                $(".yxdata").val($(this).attr("termofvalidity"));
                $(".panelid").text($(this).attr("panelid"));
            }
        })
    })
    function addKit() {
        //alert(11);
        var kit = $(".checkedKit");
        var act = $(".active");
        var reagentList = [];
        var list = [];
        var listOf = [];
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
        $("#PkKit").empty()
        for (var i = 0; i < list.length; i++) {
            var pkKit = '<div class="col-md-12">';
            pkKit += '<div class="col-md-2"><div class="btnCheck">' + reagentList[i] + '</div><input class="tagsId" type="hidden" value="'+listIDs[i]+'"></div>'
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
    }
    $("#PkKit").on("click", ".removeCherk", function () {
        $("#kitPopBox").find(".active:contains('" + $(this).parents(".col-md-12").eq(0).find(".btnCheck").html() + "')").removeClass("active")
        $(this).parents(".col-md-12").eq(0).remove()
    })

    // 点击添加按钮显示已选数据
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