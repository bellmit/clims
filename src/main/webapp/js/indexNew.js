$(function () {
    //加载菜单项
    getMenu();


});

function getMenu() {
    $.ajax({
        url: baseurl + "/getMenuList.html",
        type: "POST",
        dataType: 'json',
        data: "",
        success: function (data) {
            $("#menuList").empty();
            var text = "";
            text += '<ul class="layui-nav layui-nav-tree" lay-filter="test" lay-shrink="all">';
            $.each(data.menuList, function (i, val) {

                if (val.secondMenuList.length== 0) {
                    if(i==0){
                        text += '<li class="layui-nav-item layui-this"><a href="' + baseurl + "/" + val.menuUrl + '" target="ifm">' + val.menuName + '</a></li>';
                    }else {
                        text += '<li class="layui-nav-item"><a href="' + baseurl + "/" + val.menuUrl + '" target="ifm">' + val.menuName + '</a></li>';
                    }
                } else {
                    text += '<li class="layui-nav-item">';
                    //加载一级菜单项
                    text += '<a class="" href="javascript:;">' + val.menuName + '<i class="fa fa-angle-down move" aria-hidden="true"></i></a>';
                    text += '<dl class="layui-nav-child">';

                    //加载二级菜单项
                    $.each(val.secondMenuList, function (j, item) {
                        text += '<dd>';
                        if (item.levelMenus.length > 0) {
                            text += '<a href="javascript:;">' + item.menuName + '<i class="fa fa-angle-down move" aria-hidden="true"></i></a>';
                            text += '<dl class="layui-nav-child">';
                            //加载三级菜单项
                            $.each(item.levelMenus, function (k, vtem) {
                                text += '<dd>';
                                text += '<a href="' + baseurl + "/" + vtem.url + '" target="ifm"><span class="radio"></span>' + vtem.name + '</a>';
                                text += '</dd>';
                            });

                            text += '</dl>';
                        } else if (item.menuInfoList != null && item.menuInfoList.length > 0) {
                            if(!item.menuUrl){
                                item.menuUrl = 'javascript:;'
                            }else{
                                item.menuUrl = baseurl + "/" + item.menuUrl;
                            }

                            text += '<a href="' + item.menuUrl + '" target="ifm">' + item.menuName + '<i class="fa fa-angle-down move" aria-hidden="true"></i></a>';
                            // text += '<dl class="layui-nav-child">';
                            //加载三级菜单项

                            text += showChildMenu(item.menuInfoList);
                            // text += '</dl>';
                        } else {
                            text += '<a href="' + baseurl + "/" + item.menuUrl + '" target="ifm">' + item.menuName + '</a>';
                        }

                        text += '</dd>';
                    });
                    text += '</dl>';
                    text += '</li>';
                }
            });

            $("#menuList").append(text);
            layui.use('element', function () {
                var element = layui.element;
                element.init()
            });
            let $layuitree = $(".layui-nav-tree");
            $layuitree.on("click","a",function() {
                if ($(this).children(".move").length != 0) {
                    if ($(this).parent("li").length != 0) {
                        $(this).parent().siblings().find(".layui-nav-itemed").removeClass("layui-nav-itemed")
                        $(this).parent().siblings().find(".layui-this").removeClass("layui-this")
                    }
                    $(this).parent("").addClass("layui-nav-itemed").siblings().removeClass("layui-this")
                } else {
                    $(this).parent().addClass("layui-this").siblings().removeClass("layui-nav-itemed")
                }
            });
            $layuitree.on("click",".personnelMenuList",function() {
                var $menuChildList = $('#menuChildList');
                $(this).parent().removeClass("layui-this");
                if ($(this).parent().hasClass('layui-nav-itemed') || $(this).parent().hasClass('layui-this')) return false;
                // layer.msg('点击 {type:' + $(this).attr('data-type') + ',deptId:' + $(this).attr('data-value') + '}');
                var urlParam = '&menuId=' + $(this).attr('data-value');
                if ($(this).attr('data-type') === '1') {
                    var deptIds = [];
                    // deptIds.push($(this).attr('data-value'));
                    $.each($(this).parent().find('a'), function (i, val) {
                        deptIds.push($(val).attr('data-value'));
                    });
                    urlParam = '&deptIds=' + deptIds.join(',');
                }
                document.getElementById("ifam").src
                    = $menuChildList.parent().find('a:first').attr('href') + '?type='
                    + $(this).attr('data-type')
                    + urlParam;
            });
        },
        error: function (data) {
        }
    });
}

function showChildMenu(menuList) {
    let menu = '';
    menu += '<dl class="layui-nav-child" id="menuChildList">';
    $.each(menuList, function (i, val) {
        let classStr = '';
        // if (i === 0) {
        //     classStr += 'layui-this';
        // }
        menu += '<dd class="' + classStr + '">';
        menu += '<a class="personnelMenuList" data-value="' + val.menuId + '" data-type="' + val.type + '" href="javascript:;">' + val.menuName;
        if (val.menuInfoList != null && val.menuInfoList.length > 0) {
            menu += '<span class="layui-nav-more" style="display: block;"></span>';
            // menu += showChildMenu(val.menuInfoList);
        }
        menu += '</a>';
        if (val.menuInfoList != null && val.menuInfoList.length > 0) {
            // menu += '<i class="fa fa-angle-down move" style="display: none" aria-hidden="true"></i>';
            menu += showChildMenu(val.menuInfoList);
        }
        menu += '</dd>';
    });
    menu += '</dl>';
    return menu;
}