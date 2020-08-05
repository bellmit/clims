<%@ include file="../include.jsp" %>
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
        #look .modal-dialog {
            top: 50px;
        }

        #look .modal-body {
            padding: 0px;
            position: relative;
        }

        #look .closeBtn {
            position: absolute;
            width: 40px;
            height: 40px;
            color: #fff;
            background: #e7e7e7;
            text-align: center;
            line-height: 40px;
            top: -50px;
            right: 0px;
            border-radius: 50%;
            font-size: 25px;
            cursor: pointer;
        }

        #look .table {
            margin-bottom: 0px;
        }

        #look .table > thead > tr > th {
            border: none;
            background: #298de5;
            color: #fff;
            padding-left: 40px;
        }

        #look .table > thead > tr:nth-child(1) > th {
            line-height: 72px;
        }

        #look .table > thead > tr:nth-child(1) > th:nth-child(3) {
            background: #0060b5;
            line-height: 36px;
            text-align: center;
        }

        #look .table > thead > tr:nth-child(2) > th {
            background-color: #0074d9;
        }

        #look .table > tbody > tr > td {
            color: #000;
            font-weight: 600;
            padding-left: 40px;
            border-right: 1px solid #c1ccda;
        }

        #look .table > tbody > tr:nth-child(4n+1) {
            background: #ffd6d5;
        }

        #look .table > tbody > tr:nth-child(4n+2) {
            background: #e2efff;
        }

        #look .table > tbody > tr:nth-child(4n+3) {
            background: #eee1ff;
        }

        #look .table > tbody > tr:nth-child(4n+4) {
            background: #ffffff;
        }

        #look .table > tbody > tr:nth-last-child(1) {
            background: #fff7ed;
        }

        #look .table > tbody > tr:nth-last-child(1) td {
            color: #e27e00;
        }
        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
        }

        #look .modal-title {
            text-align: center;
            color: #0c81f5;
            font-weight: 600;
        }

        .modal-header {
            background: #f5f5f5;
        }

        .close {
            background: #d9d9d9 !important;
            padding: 5px;
            border-radius: 50%;
            color: #fff;
            opacity: 1 !important;
        }

        .close span {
            display: inline-block;
            height: 24px;
            width: 24px;
            color: #fff;
        }

        #look .modal-body {
            padding: 0px;
            padding-bottom: 15px;
        }

        #look .modal-body > div:nth-child(1) .col-md-4 {
            background: #f6fbff;
            height: 40px;
            line-height: 40px;
            color: #000;
            font-weight: 600;
            padding-left: 50px;
        }

        #look .modal-body > div:nth-child(1) .col-md-4:nth-child(2) {
            border-left: 1px solid #f2f2f2;
            border-right: 1px solid #f2f2f2;
        }

        #look .modal-body > div:nth-child(2) {
            max-height: 360px;
            overflow-y: auto;
        }

        #look .modal-body > div > div:nth-child(4n+1) .col-md-4 {
            background: #ffd6d6;
            height: 30px;
            line-height: 30px;
            color: #ff3333;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+2) .col-md-4 {
            background: #e2efff;
            height: 30px;
            line-height: 30px;
            color: #0072ff;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+3) .col-md-4 {
            background: #eee2ff;
            height: 30px;
            line-height: 30px;
            color: #5b00db;
            padding-left: 50px;
        }

        #look .modal-body > div > div:nth-child(4n+4) .col-md-4 {
            background: #f8f8f8;
            height: 30px;
            line-height: 30px;
            color: #464646;
            padding-left: 50px;
        }
        .table-title{
            display: flex;
            align-content: center;
        }
        .table-title > span{
            font-size: 18px;
            font-weight: bold;
            color: #2d8cf0;
        }
        .margin-top-8{
            margin-top:8px !important;
        }
        .btn-con{
            /*float: right;*/
            margin-left: auto;
        }
        .btn-con button{
            margin-right: 10px;
            width: 120px;
            height:35px;
            background:#2d8cf0;
            border: none;
            color: #fff;
            border-radius: 5px;
        }
        #associateMoal .modal-content{
            width: 1300px !important;
        }
        #associateMoal{
            left: -700px !important;
        }
        .modal-seach{
            padding-right: 15px;
            padding-left: 15px;
        }
        .modal-seach input{
            width: 50%;
            height:35px;
            display: inline-block !important;
            margin-right: 25px;
            /*border-radius: 3px;*/
        }
        .modal-seach button{
            width: 120px;
            height:35px;
            border: none;
            border-radius: 5px;
            background:#2d8cf0 ;
            color: #fff;
            font-size: 14px;
        }
        #associateMoal .modal-footer{
            display: flex;
            justify-content: center;
        }
        #associateMoal .modal-footer>div button{
            width: 100px;
            height:35px;
            background: #2d8cf0;
            border-radius: 5px;
            border: none;
            color:#fff;
            font-size: 14px;
            margin-right: 30px;
        }
        .iocn-gl{
            display:inline-block;
            text-align: center;
            line-height: 25px;
            width: 25px;
            height:25px;
            border-radius: 50%;
            background: #ffb515;
            color: #fff;
            font-size: 12px;
        }
        .infoModal{
            padding-top: 2% !important;
            left: 180px !important;
        }
        .infoModal .modal-content{
            width: 380px;
            /*height:120px;*/
        }
        .infoModal .modal-footer{
            padding: 5px !important;
            /*border: none !important;*/

        }
        .infoModal .modal-header{
            background: #2d8cf0 !important;
            padding: 5px 15px 5px 15px !important;
            width: 100% !important;
            border-radius:5px 5px 0px 0px;
        }
        .infoModal .modal-header span{
            color: #fff;
            font-size: 20px;
            font-weight: bold;
        }
        .infoOk{
            width: 65px;
            height:35px;
            background: #2d8cf0;
            border-radius: 5px;
            border: none;
            color:#fff;
            font-size: 14px;
        }
    </style>
