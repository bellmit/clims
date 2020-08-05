<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .manual {
            width: 25px;
            height: 25px;
            display: inline-block;
            line-height: 25px;
            text-align: center;
            background: #ff5a56;
            color: #fff;
            border-radius: 50%;
            font-weight: 600;
        }
        .loading-img{
            animation: rotation 5s linear infinite;
        }
        @-webkit-keyframes rotation {
            from {-webkit-transform: rotate(0deg);
            }to {
                 -webkit-transform: rotate(360deg);
             }
        }
    </style>
</head>
<body>
<div class="row Modular">
    <div class="col-md-12">
        <iframe id="childForm" src="" width="100%" height="70vh" style="height: 100vh;display: none;border: none;"></iframe>
    </div>
</div>
<div class="modal" id="loading" tabindex="-1" role="dialog" aria-labelledby="exportSuccess">
    <div class="modal-dialog" role="document" style="width: 456px;">
        <div class="modal-content">
            <div class="modal-body" style="padding: 90px 0;text-align: center">
                <div class="exportIng">
                    <p>
                        <img src="<%=path%>/img/loading.png" class="loading-img" alt="" width="90px">
                    </p>
                    <p style="font-size: 18px;font-weight: bold;color: #0C81F5;margin-top: 15px;">
                        加载中....
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade popBox smallBox" id="errorLoad" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">提示</h4>
            </div>
            <div class="modal-body">
                <span style="color: #333;font-size: 14px;">加载失败，请重试~</span>
            </div>
            <div class="modal-footer clearfix">
                <button type="button" class="btn btn-lang pull-right btn-blue-border" data-dismiss="modal">确定</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="../linkJs.jsp" %>
<script>
    centerModal('loading')
    $('#loading').modal('show')
    var ip = '${ip}'
    var linkIp = '${inspectCourseUrl}'
    var userParam = '${operateUser.loginName}'
    var oFrame = document.getElementById('childForm')
    var postParams = {}
    postParams.ip = ip
    postParams.userParam = userParam
    console.log(postParams,'-----')
    $("#childForm").attr('src', linkIp + '/InspectCourse')
       oFrame.onload = function () {
           oFrame.contentWindow.postMessage(postParams, linkIp)
       }
       window.addEventListener('message', function (event) {
           console.log(event.data, '------')
           if(event.data == '1') {
               $('#loading').modal('hide')
               $(oFrame).css('display','inline-block')
           } else if(event.data == '0'){
               $('#loading').modal('hide')
               $('#errorLoad').modal('show')
           }
       })

    function centerModal(id){
        $("#"+id).on('shown.bs.modal', function () {
            var $this = $(this);
            var dialog = $this.find('.modal-dialog');
            //此种方式，在使用动画第一次显示时有问题
            //解决方案，去掉动画fade样式
            var top = ($(window).height() - dialog.height()) / 2;
            dialog.css({
                marginTop:top
            });
        })
    }
</script>
</body>

</html>