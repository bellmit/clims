$(function(){
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        $('.timeLay').each(function () {
            laydate.render({
                elem: this
                , position: 'fixed'
                , format: 'yyyy-MM-dd'
            });
        });

        $('.timeLaysfm').each(function () {
            laydate.render({
                elem: this
                , position: 'fixed'
                ,type: 'datetime'
                , format: 'yyyy-MM-dd HH:mm'
            });
        });
       /* //传入Date对象给初始值
        laydate.render({
            elem: '#delegateAt'
            , value: new Date()
            , format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });
        laydate.render({
            elem: '#acceptAt'
            , value: new Date()
            , format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });

        laydate.render({
            elem: '#receiptTime'
            , value: new Date()
            , format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });

        laydate.render({
            elem: '#invoiceTime'
            , value: new Date()
            , format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });

        laydate.render({
            elem: '#signatoryAt'
            , value: new Date()
            , format: 'yyyy-MM-dd HH:mm'//参数即为：2018-08-20 20:08:08 的时间戳
        });
*/
    });
});