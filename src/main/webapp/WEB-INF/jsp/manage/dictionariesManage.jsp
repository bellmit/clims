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
</head>
<div class="modal fade popBox" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">字典操作</h4>
            </div>
            <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典类型</label>
                                <input type="text" class="form-control" placeholder="请输入字典类型" name="dictTypeCode" id="dictTypeCode">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>字典名称</label>
                                <input type="text" class="form-control" placeholder="请输入字典名称" name="dictTypeName" id="dictTypeName">
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
<body>
<ol class="breadcrumb">
    <li><a href="javascript:void(0);" id="homePage">首页</a></li>
    <li class="active">字典管理</li>
</ol>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>字典列表</div>
                <button class="btn btn-blue" data-toggle="modal" data-target="#myModal">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>字典类型</th>
                        <th>字典名称</th>
                        <th>创建时间</th>
                        <th>创建人</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="clientTbody">
                    <c:forEach items="${dictInfoList}" var="dictlist">
                        <tr>
                            <td></td>
                            <td>${dictlist.dictTypeCode} <input type="hidden" name="dictTypeCode"
                                                                value="${dictlist.dictTypeCode}"></td>
                            <td><a href="<%=path%>/manage/getChildrenDictItem?dictTypeCode=${dictlist.dictTypeCode}"
                                   id="aaa">${dictlist.dictTypeName} <input type="hidden" name="dictTypeName"
                                                                            value="${dictlist.dictTypeName}"></a></td>
                            <td><fmt:formatDate value="${dictlist.createDatetime}" pattern="yyyy-MM-dd"/>
                                <input type="hidden" name="createDatetime" value="${dictlist.createDatetime}"></td>
                            <td>${dictlist.createPerson} <input type="hidden" name="position"
                                                                value="${dictlist.createPerson}"></td>
                            </td>
                            <td>
                                <input type="hidden" name="dictInfoId" value="${dictlist.dictInfoId}"/>
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

        $("#homePage").on("click", function () {
            window.location = "<%=path%>/main/home";
            parent.$("#accordion").children().eq(0).addClass("active").siblings().removeClass("active")
        });

        //显示的序号
        $("#clientTbody").children().each(function (i) {
            $(this).children().eq(0).html(i + 1)
        })

        //删除
        $("button[name='deleteBtn']").on("click", function () {
            if(confirm("确认要删除该字典信息吗？")){
                var id = $("input[name='dictInfoId']", $(this).parent()).val();
                location.href="<%=path%>/manage/deleteDictionariesManage?dictInfoId=" + id;
            }
        });

        //添加修改
        $('#editBtn').on("click", function (){
            saveForm();
            $("#myModal").hide();
        })

        function saveForm(){
            $.ajax({
                url: "<%=path%>/manage/saveDictionariesManage",
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href="<%=path%>/manage/refreshDictionariesManage";
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
            dict.dictTypeCode = $("#dictTypeCode").val();
            dict.dictTypeName = $("#dictTypeName").val();
            return dict;
        }

        //编辑
        $("button[name='editBtn']").click(function(){
            dict.dictInfoId = $(this).parents("tr").find("input[name='dictInfoId']").val();
            $('#myModal').find("input[name='dictTypeCode']").val($(this).parents("tr").find("input[name='dictTypeCode']").val())
            $('#myModal').find("input[name='dictTypeName']").val($(this).parents("tr").find("input[name='dictTypeName']").val())
        })
        //模态框关闭清空
        $('#myModal').on('hidden.bs.modal', function (e) {
            $(this).find("input[type='text']").val("");
            dict.dictInfoId = null;
        })

    })


</script>
</body>

</html>