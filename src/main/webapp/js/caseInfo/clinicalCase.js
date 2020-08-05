$(function () {

    layui.use(['layer', 'form', 'upload'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , layer = layui.layer
            , form = layui.form;


        //照片上传
        var map = {};
        var files;
        var demoListView = $('#demo2')
            , uploadListIns = upload.render({
            elem: '#test2'
            , url: baseurl + '/upload/fileUpload.html'
            , multiple: true
            , accept: 'images'
            , auto: false
            , bindAction: '#saveFile'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files, function (key, values) {
                    var fileName = files[key].name;
                    map[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).val();
                });
                this.data = {
                    'dataMap': JSON.stringify(map),
                    'caseId': $("#caseId").val(),
                    'sampleMap': JSON.stringify(mapsample)
                };
            }
            , choose: function (obj) {
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {
                    var text = $(['<span class="imgBox layui-form">'
                        , '<i class="layui-icon demo-delete">&#x1007;</i>'
                        , '<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">'
                        , '<input type="text" id="fremark' + file.name.substring(0, file.name.indexOf(".")) + '" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">'
                        , '</span>'].join(''));

                    //删除
                    text.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        text.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(text);
                    form.render()
                });
            }
            , done: function (res, index) {
                //上传完毕
                if (res.code == 0) { //上传成功
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                layer.closeAll('loading'); //关闭loading
            }
            , allDone: function (obj) { //当文件全部被提交后，才触发
                //alert(obj.successful+"总成功数");
                $('#downFile').trigger("click");
                /*  alert(obj.total+"总文件"); //得到总文件数
                 alert(obj.successful+"总成功数"); //请求成功的文件数
                 alert(obj.aborted+"失败"); //请求失败的文件数*/
            }
        });
        //多图片上传
        upload.render({
            elem: '#test6'
            , url: '/upload/'
            , multiple: true
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img imgwh">')
                });
            }
            , done: function (res) {
                //上传完毕
            }
        });


        //添加检材信息
        $("#addsampleInfo").click(function () {

            //判断被鉴定人是否已经录入
            var num = $('#personship option').length;
            if (num == 0) {
                layer.msg('请先添加被鉴定人信息！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#sampleForm").resetForm();//清空表单
            layer.open({
                type: 1,
                title: "添加检材信息",
                skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'addSampleBox',
                btnAlign: 'c',
                moveType: 0,
                content: $('.samples')
            });

        });

        //保存检材信息
        $("#saveSampleInfo").on("click", function () {
            var personship = $("#personship").val();
            if (personship == 0) {
                layer.msg('请选择关联被鉴定人对象！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var samplePerson = $("#samplePerson").val();
            if (samplePerson == "") {
                layer.msg('请输入采样人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var sampleName = $("#sampleName").val();
            if (sampleName == "") {
                layer.msg('请输入检材名称！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var sampleType = $("#sampleType").val();
            if (sampleType == "") {
                layer.msg('请选择检材类别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var amount = $("#amount").val();
            if (amount == "") {
                layer.msg('请输入检材数量！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var measurementUnit = $("#measurementUnit").val();
            if (measurementUnit == "") {
                layer.msg('请选择检材单位！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var receiveTime = $("#receiveTime").val();
            if (receiveTime == "") {
                layer.msg('请选择接收时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var receiveTime = $("#receiveTime").val();
            if (receiveTime == "") {
                layer.msg('请选择接收时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var samplingTime = $("#samplingTime").val();
            if (samplingTime == "") {
                layer.msg('请选择采样时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var remark = $("#remark").val();
            if (remark == "") {
                layer.msg('请输入检材备注！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            //保存检材信息
            $("#sampleForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                success: function (data) {


                    var text = "";
                    var textOption = "";
                    textOption += '<option value="0">---请选择---</option>';
                    if (data.success = "true") {
                        $("#personTable").empty();
                        $("#personship").empty();
                        $.each(data.memberInfoList, function (index, item) {
                            //检材信息 关联对象赋值
                            textOption += '<option value="' + item.id + '">' + item.name + '</option>';

                            text += '<tr>';
                            text += '<input type="hidden" name="memberId" value="' + item.id + '">';
                            text += '<td>' + item.name + '</td>';
                            text += '<td>' + item.genderName + '</td>';
                            text += '<td>' + item.cardType + '</td>';
                            text += '<td>' + item.idCard + '</td>';
                            text += '<td>' + item.identityName + '</td>';
                            text += '<td>' + item.nationName + '</td>';
                            text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="modifyPersion(\'' + item.id + '\');">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,\'' + item.id + '\')">删除</button></td>';
                            text += '</tr>';
                        });


                        $("#personship").append(textOption);
                        $("#personTable").append(text);
                        form.render();
                        layer.closeAll();
                    } else {
                        layer.msg('保存检材信息失败！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        layer.closeAll();
                        return false;
                    }

                }
            });


        });


        //添加被鉴定人
        $("#addMemberInfo").click(function () {
            $("#memberForm").resetForm();//清空表单
            layer.open({
                type: 1,
                title: "添加被鉴定人",
                skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'addpersonBox',
                btnAlign: 'c',
                moveType: 0,
                content: $('.addpersonBox')
            });
        });

//保存被鉴定人信息
        $("#saveMemberInfo").on("click", function () {

            var name = $("#name").val();
            if (name == "") {
                layer.msg('请输入被鉴定人姓名！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var gender = $("#gender").val();
            if (gender == "") {
                layer.msg('请选择被鉴定人性别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var nation = $("#nation").val();
            if (nation == "") {
                layer.msg('请选择被鉴定人民族！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var identity = $("#identity").val();
            if (identity == "") {
                layer.msg('请选择被鉴定人称谓！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var birthdate = $("#birthdate").val();
            if (birthdate == "") {
                layer.msg('请选择被鉴定人生日！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var country = $("#country").val();
            if (country == "") {
                layer.msg('请选择被鉴定人国籍！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var phone = $("#phone").val();
            if (phone == "") {
                layer.msg('请输入被鉴定人联系方式！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            if (phone != "") {
                if (!(regexp.test(phone))) {
                    layer.msg('被鉴定人联系方式填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }

            var cardType = $("#cardType").val();
            if (cardType == "") {
                layer.msg('请选择被鉴定人证件类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var idCard = $("#idCard").val();
            var cardTypeText = $("select[name='cardType'] option:selected").text();
            if (cardTypeText == "身份证") {
                var regIdNo = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/;
                if (!regIdNo.test(idCard)) {
                    layer.msg('被鉴定人证件号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }

            var sampleAddress = $("#sampleAddress").val();
            if (sampleAddress == "") {
                layer.msg('请输入采样地点！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var sampleDate = $("#sampleDate").val();
            if (sampleDate == "") {
                layer.msg('请选择采样日期！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //赋值
            $("#countryName").val($("#country").find("option:selected").text());
            $("#nationName").val($("#nation").find("option:selected").text());
            $("#genderName").val($("#gender").find("option:selected").text());
            $("#identityName").val($("#identity").find("option:selected").text());

            //保存被鉴定人信息
            $("#memberForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                success: function (data) {
                    var text = "";
                    var textOption = "";
                    textOption += '<option value="0">---请选择---</option>';
                    if (data.success = "true") {
                        $("#personTable").empty();
                        $("#personship").empty();
                        $.each(data.memberInfoList, function (index, item) {
                            //检材信息 关联对象赋值
                            textOption += '<option value="' + item.id + '">' + item.name + '</option>';

                            text += '<tr>';
                            text += '<input type="hidden" name="memberId" value="' + item.id + '">';
                            text += '<td>' + item.name + '</td>';
                            text += '<td>' + item.genderName + '</td>';
                            text += '<td>' + item.cardType + '</td>';
                            text += '<td>' + item.idCard + '</td>';
                            text += '<td>' + item.identityName + '</td>';
                            text += '<td>' + item.nationName + '</td>';
                            text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="modifyPersion(\'' + item.id + '\');">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,\'' + item.id + '\')">删除</button></td>';
                            text += '</tr>';
                        });


                        $("#personship").append(textOption);
                        $("#personTable").append(text);
                        form.render();
                        layer.closeAll();
                    } else {
                        layer.msg('保存被鉴定人信息失败！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        layer.closeAll();
                        return false;
                    }

                }
            });

        });

    });


    $("#saveCaseInfo").on("click", function () {
        $('#downFiles').trigger("click");

    });
    $("button[name='downFiles']").on("click", function () {
        window.location.href = baseurl + "/clinicalCase/caseAcceptSuccess.html?caseId=" + $("#caseId").val();
    });

});


//修改被鉴定人信息
function modifyPersion(id) {
    layui.use(['layer', 'form', 'upload'], function () {
        var $ = layui.jquery
            , upload = layui.upload
            , layer = layui.layer;

        $.ajax({
            url: baseurl + "/clinicalCase/modifyMemberInfo.html",
            type: "post",
            dataType: "json",
            data: {"id": id},
            success: function (data) {
                $("#memberForm").resetForm();//清空表单
                //赋值
                $("#memberInfoId").val(data.id);
                $("#name").val(data.name);
                $("#gender").val(data.gender);
                $("#nation").val(data.nation);
                $("#identity").val(data.identity);
                $("#birthdate").val(formartDate(data.birthdate));
                $("#country").val(data.country);
                $("#phone").val(data.phone);
                $("#cardType").val(data.cardType);
                $("#idCard").val(data.idCard);
                $("#sampleAddress").val(data.sampleAddress);
                $("#sampleDate").val(formartDate(data.sampleDate));
                $("#countryName").val(data.countryName);
                $("#nationName").val(data.nationName);
                $("#genderName").val(data.genderName);
                $("#identityName").val(data.identityName);
                layer.open({
                    type: 1,
                    title: "修改被鉴定人",
                    skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                    closeBtn: 2,
                    area: '800px;',
                    shade: 0.8,
                    id: 'addpersonBox',
                    btnAlign: 'c',
                    moveType: 0,
                    content: $('.addpersonBox')
                });
            }

        });
    });

}

function deleteRow(nowTr, id) {
    //删除当前行
    //多一个parent就代表向前一个标签,

    // 本删除范围为<td><tr>两个标签,即向前两个parent

    //如果多一个parent就会删除整个table
    $.ajax({
        url: baseurl + "/clinicalCase/deleteMemberInfo.html",
        type: "post",
        dataType: "json",
        data: {"id": id},
        success: function (data) {
            if (data == true) {
                layer.msg('删除被鉴定人信息成功！！', {icon: 6, offset: '250px', time: 2000});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                $(nowTr).parent().parent().remove();
            }

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