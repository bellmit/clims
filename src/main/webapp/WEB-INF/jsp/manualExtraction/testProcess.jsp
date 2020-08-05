<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        .first {
            display: inline-block;
            background: #0c81f5;
            color: #fff;
            width: 20px;
            text-align: center;
            height: 20px;
            line-height: 20px;
        }

        .again {
            display: inline-block;
            background: #fda228;
            color: #fff;
            width: 20px;
            text-align: center;
            height: 20px;
            line-height: 20px;
        }
        #myModal .modal-body{
            text-align: center;
            padding: 50px 30px;
        }
        #myModal .modal-body a+a{
            margin-left: 20px;
        }
        tbody tr td span{
            margin-left: 12px;
        }
    </style>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/handwork/manualExtraction"
                      class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>提取编号</label>
                                <input type="text" id="extractNos" name="entity.extractNo" value="${entity.extractNo}"
                                       class="form-control"
                                       placeholder="请输入提取编号">
                            </div>
                        </div>

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>任务日期</label>
                                <div class="row">
                                    <div class="col-md-12 nopadding">
                                        <input type="text" id="createDatetime" name="createDatetime"
                                               class="form-control form_datetime"
                                               value="${createDatetime}"
                                               placeholder="请选择操作时间"
                                               readonly="readonly">
                                    </div>

                                </div>
                            </div>
                        </div>
                        <%--<div class="col-md-4 seachInputBox">--%>
                        <%--<div class="form-group">--%>
                        <%--<label></label>--%>
                        <%--<input type="text" class="form-control" placeholder="请输入板号" id="boardNo" name="boardNo" value="">--%>
                        <%--</div>--%>
                        <%--</div>--%>


                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>任务状态</label>
                                <div class="select">
                                    <select id="taskStatus" name="entity.extractStage"
                                            value="" class="form-control">
                                        <option value="" selected>全部</option>
                                        <option value="0"
                                                <c:if test="${extractStage=='0'}">selected</c:if> >进行中
                                        </option>
                                        <option value="1" <c:if test="${extractStage=='1'}">selected</c:if>>
                                            已完成
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-3 seachInputBox">
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
                <div>任务列表</div>

                <div class="pull-right" style="font-weight: 400;margin-top: -7px; margin-right: 15px;">
                    <a href="<%=path%>/center/manualExtraction" target="ifm"  class="btn btn-blue">手动提取</a>
                </div>
                 <%--<div style="float:right;" class="form-group">--%>
                     <%--<a href="<%=path%>/center/manualExtraction" target="ifm" class="btn btn-blue">新增任务</a>--%>
                <%--</div>--%>

                    <%--<div class="btn btn-yellow pull-right" style="font-weight: 600;margin-top: -7px; margin-right: 15px;">--%>
                        <%--<a href="<%=path%>/center/manualExtraction" target="ifm" class="btn btn-blue">手动提取</a>--%>
                    <%--</div>--%>


            </div>


            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                            <th>提取编号</th>
                            <th>任务日期</th>
                            <th>任务状态</th>
                            <th>检材数量</th>
                        <%--<th>板号</th>--%>
                        <%--<th>案件数量</th>--%>
                        <%--<th>检材数量</th>--%>
                        <%--<th>提取方法</th>--%>
                        <%--<th>扩增</th>--%>
                        <%--<th>电泳</th>--%>
                        <%--<th>分析</th>--%>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${labExtractInfoVos}" var="list" varStatus="s">
                        <td>${s.index+1}</td>
                        <td>${list.entity.extractNo}</td>
                        <td><fmt:formatDate value="${list.entity.createDatetime}" pattern="yyyy-MM-dd"/></td>

                        <td>
                            <c:if test="${list.entity.extractStage == 0}">进行中</c:if>
                            <c:if test="${list.entity.extractStage == 1}">已完成</c:if>
                        </td>
                        <td>${list.entity.sampleCount}</td>
                        <td>
                        <%--<c:if test="${list.entity.extractStage eq '0'}">--%>
                            <%--<a href="<%=path%>/handwork/pcrExperiment" target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                            <%--</a>--%>
                            <%--<a href="<%=path%>/center/enterManualExperiment?taskId=${list.entity.taskId}&taskStage=${list.entity.taskStage}"--%>
                               <%--target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                            <%--</a>--%>
                            <%--<c:if test="${list.entity.extractionMode == 1}">--%>
                                <span class="automatic">手</span>
                                <a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=1"
                                   class="fa fa-check-circle"></a>
                            <%--</c:if>--%>
                        <%--</c:if>--%>


                        </td>
                        <%--<tr>--%>
                            <%--&lt;%&ndash;<td>${s.index+1}</td>&ndash;%&gt;--%>
                            <%--<td>--%>
                                <%--<c:if test="${list.entity.inspectionType == 0}"> <span class="first">首</span></c:if>--%>
                                <%--<c:if test="${list.entity.inspectionType == 1}"> <span class="again">复</span></c:if>--%>
                                <%--${list.entity.taskNo}--%>
                            <%--</td>--%>
                            <%--<td>${list.entity.boardNo}</td>--%>
                            <%--<td></td>--%>
                            <%--<td>${list.entity.sampleCount}</td>--%>
                            <%--<td>--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${list.entity.taskStage ge 1}">--%>
                                        <%--<c:if test="${list.entity.extractionMode == 0}">--%>
                                            <%--<span class="manual pull-left">手</span>--%>
                                            <%--<a href="<%=path%>/center/enterManualExperiment?taskId=${list.entity.taskId}&taskStage=1"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${list.entity.extractionMode == 1}">--%>
                                            <%--<span class="automatic">自</span>--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=1"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise><span class="test">...</span></c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${list.entity.taskStage ge 2}">--%>
                                        <%--<c:if test="${list.entity.extractionMode == 0}">--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=2"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${list.entity.extractionMode == 1}">--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=2"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise><span class="test">...</span></c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${list.entity.taskStage ge 3}">--%>
                                        <%--<c:if test="${list.entity.extractionMode == 0}">--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=3"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${list.entity.extractionMode == 1}">--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=3"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise><span class="test">...</span></c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--<c:choose>--%>
                                    <%--<c:when test="${list.entity.taskStage ge 4}">--%>
                                        <%--<c:if test="${list.entity.extractionMode == 0}">--%>
                                            <%--<a href="<%=path%>/center/enterManualExperiment?taskId=${list.entity.taskId}&taskStage=4"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                        <%--<c:if test="${list.entity.extractionMode == 1}">--%>
                                            <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=4"--%>
                                               <%--class="fa fa-check-circle"></a>--%>
                                        <%--</c:if>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise><span class="test">...</span></c:otherwise>--%>
                                <%--</c:choose>--%>
                            <%--</td>--%>
                            <%--<td>2019/02/12</td>--%>
                            <%--<td>进行中</td>--%>
                            <%--<td>提取</td>--%>
                            <%--<td>--%>
                                <%--<c:if test="${list.entity.taskStage == 1}">--%>
                                    <%--<c:if test="${list.entity.extractionMode == 0}">--%>
                                        <%--<a href="<%=path%>/center/enterManualExperiment?taskId=${list.entity.taskId}&taskStage=${list.entity.taskStage}"--%>
                                           <%--target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                                        <%--</a>--%>
                                    <%--</c:if>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${list.entity.taskStage == 1}">--%>
                                    <%--<c:if test="${list.entity.extractionMode != 0}">--%>
                                        <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=${list.entity.taskStage}"--%>
                                           <%--target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                                        <%--</a>--%>
                                    <%--</c:if>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${list.entity.taskStage != 1}">--%>
                                    <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=${list.entity.taskStage}"--%>
                                       <%--target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                                    <%--</a>--%>
                                <%--</c:if>--%>
                                <%--<a href="<%=path%>/center/enterExperiment?taskId=${list.entity.taskId}&taskStage=${list.entity.taskStage}"--%>
                                   <%--target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">--%>
                                <%--</a>--%>
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
            <div class="modal-body" style="text-align: center">
                <a href="<%=path%>/center/manualExtraction" target="ifm" class="btn btn-blue">手动提取</a>
                <a href="<%=path%>/center/extractTest" target="ifm" class="btn btn-red">自动提取</a>
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
            hrefFormer: '<%=path%>/handwork/manualExtraction',
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
    })

    function cz(){
        $("#taskNo").val("")
        $("#boardNo").val("")
        $("#taskPerson").val("")
        $("#taskStartDatetim").val("")
        $("#taskEndDatetim").val("")
        $("#caseNo").val("")
        $("#sampleNo").val("")
        $("#taskStatus").val("")
        $("#inspectionType").val("")
        $("#extractionMode").val("")
        $("#page").val(1);
        $('#consignationForm').submit();
    }
</script>
</body>

</html>