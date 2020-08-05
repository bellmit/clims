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
        .btn-box {
            bottom: 0px;
            width: 100%;
            padding: 10px 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            margin-left: -10px !important;
            margin-top: 0px !important;
        }

        .btn-box label {
            color: #296fff;
        }

        .btn-box input {
            width: 200px;
            display: inline-block;
            margin: 0 10px;
            color: #296fff;
        }

        .btn-box button {
            margin-left: 10px;
        }
        td .checkbox-inline{
            margin-top: -22px;
        }
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">提取记录</a></li>
    <li class="active">当前页</li>
</ol>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>添加检材</div>
            </div>
            <div class="panel-body" style="padding: 30px">
                <div class="row">
                    <div class="col-md-4 nopadding">
                        <div class="form-group">
                            <label class="col-md-4 nopadding" style="line-height: 28px">扫描案件条码号</label>

                            <div class="col-md-7 nopadding">
                                <input type="text" name="caseNo" id="caseNo" class="form-control" value="" placeholder="请扫描案件条码号">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 nopadding">
                        <div class="form-group">
                            <label class="col-md-4 nopadding" style="line-height: 28px">扫描检材条码号</label>

                            <div class="col-md-7 nopadding">
                                <input type="text" name="sampleNo" id="sampleNo" class="form-control" value="" placeholder="请扫描检材条码号">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-1 nopadding">
                        <button class="btn btn-blue" id="addInformant">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading blue">
                <div>常规提取(<span id="cgtq">0</span>)</div>
                <%--<input type="hidden" id="cgtqId" value="0">--%>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input allcheck" type="checkbox" name="checkAll">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th>检材类型</th>

                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="extracts" id="routine_extract">

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
                <div>精斑提取(<span id="jbtq">0</span>)</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" name="checkAll">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th>检材类型</th>

                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="extracts" id="spot_extract">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row Modular">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading red">
                <div>特殊提取(<span id="tstq">0</span>)</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <th>
                        <label class="custom-control checkbox-inline">
                            <input class="custom-control-input" type="checkbox" name="checkAll">
                            <span class="custom-control-label" style="font-weight: 600">全选</span>
                        </label>
                    </th>
                    <th>检材编号</th>
                    <th>检材名称</th>
                    <th>检材类型</th>

                    <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="extracts" id="special_extract">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row Modular" style="margin-bottom: 60px;">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>其他提取(<span id="qttq">0</span>)</div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>
                            <label class="custom-control checkbox-inline">
                                <input class="custom-control-input" type="checkbox" name="checkAll">
                                <span class="custom-control-label" style="font-weight: 600">全选</span>
                            </label>
                        </th>
                        <th>检材编号</th>
                        <th>检材名称</th>
                        <th>检材类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="extracts" id="other_extract">

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12">
        <button id="startBtn" class="btn btn-blue btn-lang">开始实验</button>
    </div>
</div>


