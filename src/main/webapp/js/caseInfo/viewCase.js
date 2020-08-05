$(function () {
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        $('.timeLay').each(function(){
            laydate.render({
                elem: this
                ,position: 'fixed'
                ,format: 'yyyy-MM-dd'
                ,trigger: 'click'
                ,theme: '#02BAFA'
            });
        });
       /* //传入Date对象给初始值
        laydate.render({
            elem: '#delegateAt'
            ,value: new Date()
            ,format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });
        laydate.render({
            elem: '#acceptAt'
            ,value: new Date()
            ,format: 'yyyy-MM-dd'//参数即为：2018-08-20 20:08:08 的时间戳
        });*/
    });
    layui.use(['layer', 'form', 'upload'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form; //独立版的layer无需执行这一句

        $("button[name='acceptBtn']").on("click", function () {
            //拿到所有鉴定人的值
            var appraiserData = "";
            var count=$("select[name='appraiser']").find("option:selected").length;
            $("select[name='appraiser']").find("option:selected").each(function () {
                if($(this).val()!=""){
                    appraiserData += $(this).val();
                    appraiserData += ',';
                }
            });
            //拿到案件的id
            var caseId = this.value;
            $("#acceptCaseForm").ajaxSubmit({
                type: "POST",
                dataType: 'json',
                data:{"appraiserData":appraiserData},
                success: function (data) {
                    if (data == true) {
                        layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            window.location.href = baseurl + "/case/acceptList.html";
                        });
                    }
                },
                error: function (data) {
                }
            });


           /* $.ajax({
                url: baseurl + "/case/acceptAperate.html",
                type: "POST",
                dataType: 'json',
                data: {"dataMap": JSON.stringify(dataMap)},
                success: function (data) {
                    if (data == true) {
                        layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            window.location.href = baseurl + "/case/acceptList.html";
                        });
                    }
                },
                error: function (data) {
                }
            });*/

        });

        $("button[name='unAcceptBtn']").on("click", function () {
            //拿到案件的id
            var caseId = this.value;

            //示范一个公告层
            layer.open({
                type: 1,
                title: "退案说明", //false 不显示标题栏
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
                                layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                    window.location.href = baseurl + "/case/acceptList.html";
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

        $("#addBtn").on("click", function () {
            var caseid = this.value;
            //示范一个公告层
            layer.open({
                type: 1,
                title: "添加被鉴定人", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '100px',
                closeBtn: 1,
                area: ['800px'],//'800px;'
                anim: 5,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var genderName = $("#gender option:selected").text();
                    var nationName = $("#nation option:selected").text();
                    var countryName = $("#country option:selected").text();
                    var identityName = $("#identity option:selected").text();
                    $("#gender_Name").val(genderName);
                    $("#country_Name").val(countryName);
                    $("#identity_Name").val(identityName);
                    $("#nation_Name").val(nationName);
                    $("#memberForm").ajaxSubmit({
                        type: "POST",
                        dataType: "json",
                        data: {"memId": ""},
                        success: function (e) {
                            if (e.success == "true") {
                                //添加鉴定人信息成功    进行加载显示鉴定人信息
                                layer.closeAll();
                                getMemberInfoList(caseid);
                            }

                        }
                    });
                }
                , btn2: function (index, layero) {

                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#addperson'),
                success: function (layero) {
                    //清空form 表单
                    $("#memberForm").resetForm();//重置表单数据
                    $("#case_Id").val(caseid);
                    //修改表单action
                    $("#memberForm").attr('action', "appraiserInfo.html");
                },
                cancel: function (index, layero) {
                    if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }
            });

        });

        //编辑被鉴定人
        $("#memberInfoList").on("click", "button[name='editBtn']", function () {
            var memberid = $(this).attr("memberId");
            var caseid = this.value;
            //示范一个公告层
            layer.open({
                type: 1,
                title: "编辑被鉴定人", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '100px',
                closeBtn: 1,
                area: ['800px'],//'800px;'
                anim: 4,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var genderName = $("#gender option:selected").text();
                    var nationName = $("#nation option:selected").text();
                    var countryName = $("#country option:selected").text();
                    var identityName = $("#identity option:selected").text();
                    $("#gender_Name").val(genderName);
                    $("#country_Name").val(countryName);
                    $("#identity_Name").val(identityName);
                    $("#nation_Name").val(nationName);
                    $("#memberForm").ajaxSubmit({
                        type: "POST",
                        dataType: "json",
                        data: {"memId": ""},
                        success: function (e) {
                            if (e.success == "true") {
                                //添加鉴定人信息成功    进行加载显示鉴定人信息
                                layer.closeAll();
                                getMemberInfoList(caseid);
                            }

                        }
                    });
                }
                , btn2: function (index, layero) {

                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#addperson'),
                success: function (layero) {
                    //查一下库
                    $.ajax({
                        url: "modifyAppraiserInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"memId": memberid},
                        success: function (data) {
                            if (data.memberInfo != "") {
                                //被鉴定人编辑页面填充数据
                                $("#memberSn").val(data.memberInfo.memberSn);
                                $("#name").val(data.memberInfo.name);
                                $("#gender").val(data.memberInfo.gender);
                                $("#nation").val(data.memberInfo.nation);
                                $("#birthdate").val(formartDate(data.memberInfo.birthdate));
                                $("#country").val(data.memberInfo.country);
                                $("#phone").val(data.memberInfo.phone);
                                $("#job").val(data.memberInfo.job);
                                $("#identity").val(data.memberInfo.identity);
                                $("#idCard").val(data.memberInfo.idCard);
                                $("#address").val(data.memberInfo.address);
                                //修改表单action
                                $("#memberForm").attr('action', "modifyAppraiserInfo.html");
                                $("#member_Id").val(memberid);
                                $("#case_Id").val(caseid);
                                form.render();//必须有这句不然不渲染
                            }
                        },
                        error: function (data) {
                        }
                    });
                },
                cancel: function (index, layero) {
                    if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }
            });
        });


        //编辑检材信息
        $("#sampleInfoList").on("click", "button[name='editSampleBtn']", function () {
            var sampleId = $(this).attr("sampleId");
            var caseid = this.value;
            //示范一个公告层
            layer.open({
                type: 1,
                title: "编辑检材信息", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '100px',
                closeBtn: 1,
                area: ['800px'],//'800px;'
                anim: 4,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //获取检材类别对应的值
                    var sampleTypeName=$("#sampleType option:selected").text();
                    $("#sampleTypeName").val(sampleTypeName);
                    $("#sampleForm").ajaxSubmit({
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function (e) {
                            if (e.success == "true") {
                                layer.close(layer.index);
                                //加载检材信息列表
                                getSampleInfoList(caseid)
                            } else {
                                alert("检材信息录入失败！！！");
                                return;
                            }
                        }
                    });
                }
                , btn2: function (index, layero) {

                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#addSample'),
                success: function (layero) {
                    //查一下库
                    $.ajax({
                        url: baseurl + "/modifySampleInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"sampleId": sampleId},
                        success: function (data) {
                            if (data.memberInfo != "") {
                                $("#addsamples").click();
                                //被鉴定人编辑页面填充数据
                                $("#sampleId").val(data.sampleId);
                                $("#sampleName").val(data.sampleName);
                                $("#sampleType").val(data.sampleType);
                                $("#status").val(data.status);
                                $("#amount").val(data.amount);
                                $("#measurementUnit").val(data.measurementUnit);
                                $("#receiveTime").val(formartDate(data.receiveTime));
                                $("#samplingTime").val(formartDate(data.samplingTime));
                                $("#remark").val(data.remark);
                                //修改表单action
                                $("#sampleForm").attr('action', baseurl + "/submitModify.html");
                                form.render('select','selFilter');
                            }
                        },
                        error: function (data) {
                        }
                    })
                },
                cancel: function (index, layero) {
                    if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }
            });
        });


        $("#addSampleBtn").on("click", function () {
            var caseid = this.value;
            //示范一个公告层
            layer.open({
                type: 1,
                title: "新增检材信息", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '100px',
                closeBtn: 1,
                area: ['800px'],//'800px;'
                anim: 5,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {

                    //获取检材类别对应的值
                    var sampleTypeName=$("#sampleType option:selected").text();
                    $("#sampleTypeName").val(sampleTypeName);
                    $("#sampleForm").ajaxSubmit({
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function (e) {
                            if (e.success == "true") {
                                layer.close(layer.index);
                                //加载检材信息列表
                                getSampleInfoList(caseid)
                            } else {
                                alert("检材信息录入失败！！！");
                                return;
                            }
                        }
                    });
                }
                , btn2: function (index, layero) {

                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#addSample'),
                success: function (layero) {
                    $("#sampleForm").resetForm();//重置表单数据
                    //修改表单action
                    $("#sampleForm").attr('action', baseurl + "/addDacasSample.html");
                    $("#sampleCase_Id").val(caseid);

                },
                cancel: function (index, layero) {
                    if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                        layer.close(index)
                    }
                    return false;
                }
            });

        });

        //添加照片
        $("#sampleInfoList").on("click", ".uploadPhoto", function uploadPhoto() {
            //var caseId = $("#sampleCase_Id").val();
            //$("#sampleForm").resetForm();//重置表单数据
            //$("#sampleCase_Id").val(caseId);
            //修改表单action
            //$("#sampleForm").attr('action', baseurl + "/addDacasSample.html");
            $("#sample1_Id").val(this.value);
            layer.open({
                type: 1,
                title: "上传照片",
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'LAY_layuipro',
                btnAlign: 'c',
                moveType: 1,
                content: $('.uploadPhotoForm')
            });
        });
        //照片上传
        var map1 = {};
        var arr = [];
        var filess;
        var fileName;
        var uploadListIns = upload.render({
            elem: '#uploadFile',
            auto: false,
            bindAction: '#testListAction',
            url: baseurl + '/upload/fileUpload.html',
            multiple: false,
            accept: 'file',
            before: function () {
                $.each(filess, function (key, values) {
                    fileName = filess[key].name;
                    map1[fileName] = $("#fremarkImg").val();
                });
                this.data = {'dataMap': JSON.stringify(map1), 'caseId': $("#sample1_Id").val()};
            },
            choose: function (obj) {
                filess = this.filess = obj.pushFile();
                obj.preview(function (index, file, result) {
                    if (arr.length != 0) {
                        delete filess[arr[0]];
                        arr = [];
                    }
                    arr.push(index);
                    $('.layui-word-aux').children().remove()
                    $('.layui-word-aux').append('<div class="imgbox"><img src="' +
                        result +
                        '" alt="' + file.name + '" index="' + index +
                        '" class="layui-upload-img uploadImg">'
                    )
                });
            },
            done: function (res, index) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败!');
                }
                else {
                    map1 = {};//清空map1
                    arr = [];
                    $('.layui-word-aux').children().remove();
                    $("#fremarkImg").val("");
                    delete this.filess[index];//删除文件队列已经上传成功的文件
                    layer.msg('上传成功！');
                    return layer.closeAll();
                }
            }
        });

    });

    //查看照片
    $("#sampleInfoList").on("click", ".lookPhoto", function uploadPhoto() {
        this.value;
        $.ajax({
            url: baseurl + "/upload/viewImage.html",
            type: "POST",
            dataType: 'json',
            data: {"id": this.value},
            success: function (data) {
                $("#sampleImages").empty();
                var text = "";
                $.each(data, function (index, item) {
                    text += '<div class="layui-form-item imgBox">';
                    text += '<div>';
                    text += '<img src="' + baseurl + '/upload/viewPhoto.html?imgPath=' + item.filePath + '"  width="200px;" height="200px;"  alt="">';
                    text += '<input type="text" class="layui-input" style="width: 200px;border: 0px;" value="' + item.fileDepict + '">';
                    text += '</div>';
                    text += '<div>';
                    text += '<i class="fa fa-trash-o" value="' + item.id + '"></i>';
                    text += '<i class="fa fa-pencil-square-o"></i>';
                    text += '</div>';
                    text += '</div>';
                });
                $("#sampleImages").append(text);
                layer.open({
                    type: 1,
                    title: "查看照片",
                    closeBtn: 2,
                    area: '1200px;',
                    shade: 0.8,
                    id: 'LAY_layuipro',
                    btnAlign: 'c',
                    moveType: 1,
                    content: $('.lookPhotoForm')
                });
            },
            error: function (data) {
            }
        });


    });

    //查看图片编辑
    $('.lookPhotoForm').on("click",".fa-trash-o",function(){
        console.log($(this).parents(".imgBox"))
        $(this).parents(".imgBox").remove()
        var id=$(this).attr("value");
        $.ajax({
            url :baseurl+"/upload/deleteImage.html",
            type : "POST",
            dataType : 'json',
            data :{"id":id},
            success : function(data) {
                alert("删除图片："+data);
            },
            error : function(data) {
            }
        });



    })
});

