<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>北京市公安局法医鉴定案件受理系统</title>
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
                                    <div class="new-title" style="padding: 0px 30px">
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
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0" style="width:100%;border-bottom:1px solid #E4E4E6"></div>                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="display-style" style="padding: 0px 30px;">
                                        <div class="btn-targ">
                                            <div>
                                                <ul class="btn-targ-ul">
                                                    <li class="position-24-btn">24孔</li>
                                                    <li class="position-96-btn">96孔</li>
                                                </ul>
                                                <button class="btn btn-warning position-table-show tableBtn-style">列表</button>
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
                                    <div class="HolePosition" style="padding:0px 30px;">
                                        <div class="row">
                                            <div class="col-lg-9 col-md-9 col-sm-12 col-xs-12"
                                                 style="border-right: 1px solid #CCCCCC;">
                                                <div class="btn-targ"
                                                     style="background:#E4E4E6;border-radius:3px;">
                                                    <div>
                                                        <ul class="btn-targ-ul">
                                                            <li class="position-24-btn">24孔</li>
                                                            <li class="position-96-btn">96孔</li>
                                                        </ul>
                                                        <button class="btn btn-warning position-table-show">列表</button>
                                                    </div>
                                                </div>
                                                <div id="position-24">
                                                    <div class="row">
                                                        <c:forEach items="${tempList24}" var="tempList" varStatus="t">
                                                            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                                                <div class="HolePosition-con style-24" style="background:none;border:none">
                                                                    <div>
                                                                        <p><img src="<%=path%>/automatedWork/img/sx.png" alt=""></p>
                                                                        <div>
                                                                            <p>${t.index + 1}</p>
                                                                            <p>号</p>
                                                                            <p>过</p>
                                                                            <p>滤</p>
                                                                            <p>板</p>
                                                                        </div>
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
                                                                                </li>
                                                                            </c:forEach>
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <div id="position-96" style="display: none;">
                                                    <div class="row">
                                                        <c:forEach items="${tempList96}" var="tempList" varStatus="t">
                                                            <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
                                                                <div class="HolePosition-con">
                                                                    <div>
                                                                        <p><img src="<%=path%>/automatedWork/img/sx.png" alt=""></p>
                                                                        <p>${t.index + 1}</p>
                                                                        <p>号</p>
                                                                        <p>过</p>
                                                                        <p>滤</p>
                                                                        <p>板</p>
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
                                            <div class="col-lg-3 col-md-3 col-sm-12 col-xs-12 padding-left-15">
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
    </div>
