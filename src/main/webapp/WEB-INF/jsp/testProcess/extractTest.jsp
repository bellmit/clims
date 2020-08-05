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
        .Modular{
            padding-bottom: 65px;
        }
        .btn-box {
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px;
            text-align: left;
            box-shadow: 0px 0px 10px 5px #ebebeb;
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
        .btn-box > div:nth-child(1) > span{
            display: inline-block;
            font-size: 15px;
            margin-left: 15px;
        }
        .btn-box > div:nth-child(1) span > span {
            font-size: 20px !important;
        }
        .btn-box > div:nth-child(1) > span::before {
            content: "";
            float: left;
            width: 15px;
            height: 15px;
            border-radius: 50%;
            margin-right: 10px;
            margin-top: 15px;
        }

        .btn-box > div:nth-child(1) > span:nth-child(1)::before {
            background: #f3504a;
        }

        .btn-box > div:nth-child(1) > span:nth-child(2)::before {
            background: #ff9200;
        }

        .btn-box > div:nth-child(1) > span:nth-child(1) {
            color: #f3504a;
        }

        .btn-box > div:nth-child(1) > span:nth-child(2) {
            color: #ff9200;
        }
    </style>
</head>
<body>
<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="#">提取记录</a></li>
    <li class="active">当前页</li>
</ol>
<form action="<%=path%>/center/uploadCsvFile"  enctype="multipart/form-data" method="post" id="form">
    <div class="row Modular ">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading yellow">
                    <div>检材列表</div>
                    <input type="file" name="csvFile" id="csvFile" class="hide" />
                    <button class="btn btn-yellow" type="button" id="importFile">导入工作站文件</button>
                </div>
                <div class="panel-body">
                    <table class="table table-hover table-bordered bigTable">
                        <thead>
                        <tr>
                            <th style="width: 150px;">
                                <label class="custom-control checkbox-inline" style="margin-top: -22px">
                                    <input class="custom-control-input" type="checkbox" name="checkAll" id="allChecked"
                                           checked>
                                    <span class="custom-control-label"></span>
                                </label>全选
                            </th>
                            <th>检材编号</th>
                            <th>检材名称</th>
                            <th>检材类型</th>
                            <th>板孔位置</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody id="samplelist_tbody">
                        <c:forEach items="${sampleInfoDnaList}" var="list" varStatus="s">
                            <tr>
                                <td>
                                    <label class="custom-control checkbox-inline" style="margin-top: -22px">
                                        <input class="custom-control-input" type="checkbox" name="box" checked>
                                        <span class="custom-control-label"></span>
                                    </label>
                                </td>
                                <td>
                                    <input type="hidden" name="sampleNo" value="${list.sampleNo}">${list.sampleNo}
                                </td>
                                <td>
                                    <input type="hidden" name="sampleName" value="${list.sampleName}">${list.sampleName}
                                </td>
                                <td>
                                    <input type="hidden" name="sampleType" value="${list.sampleType}">${list.sampleTypeName}
                                </td>
                                <td>
                                    <input type="text" name="position" value="${list.position}">
                                </td>
                                <td>
                                    <input type="hidden" name="sampleId" value="${list.sampleId}">
                                    <button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove" name="removeBtn">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<div class="row btn-box">
    <div class="col-md-6">
        <span>检材总数:
            <span id="count" style="font-size: 15px !important;">${sampleCount}</span>个
        </span>
        <span>检材类型:
            <%--<span id="type">${typeCount}</span>种--%>
            <c:forEach items="${map}" var="typeList">
                <span style="font-size: 15px !important;" name="key" >${typeList.key}</span>
                <span style="font-size: 15px !important;">:</span>
                <span style="font-size: 15px !important;" name="${typeList.key}">${typeList.value}</span>
                <span style="font-size: 15px !important;">个</span>
            </c:forEach>
            <input type="hidden" id="type" value="${typeList}">
        </span>
    </div>
    <div class="col-md-6" style="text-align: right">
        <label>工作站板号</label>
        <input type="text" id="boardNo" name="boardNo" value="${boardNo}" class="form-control" placeholder="请输入板号">
        <button type="button" class="btn btn-blue btn-lang" type="button" id="startBtn">开始实验</button>
    </div>
</div>


<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {

        $(".remove").click(function(){
            var sampleType = $(this).parents("tr").find("input[name='sampleType']").val()
            var spanArray = [];
            for(var i=0;i<$('span[name="key"]').length;i++){
                spanArray.push($('span[name="key"]').eq(i).text())
            }
            for (var i = 0; i < spanArray.length; i++){
                var str = spanArray[i];
                if(sampleType == str){
                    $("span[name=" + str + "]").text($("span[name=" + str + "]").text()-1)
                }
            }
            $(this).parents("tr").remove()
            $("#count").html(Number($("#count").html())-1)

        })

        $("#importFile").on("click", function () {
            $("#csvFile").click();
        })
        $("#csvFile").change(function (e) {
            $("#shadow").modal("show")
            if (checkFileSuffix($("#csvFile").val()) == false) {
                return false;
            }

            $('#form').submit();
            /*var name = e.currentTarget.files[0].name;
            var index = name.lastIndexOf('.');
            var count = 0;
            var str = [];
            $.ajaxFileUpload({
                cache: false,
                url: "<%=path%>/center/uploadCsvFile",
                secureuri: false,
                fileElementId: 'csvFile',
                dataType: 'json',
                success: function (data) {
                    $("#shadow").modal("hidden")
                    $("input[name='boardNo']").val(name.substring(0, index))
                    if (data.success || data.success == true || data.success == "true") {
                        var sampleInfoDnaList = data.sampleInfoDnaList;
                        if (sampleInfoDnaList.length > 0) {
                            var sampleInfo;
                            for (var i = 0; i < sampleInfoDnaList.length; i++) {
                                for (var j = 0; j < sampleInfoDnaList[i].length; j++) {
                                    sampleInfo = sampleInfoDnaList[i][j];
                                    if (sampleInfo != undefined) {
                                        var sampleTr = '<tr>'
                                        sampleTr += '<td><label class="custom-control checkbox-inline" style="margin-top: -22px"><input class="custom-control-input" type="checkbox" name="box" checked><span class="custom-control-label"></span></label></td>'
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
                                            //判断检材类型个数
                                            if (str.includes(sampleInfo.sampleType)) {
                                                count = count;
                                                alert(count)
                                            }else {
                                                str.push(sampleInfo.sampleType);
                                                count ++ ;
                                                alert(str)
                                            }

                                        }
                                        sampleTr += '<td><input type="text" name="position" value="' + sampleInfo.position + '"/></td>'
                                        sampleTr += '<td>'
                                        sampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                                        sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
                                        sampleTr += '</tr>'
                                        $("#samplelist_tbody").append(sampleTr);
                                    }
                                }
                            }
                        }
                        //显示检材数
                        $('#count').html(sampleInfoDnaList.length);
                        //显示检材种类数
                        $('#type').html(count);
                    } else {
                        alert(data.errMsg)
                    }
                },
                error: function (data, status, e) {
                    alert(e);
                }
            });*/
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
                        var count = 0;
                        for (var i = 0; i < data.length; i++) {
                            count = (i % 95) - 1;

                            var sampleInfo = JSON.parse(data)[i];
                            console.log(sampleInfo);
                            //新添被鉴定人
                            var sampleTr = '<tr>'
                            sampleTr += '<td><label class="custom-control checkbox-inline" style="margin-top: -22px"><input class="custom-control-input" type="checkbox" name="box" checked><span class="custom-control-label"></span></label></td>'
                            sampleTr += '<td>' + sampleInfo.sampleNo + '<input type="hidden" name="sampleNo" value="' + sampleInfo.sampleNo + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleName + '<input type="hidden" name="sampleName" value="' + sampleInfo.sampleName + '"/></td>'
                            sampleTr += '<td>' + sampleInfo.sampleTypeName + '<input type="hidden" name="sampleType" value="' + sampleInfo.sampleType + '"/></td>'
                            if (i > 95) {
                                count = (i % 95) - 1;
                                sampleTr += '<td><input type="text" name="position" value="' + positionArr[count] + '"/></td>'
                            } else {
                                sampleTr += '<td><input type="text" name="position" value="' + positionArr[i] + '"/></td>'
                            }
                            sampleTr += '<td>'
                            sampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
                            sampleTr += '<input type="hidden" name="sampleId" value="' + sampleInfo.sampleId + '"/></td>'
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

        $("#startBtn").on("click", function () {

            var trList = $("#samplelist_tbody").children("tr");
            if (trList.length == 0) {
                alert("请添加检材！");
                return false;
            }

            var sampleArr = new Array();
            var checkCount = 0;
            var $sampleTR = $("tr", "#samplelist_tbody");
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
                    sample.position = $("input[name='position']", $sampleTR.get(i)).val();

                    sampleArr.push(sample);
                }
            }

            if (checkCount <= 0) {
                alert("至少选中一个检材！");
                return false;
            }

            var boardNo = $("#boardNo").val();
            if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                $("#boardNo").focus();
                return false;
            }
            $.ajax({
                url: "<%=path%>/center/saveExtractioListSession?inspectionType=0&extractionMode=1",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(sampleArr),
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {
                        location.href = "<%=path%>/center/extractionExperiment?boardNo=" + encodeURI(encodeURI(boardNo));
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