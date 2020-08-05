$(function () {
    'use strict';
    var fileNumber = 0;

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


        //上传页面跳转
        $("button[name='uploadFile']").on("click", function () {
            window.location.href = baseurl + "/uploadFile.html";
        });
        var uploadNubmer = 0;
        var demoListView = $('#demoList')
            , uploadListIns = upload.render({
            elem: '#testList'
            , url: baseurl + "/templateFileUpload.html"
            , accept: 'file'
            , multiple: true
            , auto: false
            , bindAction: '#testListAction'
            , choose: function (obj) {
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function (index, file, result) {
                    fileNumber++;
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td>' + file.name + '</td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            , done: function (res, index, upload) {
                if (res.code == 0) { //上传成功
                    uploadNubmer++;
                    var tr = demoListView.find('tr#upload-' + index)
                        , tds = tr.children();
                    tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(3).html(''); //清空操作

                    if (fileNumber == uploadNubmer) {
                        layer.msg('所有文件已上传成功！！', {icon: 6, offset: '250px', time: 2000}, function () {
                            window.location.href = baseurl + "/templateManagement.html";
                        });
                    }
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
            }
            , error: function (index, upload) {
                var tr = demoListView.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
            }

        });



        //删除服务器上文件

        $("button[name='deleteBtn']").on("click",function(){
            var fileId=this.value;
            $.ajax({
                url: baseurl + "/deleteFile.html",
                type: "POST",
                dataType: 'json',
                data: {"fileId": fileId},
                success: function (data) {
                    if (data == true) {
                        layer.msg('删除文件成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            window.location.href = baseurl + "/templateManagement.html";
                        });
                    }
                },
                error: function (data) {

                }
            });
        });


    });


});