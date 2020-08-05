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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .panel-body>span{
            color: #f65c52;
        }
        /*tbody tr td:nth-last-child(1){*/
        /*padding: 0px !important;*/
        /*}*/
        td .btn{
            height: 100%;
            width: 33.333%;
            margin: 0px !important;
            float: left;
            border-radius: 0px;
            background: #fff;
        }
        td .btn:nth-child(1){
            color:#296fff ;
        }
        td .btn:nth-child(1):hover,
        td .btn:nth-child(1):active,
        td .btn:nth-child(1):focus{
            background: #d6ebff;
        }
        td .btn:nth-child(2){
            color:#f65c52 ;
        }
        td .btn:nth-child(2):hover,
        td .btn:nth-child(2):active,
        td .btn:nth-child(2):focus{
            background: #ffe4e3;
        }
        td .btn:nth-child(3){
            color:#ffb802 ;
        }
        td .btn:nth-child(3):hover,
        td .btn:nth-child(3):active,
        td .btn:nth-child(3):focus{
            background: #FFF2D9;
        }
        .conditionSubtitle{
            width: 80%;
            position: absolute;
            font-weight: normal !important;
        }
        .resultTable thead tr{
            background: #298de5 !important;
            color: #fff;
            text-align: center;
        }
        .resultTable thead tr th{
            text-align: center;
            vertical-align: middle;
        }
        .resultTable thead tr:last-child td:nth-child(3),.resultTable thead tr:last-child td:nth-child(3){
            background: #0074d8 !important;
        }
        table{
            border-collapse:collapse !important;
        }
        table tr td,table th{
            text-align: center;
        }
        table th{
            background: #298de5;
            color: #fff;
        }
   /*     .bgTR tr:nth-child(4n){
            background: #ffd6d5;
        }
        .bgTR tr:nth-child(4n+1){
            background: #e2efff;
        }
        .bgTR tr:nth-child(4n+2){
            background: #eee1ff;
        }
        .bgTR tr:nth-child(4n+3){
            background: #fff;
        }*/
        .abnormal  {
            background: #ffd6d5;
        }
        tfoot{
            font-size: 20px;
            font-weight: 600;
        }
        tfoot tr{
            background: rgb(240, 240, 240);
        }
        .footGo{
            width: 100%;
            height: 62px;
            position: fixed;
            bottom: 0;
            left: 0;
            background: #fff;
            box-shadow: -6px 0 6px 10px #eaeaea;
        }
        .footGo a{
            width: 100px;
            height: 42px;
            line-height: 42px;
            background: #0c81f5;
            display: inline-block;
            text-align: center;
            position: absolute;
            right: 2%;
            top: 50%;
            margin-top: -22px;
            border-radius: 4px;
            color: #fff;
            font-size: 16px;
            font-weight: 600;
        }
        .seachButtonBox {
            height: 34px;
            line-height: 34px;
        }
        .seachInputBox {
            line-height: 34px;
        }
    </style>
