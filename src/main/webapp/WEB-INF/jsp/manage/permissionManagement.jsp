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

        .ztree li span.button.add {
            margin-left: 2px;
            margin-right: -1px;
            background-position: -144px 0;
            vertical-align: top;
            *vertical-align: middle
        }
    </style>
</head>
<!-- 修改组织架构项 -->
<div class="applyPostponeTable edithrmSubCompanyWindow" style="display:none">
    <form class="layui-form" id="editform">

        <div class="layui-form-item">
            <label class="layui-form-label">菜单名称
                <%--<i style="font-size: 20px;color: red;font-weight: 800;">*</i>--%>
            </label>
            <div class="layui-input-block">
                <input type="text" name="permissionName" placeholder="请输入菜单名称" lay-verify="phone" class="layui-input"
                       id="menuName">
            </div>
        </div>



        <div class="layui-form-item">
            <label class="layui-form-label">链接地址
                <%--<i style="font-size: 20px;color: red;font-weight: 800;">*</i>--%>
            </label>
            <div class="layui-input-block">
                <input type="text" name="permissionLink" placeholder="请输入链接地址" lay-verify="required" class="layui-input"
                       id="permissionLink">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">排序
                <%--<i style="font-size: 20px;color: red;font-weight: 800;">*</i>--%>
            </label>
            <div class="layui-input-block">
                <input type="text" name="orderNo" placeholder="请输入排序" lay-verify="required" class="layui-input"
                       id="orderNo" >
            </div>
        </div>
        <input type="hidden" name="permissionId">
        <input type="hidden" name="parentId">
    </form>
</div>


<body>
<header>
    <h2>
        <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-fenlei"></use>
        </svg>
        菜单管理
        <span></span>
    </h2>
</header>
<main>
    <div style="padding: 20px;margin: -30px; background-color: #F2F2F2;">
        <div class="layui-card" style="overflow:auto;">
            <div class="layui-card-header">菜单管理</div>
            <div class="layui-card-body">
                <ul id="treeDemo" class="ztree"></ul>
            </div>
        </div>

    </div>
    <jsp:include page="../script.jsp"/>
</main>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js"></script>
<script>
    $(function () {





        layui.use(['layer', 'form', 'upload'], function () {


            let layer = layui.layer;
            let form = layui.form;
            form.on('submit', function () {
                return false;
            });
            let setting = {
                view: {
                    addHoverDom: addHoverDom,
                    removeHoverDom: removeHoverDom,
                    selectedMulti: false
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                edit: {
                    enable: true,
                    editNameSelectAll: true,
                    showRemoveBtn: showRemoveBtn,
                    showRenameBtn: showRenameBtn,
                    renameTitle: '编辑当前菜单',
                    removeTitle: '删除当前菜单'
                },
                callback: {
                    beforeDrag: beforeDrag,
                    beforeEditName: beforeEditName,
                    beforeRemove: beforeRemove,
                    // beforeRename: beforeRename,
                    // onRemove: onRemove,
                    // onRename: onRename
                }
            };

            function initTree() {
                $.get('<%=path%>/manage/selectPermissionList', {}, function (jsonData) {
                    // layer.close(index);
                    if (jsonData == null) {
                        layer.msg('数据加载失败', {icon: 2});
                        // layero.close();
                        return false;
                    } else {
                        // $("#subCompanyId").val(id);
                        $.fn.zTree.init($("#treeDemo"), setting, jsonData);
                        // layer.render();
                    }
                });
            }

            initTree();



            function beforeEditName(treeId, treeNode) {
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                let url = '<%=path%>/manage/savePermission';
                openWindows(zTree, treeNode, '修改', url, 2);
                return false;
            }

            function beforeRemove(treeId, treeNode) {
                zTree = $.fn.zTree.getZTreeObj("treeDemo");
                layer.confirm('是否要删除[' + treeNode.name + ']？', {
                    btn: ['是', '否'] //按钮
                }, function () {
                    $.post('<%=path%>/manage/delPermission', {permissionId: treeNode.id,parentId:treeNode.pId}, function (json) {
                        try {
                            if (json.status === true) {
                                zTree.removeChildNodes(treeNode);
                                zTree.removeNode(treeNode);
                                layer.msg('数据删除成功', {icon: 1});
                            } else {
                                layer.msg('数据删除失败', {icon: 2});
                            }
                        } catch (e) {
                            layer.msg('数据删除失败', {icon: 2});
                        }
                    })
                });
                return false;
            }

            function beforeDrag() {
                return false;
            }

            function showRemoveBtn(treeId, treeNode) {
                return treeNode.edit;
            }

            function showRenameBtn(treeId, treeNode) {
                return treeNode.edit;
            }

            function addHoverDom(treeId, treeNode) {
                let sObj = $("#" + treeNode.tId + "_span");
                if (treeNode.editNameFlag || treeNode.pId || $("#addBtn_" + treeNode.tId).length > 0) return;
                let addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                    + "' title='添加子菜单' onfocus='this.blur();'></span>";
                sObj.after(addStr);
                let btn = $("#addBtn_" + treeNode.tId);
                if (btn) btn.bind("click", function () {
                    zTree = $.fn.zTree.getZTreeObj("treeDemo");
                    let url = '<%=path%>/manage/savePermission';
                    openWindows(zTree, treeNode, '新增', url, 1);
                    return false;
                })
            }

            function removeHoverDom(treeId, treeNode) {
                $("#addBtn_" + treeNode.tId).unbind().remove();
            }

            function openWindows(zTree, treeNode, title, url, type) {
                let lay_editWindow = layer.open({
                        type: 1
                        , title: title //不显示标题栏
                        // , closeBtn: true
                        , area: '600px'
                        , shade: 0.8
                        , shadeClose: true
                        , id: 'lay_editWindow' //设定一个id，防止重复弹出
                        , resize: false
                        , btn: ['提交', '取消']
                        , yes: function () {
                            let $editform = $("#editform");
                            $editform.submit();
                            let data = $editform.serializeArray();
                            layer.close(lay_editWindow);
                            $.post(url, data, function (jsonData) {
                                if (jsonData != null) {
                                    if (type === 1) {
                                        zTree.addNodes(treeNode, jsonData);
                                    }else if (type === 2) {
                                        treeNode.name = jsonData.name;
                                        treeNode.tree_url = jsonData.tree_url;
                                        treeNode.orderNo = jsonData.orderNo;
                                        zTree.updateNode(treeNode);
                                    }
                                    // return false;
                                } else {
                                    layer.msg('数据' + title + '失败', {icon: 2});
                                }
                            })
                        }
                        , btnAlign: 'c'
                        , maxmin: false
                        , moveType: 0 //拖拽模式，0或者1
                        , content: $('.edithrmSubCompanyWindow')
                        , success: function (layero) {
                            if (type === 1) {
                                $("#editform input[name='parentId']").val(treeNode.id);
                            } else if (type === 2) {
                                $("#editform input[name='parentId']").val(treeNode.pId);
                                $("#editform input[name='permissionId']").val(treeNode.id);
                                $("#editform input[name='permissionName']").val(treeNode.name);
                                $("#editform input[name='permissionLink']").val(treeNode.tree_url);
                                $("#editform input[name='orderNo']").val(treeNode.orderNo);
                            }
                        }
                        , end: function () {
                            $("#editform").find('input,select').val("");
                        }
                    }
                );
            }
        });

    });

</script>

</html>