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
        .noPadding {
            padding: 0px !important;
        }

        .btn-red {
            background: #ffe4e3;
            color: #f65c52;
        }

        .btn-red:hover,
        .btn-red:active,
        .btn-red:focus {
            color: #f65c52;
            outline: none;
        }

        .table thead tr:nth-child(1) th {
            background: #fff !important;
        }

        .table thead tr:nth-child(1) select {
            font-weight: 400;
        }

        .btn-gray {
            color: #616161;
            border: 1px solid #d8d8d8;
            background: #f0f0f0;
        }

        .btn-gray:hover,
        .btn-gray:active,
        .btn-gray:focus {
            color: #616161;
            border: 1px solid #d8d8d8;
            background: #f0f0f0;
            outline: none;
        }
        .btn-pink{
            background: #ffdcdb;
            color: #f4605a;
            border: 1px solid #f4605a;
            margin-left: 10px;
        }
        .btn-light-blue{
            background: #ebf5ff;
            color: #1e8af5;
            border: 1px solid #1e8af5;
        }
        /*设置打印样式*/
        @media print {

            /* 隐藏 chrome 浏览器下的 header, footer */
            @page {
                margin: 0;
                padding: 0;
            }


        }
        #fourImg{
            position: absolute;
            z-index: -88;
            top: 20px;
        }
        .beforeCheck{
            background: #296fff;
            color: #fff;
            padding: 6px;
            border-radius: 4px;
            width: 50px;
            text-align: center;
        }
    </style>
</head>
<body>
<script>
    /**
     * 阿拉伯数字转中文数字,
     * 如果传入数字时则最多处理到21位，超过21位js会自动将数字表示成科学计数法，导致精度丢失和处理出错
     * 传入数字字符串则没有限制
     * @param {number|string} digit
     */
    function toZhDigit(digit) {
        digit = typeof digit === 'number' ? String(digit) : digit;
        const zh = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
        const unit = ['千', '百', '十', ''];
        const quot = ['万', '亿', '兆', '京', '垓', '秭', '穰', '沟', '涧', '正', '载', '极', '恒河沙', '阿僧祗', '那由他', '不可思议', '无量', '大数'];

        let breakLen = Math.ceil(digit.length / 4);
        let notBreakSegment = digit.length % 4 || 4;
        let segment;
        let zeroFlag = [], allZeroFlag = [];
        let result = '';

        while (breakLen > 0) {
            if (!result) { // 第一次执行
                segment = digit.slice(0, notBreakSegment);
                let segmentLen = segment.length;
                for (let i = 0; i < segmentLen; i++) {
                    if (segment[i] != 0) {
                        if (zeroFlag.length > 0) {
                            result += '零' + zh[segment[i]] + unit[4 - segmentLen + i];
                            // 判断是否需要加上 quot 单位
                            if (i === segmentLen - 1 && breakLen > 1) {
                                result += quot[breakLen - 2];
                            }
                            zeroFlag.length = 0;
                        } else {
                            result += zh[segment[i]] + unit[4 - segmentLen + i];
                            if (i === segmentLen - 1 && breakLen > 1) {
                                result += quot[breakLen - 2];
                            }
                        }
                    } else {
                        // 处理为 0 的情形
                        if (segmentLen == 1) {
                            result += zh[segment[i]];
                            break;
                        }
                        zeroFlag.push(segment[i]);
                        continue;
                    }
                }
            } else {
                segment = digit.slice(notBreakSegment, notBreakSegment + 4);
                notBreakSegment += 4;

                for (let j = 0; j < segment.length; j++) {
                    if (segment[j] != 0) {
                        if (zeroFlag.length > 0) {
                            // 第一次执行zeroFlag长度不为0，说明上一个分区最后有0待处理
                            if (j === 0) {
                                result += quot[breakLen - 1] + zh[segment[j]] + unit[j];
                            } else {
                                result += '零' + zh[segment[j]] + unit[j];
                            }
                            zeroFlag.length = 0;
                        } else {
                            result += zh[segment[j]] + unit[j];
                        }
                        // 判断是否需要加上 quot 单位
                        if (j === segment.length - 1 && breakLen > 1) {
                            result += quot[breakLen - 2];
                        }
                    } else {
                        // 第一次执行如果zeroFlag长度不为0, 且上一划分不全为0
                        if (j === 0 && zeroFlag.length > 0 && allZeroFlag.length === 0) {
                            result += quot[breakLen - 1];
                            zeroFlag.length = 0;
                            zeroFlag.push(segment[j]);
                        } else if (allZeroFlag.length > 0) {
                            // 执行到最后
                            if (breakLen == 1) {
                                result += '';
                            } else {
                                zeroFlag.length = 0;
                            }
                        } else {
                            zeroFlag.push(segment[j]);
                        }

                        if (j === segment.length - 1 && zeroFlag.length === 4 && breakLen !== 1) {
                            // 如果执行到末尾
                            if (breakLen === 1) {
                                allZeroFlag.length = 0;
                                zeroFlag.length = 0;
                                result += quot[breakLen - 1];
                            } else {
                                allZeroFlag.push(segment[j]);
                            }
                        }
                        continue;
                    }
                }


                --breakLen;
            }
            //处理10-19解析成一十  一十九
            switch (result)
            {
                case '一十'  :
                    result = '十'
                    break;
                case '一十一' :
                    result = '十一'
                    break;
                case '一十二' :
                    result = '十二'
                    break;
                case '一十三' :
                    result = '十三'
                    break;
                case '一十四' :
                    result = '十四'
                    break;
                case '一十五' :
                    result = '十五'
                    break;
                case '一十六' :
                    result = '十六'
                    break;
                case '一十七' :
                    result = '十七'
                    break;
                case '一十八' :
                    result = '十八'
                    break;
                case '一十九' :
                    result = '十九'
                    break;
            }
            return document.getElementById("stage"+digit).innerHTML = "第" + result + "组";
        }
    }
