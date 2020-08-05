$(function () {

    $(".menuList_level_1").find(".tabName").click(function () {
        if ($(this).text() == "鉴定过程") {
            $(".layui-side").css("width", "0px");
            $(".layui-body").css({
                'width': '100%',
                'left': '0px'
            })
        } else {
            $(".layui-side").css("width", "200px");
            $(".layui-body").css({
                'width': '89%',
                'left': '200px'
            })
        }
        getMenu();
    })
    // var url = $("#ajlrId").val();
    // if (url != "") {
    //     getMenu(url);
    // }
    /* $("#menuList").on("click", "li", function () {
         $(".layui-nav-tree").children().removeClass("layui-this")
         $(this).addClass("layui-this")
     })
     $("#menuList").on("mouseenter", "li", function () {
         //console.log(
         var num = 45 * $("#menuList").find("li").index($(this))
         $("#menuList").find(".layui-nav-bar").css({"top": num + "px", "height": "45px", "opcity": "1"})
     })
     $("#menuList").on("mouseleave", "li", function () {
         $("#menuList").find(".layui-nav-bar").css({"height": "0px", "opcity": "0"})
     })
     $(".move").click(function(){
         $(".header-nav-bottom").fadeIn();
     })
     $(".header-nav-bottom").mouseleave(function(){
         $(this).fadeOut();
         $(".header-nav-bottom").find(".layui-nav-item").removeClass("layui-this")
     })*/
    var servicePath = $('#servicePath').val();
    layui.use(['layer', 'element'], function () {
        var layer = layui.layer, element = layui.element;

        function getMenuList() {
            $.get(servicePath + '/getChildMenuList.html', function (map) {
                if (map.message == null) {
                    // var menuList = map.menuList;
                    var menu = '';
                    $.each(map.menuList, function (i, val) {
                        var classStr = 'layui-nav-item menuOption';
                        if (i === 0) {
                            classStr += ' ' + 'layui-this';
                        }
                        menu += '<li class="' + classStr + '" value="' + val.menuId + '">'
                            + '<a class="tabName" href="javascript:void(0);">'
                            + val.menuName
                            + '</a></li>';
                    });
                    var listLevel1 = $('.menuList_level_1');
                    listLevel1.html(menu);
                    element.init();
                    $(".menuList_level_1 .menuOption").on('click', function () {
                        var menuId = this.value;
                            getChildMenu(menuId)
                    });
                    listLevel1.children(':first').click();
                } else {
                    layer.msg(map.message, {icon: 2});
                }
            })
        }

        getMenuList();

        function getChildMenu(menuId) {
            $.post(servicePath + '/getChildMenuList.html', {menuId: menuId}, function (map) {
                if (map.message == null) {

                    let menu = '';
                    $.each(map.menuList, function (i, val) {
                        if(val.menuUrl!=null){
                            menu += '<li class="layui-nav-item">';
                            menu += '<a href="' + val.menuUrl + '" target="ifm">' +
                                val.menuName +
                                '</a>';
                            if (val.menuInfoList != null && val.menuInfoList.length > 0) menu += showChildMenu(val.menuInfoList);
                            menu += '</li>';
                        }else{


                            menu += '<li class="layui-nav-item">';
                            menu += '<a href="javascript:;">' + val.menuName + '</a>';
                            //取三级菜单数据  val.menuId 作为查询条件
                            var arr=new Array();
                            arr=obtainData(val.menuId,val.menuMark);
                            $.each(arr, function (i, val) {
                                menu += '<dl class="layui-nav-child">';
                                let classStr = '';
                                menu += '<dd class="' + classStr + '">';
                                menu += '<a class="personnelMenuList" data-type="" target="ifm" style="margin-left: 50px;" href="'+servicePath+"/"+val.url+'">' + val.name + '</a>';
                                menu += '</dd>';
                                menu += '</dl>';
                            });
                            menu += '</li>';


                        }

                    });
                    var $menuChildList = $('#menuChildList');
                    $menuChildList.html(menu);
                    element.init();
                    $('.personnelMenuList').on('click', function () {
                        if (!$(this).parent().hasClass('layui-nav-itemed') && !$(this).parent().hasClass('layui-this')) return false;
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
                            = $menuChildList.find('a:first').attr('href') + '?type='
                            + $(this).attr('data-type')
                            + urlParam;
                    });
                    $menuChildList.find('a:first').click();
                    document.getElementById("ifam").src = $menuChildList.find('a:first').attr('href');
                } else {
                    layer.msg(map.message, {icon: 2});
                }
            });
        }

//获取三级菜单数据
        function obtainData(id,mark) {
            var arr=new Array();

            $.ajax({
                url: servicePath+"/menu/getThirdlevelMenu.html",
                type: "POST",
                dataType: 'json',
                data:{"id":id,"mark":mark},
                async:false,
                success: function (data) {
                    arr=data;
                },
                error: function (data) {
                }
            });
            return arr;
        }
        
        
        function showChildMenu(menuList) {
            let menu = '';
            menu += '<dl class="layui-nav-child">';
            $.each(menuList, function (i, val) {
                let classStr = '';
                // if (i === 0) {
                //     classStr += 'layui-this';
                // }
                menu += '<dd class="' + classStr + '">';
                menu += '<a class="personnelMenuList" data-value="' + val.menuId + '" data-type="' + val.type + '" href="javascript:;">' + val.menuName +
                    '</a>';
                if (val.menuInfoList != null && val.menuInfoList.length > 0) menu += showChildMenu(val.menuInfoList);
                menu += '</dd>';
            });
            menu += '</dl>';
            return menu;
        }
    });

});


