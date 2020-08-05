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
        #look .modal-dialog {
            top: 50px;
        }

        #look .modal-body {
            padding: 0px;
            position: relative;
        }

        #look .closeBtn {
            position: absolute;
            width: 40px;
            height: 40px;
            color: #fff;
            background: #e7e7e7;
            text-align: center;
            line-height: 40px;
            top: -50px;
            right: 0px;
            border-radius: 50%;
            font-size: 25px;
            cursor: pointer;
        }

        #look .table {
            margin-bottom: 0px;
        }

        #look .table > thead > tr > th {
            border: none;
            background: #298de5;
            color: #fff;
            padding-left: 40px;
        }

        #look .table > thead > tr:nth-child(1) > th {
            line-height: 72px;
        }

        #look .table > thead > tr:nth-child(1) > th:nth-child(3) {
            background: #0060b5;
            line-height: 36px;
            text-align: center;
        }

        #look .table > thead > tr:nth-child(2) > th {
            background-color: #0074d9;
        }

        #look .table > tbody > tr > td {
            color: #000;
            font-weight: 600;
            padding-left: 40px;
            border-right: 1px solid #c1ccda;
        }

        #look .table > tbody > tr:nth-child(4n+1) {
            background: #ffd6d5;
        }

        #look .table > tbody > tr:nth-child(4n+2) {
            background: #e2efff;
        }

        #look .table > tbody > tr:nth-child(4n+3) {
            background: #eee1ff;
        }

        #look .table > tbody > tr:nth-child(4n+4) {
            background: #ffffff;
        }

        #look .table > tbody > tr:nth-last-child(1) {
            background: #fff7ed;
        }

        #look .table > tbody > tr:nth-last-child(1) td {
            color: #e27e00;
        }
        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
        }

        #look .modal-title {
            text-align: center;
            color: #0c81f5;
            font-weight: 600;
        }

        .modal-header {
            background: #f5f5f5;
        }

        .close {
            background: #d9d9d9 !important;
            padding: 5px;
            border-radius: 50%;
            color: #fff;
            opacity: 1 !important;
        }

        .close span {
            display: inline-block;
            height: 24px;
            width: 24px;
            color: #fff;
        }

        #look .modal-body {
            padding: 0px;
            padding-bottom: 15px;
        }

        #look .modal-body > div:nth-child(1) .col-md-4 {
            background: #f6fbff;
            height: 40px;
            line-height: 40px;
            color: #000;
            font-weight: 600;
            padding-left: 50px;
        }

        #look .modal-body > div:nth-child(1) .col-md-4:nth-child(2) {
            border-left: 1px solid #f2f2f2;
            border-right: 1px solid #f2f2f2;
        }

        #look .modal-body > div:nth-child(2) {
            max-height: 360px;
            overflow-y: auto;
        }

        #look .modal-body > div > div:nth-child(4n+1) .col-md-4 {
            background: #ffd6d6;
            height: 30px;
            line-height: 30px;
            color: #ff3333;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+2) .col-md-4 {
            background: #e2efff;
            height: 30px;
            line-height: 30px;
            color: #0072ff;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+3) .col-md-4 {
            background: #eee2ff;
            height: 30px;
            line-height: 30px;
            color: #5b00db;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+4) .col-md-4 {
            background: #f8f8f8;
            height: 30px;
            line-height: 30px;
            color: #464646;
            padding-left: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="../testProcess/testProgressBar.jsp"/>
<input type="hidden" value="${ipAddr}" id="ipAddress">
<%--<div class="modal fade" id="look" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">--%>
    <%--<div class="modal-dialog" role="document">--%>
        <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
                <%--<button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
                    <%--<span aria-hidden="true">&times;</span>--%>
                <%--</button>--%>
                <%--<h4 class="modal-title">查看基因型</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-4">序号</div>--%>
                    <%--<div class="col-md-4">基因座</div>--%>
                    <%--<div class="col-md-4">等位基因</div>--%>
                <%--</div>--%>
                <%--<div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                    <%--<div class="row">--%>
                        <%--<div class="col-md-4">序号</div>--%>
                        <%--<div class="col-md-4">基因座</div>--%>
                        <%--<div class="col-md-4">等位基因</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="modal-footer" style="text-align: left;">--%>
                <%--<div class="row">--%>
                    <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>操作人</label>--%>
                            <%--<input type="text" class="form-control" placeholder="请输入案件名称">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>操作时间</label>--%>
                            <%--<input type="text" class="form-control" placeholder="请输入案件名称">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>
