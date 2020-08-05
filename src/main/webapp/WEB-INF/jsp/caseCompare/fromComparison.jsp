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
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>

        .leftBox {
            width: 350px;
            height: 100%;
            position: absolute;
            padding: 15px 0;
            z-index: 1;
        }

        .rightBox {
            width: 100%;
            height: 100%;
            padding: 15px 0;
            padding-left: 360px;
        }

        .rightBox-top {
            width: 100%;
            height: 215px;
            border-radius: 5px;
            position: absolute;
            top: 15px;
            left: 0px;
            padding-left: 370px;
            padding-right: 10px;
        }

        .rightBox-bottom {
            width: 100%;
            height: 100%;
            padding-top: 225px;
            overflow-y: auto;
        }

        .leftBox > div,
        .rightBox-bottom > div,
        .rightBox-top > div {
            width: 100%;
            height: 100%;
            background: #fff;
            border-radius: 5px;
        }

        .leftBox > div {
            padding: 0px 15px;
            padding-top: 50px;
        }

        .leftBox > div p {
            color: #0c81f5;
            font-weight: 600;
            font-size: 16px;
            margin-bottom: 23px;
            position: absolute;
            top: 30px;
        }

        .leftBox > div ul {
            padding-left: 25px;
            height: 100%;
            position: relative;
            overflow-y: auto;
        }

        .leftBox > div ul::before {
            content: "";
            position: absolute;
            height: 99%;
            width: 2px;
            background: #e0e0e0;
            left: 10px;
            top: 3px;
        }

        .leftBox > div ul li {
            color: #000;
            position: relative;
        }

        .leftBox > div ul li + li {
            margin-top: 15px;
        }

        .leftBox > div ul li.red {
            color: #ff5a56;
            margin-top: 30px;
        }

        .leftBox > div ul li.red::before {
            content: "";
            position: absolute;
            width: 12px;
            height: 12px;
            background: #e0e0e0;
            border-radius: 50%;
            left: -20px;
            top: 50%;
            margin-top: -7px;
        }

        .leftBox > div ul li:nth-child(1) {
            margin-top: 0px;
        }

        .rightBox-top > div > div:nth-child(1) {
            text-align: center;
            border-bottom: 1px solid #efefef;
            padding: 15px 0;
        }

        .rightBox-top > div > div:nth-child(2) {
            padding: 15px 50px;
        }

        .rightBox-bottom > div {
            position: relative;
        }

        .rightBox-bottom > div > div:nth-child(1) {
            padding: 15px 50px;
            position: absolute;
            left: 0px;
            top: 0px;
            width: 100%;
            background: #fff;
            z-index: 1;
        }

        .rightBox-bottom > div > div:nth-child(2) {
            height: 100%;
            padding-top: 69px;
            padding-bottom: 119px;
            background: #fff;
            overflow-y: auto;
        }

        .rightBox-bottom > div > div:nth-child(2) .table {
            height: 100%;
            width: 100%;

        }


        .rightBox-bottom > div > div:nth-child(3) {
            padding: 15px 50px;
            position: absolute;
            left: 0px;
            bottom: 0px;
            width: 100%;
            background: #fff;
        }

        .rightBox-bottom > div > div:nth-child(3) div > .form-group + .form-group {
            margin-left: 63px;
        }
        /*表格样式*/
        table{
            border-collapse:collapse !important;
        }
        table tr td,table th{
            text-align: center;
        }
        thead tr{
            background: #298de5 !important;
            color: #fff;
            text-align: center;
        }
        thead tr th{
            vertical-align: middle !important;
        }

        .abnormal  {
            background: #ffd6d5;
        }

        tfoot{
            color: #e28005;
            font-size: 20px;
            font-weight: 600;
        }

        .bgTR tr:nth-last-child(1) td, .bgTR tr:last-child td{
            color: #e28005;
            font-size: 16px;
            font-weight: 600;
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
            text-align: right;
        }
    </style>
</head>
<body>
<!--startprint1-->
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>案件列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-bordered ">
                    <thead>
                    <tr>
                        <th rowspan="2">序号</th>
                        <th rowspan="2">基因座</th>
                        <th colspan="2">等位基因</th>
                        <th rowspan="2">LR</th>
                    </tr>
                    <tr>
                        <td>${compareSameResult.referenceId}</td>
                        <td>${compareSameResult.sampleNo}</td>
                    </tr>
                    </thead>
                    <tbody id="viewMatchTBody" class="bgTR">
                   <c:forEach items="${mapList.resultList}" var="list" varStatus="s">
                        <tr id="row" class="${list.type}">
                            <td>${s.count}</td>
                            <td>${list.markerName}</td>
                            <td>${list.geneStr1}</td>
                            <td>${list.geneStr2}</td>

                            <c:if test="${list.alleleFreqs != null && list.alleleFreqs == 'NaN'}">
                              <td>-- --</td>
                            </c:if>
                            <c:if test="${list.alleleFreqs != null && list.alleleFreqs != 'NaN'}">
                                <td>${list.alleleFreqs}</td>
                            </c:if>
                        </tr>
                    </c:forEach>

                   <tr>
                       <td>合计</td>
                       <td>比中基因座数: <c:if test="${not empty mapList.sameCount}"> ${mapList.sameCount-1}</c:if> </td>
                       <td></td>
                       <td></td>
                       <td>LR  ${probability}  </td>

                   </tr>

                    </tbody>
                </table>

            </div>
        </div>
        <a href="javascript:history.go(-1);" style="float: right;margin-right: 10px;padding-bottom: 10px;"><button type="button" class="btn btn-blue-border btn-lang" name="return">返回</button></a>
        <input class="btn btn-blue" type=button name='button_export' title='打印1' onclick=preview(1) value="打印" style="float: right;padding: 10px 42px; margin-right: 10px;">
    </div>
</div>
<!--endprint1-->
<%@ include file="../linkJs.jsp" %>
<script>
    function preview(oper)
    {
        if (oper < 10){
            bdhtml=window.document.body.innerHTML;//获取当前页的html代码
            sprnstr="<!--startprint"+oper+"-->";//设置打印开始区域
            eprnstr="<!--endprint"+oper+"-->";//设置打印结束区域
            prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html
            prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
            window.document.body.innerHTML=prnhtml;
            window.print();
            window.document.body.innerHTML=bdhtml;
        } else {
            window.print();
        }
    }
</script>
</body>
</html>