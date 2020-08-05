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
            /*text-align: center;*/
            font-size: 25px;
            margin-left: -2px;
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
                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/handwork/manualSy">
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/handwork/manualSy"
                      class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>上样条码号</label>
                                <input type="text" id="boardNo" name="entity.boardNo" value="${labSyInfoVo.entity.boardNo}"
                                       class="form-control"
                                       placeholder="请输入上样条码号">
                            </div>
                        </div>


                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>任务日期</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptStartDatetime" name="acceptStartDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${labSyInfoVo.acceptStartDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择任务时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptEndDatetime" name="acceptEndDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${labSyInfoVo.acceptEndDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择任务时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>任务状态</label>
                                <div class="select">
                                    <select id="syStage" name="entity.syStage"
                                            value="" class="form-control">
                                        <option value="" selected>全部</option>
                                        <option value="0"
                                                <c:if test="${labSyInfoVo.entity.syStage=='0'}">selected</c:if> >进行中
                                        </option>
                                        <option value="1" <c:if test="${labSyInfoVo.entity.syStage=='1'}">selected</c:if>>
                                            已完成
                                        </option>
                                    </select>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-3 seachInputBox">
                            <div class="form-group seachButtonBox">
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
                <button class="btn btn-yellow pull-right" style="font-weight: 600;margin-top: -7px; margin-right: 15px;"
                id="addSyinfo">新增
                </button>

            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>上样编号</th>
                        <th>上样条码号</th>
                        <th>上样日期</th>
                        <th>上样状态</th>
                        <th>检材数量</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${labSyInfoVoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.entity.syNo}</td>
                            <td>${list.entity.boardNo}</td>
                            <td><fmt:formatDate value="${list.entity.createDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <c:if test="${list.entity.syStage eq '0'}">进行中</c:if>
                                <c:if test="${list.entity.syStage eq '1'}">已完成</c:if>
                            </td>
                            <td>${list.entity.sampleCount}</td>
                            <td>
                                <c:if test="${list.entity.syStage eq '0'}">
                                    <a href="<%=path%>/handwork/syExperiment?syId=${list.entity.syId}" target="ifm" class="glyphicon glyphicon-log-in btn-icon-red">
                                    </a>
                                </c:if>
                                <c:if test="${list.entity.syStage eq '1'}">
                                    <a href="<%=path%>/handwork/syExperiment?syId=${list.entity.syId}" target="ifm" class="fa fa-check-circle">
                                    </a>
                                </c:if>
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

        //添加上样任务
        $("#addSyinfo").on("click", function () {
            location.href = "<%=path%>/handwork/syPuzzleExperiment"
        });
    })

    function cz(){
        $("#taskNo").val("")
        $("#acceptStartDatetime").val("")
        $("#acceptEndDatetime").val("")
        $("#syStage").val("")
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