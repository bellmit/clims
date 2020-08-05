var flag = "add";
var tr;
var str;
var sampleArr = new Array();
var checkboxArr = [];
var kinName;
var havekinshipName;
var haveKinshipId;
$(function () {
    var caseStatus = $("#caseStatus").val();
    if (caseStatus == 1) {
        $("#submitCheckCaseInfo").removeClass("displayNone");
        //待受理
        $("#isAdm").show();
        $("#isReview").hide();
    }
    else if (caseStatus == 2) {
        $("#submitCheckCaseInfo").removeClass("displayNone");
        //待审核
        $("#isAdm").hide();
        $("#isReview").show();
    }
    else if (caseStatus == 3) {
        //审批通过
        $("#isAdm").hide();
        $("#isReview").show();
    }
    else if (caseStatus == 4) {
        //审批不通过
        $("#isAdm").hide();
        $("#isReview").show();
    } else if (caseStatus == 5) {
        $("#submitCheckCaseInfo").addClass("displayNone");
        //审批不通过
        $("#isAdm").hide();
        $("#isReview").hide();
    }

    /*var clientType = $("input[name='clientType']").val();
     if (clientType == "1") {
     $(".clientCategory").hide();
     $(".addPrincipal").hide();
     }else {
     $(".clientCategory").show();
     $(".addPrincipal").show();
     }*/
    //回避人默认隐藏
    var isApplication = $("input[name='isApplication']:checked").val();
    if (isApplication == '0') {
        $(".cover").addClass("layui-hide").removeClass("layui-show")
    }else {
        $(".cover").addClass("layui-show").removeClass("layui-hide")
    }

    layui.use(['layer', 'form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;

        //临床检材名称切换
        form.on('select(sampleNameFilter)',function(data){
            var id = data.value;
            var code=$("#majorCode").val();//拿到鉴定类型code
            $.ajax({
                url: baseurl+"/selectLcMaterial.html?id=" + id+"&code="+code,
                type: "post",
                dataType: "json",
                success: function (data) {
                        //检材的鉴定类别赋值
                        $("#sampleType").empty();
                        var sampleText = "";
                        sampleText += '<option></option>';
                        $.each(data, function (index, item) {
                            sampleText += '<option value="' + item.id + '">' + item.materialName + '</option>';
                        });
                        $("#sampleType").append(sampleText);
                        form.render();
                },
                error: function (e) {
                    alert("查询错误");
                }
            });
        });




        //切换是否显示回避人
        form.on('radio(isApplication)', function (data) {
            if (data.value == "1") {
                $(".cover").addClass("layui-show").removeClass("layui-hide")
            } else {
                $(".cover").addClass("layui-hide").removeClass("layui-show")
            }
        });

        //切换委托方时填充不同信息
        form.on('select(DispayVal)', function (data) {
            var id = data.value;
            $.ajax({
                url: "selectPrincipalData.html?id=" + id,
                type: "post",
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        $("input[name='censorship']").val(data.principalContact);
                        $("input[name='telephone']").val(data.principalContactPhone);
                        //$("input[name='contacts']").val(data.principalName);
                        //$("input[name='contactsTelephone']").val(data.principalTelephone);
                    } else {
                        alert("查询错误");
                    }
                },
                error: function (e) {
                    alert("查询错误");
                }
            });
        });

        //选中鉴定方法时把鉴定方法名称填充到鉴定内容中

        form.on('checkbox(identifyMajor)', function (data) {
            if (data.elem.checked) {
                checkboxArr.push($(data.elem).attr("title"))
            } else {
                var removeItem = $(data.elem).attr("title");
                checkboxArr = $.grep(checkboxArr, function (value) {
                    return value != removeItem;
                });
            }
            $("textarea[name='caseContent']").val(checkboxArr.join(","));
        });

        //切换不同的人员信息
        form.on('select(peopleMsg)', function (data) {
            // console.log(data.elem); //得到select原始DOM对象
            //console.log(data.othis); //得到美化后的DOM对象
            // console.log(data.value); //得到被选中的值
            var pid = data.value;
            var that = $(data.elem)
            var str = $("#personmaps").text();//拿到所有人员信息的值
            var json = eval('(' + str + ')');
            $.each(json, function (index, item) {
                if (pid == item.ID) {
                    if (item.IMGURL == undefined) {
                        that.parents(".layui-col-md6").prev().find("img").attr("src", baseurl + "/upload/viewPhoto.html?imgPath=D:/static/images/upload/renren.png")
                    } else {
                        that.parents(".layui-col-md6").prev().find("img").attr("src", baseurl + "/upload/viewPhoto.html?imgPath=" + item.IMGURL)
                    }
                    if (item.SEX == '0') {
                        that.parents(".layui-col-md6").find(".sex").html("男");
                    }else if(item.SEX == '1') {
                        that.parents(".layui-col-md6").find(".sex").html("女");
                    }else {
                        that.parents(".layui-col-md6").find(".sex").html("数据缺失");
                    }
                    that.parents(".layui-col-md6").find(".work").html(item.LICENSENUMBER);
                }
            });
        });


        //邮寄信息切换
        form.on('radio(instrumentFilter)', function (data) {
            var type = parseInt(data.value);
            //案件登记
            $("input[name='picker']").attr("value", "");
            $("input[name='pickerPhone']").attr("value", "");
            $("input[name='addressee']").attr("value", "");
            $("input[name='addresseeAddress']").attr("value", "");
            $("input[name='addresseePhone']").attr("value", "");
            $("input[name='sendDescription']").attr("value", "");
            switch (type) {
                case (1):
                    //自取
                    $("#takeReport").removeClass("layui-hide");
                    $("#sendReport").addClass("layui-hide");
                    $("#otherReport").addClass("layui-hide");
                    break;
                case (2):
                    //邮寄
                    $("#takeReport").addClass("layui-hide");
                    $("#sendReport").removeClass("layui-hide");
                    $("#otherReport").addClass("layui-hide");
                    break;
                case (3):
                    //其他
                    $("#takeReport").addClass("layui-hide");
                    $("#sendReport").addClass("layui-hide");
                    $("#otherReport").removeClass("layui-hide");
                    break;
                default:
                    break;
            }
            form.render();
        });






        //亲缘关系切换
        form.on('select(appellationVal)', function (data){
            kinName=$(data.elem).find("option:selected").text();
            /*alert(flag);*/
            if(flag == "add"){
                var code=$("#majorCode").val();//拿到鉴定类型code
                var codes = code.substring(0, 1) + code.substring(3, 2);
                var CurrentYear = $("#CurrentYear").val();//拿到时间
                var CurrentYears = CurrentYear.substring(2, 4);
                var count=0;
                var flags=0;

                var  selectKinshipName=$(data.elem).find("option:selected").text();//获取已选亲缘关系值
                //获取检材列表里面已有的亲缘关系
                if(selectKinshipName=='母亲' || selectKinshipName=='父亲'){
                    $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                        if(selectKinshipName==$(this).val()){
                            flags=1;
                            $("#kinship").val("");
                            form.render();
                            layer.msg('只能上传一个亲缘关系为'+selectKinshipName+"的检材信息！！", {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    });
                    if(flags!=1){
                        if(selectKinshipName=='母亲'){
                            count++;
                        }else if(selectKinshipName=='父亲'){
                            $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                count++;
                            });
                            count++;
                        }
                    }

                }else{
                    count=0;
                    //所选亲缘关系为子女
                    $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                        if(selectKinshipName==$(this).val() || $(this).val()=='母亲'){
                            count++;
                        }
                    });
                    count++;
                }


                if(flags==1){
                    return false;
                }

                var index;
                if(count<10){
                    index="0"+count.toString();
                }else{
                    index=count.toString();
                }
                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                //获取已有检材的数量
                $("#sampleSn").val(sampleSn);
            }else if(flag == "modify"){
                //修改检材信息
                var code=$("#majorCode").val();//拿到鉴定类型code
                var codes = code.substring(0, 1) + code.substring(3, 2);
                var CurrentYear = $("#CurrentYear").val();//拿到时间
                var CurrentYears = CurrentYear.substring(2, 4);
                var fflg=0;
                var fcount=0;
                var  selectKinshipName=$(data.elem).find("option:selected").text();//获取已选亲缘关系值
                if(havekinshipName==selectKinshipName){
                    $("#kinship").val(haveKinshipId);
                    form.render();
                    return false;
                }else{
                    if(selectKinshipName=='母亲'){
                        //从子女或者 父亲 过来的
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if(selectKinshipName==$(this).val()){
                                fflg=1;
                                $("#kinship").val(haveKinshipId);
                                form.render();
                                layer.msg('已经有一个亲缘关系为'+selectKinshipName+"的检材信息！！", {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                                return false;
                            }
                        });
                        if(fflg!=1){
                            //可以修改
                            fcount=1;
                        }

                    }else if(selectKinshipName=='子女'){
                        //从父亲或者母亲 过来的
                        fflg=1;

                    }else if(selectKinshipName=='父亲'){
                        //从子女 或者母亲 过来的
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if(selectKinshipName==$(this).val()){
                                fflg=1;
                                $("#kinship").val(haveKinshipId);
                                form.render();
                                layer.msg('已经有一个亲缘关系为'+selectKinshipName+"的检材信息！！", {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                                return false;
                            }
                        });
                        if(fflg!=1){
                            //可以修改
                            $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                fcount++;
                            });
                        }
                    }

                    if(fflg==1){
                        return false;
                    }

                    var index;
                    if(fcount<10){
                        index="0"+fcount.toString();
                    }else{
                        index=fcount.toString();
                    }
                    var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                     //获取已有检材的数量
                    $("#sampleSn").val(sampleSn);
                }
            }

        });

        form.on('radio(payfilter)', function (data) {
            var type = data.value;
            //首先清空地址内容
            $("#payMsg").val("");
            var text = $(data.elem).attr("title");
            if (text != "其他") {
                $("#payMsg").hide();
            } else {
                $("#payMsg").show();
                $("#payMsg").val($("#pmsg").val());
            }
        });

        form.on('radio(chargefilter)', function (data) {
            var type = data.value;
            $("#chargeAmount").show();
            if (type == "1") {
                $("#chargeAmount").prop("readOnly", "readOnly");
                $("#chargeAmount").val($("#stCharge").val());
            } else {
                $("#chargeAmount").removeAttr("readOnly");
                $("#chargeAmount").val("");
            }
        });

        //初始化鉴定项目
        var lbid = $("#idenCheck").val();
        var code = $("#idenCheck").attr("code");
        var shortName=$("#idenCheck").attr("shortName");

        sampleArr = randomString(16);//获取随机数组

        initIdenProject(lbid, code,shortName, 1);
        //切换不同的鉴定类别  展示不同的鉴定项目
        form.on('radio(filter)', function (data) {
            initIdenProject(data.value, $(data.elem).attr("code"),$(data.elem).attr("shortName"), 2);
            /*isupdate($(data.elem).attr("code"));*/
        });

        //切换不同类型的委托方
        form.on('radio(filterClient)', function (data) {
            //var clientType = data.value;

            var clientType = $(data.elem).attr("princiType");
            var clientText=$(data.elem).attr("title");
            if(clientText.indexOf("个人") < 0) {
                $("#clientCategory").attr("disabled","none");
                //请求后台委托方接口数据
                $.ajax({
                    url: "getClinetData.html?clientType=" + clientType,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        $("#clientData").empty();//清空委托方select 数据
                        var text = "";
                        text += '<select name="clientCategory" id="clientCategory" lay-search  lay-filter="DispayVal">';
                        text += '<option value=""></option>';
                        $.each(data, function (index, item) {
                            text += '<option value="' + item.id + '">' + item.principalName + '</option>';
                        });
                        text += '</select>';
                        $("#clientData").append(text);
                        form.render();
                    }
                });
            }else{
                $("#clientCategory").attr("disabled","disabled");
                form.render();
            }
            /*if (clientType == "1") {
             $(".clientCategory").show();
             $(".addPrincipal").show();
             }else {
             $(".clientCategory").show();
             $(".addPrincipal").show();
             }*/

        });

        //被鉴定人上传照片
        var photoView = $('#memberInfoPhoto')
            , uploadInst = upload.render({
            elem: '.addMenberPhoto'
            , url: baseurl + '/case/saveMemberPhoto.html'
            , before: function (obj) {

            }
            , choose: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $("#memberInfoPhoto").attr('src', result); //图片链接（base64）)
                    form.render()
                });
            }
            , done: function (res) {
                //如果上传失败
                if (res.code > 0) {
                    return layer.msg('上传失败');
                }

                $("#memberId").val(res.memberId);
                return true;
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        //照片上传
        var map = {};
        var mapsample = {};
        var files;
        var demoListView = $('#demo2')
            , uploadListIns = upload.render({
            elem: '#test2'
            , url: baseurl + '/upload/fileUpload.html'
            , multiple: true
            , accept: 'file'//可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            , auto: false
            , bindAction: '#saveFile'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files, function (key, values) {
                    var fileName = files[key].name;
                    map[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).val();
                    mapsample[fileName] = $("#sample" + fileName.substring(0, fileName.indexOf("."))).val();
                });
                this.data = {
                    'dataMap': JSON.stringify(map),
                    'caseId': $("#caseId").val(),
                    'sampleMap': JSON.stringify(mapsample),
                    'code':$("#majorCode").val()
                };
            }
            , choose: function (obj) {
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {
                    var newOption = "";
                    var code=$("#majorCode").val();//拿到鉴定类型code
                    $("#samplesTable").find("input[name='sampleName']").each(function () {
                        var sampleSn=$(this).parents('tr').find("input[name='sampleSn']").val();
                        if("FYLC"===code){
                            newOption += '<option value="' + sampleSn + '">' + sampleSn + '</option>'
                        }else{
                            var sn=sampleSn.substring(sampleSn.length,sampleSn.length-2);
                            newOption += '<option value="' + $(this).val() + '">' + sn+"-"+$(this).val() + '</option>'
                        }
                    })

                    var fileSuffix=file.name.substring(file.name.indexOf(".")+1,file.name.length);
                    var stuffText="";
                    if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                        stuffText='<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">';
                    }else {
                        stuffText='<img src="../img/img/word.png" alt="' + file.name + '" class="layui-upload-img"><p class="wordP" title="'+file.name+'">'+file.name+'</p>';
                    }

                    var text = $(['<span class="imgBox layui-form">'
                        , '<i class="layui-icon demo-delete">&#x1007;</i>'
                        , stuffText
                        , '<select class="imgselect" id="sample' + file.name.substring(0, file.name.indexOf(".")) + '"><option value="00"></option>' + newOption + '</select>'
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

        /*创建新的委托方*/
        $("button[name='addPrincipalBtn']").on('click', function () {
            layer.open({
                type: 1,
                title: "委托方管理",
                // closeBtn: 2,
                shadeClose: true,
                area: ['600px', 'auto'],
                // height:100px,
                shade: 0.8,
                id: 'LAY_layuipro',
                btnAlign: 'c',
                // maxmin: true, //开启最大化最小化按钮
                moveType: 1,
                content: $('#addPrincipalWindow'),
                end: function () {
                    $("#addPrincipalWindow").find("input[name='principalName']").val("");//委托方名称
                    $("#addPrincipalWindow").find("input[name='principalTelephone']").val("");//委托方联系方式
                    $("#addPrincipalWindow").find("#provinceName").val("");//委托方地址(省)
                    $("#addPrincipalWindow").find("#province").val("");//委托方地址(省)
                    $("#addPrincipalWindow").find("#cityName").val("");//市
                    $("#addPrincipalWindow").find("#city").val("");//市
                    $("#addPrincipalWindow").find("#countyName").val("");//县
                    $("#addPrincipalWindow").find("#county").val("");//县
                    $("#addPrincipalWindow").find("[name='principalNature']").val("");//委托方性质
                    $("#addPrincipalWindow").find("input[name='principalLoginname']").val("");//登录名称
                    $("#addPrincipalWindow").find("#principalPassword").val("");//密码
                    $("#addPrincipalWindow").find("input[name='principalPassword']").val("");//确认密码
                    $("#addPrincipalWindow").find("input[name='principalContact']").val("");//委托方联系人
                    $("#addPrincipalWindow").find("input[name='principalContactPhone']").val("");//委托方联系人电话
                    $("#addPrincipalWindow").find("input[name='principalzipcodelp']").val("");//邮编
                    $("#addPrincipalWindow").find("input[name='principalContactlp']").val("");//联系人
                    $("#addPrincipalWindow").find("input[name='principalContactmobilelp']").val("");//联系人电话
                    form.render();
                }
            });
        });
        /*鉴定标准弹窗*/
        $("#addjdbz").click(function () {
            layer.open({
                type: 1,
                title: '添加鉴定标准',
                area: '460px',
                offset: '100px',
                content: $("#jdbz") //这里content是一个普通的String
            });
        })
        /*添加标准*/
        form.on('checkbox(identifyNormal)', function (data) {
            if (data.elem.checked) {
                var newBz = ' <div class="layui-col-md12" title="' + $(data.elem).attr("title") + '">' + $(data.elem).attr("title") + '<i class="fa fa-times-circle removebz" aria-hidden="true"></i></div>'
                $("#jdbzTable").find(".layui-col-md9").append(newBz)
            } else {
                $("#jdbzTable").find(".layui-col-md12:contains('" + $(data.elem).attr("title") + "')").remove()
            }
            if ($("#jdbzTable").find(".layui-col-md9").children().length > 0) {
                $("#removeAlljdbz").removeClass("layui-hide")
                $("#addjdbz").text("重新选择")
            } else {
                $("#removeAlljdbz").addClass("layui-hide")
                $("#addjdbz").text("选择")
            }
        });
        /*删除标准*/
        $("#removeAlljdbz").click(function () {
            $("#jdbzTable").find(".layui-col-md9").children().remove()
            $("#jdbz").find("input:checkbox").prop("checked", false)
            form.render('checkbox');
            $("#removeAlljdbz").addClass("layui-hide")
            $("#addjdbz").text("选择")
        })
        //*点击关闭*/
        $("#saveJdbz").click(function () {
            layer.closeAll()
        })
        /*单个删除*/
        $("#jdbzTable").on("click", ".removebz", function () {
            $("#jdbz").find("input[title='" + $(this).parents('.layui-col-md12').attr('title') + "']").prop("checked", false)
            form.render('checkbox');
            if ($(this).parents(".layui-col-md9").children().length == 1) {
                $("#removeAlljdbz").addClass("layui-hide")
                $("#addjdbz").text("选择")
            }
            $(this).parent().remove()

        })

        form.on('submit(addPrincipal)', function (data) {
            var url = baseurl + "/savePrincipalInfo.html";
            $.post(url, data.field, function (json) {
                if (json) {
                    layer.closeAll();
                    layer.msg(json.message, {
                        icon: json.success ? 1 : 2, time: 2000, success: function () {
                            if (json.success) {
                                $("input[name='censorship']").val(json.data.principalContact);
                                $("input[name='telephone']").val(json.data.principalContactPhone);
                               /* $("input[name='contacts']").val(json.data.principalName);
                                $("input[name='contactsTelephone']").val(json.data.principalTelephone);*/

                                var $clientCategory = $("#clientCategory");
                                $clientCategory.append("<option value='" + json.data.id + "'>" + json.data.principalName + "</option>")
                                $clientCategory.val(json.data.id);
                                form.render("select");
                            }
                        }
                    });
                } else {
                    layer.msg('服务器异常,请稍后重试!', {icon: 2, time: 2000});
                }
            });
            return false;
        });


        form.verify({
            pass: function (val) {
                if (val.length > 0 && !/^[\S]{6,16}$/.test(val)) {
                    return '密码必须6到16位，且不能出现空格';
                }
            },
            confirmPass: function (val) {
                if (val !== $("#principalPassword").val()) {
                    return '确认密码与密码不符';
                }
            }
        });


        $("#submitCheckCaseInfo").on("click", function () {
            var status = $(this).attr("value");
            var caseId = $(this).attr("caseId");
            //获取案件的基本信息
            //鉴定类别
            /*var a = $("input[name='majorSerialNo']:checked").val();
             $("#appraisalProject").val(a);*/
            //专业code
            /* var code = $("input[name='majorCategoryId']:checked").attr("code");
             $("#majorCode").val(code);*/
            //鉴定项目
            var arr = [];
            //鉴定项目
            $("input[name='appraisalMajorId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    arr.push($(this).attr("title"));
                }
            });

            if (arr.length == 0) {
                layer.msg('请选择鉴定项目！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#appraisalMajor").val(arr);

            //鉴定标准
            var bzarr = [];
            //鉴定标准
            $("input[name='appraisalNormalId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    bzarr.push($(this).attr("title"));
                }
            });

            /* if (bzarr.length == 0) {
             layer.msg('请选择鉴定标准！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*/
            $("#appraisalNormal").val(bzarr);

            //鉴定方法
           /* var ffarr = [];
            //鉴定方法
            $("input[name='identifiMethodId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    ffarr.push($(this).attr("value"));
                }
            });
             if (ffarr.length == 0) {
             layer.msg('请选择鉴定方法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }
            $("#identifiMethod").val(ffarr);*/

            //案件类型名称
            var caseTypeName = $("[name='caseType']").find("option:selected").text();
            $("#caseTypeName").val(caseTypeName);

            //受理人姓名
            var assigneeName = $("[name='assigneeId']").find("option:selected").text();
            $("#assigneeName").val(assigneeName);
            //第一鉴定人姓名
            var appraiserOneName = $("[name='appraiserOneId']").find("option:selected").text();
            $("#appraiserOneName").val(appraiserOneName);
            //第二鉴定人姓名
            var appraiserSecondName = $("[name='appraiserSecondId']").find("option:selected").text();
            $("#appraiserSecondName").val(appraiserSecondName);
            //第三鉴定人姓名
            var appraiserThirdName = $("[name='appraiserThirdId']").find("option:selected").text();
            $("#appraiserThirdName").val(appraiserThirdName);

            var repeat = judgIdenRepeat();
            if (repeat == true) {
                layer.msg('请修改第二鉴定人信息,不能与其他重复！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            //被鉴定人信息不能与回避人信息重复


            var val=$('input:radio[name="isApplication"]:checked').val();
            if(val=="0"){
                //是否申请回避人  否
            }else if(val=="1"){
                //是否申请回避人  是
                var avoidanceRepeat = judgAvoidanceRepeat();
                if (avoidanceRepeat == true) {
                    layer.msg('请修改鉴定人信息,不能与回避人重复！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }

            //验证身份证件号
            var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/;
            var censorCard = $("#censorCard").val();
            var idCard = $("[name='idCard']").val();
            if (reg.test(censorCard) == false && censorCard != "") {
                layer.msg('送检人证件号码不合法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (reg.test(idCard) == false && idCard != "") {
                layer.msg('身份证输入不合法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //验证手机号
            var regexp = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
            var dacasCaseChargetelephone = $("#dacasCaseChargetelephone").val();
            var addressePhone = $("[name='addresseePhone']").val();
            var telephone = $("#telephones").val();
            var contactsTelephone = $("#contactsTelephon").val();
            var pickePhone = $("#pickePhone").val();
            if (regexp.test(pickePhone) == false && pickePhone != "") {
                layer.msg('邮寄信息自取手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(addressePhone) == false && addressePhone != "") {
                layer.msg('邮寄手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(dacasCaseChargetelephone) == false && dacasCaseChargetelephone != "") {
                layer.msg('手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(telephone) == false && telephone != "") {
                layer.msg('送检人手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(contactsTelephone) == false && contactsTelephone != "") {
                layer.msg('承办人手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //验证数字
            var re = /^[0-9]+.?[0-9]*$/;
            var sampleNumber = $("[name='sampleNumber']").val();
            if (re.test(sampleNumber) == false && sampleNumber != "") {
                layer.msg('样本数输入有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //保存案件信息
            $("#myCaseForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                data: {"status": status, "caseId": caseId},
                success: function (data) {
                    if (data.success == true) {
                        layer.msg('案件受理成功！！', {icon: 1, offset: '250px', time: 500});
                        window.location.href = baseurl + "/case/caseAdmissibles.html?entity.caseStatus=" + data.caseStatus;
                    } else {
                        layer.msg('案件受理失败！！', {icon: 5, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                }
            });
        });


        //提交案件所有信息
        $("#submitCaseInfo").on("click", function () {
            //委托方类型
            var clientList = $('input:radio[name="clientType"]:checked').val();
            var clientText = $('input:radio[name="clientType"]:checked').attr("title");
            if (clientList == null) {
                layer.msg('请选委托方类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if(clientText.indexOf("个人") < 0){
                //委托方
                var clientCategory = $("#clientCategory").val();
                if (clientCategory == "") {
                    layer.msg('请选择委托方！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }

            //鉴定类别
            //var a = $("input[name='appraisalProjectId']:checked").val();
            //$("#appraisalProject").val(a);
            //鉴定项目
            var arr = [];
            //鉴定项目
            $("input[name='appraisalMajorId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    arr.push($(this).attr("title"));
                }
            });

            if (arr.length == 0) {
                layer.msg('请选择鉴定项目！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            $("#appraisalMajor").val(arr);


            //鉴定标准
            var bzarr = [];
            //鉴定标准
            $("input[name='appraisalNormalId']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    bzarr.push($(this).attr("title"));
                }
            });

            /* if (bzarr.length == 0) {
             layer.msg('请选择鉴定标准！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*/
            $("#appraisalNormal").val(bzarr);


            //鉴定方法
            /*var ffarr = [];*/
            //鉴定方法
            /*$("input[name='identifiMethodId']:checkbox").each(function (index) {
             if ($(this).prop("checked") === true) {
             ffarr.push($(this).attr("value"));
             }
             });*/

            /*if (ffarr.length == 0) {
             layer.msg('请选择鉴定方法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*/
           /* $("#identifiMethod").val(ffarr);*/


            //受理日期
            var acceptAt = $("#acceptAt").val();
            if (acceptAt == "") {
                layer.msg('请选择受理日期！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //案件信息来源类型
           /* var casesourceType = $("#casesourceType").val();
            if (casesourceType == "") {
                layer.msg('请选择案件信息来源类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }*/
            //验证身份证件号
            var reg = /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/;
            var CensorShipCard = $("#censorshipCard").val();
            var IdCardValue = $("[name='authenticator0Id']").val();
            if (reg.test(CensorShipCard) == false && CensorShipCard != "") {
                layer.msg('送检人证件号码不合法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (reg.test(IdCardValue) == false && IdCardValue != "") {
                layer.msg('身份证输入不合法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //验证手机号
            var regexp = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
            var pickPhone = $("#pickerPhone").val().trim();
            var dacasCaseChargetelephone = $("#dacasCaseChargetelephone").val().trim();
            var telephone = $("#telephones").val().trim();
            var addresseePhone = $("#addresseePhone").val().trim();
            var contactsTelephone = $("#contactsTelephone").val().trim();
            if (regexp.test(pickPhone) == false && pickPhone != "") {
                layer.msg('邮寄信息自取手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(addresseePhone) == false && addresseePhone != "") {
                layer.msg('邮寄信息联系人手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(dacasCaseChargetelephone) == false && dacasCaseChargetelephone != "") {
                layer.msg('财务信息手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(telephone) == false && telephone != "") {
                layer.msg('送检人手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            if (regexp.test(contactsTelephone) == false && contactsTelephone != "") {
                layer.msg('承办人手机号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //验证数字
            var re = /^[0-9]+.?[0-9]*$/;
            var sampleNumber = $("[name='sampleNumber']").val();
            var timeLimit = $("#timeLimit").val();
            if (re.test(sampleNumber) == false && sampleNumber != "") {
                layer.msg('样本数输入有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //if (re.test(timeLimit) == false) {
            //    layer.msg('鉴定时限必须为数字，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            //    return false;
            //}
            //验证纳税人识别号
            //var taxpayerId = $("#taxpayerRegNums").val().trim();
            //if (taxpayerId.length != 15 && taxpayerId != "") {
            //    layer.msg('财务信息纳税人识别号不正确，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            //    return false;
            //} else {
            //    // 校验地址码
            //    var addressCode = taxpayerId.substring(0, 6);
            //    var check = checkAddressCode(addressCode);
            //    if (!check && check == "") {
            //        return false;
            //    }
            //    //校验组织机构代码
            //    var orgCode = taxpayerId.substring(6, 9);
            //    var check = isValidOrgCode(orgCode);
            //    if (!check && check == "") {
            //        return false;
            //    }
            //}
            // 校验组织机构代码
            //function isValidOrgCode(value) {
            //    var ws = [3, 7, 9, 10, 5, 8, 4, 2];
            //    var str = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
            //    var reg = /^([0-9A-Z]){8}-[0-9|X]$/;
            //    var sum = 0;
            //    for (var i = 0; i < 8; i++) {
            //        sum += str.indexOf(value.charAt(i)) * ws[i];
            //    }
            //    var c9 = 11 - (sum % 11);
            //    c9 = c9 == 10 ? 'X' : c9
            //    if (reg.test(value) == false && c9 == value.charAt(9) && value != "") {
            //        return false;
            //    } else {
            //        return true;
            //    }
            //}

            // 校验地址码
            //function checkAddressCode(addressCode) {
            //    var provinceAndCitys = {
            //        11: "北京",
            //        12: "天津",
            //        13: "河北",
            //        14: "山西",
            //        15: "内蒙古",
            //        21: "辽宁",
            //        22: "吉林",
            //        23: "黑龙江",
            //        31: "上海",
            //        32: "江苏",
            //        33: "浙江",
            //        34: "安徽",
            //        35: "福建",
            //        36: "江西",
            //        37: "山东",
            //        41: "河南",
            //        42: "湖北",
            //        43: "湖南",
            //        44: "广东",
            //        45: "广西",
            //        46: "海南",
            //        50: "重庆",
            //        51: "四川",
            //        52: "贵州",
            //        53: "云南",
            //        54: "西藏",
            //        61: "陕西",
            //        62: "甘肃",
            //        63: "青海",
            //        64: "宁夏",
            //        65: "新疆",
            //        71: "台湾",
            //        81: "香港",
            //        82: "澳门",
            //        91: "国外"
            //    };
            //    var check = /^[1-9]\d{5}$/.test(addressCode);
            //    if (!check && addressCode != "") {
            //        return false;
            //    } else {
            //        if (provinceAndCitys[parseInt(addressCode.substring(0, 2))] == false && provinceAndCitys[parseInt(addressCode.substring(0, 2))] != "") {
            //            return false;
            //        }
            //    }
            //
            //}
            //案件类型名称
            var caseTypeName = $("[name='caseType']").find("option:selected").text();
            $("#caseTypeName").val(caseTypeName);

            //受理人姓名
            var assigneeName = $("[name='assignee']").find("option:selected").text();
            $("#assigneeName").val(assigneeName);
            //第一鉴定人姓名
            var appraiserOneName = $("[name='appraiserOneId']").find("option:selected").text();
            $("#appraiserOneName").val(appraiserOneName);
            //第二鉴定人姓名
            var appraiserSecondName = $("[name='appraiserSecondId']").find("option:selected").text();
            $("#appraiserSecondName").val(appraiserSecondName);
            //第三鉴定人姓名
            var appraiserThirdName = $("[name='appraiserThirdId']").find("option:selected").text();
            $("#appraiserThirdName").val(appraiserThirdName);
            //授权人姓名
            var authorizerName = $("[name='authorizerId']").find("option:selected").text();
            $("#authorizerName").val(authorizerName);
            //回避人姓名
            var avoidanceName = $("[name='avoidance']").find("option:selected").text();
            $("#avoidanceName").val(avoidanceName);

            var repeat = judgIdenRepeat();
            if (repeat == true) {
                layer.msg('请修改第二鉴定人信息,不能与其他重复！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            //给案件编号进行赋值
            $("#casesAcceptNumber").val($("input[name='casesNumber']").val() + $("#authenticWord").text() + "第" + $("#caseAmount").val() + "号");
            var jsonstr = "[]";
            var jsonarray = eval('(' + jsonstr + ')');

            //获取被鉴定人信息
            $("#memberInfoList").children().each(function (i) {
                var id = $(this).find("[name='authenticator" + i + "id']").val();//id
                var name = $(this).find("[name='authenticator" + i + "Name']").val();//姓名
                var gender = $(this).find("[name='authenticator" + i + "Sex']:checked").val();//性别
                var genderName = $(this).find("[name='authenticator" + i + "Sex']:checked").attr("title");//性别名称
                var idCard = $(this).find("[name='authenticator" + i + "Id']").val();//证件号码
                var birthdate = $(this).find("[name='authenticator" + i + "Birthday']").val();//出生日期
                var cardType = $(this).find("[name='authenticator" + i + "Idtype']").find("option:selected").val();//证件类型
                //var job = $(this).find("[name='authenticator" + i + "Work']").val();//职业
                var nation = $(this).find("[name='authenticator" + i + "Nation']").val();//民族
                var nationName = $(this).find("[name='authenticator" + i + "Nation']").find("option:selected").text();//民族名称
                // var id = $(this).find("[name='memberId']").val();

                if(name!=null && name!=""){
                    var data;
                    data = {
                        "id": id,
                        "name": name,
                        "gender": gender,
                        "genderName": genderName,
                        "idCard": idCard,
                        "birthdate": birthdate,
                        "cardType": cardType,
                        "nation": nation,
                        "nationName":nationName
                    };
                    jsonarray.push(data);
                }

            });

            //获取检材列表信息
            var sampleStr = "[]";
            var sampleArray = eval('(' + sampleStr + ')');
            //获取检材列表信息
            //获取整个table的tr
            $('#samplesTable tr').each(function (i) {
                var remark = $(this).find("[name='remark']").val();//检材备注
                var personship = $(this).find("[name='personship']").val();//人员关系
                var sampleName = $(this).find("[name='sampleName']").val();//检材名称
                var sampleType = $(this).find("[name='sampleType']").val();//检材类型
                var sampleTypeName = $(this).find("[name='sampleTypeName']").val();//检材类型名称
                var amount = $(this).find("[name='amount']").val();//检材数量
                var measurementUnit = $(this).find("[name='measurementUnit']").val();//检材数量单位
                var measurementUnitName = $(this).find("[name='measurementUnitName']").val();//单位名称

                var kinship = typeof($(this).find("[name='kinship']").val()) == 'undefined' ? "" : $(this).find("[name='kinship']").val();//亲缘关系

                var samplingTime = typeof($(this).find("[name='samplingTime']").val()) == 'undefined' ? "" : $(this).find("[name='samplingTime']").val();//提取时间
                var samplePerson = typeof($(this).find("[name='samplePerson']").val()) == 'undefined' ? "" : $(this).find("[name='samplePerson']").val();//采样人
                var samplingAddress = typeof($(this).find("[name='samplingAddress']").val()) == 'undefined' ? "" : $(this).find("[name='samplingAddress']").val();//采样地点


                var receiveTime = typeof($(this).find("[name='receiveTime']").val()) == 'undefined' ? "" : $(this).find("[name='receiveTime']").val();//接收时间

                var kinshipName = typeof($(this).find("[name='kinshipName']").val()) == 'undefined' ? "" : $(this).find("[name='kinshipName']").val();//亲缘关系

                var collectionTime = typeof($(this).find("[name='collectionTime']").val()) == 'undefined' ? "" : $(this).find("[name='collectionTime']").val();//采样时间
                var sampleSn = typeof($(this).find("[name='sampleSn']").val()) == 'undefined' ? "" : $(this).find("[name='sampleSn']").val();//检材编号
                var saveState = typeof($(this).find("[name='saveState']").val()) == 'undefined' ? "" : $(this).find("[name='saveState']").val();//保存状态
                var siteSampling = typeof($(this).find("[name='siteSampling']").val()) == 'undefined' ? "" : $(this).find("[name='siteSampling']").val();//现场采样
                var homeSampling = typeof($(this).find("[name='homeSampling']").val()) == 'undefined' ? "" : $(this).find("[name='homeSampling']").val();//上门采样
                var takeSample = typeof($(this).find("[name='takeSample']").val()) == 'undefined' ? "" : $(this).find("[name='takeSample']").val();//上门拿样
                var sampleData;
                sampleData = {
                    "sampleName": sampleName,
                    "sampleType": sampleType,
                    "sampleTypeName": sampleTypeName,
                    "measurementUnitName": measurementUnitName,
                    "amount": amount,
                    "measurementUnit": measurementUnit,
                    "kinship": kinship,
                    "kinshipName": kinshipName,
                    "samplingTime": samplingTime,
                    "samplePerson": samplePerson,
                    "samplingAddress": samplingAddress,
                    "receiveTime": receiveTime,
                    "remark": remark,
                    "personship": personship,
                    "collectionTime": collectionTime,
                    "sampleSn": sampleSn,
                    "saveState": saveState,
                    "siteSampling": siteSampling,
                    "homeSampling": homeSampling,
                    "takeSample": takeSample
                };
                sampleArray.push(sampleData);
            });

           var caseCount=$("#caseAmount").val();
            //保存案件信息
            $("#myCaseForm").ajaxSubmit({
                type: "post",
                async: false,
                dataType: "json",
                data: {"memberInfoData": JSON.stringify(jsonarray), "sampleData": JSON.stringify(sampleArray),"caseCount":caseCount},
                success: function (data) {
                    if (data.success == "true") {

                        $("#caseId").val(data.caseId);//后台案件id给传回来

                        layer.msg('案件信息录入成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            //如果没有照片 就直接跳转
                            if (demoListView.find("span").length > 1) {
                                $("#saveFile").click();
                            } else {
                                $('#downFile').trigger("click");
                                /*window.location.href=baseurl+"/case/entrustCase.html";*/
                            }

                        });
                    }else if(data.success=="doubleClick"){
                        //layer.msg('案件信息录入成功！！', {icon: 1, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    }
                    else {
                        layer.msg('案件录入失败！！', {icon: 5, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                }
            });
        });


        //上传页面跳转
        $("button[name='downFile']").on("click", function () {
            /* window.location.href = baseurl + "/case/caseAcceptSuccess.html?caseId="+$("#caseId").val();*/
            /*var code = $("#majorCode").val();*/
            window.location.href = baseurl + "/appraisal/documentGenerateDetails.html?caseId=" + $("#caseId").val() + "&flag=1";
        });

        //添加检材
        $("#addsamples").click(function () {
            var title="新增检材信息";
            var code = $("#majorCode").val();
            if (code == "FYLC") {
                title="新增鉴定材料";
            }

            var count = 0;
            //首先 循环查一下 被鉴定人的信息
            $("#personship").empty();

            $("#memberInfoList").children().each(function (i) {
                var name = $(this).find("[name='authenticator" + i + "Name']").val();//姓名
                if (name != "") {
                    //取得姓名添加到检材信息的人员选型中
                    var tt = '<option></option> <option value="' + sampleArr[i] + '">' + name + '</option>';
                    $("#personship").append(tt);
                    form.render();//必须有这句不然不渲染
                    count++;
                }
            });

            /*var code = $("#majorCode").val();
            if (code == "FYDW" || code == "DWQT") {

            } else {
                if (count == 0) {
                    alert("至少有一个被鉴定人信息！！");
                    return false;
                }
            }*/
            $("#sampleForm").resetForm();//清空表单

            var code=$("#majorCode").val();//拿到鉴定类型code
            var CurrentYear = $("#CurrentYear").val();//拿到时间
            var CurrentYears = CurrentYear.substring(2, 4);
            var codes = code.substring(0, 1) + code.substring(3, 2);

            var wzCodeArr = new Array('FYWZ', 'WZZX');//法医物证专业code---微量物证不在之中
            if($.inArray(code, wzCodeArr) >= 0){
                //将检材编号置空
                $("#sampleSn").val("");

            }else{
                //为检材编号赋值
                var number=parseInt($("#samplesTable tr").length)+1;
                var index;
                if(number<10){
                    index="0"+number.toString();
                }else{
                    index=number.toString();
                }
                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                //获取已有检材的数量
                $("#sampleSn").val(sampleSn);
            }

            flag = "add";
            layer.open({
                type: 1,
                title:title,
                area: '800px',
                id: 'LAY_layuipro',
                content: $('#sampleForm'),
                end: function () {
                    $("#sampleForm")[0].reset()
                    form.render();
                }

            });


            /*layer.open({
             type: 1,
             title: "新增检材信息",
             closeBtn: 2,
             area: '800px;',
             shade: 0.8,
             id: 'LAY_layuipro',
             btnAlign: 'c',
             moveType: 1,
             content: $('#sampleForm')
             });*/
        })

        var options = ''
        // 保存检材信息
        $("#addSampleInfo").click(function () {
            var code = $(this).val();//拿到专业的code
            var CurrentYear = $("#CurrentYear").val();//拿到时间
            var CurrentYears = CurrentYear.substring(2, 4);
            var codes = code.substring(0, 1) + code.substring(3, 2);
            //检材公共字段提取出来

            //检材编号
            var sampleSn = $("#sampleSn").val();

            //被鉴定人姓名
           /* if (code == "FYDW" || code == "DWQT") {

            } else {
                var personship = $("#personship").val();
                if (personship == "") {
                    layer.msg('请选择人员关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }*/

               //检材名称
               var sampleName = $("#sampleName").val();
            //人员名称
            var samplePerson = $("#samplePerson").val();
            /*    if (sampleName == "") {
             layer.msg('请输入检材名称！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*/
            //检材类别
            var sampleType = $("#sampleType").val();
            /* if (sampleType == "") {
                layer.msg('请选择检材类别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
             }*/
            //检材数量
            var amount = $("#amount").val();
            /*if (amount == "") {
                layer.msg('请输入数量！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }*/

            /*if(code!='WLWZ'){
                //数量单位
                var measurementUnit = $("#measurementUnit").val();
                if (measurementUnit == "") {
                    layer.msg('请选择数量单位！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    return false;
                }
            }*/

            //备注
            var remark = $("#remark").val();
            /*          if (remark == "") {
             layer.msg('请输入检材备注！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*/

            var wzCodeArr=new Array('FYWZ', 'WZZX');//法医物证
            var wzArr = new Array('FYWZ', 'WZZX');//法医物证
            var wLwzArr = new Array('WLWZ');//微量物证
            var dwArr = new Array('FYDW', 'DWQT'); //法医毒物
            var blArr = new Array('FYBL', 'BLQT'); //法医病理
            var lcArr = new Array('FYLC'); //法医临床
            var wjArr = new Array('WJJY','FYSW'); //文件检验
            var hjArr = new Array('HJJY', 'CLPG', 'CLJY'); //痕迹检验
            var sxArr = new Array('TXJY'); //图像检验
            var dzArr = new Array('DZSJ'); //电子数据
            var jsArr = new Array('FYJSB');//法医精神病


            var wzstr = new Array('亲缘关系', '接收时间', '提取时间', '采样人', '采样地点', '采样时间');
            var wlwzstr = new Array('收样日期','采样人');
            var dwstr = new Array('采样人', '采样时间', '现场采样', '上门采样', '上门拿样');
            var wtdstr = new Array('接收时间', '提取时间');

            var wzFieldName = new Array('kinship', 'receiveTime', 'samplingTime', 'samplePerson', 'samplingAddress', 'collectionTime','saveState'); //物证检材信息
            var wlwzFieldName = new Array('kinship', 'receiveTime','samplePerson','saveState');//物证检材信息
            var dwFieldName = new Array('samplePerson', 'collectionTime','siteSampling','homeSampling','takeSample');//毒物检材信息
            // var blFieldName=new Array('receiveTime','samplingTime','samplePerson','samplingAddress','collectionTime','saveState');//病理检材信息
            var lcFieldName = new Array('saveState');//临床检材信息
            var wjFieldName = new Array('receiveTime', 'samplingTime','saveState');//文检检材信息
            var hjFieldName = new Array('saveState');//痕迹检材信息
            var sxFieldName = new Array('receiveTime', 'samplingTime','saveState');//图像检材信息
            var dzFieldName = new Array('receiveTime', 'samplingTime','saveState');//电子检材信息
            /* var jsFieldName=new Array('receiveTime','samplingTime','samplePerson','samplingAddress','collectionTime','saveState');//精神病检材信息*/

            var fyzArr = new Array('kinship', 'receiveTime', 'samplingTime', 'samplePerson', 'samplingAddress', 'collectionTime','saveState','siteSampling','homeSampling','takeSample');

            var dataArr = new Array();
            if ($.inArray(code, wzArr) >= 0) {
                //物证
                for (var i = 0; i < wzFieldName.length; i++) {
                    dataArr[i] = $("#" + wzFieldName[i]).val();
                    if ($.inArray(wzFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + wzstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }

                }

            }
            if ($.inArray(code, wLwzArr) >= 0) {
                //物证
                for (var i = 0; i < wlwzFieldName.length; i++) {
                    dataArr[i] = $("#" + wlwzFieldName[i]).val();
                    if ($.inArray(wlwzFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + wlwzstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }

                }

            }
            if ($.inArray(code, dwArr) >= 0) {
                //毒物
                for (var i = 0; i < dwFieldName.length; i++) {
                    dataArr[i] = $("#" + dwFieldName[i]).val();
                    if ($.inArray(dwFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + dwstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }
            if ($.inArray(code, blArr) >= 0) {
                //病理   走通用字段
                /* for(var i=0;i<blFieldName.length;i++){
                 var temp = $("#"+blFieldName[i]).val();
                 if (temp == "") {
                 layer.msg('请选择'+wzstr[i]+'！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                 return false;
                 }
                 }*/

            }
            if ($.inArray(code, lcArr) >= 0) {
                //临床
                for (var i = 0; i < lcFieldName.length; i++) {
                    dataArr[i] = $("#" + lcFieldName[i]).val();
                    if ($.inArray(lcFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择保存状况！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }
            if ($.inArray(code, wjArr) >= 0) {
                //文检
                for (var i = 0; i < wjFieldName.length; i++) {
                    dataArr[i] = $("#" + wjFieldName[i]).val();
                    if ($.inArray(wjFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + wtdstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }
            if ($.inArray(code, hjArr) >= 0) {
                //痕迹
                for (var i = 0; i < hjFieldName.length; i++) {
                    dataArr[i] = $("#" + hjFieldName[i]).val();
                    if ($.inArray(hjFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择保存状况！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }
            if ($.inArray(code, sxArr) >= 0) {
                //图像
                for (var i = 0; i < sxFieldName.length; i++) {
                    dataArr[i] = $("#" + sxFieldName[i]).val();
                    if ($.inArray(sxFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + wtdstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }

            if ($.inArray(code, dzArr) >= 0) {
                //电子
                for (var i = 0; i < dzFieldName.length; i++) {
                    dataArr[i] = $("#" + dzFieldName[i]).val();
                    if ($.inArray(dzFieldName[i], fyzArr) < 0) {
                        //没找到 验证
                        if (dataArr[i] == "") {
                            layer.msg('请选择' + wtdstr[i] + '！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            return false;
                        }
                    }
                }

            }
            if ($.inArray(code, jsArr) >= 0) {
                //精神病  走通用字段
                /*  for(var i=0;i<jsFieldName.length;i++){
                 var temp = $("#"+jsFieldName[i]).val();
                 if (temp == "") {
                 layer.msg('请选择'+wzstr[i]+'！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                 return false;
                 }
                 }*/

            }

            /*var kinship = $("#kinship").val();
             if (kinship == "") {
             layer.msg('请选择亲缘关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
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
             /!*   if (samplePerson == "") {
             layer.msg('请输入采样人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }*!/

             var samplingAddress = $("#samplingAddress").val();
             /!*
             if (samplingAddress == "") {
             layer.msg('请输入采样地点！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
             return false;
             }
             *!/
             */
            if (flag == "add") {
                if ($.inArray(code, wzCodeArr) >= 0) {
                    //拿到当前要添加的亲缘关系的名称
                    var index;
                    var count=2;
                    var fCount=1;
                    var findex;
                    if (kinName == '母亲') {
                        //更新孩子、父亲的检材编号
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if ($(this).val() == '子女') {
                                if(i<10){
                                    index="0"+count.toString();
                                }else{
                                    index=count.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;

                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                count++;
                            }else if($(this).val() =='父亲'){
                                $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                    fCount++;
                                });
                                if(fCount<10){
                                    findex="0"+fCount.toString();
                                }else{
                                    findex=fCount.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + findex;
                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                            }

                        });

                    }
                    else if(kinName == '子女'){
                        //将亲缘类型为 父亲的检材编号重新编码
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if ($(this).val() == '父亲') {
                                $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                    fCount++;
                                });
                                if(fCount<10){
                                    findex="0"+fCount.toString();
                                }else{
                                    findex=fCount.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + findex;
                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                            }
                        });


                    }else if(kinName == '父亲'){

                    }
                }


                var text = "";

                //公共字段提取
                text += '<tr>';
                text += '<input type="hidden" name="remark" value="' + remark + '">';
                text += '<input type="hidden" name="sampleTypeName" value="' + $("#sampleType").find("option:selected").text() + '">';
                if("FYLC"===code){

                }else{
                    text += '<input type="hidden" name="personshipType" value="' + $("#personship").find("option:selected").val() + '">';
                    text += '<input type="hidden" name="personship" value="' + $("#personship").find("option:selected").text() + '">';
                }

                if(code!='WLWZ'){
                    text += '<input type="hidden" name="measurementUnitName" value="' + $("#measurementUnit").find("option:selected").text() + '">';
                }


                text += '<td>' + sampleSn + '<input type="hidden" name="sampleSn" value="' + sampleSn + '"> </td>';
                if("FYLC"===code){
                    text += '<td>' + $("#sampleName").find("option:selected").text() + '<input type="hidden" name="sampleName" value="' + sampleName + '"> </td>';
                }else{
                    text += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> </td>';
                }
                text += '<td>' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '"></td>';
                text += '<td>' + amount + '<input type="hidden" name="amount" value="' + amount + '"></td>';
                if(code!='WLWZ'){
                    text += '<td>' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + $("#measurementUnit").find("option:selected").val() + '"></td>';
                }

                //物证
                if ($.inArray(code, wzArr) >= 0) {
                    text += '<input type="hidden" name="kinshipName" value="' + $("#kinship").find("option:selected").text() + '">';
                    text += '<td>' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + dataArr[0] + '"> </td>';

                    //text += '<td>' + dataArr[3] + '<input type="hidden" name="samplePerson" value="' + dataArr[3] + '"> </td>';
                    text += '<td>' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '"> </td>';
                    text += '<td>' + dataArr[5] + '<input type="hidden" name="collectionTime" value="' + dataArr[5] + '"> </td>';
                    text += '<td>' + dataArr[4] + '<input type="hidden" name="samplingAddress" value="' + dataArr[4] + '"> </td>';
                    text += '<td>' + dataArr[2] + '<input type="hidden" name="samplingTime" value="' + dataArr[2] + '"> </td>';
                    text += '<td>' + dataArr[1] + '<input type="hidden" name="receiveTime" value="' + dataArr[1] + '"> </td>';

                }

                //微量物证
                if ($.inArray(code, wLwzArr) >= 0) {

                    //text += '<td>' + dataArr[1] + '<input type="hidden" name="samplePerson" value="' + dataArr[1] + '"> </td>';
                    text += '<td>' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '"> </td>';
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '"> </td>';
                }

                //毒物
                if ($.inArray(code, dwArr) >= 0) {
                    text += '<td>' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '"> </td>';
                    //text += '<td>' + dataArr[0] + '<input type="hidden" name="samplePerson" value="' + dataArr[0] + '"> </td>';
                    text += '<td>' + dataArr[1] + '<input type="hidden" name="collectionTime" value="' + dataArr[1] + '"> </td>';
                    text += '<td>' + dataArr[2] + '<input type="hidden" name="siteSampling" value="' + dataArr[2] + '"> </td>';
                    text += '<td>' + dataArr[3] + '<input type="hidden" name="homeSampling" value="' + dataArr[3] + '"> </td>';
                    text += '<td>' + dataArr[4] + '<input type="hidden" name="takeSample" value="' + dataArr[4] + '"> </td>';

                }
                //病理
                if ($.inArray(code, blArr) >= 0) {
                    /* text += '<input type="hidden" name="kinshipName" value="' + $("#kinship").find("option:selected").text() + '">';
                     text += '<td>' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + dataArr[0] + '"> </td>';

                     text += '<td>' + dataArr[1] + '<input type="hidden" name="receiveTime" value="' + dataArr[1] + '"> </td>';
                     text += '<td>' + dataArr[2] + '<input type="hidden" name="samplingTime" value="' + dataArr[2] + '"> </td>';
                     text += '<td>' + dataArr[3] + '<input type="hidden" name="samplePerson" value="' + dataArr[3] + '"> </td>';
                     text += '<td>' + dataArr[4] + '<input type="hidden" name="samplingAddress" value="' + dataArr[4] + '"> </td>';


                     text += '<td>' + dataArr[5] + '<input type="hidden" name="collectionTime" value="' + dataArr[5] + '"> </td>';*/

                }
                //临床
               /* if ($.inArray(code, lcArr) >= 0) {
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="saveState" value="' + dataArr[0] + '"> </td>';
                }*/
                //文检
                if ($.inArray(code, wjArr) >= 0) {
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '"> </td>';
                    text += '<td>' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '"> </td>';
                }
                //痕迹
                if ($.inArray(code, hjArr) >= 0) {
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="saveState" value="' + dataArr[0] + '"> </td>';
                }
                //图像
                if ($.inArray(code, sxArr) >= 0) {
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '"> </td>';
                    text += '<td>' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '"> </td>';
                }
                //电子
                if ($.inArray(code, dzArr) >= 0) {
                    text += '<td>' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '"> </td>';
                    text += '<td>' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '"> </td>';

                }
                //精神病
                if ($.inArray(code, jsArr) >= 0) {
                    /* text += '<input type="hidden" name="kinshipName" value="' + $("#kinship").find("option:selected").text() + '">';
                     text += '<td>' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + dataArr[0] + '"> </td>';

                     text += '<td>' + dataArr[1] + '<input type="hidden" name="receiveTime" value="' + dataArr[1] + '"> </td>';
                     text += '<td>' + dataArr[2] + '<input type="hidden" name="samplingTime" value="' + dataArr[2] + '"> </td>';
                     text += '<td>' + dataArr[3] + '<input type="hidden" name="samplePerson" value="' + dataArr[3] + '"> </td>';
                     text += '<td>' + dataArr[4] + '<input type="hidden" name="samplingAddress" value="' + dataArr[4] + '"> </td>';


                     text += '<td>' + dataArr[5] + '<input type="hidden" name="collectionTime" value="' + dataArr[5] + '"> </td>';*/

                }
                text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm" type="button" onclick="modifySample(this);">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,2)">删除</button></td>';
                text += '</tr>';
                /* text += '<tr>';
                 text += '<input type="hidden" name="remark" value="' + remark + '">';
                 text += '<input type="hidden" name="sampleTypeName" value="' + $("#sampleType").find("option:selected").text() + '">';

                 text += '<input type="hidden" name="personshipType" value="' + personship + '">';
                 text += '<input type="hidden" name="personship" value="' + $("#personship").find("option:selected").text() + '">';

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
                 text += '</tr>';*/

                $("#nullData").hide();
                $("#samplesTable").append(text);
                layer.closeAll();
            }
            else if (flag == "modify") {


                if ($.inArray(code, wzCodeArr) >= 0) {
                    var  kname=$("#kinship").find("option:selected").text();
                    //拿到当前要添加的亲缘关系的名称
                    var index;
                    var count=2;
                    var fCount=1;
                    var findex;
                    str.find("[name='kinshipName']").val(kname);
                    if (kname == '母亲') {
                        //更新孩子、父亲的检材编号
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if ($(this).val() == '子女') {
                                if(i<10){
                                    index="0"+count.toString();
                                }else{
                                    index=count.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;

                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                count++;
                            }
                        });

                    }
                    else if(kname == '子女'){
                        //将亲缘类型为 父亲的检材编号重新编码

                    }else if(kname == '父亲'){
                        var kinshipNameArr=new Array();
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            kinshipNameArr.push($(this).val());
                        });
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {

                            if($.inArray('母亲', kinshipNameArr) >= 0){
                                //包含母亲
                                if ($(this).val() == '子女'){
                                    if(i<10){
                                        index="0"+count.toString();
                                    }else{
                                        index=count.toString();
                                    }

                                    var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;

                                    var tr = $(this).parent();
                                    tr.find("input[name='sampleSn']").val(sampleSn);
                                    var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                    tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                    count++;
                                }

                            }else{
                                //不包含母亲
                                if ($(this).val() == '子女'){
                                    if(i<10){
                                        index="0"+(count-1).toString();
                                    }else{
                                        index=(count-1).toString();
                                    }

                                    var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                                    var tr = $(this).parent();
                                    tr.find("input[name='sampleSn']").val(sampleSn);
                                    var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                    tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                    count++;
                                }
                            }

                        })

                    }
                }

                //公共字段提取
                str.find("[name='remark']").val(remark);
                if("FYLC"===code){

                }else{
                    str.find("[name='personshipType']").val(personship);
                    str.find("[name='personship']").val($("#personship").find("option:selected").text());
                }

                str.find("[name='sampleTypeName']").val($("#sampleType").find("option:selected").text());
                var sampleSnText = '' + sampleSn + '<input type="hidden" name="sampleSn" value="' + sampleSn + '">';
                str.find("[name='sampleSn']").parent().html(sampleSnText);
                if(code!='WLWZ'){
                    var measurementUnit = $("#measurementUnit").val();
                    str.find("[name='measurementUnitName']").val($("#measurementUnit").find("option:selected").text());
                    var measurementUnitText = '' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '">';
                    str.find("[name='measurementUnit']").parent().html(measurementUnitText);
                }

                if("FYLC"===code){
                    var sampleNameText = '' + $("#sampleName").find("option:selected").text() + '<input type="hidden" name="sampleName" value="' + sampleName + '"> ';
                    str.find("[name='sampleName']").parent().html(sampleNameText);
                }else{
                    var sampleNameText = '' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> ';
                    str.find("[name='sampleName']").parent().html(sampleNameText);
                }


                var sampleTypeText = '' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '">';
                str.find("[name='sampleType']").parent().html(sampleTypeText);

                var amountText = '' + amount + '<input type="hidden" name="amount" value="' + amount + '">';
                str.find("[name='amount']").parent().html(amountText);
                //物证
                if ($.inArray(code, wzArr) >= 0) {
                    str.find("[name='kinshipName']").val($("#kinship").find("option:selected").text());
                    var kinshipNameText = '' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + dataArr[0] + '"> ';
                    str.find("[name='kinship']").parent().html(kinshipNameText);

                    var receiveTimeText = '' + dataArr[1] + '<input type="hidden" name="receiveTime" value="' + dataArr[1] + '">';
                    str.find("[name='receiveTime']").parent().html(receiveTimeText);
                    var samplingTimeText = '' + dataArr[2] + '<input type="hidden" name="samplingTime" value="' + dataArr[2] + '">';
                    str.find("[name='samplingTime']").parent().html(samplingTimeText);
                    //var samplePersonText = '' + dataArr[3] + '<input type="hidden" name="samplePerson" value="' + dataArr[3] + '">';
                    //str.find("[name='samplePerson']").parent().html(samplePersonText);
                    var samplePerson = $("#samplePerson").val();
                    var samplePersonText = '' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '">';
                    str.find("[name='samplePerson']").parent().html(samplePersonText)
                    var samplingAddressText = '' + dataArr[4] + '<input type="hidden" name="samplingAddress" value="' + dataArr[4] + '">';
                    str.find("[name='samplingAddress']").parent().html(samplingAddressText);

                    var collectionTimeText = '' + dataArr[5] + '<input type="hidden" name="collectionTime" value="' + dataArr[5] + '">';
                    str.find("[name='collectionTime']").parent().html(collectionTimeText);
                }

                //微量物证
                if ($.inArray(code, wLwzArr) >= 0) {
                    var receiveTimeText = '' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '">';
                    str.find("[name='receiveTime']").parent().html(receiveTimeText);
                    //var samplePersonText = '' + dataArr[1] + '<input type="hidden" name="samplePerson" value="' + dataArr[1] + '">';
                    //str.find("[name='samplePerson']").parent().html(samplePersonText);
                    var samplePerson = $("#samplePerson").val();
                    var samplePersonText = '' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '">';
                    str.find("[name='samplePerson']").parent().html(samplePersonText)
                }


                //毒物
                if ($.inArray(code, dwArr) >= 0) {
                    //var samplePersonText = '' + dataArr[0] + '<input type="hidden" name="samplePerson" value="' + dataArr[0] + '">';
                    //str.find("[name='samplePerson']").parent().html(samplePersonText);
                    var samplePerson = $("#samplePerson").val();
                    var samplePersonText = '' + $("#samplePerson").find("option:selected").text() + '<input type="hidden" name="samplePerson" value="' + samplePerson + '">';
                    str.find("[name='samplePerson']").parent().html(samplePersonText)

                    var collectionTimeText = '' + dataArr[1] + '<input type="hidden" name="collectionTime" value="' + dataArr[1] + '">';
                    str.find("[name='collectionTime']").parent().html(collectionTimeText);

                    var siteSamplingText = '' + dataArr[2] + '<input type="hidden" name="siteSampling" value="' + dataArr[2] + '">';
                    str.find("[name='siteSampling']").parent().html(siteSamplingText);

                    var homeSamplingText = '' + dataArr[3] + '<input type="hidden" name="homeSampling" value="' + dataArr[3] + '">';
                    str.find("[name='homeSampling']").parent().html(homeSamplingText);

                    var takeSampleText = '' + dataArr[4] + '<input type="hidden" name="takeSample" value="' + dataArr[4] + '">';
                    str.find("[name='takeSample']").parent().html(takeSampleText);
                }
                //病理
                if ($.inArray(code, blArr) >= 0) {

                }
                //临床
                if ($.inArray(code, lcArr) >= 0) {
                    var saveStateText = '' + dataArr[0] + '<input type="hidden" name="saveState" value="' + dataArr[0] + '">';
                    str.find("[name='saveState']").parent().html(saveStateText);
                }
                //文检
                if ($.inArray(code, wjArr) >= 0) {
                    var receiveTimeText = '' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '">';
                    str.find("[name='receiveTime']").parent().html(receiveTimeText);
                    var samplingTimeText = '' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '">';
                    str.find("[name='samplingTime']").parent().html(samplingTimeText);
                }
                //痕迹
                if ($.inArray(code, hjArr) >= 0) {
                    var saveStateText = '' + dataArr[0] + '<input type="hidden" name="saveState" value="' + dataArr[0] + '">';
                    str.find("[name='saveState']").parent().html(saveStateText);
                }
                //图像
                if ($.inArray(code, sxArr) >= 0) {
                    var receiveTimeText = '' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '">';
                    str.find("[name='receiveTime']").parent().html(receiveTimeText);
                    var samplingTimeText = '' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '">';
                    str.find("[name='samplingTime']").parent().html(samplingTimeText);
                }
                //电子
                if ($.inArray(code, dzArr) >= 0) {
                    var receiveTimeText = '' + dataArr[0] + '<input type="hidden" name="receiveTime" value="' + dataArr[0] + '">';
                    str.find("[name='receiveTime']").parent().html(receiveTimeText);
                    var samplingTimeText = '' + dataArr[1] + '<input type="hidden" name="samplingTime" value="' + dataArr[1] + '">';
                    str.find("[name='samplingTime']").parent().html(samplingTimeText);
                }
                //精神病
                if ($.inArray(code, jsArr) >= 0) {

                }
                layer.closeAll();
            }

            var newOption = '<option value=""></option>'
            $("#samplesTable").find("input[name='sampleName']").each(function () {
               var sampleSn=$(this).parents('tr').find("input[name='sampleSn']").val();
                if("FYLC"===code){
                    newOption += '<option value="' + $(this).val() + '">' + sampleSn + '</option>'
                }else{
                    var sn=sampleSn.substring(sampleSn.length,sampleSn.length-2);
                    newOption += '<option value="' + $(this).val() + '">' + sn+$(this).val() + '</option>'
                }

            })
            $(".imgselect").children().remove();
            $(".imgselect").append(newOption)
            form.render();
        });

    });

});


//加载不同鉴定类别下面 下挂的鉴定项目
function initIdenProject(id, code,shortName, obj) {

    if (obj == 2) {
        //切换鉴定项目时清空检材列表
        $("#samplesTable").empty();
    }

    // $("#authenticWord").html(shortName);
    if(typeof(shortName) !='undefined'){
        if(shortName.indexOf("咨")>=0){
            $("#authenticWord").html(shortName+"  咨字");
        }else{
            $("#authenticWord").html(shortName+"  鉴字");
        }
    }

    //清空鉴定内容
    checkboxArr = [];
    $("textarea[name='caseContent']").val("");

    $("textarea[name='caseContent']").val($("#jdnr").val());
    //为检材提交按钮 赋值
    $("#addSampleInfo").val(code);
    isupdate(code);

    $("#majorCode").val(code);
    var strs = new Array(); //定义一数组
    if ($("#jdxmm").val() != "") {
        strs = $("#jdxmm").val().split(",");
    }

    var bzstrs = new Array(); //定义一数组
    if ($("#bzjdxmm").val() != "") {
        bzstrs = $("#bzjdxmm").val().split(",");
    }
    var ffstrs = new Array(); //定义一数组
    if ($("#ffjdxmm").val() != "") {
        ffstrs = $("#ffjdxmm").val().split(",");
    }


    layui.use('form', function () {
        var form = layui.form;
        $.ajax({
            url: "getIdentificationProject.html?id=" + id + "&codeType=" + code,
            type: "get",
            dataType: "json",
            success: function (data) {
                //进行切换不同专业的检材信息
                if("FYLC"===code){
                    $(".flexBox li:eq(4)").html("鉴定材料");
                    $("#jctext").html("鉴定材料");
                    var parent=$("#sampleName").parent();
                    parent.empty();
                    var text="";
                    text+='<select name="sampleName" id="sampleName" lay-filter="sampleNameFilter">';
                    text+='<option value=""></option>';
                    $.each(data.lcSampleNameList, function (index, item) {
                        text += '<option value="' + item.id + '">' + item.dictName + '</option>';
                    });
                    text+='</select>';
                    parent.append(text);

                    $("#sampleType").empty();
                    var sampleText = "";
                    sampleText += '<option></option>';
                    $("#sampleType").append(sampleText);
                    form.render();

                }else{
                    $(".flexBox li:eq(4)").html("检材信息");
                    $("#jctext").html("检材信息");
                    var parent=$("#sampleName").parent();
                    parent.empty();
                    var text="";
                    text+='<input type="text" placeholder="请输入名称" autocomplete="off" class="layui-input" name="sampleName" id="sampleName">';
                    parent.append(text);

                    //检材的鉴定类别赋值
                    $("#sampleType").empty();
                    var sampleText = "";
                    sampleText += '<option></option>';
                    $.each(data.materialTypeList, function (index, item) {
                        sampleText += '<option value="' + item.id + '">' + item.materialName + '</option>';
                    });
                    $("#sampleType").append(sampleText);
                    form.render();
                }

                //案件类型
                $("#caseType").empty();
                var caseTypeName = $("#caseTypeName").val();
                var caseTypeText = "";
                caseTypeText += '<option></option>';
                $.each(data.caseTypeVOList, function (index, item) {
                    if (caseTypeName == item.entity.id) {
                        caseTypeText += '<option value="' + item.entity.id + '" selected>' + item.entity.majorDictionaryName + '</option>';
                    }else {
                        caseTypeText += '<option value="' + item.entity.id + '">' + item.entity.majorDictionaryName + '</option>';
                    }
                });
                $("#caseType").append(caseTypeText);

                //检材单位
                $("#measurementUnit").empty();
                var measurementUnitText = "";
                measurementUnitText += '<option></option>';
                $.each(data.inspectionUnitVOList, function (index, item) {
                    measurementUnitText += '<option value="' + item.entity.id + '">' + item.entity.majorDictionaryName + '</option>';
                });
                $("#measurementUnit").append(measurementUnitText);
                form.render();

                //给物鉴鉴字赋值
                if ($("#jdxmm").val() == "") {
                    $("#caseAmount").val(data.caseNumber);
                    // $("input[name='casesAcceptNumber']").val(data.caseSn);
                    $("input[name='casesNumber']").val(data.caseSn);
                }

                //标准收费 和鉴定时限
                $("#stCharge").val(data.standardCharge);
                /*$("#chargeAmount").val(data.standardCharge);*/
                //$("input[name='timeLimit']").val(data.timeLimit);
                $("#timeLimit").val(data.timeLimit);

                $("#jdxm").empty();//鉴定项目
                $("#jdbz").children().eq(0).empty();//鉴定标准
                $("#jdbzTable").find(".layui-col-md9").empty()
                $("#jdff").empty();//鉴定方法
                var text = "";
                var bzText = "";
                var ffText = "";
                var bzTableText = '';
                $.each(data.identificationProjectList, function (index, item) {
                    var fl = $.inArray(item.id, strs);
                    var bzfl = $.inArray(item.identificationStandardId, bzstrs);
                    var fffl = $.inArray(item.identificationMethodId, ffstrs);
                    if (item.identificationProject != null && item.identificationProject != "") {
                        if (fl >= 0) {
                            text += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMajor" name="appraisalMajorId" checked="checked" value="' + item.id + '" title="' + item.identificationProject + '">';
                        } else {
                            text += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMajor" name="appraisalMajorId" value="' + item.id + '" title="' + item.identificationProject + '">';
                        }
                    }

                    if (item.identificationStandard != null && item.identificationStandard != "") {

                        bzText += '<div class="layui-col-md-12"><div class="layui-form-item"><div class="layui-input-block layui-input-top">'
                        if (bzfl >= 0) {
                            bzTableText += '<div class="layui-col-md12" title="' + item.identificationStandard + '">' + item.identificationStandard + '<i class="fa fa-times-circle removebz" aria-hidden="true"></i></div>'
                            bzText += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyNormal" name="appraisalNormalId" checked="checked" value="' + item.identificationStandardId + '" title="' + item.identificationStandard + '">';
                        } else {
                            bzText += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyNormal" name="appraisalNormalId" value="' + item.identificationStandardId + '" title="' + item.identificationStandard + '">';
                        }
                        bzText += '</div></div></div>'
                    }
                    if (item.identificationMethod != null && item.identificationMethod != "") {
                        if (fffl >= 0) {
                            ffText += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMethod" name="identifiMethod" checked="checked"  value="' + item.identificationMethodId + '" title="' + item.identificationMethod + '">';
                        } else {
                            ffText += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMethod" name="identifiMethod" value="' + item.identificationMethodId + '" title="' + item.identificationMethod + '">';
                        }
                    }


                });


                $("#jdxm").append(text);

                $("#jdbzTable").find(".layui-col-md9").append(bzTableText);//1月2号 修改案件受理-鉴定标准回显的问题

                $("#jdbz").children().eq(0).append(bzText);
                $("#jdff").append(ffText);
                if (bzTableText.length > 0) {
                    $("#removeAlljdbz").removeClass("layui-hide")
                    $("#addjdbz").text("重新选择")
                }
                form.render();
                $("#jdxm").find("input:checked").each(function(){
                    checkboxArr.push($(this).attr("title"))
                })
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
            $(obj).parent().parent().remove();
        } else if (f == 2) {
            var code=$("#majorCode").val();//拿到鉴定类型code
            var codes = code.substring(0, 1) + code.substring(3, 2);
            var CurrentYear = $("#CurrentYear").val();//拿到时间
            var CurrentYears = CurrentYear.substring(2, 4);
            //删除检材
            var wzCodeArr = new Array('FYWZ', 'WZZX');//法医物证专业code---微量物证不在之中
            //alert($(obj).parents('tr').find("input[name='kinshipName']").val());
            var count=1;
            var index;
            var ffcount=0;

            if($.inArray(code, wzCodeArr) >= 0) {
                var oldKinshipName=$(obj).parents('tr').find("input[name='kinshipName']").val();
                $(obj).parents('tr').find("input[name='kinshipName']").val("爷爷");
                if(oldKinshipName=='母亲'){
                    $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                        if ($(this).val() == '子女'){
                            if(i<10){
                                index="0"+count.toString();
                            }else{
                                index=count.toString();
                            }

                            var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                            var tr = $(this).parent();
                            tr.find("input[name='sampleSn']").val(sampleSn);
                            var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                            tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                            count++;
                        }else if($(this).val() == '父亲'){
                            $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                ffcount++;
                            });
                            ffcount=ffcount-1;
                            if(ffcount<10){
                                index="0"+ffcount.toString();
                            }else{
                                index=ffcount.toString();
                            }

                            var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                            var tr = $(this).parent();
                            tr.find("input[name='sampleSn']").val(sampleSn);
                            var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                            tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                        }

                    });
                }else if(oldKinshipName=='子女'){
                    //删除的是子女
                    var kinshipNameArr=new Array();
                    $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                        kinshipNameArr.push($(this).val());
                    });
                    if($.inArray('母亲', kinshipNameArr) >= 0){
                        //包含母亲
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if ($(this).val() == '子女'){
                                if(i<10){
                                    index="0"+(count+1).toString();
                                }else{
                                    index=(count+1).toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;

                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                count++;
                            }else if($(this).val() == '父亲'){
                                $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                    ffcount++;
                                });
                                ffcount=ffcount-1;
                                if(ffcount<10){
                                    index="0"+ffcount.toString();
                                }else{
                                    index=ffcount.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                            }
                        });


                    }else{
                        //不包含母亲
                        $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                            if ($(this).val() == '子女'){
                                if(i<10){
                                    index="0"+count.toString();
                                }else{
                                    index=count.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                                count++;
                            }else if($(this).val() == '父亲'){
                                $("#samplesTable").find("input[name='kinshipName']").each(function (i) {
                                    ffcount++;
                                });
                                ffcount=ffcount-1;
                                if(ffcount<10){
                                    index="0"+ffcount.toString();
                                }else{
                                    index=ffcount.toString();
                                }

                                var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                                var tr = $(this).parent();
                                tr.find("input[name='sampleSn']").val(sampleSn);
                                var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                                tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                            }
                        });

                    }


                }else if(oldKinshipName=='父亲'){

                }
                $(obj).parent().parent().remove();
            }else{
                //其他专业
                $(obj).parent().parent().remove();
                $("#samplesTable").find("input[name='sampleSn']").each(function (i) {
                        if(i<10){
                            index="0"+count.toString();
                        }else{
                            index=count.toString();
                        }

                        var sampleSn = codes + CurrentYears + $("#caseAmount").val() + index;
                    var tr = $(this).parent();
                    tr.find("input[name='sampleSn']").val(sampleSn);
                    var tdInput = tr.find("input[name='sampleSn']").parent().find("input").clone();
                    tr.find("input[name='sampleSn']").parent().html(sampleSn).append(tdInput);
                    count++;
                 });
            }

            var newOption = '<option value=""></option>'
            $("#samplesTable").find("input[name='sampleName']").each(function () {
                var sampleSn=$(this).parents('tr').find("input[name='sampleSn']").val();
                if("FYLC"===code){
                    newOption += '<option value="' + $(this).val() + '">' + sampleSn + '</option>'
                }else{
                    var sn=sampleSn.substring(sampleSn.length,sampleSn.length-2);
                    newOption += '<option value="' + $(this).val() + '">' + sn+$(this).val() + '</option>'
                }
            })
            //删除对应的人员关系option
            $(".imgselect").children().remove();
            $(".imgselect").append(newOption);

            form.render();
        }

    });
}

//修改检材信息

function modifySample(obj) {
    $("#sampleForm").resetForm();//清空表单
    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var $ = layui.jquery,
            form = layui.form;


        flag = "modify";
        str = $(obj).parent().parent();
        var personship = str.find("[name='personshipType']").val();//对应的被鉴定人信息
        var sampleSn = str.find("[name='sampleSn']").val();
        var remark = str.find("[name='remark']").val();//检材备注
        var sampleName = str.find("[name='sampleName']").val();//检材名称
        var id = sampleName;
        var code=$("#majorCode").val();//拿到鉴定类型code
        if("FYLC"===code){
            $.ajax({
                url: baseurl+"/selectLcMaterial.html?id=" + id+"&code="+code,
                type: "post",
                dataType: "json",
                async:false,
                success: function (data) {
                    //检材的鉴定类别赋值
                    $("#sampleType").empty();
                    var sampleText = "";
                    sampleText += '<option></option>';
                    $.each(data, function (index, item) {
                        sampleText += '<option value="' + item.id + '">' + item.materialName + '</option>';
                    });
                    $("#sampleType").append(sampleText);
                },
                error: function (e) {
                    alert("查询错误");
                }
            });
        }


        var sampleType = str.find("[name='sampleType']").val();//检材类型
        var kinship = str.find("[name='kinship']").val();//亲缘关系
        havekinshipName=str.find("[name='kinshipName']").val();//亲缘关系名称
        haveKinshipId=str.find("[name='kinship']").val();
        var amount = str.find("[name='amount']").val();//检材数量
        var measurementUnit = str.find("[name='measurementUnit']").val();//检材数量单位
        var samplingTime = str.find("[name='samplingTime']").val();//提取时间

        var collectionTime = str.find("[name='collectionTime']").val();//采样时间


        var samplePerson = str.find("[name='samplePerson']").val();//采样人
        var samplingAddress = str.find("[name='samplingAddress']").val();//采样地点


        var receiveTime = str.find("[name='receiveTime']").val();//接收时间
        var saveState = str.find("[name='saveState']").val();//保存状态
        var siteSampling = str.find("[name='siteSampling']").val();//现场采样
        var homeSampling = str.find("[name='homeSampling']").val();//上门采样
        var takeSample = str.find("[name='takeSample']").val();//上门拿样
        /* var sampleTypeName = str.find("[name='sampleTypeName']").val();//检材类型名称
         var measurementUnitName = str.find("[name='measurementUnitName']").val();//单位名称*/


        $("#personship").val(personship);
        $("#sampleSn").val(sampleSn);
        $("#remark").val(remark);
        $("#sampleName").val(sampleName);
        $("#sampleType").val(sampleType);
        $("#amount").val(amount);
        $("#measurementUnit").val(measurementUnit);
        $("#kinship").val(kinship);
        $("#receiveTime").val(receiveTime);
        $("#collectionTime").val(collectionTime);
        $("#samplingTime").val(samplingTime);
        $("#samplePerson").val(samplePerson);
        $("#samplingAddress").val(samplingAddress);
        $("#saveState").val(saveState);
        $("#siteSampling").val(siteSampling);
        $("#homeSampling").val(homeSampling);
        $("#takeSample").val(takeSample);

        form.render();
        layer.open({
            type: 1,
            title: "修改检材信息",
            area: '800px',
            id: 'LAY_layuipro',
            content: $('#sampleForm'),
            end: function () {
                $("#sampleForm")[0].reset()
                //$("#sampleForm").render()
            }
        });

    });
}

//通过鉴定类别判断样本数、采样人可否编辑
function isupdate(obj) {
    //切换不同的鉴定类别 给鉴字赋值
    var wzArr = new Array('FYWZ', 'WZZX');//法医物证
    var wlWzArr=new Array('WLWZ');//微量物证
    var dwArr = new Array('FYDW', 'DWQT'); //法医毒物
    var blArr = new Array('FYBL', 'BLQT'); //法医病理
    var lcArr = new Array('FYLC'); //法医临床
    var wjArr = new Array('WJJY', 'FYSW'); //文件检验
    var hjArr = new Array('HJJY', 'CLPG', 'CLJY'); //痕迹检验
    var sxArr = new Array('TXJY'); //图像检验
    var dzArr = new Array('DZSJ'); //电子数据
    var jsArr = new Array('FYJSB');//法医精神病


    var wzSampleArr = new Array('保存状态','现场采样','上门采样','上门拿样');//物证检材信息
    var wlWzSampleArr=new Array('亲缘关系', '提取时间', '采样地点', '采样时间','检材单位','保存状态','现场采样','上门采样','上门拿样');//微量物证检材信息
    var dwSampleArr = new Array('亲缘关系', '采样地点', '提取时间', '接收时间', '保存状态');//毒物检材信息
    var blSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '提取时间', '接收时间', '保存状态','现场采样','上门采样','上门拿样');//病理检材信息
    var lcSampleArr = new Array('被鉴定人姓名','亲缘关系', '采样人', '采样地点', '采样时间', '提取时间', '接收时间','保存状态','现场采样','上门采样','上门拿样');//临床检材信息
    var wjSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '保存状态','现场采样','上门采样','上门拿样');//文检检材信息
    var hjSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '提取时间', '接收时间','现场采样','上门采样','上门拿样');//痕迹检材信息
    var txSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '保存状态','现场采样','上门采样','上门拿样');//图像检材信息
    var dzSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '保存状态','现场采样','上门采样','上门拿样');//电子检材信息
    var jsbSampleArr = new Array('亲缘关系', '采样人', '采样地点', '采样时间', '提取时间', '接收时间', '保存状态','现场采样','上门采样','上门拿样');//精神病检材信息


    $(".layui-table").find("th").removeClass("layui-hide");
    $("#sampleForm").find(".layui-form-label").parents(".layui-col-md6").removeClass("layui-hide");

    if ($.inArray(obj, wzArr) >= 0) {
        for (var i = 0; i < wzSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + wzSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + wzSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, wlWzArr) >= 0) {
        for (var i = 0; i < wlWzSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + wlWzSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + wlWzSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
        $(".layui-table").find("th:contains('检材数量')").html("检材数量");
        $(".layui-table").find("th:contains('接收时间')").html("接收时间");
        $("#sampleForm").find(".layui-form-label:contains('检材数量')").html("检材数量");
        $("#sampleForm").find(".layui-form-label:contains('接收时间')").html("接收时间");
    }
    if ($.inArray(obj, dwArr) >= 0) {
        for (var i = 0; i < dwSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + dwSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + dwSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, blArr) >= 0) {
        for (var i = 0; i < blSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + blSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + blSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, lcArr) >= 0) {
        for (var i = 0; i < lcSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + lcSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + lcSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, wjArr) >= 0) {
        for (var i = 0; i < wjSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + wjSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + wjSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, hjArr) >= 0) {
        for (var i = 0; i < hjSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + hjSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + hjSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, sxArr) >= 0) {
        for (var i = 0; i < txSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + txSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + txSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, dzArr) >= 0) {
        for (var i = 0; i < dzSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + dzSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + dzSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }
    if ($.inArray(obj, jsArr) >= 0) {
        for (var i = 0; i < jsbSampleArr.length; i++) {
            $(".layui-table").find("th:contains('" + jsbSampleArr[i] + "')").addClass("layui-hide");
            $("#sampleForm").find(".layui-form-label:contains('" + jsbSampleArr[i] + "')").parents(".layui-col-md6").addClass("layui-hide");
        }
    }

    if (obj == 'FYWZ' || obj == 'WLWZ' || obj == 'WZZX') {
        $("input[name='sampleNumber']").parents("#sampleT").removeClass("dsp");
        $("input[name='sampleNumber']").val($("#ybs").val());
        $("#sampleNumber").html("样本数");
        $("input[name='maintainPeople']").parents("#maintainT").removeClass("dsp");
        $("input[name='maintainPeople']").val($("#cyr").val());
        $("#maintainPeople").html("采样人");
    } else if (obj == 'FYDW' || obj == 'DWQT') {

        $("input[name='sampleNumber']").parents("#sampleT").removeClass("dsp");
        $("input[name='sampleNumber']").val($("#ybs").val());
        $("#sampleNumber").html("检测数");
        $("input[name='maintainPeople']").parents("#maintainT").addClass("dsp");
        $("input[name='maintainPeople']").val($("#cyr").val());
        $("#maintainPeople").html("");
    } else {
        $("#sampleNumber").html("");
        $("input[name='sampleNumber']").parents("#sampleT").addClass("dsp");
        $("input[name='sampleNumber']").val("");
        $("#maintainPeople").html("");
        $("input[name='maintainPeople']").parents("#maintainT").addClass("dsp");
        $("input[name='maintainPeople']").val("");
    }
}

//判断回避人是否与其他鉴定人重复
function judgAvoidanceRepeat() {
    //取得受理人、第一鉴定人、第二鉴定人、授权人的值
    var assigneeId = $("#assignee").val();
    var appraiserOneId = $("#appraiser1").val();
    var appraiserSecondId = $("#appraiser2").val();
    var appraiserThridId = $("#appraiser3").val();
    var authorizerId = $("#authorizerId").val();
    var avoidance = $("[name='avoidance']").find("option:selected").val();
    var myBoolean = new Boolean(false);
    if (avoidance == "000090") {
        myBoolean = false;
    } else {
        switch (avoidance) {
            case assigneeId:
                myBoolean = true;
                break;
            case appraiserOneId:
                myBoolean = true;
                break;
            case appraiserSecondId:
                myBoolean = true;
                break;
            case appraiserThridId:
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

//判断第二鉴定人是否与其他鉴定人重复
function judgIdenRepeat() {
    //取得受理人、第一鉴定人、第二鉴定人、授权人的值
    var assigneeId = $("#assignee").val();
    var appraiserOneId = $("#appraiser1").val();
    var appraiserSecondId = $("#appraiser2").val();
    var appraiserThridId = $("#appraiser3").val();
    var authorizerId = $("#authorizerId").val();
    var myBoolean = new Boolean(false);
    if (appraiserSecondId == "000090") {
        myBoolean = false;
    } else {
        switch (appraiserSecondId) {
            case assigneeId:
                myBoolean = true;
                break;
            case appraiserOneId:
                myBoolean = true;
                break;
            case appraiserThridId:
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
//jquery 生成随机字符串
function randomString(len) {
    var strArr = new Array()
    for (j = 0; j < 10; j++) {
        len = len || 32;
        var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
        /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
        var maxPos = $chars.length;
        var pwd = '';
        for (i = 0; i < len; i++) {
            pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
        }
        strArr.push(pwd);
    }
    return strArr;
}