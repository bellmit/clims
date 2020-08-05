layui.use(['layer', 'laypage', 'form', 'layedit', 'laydate', 'element'], function () {
    // 分页
    var laypage = layui.laypage,
        layer = layui.layer;
    // 表格
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        laydate = layui.laydate;
    //tab 切换
    var $ = layui.jquery,
        element = layui.element;

    laypage.render({
        elem: 'accTable',
        count: 100,
        layout: ['count', 'prev', 'page', 'next', 'skip'],
        jump: function (obj) {
            console.log(obj)
        }
    });
    //注册日期组件
    //委托时间
    laydate.render({
        elem: '.entrustTimeBegin',
        type: 'datetime'
    });
    laydate.render({
        elem: '.entrustTimeFinish',
        type: 'datetime'
    });
    //受理时间
    laydate.render({
        elem: '.acceptTimeBegin',
        type: 'datetime'
    });
    laydate.render({
        elem: '.acceptTimeFinish',
        type: 'datetime'
    });
    //发布时间
    laydate.render({
        elem: '.issueTimeBegin',
        type: 'datetime'
    });
    laydate.render({
        elem: '.issueTimeFinish',
        type: 'datetime'
    });
    //领取时间
    laydate.render({
        elem: '.receivedAt',
        type: 'datetime'
    });
    //统计时间
    laydate.render({
        elem: '.acceptAtStart',
        type: 'datetime'
    });
    laydate.render({
        elem: '.acceptAtEnd',
        type: 'datetime'
    });
    //开票时间
    laydate.render({
        elem: '.BillingStart',
        type: 'datetime'
    });
    laydate.render({
        elem: '.BillingEnd',
        type: 'datetime'
    });
    //通知时间
    laydate.render({
        elem: '.notice',
        type: 'datetime'
    });
    //接收时间
    laydate.render({
        elem: '.getTime',
        type: 'datetime'
    });
    //接收时间
    laydate.render({
        elem: '.receiveTime'
    });
    //采样时间
    laydate.render({
        elem: '.collectionTime',
        type: 'datetime'
    });

    //会诊、指导委托单选切换
    form.on('radio(items)', function (data) {
        console.log(data);
        console.log(data.value);
        if (data.value == "autopsy") {
            console.log(1)
            $(".autopsy").css("display", "block")
            $(".clinic").css("display", "none")
        } else if (data.value == "clinic") {
            console.log(2)
            $(".autopsy").css("display", " none")
            $(".clinic").css("display", "block")
        }
    });

    //是否重新鉴定单选切换
    form.on('radio(isNewAuthenticate)', function (data) {
        console.log(data);
        console.log(data.value);
        if (data.value == "是") {
            console.log(1)
            $(".new-authenticate").css("display", "block")
        } else if (data.value == "否") {
            console.log(2)
            $(".new-authenticate").css("display", " none")
        }
    });
    //密码修改弹出
    $('.alter-password').on('click', function () {
        $("#myform").resetForm();
        layer.open({
            type: 1,
            title: "修改密码", //不显示标题栏
            closeBtn: true,
            area: '500px;',
            id: 'alterPassword', //设定一个id，防止重复弹出
            btn: ['确认修改', '关闭弹窗'],
            btnAlign: 'c',
            moveType: 1, //拖拽模式，0或者1
            content: $('#changePassword'),
            yes:function(index,layero){
                $("#myform").ajaxSubmit(function(e){
                 alert(e.message);
                });
                layer.close(index)
            }
        });
    });
})