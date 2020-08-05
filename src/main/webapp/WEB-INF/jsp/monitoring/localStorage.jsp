<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>北京市公安局法医鉴定案件受理系统</title>
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
                <form id="localStorageForm" action="<%=path%>/center/localMonitoring" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件名称</label>
                                <input class="form-control" type="text" placeholder="请输入案件名称" name="caseName" value="${query.caseName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件编号</label>
                                <input class="form-control" type="text" placeholder="请输入案件编号"  name="caseNo" value="${query.caseNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本名称</label>
                                <input class="form-control" type="text" placeholder="请输入样本名称" name="sampleName" value="${query.sampleName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本编号</label>
                                <input class="form-control" type="text" placeholder="请输入样本编号" name="sampleNo" value="${query.sampleNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本状态</label>
                                <input class="form-control" type="text" placeholder="请输入样本状态" name="status" value="${query.entity.status}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" type="submit" id="addLocalStorage">查询</button>
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
                        <th>样本名称</th>
                        <th>样本编号</th>
                        <th>板孔位置</th>
                        <th>状态</th>
                        <%--<th>--%>
                            <%--<label class="custom-control checkbox-inline">--%>
                                <%--<input class="custom-control-input" type="checkbox" name="checkAll">--%>
                                <%--<span class="custom-control-label" style="font-weight: 600">入库操作</span>--%>
                            <%--</label>--%>
                        <%--</th>--%>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${queueSampleList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.caseName}<input type="hidden" name="caseName" value="${list.caseName}"></td>
                            <td>${list.caseNo}<input type="hidden" name="caseNo" value="${list.caseNo}"></td>
                            <td>${list.sampleName}<input type="hidden" name="sampleName" value="${list.sampleName}"></td>
                            <td>${list.sampleNo}<input type="hidden" name="sampleNo" value="${list.sampleNo}"></td>
                            <%--<td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd '/><input type="hidden" name="createDatetime" value="${list.entity.createDatetime}"></td>--%>
                            <td>${list.boardNo}<input type="hidden" name="boardNo" value="${list.boardNo}"></td>
                            <td>${list.entity.status}<input type="hidden" name="status" value="${list.entity.status}"></td>
                            <td style="display: none;"> 
                                <input type="hidden" name="caseName" value="${list.caseName}">
                                <input type="hidden" name="caseNo" value="${list.caseNo}">
                                <input type="hidden" name="sampleName" value="${list.sampleName}">
                                <input type="hidden" name="sampleNo" value="${list.sampleNo}">
                                <input type="hidden" name="boardNo" value="${list.boardNo}">
                                <input type="hidden" name="status" value="${list.entity.status}">
                                <input type="hidden" id="id" name="id" value="${list.entity.id}">
                                <%--<button name="modifyBtn" type="button" class="btn-icon btn-icon-blue  btn-icon-bianji-blue edit">编辑</button>--%>
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
            pno: ${mainPageInfo.page},
            //总页码
            total: ${mainPageInfo.pageCount},
            //总数据条数
            totalRecords: ${mainPageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/localMonitoring',
            //链接尾部
            //hrefLatter: '.html',
            getLink: function (page) {
                return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#localStorageForm").serialize();
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

        $("#addLocalStorage").on("click", function () {
            $("#page").val(1);
            $('#localStorageForm').submit();
        });

        //重置
        $("button[name='reset']").on("click", function () {
            $("#localStorageForm").find("input[type='text']").val("");

            location.href = "<%=path%>/center/localMonitoring";
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

    })
</script>
</body>

</html>