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
    <link rel="stylesheet" href="<%=path%>/css/porinting.css">
    <style>
        .notice {
            text-align: center;
            height: 40px;
            line-height: 40px;
            background: #ff7c77;
            color: #fff;
            font-size: 18px;
            position: absolute;
            top: 0px;
        }

        body {
            padding: 0px;
            background-color: #f4f4f4;
        }

        body .box {
            padding: 26px 0;
            height: 100%;
            padding-top: 120px;
        }

        body .box > .col-md-12 {
            display: block;
            width: 750px;
            height: 90%;
            background: #fff;
            margin: 0 auto;
            float: none;
            text-align: center;
            padding-top: 80px;

        }

        .row .col-md-12:nth-child(1) a {
            position: relative;
        }

        .row .col-md-12:nth-child(1) .col-md-12:nth-child(1) i {
            font-size: 35px;
            color: #4aca85;
            position: absolute;
            right: 22px;
            top: -85px;
        }

        .row .col-md-12:nth-child(1) .col-md-12:nth-child(4) a {
            line-height: 25px;
        }

        .row .col-md-12:nth-child(1) .col-md-12:nth-child(4) i {
            font-size: 25px;
            margin-left: 10px;

        }

        .row .col-md-12:nth-child(1) p {
            font-size: 14px;
            color: #4aca85;
            position: absolute;
            left: 65px;
            top: -92px;
        }

        .row .col-md-12:nth-child(1) p {
            margin: 0px;
        }

        .row .col-md-12:nth-child(2) {
            margin-top: 45px;
            margin-bottom: 10px;
        }

    </style>
</head>
<div class="shadow">
    <i class="fa fa-spinner" aria-hidden="true"></i>
</div>
<div class="modal fade" id="takePhoto" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <p>请对准摄像头</p>
            </div>
            <div class="modal-body">
                <img id="bigPriDev"/></img>
                <%--<video id="video" autoplay></video>--%>
            </div>
            <div class="modal-footer">
                <input type="hidden" name="file"/>
                <button type="button" class="btn btn-blue btn-lang save" id="photographPri" data-dismiss="modal">确认
                </button>
                <button type="button" class="btn btn-red-border-transparent btn-lang" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<body>
<div class="row">
    <div class="col-md-12 notice">
        您好,请拍照上传盖章的委托书和委托聘请书,才能进行下一步操作
    </div>
</div>
<div class="row box">
    <div class="col-md-12">
        <div class="row">
            <div class="col-md-12">
                <a href="javascript:;">
                    <p class="hidden">上传完成</p>
                    <i class="fa fa-check-circle hidden" aria-hidden="true"></i>
                    <img src="<%=path%>/img/upweituo.png" name="entrust">
                    <input type="hidden" id="entrust64" name="entrust64">
                </a>
                <a href="javascript:;">
                    <p class="hidden">上传完成</p>
                    <i class="fa fa-check-circle hidden" aria-hidden="true"></i>
                    <img src="<%=path%>/img/upqinshu.png" name="employ"/>
                    <input type="hidden" id="employ64" name="employ64">
                </a>
            </div>
            <div class="col-md-12">
                <button type="button " class="btn btn-blue btn-lang upload hidden">确认上传</button>
            </div>
            <div class="col-md-12">
                <input type="hidden" id="caseId" name="caseId" value="${caseId}">
                <input type="hidden" id="consignmentId" name="consignmentId" value="${consignmentId}">
                <a href="javaScript:;" target="ifm" class="btn btn-gray-border btn-lang next">&nbsp;&nbsp;下一步&nbsp;&nbsp;</a>
            </div>
            <div class="col-md-12" style="margin-top: 40px">
                <a href="<%=path%>/center/caseAcceptDetails?caseId=${caseId}&consignmentId=${consignmentId}" target="ifm"
                   style="color: #FFAB08">稍后上传，直接开始受理<i class="fa fa-chevron-circle-right" aria-hidden="true"></i></a>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script src="<%=path%>/js/qwebchannel.js"></script>
