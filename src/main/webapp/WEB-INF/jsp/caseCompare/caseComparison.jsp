<%@ include file="../include.jsp" %>
<%
    String path = request.getContextPath();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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

        .leftBox {
            width: 350px;
            height: 100%;
            position: absolute;
            padding: 15px 0;
            z-index: 1;
        }

        .rightBox {
            width: 100%;
            height: 100%;
            padding: 15px 0;
            padding-left: 360px;
        }

        .rightBox-top {
            width: 100%;
            height: 80px;
            border-radius: 5px;
            position: absolute;
            top: 10px;
            left: 0px;
            padding-left: 370px;
            padding-right: 10px;
        }

        .rightBox-bottom {
            width: 100%;
            height: 100%;
            padding-top: 85px;
            overflow: hidden;
        }

        .leftBox > div,
        .rightBox-bottom > div,
        .rightBox-top > div {
            width: 100%;
            height: 100%;
            background: #fff;
            border-radius: 5px;
        }

        .leftBox > div {
            padding: 0px 15px;
            padding-top: 50px;
        }

        .leftBox > div p {
            color: #0c81f5;
            font-weight: 600;
            font-size: 16px;
            margin-bottom: 23px;
            position: absolute;
            top: 30px;
        }

        .leftBox > div ul {
            padding-left: 25px;
            height: 100%;
            position: relative;
            overflow-y: auto;
        }

        .leftBox > div ul::before {
            content: "";
            position: absolute;
            height: 99%;
            width: 2px;
            background: #e0e0e0;
            left: 10px;
            top: 3px;
        }

        .leftBox > div ul li {
            color: #000;
            position: relative;
        }

        .leftBox > div ul li + li {
            margin-top: 15px;
        }

        .leftBox > div ul li.red {
            color: #ff5a56;
            margin-top: 30px;
        }

        .leftBox > div ul li.red::before {
            content: "";
            position: absolute;
            width: 12px;
            height: 12px;
            background: #e0e0e0;
            border-radius: 50%;
            left: -20px;
            top: 50%;
            margin-top: -7px;
        }

        .leftBox > div ul li:nth-child(1) {
            margin-top: 0px;
        }

        .rightBox-top > div > div:nth-child(1) {
            text-align: center;
            border-bottom: 1px solid #efefef;
            padding: 15px 0;
        }

        .rightBox-top > div > div:nth-child(2) {
            padding: 15px 50px;
        }

        .rightBox-bottom > div {
            position: relative;
        }

        .rightBox-bottom > div > div:nth-child(1) {
            padding: 6px 50px;
            position: absolute;
            left: 0px;
            top: 0px;
            width: 100%;
            background: #fff;
            z-index: 1;
        }

        .rightBox-bottom > div > div:nth-child(2) {
            height: 100%;
            padding-top: 40px;
            /*padding-bottom: 119px;*/
            padding-bottom: 10px;
            background: #fff;
            overflow-y: auto;
        }

        .rightBox-bottom > div > div:nth-child(2) .table {
            height: 100%;
            width: 100%;

        }


        .rightBox-bottom > div > div:nth-child(3) {
            padding: 0 50px;
            position: absolute;
            left: 0px;
            bottom: 0px;
            width: 100%;
            background: #fff;
        }

        .rightBox-bottom > div > div:nth-child(3) div > .form-group + .form-group {
            margin-left: 63px;
        }

        .seachInputBox_new {
            padding: 0 20px;
        }

        .inlineSelect{
            display: inline-block;
            width: 200px;
        }
        .inlineMargin{
            padding-top: 20px !important;
        }
        .inlineMargin label{
            height: 34px;
            line-height: 34px;
        }
        .footerBox{
            box-shadow: 0px 0px 10px 5px #ebebeb
        }
        /*.footerBox>.form-inline .footerBox>.col-md-4{*/
        /*margin: 10px 0;*/
        /*}*/
        .footerBox .form-group{
            width: 300px;
        }
        .footerBox .row{
            margin: 10px 0;
        }
        .footerBox .row:first-child{
            border-bottom: 1px dashed #ebebeb;
        }
        .footerBox .row:last-child .col-md-8{
            margin-top: 6px;
        }
    </style>
</head>
<div class="leftBox">
    <div>
        <p>混合比对结果</p>
        <ul>
            <c:forEach items="${mixGroupList}" var="matchedGroup" varStatus="group" >
                <c:forEach items="${matchedGroup.mixMatchedSampleList}" var="matchedSample" varStatus="matched">
                    <c:if test="${matched.index eq 0}">
                        <li class="red" onclick = switchGroup('${matchedGroup.groupId}');>
                    </c:if>
                    <c:if test="${matched.index gt 0}">
                        <li>
                    </c:if>
                    ${matchedSample.sampleNo} ${matchedSample.sampleName}
                    <input type="hidden" name="sampleName" value="${matchedSample.sampleName}">
                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </div>
