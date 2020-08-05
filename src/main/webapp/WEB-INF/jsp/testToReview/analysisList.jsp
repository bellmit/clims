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
    </style>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/analysisExperiment">
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/center/analysisExperiment" class="form-horizontal tasi-form"
                      method="post">
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>上样板号</label>
                            <input type="text" class="form-control" placeholder="请输入上样板号" id="boardNo" name="entity.boardNo" value="${query.entity.boardNo}">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>检材编号</label>
                            <input type="text" class="form-control" placeholder="请输入检材编号" id="sampleNo" name="sampleNo" value="${query.sampleNo}">
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group">
                            <label>检验人</label>
                            <div class="select">
                                <select id="auditor" name="entity.auditor" value="auditor"
                                        class="form-control">
                                    <option value="" selected>全部</option>
                                    <c:forEach items="${amPersonalInfoVoList}" var="list" varStatus="cs">
                                        <option value="${list.entity.fullName}" <c:if test="${list.entity.fullName eq query.entity.auditor}">selected</c:if>>
                                                ${list.entity.fullName}
                                        </option>
                                    </c:forEach>
                                </select>
                                <%--<select id="auditor" name="entity.analysisPerson" value="analysisPerson"
                                        class="form-control">
                                    <option value="" selected>全部</option>
                                    <c:forEach items="${labAnalysisInfos}" var="list" varStatus="cs">
                                        <option value="${list.analysisPerson}" <c:if test="${list.analysisPerson eq query.entity.analysisPerson}">selected</c:if>>
                                                ${list.analysisPerson}
                                        </option>
                                    </c:forEach>
                                </select>--%>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3 seachInputBox">
                        <div class="form-group seachButtonBox">
                            <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                            <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                            <button type="button" name="reset" class="btn btn-blue-border">重置</button>
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
                <div>上样板列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>上样板号</th>
                        <th>上传时间</th>
                        <th>检验人</th>
                        <th>样本总数</th>
                        <th>测序仪</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${labAnalysisInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.entity.boardNo}</td>
                            <td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd'/></td>
                            <td>${list.entity.analysisPerson}</td>
                            <td>${list.entity.sampleCount}</td>
                            <td>${list.entity.machineNo}</td>
                            <td>
                                <a href="<%=path%>/center/enterAnalysis?id=${list.entity.id}"
                                   target="ifm"  class="btn btn-green btn-xs">结果分析</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../pagefoot.jsp" %>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script>
    $(function () {

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

        //重置
        $("button[name='reset']").on("click", function () {
            location.href = "<%=path%>/center/analysisExperiment";
        })
    })
</script>
</body>

</html>