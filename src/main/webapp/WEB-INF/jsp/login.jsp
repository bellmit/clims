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
    <link rel="shortcut icon" href="<%=path%>/img/favicon.ico" type="image/x-icon"/>
    <%@ include file="linkCss.jsp" %>
    <style>
        html,
        body {
            width: 100%;
            height: 100%;
        }

        body {
            background: url("<%=path%>/img/login-bg.png") no-repeat center;
            background-size: cover;
        }

        .loginBox {
            width: 537px;
            height: 630px;
            background-color: RGBA(1, 14, 39, .7);
            background-image: url("<%=path%>/img/login-border.png");
            background-position: center;
            background-repeat: no-repeat;
            position: absolute;
            left: 50%;
            margin-left: -268.5px;
            top: 50%;
            margin-top: -315px;
            border-radius: 15px;
            text-align: center;
        }

        .loginBox {
            padding: 20px 73px;
        }

        .loginBox .col-md-12:nth-child(1) {
            margin: 16px 0;
        }

        .loginBox p {
            color: #fff;
            margin: 0px;
        }

        .loginBox p:nth-child(1) {
            font-size: 22px;
            /* margin-bottom: 10px; */
        }

        .loginBox p:nth-child(2) {
            font-size: 9px;
            margin-bottom: 24px;
        }

        .textLeft {
            text-align: left
        }

        .textLeft label {
            color: #3193c9;
            font-weight: 400;
        }

        .checkbox-slider--default input + span:before {
            content: "";
            height: 16px;
            width: 30px;
            border-radius: 50px;
            background: #e8e8e8;
            box-shadow: none;
            transition: background .2s ease-out;
            outline: none;
        }

        .checkbox-slider--default input + span:after {
            height: 16px;
            width: 16px;
            border-radius: 50%;
            position: absolute;
            left: 0;
            top: 0;
            display: block;
            background: #b0b0b0;
            transition: margin-left .1s ease-in-out;
            text-align: center;
            font-weight: 700;
            content: "";
        }

        .checkbox-slider--a-rounded input:checked + span:after,
        .checkbox-slider--default input:checked + span:after {
            background: #0598ff;
            border: 1px solid transparent;
            background-clip: content-box;
        }

        .checkbox-slider input:checked + span:after,
        .checkbox-slider--default input:checked + span:after {
            margin-left: 14px;
            content: "";
        }

        .checkbox-slider input[type="checkbox"]:focus + *:before,
        .checkbox-slider--default input[type="checkbox"]:focus + *:before,
        .checkbox-toggle input[type="checkbox"]:focus + *:before {
            outline: none;
        }

        .checkbox-slider--default input + span {
            padding-left: 20px;
        }

        label span {
            color: #b8c2cc
        }

        .checkbox,
        .radio {
            position: relative;
            display: block;
            margin-top: 0px;
            margin-bottom: 19px;
        }

        .col-md-7:nth-child(7) {
            color: red;
            text-align: right;
            /*display: none;*/
        }

        .col-md-12:nth-child(8) button {
            background: linear-gradient(to right, #0280e3, #0ffbf9);
            color: #fff;
            border: none;
        }

        .btn.active,
        .btn:active {
            background: linear-gradient(to right, #0280e3, #0ffbf9);
            outline: 0;
            -webkit-box-shadow: none;
            box-shadow: none;
        }

        .col-md-12:nth-child(9) {
            margin-top: 18px;
        }

        .col-md-12 > span {
            color: #fff;
            display: block;
        }

        .col-md-12 > span + span {
            margin-top: 5px;
            margin-bottom: 20px;
        }

        .col-md-12:nth-child(11) {
            margin-top: 16px;
            color: #d3a814
        }

        .col-md-12:nth-child(11) a {
            color: #d3a814
        }

        .linkage {
            position: absolute;
            z-index: 2;
            background: #fff;
            width: 50%;
            top: 36px;
            max-height: 210px;
            overflow-y: auto;
        }

        .linkage li {
            height: 44px;
            line-height: 44px;
            text-align: center;
            cursor: pointer;
        }

        .linkage li:hover,
        .linkage li.active {
            color: #296fff;
            background: #f2f6ff
        }

        .linkage:nth-child(2) {
            border-radius: 5px 0 0 5px;
        }

        .linkage:nth-child(3) {
            left: 50%;
            border-radius: 0 5px 5px 0;
        }

        .pop {
            width: 100%;
            height: 100%;
            position: absolute;
            top: 0px;
            z-index: 1
        }

        .welcomTxt {
            width: 537px;
            height: 630px;
            position: absolute;
            left: 50%;
            margin-left: -268.5px;
            top: 50%;
            margin-top: -380px;
            border-radius: 15px;
            text-align: center;
        }

        .loginBox > div:nth-child(1) {
            float: left;
            margin-top: -59px;
            width: 100%;
            font-weight: 600;
            font-size: 16px;
            color: #FFFF00;
        }
    </style>
</head>

<body>
<form action="<%=path%>/loginUser" method="post">
    <input type="hidden" name="areaCode" value="${areaCode}">
    <%--<div  class="welcomTxt" style="padding: 10px 73px;"><span style="color: #FFFF00;font-size: 20px;font-weight: bold">您好,欢迎来到${labName}实验室</span></div>--%>
    <div class="loginBox">
        <div class="row">
            <div class="col-md12" style="color: #FFFF00">
                您好,欢迎来到${labName}实验室
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <img src="<%=path%>/img/logo.png" alt="">
            </div>
            <div class="col-md-12">
                <p>北京市公安局法医鉴定案件受理系统</p>
                <p>Forensic Laboratory Information Management System of Beijing Public Security Bureau</p>
            </div>
            <div class="col-md-12">
            </div>
            <div class="col-md-12 ">
                <div class="form-group textLeft">
                    <label class="hidden">用户名</label>
                    <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                </div>
            </div>
            <div class="col-md-12 textLeft">
                <div class="form-group textLeft">
                    <label class="hidden">密码</label>
                    <input type="password" class="form-control" name="password" placeholder="请输入密码">
                </div>
            </div>
            <div class="col-md-5 textLeft">
                <div class="checkbox checkbox-slider--default">
                    <label>
                        <input type="checkbox" name="rememberMe" id="rememberMe" checked><span>记住密码</span>
                    </label>
                </div>
            </div>
            <div class="col-md-7 textLeft">
                ${meg}
            </div>
            <div class="col-md-12">
                <button type="submit" class="btn btn-lg btn-block">登录</button>
            </div>
            <div class="col-md-12">
                <span>北京博安智联科技有限公司</span>
                <span>公司电话:400-011-5530</span>
            </div>
            <div class="col-md-12">
                <img src="<%=path%>/img/logo-w.png" alt="">
            </div>
            <div class="col-md-12">
                <i>下载谷歌浏览器 :</i>
                <a href="<%=path%>/chromeForXp">Windows XP版</a>
                <a href="<%=path%>/chromeForWin7">Win7/8/10</a>
            </div>
        </div>
    </div>
</form>

<div class="pop hidden">

</div>
<%@ include file="linkJs.jsp" %>
<script>
    //    $(".form-control").focus(function() {
    //        $(this).parents(".form-group").find("label").removeClass("hidden")
    //    })
    //    $(".form-control").blur(function() {
    //        $(this).parents(".form-group").find("label").addClass("hidden")
    //    })
    //    $(".select").find("input").focus(function() {
    //        $(".select").find("input").val("")
    //        $(this).next().removeClass('hidden')
    //        $(".pop").removeClass("hidden")
    //    })
    //    $(".linkage").eq(0).find("li").click(function() {
    //        $(".select").find("input").val($(this).html())
    //        $(".linkage").addClass("hidden")
    //    })
    //    $(".pop").click(function() {
    //        $(".linkage").each(function() {
    //            if (!$(this).hasClass("hidden")) {
    //                $(this).addClass("hidden")
    //            }
    //        })
    //        $(this).addClass("hidden")
    //    })
</script>
</body>

</html>
