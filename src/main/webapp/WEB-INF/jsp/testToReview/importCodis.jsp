<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../linkCss.jsp" %>
    <style>
        .col-lg-12{
            padding: 0;
        }
        #main-content{
            padding: 0 15px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<section id="main-content">
    <section class="wrapper">
        <div id="wrapper-content">
            <!-- BEGIN ROW  -->
            <div class="row">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span class="label label-primary">导入CODIS</span>
                               <span class="tools pull-right">
                            </span>
                        </header>
                        <div class="panel-body">
                            <form class="form-horizontal tasi-form" method="get">
                                <div class="form-group">
                                    <div class="col-sm-3">
                                        <label class="radio-inline" style="margin-left: 20% !important;">基因类型:</label>
                                        <label class="radio-inline" style="margin-left: 10px !important;">
                                            <input type="radio" class="" value="0" name="realTypes" checked >STR
                                        </label>
                                        <label class="radio-inline" style="margin-left: 10px !important;">
                                            <input type="radio" class="" value="1" name="realTypes">YSTR
                                        </label>
                                    </div>
                                    <label class="col-sm-1 control-label"><strong>试剂盒</strong></label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="reagentSelect" name="reagent">
                                            <option value="">请选择...</option>
                                            <c:forEach items="${panels}" var="list">
                                                <option value="${list.panelname}">${list.panelname}</option>
                                            </c:forEach>
                                           <%-- <option value="Identifiler Plus">Identifiler Plus</option>
                                            <option value="GlobalFiler">GlobalFiler</option>
                                            <option value="YFILER PLUS">YFILER PLUS</option>--%>
                                        </select>
                                    </div>
                                    <label class="col-sm-1 control-label" style="text-align: right;"><strong>CODIS文件</strong></label>
                                    <div class="col-sm-3">
                                        <input type="file" name="codisFile" id="codisFile" class="hide"  multiple="multiple" />
                                        <input type="text" id="codisFileTxt" class="form-control" readonly="readonly"/>
                                    </div>
                                    <div class="col-sm-2">
                                        <button class="btn btn-info" type="button" id="browserCodisBtn"><i class="fa  fa-folder-open"></i> 浏览...</button>
                                        <button class="btn btn-primary" type="button" id="importCodisBtn"><i class="fa fa-download"></i> 导入</button>
                                    </div>
                                </div>
                                <div class="form-group">
                             <%--       <label class="col-sm-2 control-label"><strong>板号</strong></label>
                                    <div class="col-sm-2">
                                        <input type="txt" name="boardNo" class="form-control">
                                    </div>--%>

                                    <div id="importResultDiv" class="col-sm-3 mt5 hide radio-inline">
                                        <span class="label label-info" id="sampleCountInFile">总数（0）</span>
                                        <span class="label label-success" id="succeedCountInFile">成功（0）</span>
                                        <span class="label label-warning" id="failedCountInFile">失败（0）</span>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </section>
                </div>
            </div>
            <!-- END ROW  -->
            <!-- BEGIN ROW  -->
            <div id="succeedDiv" class="row hide">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span class="label label-primary">导入成功列表</span>
                           <span class="tools pull-right">
                           </span>
                        </header>
                        <div class="panel-body">
                            <div class="space15" style="height: 5px;"></div>
                            <table class="table table-striped table-advance table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>检材编号</th>
                                    <th>查看基因型</th>
                                </tr>
                                </thead>
                                <tbody id="succeedListTable">

                                </tbody>
                            </table>
                        </div>
                    </section>
                </div>
            </div>
            <!-- END ROW  -->

            <!-- BEGIN ROW  -->
            <div id="failedDiv" class="row hide">
                <div class="col-lg-12">
                    <section class="panel">
                        <header class="panel-heading">
                            <span class="label label-primary">导入失败列表</span>
                           <span class="tools pull-right">
                           </span>
                        </header>
                        <div class="panel-body">
                            <div class="space15" style="height: 5px;"></div>
                            <table class="table table-striped table-advance table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>检材编号</th>
                                    <th>导入失败原因</th>
                                </tr>
                                </thead>
                                <tbody id="failedListTable">

                                </tbody>
                            </table>
                        </div>
                    </section>
                </div>
            </div>


            <div class="modal fade" id="GeneDetailModal" aria-hidden="true" data-backdrop="static" data-keyboard="false">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">
                                基因型信息
                            </h4>
                        </div>
                        <div class="modal-body">
                            <div class="space15" style="height: 5px;"></div>
                            <table class="table table-striped table-advance table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>基因座</th>
                                    <th>基因分型</th>
                                </tr>
                                </thead>
                                <tbody id="geneDetailTable">
                                </tbody>
                            </table>
                        </div>
                        <div class="modal-footer">
                            <i class="fa fa-hand-o-right"></i>
                            <button data-dismiss="modal"  class="btn btn-default" type="button" id="FinishedBtn"><i class="fa fa-times"></i> 关闭</button>
                        </div>
                    </div>
                </div>
            </div>
