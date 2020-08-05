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
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件委托系统</title>
    <link rel="stylesheet" href="../../lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../lib/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../lib/bootstrap-datetimepicker/css/datetimepicker.css">
    <link rel="stylesheet" href="../../css/base.css">
    <link rel="stylesheet" href="../../css/style.css">
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/scroll.css">
    <link rel="stylesheet" href="../../css/button.css">
    <link rel="stylesheet" href="../../css/form.css">
    <link rel="stylesheet" href="../../css/table.css">
    <link rel="stylesheet" href="../../css/pop.css">
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
    </style>
</head>
<!-- 修改现勘编号 -->
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
    <li><a href="#">首页</a></li>
    <li><a href="#">案件委托登记</a></li>
    <li class="active">DNA鉴定</li>
</ol>

<script src="../../lib/jquery/jquery-3.2.1.min.js"></script>
<script src="../../lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="../../lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="../../js/entrustCurrency.js"></script>
</body>
</html>