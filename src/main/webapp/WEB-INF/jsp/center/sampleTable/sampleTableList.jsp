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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../../newLink.jsp" %>
    <%@ include file="../../include.jsp" %>
</head>
<body class="flat-blue">
<div class="app-container">
    <div class="row content-container">
       <%-- <%@ include file="../../common/platformName.jsp" %>--%>
            <%--<%@ include file="../../common/menuManagement.jsp" %>--%>
                <div class="container-fluid Scrollbar">
                    <div class="side-body padding-top">
                        <div class="container-style">
                            <div class="row posi-rel">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                    <div class="top-tag-box">
                                        <div class="table-tag" style="width: 50% !important;">
                                            <ul class="active-ul">
                                                <li class="active-li">
                                                    样本表管理
                                                </li>
                                               <%-- <li>
                                                    提取表管理
                                                </li>--%>
                                                <%--<li>
                                                    扩增表管理
                                                </li>
                                                <li>
                                                    电泳表管理
                                                </li>--%>
                                                <input type="hidden" id="tabValue" value="${query.tabValue}">
                                                <input type="hidden" id="statusType" value="">
                                            </ul>
                                        </div>
                                        <div class="seach-right-box" style="width: 50% !important;">
                                            <div>
                                                <input type="text" placeholder="请输入板号" id="boardNo" onkeyup="this.value=this.value.replace(/[, ]/g,'')">
                                                <input type="text" placeholder="请输入检材编号" id="sampleNo" onkeyup="this.value=this.value.replace(/[, ]/g,'')">
                                                <button class="btn btn-primary" id="searchBtn">查 询</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                    <div id="display-sy01">
                                        <div class="con-nav-top">
                                            <div class="con-nav-top-left">
                                                <ul class="ul-btns margin-right-20px">
                                                    <li>
                                                        任务列表
                                                    </li>
                                                    <input type="hidden" name="sampleBoardNo" id="sampleBoardNo">
                                                    <input type="hidden" name="loginName" id="loginName" value="${loginName}">
                                                    <li type="button" data-toggle="modal" id='addSampleTableBtn'
                                                        data-target="#modalPrimary">
                                                        <span><img src="<%=path%>/automatedWork/img/1104.png" alt=""></span>
                                                        <span>创建样本板</span>
                                                    </li>
                                                    <li type="button" data-toggle="modal" id='editSampleTableBtn'
                                                        data-target="#modalPrimary">
                                                        <span><img src="<%=path%>/automatedWork/img/1103.png" alt=""></span>
                                                        <span>修改样本板</span>
                                                    </li>
                                                    <li type="button" id='deleteSampleTableBtn'>
                                                        <span><img src="<%=path%>/automatedWork/img/1102.png" alt=""></span>
                                                        <span>批量删除</span>
                                                    </li>
                                                    <li class="file-con" type="button" data-toggle="modal" id='importSampleTableBtn'>
                                                        <span><img src="<%=path%>/automatedWork/img/1101.png" alt=""></span>
                                                        <span>导入样本表</span>
                                                    </li>
                                                    <li type="button" id='exportCSV2'>
                                                        <span>
                                                            <img src="<%=path%>/automatedWork/img/dc001.png" alt="">
                                                        </span>
                                                        <span>导出CSV</span>
                                                    </li>
                                                    <%--<li class="border-li" type="button" id="createSampleTableBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1123.png" alt=""></span>
                                                        <span>创建DNA提取表</span>
                                                    </li>
                                                    <li class="border-li" type="button" id="spellCreateSampleTableBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1124.png" alt=""></span>
                                                        <span>拼板并创建DNA提取表</span>
                                                    </li>--%>
                                                    <li class="border-li" type="button" id="createSampleTableBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1123.png" alt=""></span>
                                                        <span>提交到等候提取任务</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="con-nav-top-right">
                                                <div class="table-btn-tag">
                                                    <ul class="table-btn-tag-ul">
                                                        <li class="active-tag">
                                                            全部
                                                        </li>
                                                        <li>
                                                            已提交
                                                        </li>
                                                        <li>
                                                            未提交
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allSampleTableChecked" id="allSampleTableChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th>板名</th>
                                                    <%--<th>状态</th>--%>
                                                    <th>孔数</th>
                                                    <th>默认预设体积</th>
                                                    <th>提交状态</th>
                                                    <th>创建者</th>
                                                    <th>创建时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="sampleTableVoListTbody">
                                                <c:forEach items="${sampleTableVoList}" var="list" varStatus="s">
                                                    <tr>
                                                        <td>
                                                            <input type="hidden" name="id" value="${list.entity.id}"/>
                                                            <input type="hidden" name="boardNo" value="${list.entity.boardNo}"/>
                                                            <input type="hidden" name="holeNum" value="${list.entity.holeNum}"/>
                                                            <input type="hidden" name="elutionDefault" value="${list.entity.elutionDefault}"/>
                                                            <input type="hidden" name="elutionDefaultName" value="${list.elutionDefaultName}"/>
                                                            <input type="hidden" name="createPerson" value="${list.entity.createPerson}"/>
                                                            <input type="hidden" name="isCreate" id="isCreate" value="${list.entity.isCreate}"/>
                                                            <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                            <label class="labelBox">
                                                                <input type="checkbox" name="allSampleTableBox">${s.count}
                                                            </label>
                                                        </td>
                                                        <td>${list.entity.boardNo}</td>
                                                            <%--<td>
                                                                <c:choose>
                                                                    <c:when test="${list.entity.state eq '1'}"><img src="<%=path%>/automatedWork/img/load.png" alt="">开始</c:when>
                                                                    <c:when test="${list.entity.state eq '2'}"><img src="<%=path%>/automatedWork/img/wc.png" alt="">结束</c:when>
                                                                    <c:otherwise>未开始</c:otherwise>
                                                                </c:choose>
                                                            </td>--%>
                                                        <td>${list.entity.holeNum}</td>
                                                        <td>${list.elutionDefaultName}</td>

                                                        <%--<td>${list.entity.extractPlateName}哈哈</td>--%>
                                                        <c:if test="${list.entity.extractPlateName!= null}">
                                                            <td style='color: green; font-weight: bold'>已提交</td>
                                                        </c:if>
                                                        <c:if test="${list.entity.extractPlateName== null}">
                                                            <td>未提交</td>
                                                        </c:if>
                                                        <td>${list.entity.createPerson}</td>
                                                        <td>
                                                            <fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                        </td>
                                                        <td>
                                                            <span class="exportBtn">
                                                                <img src="<%=path%>/automatedWork/img/1990.png" alt="">导出24孔样本表
                                                            </span>
                                                            <c:choose>
                                                                <c:when test="${list.entity.isCreate eq '1'}">
                                                                    <span class="addSampleBtn">
                                                                        <img src="<%=path%>/automatedWork/img/see.png" alt="">查看检材
                                                                    </span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="addSampleBtn">
                                                                        <img src="<%=path%>/automatedWork/img/1123.png" alt="">添加检材
                                                                    </span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div></div>
                                    <div id="display-sy02">
                                        <div class="con-nav-top">
                                            <div class="con-nav-top-left">
                                                <ul class="ul-btns margin-right-5px">
                                                    <li>
                                                        任务列表
                                                    </li>
                                                    <li type="button" id='exportCSV'>
                                                        <span>
                                                            <img src="<%=path%>/automatedWork/img/dc001.png" alt="">
                                                        </span>
                                                        <span>导出CSV</span>
                                                    </li>
                                                    <li type="button" id='exportSampleFile'>
                                                        <span><img src="<%=path%>/automatedWork/img/dc002.png" alt=""></span>
                                                        <span>快速导出上样文件</span>
                                                    </li>
                                                    <li type="button" id="exportExtractSampleTable">
                                                        <span><img src="<%=path%>/automatedWork/img/dc003.png" alt=""></span>
                                                        <span>DNA提取样本表</span>
                                                    </li>
                                                    <li type="button" id="exportExtractSampleRecord">
                                                        <span><img src="<%=path%>/automatedWork/img/dc004.png" alt=""></span>
                                                        <span>导出DNA样本提取记录表</span>
                                                    </li>
                                                    <li type="button" id="deleteExtractBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/tra.png" alt=""></span>
                                                        <span>批量删除</span>
                                                    </li>
                                                    <%--<li class="border-li" type="button" id="createPcrBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1123.png" alt=""></span>
                                                        <span>创建PCR扩增表</span>
                                                    </li>
                                                    <li class="border-li" type="button" id="spellCreatePcrBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1124.png" alt=""></span>
                                                        <span>拼板并创建PCR扩增表</span>
                                                    </li>--%>
                                                    <%--<li class="border-li" type="button" id="addToWaitExtractBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1123.png" alt=""></span>
                                                        <span>增加到等候提取任务列表</span>
                                                    </li>--%>
                                                </ul>
                                            </div>
                                            <%--<div class="con-nav-top-right">
                                                <div class="table-btn-tag">
                                                    <ul class="table-btn-tag-ul">
                                                        <li class="active-tag">
                                                            全部
                                                        </li>
                                                        &lt;%&ndash;<li>
                                                            已生成扩增表
                                                        </li>
                                                        <li>
                                                            未生成扩增表
                                                        </li>&ndash;%&gt;
                                                    </ul>
                                                </div>
                                            </div>--%>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allExtractChecked" id="allExtractChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th>板名</th>
                                                    <th>状态</th>
                                                    <th>孔数</th>
                                                    <th>试剂盒</th>
                                                    <th>设备类型</th>
                                                    <th>提取方法</th>
                                                    <th>提取时间</th>
                                                    <%--<th>扩增表名</th>--%>
                                                    <th>创建者</th>
                                                    <th>创建时间</th>
                                                    <th>操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="extractPlateVoListTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div></div>
                                    <div id="display-sy03">
                                        <div class="con-nav-top">
                                            <div class="con-nav-top-left">
                                                <ul class="ul-btns margin-right-20px">
                                                    <li >
                                                        任务列表
                                                    </li>
                                                    <li type="button" id='exportPcrSampleRecord'>
                                                        <span><img src="<%=path%>/automatedWork/img/1104.png" alt=""></span>
                                                        <span>导出PCR扩增样本表</span>
                                                    </li>
                                                    <li type="button" id='exportPcrWork'>
                                                        <span><img src="<%=path%>/automatedWork/img/1103.png" alt=""></span>
                                                        <span>导出DNA检验记录扩增作业单</span>
                                                    </li>
                                                    <li type="button" id='deletePcrBtn'>
                                                        <span><img src="<%=path%>/automatedWork/img/1102.png" alt=""></span>
                                                        <span>批量删除</span>
                                                    </li>
                                                    <li class="border-li" type="button"  id="createSyBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1123.png" alt=""></span>
                                                        <span>创建STR电泳表</span>
                                                    </li>
                                                    <li class="border-li" type="button" id="spellCreateSyBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1124.png" alt=""></span>
                                                        <span>拼板并创建STR电泳表</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="con-nav-top-right">
                                                <div class="table-btn-tag">
                                                    <ul class="table-btn-tag-ul">
                                                        <li class="active-tag">
                                                            全部
                                                        </li>
                                                        <li>
                                                            已生成电泳表
                                                        </li>
                                                        <li>
                                                            未生成电泳表
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 5%;">
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allPcrChecked" id="allPcrChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th style="width: 8%;">板名</th>
                                                    <th style="width: 5%;">状态</th>
                                                    <th style="width: 4%;">孔数</th>
                                                    <th style="width: 7%;">扩增时间</th>
                                                    <th style="width: 4%;">检验系统</th>
                                                    <th style="width: 11%">扩增体系（微量）</th>
                                                    <th style="width: 11%">扩增体系（常数）</th>
                                                    <th style="width: 4%;">扩增循环</th>
                                                    <th style="width: 4%;">试剂批次</th>
                                                    <th style="width: 4%;">仪器型号</th>
                                                    <th style="width: 8%;">电泳表名</th>
                                                    <th style="width: 5%">创建者</th>
                                                    <th style="width: 7%;">创建时间</th>
                                                    <th style="width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="pcrPlateVoListTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div></div>
                                    <div id="display-sy04">
                                        <div class="con-nav-top">
                                            <div class="con-nav-top-left">
                                                <ul class="ul-btns margin-right-20px">
                                                    <li>
                                                        任务列表
                                                    </li>
                                                    <li type="button" id='exportSySampleRecord'>
                                                        <span><img src="<%=path%>/automatedWork/img/dc001.png" alt=""></span>
                                                        <span>导出STR电泳样本表</span>
                                                    </li>
                                                    <li type="button" id="deleteSyBtn">
                                                        <span><img src="<%=path%>/automatedWork/img/1102.png" alt=""></span>
                                                        <span>批量删除</span>
                                                    </li>
                                                    <li class="border-li" type="button" id="saveSyBtn">
                                                        <span></span>
                                                        <span>保存并开始实验</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="con-nav-top-right">
                                                <div class="table-btn-tag">
                                                    <ul class="table-btn-tag-ul">
                                                        <li class="active-tag">
                                                            全部
                                                        </li>
                                                        <li>
                                                            已生成实验表
                                                        </li>
                                                        <li>
                                                            未生成实验表
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 5%;">
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allSyChecked" id="allSyChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th style="width: 8%;">板名</th>
                                                    <th style="width: 7%;">检测时间</th>
                                                    <th style="width: 5%;">状态</th>
                                                    <th style="width: 7%;">测序仪型号</th>
                                                    <th style="width: 7%;">分子量标记</th>
                                                    <th style="width: 9%">混合比例</th>
                                                    <th style="width: 10%">提取方法</th>
                                                    <th style="width: 7%;">环境温湿度</th>
                                                    <th style="width: 9%;">变性条件</th>
                                                    <th style="width: 5%">创建者</th>
                                                    <th style="width: 7%;">创建时间</th>
                                                    <th style="width: 13%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="syPlateVoListTbody">

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="actionName" id="actionName" value="sampleTableList">
                                <%--<%@ include file="../../common/pagefoot.jsp" %>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
           <%-- <%@ include file="../../common/foot.jsp" %>--%>
        </div>
        <!-- Modal  创建/修改 样本板-->
        <div class="modal fade modal-primary" id="sampleModal" data-backdrop="static"
             data-keyboard="false" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div id="content">创建样本板</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div>
                                <div>
                                    <div class="name-khc">
                                        <p>名称</p>
                                        <p>
                                            <img src="<%=path%>/automatedWork/img/khc.png" alt="">
                                            <span>年-月-日-第几板</span>
                                        </p>
                                    </div>
                                    <input name="boardNoModel" type="text">
                                </div>
                                <div>
                                    <p>孔位</p>
                                    <select name="holeNumModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${programRecordList}" var="list" varStatus="s">
                                            <option value="${list.programType}">${list.programType}</option>
                                        </c:forEach>
                                        <%--<c:forEach items="${programRecordList}" var="list" varStatus="s">
                                            <option value="24">24</option>
                                        </c:forEach>--%>
                                    </select>
                                </div>
                                <div>
                                    <p>默认洗脱体积(ul)</p>
                                    <select name="elutionDefaultModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${elutionDefaultList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div>
                                    <p>创建者</p>
                                    <input type="text" name="createPersonModel" >
                                </div>
                                <div>
                                    <p>创建时间</p>
                                    <input name="createDatetimeModel" type="text">
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="saveSampleBtn">保 存</button>
                            <button class="btn btn-info btn-sm" type="button" id="closeSampleBtn">关 闭</button>
                            <input type="hidden" name="idModel" value=""/>
                            <input type="hidden" name="sampleOperateType" value=""/>
                            <input type="hidden" name="sampleTableRownum" value=""/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 导入样本板-->
        <div class="modal fade modal-primary" id="importSampleTableModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>导入样本表</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div style="display: inline-block;">
                                <label>默认洗脱体积(ul)</label>
                                <div>
                                    <select name="elutionModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${elutionDefaultList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div style="display: inline-block;">
                                <label>选择样本表</label>
                                <div>
                                    <input type="file" name="upFile" id="upFile" multiple="multiple" class="hide"/>
                                    <input type="text" name='textfield' id='textfield' readonly="readonly" style="width: 293px;"/>
                                    <button class="btn btn-info btn-sm" type="button" id="browserBtn"><i class="fa  fa-folder-open"></i> 浏览...</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="saveSampleTableBtn">确认导入</button>
                            <button class="btn btn-info btn-sm" type="button" data-dismiss="modal">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal  修改洗脱体积-->
        <div class="modal fade modal-primary" id="sampleMultipleModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>修改洗脱体积</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div>
                                <div>
                                    <p>默认洗脱体积(ul)</p>
                                    <select name="elutionMultipleModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${elutionDefaultList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" data-dismiss="modal" id="saveMultipleBtn">保 存</button>
                            <button class="btn btn-info btn-sm" data-dismiss="modal" type="button">关 闭</button>
                            <input type="hidden" name="idModel" value=""/>
                            <input type="hidden" name="sampleOperateType" value=""/>
                            <input type="hidden" name="sampleTableRownum" value=""/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal  选择设备类型及试剂盒-->
        <div class="modal fade modal-primary" id="extractModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>选择设备类型及试剂盒</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div>
                                <div>
                                    <p>设备类型</p>
                                    <select name="deviceTypeModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${deviceTypeList}" var="list" varStatus="s">
                                            <option value="${list.dictName}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <%--<div>
                                    <p>试剂盒</p>
                                    <input name="kitNameModel" type="text">
                                </div>--%>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button"  data-dismiss="modal" id="createExtractBtn">保 存</button>
                            <button class="btn btn-info btn-sm" data-dismiss="modal">关 闭</button>
                            <input type="hidden" name="isSpell">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 提取表格 查看详情弹窗 -->
        <div class="modal fade modal-primary" id="sampleExtractModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="modelnewStyle">
                                <div>
                                    <span></span>
                                    <span>板名</span>
                                    <span name="boardNoModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>状态</span>
                                    <span name="stateModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>孔数</span>
                                    <span name="holeNumModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>试剂盒</span>
                                    <span name="kitNameModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>设备类型</span>
                                    <span name="deviceTypeModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>提取方法</span>
                                    <span name="extractMethodModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>提取时间</span>
                                    <span name="extractDatetimeModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增表名</span>
                                    <span name="pcrPlateNameModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建者</span>
                                    <span name="createPersonModelExt"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建时间</span>
                                    <span name="createDatetimeModelExt"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="newstyle">
                            <button class="btn btn-info btn-sm" data-dismiss="modal">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 扩增表格 查看详情弹窗 -->
        <div class="modal fade modal-primary" id="samplePcrModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                            aria-hidden="true">&times;</span></button> -->
                        <!-- <h4 class="modal-title" id="myModalLabel">实验人员权限管理</h4> -->
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="modelnewStyle">
                                <div>
                                    <span></span>
                                    <span>板名</span>
                                    <span name="boardNoModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>状态</span>
                                    <span name="stateModelPcr">结束</span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增时间</span>
                                    <span name="pcrDatetimeModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检验系统</span>
                                    <span name="testSystemModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增体系（微量）</span>
                                    <span name="pcrSystemTraceModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增体系（常数）</span>
                                    <span name="pcrSystemConstantModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增循环</span>
                                    <span name="pcrRunNumModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>试剂批次</span>
                                    <span name="reagentBatchModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>仪器型号</span>
                                    <span name="pcrInstrumentNumModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建者</span>
                                    <span name="createPersonModelPcr"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建时间</span>
                                    <span name="createDatetimeModelPcr"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="newstyle">
                            <!-- <button class="btn btn-info btn-sm">保 存</button> -->
                            <button class="btn btn-info btn-sm" data-dismiss="modal">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 电泳表格 查看详情弹窗 -->
        <div class="modal fade modal-primary" id="sampleSyModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <!-- <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                                aria-hidden="true">&times;</span></button> -->
                        <!-- <h4 class="modal-title" id="myModalLabel">实验人员权限管理</h4> -->
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="modelnewStyle">
                                <div>
                                    <span></span>
                                    <span>板名</span>
                                    <span name="boardNoModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检测时间</span>
                                    <span name="testDatetimeModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>状态</span>
                                    <span name="stateModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>测序仪型号</span>
                                    <span name="firstInstrumentNumModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>分子量标记</span>
                                    <span name="molecularWeightMarkerModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>混合比例</span>
                                    <span name="mixingRatioModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>提取方法</span>
                                    <span name="sySystemModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>环境温湿度</span>
                                    <span name="environmentTemperatureModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>变性条件</span>
                                    <span name="denaturationConditionModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建者</span>
                                    <span name="createPersonModelSy"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建时间</span>
                                    <span name="createDatetimeModelSy"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="newstyle">
                            <!-- <button class="btn btn-info btn-sm">保 存</button> -->
                            <button class="btn btn-info btn-sm" data-dismiss="modal">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
