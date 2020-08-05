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
    <title>本案比对入库入库按钮页面</title>
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
        select:disabled{
            background: #f5f5f5 !important;
        }
        .btn-box{
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px;
        }
    </style>
    <script>
        var warsum = -1;
    </script>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>案件基本详情</div>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-6 seachInputBox" style="border-right: 1px solid #f3f3f3;">
                        <div class="form-group">
                            <label>案件编号：</label>
                            ${limsCaseInfo.caseNo}
                            <input type="hidden" id="caseId" value="${limsCaseInfo.caseId}"/>
                        </div>
                    </div>
                    <div class="col-md-6 seachInputBox">
                        <div class="form-group">
                            <label>案件名称：</label>
                            ${limsCaseInfo.caseName}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular " style="padding-bottom: 65px">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>样本列表</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>样本编号</th>
                        <th>样本名称</th>
                        <th>入库样本类型</th>
                        <%--<th>板孔位置</th>--%>
                        <th>状态</th>
                        <th>完成时间</th>
                    </tr>
                    </thead>
                    <tbody id="sampleInfoListTbody">
                    <c:forEach items="${limsSampleInfoDnaList}" var="sampleInfo" varStatus="s">
                        <tr>
                            <td>
                                <label class="custom-control checkbox-inline" style="margin-top: -22px; padding-left: 0px;">
                                    <input class="custom-control-input" type="checkbox" name="box">
                                    <span class="custom-control-label"></span>
                                </label>${s.index+1}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${sampleInfo.entity.auditedGeneId eq null}">
                                        ${sampleInfo.entity.sampleNo}
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="sampleId"
                                               value="${sampleInfo.entity.sampleId}">
                                        <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${sampleInfo.entity.auditedGeneId}">${sampleInfo.entity.sampleNo}</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${sampleInfo.entity.sampleName}</td>
                            <td style="width: 58%">
                                <div class="col-md-3">
                                    <div class="select">
                                        <select class="form-control" name="instoredType">
                                            <option value=''>请选择入库类型</option>
                                            <c:forEach items="${instoredTypeList}" var="list" varStatus="s">
                                                <c:if test="${'04' ne list.dictCode}">
                                                    <option value="${list.dictCode}" <c:if test="${sampleInfo.entity.instoredType eq list.dictCode}">selected</c:if>  >${list.dictName}</option>
                                                </c:if>                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3" style="width: 22%;">
                                    <div class="select">
                                        <select class="form-control" name="srcRelativeType" disabled>
                                            <option value=''>样本亲属关系</option>
                                            <c:forEach items="${relationList}" var="list" varStatus="s">
                                                <option value="${list.dictCode}" <c:if test="${list.dictCode==sampleInfo.entity.sameGroupSampleRole}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="select">
                                        <select class="form-control" name="sameGroupSample" disabled>
                                            <option value=''>请选择同组样本</option>
                                        </select>
                                        <input type="hidden" name="sameGroupSampleId" value="${sampleInfo.entity.sameGroupSample}"/>
                                    </div>
                                </div>

                                <div class="col-md-3">
                                    <div class="select">
                                        <select class="form-control" name="GroupSampleRole" disabled>
                                            <option value=''>同组样本角色</option>
                                            <c:forEach items="${relationList}" var="list" varStatus="s">
                                                <option value="${list.dictCode}" <c:if test="${list.dictCode==sampleInfo.entity.targetSampleRole}">selected</c:if>>${list.dictName}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                            </td>
                            <%--<td>
                                <c:choose>
                                    <c:when test="${not empty sampleInfo.boardPosition}">
                                        ${sampleInfo.boardPosition}
                                    </c:when>
                                    <c:otherwise>
                                        --
                                    </c:otherwise>
                                </c:choose>
                            </td>--%>
                            <c:choose>
                                <c:when test="${sampleInfo.entity.instoredFlag eq '0'}">
                                    <td>未入库</td>
                                </c:when>
                                <c:otherwise>
                                    <td style="color: red;">已入库</td>
                                </c:otherwise>
                            </c:choose>
                            <td>
                                 <fmt:formatDate value="${sampleInfo.entity.instoredDatetime}" pattern="yyyy-MM-dd"/>
                            </td>
                            <input type="hidden" name="sampleId" value="${sampleInfo.entity.sampleId}"/>
                            <input type="hidden" name="sampleNo" value="${sampleInfo.entity.sampleNo}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="modal" id="loading" tabindex="-1" role="dialog" aria-labelledby="exportSuccess"
     data-keyboard="false" data-backdrop="static">
    <div class="modal-dialog" role="document" style="width: 456px;">
        <div class="modal-content">
            <div class="modal-body" style="padding: 90px 0;text-align: center">
                <div class="exportIng">
                    <p>
                        <img src="<%=path%>/img/loading.gif" class="loading-img" alt="" width="200px">
                    </p>
                    <p style="font-size: 24px;font-weight: bold;color: #0C81F5;margin-top: 15px;">
                        正在提交入库，请稍候....
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12">
        <button type="button" class="btn btn-blue btn-lang" id="compareBtn">确认提交</button>
        <a href="<%=path%>/center/caseCompare"><button type="button" class="btn btn-blue-border btn-lang" name="return">返回</button></a>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        //初始化同组样本列表
        $('#sampleInfoListTbody').find('tr').each(function () {
            var instoredType = $('select[name="instoredType"]',$(this)).find("option:selected").val()
            var sameGroupSampleId = $(this).find("input[name='sameGroupSampleId']").val();

            var newOption = ''
            $(this).siblings().each(function () {
                var content = $(this).children('td').eq(1).text() + "&nbsp;&nbsp;&nbsp;" + $(this).children('td').eq(2).text()
                if(instoredType == '08' || instoredType == '09' || instoredType == '10'){
                    if($(this).find("input[name='sampleId']").val() == sameGroupSampleId){
                        newOption += '<option value="' + $(this).find("input[name='sampleId']").val() + '" selected>' + content + '</option>'
                    }else{
                        newOption += '<option value="' + $(this).find("input[name='sampleId']").val() + '">' + content + '</option>'
                    }
                }else{
                    newOption += '<option value="' + $(this).find("input[name='sampleId']").val() + '">' + content + '</option>'
                }
            })
            $(this).find("select[name='sameGroupSample']").append(newOption)
        })
        $("select[name='sameGroupSample']").each(function(){
            if($(this).val()!=""){
                $("input[name='sampleId'][value='"+$(this).val()+"']").parents("tr").find(".checkbox-inline").addClass("hidden")
            }
        })
        //选择亲属入库类型，需选择亲缘关系及同组样本
        $('select[name="instoredType"]').change(function () {
            if ($(this).val() == '08' || $(this).val() == '09' || $(this).val() == '10') {
                $(this).parents('.col-md-3').siblings().find("select[name='sameGroupSample']").prop("disabled",false)
                var sampleId = $("input[name='sampleId']", $(this).parents("tr")).val();
                var that =$(this)
                $.ajax({
                    url: "<%=path%>/center/getPersonRelative?sampleId="+sampleId,
                    type: "post",
                    success: function (data) {
                        that.parents(".col-md-3").next().find("select").val(data.relationType)
                        var relationType = data.relationType;
                    },
                    error: function (data) {
                        alert("查询失败!");
                    }
                });
            } else {
                console.log($(this).parents('.col-md-3').siblings().find('select').find('option[value=""]'))
                $(this).parents('.col-md-3').siblings().find('select').val("").prop("disabled",true)
            }
        });

        //选择同组样本，获取同组样本的亲属关系
        $('select[name="sameGroupSample"]').change(function () {
            var sampleId = $(this).find("option:selected").val();
            var that =$(this)
            $.ajax({
                url: "<%=path%>/center/getPersonRelative?sampleId="+sampleId,
                type: "post",
                success: function (data) {
                    var relationType = data.relationType;
                    var personGender = data.personGender;
                    //如果同组样本角色为空时，根据源样本角色进行判断
                    if(relationType == null){
                        var srcRelativeType = $('select[name="srcRelativeType"]',that.parents("tr")).find("option:selected").val();
                        //源样本角色为父亲、母亲时
                        if(srcRelativeType == '01'|| srcRelativeType == '02'){
                            if(personGender == '02'){
                                relationType = '女儿';
                            }else if (personGender == '01'){
                                relationType = '儿子';
                            }
                        }
                        //源样本角色为丈夫、妻子时
                        if(srcRelativeType == '03' || srcRelativeType == '04'){
                            if(personGender == '02'){
                                relationType = '妻子';
                            }else if (personGender == '01'){
                                relationType = '丈夫';
                            }
                        }
                        //源样本角色为儿子、女儿时
                        if(srcRelativeType == '05' || srcRelativeType == '06'){
                            if(personGender == '02'){
                                relationType = '母亲';
                            }else if (personGender == '01'){
                                relationType = '父亲';
                            }
                        }
                    }else {
                        //同组样本角色为父亲、母亲时
                        if(relationType == '01'|| relationType == '02'){
                            if(personGender == '02'){
                                relationType = '母亲';
                            }else if (personGender == '01'){
                                relationType = '父亲';
                            }
                        }
                        //同组样本角色为丈夫、妻子时
                        if(relationType == '03' || relationType == '04'){
                            if(personGender == '02'){
                                relationType = '妻子';
                            }else if (personGender == '01'){
                                relationType = '丈夫';
                            }
                        }
                        //同组样本角色为儿子、女儿时
                        if(relationType == '05' || relationType == '06'){
                            if(personGender == '02'){
                                relationType = '女儿';
                            }else if (personGender == '01'){
                                relationType = '儿子';
                            }
                        }
                    }
//                    that.parents(".col-md-3").next().find("select").val(relationType)
                    console.log(relationType)
                    if(relationType){
                        that.parents(".col-md-3").next().find("select").children("option:contains('"+relationType+"')").prop("selected",true)
                    }else{
                        that.parents(".col-md-3").next().find("select").val("")
                    }
                },
                error: function (data) {
                    alert("查询失败!");
                }
            });
            $(".checkbox-inline").removeClass("hidden")
            if(sampleId){
                $("input[name='sampleId'][value='"+sampleId+"']").parents("tr").find(".checkbox-inline").addClass("hidden")
            }
            $("select[name='sameGroupSample']").each(function(){
                if($(this).val()!=""){
                    $("input[name='sampleId'][value='"+$(this).val()+"']").parents("tr").find(".checkbox-inline").addClass("hidden")
                }
            })
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

        //入库比对提交
        $("#compareBtn").on("click", function () {
            $('#loading').modal('show');

            warsum = warsum - 2;
            var instoreTypeFlag = true;
            var checkCount = 0;
            var sampleInfoArr = new Array();
            var sampleInfo;
            $("tr", "#sampleInfoListTbody").each(function () {
                sampleInfo = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                    sampleInfo.sampleId = $("input[name='sampleId']", $(this)).val();
                    sampleInfo.sampleNo = $("input[name='sampleNo']", $(this)).val();
                    sampleInfo.instoredType = $("select[name='instoredType']", $(this)).val();
                    sampleInfo.targetSampleRole = $("select[name='GroupSampleRole']", $(this)).val();
                    sampleInfo.sameGroupSample = $("select[name='sameGroupSample']", $(this)).val();
                    sampleInfo.sameGroupSampleRole = $("select[name='srcRelativeType']", $(this)).val();
                    sampleInfoArr.push(sampleInfo);
                    if ($("select[name='instoredType']", $(this)).val() == '') {
                        instoreTypeFlag = false;
                    }
                }
            });

            if (!instoreTypeFlag) {
                alert("请选择入库样本类型！");
                return false;
            }

            if (checkCount <= 0) {
                alert("请选择要入库比对的样本!");
                return false;
            }

            $.ajax({
                url: "<%=path%>/center/submitAndCompare",
                type: "post",
                data: JSON.stringify(sampleInfoArr),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    $('#loading').modal('hide');

                    if (data.success || data.success == true || data.success == "true") {
                        alert("提交入库成功");
                        var caseId  = $("#caseId").val();
                        location.href="<%=path%>/center/warehouseCompareBtn?caseId="+caseId;
                    }else{
                        alert("提交入库失败!");
                    }
                },
                error: function (data) {
                    $('#loading').modal('hide');

                    alert("提交失败!");
                }
            });
        });

        centerModal('loading');
        //$('#loading').modal('show');

        function centerModal(id){
            $("#"+id).on('shown.bs.modal', function () {
                var $this = $(this);
                var dialog = $this.find('.modal-dialog');
                //此种方式，在使用动画第一次显示时有问题
                //解决方案，去掉动画fade样式
                var top = ($(window).height() - dialog.height()) / 2;
                dialog.css({
                    marginTop:top
                });
            })
        }

    })
</script>
</body>

</html>