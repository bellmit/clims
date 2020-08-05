<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    < <title>北京市公安局法医鉴定案件受理系统</title>
    <%@ include file="../../newLink.jsp" %>
    <%@ include file="../../include.jsp" %>
    <%
        String path = request.getContextPath();
    %>
</head>

<body class="flat-blue">
<div class="app-container">
    <div class="row content-container">
                <div class="container-fluid Scrollbar">
                    <div class="side-body padding-top">
                        <div class="container-style padding-0">
                            <div class="row">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="new-title" style="padding:0px 30px">
                                        <div class="new-title-child">
                                            <span class="new-title-span">
                                                 <span>
                                                    <input type="hidden" id="sampleTableId" value="${sampleInfoVo.entity.sampleTableId}">
                                                    <input type="hidden" id="extractPlateId" value="${sampleInfoVo.entity.extractPlateId}">
                                                    <input type="hidden" id="pcrPlateId" value="${sampleInfoVo.entity.pcrPlateId}">
                                                    <input type="hidden" id="syPlateId" value="${sampleInfoVo.entity.syPlateId}">
                                                    <input type="hidden" id="boardNo" value="${sampleInfoVo.entity.boardNo}">
                                                    <input type="hidden" id="elution" value="${sampleInfoVo.entity.elution}">
                                                    <input type="hidden" id="elutionName" value="${sampleInfoVo.elutionName}">
                                                    <input type="hidden" name="holeNum" id="holeNum" value="${sampleInfoVo.holeNum}">
                                                    <input type="hidden" name="tabindex" id="tabindex">
                                                </span>
                                                <span>添加检材（${sampleInfoVo.entity.boardNo}）</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0"
                                     style="width:100%;border-bottom:1px solid #E4E4E6"></div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="display-style">
                                        <div class="btn-targ">
                                            <div>
                                                <ul class="btn-targ-ul">
                                                    <li class="position-24-btn">24孔</li>
                                                    <!-- <li class="position-48-btn">48孔</li> -->
                                                    <li class="position-96-btn">整板</li>
                                                </ul>
                                                <button
                                                        class="btn btn-warning position-table-show tableBtn-style">列表</button>
                                            </div>
                                            <div>
                                                <c:choose>
                                                    <c:when test="${sampleTable.isCreate eq '1'}">

                                                    </c:when>
                                                    <c:otherwise>
                                                <span data-toggle="modal" id='editBtn'>
                                                    <span><img src="<%=path%>/automatedWork/img/1874.png" alt=""></span>
                                                    <span style="color: #006CFF !important;">批量修改</span>
                                                </span>
                                                <span id='deleteBtn'>
                                                    <span><img src="<%=path%>/automatedWork/img/1875.png" alt=""></span>
                                                    <span style="color: #FF5A55 !important;">批量删除</span>
                                                </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="container-bottom" style="margin-top:2px;">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th>
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allChecked" id="allChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th>检材编号</th>
                                                    <th>洗脱体积（ul）</th>
                                                    <th>样本板序号</th>
                                                    <th>样本板孔位</th>
                                                    <th>提取板孔位</th>
                                                    <th>确证方法</th>
                                                    <th>预实验方法</th>
                                                    <th>检材转移方法</th>
                                                    <c:choose>
                                                        <c:when test="${sampleTable.isCreate eq '1'}">

                                                        </c:when>
                                                        <c:otherwise>
                                                            <th>操作</th>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tr>
                                                </thead>
                                                <tbody id="sampleInfoVoListTbody">
                                                <c:forEach items="${sampleInfoVoList}" var="sample" varStatus="s">
                                                    <tr>
                                                        <td>
                                                            <input type='hidden' name='id' value='${sample.entity.id}'/>
                                                            <input type='hidden' name='sampleTableId' value='${sample.entity.sampleTableId}'/>
                                                            <input type='hidden' name='elution' value='${sample.entity.elution}"'/>
                                                            <input type='hidden' name='boardNo' value='${sample.entity.boardNo}"'/>
                                                            <input type='hidden' name='sampleNo' value='${sample.entity.sampleNo}'/>
                                                            <input type='hidden' name='samplePlateSort' value='${sample.entity.samplePlateSort}'/>
                                                            <input type='hidden' name='sampleLocationSort' value='${sample.entity.sampleLocationSort}'/>
                                                            <input type='hidden' name='samplePlateLocation' value='${sample.entity.samplePlateLocation}'/>
                                                            <input type='hidden' name='preExperimentalMethod' value='${sample.entity.preExperimentalMethod}'/>
                                                            <input type='hidden' name='confirmatoryMethod' value='${sample.entity.confirmatoryMethod}'/>
                                                            <input type='hidden' name='sampleTransferMethod' value='${sample.entity.sampleTransferMethod}'/>
                                                            <input type='hidden' name='sampleProperty' value='${sample.entity.sampleProperty}'/>
                                                            <label class="labelBox"><input type='checkbox' name='box'>${s.index + 1}</label>
                                                        </td>
                                                        <td>${sample.entity.sampleNo}</td>
                                                        <td>${sample.elutionName}</td>
                                                        <td>${sample.entity.samplePlateSort}</td>
                                                        <td>${sample.entity.sampleLocationSort}</td>
                                                        <td>${sample.entity.samplePlateLocation}</td>
                                                        <td>${sample.confirmatoryMethodName}</td>
                                                        <td>${sample.preExperimentalMethodName }</td>
                                                        <td>${sample.sampleTransferMethodName}</td>
                                                        <c:choose>
                                                            <c:when test="${sampleTable.isCreate eq '1'}">

                                                            </c:when>
                                                            <c:otherwise>
                                                                <td>
                                                                    <span class='delete'>
                                                                        <span><img src='<%=path%>/automatedWork/img/tra.png' alt=''></span>
                                                                        <span>删除</span>
                                                                    </span>
                                                                </td>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div  id="position-24" class="HolePosition"  style="padding: 0px 30px;">
                                        <div class="row">
                                            <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12"
                                                 style="border-right: 1px solid #CCCCCC;height: 100%;">
                                                <div class="btn-targ" style="background:#E4E4E6;border-radius:3px;">
                                                    <div>
                                                        <ul class="btn-targ-ul">
                                                            <li class="position-24-btn style-li">24孔</li>
                                                            <li class="position-96-btn">整板</li>
                                                        </ul>
                                                        <button
                                                                class="btn btn-warning position-table-show">列表</button>
                                                    </div>
                                                </div>
                                                <!-- id position-24 -->
                                                <div class="maring-top-15">
                                                    <div class="row">
                                                        <c:forEach items="${tempList24}" var="tempList" varStatus="t">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                                                <div class="HolePosition-con style-24"
                                                                     style="background:none;border:none">
                                                                    <div>
                                                                        <p><img src="<%=path%>/automatedWork/img/sx.png" alt=""></p>
                                                                        <div>
                                                                            <p>${t.index + 1}</p>
                                                                            <p>号</p>
                                                                            <p>过</p>
                                                                            <p>滤</p>
                                                                            <p>版</p>
                                                                        </div>
                                                                        <%--<ul class="ul-li-right">
                                                                            <li>A</li>
                                                                            <li>B</li>
                                                                            <li>C</li>
                                                                            <li>D</li>
                                                                        </ul>--%>
                                                                    </div>
                                                                    <div>
                                                                        <ul class="ul-li-click">
                                                                            <c:forEach items="${tempList.list}" var="list" varStatus="s">
                                                                                <c:if test="${not empty list.entity.id}">
                                                                                    <c:choose>
                                                                                        <c:when test="${not empty list.elutionName && list.elutionName > 50}">
                                                                                            <li class="li-background-2" tabindex="${list.entity.sampleLocationSort}">
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <li class="li-background" tabindex="${list.entity.sampleLocationSort}">
                                                                                        </c:otherwise>
                                                                                    </c:choose>
                                                                                </c:if>
                                                                                <c:if test="${empty list.entity.id}">
                                                                                    <li tabindex="${list.entity.sampleLocationSort}">
                                                                                </c:if>
                                                                                <p>${list.elutionName}</p>
                                                                                <p>${list.entity.sampleNo}</p>
                                                                                <p style="display: none;">${t.index + 1}</p>
                                                                                <p style="display: none;">${list.entity.sampleLocationSort}</p>
                                                                                <p style="display: none;">${list.entity.samplePlateLocation}</p>
                                                                                <p style="display: none;">${list.entity.id}</p>
                                                                                <p style="display: none;">${list.entity.preExperimentalMethod}</p>
                                                                                <p style="display: none;">${list.entity.confirmatoryMethod}</p>
                                                                                <p style="display: none;">${list.entity.sampleTransferMethod}</p>
                                                                                <p style="display: none;">${list.entity.sampleProperty}</p>
                                                                                <p>${list.entity.sampleLocationSort}/${list.entity.samplePlateLocation}</p>
                                                                               <%-- <c:if test="${s.index lt 6}">
                                                                                    <span class="posi-abs-span">${s.index + 1}</span>
                                                                                </c:if>--%>
                                                                                </li>
                                                                            </c:forEach>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12  padding-left-15 display-col">
                                                <div class="add-form">
                                                    <div>
                                                        <p>添加检材</p>
                                                    </div>
                                                    <form action="">
                                                        <div>
                                                            <p>检材编号</p>
                                                            <input title="请输入检材编号" type="text" name="sampleNoModel" placeholder="请输入检材编号">
                                                        </div>
                                                        <div>
                                                            <p>洗脱体积(ul)</p>
                                                            <select title="请选择洗脱体积" name="elutionModel">
                                                                <option value=''>请选择洗脱体积</option>
                                                                <c:forEach items="${elutionDefaultList}" var="list" varStatus="s">
                                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>样本板序号</p>
                                                            <select title="请选择样本板序号" name="samplePlateSortModel">
                                                                <option value=''>请选择样本板序号</option>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>样本板孔位</p>
                                                            <select title="请选择样本板孔位" name="sampleLocationSortModel">
                                                                <option value=''>请选择样本板孔位</option>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>预实验方法</p>
                                                            <select title="请选择预实验方法" name="preExperimentalMethodModel">
                                                                <c:forEach items="${preExperimentalMethodList}" var="list" varStatus="s">
                                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>确证方法</p>
                                                            <select title="请选择确认方法" name="confirmatoryMethodModel">
                                                                <c:forEach items="${confirmatoryMethodList}" var="list" varStatus="s">
                                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>检材转移方法</p>
                                                            <select title="请选择检材转移方法" name="sampleTransferMethodModel">
                                                                <c:forEach items="${sampleTransferMethodList}" var="list" varStatus="s">
                                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div>
                                                            <p>检材属性</p>
                                                            <select title="请选择检材属性" name="samplePropertyModel">
                                                                <c:forEach items="${samplePropertyList}" var="list" varStatus="s">
                                                                    <option value="${list.dictCode}">${list.dictName}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </form>
                                                    <c:choose>
                                                        <c:when test="${sampleTable.isCreate eq '1'}">

                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="btn-footer">
                                                                <button class="btn btn-info" type="button" id="saveBtn">保 存</button>
                                                                <button class="btn btn-info" type="button" id="delBtn">删 除</button>
                                                                <input type="hidden" name="samplePlateLocationModel">
                                                                <input type="hidden" name="idModel">
                                                            </div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="position-96" class="HolePosition" style="padding: 0px 30px;display: none;">
                                        <div class="row">
                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
                                                 style="height: 100%;">
                                                <div class="btn-targ" style="background:#E4E4E6;border-radius:3px;">
                                                    <div>
                                                        <ul class="btn-targ-ul">
                                                            <li class="position-24-btn">24孔</li>
                                                            <!-- <li class="position-48-btn">48孔</li> -->
                                                            <li class="position-96-btn style-li" >整板</li>
                                                        </ul>
                                                        <button
                                                                class="btn btn-warning position-table-show">列表</button>
                                                    </div>
                                                </div>
                                                <div id="targ-96" class="margin-top-12">
                                                    <div class="left-style">
                                                        <div class="left-style-div">
                                                            <p><img src="<%=path%>/automatedWork/img/sx.png" alt=""></p>
                                                            <p>整</p>
                                                            <p>板</p>
                                                        </div>
                                                        <ul class="left-style-ul">
                                                            <li>A</li>
                                                            <li>B</li>
                                                            <li>C</li>
                                                            <li>D</li>
                                                            <li>E</li>
                                                            <li>F</li>
                                                            <li>G</li>
                                                            <li>H</li>
                                                        </ul>
                                                    </div>
                                                    <div class="right-style">
                                                        <div class="row" id="allBoard">

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer-style">
                <div style="width: 100%;height: 10px;background: #D6D3D6;"></div>
                <div class="div-nth-1">
                    <button class="btn btn-info btn-sm back" id="backBtn">返回上一步</button>
                    <input type="hidden" id="tabValue" value="${sampleInfoVo.tabValue}">
                </div>
                <div>

                </div>
            </div>
            <div class="modal fade modal-primary msg-model" id="splitModel" role="dialog"
                 aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">
                                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">修改检材编号</span>
                            </h4>
                        </div>
                        <div class="modal-body">
                            <form>
                                <div>
                                    <div>
                                        <p>输入拆分编号</p>
                                        <input name="spiltSampleNoModel" id="spiltSampleNoModel" type="text">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="msg-model-footer">
                            <button class="btn btn-info btn-sm" type="button" id="saveSpiltBtn">保 存</button>
                        </div>
                    </div>
                </div>
            </div>
        <div class="modal fade modal-primary" id="sampleMultipleModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>修改洗脱体积</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div>
                                <div>
                                    <p>默认洗脱体积(ul)</p>
                                    <select name="elutionMultipleModel">
                                        <option value=''>请选择</option>
                                        <c:forEach items="${elutionDefaultList}" var="list" varStatus="s">
                                            <option value="${list.dictCode}">${list.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="saveMultipleBtn">保 存</button>
                            <button class="btn btn-info btn-sm" data-dismiss="modal">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade modal-primary msg-model" id="tipsModel" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">消息提示</span>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <h4>检材编号已存在！是否做拆分</h4>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="yesBtn">是</button>
                            <button class="btn btn-info btn-sm" type="button" id="noBtn">否</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade modal-primary msg-model" id="addModel" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true">消息提示</span>
                        </h4>
                    </div>
                    <div class="modal-body">
                        <h4>此孔位检材编号已存在！是否继续添加？</h4>
                    </div>
                    <div class="modal-footer">
                        <div>
                            <button class="btn btn-info btn-sm" type="button" id="confirmBtn">确 认</button>
                            <button class="btn btn-info btn-sm" data-dismiss="modal">取 消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade modal-primary" id="promptModel-error" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span></h4>

                    </div>
                    <div class="modal-body">
                        <h4>请先选择需要添加检材的孔位！</h4>
                    </div>
                </div>
            </div>
</div>
<!-- Javascript Libs -->
<%@ include file="../../newScript.jsp" %>
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script src="${pageContext.request.contextPath}/js/check.js" ></script>
<script src="${pageContext.request.contextPath}/js/center/sampleInfo.js" ></script>
<script>
    $(function () {


    })
</script>

</body>

</html>