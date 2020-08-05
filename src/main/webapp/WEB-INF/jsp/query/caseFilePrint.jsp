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
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        body {
            padding: 0px;
        }

        .tips {
            padding: 14px 0;
            text-align: center;
            color: #ff6b66;
            font-weight: bold;
            background: #ffbfbe;
        }

        .Modular {
            margin-top: 0px;
            padding-bottom: 60px;
        }

        .panel-body .row {
            padding: 0 85px;
        }

        .panel-body .col-md-2 {
            text-align: center;
            padding: 0px;
            position: relative;
            margin-bottom: 10px;
            padding-bottom: 45px;
        }

        .panel-body .col-md-2 > div {
            display: inline-block;
            height: 160px;
            width: 135px;
            cursor: pointer;
            margin-bottom: 10px;
        }

        .panel-body .col-md-2 > div > img {
            width: 100%;
            height: 100%;
        }
        .panel-body .col-md-2 > div span{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%);
        }
        .down {
            width: 55px;
            height: 55px;
            border-radius: 50%;
            font-size: 20px;
            background: #f5faff;
            position: absolute;
            left: 50%;
            margin-left: -27.5px;
            top: 20px;
            display: none;
        }

        .down:hover {
            background: #0c81f5;
            border: 1px solid #0c81f5;
            color: #fff;

        }

        .print {
            width: 55px;
            height: 55px;
            border-radius: 50%;
            font-size: 20px;
            background: #fff1f0;
            position: absolute;
            left: 50%;
            margin-left: -27.5px;
            top: 90px;
            display: none;
        }

        .print:hover {
            background: #ff5a56;
            border: 1px solid #ff5a56;
            color: #fff;
        }

        .btn:active:focus, .btn:focus {
            outline: none;
        }

        .panel-body .col-md-2 > div:hover {
            background: rgba(0, 0, 0, .7);
            border-radius: 5px;
        }

        .panel-body .col-md-2 > div:hover .btn {
            display: block;
        }

        .custom-control {
            display: block;
            text-align: center;
            position: relative;
        }

        .custom-control span {
            position: absolute;
            left: 50%;
            margin-left: -22.5px;
        }

        .custom-control-label::after {
            width: 43px;
            height: 43px;
            cursor: pointer;
        }

        .custom-control-label::before {
            width: 43px;
            height: 43px;
            border-radius: 50%;
            background-color: #f0f0f0;
            border: 1px solid #d9d9d9;
            background-image: none;
            background-repeat: no-repeat;
            background-position: center;
            background-size: 19px;
        }

        .custom-control-input:checked ~ .custom-control-label::before{
            background-color: #50c987;
            background-image: url("<%=path%>/img/check.svg");
        }

        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-top: 0px !important;
        }

        .btn-box .btn + .btn {
            margin-left: 20px;
        }
        #recordingSheetModal .modal-header{
            background: #007ef9;
        }
        #recordingSheetModal .modal-title{
            color: #fff;
        }
        #recordingSheetModal .modal-body{
            padding:0;
        }
        #recordingSheetModal .bigTable{
            margin-top:0;
        }
        #recordingSheetModal thead tr th{
            background: #f0f0f0;
            border:none;
        }
        .sheet-label{
            height:20px;
        }
        .sheet-control-label::before{
            position: absolute;
             top: 0.25rem;
            left: 0;
            display: block;
            width: 1.5rem;
            height: 1.5rem;
            pointer-events: none;
            content: "";
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
            background-color: #dee2e6;
            background-image: url("<%=path%>/img/check.svg");
        }
        .custom-control-input:checked ~ .sheet-control-label::before{
            background-color: #50c987;
            background-image: url("<%=path%>/img/check.svg");
        }
        .sheet-control-label::after{
            position: absolute;
            top: 0.25rem;
            left: 0px;
            display: block;
            width: 1.5rem;
            height: 1.5rem;
            content: "";
            background-size: 50% 50%;
            background-repeat: no-repeat;
            background-position: center center;
        }
    </style>
