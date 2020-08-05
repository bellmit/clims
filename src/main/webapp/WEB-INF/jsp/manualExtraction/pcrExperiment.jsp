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
        .btn-light-blue{
            background: #ebf5ff;
            color: #1e8af5;
            border: 1px solid #1e8af5;
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
                            <label>操作人姓名</label>
                            <input type="text" id="pcrPerson1" name="pcrPerson1" value="${labPcrInfo.pcrPerson1}" class="form-control"
                                   placeholder="请输入操作人">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>操作时间</label>
                            <input type="text" id="pcrEndDatetime" name="pcrEndDatetime" value="<fmt:formatDate value='${labPcrInfo.pcrEndDatetime}' pattern='yyyy-MM-dd'/>"
                                   class="form_datetime form-control" placeholder="请输入操作时间">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>扩增板条码</label>
                            <input type="text" name="boardNo" id="boardNo" value="${labPcrInfo.boardNo}" class="form-control"
                                   <c:if test="${labPcrInfo.boardNo != null}">readonly</c:if>  placeholder="请输入扩增板条码">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">

                            <label>扩增仪</label>
                            <div class="select">
                                <select name="pcrInstrument" value="${labPcrInfo.pcrInstrument}" class="form-control">
                                    <option value="">请选择</option>
                                    <c:forEach items="${panelListKZY}" var="system" varStatus="s">
                                        <%--&lt;%&ndash;<c:if test="${system.equipmentName eq system.equipmentName}">selected</c:if>>&ndash;%&gt;--%>
                                        <option value="${system.equipmentName}"
                                        <c:if test="${labPcrInfo.pcrInstrument eq system.equipmentName}">selected</c:if>>
                                                ${system.equipmentName}</option>
                                        <%--&lt;%&ndash;<option value="${system.equipmentName}">${system.equipmentName}</option>&ndash;%&gt;--%>
                                    </c:forEach>
                                </select>
                            </div>
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
<div class="row Modular ">
    <div class="col-md-12" style="padding-bottom: 63px">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>列表展示</div>
                <button class="btn  btn-light-blue addJiancai">添加检材</button>

                <div class="col-md-4 seachInputBox" style="float: none;">
                    <div class="form-group">
                        <label>扩增体系</label>
                        <div class="select" style="display: inline-block;width:300px;">
                            <input type="hidden" name="pcrSystemHidden" value="${labPcrInfo.pcrSystem}">
                            <select name="pcrSystem" value="${labPcrInfo.pcrSystem}" class="form-control">
                                <option value="">请选择</option>
                                <%--<c:forEach  items="${panelListKZY}" var="system" varStatus="s">--%>
                                    <%--<option value="${system.equipmentName}"--%>
                                            <%--<c:if test="${system.equipmentName eq labPcrInfo.pcrSystem}">selected</c:if>>--%>
                                            <%--${system.equipmentName}</option>--%>
                                <%--</c:forEach>--%>
                            </select>
                        </div>
                    </div>
                </div>


            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th style='width:8.1%'>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" name="checkAll" checked>
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th style='width:7.1%'>位置</th>
                        <th>检材编号</th>
                        <th style='width:9.5%'>检材名称</th>
                        <th style='width:8.5%'>检材类型</th>
                        <th>Primer Set</th>
                        <th>Master Mix</th>
                        <%--<th>TaqE ul</th>--%>
                        <th>DNA</th>
                        <th>H₂O</th>
                        <%--<th>MgCl₂ ul</th>--%>
                        <%--<th>dNTP ul</th>--%>
                        <%--<c:if test="${labPcrInfo.pcrStage ne '1'}">
                            <th style="width:70px;">操作</th>
                        </c:if>--%>
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
                                       onmouseover="this.title=this.value" value="${sample.samplePostion}">
                            </td>
                            <td title="${sample.sampleNo}" style="width: 150px;">
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
                            <%--<td>--%>
                                <%--<input name="taqe" type="text" class="form-control small"--%>
                                       <%--onmouseover="this.title=this.value" value="${sample.taqe}">--%>
                            <%--</td>--%>
                            <td>
                                <input name="template" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.template}">
                            </td>

                            <td>
                                <input name="h2o" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${sample.h2o}">
                            </td>
                            <%--<td>--%>
                                <%--<input name="mgcl2" type="text" class="form-control small"--%>
                                       <%--onmouseover="this.title=this.value" value="${sample.mgcl2}">--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<input name="dntp" type="text" class="form-control small"--%>
                                       <%--onmouseover="this.title=this.value" value="${sample.dntp}">--%>
                            <%--</td>--%>
                            <%--<c:if test="${labPcrInfo.pcrStage ne '1'}">
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
                                <input type="text" name="addJiancai" class="form-control" placeholder="请输入检材编号" id="sampleNo" >
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
    <div class="col-md-12">
        <input type="hidden" name="pcrId" id="pcrId" value="${labPcrInfo.pcrId}">
        <button class="btn btn-blue btn-lang btn-blue-border" type="button" id="goBack">返回</button>
        <c:if test="${labPcrInfo.pcrStage ne '1'}">
            <button class="btn btn-blue btn-lang" type="button" id="saveBtn">暂存</button>
        </c:if>
        <button class="btn btn-yellow btn-lang" type="button" id="saveNextBtn">完成</button>
        <button class="btn btn-green btn-lang" type="button" name="record" id="record">生成记录表</button>
        <input type="hidden" name="pcrReagent" value="${labPcrInfo.pcrReagent}">
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

        $("#goBack").on("click", function () {
            location.href = "<%=path%>/handwork/manualPcr";
        });

        //完成并保存
        $("#saveNextBtn").on("click", function () {
            if($("#reinspectionId").val() == "1"){
                repetitive("1");
            }else {
                saveForm("1");
            }
        });


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
        // $("button[name='deleteBtn']", "#samplelist_tbody").on("click", function () {
            $("#samplelist_tbody").on("click","button[name='deleteBtn']",function(){
            var id = $("input[name='id']", $(this).parent()).val();
            var that = $(this).parents("tr")
            if (confirm("确认删除吗?")) {
                $.ajax({
                    url: "<%=path%>/center/deleteLabPcrSample?id=" + id,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        if (data.length == undefined) {
                            that.remove()
                        }
                        if (data.success || data.success == true || data.success == "true") {
                            <%--location.href = "<%=path%>/handwork/pcrExperiment?pcrId=" + $("#pcrId").val();--%>
                        }
                    }
                });
            }
        });

        /**暂存 */
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
            if (boardNo == null || boardNo == "" || typeof(boardNo) == "undefined") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            var labPcrInfo = GetLabPcrInfo(obj);

            $.ajax({
                url: "<%=path%>/handwork/repeatingByBoarsNo",
                type: "post",
                data: JSON.stringify(labPcrInfo),
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
            if (!checkInputValidation()) {
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "" || typeof(boardNo) == "undefined") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }

            var labPcrInfo = GetLabPcrInfo(obj);
            var labPcrSampleList = GetLabPcrSample();

            var params = {
                "labPcrInfo": labPcrInfo,
                "labPcrSampleList": labPcrSampleList
            };
//            $("#recordModal").modal('show');

            $.ajax({
                url: "<%=path%>/handwork/savePcrInfo",
                type: "post",
                data: JSON.stringify(params),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        $("#pcrId").val(data.pcrId);
                        <%--location.href = "<%=path%>/handwork/pcrExperiment?pcrId=" + $("#pcrId").val();--%>
                        $("#recordModal").modal('show');
                    }
                }
            });
        }

        function GetLabPcrSample() {
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

        function GetLabPcrInfo(obj) {
            var labPcrInfo = {};

            var $sampleTR = $("tr", "#samplelist_tbody");
            var sampleCnt = $sampleTR.length;
            var checkCount = 0;
            for (var i = 0; i < sampleCnt; i++) {
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            }
            labPcrInfo.pcrId = $("#pcrId").val();
            labPcrInfo.sampleCount = checkCount;
            labPcrInfo.boardNo = $("input[name='boardNo']").val();
            labPcrInfo.pcrEndDatetime = $("input[name='pcrEndDatetime']").val();
            labPcrInfo.pcrPerson1 = $("input[name='pcrPerson1']").val();
            labPcrInfo.pcrReagent = $("input[name='pcrReagent']").val();
            labPcrInfo.pcrInstrument = $("select[name='pcrInstrument'] option:selected").val();
            labPcrInfo.pcrProgram = $("select[name='pcrProgram'] option:selected").val();
            labPcrInfo.pcrSystem = $("select[name='pcrSystem'] option:selected").val();
            labPcrInfo.batchb = $("input[name='batchb']").val();
            labPcrInfo.validity = $("input[name='validity']").val();
            labPcrInfo.pcrStage = obj;

            return labPcrInfo;
        }

        //刷新
        $("#finishedBtn").on("click", function () {
            $("#recordModal").modal('hide');
            location.href = "<%=path%>/handwork/pcrExperiment?pcrId=" + $("#pcrId").val();
        });

        /**保存扩增任务信息*/
        function param() {

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
            minView: "month", /*hour*/
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
                    $("#graph").find(".col-md-12").each(function () {
                        $(this).children("span").eq(1).html("")
                        $(this).children("input").val("")
                    })
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
                alert("先保存后再生产记录表");
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


        //添加检材信息
        $("#addConsume").on("click", function () {
            $("#addJiancai").modal('hide');
            var sampleNo = $("#sampleNo").val();
            if(sampleNo == '' || sampleNo == null || sampleNo == undefined || sampleNo == ""){
                alert("请输入检材编号")
            }else {
                $.ajax({
                    <%--url: "<%=path%>/handwork/selectSampleNo?&sampleNo=" + sampleNo,--%>
                    url: "<%=path%>/handwork/addConsume?pcrId=" + $("#pcrId").val() + "&sampleNo=" + $("#sampleNo").val(),
                    type: "post",
                    data: {},
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {
                        // console.log(data.limsSampleInfoDna)
                        // console.log(data.limsSampleInfoDna.samplePostion)
                        if (data.success || data.success == true || data.success == "true") {
                            // //alert(sampleInfo)
                            // //alert(data.limsSampleInfoDna.sampleNo)
                            $("#pcrId").val(data.pcrId)
                            // saveForm("0");
                            // $("#recordModal").modal('show');

                            var sampleTr = '<tr>'
                            sampleTr += '<td><label class="custom-control checkbox-inline"> <input class="custom-control-input" type="checkbox" name="box" checked><spanclass="custom-control-label"></span><input class="custom-control-input" type="checkbox" name="box" checked><span  class="custom-control-label"></span> </label> </td>'
                            sampleTr += '<td><input name="samplePostion" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
                            sampleTr += '<td><input name="sampleNo" type="text" class="form-control small"onmouseover="this.title=this.value" value="' + data.labPcrSample.sampleNo + '"readonly="readonly"/> </td>'
                            sampleTr += '<td><input name="sampleName" type="text" class="form-control small"onmouseover="this.title=this.value" value="' + data.labPcrSample.sampleName + '"readonly="readonly"/> </td>'
                            sampleTr += '<td><input name="sampleType" type="text" class="form-control small"onmouseover="this.title=this.value" value="' + data.labPcrSample.sampleType + '"readonly="readonly"/> </td>'
                            sampleTr += '<td><input name="primer" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
                            sampleTr += '<td><input name="buffer" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
//                            sampleTr += '<td><input name="taqe" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
                            sampleTr += '<td><input name="template" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
                            sampleTr += '<td><input name="h2o" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
//                            sampleTr += '<td><input name="mgcl2" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
//                            sampleTr += '<td><input name="dntp" type="text" class="form-control small"onmouseover="this.title=this.value" value=""/> </td>'
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" name="deleteBtn" class="btn-icon btn-icon-red btn-icon-shanchu-red remove" > 删除 </button>'
                            sampleTr += '<input type="hidden" name="id" value=""/>'
                            sampleTr += '<input type="hidden" name="sampleId" value="' + data.labPcrSample.sampleId + '"/>'
                            sampleTr += '</td>'
                            sampleTr += '</tr>'
                            $("#samplelist_tbody").append(sampleTr)
                            <%--location.href = "<%=path%>/handwork/pcrExperiment?pcrId=" + $("#pcrId").val();--%>
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
                    //debugger;
                    var experimentalParameterList = data.experimentalParameterList;
                    console.log(experimentalParameterList);
                    var len = experimentalParameterList.length;
                    $("select[name='pcrSystem']").empty();
                    var inhtml = "";
                    inhtml += "<option value=''>请选择</option>";
                    //console.log();

                    if (len > 0) {
                        for ( var i =0; i <len; i++) {
                            if($("select[name='sampleType']").val() =="03"){
                                if(experimentalParameterList[i].remark == "1"){
                                    if (pcrSystem == experimentalParameterList[i].parameterName) {
                                        inhtml += "<option value='" + experimentalParameterList[i].parameterName + "' selected>" + experimentalParameterList[i].parameterName + "</option>";
                                    }else {
                                        inhtml += "<option value='" + experimentalParameterList[i].parameterName + "'>" + experimentalParameterList[i].parameterName + "</option>";
                                    }
                                }
                            }else{
                                if(experimentalParameterList[i].remark != "1"){
                                    //alert(experimentalParameterList[i].remark);
                                    if (pcrSystem == experimentalParameterList[i].parameterName) {
                                        inhtml += "<option value='" + experimentalParameterList[i].parameterName + "' selected>" + experimentalParameterList[i].parameterName + "</option>";
                                    }else {
                                        inhtml += "<option value='" + experimentalParameterList[i].parameterName + "'>" + experimentalParameterList[i].parameterName + "</option>";
                                    }
                                }
                            }
                        }
                    }
                    $("select[name='pcrSystem']").append(inhtml);
                    aa();
                }
            }
        });
    }
    //var aa = "";
    function aa(){
        ////alert($("select[name='pcrSystem']").val());
        if($("select[name='pcrSystem']").val()!=""){
            var aa = $("select[name='pcrSystem']").val();
            qwe(aa);
        }
    }
    $("select[name='pcrSystem']").change('click',function(){
        var pcrSystem = $("select[name='pcrSystem'] option:selected").val();
        qwe(pcrSystem);

    })

    function qwe(pcrSystem){
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
                //debugger;
                if (data.success || data.success == true || data.success == "true") {
                    //console.log(data.experimentalParameterList);
                    var experimentalParameterList = data.experimentalParameterList;
                    var a = experimentalParameterList.length;
                    //alert("a:"+a);
                    if($("select[name='sampleType']").val() =="03"){
                        for( var i =0; i <a; i++){
                            if(experimentalParameterList[i].remark == "1"){
                                var mapParameter = experimentalParameterList[i].parameterValue;
                                var $trs=$("tr","#samplelist_tbody");
                                if(typeof(mapParameter)!="undefined" && mapParameter!=0 && mapParameter != null && mapParameter != ""){
                                    $trs.each(function(){
                                        var param = parameter[0];
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
                                // JSON.parse(mapParameter);
                                //console.log(JSON.parse(mapParameter));
                                var $trs=$("tr","#samplelist_tbody");
                                if(typeof(mapParameter)!="undefined" && mapParameter!=0 && mapParameter != null && mapParameter != ""){
                                    $trs.each(function(){
                                        var param = parameter[0];
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

    //添加检材弹框
    $(".addJiancai").click(function (){
        //清空input框值
        $("input[name='addJiancai']").val("");
        $("#addJiancai").modal("show")
    })

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