<div class="row Modular" style="padding-bottom: 65px">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>检材/样本信息</div>
                <input type="hidden" name="dataFilePath" value="${labAnalysisInfo.dataFilePath}">
                <button class="btn btn-yellow" id="start">启动分析</button>
            </div>
        </div>
        <div class="panel-body">
            <table class="table table-hover table-bordered bigTable">
                <thead>
                <tr>
                    <th>序号</th>
                    <th>检材编号</th>
                    <th>检材名称</th>
                    <th>检材类型</th>
                    <th>位置</th>
                    <th>板号</th>
                    <th style="width:70px;">操作</th>
                </tr>
                </thead>
                <tbody id="samplelist_tbody">
                <%--<tr>
                    <td>${s.index + 1}</td>
                    <td title="${sample.sampleNo}">
                        <input name="sampleNo" type="text" class="form-control small"
                               onmouseover="this.title=this.value" value="${sample.sampleNo}"
                               readonly="readonly">
                    </td>
                    <td title="${sample.sampleName}">
                        <input name="sampleName" type="text" class="form-control small"
                               onmouseover="this.title=this.value" value="${sample.sampleName}"
                               readonly="readonly">
                    </td>
                    <td>
                        <select class="form-control small" disabled name="sampleType">
                            <c:forEach items="${sampleTypeList}" var="list">
                                <option value="${list.dictCode}"
                                        <c:if test="${sample.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input name="samplePostion" type="text" class="form-control small"
                               onmouseover="this.title=this.value" value="${sample.samplePostion}"
                               readonly="readonly">
                    </td>
                    <td>
                        <input name="boardNo" type="text" class="form-control small"
                               onmouseover="this.title=this.value" value="${labSyInfo.boardNo}"
                               readonly="readonly">
                    </td>
                    <td>
                        <input type="hidden" name="id" value="${sample.id}"/>
                        <input type="hidden" name="sampleId" value="${sample.sampleId}"/>
                        <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"
                                data-toggle="modal" data-target="#look">查看
                        </button>
                    </td>
                </tr>--%>
                <c:forEach items="${labSySampleList}" var="sample" varStatus="s">
                    <tr>
                        <td>${s.index + 1}</td>
                        <td title="${sample.sampleNo}">
                            <input name="sampleNo" type="text" class="form-control small"
                                   onmouseover="this.title=this.value" value="${sample.sampleNo}"
                                   readonly="readonly">
                        </td>
                        <td title="${sample.sampleName}">
                            <input name="sampleName" type="text" class="form-control small"
                                   onmouseover="this.title=this.value" value="${sample.sampleName}"
                                   readonly="readonly">
                        </td>
                        <td>
                            <select class="form-control small" disabled name="sampleType">
                                <c:forEach items="${sampleTypeList}" var="list">
                                    <option value="${list.dictCode}"
                                            <c:if test="${sample.sampleType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input name="samplePostion" type="text" class="form-control small"
                                   onmouseover="this.title=this.value" value="${sample.samplePostion}"
                                   readonly="readonly">
                        </td>
                        <td>
                            <input name="boardNo" type="text" class="form-control small"
                                   onmouseover="this.title=this.value" value="${labSyInfo.boardNo}"
                                   readonly="readonly">
                        </td>
                        <td>
                            <input type="hidden" name="id" value="${sample.id}"/>
                            <input type="hidden" name="sampleId" value="${sample.sampleId}"/>
                            <button type="button" name="viewBtn"
                                    class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"> 查看
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>