</div>
</section>
</section>
<!-- BEGIN JS -->
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/ajaxfileupload.js" ></script><!-- BASIC JS LIABRARY -->
<script>
    $(function() {
        /*全局回显内容*/
        var CodisSucceedList;
        var CodisFailedList;

        if(CodisSucceedList){
            console.log(CodisSucceedList);
            $("#succeedListTable").html(CodisSucceedList)
        }
        if (CodisFailedList){
            console.log(CodisFailedList);
            $("#failedListTable").html(CodisFailedList);
        }

        'use strict';

        function importCodisFtn() {

            var  reagentName = $("#reagentSelect").val();

            var  boardNo = $("input[name=boardNo]").val();

            var  types = $("input[name=realTypes]:checked").val();
            if (reagentName == null || reagentName == "") {
                alert("请选择试剂盒！");
                return false;
            }

         /*   if (boardNo == null || boardNo == "") {
                alert("请输入板号！");
                return false;
            }*/
            $.ajaxFileUpload({
                cache:false,
                url:"<%=path%>/center/uploadCodis?reagentName=" + encodeURI(encodeURI(reagentName)) + "&boardNo=" + encodeURI(encodeURI(boardNo)) + "&types=" +  encodeURI(encodeURI(types)),
                secureuri:false,
                fileElementId:'codisFile',
                dataType: 'json',
                success: function (data) {
                    if(data.success || data.success == true || data.success == "true") {
                        $("#sampleCountInFile").text("总数（" + data.sampleCountInFile + "）");
                        $("#succeedCountInFile").text("成功（" + data.succeedCountInFile + "）");
                        $("#failedCountInFile").text("失败（" + data.failedCountInFile + "）");
                        $("#importResultDiv").removeClass("hide");

                        if(data.succeedCountInFile > 0) {
                            $("#succeedDiv").removeClass("hide");
                            $("#succeedListTable").empty();

                            var succeedList = data.succeedList;
                            var succeedSample;
                            var newRowHtml;
                            for (var i = 0; i < data.succeedCountInFile; i++) {
                                succeedSample = succeedList[i];
                                newRowHtml = "";
                                newRowHtml += "<tr><td>" + succeedSample.sampleNo + "</td>";
                                newRowHtml += "<td><input type='hidden' name='geneInfo' value='"+JSON.stringify(succeedSample.geneInfo)+"'/>";
                                 newRowHtml += "<input type='hidden' name='detailBtn' value='"+ succeedSample.geneid +"'/>";
                                newRowHtml += "<button name='detailBtn' id='detailBtn' class='btn btn-info btn-xs' ><i class='fa fa-list-alt'></i> 查看</input>";
                                newRowHtml += "</td></tr>";
                                $("#succeedListTable").append(newRowHtml);
                                CodisSucceedList = $("#succeedListTable").html();
                            }
                        }
                        if(data.failedCountInFile > 0) {
                            $("#failedDiv").removeClass("hide");
                            $("#failedListTable").empty();

                            var failedList = data.failedList;
                            var failedSample;
                            for (var i = 0; i < data.failedCountInFile; i++) {
                                failedSample = failedList[i];
                                $("#failedListTable").append("<tr><td>" + failedSample.sampleNo + "</td><td>"+failedSample.failedReason+"</td></tr>");
                                CodisFailedList = $("#failedListTable").html();
                            }
                        }
                    }else{
                        alert(data.errMsg);
                        location.reload()
                    }
                },
                error: function (data,status,e) {
                    alert(e);
                }
            });

            return true;
        }


        //查看
        $("#succeedListTable").on("click",'#detailBtn',function(){
      /*  $("#detailBtn").on("click",function(){*/
            var  sampleId = $(this).prev("input[name='detailBtn']").val();
             location.href = "<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=" +sampleId ;
        });

        //导入CODIS start
        $("#browserCodisBtn").on("click",function(){
            $("#codisFile").click();
        });

        $("#codisFile").on("change",function(){
            $("#codisFileTxt").val($(this).val());
        });

        $("#importCodisBtn").on("click",function(){
           /* $.ajax({
                url: " <=path>/center/repetitionCodis?boardNo=" + $("input[name=boardNo]").val(),
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data.success || data.success == true || data.success == "true") {*/
                        importCodisFtn();
                   /* }else {
                        alert("该板号已存在！")
                    }
                },
                error: function (e) {
                    alert(e);
                }
            });*/

        });

    });
</script>

<!-- END JS -->
</body>
</html>


