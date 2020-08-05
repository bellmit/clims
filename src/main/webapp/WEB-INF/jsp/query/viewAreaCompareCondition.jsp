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
        .tdGreen {
            color: #63c9ba !important;
        }

        .tdRed {
            color: #ff6864 !important;
        }
    </style>
</head>
<body>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>比中概况</div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>审核人：</label>${matchResult.operator}
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>审核状态：</label>${matchResult.status}
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>审核时间：</label>${matchResult.verifyTime}
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>比中时间：</label>${matchResult.submitTime}
                    </c:if>
                </div>
            </div>
            <div class="panel-body">
                <table class="table table-hover table-bordered bigTable">
                    <thead>
                    <tr>
                        <th>样本类型</th>
                        <th>检材名称</th>
                        <th>案件名称</th>
                        <th>提交人</th>
                        <th>Panel</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>源样本</td>
                        <c:choose>
                            <c:when test="${not empty matchResult}">
                                <td>${matchResult.sampleAId}&nbsp;&nbsp;${matchResult.sampleAName}&nbsp;&nbsp;${matchResult.sampleANo}</td>
                                <td>${matchResult.sourceCaseName}</td>
                                <td>${matchResult.submitOperA}</td>
                                <td>${matchResult.panelA}</td>
                            </c:when>
                            <c:otherwise>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                    <c:if test="${not empty matchResult}">
                        <c:choose>
                            <c:when test="${matchResult.isSampleBSource eq 0}">
                                <tr>
                                    <td>目标样本</td>
                                    <td>${matchResult.sampleBId}&nbsp;&nbsp;${matchResult.sampleBName}&nbsp;&nbsp;${matchResult.sampleBNo}</td>
                                    <td>${matchResult.targetCaseName}</td>
                                    <td>${matchResult.submitOperB}</td>
                                    <td>${matchResult.panelB}</td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td>源样本</td>
                                    <td>${matchResult.sampleBId}&nbsp;&nbsp;${matchResult.sampleBName}&nbsp;&nbsp;${matchResult.sampleBNo}</td>
                                    <td>${matchResult.sourceCaseName}</td>
                                    <td>${matchResult.submitOperB}</td>
                                    <td>${matchResult.panelB}</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${not empty matchResult.sampleCId}">
                            <tr>
                                <td>目标样本</td>
                                <td>${matchResult.sampleCId}&nbsp;&nbsp;${matchResult.sampleCName}&nbsp;&nbsp;${matchResult.sampleCNo}</td>
                                <td>${matchResult.targetCaseName}</td>
                                <td>${matchResult.submitOperC}</td>
                                <td>${matchResult.panelC}</td>
                            </tr>
                        </c:if>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<div class="row Modular ">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>比对结果</div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>人员关系：</label>${matchResult.matchType}
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>样本类型：</label>${matchResult.targetSampleTypeName}
                    </c:if>
                </div>
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <label>试剂盒：</label>
                    </c:if>
                </div>
                <input type="hidden" name="matchType" value="${matchResult.matchType}">
                <input type="hidden" name="compareStatus" value="${matchResultVo.entity.compareStatus}">
                <input type="hidden" name="matchId" value="${matchResultVo.matchId}">
                <div class="form-group">
                    <c:if test="${not empty matchResult}">
                        <div class="select">
                            <select id="panel" name="panel" class="form-control">
                                <c:forEach items="${panelList}" var="list" varStatus="cs">
                                    <option value="${list.panelName}"
                                            <c:if test="${list.panelName eq matchResultVo.panel}">selected</c:if>>${list.panelName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="panel-body">
                <c:choose>
                    <c:when test="${matchResult.matchType eq '同一匹配'}">
                        <table class="table table-hover table-bordered bigTable">
                            <thead>
                                <th rowspan="2">序号</th>
                                <th rowspan="2">基因座</th>
                                <c:choose>
                                    <c:when test="${not empty matchResult}">
                                        <c:choose>
                                            <c:when test="${not empty matchResult.sampleCId}">
                                                <th colspan="3">等位基因</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th colspan="2">等位基因</th>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <th colspan="3">等位基因</th>
                                    </c:otherwise>
                                </c:choose>
                                <th rowspan="2">LR</th>
                                <th rowspan="2">其他</th>
                            </tr>
                            <tr>
                                <c:if test="${not empty matchResult}">
                                    <td title="${matchResult.sampleAId} ${matchResult.sampleAName}">
                                            ${matchResult.sampleAName}${matchResult.sampleArelation}
                                    </td>
                                    <td title="${matchResult.sampleBId} ${matchResult.sampleBName}">
                                            ${matchResult.sampleBName}${matchResult.sampleBrelation}
                                    </td>
                                </c:if>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty matchResult.piOfMarkers}">
                                <c:forEach items="${matchResult.piOfMarkers}" var="marker" varStatus="s">
                                    <tr onMouseOver="this.bgColor='#fff7d4'" onMouseOut="this.bgColor=''" >
                                        <td>${s.count}</td>
                                        <td>${marker.markerName}</td>
                                        <td>${marker.alleleA}</td>
                                        <td>${marker.alleleB}</td>
                                        <td>${marker.pi}</td>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <tr onMouseOver="this.bgColor='#fff7d4'" onMouseOut="this.bgColor=''" >
                                <td><b>合计</b></td>
                                <td>
                                    <b>比中基因座数:</b>&nbsp;&nbsp;
                                    <c:if test="${not empty matchResult}">
                                        ${matchResult.matchCount}
                                    </c:if>
                                </td>
                                <c:choose>
                                    <c:when test="${not empty matchResult}">
                                        <c:choose>
                                            <c:when test="${not empty matchResult.sampleCId}">
                                                <td colspan="3"></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td colspan="2"></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="3"></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <b>LR:</b>&nbsp;&nbsp;
                                    <c:if test="${not empty matchResult}">
                                            ${matchResult.totalMatchPossibility}
                                    </c:if>
                                </td>
                                <td></td>
                            </tr>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover table-bordered bigTable">
                            <thead>
                            <tr>
                                <td rowspan="2">序号</td>
                                <td rowspan="2">基因座</td>
                                <td colspan="3">等位基因</td>
                                <td rowspan="2">PI值</td>
                                <td rowspan="2">父</td>
                                <td rowspan="2">母</td>
                                <td rowspan="2">其他</td>
                            </tr>
                            <tr>
                                <td title = "${matchResult.fBarcode},${matchResult.fSampleName}">${matchResult.fSampleName}(父)</td>
                                <td title = "${matchResult.mBarcode},${matchResult.mSampleName}">${matchResult.mSampleName}(母)</td>
                                <td title = "${matchResult.cBarcode},${matchResult.cSampleName}">${matchResult.cSampleName}(子)</td>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty matchResult.piOfMarkers}">
                                <c:forEach items="${matchResult.piOfMarkers}" var="marker" varStatus="s">
                                    <tr onMouseOver="this.bgColor='#fff7d4'" onMouseOut="this.bgColor=''" >
                                        <td>${s.count}</td>
                                        <td>${marker.markerName}</td>
                                        <td>${marker.alleleF}</td>
                                        <td>${marker.alleleM}</td>
                                        <td>${marker.alleleZ}</td>
                                        <c:choose>
                                            <c:when test="${not empty marker.noMatched}">
                                                <td>Χ</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${marker.pi}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${not empty marker.noMatchedF}">
                                                <td>Χ</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${marker.piF}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${not empty marker.noMatchedM}">
                                                <td>Χ</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>${marker.piM}</td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${not empty matchResult.matchCountAndTotalPi}">
                                <tr>
                                    <td><b>pi值合计</b></td>
                                    <td>&nbsp;</td>
                                    <td><b>累计PI值:</b>&nbsp;&nbsp;${matchResult.matchCountAndTotalPi.totalPi}</td>
                                    <td><b>父累计PI值: <br/>${matchResult.matchCountAndTotalPi.fTotalPi}</b></td>
                                    <td><b>母累计PI值: <br/>${matchResult.matchCountAndTotalPi.mTotalPi}</b></td>
                                    <td colspan="4">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><b>匹配数合计</b></td>
                                    <td>&nbsp;</td>
                                    <td><b>总匹配数:</b>&nbsp;&nbsp;${matchResult.matchCountAndTotalPi.matchCount}</td>
                                    <td><b>父匹配数: ${matchResult.matchCountAndTotalPi.fMatchCount}</b></td>
                                    <td><b>母匹配数: ${matchResult.matchCountAndTotalPi.mMatchCount}</b></td>
                                    <td colspan="4">&nbsp;</td>
                                </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    $(function () {
        $("#panel").change('click',function(){
            var compareStatus = $("input[name='compareStatus']").val();
            var matchId = $("input[name='matchId']").val();
            var matchType = $("input[name='matchType']").val();
            var panel = $("#panel").val();
            if (matchType == '同一匹配') {
                location.href = "<%=path%>/queryCompareResult/queryAreaSameCompareCondition?entity.compareStatus="
                        + compareStatus + "&matchId=" + matchId + "&panel=" + panel;
            }else {
                location.href = "<%=path%>/queryCompareResult/queryAreaRelationCompareCondition?entity.compareStatus="
                        + compareStatus + "&matchId=" + matchId + "&panel=" + panel;
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
    })
</script>
</body>

</html>