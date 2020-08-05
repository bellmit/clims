<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${loginTitleName}</title>
    <%@ include file="../../common/newLink.jsp" %>
    <%@ include file="../../common/include.jsp" %>
    <%
        String path = request.getContextPath();
    %>
</head>
<body class="flat-blue">
<div class="app-container">
    <div class="row content-container">
        <%@ include file="../../common/platformName.jsp" %>
        <div style="margin-top:60px;">
            <%@ include file="../../common/menuManagement.jsp" %>
            <div class="container-right">
                <div class="container-fluid Scrollbar">
                    <div class="side-body padding-top">
                        <div class="container-style">
                            <div class="row posi-rel">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-0">
                                    <div>
                                        <div class="top-tag-box">
                                            <div class="table-tag">
                                                <div class="title-first-span">
                                                    <span></span>
                                                    <span>检材管理</span>
                                                </div>
                                                <ul class="ul-btns">
                                                    <!-- <li></li> -->
                                                    <li type="button" id="exportSampleInfo" style="font-weight:400; margin-right: 50px;">
                                                        <span><img src="<%=path%>/automatedWork/img/dc001.png" alt=""></span>
                                                        <span>导出检材信息</span>
                                                    </li>
                                                    <li type="button" id='deleteBtn'>
                                                        <span><img src="<%=path%>/automatedWork/img/1102.png" alt=""></span>
                                                        <span>批量删除</span>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="seach-right-box">
                                                <div>
                                                    <input type="text" placeholder="请输入板号" id="boardNo" value="${query.entity.boardNo}">
                                                    <input type="text" placeholder="请输入检材编号" id="sampleNo" value="${query.entity.sampleNo}">
                                                    <button class="btn btn-primary" id="searchInfoBtn">查 询</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="container-bottom">
                                            <table class="table table-bordered table-hover table-striped">
                                                <thead>
                                                <tr>
                                                    <th style="width: 5%;">
                                                        <label class="allLabel">
                                                            <input type="checkbox" name="allChecked" id="allChecked"/>序号
                                                        </label>
                                                    </th>
                                                    <th style="width: 9%">检材编号</th>
                                                    <th style="width: 9%">样板名</th>
                                                    <th style="width: 7%">提取板孔位</th>
                                                    <th style="width: 7%">扩增板孔位</th>
                                                    <th style="width: 7%">电泳板孔位</th>
                                                    <th style="width: 9%">预测验方法</th>
                                                    <th style="width: 6%">确证方法</th>
                                                    <th style="width: 8%">检材转移方法</th>
                                                    <th style="width: 6%">洗脱体积</th>
                                                    <th style="width: 6%">检材属性</th>
                                                    <th style="width: 5%">创建者</th>
                                                    <th style="width: 7%;">创建时间</th>
                                                    <th style="width: 11%;">操作</th>
                                                </tr>
                                                </thead>
                                                <tbody id="sampleInfoVoListTbody">
                                                <c:forEach items="${sampleInfoVoList}" var="sampleInfoVo" varStatus="s">
                                                    <tr>
                                                        <td>
                                                            <input type="hidden" name="id" value="${sampleInfoVo.entity.id}"/>
                                                            <input type="hidden" name="sampleNo" value="${sampleInfoVo.entity.sampleNo}"/>
                                                            <input type="hidden" name="boardNo" value="${sampleInfoVo.entity.boardNo}"/>
                                                            <input type="hidden" name="extractPlateLocation" value="${sampleInfoVo.entity.extractPlateLocation}"/>
                                                            <input type="hidden" name="pcrPlateLocation" value="${sampleInfoVo.entity.pcrPlateLocation}"/>
                                                            <input type="hidden" name="syPlateLocation" value="${sampleInfoVo.entity.syPlateLocation}"/>
                                                            <input type="hidden" name="preExperimentalMethodName" value="${sampleInfoVo.preExperimentalMethodName}"/>
                                                            <input type="hidden" name="preExperimentalMethod" value="${sampleInfoVo.entity.preExperimentalMethod}"/>
                                                            <input type="hidden" name="confirmatoryMethodName" value="${sampleInfoVo.confirmatoryMethodName}"/>
                                                            <input type="hidden" name="confirmatoryMethod" value="${sampleInfoVo.entity.confirmatoryMethod}"/>
                                                            <input type="hidden" name="sampleTransferMethodName" value="${sampleInfoVo.sampleTransferMethodName}"/>
                                                            <input type="hidden" name="sampleTransferMethod" value="${sampleInfoVo.entity.sampleTransferMethod}"/>
                                                            <input type="hidden" name="elution" value="${sampleInfoVo.entity.elution}"/>
                                                            <input type="hidden" name="elutionName" value="${sampleInfoVo.elutionName}"/>
                                                            <input type="hidden" name="sampleProperty" value="${sampleInfoVo.entity.sampleProperty}"/>
                                                            <input type="hidden" name="samplePropertyName" value="${sampleInfoVo.samplePropertyName}"/>
                                                            <input type="hidden" name="createPerson" value="${sampleInfoVo.entity.createPerson}"/>
                                                            <input type="hidden" name="createDatetime" value="<fmt:formatDate value='${sampleInfoVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>"/>
                                                            <label class="labelBox">
                                                                <input type="checkbox" name="box">${s.count}
                                                            </label>
                                                        </td>
                                                        <td>${sampleInfoVo.entity.sampleNo}</td>
                                                        <td>${sampleInfoVo.entity.boardNo}</td>
                                                        <td>${sampleInfoVo.entity.extractPlateLocation}</td>
                                                        <td>${sampleInfoVo.entity.pcrPlateLocation}</td>
                                                        <td>${sampleInfoVo.entity.syPlateLocation}</td>
                                                        <td>${sampleInfoVo.preExperimentalMethodName}</td>
                                                        <td>${sampleInfoVo.confirmatoryMethodName}</td>
                                                        <td>${sampleInfoVo.sampleTransferMethodName}</td>
                                                        <td>${sampleInfoVo.elutionName}</td>
                                                        <td>${sampleInfoVo.samplePropertyName}</td>
                                                        <td>${sampleInfoVo.entity.createPerson}</td>
                                                        <td>
                                                            <fmt:formatDate value='${sampleInfoVo.entity.createDatetime}' pattern='yyyy-MM-dd HH:mm:ss'/>
                                                        </td>
                                                        <td>
                                                             <span class="view">
                                                                 <span><img src="<%=path%>/automatedWork/img/see.png" alt=""></span>
                                                                 <span>查看</span>
                                                             </span>
                                                             <span class="delete">
                                                                 <span><img src="<%=path%>/automatedWork/img/tra.png" alt=""></span>
                                                                 <span>删除</span>
                                                             </span>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" name="actionName" id="actionName" value="<%=path%>/center/sample/sampleInfoList">
                                <%@ include file="../../common/pagefoot.jsp" %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@ include file="../../common/foot.jsp" %>
        </div>
        <div class="modal fade modal-primary" id="sampleModal" tabindex="-1" role="dialog"
             aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <div>查看详情</div>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="modelnewStyle">
                                <div>
                                    <span></span>
                                    <span>检材编号</span>
                                    <span name="sampleNoModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>样板名</span>
                                    <span name="boardNoModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>提取板孔位</span>
                                    <span name="extractPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>扩增板孔位</span>
                                    <span name="pcrPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>电泳板孔位</span>
                                    <span name="syPlateLocationModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>预测验方法</span>
                                    <span name="preExperimentalMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>确证方法</span>
                                    <span name="confirmatoryMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检材转移方法</span>
                                    <span name="sampleTransferMethodModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>洗脱体积</span>
                                    <span name="elutionModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>检材属性</span>
                                    <span name="samplePropertyModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建者</span>
                                    <span name="createPersonModel"></span>
                                </div>
                                <div>
                                    <span></span>
                                    <span>创建时间</span>
                                    <span name="createDatetimeModel"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <div class="newstyle">
                            <button data-dismiss="modal" class="btn btn-info btn-sm" type="button">关 闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- BEGIN JS -->