<div class="modal fade" id="look" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span aria-hidden="true" data-dismiss="modal" aria-label="Close" class="closeBtn">&times;</span>
                <table class="table">
                    <thead>
                    <tr>
                        <th >序号</th>
                        <th >基因座</th>
                        <th  style="background: #298de5;line-height: 72px;">等位基因</th>
                    </tr>
                    <%--<tr>--%>
                        <%--<th id="referenceNo"></th>--%>
                        <%--<th id="matchNo"></th>--%>
                    <%--</tr>--%>
                    </thead>
                    <tbody id="viewMatchTBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row btn-box ">
    <div class="col-md-12" style="margin-right: 0px;">
        <button class="btn btn-yellow" type="button" id="saveBtn">完成</button>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $("#saveNextBtn").on("click", function () {
            location.href = "<%=path%>/center/analysisExperiment";
        });

        $("#addInformant").on("click", function () {
            var caseNo = $("#caseNo").val();
            var sampleNo = $("#sampeNo").val();
            if (caseNo == "" && sampleNo == "") {
                alert("请输入案件编号或检材编号！");
            }

            $.ajax({
                url: "<%=path%>/center/getExtractSampleList?caseNo=" + caseNo + "&sampleNo=" + sampleNo,
                type: "post",
                dataType: "text",
                success: function (data) {
                    if (data != null) {
                        for (var i = 0; i < data.length; i++) {
                            var sampleInfo = JSON.parse(data)[i];
                            console.log(sampleInfo);
                            //新添被鉴定人
                            var sampleTr = '<tr>'
                            sampleTr += '<td><label class="custom-control checkbox-inline" style="margin-top: -22px"><input class="custom-control-input" type="checkbox"><span class="custom-control-label"></span></label></td>'
                            sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                            sampleTr += '<td><input type="text" name="potsion" value="' + "" + '"/></td>'
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                            sampleTr += '</td>'
                            sampleTr += '</tr>'
                            $("#samplelist_tbody").append(sampleTr)
                        }
                        // $("#referenceNo").text(referenceId);
                        // $("#matchNo").text(sampleNo);
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });

        });

        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd',
            language: 'zh',
            weekStart: 1,
            todayBtn: 1,
            minView: "month",
            autoclose: true,
            todayHighlight: true,
            forceParse: 0,
            showMeridian: 1
        }).on('changeDate', function (ev) {
            $(this).datetimepicker('hide');
        });


        $("input[name='checkAll']").change(function () {
            if ($(this).is(":checked")) {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", false)
            }
        })
        $("tbody").find("input[type='checkbox']").change(function () {
            if ($(this).parents("tbody").find("input[type='checkbox']:checked").length == $(this).parents("tbody").find("input[type='checkbox']").length) {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", false)
            }
        })
        //启动分析软件
        $("#start").click(function () {
//            var printServerUrl = "http://127.0.0.1:927/StartLocalProgram?Open=1";
            var dataFilePath = $("input[name='dataFilePath']").val();
            var printServerUrl = "http://"+ $("#ipAddress").val() + ":927/StartLocalProgram?Open=1&dataFilePath=" + dataFilePath;
            console.log(printServerUrl)
            $.ajax({
                type: "get",
                async: false,
                url: printServerUrl,
                timeout: 3000,
                dataType: "jsonp",
                success: function (data) {
                    if (data.status.msg == "Success") {

                    } else {
                        alert("打开失败！原因：" + data.msg);
                    }
                },
                error: function (e) {
                    //无法建立通信时，弹出下载安装插件的窗口

                }
            });
        })

        ///点击查看按钮
        $("[name='viewBtn']").click(function () {
            var ceneId = "D0F4768862654D16B568D2DF75AB1C1E";
            $.ajax({
                type: "get",
                async: false,
                url: "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" + ceneId,
                dataType: "json",
                success: function (data) {
                    console.log(data.maps)
                    if (data.maps!=null&&data.maps!="") {
                        $("#look").modal('show');
                        if (data.maps.length > 0) {
                            var matchInfo;
                            var sampleTr = "";

                            for (var i = 0; i < data.maps.length; i++) {
                                matchInfo = data.maps[i];
                                sampleTr += '<tr>';
                                sampleTr += '<td>'+ (i + 1) +'</td>';
                                sampleTr += '<td>'+ matchInfo.markerName +'</td>';
                                sampleTr += '<td>'+ matchInfo.allele +'</td>';
                                sampleTr += '</tr>';
                            }
                            $("#viewMatchTBody").append(sampleTr);
                        }
                    } else {
                        alert("查看失败!");
                    }
                },
            });
        });
        //点击完成按钮
        $("#saveBtn").click(function () {
          location.href="<%=path%>/center/testToReview"
        });

    })
</script>
</body>

</html>