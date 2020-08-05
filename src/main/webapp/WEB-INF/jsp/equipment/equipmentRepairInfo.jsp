<%@ include file="../include.jsp" %>
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
<div class="modal fade popBox bigBox" id="equipmentRepairBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <h4 class="modal-title">维修信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备名称</label>
                                <input type="text" class="form-control" readonly name="equipmentNo" value="${equipmentInfoVo.equipmentName}">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>维修人</label>
                                <div class="select">
                                    <select id="equipmentRepairPerson" name="equipmentRepairPerson" class="form-control" readonly="true">
                                        <option value="">全部</option>
                                        <c:forEach items="${equipmentNameInfoList}" var="list" varStatus="do">
                                            <option value="${list.id}">${list.equipmentName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>维修时间</label>
                                <input type="text" class="form-control form_datetime" id="equipmentRepairTime"
                                       placeholder="请输入维修时间" name="equipmentRepairTime" readonly>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>故障原因</label>
                                <input type="text" class="form-control" placeholder="请输入故障原因" name="failureCause">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer clearfix">
                    <input type="hidden" name="index">
                    <input type="hidden" name="id" value="${id}">
                    <input type="hidden" name="equipmentInfoId" value="${equipmentInfoVo.entity.id}">
                    <input type="hidden" name="equipmentNameId" value="${equipmentInfoVo.equipmentNameId}">
                    <input type="hidden" name="parentIndex">
                    <button class="btn btn-lang  btn-blue addMaterialEvidencerlSample" type="button" name="wzSampleButton">确认</button>
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal">取消</button>
                </div>
            </form>
        </div>
    </div>
</div>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>添加维修记录</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path %>/equipmentRepairInfo/reviewDetails" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>设备名称</label>
                                <input type="text" class="form-control" readonly id="equipmentName"
                                       name="equipmentName" value="${equipmentInfoVo.equipmentName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>维修人</label>
                                <input type="text" name="entity.equipmentRepairPerson" value="${query.entity.equipmentRepairPerson}" class="form-control" placeholder="请输入维修人">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="id" value="">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <button class="btn btn-blue-border">重置</button>
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
                <div>设备维修记录</div>
                <button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>设备名称</th>
                        <th>维修人</th>
                        <th>维修时间</th>
                        <th>故障原因</th>
                    </tr>
                    </thead>
                    <tbody id="equipmentRepairInfoListTbody">
                        <c:forEach items="${equipmentRepairInfoList}" var="list" varStatus="s">
                            <tr>
                                <td>${s.index+1}</td>
                                <td>${list.equipmentName}<input type="hidden" name="equipmentName" value="${list.equipmentName}"></td>
                                <td>${list.entity.equipmentRepairPerson}<input type="hidden" name="equipmentRepairPerson" value="${list.entity.equipmentRepairPerson}"></td>
                                <td><fmt:formatDate value='${list.entity.equipmentRepairTime}' pattern='yyyy-MM-dd '/><input type="hidden" name="equipmentRepairTime" value="${list.entity.equipmentRepairTime}"></td>
                                <td>${list.entity.failureCause}<input type="hidden" name="failureCause" value="${list.entity.failureCause}"></td>
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
            $("#equipmentRepairBox").modal('show')
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
            var equipmentName = $("#equipmentRepairBox").find('input[name="equipmentName"]').val(),
                    equipmentRepairPerson = $("#equipmentRepairBox").find('input[name="equipmentRepairPerson"]').val(),
                    equipmentRepairTime = $("#equipmentRepairBox").find('input[name="equipmentRepairTime"]').val(),
                    failureCause = $("#equipmentRepairBox").find('input[name="failureCause"]').val(),
                    equipmentInfoId = $("#equipmentRepairBox").find('input[name="equipmentInfoId"]').val(),
                    equipmentNameId = $("#equipmentRepairBox").find('input[name="equipmentNameId"]').val()
            id = $("#equipmentRepairBox").find('input[name="id"]').val()
            var form = $("#equipmentRepairBox").find('form')
            form.bootstrapValidator({
                live: 'disabled',
                message: 'This value is not valid',
                fields: {
                    equipmentNo: {
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

                var equipmentRepairInfo = {};
                equipmentRepairInfo.equipmentInfoId = equipmentInfoId;
                equipmentRepairInfo.equipmentNameId = equipmentNameId;
                equipmentRepairInfo.equipmentRepairPerson = equipmentRepairPerson;
                equipmentRepairInfo.equipmentRepairTime = equipmentRepairTime;
                equipmentRepairInfo.failureCause = failureCause;

                $.ajax({
                    url: "<%=path%>/equipmentRepairInfo/saveEquipmentRepairInfo",
                    type:"post",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(equipmentRepairInfo),
                    dataType: "json",
                    success: function (data) {
                        if(data.success || data.success == true || data.success == "true") {
                            location.href='<%=path%>/equipmentRepairInfo/equipmentRepairList';
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

    })
</script>
</body>

</html>