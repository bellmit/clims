<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=path%>/css/print.css">
    <style>
        .smallBox .modal-content .modal-body {
            padding: 47px 60px;
            padding-bottom: 15px;
        }

        .smallBox .modal-content .modal-footer {
            /*padding: 15px 110px;*/
            border-top: 2px solid #f3f3f3;
        }

        .smallBox .modal-content .modal-body .col-md-1 span {
            display: inline-block;
            width: 25px;
            height: 25px;
            line-height: 20px;
            border-radius: 50%;
            text-align: center;
            font-size: 22px;
            margin-top: 2px;
            color: #fff;
            font-weight: 600;
            cursor: pointer;
        }

        .smallBox .modal-content .modal-body .col-md-1 {
            padding: 0px;
        }

        .smallBox .modal-content .modal-body .col-md-4:nth-child(1) {
            margin-top: 4px;
            /*padding-right: 0px;*/
            padding-left: 0px;
            text-align: right;
        }

        .smallBox .modal-content .modal-body .col-md-1:nth-child(2) {
            text-align: right;
        }

        .smallBox .modal-content .modal-body .col-md-1:nth-child(2) span {
            background: #e5e5e5;
        }

        .smallBox .modal-content .modal-body .col-md-1:nth-child(4) span {
            background: #f3504a;
        }

        .down p {
            color: #ff5a56;
            margin-top: 25px;
        }
    </style>
</head>

<div class="modal fade popBox smallBox in" id="choiceNo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">请选择打印种类和份数</h4>
            </div>
            <div class="modal-body">
                <div class="row" style="margin-bottom: 10px">
                    <div class="col-md-4 col-md-offset-1">样本条码</div>
                    <div class="col-md-1">
                        <span>-</span>
                    </div>
                    <div class="col-md-3">
                        <input type="text" name="personCnt" value="2" class="form-control">
                    </div>
                    <div class="col-md-1">
                        <span>+</span>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4 col-md-offset-1">检材条码</div>

                    <div class="col-md-1">
                        <span>-</span>
                    </div>
                    <div class="col-md-3">
                        <input type="text" name="sampleCnt" value="1" class="form-control">
                    </div>
                    <div class="col-md-1">
                        <span>+</span>
                    </div>
                </div>
            </div>
            <div class="modal-footer clearfix">
                <button type="button" name="savePrint" id="savePrint" class="btn  pull-left btn-blue btn-lang">确认
                </button>
                <button type="button" class="btn  pull-right btn-blue-border btn-lang " data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade popBox  in" id="preExperiment" tabindex="-1" role="dialog"
     <%--aria-labelledby="myModalLabel"--%> >
    <div class="modal-dialog" role="document" style="width:1200px;" >
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">预实验检验记录表</h4>
            </div>
            <div class="row Modular">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <%--<div class="panel-heading blue">
                            <div>样本条码</div>
                        </div>--%>
                        <div class="panel-body">
                            <table class="table table-hover table-bordered bigTable">
                                <thead>
                                <tr>

                                    <th>样本编号</th>
                                    <th>样本名称</th>
                                    <th>样本类型</th>
                                    <th>联苯胺</th>
                                    <th>FOB</th>
                                    <th>PSA</th>
                                    <th style="width: 10%;">备注</th>
                                </tr>
                                </thead>
                                <tbody id="preExperimentTbody">
                                    <tr></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer clearfix">
                <button type="button" name="downloadPreExperiment" id="downloadPreExperiment" class="btn btn-blue btn-lang">下载
                </button>
                <button type="button" class="btn btn-blue-border btn-lang " data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="tiaomaBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <i class="fa fa-times-circle" aria-hidden="true" data-dismiss="modal"></i><br>
        <img id="imgcode"/>
    </div>