</head>
<body>
<input type="hidden" value="${ipAddr}" id="ipAddress">
<div class="row Modular" style="padding-bottom: 65px">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>检材/样本信息</div>
                <input type="hidden" name="dataFilePath" value="${labAnalysisInfo.dataFilePath}">
                <input type="hidden" name="labAnalysisInfoId" value="${labAnalysisInfo.id}">
                <input type="hidden" name="ftpUser" value="${ftpUser}">
                <input type="hidden" name="ftpPassword" value="${ftpPassword}">
                <input type="hidden" id="loginUser" value="${user.amPersonalInfo.fullName}">
                <button class="btn btn-yellow" id="start">启动GeneMaker分析</button>
                <%--<button class="btn btn-yellow" id="startIdx">启动GeneMapperIdx分析</button>--%>
                <%--<c:if test="${noAnalysisSum ne 0}"><div>结果分析还剩 ${noAnalysisSum} 个检材/样本未完成</div></c:if>--%>
            </div>
        </div>
        <div class="panel-body">
            <%-- 2020-5-11 新增 编码样本表格 --%>
            <div class="sourceSampleCon">
                <div class="table-title">
                    <span>源样本</span>
                </div>
                <table class="table table-hover table-bordered bigTable margin-top-8">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th>检材类型</th>
                        <th>位置</th>
                        <th>板号</th>
                        <th style="width:70px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="samplelist_tbody">
                    <c:forEach items="${sourceLimsGeneVoSample}" var="list" varStatus="s">
                    <%--<c:forEach items="${limsSampleGeneVoList}" var="list" varStatus="s">--%>
                        <tr>
                            <td>${s.index + 1}</td>
                            <td title="${list.sampleNo}">
                                <input name="sampleNo" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.sampleNo}"
                                       readonly="readonly">
                            </td>
                            <td title="${list.sampleName}">
                                <input name="sampleName" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.sampleName}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="sampleTypeName" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.sampleTypeName}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="boardPosition" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.entity.boardPosition}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="boardNo" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.entity.boardNo}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input type="hidden" name="geneId" value="${list.entity.geneId}"/>
                                <input type="hidden" name="sampleId" value="${list.entity.sampleId}"/>
                                <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                    <button type="button"
                                            class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"> 查看
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
           <hr>
            <div class="codeSampleCon">
                <div class="table-title">
                    <span>编码样本</span>
                    <div class="btn-con">
                        <button id="offAssociate">解除关联</button>
                        <button id="onAssociate">关 联</button>
                    </div>
                </div>
                <table class="table table-hover table-bordered bigTable margin-top-8">
                    <thead>
                    <tr>
                        <th>全选<input type="checkbox" name="allSelect" style="margin-left: 15px"></th>
                        <th>序号</th>
                        <th>编码样本编号</th>
                        <th>检材类型</th>
                        <th>位置</th>
                        <th>板号</th>
                        <th style="width:70px;">操作</th>
                    </tr>
                    </thead>
                    <tbody id="Codelist_tbody">
                    <c:forEach items="${codeLimsGeneVoSample}" var="list" varStatus="s">
                        <tr>
                            <td> <input type="checkbox" name="radioSelect" data-id="123"></td>
                            <td>
                                ${s.index + 1}
                                <c:if test="${list.isRelation == '1'}"><span class="iocn-gl">关</span></c:if>
                                    <input type="hidden" value="${list.isRelation}" name="isRelation">
                            </td>
                            <td title="${list.sampleNo}">
                                <input name="sampleNo" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.sampleNo}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="sampleTypeName" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.sampleTypeName}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="boardPosition" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.entity.boardPosition}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input name="boardNo" type="text" class="form-control small"
                                       onmouseover="this.title=this.value" value="${list.entity.boardNo}"
                                       readonly="readonly">
                            </td>
                            <td>
                                <input type="hidden" name="geneId" value="${list.entity.geneId}"/>
                                <input type="hidden" name="sampleId" value="${list.entity.sampleId}"/>
                                <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                    <button type="button"
                                            class="btn-icon btn-icon-yellow btn-icon-chakan-yellow"> 查看
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="look" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-body">
                <span aria-hidden="true" data-dismiss="modal" aria-label="Close" class="closeBtn">&times;</span>
                <table class="table">
                    <thead>
                    <tr>
                        <th >序号</th>
                        <th >基因座</th>
                        <th  style="background: #298de5;line-height: 72px;">等位基因</th>
                    </tr>
                    <%--<tr>--%>
                        <%--<th id="referenceNo"></th>--%>
                        <%--<th id="matchNo"></th>--%>
                    <%--</tr>--%>
                    </thead>
                    <tbody id="viewMatchTBody">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row btn-box ">
    <div class="col-md-12" style="margin-right: 0px;">
        <button class="btn btn-yellow" type="button" id="saveBtn">完成</button>
    </div>
