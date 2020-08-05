<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>

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
        body {
            padding: 25px 30px;
        }

        .mainBox {
            height: 100%;
            width: 100%;
            background: #fff;
            border-radius: 5px;
            position: relative;
        }
        .mainBox>div{
            position: absolute;
            top: 40%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 100%;
        }
        .mainBox > div > .row:nth-child(1) {
            text-align: center;
        }

        .mainBox > div > .row:nth-child(1) i {
            color: #50c987;
            font-size: 55px;
            margin-bottom: 15px;
        }

        .mainBox > div > .row:nth-child(1) b {
            display: inline-block;
            border: 1px solid #fff;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 25px;
        }

        .mainBox > div > .row:nth-child(1) b:hover {
            border: 1px solid #63acf5;
        }

        .mainBox > div > .row:nth-child(1) b:hover span {
            color: #0c81f5;
        }

        .mainBox > div > .row:nth-child(1) p {
            color: #50c987;
            font-size: 20px;
        }

        .mainBox > div > .row:nth-child(1) > .col-md-12 {
            position: relative;
        }

        .mainBox > div > .row:nth-child(1) > .col-md-12 span {
            position: absolute;
            font-weight: 600;
            color: #6c6c6c;
            width: 114px;
            font-size: 17px;
            top: 75%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
        }

        .technologicalProcess {
            margin-top: 70px;
        }

        .technologicalProcess .col-md-3 {
            padding: 0 18px;
            width: 20%;
        }

        .technologicalProcess .col-md-3 > div {
            width: 100%;
            height: 54px;
            text-align: center;
            position: relative;
            border-radius: 5px;
        }

        .technologicalProcess .col-md-3 > div > div:nth-child(1) {
            position: absolute;
            width: 100%;
            top: 50%;
        }

        .technologicalProcess .col-md-3 > div i {
            display: block;
            font-size: 47px;
            color: #e0e0e0;
            margin-bottom: 20px;
        }

        .technologicalProcess .col-md-3 > div p {
            color: #b9b9b9;
            font-size: 13px;
            margin: 0px
        }

        .technologicalProcess .col-md-3 > div p:nth-child(2) {
            color: #b2b2b2;
        }

        .technologicalProcess .col-md-3 > .ongoing p:nth-child(1) {
            font-weight: 600;
            font-size: 16px;
        }

        .technologicalProcess .col-md-3 > .ongoing p {
            color: #00b39b !important;
        }

        .technologicalProcess .col-md-3 > .complete p {
            color: #000 !important;
        }

        .technologicalProcess .col-md-3 .schedule {
            position: absolute;
            width: 37px;
            height: 37px;
            background: #eeeeee;
            border-radius: 50%;
            border: 2px solid #eeeeee;
            top: -20px;
            margin-left: -18.5px;
            line-height: 37px;
            text-align: center;
            color: #afafaf;
            font-weight: 600;
            left: 50%;
        }

        .technologicalProcess .col-md-3 .ongoing + div {
            border: 2px solid #00b39b;
            background: #00b39b;
            color: #fff;
        }

        .technologicalProcess .col-md-3 .complete + div {
            border: 2px solid #00b39b;
            background: #fff;
            color: #00b39b;
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+3) .schedule::after {
            content: "▶";
            position: absolute;
            right: -11px;
            top: 50%;
            margin-top: -18.4px;
            color: #eeeeee;
            font-weight: 400;
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+5) .ongoing + div:after {
            color: #00b39b
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+5) .complete + div:after {
            color: #00b39b
        }

        .technologicalProcess .col-md-3:nth-child(n+2):nth-child(-n+6) .schedule::before {
            content: "◀";
            position: absolute;
            left: -11px;
            top: 50%;
            margin-top: -18.4px;
            color: #eeeeee;
            font-weight: 400;
        }

        .technologicalProcess .col-md-3:nth-child(n+2):nth-child(-n+6) .ongoing + div:before {
            color: #00b39b
        }

        .technologicalProcess .col-md-3:nth-child(n+2):nth-child(-n+6) .complete + div:before {
            color: #00b39b
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+4) > div > div:nth-child(1)::before {
            content: "";
            position: absolute;
            width: 100%;
            height: 4px;
            background: #eeeeee;
            top: -30px;
            left: 50%;
            z-index: 1;
            margin-left: 20px;
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+5) > .complete > div:nth-child(1)::before {
            background: #00b39b
        }

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+5) > .ongoing > div:nth-child(1)::before {
            bottom: -80.5px;
            background: linear-gradient(to right, #00b39b, #eeeeee)
        }
        /*模态框样式*/
        /*#testimonialModal .col-md-12{*/
            /*background: url("/img/touxiang1.png") no-repeat left;*/
            /*background-size: 50px 50px;*/
            /*padding-left: 60px;*/
        /*}*/
        #testimonialModal .modal-header{
            text-align: center;
            background: #f5f5f5;
        }
        #testimonialModal .modal-header .modal-title{
            color: #2b8cf5;
            font-size: 20px;
            font-weight: 600;
        }
        #testimonialModal .modal-footer{
            text-align: center;
        }
        .identityIcon{
            visibility: hidden;
        }
        #testimonialModal img{
            width: 50px;
            height: 50px;
            border-radius: 50%;
            box-shadow:0 6px 20px 14px #e7e7e7;
        }
        #testimonialModal .col-md-2{
            margin-top: 55px;
            padding-left: 70px;
        }
        #testimonialModal .col-md-10{
            margin-top: 45px;
        }
    </style>

