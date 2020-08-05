<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="model.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/Chart.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/jquery.matchHeight-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/laydate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/ace/ace.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/ace/mode-html.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/automatedWork/lib/js/ace/theme-github.js"></script>
<script src="${pageContext.request.contextPath}/automatedWork/js/autowork.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/res/kkpager.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script>
    //如果为空返回""
    function isFullReturn (str) {
        if(str == undefined || str == null || str == "" || str == "null" || str.length == 0){
            return "";
        }else {
            return str;
        }
    }
    $(function () {
        'use strict';
        //退出登录
        $(".user-item").on("click", function(){
            location.href = "<%=path%>/loginOut";
        });
    });
</script>
