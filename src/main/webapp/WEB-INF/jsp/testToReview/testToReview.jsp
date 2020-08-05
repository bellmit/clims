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
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/center/testToReview" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                        <div class="form-group">
                            <label>上传时间</label>
                            <div class="row">
                                <div class="col-md-5 nopadding">
                                    <input type="text" id="auditStartDatetime"
                                           class="form-control form_datetime"
                                           value="<fmt:formatDate value='${query.auditStartDatetime}' pattern='yyyy-MM-dd'/>"
                                           placeholder="请选择委托时间" name="auditStartDatetime"
                                           readonly="readonly">
                                </div>
                                <div class="col-md-2" style="margin-top: 7px;">至</div>
                                <div class="col-md-5 nopadding">
                                    <input type="text" id="auditEndDatetime"
                                           class="form-control form_datetime"
                                           value="<fmt:formatDate value='${query.auditEndDatetime}' pattern='yyyy-MM-dd'/>"
                                           placeholder="请选择委托时间" name="auditEndDatetime"
                                           readonly="readonly">
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>审核人</label>
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
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>审核状态</label>
                                <div class="select">
                                    <select id="auditStatus" name="entity.auditStatus"
                                            value="${query.entity.auditStatus}" class="form-control">
                                        <option value="">全部</option>
                                        <option value="1" <c:if test="${query.entity.auditStatus eq '1'}">selected</c:if>>已审核</option>
                                        <option value="0" <c:if test="${query.entity.auditStatus eq '0'}">selected</c:if>>未审核</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>测序仪</label>
                                <input type="text" class="form-control" placeholder="请输入" name="entity.machineNo" value="${query.entity.machineNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>上样板号</label>
                                <input type="text" class="form-control" placeholder="请输入" id="boardNo" name="entity.boardNo" value="${query.entity.boardNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检材编号</label>
                                <input type="text" class="form-control" placeholder="请输入" id="sampleNo" name="sampleNo" value="${query.sampleNo}">
                            </div>
                        </div>
                      <%--  <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label type="hand">未审核数</label>
                                <input type="text" class="form-control" placeholder="请输入" id="reviewCount" name="entity.reviewCount" value="${query.entity.reviewCount}">
                            </div>
                        </div>--%>
                       <%-- <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>DNA编号</label>
                                <input type="text" class="form-control" placeholder="请输入" value="">
                            </div>
                        </div>--%>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <button type="button" name="reset" class="btn btn-blue-border">重置</button>
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
                <div>上样板列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>上样板号</th>
                        <th>上传时间</th>
                        <th>审核人</th>
                        <th>审核状态</th>
                        <th>样本总数</th>
                        <%--<th>审核样本数</th>
                        <th>未审核样本数</th>--%>
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
                            <td>${list.entity.auditor}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.entity.auditStatus eq 1 && list.entity.reviewCount ne '0'}">已审核</c:when>
                                    <c:otherwise>未审核</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${list.entity.sampleCount}</td>
                            <%--<td>${list.entity.reviewCount}</td>
                            <%--<td>${list.entity.reviewCount}</td>
                            <td>${list.notReviewCount}</td>--%>
                            <td>${list.entity.machineNo}</td>
                            <td>
                                <%--<input type="hidden" id="caseId" name="caseId" value="${list.entity.caseId}">--%>
                                <a href="<%=path%>/center/reviewDetails?id=${list.entity.id}"
                                   target="ifm"  class="btn btn-green btn-xs">确认复核</a>
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

        $("#addInformant").on("click", function(){
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
            hrefFormer: '<%=path%>/center/testToReview',
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

        //重置
        $("button[name='reset']").on("click", function () {
            location.href = "<%=path%>/center/testToReview";
        })
    })
</script>
</body>

</html>