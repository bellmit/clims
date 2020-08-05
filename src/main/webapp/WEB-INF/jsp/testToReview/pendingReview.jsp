<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
    String taskId = (String) request.getSession().getAttribute("taskId");
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
        .btn-red:hover {
            width: 100%;
        }

        #videoBox a {
            padding-top: 10px;
            padding-bottom: 10px
        }

        #videoBox button {
            padding: 10px 26px;
        }

        .bu {
            background: #fddddb;
            color: #fc5a56;
            padding: 5px;
            border-radius: 50%;
            font-size: 10px;
            font-weight: 600;
        }
        #recheckBox .modal-content .modal-header{
            background: #fff;
            border-radius: 5px 5px 0 0;
        }
        #recheckBox .modal-content  .panel-body .btn{
            border-radius: 50px;
        }
        #recheckBox .modal-content  .panel-body .btn+.btn{
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="modal fade popBox bs-example-modal-sm" id="recheckBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <h4 class="modal-title">选择复检阶段</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group" style="text-align: center">
                                <button type="button" class="btn btn-lang btn-blue" name="extractPhase">提取阶段</button>
                                <button type="button" class="btn btn-lang btn-yellow" name="ampPhase">扩增阶段</button>
                                <button type="button" class="btn btn-lang btn-green" name="elePhase">电泳阶段</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path %>/center/querPpendingReview" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本编号</label>
                                <input type="text" id="sampleNo" name="sampleNo"
                                       class="form-control" value="${query.sampleNo}"
                                       placeholder="请输入样本编号">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本类型</label>
                                <div class="select">
                                    <select id="sampleType" name="sampleType"
                                            class="form-control">
                                        <option value="">全部</option>
                                        <c:forEach items="${sampleTypeList}" var="list" varStatus="cs">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${list.dictCode eq query.sampleType}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本名称</label>
                                <input type="text" id="sampleName" name="sampleName"
                                       class="form-control" value="${query.sampleName}"
                                       placeholder="请输入样本名称">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <button type="button" class="btn btn-blue-border" onclick="cz()">重置</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>待复检列表</div>
                <button class="btn btn-yellow pull-right" style="font-weight: 600;margin-top: -7px; margin-right: 15px;"
                        data-toggle="modal" name="startRecheck" data-target="#myModal">开始复检
                </button>
                <%--<button class="btn btn-blue pull-right checkboxAll" name="updateStateAll1" style="font-weight: 600;margin-top: -7px; margin-right: 15px;"
                        data-toggle="modal" data-target="#myModal">全选
                </button>--%>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>样本编号</th>
                        <th>样本类型</th>
                        <th>样本名称</th>
                        <%--<th>操作</th>--%>
                        <th>
                            <label class="custom-control checkbox-inline" style="margin-top: -23px;">
                                <input class="custom-control-input" type="checkbox" name="updateStateAll">
                                <span class="custom-control-label"></span>
                            </label>
                        </th>
                    </tr>
                    </thead>
                    <tbody id="reviewQueueSampleTbody">
                    <c:forEach items="${reviewQueueSampleList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.sampleNo}<input type="hidden" name="sampleNo" value="${list.sampleNo}"></td>
                            <td>${list.sampleTypeName}<input type="hidden" name="sampleTypeName"
                                                             value="${list.sampleType}"></td>
                            <td>${list.sampleName}<input type="hidden" name="sampleName" value="${list.sampleName}">
                            </td>
                            <input type="hidden" name="sampleId" value="${list.entity.sampleId}">
                            <input type="hidden" name="id" value="${list.entity.id}">
                            <input type="hidden" name="taskId" id="taskId" value="${taskId}">
                        <%--<td>--%>
                                <%--<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove" value="${list.entity.id}">删除--%>
                                <%--</button>--%>
                            <%--</td>--%>
                            <td>
                                <label class="custom-control checkbox-inline" style="margin-top: -23px;">
                                    <input class="custom-control-input" type="checkbox" name="updateState">
                                    <span class="custom-control-label"></span>
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="row" style="padding: 0px">
                    <div class="col-md-12">
                        <div id="kkpager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $("#addInformant").on("click", function () {
            $("#page").val(1);
            $('#consignationForm').submit();
        });

        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/querPpendingReview',
            //链接尾部
            //hrefLatter: '.html',
            getLink: function (page) {
                return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#consignationForm").serialize();
            }
            , lang: {
                firstPageText: '首页',
                firstPageTipText: '首页',
                lastPageText: '尾页',
                lastPageTipText: '尾页',
                prePageText: '上一页',
                prePageTipText: '上一页',
                nextPageText: '下一页',
                nextPageTipText: '下一页',
                totalPageBeforeText: '共',
                totalPageAfterText: '页',
                currPageBeforeText: '当前第',
                currPageAfterText: '页',
                totalInfoSplitStr: '/',
                totalRecordsBeforeText: '共',
                totalRecordsAfterText: '条数据',
                gopageBeforeText: '&nbsp;转到',
                gopageButtonOkText: '确定',
                gopageAfterText: '页',
                buttonTipBeforeText: '第',
                buttonTipAfterText: '页'
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

        //删除
        $("#reviewQueueSampleTbody").on("click", ".remove", function () {
            var isNo = confirm("是否确认删除")
            if(isNo){
                location.href="<%=path%>/center/deleteReviewQueueSample?id=" + $(this).attr("value")
            }
        });

        //更新全选
        $("input[name='updateStateAll']").change(function () {
            if ($(this).is(":checked")) {
                $("tbody").find("input[name='updateState']").prop("checked", true)
                $(".btn-box").removeClass("hidden")
            } else {
                $("tbody").find("input[name='updateState']").prop("checked", false)
                $(".btn-box").addClass("hidden")
            }
        })

        //判断全选是否选中
        $("[name='updateState']").change(function(){
            if($("input[name='updateState']:checked").length==$("input[name='updateState']").length){
                $("input[name='updateStateAll']").prop("checked",true)
            }else{
                $("input[name='updateStateAll']").prop("checked",false)
            }
        })

        //开始复检
        $("button[name='startRecheck']").on("click", function () {
            var reviewQueueSampleList = []
            $("tbody").find("input[name='updateState']:checked").each(function () {
                var reviewQueueSampleArr = {}
                reviewQueueSampleArr.id = $(this).parents("tr").find("input[name='id']").val()
                reviewQueueSampleArr.taskStage = "1";
                reviewQueueSampleList.push(reviewQueueSampleArr)
            })
            if(reviewQueueSampleList.length == 0){
                alert("请选择要复检的检材！");
                return false
            }
            $("#recheckBox").modal('show')
        })

        //点击提取阶段按钮
        $("button[name='extractPhase']").on("click", function () {
            var reviewQueueSampleList = []

            $("tbody").find("input[name='updateState']:checked").each(function () {
                var reviewQueueSampleArr = {}
                reviewQueueSampleArr.id = $(this).parents("tr").find("input[name='id']").val()
                reviewQueueSampleArr.taskStage = "1";
                reviewQueueSampleList.push(reviewQueueSampleArr)
            })

            var params = {
                reviewQueueSampleList: reviewQueueSampleList
            };

            $.ajax({
                url: "<%=path%>/center/startRecheck",
                type: "post",
                data: params,
                data: {"params": JSON.stringify(params)},
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#taskId").val(data.taskId);
                        goSession1()
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })

        function goSession1() {
            var sampleArr = new Array();
            var checkCount = 0;
            var $sampleTR = $("tr", "#reviewQueueSampleTbody");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='updateState']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.sampleType = $("input[name='sampleTypeName']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sampleArr.push(sample);
                }
            }

            if (checkCount <= 0) {
                alert("至少选中一个检材！");
                return false;
            }
            $.ajax({
                url: "<%=path%>/center/saveExtractioListSession?inspectionType=0&extractionMode=0",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sampleArr),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/handwork/manualExtractionExperiment" ;
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        }

        //点击扩增阶段按钮
        $("button[name='ampPhase']").on("click", function () {
            var reviewQueueSampleList = []

            $("tbody").find("input[name='updateState']:checked").each(function () {
                var reviewQueueSampleArr = {}
                reviewQueueSampleArr.id = $(this).parents("tr").find("input[name='id']").val()
                reviewQueueSampleArr.taskStage = "2";
                reviewQueueSampleList.push(reviewQueueSampleArr)
            })

            var params = {
                reviewQueueSampleList: reviewQueueSampleList
            };

            $.ajax({
                url: "<%=path%>/center/startRecheck",
                type: "post",
                data: params,
                data: {"params": JSON.stringify(params)},
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#taskId").val(data.taskId);
                        goSession2();
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })

        function goSession2() {
            var sampleArr = new Array();
            var checkCount = 0;
            var $sampleTR = $("tr", "#reviewQueueSampleTbody");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='updateState']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.sampleType = $("input[name='sampleTypeName']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sampleArr.push(sample);
                }
            }

            $.ajax({
                url: "<%=path%>/center/saveExtractioListSession?inspectionType=0&extractionMode=0",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sampleArr),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/handwork/reinspectionPcrExperiment" ;
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        }

        //点击电泳阶段按钮
        $("button[name='elePhase']").on("click", function () {
            var reviewQueueSampleList = []

            $("tbody").find("input[name='updateState']:checked").each(function () {
                var reviewQueueSampleArr = {}
                reviewQueueSampleArr.id = $(this).parents("tr").find("input[name='id']").val()
                reviewQueueSampleArr.taskStage = "3";
                reviewQueueSampleList.push(reviewQueueSampleArr)
            })

            var params = {
                reviewQueueSampleList: reviewQueueSampleList
            };

            $.ajax({
                url: "<%=path%>/center/startRecheck",
                type: "post",
                data: params,
                data: {"params": JSON.stringify(params)},
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        $("#taskId").val(data.taskId);
                        goSession3();
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })

        function goSession3() {
            var sampleArr = new Array();
            var checkCount = 0;
            var $sampleTR = $("tr", "#reviewQueueSampleTbody");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='updateState']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.sampleType = $("input[name='sampleTypeName']", $sampleTR.get(i)).val();
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sampleArr.push(sample);
                }
            }

            $.ajax({
                url: "<%=path%>/center/saveExtractioListSession?inspectionType=0&extractionMode=0",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sampleArr),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/handwork/reinspectionSyExperiment";
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        }

    })
    function cz(){
        $("#sampleNo").val("")
        $("#sampleType").val("")
        $("#sampleName").val("")
        $("#page").val(1);
        $('#consignationForm').submit();
    }
</script>
</body>

</html>