<%@ include file="../../common/newScript.jsp" %>
<script src="${pageContext.request.contextPath}/js/page.js" ></script>
<script src="${pageContext.request.contextPath}/js/check.js" ></script>
<script type="">
    $(function () {
        'use strict';

        //导出检材信息
        $("#exportSampleInfo").on("click", function () {
            if (checkCount() <= 0) {
                $("#modelContent").text("请选择要导出的检材信息！");
                $("#msg-model").modal();
                return false;
            }
            exportSampleInfo("record");
        });

        function exportSampleInfo(obj) {
            if (obj == "record"){
                location.href = "<%=path%>/center/sample/exportSampleInfoRecord.html?sampleInfoList=" + JSON.stringify(sampleInfo());
            }
        }

        //删除扩增信息
        $(".delete", "#sampleInfoVoListTbody").on("click", function () {
            var id = $("input[name='id']", $(this).parent().parent()).val();
            $("#identification").val("1");
            $("#param1").val(id);
            $().confirmSampleInfoOperation("确认删除吗?");
        });

        function deleteSampleInfo(obj) {
            $.ajax({
                url:"<%=path%>/center/sample/deleteSampleInfo.html",
                type:"post",
                data:JSON.stringify(obj),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                success: function (data) {
                    if(data.success){
                        location.href = "<%=path%>/center/sample/sampleInfoList.html";
                    }else {
                        alert("操作失败!");
                    }
                },
                error:function(data){
                    alert("操作失败!");
                }
            });
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

        //查看样本信息
        $(".view", "#sampleInfoVoListTbody").on("click", function () {
            var sampleNo = $("input[name='sampleNo']", $(this).parent().parent()).val();
            var boardNo = $("input[name='boardNo']", $(this).parent().parent()).val();
            var extractPlateLocation = $("input[name='extractPlateLocation']", $(this).parent().parent()).val();
            var pcrPlateLocation = $("input[name='pcrPlateLocation']", $(this).parent().parent()).val();
            var syPlateLocation = $("input[name='syPlateLocation']", $(this).parent().parent()).val();
            var preExperimentalMethod = $("input[name='preExperimentalMethodName']", $(this).parent().parent()).val();
            var confirmatoryMethod = $("input[name='confirmatoryMethodName']", $(this).parent().parent()).val();
            var sampleTransferMethod = $("input[name='sampleTransferMethodName']", $(this).parent().parent()).val();
            var elution = $("input[name='elutionName']", $(this).parent().parent()).val();
            var sampleProperty = $("input[name='samplePropertyName']", $(this).parent().parent()).val();
            var createPerson = $("input[name='createPerson']", $(this).parent().parent()).val();
            var createDatetime = $("input[name='createDatetime']", $(this).parent().parent()).val();

            $("div.has-error", "#sampleModal").addClass("hide");

            $("span[name='sampleNoModel']", "#sampleModal").text(isFullReturn(sampleNo));
            $("span[name='boardNoModel']", "#sampleModal").text(isFullReturn(boardNo));
            $("span[name='extractPlateLocationModel']", "#sampleModal").text(isFullReturn(extractPlateLocation));
            $("span[name='pcrPlateLocationModel']", "#sampleModal").text(isFullReturn(pcrPlateLocation));
            $("span[name='syPlateLocationModel']", "#sampleModal").text(isFullReturn(syPlateLocation));
            $("span[name='preExperimentalMethodModel']", "#sampleModal").text(isFullReturn(preExperimentalMethod));
            $("span[name='confirmatoryMethodModel']", "#sampleModal").text(isFullReturn(confirmatoryMethod));
            $("span[name='sampleTransferMethodModel']", "#sampleModal").text(isFullReturn(sampleTransferMethod));
            $("span[name='elutionModel']", "#sampleModal").text(isFullReturn(elution));
            $("span[name='samplePropertyModel']", "#sampleModal").text(isFullReturn(sampleProperty));
            $("span[name='createPersonModel']", "#sampleModal").text(isFullReturn(createPerson));
            $("span[name='createDatetimeModel']", "#sampleModal").text(isFullReturn(createDatetime));

            $("#sampleModal").modal('show');
        });

        //删除样本信息
        $("#deleteBtn").on("click", function () {
            if (checkCount() <= 0) {
                $("#modelContent").text("请选择要删除的检材！");
                $("#msg-model").modal();
                return false;
            }
            $("#identification").val("2");
            $().confirmSampleInfoOperation("确认删除吗?");
        });

        $.fn.confirmSampleInfoOperation = function (obj) {
            $("#confirmContent").text(obj);
            $("#confirm-model").modal();
        }

        $("#confirmBtn").on("click", function (){
            var identification = $("#identification").val();
            if (identification == "1") {
                var sampleArr = new Array();
                var sample = {};
                sample.id = $("#param1").val();
                sampleArr.push(sample);

                deleteSampleInfo(sampleArr);
            }else if (identification == "2") {
                deleteSampleInfo(sampleInfo());
            }
        });

        function sampleInfo() {
            var sampleArr = new Array();
            var sample = {};
            $("tr", "#sampleInfoVoListTbody").each(function () {
                sample = {};
                var checkBox = $("input[name='box']", $(this)).is(":checked");

                if (checkBox) {
                    sample.id = $("input[name='id']", $(this)).val();

                    sampleArr.push(sample);
                }
            });

            return sampleArr;
        }
    });
</script>
</body>

</html>