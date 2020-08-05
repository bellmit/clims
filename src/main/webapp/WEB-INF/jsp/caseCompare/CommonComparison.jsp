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

     /*   .bgTR tr:nth-last-child(1) td, .bgTR tr:last-child td{
            color: #e28005;
            font-size: 16px;
            font-weight: 600;
            background: #fff;
        }*/
        #tbodsd tr td:first-child{
            width: 60px;
        }
        #tbodsd tr td:first-child+td,
        #tbodsd tr td:first-child+td+td{
            width: 140px;
        }
        #tbodsd tr td:last-child{
            vertical-align: middle;
        }
    </style>
</head>
<body>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>案件列表</div>
            </div>
            <div class="panel-body">
               <%-- <a href="javascript:history.go(-1);">返回</a>--%>
                <div class="col-md-6" >
                    <table class="table table-bordered" id="tbodsd">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>基因座</th>
                            <th>等位基因</th>
                        </tr>
                        </thead>
                        <tbody id="viewMatchTBody" class="bgTR">
                            <c:forEach items="${result}" var="list" varStatus="s">
                                <tr id="row" class="">
                                    <td>${s.count}</td>
                                    <td>${list.markerName}</td>
                                    <td>${list.allele}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>


               <div class="col-md-6" >
                   <table class="table table-bordered">
                       <thead>
                       <tr>
                           <th>基因图谱</th>
                       </tr>
                       </thead>
                       <tbody class="bgTR">
                           <tr>
                                   <td><img id="viewPhoto" src=""></td>
                           </tr>
                       </tbody>
                   </table>
               </div>
            </div>
        </div>
        <input type="hidden" name="imagePath" value="${imagePath}">
        <a href="javascript:history.go(-1);" style="float: right;margin-right: 10px;padding-bottom: 10px;"><button type="button" class="btn btn-blue-border btn-lang" name="return">返回</button></a>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
       /*var tdLength = $('#viewMatchTBody tr').length
        var tdImg = $('#viewMatchTBody tr').first().find("td").last()
        tdImg.prop('rowspan',tdLength)*/

        var imagePath = $("input[name='imagePath']").val();
        var url="";
        if(imagePath == null||imagePath==""){
            url="../images/null.png";
            $("#viewPhoto").css("width","40%");
        }else{
            url = "<%=path%>/dowmFileController/viewGenePhoto?imagePath=" + encodeURI(encodeURI(imagePath));
        }
        $("#viewPhoto").attr("src", url);
    })
</script>

</body>
</html>