</head>
<body>
<div class="tips">请选择您要打印的文书或直接点击文书图标即可下载</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>案件打印</div>
                <div>案件名称：${limsCaseInfo.caseName}</div>
                <div>案件编号：${limsCaseInfo.caseNo}</div>
            </div>
            <%--委托id--%>
            <input type="hidden"  value="${consignmentId}" id="allConsignmentId">
            <%--案件id--%>
            <input type="hidden"  value="${limsCaseInfo.caseId}" id="caseId">
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/roll.png" alt=""/>
                            <span>卷皮</span>
                            <button class="btn down btn-blue-border" id="volumeDownload">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedVolume">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="01">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/directory.png" alt=""/>
                            <span>目录</span>
                            <button class="btn down btn-blue-border">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="02">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/directory.png" alt=""/>
                            <span>委托书</span>
                            <button class="btn down btn-blue-border" id="entrustBookBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesWts">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="14">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/contract.png" alt=""/>
                            <span>聘书</span>
                            <button class="btn down btn-blue-border" id="letterBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesPs">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="04">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/contract.png" alt=""/>
                            <span>检材流转记录表</span>
                            <button class="btn down btn-blue-border" id="transferRecordBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesJclz">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="13">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/experiment.png" alt=""/>
                            <span>预实验记录</span>
                            <button class="btn down btn-blue-border" id="preliminaryExperimenBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesYsy">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="05">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/powerAttorney.png" alt=""/>
                            <span>受理确认书</span>
                            <button class="btn down btn-blue-border" id="commissionedToConfirmBtn" >
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesSlqrs">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="03">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div id="recordBtn">
                            <img src="<%=path%>/img/caseFilePrint/edit.png" alt=""/>
                            <span>记录表</span>
                           <%-- <button class="btn down btn-blue-border" id="recordBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="recordPrintBtn">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>--%>
                            <%--<button class="btn down btn-blue-border" data-toggle="modal" data-target="#recordingSheetModal">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" data-toggle="modal" data-target="#recordingSheetModal">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>--%>
                        </div>
                        <%--<label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="09">
                            <span class="custom-control-label"></span>
                        </label>--%>
                    </div>
                    <%--<div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/extract.png" alt=""/>
                            <span>提取记录</span>
                            <button class="btn down btn-blue-border" id="extractBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesTq">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="06">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/amplification.png" alt=""/>
                            <span>扩增记录</span>
                            <button class="btn down btn-blue-border" id="amplificationRecordBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesKz">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="07">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/electrophoresis.png" alt=""/>
                            <span>上样记录</span>
                            <button class="btn down btn-blue-border" id="loadSampleBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesSy">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="08">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>--%>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/atlas.png" alt=""/>
                            <span>图谱</span>
                            <button class="btn down btn-blue-border">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="09">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/storage.png" alt=""/>
                            <span>入库单</span>
                            <button class="btn down btn-blue-border" id="warehouseReceipt">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="warehouseFiles">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="10">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/testimonial.png" alt=""/>
                            <span>鉴定书</span>
                            <button class="btn down btn-blue-border" id="expertiseReportBtn">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border" id="compressedFilesJds">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="11">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                    <div class="col-md-2">
                        <div>
                            <img src="<%=path%>/img/caseFilePrint/photo.png" alt=""/>
                            <span>照片</span>
                            <button class="btn down btn-blue-border">
                                <i class="fa fa-download" aria-hidden="true"></i>
                            </button>
                            <button class="btn print btn-red-border">
                                <i class="fa fa-print" aria-hidden="true"></i>
                            </button>
                        </div>
                        <label class="custom-control nopadding">
                            <input class="custom-control-input" type="checkbox" name="fileBox" value="12">
                            <span class="custom-control-label"></span>
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade popBox" id="recordingSheetModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">记录表</h4>
            </div>
            <div class="modal-body">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>编号</th>
                            <th>板号</th>
                            <th>提取</th>
                            <th>扩增</th>
                            <th>上样</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>01</td>
                            <td>2DSDFDSD</td>
                            <td>
                                <label class=" checkbox-inline sheet-label">
                                    <input class="custom-control-input " type="checkbox" name="box">
                                    <span class="sheet-control-label"></span>
                                </label>
                            </td>
                            <td>
                                <label class=" checkbox-inline sheet-label">
                                <input class="custom-control-input " type="checkbox" name="box">
                                <span class="sheet-control-label"></span>
                            </label></td>
                            <td>
                                <label class=" checkbox-inline sheet-label">
                                <input class="custom-control-input " type="checkbox" name="box">
                                <span class="sheet-control-label"></span>
                            </label></td>


                        </tr>
                    </tbody>
                </table>
                <div class="modal-footer">
                    <button class="btn btn-blue btn-lang" id="saveBtn">确定</button>
                    <button class="btn btn-blue-border btn-lang" type="button" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<form action="<%=path%>/dowmFileController/downLoadZip" type="hidden" id="downLoadZipForm">
    <input type="hidden" name="downLoadZip" value="">
