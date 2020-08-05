$(function () {
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form; //独立版的layer无需执行这一句
        var active = {
            notice: function () {
                //示范一个公告层
                layer.open({
                    type: 1,
                    title: "新增角色", //false 不显示标题栏
                    skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                    offset: '170px',
                    closeBtn: 1,
                    area: ['800px', '300px'],//'800px;'
                    anim: 1,
                    shade: 0.8,
                    id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                    btn: ['确定', '取消']
                    , yes: function (index, layero) {
                        //按钮【确定】的回调
                        var roleName = $("#roleName").val();
                        if (roleName == "") {
                            layer.msg('角色名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                        var id_array = new Array();
                        $('input[name="permissionName"]:checked').each(function () {
                            id_array.push($(this).val());//向数组中添加元素
                        });
                        var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串
                        /* var count = parseInt($("#roleListSize").val());*/
                        $.ajax({
                            url: baseurl + "/role/saveRolePermission.html",
                            type: "POST",
                            dataType: 'json',
                            data: {"roelName": roleName, "permissionIds": idstr},
                            success: function (data) {
                                /*  var text = "";
                                 text += '<tr>';
                                 text += '<td>' + (count + 1) + '</td>';
                                 text += '<td>' + data.roleName + '</td>';
                                 text += '<td>' + formartDate(data.createAt) + '</td>';
                                 text += '<td>' + data.creatorName + '</td>';
                                 text += '<td>';
                                 text += '<button class="layui-btn layui-btn-xs" lay-event="edit" name="editBtn" value="' + data.roleId + '">编辑</button>';
                                 text += '<button class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"  name="delBtn">删除</button>';
                                 text += '</td>';
                                 $("#roleList").append(text);
                                 $("#roleListSize").val(count + 1);
                                 //清空数据
                                 $("#addRole input").val("");
                                 $("input[type='checkbox'][name='permissionName']").prop("checked", false);
                                 form.render('checkbox');
                                 layer.close(index);*/
                                layer.msg('新增角色权限信息成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                    layer.close(index);
                                    window.location.reload();
                                });
                            },
                            error: function (data) {
                            }
                        });


                    }
                    , btn2: function (index, layero) {
                        /* if(confirm('确定要取消么')){ //只有当点击confirm框的确定时，该层才会关闭
                         layer.close(index)
                         }
                         return false;*/
                    },
                    btnAlign: 'c',
                    moveType: 1, //拖拽模式，0或者1
                    content: $('#addRole'),
                    success: function (layero) {
                        /* var btn = layero.find('.layui-layer-btn');
                         btn.find('.layui-layer-btn0').attr({
                         href: 'http://www.layui.com/'
                         , target: '_blank'
                         });*/
                    },
                    cancel: function (index, layero) {
                        if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                            layer.close(index)
                        }
                        return false;
                    }
                });
            }
        }
        /*---------------------*/
        $('#layerDemo .layui-btn').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });
        /*---------------------*/
        // 修改角色
        $("button[name='editBtn']", "#roleList").on("click", function () {
            var roleId = this.value;
            layer.open({
                type: 1,
                title: "修改角色", //false 不显示标题栏
                skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                offset: '170px',
                closeBtn: 1,
                area: ['800px', '300px'],//'800px;'
                anim: 1,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    var roleName = $("#moRoleName").val();
                    if (roleName == "") {
                        layer.msg('角色名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                    var id_array = new Array();
                    $('input[name="mPermissionName"]:checked').each(function () {
                        id_array.push($(this).val());//向数组中添加元素
                    });
                    var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串

                    var dataMap = {};
                    dataMap["roleId"] = roleId;
                    dataMap["roleName"] = roleName;
                    dataMap["idstr"] = idstr;

                    $.ajax({
                        url: baseurl + "/role/modifyRole.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"dataMap": JSON.stringify(dataMap)},
                        success: function (data) {
                            if (data == true) {
                                layer.msg('更新角色权限信息成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                    layer.close(index);
                                    window.location.reload();
                                });
                            }

                        },
                        error: function (data) {
                        }
                    });


                },
                error: function (data) {
                }
                , btn2: function (index, layero) {
                },
                btnAlign: 'c',
                moveType: 1, //拖拽模式，0或者1
                content: $('#modifyRole'),
                success: function (layero) {
                    //查询数据库
                    $.ajax({
                        url: baseurl + "/role/selectRolePermission.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"roleId": roleId},
                        success: function (data) {
                            $("#moRoleName").val(data[0].roleName);
                            $("input[type='checkbox'][name='mPermissionName']").prop("checked", false);
                            $.each(data, function (index, item) {
                                $("input[name='mPermissionName'][value=" + item.permissionId + "]").prop("checked", "checked");
                            });
                            form.render('checkbox');
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

        /*---------------------*/
        // 删除角色
        $("button[name='delBtn']", "#roleList").on("click", function () {
            alert("删除模块尚未完成！！！");
        });


    });
});
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function formartDate(dateTime) {
    var time = new Date(dateTime).Format("yyyy-MM-dd");
    ;
    return time;
}

