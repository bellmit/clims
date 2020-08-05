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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
      .panel-body>span{
          color: #f65c52;
      }
      tbody tr td:nth-last-child(1){
          padding: 0px !important;
      }
        td .btn{
            height: 100%;
            width: 33.333%;
            margin: 0px !important;
            float: left;
            border-radius: 0px;
            background: #fff;
        }
      td .btn:nth-child(3){
          color:#296fff ;
      }
      td .btn:nth-child(3):hover,
      td .btn:nth-child(3):active,
      td .btn:nth-child(3):focus{
        background: #d6ebff;
      }
      td .btn:nth-child(4){
          color:#f65c52 ;
      }
      td .btn:nth-child(4):hover,
      td .btn:nth-child(4):active,
      td .btn:nth-child(4):focus{
          background: #ffe4e3;
      }
      td .btn:nth-child(5){
          color:#ffb802 ;
      }
      td .btn:nth-child(5):hover,
      td .btn:nth-child(5):active,
      td .btn:nth-child(5):focus{
          background: #FFF2D9;
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
                <form id="consignationForm" action="<%=path %>/queryCompareResult/queryCompareResultDetail" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>源案件名称</label>
                                <input type="text" id="caseaName" name="caseaName" value="${matchSameResult.caseaName}"
                                       class="form-control"
                                       placeholder="请输入源案件名称">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检材名称</label>
                                <input type="text" class="form-control" placeholder="请输入样本名称" id="sampleaName" name="sampleaName" value="${matchSameResult.sampleaName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检验人</label>
                                <div class="select">
                                    <select id="firstChecker" name="firstChecker"
                                            value="${matchSameResult.firstChecker}" class="form-control">
                                        <option value="">请选择</option>
                                        <c:forEach items="${amPersonalInfoList}" var="list" varStatus="cs">
                                            <option value="${list.personalId}"
                                                    <c:if test="${list.personalId eq matchSameResult.firstChecker}">selected</c:if>>${list.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="groupNo" id="groupNo" value="${groupNo}">
                                <input type="hidden" name="groupId" id="groupId" value="${groupId}">
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
                <div>查看详情</div>
            </div>
            <div class="panel-body">
               <span> ${groupNo}串并案列表</span>
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>源案件名称</th>
                        <th>检材名称</th>
                        <th>对中案件</th>
                        <th>对中样本</th>
                        <th>样本类型</th>
                        <th>检验人</th>
                        <th>状态</th>
                        <th>提交时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${matchResultList}" var="matchResult" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${matchResult.caseaName}</td>
                            <td>${matchResult.sampleaName}</td>
                            <td>${matchResult.casebName}</td>
                            <td>${matchResult.samplebName}</td>
                            <td>${matchResult.instoredType}</td>
                            <td>${matchResult.firstChecker}</td>
                            <c:if test="${matchResult.compareStatus == '0'}">
                                <td>待复核</td>
                            </c:if>
                            <c:if test="${matchResult.compareStatus == '1'}">
                                <td>复核</td>
                            </c:if>
                            <c:if test="${matchResult.compareStatus == '2'}">
                                <td>确认比中</td>
                            </c:if>
                            <c:if test="${matchResult.compareStatus == '3'}">
                                <td>解除关联</td>
                            </c:if>
                            <c:if test="${matchResult.compareStatus == null}">
                                <td></td>
                            </c:if>
                            <td><fmt:formatDate value='${matchResult.createDatetime}' pattern='yyyy-MM-dd '/></td>
                            <td>
                                <input type="hidden" name="id" value="${matchResult.id}">
                                <input type="hidden" name="groupId" value="${matchResult.groupId}">
                                <button type="button" name="confirmCompare" class="btn">确认比中</button>
                                <button type="button" name="relieveRelevance" class="btn">解除关联</button>
                                <button type="button" name="queryCompareCondition" class="btn">查看详情</button>
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
<script src="<%=path%>/js/entrustCurrency.js"></script>
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
            hrefFormer: '<%=path%>/queryCompareResult/queryCompareResultDetail',
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

        $("button[name='queryCompareCondition']").on("click", function () {
            var id = $(this).siblings("input[name='id']").val()
            location.href = "<%=path%>/queryCompareResult/queryCompareCondition?id=" + id;
        })

        //解除关联
        $("button[name='relieveRelevance']").on("click", function () {
            var id = $(this).siblings("input[name='id']").val()
            var groupId = $(this).siblings("input[name='groupId']").val()
            //location.href = "<%=path%>/queryCompareResult/relieveRelevance?id=" + id
            $.ajax({
                url:"<%=path%>/queryCompareResult/relieveRelevance?id=" + id,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/queryCompareResult/queryCompareResultDetail?groupId=" + groupId
                    }else {
                        alert("生成失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("生成失败！");
                }
            });
        })

        //比中
        $("button[name='confirmCompare']").on("click", function () {
            var id = $(this).siblings("input[name='id']").val()
            //location.href = "<%=path%>/queryCompareResult/confirmCompare?id=" + id;
            var groupId = $(this).siblings("input[name='groupId']").val()
            $.ajax({
                url:"<%=path%>/queryCompareResult/confirmCompare?id=" + id,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/queryCompareResult/queryCompareResultDetail?groupId=" + groupId
                    }else {
                        alert("生成失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("生成失败！");
                }
            });
        })

        //重置
        $("button[name='reset']").on("click", function () {
            var groupId = $(this).siblings("input[name='groupId']").val()
            var groupNo = $(this).siblings("input[name='groupNo']").val()
            location.href = "<%=path%>/queryCompareResult/queryCompareResultDetail?groupId=" + groupId+"&groupNo="+groupNo
        })
    })
</script>
</body>

</html>