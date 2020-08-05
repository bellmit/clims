$(function () {
    'use strict';
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , laydate = layui.laydate
            , layer = layui.layer;


        $('.timeLay').each(function () {
            laydate.render({
                elem: this
                , position: 'fixed'
                , format: 'yyyy-MM-dd'
            });
        });

        //同时绑定多个元素，并将属性设定在元素上
        upload.render({
             elem: '.demoMore'
            , auto: true
            , multiple: false
            ,accept: 'file'
            , before: function () {
                //获取当前触发上传的元素，layui 2.1.0 新增
                /*  layer.tips('接口地址：'+ this.url, this.item, {tips: 1});*/
            }
            , done: function (res, index, upload) {
                if (res.code == 0) {//上传成功
                    layer.msg('文件已上传成功！！', {icon: 6, offset: '250px', time: 2000});
                }

            }
        })


        $("button[name='downloadTemplate']").on("click",function(){
            var id=this.value;
            $.ajax({
                url: baseurl + "/selectIdentifica.html",
                type: "POST",
                dataType: 'json',
                data: {"id": id},
                success: function (data) {
                    if (data.msg == "true") {
                        window.location.href = baseurl + "/downloadIdentifica.html?fileSavePath="+data.fileSavePath;
                    }else{
                        layer.msg('请先上传鉴定书！！', {icon: 2, offset: '250px', time: 500});
                    }
                },
                error: function (data) {

                }
            });

        });

    });


});