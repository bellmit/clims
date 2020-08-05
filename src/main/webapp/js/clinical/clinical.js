var flag = "add";
var tr;
var str;
$(function () {

    //初始化鉴定项目
    var lbid = $("#idenCheck").val();
    initIdenProject(lbid);


    layui.use(['layer', 'form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;


        form.on('select(cardType)', function (data) {
            var dictName = data.elem[data.elem.selectedIndex].text;
            if (dictName == "无证") {
                $("#identificationNumber").hide();
                form.render();
            } else {
                $("#identificationNumber").show();
                form.render();
            }

        });

        form.on('select(assigneeValue)', function (data) {

            /*var str=data.elem[data.elem.selectedIndex].text;
            var values = data.elem[data.elem.selectedIndex].value;*/

            $("#appraiser1").val(data.value);
            form.render();

            // $("#appraiser1").append("<option value='" + values + "'>" + str + "</option>");

        });
        form.on('radio(filter)', function (data) {
            // var genre = $(this).attr("genre");
            initIdenProject(data.value);
        });


        form.on('radio(instrumentFilter)', function (data) {
            var type = data.value;
            //首先清空地址内容
            $("input[name='instrumentsContent']").val("");
            if (type == "1") {
                $("#instrumentsContentDiv").hide();
            }
            else if (type == "2") {
                $("#addressContent").html("邮寄地址<i class=\"icolor\">*</i>");
                $("#instrumentsContentDiv").show();
            } else if (type == "3") {
                $("#addressContent").html("发送内容<i class=\"icolor\">*</i>");
                $("#instrumentsContentDiv").show();
            }
        });


        form.on('select(gender)', function (data) {
            var str = data.elem[data.elem.selectedIndex].text;
            //拿到称谓的值
            var identity = $("#identity").find("option:selected").text();
            if (identity.indexOf('父亲') >= 0) {
                if (str.indexOf("女") >= 0) {
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#gender').val('0');
                    form.render();
                    return false;
                }
            }
            if (identity.indexOf("母亲") >= 0) {
                if (str.indexOf("男") >= 0) {
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#gender').val('0');
                    form.render();
                    return false;
                }
            }

        });

        form.on('select(identity)', function (data) {
            var str = data.elem[data.elem.selectedIndex].text;
            //拿到称谓的值
            var gender = $("#gender").find("option:selected").text();
            if (gender.indexOf("男") >= 0) {
                if (str.indexOf("母亲") >= 0) {
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#identity').val('0');
                    form.render();
                    return false;
                }
            }
            if (gender.indexOf("女") >= 0) {
                if (str.indexOf("父亲") >= 0) {
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#identity').val('0');
                    form.render();
                    return false;
                }
            }

        });


        form.on('select(caseTypeId)', function (data) {
            var text = $("#caseTypeId").find("option:selected").text();
            if (text == "其他") {
                $("#ortherCaseType").show();
            } else {
                $("#otCaseType").val("");
                $("#ortherCaseType").hide();
            }
        });

        form.on('select(reappraisal)', function(data){
            if(data.value=="1"){
                $("#otCaseTypes").parent().removeClass("layui-hide")
            }else{
                $("#otCaseTypes").parent().addClass("layui-hide")
            }
        });


        //照片上传
        var map = {};
        var mapSampleType = {};
        var files;
        var demo;
        uploadListIns = upload.render({
            elem: '.uploaData'
            , url: baseurl + '/clinicalCase/fileUpload.html'
            , multiple: true
            , accept: 'images'
            , auto: false
            , bindAction: '#saveFile'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files, function (key, values) {
                    var fileName = files[key].name;
                    map[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).val();
                    mapSampleType[fileName] = $("#sampleType" + fileName.substring(0, fileName.indexOf("."))).val();
                });
                this.data = {
                    'dataMap': JSON.stringify(map),
                    'sampleId': $("#sampleId").val(),
                    'caseId': $("#caseId").val(),
                    'memberId': $("#memberId").val(),
                    'sampleTypeMap': JSON.stringify(mapSampleType)
                };
            }
            , choose: function (obj) {
                demo = this.flug;
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {
                    var text = $(['<span class="imgBox layui-form">'
                        , '<i class="layui-icon demo-delete">&#x1007;</i>'
                        , '<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">'
                        , '<input  hidden="hidden" id="sampleType' + file.name.substring(0, file.name.indexOf(".")) + '" value="' + demo + '"></input>'
                        , '<input type="text" id="fremark' + file.name.substring(0, file.name.indexOf(".")) + '" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">'
                        , '</span>'].join(''));

                    //删除
                    text.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        text.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    $("#demo" + demo).append(text);
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


        //鉴定项目
        /* var checkArr = []
          form.on('checkbox(identifyMajor)', function (data) {
          if (data.elem.checked) {
          checkArr.push($(data.elem).attr("title"))
          } else {
          checkArr.splice($.inArray($(data.elem).attr("title"), checkArr), 1);
          }
          var text = ''

          $(".appraisalContent").val(checkArr.join("，"))
          });*/


        //仅物证、毒化需要填写，其他专业禁灰A
        form.on('select(yangben)', function(data){
            if(data.value=="物证" || data.value=="毒化"){
                $("#name").attr('disabled',false)
            }else{
                $("#name").attr("disabled",true)
            }
        });


        //添加被鉴定人
        $("#addMemberInfo").click(function () {
            $("#memberForm").resetForm();//清空表单
            $("#identificationNumber").show();
            $("#nixHistrory").prop("checked", "checked");
            $("#existHistory").removeAttr("checked");
            flag = "add";
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


        //保存被鉴定人

        $("#saveMemberInfo").click(function () {
            var regexp = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
            //取得被鉴定人的信息
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
            // if (idCard == "") {
            //     return " ";
            // }
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

            var bloodHistory = $("input[name='bloodHistory']:checked").val();

            if (flag == "add") {
                var text = "";
                text += '<tr>';
                text += '<input type="hidden" name="birthdate" value="' + birthdate + '">';
                text += '<input type="hidden" name="country" value="' + country + '">';

                text += '<input type="hidden" name="sampleAddress" value="' + sampleAddress + '">';
                text += '<input type="hidden" name="sampleDate" value="' + sampleDate + '">';
                text += '<input type="hidden" name="bloodHistory" value="' + bloodHistory + '">';

                text += '<input type="hidden" name="phone" value="' + phone + '">';
                text += '<input type="hidden" name="countryName" value="' + $("#country").find("option:selected").text() + '">';
                text += '<input type="hidden" name="identityName" value="' + $("#identity").find("option:selected").text() + '">';
                text += '<input type="hidden" name="nationName" value="' + $("#nation").find("option:selected").text() + '">';
                text += '<input type="hidden" name="genderName" value="' + $("#gender").find("option:selected").text() + '">';
                text += '<td>' + name + '<input type="hidden" name="name" value="' + name + '"> </td>';
                text += '<td>' + $("#gender").find("option:selected").text() + '<input type="hidden" name="gender" value="' + gender + '"></td>';
                text += '<td>' + $("#cardType").find("option:selected").text() + '<input type="hidden" name="cardType" value="' + cardType + '"></td>';
                text += '<td>' + idCard + '<input type="hidden" name="idCard" value="' + idCard + '"></td>';
                text += '<td>' + $("#identity").find("option:selected").text() + '<input type="hidden" name="identity" value="' + identity + '"></td>';
                text += '<td>' + $("#nation").find("option:selected").text() + '<input type="hidden" name="nation" value="' + nation + '"></td>';
                text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="modifyPersion(this);">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,1)">删除</button></td>';
                text += '</tr>';


                //取得姓名添加到检材信息的人员选型中
                var tt = '<option value="' + name + '">' + name + '</option>';
                $("#personship").append(tt);
                form.render();//必须有这句不然不渲染
                $("#personTable").append(text);
                layer.closeAll();
            } else if (flag == "modify") {
                tr.find("[name='birthdate']").val(birthdate);
                tr.find("[name='country']").val(country);
                tr.find("[name='phone']").val(phone);

                tr.find("[name='bloodHistory']").val(bloodHistory);
                tr.find("[name='sampleAddress']").val(sampleAddress);
                tr.find("[name='sampleDate']").val(sampleDate);

                tr.find("[name='countryName']").val($("#country").find("option:selected").text());
                tr.find("[name='identityName']").val($("#identity").find("option:selected").text());
                tr.find("[name='nationName']").val($("#nation").find("option:selected").text());
                tr.find("[name='genderName']").val($("#gender").find("option:selected").text());

                var nameText = '' + name + '<input type="hidden" name="name" value="' + name + '">';
                tr.find("[name='name']").parent().html(nameText);

                var genderText = '' + $("#gender").find("option:selected").text() + '<input type="hidden" name="gender" value="' + gender + '">';
                tr.find("[name='gender']").parent().html(genderText);

                var cardTypeText = '' + $("#cardType").find("option:selected").text() + '<input type="hidden" name="cardType" value="' + cardType + '">';
                tr.find("[name='cardType']").parent().html(cardTypeText);

                var idCardText = '' + idCard + '<input type="hidden" name="idCard" value="' + idCard + '">';
                tr.find("[name='idCard']").parent().html(idCardText);

                var identityText = '' + $("#identity").find("option:selected").text() + '<input type="hidden" name="identity" value="' + identity + '">';
                tr.find("[name='identity']").parent().html(identityText);

                var nationText = '' + $("#nation").find("option:selected").text() + '<input type="hidden" name="nation" value="' + nation + '">';
                tr.find("[name='nation']").parent().html(nationText);

                layer.closeAll();
            }


        });


        //添加检材
        $(".addsamples").click(function () {
            $("#sampleForm").resetForm();//清空表单
            flag = "add";
            layer.open({
                type: 1,
                title: "新增检材信息",
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'LAY_layuipro',
                btnAlign: 'c',
                moveType: 1,
                content: $('.samples')
            });
        })
        var options = ''
        // 保存检材信息
        $("#addSampleInfo").click(function () {

            var sampleName = $("#sampleName").val();
            if (sampleName == "") {
                layer.msg('请输入检材名称！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var kinship = $("#kinship").val();
            if (kinship == "") {
                layer.msg('请选择亲缘关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var personship = $("#personship").val();
            if (personship == "") {
                layer.msg('请选择人员关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var sampleType = $("#sampleType").val();
            if (sampleType == "") {
                layer.msg('请选择检材类别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            var amount = $("#amount").val();
            if (amount == "") {
                layer.msg('请输入数量！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var measurementUnit = $("#measurementUnit").val();
            if (measurementUnit == "") {
                layer.msg('请选择数量单位！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
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


            var samplePerson = $("#samplePerson").val();
            if (samplePerson == "") {
                layer.msg('请输入采样人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var samplingAddress = $("#samplingAddress").val();
            if (samplingAddress == "") {
                layer.msg('请输入采样地点！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            var remark = $("#remark").val();
            if (remark == "") {
                layer.msg('请输入检材备注！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            if (flag == "add") {
                var text = "";
                text += '<tr>';
                text += '<input type="hidden" name="remark" value="' + remark + '">';
                text += '<input type="hidden" name="sampleTypeName" value="' + $("#sampleType").find("option:selected").text() + '">';

                text += '<input type="hidden" name="personship" value="' + personship + '">';

                text += '<input type="hidden" name="kinshipName" value="' + $("#kinship").find("option:selected").text() + '">';

                text += '<input type="hidden" name="measurementUnitName" value="' + $("#measurementUnit").find("option:selected").text() + '">';
                text += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> </td>';
                text += '<td>' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '"></td>';
                text += '<td>' + amount + '<input type="hidden" name="amount" value="' + amount + '"></td>';
                text += '<td>' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '"></td>';

                text += '<td>' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + kinship + '"> </td>';

                text += '<td>' + samplingTime + '<input type="hidden" name="samplingTime" value="' + samplingTime + '"> </td>';
                text += '<td>' + samplePerson + '<input type="hidden" name="samplePerson" value="' + samplePerson + '"> </td>';
                text += '<td>' + samplingAddress + '<input type="hidden" name="samplingAddress" value="' + samplingAddress + '"> </td>';

                text += '<td>' + receiveTime + '<input type="hidden" name="receiveTime" value="' + receiveTime + '"> </td>';
                text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm" type="button" onclick="modifySample(this);">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,2)">删除</button></td>';
                text += '</tr>';


                //取得检材的名称
                options += '<option value="' + sampleName + '">' + sampleName + '</option>';

                $("#samplesTable").append(text);
                layer.closeAll();
            } else if (flag == "modify") {
                str.find("[name='remark']").val(remark);
                str.find("[name='personship']").val(personship);
                str.find("[name='sampleTypeName']").val($("#sampleType").find("option:selected").text());
                str.find("[name='measurementUnitName']").val($("#measurementUnit").find("option:selected").text());
                str.find("[name='kinshipName']").val($("#kinship").find("option:selected").text());

                var sampleNameText = '' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> ';
                str.find("[name='sampleName']").parent().html(sampleNameText);

                var sampleTypeText = '' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '">';
                str.find("[name='sampleType']").parent().html(sampleTypeText);

                var amountText = '' + amount + '<input type="hidden" name="amount" value="' + amount + '">';
                str.find("[name='amount']").parent().html(amountText);

                var measurementUnitText = '' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '">';
                str.find("[name='measurementUnit']").parent().html(measurementUnitText);

                var kinshipNameText = '' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + kinship + '"> ';
                str.find("[name='kinship']").parent().html(kinshipNameText);

                var samplingTimeText = '' + samplingTime + '<input type="hidden" name="samplingTime" value="' + samplingTime + '">';
                str.find("[name='samplingTime']").parent().html(samplingTimeText);

                var samplePersonText = '' + samplePerson + '<input type="hidden" name="samplePerson" value="' + samplePerson + '">';
                str.find("[name='samplePerson']").parent().html(samplePersonText);

                var samplingAddressText = '' + samplingAddress + '<input type="hidden" name="samplingAddress" value="' + samplingAddress + '">';
                str.find("[name='samplingAddress']").parent().html(samplingAddressText);


                var receiveTimeText = '' + receiveTime + '<input type="hidden" name="receiveTime" value="' + receiveTime + '">';
                str.find("[name='receiveTime']").parent().html(receiveTimeText);

                layer.closeAll();

            }

        });

        //上传页面跳转
        $("button[name='downFile']").on("click", function () {
            window.location.href = baseurl + "/case/caseAcceptSuccess.html?caseId=" + $("#caseId").val();
        });


        //被鉴定人信息 查重
        $("button[name='checkMemberInfo']").on("click",function () {
            //获取到证件号码的值
            var idCard=$("#idCard").val();
            if (idCard == "") {
                layer.msg('请先填写证件号码！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $.ajax({
                url: baseurl+"/clinicalCase/checkInfo.html",
                type: "post",
                dataType: "json",
                data:{"cardNumber":idCard},
                success: function (data) {
                    if(data>0){
                        layer.msg('已有该被鉴定人'+data+'次检验记录！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    }else{
                        layer.msg('无该被鉴定人检验记录！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    }
                }
            });
        });

        //保存案件信息
        $("#saveAllCaseInfo").on("click", function () {
            //鉴定类别
            var a = $("input[name='projectId']:checked").val();
            var atext = $("input[name='projectId']:checked").attr("title");
            $("#appraisalProjectId").val(a);
            $("#appraisalProject").val(atext);

            var arr = [];
            var arrText = [];
            //鉴定项目
            $("input[name='majorId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    arr.push($(this).val());
                    arrText.push($(this).attr("title"))
                }
            });

            if (arr.length == 0) {
                layer.msg('请选择鉴定项目！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#appraisalMajorId").val(arr);
            $("#appraisalMajor").val(arrText);

            var principalId = $("#principalId ").val();
            if (principalId == "") {
                layer.msg('请选择委托方！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            $("#principalName").val($("#principalId").find("option:selected").text());

            var delegateAt = $("#delegateAt ").val();
            if (delegateAt == "") {
                layer.msg('请选择委托时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var contacts = $("#contacts ").val();
            if (contacts == "") {
                layer.msg('请输入联系人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var telephone = $("#telephone").val();
            if (telephone == "") {
                layer.msg('请输入联系电话！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var acceptAt = $("#acceptAt").val();
            if (acceptAt == "") {
                layer.msg('请选择受理日期！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraisalLimit = $("#appraisalLimit").val();
            if (appraisalLimit == "") {
                layer.msg('请输入鉴定时限！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            //进入诉讼程序
            var a = $("input[name='proc']:checked").val();
            $("input[name='caseLitigation']").val(a);


            var briefCase = $("#briefCase").val();
            if (briefCase == "") {
                layer.msg('请输入简要案情！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            //省
            $("#province").val($("#provid").find("option:selected").val());
            //市
            $("#city").val($("#cityid").find("option:selected").val());
            //区
            $("#county").val($("#areaid").find("option:selected").val());


            var chargeType = $('input:radio[name="dacasCaseCharge.chargeType"]:checked').val();
            if (chargeType == null) {
                layer.msg('请选择收费类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            var chargeAmount = $("#chargeAmount").val();
            if (chargeAmount == "") {
                layer.msg('请输入收费金额！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var assigneeId = $("#assigneeId").val();
            if (assigneeId == "") {
                layer.msg('请选择受理人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#assigneeName").val($("#assigneeId").find("option:selected").text());

            var appraiserOneId = $("#appraiserOneId").val();
            if (appraiserOneId == "") {
                layer.msg('请选择第一鉴定人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#appraiserOneName").val($("#appraiserOneId").find("option:selected").text());

            //第二鉴定人
            $("#appraiserSecondName").val($("#appraiserSecondId").find("option:selected").text());
            //授权人
            $("#authorizerName").val($("#authorizerId").find("option:selected").text());
            var repeat=judgIdenRepeat();
            if(repeat==true){
                layer.msg('请修改第二鉴定人信息,不能与其他重复！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            /* var jsonstr = "[]";
             var jsonarray = eval('(' + jsonstr + ')');

             //获取被鉴定人列表信息
             //获取整个table的tr
             $('#personTable tr').each(function (i) {
                 var name = $(this).find("[name='name']").val();//姓名
                 var gender = $(this).find("[name='gender']").val();//性别
                 var nation = $(this).find("[name='nation']").val();//民族
                 var country = $(this).find("[name='country']").val();//国籍
                 var identity = $(this).find("[name='identity']").val();//称谓
                 var birthdate = $(this).find("[name='birthdate']").val();//被鉴定人生日
                 var phone = $(this).find("[name='phone']").val();//电话
                 var cardType = $(this).find("[name='cardType']").val();//证件类型
                 var idCard = $(this).find("[name='idCard']").val();//证件号码
                 var countryName = $(this).find("[name='countryName']").val();//国籍名称
                 var identityName = $(this).find("[name='identityName']").val();//称谓
                 var nationName = $(this).find("[name='nationName']").val();//民族名称
                 var genderName=$(this).find("[name='genderName']").val();//性别名称

                 var bloodHistory=$(this).find("[name='bloodHistory']").val();//有无输血史
                 var sampleAddress = $(this).find("[name='sampleAddress']").val();
                 var sampleDate = $(this).find("[name='sampleDate']").val();

                 var data;
                 data = {
                     "name": name,
                     "gender": gender,
                     "genderName": genderName,
                     "identity": identity,
                     "identityName": identityName,
                     "idCard": idCard,
                     "nation": nation,
                     "nationName": nationName,
                     "birthdate": birthdate,
                     "country": country,
                     "countryName": countryName,
                     "cardType": cardType,
                     "phone": phone,
                     "sampleAddress": sampleAddress,
                     "sampleDate": sampleDate,
                     "bloodHistory":bloodHistory
                 };
                 jsonarray.push(data);
             });

 */
            var attorneyOriginal = $("#attorneyOriginal").val();//委托书原件
            var injuryOriginal = $("#injuryOriginal").val();//伤势报告份数
            var hireBookOriginal = $("#hireBookOriginal").val();//聘请书份数
            var otherMaterial = $("#otherMaterial").val();//其他资料
            var clinicalSampleData = {
                "attorneyOriginal": attorneyOriginal,
                "injuryOriginal": injuryOriginal,
                "hireBookOriginal": hireBookOriginal,
                "otherMaterial": otherMaterial
            };


            //保存案件信息
            $("#myCaseForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                data: {'clinicalSampleData': JSON.stringify(clinicalSampleData)},
                success: function (data) {
                    if (data.success == "true") {

                        $("#caseId").val(data.caseId);//后台案件id给传回来
                        $("#memberId").val(data.memberId);//后台被鉴定人id
                        $("#sampleId").val(data.sampleId);//后台检材id
                        layer.msg('案件信息录入成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            //保存检材文件
                            /* if(demoListView.find("span").length>1){
                                 $("#saveFile").click();
                             }*/
                            $("#saveFile").click();
                        });
                    } else {
                        layer.msg('案件录入失败！！', {icon: 5, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                }
            });

        });


    });


});

function initIdenProject(id) {
    layui.use('form', function () {
        var form = layui.form;
        $.ajax({
            url: "getIdentificationProject.html?identificationCategoryId=" + id,
            type: "get",
            dataType: "json",
            success: function (data) {

                //给物鉴鉴字赋值
                $("#caseNumber").val(data.caseNumber);
                $("input[name='caseSn']").val(data.caseSn);
                $("#jdxm").empty();
                var text = "";
                $.each(data.identificationProjectList, function (index, item) {
                    text += '<input type="checkbox"  lay-skin="primary" lay-filter="appraisalMajorId" name="majorId" value="' + item.id + '" title="' + item.identificationProject + '">';
                });

                $("#jdxm").append(text);

                /*  $("#pageCase").load(url);*/
                form.render();
            }
        });
    });

}


//删除被鉴定人信息  删除当前行
function deleteRow(obj, f) {
    layui.use(['form'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        if (f == 1) {
            var name = $(obj).parent().siblings().find("input[name='name']").val();
            //删除对应的人员关系option
            $("#personship option[value=" + name + "]").remove();
            form.render();
        }
        $(obj).parent().parent().remove();
    });


}

//修改被鉴定人信息

function modifyPersion(obj) {
    flag = "modify";
    tr = $(obj).parent().parent();
    var name = tr.find("[name='name']").val();//姓名
    var gender = tr.find("[name='gender']").val();//性别
    var nation = tr.find("[name='nation']").val();//民族
    var country = tr.find("[name='country']").val();//国籍
    var identity = tr.find("[name='identity']").val();//称谓
    var birthdate = tr.find("[name='birthdate']").val();//被鉴定人生日
    var phone = tr.find("[name='phone']").val();//电话
    var cardType = tr.find("[name='cardType']").val();//证件类型
    var idCard = tr.find("[name='idCard']").val();//证件号码

    var sampleAddress = tr.find("[name='sampleAddress']").val();
    var sampleDate = tr.find("[name='sampleDate']").val();
    var bloodHistory = tr.find("[name='bloodHistory']").val();//是否有输血史

    /*  var countryName = tr.find("[name='countryName']").val();//国籍名称
     var identityName = tr.find("[name='identityName']").val();//称谓
     var nationName = tr.find("[name='nationName']").val();//民族名称
     var genderName=tr.find("[name='genderName']").val();//性别名称*/
    $("#memberForm").resetForm();//清空表单
    $("#name").val(name);
    $("#nation").val(nation);
    $("#gender").val(gender);
    $("#country").val(country);
    $("#identity").val(identity);
    $("#birthdate").val(birthdate);
    $("#phone").val(phone);
    $("#cardType").val(cardType);
    $("#idCard").val(idCard);

    if (bloodHistory == 1) {
        $("#existHistory").prop("checked", "checked");
        $("#nixHistrory").removeAttr("checked");
    } else if (bloodHistory == 0) {
        $("#nixHistrory").prop("checked", "checked");
        $("#existHistory").removeAttr("checked");
    }

    $("#sampleAddress").val(sampleAddress);
    $("#sampleDate").val(sampleDate);

    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var $ = layui.jquery;
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

    });
}


//修改检材信息

function modifySample(obj) {
    flag = "modify";
    str = $(obj).parent().parent();
    var remark = str.find("[name='remark']").val();//检材备注
    var sampleName = str.find("[name='sampleName']").val();//检材名称
    var sampleType = str.find("[name='sampleType']").val();//检材类型
    var kinship = str.find("[name='kinship']").val();//亲缘关系
    var amount = str.find("[name='amount']").val();//检材数量
    var measurementUnit = str.find("[name='measurementUnit']").val();//检材数量单位
    var samplingTime = str.find("[name='samplingTime']").val();//采样时间

    var samplePerson = str.find("[name='samplePerson']").val();//采样人
    var samplingAddress = str.find("[name='samplingAddress']").val();//采样地点


    var receiveTime = str.find("[name='receiveTime']").val();//接收时间
    /* var sampleTypeName = str.find("[name='sampleTypeName']").val();//检材类型名称
     var measurementUnitName = str.find("[name='measurementUnitName']").val();//单位名称*/

    $("#sampleForm").resetForm();//清空表单

    $("#remark").val(remark);
    $("#sampleName").val(sampleName);
    $("#sampleType").val(sampleType);
    $("#amount").val(amount);
    $("#measurementUnit").val(measurementUnit);
    $("#kinship").val(kinship);
    $("#receiveTime").val(receiveTime);
    $("#samplingTime").val(samplingTime);
    $("#samplePerson").val(samplePerson);
    $("#samplingAddress").val(samplingAddress);


    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var $ = layui.jquery;
        layer.open({
            type: 1,
            title: "修改检材信息",
            closeBtn: 2,
            area: '800px;',
            shade: 0.8,
            id: 'LAY_layuipro',
            btnAlign: 'c',
            moveType: 1,
            content: $('.samples')
        });

    });
}

//判断第二鉴定人是否与其他鉴定人重复
function judgIdenRepeat() {
    //取得受理人、第一鉴定人、第二鉴定人、授权人的值
    var assigneeId = $("#assigneeId").val();
    var appraiserOneId = $("#appraiserOneId").val();
    var appraiserSecondId = $("#appraiserSecondId").val();
    var authorizerId = $("#authorizerId").val();
    var myBoolean = new Boolean(false);
    if(appraiserSecondId=="000090"){
        myBoolean=false;
    }else{
        switch (appraiserSecondId) {
            case assigneeId:
                myBoolean = true;
                break;
            case appraiserOneId:
                myBoolean = true;
                break;
            case authorizerId:
                myBoolean = true;
                break;
            default:
                break;
        }
    }

    return myBoolean;
}