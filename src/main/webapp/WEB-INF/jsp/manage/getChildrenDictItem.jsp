<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        #myModal .col-md-6{
            margin-top: 10px;
        }
    </style>
</head>

<body>
<ol class="breadcrumb">
    <li><a href="javascript:void(0);" id="homePage">首页</a></li>
    <li class="active">字典管理</li>
</ol>
<div class="modal fade popBox" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">字典信息</h4>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典类型</label>
                                <span class="form-control" placeholder="请输入字典类型" id="dictTypeCode">${dictTypeCode}</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典编号</label>
                                <input type="text" class="form-control" placeholder="请输入字典编号" name="dictCode" id="dictCode">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典名称</label>
                                <input type="text" class="form-control" placeholder="请输入字典名称" name="dictName" id="dictName">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典详情</label>
                                <input type="text" class="form-control" placeholder="请输入字典详情" name="dictDesc" id="dictDesc">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-blue btn-lang" id="editBtn">保存</button>
                        <button class="btn btn-blue-border btn-lang" type="button" data-dismiss="modal">取消</button>
                    </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>字典列表</div>
                <button class="btn btn-blue" data-toggle="modal" data-target="#myModal" name="addBtn">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>字典类型</th>
                        <th>字典编号</th>
                        <th>字典名称</th>
                        <th>字典详情</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="clientTbody">
                    <c:forEach items="${dictItemslist}" var="dictlist">
                        <tr>
                            <td></td>
                            <td>${dictlist.dictTypeCode} <input type="hidden" name="dictTypeCode"
                                                                value="${dictlist.dictTypeCode}"></td>
                            <td>${dictlist.dictCode}<input type="hidden" name="dictCode"
                                                               value="${dictlist.dictCode}"></td>
                            <td>${dictlist.dictName} <input type="hidden" name="dictName"
                                                                value="${dictlist.dictName}"></td>
                            <td>${dictlist.dictDesc} <input type="hidden" name="dictDesc"
                                                                value="${dictlist.dictDesc}"></td>
                            <td><fmt:formatDate value="${dictlist.createDatetime}" pattern="yyyy-MM-dd" />
                                <input type="hidden" name="createDatetime" value="${dictlist.createDatetime}"></td>
                            <td>${dictlist.createPerson} <input type="hidden" name="position"
                                                                value="${dictlist.createPerson}"></td>
                            </td>
                            <td>
                                <input type="hidden" name="dictItemId" value="${dictlist.dictItemId}"/>
                                <button type="button" name="editBtn" data-toggle="modal" data-target="#myModal"
                                        class="btn-icon btn-icon-blue btn-icon-bianji-blue">编辑</button>
                                <button type="button" name="deleteBtn" class="btn-icon btn-icon-red btn-icon-shanchu-red">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>
<script>

    $(function(){
        $("#homePage").on("click",function(){
            window.location="<%=path%>/main/home";
            parent.$("#accordion").children().eq(0).addClass("active").siblings().removeClass("active")
        });

        //删除
        $("button[name='deleteBtn']").on("click", function () {
            if(confirm("确认要删除该字典信息吗？")){
                var id = $("input[name='dictItemId']", $(this).parent()).val();
                location.href="<%=path%>/manage/deleteChildrenDictItem?dictItemId=" + id;
            }
        });

        $("#clientTbody").children().each(function (i) {
            $(this).children().eq(0).html(i + 1)
        })


        //修改添加
        $('#editBtn').on("click", function (){
            saveForm();
            $("#myModal").hide();
        })

        function saveForm(){
            $.ajax({
                url: "<%=path%>/manage/saveChildrenDictItem?dictTypeCode=${dictTypeCode}",
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href="<%=path%>/manage/refreshChildrenDictItem?dictTypeCode=${dictTypeCode}";
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                }
            })
        }

        var dict = {};
        function param() {
            dict.dictCode = $("#dictCode").val();
            dict.dictName = $("#dictName").val();
            dict.dictDesc = $("#dictDesc").val();
            return dict;
        }

        //修改回显
        $("button[name='editBtn']").click(function(){
            dict.dictItemId = $(this).parents("tr").find("input[name='dictItemId']").val()
            $('#myModal').find("input[name='dictTypeCode']").val($(this).parents("tr").find("input[name='dictTypeCode']").val())
            $('#myModal').find("input[name='dictCode']").val($(this).parents("tr").find("input[name='dictCode']").val())
            $('#myModal').find("input[name='dictName']").val($(this).parents("tr").find("input[name='dictName']").val())
            $('#myModal').find("input[name='dictDesc']").val($(this).parents("tr").find("input[name='dictDesc']").val())
        })
        //模态框关闭清空
        $('#myModal').on('hidden.bs.modal', function (e) {
            $(this).find("input[type='text']").val("")
            dict.dictItemId = null;
        })
    })

</script>
</body>

</html>