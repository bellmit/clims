var baseurl = "${ctx}";
$(function () {
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;

        /*$("#insertBtn").on("click", function () {
         $("#SampleTable").removeClass("hide");
         });*/

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

        $("#inputPick").on("click", function () {

            layer.open({
                type: 1,
                title: '添加',
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'LAY_layuipro1',
                btnAlign: 'c',
                moveType: 1,
                content: $('.popup'),
                cancel: function (index, layero) {
                    layer.close(index);
                    $("#LAY_layuipro1").find("form")[0].reset();
                    return false;
                }

            });
        });


        $("#saveBtn").on("click", function () {

            var jsonstr = "[]";
            var jsonarray = eval('(' + jsonstr + ')');

            var sampleSendMan = $.trim($("#sampleSendMan").val()); //检材递送人
            var sampleReciveMan = $.trim($("#sampleReciveMan").val());//检材接收人
            var sampleLocation = $.trim($("#sampleLocation").val());//存放位置
            var sampleTimeLimit = $.trim($("#sampleTimeLimit").val()); //保存期限
            var sampleOverTime = $.trim($("#sampleOverTime").val()); //超时处理方式
            var sampleComments = $.trim($("#sampleComments").val());//备注


            $("#dacasSampleStorageVOListbody input[name='sampleCheckBox']:checked").each(function () {
                var caseSn = $(this).parent().parent().find("[name='caseSn']").val();//案件id
                var sampleSn = $(this).parent().parent().find("[name='sampleSn']").val();//检材id

                temp = {
                    "caseId": caseSn,
                    "sampleId": sampleSn,
                    "sampleSendMan": sampleSendMan,
                    "sampleReciveMan": sampleReciveMan,
                    "sampleLocation": sampleLocation,
                    "sampleTimeLimit": sampleTimeLimit,
                    "sampleOverTime": sampleOverTime,
                    "sampleComments": sampleComments
                };


                jsonarray.push(temp);
            });
            $.ajax({
                url: "sampleInsert.html",
                type: "POST",
                async: false,
                dataType: 'json',
                contentType: "application/json",
                data: JSON.stringify(jsonarray),
                success: function (data) {
                    if (data == true) {
                         layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 5000}, function () {
                             location.reload();
                         });
                    } else {
                        layer.msg('操作失败！！', {icon: 1, offset: '250px', time: 5000}, function () {
                            location.reload();
                        });
                    }
                },
                error: function (data) {
                    alert(arguments[1]);
                    layer.msg('操作失败！！', {icon: 1, offset: '250px', time: 500}, function () {
                        location.reload();
                    });
                }
            });

        });
    });

});