</head>
<body>
<div class="mainBox">
    <div>
        <div class="row">
            <div class="col-md-12 identityIcon">
                <i class="fa fa-check-circle" aria-hidden="true"></i>
                <p>已生成鉴定书</p>
            </div>
            <div class="col-md-12">
                <b>
                    <input type="hidden" id="caseId" name="caseId" value="${limsCaseInfo.caseId}">
                    <img src="<%=path%>/img/caseFilePrint/jds.png" alt="" class="jiandingTrue">
                    <span class="identifyName" data-toggle="modal" data-target="#testimonialModal">
                        <button name="generateIdentifyBook" class="btn btn-blue btn-xs">点击生成鉴定书</button>
                    </span>
                </b>
            </div>
        </div>
        <div class="row technologicalProcess">
            <div class="col-md-3">
                <div class="complete">
                    <div>
                        <p>受理完成</p>
                        <p id="extractionExperimentDate"></p>
                    </div>
                </div>
                <div class="schedule">1</div>
            </div>
            <div class="col-md-3">
                <div class="complete">
                    <div>
                        <p>实验完成</p>
                        <p></p>
                    </div>
                </div>
                <div class="schedule">2</div>
            </div>
            <div class="col-md-3">
                <div class="ongoing">
                    <div class="identityText">
                        <p>正在生成鉴定书</p>
                        <p>..........</p>
                    </div>
                </div>
                <div class="schedule">3</div>
            </div>
            <div class="col-md-3">
                <div>
                    <div>
                        <p>已签发</p>
                        <p></p>
                    </div>
                </div>
                <div class="schedule">4</div>
            </div>
            <div class="col-md-3">
                <div>
                    <div>
                        <p>已领取</p>
                        <p></p>
                    </div>
                </div>
                <div class="schedule">5</div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
</body>
<script>

    $(function () {
        'use strict';


        $("button[name='generateIdentifyBook']").on("click",function(){
            if (!confirm("是否生成鉴定书！")) {
                return false;
            }

            var caseId = $("input[name='caseId']").val();
            $.ajax({
                url:"<%=path%>/center/generateIdentifyBook?caseId=" + caseId,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));
                    }else {
                        alert("生成失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("生成失败！");
                }
            });
        });

        /*$('.jiandingTrue').click(function () {
            $('.identifyName').text('鉴定书名称XXXXXXX')
            $('.identityIcon').css('visibility','visible')
            $('.identityText p:first-child').text('鉴定书已生成')
            $('.identityText p:last-child').text('第一审批人:苏大强')
        })*/
    })
</script>
</html>