</div>
<%@ include file="../../newScript.jsp" %>
<script src="${pageContext.request.contextPath}/js/newPage.js" ></script>
<script src="${pageContext.request.contextPath}/js/check.js" ></script>
<script src="${pageContext.request.contextPath}/js/center/sampleTable.js" ></script>
<script src="${pageContext.request.contextPath}/js/center/extractPlate.js" ></script>
<script>
    $(function () {
        'use strict';

        var tabValue = $("#tabValue").val();
        if (tabValue != undefined && tabValue != null && tabValue != "") {
            $(".active-ul li").addClass('active-li').siblings().removeClass('active-li');
            if ("sampleTableTab" == tabValue) {
                tabClick(0);
            }else if ("extractTab" == tabValue) {
                tabClick(1);
            }else if ("pcrTab" == tabValue) {
                tabClick(2);
            }else if ("syTab" == tabValue) {
                tabClick(3);
            }
        }

        function tabClick(tagIndex) {
            $('.active-ul li').eq(tagIndex).addClass('active-li');
            if (tagIndex == 0) {
                $("#display-sy01").show();
                $("#display-sy02").hide();
                $("#display-sy03").hide();
                $("#display-sy04").hide();
                $("#textBlock").show();
                $("#btnBlock").hide();
                $("#tabValue").val("sampleTableTab");
                $("#boardNo").val("");
                $("#sampleNo").val("");
                $().getList();
            } else if (tagIndex == 1) {
                $("#display-sy01").hide();
                $("#display-sy02").show();
                $("#display-sy03").hide();
                $("#display-sy04").hide();
                $("#textBlock").show();
                $("#btnBlock").hide();
                $("#tabValue").val("extractTab");
                $("#boardNo").val("");
                $("#sampleNo").val("");
                $().getList();
            } else if (tagIndex == 2) {
                $("#display-sy01").hide();
                $("#display-sy02").hide();
                $("#display-sy03").show();
                $("#display-sy04").hide();
                $("#textBlock").show();
                $("#btnBlock").hide();
                $("#tabValue").val("pcrTab");
                $("#boardNo").val("");
                $("#sampleNo").val("");
                $().getList();
            } else if (tagIndex == 3) {
                $("#display-sy01").hide();
                $("#display-sy02").hide();
                $("#display-sy03").hide();
                $("#display-sy04").show();
                $("#textBlock").hide();
                $("#btnBlock").show();
                $("#tabValue").val("syTab");
                $("#boardNo").val("");
                $("#sampleNo").val("");
                $().getList();
            }
        }
    });

</script>
</body>

</html>