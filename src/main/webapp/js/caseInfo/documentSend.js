$(function () {
    if ($('input[name="sendType"]:checked').val() == '2') {
        $("#sendTypeInfo").removeClass("hide");
    }


    layui.use(['layer', 'form', 'laydate'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;

        //通用前端验证
        form.verify({
            check64: function (value) {
                if (value.length > 64) {
                    return '字符最多64字符';
                }
            },
            remark256: function (value) {
                if (value.length > 256) {
                    return '备注最多256字符';
                }
            },
            check256: function (value) {
                if (value.length > 256) {
                    return '内容最多256字符';
                }
            },
            phone: [/^1[1|2|3|4|5|6|7|8|9|0]\d{9}$/, '手机必须11位，只能是数字！'],

            email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']
        });


        //sendTypeInfo
        form.on('radio(other)', function (data) {
            $("#sendTypeInfo").removeClass("hide");
        });

        form.on('radio(type)', function (data) {
            $("#sendTypeInfo").addClass("hide");
        });

    });
});