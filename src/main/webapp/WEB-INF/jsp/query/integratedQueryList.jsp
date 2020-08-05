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
    <title>北京市公安局法医鉴定案件委托系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .searchBox {
            padding: 15px 0;
        }

        .searchBox .form-group {
            position: relative;
        }

        .searchBox button {
            position: absolute;
            right: 0px;
            top: 0px;
        }

        .searchBox ul li {
            float: left;
            color: #999999;
        }

        .searchBox ul li:nth-child(1) {
            color: #3e8af9
        }

        .searchBox ul li + li {
            margin-left: 30px;
        }

        .caseOverview {
            padding: 15px 52px;
        }

        .caseOverview {
            padding: 15px 52px;
        }

        .caseOverview li {
            background: #f8f8f8;
            margin-top: 10px;
            border-radius: 10px
        }

        .caseOverview li:hover {
            background: #fff;
            box-shadow: 0px 0px 10px 5px #f8f8f8;
        }

        .caseOverview li a {
            width: 100%;
            height: 80px;
            display: block;
            padding: 0 73px;
        }

        .caseOverview li:hover a p:nth-child(1) {
            border-bottom: 2px dashed #f8f8f8;
        }

        .caseOverview li a p:nth-child(2) {
            height: 50%;
            color: #2187f5;
            margin: 0px;
            border-bottom: 2px dashed #fff;
            line-height: 40px;
            font-weight: 600;
        }
        .caseOverview li:hover button:nth-child(1){
            background: #fff;
            color: #ffb802;
        }
        .caseOverview li a button:nth-child(1) {
            margin-right: -65px;
            margin-top: 4px;
        }

        .caseOverview li a div.col-md-4 {
            line-height: 40px;
            padding-left: 0px;
        }

        .caseOverview li a div.col-md-4 label:nth-child(1) {
            color: #7c7c7c;
            font-weight: 400
        }

        .caseOverview li a div.col-md-4 label:nth-child(2) {
            color: #000;
            font-weight: 400
        }

        .caseOverview li a div.col-md-4:nth-child(1) label:nth-child(1)::before {
            content: "";
            width: 12px;
            height: 12px;
            background: #ff7a75;
            display: inline-block;
            border-radius: 50%;
            margin-right: 15px;
        }

        .caseOverview li a div.col-md-4:nth-child(2) label:nth-child(1)::before {
            content: "";
            width: 12px;
            height: 12px;
            background: #4098fa;
            display: inline-block;
            border-radius: 50%;
            margin-right: 15px;
        }

        .caseOverview li a div.col-md-4:nth-child(3) label:nth-child(1)::before {
            content: "";
            width: 12px;
            height: 12px;
            background: #00b39b;
            display: inline-block;
            border-radius: 50%;
            margin-right: 15px;
        }
    </style>
</head>

<body>
<ol class="breadcrumb">
    <li><a href="javascript:void(0);" id="pageHome">首页</a></li>
    <li class="active">综合查询</li>
</ol>

<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-6 col-md-offset-3 searchBox">
                        <div class="form-group ">
                            <form action="<%=path%>/integratedQuery/search" method="post" id="queryForm">
                                <input type="search" class="form-control" placeholder="请输入关键词" name="text"
                                       value="${text}">
                                <button type="submit" class="btn btn-blue">搜索</button>
                            </form>
                        </div>
                        <ul>
                            <li>关键词推荐</li>
                            <li>案件受理号</li>
                            <li>案件名称</li>
                            <li>现堪编号</li>
                            <li>鉴定要求</li>
                            <li>检验状态</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular caseOverview">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>关联案件列表</div>
            </div>
            <table class="table table-hover table-bordered bigTable">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>案件编号</th>
                    <th>案件名称</th>
                    <th>案件性质</th>
                    <th>案件现堪编号</th>
                    <th>检验状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody id="integratedQueryTbody">
                <c:forEach items="${documentList}" var="document" varStatus="s">
                    <tr>
                        <td>${s.count}</td>
                        <td>${document.case_no}</td>
                        <td>${document.case_name}</td>
                        <td>${document.case_property}</td>
                        <td>${document.case_xk_no}</td>
                        <td>${document.case_status}</td>
                        <td>
                            <a href="<%=path%>/integratedQuery/queryDetail?caseId=${document.case_id}&text=${text}&consignmentId=${document.consignment_id}&caseXkNo=${document.case_xk_no}"/>
                            <button class="btn btn-yellow btn-sm pull-left">查看详情</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-md-12">
        <div id="kkpager"></div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        $("#pageHome").on("click",function () {
            window.location="<%=path%>/main/home";
            parent.$("#bs-example-navbar-collapse-1").children().eq(1).children().eq(0).addClass("active").siblings().removeClass("active")
        });

        $(".replacementTableShow").click(function () {
            $(this).siblings(".replacementTable").slideToggle("slow")
        })
        $("select[name='acceptingState']").change(function () {
            if ($(this).val() == "已受理") {
                $(".notAccepted").addClass("hidden")
                $(".Accepted").removeClass("hidden")
            } else {
                $(".notAccepted").removeClass("hidden")
                $(".Accepted").addClass("hidden")
            }
        })
        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/integratedQuery/search',
            getLink: function (page) {
                return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#queryForm").serialize();
            }
        });


    })
</script>
</body>

</html>