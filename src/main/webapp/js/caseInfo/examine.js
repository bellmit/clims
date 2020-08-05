$(function () {
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;

        //执行一个laydate实例
        $('.timeLay').each(function () {
            laydate.render({
                elem: this
                , position: 'fixed'
                , format: 'yyyy-MM-dd'
            });
        });

        $('input[name="selectall"]').click(function () {
            //alert(this.checked);
            if ($(this).is(':checked')) {
                $('input[name="stuCheckBox"]').each(function () {
                    //此处如果用attr，会出现第三次失效的情况
                    $(this).prop("checked", true);
                });
            } else {
                $('input[name="stuCheckBox"]').each(function () {
                    $(this).prop("checked", false);
                });
                $(this).prop("checked", false);
            }

        });


        $("button[name='batchExamine']").on("click", function () {

            var caseId = this.value;
            var caseSn=$(this).attr("caseSn");

            var sampleIdArr = new Array();
            var count = 0;
            $('input[name="stuCheckBox"]').each(function () {
                if ($(this).is(':checked')) {
                    sampleIdArr.push($(this).val());
                    count++;
                }
            });
            if (count == 0) {
                layer.msg('请选择要检验的检材！！', {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            } else {
                window.location.href = baseurl + "/inspectionRecord.html?sampleIdArr=" + sampleIdArr + "&caseId=" + caseId+"&caseSn="+caseSn;
            }
        });


/*        //table 删除当前行
        $("button[name='deleteBtn']", "#sampleExamineList").on("click", function () {
            $(this).parent().parent().remove();
        });


        //完成检验
        $("button[name='examineOverBtn']").on("click", function () {
            var mark = $("#mark").val();

            var jsonstr = "[]";
            var jsonarray = eval('(' + jsonstr + ')');

            var examineMethod = $("[name='method']").val();
            if (examineMethod == 0) {
                layer.msg('请选择要检验的方法！！', {icon: 2, offset: '250px'});
                return false;
            }
            var examinePeople = $("[name='examinePeople']").val();
            if (examinePeople == 0) {
                layer.msg('请选择检验人员！！', {icon: 6, offset: '250px'});
                return false;
            }

            var examineAt = $("#examineAt").val();

            var commonParameters = {
                "method": examineMethod,
                "people": examinePeople,
                "examineAt": examineAt
            }
            var _isInvalid = false;
            //获取整个table的tr
            $('#sampleExamineList tr').each(function (i) {
                var sampleId = $(this).find("[name='sampleId']").val();//检材id
                var sampleType = $(this).find("[name='sampleType']").val();//检材类型
                var sampleName = $(this).find("[name='sampleName']").val();//检材名称
                var sampleSn = $(this).find("[name='sampleSn']").val();//检材编号
                var sampleRemark = $(this).find("[name='sampleRemark']").text();//检材备注
                var current_row = $(this).find("[name='count']").val();
                if (mark == "blood") {
                    var fob = $(this).find("[name='fob" + current_row + "']:checked").val();//阴性和阳性
                    if (fob == undefined) {
                        layer.msg('第' + (i + 1) + '行没有实验结果！！', {icon: 2, offset: '250px'});
                        _isInvalid = true;
                        return false;
                    }
                }
                if (mark == "spots") {
                    var psa = $(this).find("[name='psa" + current_row + "']:checked").val();//阴性和阳性
                    if (psa == undefined) {
                        layer.msg('第' + (i + 1) + '行没有实验结果！！', {icon: 2, offset: '250px'});
                        _isInvalid = true;
                        return false;
                    }
                }
                var data;
                if (mark == "blood") {
                    data = {
                        "sampleSn": sampleSn,
                        "sampleName": sampleName,
                        "sampleRemark": sampleRemark,
                        "sampleType": sampleType,
                        "sampleId": sampleId,
                        "fob": fob
                    };
                }
                if (mark == "spots") {
                    data = {
                        "sampleSn": sampleSn,
                        "sampleName": sampleName,
                        "sampleRemark": sampleRemark,
                        "sampleType": sampleType,
                        "sampleId": sampleId,
                        "psa": psa
                    };
                }

                jsonarray.push(data);
            });

            if (_isInvalid) {
                return false;
            }
            //传数据到后台
            $.ajax({
                url: baseurl + "/batchExamine.html",
                type: "POST",
                dataType: 'json',
                data: {"dataMap": JSON.stringify(jsonarray), "commonParameters": JSON.stringify(commonParameters)},
                success: function (data) {
                    if (data == true) {
                        if (mark == "blood") {
                            layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                window.location.href = baseurl + "/bloodExamine.html";
                            });
                        }
                        else if (mark == "spots") {
                            layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                                window.location.href = baseurl + "/spotsExamine.html";
                            });
                        }

                    }
                },
                error: function (data) {
                }
            });

        });*/

    });
});