</div>
<body>
<div class="row">
    <div class="col-md-4 ">
        <div class="down">
            <i class="fa fa-check-circle" aria-hidden="true"></i>
            <p>受理完成</p>
            <p>案件即将进入实验阶段</p>
            <%--<img src="<%=path%>/img/yushiyan.png" alt="" name="yushiyan">--%>
            <img src="<%=path%>/img/jiancailizhuan.png" alt="" name="circulationRecord">
            <img src="<%=path%>/img/weituoqueren.png" alt="" name="weituoqueren ">
            <%--<img src="<%=path%>/img/rukudan.png" alt="" name="rukudan ">--%>

            <p><i class="fa fa-exclamation-circle" aria-hidden="true"></i>支持下载文件/视频</p>
            <input type="hidden" name="consignmentId" value="${consignmentId}">
            <input type="hidden" name="caseId" value="${caseId}">

            <div style="padding-left: 40%;padding-top: 30%;">
                <button type="button" class="btn btn-blue btn-yellow upload" style="width: 120px;height: 42px;float: left;"
                        name="documentPrintAll">文书打印
                </button>
            </div>

        </div>
    </div>
    <div class="col-md-8">
        <div>
            <div>
                <div class="header">
                    <img src="<%=path%>/img/bianhao.png" alt="">
                    案件编号:<span>${caseInfo.caseNo}</span>
                    <img src="<%=path%>/img/ma1.png" alt="">
                </div>
                <div class="row Modular">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading blue">
                                <div>样本条码</div>
                            </div>
                            <div class="panel-body">
                                <table class="table table-hover table-bordered bigTable">
                                    <thead>
                                    <tr>
                                        <th>
                                            <label class="custom-control checkbox-inline">
                                                <input class="custom-control-input" type="checkbox" name="checkAll">
                                                <span class="custom-control-label"></span>
                                            </label>序号
                                        </th>
                                        <th>样本编号</th>
                                        <th>样本类型</th>
                                        <th>样本名称</th>
                                        <th>查看条码</th>
                                    </tr>
                                    </thead>
                                    <tbody id="personInfoTbody">
                                    <c:forEach items="${limsPersonInfoDnaList}" var="sampleInfo" varStatus="s">
                                        <tr>
                                            <td>
                                                <label class="custom-control checkbox-inline">
                                                    <input type="hidden" name="sampleId" value="${sampleInfo.sampleId}">
                                                    <input type="hidden" name="sampleNo" value="${sampleInfo.sampleNo}">
                                                    <input type="hidden" name="sampleName"
                                                           value="${sampleInfo.sampleName}">
                                                    <input type="hidden" name="sampleTypeName"
                                                           value="${sampleInfo.sampleTypeName}">
                                                    <input class="custom-control-input" type="checkbox"
                                                           name="personInfoBox">
                                                    <span class="custom-control-label"></span>
                                                </label>${s.index+1}
                                            </td>
                                            <td>${sampleInfo.sampleNo}</td>
                                            <td>${sampleInfo.sampleTypeName}</td>
                                            <td>${sampleInfo.sampleName}</td>
                                            <td>
                                                <img src="<%=path%>/img/ma2.png" alt="">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row Modular">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading yellow">
                                <div>检材条码</div>
                            </div>
                            <div class="panel-body">
                                <table class="table table-hover table-bordered bigTable">
                                    <thead>
                                    <tr>
                                        <th>
                                            <label class="custom-control checkbox-inline">
                                                <input class="custom-control-input" type="checkbox" name="checkAll">
                                                <span class="custom-control-label"></span>
                                            </label>序号
                                        </th>
                                        <th>样本编号</th>
                                        <th>样本类型</th>
                                        <th>样本名称</th>
                                        <th>查看条码</th>
                                    </tr>
                                    </thead>
                                    <tbody id="sampleInfoTbody">
                                    <c:forEach items="${limsSampleInfoDnaList}" var="sampleInfo" varStatus="s">
                                        <tr>
                                            <td>
                                                <label class="custom-control checkbox-inline">
                                                    <input type="hidden" name="sampleId" value="${sampleInfo.sampleId}">
                                                    <input type="hidden" name="preMethod1Result" value="${sampleInfo.preMethod1Result}">
                                                    <input type="hidden" name="preMethod2Result" value="${sampleInfo.preMethod2Result}">
                                                    <input type="hidden" name="preMethod3Result" value="${sampleInfo.preMethod3Result}">
                                                    <input type="hidden" name="sampleNo" value="${sampleInfo.sampleNo}">
                                                    <input type="hidden" name="sampleName"
                                                           value="${sampleInfo.sampleName}">
                                                    <input type="hidden" name="sampleTypeName"
                                                           value="${sampleInfo.sampleTypeName}">
                                                    <input class="custom-control-input" type="checkbox"
                                                           name="sampleInfoBox">
                                                    <span class="custom-control-label"></span>
                                                </label>${s.index+1}
                                            </td>
                                            <td>${sampleInfo.sampleNo}</td>
                                            <td>${sampleInfo.sampleTypeName}</td>
                                            <td>${sampleInfo.sampleName}</td>
                                            <td>
                                                <img src="<%=path%>/img/ma2.png" alt="">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box ">
    <div class="col-md-12">
        <input type="hidden" name="ipAddress" id="ipAddress" value="${ipAddress}">

        <%--<button type="button" class="btn btn-blue btn-yellow upload" style="width: 120px;height: 42px;float: left;"
                name="documentPrintAll">文书打印
        </button>--%>

        <button type="button" class="btn btn-blue btn-green upload" style="width: 120px;height: 42px;"
                name="yushiyan">预实验记录表
        </button>
        <button type="button" class="btn btn-blue btn-lang upload" name="barCodePrint" id="barCodePrint">条码打印
        </button>

    </div>
