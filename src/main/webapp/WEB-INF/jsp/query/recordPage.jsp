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
        .tdGreen {
            color: #63c9ba !important;
        }

        .tdRed {
            color: #ff6864 !important;
        }
    </style>
</head>
<body>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>提取实验列表</div>
            </div>
            <div class="panel-body" style="padding: 2px 30px">
                <table class="table table-hover table-bordered bigTable" style="margin-top: 5px;">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>提取编号</th>
                        <th>板号</th>
                        <th>检材数量</th>
                        <th>任务日期</th>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" id="allExtractChecked" name="allExtractChecked">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="extractTbody">
                    <c:forEach items="${labExtractInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.extractNo}</td>
                            <td>${list.boardNo}</td>
                            <td>${list.sampleCount}</td>
                            <td><fmt:formatDate value="${list.extractDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="hidden" name="extractId" value="${list.extractId}">
                                <label class="custom-control checkbox-inline" style="margin-bottom: 23px">
                                    <input class="custom-control-input" type="checkbox" name="boxExtract">
                                    <span class="custom-control-label"></span>
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
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
                <div>扩增实验列表</div>
            </div>
            <div class="panel-body" style="padding: 2px 30px">
                <table class="table table-hover table-bordered bigTable" style="margin-top: 5px;">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>扩增编号</th>
                        <th>板号</th>
                        <th>检材数量</th>
                        <th>任务日期</th>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" id="allPcrChecked" name="allPcrChecked">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="pcrTbody">
                    <c:forEach items="${labPcrInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.pcrNo}</td>
                            <td>${list.boardNo}</td>
                            <td>${list.sampleCount}</td>
                            <td><fmt:formatDate value="${list.pcrEndDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="hidden" name="pcrId" value="${list.pcrId}">
                                <label class="custom-control checkbox-inline" style="margin-bottom: 23px">
                                    <input class="custom-control-input" type="checkbox" name="boxPcr">
                                    <span class="custom-control-label"></span>
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
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
                <div>电泳实验列表</div>
            </div>
            <div class="panel-body" style="padding: 2px 30px">
                <table class="table table-hover table-bordered bigTable" style="margin-top: 5px;">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>电泳编号</th>
                        <th>板号</th>
                        <th>检材数量</th>
                        <th>任务日期</th>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" id="allSyChecked" name="allSyChecked">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="syTbody">
                    <c:forEach items="${labSyInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.syNo}</td>
                            <td>${list.boardNo}</td>
                            <td>${list.sampleCount}</td>
                            <td><fmt:formatDate value="${list.syEndDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="hidden" name="syId" value="${list.syId}">
                                <label class="custom-control checkbox-inline" style="margin-bottom: 23px">
                                    <input class="custom-control-input" type="checkbox" name="boxSy">
                                    <span class="custom-control-label"></span>
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box" style="margin-top: 5px;margin-right : 0px">
    <div class="col-md-12">
        <input type="hidden" name="caseId" id="caseId" value="${caseId}">
        <button class="btn btn-red btn-lang" type="button" id="downFileAll">下载</button>
        <button class="btn btn-blue btn-lang" type="button" id="printFilesAll">打印</button>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        //下载
        $("#downFileAll").on("click", function(){
            var record;

            //提取
            var checkExtractCount = 0;
            var recordExtractArr = new Array();
            $("tr", "#extractTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxExtract']", $(this)).is(":checked");
                if (checkBox) {
                    checkExtractCount++;
                    record.extractId = $("input[name='extractId']", $(this)).val();
                    recordExtractArr.push(record);
                }
            });

            //扩增
            var checkPcrCount = 0;
            var recordPcrArr = new Array();
            $("tr", "#pcrTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxPcr']", $(this)).is(":checked");
                if (checkBox) {
                    checkPcrCount++;
                    record.pcrId = $("input[name='pcrId']", $(this)).val();
                    recordPcrArr.push(record);
                }
            });

            //电泳
            var checkSyCount = 0;
            var recordSyArr = new Array();
            $("tr", "#syTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxSy']", $(this)).is(":checked");
                if (checkBox) {
                    checkSyCount++;
                    record.syId = $("input[name='syId']", $(this)).val();
                    recordSyArr.push(record);
                }
            });

            if ((checkExtractCount + checkPcrCount + checkSyCount) <= 0) {
                alert("至少要选择一个!");
                return false;
            }

            var caseId = $("#caseId").val();
            //下载提取
            var listLength = recordExtractArr.length;
            if (listLength > 0) {
                var extractId = "";
                for (var i = 0; i < listLength; i++) {
                    extractId = recordExtractArr[i].extractId;
                    if (extractId !== null || extractId !== undefined || extractId !== '') {
                        var url = "<%=path%>/dowmFileController/dowmFileExtractBtn?caseId=" + caseId + "&extractId=" + extractId;
                        generateRecord(url);
                    }
                }
            }

            //下载扩增
            listLength = recordPcrArr.length;
            if (listLength > 0) {
                var pcrId = "";
                for (var i = 0; i < listLength; i++) {
                    pcrId = recordPcrArr[i].pcrId;
                    if (pcrId !== null || pcrId !== undefined || pcrId !== '') {
                        var url = "<%=path%>/dowmFileController/dowmFileAmplificationRecord?caseId=" + caseId + "&pcrId=" + pcrId;
                        generateRecord(url);
                    }
                }
            }

            //下载电泳
            listLength = recordSyArr.length;
            if (listLength > 0) {
                var syId = "";
                for (var i = 0; i < listLength; i++) {
                    syId = recordSyArr[i].syId;
                    if (syId !== null || syId !== undefined || syId !== '') {
                        var url = "<%=path%>/dowmFileController/dowmFileloadSample?caseId=" + caseId + "&syId=" + syId;
                        generateRecord(url);
                    }
                }
            }
        })

        function generateRecord(url) {
            var fileFrame = document.createElement("iframe");
            fileFrame.src = url;//文件路径
            fileFrame.style.display = "none";
            document.body.appendChild(fileFrame);
        }

        //打印
        $("#printFilesAll").on("click", function () {
            var record;

            //提取
            var checkExtractCount = 0;
            var recordExtractArr = new Array();
            $("tr", "#extractTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxExtract']", $(this)).is(":checked");
                if (checkBox) {
                    checkExtractCount++;
                    record.extractId = $("input[name='extractId']", $(this)).val();
                    recordExtractArr.push(record);
                }
            });

            //扩增
            var checkPcrCount = 0;
            var recordPcrArr = new Array();
            $("tr", "#pcrTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxPcr']", $(this)).is(":checked");
                if (checkBox) {
                    checkPcrCount++;
                    record.pcrId = $("input[name='pcrId']", $(this)).val();
                    recordPcrArr.push(record);
                }
            });

            //电泳
            var checkSyCount = 0;
            var recordSyArr = new Array();
            $("tr", "#syTbody").each(function () {
                record = {};
                var checkBox = $("input[name='boxSy']", $(this)).is(":checked");
                if (checkBox) {
                    checkSyCount++;
                    record.syId = $("input[name='syId']", $(this)).val();
                    recordSyArr.push(record);
                }
            });

            if ((checkExtractCount + checkPcrCount + checkSyCount) <= 0) {
                alert("至少要选择一个!");
                return false;
            }

            var caseId = $("#caseId").val();
            var params = {
                "caseId": caseId,
                "recordExtractList": recordExtractArr,
                "recordPcrList": recordPcrArr,
                "recordSyList": recordSyArr
            };

            $.ajax({
                url: "<%=path%>/dowmFileController/printRecord",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(params),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {

                    }else {
                        alert("打印失败！");
                    }
                },
                error: function (e) {
//                    alert("打印失败！" + e);
                }
            });
        })

        /**
         * 提取
         */
        $("#allExtractChecked").on("click", function () {
            $("tr", "#extractTbody").each(function () {
                if ($("#allExtractChecked").prop('checked')) {
                    $("input[name='boxExtract']", $(this)).prop('checked', true);
                } else {
                    $("input[name='boxExtract']", $(this)).prop('checked', false);
                }
            });
        });

        $("input[name='boxExtract']", "#extractTbody").on("click", function () {
            //获取选中复选框长度
            var length = $("input[name=boxExtract]:checked").length;
            //未选中的长度
            var len = $("input[name=boxExtract]").length;
            if (length == len) {
                $("#allExtractChecked").get(0).checked = true;
            } else {
                $("#allExtractChecked").get(0).checked = false;
            }
        });

        /**
         * 扩增
         */
        $("#allPcrChecked").on("click", function () {
            $("tr", "#pcrTbody").each(function () {
                if ($("#allPcrChecked").prop('checked')) {
                    $("input[name='boxPcr']", $(this)).prop('checked', true);
                } else {
                    $("input[name='boxPcr']", $(this)).prop('checked', false);
                }
            });
        });

        $("input[name='boxPcr']", "#pcrTbody").on("click", function () {
            //获取选中复选框长度
            var length = $("input[name=boxPcr]:checked").length;
            //未选中的长度
            var len = $("input[name=boxPcr]").length;
            if (length == len) {
                $("#allPcrChecked").get(0).checked = true;
            } else {
                $("#allPcrChecked").get(0).checked = false;
            }
        });

        /**
         * 电泳
         */
        $("#allSyChecked").on("click", function () {
            $("tr", "#syTbody").each(function () {
                if ($("#allSyChecked").prop('checked')) {
                    $("input[name='boxSy']", $(this)).prop('checked', true);
                } else {
                    $("input[name='boxSy']", $(this)).prop('checked', false);
                }
            });
        });

        $("input[name='boxSy']", "#syTbody").on("click", function () {
            //获取选中复选框长度
            var length = $("input[name=boxSy]:checked").length;
            //未选中的长度
            var len = $("input[name=boxSy]").length;
            if (length == len) {
                $("#allSyChecked").get(0).checked = true;
            } else {
                $("#allSyChecked").get(0).checked = false;
            }
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
    })
</script>
</body>

</html>