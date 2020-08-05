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
<div class="modal fade popBox bigBox" id="equipmentNameBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="">
                <div class="modal-header">
                    <h4 class="modal-title">设备名称信息</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备类型</label>
                                <div class="select">
                                    <select class="form-control " required name="equipmentTypeId" id="equipmentTypeId">
                                        <option value="" disabled selected hidden>请选择设备类型</option>
                                        <c:forEach items="${equipmentTypeInfos}" var="equipmentTypeInfo" varStatus="do">
                                            <option value="${equipmentTypeInfo.id}">${equipmentTypeInfo.equipmentTypeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备编号</label>
                                <%--<input type="text" class="form-control" placeholder="请选择设备编号" name="equipmentNo" id="equipmentNos">--%>
                                <select class="form-control " required  name="equipmentNo" id="equipmentNos">
                                    <option value="" disabled selected hidden>请选择设备编号</option>
                                    <c:forEach items="${dictItemslist}" var="dictItemslist" varStatus="do">
                                        <option value="${dictItemslist.dictCode}">${dictItemslist.dictName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label>设备名称</label>
                                <input type="text" class="form-control" placeholder="请输入设备名称" name="equipmentName" id="equipmentNames">
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
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path %>/equipmentNameInfo/equipmentNameList" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>设备编号</label>
                                <%--<input type="text" id="equipmentNo" name="entity.equipmentNo" value="${query.entity.equipmentNo}"
                                       class="form-control" placeholder="请输入设备编号">--%>
                                <select class="form-control " name="entity.equipmentNo" id="equipmentNo" value="${query.entity.equipmentNo}">
                                    <option value="" disabled selected hidden>请选择设备编号</option>
                                    <c:forEach items="${dictItemslist}" var="dictItemslist" varStatus="do">
                                        <option value="${dictItemslist.dictCode}"
                                             <c:if test="${dictItemslist.dictCode eq query.entity.equipmentNo}">selected</c:if>>
                                             ${dictItemslist.dictName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>设备名称</label>
                                <input type="text" class="form-control" placeholder="请输入设备名称" id="equipmentName"
                                       name="entity.equipmentName" value="${query.entity.equipmentName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>设备类型</label>
                                <div class="select">
                                    <select id="equipmentTypeName" name="equipmentTypeName"
                                            value="${query.equipmentTypeName}" class="form-control">
                                        <option value="">全部</option>
                                        <c:forEach items="${equipmentTypeInfos}" var="equipmentTypeInfo" varStatus="do">
                                            <option value="${equipmentTypeInfo.id}" <c:if test="${equipmentTypeInfo.id eq query.entity.equipmentTypeId}">selected</c:if>>${equipmentTypeInfo.equipmentTypeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <button class="btn btn-blue-border" id="resetBtn">重置</button>
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
                <div>设备名称列表</div>
                <button class="btn btn-yellow addMaterialEvidencer" type="button">添加</button>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>设备类型</th>
                        <th>设备编号</th>
                        <th>设备名称</th>
                        <th>所属单位</th>
                        <th>入库时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="equipmentNameInfoListTbody">
                        <c:forEach items="${equipmentNameInfoList}" var="list" varStatus="s">
                            <tr>
                                <td>${s.index+1}</td>
                                <td>
                                    <c:forEach items="${equipmentTypeInfos}" var="equipmentTypeInfo" varStatus="do">
                                        <c:if test="${list.entity.equipmentTypeId eq equipmentTypeInfo.id}">${equipmentTypeInfo.equipmentTypeName}</c:if>
                                    </c:forEach>
                                    <input type="hidden" name="equipmentTypeId" value="${list.entity.equipmentTypeId}">
                                </td>
                                <td>
                                    <c:forEach items="${dictItemslist}" var="dictItemslist" varStatus="do">
                                        <c:if test="${list.entity.equipmentNo eq dictItemslist.dictCode}">${dictItemslist.dictName}</c:if>
                                    </c:forEach>
                                    <input type="hidden" name="equipmentNo" value="${list.entity.equipmentNo}">
                                </td>
                                <td>${list.entity.equipmentName}<input type="hidden" name="equipmentName" value="${list.entity.equipmentName}"></td>
                                <td>
                                    <c:forEach items="${orgInfo}" var="orglist">
                                         <c:if test="${orglist.orgId eq list.entity.orgId}">${orglist.orgName}</c:if>
                                    </c:forEach>
                                    <input type="hidden" name="orgId" value="${list.entity.orgId}"></td>
                                <td><fmt:formatDate value='${list.entity.createDatetime}' pattern='yyyy-MM-dd '/><input type="hidden" name="createDatetime" value="${list.entity.createDatetime}"></td>
                                <td>
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

        $("#resetBtn").off().on("click",function(){
            $("#equipmentTypeName").val("");
            $("#equipmentNo").val("");
            $("#equipmentName").val("");
        });
        $("#qu").off().on("click",function(){
            $("#equipmentTypeId").val("");
            $("#equipmentNos").val("");
            $("#equipmentNames").val("");
            $("#equipmentNameBox").find('input[name="id"]').val("");
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
            $("#equipmentNameBox").modal('show')
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
            var equipmentNo = $("#equipmentNameBox").find('select[name="equipmentNo"]').val(),
                    equipmentName = $("#equipmentNameBox").find('input[name="equipmentName"]').val(),
                    equipmentTypeId = $("#equipmentNameBox").find('select[name="equipmentTypeId"]').val(),
                    equipmentTypeName = $("#equipmentNameBox").find('select[name="equipmentTypeName"]').find("option:selected").text()
                    id = $("#equipmentNameBox").find('input[name="id"]').val()
            var form = $("#equipmentNameBox").find('form')
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
                    },
                    equipmentName: {
                        validators: {
                            notEmpty: {
                                message: "不能为空"
                            }
                        }
                    },
                    equipmentTypeId: {
                        validators: {
                            notEmpty: {
                                message: "请选择设备类型"
                            }
                        }
                    }
                }
            });
            form.bootstrapValidator('validate');
            if (form.data('bootstrapValidator').isValid()) {
                form.data('bootstrapValidator').destroy();
                form.data('bootstrapValidator', null);

                var equipmentNameInfo = {};
                equipmentNameInfo.id = id;
                equipmentNameInfo.equipmentNo = equipmentNo;
                equipmentNameInfo.equipmentName = equipmentName;
                equipmentNameInfo.equipmentTypeId = equipmentTypeId;

                $.ajax({
                    url: "<%=path%>/equipmentNameInfo/saveEquipmentNameInfo",
                    type:"post",
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(equipmentNameInfo),
                    dataType: "json",
                    success: function (data) {
                       if(data.success || data.success == true || data.success == "true" ) {
                            location.href='<%=path%>/equipmentNameInfo/equipmentNameList';
                        }
                        else {
                            alert("操作失败！");
                        }
                    },
                    error: function (e) {
                        alert(e);
                    }
                });

            }
        })
        //回显设备信息
        $("#equipmentNameInfoListTbody").on("click", ".edit", function () {
            var equipmentNo = $(this).parents("tr").find('input[name="equipmentNo"]').val(),
                    equipmentName = $(this).parents("tr").find('input[name="equipmentName"]').val(),
                    equipmentTypeId = $(this).parents("tr").find('input[name="equipmentTypeId"]').val(),
                    index = $(this).parents("tr").index(),
                    id = $(this).parents("tr").find('input[name="id"]').val()
            $("#equipmentNameBox").find('select[name="equipmentNo"]').val(equipmentNo)
            $("#equipmentNameBox").find('input[name="equipmentName"]').val(equipmentName)
            $("#equipmentNameBox").find('select[name="equipmentTypeId"]').val(equipmentTypeId)
            $("#equipmentNameBox").find('input[name="index"]').val(index)
            $("#equipmentNameBox").find('input[name="id"]').val(id)
            $("#equipmentNameBox").modal('show')
        });

        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/equipmentNameInfo/equipmentNameList',
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