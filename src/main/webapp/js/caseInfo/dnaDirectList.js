$(function () {
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {

        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;

        //执行一个laydate实例
        $('.timeLay').each(function () {
            laydate.render({
                elem: this
                , position: 'fixed'
                , format: 'yyyy-MM-dd'
            });
        });
        //传入Date对象给初始值
        laydate.render({
            elem: '#examineAt'
            , value: new Date()
            , format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });


        //查看dna详情
       /* $("button[name='viewDnaInfo']").on("click",function(){
            var specimenNumber=this.value;//样本编号
            alert(specimenNumber);
            //进行保存直扩检验信息

            $.ajax({
                url: baseurl + "/viewDnaInfo.html",
                type: "POST",
                dataType: 'json',
                data: {"specimenNumber":specimenNumber},
                success: function (data) {


                },
                error: function (data) {
                }
            });


        });*/







    })



    $("button[name='addRecordBtn']").on("click", function () {
        window.location.href = baseurl + "/dnaDirect.html";
    });





})