</div>

<div class="rightBox">
    <div class="rightBox-top">
        <div>
            <%--<div>--%>
            <%--<button class="btn btn-yellow btn-lang">重新寻找贡献者</button>--%>
            <%--</div>--%>


            <div class="row inlineMargin">

                <div class="col-md-4 form-inline">
                    <div class="form-group">
                        <label>案件名称:</label>
                        ${limsCaseInfo.caseName}
                    </div>
                </div>
                <div class="col-md-4 form-inline">
                    <div class="form-group">
                        <label>案件编号:</label>${limsCaseInfo.caseNo}
                    </div>
                </div>

                <div class="col-md-4 seachInputBox_new">
                    <div class="form-group">
                        <button class="btn btn-blue" name="saveBlend" type="button" id="hidebutton">保存</button>
                        <input type="hidden" name="caseId" id="caseId" value="${caseId}">
                        <input type="hidden" name="consignmentId" id="consignmentId" value="${consignmentId}">
                        <input type="hidden" name="mixMatchLimit" id="mixMatchLimit" value="${mixMatchLimit}">
                        <a href="javascript:history.go(-1);"><button type="button" class="btn btn-blue-border" name="return">返回</button></a>
                    </div>
                </div>


                <%--<div class="col-md-4">--%>
                    <%--<div class="form-group">--%>
                        <%--<button class="btn btn-yellow btn-lang" style="margin-top: -6px;">重新寻找贡献者</button>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="col-md-6">
                    <button class="btn btn-blue">查看详情</button>
                    <button class="btn btn-blue-border">查看未知贡献者分型报告</button>
                </div>--%>
            </div>
        </div>
    </div>
    <div class="rightBox-bottom">
        <div>
            <div class="row">
                <div class="col-md-4 seachInputBox_new">
                    <div class="form-group">
                        <label> 试剂盒 </label>

                        <div class="select inlineSelect">
                            <select class="form-control" id="reagentSelect" name="reagent">
                                <%--<option value="">请选择...</option>--%>
                                <c:forEach items="${panels}" var="list">
                                    <option value="${list.panelname}">${list.panelname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 seachInputBox_new">
                    <div class="form-group">
                        <label>贡献者1</label>

                        <div class="select inlineSelect">
                            <select class="form-control" name="contributor1" id="contributor1"  onchange="contributorChange(0)">
                                <option value="">请选择</option>
                                <c:forEach items="${contributorList1}" var="contributor" varStatus="c">
                                    <option value="${contributor.sampleId}"
                                            <c:if test="${contributor.sampleId eq contributor3.sampleId1}">selected</c:if>>
                                            ${contributor.sampleName}
                                    </option>
                                    <%--<option value="${contributor.sampleId}">${contributor.sampleName}</option>--%>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 seachInputBox_new">
                    <div class="form-group">
                        <label>贡献者2</label>
                        <input type="hidden" name="sampleid" id="sampleid" value="${mixAuditedGene.sampleId}">
                        <input type="hidden" name="sampleName" id="sampleName" value="${mixAuditedGene.sampleName}">
                        <div class="select inlineSelect">
                            <select class="form-control" name="contributor2" id="contributor2" onchange="contributorChange(1)">
                                <option value="">请选择</option>
                                <c:forEach items="${contributorList2}" var="contributor" varStatus="c">
                                    <option value="${contributor.sampleId}"
                                            <c:if test="${contributor.sampleId eq contributor4.sampleId2}">selected</c:if>>
                                            ${contributor.sampleName}
                                    </option>

                                    <%--<option value="${contributor.sampleId}">${contributor.sampleName}</option>--%>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div>
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>基因座</th>
                        <th>混合斑</th>
                        <th>贡献者1</th>
                        <th>贡献者2</th>
                        <th>LR</th>
                    </tr>
                    </thead>
                    <tbody id="groupListTbody"  class="table-tr-td">

                             <%--<c:if test="${geneList1 != null}">--%>
                             <%--<c:forEach items="${geneList1}" var="list1" varStatus="s">--%>
                                    <%--<td>${list1}</td>--%>
                             <%--</c:forEach>--%>
                             <%--</c:if>--%>
                            <%--<c:if test="${geneList2 != null}">--%>
                                <%--<c:forEach items="${geneList2}" var="list2" varStatus="z">--%>
                                    <%--<td>${list2}</td>--%>
                                <%--</c:forEach>--%>
                            <%--</c:if>--%>
                        <c:forEach items="${resultList}" var="list" varStatus="r">
                            <tr>
                                <td>${r.count}</td>
                                <td>${list.markerName}</td>
                                <td>${list.mixAllele}</td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
            <%--<div class="row footerBox">
                <div class="row">
                    <div class="col-md-3 form-inline">
                        <div class="form-group">
                            <label >混合样本:</label>
                            ${mixAuditedGene.sampleName}
                            <input type="hidden" name="sampleid" id="sampleid" value="${mixAuditedGene.sampleId}">
                            <input type="hidden" name="sampleName" id="sampleName" value="${mixAuditedGene.sampleName}">
                        </div>
                    </div>
                    <div class="col-md-4 form-inline">
                        <div class="form-group">
                            <label>案件名称:</label>
                            ${limsCaseInfo.caseName}
                        </div>
                    </div>
                    <div class="col-md-3 form-inline">
                        <div class="form-group">
                            <label>案件编号:</label>${limsCaseInfo.caseNo}
                        </div>
                    </div>
                    <div class="col-md-2 form-inline">
                        <div class="form-group">
                            <label>检验人:</label>${fullName}
                        </div>
                    </div>
                </div>
            </div>--%>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function(){
            var sampleid = $("#contributor1").val();
            console.log(sampleid+"-------1-----")
            $.ajax({
                url: "<%=path%>/center/selectMatchAuditedGene?sampleid=" + sampleid ,
                type: "post",
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function (data) {
                    var list = $(".table-tr-td tr");
                    for(var k = 0; k<data.geneList.length; k++){
                        $(list[k]).find("td").eq("3").text(data.geneList[k])
                    }
                },
                error: function (data) {
                    console.log(data)
                    alert("保存失败!");
                }
            });
            var sampleid = $("#contributor2").val();
            console.log(sampleid+"-------1-----")
            $.ajax({
                url: "<%=path%>/center/selectMatchAuditedGene?sampleid=" + sampleid ,
                type: "post",
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function (data) {
                    var list = $(".table-tr-td tr");

                    for(var k = 0; k<data.geneList.length; k++){
                        $(list[k]).find("td").eq("4").text(data.geneList[k])
                    }
                },
                error: function (data) {
                    console.log(data)
                    alert("保存失败!");
                }
            });
    })
    function hidebutton()
    {
        var mybutton = document.getElementById("hidebutton");

        mybutton.style.display="none";

    }

    function contributorChange(obj)
    {
        console.log(obj+"------------")

        if (obj==0){
            var sampleid = $("#contributor1").val();
            console.log(sampleid+"-------1-----")
            $.ajax({
                url: "<%=path%>/center/selectMatchAuditedGene?sampleid=" + sampleid ,
                type: "post",
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function (data) {
                    var list = $(".table-tr-td tr");
                    for(var k = 0; k<data.geneList.length; k++){
                        $(list[k]).find("td").eq("3").text(data.geneList[k])
                    }
                },
                error: function (data) {
                    console.log(data)
                    alert("保存失败!");
                }
            });
        }else{
            var sampleid = $("#contributor2").val();
            console.log(sampleid+"-------1-----")
            $.ajax({
                url: "<%=path%>/center/selectMatchAuditedGene?sampleid=" + sampleid ,
                type: "post",
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function (data) {
                    var list = $(".table-tr-td tr");

                    for(var k = 0; k<data.geneList.length; k++){
                        $(list[k]).find("td").eq("4").text(data.geneList[k])
                    }
                },
                error: function (data) {
                    console.log(data)
                    alert("保存失败!");
                }
            });
        }


    }


        $("button[name='saveBlend']").on("click", function (){
            var contributor1 = $("#contributor1").val();
            var contributor2 = $("#contributor2").val();

            var reagentSelect = $("#reagentSelect").val();
            var sampleName = $("#sampleName").val();
            var sampleid = $("#sampleid").val();
            var sampleid1 = $("#contributor1").val();
            var sampleid2 = $("#contributor2").val();
            console.log(sampleid);
            console.log(sampleName);
            console.log("-----------   ");
            //

            $.ajax({
                url: "<%=path%>/center/saveBlendRelation?blendId=" + sampleid + "&sampleName=" + sampleName + "&contributionName=" + contributor1 + "&contributionName2=" + contributor2+ "&testkitName=" + reagentSelect+ "&sampleId1=" + sampleid1+ "&sampleId2=" + sampleid2,
                type: "post",
                // contentType: "application/json; charset=utf-8",
                // dataType: "json",
                success: function (data) {
                    console.log(data)
                        alert("保存成功!");
                },
                error: function (data) {
                    console.log(data)
                    alert("保存失败!");
                }
            });
        <%--location.href = "<%=path%>/center/saveBlendRelation?blendId="+ sampleid + "&sampleName=" + sampleName + "&contributionName=" + contributor1 + "&contributionName2=" + contributor2+ "&testkitName=" + reagentSelect;--%>
    })
</script>
</body>
</html>