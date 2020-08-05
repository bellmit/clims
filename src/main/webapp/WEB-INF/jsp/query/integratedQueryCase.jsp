<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>北京市公安局法医鉴定案件委托系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .centerInformation {
            background: #f5f5f5;
            padding: 25px;
            position: relative;
        }

        .centerInformation .caret {
            position: absolute;
            transform: rotate(180deg);
            top: -8px;
            left: 20%;
            margin-left: -8px;
            color: #f5f5f5;
            border-top: 8px dashed;
            border-right: 8px solid transparent;
            border-left: 8px solid transparent;
        }

        .centerInformation p {
            font-weight: 600;
        }

        .centerInformation p span {
            font-weight: 400;
        }

        .centerInformation p:nth-last-child(2) span {
            color: #ff7a74
        }

        #addAuthenticatorBig {
            text-align: center;
        }

        #addAuthenticatorBig > div {
            width: 480px;
            margin: 0 auto;
            margin-top: 20px;
        }

        #addAuthenticatorBig .modal-content {
            position: static;
            float: left;
        }

        #addAuthenticatorBig .form-group {
            margin-bottom: 5px;
        }

        #addAuthenticatorBig .modal-content {
            width: 480px;
            top: 20px;
            text-align: left;
        }

        #addAuthenticatorBig .sampleBox {
            width: 720px;
            top: -519px;
            display: none;
        }

        #addAuthenticatorBig .modal-content .modal-header {
            text-align: center;
        }

        #addAuthenticatorBig .personBox .modal-header h4 {
            font-size: 17px;
            color: #007ef9;
            font-weight: 600;
        }

        #addAuthenticatorBig .sampleBox .modal-header h4 {
            font-size: 17px;
            color: #ffa400;
            font-weight: 600;
        }

        #addAuthenticatorBig .personBox .modal-body {
            padding: 13px 50px;
            padding-bottom: 0px;
            max-height: 492px;
            overflow-y: auto;
        }

        #addAuthenticatorBig .sampleBox .modal-body {
            padding: 13px 50px;
        }

        #addAuthenticatorBig .personBox .modal-body .photoFile, #addAuthenticatorBig .modal-body .samplePhoto {
            display: none;
        }

        #addAuthenticatorBig .modal-content .modal-body .form-group:nth-child(1) img {
            height: 55px;
            background: #fff;
            cursor: pointer;
        }

        #addAuthenticatorBig .modal-content .modal-body .form-group:nth-child(1) img + button {
            margin: 0 20px;
        }

        #addAuthenticatorBig .modal-content .modal-footer {
            text-align: center;
            border: none;
            padding-top: 0px;
        }

        #addAuthenticatorBig .modal-content .modal-footer div {
            margin-bottom: 10px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 {
            height: 85px;
            position: relative;
            padding: 0 7px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 img {
            width: 100%;
            height: 100% !important;
            border: 2px solid #f3f3f3;
            padding: 5px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 .fa-times-circle {
            color: #f84c3d;
            position: absolute;
            right: 2px;
            top: -6px;
            font-size: 17px;
            cursor: pointer;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 .addsamplePhoto {
            width: 100%;
            height: 100%;
            background: #fff;
            color: #f3f3f3;
            border: 2px solid #f3f3f3;
            font-size: 35px;
        }

        td .select {
            width: 90px;
            float: left;
        }

        td .select + .select {
            margin-left: 5px;
        }
    </style>
</head>
<!-- 修改现堪编号 -->
<div class="modal fade popBox smallBox" id="siteSurveyBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">输入现勘编号</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <input type="text" id="caseXkNo" name="caseXkNo" class="form-control">
                </div>
            </div>
            <div class="modal-footer clearfix">
                <button class="btn btn-lang pull-left btn-blue modifySiteSurve">确认</button>
                <button type="button" class="btn btn-lang pull-right btn-blue-border" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 委托人添加 -->
<div class="modal fade popBox xsBox" id="addClientBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">委托人信息</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>委托人姓名</label>
                    <input type="text" class="form-control" placeholder="请输入委托人姓名" name="clientName">
                </div>
                <div class="form-group">
                    <label>职务</label>
                    <input type="text" class="form-control" placeholder="请输入职务" name="clientDuties">
                </div>
                <div class="form-group">
                    <label>联系方式</label>
                    <input type="text" class="form-control" placeholder="请输入职务" name="clientPhone">
                </div>
                <div class="form-group">
                    <label>证件类型</label>

                    <div class="select">
                        <select class="form-control" required name="clientIdType">
                            <option value="" disabled selected hidden>请选择证件类型</option>
                            <option value="警官证">警官证</option>
                            <option value="身份证">身份证</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label>证件号</label>
                    <input type="text" class="form-control" placeholder="请输入证件号" name="clientId">
                </div>
            </div>
            <div class="modal-footer clearfix">
                <input type="hidden" name="index">
                <button class="btn btn-lang btn-blue pull-left addClient">确认</button>
                <button type="button" class="btn btn-lang pull-right btn-blue-border" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<!-- 检材信息 -->
<div class="modal fade popBox bigBox" id="materialEvidencerlSampleBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">物证检材信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>检材类型</label>
                            <div class="select">
                                <select class="form-control" required name="sampleType">
                                    <option value="" disabled selected hidden>请选择检材类型</option>
                                    <c:forEach items="${sampleTypeList}" var="sampleList">
                                        <option value="${sampleList.dictCode}">${sampleList.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>检材名称</label>
                            <input type="text" class="form-control" placeholder="请输入检材名称" name="sampleName">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>包装方法</label>
                            <div class="select">
                                <select name="samplePacking" id="samplePacking" class="form-control" required>
                                    <option value="">请选包装方法</option>
                                    <c:forEach items="${packMethodList}" var="packMethod">
                                        <option value="${packMethod.dictCode}">${packMethod.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>提取时间</label>
                            <input type="text" class="form-control form_datetime" placeholder="请输入提取时间"
                                   name="extractTime" readonly>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>提取方法</label>
                            <div class="select">
                                <select name="extractMethod" class="form-control" required>
                                    <option value="">请选提取方法</option>
                                    <c:forEach items="${extractMethodList}" var="list">
                                        <option value="${list.dictCode}">${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>送检目的</label>
                            <input class="form-control" placeholder="请输入送检目的" name="inspectionObjective">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>检材描述</label>
                            <textarea class="form-control" rows="3" placeholder="请输入检材描述"
                                      name="sampleDescribe"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer clearfix">
                <input type="hidden" name="index">
                <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample">确认</button>
                <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!-- 保存弹窗 -->
<div class="modal fade popBox saveBox" id="saveBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">确认鉴定中心</h4>
            </div>
            <div class="modal-body">
                <div class="row inputBox">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>案件名称 :</label>
                            <label class="caseName"></label>
                        </div>
                    </div>
                </div>
                <div class="row inputBox">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>案件基本情况 :</label>
                            <label class="caseBrief"></label>
                        </div>
                    </div>
                </div>
                <div class="row inputBox">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>委托时间 :</label>
                            <label class="times"></label>
                        </div>
                    </div>
                </div>
                <div class="row inputBox">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label>鉴定中心 :</label>
                            <label class="identificationCenter">北京市法医中心</label>
                            <button class="btn btn-blue-border pull-right savechange" style="margin-top: -10px;">选择
                            </button>
                            <%--
                            <div class="selectbox hidden" style="right:-54px;">
                                <span class="caret"></span>
                                <ul>
                                    <li>昌平分局鉴定中心</li>
                                    <li>北京市法医中心</li>
                                </ul>
                            </div>
                            --%>
                            <div class="centerInformation">
                                <span class="caret"></span>

                                <div>
                                    <p>中心地址 : <%--<span>昌平区XXXXXXXXX</span>--%></p>

                                    <p>联系方式 : <%--<span>010-11223310/</span><span>010-11223310</span>--%></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer clearfix">
                    <input type="hidden" name="" value="">
                    <button type="button" id="save" name="saveInfo" class="btn btn-lang  btn-blue modifySiteSurve"
                            data-dismiss="modal">确认
                    </button>
                    <button type="button" class="btn btn-lang  btn-blue-border" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<body>
<ol class="breadcrumb">
    <li><a href="javascript:void(0);" id="pageHome">首页</a></li>
    <li><a href="#">案件委托登记</a></li>
    <li class="active">DNA鉴定</li>
</ol>
<div class="row fixedBox hidden">
    <ul>
        <li class="success">委托信息</li>
        <li class="error">案件信息</li>
        <li class="now">鉴定要求</li>
        <li>被鉴定人信息</li>
        <li>人员样本信息</li>
        <li>物证检材信息</li>
    </ul>
</div>
<div class="row Modular">
    <div class="col-md-6 starBox">
        <p class="starP">
            <i class="fa fa-star star" aria-hidden="true"></i>鉴定类别:<span>DNA鉴定</span>
        </p>
    </div>
    <div class="col-md-6 starBox">
        <p class="starP">
            <i class="fa fa-star star" aria-hidden="true"></i>现勘编号:<span>${xkNo}</span>
            <button class="btn btn-blue" type="button" data-toggle="modal" data-target="#siteSurveyBox">修改</button>
        </p>
    </div>
</div>
<form id="saveform" enctype="multipart/form-data" method="post">
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading blue">
                    <div>委托信息</div>
                </div>
                <div class="panel-body">
                    <div class="row inputBox">
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>委托单位 :</label>
                                <input type="hidden" id="consignmentId" value="${consignatioInfo.consignmentId}">
                                <input type="hidden" id="delegateOrgCode" name="delegateOrgCode"
                                       value="${orgInfo.orgCode}">
                                <input type="hidden" id="delegateOrgName" name="delegateOrgName"
                                       value="${orgInfo.orgName}">
                                <label>${orgInfo.orgName}</label>
                            </div>
                        </div>
                        <div class="col-md-4 small-left">
                            <div class="form-group">
                                <label class="col-md-3 control-label">所属辖区 :</label>
                                <div class="col-sm-9" style="margin-top: -7px;">
                                    <div class="select">
                                        <select class="form-control" name="areaOrgCode" id="areaOrgCode">
                                            <option value="">请选择所属辖区</option>
                                            <c:forEach items="${orgInfoList}" var="list" varStatus="s">
                                                <option value="${list.orgCode}"
                                                        <c:if test="${consignatioInfo.areaOrgCode eq list.orgCode}">selected</c:if>>${list.orgName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row inputBox">
                        <div class="col-md-12" style="margin-top: 60px;">
                            <div class="col-md-6">
                                <div class="col-md-12 personCard">
                                    <img src="<%=path%>/img/touxiang1.png" alt="">

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>姓名</label>

                                                <div class="select">
                                                    <select class="form-control" name="delegator1Id" id="delegator1Id">
                                                        <option value="">请选委托人</option>
                                                        <c:forEach items="${personalInfoList}" var="list" varStatus="s">
                                                            <option value="${list.personalId}"
                                                                    <c:if test="${consignatioInfo.delegator1Id eq list.personalId}">selected</c:if>>${list.fullName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>职务</label>
                                                <div class="select">
                                                    <select class="form-control" name="delegator1Position"
                                                            id="delegator1Position">
                                                        <option value="">请选择职务</option>
                                                        <c:forEach items="${positionList}" var="list" varStatus="s">
                                                            <option value="${list.dictCode}"
                                                                    <c:if test="${consignatioInfo.delegator1Position eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>证件</label>
                                                <input name="delegator1PaperworkType" id="delegator1PaperworkType"
                                                       type="text" class="form-control" name="clientIdType"
                                                       value="警官证">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>证件号</label>
                                                <input name="delegator1PaperworkNo" id="delegator1PaperworkNo"
                                                       type="text" class="form-control" name="clientId"
                                                       value="${consignatioInfo.delegator1PaperworkNo}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>电话</label>
                                                <input name="delegator1PhoneNumber" id="delegator1PhoneNumber"
                                                       type="text" class="form-control" name="clientPhone"
                                                       value="${consignatioInfo.delegator1PhoneNumber}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="col-md-12 personCard">
                                    <img src="<%=path%>/img/touxiang1.png" alt="">

                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>姓名</label>

                                                <div class="select">
                                                    <select class="form-control" name="delegator2Id" id="delegator2Id">
                                                        <option value="">请选委托人</option>
                                                        <c:forEach items="${personalInfoList}" var="list" varStatus="s">
                                                            <option value="${list.personalId}"
                                                                    <c:if test="${consignatioInfo.delegator2Id eq list.personalId}">selected</c:if>>${list.fullName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>职务</label>
                                                <div class="select">
                                                    <select class="form-control" name="delegator2Position"
                                                            id="delegator2Position">
                                                        <option value="">请选择职务</option>
                                                        <c:forEach items="${positionList}" var="list" varStatus="s">
                                                            <option value="${list.dictCode}"
                                                                    <c:if test="${consignatioInfo.delegator2Position eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>证件</label>
                                                <input name="delegator2PaperworkType" id="delegator2PaperworkType"
                                                       type="text" class="form-control" name="clientIdType"
                                                       value="警官证">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>证件号</label>
                                                <input name="delegator2PaperworkNo" id="delegator2PaperworkNo"
                                                       type="text" class="form-control" name="clientId"
                                                       value="${consignatioInfo.delegator2PaperworkNo}">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>电话</label>
                                                <input name="delegator2PhoneNumber" id="delegator2PhoneNumber"
                                                       type="text" class="form-control" name="clientPhone"
                                                       value="${consignatioInfo.delegator2PhoneNumber}">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading yellow">
                    <div>案件信息</div>
                </div>
                <div class="panel-body">
                    <div class="row inputBox">
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案件名称</label>
                                <i class="required">*</i>
                                <input type="hidden" id="caseId" value="${limsCaseInfo.caseId}">
                                <input type="hidden" id="majorNo" value="DNA鉴定">
                                <input type="hidden" id="majorType" value="01">
                                <input type="text" name="caseName" id="caseName" value="${limsCaseInfo.caseName}"
                                       class="form-control" placeholder="请输入案件名称">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案发地点</label>
                                <input type="text" name="caseLocation" id="caseLocation"
                                       value="${limsCaseInfo.caseLocation}" class="form-control" placeholder="请输入案发地点">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案发时间</label>

                                <input type="text" name="caseDatetime" id="caseDatetime"
                                       value="<fmt:formatDate value='${limsCaseInfo.caseDatetime}' pattern='yyyy-MM-dd '/>"
                                       class="form-control" placeholder="请输入案发时间">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案件性质</label>
                                <div class="select">
                                    <select class="form-control" name="caseProperty" id="caseProperty">
                                        <option value="" disabled selected hidden>请选择案发性质</option>
                                        <c:forEach items="${casePropertyList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${limsCaseInfo.caseProperty eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>其他说明</label>
                                <textarea name="caseRemark" id="caseRemark" class="form-control" rows="1"
                                          placeholder="请输入其他说明">${limsCaseInfo.caseRemark}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row inputBox">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>简要案情</label>
                                <i class="required">*</i>
                                <textarea name="caseBrief" id="caseBrief" value="${limsCaseInfo.caseBrief}"
                                          class="form-control" rows="3"
                                          placeholder="请输入简要案情">${limsCaseInfo.caseBrief}</textarea>
                            </div>
                        </div>
                        <%--<div class="col-md-4">--%>
                        <%--<div class="form-group">--%>
                        <%--<label>其他说明</label>--%>
                        <%--<textarea name="caseRemark" id="caseRemark" class="form-control" rows="3"--%>
                        <%--placeholder="请输入其他说明">${limsCaseInfo.caseRemark}</textarea>--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading red">
                    <div>鉴定要求</div>
                    <%--<button class="btn btn-blue checkboxAll" type="button">全选</button>--%>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <ul class="btn-checkbox clearfix">
                                    <li class="pull-left " value="01">同一鉴定</li>
                                    <li class="pull-left" value="02">亲缘鉴定</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading green">
                    <div>被鉴定人信息</div>
                    <%--<button class="btn btn-blue" data-toggle="modal" data-target="#addAuthenticatorBig" type="button">
                        添加
                    </button>--%>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>人员类型</th>
                            <th>人员名称</th>
                            <th>性别</th>
                            <th>年龄</th>
                            <th>身份证号</th>
                            <th>亲缘关系</th>
                        </tr>
                        </thead>
                        <tbody id="authenticatorTbody">
                        <c:forEach items="${limsPersonInfoList}" var="limsPersonInfo" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${limsPersonInfo.personTypeName}
                                    <input type="hidden" name="personTypeName" value="${limsPersonInfo.personTypeName}">
                                </td>
                                <td>${limsPersonInfo.personName}<input type="hidden" name="personName"
                                                                       value="${limsPersonInfo.personName}"></td>
                                <td>${limsPersonInfo.personGenderName}<input type="hidden" name="sexName"
                                                                             value="${limsPersonInfo.personGenderName}">
                                </td>
                                <td>${limsPersonInfo.perosnAge}<input type="hidden" name="year"
                                                                      value="${limsPersonInfo.perosnAge}"></td>
                                <td>
                                    <c:if test="${not empty limsPersonInfo.personIdCard}">
                                        ${limsPersonInfo.personIdCard}
                                    </c:if>
                                    <c:if test="${empty limsPersonInfo.personIdCard}">
                                        无身份证号(${limsPersonInfo.noIdCardDesc})
                                    </c:if>
                                    <input type="hidden" name="idCard"
                                           value="${limsPersonInfo.personIdCard}"></td>
                                <td>${limsPersonInfo.relationTypeName}<input type="hidden" name="kinshipName"
                                                                             value="${limsPersonInfo.relationTypeName}">
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading blue">
                    <div>人员样本信息</div>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>样本类型</th>
                            <th>样本名称</th>
                            <th>样本描述</th>
                            <th>包装方法</th>
                            <th>提取时间</th>
                            <th style="width: 215px;">提取方法</th>
                            <th>送检目的</th>
                            <th>关联人员</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="personSampleTbody">
                        <c:forEach items="${sampleInfoDnaList}" var="sampleInfoDna" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${sampleInfoDna.sampleTypeName}<input type="hidden" name="sampleTypeName"
                                                                          value="${sampleInfoDna.sampleTypeName}"></td>
                                <td>${sampleInfoDna.sampleName}<input type="hidden" name="sampleName"
                                                                      value="${sampleInfoDna.sampleName}"></td>
                                <td>${sampleInfoDna.sampleDesc}<input type="hidden" name="sampleDescribe"
                                                                      value="${sampleInfoDna.sampleDesc}"></td>
                                <td>${sampleInfoDna.samplePackingName}<input type="hidden" name="samplePackingName"
                                                                             value="${sampleInfoDna.samplePackingName}">
                                </td>
                                <td>
                                    <fmt:formatDate value='${sampleInfoDna.extractDatetime}' pattern='yyyy-MM-dd '/>
                                    <input type="hidden" name="extractTime"
                                           value="<fmt:formatDate value='${sampleInfoDna.extractDatetime}' pattern='yyyy-MM-dd '/>">
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control" required name="extractMethod" disabled>
                                            <c:forEach items="${extractMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfoDna.extractMethod}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select">
                                        <select class="form-control" required name="sampleCarrier" disabled>
                                            <c:forEach items="${sampleCarrierList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfoDna.sampleCarrier}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>${sampleInfoDna.samplePurpose}<input type="hidden" name="inspectionObjective"
                                                                         value="${sampleInfoDna.samplePurpose}"></td>
                                <td>${sampleInfoDna.personName}<input type="hidden" name="personName"
                                                                      value="${sampleInfoDna.personName}"></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sampleInfoDna.auditedGeneId eq null}">

                                        </c:when>
                                        <c:otherwise>
                                            <a href="/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${sampleInfoDna.auditedGeneId}">

                                                <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                                    查看
                                                </button>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--添加人员样本-->
                    <div class="modal fade" id="addAuthenticatorBig" tabindex="-1" role="dialog"
                         aria-labelledby="myModalLabel">
                        <div class="clearfix">
                            <div class="modal-content personBox">
                                <div class="modal-header">
                                    <h4 class="modal-title">被鉴定人信息</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <img src="<%=path%>/img/policeman.png" alt=""/>
                                        <button class="btn btn-gray btn-sm addphoto" type="button">添加照片</button>
                                        <input type="file" id="photoFile" name="photoFile" class="photoFile"
                                               accept="image/*"/>
                                        <input type="hidden" id="personInfoFileTxt" class="form-control"/>
                                    </div>
                                    <div class="form-group">
                                        <label>身份证号</label>
                                        <div class="row">
                                            <div class="col-md-7 nopadding idBox">
                                                <input type="text" class="form-control" placeholder="请输入身份证号"
                                                       name="idCard" id="idCard">
                                                <small class="help-block" data-bv-validator="regexp"
                                                       data-bv-for="idCard" data-bv-result="NOT_VALIDATED"
                                                       style="display: none;">身份证输入有误
                                                </small>
                                            </div>
                                            <div class="col-md-2 nopadding">
                                                <label class="custom-control checkbox-inline">
                                                    <input class="custom-control-input" type="checkbox"
                                                           name="noIdCheck">
                                                    <span class="custom-control-label">无</span>
                                                </label>
                                            </div>
                                            <div class="col-md-3 nopadding" style="text-align: right;">
                                                <button class="btn btn-gray btn-sm" type="button" id="verification">
                                                    核验
                                                </button>
                                            </div>
                                        </div>
                                        <input type="text" class="form-control hidden" placeholder="请输入无身份证号原因"
                                               style="margin-top: 5px;"
                                               name="noIdCard">
                                    </div>
                                    <div class="form-group">
                                        <label>人员类型</label>

                                        <div class="select">
                                            <select class="form-control" required name="personType" id="personType">
                                                <option value="" disabled selected hidden>请选择人员类型</option>
                                                <c:forEach items="${personTypeList}" var="list">
                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label>人员名称</label>
                                        <input type="text" class="form-control" placeholder="请输入人员名称" name="personName"
                                               id="personName">
                                    </div>
                                    <div class="form-group">
                                        <label>性别</label>
                                        <label class="radio-inline radio-sex">
                                            <input type="radio" class="sex sexman" value="01" name="sex">男性
                                        </label>
                                        <label class="radio-inline  radio-sex">
                                            <input type="radio" class="sex sexwoman" value="02" name="sex">女性
                                        </label>
                                    </div>

                                    <div class="form-group">
                                        <label>年龄</label>
                                        <input type="text" class="form-control" placeholder="请输入年龄" name="year"
                                               id="year">
                                    </div>
                                    <div class="form-group hidden">
                                        <label>亲缘关系</label>

                                        <div class="select">
                                            <select class="form-control" required name="kinship">
                                                <option value="" disabled selected hidden>请选择亲缘关系</option>
                                                <c:forEach items="${relationTypeList}" var="list">
                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group ">
                                        <label style="color: #ff7570">是否同时送检人员样本信息</label>
                                        <label class="custom-control checkbox-inline">
                                            <input class="custom-control-input" type="checkbox" name="moveCheckbox">
                                            <span class="custom-control-label">是</span>
                                        </label>
                                    </div>
                                    <div class="form-group moveInput hidden">
                                        <label>住址</label>
                                        <input type="text" class="form-control" placeholder="请输入住址"
                                               name="personCurrentAddress">
                                    </div>
                                    <div class="form-group moveInput hidden">
                                        <label>身高</label>
                                        <input type="text" class="form-control" placeholder="请输入身高" name="personHeight">
                                    </div>
                                    <div class="form-group moveInput hidden">
                                        <label>体重</label>
                                        <input type="text" class="form-control" placeholder="请输入体重" name="personWeight">
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <div>
                                        <button class="btn btn-yellow btn-sm moveBtn" type="button">添加更多</button>
                                    </div>
                                    <input type="hidden" name="index"/>
                                    <button type="button" class="btn btn-blue btn-lang addAuthenticator">确认</button>
                                    <button type="button" class="btn btn-blue-border  btn-lang" data-dismiss="modal"
                                            type="button">
                                        取消
                                    </button>
                                </div>
                            </div>
                            <div class="modal-content sampleBox">
                                <div class="modal-header">
                                    <h4 class="modal-title">样本信息</h4>
                                </div>
                                <div class="modal-body">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>样本类型</label>
                                                <div class="select">
                                                    <select class="form-control" required name="sampleType"
                                                            id="sampleType">
                                                        <option value="" disabled selected hidden>请选择样本类型</option>
                                                        <c:forEach items="${sampleTypeList}" var="sampleList">
                                                            <option value="${sampleList.dictCode}">${sampleList.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>样本名称</label>
                                                <input type="text" class="form-control" placeholder="请输入样本名称"
                                                       name="sampleName">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>包装</label>

                                                <div class="select">
                                                    <select class="form-control" required name="samplePacking">
                                                        <c:forEach items="${packMethodList}" var="packMethod">
                                                            <option value="${packMethod.dictCode}">${packMethod.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>提取时间</label>
                                                <input type="text" class="form-control form_datetime"
                                                       placeholder="请输入提取时间" name="extractTime" readonly>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>提取方法</label>
                                                <div class="select">

                                                    <select class="form-control" required name="extractMethod">
                                                        <c:forEach items="${extractMethodList}" var="list">
                                                            <option value="${list.dictCode}">${list.dictName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>送检目的</label>
                                                <input class="form-control" placeholder="请输入送检目的"
                                                       name="inspectionObjective" value="DNA检验">
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>样本描述</label>
                                                <textarea class="form-control" rows="3" placeholder="请输入样本描述"
                                                          name="sampleDescribe"></textarea>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>添加照片</label>

                                                <div class="row samplePhotobox">
                                                    <div class="col-md-2">
                                                        <button class="btn addsamplePhoto" type="button">
                                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                                        </button>
                                                        <input type="file" name="samplePhoto" id="samplePhoto"
                                                               class="samplePhoto"
                                                               accept="image/*"/>
                                                        <input type="hidden" id="sampleInfoFileTxt"
                                                               class="form-control"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer hidden">
                                    <input type="hidden" name="index"/>
                                    <button type="button" class="btn btn-blue btn-lang addSampleBtn">确认</button>
                                    <button type="button" class="btn btn-blue-border  btn-lang" data-dismiss="modal">
                                        取消
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading yellow">
                    <div>物证检材信息</div>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>样本类型</th>
                            <th>样本名称</th>
                            <th style="width: 250px;">检材描述</th>
                            <th>包装方法</th>
                            <th>提取时间</th>
                            <th style="width: 215px;">提取方法</th>
                            <th>送检目的</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="materialEvidencerTbody">
                        <c:forEach items="${limsSampleInfoList}" var="sampleInfo" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${sampleInfo.sampleTypeName}
                                    <input type="hidden" name="sampleTypeName" value="${sampleInfo.sampleTypeName}">
                                </td>
                                <td>${sampleInfo.sampleName}
                                    <input type="hidden" name="sampleName" value="${sampleInfo.sampleName}">
                                </td>
                                <td>${sampleInfo.sampleDesc}
                                    <input type="hidden" name="sampleDescribe" value="${sampleInfo.sampleDesc}">
                                </td>
                                <td>${sampleInfo.samplePackingName}
                                    <input type="hidden" name="samplePackingName"
                                           value="${sampleInfo.samplePackingName}">
                                </td>
                                <td><fmt:formatDate value="${sampleInfo.extractDatetime}" pattern="yyyy-MM-dd"/>
                                    <input type="hidden" name="extractTime"
                                           value="<fmt:formatDate value="${sampleInfo.extractDatetime}" pattern="yyyy-MM-dd" />">
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control" required name="extractMethod" disabled>
                                            <c:forEach items="${extractMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfo.extractMethod}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select">
                                        <select class="form-control" required name="sampleCarrier" disabled>
                                            <c:forEach items="${sampleCarrierList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfo.sampleCarrier}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                        ${sampleInfo.samplePurpose}<input type="hidden" name="inspectionObjective"
                                                                          value="${sampleInfo.samplePurpose}">
                                </td>

                                <td>
                                    <c:choose>
                                        <c:when test="${sampleInfo.auditedGeneId eq null}">

                                        </c:when>
                                        <c:otherwise>
                                            <a href="/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${sampleInfo.auditedGeneId}">
                                                <input type="hidden" name="sampleId"
                                                       value="${sampleInfo.entity.sampleId}">
                                                <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                                    查看
                                                </button>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="CaseSucceedModal" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        消息提示
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal tasi-form">
                        <div class="form-group m-bot20">
                            <div class="col-md-12 text-center">
                                <h3 class="alert alert-success"><Strong>保存成功！</Strong></h3>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn_blue_border" type="button" id="\">
                        <i class="fa fa-reply"></i> 返 回
                    </button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="CaseErrorModal" aria-hidden="true" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">
                        消息提示
                    </h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal tasi-form">
                        <div class="form-group m-bot20">
                            <div class="col-md-12 text-center">
                                <h3 class="alert alert-success"><Strong>保存失败！</Strong></h3>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn_blue_border" type="button" id=""><i
                            class="fa fa-reply"></i> 返 回
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/entrustCurrency.js"></script>
<script>
    $(function () {
        $("#pageHome").on("click", function () {
            window.location = "<%=path%>/main/home";
            parent.$("#bs-example-navbar-collapse-1").children().eq(1).children().eq(0).addClass("active").siblings().removeClass("active")
        });

        var btnChecked = '${consignatioInfo.identifyRequirement}'.split(",")
        if (btnChecked[0] == "") {
            $(".btn-checkbox").find("li[value='01']").addClass("active")
        } else {
            $.each(btnChecked, function (i, item) {
                $(".btn-checkbox").find("li[value='" + item + "']").addClass("active")
            })
        }
        $("#addAuthenticatorBig").find("input[name='idCard']").blur(function () {
            if ($.trim($(this).val()).length > 0 && isCardNo($.trim($(this).val()))) {
                $(this).siblings("small").css("display", "none")
                $("#verification").addClass("btn-blue").removeClass("btn-gray")
                if ($(this).val().length == 18) {
                    if (Number($(this).val().substring(16, 17)) % 2 == 0) {
                        $(".personBox").find("input[name='sex'][value='02']").prop("checked", true)
                    } else {
                        $(".personBox").find("input[name='sex'][value='01']").prop("checked", true)
                    }
                    var year = Number($(this).val().substring(6, 10))
                    var myDate = new Date();
                    var Nowyear = Number(myDate.getFullYear())
                    $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year)
                }
                else if ($(this).val().length == 15) {
                    if (Number($(this).val().substring(14, 15)) % 2 == 0) {
                        $(".personBox").find("input[name='sex'][value='02']").prop("checked", true)
                    } else {
                        $(".personBox").find("input[name='sex'][value='01']").prop("checked", true)
                    }
                    var year = Number("19" + $(this).val().substring(6, 8))
                    var myDate = new Date();
                    var Nowyear = Number(myDate.getFullYear())
                    $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year)
                }
            } else {
                $("#verification").addClass("btn-gray").removeClass("btn-blue")
                $(this).siblings("small").css("display", "block")
            }
        })
        //核验
        $("#verification").click(function () {
            var idcard = $("#idCard").val()
            if ($.trim(idcard).length > 0 && isCardNo($.trim(idcard))) {
                var urlStr = "<%=path%>/delegate/queryHjrkPerson?idcard=" + idcard;
                $.ajax({
                    type: "get",
                    url: urlStr,
                    dataType: "json",
                    success: function (data) {
                        console.log(data.hjrkPersonInfos)
                        $("#personName").val(data.hjrkPersonInfos[0].name)
                    }
                });
            } else {

            }

        })
    })
</script>
</body>

</html>