$(function () {

    //初始化鉴定项目
    var lbid = $("#idenCheck").val();
    var genre = $("#idenCheck").attr("genre");
    initIdenProject(lbid, genre);
    layui.use(['layer', 'form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;


        form.on('radio(filter)', function (data) {
            var genre = $(this).attr("genre");
            initIdenProject(data.value, genre);
        });


        //照片上传
        var map = {};
        var files;
        var demoListView = $('#demo2')
            ,uploadListIns=upload.render({
            elem: '#test2'
            ,url:baseurl+ '/upload/fileUpload.html'
            , multiple: true
            , accept: 'images'
            , auto: false
            , bindAction: '#saveFile'
            ,before: function(obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files,function(key,values){
                    var fileName=files[key].name;
                    map[fileName]=$("#fremark"+fileName.substring(0,fileName.indexOf("."))).val();
                });
                this.data={'dataMap': JSON.stringify(map),'caseId':$("#caseId").val()};
            }
            , choose: function (obj) {
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {
                    var text = $(['<span class="imgBox">'
                        ,'<i class="layui-icon demo-delete">&#x1007;</i>'
                        ,'<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">'
                        ,'<input type="text" id="fremark'+file.name.substring(0,file.name.indexOf("."))+'" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">'
                        ,'</span>'].join(''));

                    //删除
                    text.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        text.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(text);

                });
            }
            , done: function (res, index) {
                //上传完毕
                if(res.code == 0){ //上传成功
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                layer.closeAll('loading'); //关闭loading
            }
            ,allDone: function(obj){ //当文件全部被提交后，才触发
                //alert(obj.successful+"总成功数");
                $('#downFile').trigger("click");
                /*  alert(obj.total+"总文件"); //得到总文件数
                 alert(obj.successful+"总成功数"); //请求成功的文件数
                 alert(obj.aborted+"失败"); //请求失败的文件数*/
            }
        });


        //鉴定项目
        var checkArr = []
        form.on('checkbox(identifyMajor)', function (data) {
            if (data.elem.checked) {
                checkArr.push($(data.elem).attr("title"))
            } else {
                checkArr.splice($.inArray($(data.elem).attr("title"), checkArr), 1);
            }
            var text = ''

            $(".appraisalContent").val(checkArr.join("，"))
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


        //保存被鉴定人
        var personArr = [];

        $("#saveMemberInfo").click(function () {

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

            var cardType = $("#cardType").val();
            if (cardType == "") {
                layer.msg('请选择被鉴定人证件类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var idCard = $("#idCard").val();
            if (idCard == "") {
                layer.msg('请输入被鉴定人证件号码！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var text = "";
            text += '<tr>';
            text += '<input type="hidden" name="birthdate" value="' + birthdate + '">';
            text += '<input type="hidden" name="country" value="' + country + '">';
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
            text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm personModify" type="button">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this)">删除</button></td>';
            text += '</tr>';
            $("#personTable").append(text);
        });

        //修改被鉴定人信息 TODO
        //修改被鉴定人
        /* $("table").on("click", '.personModify', function () {

         $("#memberForm")[0].reset()
         var parentsnode = $(this).parents("tr")
         $("#memberForm").next().find("input[name='index']").val($(this).parents("tr").index())
         $("#memberForm").find("input[name='name']").val(parentsnode.find("input[name='name']").val())
         $("#memberForm").find("select[name='gender']").children("option:contains('" + parentsnode.find("input[name='gender']").val() + "')").attr("selected", "selected")
         $("#memberForm").find("select[name='nation']").children("option:contains('" + parentsnode.find("input[name='nation']").val() + "')").attr("selected", "selected")
         $("#memberForm").find("select[name='appellation']").children("option:contains('" + parentsnode.find("input[name='appellation']").val() + "')").attr("selected", "selected")
         $("#memberForm").find("input[name='birthdate']").val(parentsnode.find("input[name='birthdate']").val())
         $("#memberForm").find("select[name='country']").children("option:contains('" + parentsnode.find("input[name='country']").val() + "')").attr("selected", "selected")
         $("#memberForm").find("input[name='phone']").val(parentsnode.find("input[name='phone']").val())
         $("#memberForm").find("select[name='identity']").children("option:contains('" + parentsnode.find("input[name='identity']").val() + "')").attr("selected", "selected")
         $("#memberForm").find("input[name='idCard']").val(parentsnode.find("input[name='idCard']").val())
         form.render(null, 'addpersonBox')

         layer.open({
         type: 1,
         title: "修改被鉴定人",
         closeBtn: 2,
         area: '800px;',
         shade: 0.8,
         id: 'addpersonBox',
         btnAlign: 'c',
         moveType: 0,
         content: $('.addpersonBox')
         });
         })
         */



        //添加检材
        $(".addsamples").click(function () {
            $("#sampleForm").resetForm();//清空表单
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
        var sampleArr = []
        // 保存检材信息
        $("#addSampleInfo").click(function () {

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

            var remark = $("#remark").val();
            if (remark == "") {
                layer.msg('请输入检材备注！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var text = "";
            text += '<tr>';
            text += '<input type="hidden" name="remark" value="' + remark + '">';
            text += '<input type="hidden" name="sampleTypeName" value="' + $("#sampleType").find("option:selected").text() + '">';
            text += '<input type="hidden" name="measurementUnitName" value="' + $("#measurementUnit").find("option:selected").text() + '">';
            text += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> </td>';
            text += '<td>' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '"></td>';
            text += '<td>' + amount + '<input type="hidden" name="amount" value="' + amount + '"></td>';
            text += '<td>' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '"></td>';
            text += '<td>' + samplingTime + '<input type="hidden" name="samplingTime" value="' + samplingTime + '"> </td>';
            text += '<td>' + receiveTime + '<input type="hidden" name="receiveTime" value="' + receiveTime + '"> </td>';

            text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm samplesModify" type="button">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this)">删除</button></td>';
            text += '</tr>';
            $("#samplesTable").append(text);
        })


        //修改检材信息
        $("table").on("click", '.samplesModify', function () {
            $("#sampleForm")[0].reset()
            var parentsnode = $(this).parents("tr")
            $("#sampleForm").next().find("input[name='index']").val($(this).parents("tr").index())
            $("#sampleForm").find("input[name='samplesId']").val(parentsnode.find("input[name='samplesId']").val())
            $("#sampleForm").find("input[name='sampleName']").val(parentsnode.find("input[name='sampleName']").val())
            $("#sampleForm").find("select[name='sampleType']").children("option:contains('" + parentsnode.find("input[name='sampleType']").val() + "')").attr("selected", "selected")
            $("#sampleForm").find("input[name='amount']").val(parentsnode.find("input[name='amount']").val())
            $("#sampleForm").find("select[name='measurementUnit']").children("option:contains('" + parentsnode.find("input[name='measurementUnit']").val() + "')").attr("selected", "selected")
            $("#sampleForm").find("input[name='receiveTime']").val(parentsnode.find("input[name='receiveTime']").val())
            $("#sampleForm").find("input[name='samplingTime']").val(parentsnode.find("input[name='samplingTime']").val())
            $("#sampleForm").find("input[name='remark']").val(parentsnode.find("input[name='remark']").val())
            form.render(null, 'samples')
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
        })




        //上传页面跳转
        $("button[name='downFile']").on("click", function () {
            window.location.href = baseurl + "/case/caseAcceptSuccess.html";
        });

        //保存案件信息
        $("#saveAllCaseInfo").on("click", function () {
            //鉴定类别
            var a = $("input[name='identifyProject']:checked").val();
            $("#appraisalProject").val(a);

            var arr = [];
            //鉴定项目
            $("input[name='identifyMajor']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    arr.push($(this).val());
                }
            });

            if (arr.length == 0) {
                layer.msg('请选择鉴定项目！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#appraisalMajor").val(arr);

            var client = $("#client").val();
            if (client == "") {
                layer.msg('请输入委托人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var clientCategory = $("#clientCategory ").val();
            if (clientCategory == "") {
                layer.msg('请选择委托方！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            var censorship = $("#censorship").val();
            if (censorship == "") {
                layer.msg('请输入送检人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var contacts = $("#contacts").val();
            if (contacts == "") {
                layer.msg('请输入联系人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var telephone = $("#telephone").val();
            if (telephone == "") {
                layer.msg('请输入联系电话！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var timeLimit = $("#timeLimit").val();
            if (timeLimit == "") {
                layer.msg('请输入鉴定时限！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var caseWas = $("#caseWas ").val();
            if (caseWas == "") {
                layer.msg('请选择案由！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraisalPurpose = $("#appraisalPurpose ").val();
            if (appraisalPurpose == "") {
                layer.msg('请选择鉴定用途！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var caseSource = $("#caseSource ").val();
            if (caseSource == "") {
                layer.msg('请输入案件来源！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var caseType = $("#caseType ").val();
            if (caseType == "") {
                layer.msg('请选择案件类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var briefCase = $("#briefCase").val();
            if (briefCase == "") {
                layer.msg('请输入简要案情！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var sampleNumber = $("#sampleNumber").val();
            if (sampleNumber == "") {
                layer.msg('请输入样本数！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var maintainPeople = $("#maintainPeople").val();
            if (maintainPeople == "") {
                layer.msg('请输入采样人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var assignee = $("#assignee").val();
            if (assignee == "") {
                layer.msg('请选择受理人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraiser1 = $("#appraiser1").val();
            if (appraiser1 == "") {
                layer.msg('请选择第一鉴定人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraiser2 = $("#appraiser2").val();
            if (appraiser2 == "") {
                layer.msg('请选择第二鉴定人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraiser3 = $("#appraiser3").val();
            if (appraiser3 == "") {
                layer.msg('请选择第三鉴定人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var appraiser3 = $("#appraiser3").val();
            if (appraiser3 == "") {
                layer.msg('请选择第三鉴定人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var signatory = $("#signatory").val();
            if (signatory == "") {
                layer.msg('请选择授权人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }


            //进入诉讼程序
            if ($("input[name='proceedings']").is(':checked')) {
                $("input[name='proceedings']").val("1");
            }
            ;
            //重新鉴定
            if ($("input[name='reappraisal']").is(':checked')) {
                $("input[name='reappraisal']").val("1");
            }
            ;

            var jsonstr = "[]";
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
                    "phone": phone
                };
                jsonarray.push(data);
            });



            //获取检材列表信息

            var sampleStr = "[]";
            var sampleArray = eval('(' + sampleStr + ')');

            //获取被鉴定人列表信息
            //获取整个table的tr
            $('#samplesTable tr').each(function (i) {
                var remark = $(this).find("[name='remark']").val();//检材备注
                var sampleName = $(this).find("[name='sampleName']").val();//检材名称
                var sampleType = $(this).find("[name='sampleType']").val();//检材类型
                var amount = $(this).find("[name='amount']").val();//检材数量
                var measurementUnit = $(this).find("[name='measurementUnit']").val();//检材数量单位
                var samplingTime = $(this).find("[name='samplingTime']").val();//采样时间
                var receiveTime = $(this).find("[name='receiveTime']").val();//接收时间
                var sampleTypeName = $(this).find("[name='sampleTypeName']").val();//检材类型名称
                var measurementUnitName = $(this).find("[name='measurementUnitName']").val();//单位名称
                var sampleData;
                sampleData = {
                    "sampleName": sampleName,
                    "sampleType": sampleType,
                    "sampleTypeName": sampleTypeName,
                    "measurementUnitName": measurementUnitName,
                    "amount": amount,
                    "measurementUnit": measurementUnit,
                    "samplingTime": samplingTime,
                    "receiveTime": receiveTime,
                    "remark": remark
                };
                sampleArray.push(sampleData);
            });


            //保存案件信息
            $("#myCaseForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                data: {"memberInfoData": JSON.stringify(jsonarray), "sampleData": JSON.stringify(sampleArray)},
                success: function (data) {
                    if (data.success == "true") {

                        $("#caseId").val(data.caseId);//后台案件id给传回来
                        layer.msg('案件信息录入成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            //如果没有照片 就直接跳转
                            if(demoListView.find("span").length>1){
                                $("#saveFile").click();
                            }else{
                                $('#downFile').trigger("click");
                            }

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
function initIdenProject(id, genre) {
    layui.use('form', function () {
        var form = layui.form;
        $.ajax({
            url: "getIdentificationProject.html?identificationCategoryId=" + id + "&&genre=" + genre,
            type: "get",
            dataType: "json",
            success: function (data) {

                //给物鉴鉴字赋值
                $("#caseNumber").val(data.caseNumber);
                $("input[name='caseSn']").val(data.caseSn);
                $("#jdxm").empty();
                var text = "";
                $.each(data.identificationProjectList, function (index, item) {
                    text += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMajor" name="identifyMajor" value="' + item.id + '" title="' + item.identificationProject + '">';
                });

                $("#jdxm").append(text);

                /*  $("#pageCase").load(url);*/
                form.render();
            }
        });
    });

}


//删除被鉴定人信息  删除当前行
function deleteRow(obj){
    $(obj).parent().parent().remove();
}