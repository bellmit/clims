$(function () {
//进入受理系统
    $("#enterReceiveSystem").click(function () {
        var areaCode=$("#areaCode").val();//拿到所选区域的code
        var url=baseurl+"/login";
        var form = $("<form method='post'></form>");
        form.attr({"action":url});

        var input = $("<input type='hidden'>"); //单个参数进行操作
        input.attr({"name":"areaCode"});
        input.val(areaCode);
        form.append(input);
      /*  for (arg in areaCode)  //多个参数可以进行循环操作
        {
            var input = $("<input type='hidden'>");
            input.attr({"name":"areaCode"});
            input.val(areaCode[arg]);
            form.append(input);
        }*/
        $("html").append(form);
        form.submit();
    })
});

