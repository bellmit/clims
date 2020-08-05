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
Date: 2019/05/26
Time: 11:30
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
    <title>查看案件详情页面</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .searchBox {
            width: 300px;
            margin-bottom: 0px;
            margin-right: 5px !important;
            margin-top: 2px;
        }

        .panel-heading > div:nth-child(1) + button {
            margin-top: -4px;
        }

        .searchBox + button {
            margin-top: -4px;
        }

        .searchBox input {
            font-weight: 400;
            background: #f4f4f4;
            border: 1px solid #f4f4f4;
            border-radius: 0px;
        }

        #materialEvidencerTbody > tr > td {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .bs-callout {
            padding: 20px;
            margin: 20px 0;
            border: 1px solid #eee;
            border-left-width: 5px;
            border-radius: 3px;
            background: #fff;
            padding-top: 10px;
        }

        .bs-callout-danger {
            border-left-color: #FF5A56;
        }

        .bs-callout-danger h4 {
            margin-top: 0px;
            font-size: 14px;
            color: #FF5A56;
            font-weight: 600;
        }

        .bs-callout p:last-child {
            margin-bottom: 0;
        }

        #autograph .modal-body p {
            text-align: center;
            margin-top: 30px;
            color: #9c9c9c;
            font-weight: 600;
        }

        #autograph .modal-body p code {
            background: transparent;
            color: #FF5A56;
        }

        #autograph .modal-body p i {
            color: #FF5A56;
            margin-right: 10px;
        }

        #signature {
            background-color: #f5f5f5;
            height: 320px;
            width: 793px;
            border: 2px dashed #eeeeee;
            position: relative;
        }

        .bg {
            width: 100%;
            height: 100%;
            position: absolute;
            background: transparent;
            color: #eaeaea;
            line-height: 320px;
            text-align: center;
            font-weight: 600;
            font-size: 50px;;
            pointer-events: none;

        }

        .bigBox .modal-content .modal-body {
            padding: 0px 42px;
            padding-top: 20px;

        }

        #autograph .modal-dialog {
            width: 873px;
            top: 5%;
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

        .showPhoto {
            width: 27px;
            height: 27px;
            background: url("<%=path%>/img/icon1.png") no-repeat;
            background-position: -170px -278px;
            cursor: pointer;
        }

        #showPhotoBox > div {
            text-align: center;
            top: 10%;
        }

        #showPhotoBox {
            background: rgba(0, 0, 0, .5);
        }

        #showPhotoBox img {
            width: 500px;
        }

        .table > tbody > tr > td, .table > tbody > tr > th, .table > tfoot > tr > td, .table > tfoot > tr > th, .table > thead > tr > td, .table > thead > tr > th {
            padding: 4px;
            padding-left: 5px;
            position: relative;
        }

        .jiajiImg {
            position: absolute;
            right: 56px;
            top: 72px;
            z-index: 0;
            opacity: 0
        }

        tr td .select select {
            padding: 6px 4px;
        }

        tr td .select:after {
            right: 10px;
        }

        tr td:nth-child(7) .select:nth-child(1) {
            width: 62px;
            float: left;
        }

        tr td:nth-child(7) .select:nth-child(2) {
            width: 80px;
            float: left;
        }

        tr td:nth-child(7) .select + .select {
            margin-left: 2px;
        }

        tr td:nth-child(9) .select,
        tr td:nth-child(10) .select,
        tr td:nth-child(11) .select {
            width: 52px;
        }

        #materialEvidencerTbody .btn-icon + .btn-icon {
            margin-left: 2px;
        }

        #idCard + small {
            position: absolute;
            top: -29px;
            left: 70px;
            color: #a94442;
        }

        #addAuthenticatorBig .modal-content .modal-body .form-group:nth-child(1) span {
            color: #a94442;
            font-size: 11px;
        }

        .shadow {
            position: fixed;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            z-index: 1050;
            /* display: none; */
            overflow: hidden;
            -webkit-overflow-scrolling: touch;
            outline: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, .6);
            z-index: 11111;
        }

        .shadow i {
            font-size: 80px;
            color: #fff;
            position: absolute;
            top: 30%;
            left: 50%;
            margin-left: -40px;
            transform: rotate(323deg);
            animation: myfirst 3s infinite;
        }

        .shadow span {
            font-size: 25px;
            color: #fff;
            position: absolute;
            top: 30%;
            /*left: 50%;*/
            /*margin-left: -40px;*/
            margin-top: 100px;
            width: 100%;
            text-align: center;
        }

        .clientSelect {
            position: absolute;
        }
    </style>
</head>
<%--拒绝弹窗框--%>
<div class="modal fade popBox smallBox" id="refuseBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">拒绝理由</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <%--<input type="text" id="refuse" name="refuse" class="form-control">--%>
                    <textarea class="form-control" rows="2"
                              name="refuse" id="refuse"></textarea>
                </div>
            </div>
            <div class="modal-footer clearfix">
                <input type="hidden" name="index">
                <input type="hidden" name="parentId">
                <button class="btn btn-lang pull-left btn-blue saveRefuse">确认</button>
                <button type="button" class="btn btn-lang pull-right btn-blue-border closeRefuse">取消</button>
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
    <form action="" id="addMaterialEvidencerlSample_form">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">物证检材信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <c:if test="${limsCaseInfo.caseStatus ne '01'}">
                            <div class="col-md-6 hidden">
                                <div class="form-group">
                                    <label>检材编号</label>
                                    <input type="text" class="form-control" name="sampleNo">
                                </div>
                            </div>
                        </c:if>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>检材类型</label>
                                <div class="select">
                                    <select class="form-control" name="sampleType">
                                        <option value="">请选择检材类型</option>
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
                                <input class="form-control" placeholder="请输入送检目的" name="inspectionObjective"
                                       value="DNA检验">
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
                                <span class="hidden" style=" color: #a94442;font-size: 11px">上传图片过大，请上传小于500Kb的图片</span>
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
                    <input type="hidden" name="isRefuse">
                    <input type="hidden" name="isRefuseCode">
                    <input type="hidden" name="index">
                    <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample" type="button"
                            name="wzSampleButton">确认
                    </button>
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </form>
</div>
<%--<div class="shadow">--%>
<%--<i class="fa fa-spinner" aria-hidden="true"></i>--%>
<%--<span>请在手写屏弹窗上用手写笔签字!</span>--%>
<%--</div>--%>
<%--签字--%>
<div class="modal fade popBox bigBox" id="autograph" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">委托人签字</h4>
            </div>
            <div class="modal-body">
                <div id="signature">
                    <div class="bg">委托人签字区域</div>
                </div>
                <p><i class="fa fa-exclamation-circle" aria-hidden="true"></i>签字完成后将生成一份<code>委托确认书</code>
                </p>
            </div>
            <div class="modal-footer clearfix">
                <input type="hidden" name="consignmentId" value="${consignatioInfo.consignmentId}">
                <input type="hidden" name="caseId" value="${consignatioInfo.caseId}">
                <input type="hidden" name="appendFlag" value="${consignatioInfo.appendFlag}">
                <input type="hidden" name="signatureBase" value="">
                <button type="button" class="btn btn-lang  btn-blue saveSignature">确认</button>
                <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>

                <%--<a href="javascript:;" id="noSignatureHref"--%>
                <%--style="color: #AC3333; margin-left: 15px; font-size: 14px; font-weight: bold;">--%>
                <%--&gt;&gt;未连接手写屏，待打印后再签名</a>--%>
                <button type="button" class="btn btn-lang  btn-info" id="noSignatureHref">&gt;&gt;未连接手写屏，待打印后再签名
                </button>

                <img src="" alt="" class="img1">
                <img src="" alt="" class="img2">
            </div>
        </div>
    </div>
</div>
<%--照片展示--%>
<div class="modal fade" id="showPhotoBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <%--<img style="height: 200px;"/>--%>
        <img/>
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
                        <img src="<%=path%>/img/policeman.png"/>
                        <button class="btn btn-gray btn-sm addphoto" type="button">添加照片</button>
                        <span class="hidden">上传图片过大，请上传小于500Kb的图片</span>
                        <input type="file" id="photoFile" name="photoFile" class="photoFile"
                               accept="image/*"/>
                        <input type="hidden" id="personInfoFileTxt" class="form-control"/>
                        <input type="hidden" id="personBase" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>身份证号</label>
                        <div class="row">
                            <div class="col-md-8 nopadding">
                                <input type="text" class="form-control" placeholder="请输入身份证号"
                                       name="idCard" id="idCard">
                            </div>
                            <div class="col-md-4 nopadding">
                                <label class="custom-control checkbox-inline">
                                    <input class="custom-control-input" type="checkbox"
                                           name="noIdCheck">
                                    <span class="custom-control-label">无</span>
                                </label>
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
                    <button type="button" class="btn btn-blue btn-lang addAuthenticator" name="querButton">确认</button>
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
                        <div class="col-md-6 hidden">
                            <div class="form-group">
                                <label>关联人员</label>
                                <div class="select">
                                    <select class="form-control" required name="relationPersonName">

                                    </select>
                                </div>
                            </div>
                        </div>
                        <c:if test="${limsCaseInfo.caseStatus ne '01'}">
                            <div class="col-md-6 hidden">
                                <div class="form-group">
                                    <label>样本编号</label>
                                    <input type="text" class="form-control" name="sampleNo" disabled>
                                </div>
                            </div>
                        </c:if>
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
                                        <input type="file" name="samplePhoto" id="samplePhoto"
                                               class="samplePhoto"
                                               accept="image/*"/>
                                        <input type="hidden" id="sampleInfoFileTxt"
                                               class="form-control"/>
                                        <input type="hidden" id="sampleBase" class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer hidden">
                    <input type="hidden" name="index"/>
                    <input type="hidden" name="consignmentId" value="${consignatioInfo.consignmentId}">
                    <input type="hidden" name="caseId" value="${consignatioInfo.caseId}">
                    <input type="hidden" name="appendFlag" value="${consignatioInfo.appendFlag}">
                    <button type="button" class="btn btn-blue btn-lang addSampleBtn" name="querenSample">确认</button>
                    <button type="button" class="btn btn-blue-border  btn-lang" data-dismiss="modal">
                        取消
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<body>
<div class="row fixedBox hidden">
    <ul>
        <li>案件信息</li>
        <li>委托信息</li>
        <li>鉴定要求</li>
        <li>被鉴定人信息</li>
        <li>人员样本信息</li>
        <li>物证检材信息</li>
    </ul>
</div>

