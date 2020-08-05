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
                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/reagentName">
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path%>/center/reagentName" class="form-horizontal tasi-form" method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>编号</label>
                                <input type="text" name="reagentNo" value="${query.reagentNo}"
                                       class="form-control" placeholder="请输入条码号">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" placeholder="请输入出库名称" name="reagentName"
                                       value="${query.reagentName}">
                            </div>
                        </div>
                        <%--<div class="col-md-4 seachInputBox">--%>
                            <%--<div class="form-group">--%>
                                <%--<label></label>--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input id="reagentFlag" name="reagentType" type="radio" value="1" <c:if test="${query.reagentType eq '1'}">checked</c:if>/>试剂--%>
                                    <%--</label>--%>
                                    <%--<label class="checkbox-inline">--%>
                                        <%--<input id="exclusiveFlag" name="reagentType" type="radio" value="2" <c:if test="${query.reagentType eq '2'}">checked</c:if> />耗材--%>
                                    <%--</label>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>型号</label>
                                <input type="text" class="form-control" placeholder="请输入出库名称" name="reagentModel"
                                       value="${query.reagentModel}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>实验阶段</label>
                                <select class="form-control " name="experimentalStage">
                                    <option value="">请选择</option>
                                    <c:forEach items="${sampleTypeList}" var="list">
                                        <option value="${list.dictCode}"
                                                <c:if test="${list.dictCode eq query.experimentalStage}">selected</c:if>>
                                                ${list.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" name="queryBtn" type="submit" id="">查询</button>
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
                <div>试剂列表</div>
                <button class="btn btn-yellow pull-right" style="font-weight: 600;margin-top: -7px; margin-right: 15px;"
                        data-toggle="modal" data-target="#myModal">添加
                </button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>编号</th>
                        <%--<th>类型</th>--%>
                        <th>名称</th>
                        <th>实验阶段</th>
                        <th>品牌</th>
                        <th>型号</th>
                        <th>价格</th>
                        <th>厂商</th>
                        <th>所属单位</th>
                        <%--<th>批号</th>--%>
                        <%--<th>有效日期</th>--%>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${reagentInfoList}" var="infolist" varStatus="s">
                        <tr>
                            <td>${s.index+1}</td>
                            <td>${infolist.reagentNo}
                                <input type="hidden" name="reagentNo"
                                       value="${infolist.reagentNo}"></td>
                            <%--<td>--%>
                                <%--<c:if test="${infolist.reagentType eq '1'}">试剂</c:if>--%>
                                <%--<c:if test="${infolist.reagentType eq '2'}">耗材</c:if>--%>
                                <%--<input type="hidden" name="reagentType"--%>
                                       <%--value="${infolist.reagentType}"></td>--%>
                            <td>${infolist.reagentName}
                                <input type="hidden" name="reagentName"
                                       value="${infolist.reagentName}"></td>
                            <td><c:forEach items="${sampleTypeList}" var="list">
                                    <c:if test="${infolist.experimentalStage eq list.dictCode}">${list.dictName}
                                        <input type="hidden" name="experimentalStage" value="${list.dictCode}">
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${infolist.reagentBrand}
                                <input type="hidden" name="reagentBrand"
                                       value="${infolist.reagentBrand}">
                            </td>
                            <td>${infolist.reagentModel}
                                <input type="hidden" name="reagentModel"
                                       value="${infolist.reagentModel}">
                            </td>
                            <td>${infolist.reagentPrice}
                                <input type="hidden" name="reagentPrice"
                                       value="${infolist.reagentPrice}">
                            </td>
                            <td>${infolist.reagentFirm}
                                <input type="hidden" name="reagentFirm"
                                       value="${infolist.reagentFirm}">
                            </td>
                            <td>
                                <c:forEach items="${orgInfo}" var="list">
                                    <c:if test="${list.orgId eq infolist.orgId}">${list.orgName}</c:if>
                                </c:forEach>
                                <input type="hidden" name="orgId"
                                       value="${infolist.orgId}">
                            </td>
                            <td style="display:none">${infolist.batchNumber}
                                <input type="hidden" name="batchNumber"
                                       value="${infolist.batchNumber}">
                            </td>
                            <td style="display:none"><fmt:formatDate value="${infolist.validityTime}" pattern="yyyy-MM-dd"/>
                                <input type="hidden" name="validityTime"
                                       value="<fmt:formatDate value="${infolist.validityTime}" pattern="yyyy-MM-dd"/>">
                            </td>
                            <td>
                                <input type="hidden" name="infoid" id="infoid" value="${infolist.id}"/>
                                <button type="button" name="editBtn" data-toggle="modal" data-target="#myModal"
                                class="btn-icon btn-icon-blue btn-icon-bianji-blue">编辑</button>
                            </td>
                            <input type="hidden" name="remark" value="${infolist.remark}">
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../pagefoot.jsp" %>
                <div class="row" style="padding: 0px">
                    <div class="col-md-12">
                        <div id="kkpager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade popBox" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">试剂信息</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>类型</label>--%>
                            <%--<div class="form-group">--%>
                                <%--<label class="checkbox-inline">--%>
                                    <%--<input id="reagentFlag1" name="reagentType" type="radio" value="1" />试剂--%>
                                <%--</label>--%>
                                <%--<label class="checkbox-inline">--%>
                                    <%--<input id="exclusiveFlag2" name="reagentType" type="radio" value="2"/>耗材--%>
                                <%--</label>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>实验阶段</label>
                            <select class="form-control " id="experimentalStage" name="experimentalStage">
                                <option>请选择</option>
                                <c:forEach items="${sampleTypeList}" var="list">
                                    <option value="${list.dictCode}">${list.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" class="form-control" placeholder="请输入名称" id="reagentName" name="reagentName">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>编号</label>
                            <input type="text" class="form-control" placeholder="请输入编号" id="reagentNo" name="reagentNo">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>品牌</label>
                            <input type="text" class="form-control" placeholder="请输入品牌" id="reagentBrand" name="reagentBrand" >
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>型号</label>
                            <input type="text" class="form-control" placeholder="请输入型号" id="reagentModel" name="reagentModel" >
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>价格</label>
                            <input type="text" class="form-control" placeholder="请输入价格" id="reagentPrice" name="reagentPrice">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>厂商</label>
                            <input type="text" class="form-control" placeholder="请输入厂商" id="reagentFirm" name="reagentFirm">
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" class="form-control" placeholder="请输入描述" id="remark" name="remark">
                        </div>
                    </div>
                        <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>批号</label>--%>
                            <%--<input type="text" class="form-control" placeholder="请输入批号" id="batchNumber" name="batchNumber">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                        <%--<div class="col-md-6">--%>
                        <%--<div class="form-group">--%>
                            <%--<label>有效期</label>--%>
                            <%--<input type="text" class="form-control form_datetime" placeholder="请选择操作时间" id="validityTime" name="validityTime">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-blue btn-lang" id="saveBtn">保存</button>
                    <button class="btn btn-blue-border btn-lang" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
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


        //添加修改
        $('#saveBtn').on("click", function (){
            saveForm();
            $("#myModal").hide();
        })

        function saveForm(){
            var id = $("input[name='dictInfoId']").val();
            $.ajax({
                url: "<%=path%>/center/addReagents?operateType=" + $("#operateType").val(),
                type: "post",
                data: JSON.stringify(param()),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/center/reagentName";
                    } else {
                        alert("保存失败！");
                    }
                },
                error: function (e) {
                    alert("操作失败！");
                    location.href = "<%=path%>/center/refreshReagentName";
                }
            })
        }
        //定义一个对象
        var reagent = {};
        //赋值
        function param() {
//            if ($("#reagentFlag1").is(":checked")==true) {
//                reagent.reagentType =  $("#reagentFlag1").val();
//            }
//            if ($("#exclusiveFlag2").is(":checked")==true) {
//                reagent.reagentType =  $("#exclusiveFlag2").val();
//            }
            reagent.experimentalStage = $("#experimentalStage").val();
            reagent.reagentName = $("#reagentName").val();
            reagent.reagentNo = $("#reagentNo").val();
            reagent.reagentModel = $("#reagentModel").val();
            reagent.reagentPrice = $("#reagentPrice").val();
            reagent.reagentFirm = $("#reagentFirm").val();
            reagent.remark = $("#remark").val();
            reagent.reagentBrand = $("#reagentBrand").val();
            reagent.batchNumber = $("#batchNumber").val();
            reagent.validityTime = $("#validityTime").val();
            return reagent;
        }
        //模态框关闭清空
        $('#myModal').on('hidden.bs.modal', function (e) {
            $(this).find("input[type='text']").val("");
            $("#experimentalStage").val("请选择");
            reagent.id = null;
            $('input[type=radio][name="reagentType"]:checked').prop("checked", false);
        })

        //编辑回显
        $("button[name='editBtn']").click(function(){
            //获取id
            reagent.id = $(this).parents("tr").find("input[name='infoid']").val();
            $('#myModal').find("input[name='reagentNo']").val($(this).parents("tr").find("input[name='reagentNo']").val());
            $('#myModal').find("input[name='reagentName']").val($(this).parents("tr").find("input[name='reagentName']").val());
            $('#myModal').find("input[name='reagentModel']").val($(this).parents("tr").find("input[name='reagentModel']").val());
            $('#myModal').find("input[name='reagentPrice']").val($(this).parents("tr").find("input[name='reagentPrice']").val());
            $('#myModal').find("input[name='reagentFirm']").val($(this).parents("tr").find("input[name='reagentFirm']").val());
            $('#myModal').find("input[name='remark']").val($(this).parents("tr").find("input[name='remark']").val());
            $('#myModal').find("input[name='reagentBrand']").val($(this).parents("tr").find("input[name='reagentBrand']").val());
            $('#myModal').find("input[name='batchNumber']").val($(this).parents("tr").find("input[name='batchNumber']").val());
            $('#myModal').find("input[name='validityTime']").val($(this).parents("tr").find("input[name='validityTime']").val());
            //下拉框回显
            $("#experimentalStage").val($(this).parents('tr').find("input[name='experimentalStage']").val());
        })

    })
</script>
</body>
</html>
