<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
            "yyyy-MM-dd");
    java.util.Date currentTime = new java.util.Date();
    String time = simpleDateFormat.format(currentTime).toString();
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

        #lyInput {
            width: 100%;
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
            width: 55px;
            background: #fff;
            cursor: pointer;
        }

        #addAuthenticatorBig .modal-content .modal-body .form-group:nth-child(1) img + button {
            margin: 0 20px;
            margin-right: 5px;
        }

        #addAuthenticatorBig .modal-content .modal-body .form-group:nth-child(1) span {
            color: #a94442;
            font-size: 11px;
        }

        #addAuthenticatorBig .modal-content .modal-footer {
            text-align: center;
            border: none;
            padding-top: 0px;
        }

        #addAuthenticatorBig .modal-content .modal-footer div {
            margin-bottom: 10px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2,
        #materialEvidencerlSampleBox .modal-content .samplePhotobox .col-md-2 {
            height: 85px;
            position: relative;
            padding: 0 7px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 img,
        #materialEvidencerlSampleBox .modal-content .samplePhotobox .col-md-2 img {
            width: 100%;
            height: 100% !important;
            border: 2px solid #f3f3f3;
            padding: 5px;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 .fa-times-circle,
        #materialEvidencerlSampleBox .modal-content .samplePhotobox .col-md-2 .fa-times-circle {
            color: #f84c3d;
            position: absolute;
            right: 2px;
            top: -6px;
            font-size: 17px;
            cursor: pointer;
        }

        #addAuthenticatorBig .modal-content .samplePhotobox .col-md-2 .addsamplePhoto,
        #materialEvidencerlSampleBox .modal-content .samplePhotobox .col-md-2 .addsamplePhoto {
            width: 100%;
            height: 100%;
            background: #fff;
            color: #f3f3f3;
            border: 2px solid #f3f3f3;
            font-size: 35px;
        }

        .bv-form .has-error .idBox .help-block,
        .idBox small {
            position: absolute;
            top: -29px;
            left: 70px;
            color: #a94442;
        }

        td .select {
            width: 90px;
            float: left;
        }

        td .select + .select {
            margin-left: 5px;
        }

        #materialEvidencerTbody tr td:nth-child(2) {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        #showPhotoBox {
            background: rgba(0, 0, 0, .5);
        }

        #showPhotoBox > div {
            text-align: center;
            top: 25%;
        }

        .bv-form .small-consignment .help-block {
         /*   position: absolute;
            margin-top: 2%;
            top: 5px;
            color: #a94442;
            left: 425px;*/
            position: absolute;
            margin-top: 2%;
            top: -8px;
            color: #a94442;
            left: 740px;
            width:58px;
        }

        .clientSelect {
            display: block;
        !important;
            position: absolute;
        }

        #caseName + small {
            top: 28px;
            left: 330px;
        }

        #siteSurveyBox small {
            top: 2px;
            left: 234px;
        }

        #siteSurveyBox small {
            top: 2px;
            left: 234px;
        }
        .starBox .form-group {
            display: inline-block;
            width: 250px;
        }
        .starBox small {
            left: 195px !important;
            top: -5px !important;
            right: 0px !important;
        }
        .starBox .form-group input{
            font-weight:600;
        }
    </style>
</head>
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
<!--添加人员样本-->
<div class="modal fade" id="addAuthenticatorBig" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="clearfix">
        <form>
            <div class="modal-content personBox">
                <div class="modal-header">
                    <h4 class="modal-title">被鉴定人信息</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <img class="personFrontPicture" src="<%=path%>/img/policeman.png" alt=""/>
                        <button class="btn btn-gray btn-sm addphoto" type="button">添加照片</button>
                        <span class="hidden">上传图片过大，请上传小于500Kb的图片</span>
                        <input type="file" id="photoFile" name="photoFile" class="photoFile" accept="image/*"/>
                        <input type="hidden" id="personBase" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>身份证号</label>
                        <div class="row">
                            <div class="col-md-7 nopadding idBox">
                                <input type="text" class="form-control" placeholder="请输入身份证号"
                                       name="idCard" id="idCard">
                                <small class="help-block hidden">身份证输入有误</small>
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
                        <input type="text" class="form-control" placeholder="请输入人员名称"
                               name="personName"
                               id="personName">
                    </div>
                    <div class="form-group">
                        <label>性别</label>
                        <label class="radio-inline radio-sex">
                            <input type="radio" class="sex sexman" value="01" name="sex" checked>男性
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
                        <input type="text" class="form-control" placeholder="请输入身高"
                               name="personHeight">
                    </div>
                    <div class="form-group moveInput hidden">
                        <label>体重</label>
                        <input type="text" class="form-control" placeholder="请输入体重"
                               name="personWeight">
                    </div>
                </div>
                <div class="modal-footer">
                    <div>
                        <button class="btn btn-yellow btn-sm moveBtn" type="button">添加更多</button>
                    </div>
                    <input type="hidden" name="index"/>
                    <input type="hidden" name="addflag" value="0"/>
                    <button type="button" class="btn btn-blue btn-lang addAuthenticator" name="qrinfoButton">确认</button>
                    <button type="button" class="btn btn-blue-border  btn-lang"
                            data-dismiss="modal">取消
                    </button>
                </div>
            </div>
            <div class="modal-content sampleBox">
                <div class="modal-header">
                    <h4 class="modal-title">样本信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6 hidden">
                            <div class="form-group">
                                <label>关联人员</label>
                                <div class="select">
                                    <select class="form-control" required name="relationPersonName">

                                    </select>
                                </div>
                            </div>
                        </div>
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
                                       placeholder="请输入提取时间" name="extractTime" readonly value="<%=time%>">
                            </div>
                        </div>
                        <div class="col-md-3" style="padding-right: 2px;">
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
                        <div class="col-md-3" style=" margin-top: 25px;padding-left: 2px;">
                            <div class="form-group">
                                <div class="select">
                                    <select class="form-control" required name="sampleCarrier">
                                        <c:forEach items="${sampleCarrierList}" var="list">
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
                                <span class="hidden">上传图片过大，请上传小于500Kb的图片</span>
                                <div class="row samplePhotobox">
                                    <div class="col-md-2">
                                        <button class="btn addsamplePhoto" type="button">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        </button>
                                        <input type="file" name="samplePhoto" id="samplePhoto" class="samplePhoto"
                                               accept="image/*"/>
                                        <input type="hidden" id="sampleBase" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer hidden">
                    <input type="hidden" name="index"/>
                    <button type="button" class="btn btn-blue btn-lang addSampleBtn" name="addSampButton">确认</button>
                    <button type="button" class="btn btn-blue-border  btn-lang" data-dismiss="modal">
                        取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 检材信息 -->
