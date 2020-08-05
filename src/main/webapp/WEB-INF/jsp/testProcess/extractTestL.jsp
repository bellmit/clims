<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">检验过程</a></li>
    <li class="active">当前页</li>
</ol>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>添加检材</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>扫描案件编号</label>
                                <input type="text" id="caseNo" name="caseNo" value=""
                                       class="form-control"
                                       placeholder="请输入案件编号">
                            </div>
                        </div>

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group">
                                <label>扫描检材编号</label>
                                <input type="text" id="sampeNo" name="sampeNo" value=""
                                       class="form-control"
                                       placeholder="请输入检材编号">
                            </div>
                        </div>

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-blue" type="button" id="addInformant">确认</button>
                            </div>
                        </div>

                        <div class="col-md-3 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <button class="btn btn-yellow" type="button" id="">导入工作站文件</button>
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
                <div>检材列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th style="width: 150px;">
                            <label class="custom-control checkbox-inline"  style="margin-top: -22px">
                                <input class="custom-control-input" type="checkbox" name="checkAll">
                                <span class="custom-control-label"></span>
                            </label>全选
                        </th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th>检材类型</th>
                        <th>版孔位置</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="samplelist_tbody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="form-group seachButtonBox" style="height: 40px;">
                    <div style="float: right;height: 40px;">
                        <label style="margin-left: -350px;">板号</label>
                        <input type="text" id="boardNo" name="boardNo" value="" class="form-control"  placeholder="请输入板号" style="margin-left: -280px; margin-top: -60px; width: 150px;">
                        <button class="btn btn-blue" type="button" id="startBtn" style="margin-top: -110px;">开始实验</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $("#addInformant").on("click", function(){
            var caseNo = $("#caseNo").val();
            var sampleNo = $("#sampeNo").val();
            if(caseNo == "" && sampleNo == ""){
                alert("请输入案件编号或检材编号！");
            }

            $.ajax({
                url: "<%=path%>/center/getExtractSampleList?caseNo=" + caseNo + "&sampleNo=" + sampleNo,
                type: "post",
                dataType: "text",
                success: function (data) {
                    if (data != null) {
                        for(var i=0; i<data.length; i++){
                            var sampleInfo = JSON.parse(data)[i];
                            console.log(sampleInfo);
                            //新添被鉴定人
                            var sampleTr = '<tr>'
                            sampleTr += '<td><label class="custom-control checkbox-inline" style="margin-top: -22px"><input class="custom-control-input" type="checkbox"><span class="custom-control-label"></span></label></td>'
                            sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleTypeName  + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                            sampleTr += '<td><input type="text" name="potsion" value="' + "" + '"/></td>'
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                            sampleTr += '</td>'
                            sampleTr += '</tr>'
                            $("#samplelist_tbody").append(sampleTr)
                        }
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });

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


        $("input[name='checkAll']").change(function () {
            if ($(this).is(":checked")) {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("thead").next().find("input[type='checkbox']").prop("checked", false)
            }
        })
        $("tbody").find("input[type='checkbox']").change(function () {
            if ($(this).parents("tbody").find("input[type='checkbox']:checked").length == $(this).parents("tbody").find("input[type='checkbox']").length) {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", false)
            }
        })
    })
</script>
</body>

</html>