</div>
<!-- Javascript Libs -->
    <%@ include file="../../newScript.jsp" %>
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script src="${pageContext.request.contextPath}/js/check.js" ></script>
<script>

    function deleteClick(obj) {
        var id = $("input[name='id']", $(obj).parent().parent()).val();
        var sampleLocationSort = $("input[name='sampleLocationSort']", $(obj).parent().parent()).val();
        $("#identification").val("3");
        $("#param1").val(id);
        $("#param2").val(sampleLocationSort);
        $().confirmOperation("确认删除吗?");
    }


    function deletePlate(obj, str) {
        $.ajax({
            url:"<%=path%>/center/sample/deleteSampleInfo.html",
            type:"post",
            data:JSON.stringify(obj),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            success: function (data) {
                if(data.success){
//                        document.location.reload();
                    var position;
                    var holeNum = $("#holeNum").val();
                    if (holeNum == "24") {
                        position = "#position-24";
                    }else if (holeNum == "96"){
                        position = "#position-96";
                    }
                    if (str == "1") {
                        var tabindex = $("#tabindex").val();
                        //寻找下一个位置
                        $(position).find('.ul-li-click li').each(function(key,val){
                            var tab = $(this).attr("tabindex");
                            var num = Number(tabindex);
                            if (Number(tab) == num) {
                                $(this).removeClass();
                                $("input[name='sampleNoModel']").val("");
                                $("input[name='sampleNoModel']").focus();
                                var elutionName = $("#elutionName").val();
                                $(this).children("p:nth-child(1)").text(elutionName);
                                $(this).children("p:nth-child(2)").text('');
                                $("select[name='samplePlateSortModel']").empty();
                                $("select[name='sampleLocationSortModel']").empty();
                                $("input[name='samplePlateLocationModel']").val("");
                                $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
                                $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
                                $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
                                $("select[name='samplePropertyModel']").prop('selectedIndex', 0);
                                return false;
                            }
                        })
                    }else {
//                            document.location.reload();
                        $('.position-table-show').click();
                        var sampleLocationSort = "";
                        for (var i=0;i<obj.length;i++) {
                            sampleLocationSort = obj[i].sampleLocationSort;
                            $(id).find('.ul-li-click li').each(function(key,val){
                                var tab = $(this).attr("tabindex");
                                if (Number(sampleLocationSort) == Number(tab)) {
                                    $(this).removeClass();
                                    $("input[name='sampleNoModel']").val("");
                                    var elutionName = $("#elutionName").val();
                                    $(this).children("p:nth-child(1)").text(elutionName);
                                    $(this).children("p:nth-child(2)").text('');
                                    $("select[name='samplePlateSortModel']").empty();
                                    $("select[name='sampleLocationSortModel']").empty();
                                    $("input[name='samplePlateLocationModel']").val("");
                                    $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
                                    $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
                                    $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
                                    $("select[name='samplePropertyModel']").prop('selectedIndex', 0);
                                    return false;
                                }
                            })
                        }
                    }
                }else {
                    alert("操作失败!");
                }
            },
            error:function(data){
                alert("操作失败!");
            }
        });
    }

    $(function () {

        //模态框消失触发事件
        function modelHide() {
            var position;
            if (holeNum == "24") {
                position = "#position-24";
            }else if (holeNum == "96"){
                position = "#position-96";
            }

            var tabindex = $("#tabindex").val();
            if (tabindex == 24 || tabindex == 96) {
                var id = $("input[name='idModel']").val();
                if(id == undefined || id == null || id == ""){
                    return true;
                }else{
                    $("#modelContent").text("已是最后一个孔位！");
                    $("#msg-model").modal();
                    return false;
                }
            }

            //寻找下一个位置
            $(position).find('.ul-li-click li').each(function(key,val){
                var tab = $(this).attr("tabindex");
                var num = Number(tabindex) + 1;
                if (Number(tab) == num) {
                    $(this).click();
                }
            })

            //判断是否存在相同检材编号
            checkSampleNo();
        }

        //修改样本板
        $("#editBtn").on("click", function () {
            edit();
        });

        function edit() {
            if (checkCount() <= 0) {
                $("#modelContent").text("请选择要修改的检材！");
                $("#msg-model").modal();
                return false;
            }

            $("#sampleMultipleModal").modal('show');
        }

        //保存多个个板信息
        $("#saveMultipleBtn").on("click", function () {
            saveInfo(getSampleInfo("multiple"));
        });

        function saveInfo(params) {
            $.ajax({
                url:"<%=path%>/center/sample/saveSampleInfo.html",
                type:"post",
                data:JSON.stringify(params),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
//                        document.location.reload();
                        $("#sampleMultipleModal").modal('hide');
                        var position;
                        var holeNum = $("#holeNum").val();
                        if (holeNum == "24") {
                            position = "#position-24";
                        }else if (holeNum == "96"){
                            position = "#position-96";
                        }
                        $('.position-table-show').click();
                        var sampleLocationSort = "";
                        var elutionName = $("select[name='elutionMultipleModel'] :checked").text();
                        for (var i=0;i<params.length;i++) {
                            sampleLocationSort = params[i].sampleLocationSort;
                            $(position).find('.ul-li-click li').each(function(key,val){
                                var tab = $(this).attr("tabindex");
                                if (Number(sampleLocationSort) == Number(tab)) {
                                    $(this).removeClass();
                                    $(this).children("p:nth-child(1)").text(elutionName);
                                    if (Number(elutionName) > 50) {
                                        $(this).addClass("li-background-2");
                                    }else {
                                        $(this).addClass("li-background");
                                    }
                                    $(this).children("p:nth-child(6)").text(params[i].id);
                                    return false;
                                }
                            })
                        }
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        // 孔位切换按钮事件 24 48 96
        $('.btn-targ-ul li').click(function () {
            $(this).addClass("style-li").siblings().removeClass("style-li");
        })

        function selectElution (obj) {
            $("select[name='elutionModel'] option").each(function() {
                if($(this).text() == obj) {
                    $("select[name='elutionModel']").val($(this).val());
                }
            })
        }

        //首次进入时默认选中孔的位置
        checkPositon();

        function checkPositon() {
            var arr = [
                1,2,3,4,5,6,7,8,9,10,11,12,
                13,14,15,16,17,18,19,20,21,22,23,24,
                25,26,27,28,29,30,31,32,33,34,35,36,
                37,38,39,40,41,42,43,44,45,46,47,48,
                49,50,51,52,53,54,55,56,47,48,49,60,
                61,62,63,64,65,66,67,68,69,70,71,72,
                73,74,75,76,77,78,79,80,81,82,83,84,
                85,86,87,88,89,90,91,92,93,94,95,96
            ];

            var holeNum = $("#holeNum").val();
            var position;
            if (holeNum == "24") {
                position = "#position-24";
            }else if (holeNum == "96"){
                position = "#position-96";
            }

            //默认第一个空的位置

            for (var i = 0;i< arr.length;i++) {
                var flag = true;
                $(position).find('.ul-li-click li').each(function(key,val){
                    var tab = $(this).attr("tabindex");
                    var id = $(this).children("p:nth-child(6)").text();
                    if(id == undefined || id == null || id == ""){
                        if (arr[i] == Number(tab)) {
                            if (Number(tab) == 24 || Number(tab) == 96) {
                                if(id == undefined || id == null || id == ""){
                                    checkEvent(this);
                                }else{
                                    $("#modelContent").text("已是最后一个孔位！");
                                    $("#msg-model").modal();
                                }
                                flag = false;
                            }else {
                                if(id == undefined || id == null || id == ""){
                                    checkEvent(this);
                                    flag = false;
                                }
                            }
                            return false;
                        }
                    }
                })

                if (!flag) {
                    return false;
                }
            }
        }

        var positionText;
        var holeNum = $("#holeNum").val();
        var elutionName = $("#elutionName").val();
        // 孔位选择 添加检材事件
        $('.ul-li-click li').click(function(){
            checkEvent(this);
        })

        function checkEvent(obj) {
            $('.ul-li-click li').removeClass("four");
            $(obj).addClass("four")
            $(obj).siblings().removeClass("four");
            positionText =  $(obj).children("p:last-child").text();

            var elutionName = $(obj).children("p:nth-child(1)").text()+"";
            selectElution(elutionName);

            var sampleNo = $(obj).children("p:nth-child(2)").text();
            $("input[name='sampleNoModel']").val(sampleNo);
            $("input[name='sampleNoModel']").focus();

            var samplePlateSort = $(obj).children("p:nth-child(3)").text();
            $("select[name='samplePlateSortModel']").empty();
            $("select[name='samplePlateSortModel']").append("<option value='"+samplePlateSort+"'>"+samplePlateSort+"</option>");

            var sampleLocationSort = $(obj).children("p:nth-child(4)").text();
            $("select[name='sampleLocationSortModel']").empty();
            $("select[name='sampleLocationSortModel']").append("<option value='"+sampleLocationSort+"'>"+sampleLocationSort+"</option>");

            var samplePlateLocation = $(obj).children("p:nth-child(5)").text();
            $("input[name='samplePlateLocationModel']").val(samplePlateLocation);

            var id = $(obj).children("p:nth-child(6)").text();
            $("input[name='idModel']").val(id);

            var preExperimentalMethod = $(obj).children("p:nth-child(7)").text();
            if (typeof(preExperimentalMethod != undefined) && preExperimentalMethod != null && preExperimentalMethod != "") {
                $("select[name='preExperimentalMethodModel']").val(preExperimentalMethod);
            }else {
                $("select[name='preExperimentalMethodModel']").prop('selectedIndex', 0);
            }

            var confirmatoryMethod = $(obj).children("p:nth-child(8)").text();
            if (typeof(confirmatoryMethod != undefined) && confirmatoryMethod != null && confirmatoryMethod != "") {
                $("select[name='confirmatoryMethodModel']").val(confirmatoryMethod);
            }else {
                $("select[name='confirmatoryMethodModel']").prop('selectedIndex', 0);
            }

            var sampleTransferMethod = $(obj).children("p:nth-child(9)").text();
            if (typeof(sampleTransferMethod != undefined) && sampleTransferMethod != null && sampleTransferMethod != "") {
                $("select[name='sampleTransferMethodModel']").val(sampleTransferMethod);
            }else {
                $("select[name='sampleTransferMethodModel']").prop('selectedIndex', 0);
            }

            var sampleProperty = $(obj).children("p:nth-child(10)").text();
            if (typeof(sampleProperty != undefined) && sampleProperty != null && sampleProperty != "") {
                $("select[name='samplePropertyModel']").val(sampleProperty);
            }else {
                if (Number(elutionName) > 50) {
                    $("select[name='samplePropertyModel']").prop('selectedIndex', 2);
                }else {
                    $("select[name='samplePropertyModel']").prop('selectedIndex', 1);
                }
            }

            var tabindex = $(obj).attr("tabindex");
            $("input[name='tabindex']").val(tabindex);
            console.log(positionText)
        }

        //查看是否存在相同检材编号
        checkSampleNo();

        function checkSampleNo() {
            var holeNum = $("#holeNum").val();
            var position;
            if (holeNum == "24") {
                position = "#position-24";
            }else if (holeNum == "96"){
                position = "#position-96";
            }

            $(position).find('.ul-li-click li').each(function(key,val){
                var elutionName = $(this).children("p:nth-child(1)").text();
                var thisSampleNo = $(this).children("p:nth-child(2)").text();
                var thisTab = $(this).attr("tabindex");
                if (typeof(thisSampleNo != undefined) && thisSampleNo != null && thisSampleNo != "") {
                    var flag = false;
                    $(position).find('.ul-li-click li').each(function(key,val){
                        var sampleNo = $(this).children("p:nth-child(2)").text();
                        var tab = $(this).attr("tabindex");
                        if (typeof(sampleNo != undefined) && sampleNo != null && sampleNo != "" && thisTab != tab) {
                            if(thisSampleNo == sampleNo) {
                                flag = true;
                                $(this).removeClass();
                                $(this).addClass("li-background-3");
                            }
                        }
                    })
                    //如果不存在相同编号判断属于那种背景色
                    if (!flag) {
                        $(this).removeClass();
                        if (Number(elutionName) > 50) {
                            $(this).addClass("li-background-2");
                        }else {
                            $(this).addClass("li-background");
                        }
                    }
                }
            })
        }

        click("position-24-btn");
        click("position-48-btn");
        click("position-96-btn");

        position();

        function position() {
            if (holeNum == "24") {
                $(".position-24-btn").addClass("style-li");
            }else if (holeNum == "96") {
                $(".btn-targ-ul li").click();
            }
        }

        // 孔位模板切换事件
        function click(classId){
            $("."+classId).click(function(){
                var text = $(this).text();
                $(".btn-targ-ul li").each(function(){
                    if(text == $(this).text()){
                        $(this).addClass("style-li")
                    }
                })
                if(classId == "position-24-btn"){
                    $('.display-style').css('display','none');
                    $('.HolePosition').css('display','block');
                    $("#position-24").css("display","block");
                    $("#position-48").css("display","none");
                    $("#position-96").css("display","none");
                    $("input[name='sampleNoModel']").focus();
                }else if(classId == "position-48-btn"){
                    $('.display-style').css('display','none');
                    $('.HolePosition').css('display','block');
                    $("#position-24").css("display","none");
                    $("#position-48").css("display","block");
                    $("#position-96").css("display","none");
                    $("input[name='sampleNoModel']").focus();
                }else if(classId == "position-96-btn"){
                    $('.display-style').css('display','none');
                    $('.HolePosition').css('display','block');
                    $("#position-24").css("display","none");
                    $("#position-48").css("display","none");
                    $("#position-96").css("display","block");
                    $("input[name='sampleNoModel']").focus();
                }
            })
        }

        // 表格 孔位切换事件
        $(".position-table-show").click(function(){
            tableClick("tableList");
        })


        function tableClick(obj) {
            $("#allChecked").attr("checked",false);
            var param = {
                "entity": sampleInfo(),
                "holeNum":$("#holeNum").val()
            }
            var tabValue = $("#tabValue").val();
            var stage = "";
            if ("extractTab" == tabValue) {
                stage = "tq";
            }else if ("pcrTab" == tabValue) {
                stage = "kz";
            }else if ("syTab" == tabValue) {
                stage = "sy";
            }

            $.ajax({
                url:"<%=path%>/center/sample/getSampleInfoList.html?stage=" + stage,
                type:"post",
                data:JSON.stringify(param),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        $("#sampleInfoVoListTbody").empty();
                        var len = data.sampleInfoVoList.length;
                        var htmlStr = "";
                        var sample;
                        for(var i = 0; i < len; i++){
                            sample = data.sampleInfoVoList[i];
                            htmlStr += "<tr>";
                            htmlStr += "<td><input type='hidden' name='id' value='"+sample.entity.id+"'/>";
                            htmlStr += "<input type='hidden' name='sampleTableId' value='"+sample.entity.sampleTableId+"'/>";
                            htmlStr += "<input type='hidden' name='boardNo' value='"+sample.entity.boardNo+"'/>";
                            htmlStr += "<input type='hidden' name='elution' value='"+sample.entity.elution+"'/>";
                            htmlStr += "<input type='hidden' name='sampleNo' value='"+sample.entity.sampleNo+"'/>";
                            htmlStr += "<input type='hidden' name='samplePlateSort' value='"+sample.entity.samplePlateSort+"'/>";
                            htmlStr += "<input type='hidden' name='sampleLocationSort' value='"+sample.entity.sampleLocationSort+"'/>";
                            htmlStr += "<input type='hidden' name='samplePlateLocation' value='"+sample.entity.samplePlateLocation+"'/>";
                            htmlStr += "<input type='hidden' name='preExperimentalMethod' value='"+sample.entity.preExperimentalMethod+"'/>";
                            htmlStr += "<input type='hidden' name='confirmatoryMethod' value='"+sample.entity.confirmatoryMethod+"'/>";
                            htmlStr += "<input type='hidden' name='sampleTransferMethod' value='"+sample.entity.sampleTransferMethod+"'/>";
                            htmlStr += "<input type='hidden' name='sampleProperty' value='"+sample.entity.sampleProperty+"'/>";
                            htmlStr += "<label class='labelBox'><input type='checkbox' name='box' onclick='selectAll(this)'>"+(i + 1)+"</label></td>";
                            htmlStr += "<td>"+ sample.entity.sampleNo +"</td>";
                            htmlStr += "<td>"+ sample.elutionName +"</td>";
                            htmlStr += "<td>"+ sample.entity.samplePlateSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.sampleLocationSort +"</td>";
                            htmlStr += "<td>"+ sample.entity.samplePlateLocation +"</td>";
                            htmlStr += "<td>"+ sample.confirmatoryMethodName +"</td>";
                            htmlStr += "<td>"+ sample.preExperimentalMethodName +"</td>";
                            htmlStr += "<td>"+ sample.sampleTransferMethodName +"</td>";
                            var sampleTable = data.sampleTable;
                            if ("1" == sampleTable.isCreate) {

                            }else {
                                htmlStr += "<td>";
                                <%--htmlStr += "<span class='view'><span><img src='<%=path%>/automatedWork/img/see.png' alt=''></span><span>查看</span></span>";--%>
                                htmlStr += "<span onclick= 'deleteClick(this)'>";
                                htmlStr += "<span>";
                                htmlStr += "<img src='<%=path%>/automatedWork/img/tra.png' alt=''>";
                                htmlStr += "</span><span>删除</span></span>";
                                htmlStr += "</td>";
                            }
                            htmlStr +="</tr>";
                        }
                        $("#sampleInfoVoListTbody").append(htmlStr);

                        if (obj == "tableList") {
                            $('.HolePosition').css('display','none');
                            $('.display-style').css('display','block');
                            $('.btn-targ-ul li').removeClass("style-li");
                        }else {
                            $("#sampleMultipleModal").modal('hide');
                        }
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        function sampleInfo() {
            var sample = {};

            var tabValue = $("#tabValue").val();
            if ("extractTab" == tabValue) {
                sample.extractPlateId = $("#extractPlateId").val();
            }else if ("pcrTab" == tabValue) {
                sample.pcrPlateId = $("#pcrPlateId").val();
            }else if ("syTab" == tabValue) {
                sample.syPlateId = $("#syPlateId").val();
            }else {
                sample.sampleTableId = $("#sampleTableId").val();
            }
//            sample.boardNo = $("#boardNo").val();
            sample.elution = $("#elution").val();

            return sample;
        }

        var tabValue = $("#tabValue").val();
        if (tabValue != undefined && tabValue != null && tabValue != "") {
            if ("extractTab" == tabValue) {
                $(".position-24-btn").addClass("hide");
            }else if ("pcrTab" == tabValue) {
                $(".position-24-btn").addClass("hide");
            }else if ("syTab" == tabValue) {
                $(".position-24-btn").addClass("hide");
            }
        }

        //返回上一页
        $("#backBtn").on("click", function () {
            location.href = "<%=path%>/center/sample/sampleTableList.html?tabValue=" + $("#tabValue").val();
        });

        function getSampleInfo(obj) {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#sampleInfoVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.id = $("input[name='id']", $(this)).val();
                    if (obj == "multiple") {
                        sample.elution = $("select[name='elutionMultipleModel'] :checked").val();
                    }else {
                        sample.elution = $("select[name='elution']", $(this)).val();
                    }
                    sample.sampleTableId = $("#sampleTableId").val();
                    sample.boardNo = $("input[name='boardNo']", $(this)).val();
                    sample.sampleNo = $("input[name='sampleNo']", $(this)).val();
                    sample.samplePlateSort = $("input[name='samplePlateSort']", $(this)).val();
                    sample.sampleLocationSort = $("input[name='sampleLocationSort']", $(this)).val();
                    sample.samplePlateLocation = $("input[name='samplePlateLocation']", $(this)).val();
                    sample.preExperimentalMethod = $("input[name='preExperimentalMethod']", $(this)).val();
                    sample.confirmatoryMethod = $("input[name='confirmatoryMethod']", $(this)).val();
                    sample.sampleTransferMethod = $("input[name='sampleTransferMethod']", $(this)).val();
                    sample.sampleProperty = $("input[name='sampleProperty']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }

        function checkCount() {
            var checkCount = 0;
            $("tr", "#sampleInfoVoListTbody").each(function () {
                var checkBox = $("input[name='box']", $(this)).is(":checked");
                if (checkBox) {
                    checkCount++;
                }
            });

            return checkCount;
        }

        //删除样本板
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                $("#modelContent").text("请选择要删除的检材！");
                $("#msg-model").modal();
                return false;
            }

            $("#identification").val("2");
            $().confirmOperation("确认删除吗?");
        });

        // 添加检材保存按钮事件
        $("#saveBtn").click(function(){
            console.log(positionText);
            if(positionText == undefined || positionText == null || positionText == ""){
                $("#promptModel-error").modal();
                return false;
            }else{
                var id = $("input[name='idModel']").val();
                //判断保存的孔位是否已经存在检材信息
                if (id == undefined || id == null || id == "") {
                    saveInfo();
                }else {
                    $("#addModel").modal('show');
                }
            }
        })

        $("#confirmBtn").click(function(){
            $("#addModel").modal('hide');
            saveInfo();
        });

        function saveInfo() {
            var sampleNo = $("input[name='sampleNoModel']").val();
            if(sampleNo == undefined || sampleNo == null || sampleNo == ""){
                $("#modelContent").text("请选择检材放置孔位！");
                $("#msg-model").modal();
                return false;
            }

            var sample = {};

            sample.sampleNo = $("input[name='sampleNoModel']").val();
            sample.boardNo = $("#boardNo").val();

            var param = {
                "entity": sample
            }

            $.ajax({
                url:"<%=path%>/center/sample/getSampleInfoList.html",
                type:"post",
                data:JSON.stringify(param),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        var len = data.sampleInfoVoList.length;
                        if (len > 0) {
                            $("#tipsModel").modal('show');
                        }else {
                            saveSampleInfo("noSpilt");
                        }
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        $("#noBtn").on('click', function () {
            $("#tipsModel").modal('hide');
            saveSampleInfo("noSpilt");
        })

        $("#yesBtn").on("click", function(){
            $("#tipsModel").modal('hide');
            var sampleNoModel = $("input[name='sampleNoModel']").val();
            $("input[name='spiltSampleNoModel']").val(sampleNoModel + "-");
            $("#splitModel").modal('show');
        });

        $("#saveSpiltBtn").on("click", function(){
            $("#splitModel").modal('hide');
            saveSampleInfo("spilt");
        });

        $("#delBtn").click(function(){
            $("#identification").val("1");
            $().confirmOperation("确认删除吗?");
        })

        $.fn.confirmOperation = function (obj) {
            $("#confirmContent").text(obj);
            $("#confirm-model").modal();
        }

        $("#confirmBtn").on("click", function (){
            var identification = $("#identification").val();
            if (identification == "1") {
                var id = $("input[name='idModel']").val();
                if (id != 'undefined' && id != null && id != "") {
                    var sampleArr = new Array();
                    var sample = {};
                    sample.id = id;
                    sampleArr.push(sample);

                    deletePlate(sampleArr,'1');
                }
            }else if (identification == "2") {
                deletePlate(getSampleInfo("single"),"2");
            }else if (identification == "3") {
                var sampleArr = new Array();
                var sample = {};
                sample.id = $("#param1").val();
                sample.sampleLocationSort = $("#param2").val();
                sampleArr.push(sample);

                deletePlate(sampleArr, "2");
            }
        });

        function saveSampleInfo(obj) {
            $.ajax({
                url:"<%=path%>/center/sample/saveSampleInfo.html",
                type:"post",
                data:JSON.stringify(getParams(obj)),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        var position;
                        if (holeNum == "24") {
                            position = "#position-24";
                        }else if (holeNum == "96"){
                            position = "#position-96";
                        }
                        var sampleInfo = data.sampleInfo;
                        if (sampleInfo != null) {
                            $(position).find('.ul-li-click li').each(function(key,val){
                                if($(this).children("p:last-child").text() == positionText) {
                                    $(this).removeClass();
                                    $(this).children("p:nth-child(1)").text(sampleInfo.elutionName);
                                    var elution = $(this).children("p:nth-child(1)").text();
                                    if (Number(elution) > 50) {
                                        $(this).addClass("li-background-2");
                                    }else {
                                        $(this).addClass("li-background");
                                    }
                                    $(this).children("p:nth-child(2)").text(sampleInfo.sampleNo);
                                    $(this).children("p:nth-child(3)").text(sampleInfo.samplePlateSort);
                                    $(this).children("p:nth-child(4)").text(sampleInfo.sampleLocationSort);
                                    $(this).children("p:nth-child(5)").text(sampleInfo.samplePlateLocation);
                                    $(this).children("p:nth-child(6)").text(sampleInfo.id);
                                    $(this).children("p:nth-child(7)").text(sampleInfo.preExperimentalMethod);
                                    $(this).children("p:nth-child(8)").text(sampleInfo.confirmatoryMethod);
                                    $(this).children("p:nth-child(9)").text(sampleInfo.sampleTransferMethod);
                                    $(this).children("p:nth-child(10)").text(sampleInfo.sampleProperty);
                                    $("input[name='idModel']").val(data.id);
                                }
                            })
                        }
                        //模态框消失
                        modelHide();
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
        }

        document.onkeydown = function (e) {
            var ev = document.all ? window.event : e;
            if (ev.keyCode == 13) {
                $("#saveBtn").click();
                e.preventDefault();
            }
        }

        function getParams(obj){
            var sampleArr = new Array();
            var sample = {};

            sample.id = $("input[name='idModel']").val();
            sample.sampleTableId = $("#sampleTableId").val();
            sample.boardNo = $("#boardNo").val();
            if ("spilt" == obj) {
                sample.sampleNo = $("input[name='spiltSampleNoModel']").val();
            }else {
                sample.sampleNo = $("input[name='sampleNoModel']").val();
            }
            sample.elution = $("select[name='elutionModel'] :checked").val();
            sample.elutionName = $("select[name='elutionModel'] :checked").text();
            sample.samplePlateSort = $("select[name='samplePlateSortModel'] :checked").val();
            sample.sampleLocationSort = $("select[name='sampleLocationSortModel'] :checked").val();
            sample.samplePlateLocation = $("input[name='samplePlateLocationModel']").val();
            sample.preExperimentalMethod = $("select[name='preExperimentalMethodModel'] :checked").val();
            sample.confirmatoryMethod = $("select[name='confirmatoryMethodModel'] :checked").val();
            sample.sampleTransferMethod = $("select[name='sampleTransferMethodModel'] :checked").val();
            sample.sampleProperty = $("select[name='samplePropertyModel'] :checked").val();

            sampleArr.push(sample);
            return sampleArr;
        }

    })
</script>

</body>

</html>