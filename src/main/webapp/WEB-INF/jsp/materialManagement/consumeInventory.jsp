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
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/supplies/consumeInventory" class="form-horizontal tasi-form" method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>编号</label>
                                <input type="text" name="suppliesNo" value="${query.suppliesNo}"
                                       class="form-control" placeholder="请输入条码号">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" placeholder="请输入出库名称" name="suppliesName"
                                       value="${query.suppliesName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>型号</label>
                                <input type="text" class="form-control" placeholder="请输入出库名称" name="suppliesModel"
                                       value="${query.suppliesModel}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>实验阶段</label>
                                <select class="form-control " name="experimentalStage">
                                    <option value="">请选择</option>
                                    <c:forEach items="${sampleTypeList}" var="list">
                                        <option value="${list.dictCode}"
                                                <c:if test="${list.dictCode eq query.experimentalStage}">selected</c:if>>
                                                ${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <%--<input type="hidden" name="page" id="page" value="${pageInfo.page}"/>--%>
                                <button class="btn btn-blue" id="queryBtn" type="submit" id="addInformant">查询</button>
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
                <div>试剂耗材列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>实验阶段</th>
                        <th>品牌</th>
                        <th>型号</th>
                        <th>价格</th>
                        <th>厂商</th>
                        <th>库存</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${suppliesInfoList}" var="infolist" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${infolist.suppliesNo}
                                <input type="hidden" name="suppliesNo"
                                       value="${infolist.suppliesNo}"></td>
                            <td>${infolist.suppliesName}
                                <input type="hidden" name="suppliesName"
                                       value="${infolist.suppliesName}"></td>
                            <td><c:forEach items="${sampleTypeList}" var="list">
                                <c:if test="${infolist.experimentalStage eq list.dictCode}">${list.dictName}</c:if>
                                </c:forEach>
                                <input type="hidden" name="experimentalStage" value="${list.dictCode}">
                            </td>
                            <td>${infolist.suppliesBrand}
                                <input type="hidden" name="suppliesBrand"
                                       value="${infolist.suppliesBrand}"></td>
                            <td>${infolist.suppliesModel}
                                <input type="hidden" name="suppliesModel"
                                       value="${infolist.suppliesModel}"></td>
                            <td>${infolist.suppliesPrice}
                                <input type="hidden" name="suppliesPrice"
                                       value="${infolist.suppliesPrice}"></td>
                            <td>${infolist.suppliesFirm}
                                <input type="hidden" name="suppliesFirm"
                                       value="${infolist.suppliesFirm}"></td>
                            <td><%--<c:forEach items="${suppliesList}" var="list">
                                <c:if test="${infolist.id eq list.suppliesId}">${list.storageNum}</c:if>
                                </c:forEach>--%>
                                    ${infolist.storageNum}
                                <input type="hidden" name="storageNum" value="${list.storageNum}"></td>
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

        /*kkpager.generPageHtml({
         pno: ${pageInfo.page},
         //总页码
         total: ${pageInfo.pageCount},
         //总数据条数
         totalRecords: ${pageInfo.allRecordCount},
         //链接前部
         hrefFormer: '<%=path%>/center/testProcess',
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
         });*/


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
