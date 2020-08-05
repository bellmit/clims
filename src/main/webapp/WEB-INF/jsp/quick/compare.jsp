<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .Modular {
            height: 98%;
        }
        .manual {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #ff5a56;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
        }

        .automatic {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #fda228;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
            float: left;
        }

        .tested {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #50c987;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
            float: left;
        }

        .test {
            width: 25px;
            height: 25px;
            display: block;
            line-height: 18px;
            text-align: center;
            background: #e4e4e4;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
        }

        td:nth-last-child(1) a {
            display: inline-block;
            width: 70px;
            color: #ff6561;
        }

        td a:hover::before {
            content: "进入实验";
        }

        td .fa-check-circle {
            color: #50c987;
            width: 30px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            font-size: 25px;
            margin-left: 10px;
        }

        td .fa-check-circle::before {
            display: inline-block;
            width: 30px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            font-size: 29px;
        }

        td .fa-check-circle:hover::before {
            content: "查看";
            font-size: 14px;
        }

        .mssageDiv {
            height: 300px;
            text-align: center;
        }

    </style>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>快速比对</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" class="form-horizontal tasi-form">
                    <div class="row mssageDiv">
                        <h1>
                            请在自动打开的IE浏览器窗口中进行快速比对！
                        </h1>
                        <input type="hidden" value="${ipAddr}" id="ipAddress">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        var newUrl = "http://14.60.76.241:8087/DnaQuickMatch/quickCompare.jsp";
        // var newUrl = "http://192.168.1.133:8080/DnaWebservice/quickCompare.jsp";
        var printServerUrl = "http://"+ $("#ipAddress").val() + ":927/StartIE?url=" + newUrl;

        $.ajax({
            type: "get",
            url: printServerUrl,
            timeout: 3000,
            cache: false,
            success: function (data) {
                if (data.msg == "Success") {
                } else {
                    alert("打开失败！原因：" + data.msg);
                }
            },
            error: function (e) {
                //无法建立通信时，弹出下载安装插件的窗口
            }
        });

        //window.open("http://localhost:8080/DnaWebservice/quickMatch.jsp");

    });
</script>
</body>
</html>