</form>
<div class="row btn-box">
    <div class="col-md-12">
        <button class="btn btn-yellow-border btn-lang checkAll" type="button">全选</button>
        <button class="btn btn-red btn-lang" type="button" id="downFileAll">下载</button>
        <button class="btn btn-blue btn-lang" type="button" id="printFilesAll">打印</button>
    </div>
</div>
<%--<iframe style="display:none" id="printIframe" src="<%=path%>/dowmFileController/downLoadZip"></iframe>--%>
<%@ include file="../linkJs.jsp" %>
<script>
    setTimeout(function () {
        $(".tips").fadeOut("slow")
    }, 5000);
    $(".checkAll").click(function () {
        if ($("input[type='checkbox']:checked").length == $("input[type='checkbox']").length) {
            $("input[type='checkbox']").prop("checked", false)
        } else {
            $("input[type='checkbox']").prop("checked", true)
        }
    })


    //    卷皮下载

    $("#volumeDownload").on("click", function () {
        var caseId = $("#caseId").val();
        console.log(caseId);
        var consignmentId = $("#allConsignmentId").val();
        console.log(consignmentId);
        location.href = "<%=path%>/dowmFileController/isdowmFileVolumeBtn?consignmentId=" + consignmentId;
    });


    // 提取、扩增、电泳下载
    $("#recordBtn").on("click", function () {
        var caseId = $("#caseId").val();
        location.href = "<%=path%>/dowmFileController/recordDownload?caseId=" + caseId;
    });


    /*卷皮-打印*/
    $("#compressedVolume").on("click",function(){
        var consignmentId = $("#allConsignmentId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedVolume",
            data:{"consignmentId":consignmentId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });
    //入库单下载
    $("#warehouseReceipt").on("click", function () {
        var caseId = $("#caseId").val();
        location.href = "<%=path%>/dowmFileController/generateInboundOrder?caseId=" + caseId;
        /*var consignmentId = $("#allConsignmentId").val();*/
        /*$.ajax({
            url:"<%=path%>/dowmFileController/generateInboundOrder",
            data:{"caseId":caseId},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data!=null){
                    alert(data);
                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("案件未入库！");
            }
        });*/
    });

    /*检材流转记录表*/
    $("#transferRecordBtn").on("click", function () {
        var consignmentId = $("#allConsignmentId").val();
        $.ajax({
            url: "<%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == 1) {
                    location.href = "<%=path%>/center/transferRecordBtn?consignmentId=" + consignmentId;
                } else {
                    alert("所送现场检材均已取走！");
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    });

    /*受理确认书下载*/
    $("#commissionedToConfirmBtn").click(function(){
        location.href="<%=path%>/dowmFileController/dowmFileCommissionedToConfirm?consignmentId="+$("#allConsignmentId").val();
    })

    /*聘书下载*/
    $("#letterBtn").click(function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/dowmletterBtn?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.filePath!=null){
                    location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));
                }else {
                    alert("文件不存在,聘书下载失败！");
                }
            },
            error:function(e) {
                alert("聘书下载失败！");
            }
        });
    })

    /*委托书下载*/
    $("#entrustBookBtn").click(function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/entrustBookBtn?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.filePath!=null){
                    location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));
                }else {
                    alert("文件不存在,委托书下载失败！");
                }
            },
            error:function(e) {
                alert("委托书下载失败！");
            }
        });
    })

    /*预实验记录表下载*/
    $("#preliminaryExperimenBtn").click(function(){
        location.href="<%=path%>/dowmFileController/dowmFilePreliminaryExperiment?consignmentId="+$("#allConsignmentId").val();
    })

    /*提取记录下载*/
    $("#extractBtn").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/isdowmFileExtractBtn?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                console.log(data.status)
                if(data.status=="error"){
                    alert("文件不存在,提取记录生成失败！");
                }else {
                    var labExtractInfoList = data.labExtractInfoList;
                    var listLength = data.labExtractInfoList.length;
                    if (listLength > 0) {
                        var extractId = "";
                        for (var i = 0; i < listLength; i++) {
                            extractId = labExtractInfoList[i].extractId;
                            var url = "<%=path%>/dowmFileController/dowmFileExtractBtn?caseId="+caseId
                                    + "&extractId=" + extractId;
                            generateRecord(url);
                        }
                    }
                    <%--location.href="<%=path%>/dowmFileController/dowmFileExtractBtn?caseId="+caseId--%>
                }
            },
            error:function(e) {
                alert("提取记录生成失败！");
            }
        });
    });

    /*扩增记录下载*/
    $("#amplificationRecordBtn").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/isdowmFileAmplificationRecord?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                console.log(data.status)
                if(data.status=="error"){
                    alert("文件不存在,扩增记录生成失败！");
                }else {
                    var labPcrInfoList = data.labPcrInfoList;
                    var listLength = data.labPcrInfoList.length;
                    if (listLength > 0) {
                        var pcrId = "";
                        for (var i = 0; i < listLength; i++) {
                            pcrId = labPcrInfoList[i].pcrId;
                            var url = "<%=path%>/dowmFileController/dowmFileAmplificationRecord?caseId="+$("#caseId").val()
                                    + "&pcrId=" + pcrId;
                            generateRecord(url);
                            /*parent.location.href="<%=path%>/dowmFileController/dowmFileAmplificationRecord?caseId="+$("#caseId").val()
                                    + "&pcrId=" + pcrId;*/
                        }
                    }
                }
            },
            error:function(e) {
                alert("扩增记录生成失败！");
            }
        });
    });

    function generateRecord(url) {
        var fileFrame = document.createElement("iframe");
        fileFrame.src = url;//文件路径
        fileFrame.style.display = "none";
        document.body.appendChild(fileFrame);
    }

    /*电泳分离记录下载*/
    $("#loadSampleBtn").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/isdowmFileloadSample?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                console.log(data.status)
                if(data.status=="error"){
                    alert("文件不存在,电泳分离记录生成失败！");
                }else {
                    var labSyInfoList = data.labSyInfoList;
                    var listLength = data.labSyInfoList.length;
                    if (listLength > 0) {
                        var syId = "";
                        for (var i = 0; i < listLength; i++) {
                            syId = labSyInfoList[i].syId;
                            var url = "<%=path%>/dowmFileController/dowmFileloadSample?caseId="+$("#caseId").val()
                                    + "&syId=" + syId;
                            generateRecord(url);
                        }
                    }
                    <%--location.href="<%=path%>/dowmFileController/dowmFileloadSample?caseId="+caseId--%>
                }
            },
            error:function(e) {
                alert("电泳分离记录生成失败！");
            }
        });
    });

    /*鉴定书下载*/
    $("#expertiseReportBtn").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/dowmFileExpertiseRepor?caseId=" + caseId,
            type:"get",
            dataType:"json",
            success:function(data){
                if(data.success || data.success == true || data.success == "true") {
                    location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));
                }else {
                    alert("鉴定书不存在,生成失败！" + data.message);
                }
            },
            error:function(e) {
                alert("鉴定书生成失败！");
            }
        });
    });

    //点击下载按钮，下载选中的文件
    <%--$("#downFileAll").on("click",function(){--%>
    <%--var str="";--%>
    <%--$("[name='fileBox']:checked").each(function(){--%>
    <%--str+=$(this).val()+",";--%>
    <%--})--%>

    <%--var msg =  str.substring(0,str.length-1).split(",")--%>
    <%--for(var i=0;i<msg.length;i++){--%>
    <%--if(msg[i]=="03"){--%>
    <%--setTimeout(function(){--%>
    <%--/*委托确认书下载*/--%>
    <%--location.href="<%=path%>/dowmFileController/dowmFileCommissionedToConfirm?consignmentId="+$("#allConsignmentId").val();--%>
    <%--},1000)--%>
    <%--}else if(msg[i]=="05"){--%>
    <%--/*预实验记录表下载*/--%>
    <%--setTimeout(function(){--%>
    <%--location.href="<%=path%>/dowmFileController/dowmFilePreliminaryExperiment?consignmentId="+$("#allConsignmentId").val();--%>
    <%--},2000)--%>
    <%--}else if(msg[i]=="06"){--%>
    <%--setTimeout(function(){--%>
    <%--/*提取记录*/--%>
    <%--var caseId = $("#caseId").val();--%>
    <%--$.ajax({--%>
    <%--url:"<%=path%>/dowmFileController/isdowmFileExtractBtn?caseId=" + caseId,--%>
    <%--type:"get",--%>
    <%--dataType:"json",--%>
    <%--success:function(data){--%>
    <%--console.log(data.status)--%>
    <%--if(data.status=="error"){--%>
    <%--alert("提取记录生成失败！");--%>
    <%--}else {--%>
    <%--location.href="<%=path%>/dowmFileController/dowmFileExtractBtn?caseId="+caseId--%>
    <%--}--%>
    <%--},--%>
    <%--error:function(e) {--%>
    <%--alert("提取记录生成失败！");--%>
    <%--}--%>
    <%--});--%>
    <%--},4000)--%>
    <%--}else if(msg[i]=="07"){--%>
    <%--/*扩增记录*/--%>
    <%--setTimeout(function(){--%>
    <%--var caseId = $("#caseId").val();--%>
    <%--$.ajax({--%>
    <%--url:"<%=path%>/dowmFileController/isdowmFileAmplificationRecord?caseId=" + caseId,--%>
    <%--type:"get",--%>
    <%--dataType:"json",--%>
    <%--async: false,--%>
    <%--success:function(data){--%>
    <%--console.log(data.status)--%>
    <%--if(data.status=="error"){--%>
    <%--alert("扩增记录生成失败！");--%>
    <%--}else {--%>
    <%--location.href="<%=path%>/dowmFileController/dowmFileAmplificationRecord?caseId="+$("#caseId").val();--%>
    <%--}--%>
    <%--},--%>
    <%--error:function(e) {--%>
    <%--alert("扩增记录生成失败！");--%>
    <%--}--%>
    <%--});--%>
    <%--},6000);--%>
    <%--}else if(msg[i]=="08"){--%>
    <%--/*上样记录*/--%>
    <%--setTimeout(function(){--%>
    <%--var caseId = $("#caseId").val();--%>
    <%--$.ajax({--%>
    <%--url:"<%=path%>/dowmFileController/isdowmFileloadSample?caseId=" + caseId,--%>
    <%--type:"get",--%>
    <%--dataType:"json",--%>
    <%--async: false,--%>
    <%--success:function(data){--%>
    <%--console.log(data.status)--%>
    <%--if(data.status=="error"){--%>
    <%--alert("电泳分离记录生成失败！");--%>
    <%--}else {--%>
    <%--location.href="<%=path%>/dowmFileController/dowmFileloadSample?caseId="+caseId--%>
    <%--}--%>
    <%--},--%>
    <%--error:function(e) {--%>
    <%--alert("电泳分离记录生成失败！");--%>
    <%--}--%>
    <%--});--%>
    <%--},7000);--%>
    <%--}else if(msg[i]=="11"){--%>
    <%--setTimeout(function(){--%>
    <%--var caseId = $("#caseId").val();--%>
    <%--$.ajax({--%>
    <%--url:"<%=path%>/dowmFileController/dowmFileExpertiseRepor?caseId=" + caseId,--%>
    <%--type:"get",--%>
    <%--dataType:"json",--%>
    <%--async: false,--%>
    <%--success:function(data){--%>
    <%--if(data.success || data.success == true || data.success == "true") {--%>
    <%--location.href = "<%=path%>/downloadFile?filePath=" + encodeURI(encodeURI(data.filePath));--%>
    <%--}else {--%>
    <%--alert("生成失败！" + data.message);--%>
    <%--}--%>
    <%--},--%>
    <%--error:function(e) {--%>
    <%--alert("生成失败！");--%>
    <%--}--%>
    <%--});--%>
    <%--},10000);--%>
    <%--}--%>
    <%--}--%>
    <%--})--%>

    //点击下载按钮，下载选中的文件
    $("#downFileAll").on("click",function(){
        debugger;
        var allConsignmentId =$("#allConsignmentId").val();
        var str="";
        $("[name='fileBox']:checked").each(function(){
            str+=$(this).val()+",";
        })
        if (!str) {
            alert("请选择要下载的文件!");
            return false;
        }
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesAll",
            data:{"consignmentId":allConsignmentId,"codeParm":str},
            type:"post",
            dataType:"json",
            success:function(data){
                debugger;

                if(data.success !=null && data.success.length > 0) {
                    if(data.status1){
                        alert(data.status1 +" ！");
                    }
                    if(data.status3){
                        alert(data.status3 +" ！");
                    }
                    if(data.status4){
                        alert(data.status4 +" ！");
                    }
                    if(data.status5){
                        alert(data.status5 +" ！");
                    }
                    if(data.status6){
                        alert(data.status6 +" ！");
                    }
                    if(data.status7){
                        alert(data.status7 +" ！");
                    }
                    if(data.status8){
                        alert(data.status8 +" ！");
                    }
                    if(data.status11){
                        alert(data.status11 +" ！");
                    }
                    if(data.status13){
                        alert(data.status13 +" ！");
                    }
                    if(data.status14){
                        alert(data.status14 +" ！");
                    }

                    <%--location.href = "<%=path%>/dowmFileController/downLoadZip?zipFilePath=" + data.success;--%>
                    location.href = "<%=path%>/dowmFileController/downLoadZip?filePath=" + encodeURI(encodeURI(data.success));

                }else {
                    alert("无可下载文件!");
                }
            },
//            error:function(e) {
//                debugger;
//                alert("下载失败！");
//            }
        });


    })

    //点击打印按钮，下载选中的文件
    $("#printFilesAll").on("click",function(){
        var allConsignmentId =$("#allConsignmentId").val();
        var str="";
        $("[name='fileBox']:checked").each(function(){
            str+=$(this).val()+",";
        })
        if (!str) {
            alert("请选择要打印的信息!");
            return false;
        }
        $.ajax({
            url:"<%=path%>/dowmFileController/printFilesAll",
            data:{"consignmentId":allConsignmentId,"codeParm":str},
            type:"post",
            dataType:"json",
            success:function(data){
                debugger;

                if(data){
                    if(data.status1){
                        alert(data.status1 +" ！");
                    }
                    if(data.status3){
                        alert(data.status3 +" ！");
                    }
                    if(data.status4){
                        alert(data.status4 +" ！");
                    }
                    if(data.status5){
                        alert(data.status5 +" ！");
                    }
                    if(data.status6){
                        alert(data.status6 +" ！");
                    }
                    if(data.status7){
                        alert(data.status7 +" ！");
                    }
                    if(data.status8){
                        alert(data.status8 +" ！");
                    }
                    if(data.status11){
                        alert(data.status11 +" ！");
                    }
                    if(data.status13){
                        alert(data.status13 +" ！");
                    }
                    if(data.status14){
                        alert(data.status14 +" ！");
                    }
                    if(data.status10){
                        alert(data.status10 +" ！");
                    }

                }else if(!data){
                    alert("无文件生成！");
                }



            }
//            error:function(e) {
//                debugger;
//                alert("下载失败！");
//            }
        });


    })

    /*电泳分离记录-打印*/
    $("#compressedFilesSy").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesSy",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*扩增记录-打印*/
    $("#compressedFilesKz").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesKz",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*扩增记录-打印*/
    $("#compressedFilesTq").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesTq",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*聘书-打印*/
    $("#compressedFilesPs").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesPs",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*
     委托书-打印*/
    $("#compressedFilesWts").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesWts",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*预实验记录-打印*/
    $("#compressedFilesYsy").on("click",function(){
        var consignmentId = $("#allConsignmentId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesYsy",
            data:{"consignmentId":consignmentId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*鉴定事项确认书-打印*/
    $("#compressedFilesSlqrs").on("click",function(){
        var consignmentId = $("#allConsignmentId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesSlqrs",
            data:{"consignmentId":consignmentId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*入库单-打印*/
    $("#warehouseFiles").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/warehouseFiles",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });


    /*鉴定书书-打印*/
    $("#compressedFilesJds").on("click",function(){
        var caseId = $("#caseId").val();
        $.ajax({
            url:"<%=path%>/dowmFileController/compressedFilesJds",
            data:{"caseId":caseId,"flag":"2"},
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.filePathName!=null&&data.filePathName!=""){

                }else {
                    alert("文件不存在打印失败")
                }
            },
            error:function(e) {
                alert("打印失败！");
            }
        });
    });

    /*检材流转记录表-打印*/
    $("#compressedFilesJclz").on("click", function () {
        var consignmentId = $("#allConsignmentId").val();
        $.ajax({
            url: "<%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == 1) {
                    //判断是否留有流转检材
                    $.ajax({
                        url:"<%=path%>/dowmFileController/compressedFilesJclz",
                        data:{"consignmentId":consignmentId,"flag":"2"},
                        type:"post",
                        dataType:"json",
                        success:function(data){
                            if(data.filePathName!=null&&data.filePathName!=""){

                            }else {
                                alert("文件不存在打印失败")
                            }
                        },
                        error:function(e) {
                            alert("打印失败！");
                        }
                    });
                } else {
                    alert("所送现场检材均已取走！");
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    });

</script>
</body>

</html>