<script>
    $(function () {
        $(".box").find("img").click(function () {
            $("#takePhoto").modal("show")
            $("#takePhoto").find("input[name='file']").val($(this).attr("name"))
            $(".upload").removeClass("hidden")
        })
        $("#takePhoto").find(".save").click(function () {
            $(".box").find("img[name='" + $(this).siblings("input").val() + "']").attr("src", "<%=path%>/img/" + $(this).siblings("input").val() + ".png").attr("uplaod", "true")
        })

        $(".upload").click(function () {
//            $(".shadow").css("display","block")
            var url = "<%=path%>/center/uploadImg";
            var paramsData = getArchivesInfos();
            $(".next").removeClass("btn-gray").addClass("btn-yellow").attr("href", "<%=path%>/center/caseAcceptDetails?caseId=${caseId}&consignmentId=${consignmentId}")

            var paramsData = {
                "fileArchivesInfoList": getArchivesInfos()
            };
            $(".box").find("img[uplaod='true']").siblings().removeClass("hidden")

           /* $.ajax({
                url: url,
                type: "post",
                data: {"paramsData": JSON.stringify(paramsData)},
                dataType: "json",
                success: function (data) {
                    if (data.result == true) {*/
                        $(".shadow").css("display", "none")
                        $(".box").find("img[uplaod='true']").siblings().removeClass("hidden")
                        $(".next").removeClass("btn-gray").addClass("btn-yellow").attr("href", "<%=path%>/center/caseAcceptDetails?caseId=${caseId}&consignmentId=${consignmentId}")
                   /* }
                }
            });*/
        })


        function getArchivesInfos() {
            var archivesInfoArr = new Array();
            var archivesInfo = {};
            archivesInfo.caseId = $("#caseId").val();
            archivesInfo.consignmentId = $("#consignmentId").val();
            archivesInfo.archivesType = '01';
            archivesInfo.archivesInfoPicture = $("#entrust64").val();
            archivesInfoArr.push(archivesInfo);

            archivesInfo = {};
            archivesInfo.caseId = $("#caseId").val();
            archivesInfo.consignmentId = $("#consignmentId").val();
            archivesInfo.archivesType = '02';
            archivesInfo.archivesInfoPicture = $("#employ64").val();
            archivesInfoArr.push(archivesInfo);

            console.log(archivesInfoArr);
            return archivesInfoArr;
        }

        //视频
        /*        var promisifiedOldGUM = function (constraints) {
         var getUserMedia = (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia);
         if (!getUserMedia) {
         return Promise.reject(new Error(
         'getUserMedia is not implemented in this browser-getUserMedia是不是在这个浏览器实现'));
         }
         return new Promise(function (resolve, reject) {
         getUserMedia.call(navigator, constraints, resolve, reject);
         });
         }
         if (navigator.mediaDevices === undefined) {
         navigator.mediaDevices = {};
         }
         if (navigator.mediaDevices.getUserMedia === undefined) {
         navigator.mediaDevices.getUserMedia = promisifiedOldGUM;
         }
         var constraints = {
         audio: true,
         video: {
         width: 1280,
         height: 720
         }
         };
         navigator.mediaDevices.getUserMedia(constraints)
         .then(function (stream) {
         var video = document.querySelector('video');
         video.src = window.URL.createObjectURL(stream);
         video.onloadedmetadata = function (e) {
         video.play();
         };
         }).catch(function (err) {
         console.log(err.name + ": " + err.message);
         });*/
    })
</script>
<script type="text/javascript">
    var baseUrl;
    var socket;

    function openSocket() {
        socket = new WebSocket(baseUrl);
        socket.onclose = function () {
            console.error("web channel closed");
        };
        socket.onerror = function (error) {
            console.error("web channel error: " + error);
        };
        socket.onopen = function () {
            new QWebChannel(socket, function (channel) {
                // make dialog object accessible globally
                window.dialog = channel.objects.dialog;
                //网页关闭函数
                window.onbeforeunload = function () {
                    dialog.get_actionType("closeSignal");
                }
                window.onunload = function () {
                    dialog.get_actionType("closeSignal");
                }

                //拍照按钮点击
                document.getElementById("photographPri").onclick = function () {
                    dialog.photoBtnClicked("primaryDev_");
                    dialog.get_actionType("savePhotoPriDev");
                };

                //服务器返回消息
                dialog.sendPrintInfo.connect(function (message) {
                    //设备信息 priModel
                    if (message.indexOf("priDevName:") >= 0) {
                        message = message.substr(11);
                    }
                    //设备出图格式
                    if (message.indexOf("priModel:") >= 0) {
                        message = message.substr(9);
                    }
                    //设备分辨率
                    if (message.indexOf("priResolution:") >= 0) {
                        message = message.substr(14);

                    }
                    //图片保存后返回路径关键字savePhoto_success:
                    else if (message.indexOf("savePhoto_success:") >= 0) {
                        imgPath = message.substr(18);
                    }
                });
                //接收图片流用来展示，多个，较小的base64数据
                dialog.send_priImgData.connect(function (message) {
                    var element = document.getElementById("bigPriDev");
                    element.src = "data:image/jpg;base64," + message;
                });
                //接收拍照base64
                dialog.send_priPhotoData.connect(function (message) {
                    var src64 = "data:image/jpg;base64," + message;
                    if ($("#takePhoto").find("input[name='file']").val() == 'entrust') {
                        $("#entrust64").val(src64)
                    } else if ($("#takePhoto").find("input[name='file']").val() == 'employ') {
                        $("#employ64").val(src64)
                    }
                });
                //网页加载完成信号
                dialog.html_loaded("one");
            });
        }
    }


    //网页初始化函数
    window.onload = function () {
        baseUrl = "ws://127.0.0.1:12345";
        openSocket();
    }
    //END SETUP
</script>
</body>

</html>