</div>

<div class="modal fade popBox smallBox" id="siteSurveyBox" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">下载打印条码程序</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label>请下载安装打印条码程序！</label>
                    <a href="<%=path%>/printDna">点此下载</a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/lib/JsBarcode/JsBarcode.all.min.js"></script>
<script>
    <%--排序--%>
    <%--
    personInfoArr = []
    $("#personInfoTbody").children().each(function (i) {
        personInfoArr.push($(this).children().eq(1).html().substring(9, 14).replace(/\b(0+)/gi, ""))
    })
    function bubbleSort(arr) {
        var len = arr.length, j;
        var temp;
        while (len > 1) {
            for (j = 0; j < len - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            len--;
        }
        return arr;
    }
    $.each(bubbleSort(personInfoArr), function (i, item) {
        $("#personInfoTbody").children().each(function (i) {
            if ($(this).children().eq(1).html().substring(9, 14).replace(/\b(0+)/gi, "") == item) {
                var newtr = $(this).clone(true)
                $("#personInfoTbody").append(newtr)
                $(this).remove()
            }
        })
        $("#personInfoTbody").children().each(function (i) {
            var Chl = $(this).children().eq(0).children()
            $(this).children().eq(0).empty().append(Chl).append(i+1)
        })
    })

    sampleInfoArr = []
    $("#sampleInfoTbody").children().each(function (i) {
        sampleInfoArr.push($(this).children().eq(1).html().substring(9).replace(/\b(0+)/gi, ""))
    })

    $.each(bubbleSort(sampleInfoArr), function (i, item) {
        $("#sampleInfoTbody").children().each(function (i) {
            if ($(this).children().eq(1).html().substring(9).replace(/\b(0+)/gi, "") == item) {
                var newtr = $(this).clone(true)
                $("#sampleInfoTbody").append(newtr)
                $(this).remove()
            }
        })
        $("#sampleInfoTbody").children().each(function (i) {
            var Chl = $(this).children().eq(0).children()
            $(this).children().eq(0).empty().append(Chl).append(i+1)
        })
    })
    --%>
    <%--排序--%>
    $("tbody").find("img").click(function () {
        if ($(this).parents("tr").find("td").eq(1).html() !== "") {
            $("#tiaomaBox").modal("show")
            $("#imgcode").JsBarcode($(this).parents("tr").find("td").eq(1).html(), {
                lineColor: '#000',
                width: 2,
                height: 50,
                margin: 15,
                displayValue: true
            });
        }

    })
    $("input[type='checkbox']").prop("checked", true);
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

    $("img[name='weituoqueren ']").on("click", function () {
        var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();
        location.href = "<%=path%>/center/acceptDoc?consignmentId=" + consignmentId;
    });

    /**
     * 入库单
     */
    <%--$("img[name='rukudan ']").on("click", function () {--%>
    <%--var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();--%>
    <%--location.href = "<%=path%>/dowmFileController/generateInboundOrder?consignmentId=" + consignmentId;--%>
    <%--});--%>

    /*$("img[name='yushiyan']").on("click", function () {
     var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();
     $.ajax({
     url: "<%=path%>/center/preAceptDocIsNull?consignmentId=" + consignmentId,
     type: "post",
     dataType: "text",
     success: function (data) {
     if (data == 1) {
     location.href = "<%=path%>/center/preAceptDoc?consignmentId=" + consignmentId;
     } else {
     if (window.confirm('所有检材均未填写预实验结果的情况,是否生成')) {
     location.href = "<%=path%>/center/preAceptDoc?consignmentId=" + consignmentId;
     }
     }
     },
     error: function (e) {
     alert(e);
     }
     });
     });*/

    /*弹出预实验页面*/
    $("button[name='yushiyan']").on("click", function () {
        $("#preExperimentTbody").html("");
        var limsSampleList = GetSample();

        if(limsSampleList.length<1){
            alert("请勾选要下载预实验记录的样本！");
            return;
        }

        for (var i = 0; i < limsSampleList.length; i++) {
            var sampleId = limsSampleList[i].sampleId;
            var sampleNo = limsSampleList[i].sampleNo;
            var sampleName = limsSampleList[i].sampleName;
            var sampleTypeName = limsSampleList[i].sampleTypeName;
            var preMethod1Result = limsSampleList[i].preMethod1Result;
            var preMethod2Result = limsSampleList[i].preMethod2Result;
            var preMethod3Result = limsSampleList[i].preMethod3Result;

            var newRowHtml = "<td><input type='hidden' name='sampleId' value='" + sampleId + "'/> <input type='hidden' name='sampleNo' value='" + sampleNo + "'/>" + sampleNo + "</td>";
             newRowHtml += "<td><input type='hidden' name='sampleName' value='" + sampleName + "'/>" + sampleName + "</td>";
            newRowHtml += "<td><input type='hidden' name='sampleTypeName' value='" + sampleTypeName + "'/>" + sampleTypeName + "</td>";

            if(preMethod3Result=="+"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod3Result' > <option value=''>请选择</option> <option value='+' selected >+</option> <option value='-' >-</option> </select> </div></td>";
            }else if (preMethod3Result=="-"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod3Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' selected>-</option> </select> </div></td>";
            }else{
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod3Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' >-</option> </select> </div></td>";
            }
            if(preMethod1Result=="+"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod1Result' > <option value=''>请选择</option> <option value='+' selected >+</option> <option value='-' >-</option> </select> </div></td>";
            }else if (preMethod1Result=="-"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod1Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' selected>-</option> </select> </div></td>";
            }else{
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod1Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' >-</option> </select> </div></td>";
            }
            if(preMethod2Result=="+"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod2Result' > <option value=''>请选择</option> <option value='+' selected >+</option> <option value='-' >-</option> </select> </div></td>";
            }else if (preMethod2Result=="-"){
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod2Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' selected>-</option> </select> </div></td>";
            }else{
                newRowHtml += "<td><div class='select'> <select class='form-control' name='preMethod2Result' > <option value=''>请选择</option> <option value='+' >+</option> <option value='-' >-</option> </select> </div></td>";
            }
            newRowHtml += "<td><input type='text' name='sampleRemark'></td>";

            $("#preExperimentTbody").append("<tr>" + newRowHtml + "</tr>");
        }

        $("#preExperiment").modal('show');

    });

    /*下载预实验记录表*/
    $("button[name='downloadPreExperiment']").on("click", function () {
        var consignmentId = $("input[name='consignmentId']").val();
        var sampleInfoDnaList = GetDownloadPreExperimentSample();

        var params = {
            "sampleInfoDnaList": sampleInfoDnaList
        };

        $.ajax({
            url: "<%=path%>/center/inspectionpreAceptDoc",
            type: "post",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(params),
            dataType: "json",
            success: function (data) {
                if (data.success || data.success == true || data.success == "true"){
                    location.href = "<%=path%>/center/downloadPreAceptDoc?consignmentId=" + consignmentId;
                }else{
                    alert("下载预实验记录表失败！");
                }
            },
            error: function (e) {
                alert(e);
            }
        });

    });

    function GetDownloadPreExperimentSample() {
        var sampleArr = new Array();
        var sampleInfo;
        if ($("tr", "#preExperimentTbody").length > 0) {
            var $sampleInfoTRArr = $("tr", "#preExperimentTbody").not(".regedTr");
            var sampleCnt = $sampleInfoTRArr.length;
            var $sampleTR;
            for (var i = 0; i < sampleCnt; i++) {
                sampleInfo = {};
                $sampleTR = $($sampleInfoTRArr.get(i));
                sampleInfo.sampleId = $("input[name='sampleId']", $sampleTR).val();
                sampleInfo.preMethod1Result = $("select[name='preMethod1Result']", $sampleTR).val();
                sampleInfo.preMethod2Result = $("select[name='preMethod2Result']", $sampleTR).val();
                sampleInfo.preMethod3Result = $("select[name='preMethod3Result']", $sampleTR).val();
                sampleInfo.sampleNo = $("input[name='sampleNo']", $sampleTR).val();
                sampleInfo.sampleName = $("input[name='sampleName']", $sampleTR).val();
                sampleInfo.sampleTypeName = $("input[name='sampleTypeName']", $sampleTR).val();
                sampleInfo.sampleRemark = $("input[name='sampleRemark']", $sampleTR).val();

                sampleArr.push(sampleInfo);
            }
        }
        return sampleArr;
    }

    function GetSample() {
        var sampleArr = new Array();
        var sampleInfo;
        var checkBox;
        if ($("tr", "#personInfoTbody").length > 0) {
            var $personInfoTRArr = $("tr", "#personInfoTbody").not(".regedTr");
            var personCnt = $personInfoTRArr.length;
            var $personTR;
            for (var i = 0; i < personCnt; i++) {
                sampleInfo = {};
                $personTR = $($personInfoTRArr.get(i));
                sampleInfo.sampleId = $("input[name='sampleId']", $personTR).val();
                sampleInfo.preMethod1Result = $("input[name='preMethod1Result']", $personTR).val();
                sampleInfo.preMethod2Result = $("input[name='preMethod2Result']", $personTR).val();
                sampleInfo.preMethod3Result = $("input[name='preMethod3Result']", $personTR).val();
                sampleInfo.sampleNo = $("input[name='sampleNo']", $personTR).val();
                sampleInfo.sampleName = $("input[name='sampleName']", $personTR).val();
                sampleInfo.sampleTypeName = $("input[name='sampleTypeName']", $personTR).val();
                checkBox = $("input[name='personInfoBox']", $personTR).is(":checked");
                if (checkBox)
                    sampleArr.push(sampleInfo);
            }
        }

        if ($("tr", "#sampleInfoTbody").length > 0) {
            var $sampleInfoTRArr = $("tr", "#sampleInfoTbody").not(".regedTr");
            var sampleCnt = $sampleInfoTRArr.length;
            var $sampleTR;
            for (var i = 0; i < sampleCnt; i++) {
                sampleInfo = {};
                $sampleTR = $($sampleInfoTRArr.get(i));
                sampleInfo.sampleId = $("input[name='sampleId']", $sampleTR).val();
                sampleInfo.preMethod1Result = $("input[name='preMethod1Result']", $sampleTR).val();
                sampleInfo.preMethod2Result = $("input[name='preMethod2Result']", $sampleTR).val();
                sampleInfo.preMethod3Result = $("input[name='preMethod3Result']", $sampleTR).val();
                sampleInfo.sampleNo = $("input[name='sampleNo']", $sampleTR).val();
                sampleInfo.sampleName = $("input[name='sampleName']", $sampleTR).val();
                sampleInfo.sampleTypeName = $("input[name='sampleTypeName']", $sampleTR).val();
                sampleInfo.flag = "sample";
                checkBox = $("input[name='sampleInfoBox']", $sampleTR).is(":checked");
                if (checkBox)
                    sampleArr.push(sampleInfo);
            }
        }
        return sampleArr;
    }


    $("img[name='circulationRecord']").on("click", function () {
        var consignmentId = $("input[name='consignmentId']", $(this).parent()).val();
        $.ajax({
            url: "<%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == 1) {
                    location.href = "<%=path%>/center/circulationRecord?consignmentId=" + consignmentId;
                } else {
                    alert("所送现场检材均已取走！");
                }
            },
            error: function (e) {
                alert(e);
            }
        });
    });

    $("button[name='documentPrintAll']").on("click", function () {
        var consignmentId = $("input[name='consignmentId']").val();

        $.ajax({
            url: "<%=path%>/center/preAceptDocIsNull?consignmentId=" + consignmentId,
            type: "post",
            dataType: "text",
            success: function (data) {
                if (data == 1) {
                    $.ajax({
                        url: "<%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
                        type: "post",
                        dataType: "text",
                        success: function (data) {
                            if (data == 1) {
                                $.ajax({
                                    url: "<%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId,
                                    type: "post",
                                    dataType: "json",
                                    success: function (data) {
                                        location.href = '<%=path%>/center/downloadFile';
                                    },
                                    error: function (e) {
                                        alert("打印失败！");
                                    }
                                });
                            } else {
                                alert("所送现场检材均已取走！");
                                $.ajax({
                                    url: "<%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId,
                                    type: "post",
                                    dataType: "json",
                                    success: function (data) {
                                        location.href = '<%=path%>/center/downloadFile';
                                    },
                                    error: function (e) {
                                        alert(e);
                                    }
                                });
                            }
                        },
                        error: function (e) {
                            alert(e);
                        }
                    });
                } else {
                    /*if (window.confirm('所有检材均未填写预实验结果的情况,是否生成')) {
                     $.ajax({
                     url: "
                    <%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
                     type: "post",
                     dataType: "text",
                     success: function (data) {
                     if (data == 1) {
                     $.ajax({
                     url: "
                    <%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId,
                     type: "post",
                     dataType: "json",
                     success: function (data) {
                     location.href = '
                    <%=path%>/center/downloadFile';
                     },
                     error: function (e) {
                     alert(e);
                     }
                     });
                     } else {
                     alert("所送现场检材均已取走！");
                     $.ajax({
                     url: "
                    <%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId,
                     type: "post",
                     dataType: "json",
                     success: function (data) {
                     location.href = '
                    <%=path%>/center/downloadFile';
                     },
                     error: function (e) {
                     alert(e);
                     }
                     });
                     }
                     },
                     error: function (e) {
                     alert(e);
                     }
                     });
                     } else {*/
                    $.ajax({
                        url: "<%=path%>/center/circulationRecordIsRtain?consignmentId=" + consignmentId,
                        type: "post",
                        dataType: "text",
                        success: function (data) {
                            if (data == 1) {
                                $.ajax({
                                    url: "<%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId + "&preAceptFlag=" + "1",
                                    type: "post",
                                    dataType: "json",
                                    success: function (data) {
                                        location.href = '<%=path%>/center/downloadFile';
                                    },
                                    error: function (e) {
                                        alert(e);
                                    }
                                });
                            } else {
                                alert("所送现场检材均已取走！");
                                $.ajax({
                                    url: "<%=path%>/center/printAllAceptDoc?consignmentId=" + consignmentId + "&preAceptFlag=" + "1",
                                    type: "post",
                                    dataType: "json",
                                    success: function (data) {
                                        location.href = '<%=path%>/center/downloadFile';
                                    },
                                    error: function (e) {
                                        alert(e);
                                    }
                                });
                            }
                        },
                        error: function (e) {
                            alert(e);
                        }
                    });
                    /*}*/
                }
            },
            error: function (e) {
                alert(e);
            }
        });

    });

    $("button[name='barCodePrint']").on("click", function () {
        var limsSampleInfoDnaList = GetSampleInfo();
        if (limsSampleInfoDnaList.length < 1) {
            alert("请勾选需要打印的样本!");
            return;
        }

        $("#choiceNo").modal("show");
    });

    function GetSampleInfo() {
        var sampleArr = new Array();

        var sampleInfo;
        var checkBox;

        if ($("tr", "#personInfoTbody").length > 0) {
            var $personInfoTRArr = $("tr", "#personInfoTbody").not(".regedTr");
            var personCnt = $personInfoTRArr.length;
            var $personTR;
            for (var i = 0; i < personCnt; i++) {
                sampleInfo = {};
                $personTR = $($personInfoTRArr.get(i));
                sampleInfo.sampleNo = $("input[name='sampleNo']", $personTR).val();
                sampleInfo.flag = "person";
                checkBox = $("input[name='personInfoBox']", $personTR).is(":checked");
                if (checkBox)
                    sampleArr.push(sampleInfo);
            }
        }

        if ($("tr", "#sampleInfoTbody").length > 0) {
            var $sampleInfoTRArr = $("tr", "#sampleInfoTbody").not(".regedTr");
            var sampleCnt = $sampleInfoTRArr.length;
            var $sampleTR;
            for (var i = 0; i < sampleCnt; i++) {
                sampleInfo = {};
                $sampleTR = $($sampleInfoTRArr.get(i));
                sampleInfo.sampleNo = $("input[name='sampleNo']", $sampleTR).val();
                sampleInfo.flag = "sample";
                checkBox = $("input[name='sampleInfoBox']", $sampleTR).is(":checked");
                if (checkBox)
                    sampleArr.push(sampleInfo);
            }
        }
        return sampleArr;
    }
    //    打印份数
    $("#choiceNo").find(".col-md-1").children().click(function () {
        var num = Number($(this).parent().siblings().find("input[type='text']").val())
        if ($(this).html() == "+") {
            num += 1
        } else {
            if (num > 1) {
                num -= 1
            }
        }
        $(this).parent().siblings().find("input").val(num)
    })
    $("#choiceNo").find(".btn-yellow").click(function () {
        if ($("#choiceNo").find("input[type='checkbox']:checked").length == $("#choiceNo").find(".modal-body").children().length) {
            $("#choiceNo").find("input[type='checkbox']").prop("checked", false)
        } else {
            $("#choiceNo").find("input[type='checkbox']").prop("checked", true)
        }

    })
    var index = 0;
    $("button[name='savePrint']").on("click", function () {
        index = 0;
        // $('#savePrint').attr("disabled","disabled");
        printSample();
    });


    function printSample() {

        var limsSampleInfoDnaList = GetSampleInfo();

        var samplePrintCnt = $("input[name='sampleCnt']").val();
        var personPrintCnt = $("input[name='personCnt']").val();

        if (index >= limsSampleInfoDnaList.length) {
            return;
        }
        var page;
        if (limsSampleInfoDnaList[index].flag == "person") {
            page = personPrintCnt;
        } else if (limsSampleInfoDnaList[index].flag == "sample") {
            page = samplePrintCnt;
        }

//        var printServerUrl = "http://127.0.0.1:927/PrintDNA?page=" + page + "&personno=" + limsSampleInfoDnaList[index].sampleNo;

        var printServerUrl = "http://" + $("#ipAddress").val() + ":927/PrintDNA?page=" + page + "&personno=" + limsSampleInfoDnaList[index].sampleNo;


//        $.ajax({
//            type: "GET",
//            async: false,
//            url: printServerUrl,
//            timeout: 3000,
//            dataType: "jsonp",
//            success: function (data) {
//                if (data.status.msg != "") {
//                    alert("打印失败！原因：" + data.msg);
//                } else {
//                    index++;
//                    printSample();
//                    setTimeout(function(){
//                        $("#choiceNo").modal("hide");
//                    },3000)
//                }
//            },
//            error: function (e) {
//                //无法建立通信时，弹出下载安装插件的窗口
//                $("#siteSurveyBox").modal("show");
//            }
//        });


        $.ajax({
            type: "GET",
            url: printServerUrl + "&callback=1",
            timeout: 3000,
            success: function (data) {
                //debugger;
                if (data.status.msg != "Success") {
                    alert("打印失败！原因：" + data.msg);
                } else {
                    index++;
                    printSample();
                    setTimeout(function () {
                        $("#choiceNo").modal("hide");
                    }, 3000)
                }
            },
            error: function (e) {
                //无法建立通信时，弹出下载安装插件的窗口
                $("#siteSurveyBox").modal("show");
            }
        });

    }

</script>
</body>

</html>