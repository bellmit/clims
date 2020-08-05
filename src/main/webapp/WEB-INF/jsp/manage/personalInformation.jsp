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
    <%@ include file="../linkCss.jsp" %>
    <style>
        .personBox {
            width: 755px;
            height: 410px;
            border: 1px solid #f2f2f2;
            box-shadow: 0px 0px 10px 5px #f3f3f3;
            margin: 0 auto;
        }
        
        .personBox .col-md-12:nth-child(1) {
            height: 118px;
            border-bottom: 2px dashed #e8e8e8;
            text-align: center;
            padding-top: 10px;
            margin-bottom: 15px;
        }
        
        .personBox .col-md-12:nth-child(1) img {
            display: block;
            margin: 0 auto;
            height: 70px;
        }
        
        .personBox .col-md-12:nth-child(1) i {
            height: 20px;
            width: 20px;
            display: inline-block;
            background: #ff5951;
            color: #fff;
            text-align: center;
            line-height: 20px;
            border-radius: 50%;
        }
        
        .personBox .col-md-12:nth-child(1) span {
            font-weight: 600;
        }
        
        .personBox .col-md-12:nth-last-child(1) {
            text-align: center
        }
        
        .personBox .col-md-12:nth-last-child(1) button:nth-child(1) {
            margin-right: 15px;
        }
    </style>
</head>

<body>
    <ol class="breadcrumb">
        <li><a href="javascript:void(0);" id="homePage">首页</a></li>
        <li class="active">个人信息</li>
    </ol>
    <form action="<%=path%>/manage/updateAmPersonalInfoAndLoaUserInfo">
    <div class="row Modular">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading yellow">
                    <div>个人信息</div>
                </div>
                <div class="panel-body">
                    <div class="personBox">
                        <div class="row">
                            <div class="col-md-12">
                                <img src="<%=path%>/img/touxiang1.png" alt="">
                                <i class="fa fa-pencil" aria-hidden="true" style="cursor: pointer"></i>
                                <span class="name" name="fullName">${user.amPersonalInfo.fullName}</span>
                                <input type="text" class="hidden">
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>警号</label>
                                    <input type="text" class="form-control" name="policeNo" value="${amObject.policeNo}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>职务</label>
                                    <div class="select">
                                        <select class="form-control" required="" name="position">
                                            <option value="" disabled="" selected="" hidden="">请选择职务</option>
                                            <c:forEach items="${listDictItem}" var="list">
                                                <option value="${list.dictCode}" <c:if test="${list.dictCode eq amObject.position}">selected="true"</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>单位电话</label>
                                    <input type="text" class="form-control" name="orgPhone" value="${amObject.orgPhone}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>联系电话</label>
                                    <input type="text" class="form-control" name="phoneNumber" value="${amObject.phoneNumber}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>系统登录名</label>
                                    <input type="text" class="form-control" name="loginName" value="${amObject.loginName}">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>密码</label>
                                    <input type="password" class="form-control" name="loginPassword" value="${amObject.loginPassword}">
                                </div>
                            </div>
                            <div class="col-md-12">
                                <button class="btn btn-blue btn-lang" type="submit">确认</button>
                                <a href="<%=path%>/main/home" target="ifm" class="btn btn-blue-border btn-lang">取消</a>
                            </div>
                            <input type="hidden" name="personalId" value="${user.personalId}">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </form>
    <%@ include file="../linkJs.jsp" %>
    <script>
        $(".name").prev().click(function() {
            $(this).next().addClass("hidden").next().removeClass("hidden")
        })
        $(".name").next().blur(function() {
            $(this).addClass("hidden").prev().html($(this).val()).removeClass("hidden")
        })

        $("#homePage").on("click",function(){
            window.location="<%=path%>/main/home";
            parent.$("#accordion").children().eq(0).addClass("active").siblings().removeClass("active")
        });



    </script>
</body>

</html>