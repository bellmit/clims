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
        body > .Modular:nth-child(1) .panel-body:nth-child(2) .row {
            border: 1px dashed #d0d0d0;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(2) .row .col-md-4 {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(2) .row .col-md-4 + .col-md-4 {
            border-top: 1px solid #f0f0f0;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(2) .row .col-md-4 span:nth-child(1) {
            display: inline-block;
            width: 50%;
            text-align: right;
            color: #8e8e8e;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) {
            margin-bottom: 5px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div {
            display: inline-block;
            width: 11%;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(1),
        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(2) {
            width: 20%;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div::before {
            content: "";
            width: 18px;
            height: 18px;
            border-radius: 50%;
            float: left;
            margin-top: 2px;
            margin-right: 5px;
            color: #fff;
            line-height: 18px;
            text-align: center;
            font-weight: 600;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(1)::before {
            background: #ff5a56;
            margin-top: 3px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(2)::before {
            content: "复";
            margin-top: 3px;
            background: #4019e3;
            font-size: 12px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(3)::before {
            background: #d3d3d3;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(4)::before {
            background: #b2551b;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(5)::before {
            background: #1bb29b;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(6)::before {
            background: #d9d9d9;
            content: "×";
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(7) {
            display: inline-block;
            font: normal normal normal 14px/1 FontAwesome;
            font-size: inherit;
            text-rendering: auto;
            -webkit-font-smoothing: antialiased;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(1) > div:nth-child(7)::before {
            content: "\f00c";
            background: #50c987;
            font-size: 11px;
            margin-top: -1px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) > div {
            padding: 0 5px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul {
            border: 1px solid #d0d0d0;
            padding: 10px 30px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li {
            width: 86%;
            height: 35px;
            line-height: 35px;
            background: #f0f0f0;
            border: 2px dashed #dadada;
            color: #000;
            font-weight: 600;
            border-radius: 50px;
            margin-top: 7px;
            position: relative;
            padding-left: 10px;
            cursor: pointer;
            display: inline-block;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.problem {
            /*问题*/
            background: #ff5a56;
            color: #fff;
            border: 2px solid #ff5a56;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.problem i.removeProblem {
            position: absolute;
            left: -22px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 20px;
            cursor: pointer;
            color: #d9d9d9 !important;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.problem i.removeProblem:hover {
            color: #ff5a56 !important;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.active i {
            /*勾选*/
            position: absolute;
            top: -10px;
            right: -10px;
            font-size: 20px !important;
            color: #50c987 !important;
            padding: 0px !important;
            background: none !important;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.review .reviewI {
            /*复检*/
            position: absolute;
            top: -10px;
            right: -10px;
            font-size: 12px;
            background: #4019e3;
            color: #fff;
            border-radius: 50%;
            padding: 5px;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.review .reviewI::before {
            content: "复";
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.noGene {
            /*无基因*/
            color: #d3d3d3;
        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.board {
            /*本板比对*/
            background: #b2551b;
            color: #fff;
            border: 2px solid #b2551b;
        }
        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.board::before{
            content: "本";
            font-size: 10px;
            width: 20px;
            height: 20px;
            text-align: center;
            line-height: 20px;
            color: #fff;
            background: #b2551b;
            border-radius: 50%;
            position: absolute;
            left:-25px;
            top: 50%;
            margin-top: -10px;


        }

        body > .Modular:nth-child(1) .panel-body:nth-child(3) .row:nth-child(2) ul li.audited {
            /*已审核*/
            background: #1bb29b;
            color: #fff;
            border: 2px solid #1bb29b;
        }

        .btn-box {
            margin: 0px;
            box-shadow: 0px 0px 10px 5px #ebebeb;
            position: fixed;
            bottom: 0px;
            width: 100%;
            margin-left: -10px !important;
            margin-top: 0px !important;
            text-align: right;
        }

        .popover {
            background: #ffe8e8 !important;;
            color: #ff5a56 !important;;
            border: 1px dashed #ff5a56 !important;;
            font-weight: 600 !important;
        }

        .popover .arrow {
            border-top-color: #ff5a56 !important;
        }

        .popover .arrow::after {
            border-top-color: #ffe8e8 !important;;
        }
        .disabled{
            cursor:not-allowed !important
        }
    </style>
</head>
<body>
<div class="row Modular" style="padding-bottom: 60px;">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading yellow">
                <div>检验复核详情</div>
            </div>
            <form id="saveform" method="post">
                <div class="panel-body">
                    <div class="row">

                    </div>
                </div>
            </form>
            <div class="panel-body">
                <div class="row">
                    <div>结果不好
                        <button class="btn btn-blue btn-sm problemBtn" data-toggle="popover" data-placement="top"
                                data-content='无基因,本板比对,已审核不可问题标记' data-trigger="focus">问题标记
                        </button>
                    </div>
                    <div>复检
                        <button class="btn btn-blue btn-sm reviewBtn">复检标记</button>
                        <button class="btn btn-red btn-sm reviewRemoveBtn">复检删除</button>
                    </div>
                    <div>无基因</div>
                    <div>本板比对</div>
                    <div>已审核</div>
                    <div>删除标记</div>
                    <div>选中状态</div>
                </div>
                <div class="row" id="row">
                    <div class="col-md-3">
                        <ul>
                            <c:forEach items="${limsSampleGeneVo1}" var="list" varStatus="s">
                                <li auditStatus="${list.entity.auditStatus}" reviewStatus="${list.entity.reviewStatus}" colorMark="${list.colorMark}">
                                    <input type="hidden" name="geneId" value="${list.entity.geneId}">
                                    <input type="hidden" name="caseId" value="${list.entity.caseId}">
                                        ${list.sampleNo}
                                    <c:if test="${not empty list.entity.boardPosition}">,</c:if>
                                        ${list.entity.boardPosition}
                                </li>
                                <c:if test="${not empty list.sampleNo}">
                                    <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                        <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                            查看
                                        </button>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <ul>
                            <c:forEach items="${limsSampleGeneVo2}" var="list" varStatus="s">
                                <li auditStatus="${list.entity.auditStatus}" reviewStatus="${list.entity.reviewStatus}" colorMark="${list.colorMark}">
                                    <input type="hidden" name="geneId" value="${list.entity.geneId}">
                                        ${list.sampleNo}
                                    <c:if test="${not empty list.entity.boardPosition}">,</c:if>
                                        ${list.entity.boardPosition}
                                </li>
                                <c:if test="${not empty list.sampleNo}">
                                    <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                        <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                            查看
                                        </button>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <ul>
                            <c:forEach items="${limsSampleGeneVo3}" var="list" varStatus="s">
                                <li auditStatus="${list.entity.auditStatus}" reviewStatus="${list.entity.reviewStatus}" colorMark="${list.colorMark}">
                                    <input type="hidden" name="geneId" value="${list.entity.geneId}">
                                        ${list.sampleNo}
                                    <c:if test="${not empty list.entity.boardPosition}">,</c:if>
                                        ${list.entity.boardPosition}
                                </li>
                                <c:if test="${not empty list.sampleNo}">
                                    <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                        <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                            查看
                                        </button>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="col-md-3">
                        <ul>
                            <c:forEach items="${limsSampleGeneVo4}" var="list" varStatus="s">
                                <li auditStatus="${list.entity.auditStatus}" reviewStatus="${list.entity.reviewStatus}" colorMark="${list.colorMark}">
                                    <input type="hidden" name="geneId" value="${list.entity.geneId}">
                                        ${list.sampleNo}
                                    <c:if test="${not empty list.entity.boardPosition}">,</c:if>
                                        ${list.entity.boardPosition}
                                </li>
                                <c:if test="${not empty list.sampleNo}">
                                    <a href="<%=path%>/LimsSampleGeneController/queryLimsSampleGeneByCeneId?ceneId=${list.entity.geneId}">
                                        <button type="button" class="btn-icon btn-icon-yellow btn-icon-chakan-yellow">
                                            查看
                                        </button>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row btn-box">
    <div class="col-md-12">
        <%--<button class="btn btn-blue btn-lang analysisCompleted" type="button">分析完成</button>--%>
        <button class="btn btn-blue checkboxAll" type="button">全选</button>
        <button class="btn btn-yellow btn-lang save" type="button">确认审核并上传</button>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>

    $(function () {
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
        //添加状态
        $("li").each(function () {
            switch ($(this).attr("auditstatus")) {
                case "1":
                    $(this).addClass("audited")
                    break;
                default:
                    $(this).removeAttr("class")
            }
            if (($(this).attr("reviewstatus") == "1")) {
                $(this).addClass("review").append('<i aria-hidden="true" class="fa reviewI"></i>')
            }
        });
        //添加状态
        $("li").each(function () {
            switch ($(this).attr("colorMark")) {
                case "4":
                    $(this).addClass("noGene")
                    break;
                case "5":
                    $(this).addClass("board")
                    break;
                case "6":
                    $(this).addClass("problem").append('<i aria-hidden="true" class="fa fa-times-circle removeProblem"></i>')
                    break;
            }
        })
        var s = 1;
        $(".checkboxAll").click(function () {
            //alert($(this).attr("class"));
            if(s == 1){
                $("#row .col-md-3 ul li").each(function(){
                    //判断无基因型的不选中
//                    if($(this).attr("class")!='noGene'){
//                        if($(this).find("input[name='geneId']").val() != ""){
//                            if($(this).attr("auditstatus") == 1){
//                                $(this).addClass("active");
//                                $(this).append('<i class="fa fa-check-circle" aria-hidden="true"></i>');
//                            }
//                        }
//                    }

                    if($(this).hasClass("noGene")){

                    }else{
                        if($(this).find("input[name='geneId']").val() != ""){
                            $(this).addClass("active");
                            $(this).append('<i class="fa fa-check-circle" aria-hidden="true"></i>');

                        }
                    }

                })
                s = 2;
            }else{
                $("#row .col-md-3 ul li").each(function(){
                    $(this).removeClass("active");
                    $(this).find("i").remove();
                })
                s = 1;
            }


        })

        //勾选操作
        $("li").not(".noGene").click(function () {
            if ($(this).attr("class") && $(this).hasClass("active")) {
                $(this).removeClass("active").children("i").remove()
                if ($(this).hasClass("problem review")) {
                    $(this).append('<i aria-hidden="true" class="fa fa-times-circle removeProblem"></i>')
                    $(this).append('<i aria-hidden="true" class="fa reviewI"></i>')
                } else if ($(this).hasClass("review")) {
                    $(this).append('<i aria-hidden="true" class="fa reviewI"></i>')
                } else if ($(this).hasClass("problem")) {
                    $(this).append('<i aria-hidden="true" class="fa fa-times-circle removeProblem"></i>')
                }
                if ($("li[class='board active']").length == 0 && $("li[class='noGene active']").length == 0 && $("li[class='audited active']").length == 0) {
                    $(".problemBtn").prop("disabled", false)
                    $('[data-toggle="popover"]').popover('hide')
                }
            } else {
                if ($(this).hasClass("problem review")) {
                    $(this).addClass("active").find(".reviewI").remove()
                    $(this).append('<i class="fa fa-check-circle" aria-hidden="true"></i>')
                } else if ($(this).hasClass("problem")) {
                    $(this).addClass("active")
                    $(this).append('<i class="fa fa-check-circle" aria-hidden="true"></i>')
                } else {
                    if (!(!$(this).attr("class") || $(this).hasClass("review"))) {
                        $(".problemBtn").prop("disabled", true)
//                        $('[data-toggle="popover"]').popover('show')
                    }
                    $(this).addClass("active").children("i").remove()
                    $(this).append('<i class="fa fa-check-circle" aria-hidden="true"></i>')
                }
            }
        })
        //问题标记
        $(".problemBtn").click(function () {
            var id = '${id}';
            var limsSampleGeneArr = []
            $(".active").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                limsSampleGene.auditStatus = $(this).attr("auditStatus")
                limsSampleGeneArr.push(limsSampleGene)
            })
            var params = {
                "limsSampleGeneArr": limsSampleGeneArr,
            }
            $.ajax({
                url: "<%=path%>/center/questionFlag",
                type: "post",
                data: {"params": JSON.stringify(params)},
                dataType: "json",
                success: function (data) {
                    location.href = "<%=path%>/center/reviewDetails?id=" + id;
                },
                error: function (e) {
                    alert(e);
                }
            });
        })
        //删除问题标记
        $("ul").on("click", ".removeProblem", function () {
            var id = '${id}';
            var geneId = $(this).parents("li").find("input[name='geneId']").val();
            $.ajax({
                url: "<%=path%>/center/removeProblem",
                type: "post",
                dataType: "json",
                data: {geneId: geneId},
                success: function (data) {
                    $(this).parent().removeAttr("class").find('i').remove()
                    location.href = "<%=path%>/center/reviewDetails?id=" + id;
                },
                error: function (e) {
                    alert(e);
                }
            });
        })
        //复检标记
        $(".reviewBtn").click(function () {
            var id = '${id}';
            var limsSampleGeneArr = []
            $(".active").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                limsSampleGeneArr.push(limsSampleGene)
            })
            var params = {
                "limsSampleGeneArr": limsSampleGeneArr,
            }
            $.ajax({
                url: "<%=path%>/center/recheck",
                type: "post",
                data: {"params": JSON.stringify(params)},
                dataType: "json",
                success: function (data) {
                    location.href = "<%=path%>/center/reviewDetails?id=" + id;
//                    $.each(data.date,function(i,item){
//                        var kong = item.split(",")
//                        var kongName=kong[1]+","+kong[2]
//                        $("li:contains('"+kongName+"')").append('<input type="hidden" name="reviewQueueSampleId" value="'+kong[0]+'">')
//                    })
//                    $(".active").each(function () {
//                        $(this).removeClass("active").addClass("review").children("i").remove("i")
//                        if ($(this).hasClass("problem")) {
//                            $(this).append('<i aria-hidden="true" class="fa fa-times-circle removeProblem"></i>')
//                        }
//                        $(this).append('<i aria-hidden="true" class="fa reviewI"></i>')
//                    })
                },
                error: function (e) {
                    alert(e);
                }
            });
        })
        //复检删除
        $(".reviewRemoveBtn").click(function () {
            var id = '${id}';
            var geneIdArr = []
            $("li[class*='review active']").each(function () {
                geneIdArr.push($(this).find("input[name='geneId']").val())
            })
            $.ajax({
                url: "<%=path%>/center/deleteReviewQueueSample",
                type: "post",
                data: {"geneId": geneIdArr.join(",")},
                dataType: "json",
                success: function (data) {
                    location.href = "<%=path%>/center/reviewDetails?id=" + id;
                },
                error: function (e) {
                    alert(e);
                }
            });
//            $(".active").removeClass("review active").children(".fa-check-circle").remove()
//            $(".problemBtn").prop("disabled", false)
//            $('[data-toggle="popover"]').popover('hide')
        })
        //确认审核并上传
        $(".save").click(function () {
            var id = '${id}';
            var limsSampleGeneArr = []
            var issue = []

            /*
            $("li[class='problem active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                issue.push(limsSampleGene)
            })
            $("li[class='problem review active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                issue.push(limsSampleGene)
            })
            $("li[class='review active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                issue.push(limsSampleGene)
            })
            $("li[class='review board active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                issue.push(limsSampleGene)
            })

            $("li[class='active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                limsSampleGeneArr.push(limsSampleGene)
            })
            $("li[class='board active']").each(function () {
                var limsSampleGene = {}
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();
                limsSampleGeneArr.push(limsSampleGene)
            })
            */

            $("li.active").each(function () {
                var limsSampleGene = {};
                limsSampleGene.geneId = $(this).find("input[name='geneId']").val();

                if($(this).hasClass("problem")
                    || $(this).hasClass("review")){
                    issue.push(limsSampleGene)
                }

                limsSampleGeneArr.push(limsSampleGene)
            })

            if (issue == "" && limsSampleGeneArr == "") {
                alert("请选择检材")
            } else {
                var params = {
                    "Issue": issue,
                    "limsSampleGeneArr": limsSampleGeneArr,
                }
                console.log(params)
                $.ajax({
                    url: "<%=path%>/center/confirmAuditAndReport?id=" + id,
                    type: "post",
                    data: {"params": JSON.stringify(params)},
                    dataType: "json",
                    success: function (data) {
                        location.href = "<%=path%>/center/reviewDetails?id=" + id;
                    },
                    error: function (e) {
                        alert(e);
                    }
                });
            }

        })

//        判断caseID
        var caseIdBoard = $(".board:first").find("input[name='caseId']").val()
        console.log(caseIdBoard);
        var boardLength = 0
        $(".board").each(function(){
            if($(this).find("input[name='caseId']").val() == caseIdBoard){
                boardLength ++
            }
        })
        console.log(boardLength);
        if($(".board").length == boardLength){
            $(".board").removeClass('board')
        }
    })
</script>
</body>

</html>