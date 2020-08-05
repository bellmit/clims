$(function () {
    layui.use(['layer', 'form','laydate'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form,//独立版的layer无需执行这一句
           laydate = layui.laydate;

//执行一个laydate实例
        $('.timeLay').each(function () {
            laydate.render({
                elem: this
                , format: 'yyyy-MM-dd'
            });
        });


        $("button[name='unAcceptBtn']", "#caseInfoVOListTbody").on("click", function () {
            //拿到案件的id
            var caseId = this.value;

            //示范一个公告层
            layer.open({
                type: 1,
                title: "不受理原因", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '300px',
                closeBtn: 1,
                area: ['470px', '260px'],//'800px;'['390px', '260px']
                anim: 5,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var dataMap = {};
                    var unAcceptCause = $("#unAcceptCause").val();
                    if (unAcceptCause == "") {
                        layer.msg('不受理原因不能为空！！', {icon: 6, offset: '380px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                    dataMap["caseId"] = caseId;
                    dataMap["unAcceptCause"] = unAcceptCause;
                    $.ajax({
                        url: baseurl + "/case/acceptAperate.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"dataMap": JSON.stringify(dataMap)},
                        success: function (data) {
                            if (data == true) {
                                layer.msg('受理操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                    window.location.reload();
                                });
                            }
                        },
                        error: function (data) {

                        }
                    });
                }
                , btn2: function (index, layero) {

                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#unAcceptText'),
                success: function (layero) {

                },
                cancel: function (index, layero) {
                    if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }
            });
        });

    });
});