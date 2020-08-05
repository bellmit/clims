var baseurl = "${ctx}";
$(function () {
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;

        $("#queryBtn").on("click", function () {
            $("#page").val(1);
            $('#form').submit();
        });

        $('input[name="selectall"]').click(function () {
            //alert(this.checked);
            if ($(this).is(':checked')) {
                $('input[name="sampleCheckBox"]').each(function () {
                    //此处如果用attr，会出现第三次失效的情况
                    $(this).prop("checked", true);
                });
            } else {
                $('input[name="sampleCheckBox"]').each(function () {
                    $(this).prop("checked", false);
                });
                $(this).prop("checked", false);
            }

        });

        $("#OutPick").on("click", function () {

            var jsonstr = "[]";
            var jsonarray = eval('(' + jsonstr + ')');

            $("#dacasSampleStorageVOListbody input[name='sampleCheckBox']:checked").each(function () {
                var id = $(this).parent().parent().find("[name='sampleCheckBox']").val();//案件id

                temp = {
                    "id": id
                };

                jsonarray.push(temp);
            });

            $.ajax({
                url: "sampleDelete.html",
                type: "POST",
                async: false,
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(jsonarray),
                success: function (data) {
                    if (data == true) {
                        layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 2000}, function () {
                            window.location.reload();
                        });
                    } else {
                        layer.msg('操作失败！！', {icon: 1, offset: '250px', time: 2000}, function () {
                            window.location.reload();
                        });
                    }
                },
                error: function (data) {
                    alert(arguments[1]);
                    layer.msg('操作失败！！', {icon: 1, offset: '250px', time: 500}, function () {
                        window.location.reload();
                    });
                }
            });

        });
    });

});