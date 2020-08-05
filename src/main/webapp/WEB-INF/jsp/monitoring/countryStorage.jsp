<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
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
    <title>入国家库监控</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .table .successBox {
            color: #38bca8;
            font: normal normal normal 14px FontAwesome;
            font-size: inherit;
            text-rendering: auto;
            -webkit-font-smoothing: antialiased;
        }

        .table .successBox::before {
            content: "\f058";
            color: #50c987;
            float: left;
            margin-right: 5px;
            font-size: 20px;
        }

        .table .errorBox {
            color: #fe7371;
            font: normal normal normal 14px FontAwesome;
            font-size: inherit;
            text-rendering: auto;
            -webkit-font-smoothing: antialiased;
            cursor: pointer;
        }

        .table .errorBox::before {
            content: "\f057";
            color: #ff5a56;
            float: left;
            margin-right: 5px;
            font-size: 20px;
        }

        .popover {
            top: 420px !important;
            left: 860px !important;
        }

        .popover-title {
            background: #fff;
            border: none;
            padding-top: 20px;
            padding-left: 40px;
            padding-right: 40px;

        }

        .popover-title::before {
            content: "";
            width: 12px;
            height: 12px;
            background: #3d9af7;
            float: left;
            border-radius: 50%;
            margin-left: -20px;
        }

        .popover-content {
            padding: 0 40px;
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
            </div>
            <div class="panel-body">
                <form id="stateStorageForm" action="<%=path%>/center/stateMonitoring" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件名称</label>
                                <input class="form-control" type="text" placeholder="请输入案件名称" name="entity.caseName" value="${query.entity.caseName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件编号</label>
                                <input class="form-control" type="text" placeholder="请输入案件编号"  name="entity.caseNo" value="${query.entity.caseNo}">
                            </div>
                        </div>

                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>现勘A号</label>
                                <input type="text" class="form-control" placeholder="请输入案件编号" id="xkANo"
                                       name="entity.xkANo" value="${query.entity.xkANo}">
                            </div>
                        </div>

                        <%--<div class="col-md-4 seachInputBox">--%>
                            <%--<div class="form-group">--%>
                                <%--<label>样本名称</label>--%>
                                <%--<input class="form-control" type="text" placeholder="请输入样本名称" name="sampleName" value="${query.sampleName}">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-4 seachInputBox">--%>
                            <%--<div class="form-group">--%>
                                <%--<label>样本编号</label>--%>
                                <%--<input class="form-control" type="text" placeholder="请输入样本编号" name="sampleNo" value="${query.sampleNo}">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-4 seachInputBox">--%>
                            <%--<div class="form-group">--%>
                                <%--<label>样本状态</label>--%>
                                <%--<input class="form-control" type="text" placeholder="请输入样本状态" name="status" value="${query.entity.status}">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>受理起止时间</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptStartDatetime" name="acceptStartDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.acceptStartDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择受理时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptEndDatetime" name="acceptEndDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.acceptEndDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择受理时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>受理人</label>
                                <div class="select">
                                    <select id="acceptorId" name="acceptorId" value="${query.acceptorId}"
                                            class="form-control">
                                        <option value="1001" selected>全部</option>
                                        <c:forEach items="${amPersonalInfoVoList}" var="list" varStatus="cs">
                                            <option value="${list.entity.personalId}"
                                                    <c:if test="${list.entity.personalId eq query.acceptorId}">selected</c:if>>${list.entity.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>上报类型</label>
                                <div class="select">
                                    <select id="queueType" name="queueType" value="${query.queueType}"
                                            class="form-control">
                                        <option value="15" <c:if test="${query.queueType eq '15'}">selected</c:if>>案件上报</option>
                                        <option value="16" <c:if test="${query.queueType eq '16'}">selected</c:if>>建库上报</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" type="submit" id="addStsteStorage">查询</button>
                                <button class="btn btn-blue-border" name="reset">重置</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>样本列表</div>
                <%--<button class="btn btn-yellow-border pull-right" style="margin-top: -7px">重新入库</button>--%>
            </div>
            <div class="panel-body nopadding">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>案件名称</th>
                        <th>案件编号</th>
                        <th>入库样本总数</th>
                        <th>入库样本成功数</th>
                        <th>入库样本失败数</th>
                        <th>等待入库样本总数</th>
                        <th>操作</th>
                        <%--<th>--%>
                            <%--<label class="custom-control checkbox-inline">--%>
                                <%--&lt;%&ndash;<input class="custom-control-input" type="checkbox" name="checkAll">&ndash;%&gt;--%>
                                <%--<span class="custom-control-label" style="font-weight: 600">入库操作</span>--%>
                            <%--</label>--%>
                        <%--</th>--%>
                    </tr>
                    </thead>
                    <tbody id="caseInfoListTbody">

                    <c:forEach items="${limsCaseInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td style="display: none">${list.entity.gjkSYSNo}<input type="hidden" name="gjkSYSNo" value="${list.entity.gjkSYSNo}"></td>
                            <td>${list.entity.caseName}<input type="hidden" name="caseName" value="${list.entity.caseName}"></td>
                            <td>${list.entity.caseNo}<input type="hidden" name="caseNo" value="${list.entity.caseNo}"></td>
                            <td>${list.backUpCount}<input type="hidden" name="backUpCount" value="${list.backUpCount}"></td>
                            <td>${list.backSuccessCount}<input type="hidden" name="backSuccessCount" value="${list.backSuccessCount}"></td>
                                <%--&lt;%&ndash;<td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd '/><input type="hidden" name="createDatetime" value="${list.entity.createDatetime}"></td>&ndash;%&gt;--%>
                            <td>${list.backFailCount}<input type="hidden" name="backFailCount" value="${list.backFailCount}"></td>
                            <td>${list.backUpWaitForCount}<input type="hidden" name="backUpWaitForCount" value="${list.backUpWaitForCount}"></td>
                            <td>
                                <input type="hidden" id="caseId" name="caseId" value="${list.entity.caseId}">
                                <input type="hidden" id="consignmentId" name="consignmentId"
                                       value="${list.consignmentId}">
                                <%--<a href="<%=path%>/center/warehouseCompare?caseId=${list.entity.caseId}"--%>
                                   <%--target="ifm"  class="btn-icon btn-icon-blue  btn-icon-bianji-blue">入库</a>--%>
                                <%--<a href="<%=path%>/center/warehouseCompare?caseId=${list.entity.caseId}"--%>
                                <a href="<%=path%>/center/warehouseCompareAgain?caseId=${list.entity.caseId}"
                                   class="btn btn-red btn-xs">入库</a>
                                <a href="<%=path%>/dowmFileController/generateInboundOrder?caseId=${list.entity.caseId}"
                                   class="btn btn-yellow btn-xs">生成入库单</a>
                            </td>
                            <td style="display: none;">
                                <input type="hidden" name="caseName" value="${list.entity.caseName}">
                                <input type="hidden" name="caseNo" value="${list.entity.caseNo}">
                                <%--<input type="hidden" name="sampleName" value="${list.sampleName}">--%>
                                <%--<input type="hidden" name="sampleNo" value="${list.sampleNo}">--%>
                                <%--<input type="hidden" name="boardNo" value="${list.boardNo}">--%>
                                <%--<input type="hidden" name="status" value="${list.entity.status}">--%>
                                <%--<button name="modifyBtn" type="button" class="btn-icon btn-icon-blue  btn-icon-bianji-blue edit">入库</button>--%>
                            </td>
                        </tr>
                    </c:forEach>

                    <%--<tr>--%>
                        <%--<td>01</td>--%>
                        <%--<td>案件名称</td>--%>
                        <%--<td>案件编号</td>--%>
                        <%--<td>样本名称</td>--%>
                        <%--<td>样本编号</td>--%>
                        <%--<td>板孔位置</td>--%>
                        <%--<td class="successBox">成功</td>--%>
                        <%--<td>--%>
                            <%--<label class="custom-control checkbox-inline">--%>
                                <%--<input class="custom-control-input" type="checkbox">--%>
                                <%--<span class="custom-control-label">入库操作</span>--%>
                            <%--</label>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td>02</td>--%>
                        <%--<td>案件名称</td>--%>
                        <%--<td>案件编号</td>--%>
                        <%--<td>样本名称</td>--%>
                        <%--<td>样本编号</td>--%>
                        <%--<td>板孔位置</td>--%>
                        <%--<td class="errorBox" data-container="body" data-toggle="popover" title="失败原因失败原因失败原因失败原因"--%>
                            <%--data-content="16分钟前">失败--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<label class="custom-control checkbox-inline">--%>
                                <%--<input class="custom-control-input" type="checkbox">--%>
                                <%--<span class="custom-control-label">入库操作</span>--%>
                            <%--</label>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    </tbody>
                </table>
                <div class="row" style="padding: 0px">
                    <div class="col-md-12">
                        <div id="kkpager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        kkpager.generPageHtml({
            pno: ${mainStateCnt.page},
            //总页码
            total: ${mainStateCnt.pageCount},
            //总数据条数
            totalRecords: ${mainStateCnt.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/stateMonitoring',
            //链接尾部
            //hrefLatter: '.html',
            getLink: function (page) {
                return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#stateStorageForm").serialize();
            }
            , lang: {
                firstPageText: '首页',
                firstPageTipText: '首页',
                lastPageText: '尾页',
                lastPageTipText: '尾页',
                prePageText: '上一页',
                prePageTipText: '上一页',
                nextPageText: '下一页',
                nextPageTipText: '下一页',
                totalPageBeforeText: '共',
                totalPageAfterText: '页',
                currPageBeforeText: '当前第',
                currPageAfterText: '页',
                totalInfoSplitStr: '/',
                totalRecordsBeforeText: '共',
                totalRecordsAfterText: '条数据',
                gopageBeforeText: '&nbsp;转到',
                gopageButtonOkText: '确定',
                gopageAfterText: '页',
                buttonTipBeforeText: '第',
                buttonTipAfterText: '页'
            }
        });

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

        $("#addStsteStorage").on("click", function () {
            $("#page").val(1);
            $('#stateStorageForm').submit();
        });

        //重置
        $("button[name='reset']").on("click", function () {
            $("#stateStorageForm").find("input[type='text']").val("");

            location.href = "<%=path%>/center/stateMonitoring";
        })



        $('.errorBox').popover({
            trigger: "hover",
            placement: 'bottom'
        })
        //全选
        $("input[name='checkAll']").change(function () {
            if ($(this).is(":checked")) {
                $("tbody").find("input[type='checkbox']").prop("checked", true)
            } else {
                $("tbody").find("input[type='checkbox']").prop("checked", false)
            }
        })
        $("tbody").find("input[type='checkbox']").change(function () {
            if ($("tbody").find("input[type='checkbox']").length == $("tbody").find("input[type='checkbox']:checked").length) {
                $("input[name='checkAll']").prop("checked", true)
            } else {
                $("input[name='checkAll']").prop("checked", false)
            }
        })


            //入库失败标红

            $('tbody tr').each(function() {
                txtVlaue = $(this).find('td:eq(1)').text();
                if(txtVlaue == ""){
//                    $(this).find('td').css("background-color", "#f65c82");
                    $(this).find('td').css("background-color", "#f65c82");
                }
            })



        })
</script>
</body>

</html>