</div>
<div class="modal fade" id="associateMoal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" style="padding-top: 10%;"  >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <%--<div class="modal-header" style="background: #2d8cf0;">--%>
                <%--</div>--%>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <table class="table table-hover table-bordered bigTable margin-top-8">
                                <thead>
                                <tr>
                                    <th>选择</th>
                                    <th>序号</th>
                                    <th>检材编号</th>
                                    <th>检材名称</th>
                                    <th>检材类型</th>
                                    <th>位置</th>
                                    <th>板号</th>
                                </tr>
                                </thead>
                                <tbody id="radio_tbody">
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--1--%>
                                        <%--<input  name="X01" type="radio"  data-id="456" />--%>

                                    <%--</td>--%>
                                    <%--<td>sy1231-2301-231</td>--%>
                                    <%--<td>左后车窗内测玻璃xx</td>--%>
                                    <%--<td>脱落细胞</td>--%>
                                    <%--<td>D12</td>--%>
                                    <%--<td>201030-sxx-212-3</td>--%>
                                <%--</tr>--%>
                                <%--<tr>--%>
                                    <%--<td>--%>
                                        <%--2--%>
                                        <%--<input name="X01" type="radio" data-id="456" />--%>
                                    <%--</td>--%>
                                    <%--<td>sy1231-2301-231</td>--%>
                                    <%--<td>左后车窗内测玻璃xx</td>--%>
                                    <%--<td>脱落细胞</td>--%>
                                    <%--<td>D12</td>--%>
                                    <%--<td>201030-sxx-212-3</td>--%>
                                <%--</tr>--%>
                                <c:forEach items="${sourceLimsGeneVoSample}" var="list" varStatus="s">
                                    <%--<c:forEach items="${limsSampleGeneVoList}" var="list" varStatus="s">--%>
                                    <tr>
                                        <th><input  name="X01" type="radio"  data-id="456" /></th>
                                        <td>${s.index + 1}</td>
                                        <td title="${list.sampleNo}">
                                            <input name="sampleNo" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${list.sampleNo}"
                                                   readonly="readonly">
                                        </td>
                                        <td title="${list.sampleName}">
                                            <input name="sampleName" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${list.sampleName}"
                                                   readonly="readonly">
                                        </td>
                                        <td>
                                            <input name="sampleTypeName" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${list.sampleTypeName}"
                                                   readonly="readonly">
                                        </td>
                                        <td>
                                            <input name="boardPosition" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${list.entity.boardPosition}"
                                                   readonly="readonly">
                                        </td>
                                        <td>
                                            <input name="boardNo" type="text" class="form-control small"
                                                   onmouseover="this.title=this.value" value="${list.entity.boardNo}"
                                                   readonly="readonly">
                                        </td>
                                        <td>
                                            <input type="hidden" name="geneId" value="${list.entity.geneId}"/>
                                            <input type="hidden" name="sampleId" value="${list.entity.sampleId}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <div>
                        <button id="associateonOK">关 联</button>
                        <button id="offmodal-btn">取 消</button>
                    </div>
                </div>
            </div>
        </div>