//检材信息列表数据
function getSampleInfoList(sampleCaseId) {
    $.ajax({
        url: baseurl + "/queryDacasSampleList.html",
        type: "POST",
        dataType: 'json',
        data: {"caseId": sampleCaseId},
        success: function (data) {

            $("#sampleInfoList").empty();
            var text = "";
            $.each(data.dacasSampleList, function (index, item) {
                text += "<tr>";
                text += "<td>" + item.sampleName + "</td>";
                text += "<td>" + item.sampleTypeName + "</td>";
                text += "<td>" + formartDate(item.receiveTime) + "</td>";
                text += "<td>" + formartDate(item.samplingTime) + "</td>";
                text += "<td>" + item.remark + "</td>";
                text += "<td>";

                text += "<button class='layui-btn layui-btn-warm layui-btn-sm' lay-event='editSample' name='editSampleBtn' sampleId=\'" + item.sampleId + "\' value=\'" + item.caseBaseId + "\'><i class='layui-icon'>&#xe642;</i>编辑</button>";
                text += "<button class='layui-btn layui-btn-warm layui-btn-sm uploadPhoto' value=\'" + item.sampleId + "\'><i class='layui-icon'>&#xe62f;</i>上传照片</button>";
                text += "<button class='layui-btn layui-btn-warm layui-btn-sm lookPhoto' value=\'" + item.sampleId + "\'><i class='layui-icon'>&#xe64a;</i>查看照片</button>";
                text += "<button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"deleteSample(\'" + item.sampleId + "\',\'" + item.caseBaseId + "\')\"><i class='layui-icon'>&#xe640;</i>删除</button>";
                text += " </td>";
                text += " </tr>";
            });
            $("#sampleInfoList").append(text);
        },
        error: function (data) {
        }
    });
}
//拿到被鉴定人列表
function getMemberInfoList(caseid) {
    $.ajax({
        url: "appraiserInfoList.html",
        type: "POST",
        dataType: 'json',
        data: {"caseId": caseid},
        success: function (data) {
            $("#memberInfoList").empty();
            var text = "";
            $.each(data, function (index, item) {
                text += "<tr>";
                text += "<td>" + item.name + "</td>";
                text += "<td>" + item.genderName + "</td>";
                text += "<td>" + item.idCard + "</td>";
                text += "<td>" + item.phone + "</td>";
                text += "<td>" + item.nationName + "</td>";
                /* text+="<td>"+item.address+"</td>";*/
                text += "<td>";
                text += "<button class='layui-btn layui-btn-warm layui-btn-sm' lay-event='edit' name='editBtn' memberId='" + item.memberId + "' value='" + caseid + "'><i class='layui-icon'>&#xe642;</i>编辑</button>";
                /* text+="<button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"modify(\'"+item.memberId+"\',\'"+caseid+"\')\"><i class='layui-icon'>&#xe642;</i>编辑</button>";*/
                text += "<button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"deleteMember(\'" + item.memberId + "\',\'" + caseid + "\')\"><i class='layui-icon'>&#xe640;</i>删除</button>";
                text += "</td>";
                text += "</tr>";
            });
            $("#memberInfoList").append(text);

        },
        error: function (data) {
        }
    });
}

//删除被鉴定人信息
function deleteMember(memberId, caseId) {
    $.confirm({
        title: '系统提示',
        content: '确认要删除吗？',
        type: 'red',
        icon: 'glyphicon glyphicon-question-sign',
        buttons: {
            ok: {
                text: '确认',
                btnClass: 'btn-primary',
                action: function () {
                    $.ajax({
                        url: "deleteAppraiserInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"memberId": memberId},
                        success: function (data) {
                            if (data == true) {
                                getMemberInfoList(caseId);
                            } else {
                                alert("删除被鉴定人信息报错！！！");
                            }
                        },
                        error: function (data) {
                        }
                    });
                }
            },
            cancel: {
                text: '取消',
                btnClass: 'btn-primary',
                action: function () {
                    // button action.
                }
            },
        }
    });

}


function deleteSample(sampleId, caseId) {
    $.confirm({
        title: '系统提示',
        content: '确认要删除吗？',
        type: 'red',
        icon: 'glyphicon glyphicon-question-sign',
        buttons: {
            ok: {
                text: '确认',
                btnClass: 'btn-primary',
                action: function () {
                    $.ajax({
                        url: baseurl + "/deleteSampleInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"sampleId": sampleId},
                        success: function (data) {
                            if (data == true) {
                                getSampleInfoList(caseId)
                            } else {
                                alert("删除检材信息报错！！！");
                            }
                        },
                        error: function (data) {
                        }
                    });
                }
            },
            cancel: {
                text: '取消',
                btnClass: 'btn-primary',
                action: function () {
                    // button action.
                }
            },
        }
    });

}
function formartDate(dateTime) {
    var time = new Date(dateTime);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    return y + "-" + m + "-" + d;
}