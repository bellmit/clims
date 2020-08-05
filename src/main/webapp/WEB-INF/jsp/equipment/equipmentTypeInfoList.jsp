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
<div class="modal fade popBox bigBox" id="equipmentTypeBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form  id="consignationForm" action="<%=path %>/equipmentTypeInfo/equipmentTypeList" class="form-horizontal tasi-form"
                   method="post">
                <div class="modal-header">
                    <h4 class="modal-title">设备类型信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>类型编号</label>
                                <input type="text" class="form-control" placeholder="请输入类型编号" name="equipmentTypeNo" id="equipmentTypeNo">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>类型名称</label>
                                <input type="text" class="form-control" placeholder="请输入类型名称" name="equipmentTypeName" id="equipmentTypeName">
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>实验阶段</label>
                                <div class="select">
                                    <select class="form-control" required name="experimentalStage" id="experimentalStage">
                                        <option value="" disabled selected hidden>请选择实验阶段</option>
                                        <c:forEach items="${experimentalStageList}" var="list">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">使用期限</label>
                                <div class="col-sm-2">蓝色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="useBlueWarn" name="useBlueWarn" value="${equipmentTypeInfo.useBlueWarn}"/>
                                </div>
                                <div class="col-sm-1">月</div>
                                <div class="col-sm-2">红色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="useRedWarn" name="useRedWarn" value="${equipmentTypeInfo.useRedWarn}"/>
                                </div>
                                <div class="col-sm-1">月</div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">维修周期</label>
                                <div class="col-sm-2">蓝色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="repairBlueWarn" name="repairBlueWarn"  value="${equipmentTypeInfo.repairBlueWarn}"/>
                                </div>
                                <div class="col-sm-1">天</div>
                                <div class="col-sm-2">红色预警</div>
                                <div class="col-sm-2">
                                    <input type="text" class="form-control" id="repairRedWarn" name="repairRedWarn" value="${equipmentTypeInfo.repairRedWarn}"/>
                                </div>
                                <div class="col-sm-1">天</div>
                            </div>
                        </div>
                        <div class="col-md-12">
                            <div class="form-group">
                                <label>描述</label>
                                <textarea class="form-control" rows="3" placeholder="请输入描述" id="remark"
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
                    <button type="button" class="btn btn-lang btn-blue-border" data-dismiss="modal" id="qu">取消</button>
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
                <div>设备类型列表</div>
                <button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>类型编号</th>
                        <th>类型名称</th>
                        <th>实验阶段</th>
                        <th>所属单位</th>
                        <th>入库时间</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="equipmentTypeInfoListTbody">
                        <c:forEach items="${equipmentTypeInfoList}" var="list" varStatus="s">
                            <tr>
                                <td>${s.index+1}</td>
                                <td>${list.entity.equipmentTypeNo}<input type="hidden" name="equipmentTypeNo" value="${list.entity.equipmentTypeNo}"></td>
                                <td>${list.entity.equipmentTypeName}<input type="hidden" name="equipmentTypeName" value="${list.entity.equipmentTypeName}"></td>
                                <td>${list.experimentalStageName}<input type="hidden" name="experimentalStage" value="${list.entity.experimentalStage}"></td>
                                <td><c:forEach items="${orgInfo}" var="orglist">
                                    <c:if test="${orglist.orgId eq list.entity.orgId}">${orglist.orgName}</c:if>
                                    </c:forEach>
                                    <input type="hidden" name="orgId" value="${list.entity.orgId}"></td>
                                <td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd '/><input type="hidden" name="createDatetime" value="${list.entity.createDatetime}"></td>
                                <td>${list.entity.remark}<input type="hidden" name="remark" value="${list.entity.remark}"></td>
                                <td>
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

        $("#qu").off().on("click",function(){
            $("#equipmentTypeNo").val("");
            $("#equipmentTypeName").val("");
            $("#experimentalStage").val("");
            $("#useBlueWarn").val("");
            $("#useRedWarn").val("");
            $("#repairBlueWarn").val("");
            $("#repairRedWarn").val("");
            $("#remark").val("");
            $("#equipmentTypeBox").find('input[name="id"]').val("");
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

        $(".addMaterialEvidencer").click(function () {
            $("#equipmentTypeBox").modal('show')
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
            var equipmentTypeNo = $("#equipmentTypeBox").find('input[name="equipmentTypeNo"]').val(),
                    equipmentTypeName = $("#equipmentTypeBox").find('input[name="equipmentTypeName"]').val(),
                    experimentalStage = $("#equipmentTypeBox").find('select[name="experimentalStage"]').val(),
                    useBlueWarn = $("#equipmentTypeBox").find('input[name="useBlueWarn"]').val(),
                    useRedWarn = $("#equipmentTypeBox").find('input[name="useRedWarn"]').val(),
                    repairBlueWarn = $("#equipmentTypeBox").find('input[name="repairBlueWarn"]').val(),
                    repairRedWarn = $("#equipmentTypeBox").find('input[name="repairRedWarn"]').val(),
                    remark = $("#equipmentTypeBox").find('textarea[name="remark"]').val(),
                    id = $("#equipmentTypeBox").find('input[name="id"]').val()
            var form = $("#equipmentTypeBox").find('form')
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
                    },
                    experimentalStage: {
                        validators: {
                            notEmpty: {
                                message: "请选择试验阶段"
                            }
                        }
                    }
                }
            });
            form.bootstrapValidator('validate');
            if (form.data('bootstrapValidator').isValid()) {
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);

                var equipmentTypeInfo = {};
                equipmentTypeInfo.id = id;
                equipmentTypeInfo.equipmentTypeNo = equipmentTypeNo;
                equipmentTypeInfo.equipmentTypeName = equipmentTypeName;
                equipmentTypeInfo.experimentalStage = experimentalStage;
                equipmentTypeInfo.useBlueWarn = useBlueWarn;
                equipmentTypeInfo.useRedWarn = useRedWarn;
                equipmentTypeInfo.repairBlueWarn = repairBlueWarn;
                equipmentTypeInfo.repairRedWarn = repairRedWarn;
                equipmentTypeInfo.remark = remark;

                $.ajax({
                    url: "<%=path%>/equipmentTypeInfo/saveEquipmentTypeInfo",
                    type:"post",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(equipmentTypeInfo),
                    dataType: "json",
                    success: function (data) {
                        if(data.success || data.success == true || data.success == "true") {
                            location.href='<%=path%>/equipmentTypeInfo/equipmentTypeList';
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
        $("#equipmentTypeInfoListTbody").on("click", ".edit", function () {
            var equipmentTypeNo = $(this).parents("tr").find('input[name="equipmentTypeNo"]').val(),
                    equipmentTypeName = $(this).parents("tr").find('input[name="equipmentTypeName"]').val(),
                    experimentalStage = $(this).parents("tr").find('input[name="experimentalStage"]').val(),
                    useBlueWarn = $(this).parents("tr").find('input[name="useBlueWarn"]').val(),
                    useRedWarn = $(this).parents("tr").find('input[name="useRedWarn"]').val(),
                    repairBlueWarn = $(this).parents("tr").find('input[name="repairBlueWarn"]').val(),
                    repairRedWarn = $(this).parents("tr").find('input[name="repairRedWarn"]').val(),
                    remark = $(this).parents("tr").find('input[name="remark"]').val(),
                    index = $(this).parents("tr").index(),
                    id = $(this).parents("tr").find('input[name="id"]').val()
            $("#equipmentTypeBox").find('input[name="equipmentTypeNo"]').val(equipmentTypeNo)
            $("#equipmentTypeBox").find('input[name="equipmentTypeName"]').val(equipmentTypeName)
            $("#equipmentTypeBox").find('select[name="experimentalStage"]').val(experimentalStage)
            $("#equipmentTypeBox").find('input[name="useBlueWarn"]').val(useBlueWarn)
            $("#equipmentTypeBox").find('input[name="useRedWarn"]').val(useRedWarn)
            $("#equipmentTypeBox").find('input[name="repairBlueWarn"]').val(repairBlueWarn)
            $("#equipmentTypeBox").find('input[name="repairRedWarn"]').val(repairRedWarn)
            $("#equipmentTypeBox").find('textarea[name="remark"]').val(remark)
            $("#equipmentTypeBox").find('input[name="index"]').val(index)
            $("#equipmentTypeBox").find('input[name="id"]').val(id)
            $("#equipmentTypeBox").modal('show')
        });

        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/equipmentTypeInfo/equipmentTypeList',
            //链接尾部
            //hrefLatter: '.html',
            getLink: function (page) {
                return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#consignationForm").serialize();
            }
            , lang: {
                firstPageText: '首页',
                firstPageTipText: '首页',
                lastPageText: '尾页',
                lastPageTipText: '尾页',
                prePageText: '上一页',
                prePageTipText: '上一页',
                nextPageText: '下一页',
                nextPageTipText: '下一页',
                totalPageBeforeText: '共',
                totalPageAfterText: '页',
                currPageBeforeText: '当前第',
                currPageAfterText: '页',
                totalInfoSplitStr: '/',
                totalRecordsBeforeText: '共',
                totalRecordsAfterText: '条数据',
                gopageBeforeText: '&nbsp;转到',
                gopageButtonOkText: '确定',
                gopageAfterText: '页',
                buttonTipBeforeText: '第',
                buttonTipAfterText: '页'
            }
        });

    })
</script>
</body>

</html>