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
                <form id="consignationForm" action="<%=path %>/center/appraisalManage" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件名称</label>
                                <input type="text" id="caseName" name="entity.caseName" value="${query.entity.caseName}"
                                       class="form-control"
                                       placeholder="请输入案件名称">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件编号</label>
                                <input type="text" class="form-control" placeholder="请输入案件编号" id="caseNo" name="entity.caseNo" value="${query.entity.caseNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件性质</label>
                                <div class="select">
                                    <select id="caseProperty" name="entity.caseProperty"
                                            value="${query.entity.caseProperty}" class="form-control">
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
                                <label>案件类型</label>
                                <div class="select">
                                    <select id="caseType" name="entity.caseType"
                                            value="${query.entity.caseType}" class="form-control">
                                        <option value="">全部</option>
                                        <c:forEach items="${caseTypeList}" var="list" varStatus="cs">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${list.dictCode eq query.entity.caseType}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
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
                                        <input type="text" id="delegateStartDatetime" name="delegateStartDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.delegateStartDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择委托时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="delegateEndDatetime" name="delegateEndDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.delegateEndDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择委托时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>委托人</label>
                                <input type="text" id="delegator1Name" name="delegator1Name"
                                       value="${query.delegator1Name}" class="form-control" placeholder="请输入委托人">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件状态</label>
                                <div class="select">
                                    <select id="status" name="status" value="${query.status}"
                                            class="form-control">
                                        <option value="" selected>全部</option>
                                        <c:forEach items="${caseStatusList}" var="list" varStatus="cs">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${list.dictCode eq query.status}">selected</c:if>>${list.dictName}</option>
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
                                <input type="text" class="form-control" placeholder="请输入案件编号" id="xkANo"
                                       name="entity.xkANo" value="${query.entity.xkANo}">
                            </div>
                        </div>

                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>受理起止时间</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptStartDatetime" name="acceptStartDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.acceptStartDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择受理时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="acceptEndDatetime" name="acceptEndDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.acceptEndDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择受理时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>受理人</label>
                                <div class="select">
                                    <select id="acceptorId" name="acceptorId" value="${query.acceptorId}"
                                            class="form-control">
                                        <option value="1001" selected>全部</option>
                                        <c:forEach items="${amPersonalInfoVoList}" var="list" varStatus="cs">
                                            <option value="${list.entity.personalId}"
                                                    <c:if test="${list.entity.personalId eq query.acceptorId}">selected</c:if>>${list.entity.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <a href="<%=path%>/center/appraisalManage" class="btn btn-blue-border"  >重置</a>
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
                <div>案件列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>案件编号</th>
                        <th>案件名称</th>
                        <th>案件性质</th>
                        <th>案发时间</th>
                        <th>委托单位</th>
                        <th>委托人</th>
                        <th>委托时间</th>
                        <th>案件状态</th>
                        <th>受理时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="caseInfoListTbody">
                    <c:forEach items="${caseInfoList}" var="list" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${list.entity.caseNo}</td>
                            <td  title="${list.entity.caseName}">
                                <c:if test="${fn:length(list.entity.caseName) gt 13}">
                                    <a href="<%=path%>/center/vewDetailsCase?caseId=${list.entity.caseId}&consignmentId=${list.consignmentId}"
                                       target="ifm"  > ${fn:substring(list.entity.caseName,0,12)} ...</a>
                                </c:if>
                                <c:if test="${fn:length(list.entity.caseName) lt 14}">
                                    <a href="<%=path%>/center/vewDetailsCase?caseId=${list.entity.caseId}&consignmentId=${list.consignmentId}"
                                       target="ifm"  > ${list.entity.caseName}</a>
                                </c:if>
                            </td>
                            <td>${list.casePropertyName}</td>
                            <td><fmt:formatDate value="${list.entity.caseDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>${list.delegateOrgName}</td>
                            <td>${list.delegator1Name}、${list.delegator2Name}</td>
                            <td><fmt:formatDate value="${list.delegateDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${list.status eq '02'}">在检验</c:when>
                                    <c:when test="${list.status eq '03'}">已完成</c:when>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${list.acceptDatetime}" pattern="yyyy-MM-dd"/></td>
                            <td>
                                <input type="hidden" id="caseId" name="caseId" value="${list.entity.caseId}">
                                <input type="hidden" id="consignmentId" name="consignmentId" value="${list.consignmentId}">
                                <%--<button name="generateIdentifyBook" class="btn-icon btn-icon-blue  btn-icon-bianji-blue">生成鉴定书</button>--%>
                                <button name="generateIdentifyBook"class="btn down btn-blue-border">
                                        <i class="fa fa-download" aria-hidden="true">
                                    生成鉴定书</i></button>
                                <button class="btn btn-blue" type="button" name="printFilesPhotos">打印资质照片</button>
                                <%--<a href="<%=path%>/dowmFileController/printFilesPhotos?consignmentId=${list.consignmentId}"--%>
                                   <%--target="ifm"  class="btn btn-green btn-xs">资质照片</a>--%>
                                <input type="button" class="btn-icon btn-icon-red  btn-icon-dayin-red dybtn" id="${list.consignmentId}" value="案件打印"/>
                                <%--<a href="<%=path%>/center/caseFilePrint?consignmentId=${list.consignmentId}" target="ifm" class="btn-icon btn-icon-red  btn-icon-dayin-red">案件打印</a>--%>
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
        'use strict';

        $("#addInformant").on("click", function(){
            $("#page").val(1);
            $('#consignationForm').submit();
        });

        $("button[name='generateIdentifyBook']","#caseInfoListTbody").on("click",function(){
            if (!confirm("是否生成鉴定书！")) {
                return false;
            }

            var caseId = $("input[name='caseId']",$(this).parent()).val();
            $.ajax({
                url:"<%=path%>/center/generateIdentifyBook?caseId=" + caseId,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        console.log(data.success)
                        location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));
                    }else {
                        alert("生成失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("生成失败！");
                }
            });
        });

        $("button[name='printFilesPhotos']","#caseInfoListTbody").on("click",function(){
            var consignmentId =$("#consignmentId").val();

            $.ajax({
                url:"<%=path%>/dowmFileController/printFilesPhotos",
                data:{"consignmentId":consignmentId},
                type:"post",
                dataType:"json",
                success:function(data){

                    if(data){

                    }else if(!data){
                        alert("打印资质照片失败！");
                    }

                }
//            error:function(e) {
//                debugger;
//                alert("下载失败！");
//            }
            });


        })


        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/center/appraisalManage',
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

        $(".dybtn").on("click",function(){
            var e = $(this).attr("id");
            location.href="<%=path%>/center/caseFilePrint?consignmentId="+e;
        })
    })
</script>
</body>

</html>