<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
%>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%@ include file="../link.jsp" %>
    <%@ include file="../include.jsp" %>
    <link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">

    <style>
        #kkpager span {
            display: inline-block !important;
        }
    </style>
</head>
<div class="applyPostponeTable addRoleForm" style="display:none">
    <form class="layui-form" lay-filter="addRoleForm">
        <div class="layui-form-item">
            <label class="layui-form-label">权限名称<i
                    style="font-size: 20px;color: red;font-weight: 800;">*</i></label>
            <div class="layui-input-block">
                <input type="text" name="roleName" placeholder="请输入权限名称" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">权限管理<i
                    style="font-size: 20px;color: red;font-weight: 800;">*</i></label>
            <div class="layui-input-block">
                <div class="layui-tab layui-tab-card">
                    <ul class="layui-tab-title">
                        <li class="layui-this">菜单</li>
                    </ul>
                    <div class="layui-tab-content" style="height: 200px;overflow: auto;">
                        <div class="layui-tab-item layui-show">
                            <ul id="treeDemo" class="ztree"></ul>
                        </div>

                    </div>
                </div>
            </div>
        </div>
            <div class="layui-form-item">
        <label class="layui-form-label">权限级别<i
                style="font-size: 20px;color: red;font-weight: 800;">*</i></label>
        <div class="layui-input-block">
            <input type="text" name="roleLevel" placeholder="请输入权限级别" lay-verify="required"
                   class="layui-input">
        </div>

    </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="hidden" name="roleId">
                <button class="layui-btn" lay-submit lay-filter="addRoleBtn">提交</button>
            </div>
        </div>
    </form>
</div>
<body>
<header>
    <h2>
        <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
        </svg>
        权限管理
        <span></span>
    </h2>
    <div>
        <div class="layui-form-item">
            <button type="button" class="layui-btn addRole">
                <i class="layui-icon">&#xe654;</i>
                添加
            </button>
        </div>
    </div>
</header>
<main>
    <table class="layui-table" lay-size="sm" id="tableList">
        <thead>
        <th>序号</th>
        <th>权限名称</th>
        <th>权限级别</th>
        <th>操作</th>
        <%----%>
        </thead>
        <tbody>
        <c:forEach items="${loaRoles}" var="obj" varStatus="i">
            <tr>
                <td>${i.count}</td>
                <td>${obj.roleName}</td>
                <td>${obj.roleLevel}</td>
                <td>
                    <button type="button" value="${obj.roleId}" class="layui-btn layui-btn-xs editRoleBtn">编辑</button>
                    <button type="button" value="${obj.roleId}"
                            class="layui-btn layui-btn-xs delRoleBtn layui-btn-danger">删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
