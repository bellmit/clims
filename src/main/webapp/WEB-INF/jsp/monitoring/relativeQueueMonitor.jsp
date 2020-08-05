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
    <title>同一比对队列监控</title>
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
                <form id="stateStorageForm" action="<%=path%>/center/RelativeQueueMonitor" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检材名称</label>
                                <input class="form-control" type="text" placeholder="请输入检材名称" name="sampleName" value="${query.sampleName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检材编号</label>
                                <input class="form-control" type="text" placeholder="请输入检材编号"  name="sampleNo" value="${query.sampleNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
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
                        <th>同组检材A名称</th>
                        <th>同组检材B名称</th>
                        <th>同组检材A编号</th>
                        <th>同组检材B编号</th>
                        <th>比中状态</th>
                    </tr>
                    </thead>
                    <tbody id="listTbody">
                    <c:forEach items="${queueVoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.sampleAName}<input type="hidden" name="sampleAName" value="${list.sampleAName}"></td>
                            <td>${list.sampleANo}<input type="hidden" name="sampleANo" value="${list.sampleANo}"></td>
                            <td>${list.sampleBName}<input type="hidden" name="sampleBName" value="${list.sampleBName}"></td>
                            <td>${list.sampleBNo}<input type="hidden" name="sampleBNo" value="${list.sampleBNo}"></td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.entity.compareStatus eq 1}">已比对</c:when>
                                    <c:otherwise>未比对</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
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
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/RelativeQueueMonitor',
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

        $("#addStsteStorage").on("click", function () {
            $("#page").val(1);
            $('#localStorageForm').submit();
        });

        //重置
        $("button[name='reset']").on("click", function () {
            $("#stateStorageForm").find("input[type='text']").val("");

            location.href = "<%=path%>/center/RelativeQueueMonitor";
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