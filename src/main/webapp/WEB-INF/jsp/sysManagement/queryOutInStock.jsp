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
    </style>
</head>

<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/reagentOutInStock">
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/center/reagentOutInStock"
                      class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <%--<div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>条码号</label>
                                <input type="text" name="barcode" value="${query.barcode}"
                                       class="form-control" placeholder="请输入条码号">
                            </div>
                        </div>--%>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" placeholder="请输入出库名称" name="reagentName"
                                       value="${query.reagentName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>操作时间</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" name="storageDatetimeStart"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.storageDatetimeStart}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择操作时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="4" name="storageDatetimeEnd"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.storageDatetimeEnd}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择操作时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>
                            <div class="col-md-4 seachInputBox">
                                <div class="form-group">
                                    <label>操作类型</label>
                                    <div class="form-group">
                                        <label class="checkbox-inline">
                                            <input id="recordInType" name="recordType" type="radio" value="0" <c:if test="${query.recordType eq '0'}">checked</c:if>/>入库
                                        </label>
                                        <label class="checkbox-inline">
                                            <input id="recordOutType" name="recordType" type="radio" value="1" <c:if test="${query.recordType eq '1'}">checked</c:if> />出库
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
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
                <div>查询结果</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <%--<th>条码号</th>--%>
                        <th>名称</th>
                        <th>数量</th>
                        <th>操作人</th>
                        <th>操作类型</th>
                        <th>有效日期</th>
                        <th>入库时间</th>
                        <th>备注</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${OutgoInfoList}" var="infolist" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <%--<td>${infolist.barcode}--%>
                                <%--<input type="hidden" name="barcode"--%>
                                       <%--value="${infolist.barcode}"></td>--%>
                            <td>${infolist.reagentName}
                                <input type="hidden" name="reagentName"
                                       value="${infolist.reagentName}"></td>
                            <td>${infolist.storageNum}
                                <input type="hidden" name="storageNum"
                                       value="${infolist.storageNum}"></td>
                            <td>${infolist.storagePerson}
                                <input type="hidden" name="storagePerson"
                                       value="${infolist.storagePerson}"></td>
                            <td><c:if test="${infolist.recordType eq '0'}">入库</c:if>
                                <c:if test="${infolist.recordType eq '1'}">出库</c:if>
                                <input type="hidden" name="recordType"
                                       value="${infolist.recordType}"></td>
                            <td><fmt:formatDate value="${infolist.effectiveDatetime}" pattern="yyyy-MM-dd"/>
                                <input type="hidden" name="effectiveDatetime"
                                       value="<fmt:formatDate value="${infolist.effectiveDatetime}" pattern="yyyy-MM-dd"/>"></td>
                            <td><fmt:formatDate value="${infolist.storageDatetime}" pattern="yyyy-MM-dd"/>
                                <input type="hidden" name="storageDatetime"
                                       value="<fmt:formatDate value="${infolist.storageDatetime}" pattern="yyyy-MM-dd"/>"></td>
                            <td>${infolist.storageRemark}
                                <input type="hidden" name="storageRemark"
                                       value="${infolist.storageRemark}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../pagefoot.jsp" %>
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
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script>
    $(function () {

        $("#addInformant").on("click", function () {
            $("#page").val(1);
            $('#consignationForm').submit();
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
