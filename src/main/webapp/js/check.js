//全选、全不选
/*function setAll() {
    var box = document.getElementById("allSampleTableChecked");
    var loves = document.getElementsByName("allSampleTableBox");
    if(box.checked == false){
        for (var i = 0; i < loves.length; i++) {
            loves[i].checked = false;
        }
    }else{
        for (var i = 0; i < loves.length; i++) {
            loves[i].checked = true;
        }
    }
}*/
//如果有未选的，去掉全选
function selectAll(obj) {
    //获取name=box的复选框
    var boxs = document.getElementsByName(obj.name);
    var count = 0;
    //遍历所有的复选框
    for(var i = 0; i < boxs.length; i++) {
        if(boxs[i].checked) {
            count++;
        }
    }
    //选中复选框的个数==获取复选框的个数
    if(count == boxs.length) {
        //设置id为all复选框选中
        if (obj.name == "allSampleTableBox") {
            document.getElementById("allSampleTableChecked").checked = true;
        }else if (obj.name == "extractBox") {
            document.getElementById("allExtractChecked").checked = true;
        }else if (obj.name == "pcrBox") {
            document.getElementById("allPcrChecked").checked = true;
        }else if (obj.name == "syBox") {
            document.getElementById("allSyChecked").checked = true;
        }
    } else {
        //设置id为all复选框不选中
        if (obj.name == "allSampleTableBox") {
            document.getElementById("allSampleTableChecked").checked = false;
        }else if (obj.name == "extractBox") {
            document.getElementById("allExtractChecked").checked = false;
        }else if (obj.name == "pcrBox") {
            document.getElementById("allPcrChecked").checked = false;
        }else if (obj.name == "syBox") {
            document.getElementById("allSyChecked").checked = false;
        }
    }
}
$(function () {
    'use strict';

    //通用选择框
    $("#allChecked").click(function () {
        // this 全选的复选框
        var allChecked = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=box]").each(function () {
            this.checked = allChecked;
            $(this).change()
        });
    });

    //给name=box的复选框绑定单击事件
    $("input[name=box]").click(function () {
        //获取选中复选框长度
        var length = $("input[name=box]:checked").length;
        //未选中的长度
        var len = $("input[name=box]").length;
        if (length == len) {
            $("#allChecked").get(0).checked = true;
        } else {
            $("#allChecked").get(0).checked = false;
        }
    });

    //样本表选择框
    $("#allSampleTableChecked").click(function () {
        // this 全选的复选框
        var allChecked = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=allSampleTableBox]").each(function () {
            this.checked = allChecked;
            $(this).change()
        });
    });

    //给name=box的复选框绑定单击事件
    $("input[name=allSampleTableBox]").click(function () {
        //获取选中复选框长度
        var length = $("input[name=allSampleTableBox]:checked").length;
        //未选中的长度
        var len = $("input[name=allSampleTableBox]").length;
        if (length == len) {
            $("#allSampleTableChecked").get(0).checked = true;
        } else {
            $("#allSampleTableChecked").get(0).checked = false;
        }
    });

    //提取表选择框
    $("#allExtractChecked").click(function () {
        // this 全选的复选框
        var allChecked = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=extractBox]").each(function () {
            this.checked = allChecked;
            $(this).change()
        });
    });

    //给name=box的复选框绑定单击事件
    $("input[name=extractBox]").click(function () {
        //获取选中复选框长度
        var length = $("input[name=extractBox]:checked").length;
        //未选中的长度
        var len = $("input[name=extractBox]").length;
        if (length == len) {
            $("#allExtractChecked").get(0).checked = true;
        } else {
            $("#allExtractChecked").get(0).checked = false;
        }
    });

    //扩增表选择框
    $("#allPcrChecked").click(function () {
        // this 全选的复选框
        var allChecked = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=pcrBox]").each(function () {
            this.checked = allChecked;
            $(this).change()
        });
    });

    //给name=box的复选框绑定单击事件
    $("input[name=pcrBox]").click(function () {
        //获取选中复选框长度
        var length = $("input[name=pcrBox]:checked").length;
        //未选中的长度
        var len = $("input[name=pcrBox]").length;
        if (length == len) {
            $("#allPcrChecked").get(0).checked = true;
        } else {
            $("#allPcrChecked").get(0).checked = false;
        }
    });

    //电泳表选择框
    $("#allSyChecked").click(function () {
        // this 全选的复选框
        var allChecked = this.checked;
        //获取name=box的复选框 遍历输出复选框
        $("input[name=syBox]").each(function () {
            this.checked = allChecked;
            $(this).change()
        });
    });

    //给name=box的复选框绑定单击事件
    $("input[name=pcrBox]").click(function () {
        //获取选中复选框长度
        var length = $("input[name=syBox]:checked").length;
        //未选中的长度
        var len = $("input[name=syBox]").length;
        if (length == len) {
            $("#allSyChecked").get(0).checked = true;
        } else {
            $("#allSyChecked").get(0).checked = false;
        }
    });

});