<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {


        $("#importFile").on("click", function () {
            $("#csvFile").click();
        })
        $("#csvFile").change(function (e) {
            if (checkFileSuffix($("#csvFile").val()) == false) {
                return false;
            }
            var name = e.currentTarget.files[0].name;
            var index = name.lastIndexOf('.');

            $.ajaxFileUpload({
                cache: false,
                url: "<%=path%>/center/uploadCsvFile",
                secureuri: false,
                fileElementId: 'csvFile',
                dataType: 'json',
                success: function (data) {
                    $("input[name='boardNo']").val(name.substring(0, index))
                    if (data.success || data.success == true || data.success == "true") {
                        var sampleInfoDnaList = data.sampleInfoDnaList;
                        if (sampleInfoDnaList.length > 0) {
                            var sampleInfo;
                            for (var i = 0; i < sampleInfoDnaList.length; i++) {
                                sampleInfo = sampleInfoDnaList[i];
                                var sampleTr = '<tr>'

                                sampleTr += '</tr>'

                                $("#samplelist_tbody").append(sampleTr);
                            }
                        }
                    } else {
                        alert(data.errMsg)
                    }
                },
                error: function (data, status, e) {
                    alert(e);
                }
            });
        })

        function checkFileSuffix(str) {
            //检查上传文件后缀
            var photoExt = str.substr(str.lastIndexOf(".")).toLowerCase();

            if (photoExt == '.csv') {
                return true;
            } else {
                alert("请上传后缀名为csv文件!");
                $("#textfield").val("");
                return false;
            }
        }

        var positionArr = [
            "A01", "B01", "C01", "D01", "E01", "F01", "G01", "H01",
            "A02", "B02", "C02", "D02", "E02", "F02", "G02", "H02",
            "A03", "B03", "C03", "D03", "E03", "F03", "G03", "H03",
            "A04", "B04", "C04", "D04", "E04", "F04", "G04", "H04",
            "A05", "B05", "C05", "D05", "E05", "F05", "G05", "H05",
            "A06", "B06", "C06", "D06", "E06", "F06", "G06", "H06",
            "A07", "B07", "C07", "D07", "E07", "F07", "G07", "H07",
            "A08", "B08", "C08", "D08", "E08", "F08", "G08", "H08",
            "A09", "B09", "C09", "D09", "E09", "F09", "G09", "H09",
            "A10", "B10", "C10", "D10", "E10", "F10", "G10", "H10",
            "A11", "B11", "C11", "D11", "E11", "F11", "G11", "H11",
            "A12", "B12", "C12", "D12", "E12", "F12", "G12", "H12"
        ];
        var inputCaseNo = document.getElementById("caseNo");
        inputCaseNo.addEventListener("keyup", function(event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                document.getElementById("addInformant").click();
            }
        });
        var inputSampleNo = document.getElementById("sampleNo");
        inputSampleNo.addEventListener("keyup", function(event) {
            event.preventDefault();
            if (event.keyCode === 13) {
                document.getElementById("addInformant").click();
            }
        });

        $("#addInformant").on("click", function () {
            var caseNo = $("#caseNo").val();
            var sampleNo = $("#sampleNo").val();
            if (caseNo == "" && sampleNo == "") {
                alert("请输入案件编号或检材编号！");
            }
            $.ajax({
                url: "<%=path%>/center/getExtractSampleList?caseNo=" + caseNo + "&sampleNo=" + sampleNo,
                type: "post",
                dataType: "text",
                success: function (data) {
                    var cgtq=0;
                    var jbtq=0;
                    var tstq=0;
                    var qttq=0;
                    if (data != null) {
                        for (var i = 0; i < data.length; i++) {
                            var sampleInfo = JSON.parse(data)[i];

                            //新添被鉴定人
                            if (sampleInfo.sampleType == "01" || sampleInfo.sampleType == "03" || sampleInfo.sampleType == "04" || sampleInfo.sampleType == "08" || sampleInfo.sampleType == "21") {
                                cgtq++;
                                console.log(cgtq)
                                $("#cgtq").text(cgtq)
                                var sampleTr = '<tr>'
                                sampleTr += '<td>\n' +
                                        '                            <label class="custom-control checkbox-inline">\n' +
                                        '                                <input onclick="rem2()" class="custom-control-input" type="checkbox" name="box" checked="true"><span\n' +
                                        '                                    class="custom-control-label"></span>\n' +
                                        '                            </label>\n' +
                                        '                        </td>'
                                if (sampleInfo.sampleNo == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleNo" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                                }
                                if (sampleInfo.sampleName == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleName" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                                }
                                if (sampleInfo.sampleType == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleType" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                                }
                                sampleTr += '<td>'
                                sampleTr += '<button class="btn-icon btn-icon-red btn-icon-shanchu-red remove" onclick="rem(this)">删除</button>'
                                sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
                                sampleTr += '</tr>'
                                $("#routine_extract").append(sampleTr)
                            } else if (sampleInfo.sampleType == '02') {
                                jbtq++;
                                console.log(cgtq)
                                $("#jbtq").text(jbtq)
                                var sampleTr = '<tr>'
                                sampleTr += '<td>\n' +
                                        '                            <label class="custom-control checkbox-inline">\n' +
                                        '                                <input onclick="rem2()" class="custom-control-input" type="checkbox" name="box"><span\n' +
                                        '                                    class="custom-control-label"></span>\n' +
                                        '                            </label>\n' +
                                        '                        </td>'
                                if (sampleInfo.sampleNo == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleNo" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                                }
                                if (sampleInfo.sampleName == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleName" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                                }
                                if (sampleInfo.sampleType == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleType" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                                }
                                sampleTr += '<td>'
                                sampleTr += '<button class="btn-icon btn-icon-red btn-icon-shanchu-red remove" onclick="rem(this)">删除</button>'
                                sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
                                sampleTr += '</tr>'
                                $("#spot_extract").append(sampleTr)
                            } else if (sampleInfo.sampleType == '05' || sampleInfo.sampleType == '06' || sampleInfo.sampleType == '07' || sampleInfo.sampleType == '09') {
                                tstq++;
                                console.log(tstq)
                                $("#tstq").text(tstq)
                                var sampleTr = '<tr>'
                                sampleTr += '<td>\n' +
                                        '                            <label class="custom-control checkbox-inline">\n' +
                                        '                                <input onclick="rem2()" class="custom-control-input" type="checkbox" name="box"><span\n' +
                                        '                                    class="custom-control-label"></span>\n' +
                                        '                            </label>\n' +
                                        '                        </td>'
                                if (sampleInfo.sampleNo == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleNo" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                                }
                                if (sampleInfo.sampleName == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleName" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                                }
                                if (sampleInfo.sampleType == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleType" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                                }
                                sampleTr += '<td>'
                                sampleTr += '<button class="btn-icon btn-icon-red btn-icon-shanchu-red remove" onclick="rem(this)">删除</button>'
                                sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
                                sampleTr += '</tr>'
                                $("#special_extract").append(sampleTr)
                            } else if (sampleInfo.sampleType == '99') {
                                qttq++;
                                console.log(qttq)
                                $("#qttq").text(qttq)
                                var sampleTr = '<tr>'
                                sampleTr += '<td>\n' +
                                        '                            <label class="custom-control checkbox-inline">\n' +
                                        '                                <input onclick="rem2()" class="custom-control-input" type="checkbox" name="box" ><span\n' +
                                        '                                    class="custom-control-label"></span>\n' +
                                        '                            </label>\n' +
                                        '                        </td>'
                                if (sampleInfo.sampleNo == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleNo" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                                }
                                if (sampleInfo.sampleName == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleName" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                                }
                                if (sampleInfo.sampleType == null) {
                                    sampleTr += '<td><input type="hidden" name="sampleType" value=""/></td>'
                                } else {
                                    sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                                }
                                sampleTr += '<td>'
                                sampleTr += '<button class="btn-icon btn-icon-red btn-icon-shanchu-red remove" onclick="rem(this)">删除</button>'
                                sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
                                sampleTr += '</tr>'
                                $("#other_extract").append(sampleTr)
                            }

                        }
//                        var dd=$("#routine_extract").length
//                        console.log(dd)
//                        $("#cgtq").text(dd)
//                        console.log($("#routine_extract").length)
//                        console.log($("#cgtq").text())
                    }

                },
                error: function (e) {
                    alert(e);
                }
            });

            $("#caseNo").val("");
            $("#sampleNo").val("");
        });

        $("#startBtn").on("click", function () {
            <%--location.href = "<%=path%>/center/manualExtractionExperiment?inspectionType=0&extractionMode=0";--%>

            var trList = $(".extracts").children("tr");
            if (trList.length == 0) {
                alert("请添加检材！");
                return false;
            }

            var sampleArr = new Array();
            var checkCount = 0;
            var $sampleTR = $("tr", ".extracts");
            var sampleCnt = $sampleTR.length;
            var sample;
            for (var i = 0; i < sampleCnt; i++) {
                sample = {};
                var checkBox = $("input[name='box']", $sampleTR.get(i)).is(":checked");
                if (checkBox) {
                    checkCount++;
                    sample.sampleId = $("input[name='sampleId']", $sampleTR.get(i)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $sampleTR.get(i)).val();
                    sample.sampleName = $("input[name='sampleName']", $sampleTR.get(i)).val();
                    sample.sampleType = $("input[name='sampleType']", $sampleTR.get(i)).val();
                    // sample.position = $("input[name='position']", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                }
            }

            if (checkCount <= 0) {
                alert("至少选中一个检材！");
                return false;
            }

            // var boardNo = $("#boardNo").val();
            // if (boardNo == null || boardNo == "") {
            //     alert("请输入板号！");
            //     $("#boardNo").focus();
            //     return false;
            // }
            $.ajax({
                url: "<%=path%>/center/saveExtractioListSession?inspectionType=0&extractionMode=0",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sampleArr),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                    <%--location.href = "<%=path%>/center/manualExtractionExperiment";--%>
                        location.href = "<%=path%>/handwork/manualExtractionExperiment";
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });
        })

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
        // $("tbody").find("input[type='checkbox']").change(function () {
        //     if ($(this).parents("tbody").find("input[type='checkbox']:checked").length == $(this).parents("tbody").find("input[type='checkbox']").length) {
        //         $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", true)
        //     } else {
        //         $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", false)
        //     }
        // })
    })
    function rem2(){
        $("tbody").find("input[type='checkbox']").change(function () {
            if ($(this).parents("tbody").find("input[type='checkbox']:checked").length == $(this).parents("tbody").find("input[type='checkbox']").length) {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", true)
            } else {
                $(this).parents("tbody").prev().find("input[type='checkbox']").prop("checked", false)
            }
        })
    }
    function rem(obj){
        var tbody = obj.parentNode.parentNode.parentNode
        var tbodyId = $(tbody).attr("id")
        if(tbodyId=="routine_extract") {
            var trList = $("#routine_extract").children("tr");
            $("#cgtq").text(trList.length-1)
        }else if(tbodyId == "spot_extract"){
            var trList = $("#spot_extract").children("tr");
            $("#jbtq").text(trList.length-1)
        }else if(tbodyId == "special_extract"){
            var trList = $("#special_extract").children("tr");
            $("#tstq").text(trList.length-1)
        }else if(tbodyId == "other_extract"){
            var trList = $("#other_extract").children("tr");
            $("#qttq").text(trList.length-1)
        }
        obj.parentNode.parentNode.parentNode.removeChild(obj.parentNode.parentNode);

    }
</script>
</body>

</html>