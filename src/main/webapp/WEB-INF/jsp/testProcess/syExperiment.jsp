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
        .panel-heading .btn {
            border-radius: 0px !important;
        }
        .kongYellow{
            background: #ffb802 !important;
            border-color: #ffb802 !important;
        }
        .kit-group>.form-control {
            width: 30%;
            display: inline-block;
        }
        .kit-group-min .form-control {
            width: 150px;
            display: inline-block;
        }
        .kit-group label:last-of-type {
            margin-left: 15px;
        }
    </style>
</head>
<body>
<%--<jsp:include page="testProgressBar.jsp"/>--%>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>电泳记录详情</div>
            </div>
            <div class="panel-body">
                <div class="col-md-3 seachInputBox">
                    <div class="form-group">
                        <label>电泳板号</label>
                        <input type="text" class="form-control" name="boardNo" id="boardNo" value="${boardNo}"
                               readonly>
                    </div>
                </div>
                <div class="col-md-3 seachInputBox">
                    <div class="form-group">
                        <label>电泳仪</label>
                        <div class="select">
                            <select name="elecInstrument" value="${labSyInfo.elecInstrument}" class="form-control">
                                <option value="">请选择电泳仪</option>
                                <c:forEach items="${panelListDYY}" var="system" varStatus="s">
                                    <option value="${system.equipmentName}" <c:if test="${system.equipmentName eq labSyInfo.elecInstrument}">selected</c:if>>${system.equipmentName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>操作人</label>
                            <input type="text" class="form-control" placeholder="请输入操作人" name="syPerson1" id="syPerson1"
                                   value="${operateUser}">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>电泳时间</label>
                            <input type="text" class="form-control" placeholder="请输入电泳时间" id="syEndDatetime"
                                   value="${syExperimentDate}">
                        </div>
                    </div>
                    <%--<div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>导入上样本表</label>
                            <div class="row">
                                <div class="col-md-9 nopadding">
                                    <input type="text" class="form-control" placeholder="请导入上样本表">
                                </div>
                                <div class="col-md-3">
                                    <button class="btn btn-blue">导入</button>
                                </div>
                            </div>
                        </div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%--增加试剂盒--%>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>选择试剂盒</div>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-12 seachInputBox">
                        <div class="form-group">
                            <label>试剂盒:</label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="Identifiler_Direct" <c:if test="${mapSyString.kitRadio eq 'Identifiler_Direct'}">checked</c:if>
                                       name="kitRadio">Identifiler_Direct
                            </label>
                            <label class="radio-inline" >
                                <input type="radio" class="" value="Identifiler_plus" <c:if test="${mapSyString.kitRadio eq 'Identifiler_plus'}">checked</c:if>
                                       name="kitRadio">Identifiler_plus
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="" value="PP21" <c:if test="${mapSyString.kitRadio eq 'PP21'}">checked</c:if>
                                       name="kitRadio">PP21
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="" value="Yfiler_plus" <c:if test="${mapSyString.kitRadio eq 'Yfiler_plus'}">checked</c:if>
                                       name="kitRadio">Yfiler_plus
                            </label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="DNATyper15" <c:if test="${mapSyString.kitRadio eq 'DNATyper15'}">checked</c:if>
                                       name="kitRadio">DNATyper15
                            </label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="Sinofiler" <c:if test="${mapSyString.kitRadio eq 'Sinofiler'}">checked</c:if>
                                       name="kitRadio">Sinofiler
                            </label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="GoldenEye" <c:if test="${mapSyString.kitRadio eq 'GoldenEye'}">checked</c:if>
                                       name="kitRadio">GoldenEye
                            </label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="Minifiler" <c:if test="${mapSyString.kitRadio eq 'Minifiler'}">checked</c:if>
                                       name="kitRadio">Minifiler
                            </label>
                            <label class="radio-inline" style="display:none">
                                <input type="radio" class="" value="Globalfiler" <c:if test="${mapSyString.kitRadio eq 'Globalfiler'}">checked</c:if>
                                       name="kitRadio">Globalfiler
                            </label>
                        </div>
                    </div>
                    <div class="col-md-6 seachInputBox">
                        <div class="form-group kit-group">
                            <label>甲酰胺:</label>
                            <label>批号</label>
                            <c:if test="${not empty mapSyString.jiaXianAnBatchCode}">
                                <input type="text" class="form-control" name="jiaXianAnBatchCode" value="${mapSyString.jiaXianAnBatchCode}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if>>
                            </c:if>
                            <c:if test="${empty mapSyString.jiaXianAnBatchCode}">
                                <input type="text" class="form-control" name="jiaXianAnBatchCode" value="1904623" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <label>有效期</label>
                            <c:if test="${not empty mapSyString.jiaXianAnInstockDate}">
                                <input type="text" class="form-control form_datetime" name="jiaXianAnInstockDate" value="${mapSyString.jiaXianAnInstockDate}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <c:if test="${empty mapSyString.jiaXianAnInstockDate}">
                                <input type="text" class="form-control form_datetime" name="jiaXianAnInstockDate" value="2021-04-02" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-6 seachInputBox">
                        <div class="form-group kit-group">
                            <label>
                                <div class="dropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        内标 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><label><input type="radio" value="LIZ500" class="radBiao" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'LIZ500' || empty mapSyString.neiBiao}">checked</c:if>>LIZ500</label></li>
                                        <li><label><input type="radio" value="ILS600" class="radBiao" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'ILS600'}">checked</c:if>>ILS600</label></li>
                                        <li><label><input type="radio" value="LIZ600" class="radBiao" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'LIZ600'}">checked</c:if>>LIZ600</label></li>
                                        <li><label><input type="radio" value="ILS500" class="radBiao" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'ILS500'}">checked</c:if>>ILS500</label></li>
                                    </ul>
                                </div>
                            </label>
                            <label>批号</label>
                            <c:if test="${not empty mapSyString.neiBiaoBatchCode}">
                                <input type="text" class="form-control" name="neiBiaoBatchCode" value="${mapSyString.neiBiaoBatchCode}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>

                            <c:if test="${empty mapSyString.neiBiaoBatchCode}">
                                <input type="text" class="form-control" name="neiBiaoBatchCode" value="1901012" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>

                            <label>有效期</label>
                            <c:if test="${not empty mapSyString.neiBiaoInstockDate}">
                                <input type="text" class="form-control form_datetime" name="neiBiaoInstockDate" value="${mapSyString.neiBiaoInstockDate}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>

                            <c:if test="${empty mapSyString.neiBiaoBatchCode}">
                                <input type="text" class="form-control form_datetime" name="neiBiaoInstockDate" value="2020-04-01" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-6 seachInputBox">
                        <div class="form-group kit-group">
                            <label>毛细管:</label>
                            <label>批号</label>
                            <c:if test="${not empty mapSyString.maoXiGuanBatchCode}">
                                <input type="text" class="form-control" name="maoXiGuanBatchCode" value="${mapSyString.maoXiGuanBatchCode}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <c:if test="${empty mapSyString.maoXiGuanBatchCode}">
                                <input type="text" class="form-control" name="maoXiGuanBatchCode" value="34B09346" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <label>有效期</label>
                            <c:if test="${not empty mapSyString.maoXiGuanInstockDate}">
                                <input type="text" class="form-control form_datetime" name="maoXiGuanInstockDate" value="${mapSyString.maoXiGuanInstockDate}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <c:if test="${empty mapSyString.maoXiGuanInstockDate}">
                                <input type="text" class="form-control form_datetime" name="maoXiGuanInstockDate" value="" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                        </div>
                    </div>
                    <div class="col-md-6 seachInputBox">
                        <div class="form-group kit-group">
                            <label>
                                <div class="dropdown">
                                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                                        胶液 <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu">
                                        <li><label><input type="radio" name="jiaoYe" value="POP4" <c:if test="${mapSyString.jiaoYe eq 'POP4'|| empty mapSyString.jiaoYe}">checked</c:if>>POP4</label></li>
                                        <li><label><input type="radio" name="jiaoYe" value="POP6" <c:if test="${mapSyString.jiaoYe eq 'POP6'}">checked</c:if>>POP6</label></li>
                                    </ul>
                                </div>
                            </label>
                            <label>批号</label>
                            <c:if test="${not empty mapSyString.jiaoYeBatchCode}">
                                <input type="text" class="form-control" name="jiaoYeBatchCode" value="${mapSyString.jiaoYeBatchCode}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <c:if test="${empty mapSyString.jiaoYeBatchCode}">
                                <input type="text" class="form-control" name="jiaoYeBatchCode" value="1904187" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            </c:if>
                            <label>有效期</label>
                            <c:if test="${not empty mapSyString.jiaoYeInstockDate}">
                                <input type="text" class="form-control form_datetime" name="jiaoYeInstockDate" value="${mapSyString.jiaoYeInstockDate}" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if>>
                            </c:if>
                            <c:if test="${empty mapSyString.jiaoYeInstockDate}">
                                <input type="text" class="form-control form_datetime" name="jiaoYeInstockDate" value="2020-01-18" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if>>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-md-12 seachInputBox">
                        <div class="form-group">
                            <label>样品数量:</label>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" class="" value="16" name="quantity" <c:if test="${mapSyString.quantity eq '16' || empty mapSyString.quantity}">checked</c:if> >16--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" class="" value="32" name="quantity" <c:if test="${mapSyString.quantity eq '32'}">checked</c:if>>32--%>
                            <%--</label>--%>
                            <label class="radio-inline">
                                <input type="radio" class="" value="24" name="quantity" <c:if test="${mapSyString.quantity eq '24' || empty mapSyString.quantity}">checked</c:if>>24
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="" value="48" name="quantity" <c:if test="${mapSyString.quantity eq '48'}">checked</c:if>>48
                            </label>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" class="" value="64" name="quantity" <c:if test="${mapSyString.quantity eq '64'}">checked</c:if>>64--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                                <%--<input type="radio" class="" value="80" name="quantity" <c:if test="${mapSyString.quantity eq '80'}">checked</c:if>>80--%>
                            <%--</label>--%>
                            <label class="radio-inline">
                                <input type="radio" class="" value="72" name="quantity" <c:if test="${mapSyString.quantity eq '72'}">checked</c:if>>72
                            </label>
                            <label class="radio-inline">
                                <input type="radio" class="" value="96" name="quantity" <c:if test="${mapSyString.quantity eq '96'}">checked</c:if>>96
                            </label>
                            <%--<label class="radio-inline">--%>
                            <%--<label>其他:</label>--%>
                            <%--<input type="number" class="form-control" style="display: inline-block;width: 140px;">--%>
                            <%--</label>--%>
                        </div>
                    </div>
                    <div class="col-md-12 seachInputBox">
                        <div class="form-group kit-group-min">
                            <label>产物:</label>
                            <label>参数</label>
                            <input type="text" class="form-control" name="product" value="${labSyInfo.product}" readonly>
                            <label class="marginLeft100">甲酰胺:</label>
                            <label>参数</label>
                            <input type="text" class="form-control" name="formamide" value="${labSyInfo.formamide}" readonly>
                            <label class="marginLeft15">合计</label>
                            <input type="text" class="form-control" name="formamideTotal" readonly>
                            <label class="marginLeft100">内标参数:</label>
                            <label>参数</label>
                            <input type="text" class="form-control" name="internalStandardUl" value="${labSyInfo.internalStandardUl}" readonly>
                            <label class="marginLeft15">合计</label>
                            <input type="text" class="form-control" name="internalStandardUlTotal" readonly>
                        </div>
                    </div>
                    <div class="col-md-12 seachInputBox">
                        <div class="form-group kit-group-min">
                            <label>电泳类别:</label>
                            <input type="text" class="form-control" value="STR" name="syCategory" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if>>
                            <input type="text" class="form-control" value="36" name="syCategoryLength" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if> >
                            <label>cm</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular ">
    <div class="col-md-12" style="height:635px;">
        <div class="panel panel-default">
            <div class="panel-heading red">
                <div>电泳阶段(图形展示)</div>
                <button class="btn  btn-yellow-border btn-sm pull-right tabBtn">列表展示</button>
                <button class="btn btn-yellow btn-sm pull-right" style="margin-left: 15px">图形展示</button>
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
                                                <div class="kongGreen"></div>
                                            </c:when>
                                            <c:otherwise>
                                                <div></div>
                                            </c:otherwise>
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
<div class="row Modular " style="display: none">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>电泳记录详情</div>
                <button class="btn  btn-yellow-border btn-sm pull-right tabBtn">图形展示</button>
                <button class="btn btn-yellow btn-sm pull-right ">列表展示</button>
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
                        <th style="width:70px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="samplelist_tbody">
                    <c:forEach items="${labSySampleList}" var="sample" varStatus="s">
                        <tr>
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
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group seachButtonBox" style="height: 40px;">
                    <div style="float: right;height: 40px;">
                        <input type="hidden" name="syId" id="syId" value="${syId}">
                        <input type="hidden" name="taskId" id="taskId" value="${taskId}">
                        <input type="hidden" name="operateType" id="operateType" value="${operateType}">
                        <button class="btn btn-blue" type="button" id="saveBtn">保存</button>
                        <button class="btn btn-blue" type="button" id="record">生成记录表</button>
                        <button class="btn btn-yellow" type="button" id="saveNextBtn">完成</button>
                        <%--<button class="btn btn-blue-border btn-lang" type="button">返回</button>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        $("input[name='kitRadio']").on("click", function(){
            var param = {};

            param.panelName = $("input:radio[name='kitRadio']:checked").val();

            var quantity = $("input:radio[name='quantity']:checked").val();

            $.ajax({
                url: "<%=path%>/handwork/selectSyKit",
                type: "post",
                data: JSON.stringify(param),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        var mapParameter = data.mapParameter;

                        var product = mapParameter.product;
                        var jiaxianan = mapParameter.jiaxianan;
                        var nb = mapParameter.nb;

                        $("input[name='product']").val(mapParameter.product);
                        $("input[name='formamide']").val(mapParameter.jiaxianan);
                        $("input[name='internalStandardUl']").val(mapParameter.nb);
                        $("input[name='formamideTotal']").val(Mul(jiaxianan, quantity));
                        $("input[name='internalStandardUlTotal']").val(Mul(nb, quantity));
                    }
                }
            });
        })

        $("input[name='quantity']").on("click", function(){
            getTotal();
        })

        getTotal();

        function getTotal() {
            debugger;
            var quantity = $("input:radio[name='quantity']:checked").val();
            var jiaxianan = $("input[name='formamide']").val();
            var nb = $("input[name='internalStandardUl']").val();

            $("input[name='formamideTotal']").val(Mul(jiaxianan, quantity));
            $("input[name='internalStandardUlTotal']").val(Mul(nb, quantity));
        }

        //计算相乘
        function Mul(arg1, arg2){
            debugger;
            var m=0,s1=arg1.toString(),s2=arg2.toString();
            try{
                m+=s1.split(".")[1].length;
            }catch(e){}
            try{
                m+=s2.split(".")[1].length;
            }catch(e){}
            return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
        }

        //完成并进入下一步
        $("#saveNextBtn").on("click", function () {
            saveFormAndNext();
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
            $.ajax({
                url: "<%=path%>/center/saveSyInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&syEndDatetime=" + $("#syEndDatetime").val(),
                type: "post",
                data: JSON.stringify(syinfo()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#syId").val(data.syId);

                        $.ajax({
                            url: "<%=path%>/center/saveSyExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&syEndDatetime=" + $("#syEndDatetime").val() + "&syId=" + $("#syId").val(),
                            type: "post",
                            data: JSON.stringify(param()),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                if (data.success || data.success == true || data.success == "true") {
                                    <%--location.href = "<%=path%>/center/enterAnalysisExperiment?syId=" + data.syId;--%>
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

        /** 删除上样检材实验记录 */
        $("button[name='deleteBtn']", "#samplelist_tbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            var sampleId = $(that).find("input[name='sampleId']").val()
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabSySample?id=" + id,
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
                            location.href = "<%=path%>/center/refreshSyExperiment?syId=" + data.syId;
                        }
                    }
                });
            }
        });

        /** 保存上样实验记录 */
        $("#saveBtn").on("click", function () {
            saveForm();
        });

        function saveForm() {
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
                url: "<%=path%>/center/saveSyInfo?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&syEndDatetime=" + $("#syEndDatetime").val(),
                type: "post",
                data: JSON.stringify(syinfo()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#syId").val(data.syId);

                        $.ajax({
                            url: "<%=path%>/center/saveSyExperiment?taskId=" + $("#taskId").val() + "&operateType=" + $("#operateType").val() + "&syEndDatetime=" + $("#syEndDatetime").val(),
                            type: "post",
                            data: JSON.stringify(param()),
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
            location.href = "<%=path%>/center/refreshSyExperiment?syId=" + $("#syId").val();
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
                    sample.syId = $("#syId").val();
                    sample.id = $("input[name='id']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sample.samplePostion = $("input[name='samplePostion']", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                }
            }

            return sampleArr;
        }

        function syinfo() {
            var $syinfoTR = $("tr", "#samplelist_tbody");
            var syinfoCnt = $syinfoTR.length;
            var checkCount = 0;
            var labSyInfo = {};
            for (var i = 0; i < syinfoCnt; i++) {
                var checkBox = $("input[name='box']", $syinfoTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            }
            labSyInfo.syId = $("#syId").val();
            labSyInfo.sampleCount = checkCount;
//            labSyInfo.syStage = obj;
            labSyInfo.boardNo = $("input[name='boardNo']").val();
            labSyInfo.syPerson1 = $("input[name='syPerson1']").val();
            labSyInfo.syEndDatetime = $("input[name='syEndDatetime']").val();
            labSyInfo.elecInstrument = $("select[name='elecInstrument'] option:selected").val();
            labSyInfo.product = $("input[name='product']").val();
            labSyInfo.formamide = $("input[name='formamide']").val();
            labSyInfo.internalStandardUl = $("input[name='internalStandardUl']").val();

            var kitRadio = $("input:radio[name='kitRadio']:checked").val();
            var jiaXianAnBatchCode = $("input[name='jiaXianAnBatchCode']").val();
            var jiaXianAnInstockDate = $("input[name='jiaXianAnInstockDate']").val();
            var neiBiao = $("input:radio[name='neiBiao']:checked").val();
            var neiBiaoBatchCode = $("input[name='neiBiaoBatchCode']").val();
            var neiBiaoInstockDate = $("input[name='neiBiaoInstockDate']").val();
            var maoXiGuanBatchCode = $("input[name='maoXiGuanBatchCode']").val();
            var maoXiGuanInstockDate = $("input[name='maoXiGuanInstockDate']").val();
            var jiaoYe = $("input:radio[name='jiaoYe']:checked").val();
            var jiaoYeBatchCode = $("input[name='jiaoYeBatchCode']").val();
            var jiaoYeInstockDate = $("input[name='jiaoYeInstockDate']").val();
            var quantity = $("input:radio[name='quantity']:checked").val();
            var syCategory = $("input[name='syCategory']").val();
            var syCategoryLength = $("input[name='syCategoryLength']").val();

            var object = {};
            object['kitRadio'] = kitRadio;
            object['jiaXianAnBatchCode'] = jiaXianAnBatchCode;
            object['jiaXianAnInstockDate'] = jiaXianAnInstockDate;
            object['neiBiao'] = neiBiao;
            object['neiBiaoBatchCode'] = neiBiaoBatchCode;
            object['neiBiaoInstockDate'] = neiBiaoInstockDate;
            object['maoXiGuanBatchCode'] = maoXiGuanBatchCode;
            object['maoXiGuanInstockDate'] = maoXiGuanInstockDate;
            object['jiaoYe'] = jiaoYe;
            object['jiaoYeBatchCode'] = jiaoYeBatchCode;
            object['jiaoYeInstockDate'] = jiaoYeInstockDate;
            object['quantity'] = quantity;
            object['syCategory'] = syCategory;
            object['syCategoryLength'] = syCategoryLength;
            var json = JSON.stringify(object);

            labSyInfo.syString = json;

            return labSyInfo;
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
                })
            } else {
                $(this).parents(".Modular").fadeOut("slow", function () {
                    $(".tabBtn").eq(0).parents(".Modular").fadeIn("slow")
                })
            }
        });

        /** 获取检材信息 */
        $(".kongGreen").on("click", function () {
            $(".kongYellow").removeClass("kongYellow")
            $(this).addClass("kongYellow")
            var sampleNo = $("input[name='sampleNo']", $(this).parent()).val();
            $('#span_SampleNo').html(sampleNo);
            var sampleType = $("input[name='sampleType']", $(this).parent()).val();
            $('#span_SampleName').html(sampleType);
            var samplePosition = $("input[name='samplePosition']", $(this).parent()).val();
            $('#span_SamplePosition').html(samplePosition);
            var primer = $("input[name='primer']", $(this).parent()).val();
            $('#span_Primer').html(primer);
            var buffer = $("input[name='buffer']", $(this).parent()).val();
            $('#span_Buffer').html(buffer);
            var taqe = $("input[name='taqe']", $(this).parent()).val();
            $('#span_TaqE').html(taqe);
            var template = $("input[name='template']", $(this).parent()).val();
            $('#span_Template').html(template);
            var h2o = $("input[name='h2o']", $(this).parent()).val();
            $('#span_H₂O').html(h2o);
            var mgcl2 = $("input[name='mgcl2']", $(this).parent()).val();
            $('#span_MgCl₂').html(mgcl2);
            var dntp = $("input[name='dntp']", $(this).parent()).val();
            $('#span_dNTP').html(dntp);
        });

        /** 清空检材信息 */
        $(".kongBlue").on("click", function () {
            $(".kongYellow").removeClass("kongYellow")
            $('#span_SampleNo').html("");
            $('#span_SampleName').html("");
            $('#span_SamplePosition').html("");
            $('#span_Primer').html("");
            $('#span_Buffer').html("");
            $('#span_TaqE').html("");
            $('#span_Template').html("");
            $('#span_H₂O').html("");
            $('#span_MgCl₂').html("");
            $('#span_dNTP').html("");
        });

        //生成记录表
        $("#record").on("click", function () {
            if (!confirm("是否生成电泳记录表！")) {
                return false;
            }

            var syId = $("input[name='syId']", $(this).parent()).val();
            var taskId = $("input[name='taskId']", $(this).parent()).val();
            if (syId == '' || syId == null || syId == undefined || syId == "") {
                alert("先保存后再生成记录表");
                return false;
            }
            location.href = "<%=path%>/center/syRecordBook?syId=" + syId + "&taskId=" + taskId;
        });

        //试剂盒，内标选择
        $("input[name='neiBiao']").change(function(){
            if($(this).val()=="LIZ600"){
                $("input[name='neiBiaoBatchCode']").val("00759080");
                $("input[name='neiBiaoInstockDate']").val("22020-09-30");
            }
            if($(this).val()=="LIZ500"){
                $("input[name='neiBiaoBatchCode']").val("0000357764");
                $("input[name='neiBiaoInstockDate']").val("2021-07-01");
            }
            if($(this).val()=="ILS600"){
                $("input[name='neiBiaoBatchCode']").val("0000357764");
                $("input[name='neiBiaoInstockDate']").val("2021-07-01");
            }
            if($(this).val()=="ILS500"){
                $("input[name='neiBiaoBatchCode']").val("0000357764");
                $("input[name='neiBiaoInstockDate']").val("2021-07-01");
            }

        })



    })
</script>
</body>

</html>