/*function getMenu(str) {
    //获取二级菜单
    layui.use('element', function(){
        var element = layui.element;
        $.ajax({
            url: str,
            type: "POST",
            dataType: 'json',
            data: "",
            success: function (data) {
                data[data.length - 1].children = [{
                    permissionId: "106",
                    permissionName: "二级菜单1",
                    url: "case/acceptLedger.html"
                }, {
                    permissionId: "106",
                    permissionName: "二级菜单2",
                    url: "case/acceptLedger.html"
                }]
                console.log(data[data.length - 1])
                $("#menuList").children().remove();
                var text = '';
                text += "<ul class='layui-nav layui-nav-tree' lay-filter='test'>";
                $.each(data, function (index, item) {
                    console.log(item.children)
                    if (typeof item.children !== "undefined" && item.children.length > 0) {
                        console.log(typeof item.children)
                        if (index == 0) {
                            text += ' <li class="layui-nav-item layui-nav-itemed">'
                            text += '  <a class="" href="javascript:;">' + item.permissionName + '</a>'
                            text += ' <dl class="layui-nav-child">'
                            $.each(item.children, function (index, item) {
                                if(index==0){
                                    text += ' <dd class="layui-this"><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                                }else{
                                    text += ' <dd><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                                }
                            })
                            text += ' </dl>'
                            text += ' </li>'
                        } else {
                            text += ' <li class="layui-nav-item">'
                            text += '  <a class="" href="javascript:;">' + item.permissionName + '</a>'
                            text += ' <dl class="layui-nav-child">'
                            $.each(item.children, function (index, item) {
                                text += ' <dd><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                            })
                            text += ' </dl>'
                            text += ' </li>'
                        }
                    } else {
                        if (index == 0) {
                            document.getElementById("ifam").src = item.url;
                            text += '<li class="layui-nav-item layui-this">';
                        } else {
                            text += '<li class="layui-nav-item">';
                        }
                        text += "<a href='" + item.url + "' target='ifm' >" + item.permissionName + "</a>";
                        text += '</li>';
                    }
                });
                text += '<span class="layui-nav-bar"></span></ul>'
                $("#menuList").append(text);
                text = "";
                element.init()
            },
            error: function (data) {
            }
        });

    });
}*/
function getMenu() {
    //获取二级菜单
    layui.use('element', function () {
        var element = layui.element;
        var data = [{
            menuId: "1200",
            permissionName: "人员列表",
            url: "personnelManagementList.html",
            children: [
                {menuId: "241", permissionName: "上海华医司法鉴定所", url: null, type: 0},
                {menuId: "561", permissionName: "安徽法润司法鉴定有限公司123", url: null, type: 0}
            ]
        }]
        $("#menuChildList").children().remove();
        var text = '';
        $.each(data, function (index, item) {
            if (typeof item.children !== "undefined" && item.children.length > 0) {
                console.log(typeof item.children)
                if (index == 0) {
                    text += ' <li class="layui-nav-item layui-nav-itemed">'
                    text += '  <a class="" href="javascript:;">' + item.permissionName + '</a>'
                    text += ' <dl class="layui-nav-child">'
                    $.each(item.children, function (index, item) {
                        if(index==0){
                            text += ' <dd class="layui-this"><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                        }else{
                            text += ' <dd><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                        }
                    })
                    text += ' </dl>'
                    text += ' </li>'
                } else {
                    text += ' <li class="layui-nav-item">'
                    text += '  <a class="" href="javascript:;">' + item.permissionName + '</a>'
                    text += ' <dl class="layui-nav-child">'
                    $.each(item.children, function (index, item) {
                        text += ' <dd><a href="' + item.url + '" target="ifm">' + item.permissionName + '</a></dd>'
                    })
                    text += ' </dl>'
                    text += ' </li>'
                }
            } else {
                if (index == 0) {
                    document.getElementById("ifam").src = item.url;
                    text += '<li class="layui-nav-item layui-this">';
                } else {
                    text += '<li class="layui-nav-item">';
                }
                text += "<a href='" + item.url + "' target='ifm' >" + item.permissionName + "</a>";
                text += '</li>';
            }
        });
        $("#menuChildList").append(text);
        text = "";
        element.init()
    });
}

