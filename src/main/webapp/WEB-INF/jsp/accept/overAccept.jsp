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
        .btn-red:hover{
            width: 100%;
        }
        #videoBox a{
            padding-top: 10px;padding-bottom: 10px
        }
        #videoBox button{
            padding: 10px 26px;
        }
        tbody .btn{
            background: #ff5a56;
            color: #fff;
        }
        tbody .btn:hover{
            display: block;
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
                <form id="consignationForm" action="<%=path %>/center/overtimeAccept" class="form-horizontal tasi-form"
                      method="post">
                <div class="row">
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>案件名称</label>
                            <input type="text" id="caseName" name="entity.caseName" value="${query.entity.caseName}" class="form-control"
                                   placeholder="请输入案件名称">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>案件性质</label>
                            <div class="select">
                                <select id="caseProperty" name="entity.caseProperty" value="${query.entity.caseProperty}" class="form-control">
                                    <option value="">全部</option>
                                    <c:forEach items="${casePropertyList}" var="list" varStatus="cs">
                                        <option value="${list.dictCode}"
                                                <c:if test="${list.dictCode eq query.entity.caseProperty}">selected</c:if>>${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>现场勘验号</label>
                            <input type="text" id="caseXkNo" name="entity.caseXkNo" value="${query.entity.caseXkNo}" class="form-control" placeholder="请输入现场勘验号">
                        </div>
                    </div>

                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>现勘A号</label>
                            <input type="text" id="xkANo" name="entity.xkANo" value="${query.entity.xkANo}"
                                   class="form-control" placeholder="请输入A号">
                        </div>
                    </div>

                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>委托单位</label>
                            <div class="select">
                                <select id="delegateOrgCode" name="delegateOrgCode" value="${query.delegateOrgCode}"
                                        class="form-control">
                                    <option value="">全部</option>
                                    <c:forEach items="${orgInfoList}" var="list" varStatus="cs">
                                        <option value="${list.orgCode}"
                                                <c:if test="${list.orgCode eq query.delegateOrgCode}">selected</c:if>>${list.orgName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>委托时间</label>
                            <div class="row">
                                <div class="col-md-5 nopadding">
                                    <input type="text" id="delegateStartDatetime" name="delegateStartDatetime" class="form-control form_datetime"
                                           value="<fmt:formatDate value='${query.delegateStartDatetime}' pattern='yyyy-MM-dd'/>" placeholder="请选择委托时间"
                                           readonly="readonly">
                                </div>
                                <div class="col-md-2" style="margin-top: 7px;">至</div>
                                <div class="col-md-5 nopadding">
                                    <input type="text" id="delegateEndDatetime" name="delegateEndDatetime" class="form-control form_datetime"
                                           value="<fmt:formatDate value='${query.delegateEndDatetime}' pattern='yyyy-MM-dd'/>" placeholder="请选择委托时间"
                                           readonly="readonly">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>委托人</label>
                            <input type="text" id="delegator1Name" name="delegator1Name" value="${query.delegator1Name}" class="form-control" placeholder="请输入委托人">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>人员姓名</label>
                            <input type="text" id="personName" name="personName" value="${query.personName}"
                                   class="form-control" placeholder="请输入人员姓名">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>人员身份证</label>
                            <input type="text" id="personIdCard" name="personIdCard" value="${query.personIdCard}"
                                   class="form-control" placeholder="请输入人员身份证">
                        </div>
                    </div>
                    <div class="col-md-4 seachInputBox">
                        <div class="form-group seachButtonBox">
                            <input type="hidden" name="visitNum" value="${visitNum}">
                            <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                            <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                            <%--<a href="<%=path%>/center/overtimeAccept" class="btn btn-blue-border reset" target="ifm" style="margin-left: 15px;">重置</a>--%>
                            <input type="button" name="resetBtn" class="btn btn-blue-border" value="重置" />
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
                <div>待受理案件</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>案件名称</th>
                        <th>案件性质</th>
                        <th>案发时间</th>
                        <th>委托单位</th>
                        <th>委托人</th>
                        <th>委托时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${caseInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.entity.caseName}</td>
                            <td>${list.casePropertyName}</td>
                            <td><fmt:formatDate value="${list.entity.caseDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>${list.delegateOrgName}</td>
                            <td>${list.delegator1Name}、${list.delegator2Name}</td>
                            <td><fmt:formatDate value="${list.delegateDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="hidden" id="caseId" name="caseId" value="${list.entity.caseId}">
                                <input type="hidden" id="consignmentId" name="consignmentId" value="${list.consignmentId}">
                                <a href="<%=path%>/center/upload?caseId=${list.entity.caseId}&consignmentId=${list.consignmentId}" target="ifm" class="btn btn-red btn-sm" >受理</a>
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

<div class="modal fade popBox bigBox" id="confirmAccept" tabindex="-1" role="dialog" style="padding-top: 10%;" data-backdrop="static"
     aria-labelledby="myModalLabel">
    <form action="" id="addMaterialEvidencerlSample_form">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" style="font-weight:bold;">受理账号确认</h4>
                </div>
                <div class="modal-body">
                    <div class="col-md-12" style="padding-top: 3%;">
                        <div class="form-group" style="padding-left: 30%;opacity:0.5;color:black;">
                            <div>
                                <h2 style="font-size: 20px;font-weight:bold;color: black">当前受理人为：${loaUserInfo.amPersonalInfo.fullName}</h2>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="padding-top: 15%">
                    <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample" type="button"
                            data-dismiss="modal">开始受理
                    </button>
                    <button type="button" class="btn btn-lang btn-blue-border" name="getOutBtn" >切换受理账号</button>
                </div>
            </div>
        </div>
    </form>
</div>

<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        //受理账号确认弹框
        var visitNum = $("input[name='visitNum']").val();
        if (visitNum == 1){
            $("#confirmAccept").modal('show')
            $("input[name='visitNum']").val(0);
        }

        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/overtimeAccept',
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

        $("#addInformant").on("click", function () {
            $("#page").val(1);
            $('#consignationForm').submit();
        });

        //切换受理人
        $("button[name='getOutBtn']").on("click", function () {
            top.location.href = "<%=path%>/loginOut";
        })

        $("input[name='resetBtn']").on("click", function () {
            $("#consignationForm").find('input[type=text],select,input[type=hidden]').each(function () {
                $(this).val('');
            });
            $("input[name='visitNum']").val(0);
        });

    })
</script>
</body>

</html>