<form id="saveform" enctype="multipart/form-data" method="post">
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading yellow">
                    <input type="hidden" name="ipAddress" id="ipAddress" value="${ipAddr}">
                    <div>案件信息</div>
                    <label class="custom-control checkbox-inline">
                        <input class="custom-control-input" type="checkbox" name="caseUrgentFlag" id="caseUrgentFlag"
                               value="${limsCaseInfo.caseUrgentFlag}">
                        <span class="custom-control-label" style="color: #ff5951">加急</span>
                    </label>
                </div>
                <div class="panel-body">
                    <div class="row inputBox">
                        <c:if test="${limsCaseInfo.caseStatus != '01'}">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>案件编号</label>
                                    <i class="required">*</i>
                                    <input type="text" value="${limsCaseInfo.caseNo}"
                                           class="form-control">
                                </div>
                            </div>
                        </c:if>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>委托编号</label>
                                <i class="required">*</i>
                                <input type="text" name="consignmentNo" id="consignmentNo"
                                       value="${consignatioInfo.consignmentNo}"
                                       class="form-control">
                            </div>
                        </div>
                        <c:if test="${not empty limsCaseInfo.caseXkNo}">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>现勘编号</label>
                                    <i class="required">*</i>
                                    <input type="text" name="caseXkNo" id="caseXkNo" value="${limsCaseInfo.caseXkNo}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>现勘委托编号</label>
                                    <i class="required">*</i>
                                    <input type="text" value="${limsCaseInfo.consignationXkNo}"
                                           class="form-control">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label>案件三版本编号</label>
                                    <i class="required">*</i>
                                    <input type="text" value="${limsCaseInfo.xkANo}"
                                           class="form-control">
                                </div>
                            </div>
                        </c:if>
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
                                <i class="required">*</i>
                                <input type="text" name="caseLocation" id="caseLocation"
                                       value="${limsCaseInfo.caseLocation}" class="form-control" placeholder="请输入案发地点">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案发时间</label>
                                <i class="required">*</i>
                                <input type="text" name="caseDatetime" id="caseDatetime"
                                       value="<fmt:formatDate value='${limsCaseInfo.caseDatetime}' pattern='yyyy-MM-dd '/>"
                                       class="form-control form_datetime" placeholder="请输入案发时间">
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="form-group">
                                <label>案件性质</label>
                                <i class="required">*</i>
                                <div class="select">
                                    <select class="form-control" name="caseProperty" id="caseProperty">
                                        <option value="" disabled selected hidden>请选择案件性质</option>
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
                                <input type="text" name="caseRemark" id="caseRemark" value="${limsCaseInfo.caseRemark}"
                                       class="form-control">
                            </div>
                        </div>
                        <img src="<%=path%>/img/jiaji.png" alt="" class="jiajiImg">
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

                    </div>
                </div>
            </div>
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
                        <div class="col-md-2">
                            <div class="form-group">
                                <label>委托单位 :</label>
                                <input type="hidden" id="consignmentId" value="${consignatioInfo.consignmentId}">
                                <input type="hidden" id="appendFlag" value="${consignatioInfo.appendFlag}">
                                <input type="hidden" id="delegateOrgCode" name="delegateOrgCode"
                                       value="${orgInfo.orgCode}">
                                <input type="hidden" id="delegateOrgName" name="delegateOrgName"
                                       value="${orgInfo.orgName}">
                                <label style="color: #ffb802;">${orgInfo.orgName}</label>
                            </div>
                        </div>
                        <div class="col-md-4 small-left">
                            <div class="form-group">
                                <label class="col-md-3 control-label">所属辖区 :</label>
                                <i class="required">*</i>
                                <div class="col-sm-9" style="margin-top: -7px;">
                                    <div class="select">
                                        <select class="form-control clientSelect" name="areaOrgCode" id="areaOrgCode" required>
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
                                                            id="delegator1Id" required>
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
                                                    <select class="form-control clientSelect" name="delegator2Id" required
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
                                                       type="text" class="form-control" name="clientIdType" value="警官证">
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
                    <%--<div class="form-group searchBox">--%>
                    <%--<input type="text" class="form-control" placeholder="请输入身份证号">--%>
                    <%--</div>--%>
                    <%--<button class="btn btn-blue-border" type="button">查询</button>--%>
                    <%--<button class="btn btn-blue" id="openAddAuthenticatorBig" type="button">--%>
                        <%--添加--%>
                    <%--</button>--%>
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
                            <th>人员照片</th>
                            <%--<th>操作</th>--%>
                        </tr>
                        </thead>
                        <tbody id="authenticatorTbody">
                        <c:forEach items="${limsPersonInfoList}" var="limsPersonInfo" varStatus="s">
                            <tr>
                                <td>${s.count}</td>
                                <td>${limsPersonInfo.personTypeName}
                                    <input type="hidden" name="personTypeName" value="${limsPersonInfo.personTypeName}">
                                </td>
                                <td>${limsPersonInfo.personName}
                                    <input type="hidden" name="personName" value="${limsPersonInfo.personName}">
                                </td>
                                <td>${limsPersonInfo.personGenderName}
                                    <input type="hidden" name="sexName" value="${limsPersonInfo.personGenderName}">
                                </td>
                                <td>${limsPersonInfo.perosnAge}
                                    <input type="hidden" name="year" value="${limsPersonInfo.perosnAge}">
                                </td>
                                <td>${limsPersonInfo.personIdCard}${limsPersonInfo.noIdCardDesc}
                                    <input type="hidden" name="idCard" value="${limsPersonInfo.personIdCard}">
                                    <input type="hidden" name="noIdCard" value="${limsPersonInfo.noIdCardDesc}">
                                </td>
                                <td>${limsPersonInfo.relationTypeName}
                                    <input type="hidden" name="kinshipName" value="${limsPersonInfo.relationTypeName}">
                                </td>
                                <td>
                                    <div class="showPhoto"></div>
                                </td>
                                <%--<td>--%>
                                    <%--<button type="button" name="editBtn"--%>
                                            <%--class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑--%>
                                    <%--</button>--%>
                                    <%--<input type="hidden" name="personId" value="${limsPersonInfo.personId}">--%>
                                    <%--<input type="hidden" name="idCardFlag" value="${limsPersonInfo.idCardFlag}">--%>
                                    <%--<input type="hidden" name="sex" value="${limsPersonInfo.personGender}">--%>
                                    <%--<input type="hidden" name="noIdCardDesc" value="${limsPersonInfo.noIdCardDesc}">--%>
                                    <%--<input type="hidden" name="personHeight" value="${limsPersonInfo.personHeight}">--%>
                                    <%--<input type="hidden" name="personWeight" value="${limsPersonInfo.personWeight}">--%>
                                    <%--<input type="hidden" name="imgsrc" value="">--%>
                                    <%--<input type="hidden" name="personCurrentAddress"--%>
                                           <%--value="${limsPersonInfo.personCurrentAddress}">--%>
                                    <%--<input type="hidden" name="personFrontPicture"--%>
                                           <%--value="${limsPersonInfo.personFrontPicture}">--%>
                                    <%--<input type="hidden" name="personFrontPicturePath"--%>
                                           <%--value="${limsPersonInfo.personFrontPicturePath}">--%>
                                    <%--<input type="hidden" name="personType" value="${limsPersonInfo.personType}">--%>
                                    <%--<input type="hidden" name="kinship" value="${limsPersonInfo.relationType}">--%>
                                <%--</td>--%>
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
                    <%--<button class="btn btn-blue addsampleInfo" type="button">添加</button>--%>
                    <%--<div class="form-group searchBox">--%>
                    <%--<input type="text" class="form-control" placeholder="请输入样本编号">--%>
                    <%--</div>--%>
                    <%--<button class="btn btn-blue-border" type="button">查询</button>--%>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable">
                        <thead>
                        <tr>
                            <c:if test="${limsCaseInfo.caseStatus ne '01'}">
                                <th style="width: 140px;">样本编号</th>
                            </c:if>
                            <c:if test="${limsCaseInfo.caseStatus ne '02'}">
                                <th style="width:140px;">序号</th>
                            </c:if>
                            <th style="width:80px;">样本类型</th>
                            <th>样本名称</th>
                            <th>样本描述</th>
                            <th style="width: 95px;">包装方法</th>
                            <th style="width: 100px;">提取时间</th>
                            <th style="width:165px">提取方法</th>
                            <th style="width: 90px;">送检目的</th>
                            <th>关联人员</th>
                            <th style="width: 50px;">是否拒绝</th>
                            <th style="width: 90px;">是否为事主<i class="required">*</i></th>
                            <%--<th style="width: 102px;">操作</th>--%>
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
                                            <input type="hidden" name="sampleNo" value="${sampleInfoDna.sampleNo}">
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
                                    <div class="isRefuse <c:if test="${sampleInfoDna.sampleStatus eq '04'}">active</c:if>"></div>
                                    <input type="hidden" name="isRefuse">
                                    <input type="hidden" name="isRefuseCode" value="${sampleInfoDna.sampleStatus}">
                                </td>
                                <td>
                                    <div class="victimSelect">
                                        <select class="form-control" name="coreVictimStats" id="coreVictimStats"  required>
                                            <option value="1" <c:if test="${ sampleInfoDna.coreVictimStats == 1 || sampleInfoDna.coreVictimStats == 2}">selected</c:if>>是</option>
                                            <option value="0"<c:if test="${'0' eq sampleInfoDna.coreVictimStats}">selected</c:if>>否</option>
                                        </select>
                                    </div>
                                </td>
                                <%--<td>--%>
                                    <%--<input type="hidden" name="sampleType" value="${sampleInfoDna.sampleType}">--%>
                                    <%--<input type="hidden" name="samplePacking" value="${sampleInfoDna.samplePacking}">--%>
                                    <%--<input type="hidden" name="extractMethod" value="${sampleInfoDna.extractMethod}">--%>
                                    <%--<input type="hidden" name="sampleCarrier" value="${sampleInfoDna.sampleCarrier}">--%>
                                    <%--<input type="hidden" name="sampleId" value="${sampleInfoDna.sampleId}">--%>
                                    <%--<input type="hidden" name="linkId" value="${sampleInfoDna.linkId}">--%>
                                    <%--<input type="hidden" name="sampleDnaPicture"--%>
                                           <%--value="${sampleInfoDna.sampleDnaPicture}">--%>
                                    <%--<input type="hidden" name="sampleImgSrc" value="">--%>
                                    <%--<button type="button" name="editSampleInfoDnaBtn"--%>
                                            <%--class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑--%>
                                    <%--</button>--%>
                                    <%--<button type="button" name="delBtn"--%>
                                            <%--class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除--%>
                                    <%--</button>--%>
                                <%--</td>--%>
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
                    <%--<button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>--%>
                    <%--<div class="form-group searchBox">--%>
                    <%--<input type="text" class="form-control" placeholder="请输入检材编号">--%>
                    <%--</div>--%>
                    <%--<button class="btn btn-blue-border search" type="button">查询</button>--%>
                </div>
                <div class="panel-body nopadding">
                    <table class="table table-hover table-bordered bigTable" style="table-layout: fixed;">
                        <thead>
                        <tr>
                            <c:if test="${limsCaseInfo.caseStatus ne '01'}">
                                <th rowspan="2" style="width: 120px;">检材编号</th>
                            </c:if>
                            <c:if test="${limsCaseInfo.caseStatus ne '02'}">
                                <th rowspan="2" style="width: 120px;">序号</th>
                            </c:if>
                            <th rowspan="2" style="width:80px;">物证编号</th>
                            <th rowspan="2" style="width:80px;">检材类型</th>
                            <th rowspan="2" style="width: 100px;">检材名称</th>
                            <th rowspan="2" style="width: 100px;">检材描述</th>
                            <th rowspan="2" style="width: 80px;">包装方法</th>
                            <th rowspan="2" style="width: 90px;">提取时间</th>
                            <th rowspan="2" style="width: 162px;">提取方法</th>
                            <th rowspan="2" style="width: 90px;">送检目的</th>
                            <th colspan="3" style="text-align: center;width:240px;">预实验</th>
                            <th rowspan="2" style="width: 52px;">是否拒绝</th>
                            <th rowspan="2" style="width: 52px;">是否留存</th>
                            <th rowspan="2" style="width: 55px;">是否中心提取<i class="required">*</i></th>
                            <%--<th rowspan="2" style="width: 102px;">操作</th>--%>
                        </tr>
                        <tr>
                            <th>FOB</th>
                            <th>PSA</th>
                            <th>联苯胺</th>
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
                                            <input type="hidden" class="form-control" name="sampleNo"
                                                   value="${sampleInfo.sampleNo}">
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td title="${sampleInfo.evidenceNo}">${sampleInfo.evidenceNo}
                                    <input type="hidden" name="evidenceNo" value="${sampleInfo.evidenceNo}">
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
                                    <%--<td>${sampleInfo.extractMethodName}--%>
                                    <%--<input type="hidden" name="extractMethodName"--%>
                                    <%--value="${sampleInfo.extractMethodName}">--%>
                                    <%--</td>--%>
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
                                    <div class="select">
                                        <select class="form-control"  name="preMethod1Result"
                                                id="preMethod1Result">
                                            <option value="" <c:if test="${'' eq sampleInfo.preMethod1Result}">selected</c:if>>无</option>
                                            <option value="+" <c:if test="${'+' eq sampleInfo.preMethod1Result}">selected</c:if>>+</option>
                                            <option value="-" <c:if test="${'-' eq sampleInfo.preMethod1Result}">selected</c:if>>-</option>

                                                <%--<option value="">无</option>--%>
                                                <%--<option value="+">+</option>--%>
                                                <%--<option value="-">-</option>--%>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control"  name="preMethod2Result"
                                                id="preMethod2Result">
                                            <option value="" <c:if test="${'' eq sampleInfo.preMethod2Result}">selected</c:if>>无</option>
                                            <option value="+" <c:if test="${'+' eq sampleInfo.preMethod2Result}">selected</c:if>>+</option>
                                            <option value="-" <c:if test="${'-' eq sampleInfo.preMethod2Result}">selected</c:if>>-</option>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="select">
                                        <select class="form-control"  name="preMethod3Result"
                                                id="preMethod3Result">
                                            <option value="" <c:if test="${'' eq sampleInfo.preMethod3Result}">selected</c:if>>无</option>
                                            <option value="+" <c:if test="${'+' eq sampleInfo.preMethod3Result}">selected</c:if>>+</option>
                                            <option value="-" <c:if test="${'-' eq sampleInfo.preMethod3Result}">selected</c:if>>-</option>
                                        </select>
                                    </div>
                                </td>
                                <td>
                                    <div class="isRefuse <c:if test="${sampleInfo.sampleStatus eq '04'}">active</c:if>"></div>
                                    <input type="hidden" name="isRefuse">
                                    <input type="hidden" name="isRefuseCode" value="${sampleInfo.sampleStatus}">
                                </td>
                                <td>
                                    <div class="isRetain <c:if test='${sampleInfo.isRetain eq 1}'>active</c:if>"></div>
                                </td>
                                <td>
                                    <div class="coreTakenSelect">
                                        <select class="form-control" name="coreTakenStats" required>
                                            <option value=" " > </option>
                                            <option value="1"
                                                    <c:if test="${'1' eq sampleInfo.coreTakenStats}">selected</c:if>>是
                                            </option>
                                            <option value="0"
                                                    <c:if test="${'0' eq sampleInfo.coreTakenStats}">selected</c:if>>否
                                            </option>
                                        </select>
                                    </div>
                                </td>
                                <%--<td>--%>
                                    <%--<button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑--%>
                                    <%--</button>--%>
                                    <%--<button type="button" name="delBtn"--%>
                                            <%--class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除--%>
                                    <%--</button>--%>
                                    <%--<input type="hidden" name="sampleId" value="${sampleInfo.sampleId}">--%>
                                    <%--<input type="hidden" name="sampleType" value="${sampleInfo.sampleType}">--%>
                                    <%--<input type="hidden" name="samplePacking" value="${sampleInfo.samplePacking}">--%>
                                    <%--<input type="hidden" name="extractMethod" value="${sampleInfo.extractMethod}">--%>
                                    <%--<input type="hidden" name="sampleCarrier" value="${ sampleInfo.sampleCarrier}">--%>
                                    <%--<input type="hidden" name="evidenceNo" value="${sampleInfo.evidenceNo}">--%>
                                    <%--<input type="hidden" name="samplePicture"--%>
                                           <%--value="${sampleInfo.sampleMaterialPicture}">--%>
                                <%--</td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="bs-callout bs-callout-danger" id="callout-inline-form-width">
        <h4>检材说明</h4>
        <p>经本人现场全程监督，证实所送检材在拍照，剪取，存放于提取管的过程中编号均一致。</p>
        <p class="zhanshi">本案所送现场检材<span></span><span>均已取走</span></p>
        <%--<p>本案所送现场检材除（呵呵哒、某某（血样））均已取走--%>
        <%--<p>本案所送现场检材全部留存--%>
        </p>

        <p>取走人: <input type="text" name="takePerson" id="takePerson" class="form-control"
                       style="width: 100px;display: inline-block"></p>
    </div>
    <div class="row btn-box " style="margin-right: 0px">
        <div class="col-md-12">
            <button type="button" class="btn btn-blue btn-lang" id="viewCaseInfo">返回
                <%--<button type="button" class="btn btn-blue btn-lang" onclick="exec()">确认受理--%>
            </button>
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
<script src="<%=path%>/lib/jSignature/jSignature.min.js"></script>

