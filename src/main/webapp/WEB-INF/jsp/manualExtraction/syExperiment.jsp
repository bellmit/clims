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
        .btn-light-blue{
            background: #ebf5ff;
            color: #1e8af5;
            border: 1px solid #1e8af5;
        }
        .btn-box{
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
            margin-top: 0px !important;
        }
        .btn-box button + button {
            margin-left: 10px;
        }
        .kit-group>.form-control{
            width: 30%;
            display: inline-block;
        }
        .kit-group label:last-of-type{
            margin-left: 15px;
        }
        .kit-group-min .form-control{
            width: 150px;
            display: inline-block;
        }
        .marginLeft15{
            margin-left: 15px;
        }
        .marginLeft100{
            margin-left: 100px;
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
                <div class="row">
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>操作人</label>
                            <input type="text" class="form-control" placeholder="请输入操作人" name="syPerson1" id="syPerson1"
                                   value="${labSyInfo.syPerson1}">
                        </div>
                    </div>

                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>电泳时间</label>
                            <input type="text" class="form_datetime form-control" placeholder="请输入电泳时间" id="syEndDatetime" name="syEndDatetime"
                                   value="<fmt:formatDate value='${labSyInfo.syEndDatetime}' pattern='yyyy-MM-dd'/>">
                        </div>
                    </div>

                   <%-- <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>产物</label>
                            <div class="select">
                                <select name="product" value="${labSyInfo.product}" class="form-control">
                                    <option value="">请选择产物</option>
                                    <c:forEach items="${panelListCW}" var="system" varStatus="s">
                                        <option value="${system.equipmentName}" <c:if test="${labSyInfo.product eq system.equipmentName}">selected</c:if> >${system.equipmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>甲酰胺</label>
                            <div class="select">
                                <select name="formamide" value="${labSyInfo.formamide}" class="form-control">
                                    <option value="">请选择甲酰胺</option>
                                    <c:forEach items="${panelListJQA}" var="system" varStatus="s">
                                        <option value="${system.equipmentName}" <c:if test="${system.equipmentName eq labSyInfo.formamide}">selected</c:if>>${system.equipmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>内标μl</label>
                            <div class="select">
                                <select name="internalStandardUl" value="${labSyInfo.internalStandardUl}" class="form-control">
                                    <option value="">请选择内标μl</option>
                                    <c:forEach items="${panelListNBμl}" var="system" varStatus="s">
                                        <option value="${system.equipmentName}" <c:if test="${system.equipmentName eq labSyInfo.internalStandardUl}">selected</c:if>>${system.equipmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>内标</label>
                            <div class="select">
                                <select name="internalStandard" value="${labSyInfo.internalStandard}" class="form-control">
                                    <option value="">请选择内标</option>
                                    <c:forEach items="${panelListNB}" var="system" varStatus="s">
                                        <option value="${system.equipmentName}" <c:if test="${system.equipmentName eq labSyInfo.internalStandard}">selected</c:if>>${system.equipmentName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>--%>

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
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>板号</label>
                            <input type="text" class="form-control" name="boardNo" id="boardNo" value="${labSyInfo.boardNo}"
                                   <c:if test="${labSyInfo.boardNo != null}">readonly</c:if>>
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
                            <label class="radio-inline">
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
                            <label class="radio-inline" style="display: none;">
                                <input type="radio" class="" value="DNATyper15" <c:if test="${mapSyString.kitRadio eq 'DNATyper15'}">checked</c:if>
                                       name="kitRadio">DNATyper15
                            </label>
                            <label class="radio-inline" style="display: none;">
                                <input type="radio" class="" value="Sinofiler" <c:if test="${mapSyString.kitRadio eq 'Sinofiler'}">checked</c:if>
                                       name="kitRadio">Sinofiler
                            </label>
                            <label class="radio-inline" style="display: none;">
                                <input type="radio" class="" value="GoldenEye" <c:if test="${mapSyString.kitRadio eq 'GoldenEye'}">checked</c:if>
                                       name="kitRadio">GoldenEye
                            </label>
                            <label class="radio-inline" style="display: none;">
                                <input type="radio" class="" value="Minifiler" <c:if test="${mapSyString.kitRadio eq 'Minifiler'}">checked</c:if>
                                       name="kitRadio">Minifiler
                            </label>
                            <label class="radio-inline" style="display: none;">
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
                                <input type="text" class="form-control" name="jiaXianAnBatchCode" value="1904623" <c:if test="${labSyInfo.syStage eq '1'}">readonly</c:if>>
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
                                        <li><label><input type="radio" value="LIZ500" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'LIZ500' || empty mapSyString.neiBiao}">checked</c:if>>LIZ500</label></li>
                                        <li><label><input type="radio" value="ILS600" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'ILS600'}">checked</c:if>>ILS600</label></li>
                                        <li><label><input type="radio" value="LIZ600" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'LIZ600'}">checked</c:if>>LIZ600</label></li>
                                        <li><label><input type="radio" value="ILS500" name="neiBiao" <c:if test="${mapSyString.neiBiao eq 'ILS500'}">checked</c:if>>ILS500</label></li>
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
                                <input type="radio" class="" value="24" name="quantity" <c:if test="${mapSyString.quantity eq '24' || empty mapSyString.quantity}">checked</c:if> >24
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
                                <input type="radio" class="" value="72" name="quantity" <c:if test="${mapSyString.quantity eq '72' || empty mapSyString.quantity}">checked</c:if> >72
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

<div class="row Modular ">
    <div class="col-md-12" style="padding-bottom: 63px">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>列表展示</div>
                <button class="btn btn-light-blue addJiancai">添加检材</button>
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
                        <%--<c:if test="${labSyInfo.syStage ne '1'}">
                            <th style="width:70px;">操作</th>
                        </c:if>--%>
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
                                       onmouseover="this.title=this.value" value="${sample.samplePostion}">
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
                           <%-- <c:if test="${labSyInfo.syStage ne '1'}">
                                <td>
                                    <input type="hidden" name="id" value="${sample.id}"/>
                                    <input type="hidden" id="reinspectionId" value="${reinspectionId}"/>
                                    <input type="hidden" name="sampleId" value="${sample.sampleId}"/>
                                    <button type="button" name="deleteBtn"
                                            class="btn-icon btn-icon-red btn-icon-shanchu-red"> 删除
                                    </button>
                                </td>
                            </c:if>--%>
                            <td>
                                <input type="hidden" name="id" value="${sample.id}"/>
                                <input type="hidden" id="reinspectionId" value="${reinspectionId}"/>
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
<div class="modal fade popBox bigBox" id="addJiancai" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  id="" action="" class="">
                <div class="modal-header">
                    <h4 class="modal-title">添加检材</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="请输入检材编号" id="sampleNo">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer clearfix">
                    <button class="btn btn-lang  btn-blue " type="button" id="addConsume">确定</button>
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal" id="qu">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12" style="text-align: right">
        <input type="hidden" name="syId" id="syId" value="${labSyInfo.syId}">
        <button class="btn btn-blue btn-lang btn-blue-border" type="button" id="goBack">返回</button>
        <c:if test="${labSyInfo.syStage ne '1'}">
            <button class="btn btn-blue btn-lang" type="button" id="saveBtn">暂存</button>
        </c:if>
        <button class="btn btn-yellow btn-lang" type="button" id="saveNextBtn">完成</button>
        <button class="btn btn-green btn-lang" type="button" name="record" id="record">生成记录表</button>
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
            var quantity = $("input:radio[name='quantity']:checked").val();
            var jiaxianan = $("input[name='formamide']").val();
            var nb = $("input[name='internalStandardUl']").val();

            $("input[name='formamideTotal']").val(Mul(jiaxianan, quantity));
            $("input[name='internalStandardUlTotal']").val(Mul(nb, quantity));
        }

        //计算相乘
        function Mul(arg1, arg2){
            var m=0,s1=arg1.toString(),s2=arg2.toString();
            try{
                m+=s1.split(".")[1].length;
            }catch(e){}
            try{
                m+=s2.split(".")[1].length;
            }catch(e){}
            return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);
        }

        //返回
        $("#goBack").on("click", function () {
            location.href = "<%=path%>/handwork/manualSy";
        });

        //完成
        $("#saveNextBtn").on("click", function () {
            if($("#reinspectionId").val() == "1"){
                repetitive("1");
            }else {
                saveForm("1");
            }
        });

        /** 删除上样检材实验记录 */
        $("#samplelist_tbody").on("click","button[name='deleteBtn']",function(){
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabSySample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if (data.length == undefined) {
                            that.remove()
                        }
                        if (data.success || data.success == true || data.success == "true") {
                            <%--location.href = "<%=path%>/handwork/syExperiment?syId=" + $("#syId").val();--%>
                        }
                    }
                });
            }
        });

        /** 保存上样实验记录 */
        $("#saveBtn").on("click", function () {
            if($("#reinspectionId").val() == "1"){
                repetitive("0");
            }else {
                saveForm("0");
            }
        });

        //判断板号不重复
        function repetitive(obj){
            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            var labSyInfo = GetLabSyInfo(obj);

            $.ajax({
                url: "<%=path%>/handwork/selectBoardNoIsExist",
                type: "post",
                data: JSON.stringify(labSyInfo),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        saveForm(obj);
                    } else {
                        alert("该板已存在")
                    }
                }
            });
        }

        function saveForm(obj) {
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

            var labSyInfo = GetLabSyInfo(obj);
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
                success: function (data) {
                    debugger;
                    if (data.success || data.success == true || data.success == "true") {
                        $("#syId").val(data.syId);
                        $("#recordModal").modal('show');
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    debugger;
                    alert("保存失败！");
                }
            });

        }

        function GetLabSySample() {
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

        function GetLabSyInfo(obj) {
            debugger;
            var labSyInfo = {};

            var $labSyInfoTR = $("tr", "#samplelist_tbody");
            var labSyInfoCnt = $labSyInfoTR.length;
            var checkCount = 0;
            for (var i = 0; i < labSyInfoCnt; i++) {
                var checkBox = $("input[name='box']", $labSyInfoTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            }
            labSyInfo.syId = $("#syId").val();
            labSyInfo.sampleCount = checkCount;
            labSyInfo.syStage = obj;
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

        $("#finishedBtn").on("click", function () {
            $("#recordModal").modal('hide');
            location.href = "<%=path%>/handwork/syExperiment?syId=" + $("#syId").val();
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


        //生成记录表
        $("#record").on("click", function () {
            if (!confirm("是否生成电泳记录表！")) {
                return false;
            }
            var syId = $("input[name='syId']", $(this).parent()).val();
            var taskId = $("input[name='taskId']", $(this).parent()).val();
            if (syId == '' || syId == null || syId == undefined || syId == "") {
                alert("先保存后再生产记录表");
                return false;
            }
            location.href = "<%=path%>/center/syRecordBook?syId=" + syId + "&taskId=" + taskId;
        });

        //电泳添加检材信息
        $("#addConsume").on("click", function () {
            $("#addJiancai").modal('hide');
            var sampleNo = $("#sampleNo").val();
            if(sampleNo == '' || sampleNo == null || sampleNo == undefined || sampleNo == ""){
                alert("请输入检材编号")
            }else {
                $.ajax({
                    url: "<%=path%>/handwork/addLoadSample?syId=" + $("#syId").val() + "&sampleNo=" + $("#sampleNo").val(),
                    type: "post",
                    data: {},
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        if (data.success || data.success == true || data.success == "true") {
                            $("#syId").val(data.syId)
//                            $("#recordModal").modal('show');
                            var sampleTr = '<tr>'
                            sampleTr += '<td> <label class="custom-control checkbox-inline"> <input class="custom-control-input" type="checkbox" name="box" checked><spanclass="custom-control-label"></span> <input class="custom-control-input" type="checkbox" name="box" checked><span  class="custom-control-label"></span> </label> </td>'
                            sampleTr += '<td><input name="samplePostion" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'

                            sampleTr += '<td><input name="sampleNo" type="text" class="form-control small"onmouseover="this.title=this.value" value="' + data.labSySample.sampleNo + '"readonly="readonly"/> </td>'
                            sampleTr += '<td><input name="sampleName" type="text" class="form-control small"onmouseover="this.title=this.value" value="'+ data.labSySample.sampleName +'"readonly="readonly"></td>'
                            <%--sampleTr += '<td> <select class="form-control small" disabled name="sampleType"><c:forEach items="${sampleTypeList}" var="list"> <option value="${list.dictCode}"<c:if test="${sample.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option></c:forEach> </select> </td>'--%>
                            sampleTr += '<td><input name="sampleType" type="text" class="form-control small"onmouseover="this.title=this.value" value="' +  data.labSySample.sampleType +'"readonly="readonly"/> </td>'
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" name="deleteBtn" class="btn-icon btn-icon-red btn-icon-shanchu-red remove"> 删除 </button>'
                            sampleTr += '<input type="hidden" name="id" value=""/>'
                            sampleTr += '<input type="hidden" name="sampleId" value="' + data.labSySample.sampleId + '"/>'
                            sampleTr += '</td>'
                            sampleTr += '</tr>'
                            $("#samplelist_tbody").append(sampleTr);
                        } else {
                            alert("该检材不存在！");
                        }
                    },
                    error: function (e) {
                        alert("添加失败！");
                    }
                });
            }
        });

        //添加检材弹框
        $(".addJiancai").click(function (){
            //清空input框值
            $(".modal-body input").val("");
            $("#addJiancai").modal("show")
        })

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