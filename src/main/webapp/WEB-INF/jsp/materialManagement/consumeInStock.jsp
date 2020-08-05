<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
%>
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
        .manual {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #ff5a56;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
        }

        .automatic {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #fda228;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
            float: left;
        }

        .tested {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #50c987;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
            float: left;
        }

        .test {
            width: 25px;
            height: 25px;
            display: block;
            line-height: 18px;
            text-align: center;
            background: #e4e4e4;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
        }

        td:nth-last-child(1) a {
            display: inline-block;
            width: 70px;
            color: #ff6561;
        }

        td a:hover::before {
            content: "进入实验";
        }

        td .fa-check-circle {
            color: #50c987;
            width: 30px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            font-size: 25px;
            margin-left: 10px;
        }

        td .fa-check-circle::before {
            display: inline-block;
            width: 30px;
            height: 25px;
            line-height: 25px;
            text-align: center;
            font-size: 29px;
        }

        td .fa-check-circle:hover::before {
            content: "查看";
            font-size: 14px;
        }

        .first {
            display: inline-block;
            background: #0c81f5;
            color: #fff;
            width: 20px;
            text-align: center;
            height: 20px;
            line-height: 20px;
        }

        .again {
            display: inline-block;
            background: #fda228;
            color: #fff;
            width: 20px;
            text-align: center;
            height: 20px;
            line-height: 20px;
        }
        #myModal .modal-body{
            text-align: center;
            padding: 50px 30px;
        }
        #myModal .modal-body a+a{
            margin-left: 20px;
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
                <form id="consignationForm" action="<%=path%>/supplies/consumeInStock"
                      class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>入库名称</label>
                                <input type="text" class="form-control" placeholder="请输入入库名称" name="suppliesName"
                                       value="${query.suppliesName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>入库时间</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" name="storageDatetimeStart"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.storageDatetimeStart}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择操作时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="4" name="storageDatetimeEnd"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${query.storageDatetimeEnd}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择操作时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>入库人</label>
                                <input type="text" class="form-control" placeholder="请输入入库人" name="storagePerson"
                                       value="${query.storagePerson}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
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
                <div>入库列表</div>
                <button class="btn btn-yellow pull-right" style="font-weight: 600;margin-top: -7px; margin-right: 15px;"
                        data-toggle="modal" data-target="#myModal">添加
                </button>

            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>入库名称</th>
                        <th>入库数量</th>
                        <th>入库人</th>
                        <th>所属单位</th>
                        <th>有效时间</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${suppliesOutgoInfoList}" var="outgolist" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td><c:forEach items="${suppliesInfoList}" var="supplieslist">
                                <c:if test="${supplieslist.id eq outgolist.suppliesId}">${supplieslist.suppliesName}</c:if>
                            </c:forEach>
                                <input type="hidden" name="suppliesId" value="${outgolist.suppliesId}"></td>
                            <td>${outgolist.storageNum}
                                <input type="hidden" name="storageNum"
                                       value="${outgolist.storageNum}"></td>
                            <td>${outgolist.storagePerson}
                                <input type="hidden" name="storagePerson"
                                       value="${outgolist.storagePerson}"></td>
                            <td>
                                <c:forEach items="${orgInfo}" var="list">
                                    <c:if test="${list.orgId eq outgolist.orgId}">${list.orgName}</c:if>
                                </c:forEach>
                                <input type="hidden" name="orgId" value="${outgolist.orgId}"></td>
                            <td><fmt:formatDate value="${outgolist.effectiveDatetime}" pattern="yyyy-MM-dd"/>
                                <input type="hidden" class="form-control form_datetime" name="effectiveDatetime"
                                       value="<fmt:formatDate value="${outgolist.effectiveDatetime}" pattern="yyyy-MM-dd"/>"></td>
                            <td>${outgolist.storageRemark}
                                <input type="hidden" name="storageRemark"
                                       value="${outgolist.storageRemark}"></td>
                            <td>
                                <input type="hidden" name="outgoid" value="${outgolist.id}"/>
                                <button type="button" name="editBtn" data-toggle="modal" data-target="#myModal"
                                        class="btn-icon btn-icon-blue btn-icon-bianji-blue">编辑</button>
                            </td>
                            <input type="hidden" class="form-control form_datetime" name="storageDatetime"
                                   value="<fmt:formatDate value="${outgolist.storageDatetime}" pattern="yyyy-MM-dd"/>">
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
<div class="modal fade popBox" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">入库信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>入库名称</label>
                            <select class="form-control " id="suppliesId" name="suppliesId">
                                <option>请选择</option>
                                <c:forEach items="${suppliesInfoList}" var="list">
                                    <option value="${list.id}">${list.suppliesName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>入库数量</label>
                            <input type="text" class="form-control" placeholder="请输入入库数量" id="storageNum" name="storageNum">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>入库人</label>
                            <input type="text" class="form-control" placeholder="请输入入库人" id="storagePerson" name="storagePerson">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>入库时间</label>
                            <input type="text" id="storageDatetime" name="storageDatetime" class="form-control form_datetime"
                                   value="" placeholder="请选择操作时间" readonly="readonly">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>有效时间</label>
                            <input type="text" id="effectiveDatetime" name="effectiveDatetime" class="form-control form_datetime"
                                   value="" placeholder="请选择操作时间" readonly="readonly">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>备注</label>
                            <input type="text" class="form-control" placeholder="请输入备注" id="storageRemark" name="storageRemark">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-blue btn-lang" id="addtBtn">保存</button>
                    <button class="btn btn-blue-border btn-lang" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $("#addInformant").on("click", function () {
            $("#page").val(1);
            $('#consignationForm').submit();
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


        //添加
        $('#addtBtn').on("click", function (){
            saveForm();
            $("#myModal").hide();
        })

        function saveForm(){
            $.ajax({
                url: "<%=path%>/supplies/addConsumeInStock",
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
//                        alert("保存成功");
                        location.href = "<%=path%>/supplies/consumeInStock";
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                    location.href = "<%=path%>/supplies/consumeInStock";
                }
            })
        }

        var reagent = {};
        function param() {
            reagent.barcode = $("#barcode").val()
            reagent.suppliesId = $("select[name='suppliesId'] option:selected").val();
            reagent.storageNum = $("#storageNum").val()
            reagent.storagePerson = $("#storagePerson").val()
            reagent.storageDatetime = $("#storageDatetime").val()
            reagent.effectiveDatetime = $("#effectiveDatetime").val()
            reagent.storageRemark = $("#storageRemark").val()
            return reagent;
        }

        //模态框关闭清空
        $('#myModal').on('hidden.bs.modal', function (e) {
            $(this).find("input[type='text']").val("");
            reagent.id = null;
            $("#suppliesId").val("请选择");
        })

        //编辑回显
        $("button[name='editBtn']").click(function(){
            reagent.id = $(this).parents("tr").find("input[name='outgoid']").val();
            $('#myModal').find("input[name='barcode']").val($(this).parents("tr").find("input[name='barcode']").val());
            $('#myModal').find("input[name='storageNum']").val($(this).parents("tr").find("input[name='storageNum']").val());
            $('#myModal').find("input[name='storagePerson']").val($(this).parents("tr").find("input[name='storagePerson']").val());
            $('#myModal').find("input[name='effectiveDatetime']").val($(this).parents("tr").find("input[name='effectiveDatetime']").val());
            $('#myModal').find("input[name='storageRemark']").val($(this).parents("tr").find("input[name='storageRemark']").val());
            $('#myModal').find("input[name='storageDatetime']").val($(this).parents("tr").find("input[name='storageDatetime']").val());
            //下拉框回显
            $("#suppliesId").val($(this).parents('tr').find("input[name='suppliesId']").val());
        })

    })
</script>
</body>
</html>
