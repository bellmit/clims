<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .technologicalProcess .col-md-3 {
            padding: 0 18px;
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
            color: #000;
            font-size: 13px;
            margin: 0px
        }

        .technologicalProcess .col-md-3 > div p:nth-child(2) {
            color: #b2b2b2;
        }

        .technologicalProcess .col-md-3 > .ongoing p:nth-child(1) {
            font-weight: 600;
        }

        .technologicalProcess .col-md-3 > .ongoing p {
            color: #00b39b !important;
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

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+2) .schedule::after {
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

        .technologicalProcess .col-md-3:nth-child(n+1):nth-child(-n+2) > div > div:nth-child(1)::before {
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

        .badge {
            position: absolute;
            top: -30px;
            left: 50%;
            background: #fda228;
            z-index: 1;
            padding: 0px;
            width: 20px;
            height: 20px;
            display: inline-block;
            text-align: center;
            line-height: 20px;
            cursor: pointer;
        }

        .amplificationBox {
            width: 530px;
            border: 1px solid #dddddd;
            border-radius: 5px;
            position: absolute;
            left: 50%;
            margin-left: 30px;
            top: -70px;
            z-index: 2;
            display: none;
        }

        .amplificationBox .title {
            height: 40px;
            line-height: 40px;
            color: #1681f5;
            font-weight: 600;
            text-align: center;
            background: #f5f5f5;
        }

        .tableCount {
            padding: 15px;
            background: #fff;
        }

        td {
            text-align: left !important;
        }
    </style>
</head>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">检验过程</a></li>
    <li class="active">当前页</li>
</ol>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>检验过程</div>
            </div>
            <div class="panel-body" style="padding: 30px">
                <div class="row technologicalProcess" style="padding-left: 25%;">
                    <div class="col-md-3">
                        <div class="complete">
                            <div>
                                <p>提取阶段</p>
                                <p id="extractionExperimentDate">${extractionExperimentDate}</p>
                            </div>
                        </div>
                        <div class="schedule">1</div>
                    </div>
                    <div class="col-md-3">
                        <div class="complete">
                            <div>
                                <p>扩增阶段</p>
                                <p>${pcrexperimentDate}</p>
                            </div>
                            <%--<span class="badge">4</span>--%>
                            <div class="amplificationBox">
                                <div class="title">待完成扩增任务</div>
                                <div class="tableCount">
                                    <table class="table table-hover table-bordered bigTable">
                                        <thead>
                                        <tr>
                                            <th>生成任务号</th>
                                            <th>检材数量</th>
                                            <th>PCR板号</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>11212334455</td>
                                            <td>88</td>
                                            <td>2019-03-21-Y-plus</td>
                                            <td>
                                                <button class="btn-green btn-sm btn">进行中</button>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>11212334455</td>
                                            <td>88</td>
                                            <td>2019-03-21-Y-plus</td>
                                            <td>
                                                <button class="btn-gray btn-sm btn">待扩增</button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="schedule">2</div>
                    </div>
                    <div class="col-md-3">
                        <div class="complete">
                            <div>
                                <p>电泳阶段</p>
                                <p>${syExperimentDate}</p>
                            </div>
                        </div>
                        <div class="schedule">3</div>
                    </div>
                    <%--<div class="col-md-3">
                        <div class="complete">
                            <div>
                                <p>分析阶段</p>
                                <p>2019-01-08 23:23</p>
                            </div>
                        </div>
                        <div class="schedule">4</div>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJsCopy.jsp" %>
<script>
    $(function () {
        //获取实验时间
        function getExtractionDate() {
            $.ajax({
                url: "<%=path%>/center/saveExtractionExperiment?boardNo=" + boardNo + "&operateType=" + $("#operateType").val() + "&extractDatetimes=" + $("#extractionExperimentDates").val() + "&extractPerson1=" + $("#operateUser").val() + "&equipment=" + equipments + "&kit=" + kits,
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#extractId").val(data.extractId);
                        $("#recordModal").modal('show');
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("保存失败！");
                }
            });
        }

        $(".technologicalProcess").find(".col-md-3").eq(1).find(".schedule").mouseenter(function () {
            if ($(this).prev().find(".badge").length > 0) {
                $(".amplificationBox").fadeIn("slow")
            }
        })
        $(".technologicalProcess").find(".col-md-3").eq(1).find(".schedule").mouseleave(function () {
            $(".amplificationBox").fadeOut("slow")
        })
        $(".badge").mouseenter(function () {
            if ($(this).prev().find(".badge").length > 0) {
                $(".amplificationBox").fadeIn("slow")
            }
        })
        $(".badge").mouseleave(function () {
            $(".amplificationBox").fadeOut("slow")
        })
    })
</script>