<jsp:include page="../script_layui_v2_3_0.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js"></script>
<script>
    let init_html_level_1 = $('.addRoleForm').html();
    $(function () {

        layui.use(['layer', 'form', 'laydate', 'element'], function () {
            let layer = layui.layer, form = layui.form, laydate = layui.laydate, element = layui.element;

            form.on('submit(addRoleBtn)', function (data) {
                let formData = data.field;
                formData.roles = [];
                formData.menus = [];
                formData.organs = [];
                let find = $(data.form).find('input[name="roles"]:checked');
                $.each(find, function (i, val) {
                    formData.roles.push($(val).val());
                });
                let treeObj = $.fn.zTree.getZTreeObj("treeDemo");
                // let organTreeObj = $.fn.zTree.getZTreeObj("allOrganizationInfo");
                let nodes = treeObj.getCheckedNodes(true);
                $.each(nodes, function (i, val) {
                    formData.menus.push(val.id);
                });
                // nodes = organTreeObj.getCheckedNodes(true);
                $.each(nodes, function (i, val) {
                    formData.organs.push(val.id);
                });
                let url;
                if (data.field.roleId) {
                    url = '<%=path%>/manage/updateRoleInfo';
                } else {
                    url = '<%=path%>/manage/saveRoleInfo';
                }
                $.post(url, formData, function (json) {
                    layer.close();
                    if (json.status) {
                        layer.confirm('数据保存成功', {icon: 1, closeBtn: false}, function () {
                            location.reload();
                        });
                        return false
                    }
                    layer.msg('数据保存失败', {icon: 2});
                });
                return false;
            });

            $(".addRole").on('click', function () {
                openWindow();
            });
            $(".editRoleBtn").on('click', function () {
                let id = $(this).val();
                openWindow(id);
            });
            $(".delRoleBtn").on('click', function () {
                let id = $(this).val();
                layer.confirm('确认删除？', {icon: 3, title: '确认'}, function (index) {
                    $.post('<%=path%>/manage/delRoleInfo', {roleId: id}, function (json) {
                        if (json && json.status) {
                            layer.msg('删除成功', function () {
                                location.reload();
                            });
                        }else{
                            layer.msg('删除失败');
                        }
                    });
                });
            });
            $(".deletePracticeBtn").on('click', function () {
                let id = $(this).val();
                let personnelId = getQueryString('id');
                if (personnelId == null || personnelId === "") {
                    layer.msg('系统错误,请刷新页面重试', {icon: 2});
                    return false;
                }
                layer.confirm('您确定要删除？', {
                    btn: ['删除', '取消'] //按钮
                }, function () {
                    $.post('<%=path%>/deletePractice.html', {id: id, personnelId: personnelId}, function (json) {
                        if (json != null && json.status === true) {
                            layer.alert('删除成功', {icon: 1, closeBtn: 0}, function () {
                                window.location.href = location.href
                            });
                        } else {
                            layer.alert('删除失败', {icon: 2, closeBtn: 0});
                        }
                    })
                });
            });

            let setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": "ps"}
                },
                view: {
                    // dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };
            let organ_setting = {
                check: {
                    enable: true,
                    chkboxType: {"Y": "ps", "N": 'ps'}
                },
                view: {
                    // dblClickExpand: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                }
            };

            function treeInit(index, ids) {
                $.get('<%=path%>/manage/selectPermissionList', {}, function (jsonData) {
                    if (jsonData == null) {
                        if (index) layer.close(index);
                        layer.msg('菜单数据加载失败', {icon: 2});
                        return false;
                    } else {
                        let treeObj = $.fn.zTree.init($("#treeDemo"), setting, jsonData);
                        if (ids != null && ids.length > 0) {
                            var nodes = treeObj.getCheckedNodes(false);
                            $.each(nodes, function (i, val) {
                                if (ids.indexOf(val.id) > -1) {
                                    treeObj.checkNode(val, true);
                                }
                            })
                        }
                        if (index) layer.close(index);
                    }
                });
            }

            function allOrganizationInfoTreeInit(index, ids) {
            }


            function openWindow(id) {
                layer.open({
                    type: 1,
                    title: "权限管理",
                    // closeBtn: 2,
                    shadeClose: true,
                    area: ['760px', 'auto'],
                    // height:100px,
                    shade: 0.8,
                    id: 'LAY_layuipro',
                    btnAlign: 'c',
                    // maxmin: true, //开启最大化最小化按钮
                    moveType: 1,
                    content: $('.addRoleForm'),
                    success: function (layero, index) {
                        let load_index = layer.load(2, {
                            shade: [0.2, '#000'] //0.1透明度的白色背景
                        });
                        form.render();
                        if (id != null) {
                            $.post('<%=path%>/manage/selectRoleInfoById', {roleId: id}, function (json) {
                                if (json.status) {
                                    form.val('addRoleForm', json.loaRole);
                                    $.each(json.permissions, function (i, val) {
                                        $.each(val.permissionCruds, function (i, curds) {
                                            $("#" + val.code + "_" + curds.code).attr('checked', true);
                                        });
                                    });
                                    form.render('checkbox');
                                    allOrganizationInfoTreeInit(null, json.organizationIds);
                                    treeInit(load_index, json.menuList);
                                } else {
                                    layer.close(load_index);
                                    layer.msg('数据加载失败', {icon: 2});
                                    layer.close(index);
                                }
                            })
                        } else {
                            allOrganizationInfoTreeInit();
                            treeInit(load_index);
                            layer.close(load_index);
                        }
                    },
                    end: function () {
                        $('.addRoleForm').html(init_html_level_1);
                    }
                });
            }
        });
        pageInit();
    });

    function pageInit() {
    }

    function getQueryString(name) {
        let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
        let r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
</script>
</html>