</div>
<div class="modal fade infoModal" id="infoModal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" style="padding-top: 10%;"  >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <span>！</span>
            </div>
            <div class="modal-body">
                <span style="font-size: 18px;font-weight: bold">请先选择需要处理的检材！</span>
            </div>
            <div class="modal-footer">
                <div class="">
                    <button id="infoOk" class="infoOk">确 定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade infoModal" id="statusInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" style="padding-top: 10%;"  >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <span>！</span>
            </div>
            <div class="modal-body">
                <span style="font-size: 18px;font-weight: bold">请先取消已关联检材的选中状态！</span>
            </div>
            <div class="modal-footer">
                <div class="">
                    <button id="statusInfoOk" class="infoOk">确 定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        $("#infoOk").click(function(){
            $("#infoModal").modal('hide');
        });
        // 2020-5-11 新增 表格复选框 全选事件
        $('[name="allSelect"]').click(function () {
            //  改变表格中复选框状态 如果为true改为false 为false改为true
            if ($('[name="allSelect"]').prop("checked")) {
                $('[name="radioSelect"]').prop("checked", true)
            } else {
                $('[name="radioSelect"]').prop("checked", false)
            }

        });
        // 2020-5-11 关联按钮 点击事件
        var $ids = [];
        $("#onAssociate").on("click",function(){
            // 查找表格中被选中的tr
            var $chkBoxes =  $("#Codelist_tbody").find('input:checked');
            $ids = [];
            if($chkBoxes.length == 0){
                $("#infoModal").modal('show');
                return;
            }
            $("#Codelist_tbody tr").each(function(){
                if($(this).find("input[type='checkbox']").is(':checked')){
                    $ids.push($(this).children('td').eq(2).find("input").val());
                }
            })
            // console.log($ids); // 数组 ，可用join方法，截取为逗号分隔的字符串。
            $("#associateMoal").modal('show');
        });
        $("#offAssociate").on("click",function(){
            var isRelation1 = '0';
            var off_ids = [];
            var $off_chkBoxes =  $("#Codelist_tbody").find('input:checked');
            if($off_chkBoxes.length == 0){
                $("#infoModal").modal('show');
                return;
            }
            $("#Codelist_tbody tr").each(function(){
                if($(this).find("input[type='checkbox']").is(':checked')){
                    off_ids.push($(this).children('td').eq(2).find("input").val());
                }
            })
            var a = off_ids.join(",");
            console.log(a,"解除关联")
            // 发送需要解除关联的检材编号集合,成功时刷新表格，改变关联标识状态 隐藏弹窗 $("#associateMoal").modal('hide');

            $.ajax({
                url: "<%=path%>/center/updateCodeSampleRelation?codeSampleNos="+a + "&isRelation=" + isRelation1,
                type: "post",
                success: function (data) {
                    history.go(0);
                },
                error: function (e) {
                    alert(e);
                }
            })
        });
        //  关联弹窗 关联确认按钮
        $("#associateonOK").click(function(){
            var $radios =  $("#radio_tbody").find(':radio:checked');
            var $ids_radio = [];
            if($radios.length == 0){
                $("#infoModal").modal('show');
                return;
            }
            $("#radio_tbody tr").each(function(){
                if($(this).find("input[type='radio']").is(':checked')){
                    $ids_radio.push($(this).children('td').eq(1).find("input").val());
                }
            })
            var codeSampleNos = $ids.join(","); // 编码样本 检材编号集合
            var sampleNo = $ids_radio.join(",") // 单选框检材编号集合，点击关联按钮时，将两组id集合发送后台，做关联操作。
            var isRelation = "1"
            console.log(codeSampleNos,"复选")
            console.log(sampleNo,"单选")
            var status = 0;
            $("#Codelist_tbody tr").each(function(){
                if($(this).find("input[type='checkbox']").is(':checked')){
                    console.log("进入一层判断");
                    if($(this).find("input[name='isRelation']").val() == 1){
                        console.log("进入二层判断");
                        status += 1;
                    }
                }
            })
            console.log(status)
            if(status > 0){
                $("#statusInfo").modal('show');
                return;
            }
           $.ajax({
               url: "<%=path%>/center/updateSampleRelation?codeSampleNos="+codeSampleNos + "&sampleNo=" + sampleNo + "&isRelation=" + isRelation,
               type: "post",
               success: function (data) {
                   history.go(0);

               },
               error: function (e) {
                   alert(e);
               }
           })
            // 循环表格中data-id的值，相等则添加关联标识
//            for(i = 0; i< $("#Codelist_tbody tr").length; i++ ){
//                if($ids[i] == $("#Codelist_tbody tr").children().eq(0).find("input").attr("data-id")){
//                    $("#Codelist_tbody tr").eq(i).children().eq(1).append("<span class='iocn-gl'>"+ "关" +"</span>")
//                }
//            }
        });
        $("#statusInfoOk").click(function () {
            $("#statusInfo").modal('hide');
        })
        // 关联弹窗 取消按钮
        $("#offmodal-btn").click(function () {
            $("#associateMoal").modal('hide');
        })
        $("#saveNextBtn").on("click", function () {
            location.href = "<%=path%>/center/analysisExperiment";
        });

        $("#addInformant").on("click", function () {
            var caseNo = $("#caseNo").val();
            var sampleNo = $("#sampeNo").val();
            if (caseNo == "" && sampleNo == "") {
                alert("请输入案件编号或检材编号！");
            }

            $.ajax({
                url: "<%=path%>/center/getExtractSampleList?caseNo=" + caseNo + "&sampleNo=" + sampleNo,
                type: "post",
                dataType: "text",
                success: function (data) {
                    if (data != null) {
                        for (var i = 0; i < data.length; i++) {
                            var sampleInfo = JSON.parse(data)[i];
                            console.log(sampleInfo);
                            //新添被鉴定人
                            var sampleTr = '<tr>'
                            sampleTr += '<td><label class="custom-control checkbox-inline" style="margin-top: -22px"><input class="custom-control-input" type="checkbox"><span class="custom-control-label"></span></label></td>'
                            sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                            sampleTr += '<td><input type="text" name="potsion" value="' + "" + '"/></td>'
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                            sampleTr += '</td>'
                            sampleTr += '</tr>'
                            $("#samplelist_tbody").append(sampleTr)
                        }
                        // $("#referenceNo").text(referenceId);
                        // $("#matchNo").text(sampleNo);
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
        //启动分析软件
        $("#start").click(function () {
            var labAnalysisInfoId = $("input[name='labAnalysisInfoId']").val();
            $.ajax({
                url: "<%=path%>/center/analysisResultFileEditStatus",
                type: "post",
                dataType: "json",
                data: {labAnalysisInfoId: labAnalysisInfoId},
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        if (data.isLock) {
                            printServer();
                            /*if (data.isLock == 0) {
                                if(confirm("该文件当前有人在修改,您打开后只能查看不能修改")) {
                                    printServer(printServerUrl);
                                }
                            }else {
                                printServer(printServerUrl);
                            }*/
                        }else {
                            alert("获取文件可修改状态失败");
                        }
                    } else {
                        alert("打开失败！原因：" + data.msg);
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })


        //启动分析软件
        $("#startIdx").click(function () {
            var labAnalysisInfoId = $("input[name='labAnalysisInfoId']").val();
            $.ajax({
                url: "<%=path%>/center/analysisResultFileEditStatus",
                type: "post",
                dataType: "json",
                data: {labAnalysisInfoId: labAnalysisInfoId},
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        if (data.isLock) {
                            printIdxServer();
                            /*if (data.isLock == 0) {
                                if(confirm("该文件当前有人在修改,您打开后只能查看不能修改")) {
                                    printServer(printServerUrl);
                                }
                            }else {
                                printServer(printServerUrl);
                            }*/
                        }else {
                            alert("获取文件可修改状态失败");
                        }
                    } else {
                        alert("打开失败！原因：" + data.msg);
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })

        function printServer() {
            var printServerUrl = "http://"+ $("#ipAddress").val() + ":927/StartLocalProgram?type=2";
            // var printServerUrl = "http://192.168.1.128:927/StartLocalProgram?type=2";

            // var dataFilePath = $("input[name='dataFilePath']").val();
            var dataFilePath = 1;
            var username = $("input[name='ftpUser']").val();
            var userpwd = $("input[name='ftpPassword']").val();
            var loginUser = $("#loginUser").val();

            var jsondata = {};
            jsondata.GeneMarkerFileFtp = dataFilePath;
            jsondata.username = username;
            jsondata.userpwd = userpwd;
            jsondata.localname = loginUser;
            jsondata.use = "1";
            //"{\"GeneMarkerFileFtp\":\""+dataFilePath+"\",\"username\":\""+ftpUser+"\",\"userpwd\":\""+ftpPassword+"\"}";

            $.ajax({
                type: "post",
                url: printServerUrl,
                timeout: 3000,
                data: JSON.stringify(jsondata),
                dataType: "json",
                success: function (data) {
                    if (data.msg == "Success") {
                    } else {
                        alert("打开失败！原因：" + data.msg);
                    }
                },
                error: function (e) {
                    //无法建立通信时，弹出下载安装插件的窗口

                }
            });
        }

        function printIdxServer() {
            // var printServerUrl = "http://"+ $("#ipAddress").val() + ":927/StartLocalProgram?Open=1";
            var printServerUrl = "http://192.168.1.128:927/StartLocalProgram?type=1";
            var dataFilePath = $("input[name='dataFilePath']").val();
            var username = $("input[name='ftpUser']").val();
            var userpwd = $("input[name='ftpPassword']").val();
            var loginUser = $("#loginUser").val();

            var jsondata = {};
            jsondata.GeneMarkerFileFtp = dataFilePath;
            jsondata.username = username;
            jsondata.userpwd = userpwd;
            jsondata.localname = loginUser;
            jsondata.use = "1";
            //"{\"GeneMarkerFileFtp\":\""+dataFilePath+"\",\"username\":\""+ftpUser+"\",\"userpwd\":\""+ftpPassword+"\"}";

            $.ajax({
                type: "post",
                url: printServerUrl,
                timeout: 3000,
                data: JSON.stringify(jsondata),
                dataType: "json",
                success: function (data) {
                    if (data.msg == "Success") {
                    } else {
                        alert("打开失败！原因：" + data.msg);
                    }
                },
                error: function (e) {
                    //无法建立通信时，弹出下载安装插件的窗口

                }
            });
        }

        ///点击查看按钮
        $("[name='viewBtn']").click(function () {
            var  geneId = $(this).prev("input[name='geneId']").val();

            location.href = "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" +geneId ;

            /*var ceneId = "D0F4768862654D16B568D2DF75AB1C1E";
            $.ajax({
                type: "get",
                async: false,
                <%--url: "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" + ceneId,--%>
                dataType: "json",
                success: function (data) {
                    console.log(data.maps)
                    if (data.maps!=null&&data.maps!="") {
                        $("#look").modal('show');
                        if (data.maps.length > 0) {
                            var matchInfo;
                            var sampleTr = "";

                            for (var i = 0; i < data.maps.length; i++) {
                                matchInfo = data.maps[i];
                                sampleTr += '<tr>';
                                sampleTr += '<td>'+ (i + 1) +'</td>';
                                sampleTr += '<td>'+ matchInfo.markerName +'</td>';
                                sampleTr += '<td>'+ matchInfo.allele +'</td>';
                                sampleTr += '</tr>';
                            }
                            $("#viewMatchTBody").append(sampleTr);
                        }
                    } else {
                        alert("查看失败!");
                    }
                },
            });*/
        });
        //点击完成按钮
        $("#saveBtn").click(function () {
          location.href="<%=path%>/center/analysisExperiment"
        });

    })
</script>
</body>

</html>