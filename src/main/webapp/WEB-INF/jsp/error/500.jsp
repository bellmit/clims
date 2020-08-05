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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        body {
            padding: 15px;
        }

        body > div {
            height: 100%;
            background: #fff;
        }
        body > div .row{
            position: absolute;
            left: 50%;
            top: 40%;
            transform: translateX(-50%)translateY(-50%);
        }
        body > div  .col-md-6:nth-child(1) {
            text-align: right;
        }
        body > div  .col-md-6:nth-child(2) {
            margin-top: 55px;
            padding-left: 30px;
        }

        body > div  .col-md-6:nth-child(2) p:nth-child(1) {
            font-size: 56px;
            font-weight: 600;
            color: #bebebe;
        }
        body > div  .col-md-6:nth-child(2) p:nth-child(2) {
            font-size: 23px;
            color:#d5d5d5 ;
        }
        body > div  .col-md-6:nth-child(2) button{
            margin-top: 15px;
        }
    </style>
</head>
<body>
<div>
   <div class="row">
       <div class="col-md-6">
           <img src="<%=path%>/img/500.png" alt=""/>
       </div>
       <div class="col-md-6">
           <p>500</p>
           <p>抱歉,服务器出差了。</p>
           <button class="btn-blue btn-lang">返回</button>
       </div>
   </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $("button").click(function(){
        history.back()
    })
</script>
</body>
</html>