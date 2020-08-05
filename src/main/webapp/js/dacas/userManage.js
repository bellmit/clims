$(function () {
    var companyId = "";
    var mdyCompanyId="";
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery,
            layer = layui.layer,
            form = layui.form; //独立版的layer无需执行这一句
        form.on('radio(fj)', function (data) {
            //所属分局点击事件
            companyId = data.value;
            $.ajax({
                url: baseurl + "/initProData.html",
                type: "POST",
                dataType: 'json',
                data: {"companyId": companyId},
                success: function (data) {
                    $("#profession").empty();
                    var text = "";
                    $.each(data, function (index, value) {
                        text += '<input type="checkbox" lay-skin="primary"  name="jobName" amDepartmentId="' + value.AMDEPARTMENTID + '"  value="' + value.CATEGORYID + '" title="' + value.CATEGORYNAME + '">';
                    });
                    $("#profession").append(text);
                    form.render();
                },
                error: function (data) {
                }
            });
        });

        form.on('radio(mdyFj)', function (data) {
            //所属分局点击事件
            mdyCompanyId = data.value;
            $.ajax({
                url: baseurl + "/initProData.html",
                type: "POST",
                dataType: 'json',
                data: {"companyId": mdyCompanyId},
                success: function (data) {
                    $("#mdyProfession").empty();
                    var text = "";
                    $.each(data, function (index, value) {
                        text += '<input type="checkbox" lay-skin="primary"  name="mdyJobName" amDepartmentId="' + value.AMDEPARTMENTID + '"  value="' + value.CATEGORYID + '" title="' + value.CATEGORYNAME + '">';
                    });
                    $("#mdyProfession").append(text);
                    form.render();
                },
                error: function (data) {
                }
            });
        });


        var active = {
            notice: function () {
                //示范一个公告层
                layer.open({
                    type: 1,
                    title: "新增用户", //false 不显示标题栏
                    skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                    offset: '100px',
                    closeBtn: 1,
                    area: ['800px'],//'800px;'
                    anim: 4,
                    shade: 0.8,
                    id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                    btn: ['确定', '取消']
                    , yes: function (index, layero) {

                        var name = $("#name").val();
                        if (name == "") {
                            layer.msg('名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                        var loginname =  $("#loginname").val();
                        if (loginname == "") {
                            layer.msg('登录名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                        //取到新增用户的数据
                        var dataMap = {};
                        dataMap["name"] = name;
                        dataMap["loginname"] = loginname;
                        dataMap["phone"] = $("#phone").val();
                        dataMap["email"] = $("#email").val();
                        dataMap["companyId"] = companyId;
                        //所属专业
                        var job_array = new Array();
                        $('input[name="jobName"]:checked').each(function () {
                            /* job_array.push($(this).val());//向数组中添加元素*/
                            job_array.push($(this).attr("amDepartmentId"));
                        });
                        var jobStr = job_array.join(',');//将数组元素连接起来以构建一个字符串
                        dataMap["profession"] = jobStr;
                        //角色
                        var role_array = new Array();
                        $('input[name="roleIds"]:checked').each(function () {
                            role_array.push($(this).val());//向数组中添加元素
                        });
                        var roleStr = role_array.join(',');//将数组元素连接起来以构建一个字符串
                        dataMap["roleIds"] = roleStr;
                        $.ajax({
                            url: baseurl + "/saveLoginUserInfo.html",
                            type: "POST",
                            dataType: 'json',
                            data: {"dataMap": JSON.stringify(dataMap)},
                            success: function (data) {
                                if(data==true){
                                    layer.msg('新增用户信息成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                        layer.close(index);
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
                    content: $('#addUserInfo'),
                    success: function (layero) {
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
        // 删除用户信息
        $("button[name='delBtn']", "#userInfoList").on("click", function () {
            var userId = this.value;
            $.ajax({
                url: baseurl + "/delUser.html",
                type: "POST",
                dataType: 'json',
                data: {"userId": userId},
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


            alert("删除模块尚未完成！！！");
        });
        // 修改用户信息
        $("button[name='editBtn']", "#userInfoList").on("click", function () {
            var userId = this.value;//拿到用户的id
            //示范一个公告层
            layer.open({
                type: 1,
                title: "编辑用户", //false 不显示标题栏
                skin: 'layui-layer-lan',//layui-layer-lan    layui-layer-molv
                offset: '100px',
                closeBtn: 1,
                area: ['800px'],//'800px;'
                anim: 5,
                shade: 0.8,
                id: 'LAY_layuipro', //设定一个id，防止重复弹出,
                btn: ['确定', '取消']
                , yes: function (index, layero) {
                    //拿到用户的数据信息
                    var name = $("#mdyName").val();
                    if (name == "") {
                        layer.msg('名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                    var loginname =  $("#mdyLoginname").val();
                    if (loginname == "") {
                        layer.msg('登录名称不能为空！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }


                    mdyCompanyId= $("input[name='mdyCompanyName']:checked").val();
                    //取到新增用户的数据
                    var dataMap = {};
                    dataMap["name"] = name;
                    dataMap["loginname"] = loginname;
                    dataMap["phone"] = $("#mdyPhone").val();
                    dataMap["email"] = $("#mdyEmail").val();
                    dataMap["userId"] = userId;
                    dataMap["companyId"]=mdyCompanyId;
                    //所属专业
                    var job_array = new Array();
                    $('input[name="mdyJobName"]:checked').each(function () {
                        /* job_array.push($(this).val());//向数组中添加元素*/
                        job_array.push($(this).attr("amDepartmentId"));
                    });
                    var jobStr = job_array.join(',');//将数组元素连接起来以构建一个字符串
                    dataMap["profession"] = jobStr;
                    //角色
                    var role_array = new Array();
                    $('input[name="mdyRoleIds"]:checked').each(function () {
                        role_array.push($(this).val());//向数组中添加元素
                    });
                    var roleStr = role_array.join(',');//将数组元素连接起来以构建一个字符串
                    dataMap["roleIds"] = roleStr;

                    $.ajax({
                        url: baseurl + "/mdyLoginUserInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"dataMap": JSON.stringify(dataMap)},
                        success: function (data) {
                            if(data==true){
                                layer.msg('修改用户信息成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                    layer.close(index);
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
                content: $('#modifyUserInfo'),
                success: function (layero) {
                    $.ajax({
                        url: baseurl + "/mdyUser.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"userId": userId},
                        success: function (data) {
                            /*用户基本信息赋值*/
                            $("#mdyName").val(data.DacasAmEmployee.name);
                            $("#mdyLoginname").val(data.DacasAmEmployee.loginname);
                            $("#mdyPhone").val(data.DacasAmEmployee.phone);
                            $("#mdyEmail").val(data.DacasAmEmployee.email);
                            /*用户所属分局、所属专业、关联角色赋值*/
                            $("input[type='radio'][name='mdyCompanyName']").prop("checked", false);
                            $("input[type='checkbox'][name='mdyRoleIds']").prop("checked", false);
                            $("input[type='radio'][name='mdyCompanyName'][value='" + data.DacasAmEmployee.companyId + "']").prop("checked", "checked");
                            //罗列出所有专业
                            $("#mdyProfession").empty();
                            var text = "";
                            $.each(data.dacasProDataList, function (index, value) {
                                text += '<input type="checkbox" lay-skin="primary"  name="mdyJobName" amDepartmentId="' + value.AMDEPARTMENTID + '"  value="' + value.CATEGORYID + '" title="' + value.CATEGORYNAME + '">';
                            });
                            $("#mdyProfession").append(text);
                            //自己的专业选中
                            if (data.identificationCategoryList != "") {
                                $.each(data.identificationCategoryList, function (index, item) {
                                    $("input[type='checkbox'][name='mdyJobName'][value=" + item.id + "]").prop("checked", "checked");
                                });
                            }
                            //所属角色选中
                            if (data.dacasAmRoleList != "") {
                                $.each(data.dacasAmRoleList, function (index, item) {
                                    $("input[type='checkbox'][name='mdyRoleIds'][value=" + item.roleId + "]").prop("checked", "checked");
                                });
                            }

                            form.render('radio');//必须有这句不然
                            form.render('checkbox');//必须有这句不然 radio不渲染

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

    });

    /*  $("#addUser").on("click",function(){
     //进行加载分局和专业
     $.ajax({
     url :baseurl+"/initAgency.html",
     type : "POST",
     dataType : 'json',
     data :"",
     success : function(data) {
     $("#fj").empty();
     $("#am_role").empty();
     var text="";
     var textRole="";
     $.each(data.companyList,function(index,item){
     text+='<input type="radio" name="companyId"  onclick="initProfession(this)" value="'+item.superCompanyId+'" title="'+item.companyName+'">';
     });
     $.each(data.roleList,function(index,item){
     textRole+='<input type="checkbox" name="roleName"  value="'+item.roleId+'" title="'+item.roleName+'">';
     });
     $("#fj").html(text);
     $("#am_role").append(textRole);
     },
     error : function(data) {
     }
     });
     });




     //提交保存用户信息
     $("#submitUserInfo").on("click",function(){


     });
     */

});
//拿到总局id
function initProfession(obj) {
    var companyId = $(obj).val();
    $.ajax({
        url: baseurl + "/initProData.html",
        type: "POST",
        dataType: 'json',
        data: {"companyId": companyId},
        success: function (data) {
            $("#zy").empty();
            var text = "";
            $.each(data, function (index, value) {
                text += '<input type="checkbox" name="jobName" amDepartmentId="' + value.AMDEPARTMENTID + '"  value="' + value.CATEGORYID + '" title="' + value.CATEGORYNAME + '">' + value.CATEGORYNAME + '';
            });
            $("#zy").append(text);
        },
        error: function (data) {
        }
    });

}
