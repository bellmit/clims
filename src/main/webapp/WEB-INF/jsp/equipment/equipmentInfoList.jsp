<%@ include file="../include.jsp" %>
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
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .btn-red:hover {
            width: 100%;
        }

        #videoBox a {
            padding-top: 10px;
            padding-bottom: 10px
        }

        #videoBox button {
            padding: 10px 26px;
        }

        .bu {
            background: #fddddb;
            color: #fc5a56;
            padding: 5px;
            border-radius: 50%;
            font-size: 10px;
            font-weight: 600;
        }
    </style>
</head>
<div class="modal fade popBox bigBox" id="equipmentInfoBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <h4 class="modal-title">设备信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备名称</label>
                                <div class="select">
                                    <select class="form-control" name="equipmentNameId">
                                        <option value="" disabled selected hidden>请选择设备类型</option>
                                        <c:forEach items="${equipmentNameInfoList}" var="equipmentNameInfo" varStatus="do">
                                            <option value="${equipmentNameInfo.id}">${equipmentNameInfo.equipmentName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备编号</label>
                                <input type="text" class="form-control" placeholder="请输入设备编号" name="equipmentNo">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>规格</label>
                                <input type="text" class="form-control" placeholder="请输入类型名称" name="equipmentSpecification">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备状态</label>
                                <div class="select">
                                    <select id="equipmentStatus" name="equipmentStatus"
                                            value="${query.entity.equipmentStatus}" class="form-control">
                                        <option value="">全部</option>
                                        <c:forEach items="${equipmentStatusList}" var="list" varStatus="do">
                                            <option value="${list.dictCode}" <c:if test="${list.dictCode eq equipmentNameInfo.equipmentTypeId}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>数量</label>
                                <input type="text" class="form-control" placeholder="请输入类型名称" name="equipmentNum">
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">使用期限</label>
                                <div class="col-sm-2">蓝色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="useBlueWarn" name="useBlueWarn" value="${equipmentInfo.useBlueWarn}"/>
                                </div>
                                <div class="col-sm-1">月</div>
                                <div class="col-sm-2">红色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="useRedWarn" name="useRedWarn" value="${equipmentInfo.useRedWarn}"/>
                                </div>
                                <div class="col-sm-1">月</div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">维修周期</label>
                                <div class="col-sm-2">蓝色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="repairBlueWarn" name="repairBlueWarn"  value="${equipmentInfo.repairBlueWarn}"/>
                                </div>
                                <div class="col-sm-1">天</div>
                                <div class="col-sm-2">红色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="repairRedWarn" name="repairRedWarn" value="${equipmentInfo.repairRedWarn}"/>
                                </div>
                                <div class="col-sm-1">天</div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>描述</label>
                                <textarea class="form-control" rows="3" placeholder="请输入描述"
                                          name="remark"></textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer clearfix">
                    <input type="hidden" name="index">
                    <input type="hidden" name="id">
                    <input type="hidden" name="parentIndex">
                    <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample" type="button" name="wzSampleButton">确认</button>
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<body>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>设备列表</div>
                <button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>类型编号</th>
                        <th>规格</th>
                        <th>设备名称</th>
                        <th>数量</th>
                        <th>入库时间</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="equipmentInfoListTbody">
                        <c:forEach items="${equipmentInfoList}" var="list" varStatus="s">
                            <tr>
                                <td>${s.index+1}</td>
                                <td>${list.entity.equipmentNo}<input type="hidden" name="equipmentNo" value="${list.entity.equipmentNo}"></td>
                                <td>${list.entity.equipmentSpecification}<input type="hidden" name="equipmentSpecification" value="${list.entity.equipmentSpecification}"></td>
                                <td>${list.equipmentName}<input type="hidden" name="equipmentNameId" value="${list.entity.equipmentNameId}"></td>
                                <td>${list.entity.equipmentNum}<input type="hidden" name="equipmentNum" value="${list.entity.equipmentNum}"></td>
                                <td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd '/><input type="hidden" name="createDatetime" value="${list.entity.createDatetime}"></td>
                                <td>${list.entity.remark}<input type="hidden" name="remark" value="${list.entity.remark}"></td>
                                <td>
                                    <input type="hidden" name="equipmentStatus" value="${list.entity.equipmentStatus}">
                                    <input type="hidden" name="useBlueWarn" value="${list.entity.useBlueWarn}">
                                    <input type="hidden" name="useRedWarn" value="${list.entity.useRedWarn}">
                                    <input type="hidden" name="repairBlueWarn" value="${list.entity.repairBlueWarn}">
                                    <input type="hidden" name="repairRedWarn" value="${list.entity.repairRedWarn}">
                                    <input type="hidden" id="id" name="id" value="${list.entity.id}">
                                    <button name="modifyBtn" type="button" class="btn-icon btn-icon-blue  btn-icon-bianji-blue edit">编辑</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
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
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/entrustCurrency.js"></script>
<script>
    $(function () {

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

        $(".addMaterialEvidencer").click(function () {
            $("#equipmentInfoBox").modal('show')
        })

        var CLICKTAG2 = 0;
        $('.addMaterialEvidencerlSample').click(function () {
            if (CLICKTAG2 == 0) {
                CLICKTAG2 = 1;
                //this.disabled=true;
                $("button[name='wzSampleButton']").attr("disabled",true)
                // 等待2s后重置按钮可用
                setTimeout(function () { CLICKTAG2 = 0 ; $("button[name='wzSampleButton']").removeAttr("disabled")}, 2000);
            }
            var equipmentNo = $("#equipmentInfoBox").find('input[name="equipmentNo"]').val(),
                    equipmentSpecification = $("#equipmentInfoBox").find('input[name="equipmentSpecification"]').val(),
                    equipmentNameId = $("#equipmentInfoBox").find('select[name="equipmentNameId"]').val(),
                    equipmentName = $("#equipmentInfoBox").find('select[name="equipmentNameId"]').find("option:selected").text(),
                    equipmentStatus =  $("#equipmentInfoBox").find('select[name="equipmentStatus"]').val(),
                    equipmentNum = $("#equipmentInfoBox").find('input[name="equipmentNum"]').val(),
                    useBlueWarn = $("#equipmentInfoBox").find('input[name="useBlueWarn"]').val(),
                    useRedWarn = $("#equipmentInfoBox").find('input[name="useRedWarn"]').val(),
                    repairBlueWarn = $("#equipmentInfoBox").find('input[name="repairBlueWarn"]').val(),
                    repairRedWarn = $("#equipmentInfoBox").find('input[name="repairRedWarn"]').val(),
                    remark = $("#equipmentInfoBox").find('textarea[name="remark"]').val(),
                    id = $("#equipmentInfoBox").find('input[name="id"]').val()
            var form = $("#equipmentInfoBox").find('form')
            form.bootstrapValidator({
                live: 'disabled',
                message: 'This value is not valid',
                fields: {
                    equipmentTypeNo: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    equipmentTypeName: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    }
                }
            });
            form.bootstrapValidator('validate');
            if (form.data('bootstrapValidator').isValid()) {
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);

                var equipmentInfo = {};
                equipmentInfo.id = id;
                equipmentInfo.equipmentNo = equipmentNo;
                equipmentInfo.equipmentSpecification = equipmentSpecification;
                equipmentInfo.equipmentNameId = equipmentNameId;
                equipmentInfo.equipmentNum = equipmentNum;
                equipmentInfo.useBlueWarn = useBlueWarn;
                equipmentInfo.useRedWarn = useRedWarn;
                equipmentInfo.repairBlueWarn = repairBlueWarn;
                equipmentInfo.repairRedWarn = repairRedWarn;
                equipmentInfo.remark = remark;
                equipmentInfo.equipmentStatus = equipmentStatus;

                $.ajax({
                    url: "<%=path%>/equipmentInfo/saveEquipmentInfo",
                    type:"post",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(equipmentInfo),
                    dataType: "json",
                    success: function (data) {
                        if(data.success || data.success == true || data.success == "true") {
                            location.href='<%=path%>/equipmentInfo/equipmentInfoList';
                        }else {
                            alert("添加失败！");
                        }
                    },
                    error: function (e) {
                        alert(e);
                    }
                });

            }
        })
        //回显设备信息
        $("#equipmentInfoListTbody").on("click", ".edit", function () {
            var equipmentNo = $(this).parents("tr").find('input[name="equipmentNo"]').val(),
                    equipmentSpecification = $(this).parents("tr").find('input[name="equipmentSpecification"]').val(),
                    equipmentNameId = $(this).parents("tr").find('input[name="equipmentNameId"]').val(),
                    equipmentStatus = $(this).parents("tr").find('input[name="equipmentStatus"]').val(),
                    equipmentNum = $(this).parents("tr").find('input[name="equipmentNum"]').val(),
                    useBlueWarn = $(this).parents("tr").find('input[name="useBlueWarn"]').val(),
                    useRedWarn = $(this).parents("tr").find('input[name="useRedWarn"]').val(),
                    repairBlueWarn = $(this).parents("tr").find('input[name="repairBlueWarn"]').val(),
                    repairRedWarn = $(this).parents("tr").find('input[name="repairRedWarn"]').val(),
                    remark = $(this).parents("tr").find('input[name="remark"]').val(),
                    index = $(this).parents("tr").index(),
                    id = $(this).parents("tr").find('input[name="id"]').val()
            $("#equipmentInfoBox").find('input[name="equipmentNo"]').val(equipmentNo)
            $("#equipmentInfoBox").find('input[name="equipmentSpecification"]').val(equipmentSpecification)
            $("#equipmentInfoBox").find('select[name="equipmentNameId"]').val(equipmentNameId)
            $("#equipmentInfoBox").find('select[name="equipmentStatus"]').val(equipmentStatus)
            $("#equipmentInfoBox").find('input[name="equipmentNum"]').val(equipmentNum)
            $("#equipmentInfoBox").find('input[name="useBlueWarn"]').val(useBlueWarn)
            $("#equipmentInfoBox").find('input[name="useRedWarn"]').val(useRedWarn)
            $("#equipmentInfoBox").find('input[name="repairBlueWarn"]').val(repairBlueWarn)
            $("#equipmentInfoBox").find('input[name="repairRedWarn"]').val(repairRedWarn)
            $("#equipmentInfoBox").find('textarea[name="remark"]').val(remark)
            $("#equipmentInfoBox").find('input[name="index"]').val(index)
            $("#equipmentInfoBox").find('input[name="id"]').val(id)
            $("#equipmentInfoBox").modal('show')
        });
    })
</script>
</body>

</html>