</head>
<body>
<!--startprint1-->
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>比中情况</div>
                <div class="row conditionSubtitle">
                    <div class="col-md-2">
                        审核人:
                        <span>${matchRelativeResult.updatePerson}</span>
                    </div>
                    <div class="col-md-2">
                        审核状态:
                        <c:if test="${matchRelativeResult.compareStatus == '0'}">
                            <span>待复核</span>
                        </c:if>
                        <c:if test="${matchRelativeResult.compareStatus == '1'}">
                            <span>复核</span>
                        </c:if>
                        <c:if test="${matchRelativeResult.compareStatus == '2'}">
                            <span>确认比中</span>
                        </c:if>
                        <c:if test="${matchRelativeResult.compareStatus == '3'}">
                            <span>解除关联</span>
                        </c:if>
                    </div>
                    <div class="col-md-2">
                        审核时间:
                        <span><fmt:formatDate value='${matchRelativeResult.updateDatetime}' pattern='yyyy-MM-dd '/></span>
                    </div>
                    <div class="col-md-2">
                        比中时间:
                        <span><fmt:formatDate value='${matchRelativeResult.createDatetime}' pattern='yyyy-MM-dd '/></span>
                    </div>
                </div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                        <tr>
                            <th>序号</th>
                            <th>样本类型</th>
                            <th>检材名称</th>
                            <th>案件名称</th>
                            <th>提交人</th>
                            <th>Panel</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${matchRelativeResult != null}">
                            <tr>
                                <td>0</td>
                                <td>${matchRelativeResult.sampleaType}</td>
                                <td>${matchRelativeResult.sampleaName}</td>
                                <td>${matchRelativeResult.caseaName}</td>
                                <td>${matchRelativeResult.acceptAName}</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>${matchRelativeResult.samplebType}</td>
                                <td>${matchRelativeResult.samplecName}</td>
                                <td>${matchRelativeResult.casebName}</td>
                                <td>${matchRelativeResult.acceptBName}</td>
                                <td></td>
                            </tr>
                        </c:if>
                        <c:if test="${matchRelativeResult == null}">
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
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
                        <th colspan="3">等位基因</th>
                        <th rowspan="2">PI值</th>
                        <th rowspan="2">父</th>
                        <th rowspan="2">母</th>
                        <th rowspan="2">其他</th>
                    </tr>
                    <tr>
                        <th style='width:15%'><c:if test="${not empty fatherSampleNo}">${fatherSampleNo}(父)</c:if></th>
                        <th style='width:15%'><c:if test="${not empty motherSampleNo}">${motherSampleNo}(母)</c:if></th>
                        <th style='width:15%'><c:if test="${not empty childSampleNo}">(A)${childSampleNo}(子)</c:if></th>
                    </tr>
                    </thead>
                    <tbody id="viewMatchTBody" class="bgTR">
                    <c:forEach items="${groupList}" var="list" varStatus="s">
                        <tr id="row" class="${list.type}">
                            <td>${s.count}</td>
                            <td>${list.markerName}</td>
                            <td class="fGene">${list.fGene}</td>
                            <td class="mGene">${list.mGene}</td>
                            <td class="cGene">${list.cGene}</td>
                            <td>${list.cPiStr}</td>
                            <td>${list.fPiStr}</td>
                            <td>${list.mPiStr}</td>
                            <td></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">pi值合计</td>
                        <td>累积PI值合计：${totalPiStr}</td>
                        <td>父累积PI值合计：${fTotalPiStr}</td>
                        <td>母累积PI值合计：${mTotalPiStr}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="5">匹配数合计</td>
                        <td>总匹配数：${cMatchCount}</td>
                        <td>父匹配数：${fMatchCount}</td>
                        <td>母匹配数：${mMatchCount}</td>
                        <td></td>
                    </tr>
                    </tbody>
                </table>
                <!--endprint1-->
            </div>
        </div>
        <a href="javascript:history.go(-1);" style="float: right;margin-right: 10px;padding-bottom: 10px;"><button type="button" class="btn btn-blue-border btn-lang" name="return">返回</button></a>
    </div>
</div>
<div class="row footGo">
    <div class="col-md-2 seachInputBox DYnone"  style="float: right;padding-top: 10px;">
        <div class="form-group seachButtonBox">
            <input class="btn btn-red" style="width: 100px;height: 47px;" type=button name='button_export' title='打印1' onclick=preview(1)
                   value="打印">
        </div>
    </div>
    <a href="javascript:history.go(-1)">返回上一页</a>
</div>
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/entrustCurrency.js"></script>
</body>
<script>

    function preview(oper) {
        $(".DYnone").css("display", "none");
        if (oper < 10) {
            bdhtml = window.document.body.innerHTML;//获取当前页的html代码
            sprnstr = "<!--startprint" + oper + "-->";//设置打印开始区域
            console.log(sprnstr)
            eprnstr = "<!--endprint" + oper + "-->";//设置打印结束区域
            prnhtml = bdhtml.substring(bdhtml.indexOf(sprnstr) + 18); //从开始代码向后取html
            prnhtml = prnhtml.substring(0, prnhtml.indexOf(eprnstr));//从结束代码向前取html
            window.document.body.innerHTML = prnhtml;
            window.print();
            window.document.body.innerHTML = bdhtml;
            $(".DYnone").css("display", "block");
        } else {
            window.print();
            $(".DYnone").css("display", "block");
        }
    }

</script>
</html>