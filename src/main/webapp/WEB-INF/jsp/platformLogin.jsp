<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定综合管理平台</title>
    <link rel="shortcut icon" href="<%=path%>/img/favicon.ico" type="image/x-icon"/>
    <%@ include file="linkCss.jsp" %>
    <style>
        body {
            background: url("<%=path%>/img/platimg/totalLogin-bg.png") no-repeat center;
            background-size: cover;
            position: relative;
            padding: 0px 22px;
            padding-top: 13px;
        }

        body > div:nth-child(1) {
            background: url("<%=path%>/img/platimg/totalLogin-titBg.png") no-repeat;
            background-size: cover;
            display: inline-block;
            padding: 4px 57px;
            padding-top: 0px;
            border-radius: 50px;
            margin-left: 40px;
        }

        body > div:nth-child(1) > p {
            color: #fff;
            margin: 0px;
            margin-left: 45px;
        }

        body > div:nth-child(1) > img:nth-child(1) {
            float: left;
            width: 37px;
            margin-top: 3px;
        }

        body > div:nth-child(1) > p:nth-child(2) {
            font-size: 20px;
            letter-spacing: 5px;
            font-weight: 600;
        }

        body > div:nth-child(1) > p:nth-child(3) {
            font-size: 11px;
        }

        body > div:nth-child(2) {
            position: absolute;
            color: #fff;
            font-weight: 600;
        }

        body ul {
            position: fixed;
            bottom: 0px;
            width: 100%;
            padding: 0 22px;
            margin-left: -22px;
            margin-bottom: 0px;
            z-index: 5;
        }

        body ul li {
            float: left;
            color: #fff;
            width: 20%;
            text-align: center;
            height: 50px;
            line-height: 50px;
            cursor: pointer;
            border-top: 1px solid #025aa4;
            border-bottom: 1px solid #025aa4;
            background: url("<%=path%>/img/platimg/totalLogin-BottomBar.png") no-repeat center;
            background-size: cover;
        }

        body ul li a {
            color: #fff;
            display: block;
            height: 100%;
        }

        body ul li:nth-child(1) {
            border-left: 1px solid #025aa4;
        }

        body ul li:nth-last-child(1) {
            border-right: 1px solid #025aa4;
        }

        body ul li:nth-child(n+2)::before {
            content: "";
            display: inline-block;
            width: 2px;
            height: 30px;
            background: #16387c;
            float: left;
            margin-top: 12px;
        }

        body ul li:hover {
            background: url("<%=path%>/img/platimg/totalLogin-titBgHover.png") no-repeat center;
            background-size: cover;
        }

        body ul li:hover a {
            color: #0ff6f4;
            font-weight: 600;
        }

        body ul li.active {
            background: url("<%=path%>/img/platimg/totalLogin-titBgHover.png") no-repeat center;
            background-size: cover;
        }

        body ul li.active a {
            color: #0ff6f4;
            font-weight: 600;
        }

        .mapBox {
            height: 100%;
            width: 1400px;
            position: absolute;
            top: -4px;
            left: 50%;
            margin-left: -700px;
            padding-bottom: 58px;
            padding-top: 13px;
        }

        .map {
            width: 1070px;
            height: 100%;
            display: inline-block;
            position: relative;
            float: left;
        }

        .map img:nth-child(1) {
            position: absolute;
            top: 50%;
            left: 0px;
            transform: translateY(-45%)
        }

        .introduce {
            width: 325px;
            height: 100%;
            display: inline-block;
            background: url("<%=path%>/img/platimg/right-bg.png") no-repeat left;
            background-size: cover;
            border: 1px solid transparent;
            border-image: url("<%=path%>/img/platimg/border.png");
            border-image-slice: 1 1 1 1;
            border-image-outset: 0;
            border-image-repeat: stretch;
            overflow-y: auto;
        }

        .introduce .btn {
            background: linear-gradient(to right, #0280e3, #0ffbf9);
            color: #fff;
            border: none;
            border-radius: 0px;
        }

        .introduce > div {
            border-bottom: 2px solid;
            border-image: url('<%=path%>/img/platimg/border-bottom.png');
            border-image-slice: 2 2 2 2;
            border-image-outset: 0;
            border-image-repeat: stretch;
            padding: 15px 20px;
        }

        .laboratoryName {
            background: url("<%=path%>/img/platimg/totalLogin-titBg.png") no-repeat;
            background-size: cover;
            border-radius: 50px;
            color: #ffb72c;
            padding: 10px 0px;
            text-align: center;
            margin: 0 auto;
            font-weight: 600;
        }

        #backlogCharts2 {
            height: 100px;
            width: 100%;
            margin: 10px 0;
        }

        .rightTit {
            color: #fff;
            display: inline-block;
            margin-right: 10px;
            margin-bottom: 10px;
        }

        .rightTit::before {
            content: "";
            width: 4px;
            height: 18px;
            float: left;
            background: linear-gradient(to right, #0ffbf9, #0a57ea);
            display: inline-block;
            margin-top: 2px;
            margin-right: 10px;
        }

        .numBox {
            display: inline-block;
            width: 25px;
            height: 30px;
            background: linear-gradient(45deg, #0ffbf9, #0a57ea);
            color: #fff;
            font-weight: 600;
            line-height: 30px;
            text-align: center;
            margin-left: 4px;
            font-size: 18px;
        }

        .numBox.noNum {
            opacity: .4;
        }

        .wendu {
            color: #ffb72c;
            background: url(<%=path%>/img/platimg/wendu.png) no-repeat left;
            padding-left: 50px;
            display: inline-block;
            height: 40px;
            line-height: 40px;
            margin-left: 35px;
            font-weight: 600;
        }

        .shidu {
            color: #a43d4a;
            background: url(<%=path%>/img/platimg/shidu.png) no-repeat left;
            padding-left: 50px;
            display: inline-block;
            height: 40px;
            line-height: 40px;
            margin-left: 15px;
            font-weight: 600;
        }

        .shidu span,
        .wendu span {
            margin-left: 5px;
        }

        .yellowFont {
            color: #d49932;
            font-weight: 600;
        }

        .introduce img {
            border-radius: 50%;
            width: 42px;
            height: 42px;
        }

        .introduce > div:nth-child(5) {
            border: none;
        }

        .introduce > div:nth-child(5) > div {
            word-wrap: break-word;
            color: #fff;
            padding: 0 15px;
        }

        .changeImg,
        .laboratoryImg,
        .arrow {
            position: absolute;
            top: 50%;
            cursor: pointer
        }

        .arrow {
            position: absolute;
            pointer-events: none;
            top: 50%;
            cursor: pointer;
        }

        .laboratoryImg {
            width: 50px;
        }

        .haidian {
            width: 137px;
            transform: translate(244px, 59px);
            z-index: 2;
        }

        .haidianLaboratory {
            transform: translate(333px, 100px);
            z-index: 2;
        }

        .haidianArrow {
            width: 776px;
            transform: translate(288px, -215px);
            z-index: 3;
        }

        .fayiLaboratory {
            transform: translate(310px, 60px);
            z-index: 2;
        }

        .fayiArrow {
            width: 776px;
            transform: translate(267px, -233px);
            z-index: 3;
        }

        .changping {
            width: 186px;
            transform: translate(223px, -28px);
            z-index: 1;
        }

        .changpingArrow {
            width: 765px;
            transform: translate(302px, -300px);
            z-index: 3;
        }

        .xicheng {
            width: 79px;
            transform: translate(307px, 129px);
            z-index: 2;
        }

        .xichengLaboratory {
            transform: translate(355px, 146px);
            z-index: 2;
            width: 30px;
        }

        .xichengArrow {
            width: 745px;
            transform: translate(312px, -227px);
            z-index: 3;
        }

        .dongcheng {
            width: 75px;
            transform: translate(382px, 130px);
            z-index: 2;
        }

        .dongchengLaboratory {
            transform: translate(370px, 129px);
            z-index: 2;
            width: 30px;
        }

        .dongchengArrow {
            width: 687px;
            transform: translate(373px, -225px);
            z-index: 3;
        }

        .chaoyang {
            width: 99px;
            transform: translate(371px, 81px);
            z-index: 1;
        }

        .chaoyangArrow {
            width: 666px;
            transform: translate(392px, -240px);
            z-index: 3
        }

        .shijingshan {
            width: 87px;
            transform: translate(258px, 126px);
            z-index: 1;
        }

        .shijingshanLaboratory {
            transform: translate(318px, 129px);
            z-index: 3;
            width: 30px;
        }

        .shijingshanArrow {
            width: 776px;
            transform: translate(277px, -215px);
            z-index: 3;
        }

        .fengtai {
            width: 153px;
            transform: translate(264px, 151px);
            z-index: 1;
        }

        .fengtaiArrow {
            width: 776px;
            transform: translate(279px, -184px);
            z-index: 4;
        }

        .daxing {
            width: 144px;
            transform: translate(342px, 179px);
            z-index: 1;
        }

        .daxingArrow {
            width: 702px;
            transform: translate(358px, -134px);
            z-index: 3;
        }
        .tongzhou{
            width: 120px;
            transform: translate(426px, 102px)
        }
        .tongzhouArrow{
            width: 625px;
            transform: translate(442px, -168px);
            z-index: 3;
        }
        .shunyi{
            width: 144px;
            transform: translate(402px, -5px);
        }
        .shunyiArrow{
            width: 640px;
            transform: translate(429px, -318px);
            z-index: 1;
        }
        .bigImg {
            position: absolute;
            top: 50%;
            right: 0px;
            transform: translate(-45%, 34%);
            width: 250px;
        }

        .map span {
            position: absolute;
            top: 50%;
            right: 0px;
            transform: translate(-45%, 34%);
            width: 250px;
            color: #fff;
        }
    </style>
</head>

<body>
<div>
    <img src="<%=path%>/img/platimg/logo.png" alt=""/>

    <p>北京市公安局法医鉴定综合管理平台</p>

    <p>Beijing City Public Security Bureau forensic management platform</p>
</div>
<ul>
    <li><a href="javaScript:;">数据可视化</a></li>
    <li><a href="${commissionSystemUrL}">委托登记</a></li>
    <li class="active"><a href="javaScript:;">实验室</a></li>
    <li><a href="${commissionAssesUrL}">绩效考核</a></li>
    <li><a href="javaScript:;">更多</a></li>
</ul>
<div class="mapBox">
    <div class="map">
        <img src="<%=path%>/img/platimg/map.png" alt=""/>
        <img src="<%=path%>/img/platimg/haidianArrow.png" class="haidianArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/haidianActiveF.png" class="haidian changeImg" alt="海淀"/>
        <img src="<%=path%>/img/platimg/laboratoryIcon.png" class="laboratoryImg haidianLaboratory" alt="海淀"
             areaCode="110108000000"/>
        <img src="<%=path%>/img/platimg/fayiArrow.png" class="fayiArrow arrow"/>
        <img src="<%=path%>/img/platimg/laboratoryIcon.png" class="laboratoryImg fayiLaboratory" alt="法医"
             areaCode="110023000004"/>
        <img src="<%=path%>/img/platimg/changping.png" class="laboratoryImg changping changeImg" alt="昌平"
             areaCode="110114000000"/>
        <img src="<%=path%>/img/platimg/changpingArrow.png" class="changpingArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/daxing.png" class="laboratoryImg daxing changeImg" alt="大兴"
             areaCode="110115000000"/>
        <img src="<%=path%>/img/platimg/daxingArrow.png" class="daxingArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/fengtai.png" class="laboratoryImg fengtai changeImg" alt="丰台"
             areaCode="110106000000"/>
        <img src="<%=path%>/img/platimg/fengtaiArrow.png" class="fengtaiArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/shijingshan.png" class="shijingshan changeImg" alt="石景山"/>
        <img src="<%=path%>/img/platimg/laboratoryIcon.png" class="laboratoryImg shijingshanLaboratory" alt="石景山"
             areaCode="110107000000"/>
        <img src="<%=path%>/img/platimg/shijingshanArrow.png" class="shijingshanArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/chaoyang.png" class="laboratoryImg chaoyang changeImg" alt="朝阳"
             areaCode="110105000000"/>
        <img src="<%=path%>/img/platimg/chaoyangArrow.png" class="chaoyangArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/xicheng.png" class="xicheng changeImg" alt="西城"/>
        <img src="<%=path%>/img/platimg/laboratoryIcon.png" class="laboratoryImg xichengLaboratory" alt="西城"
             areaCode="110102000000"/>
        <img src="<%=path%>/img/platimg/xichengArrow.png" class="xichengArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/dongcheng.png" class="dongcheng changeImg" alt="东城"/>
        <img src="<%=path%>/img/platimg/laboratoryIcon.png" class="laboratoryImg dongchengLaboratory" alt="东城"
             areaCode="110101000000"/>
        <img src="<%=path%>/img/platimg/dongchengArrow.png" class="dongchengArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/tongzhou.png" class="laboratoryImg tongzhou changeImg" alt="通州"
             areaCode="110112000000"/>
        <img src="<%=path%>/img/platimg/tongzhouArrow.png" class="tongzhouArrow arrow hidden"/>
        <img src="<%=path%>/img/platimg/shunyi.png" class="laboratoryImg shunyi changeImg" alt="顺义"
             areaCode="110113000000"/>
        <img src="<%=path%>/img/platimg/shunyiArrow.png" class="shunyiArrow arrow hidden"/>
        <span class="laboratoryName">局部放大图</span>
        <img src="<%=path%>/img/platimg/fayiBig.png" class="bigImg" alt=""/>
        <input type="hidden" id="areaCode" value="${laboratoryInfo.orgId}">
    </div>
    <div class="introduce">
        <a href="javaScript:void(0);" class="btn btn-lg btn-block" id="enterReceiveSystem">进入系统</a>
        <div>
            <div class="laboratoryName"><span class="labName">${laboratoryInfo.labName}</span>实验室</div>
            <div class="col-md-6" id="backlogCharts2"></div>
            <div>
                <span class="rightTit">案件完成总量</span>
                <div id="caseTotalStr" style="float: right">
                    <c:forEach items="${nocaseNum}" var="nocase" varStatus="s">
                        <span class="numBox noNum">${nocase}</span>
                    </c:forEach>

                    <c:forEach items="${caseNumber}" var="caseNb" varStatus="s1">
                        <span class="numBox">${caseNb}</span>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="laboratoryAttribute">
            <span class="rightTit">实验室温度和湿度</span>
            <div>
                <span class="shidu">湿度:<span id="labHum">${laboratoryInfo.labHumidity}</span></span>
                <span class="wendu">温度:<span id="labTemper">${laboratoryInfo.labTemperature}</span></span>
            </div>
        </div>
        <div>
            <div>
                <span class="rightTit">建设时间&nbsp&nbsp&nbsp</span>
                <span class="yellowFont" id="labSetType">${laboratoryInfo.labSetupTime}</span>
            </div>
            <div>
                <span class="rightTit">鉴定人数量</span>
                <span class="yellowFont" id="labPerson">${laboratoryInfo.labPersonnel}人</span>
            </div>
            <div>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
                <img src="<%=path%>/img/platimg/policeman.png" alt=""/>
            </div>
        </div>
        <div>
            <span class="rightTit yellowFont labName">${laboratoryInfo.labName}简介</span>
            <div>
                <span id="labIntroInfo">${laboratoryInfo.labIntroduction}</span>
                <button class="btn btn-xs">更多</button>
            </div>
        </div>
    </div>
</div>
<%--<div>--%>
<%--<div>--%>
<%--<a href="javaScript:void(0);" class="btn btn-lg btn-block" id="enterReceiveSystem">进入系统</a>--%>
<%--<div>--%>
<%--<div class="laboratoryName"><span id="labName">${laboratoryInfo.labName}</span>实验室</div>--%>
<%--<div class="col-md-6" id="backlogCharts2"></div>--%>
<%--<div>--%>
<%--<span class="rightTit">案件完成总量</span>--%>
<%--<div id="caseTotalStr" style="float: right">--%>
<%--<c:forEach items="${nocaseNum}" var="nocase" varStatus="s">--%>
<%--<span class="numBox noNum">${nocase}</span>--%>
<%--</c:forEach>--%>

<%--<c:forEach items="${caseNumber}" var="caseNb" varStatus="s1">--%>
<%--<span class="numBox">${caseNb}</span>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="laboratoryAttribute">--%>
<%--<span class="rightTit">实验室温度和湿度</span>--%>
<%--<div>--%>
<%--<span class="shidu">湿度: <span id="labHum">${laboratoryInfo.labHumidity}</span></span>--%>
<%--<span class="wendu">温度:<span id="labTemper">${laboratoryInfo.labTemperature}</span></span>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div>--%>
<%--<div>--%>
<%--<span class="rightTit">建设时间&nbsp&nbsp&nbsp</span>--%>
<%--<span class="yellowFont" id="labSetType">${laboratoryInfo.labSetupTime}</span>--%>
<%--</div>--%>
<%--<div>--%>
<%--<span class="rightTit">鉴定人数量</span>--%>
<%--<span class="yellowFont" id="labPerson">${laboratoryInfo.labPersonnel}人</span>--%>
<%--</div>--%>
<%--<div>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--<img src="<%=path%>/img/platimg/policeman.png" alt=""/>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div>--%>
<%--<span class="rightTit yellowFont">北京法医中心实验室简介</span>--%>

<%--<div>--%>
<%--<span id="labIntroInfo">${laboratoryInfo.labIntroduction}</span>--%>
<%--<button class="btn btn-xs">更多</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div>--%>
<%--<div>--%>

<%--</div>--%>
<%--<div>--%>
<%--<img src="<%=path%>/img/platimg/fayiBig.png" class="bigImg" alt=""/>--%>
<%--</div>--%>
<%--</div>--%>
</body>
<%@ include file="linkJs.jsp" %>
<script src="<%=path%>/js/platjs/platform.js"></script>
<script>
    var baseurl = "${ctx}";
    $(function () {
        $("ul").find("li").click(function () {
            $(this).addClass("active").siblings().removeClass("active")
        })

        $(".laboratoryImg").click(function () {
            var areaCode = parseInt($(this).attr("areaCode"));
            //给后台接收的区域字段赋值
            $("#areaCode").val(areaCode);
            //异步获取各区实验室的信息数据
            obtainAreaInfo(areaCode);
            var imgSrc = $(this).attr("src").substring(0, $(this).attr("src").indexOf("png") - 1)
            if (imgSrc.indexOf("Active") == -1) {
                if ($(this).attr("src").indexOf("laboratoryIcon") > 0 && $(this).attr("alt") == "法医" && !$(this).attr("active")) {
                    $(this).attr("active", "true").siblings("img").removeAttr("active")
                    imgSrc = $(".haidian").attr("src").substring(0, $(".haidian").attr("src").indexOf("haidian"))
                    $(".haidian").attr("src", imgSrc + "haidianActiveF.png")
                    $(".fayiArrow").removeClass("hidden").siblings(".arrow").addClass("hidden")
                    $(".bigImg").attr("src", imgSrc + "fayiBig.png").removeClass("hidden")
                } else if ($(this).attr("src").indexOf("laboratoryIcon") > 0 && $(this).attr("alt") == "海淀" && !$(this).attr("active")) {
                    $(this).attr("active", "true").siblings("img").removeAttr("active")
                    imgSrc = $(".haidian").attr("src").substring(0, $(".haidian").attr("src").indexOf("haidian"))
                    $(".haidian").attr("src", imgSrc + "haidianActiveH.png")
                    $(".haidianArrow").removeClass("hidden").siblings(".arrow").addClass("hidden")
                    $(".bigImg").attr("src", imgSrc + "haidianBig.png").removeClass("hidden")
                } else if ($(this).attr("src").indexOf("laboratoryIcon") > 0 && !$(this).attr("active")) {
                    $(this).attr("active", "true").siblings("img").removeAttr("active")
                    imgSrc = $(this).prev().attr("src").substring(0, $(this).prev().attr("src").indexOf("png") - 1)
                    $(this).prev().attr("src", imgSrc + "Active.png")
                    $(".bigImg").attr("src", imgSrc + "Big.png").removeClass("hidden")
                    $(this).next().removeClass("hidden").siblings(".arrow").addClass("hidden")
                } else if ($(this).attr("src").indexOf("laboratoryIcon") < 0 && !$(this).attr("active")) {
                    $(this).attr("active", "true").siblings("img").removeAttr("active")
                    $(this).attr("src", imgSrc + "Active.png")
                    $(".bigImg").attr("src", imgSrc + "Big.png").removeClass("hidden")
                    $(this).next().removeClass("hidden").siblings(".arrow").addClass("hidden")
                }
                if ($(this).attr("src").indexOf("laboratoryIcon") > 0) {
                    if ($(this).attr("alt") == "法医") {
                        var imgBox = $(".haidian");
                    } else {
                        var imgBox = $(this).prev();
                    }
                } else {
                    var imgBox = $(this);
                }
                imgBox.siblings(".changeImg").each(function () {
                    if ($(this).attr("src").indexOf("Active") > 0) {
                        imgSrc = $(this).attr("src").substring(0, $(this).attr("src").indexOf("Active"))
                        $(this).attr("src", imgSrc + ".png")
                    }
                })
            }
        });

        var backlogCharts2 = echarts.init(document.getElementById('backlogCharts2'));
        var backlogCharts2Option = {
            series: [{
                type: 'liquidFill',
                center: ['50%', '50%'],
                radius: '80%',
                data: [0.6, 0.5, 0.4],
                color: ['#c1dffd', '#a5d0fe', '#75b8fd'],
                itemStyle: {
                    normal: {
                        shadowBlur: 0,
                        opacity: 0.6
                    }
                },
                backgroundStyle: {
                    color: '#fff'
                },
                outline: {
                    borderDistance: 0,
                    itemStyle: {
                        borderWidth: 6,
                        borderColor: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0.42,
                            color: '#ededed'
                        }, {
                            offset: 0.42,
                            color: '#007ef9'
                        }]),
                        shadowBlur: 1,
                    }
                },
                label: {
                    normal: {
                        formatter: [
                            '{textBorder|' + 70 + '%}',
                        ].join('\n'),
                        rich: {
                            textBorder: {
                                fontSize: 18,
                                color: '#0065aa',
                                insideColor: '#0065aa',
                                fontWeight: "bold"
                            },
                        }
                    }
                }
            }]
        };
        backlogCharts2.setOption(backlogCharts2Option);

        var defaultAreaCode = $("#areaCode").val();
        $(".laboratoryImg").each(function (){
            if($(this).attr("areaCode") == defaultAreaCode){
                $(this).click();
                return false;
            }
        });

    });

    //异步获取各区实验室的信息数据
    function obtainAreaInfo(areaCode) {
        $.ajax({
            url: baseurl + "/loginLabInfo",
            type: "POST",
            data: {areaCode: areaCode},
            dataType: "json",
            async: false,
            success: function (data) {
                //给实验室信息赋值
                $(".labName").eq(0).text(data.labName);//名称
                $(".labName").eq(1).text(data.labName+"简介");//名称
                //案件总量
                var text = "";
                $.each(data.nocaseNumStr, function (i, item) {
                    text += '<span class="numBox noNum" style="margin-left: 8px">' + item + '</span>';
                });
                $.each(data.caseNumberStr, function (i, item) {
                    text += '<span class="numBox" style="margin-left: 8px">' + item + '</span>';
                });
                $("#caseTotalStr").children().remove();
                $("#caseTotalStr").append(text);

                $("#labHum").text(data.labHumidity);//湿度
                $("#labTemper").text(data.labTemperature);//温度
                $("#labSetType").text(data.labSetupTime);//建设时间
                $("#labPerson").text(data.labPersonnel);//人员
                $("#labIntroInfo").text(data.labIntroduction);//简介信息
            }, error: function () {
            }
        });
    }

</script>
</html>