</script>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>查询条件</div>
            </div>
            <div class="panel-body">
                <form id="consignationForm" action="<%=path %>/queryCompareResult/queryAreaRelationCompareResultList"
                      class="form-horizontal tasi-form"
                      method="post">
                    <div class="row">
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件名称</label>
                                <input type="text" id="caseName" name="caseName" value="${matchResult.caseName}"
                                       class="form-control"
                                       placeholder="请输入案件名称">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本条码</label>
                                <input type="text" class="form-control" placeholder="请输入样本条码" id="sampleNo"
                                       name="sampleNo" value="${matchResult.sampleNo}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>检材名称</label>
                                <input type="text" class="form-control" placeholder="请输入检材名称" id="sampleName"
                                       name="sampleName" value="${matchResult.sampleName}">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>样本类型</label>
                                <div class="select">
                                    <select id="instoredType" name="instoredType"
                                            value="${matchResult.instoredType}" class="form-control">
                                        <option value="">全部</option>
                                        <c:forEach items="${dictSampleEntryTypeList}" var="list" varStatus="cs">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${list.dictCode eq matchResult.instoredType}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <%--<div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>审核状态</label>
                                <div class="select">
                                    <select class="form-control">
                                        <option value="">审核状态</option>
                                    </select>
                                </div>
                            </div>
                        </div>--%>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>比中起止时间</label>
                                <div class="row">
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="createStartDatetime" name="createStartDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${matchResult.createStartDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择比中时间"
                                               readonly="readonly">
                                    </div>
                                    <div class="col-md-2" style="margin-top: 7px;">至</div>
                                    <div class="col-md-5 nopadding">
                                        <input type="text" id="createEndDatetime" name="createEndDatetime"
                                               class="form-control form_datetime"
                                               value="<fmt:formatDate value='${matchResult.createEndDatetime}' pattern='yyyy-MM-dd'/>"
                                               placeholder="请选择比中时间"
                                               readonly="readonly">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>分县局</label>
                                <div class="select">
                                    <c:choose>
                                        <c:when test="${not empty userOrgId}">
                                            <select id="acceptOrgId" name="acceptOrgId" value="${matchResult.acceptOrgId}" disabled="disabled"
                                                    class="form-control">
                                                <option value="" selected>全部</option>
                                                <c:forEach items="${acceptOrgList}" var="list" varStatus="cs">
                                                    <option value="${list.orgId}"
                                                            <c:if test="${list.orgId eq userOrgId}">selected</c:if>>${list.orgName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select id="acceptOrgId" name="acceptOrgId" value="${matchResult.acceptOrgId}"
                                                    class="form-control">
                                                <option value="" selected>全部</option>
                                                <c:forEach items="${acceptOrgList}" var="list" varStatus="cs">
                                                    <option value="${list.orgId}"
                                                            <c:if test="${list.orgId eq matchResult.acceptOrgId}">selected</c:if>>${list.orgName}</option>
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>匹配数(大于等于)</label>
                                <input type="text" class="form-control" id="sameCount" name="entity.sameCount" value="${matchResult.entity.sameCount}" placeholder="请输入匹配数">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>案件编号</label>
                                <input type="text" class="form-control" id="caseNo" name="caseNo" value="${matchResult.caseNo}" placeholder="请输入案件编号">
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group">
                                <label>比中状态</label>
                                <div class="select">
                                    <select id="compareStatus" name="entity.compareStatus"
                                            value="${matchResult.entity.compareStatus}" class="form-control">
                                        <c:forEach items="${compareStatusList}" var="list" varStatus="cs">
                                            <option value="${list.dictCode}"
                                                    <c:if test="${list.dictCode eq matchResult.entity.compareStatus}">selected</c:if>>${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 seachInputBox">
                            <div class="form-group seachButtonBox">
                                <input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
                                <button class="btn btn-blue" type="submit" id="addInformant">查询</button>
                                <button type="button" name="reset" class="btn btn-blue-border">重置</button>
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
                <div>串并案列表</div>
            </div>
            <div class="panel-body">
                <div class="row" style="margin: 0px">
                    <c:forEach items="${matchResultGroupList}" var="matchResultGroup" varStatus="s">
                        <div class="col-md-12 TableBox">
                            <table class="table table-hover table-bordered bigTable">
                                <thead>
                                <tr>
                                    <th colspan="1">
                                        <label class="custom-control checkbox-inline">
                                            <span class="custom-control-label" style="font-weight: 600" id="stage${s.index+1}">
                                                <script>toZhDigit(${s.index+1})</script>
                                            </span>
                                        </label>
                                    </th>
                                    <th colspan="4">
                                        <label style="height: 34px;line-height: 34px">${matchResultGroup.lastMatchTime}</label>
                                    </th>
                                    <th colspan="1">
                                        <label style="height: 34px;line-height: 34px">匹配个数：${matchResultGroup.matchCnt}</label>
                                    </th>
                                </tr>
                                <tr>
                                    <th>序号</th>
                                    <th>案件名称</th>
                                    <th>样本条码号</th>
                                    <th>检材名称</th>
                                    <th>入库样本类型</th>
                                    <th>所属分局</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${matchResultGroup.sampleList}" var="result" varStatus="c">
                                    <tr>
                                        <td>${c.index+1}</td>
                                        <td title="${result.casename}">
                                            <c:if test="${fn:length(result.casename) gt 14}">${fn:substring(result.casename,0,13)} ...</c:if>
                                            <c:if test="${fn:length(result.casename) lt 15}">${result.casename}</c:if>
                                        </td>
                                        <td title="${result.sampleid}" style="color: black">${result.sampleid}</td>
                                        <td class="clickRed" title="${result.samplename}">${result.samplename}</td>
                                        <td>${result.sampleTypeName}</td>
                                        <td>${result.delegateOrgName}</td>
                                        <td>
                                            <input type="hidden" name="groupId" value="${matchResultGroup.groupId}">
                                            <input type="hidden" name="sampleNo" value="${result.sampleid}">
                                            <input type="hidden" name="levelflag" value="${matchResultGroup.levelflag}">
                                            <button type="button" name="viewDatail" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                                查看详情
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </c:forEach>
                </div>
                <div class="row" style="padding: 0px">
                    <div class="col-md-12">
                        <div id="kkpager"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row">
        <canvas id="myCanvas" style="width: 0;height: 0;"></canvas>
    </div>
</div>
<img src="" alt="" id="fourImg">
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/entrustCurrency.js"></script>
<script src="<%=path%>/js/jQuery.print.js"></script>
<script>
    $(function () {

        $("button[name='viewDatail']").on("click",function(){
            var groupId = $("input[name='groupId']", $(this).parent()).val();
            var sampleNo = $("input[name='sampleNo']", $(this).parent()).val();
            var levelflag = $("input[name='levelflag']", $(this).parent()).val();
            location.href = "<%=path%>/queryCompareResult/queryAreaRelationCompareResultDetail?sampleNo=" +
                    sampleNo + "&groupId=" + groupId + "&levelflag=" + levelflag;
        });

        kkpager.generPageHtml({
            pno: ${pageInfo.page},
            //总页码
            total: ${pageInfo.pageCount},
            //总数据条数
            totalRecords: ${pageInfo.allRecordCount},
            //链接前部
            hrefFormer: '<%=path%>/queryCompareResult/queryAreaRelationCompareResultList',
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

        $("button[name='queryDetail']").on("click", function () {
            $(this).parent().siblings('.clickRed').css({'color':'red'})
            // var abd = $(this).parent().prev().find("span.custom-control-label").text()
            var groupNo = $(this).parent().parent().parent().parent().find('span.custom-control-label').text()
            console.log(groupNo)
            var groupId = $("input[name='groupId']", $(this).parent()).val();
            //var id = $("input[name='id']", $(this).parent()).val();
            location.href = "<%=path%>/queryCompareResult/queryCompareResultDetail?groupId=" + groupId + "&groupNo=" + groupNo;
        })

        $("#addInformant").on("click", function () {
            $("#page").val(1);
            $('#consignationForm').submit();
        });
        // 设置全选
        $("input[name='checkAll']").change(function () {
            if ($(this).is(":checked")) {
                $('input[name="checkboxCase"]').prop("checked", true)
            } else {
                $('input[name="checkboxCase"]').prop("checked", false)
            }
        })

        //解除关联全部
        $(".relieveGroupRelevanceAll").click(function(){
            var groupIdArr = []
            $("input[name='checkboxCase']:checked").each(function(){
                groupIdArr.push( $(this).parents("table").find("input[name='groupId']").eq(0).val())
            })
            groupIdArr.join(",")
            //location.href = "<%=path%>/queryCompareResult/relieveGroupsRelevance?groupIdArr=" + groupIdArr;
            $.ajax({
                url:"<%=path%>/queryCompareResult/relieveGroupsRelevance?groupIdArr=" + groupIdArr,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        $('#consignationForm').submit();
                    }else {
                        alert("生成失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("生成失败！");
                }
            });
        })

        //比中
        $(".confirmCompareGroup").click(function(){
            var groupIdArr = []
            $("input[name='checkboxCase']:checked").each(function(){
                groupIdArr.push( $(this).parents("table").find("input[name='groupId']").eq(0).val())
            })
            groupIdArr.join(",")
            //location.href = "<%=path%>/queryCompareResult/confirmCompareGroup?groupId=" + groupId;
            $.ajax({
                url:"<%=path%>/queryCompareResult/confirmCompareGroup?groupIdArr=" + groupIdArr,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(data.success || data.success == true || data.success == "true") {
                        $('#consignationForm').submit();``
                    }else {
                        alert("比中失败！" + data.message);
                    }
                },
                error:function(e) {
                    alert("比中失败！");
                }
            });
        })


        //导出
        $(".exportListExcel").click(function(){
            $('.exportListExcel').attr("disabled","true");
            location.href = "<%=path%>/queryCompareResult/exportListExcel?"+$("#consignationForm").serialize();
        })

        //判断选中案件是否是当前登录用户所属分局
        $("select[name='caseId']").change(function(){
            var caseId  =$(this).val()
            if(caseId!=""&&caseId!=null){
                $.ajax({
                    url:"<%=path%>/ExaminerController/queryByCaseId?caseId=" + caseId,
                    type:"get",
                    dataType:"text",
                    success:function(data){
                        console.log(data)
                        if(data=="error") {
//                            alert("选中的案件不是当前分局的案件")
                        }else {
                        }
                    },
                });
            }
        })


        //点击打印按钮
        $("button[name='queryImgUrl']").click(function(){
            var caseId="";
            $("select[name=caseId] option:selected").each(function(){
                    if($(this).val()!=""&&$(this).val()!=null){
                        caseId=$(this).val()
                    }
            });
            $.ajax({
                url:"<%=path%>/ExaminerController/queryExaminer?caseId=" + caseId,
                type:"get",
                dataType:"json",
                success:function(data){
                    if(!data.orgImg) {
                        alert("机构资质不存在，打印失败")
                        location.reload()
                        return
                    }
                    if(!data.amPersonalAptitudeOne){
                        alert("检验人资质不存在，打印失败")
                        location.reload()
                        return
                    }else {
                        if(!data.amPersonalAptitudeOne.aptitudePicture){
                            alert("检验人资质不存在，打印失败")
                            location.reload()
                            return
                        }
                    }
                    if(!data.amPersonalAptitudeTwo){
                        alert("检查人资质不存在，打印失败")
                        location.reload()
                        return
                    }else {
                        if(!data.amPersonalAptitudeTwo.aptitudePicture){
                            alert("检查人资质不存在，打印失败")
                            location.reload()
                            return
                        }
                    }
                    if(!data.amPersonalAptitudeThree){
                        alert("授权签字人资质不存在，打印失败")
                        location.reload()
                        return
                    }else {
                        if(!data.amPersonalAptitudeThree.aptitudePicture){
                            alert("检验人资质不存在，打印失败")
                            location.reload()
                            return
                        }
                    }
                    console.log(data.orgImg)
                    console.log(data.amPersonalAptitudeOne.aptitudePicture)
                    console.log(data.amPersonalAptitudeTwo.aptitudePicture)
                    console.log(data.amPersonalAptitudeThree.aptitudePicture)


                    //设置打印
                    // 四张图片合并为一张图片
                    var canvas = document.getElementById("myCanvas");
                    canvas.width=1240;
                    canvas.height=1640;
                    var ctx = canvas.getContext("2d");
                    var image1 = new Image();
                    image1.onload=function(){
                        ctx.drawImage(image1,0,0,620,820)
                    }
                    image1.src=data.orgImg;

                    var image2 = new Image();
                    image2.onload=function(){
                        ctx.drawImage(image2,620,0,620,820)
                    }
                    image2.src=data.amPersonalAptitudeOne.aptitudePicture;

                    var image3 = new Image();
                    image3.onload=function(){
                        ctx.drawImage(image3,0,820,620,820)
                    }
                    image3.src=data.amPersonalAptitudeTwo.aptitudePicture;

                    var image4 = new Image();
                    image4.onload=function(){
                        ctx.drawImage(image4,620,820,620,820)
                        // var image5 = new Image()
                        var image5 = document.getElementById("fourImg")
                        image5.crossOrigin = 'Anonymous';
                        var imgUrl = canvas.toDataURL("image/jpeg");
                        image5.src = imgUrl
                        $('body').append(image5)
                        // var myWindow = window.open("","")
                        // myWindow.innerHTML = image5;
                        my_print();
                        image5.style.display="none";
                    }
                    image4.src=data.amPersonalAptitudeThree.aptitudePicture;

                    $("#myCanvas").hide();






                }
            })
        })

        //重置
        $("button[name='reset']").on("click", function () {
            location.href = "<%=path%>/queryCompareResult/queryAreaRelationCompareResultList";
        })

    })
    //打印设置
    function my_print() {
        $("#fourImg").print();
    }
</script>
</body>

</html>