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

        body > div .row {
            position: absolute;
            left: 50%;
            top: 40%;
            transform: translateX(-50%) translateY(-50%);
        }

        body > div .row .col-md-12 {
            text-align: center;
        }

        body > div .row .col-md-12 p {
            font-size: 23px;
            color: #d5d5d5;
            margin: 15px 0;
        }

        body > div .row .col-md-12 button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<div>
    <div class="row">
        <div class="col-md-12">
            <img src="<%=path%>/img/404.png" alt=""/>
            <p>抱歉,您访问的页面不存在。</p>
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