<script>


    $(function () {


        $("#viewCaseInfo").bind('click',function(){
            window.history.go(-1);
        })

        $("#takePerson").val($("#delegator1Id").children("option:selected").html() + "," + $("#delegator2Id").children("option:selected").html())

        var identifyRequirement = '${consignatioInfo.identifyRequirement}';

        $.each(identifyRequirement.split(","), function (i, item) {
            $(".btn-checkbox").find("li[value='" + item + "']").addClass("active")
        })

        if ('${consignatioInfo.status}' != "01") {
            // $(".btn-box").addClass("hidden")
            $(".btn-box").find("button").html("返回")
        }

        <%--if ('${limsCaseInfo.caseXkNo}' == '') {--%>
        <%--$("#materialEvidencerTbody").parents(".Modular").addClass("hidden")--%>
        <%--$(".fixedBox").find("li:last").addClass("hidden")--%>
        <%--}--%>

        if ($("#caseUrgentFlag").val() == 1) {
            $("input[name='caseUrgentFlag']").attr('checked', 'true')
            $(".jiajiImg").animate({
                opacity: '1',
                height: '150px',
                width: '150px'
            });
        }
        //加急特效
        $("#caseUrgentFlag").change(function () {
            if ($(this).is(":checked")) {
                $(".jiajiImg").animate({
                    opacity: '1',
                    height: '150px',
                    width: '150px'
                });
            } else {
                $(".jiajiImg").animate({
                    opacity: '0',
                    height: '175px',
                    width: '175px'
                });
            }
        })
        //        手写签字板
        $("#signature").on("mousedown", "canvas", function () {
            $(this).siblings(".bg").addClass("hidden");
        })
        $('#autograph').on('shown.bs.modal', function (e) {
            $("#signature").jSignature({
                'height': '100%',
                'width': '100%'
            });
        })
        $('#autograph').on('hidden.bs.modal', function (e) {
            $("#signature").jSignature("clear");
            $("#signature").children().remove()
        })


        $("#noSignatureHref").click(function () {
            $(this).attr("disabled", "disabled");
            $(".saveSignature").attr("disabled", "disabled");
            // $(this).unbind('click');
            /**
             * 保存整个页面信息
             */

            <%--var url = "<%=path%>/center/submitAcceptCase";--%>

            /**
             * 保存整个页面信息
             */
            if ($.trim($(".btn-box").find("button").html()) == "确认受理") {
                var url = "<%=path%>/center/submitAcceptCase";
            } else {
                var url = "<%=path%>/center/updateCase";
            }

            var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();
            var caseId = $("input[name='caseId']", $(this).parent()).val();
            var appendFlag = $("input[name='appendFlag']", $(this).parent()).val();
//            var signature = $("#signature");
//            var signatureSrc = "data:" + signature.jSignature("getData", "image")[0] + "," + signature.jSignature("getData", "image")[1]
//            $(this).siblings("input[name='signatureBase']").val(signatureSrc)
            var form = $("#saveform ")

            var caseInfoDna = getCaseInfoDna();
            var limsConsignmentInfo = getConsignmentInfo();
            var sampleInfoDnaList = getSampleInfoDna();
            var limsPersonInfoList = getLimsPersonInfo();

            var paramsData = {
                "caseInfoDna": caseInfoDna,
                "consignmentInfo": limsConsignmentInfo,
                "sampleInfoDnaList": sampleInfoDnaList,
                "limsPersonInfoList": limsPersonInfoList,
                "personIds": personIds,
                "sampleIds": sampleIds,
                "sampleIdWzs": sampleIdWzs,
            };
            console.log(paramsData)
            $('#saveform').ajaxSubmit({
                url: url,
                type: "post",
                data: {"paramsData": JSON.stringify(paramsData)},
                dataType: "json",
                success: function (data) {
                    location.href = "<%=path%>/center/print?consignmentId=" + consignmentId + "&caseId=" + caseId;
                },
                error: function (e) {
                    alert(e);
                }
            });
        });

//        确认生成图片
        $(".saveSignature").click(function () {
            $(this).attr("disabled", "disabled");
            $("#noSignatureHref").attr("disabled", "disabled");

            /**
             * 保存整个页面信息
             */
            if ($.trim($(".btn-box").find("button").html()) == "确认受理") {
                var url = "<%=path%>/center/submitAcceptCase";
            } else {
                var url = "<%=path%>/center/updateCase";
            }


            var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();
            var caseId = $("input[name='caseId']", $(this).parent()).val();

            // var signatureSrc = $("#signPices").attr("src");
            var signature = $("#signature");
            var signatureSrc = "data:" + signature.jSignature("getData", "image")[0] + "," + signature.jSignature("getData", "image")[1];
            // var signatureSrc = signature.jSignature("getData", "image")[1]
            $(this).siblings("input[name='signatureBase']").val(signatureSrc)
            var form = $("#saveform ")

            var caseInfoDna = getCaseInfoDna();
            var limsConsignmentInfo = getConsignmentInfo();
            var sampleInfoDnaList = getSampleInfoDna();
            var limsPersonInfoList = getLimsPersonInfo();

            var paramsData = {
                "caseInfoDna": caseInfoDna,
                "consignmentInfo": limsConsignmentInfo,
                "sampleInfoDnaList": sampleInfoDnaList,
                "limsPersonInfoList": limsPersonInfoList,
                "personIds": personIds,
                "sampleIds": sampleIds,
                "sampleIdWzs": sampleIdWzs,
            };

            $('#saveform').ajaxSubmit({
                url: url,
                type: "post",
                data: {"paramsData": JSON.stringify(paramsData)},
                dataType: "json",
                success: function (data) {
                    location.href = "<%=path%>/center/print?consignmentId=" + consignmentId + "&caseId=" + caseId;
                },
                error: function (e) {
                    alert(e);
                }
            });

        })

        function scroll(form) {
            $(form).find("small[data-bv-result='INVALID']").each(function (index, ele) {
                if (index == 0) {
                    $("body,html").animate({
                        scrollTop: $(this).offset().top - 100
                    })
                    $(this).prev().focus()
                }
            })
        }

        retained();
        //回显留存
        function retained(){
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active")
                if ($(this).parents("tr").find(".isRefuse").hasClass("active")) {
                    $(this).parents("tr").find(".isRefuse").removeClass("active")
                    $(this).parents("tr").find(".isRefuse").siblings('input[name="isRefuse"]').val("")
                    $(this).parents("tr").find(".isRefuse").siblings('input[name="isRefuseCode"]').val("02")
                }
            }
            var retainArr = []
            $(".isRetain").each(function () {
                if ($(this).hasClass("active")) {
                    retainArr.push($(this).parents("tr").find("input[name='sampleName']").val())
                }
            })
            if (retainArr.length > 0 && retainArr.length !== $("#materialEvidencerTbody").children().length) {
                $(".zhanshi").find("span").eq(0).html("除(" + retainArr.join('、') + ")").next().html("均已取走")
            } else if (retainArr.length == $("#materialEvidencerTbody").children().length) {
                $(".zhanshi").find("span").eq(0).html("").next().html("均已留存")
            } else {
                $(".zhanshi").find("span").eq(0).html("").next().html("均已取走")
            }

        }


        //留存切换
        $("tbody").on('click', '.isRetain', function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
            } else {
                $(this).addClass("active")
                if ($(this).parents("tr").find(".isRefuse").hasClass("active")) {
                    $(this).parents("tr").find(".isRefuse").removeClass("active")
                    $(this).parents("tr").find(".isRefuse").siblings('input[name="isRefuse"]').val("")
                    $(this).parents("tr").find(".isRefuse").siblings('input[name="isRefuseCode"]').val("02")
                }
            }
            var retainArr = []
            $(".isRetain").each(function () {
                if ($(this).hasClass("active")) {
                    retainArr.push($(this).parents("tr").find("input[name='sampleName']").val())
                }
            })
            if (retainArr.length > 0 && retainArr.length !== $("#materialEvidencerTbody").children().length) {
                $(".zhanshi").find("span").eq(0).html("除(" + retainArr.join('、') + ")").next().html("均已取走")
            } else if (retainArr.length == $("#materialEvidencerTbody").children().length) {
                $(".zhanshi").find("span").eq(0).html("").next().html("均已留存")
            } else {
                $(".zhanshi").find("span").eq(0).html("").next().html("均已取走")
            }
        })

        $(".saveBoxBtn").mousedown(function () {
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
                    caseType: {
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
                    caseLevel: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
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
            }
        })


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
            $('.identificationCenter').html($(this).html())
            $(this).parents(".selectbox").addClass("hidden")
        })
        //现勘修改保存
        $(".modifySiteSurve").click(function () {
            $(".starBox").eq(1).find("span").html($("#siteSurveyBox").find("input").val())
            $('#siteSurveyBox').modal('hide')
        })
        // 现勘改关闭清空
        $('#siteSurveyBox').on('hidden.bs.modal', function (e) {
            $("#siteSurveyBox").find("input").val("")
        })
        // 页面滚动定位
        window.onscroll = function () {
            if ($(document).scrollTop() >= 112) {
                $(".fixedBox").removeClass("hidden")
                var currentScroll = $(this).scrollTop()
                var currentSection
                $(".Modular").each(function () {
                    var divPosition = $(this).offset().top - 62;
                    if (currentScroll > divPosition) {
                        currentSection = $(this).find(".panel-heading").children().html()
                    }
                })
                $(".fixedBox").find("li").each(function () {
                    if ($(this).html() == currentSection) {
                        $(this).addClass("now").siblings().removeClass("now")
                    }
                })
            } else {
                $(".fixedBox").addClass("hidden")
            }
        }
        // 顶部导航点击定位
        $(".fixedBox").find("li").click(function () {
            var that = $(this)
            $(".Modular").each(function () {
                if (that.html() == $(this).find(".panel-heading").children().html()) {
                    $("html,body").animate({
                        scrollTop: $(this).offset().top - 60
                    }, 500);
                }
            })
            that.addClass("liActive").siblings().removeClass("liActive")
        })
        //被鉴定人添加样本
        $("#addAuthenticatorBig").find("input[name='moveCheckbox']").change(function () {
            if ($(this).is(':checked')) {
                $("#addAuthenticatorBig").children().width(1200)
                $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                    "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
                }).fadeIn(800);
            } else {
                $("#addAuthenticatorBig").children().width(480)
                $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                    "display": "none",
                });
            }
        })


        //被鉴定人更多信息
        $("#addAuthenticatorBig").find(".moveBtn").click(function () {
            if ($(this).html() == "添加更多") {
                $("#addAuthenticatorBig").find(".moveInput").removeClass("hidden")
                $(this).html("清除关闭")
            } else {
                $("#addAuthenticatorBig").find(".moveInput").addClass("hidden").find("input").val('')
                $(this).html("添加更多")
            }
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
            });
        })
        // 被鉴定人身份证切换
        $("#addAuthenticatorBig").find("input[name='noIdCheck']").change(function () {
            if ($("#addAuthenticatorBig").find(".has-error").length > 0) {
                var form = $("#addAuthenticatorBig").find("form")
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);
            }
            if ($(this).is(":checked")) {
                $("#addAuthenticatorBig").find("input[name='idCard']").val("").prop("disabled", true)
                $("#addAuthenticatorBig").find("input[name='noIdCard']").val("").removeClass("hidden")
            } else {
                $("#addAuthenticatorBig").find("input[name='idCard']").val("").prop("disabled", false)
                $("#addAuthenticatorBig").find("input[name='noIdCard']").val("").addClass("hidden")
            }
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
            });
        })
        //身份证验证
        function isCardNo(card) {
            var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            return pattern.test(card);
        }

        // 被鉴定人年龄计算
        $("#addAuthenticatorBig").find("input[name='idCard']").blur(function () {
            if ($.trim($(this).val()).length > 0 && isCardNo($.trim($(this).val()))) {
                $(this).siblings("small").addClass("hidden")
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
                    $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year).change()
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
                    $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year).change()
                }
            } else {
                $("#verification").addClass("btn-gray").removeClass("btn-blue")
                $(this).siblings("small").removeClass("hidden")
            }
        })
        // 被鉴定人亲缘切换
        $("#addAuthenticatorBig").find("select[name='personType']").change(function () {
            if ($(this).find("option:selected").text().indexOf("亲属") != -1) {
                var headerHtml = $(this).find("option:selected").text().substring(0, $(this).find("option:selected").text().indexOf("亲属"))
                $("#addAuthenticatorBig").find("select[name='kinship']").parents(".form-group").removeClass("hidden").find("label").html("与" + headerHtml + "亲缘关系")
            } else {
                $("#addAuthenticatorBig").find("select[name='kinship']").val("").parents(".form-group").addClass("hidden")
            }
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
            });
        })
        //被鉴定人照片添加
        $(".addphoto").click(function () {
            $(this).siblings("input[type='file']").val("").click()
        })
        $(".photoFile").change(function (e) {
            var file = e.target.files[0];
            if (file.size / 1024 > 500) {
                $(this).siblings("span").removeClass("hidden")
                $(this).val("")
            } else {
                $(this).siblings("span").addClass("hidden")
                var reader = new FileReader();
                console.log(file.size / 1024)
                reader.readAsDataURL(file); // 读出 base64
                reader.onloadend = function () {
                    var dataURL = reader.result;
                    $("#personBase").val(dataURL)
                };
                $(this).siblings("button").html("修改")
                $(this).siblings("img").attr("src", URL.createObjectURL($(this)[0].files[0])).attr("alt", e.currentTarget.files[0].name);
            }

        })
        //被鉴定人样本照片添加
        $("#addAuthenticatorBig").find(".addsamplePhoto").click(function () {
            $(this).siblings("input[type='file']").val("").click()
        })
        $("#addAuthenticatorBig").find(".samplePhoto").change(function (e) {
            var file = e.target.files[0];
            if (file.size / 1024 > 500) {
                $(this).parents(".samplePhotobox").siblings("span").removeClass("hidden")
                $(this).val("")
            } else {
                $(this).parents(".samplePhotobox").siblings("span").addClass("hidden")
                var reader = new FileReader();
                reader.readAsDataURL(file); // 读出 base64
                reader.onloadend = function () {
                    var dataURL = reader.result;
                    $("#sampleBase").val(dataURL)
                };
                $.each($(this)[0].files, function (i, item) {
                    var newPhoto = '<div class="col-md-2"><img src="' + URL.createObjectURL(item) + '" alt="' + e.currentTarget.files[0].name + '"><i class="fa fa-times-circle removePhoto" aria-hidden="true"></i></div>'
                    $("#addAuthenticatorBig").find(".addsamplePhoto").parent().before(newPhoto)
                })
            }
        })
        $("#addAuthenticatorBig").find(".samplePhotobox").on("click", ".removePhoto", function () {
            var that = $(this)
            $.each($("#addAuthenticatorBig").find(".samplePhoto")[0].files, function (i, item) {
                if (that.siblings('img').attr("src") == URL.createObjectURL(item)) {
                    delete $("#addAuthenticatorBig").find(".samplePhoto")[0].files.i
                }
            })
            $("#sampleBase").val("")
            $(this).parents(".col-md-2").eq(0).remove()
        })
        //检材照片添加
        $("#materialEvidencerlSampleBox").find(".addsamplePhoto").click(function () {
            $(this).siblings("input[type='file']").val("").click()
        })
        $("#materialEvidencerlSampleBox").find(".samplePhoto").change(function (e) {
            var file = e.target.files[0];
            if (file.size / 1024 > 500) {
                $(this).parents(".samplePhotobox").siblings("span").removeClass("hidden")
                $(this).val("")
            } else {
                $(this).parents(".samplePhotobox").siblings("span").addClass("hidden")
                var reader = new FileReader();
                reader.readAsDataURL(file); // 读出 base64
                reader.onloadend = function () {
                    var dataURL = reader.result;
                    $("#samplePicture").val(dataURL)
                };
                $.each($(this)[0].files, function (i, item) {
                    var newPhoto = '<div class="col-md-2"><img src="' + URL.createObjectURL(item) + '" alt="' + e.currentTarget.files[0].name + '"><i class="fa fa-times-circle removePhoto" aria-hidden="true"></i></div>'
                    $("#materialEvidencerlSampleBox").find(".addsamplePhoto").parent().before(newPhoto)
                })
            }
        })
        $("#materialEvidencerlSampleBox").find(".samplePhotobox").on("click", ".removePhoto", function () {
            var that = $(this)
            $.each($("#materialEvidencerlSampleBox").find(".samplePhoto")[0].files, function (i, item) {
                if (that.siblings('img').attr("src") == URL.createObjectURL(item)) {
                    delete  $("#materialEvidencerlSampleBox").find(".samplePhoto")[0].files.i
                }
            })
            $("#samplePicture").val("")
            $(this).parents(".col-md-2").eq(0).remove()
        })


        //样本名称关联
        $("#addAuthenticatorBig").find(".sampleBox").find("#sampleType").change(function () {
            if ($("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").hasClass("hidden")) {
                var values = $("#addAuthenticatorBig").find("#personName").val() + $(this).children(":selected").html()
                $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val(values)
                $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
                if ($("#personType").val()) {
                    $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val($("#personType").children(":selected").html() + values)
                }
            } else {
                var values = $("#addAuthenticatorBig").find('select[name="relationPersonName"]').find("option:selected").text() + $(this).children(":selected").html()
                $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val(values)
                $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val($("#addAuthenticatorBig").find('select[name="relationPersonName"]').val() + values)
            }

        })
        $("#addAuthenticatorBig").find("#personType").change(function () {
            var values = $(this).children(":selected").html() + $("#personName").val()
            if ($("#sampleType").val()) {
                $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values + $("#sampleType").children(":selected").html())
            }
        })
        $("#addAuthenticatorBig").find("#personName").change(function () {
            var values = $(this).val()
            if ($("#personType").children(":selected").html() !== "请选择人员类型") {
                values = $("#personType").children(":selected").html() + values
            }
            if ($("#sampleType").children(":selected").html() !== "请选择样本类型") {
                values = values + $("#sampleType").children(":selected").html()
                $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).val() + $("#sampleType").children(":selected").html())
            }
            $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
        })
        $("#addAuthenticatorBig").find('select[name="relationPersonName"]').change(function () {
            var values = $(this).val() + $(this).find("option:selected").text()
            if ($("#sampleType").children(":selected").html() !== "请选择样本类型") {
                $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).find("option:selected").text() + $("#sampleType").children(":selected").html())
                $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values + $("#sampleType").children(":selected").html())
            } else {
                $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).find("option:selected").text())
                $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
            }
        })

        //拒绝弹出框
        $("tbody").on("click", ".isRefuse", function () {
            if ($(this).hasClass("active")) {
                $(this).removeClass("active")
                $(this).siblings("input").val("")
                $(this).siblings("input[name='isRefuseCode']").val("02")
            } else {
                $(this).addClass("active")
                $("#refuseBox").find("input[name='index']").val($(this).parents("tr").index())
                $("#refuseBox").find("input[name='parentId']").val($(this).parents("tbody").attr("id"))
                $("#refuse").val("");
                $("#refuseBox").modal("show")
            }
        })
        //拒绝弹出框
        $(".saveRefuse").click(function () {
            var parentId = $("#refuseBox").find("input[name='parentId']").val()
            var index = $("#refuseBox").find("input[name='index']").val()
            $("#" + parentId).find("tr").eq(index).find("input[name='isRefuse']").val($("#refuse").val())
            $("#" + parentId).find("tr").eq(index).find("input[name='isRefuseCode']").val("04")
            if ($("#" + parentId).find("tr").eq(index).find(".isRetain").hasClass("active")) {
                $("#" + parentId).find("tr").eq(index).find(".isRetain").removeClass("active")
                var retainArr = []
                $(".isRetain").each(function () {
                    if ($(this).hasClass("active")) {
                        retainArr.push($(this).parents("tr").find("input[name='sampleName']").val())
                    }
                })
                if (retainArr.length > 0 && retainArr.length !== $("#materialEvidencerTbody").children().length) {
                    $(".zhanshi").find("span").eq(0).html("除(" + retainArr.join('、') + ")").next().html("均已取走")
                } else if (retainArr.length == $("#materialEvidencerTbody").children().length) {
                    $(".zhanshi").find("span").eq(0).html("").next().html("均已留存")
                } else {
                    $(".zhanshi").find("span").eq(0).html("").next().html("均已取走")
                }
            }
            $("#refuseBox").modal("hide")
        })
        $(".closeRefuse").click(function () {
            var parentId = $("#refuseBox").find("input[name='parentId']").val()
            var index = $("#refuseBox").find("input[name='index']").val()
            $("#" + parentId).find("tr").eq(index).find(".isRefuse").removeClass("active")
            $("#refuseBox").modal("hide")

        })
        $("#refuseBox").on('hidden.bs.modal', function () {
            $("#refuseBox").find("input").val("")
        })

        //添加被鉴定人
        $("#openAddAuthenticatorBig").click(function () {
            $("#addAuthenticatorBig").find("input[name='moveCheckbox']").prop("checked", true)
            $("#addAuthenticatorBig").children().width(1200)
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
                "height": 542.6 + 2,
            }).fadeIn(800);
            $("#addAuthenticatorBig").modal("show")
        })
        //新添修改被鉴定人信息
        //全局变量标识
        var CLICKTAG1 = 0;
        $(".addAuthenticator").click(function () {
            if (CLICKTAG1 == 0) {
                CLICKTAG1 = 1;
                //this.disabled=true;
                $("button[name='querButton']").attr("disabled", true)
                // 等待2s后重置按钮可用
                setTimeout(function () {
                    CLICKTAG1 = 0;
                    $("button[name='querButton']").removeAttr("disabled")
                }, 2000);
            }
            var form = $(this).parents("form");
            if ($("#addAuthenticatorBig").find("input[name='noIdCheck']").is(":checked")) {
                form.bootstrapValidator({
                    live: 'disabled',
                    message: 'This value is not valid',
                    fields: {
                        year: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                },
                                regexp: {
                                    regexp: /^[0-9]*$/,
                                    message: '格式有误.'
                                },
                            },
                        },
                        noIdCard: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        personType: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        personName: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        sampleType: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        extractTime: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        year: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                },
                                regexp: {
                                    regexp: /^[0-9]*$/,
                                    message: '格式有误.'
                                },
                            },
                        },
                    }
                });
            } else {
                form.bootstrapValidator({
                    live: 'disabled',
                    message: 'This value is not valid',
                    fields: {
                        year: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                },
                                regexp: {
                                    regexp: /^[0-9]*$/,
                                    message: '格式有误.'
                                },
                            },
                        },
                        idCard: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                },
                                regexp: {
                                    regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                                    message: '身份证输入有误'
                                },
                            }
                        },
                        year: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                },
                                regexp: {
                                    regexp: /^[0-9]*$/,
                                    message: '格式有误.'
                                },
                            },
                        },
                        personType: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        personName: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        sampleType: {
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                        extractTime: {
                            trigger: 'change',
                            validators: {
                                notEmpty: {
                                    message: "不能为空"
                                }
                            }
                        },
                    }
                });
            }
            form.bootstrapValidator('validate');
            scroll(form);
            if (form.data('bootstrapValidator').isValid()) {
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);

                var authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(0),
                        personType = authenticatorBox.find("select[name='personType']").val(),
                        personTypeName = authenticatorBox.find("select[name='personType']").find("option:selected").text(),
                        personName = authenticatorBox.find("input[name='personName']").val(),
                        sex = authenticatorBox.find("input[name='sex']:checked").val(),
                        idCard = authenticatorBox.find("input[name='idCard']").val(),
                        noIdCard = authenticatorBox.find("input[name='noIdCard']").val(),
                        nowId = "",
                        kinshipName = "",
                        year = authenticatorBox.find("input[name='year']").val(),
                        kinship = authenticatorBox.find("select[name='kinship']").val(),
                        personFileName = authenticatorBox.find("img").attr("alt"),
                        authenticatorIndex = authenticatorBox.find("input[name='index']").val(),
                        personCurrentAddress = authenticatorBox.find("input[name='personCurrentAddress']").val(),
                        personHeight = authenticatorBox.find("input[name='personHeight']").val(),
                        personWeight = authenticatorBox.find("input[name='personWeight']").val(),
                        personFrontPicture = $("#personBase").val(),
                        timeId = new Date().getTime(),
                        personLength = Number($("#authenticatorTbody").children().length) + 1;
                var sexName;
                if (sex == '01') {
                    sexName = '男'
                } else if (sex == '02') {
                    sexName = '女'
                }
                if (kinship == null) {
                    kinshipName = ''
                    kinship = ""
                } else {
                    kinshipName = authenticatorBox.find("select[name='kinship']").find("option:selected").text()
                }
                if (noIdCard !== "") {
                    nowId = '无身份证号(' + noIdCard + ')'
                } else {
                    nowId = idCard
                }

                if (authenticatorIndex != "") {
                    var tr = $("#authenticatorTbody").children("tr").eq(authenticatorIndex)
                    tr.find('input[name="personType"]').val(personType)
                    tr.find('input[name="personTypeName"]').val(personTypeName)
                    tr.find('input[name="personName"]').val(personName)
                    tr.find('input[name="sexName"]').val(sexName)
                    tr.find('input[name="sex"]').val(sex)
                    tr.find('input[name="nowId"]').val(nowId)
                    tr.find('input[name="idCard"]').val(idCard)
                    tr.find('input[name="noIdCard"]').val(noIdCard)
                    tr.find('input[name="year"]').val(year)
                    tr.find('input[name="kinship"]').val(kinship)
                    tr.find('input[name="kinshipName"]').val(kinshipName)
                    tr.find('input[name="personCurrentAddress "]').val(personCurrentAddress)
                    tr.find('input[name="personHeight"]').val(personHeight)
                    tr.find('input[name="personWeight"]').val(personWeight)
                    tr.find('input[name="personFileName"]').val(personFileName)
                    tr.find('input[name="personFrontPicture"]').val(personFrontPicture)
                    if (tr.find("input[name='personId']").length) {
                        $("#personSampleTbody").find("input[name='linkId'][value='" + tr.find("input[name='personId']").val() + "']").parents("tr").find("input[name='personName']").parent().html(personName + '<input type="hidden" name="personName" value="' + personName + '">')
                    } else {
                        $("#personSampleTbody").find("input[name='timeId'][value='" + tr.find("input[name='timeId']").val() + "']").parents("tr").find("input[name='personName']").parent().html(personName + '<input type="hidden" name="personName" value="' + personName + '">')
                    }
                    tr.children("td").each(function (i) {
                        if (tr.children("td").length - 1 !== i) {
                            var tdInput = $(this).children("input").clone()
                            if (i == 5) {
                                $(this).html(nowId)
                            } else {
                                $(this).html(tdInput.val())
                            }
                            $(this).append(tdInput)
                        }
                    })
                    $('#addAuthenticatorBig').modal('hide')
                    return true;
                } else {
                    //新添被鉴定人
                    var personTr = '<tr>'
                    personTr += '<td>' + personLength + '</td>'
                    personTr += '<td>' + personTypeName + '<input type="hidden" name="personTypeName" value="' + personTypeName + '"/></td>'
                    personTr += '<td>' + personName + '<input type="hidden" name="personName" value="' + personName + '"/></td>'
                    personTr += '<td>' + sexName + '<input type="hidden" name="sexName" value="' + sexName + '"/></td>'
                    personTr += '<td>' + year + '<input type="hidden" name="year" value="' + year + '"/></td>'
                    personTr += '<td>' + nowId + '<input type="hidden" name="idCard" value="' + idCard + '"/><input type="hidden" name="noIdCard" value="' + noIdCard + '"/></td>'
                    personTr += '<td>' + kinshipName + '<input type="hidden" name="kinshipName" value="' + kinshipName + '"/></td>'
                    personTr += '<td><div class="showPhoto"></div></td>'
                    personTr += '<td>'
                    personTr += '<button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑</button>'
                    personTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                    personTr += '<input type="hidden" name="personType" value="' + personType + '"/>'
                    personTr += '<input type="hidden" name="sex" value="' + sex + '"/>'
                    personTr += '<input type="hidden" name="kinship" value="' + kinship + '"/>'
                    personTr += '<input type="hidden" name="personCurrentAddress" value="' + personCurrentAddress + '"/>'
                    personTr += '<input type="hidden" name="personHeight" value="' + personHeight + '"/>'
                    personTr += '<input type="hidden" name="personFileName" value="' + personFileName + '"/>'
                    personTr += '<input type="hidden" name="personWeight" value="' + personWeight + '"/>'
                    personTr += '<input type="hidden" name="personFrontPicture" value="' + personFrontPicture + '"/>'
                    personTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
                    personTr += '</td>'
                    personTr += '</tr>'
                    $("#authenticatorTbody").append(personTr)
                }
                var personSampleBox = $("#addAuthenticatorBig").find(".sampleBox"),
                        sampleType = personSampleBox.find('select[name="sampleType"]').val(),
                        sampleTypeName = personSampleBox.find('select[name="sampleType"]').find("option:selected").text(),
                        sampleName = personSampleBox.find('input[name="sampleName"]').val(),
                        samplePacking = personSampleBox.find('select[name="samplePacking"]').val(),
                        samplePackingName = personSampleBox.find('select[name="samplePacking"]').find("option:selected").text(),
                        extractTime = personSampleBox.find('input[name="extractTime"]').val(),
                        extractMethod = personSampleBox.find('select[name="extractMethod"]').val(),
                        sampleCarrier = personSampleBox.find('select[name="sampleCarrier"]').val(),
                        inspectionObjective = personSampleBox.find('input[name="inspectionObjective"]').val(),
                        sampleDescribe = personSampleBox.find('textarea[name="sampleDescribe"]').val(),
                        sampleIndex = personSampleBox.find('input[name="index"]').val(),
                        relationPersonName = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").text(),
                        sampleDnaPicture = $("#sampleBase").val(),
                        sampleLength = Number($("#personSampleTbody").children().length) + 1;
                var extractMethodBox = personSampleBox.find('select[name="extractMethod"]').parent().clone()
                var sampleCarrierBox = personSampleBox.find('select[name="sampleCarrier"]').parent().clone()
                extractMethodBox.find("select").val(extractMethod)
                sampleCarrierBox.find("select").val(sampleCarrier)
                if (authenticatorBox.find("input[name='moveCheckbox']").is(':checked')) {
                    //添加样本
                    var personSampleTr = '<tr>'
                    personSampleTr += '<td>' + sampleLength + '</td>'
                    personSampleTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"/></td>'
                    personSampleTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"/></td>'
                    personSampleTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"/></td>'
                    personSampleTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"/></td>'
                    personSampleTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"/></td>'
                    personSampleTr += '<td></td>'
                    personSampleTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"/></td>'
                    personSampleTr += '<td>' + personName + '<input type="hidden" name="personName" value="' + personName + '"/></td>'
                    personSampleTr += '<td><div class="isRefuse"></div><input type="hidden" name="isRefuse" value=""/><input type="hidden" name="isRefuseCode" value="02"/></td>'
                    personSampleTr += '<td>'
                    personSampleTr += '<button class="btn-icon btn-icon-blue btn-icon-bianji-blue edit" type="button">编辑</button>'
                    personSampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                    personSampleTr += '<input type="hidden" name="sampleType" value="' + sampleType + '"/>'
                    personSampleTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '"/>'
                    personSampleTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '"/>'
                    personSampleTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '"/>'
                    personSampleTr += '<input type="hidden" name="personLength" value="' + personLength + '"/>'
                    personSampleTr += '<input type="hidden" name="sampleDnaPicture" value="' + sampleDnaPicture + '"/>'
                    personSampleTr += '<input type="hidden" name="addFlag" value="1"/>'//判断样本是否添加
                    personSampleTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
                    personSampleTr += '</td>'
                    personSampleTr += '</tr>'
                    $("#personSampleTbody").append(personSampleTr)
                    $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(extractMethodBox)
                    $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(sampleCarrierBox)
                }
                $('#addAuthenticatorBig').modal('hide')

            }
        })
        //被鉴定人照片展示
        $("#authenticatorTbody").on("mouseenter", ".showPhoto", function () {
            if (!$(this).parents("tr").find("input[name='personFrontPicture']").val()) {
                $(this).css("cursor", "no-drop").attr("title", "暂无图片")
            } else {
                $(this).css("cursor", "pointer").removeAttr("title")
            }
        })
        $("#authenticatorTbody").on("click", ".showPhoto", function () {
            if ($(this).parents("tr").find("input[name='personFrontPicture']").val()) {
                $("#showPhotoBox").find("img").attr("src", $(this).parents("tr").find("input[name='personFrontPicture']").val())
                $("#showPhotoBox").modal("show")
            }
        })
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
        //修改被鉴定人
        $("#authenticatorTbody").on("click", ".edit", function () {
            $("#addAuthenticatorBig").find("input[name='moveCheckbox']").parents(".form-group ").addClass("hidden")
            var authenticatorTr = $(this).parents("tr"),
                    authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(0),
                    personType = authenticatorTr.find("input[name='personType']").val(),
                    personName = authenticatorTr.find("input[name='personName']").val(),
                    sexName = authenticatorTr.find("input[name='sexName']").val(),
                    idCard = authenticatorTr.find("input[name='idCard']").val(),
                    noIdCard = authenticatorTr.find("input[name='noIdCard']").val(),
                    year = authenticatorTr.find("input[name='year']").val(),
                    kinship = authenticatorTr.find("input[name='kinship']").val(),
                    personCurrentAddress = authenticatorTr.find("input[name='personCurrentAddress']").val(),
                    personHeight = authenticatorTr.find("input[name='personHeight']").val(),
                    personWeight = authenticatorTr.find("input[name='personWeight']").val(),
                    personFrontPicture = authenticatorTr.find("input[name='personFrontPicture']").val(),
                    index = authenticatorTr.index();
            if (noIdCard) {
                authenticatorBox.find("input[type='checkbox']").prop("checked", true)
                authenticatorBox.find("input[name='noIdCard']").removeClass("hidden")
            }
            if (kinship) {
                authenticatorBox.find("select[name='kinship']").parents(".form-group ").removeClass("hidden")
            }
            if (sexName == '男') {
                authenticatorBox.find("input[name='sex'][value='01']").prop("checked", true)
            } else if (sexName == '女') {
                authenticatorBox.find("input[name='sex'][value='02']").prop("checked", true)
            }
            console.log("ffggg" + personFrontPicture)
            if (personFrontPicture != "") {
                authenticatorBox.find("img").attr("src", personFrontPicture)
            }
            authenticatorBox.find(".addphoto").html("修改")
            authenticatorBox.find("select[name='personType']").val(personType)
            authenticatorBox.find("input[name='personName']").val(personName)
            authenticatorBox.find("input[name='idCard']").val(idCard)
            authenticatorBox.find("input[name='noIdCard']").val(noIdCard)
            authenticatorBox.find("input[name='year']").val(year)
            authenticatorBox.find("input[name='index']").val(index)
            authenticatorBox.find("input[name='personCurrentAddress']").val(personCurrentAddress)
            authenticatorBox.find("input[name='personHeight']").val(personHeight)
            authenticatorBox.find("input[name='personWeight']").val(personWeight)
            authenticatorBox.find("select[name='kinship']").val(kinship)
            $("#addAuthenticatorBig").modal('show')
        })

        //添加样本
        $(".addsampleInfo").click(function () {
            if ($("#authenticatorTbody").children().length > 0) {
                $("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").removeClass('hidden')
                $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", 'none');
                $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", 'block').height("auto");
                $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").removeClass("hidden");
                $("#addAuthenticatorBig").find('select[name="relationPersonName"]').append("<option value=''>请选择关联人员</option>")
                $("#authenticatorTbody").children().each(function () {
                    var newOption = '<option timeId="' + $(this).find("input[name='timeId']").val() + '"  linkId="' + $(this).find("input[name='personId']").val() + '" value="' + $(this).find("input[name='personTypeName']").val() + '">' + $(this).find("input[name='personName']").val() + '</option>'
                    $("#addAuthenticatorBig").find('select[name="relationPersonName"]').append(newOption)
                })
                $("#addAuthenticatorBig").children().width(720)
                $("#addAuthenticatorBig").modal("show")
            } else {
                alert("请先添加被鉴定人")
            }

        })
        //样本保存
        //全局变量标识
        var CLICKTAG = 0;
        $(".addSampleBtn").click(function () {

            if (CLICKTAG == 0) {
                CLICKTAG = 1;
                //this.disabled=true;
                $("button[name='querenSample']").attr("disabled", true)
                // 等待2s后重置按钮可用
                setTimeout(function () {
                    CLICKTAG = 0;
                    $("button[name='querenSample']").removeAttr("disabled")
                }, 2000);
            }
            var form = $(this).parents("form");
            form.bootstrapValidator({
                live: 'disabled',
                message: 'This value is not valid',
                fields: {
                    relationPersonName: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    sampleType: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    extractTime: {
                        trigger: 'change',
                        validators: {
                            notEmpty: {
                                message: "不能为空"
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
                var personSampleBox = $("#addAuthenticatorBig").find(".sampleBox"),
                        sampleType = personSampleBox.find('select[name="sampleType"]').val(),
                        sampleTypeName = personSampleBox.find('select[name="sampleType"]').find("option:selected").text(),
                        sampleName = personSampleBox.find('input[name="sampleName"]').val(),
                        samplePacking = personSampleBox.find('select[name="samplePacking"]').val(),
                        samplePackingName = personSampleBox.find('select[name="samplePacking"]').find("option:selected").text(),
                        extractTime = personSampleBox.find('input[name="extractTime"]').val(),
                        extractMethod = personSampleBox.find('select[name="extractMethod"]').val(),
                        sampleCarrier = personSampleBox.find('select[name="sampleCarrier"]').val(),
                        inspectionObjective = personSampleBox.find('input[name="inspectionObjective"]').val(),
                        sampleDescribe = personSampleBox.find('textarea[name="sampleDescribe"]').val(),
                        sampleIndex = personSampleBox.find('input[name="index"]').val(),
                        relationPersonName = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").text(),
                        sampleDnaPicture = $("#sampleBase").val(),
                        timeId = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").attr("timeId"),
                        linkId = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").attr("linkId");
                var extractMethodBox = personSampleBox.find('select[name="extractMethod"]').parent().clone()
                var sampleCarrierBox = personSampleBox.find('select[name="sampleCarrier"]').parent().clone()
                extractMethodBox.find("select").val(extractMethod)
                sampleCarrierBox.find("select").val(sampleCarrier)
                if (sampleIndex != "") {
                    var tr = $("#personSampleTbody").children("tr").eq(sampleIndex)
                    tr.find('input[name="sampleType"]').val(sampleType)
                    tr.find('input[name="sampleTypeName"]').val(sampleTypeName)
                    tr.find('input[name="sampleName"]').val(sampleName)
                    tr.find('input[name="samplePacking"]').val(samplePacking)
                    tr.find('input[name="samplePackingName"]').val(samplePackingName)
                    tr.find('input[name="extractTime"]').val(extractTime)
                    tr.find('input[name="extractMethod"]').val(extractMethod)
                    tr.find('input[name="sampleCarrier"]').val(sampleCarrier)
                    tr.find('input[name="inspectionObjective"]').val(inspectionObjective)
                    tr.find('input[name="sampleDescribe"]').val(sampleDescribe)
                    tr.find('input[name="sampleDnaPicture"]').val(sampleDnaPicture)
                    tr.children("td").each(function (i) {
                        if (tr.children("td").length - 1 !== i) {
                            var tdInput = $(this).children("input").clone()
                            if (i == 9) {

                            } else if (i == 6) {
                                $(this).find("select[name='extractMethod']").val(extractMethod)
                                $(this).find("select[name='sampleCarrier']").val(sampleCarrier)
                            } else {
                                $(this).html(tdInput.val())
                                $(this).append(tdInput)
                            }

                        }
                    })
                }
                else {
                    //添加样本
                    var sampleLength = Number($("#personSampleTbody").children().length) + 1
                    var personSampleTr = '<tr>'
                    personSampleTr += '<td>' + sampleLength + '</td>'
                    personSampleTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"/></td>'
                    personSampleTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"/></td>'
                    personSampleTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"/></td>'
                    personSampleTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"/></td>'
                    personSampleTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"/></td>'
                    personSampleTr += '<td></td>'
                    personSampleTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"/></td>'
                    personSampleTr += '<td>' + relationPersonName + '<input type="hidden" name="personName" value="' + relationPersonName + '"/></td>'
                    personSampleTr += '<td><div class="isRefuse"></div><input type="hidden" name="isRefuse" value=""/><input type="hidden" name="isRefuseCode" value="02"/></td>'
                    personSampleTr += '<td>'
                    personSampleTr += '<button class="btn-icon btn-icon-blue btn-icon-bianji-blue edit" type="button">编辑</button>'
                    personSampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                    personSampleTr += '<input type="hidden" name="sampleType" value="' + sampleType + '"/>'
                    personSampleTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '"/>'
                    personSampleTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '"/>'
                    personSampleTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '"/>'
                    personSampleTr += '<input type="hidden" name="sampleDnaPicture" value="' + sampleDnaPicture + '"/>'
                    personSampleTr += '<input type="hidden" name="linkId" value="' + linkId + '"/>'
                    personSampleTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
                    personSampleTr += '</td>'
                    personSampleTr += '</tr>'
                    $("#personSampleTbody").append(personSampleTr)
                    $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(extractMethodBox)
                    $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(sampleCarrierBox)
                }
                $('#addAuthenticatorBig').modal('hide')
            }
        })
        $("body").on("change", "select", function () {
            $(this).parents("tr").find("input[name='" + $(this).attr("name") + "']").val($(this).val())
        })
        //修改样本
        $("#personSampleTbody").on("click", ".edit", function () {
            $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", 'none');
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", 'block').height("auto");
            $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").removeClass("hidden");
            $("#addAuthenticatorBig").find(".modal-content").eq(1).find("input[name='sampleNo']").parents(".col-md-6").removeClass("hidden")

            $("#addAuthenticatorBig").children().width(720)
            var authenticatorTr = $(this).parents("tr"),
                    authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(1),
                    sampleType = authenticatorTr.find("input[name='sampleType']").val(),
                    sampleName = authenticatorTr.find("input[name='sampleName']").val(),
                    samplePacking = authenticatorTr.find("input[name='samplePacking']").val(),
                    extractTime = authenticatorTr.find("input[name='extractTime']").val(),
                    extractMethod = authenticatorTr.find("input[name='extractMethod']").val(),
                    sampleCarrier = authenticatorTr.find("input[name='sampleCarrier']").val(),
                    inspectionObjective = authenticatorTr.find("input[name='inspectionObjective']").val(),
                    sampleDnaPicture = authenticatorTr.find("input[name='sampleDnaPicture']").val(),
                    sampleDescribe = authenticatorTr.find("input[name='sampleDescribe']").val(),
                    sampleNo = authenticatorTr.find("input[name='sampleNo']").val(),
                    sampleIndex = authenticatorTr.index();

            authenticatorBox.find("input[name='sampleNo']").val(sampleNo)
            authenticatorBox.find("select[name='sampleType']").val(sampleType)
            authenticatorBox.find("input[name='sampleName']").val(sampleName)
            authenticatorBox.find("select[name='samplePacking']").val(samplePacking)
            authenticatorBox.find("input[name='extractTime']").val(extractTime)
            authenticatorBox.find("select[name='extractMethod']").find("option[value='" + extractMethod + "']").prop("selected", true)
            authenticatorBox.find("select[name='sampleCarrier']").find("option[value='" + sampleCarrier + "']").prop("selected", true)
            authenticatorBox.find("input[name='inspectionObjective']").val(inspectionObjective)
            authenticatorBox.find("textarea[name='sampleDescribe']").val(sampleDescribe)
            if (sampleDnaPicture) {
                var imgBox = ' <div class="col-md-2">'
                imgBox += '<img class="sampleDnaPicture" src="' + sampleDnaPicture + '" alt="">'
                imgBox += '<i class="fa fa-times-circle removePhoto" aria-hidden="true"></i>'
                imgBox += ' </div>'
            }
            $("#addAuthenticatorBig").find(".samplePhotobox").children().eq(0).before(imgBox)
            $("#sampleBase").val(sampleDnaPicture)
            authenticatorBox.find("input[name='index']").val(sampleIndex)
            $("#addAuthenticatorBig").modal('show')
        })
        //人员样本关闭清空
        $("#addAuthenticatorBig").on('hidden.bs.modal', function (e) {
            if ($("#addAuthenticatorBig").find(".has-error").length > 0) {
                var form = $("#addAuthenticatorBig").find("form")
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);
            }
            $("#addAuthenticatorBig").find('input[name="sampleNo"]').parents(".col-md-6").addClass('hidden')
            $("#addAuthenticatorBig").find('select[name="relationPersonName"]').children().remove()
            $("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").addClass('hidden')
            $("#addAuthenticatorBig").find("input[type='text']").val("")
            $("#addAuthenticatorBig").find("input[type='hidden']").val("")
            $("#addAuthenticatorBig").find(".modal-content").eq(1).find("input[name='inspectionObjective']").val("")
            $("#addAuthenticatorBig").find("input[type='radio'][value='01']").prop("checked", true)
            $("#addAuthenticatorBig").find("input[type='checkbox']:checked").prop("checked", false)
            $("#addAuthenticatorBig").find("input[name='idCard']").prop("disabled", false)
            $("#addAuthenticatorBig").find("input[name='noIdCard']").addClass("hidden")
            $("#photoFile").val("")
            $("#addAuthenticatorBig").find("select").val("")
            $("#addAuthenticatorBig").find("textarea").val("")
            $("#addAuthenticatorBig").find("select[name='kinship']").parents(".form-group").addClass("hidden")
            $("#addAuthenticatorBig").find(".moveInput").addClass("hidden")
            $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", "block")
            $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", "none")
            $("#addAuthenticatorBig").find(".modal-content").eq(0).find("img").attr("src", '<%=path%>/img/policeman.png')
            $("#addAuthenticatorBig").find("input[name='moveCheckbox']").parents(".form-group ").removeClass("hidden")
            $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").addClass("hidden")
            $("#addAuthenticatorBig").find(".moveBtn").html("添加更多")
            $("#addAuthenticatorBig").find(".addphoto").html("添加照片")
            $("#addAuthenticatorBig").find("select[name='samplePacking']").val("01")
            $("#addAuthenticatorBig").find("select[name='extractMethod']").val("01")
            $("#addAuthenticatorBig").find("select[name='sampleCarrier']").val("01")
            $("#addAuthenticatorBig").find("input[name='inspectionObjective']").val("DNA检验")
            $("#addAuthenticatorBig").find("input[name='extractTime']").val("<%=time%>")

            $("#addAuthenticatorBig").find(".addsamplePhoto").parent().siblings().remove()
            $("#addAuthenticatorBig").find("input[name='idCard']").siblings().addClass("hidden")
            $(".samplePhoto").parents(".samplePhotobox").siblings("span").addClass("hidden")
            $(".photoFile").siblings("span").addClass("hidden")
            $("#addAuthenticatorBig").children().width(480)
        })

        // 拆分检材信息
        $(".addMaterialEvidencer").click(function () {
            $("#materialEvidencerlSampleBox").modal('show')
        })

        // 检材信息
        $("#materialEvidencerTbody").on("click", ".edit", function () {
            var sampleTypeName = $(this).parents("tr").find('input[name="sampleTypeName"]').val(),
                    sampleName = $(this).parents("tr").find("input[name='sampleName']").val(),
                    samplePackingName = $(this).parents("tr").find('input[name="samplePackingName"]').val(),
                    extractTime = $(this).parents("tr").find('input[name="extractTime"]').val(),
                    extractMethod = $(this).parents("tr").find('input[name="extractMethod"]').val(),
                    sampleCarrier = $(this).parents("tr").find('input[name="sampleCarrier"]').val(),
                    inspectionObjective = $(this).parents("tr").find('input[name="inspectionObjective"]').val(),

                    isRefuse = $(this).parents("tr").find('input[name="isRefuse"]').val(),
                    isRefuseCode = $(this).parents("tr").find('input[name="isRefuseCode"]').val(),
                    sampleNo = $(this).parents("tr").find('input[name="sampleNo"]').val(),
                    evidenceNo = $(this).parents("tr").find('input[name="evidenceNo"]').val(),
                    samplePicture = $(this).parents("tr").find('input[name="samplePicture"]').val(),
                    index = $(this).parents("tr").index(),
                    sampleDescribe = $(this).parents("tr").find('input[name="sampleDescribe"]').val();

            if (sampleTypeName !== "") {
                $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option:contains('" + sampleTypeName + "')").prop("selected", true)
            } else {
                $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option[value='']").prop("selected", true)
            }
            if (samplePackingName !== "") {
                $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option:contains('" + samplePackingName + "')").prop("selected", true)
            } else {
                $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option[value='']").prop("selected", true)
            }
            if (extractMethod !== "") {
                $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(extractMethod)
            } else {
                $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').children("option[value='']").prop("selected", true)
            }
            if (sampleCarrier !== "") {
                $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(sampleCarrier)
            } else {
                $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').children("option[value='']").prop("selected", true)
            }
            if (samplePicture) {
                var imgBox = ' <div class="col-md-2">'
                imgBox += '<img class="sampleDnaPicture" src="' + samplePicture + '" alt="">'
                imgBox += '<i class="fa fa-times-circle removePhoto" aria-hidden="true"></i>'
                imgBox += ' </div>'
                $("#materialEvidencerlSampleBox").find(".samplePhotobox").children().eq(0).before(imgBox)
            }
            $("#materialEvidencerlSampleBox").find('input[name="sampleNo"]').val(sampleNo).parents(".col-md-6").removeClass("hidden")
            $("#materialEvidencerlSampleBox").find('input[name="extractMethod"]').val(extractMethod)
            $("#materialEvidencerlSampleBox").find('input[name="sampleCarrier"]').val(sampleCarrier)
            $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(extractMethod)
            $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(sampleCarrier)
            $("#materialEvidencerlSampleBox").find('input[name="isRefuse"]').val(isRefuse)
            $("#materialEvidencerlSampleBox").find('input[name="isRefuseCode"]').val(isRefuseCode)
            $("#materialEvidencerlSampleBox").find('input[name="parentIndex"]').val("")
            $("#materialEvidencerlSampleBox").find('input[name="evidenceNo"]').val(evidenceNo)
            $("#materialEvidencerlSampleBox").find('input[name="sampleName"]').val(sampleName)
            $("#materialEvidencerlSampleBox").find('input[name="extractTime"]').val(extractTime)
            $("#materialEvidencerlSampleBox").find('input[name="inspectionObjective"]').val(inspectionObjective)
            $("#materialEvidencerlSampleBox").find('textarea[name="sampleDescribe"]').val(sampleDescribe)
            $("#materialEvidencerlSampleBox").find('input[name="index"]').val(index)
            $("#samplePicture").val(samplePicture)
            $("#materialEvidencerlSampleBox").modal('show')
        })
        //全局变量标识
        var CLICKTAG2 = 0;
        $('.addMaterialEvidencerlSample').click(function () {
            if (CLICKTAG2 == 0) {
                CLICKTAG2 = 1;
                //this.disabled=true;
                $("button[name='wzSampleButton']").attr("disabled", true)
                // 等待2s后重置按钮可用
                setTimeout(function () {
                    CLICKTAG2 = 0;
                    $("button[name='wzSampleButton']").removeAttr("disabled")
                }, 2000);
            }
            var form = $(this).parents("form");
            form.bootstrapValidator({
                live: 'disabled',
                message: 'This value is not valid',
                fields: {
                    sampleType: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    extractTime: {
                        trigger: 'change',
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    sampleName: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
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
                var sampleTypeName = $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option:selected").html(),
                        sampleType = $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').val(),
                        sampleName = $("#materialEvidencerlSampleBox").find('input[name="sampleName"]').val(),
                        samplePackingName = $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option:selected").html(),
                        samplePacking = $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').val(),
                        extractTime = $("#materialEvidencerlSampleBox").find('input[name="extractTime"]').val(),
                        extractMethod = $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(),
                        sampleCarrier = $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(),
                        inspectionObjective = $("#materialEvidencerlSampleBox").find('input[name="inspectionObjective"]').val(),
                        sampleDescribe = $("#materialEvidencerlSampleBox").find('textarea[name="sampleDescribe"]').val(),
                        isRefuse = $("#materialEvidencerlSampleBox").find('input[name="isRefuse"]').val(),
                        isRefuseCode = $("#materialEvidencerlSampleBox").find('input[name="isRefuseCode"]').val(),
                        samplePicture = $("#samplePicture").val(),
                        length = $("#materialEvidencerTbody").children().length + 1,
                        index = $("#materialEvidencerlSampleBox").find('input[name="index"]').val();

                if (index != "") {
                    var tr = $("#materialEvidencerTbody").children("tr").eq(index)
                    tr.find('input[name="sampleTypeName"]').val(sampleTypeName)
                    tr.find('input[name="sampleType"]').val(sampleType)
                    tr.find('input[name="sampleName"]').val(sampleName)
                    tr.find('input[name="samplePackingName"]').val(samplePackingName)
                    tr.find('input[name="samplePacking"]').val(samplePacking)
                    tr.find('input[name="sampleDescribe"]').val(sampleDescribe)
                    tr.find('input[name="extractTime"]').val(extractTime)
                    tr.find('input[name="extractMethod"]').val(extractMethod)
                    tr.find('input[name="sampleCarrier"]').val(sampleCarrier)
                    tr.find('input[name="inspectionObjective"]').val(inspectionObjective)
                    tr.find('input[name="samplePicture"]').val(samplePicture)
                    tr.children("td").each(function (i) {
                        if (tr.children("td").length - 1 !== i) {
                            var tdInput = $(this).children("input").clone()
                            if (i == 6) {
                                $(this).find("select[name='extractMethod']").val(extractMethod)
                                $(this).find("select[name='sampleCarrier']").val(sampleCarrier)
                            } else if (i == 11) {
                                tr.find('input[name="isRefuse"]').val(isRefuse)
                                tr.find('input[name="isRefuseCode"]').val(isRefuseCode)
                            } else if (i == 12) {

                            } else {
                                $(this).html(tdInput.val())
                                $(this).append(tdInput)
                            }

                        }
                    })
                } else {
                    var Box = '<div class="select"><select class="form-control" required="" name="extractMethod"> <option value="01" selected="">擦</option> <option value="02">吸</option> <option value="03">粘</option> <option value="04">剪</option> <option value="05">刮</option> <option value="06">其他</option> </select> </div>'
                    Box += '<div class="select"><select class="form-control" required="" name="sampleCarrier"> <option value="01" selected="">棉签</option> <option value="02">粘取器</option> <option value="03">烟蒂</option> <option value="04">血卡</option><option value="05">实物</option><option value="06">唾液卡</option></select></div>'
                    var newTr = '<tr>'
                    newTr += '<td>' + length + '</td>'
                    newTr += '<td></td>'
                    newTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"></td>'
                    newTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"></td>'
                    newTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"></td>'
                    newTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"></td>'
                    newTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"></td>'
                    newTr += '<td>' + Box + '</td>'
                    newTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"></td>'
                    newTr += '<td><div class="select"><select class="form-control"  name="preMethod1Result" id="preMethod1Result"><option value="">无</option><option value="+">+</option><option value="-">-</option></select></div></td>'
                    newTr += '<td><div class="select"><select class="form-control"  name="preMethod2Result" id="preMethod2Result"><option value="">无</option><option value="+">+</option><option value="-">-</option></select></div></td>'
                    newTr += '<td><div class="select"><select class="form-control"  name="preMethod3Result" id="preMethod3Result"><option value="">无</option><option value="+">+</option><option value="-">-</option></select></div></td>'
                    newTr += '<td><div class="isRefuse"></div><input type="hidden" name="isRefuse" value=""><input type="hidden" name="isRefuseCode" value="02"></td>'
                    newTr += '<td><div class="isRetain"></div></td>'
                    newTr += '<td>'
                    newTr += '<button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑 </button>'
                    newTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                    newTr += '<input type="hidden" name="sampleId" value="">'
                    newTr += '<input type="hidden" name="sampleType" value="' + sampleType + '">'
                    newTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '">'
                    newTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '">'
                    newTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '">'
                    newTr += '<input type="hidden" name="samplePicture" value="' + samplePicture + '">'
                    newTr += '</td>'
                    newTr += '</tr>'
                    $("#materialEvidencerTbody").append(newTr)
                    $("#materialEvidencerTbody").children().eq(length - 1).find("select[name='extractMethod']").val(extractMethod)
                    $("#materialEvidencerTbody").children().eq(length - 1).find("select[name='sampleCarrier']").val(sampleCarrier)
                }
                $('#materialEvidencerlSampleBox').modal('hide')
            }
        })

        $('#materialEvidencerlSampleBox').on('hidden.bs.modal', function (e) {
            $("#materialEvidencerlSampleBox").find('input[name="sampleNo"]').parents(".col-md-6").addClass("hidden")
            $("#materialEvidencerlSampleBox").find("input[type='text']").val("")
            $("#materialEvidencerlSampleBox").find("input[type='hidden']").val("")
            $("#materialEvidencerlSampleBox").find("select").val("")
            $("#materialEvidencerlSampleBox").find("textarea").val("")
            $("#materialEvidencerlSampleBox").find("select[name='extractMethod']").val("01")
            $("#materialEvidencerlSampleBox").find("select[name='sampleCarrier']").val("01")
            $("#materialEvidencerlSampleBox").find("select[name='samplePacking']").val("01")
            $("#materialEvidencerlSampleBox").find("input[name='inspectionObjective']").val("DNA检验")
            $("#materialEvidencerlSampleBox").find("input[name='extractTime']").val("<%=time%>")

            $("#materialEvidencerlSampleBox").find(".addsamplePhoto").parent().siblings().remove()
            $("#materialEvidencerlSampleBox").find(".modal-body").find(".col-md-12").find("span").addClass("hidden")
            if ($('#materialEvidencerlSampleBox').find(".has-error").length > 0) {
                var form = $("#saveform ")
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);
            }
        })


        //获取委托信息
        function getConsignmentInfo() {
            var consignmentInfo = {};

            consignmentInfo.areaOrgCode = $("option:selected", "#areaOrgCode").val();//所属辖区code

            consignmentInfo.delegateOrgCode = $("#delegateOrgCode").val();//委托单位code
            consignmentInfo.delegateOrgName = $("#delegateOrgName").val();//委托单位名称
            consignmentInfo.consignmentNo = $("#consignmentNo").val();//委托编号
            consignmentInfo.appendFlag = $("#appendFlag").val();//补送标识
            //委托人信息1
            consignmentInfo.delegator1Id = $("option:selected", "#delegator1Id").val();//委托人1id
            consignmentInfo.delegator1Name = $("option:selected", "#delegator1Id").text();//委托人1
            consignmentInfo.delegator1Position = $("#delegator1Position").val();//职务
            consignmentInfo.delegator1PaperworkType = $("#delegator1PaperworkType").val();//z证件
            consignmentInfo.delegator1PaperworkNo = $("#delegator1PaperworkNo").val();//证件号
            consignmentInfo.delegator1PhoneNumber = $("#delegator1PhoneNumber").val();//电话
            //委托人信息2
            consignmentInfo.delegator2Id = $("option:selected", "#delegator2Id").val();//委托人2id
            consignmentInfo.delegator2Name = $("option:selected", "#delegator2Id").text();//委托人2
            consignmentInfo.delegator2Position = $("#delegator2Position").val();//职务
            consignmentInfo.delegator2PaperworkType = $("#delegator2PaperworkType").val();//z证件
            consignmentInfo.delegator2PaperworkNo = $("#delegator2PaperworkNo").val();//证件号
            consignmentInfo.delegator2PhoneNumber = $("#delegator2PhoneNumber").val();//电话
            consignmentInfo.autographPicture = $("input[name='signatureBase']").val()
            //取走人
            consignmentInfo.takePerson = $("#takePerson").val();//取走人
            //鉴定要求
            var identifyRequirementArr = []
            $(".btn-checkbox").children(".active").each(function (i, item) {
                identifyRequirementArr.push($(this).attr("value"))
            })
            consignmentInfo.identifyRequirement = identifyRequirementArr.join(",")

            //鉴定类别
            consignmentInfo.identifyType = "DNA鉴定";//DNA鉴定

            consignmentInfo.consignmentId = $("#consignmentId").val();//委托id

            //FOB
            consignmentInfo.preMethod1Result = $("option:selected", "#preMethod1Result").val();//预实验方法1结果(FOB)
            //PSA
            consignmentInfo.preMethod2Result = $("option:selected", "#preMethod2Result").val();//预实验方法2结果(PSA)
            //联苯胺
            consignmentInfo.preMethod3Result = $("option:selected", "#preMethod3Result").val();//预实验方法3结果(联苯胺)

            return consignmentInfo;
        }
        /*
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
         }
         },
         error: function (e) {
         alert(e);
         }
         });
         });*/
        //获取案件信息
        function getCaseInfoDna() {
            var caseInfoDna = {};

            caseInfoDna.caseName = $("#caseName").val();//案件名称
            caseInfoDna.caseLocation = $("#caseLocation").val();//案发地址
            caseInfoDna.caseDatetime = $("#caseDatetime").val().trim();//案发时间
            caseInfoDna.caseType = $("option:selected", "#caseType").val();//案件类型
            caseInfoDna.caseProperty = $("option:selected", "#caseProperty").val();//案发性质
            caseInfoDna.caseLevel = $("option:selected", "#caseLevel").val();//案发性质
            caseInfoDna.caseBrief = $("#caseBrief").val();//简要案情
            caseInfoDna.caseRemark = $("#caseRemark").val();//其他说明
            caseInfoDna.caseXkNo = '${xkNo}';//现堪编号

            caseInfoDna.caseId = $("#caseId").val();//案件id

            caseInfoDna.majorNo = $("#majorNo").val();//专业
            caseInfoDna.majorType = $("#majorType").val();//专业类型

            //加急  0.加急  1.不加急
            var caseUrgentFlag = $("#caseUrgentFlag")
            if ($("#caseUrgentFlag").is(":checked")) {
                caseInfoDna.caseUrgentFlag = "1";
            } else {
                caseInfoDna.caseUrgentFlag = "0";
            }

            return caseInfoDna;
        }

        //被鉴定人信息
        function getLimsPersonInfo() {
            var limsPersonInfoArr = new Array();
            $("#authenticatorTbody").find("tr").each(function () {
                var that = $(this)
                limsPersonInfo = {};
                limsPersonInfo.personType = $(this).find("input[name='personType']").val()
                limsPersonInfo.personName = $(this).find("input[name='personName']").val()
                limsPersonInfo.personGender = $(this).find("input[name='sex']").val()
                limsPersonInfo.perosnAge = $(this).find("input[name='year']").val()
                limsPersonInfo.personIdCard = $(this).find("input[name='idCard']").val()
                limsPersonInfo.idCardFlag = limsPersonInfo.personIdCard == "" ? 1 : 0
                limsPersonInfo.noIdCardDesc = $(this).find("input[name='noIdCard']").val()
                limsPersonInfo.relationType = $(this).find("input[name='kinship']").val()
                limsPersonInfo.personCurrentAddress = $(this).find("input[name='personCurrentAddress']").val()
                limsPersonInfo.personHeight = $(this).find("input[name='personHeight']").val()
                limsPersonInfo.personWeight = $(this).find("input[name='personWeight']").val()
                limsPersonInfo.personId = $(this).find("input[name='personId']").val()
                limsPersonInfo.personFrontPicture = $(this).find("input[name='personFrontPicture']").val()
                limsPersonInfo.sampleInfoDnaList = []
                var linkId = $(this).find("input[name='personId']").val(),
                        timeId = $(this).find("input[name='timeId']").val();

                $("#personSampleTbody").find("tr").each(function () {
                    var sampleInfoDna = {}
                    if ($(this).find("input[name='linkId']").length > 0 && $(this).find("input[name='linkId']").val() == linkId) {
                        sampleInfoDna.sampleType = $(this).find("input[name='sampleType']").val()
                        sampleInfoDna.sampleName = $(this).find("input[name='sampleName']").val()
                        sampleInfoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val()
                        sampleInfoDna.samplePacking = $(this).find("input[name='samplePacking']").val()
                        sampleInfoDna.extractDatetime = $(this).find("input[name='extractTime']").val().trim();
                        sampleInfoDna.extractMethod = $(this).find("input[name='extractMethod']").val()
                        sampleInfoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val()
                        sampleInfoDna.sampleId = $(this).find("input[name='sampleId']").val()
                        sampleInfoDna.sampleStatus = $(this).find("input[name='isRefuseCode']").val()
                        sampleInfoDna.refuseReason = $(this).find("input[name='isRefuse']").val()
                        sampleInfoDna.sampleDnaPicture = $(this).find("input[name='sampleDnaPicture']").val()
                        sampleInfoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val()
                        sampleInfoDna.sampleDnaPicture = $(this).find("input[name='sampleBase']").val()
                        limsPersonInfo.sampleInfoDnaList.push(sampleInfoDna);
                    }
                    else if ($(this).find("input[name='timeId']").length > 0 && $(this).find("input[name='timeId']").val() == timeId) {
                        sampleInfoDna.sampleType = $(this).find("input[name='sampleType']").val()
                        sampleInfoDna.sampleName = $(this).find("input[name='sampleName']").val()
                        sampleInfoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val()
                        sampleInfoDna.samplePacking = $(this).find("input[name='samplePacking']").val()
                        sampleInfoDna.extractDatetime = $(this).find("input[name='extractTime']").val().trim();
                        sampleInfoDna.extractMethod = $(this).find("input[name='extractMethod']").val()
                        sampleInfoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val()
                        sampleInfoDna.sampleStatus = $(this).find("input[name='isRefuseCode']").val()
                        sampleInfoDna.refuseReason = $(this).find("input[name='isRefuse']").val()
                        sampleInfoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val()
                        sampleInfoDna.addFlag = $(this).find("input[name='addFlag']").val()
                        sampleInfoDna.sampleId = $(this).find("input[name='sampleId']").val()
                        sampleInfoDna.sampleDnaPicture = $(this).find("input[name='sampleBase']").val()
                        limsPersonInfo.sampleInfoDnaList.push(sampleInfoDna);
                    }
                })
                limsPersonInfoArr.push(limsPersonInfo);
            })
            console.log(limsPersonInfoArr)
            return limsPersonInfoArr;
        }

        //检材信息
        function getSampleInfoDna() {
            var sampleInfoDnaArr = new Array();
            $("#materialEvidencerTbody").find("tr").each(function () {
                var that = $(this)
                samplefoDna = {};
                samplefoDna.sampleType = $(this).find("input[name='sampleType']").val();
                samplefoDna.sampleName = $(this).find("input[name='sampleName']").val();
                samplefoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val();
                samplefoDna.samplePacking = $(this).find("input[name='samplePacking']").val();
                samplefoDna.extractDatetime = $(this).find("input[name='extractTime']").val();
                samplefoDna.extractMethod = $(this).find("input[name='extractMethod']").val();
                samplefoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val();
                samplefoDna.sampleIdwz = $(this).find("input[name='sampleId']").val();
                samplefoDna.evidenceNo = $(this).find("input[name='evidenceNo']").val();
                samplefoDna.preMethod1Result = $(this).find("select[name='preMethod1Result']").val();
                samplefoDna.preMethod2Result = $(this).find("select[name='preMethod2Result']").val();
                samplefoDna.preMethod3Result = $(this).find("select[name='preMethod3Result']").val();
                if ($(this).find(".isRetain").hasClass("active")) {
                    samplefoDna.isRetain = "1"
                } else {
                    samplefoDna.isRetain = "0"
                }
                samplefoDna.sampleStatus = $(this).find("input[name='isRefuseCode']").val()
                samplefoDna.refuseReason = $(this).find("input[name='isRefuse']").val()
                samplefoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val();
                samplefoDna.sampleMaterialPicture = $(this).find("input[name='samplePicture']").val();
                sampleInfoDnaArr.push(samplefoDna);
            })
            console.log(sampleInfoDnaArr)
            return sampleInfoDnaArr;
        }


        //删除被鉴定人信息
        var personIds = "";
        var sampleIds = "";
        $("#authenticatorTbody").on("click", ".remove", function () {
            var personId = $(this).siblings("input[name='personId']").val();
            if (personId != undefined) {
                personIds += "," + personId;
            }
            //$(this).parents("tr").remove()
            if ($(this).parents("tr").find("input[name='personId']").length) {
                $("#personSampleTbody").find("input[name='linkId'][value='" + $(this).parents("tr").find("input[name='personId']").val() + "']").each(function () {
                    if ($(this).parents("tr").find("input[name='sampleId']").val() != "") {
                        sampleIds += "," + $(this).parents("tr").find("input[name='sampleId']").val()
                    }
                    $(this).parents("tr").remove()
                })
            } else {
                $("#personSampleTbody").find("input[name='timeId'][value='" + $(this).parents("tr").find("input[name='timeId']").val() + "']").each(function () {
                    $(this).parents("tr").remove()
                })
            }
            $("#personSampleTbody").find("tr").each(function (i) {
                $(this).children().eq(0).html(i + 1)
            })
            var trNode = $(this).parent().parent();
            $($(this).parent().parent().nextAll("tr")).each(function () {
                var a = $(this).find("td").eq(0).text();
                $(this).find("td").eq(0).text(a - 1);
            });
            trNode.remove();
        });
        //删除样本信息

        $("#personSampleTbody").on("click", ".remove", function () {
            var sampleId = $(this).siblings("input[name='sampleId']").val();
            if (sampleId != undefined) {
                sampleIds += "," + sampleId;
            }
            console.log(sampleIds)
            //$(this).parents("tr").remove()
            var trNode = $(this).parent().parent();
            $($(this).parent().parent().nextAll("tr")).each(function () {
                var a = $(this).find("td").eq(0).text();
                $(this).find("td").eq(0).text(a - 1);
            });
            trNode.remove();
        });
        //删除检材信息
        var sampleIdWzs = "";
        $("#materialEvidencerTbody").on("click", ".remove", function () {
            var sampleIdWz = $(this).siblings("input[name='sampleId']").val();
            if (sampleIdWz != "") {
                sampleIdWzs += "," + sampleIdWz;
            }
            //$(this).parents("tr").remove()
            //删除检材信息，序号自动调整
            var trNode = $(this).parent().parent();
            $($(this).parent().parent().nextAll("tr")).each(function () {
                var a = $(this).find("td").eq(0).text();
                var b = a.replace(/(^\s*)|(\s*$)/g, "").length;
                if (b < 5) {
                    $(this).find("td").eq(0).text(a - 1);
                }
            });
            trNode.remove();
        });


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


    })
</script>
</body>

</html>