<div class="modal fade popBox bigBox" id="materialEvidencerlSampleBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
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
                                       name="extractTime" readonly value="<%=time%>">
                            </div>
                        </div>
                        <div class="col-md-3" style="padding-right: 2px;">
                            <div class="form-group">
                                <label>提取方法</label>
                            </div>
                            <div class="select">
                                <select class="form-control" required name="extractMethod">
                                    <c:forEach items="${extractMethodList}" var="list">
                                        <option value="${list.dictCode}"
                                                <c:if test="${list.dictCode eq sampleInfo.extractMethod}">selected</c:if>>${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-3" style=" margin-top: 25px;padding-left: 2px;">
                            <div class="form-group">
                                <div class="select">
                                    <select class="form-control" required name="sampleCarrier">
                                        <c:forEach items="${sampleCarrierList}" var="list">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>送检目的</label>
                                <input class="form-control" placeholder="请输入送检目的" name="inspectionObjective"
                                       value="DNA检验" id="inspectionObjective">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>检材描述</label>
                            <textarea class="form-control" rows="3" placeholder="请输入检材描述"
                                      name="sampleDescribe"></textarea>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>添加照片</label>
                                <span class="hidden">上传图片过大，请上传小于500Kb的图片</span>
                                <div class="row samplePhotobox">
                                    <div class="col-md-2">
                                        <button class="btn addsamplePhoto" type="button">
                                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                                        </button>
                                        <input type="file" name="samplePhoto" class="samplePhoto hidden"
                                               accept="image/*"/>
                                        <input type="hidden" class="form-control"/>
                                        <input type="hidden" id="samplePicture" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer clearfix">
                    <input type="hidden" name="index">
                    <input type="hidden" name="evidenceNo">
                    <input type="hidden" name="parentIndex">
                    <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample" type="button"  name="wzSampleButton">确认</button>
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>
                </div>
            </form>
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
                            <%--判断，如果登录用户鉴定中心不为空，则显示，如果为空，则显示法医中心--%>
                            <c:choose>
                                <c:when test="${not empty orgInfo.orgQualification}">
                                    <label class="identificationCenter">${orgInfo.orgQualification}
                                        <input type="hidden" name="orgCodeName" value="${orgInfo.orgId}"></label>
                                </c:when>
                                <c:otherwise>
                                    <label class="identificationCenter">${forensicCenterorg.orgQualification}
                                        <input type="hidden" name="orgCodeName"
                                               value="${forensicCenterorg.orgId}"></label>
                                </c:otherwise>
                            </c:choose>
                            <button class="btn btn-blue-border pull-right savechange" style="margin-top: -10px;">选择
                            </button>
                            <div class="selectbox hidden" style="right:-54px;">
                                <span class="caret"></span>
                                <ul>
                                    <%--<li>昌平分局鉴定中心</li>
                                    <li>北京市法医中心</li>--%>
                                    <%--获取该分局的鉴定中心--%>
                                    <c:if test="${not empty orgInfo.orgQualification}">
                                        <c:forEach items="${orgInfos}" var="orgInfo" varStatus="o">
                                            <li> ${orgInfo.orgQualification}
                                                <input type="hidden" name="orgAddressName"
                                                       value="${orgInfo.orgAddress}"/>
                                                <input type="hidden" name="orgContactPhoneName"
                                                       value="${orgInfo.orgContactPhone}"/>
                                                <input type="hidden" name="orgCodeName" value="${orgInfo.orgId}">
                                            </li>
                                        </c:forEach>
                                    </c:if>
                                </ul>
                            </div>
                            <div class="centerInformation">
                                <span class="caret"></span>

                                <div>
                                    <%--显示地址和联系方式--%>
                                    <c:if test="${not empty orgInfo.orgQualification}">
                                        <p>中心地址 :
                                            <span class="orgAddressSpanId">${orgInfo.orgAddress}</span></p>
                                        <p><br>联系方式 :
                                            <span class="orgContactPhoneSpanId">${orgInfo.orgContactPhone}</span></p>
                                    </c:if>
                                    <c:if test="${empty orgInfo.orgQualification}">
                                        <p>中心地址 :
                                            <span>${forensicCenterorg.orgAddress}</span></p>
                                        <p><br>联系方式 :
                                            <span>${forensicCenterorg.orgContactPhone}</span></p>
                                    </c:if>
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
<%--照片展示--%>
<div class="modal fade" id="showPhotoBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <img/>
    </div>
</div>

<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">案件委托登记</a></li>
    <li class="active">DNA鉴定</li>
</ol>
<div class="row fixedBox hidden">
    <ul>
        <%--<li class="success">委托信息</li>--%>
        <%--<li class="error">案件信息</li>--%>
        <%--<li class="now">鉴定要求</li>--%>
        <li>委托信息</li>
        <li>案件信息</li>
        <li>鉴定要求</li>
        <li>被鉴定人信息</li>
        <li>人员样本信息</li>
    </ul>
</div>

<form id="saveform" enctype="multipart/form-data" method="post">
    <div class="row Modular">
        <div class="col-md-10 starBox small-consignment">
            <p class="starP">
                <i class="fa fa-star star" aria-hidden="true"></i>鉴定类别:<span>DNA鉴定</span>
            </p>
            <p class="starP" style="margin-left: 100px">
                <i class="fa fa-star star" aria-hidden="true"></i>委托书编号:
            <span style="margin-right: 27px;">
<%--
            <input type="text" class="form-control" id="consignmentNo" name="consignmentNo"
                   style="width: 120%;" disabled="disabled" value="${consignatioInfo.consignmentNo}">
--%>
                <%--${consignmentNo}--%>
                 <c:choose>
                     <c:when test="${user.orgId   eq 110106000000}">
                         <div class="form-group">
                            <input type="text" class="form-control" id="consignmentNos" name="consignmentNo" value="${consignatioInfo.consignmentNo}">
                        </div>
                     </c:when>
                     <c:otherwise>
                       <div class="form-group">
                        <input type="text" class="form-control" id="consignmentNo" name="consignmentNo" value="${consignatioInfo.consignmentNo}">
                      </div>
                     </c:otherwise>
                 </c:choose>
            </span>
            <span style="color: #666666; font-size: medium;  font-weight: 400;">
                <label class="custom-control radio-inline" style=" margin-top: -22px; padding-left: 0px;">
                    <input class="custom-control-input" type="checkbox"
                           name="WtsNoBoxs" id="manualWtsNoBox">
                    <span class="custom-control-label"></span>
                </label>手动填写
                 <c:choose>
                     <%--判断丰台--%>
                     <c:when test="${user.orgId   eq 110106000000 and user.statusId eq 1}">
                         <label class="custom-control radio-inline" style=" margin-top: -22px; padding-left: 0px;">
                             <input class="custom-control-input" type="checkbox" name="WtsNoBox" checked>
                             <span class="custom-control-label"></span>
                         </label>留空
                     </c:when>
                     <c:otherwise>
                         <label class="custom-control radio-inline" style=" margin-top: -22px; padding-left: 0px;">
                             <input class="custom-control-input" type="checkbox"
                                    name="WtsNoBox" id="nullWtsNoBox">
                             <span class="custom-control-label"></span>
                         </label>留空
                     </c:otherwise>
                 </c:choose>
            </span>
            </p>
        </div>
    </div>
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading blue">
                    <div>委托信息</div>
                </div>
                <div class="panel-body">
                    <div class="row inputBox">
                        <%--<div class="col-md-4">
                            <div class="form-group">
                                <label  class="col-md-3 control-label">委托单位 :</label>
                                <i class="required">*</i>
                                &lt;%&ndash;<input type="hidden" id="consignmentId" value="${consignatioInfo.consignmentId}">
                                <input type="hidden" id="delegateOrgCode" name="delegateOrgCode"
                                       value="${orgInfo.orgCode}">
                                <input type="hidden" id="delegateOrgName" name="delegateOrgName"
                                       value="${orgInfo.orgName}">
                                <label>${orgInfo.orgName}</label>&ndash;%&gt;
                                <div class="select">
                                    <select class="form-control" name="delegateOrgCode" id="delegateOrgCode">
                                        <option value="">委托单位</option>
                                        <c:forEach items="${orgInfoList1}" var="list" varStatus="s">
                                            <option value="${list.orgCode}"
                                                    <c:if test="${orgInfo.orgCode eq list.orgCode}">selected</c:if>>${list.orgName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>--%>
                        <div class="col-md-4 small-left">
                            <div class="form-group">
                                <%--<label class="col-md-3 control-label">委托单位 :</label>
                                <i class="required">*</i>
                                <div class="col-sm-9" style="margin-top: -7px;">
                                    <div class="select">
                                        <select class="form-control" name="delegateOrgCode" id="delegateOrgCode">
                                            <option value="">请选择委托单位</option>
                                            <c:forEach items="${orgInfoList}" var="list" varStatus="s">
                                                <option value="${list.orgCode}"
                                                        <c:if test="${orgInfo.orgCode eq list.orgCode}">selected</c:if>>${list.orgName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>--%>
                                <label>委托单位 :</label>
                                <input type="hidden" id="consignmentId" value="${consignatioInfo.consignmentId}">
                                <input type="hidden" id="delegateOrgCode" name="delegateOrgCode"
                                       value="${subOrgInfo.orgCode}">
                                <input type="hidden" id="delegateOrgName" name="delegateOrgName"
                                       value="${subOrgInfo.orgName}">
                                <label>${subOrgInfo.orgName}</label>
                            </div>
                        </div>
                        <div class="col-md-4 small-left">
                            <div class="form-group">
                                <label class="col-md-3 control-label">所属辖区 :</label>
                                <i class="required">*</i>
                                <div class="col-sm-9" style="margin-top: -7px;">
                                    <div class="select">
                                        <select class="form-control clientSelect" name="areaOrgCode" id="areaOrgCode">
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
                                                <i class="required">*</i>
                                                <div class="select">
                                                    <select class="form-control clientSelect" name="delegator1Id"
                                                            id="delegator1Id">
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
                                                <i class="required">*</i>
                                                <div class="select">
                                                    <select class="form-control clientSelect" name="delegator2Id"
                                                            id="delegator2Id">
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
                                <label style="font-size: 11px;font-weight: 400;color: #a94442;">格式为:受害人姓名+被+案件性质+案;或:嫌疑人姓名+案件性质+案</label>
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
                                <i class="required">*</i>
                                <input type="text" name="caseLocation" id="caseLocation"
                                       value="${limsCaseInfo.caseLocation}" class="form-control" placeholder="请输入案发地点">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案发时间</label>
                                <i class="required">*</i>
                                <input type="text" readonly name="caseDatetime" id="caseDatetime"
                                       value="<fmt:formatDate value='${limsCaseInfo.caseDatetime}' pattern='yyyy-MM-dd '/>"
                                       class="form-control form_datetime" placeholder="请输入案发时间">
                            </div>
                        </div>
                        <div style="display: none">
                            <div class="form-group">
                                <label>案件类型</label>
                                <i class="required">*</i>
                                <div class="select">
                                    <select class="form-control" name="caseType" id="caseType">
                                        <c:forEach items="${caseTypeList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${limsCaseInfo.caseType eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案件性质</label>
                                <i class="required">*</i>
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
                        <%--<div class="col-md-4">
                            <div class="form-group">
                                <label>案件级别</label>
                                <i class="required">*</i>
                                <div class="select">
                                    <select class="form-control" name="caseLevel" id="caseLevel">
                                        <option value="" disabled selected hidden>请选择案件级别</option>
                                        <c:forEach items="${caseLevelList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${limsCaseInfo.caseLevel eq list.dictCode}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>--%>
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
                            <div class="form-group" id="lyInput">
                                <label>简要案情</label>
                                <i class="required">*</i>
                                <textarea name="caseBrief" id="caseBrief" value="${limsCaseInfo.caseBrief}"
                                          class="form-control" rows="3"
                                          placeholder="请输入简要案情">${limsCaseInfo.caseBrief}</textarea>
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
                <div class="panel-heading red">
                    <div>鉴定要求</div>
                    <button class="btn btn-blue checkboxAll" type="button">全选</button>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <ul class="btn-checkbox clearfix">
                                    <li class="pull-left " value="01">同一鉴定
                                        <input type="checkbox" name="identifyRequirement">
                                    </li>
                                    <li class="pull-left" value="02">亲缘鉴定
                                        <input type="checkbox" name="identifyRequirement">
                                    </li>
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
                    <button class="btn btn-blue" id="openAddAuthenticatorBig" type="button">
                        添加
                    </button>
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
                            <th>操作</th>
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
                                <td><c:if test="${not empty limsPersonInfo.personIdCard}">
                                    ${limsPersonInfo.personIdCard}
                                </c:if>
                                    <c:if test="${empty limsPersonInfo.personIdCard}">
                                        无身份证号(${limsPersonInfo.noIdCardDesc})
                                    </c:if><input type="hidden" name="idCard"
                                                  value="${limsPersonInfo.personIdCard}">
                                    <input type="hidden" name="noIdCard"
                                           value="${limsPersonInfo.noIdCardDesc}">
                                </td>
                                <td>${limsPersonInfo.relationTypeName}<input type="hidden" name="kinshipName"
                                                                             value="${limsPersonInfo.relationTypeName}">
                                </td>
                                <td>
                                    <button type="button" name="editBtn"
                                            class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑
                                    </button>
                                    <button type="button" name="delBtn"
                                            class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除
                                    </button>
                                    <input type="hidden" name="personId" value="${limsPersonInfo.personId}">
                                    <input type="hidden" name="idCardFlag" value="${limsPersonInfo.idCardFlag}">
                                    <input type="hidden" name="sex" value="${limsPersonInfo.personGender}">
                                    <input type="hidden" name="noIdCardDesc" value="${limsPersonInfo.noIdCardDesc}">
                                    <input type="hidden" name="personHeight" value="${limsPersonInfo.personHeight}">
                                    <input type="hidden" name="personWeight" value="${limsPersonInfo.personWeight}">
                                    <input type="hidden" name="personCurrentAddress" value="${limsPersonInfo.personCurrentAddress}">
                                    <input type="hidden" name="personFrontPicture" value="${limsPersonInfo.personFrontPicture}">
                                    <input type="hidden" name="personFrontPicturePath" value="${limsPersonInfo.personFrontPicturePath}">
                                    <input type="hidden" name="personBase" value="${limsPersonInfo.personFrontPicturePath}">
                                    <input type="hidden" name="personType" value="${limsPersonInfo.personType}">
                                    <input type="hidden" name="kinship" value="${limsPersonInfo.relationType}">
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
                    <button class="btn btn-blue addsampleInfo" type="button">添加</button>
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
                                <td>
                                    <c:choose>
                                        <c:when test="${consignatioInfo.status eq '01'}">
                                            ${s.count}
                                        </c:when>
                                        <c:otherwise>
                                            ${sampleInfoDna.sampleNo}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${sampleInfoDna.sampleTypeName}
                                    <input type="hidden" name="sampleTypeName" value="${sampleInfoDna.sampleTypeName}">
                                </td>
                                <td>${sampleInfoDna.sampleName}
                                    <input type="hidden" name="sampleName" value="${sampleInfoDna.sampleName}">
                                </td>
                                <td>${sampleInfoDna.sampleDesc}
                                    <input type="hidden" name="sampleDescribe" value="${sampleInfoDna.sampleDesc}">
                                </td>
                                <td>${sampleInfoDna.samplePackingName}
                                    <input type="hidden" name="samplePackingName"
                                           value="${sampleInfoDna.samplePackingName}">
                                </td>
                                <td>
                                    <fmt:formatDate value='${sampleInfoDna.extractDatetime}' pattern='yyyy-MM-dd '/>
                                    <input type="hidden" name="extractTime"
                                           value="<fmt:formatDate value='${sampleInfoDna.extractDatetime}' pattern='yyyy-MM-dd '/>">
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control" required name="extractMethod">
                                            <c:forEach items="${extractMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfoDna.extractMethod}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select">
                                        <select class="form-control" required name="sampleCarrier">
                                            <c:forEach items="${sampleCarrierList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfoDna.sampleCarrier}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td>${sampleInfoDna.samplePurpose}
                                    <input type="hidden" name="inspectionObjective"
                                           value="${sampleInfoDna.samplePurpose}">
                                </td>
                                <td>${sampleInfoDna.personName}
                                    <input type="hidden" name="personName" value="${sampleInfoDna.personName}">
                                </td>
                                <td>
                                    <input type="hidden" name="sampleType" value="${sampleInfoDna.sampleType}">
                                    <input type="hidden" name="samplePacking" value="${sampleInfoDna.samplePacking}">
                                    <input type="hidden" name="extractMethod" value="${sampleInfoDna.extractMethod}">
                                    <input type="hidden" name="sampleCarrier" value="${sampleInfoDna.sampleCarrier}">
                                    <input type="hidden" name="sampleId" value="${sampleInfoDna.sampleId}">
                                    <input type="hidden" name="linkId" value="${sampleInfoDna.linkId}">
                                    <input type="hidden" name="sampleBase" value="${sampleInfoDna.sampleDnaPicture}">
                                    <button type="button" name="editSampleInfoDnaBtn"
                                            class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑
                                    </button>
                                    <button type="button" name="delBtn"
                                            class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除
                                    </button>
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
                <div class="panel-heading yellow">
                    <div>物证检材信息</div>
                    <button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable" style="table-layout: fixed;">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>检材类型</th>
                            <th>检材名称</th>
                            <th style="width: 250px;">检材描述</th>
                            <th>包装方法</th>
                            <th>提取时间</th>
                            <th style="width: 215px;">提取方法</th>
                            <th>送检目的</th>
                            <th style="width: 150px">操作</th>
                        </tr>
                        </thead>

                        <tbody id="materialEvidencerTbody">
                        <c:forEach items="${limsSampleInfoList}" var="sampleInfo" varStatus="s">
                            <tr>
                                <td title="${sampleInfo.sampleNo}">
                                    <c:choose>
                                        <c:when test="${consignatioInfo.status eq '01'}">
                                            ${s.count}
                                        </c:when>
                                        <c:otherwise>
                                            ${sampleInfo.sampleNo}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td title="${sampleInfo.sampleTypeName}">${sampleInfo.sampleTypeName}
                                    <input type="hidden" name="sampleTypeName" value="${sampleInfo.sampleTypeName}">
                                </td>
                                <td title="${sampleInfo.sampleName}">${sampleInfo.sampleName}
                                    <input type="hidden" name="sampleName" value="${sampleInfo.sampleName}">
                                </td>
                                <td title="${sampleInfo.sampleDesc}">${sampleInfo.sampleDesc}
                                    <input type="hidden" name="sampleDescribe" value="${sampleInfo.sampleDesc}">
                                </td>
                                <td title="${sampleInfo.samplePackingName}">${sampleInfo.samplePackingName}
                                    <input type="hidden" name="samplePackingName"
                                           value="${sampleInfo.samplePackingName}">
                                </td>
                                <td title="<fmt:formatDate value="${sampleInfo.extractDatetime}" pattern="yyyy-MM-dd"/>">
                                    <fmt:formatDate value="${sampleInfo.extractDatetime}" pattern="yyyy-MM-dd"/>
                                    <input type="hidden" name="extractTime"
                                           value="<fmt:formatDate value="${sampleInfo.extractDatetime}" pattern="yyyy-MM-dd" />">
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control" required name="extractMethod">
                                            <c:forEach items="${extractMethodList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfo.extractMethod}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="select">
                                        <select class="form-control" required name="sampleCarrier">

                                            <c:forEach items="${sampleCarrierList}" var="list">
                                                <option value="${list.dictCode}"
                                                        <c:if test="${list.dictCode eq sampleInfo.sampleCarrier}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </td>
                                <td title="${sampleInfo.samplePurpose}">${sampleInfo.samplePurpose}
                                    <input type="hidden" name="inspectionObjective" value="${sampleInfo.samplePurpose}">
                                </td>
                                <td>
                                    <button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑
                                    </button>
                                    <button type="button" name="delBtn"
                                            class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除
                                    </button>
                                    <input type="hidden" name="sampleId" value="${sampleInfo.sampleId}">
                                    <input type="hidden" name="sampleType" value="${sampleInfo.sampleType}">
                                    <input type="hidden" name="samplePacking" value="${sampleInfo.samplePacking}">
                                    <input type="hidden" name="extractMethod" value="${sampleInfo.extractMethod}">
                                    <input type="hidden" name="sampleCarrier" value="${ sampleInfo.sampleCarrier}">
                                    <input type="hidden" name="evidenceNo" value="${sampleInfo.evidenceNo}">
                                    <input type="hidden" name="samplePicture" value="${sampleInfo.sampleMaterialPicture}">
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="row btn-box" style="margin-right: 0px;">
        <div class="col-md-12">
            <button type="button" class="btn btn-blue btn-lang saveBoxBtn">提交</button>
            <a href="<%=path%>/center/caseOffManage" name="quxiao" class="btn btn-blue-border btn-lang" target="ifm">取消</a>
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
                    <button data-dismiss="modal" class="btn btn_blue_border" type="button" id="\"><i
                            class="fa fa-reply"></i> 返 回
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
<script src="<%=path%>/js/modifyAdd.js"></script>
<script>
    $(function () {
        if ('${consignatioInfo.appendFlag}' == '1') {
            $(".Modular").eq(2).find("input[type='text']").prop("disabled", true)
            $(".Modular").eq(2).find("textarea").prop("disabled", true)
            $(".Modular").eq(2).find("select").prop("disabled", true)
        }
        if ('${consignatioInfo.status}' == '02') {
            $(".saveBoxBtn").parents(".btn-box").addClass("hidden")
        }

        var btnChecked = '${consignatioInfo.identifyRequirement}'.split(",")
        if (btnChecked[0] == "") {
            $(".btn-checkbox").find("li[value='01']").addClass("active").find("input").prop("checked",true)
        } else {
            $.each(btnChecked, function (i, item) {
                $(".btn-checkbox").find("li[value='" + item + "']").addClass("active").find("input").prop("checked",true)
            })
        }
        //照片展示
        $("#addAuthenticatorBig").on("click", "img", function () {
            $("#showPhotoBox").find("img").attr("src", $(this).attr("src"))
            $("#showPhotoBox").modal("show")
        })
        $("#materialEvidencerlSampleBox").on("click", "img", function () {
            $("#showPhotoBox").find("img").attr("src", $(this).attr("src"))
            $("#showPhotoBox").modal("show")
        })
        $("#showPhotoBox").on('show.bs.modal', function (e) {
            $("#showPhotoBox").css("z-index", 1050 + 1000);
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

        //保存弹窗选择下拉
        $(".savechange").click(function () {
            $(".selectbox").eq(0).removeClass("hidden")
        })
        //保存弹窗选择下拉点击
        $(".selectbox").on('click', 'li', function () {
            var orgAddressName = $(this).find("input[name='orgAddressName']").val()
            var orgContactPhoneName = $(this).find("input[name='orgContactPhoneName']").val()
            //获取选择单位的id
            var orgCodeName = $(this).find("input[name='orgCodeName']").val()
            $(".centerInformation").find("p").find(".orgAddressSpanId").html(orgAddressName)
            $(".centerInformation").find("p").find(".orgContactPhoneSpanId").html(orgContactPhoneName)
            //将获取的orgCodeName赋值
            $(".identificationCenter").find("input[name='orgCodeName']").val(orgCodeName);
            $('.identificationCenter').html($(this).html())
            $(this).parents(".selectbox").addClass("hidden")
        })
        /**
         * 保存整个页面信息
         */

        $(".saveBoxBtn").click(function () {
            var form = $("#saveform ")
            form.bootstrapValidator({
                live: 'disabled',
                message: 'This value is not valid',
                fields: {
                    areaOrgCode: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    delegator1Id: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    delegator2Id: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    caseName: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    caseLocation: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    caseDatetime: {
                        trigger: 'change',
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    caseProperty: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    caseBrief: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    idCard: {
                        validators: {
                            regexp: {
                                regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                                message: '格式有误'
                            }
                        }
                    },
                    delegator2PhoneNumber: {
                        validators: {
                            regexp: {
                                regexp: /^1[34578]\d{9}$/,
                                message: '电话格式有误'
                            }
                        }
                    },
                    delegator1PhoneNumber: {
                        validators: {
                            regexp: {
                                regexp: /^1[34578]\d{9}$/,
                                message: '电话格式有误'
                            }
                        }
                    },
                }
            });
            form.bootstrapValidator('validate');
            scroll(form);
            if (form.data('bootstrapValidator').isValid()) {
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);
                $("#saveBox").modal("show")
//                saveInfo();
            }
        })
        $("button[name='saveInfo']").on("click", function () {
            saveInfo();
        });
        var url = "<%=path%>/center/updateCase";

        function saveInfo() {
            var form = $("#saveform ")

            var caseInfoDna = getCaseInfoDna();
            var limsConsignmentInfo = getConsignmentInfo();
            var sampleInfoDnaList = getSampleInfoDna();
            var limsPersonInfoList = getLimsPersonInfo();
            var evaluationCenter = $(".identificationCenter").find("input[name='orgCodeName']").val();

            var paramsData = {
                "caseInfoDna": caseInfoDna,
                "sampleInfoDnaList": sampleInfoDnaList,
                "consignmentInfo": limsConsignmentInfo,
                "limsPersonInfoList": limsPersonInfoList,
                "personIds": personIds,
                "sampleIds": sampleIds,
                "evaluationCenter": evaluationCenter,
                "sampleIdWzs": sampleIdWzs,
            };
            console.log(paramsData)
            $('#saveform').ajaxSubmit({
                url: url,
                type: "post",
                //contentType: "application/json",
                data: {"paramsData": JSON.stringify(paramsData)},
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        parent.$("#accordion").children().eq(2).addClass("active").siblings().removeClass("active")
                        location.href = '<%=path%>/center/waitAccept';
                       /* location.href = '<%=path%>/center/caseOffManage';*/
                    } else if (data.success || data.success == false || data.success == "false") {
                        $("#CaseErrorModal").modal('show');
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        }
        //人员照片单张
        var files;
        $("#photoFile").on("change", function () {
            var t_files = this.files;
            files = t_files;
            var str = new Array("");
            for (var i = 0; i < t_files.length; i++) {
                str.push(t_files[i].name);
            }

            str = str.join(",").substring(1);
            $("#personInfoFileTxt").val(str);
        });

        //检材多张图片上传拼接
        var files;
        $("#samplePhoto").on("change", function () {

            var t_files = this.files;
            files = t_files;
            var str = new Array("");
            for (var i = 0; i < t_files.length; i++) {
                str.push(t_files[i].name);
            }

            str += str.join(",").substring(1);
            $("#sampleInfoFileTxt").val(str);

            /*var t_files = this.files;
             files = t_files;
             var sampleInfoFileTxt = $("#sampleInfoFileTxt").val();
             sampleInfoFileTxt += t_files[0].name + ",";
             $("#sampleInfoFileTxt").val(sampleInfoFileTxt);*/
        });

        //获取委托人1信息
        $("#delegator1Id").change(function () {
            var delegator1Id = $("option:selected", "#delegator1Id").val()
            loadAmPeronsalInfoByDelegator1Id(delegator1Id);
        });

        var loadAmPeronsalInfoByDelegator1Id = function (delegator1Id) {
            var urlStr = "<%=path%>/center/queryAmPersonalInfo?parentId=" + delegator1Id;
            $.ajax({
                type: "get",
                url: urlStr,
                dataType: "json",
                success: function (amPersonalInfo) {
                    if (amPersonalInfo.length != 0) {
                        $("#delegator1PaperworkNo").val(amPersonalInfo.policeNo);
                        $("#delegator1PhoneNumber").val(amPersonalInfo.phoneNumber);
                        $("#delegator1Position").val(amPersonalInfo.position);
                    }
                }
            });
        };
        //获取委托人2信息
        $("#delegator2Id").change(function () {
            var delegator2Id = $("option:selected", "#delegator2Id").val()
            loadAmPeronsalInfoByDelegator1Id1(delegator2Id);
        });

        var loadAmPeronsalInfoByDelegator1Id1 = function (delegator2Id) {
            var urlStr = "<%=path%>/center/queryAmPersonalInfo?parentId=" + delegator2Id;
            $.ajax({
                type: "get",
                url: urlStr,
                dataType: "json",
                success: function (amPersonalInfo) {
                    if (amPersonalInfo.length != 0) {
                        $("#delegator2PaperworkNo").val(amPersonalInfo.policeNo);
                        $("#delegator2PhoneNumber").val(amPersonalInfo.phoneNumber);
                        $("#delegator2Position").val(amPersonalInfo.position);
                    }
                }
            });
        };

        function EditPersonInfoInfoRow(obj) {
            var $curTR = $(obj).parents("tr");
            var personType = $("input[name='personType']", $curTR).val();
            var personName = $("input[name='personName']", $curTR).val();
            var personGender = $("input[name='personGender']", $curTR).val();
            var personIdCard = $("input[name='personIdCard']", $curTR).val();
            var idCardFlag = $("input[name='idCardFlag']", $curTR).val();
            var noIdCardDesc = $("input[name='noIdCardDesc']", $curTR).val();
            var perosnAge = $("input[name='perosnAge']", $curTR).val();
            var personHeight = $("input[name='personHeight']", $curTR).val();
            var personWeight = $("input[name='personWeight']", $curTR).val();
            var personCurrentAddress = $("input[name='personCurrentAddress']", $curTR).val();
            var personFrontPicture = $("input[name='personFrontPicture']", $curTR).val();
            var personFrontPicturePath = $("input[name='personFrontPicturePath']", $curTR).val();

            $("#addAuthenticatorBig").find('select[name="personType"]').val(personType)
            $("#addAuthenticatorBig").find('input[name="personName"]').val(personName)
            $("#addAuthenticatorBig").find("input[name='sex'][value='" + personGender + "']").prop("checked", true)
            $("#addAuthenticatorBig").find('input[name="year"]').val(perosnAge)
            $("#addAuthenticatorBig").find("input[name='idCard']").val(personIdCard)
            $("#addAuthenticatorBig").find('input[name="noIdCard"]').val(noIdCardDesc)
            $("#addAuthenticatorBig").find('input[name="personHeight"]').val(personHeight)
            $("#addAuthenticatorBig").find('input[name="personWeight"]').val(personWeight)
            $("#addAuthenticatorBig").find('input[name="personCurrentAddress"]').val(personCurrentAddress)
            $("#addAuthenticatorBig").modal('show')
        }

        $('#saveBox').on('show.bs.modal', function (e) {
            $('#saveBox').find(".caseName").html($('#caseName').val());
            $('#saveBox').find(".caseBrief").html($('#caseBrief').val());

            var myDate = new Date();//获取系统当前时间
            var month = Number(myDate.getMonth()) + 1
            var times = myDate.getFullYear() + "-" + month + "-" + myDate.getDate()
            $('#saveBox').find(".times").html(times)
        })
        //核验
        <%--$("#verification").click(function () {--%>
            <%--var idcard = $("#idCard").val()--%>
            <%--if ($.trim(idcard).length > 0 && isCardNo($.trim(idcard))) {--%>
                <%--var urlStr = "<%=path%>/delegate/queryHjrkPerson?idcard=" + idcard;--%>
                <%--$.ajax({--%>
                    <%--type: "get",--%>
                    <%--url: urlStr,--%>
                    <%--dataType: "json",--%>
                    <%--success: function (data) {--%>
                        <%--console.log(data.hjrkPersonInfos)--%>
                        <%--$("#personName").val(data.hjrkPersonInfos[0].name)--%>
                    <%--}--%>
                <%--});--%>
            <%--} else {--%>

            <%--}--%>

        <%--})--%>

    })
    $("[name='consignmentNo']").blur(function () {
        var consignmentNo = $("[name='consignmentNo']").val();
        $.ajax({
            url: "<%=path%>/delegate/testConsignmentNo?consignmentNo=" + consignmentNo,
            type: "post",
            dataType: "json",
            success: function (data) {
                if (data.success == true || data.success == 'true') {
                    alert("该委托编号已存在！")
                    $("[name='consignmentNo']").val('');
                    /*$("[name='WtsNoBoxs']").prop("checked", false)*/
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    });
    //取消
//    $("button[name='quxiao']").on("click", function () {
//        alert(111)
//        location.href = "center/caseOffManage";
//    })
</script>
</body>

</html>