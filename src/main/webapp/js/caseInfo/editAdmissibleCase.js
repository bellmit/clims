var flag="add";
var tr;
var str;
var mrName;
var samName;
var options ='';
$(function () {

    //初始化鉴定项目
    var lbid = $("#idenCheck").val();
    initIdenProject(lbid);


    layui.use(['layer','form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;


        form.on('select(cardType)',function(data){
            var dictName = data.elem[data.elem.selectedIndex].text;
            if(dictName == "无证"){
                $("#identificationNumber").hide();
                form.render();
            }else{
                $("#identificationNumber").show();
                form.render();
            }

        });

        form.on('select(assigneeValue)',function(data){

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
            var type=data.value;
            //首先清空地址内容
            $("input[name='instrumentsContent']").val("");
            if(type=="1"){
                $("#instrumentsContentDiv").hide();
            }
            else if(type=="2"){
                $("#addressContent").html("邮寄地址<i class=\"icolor\">*</i>");
                $("#instrumentsContentDiv").show();
            }else if(type=="3"){
                $("#addressContent").html("发送内容<i class=\"icolor\">*</i>");
                $("#instrumentsContentDiv").show();
            }
        });


        form.on('select(gender)',function(data){
            var str=data.elem[data.elem.selectedIndex].text;
            //拿到称谓的值
            var identity=$("#identity").find("option:selected").text();
            if(identity.indexOf('父亲') >= 0 ){
                if(str.indexOf("女")>=0){
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#gender').val('0');
                    form.render();
                    return false;
                }
            }
            if(identity.indexOf("母亲") >= 0 ){
                if(str.indexOf("男")>=0){
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#gender').val('0');
                    form.render();
                    return false;
                }
            }

        });

        form.on('select(identity)',function(data){
            var str=data.elem[data.elem.selectedIndex].text;
            //拿到称谓的值
            var gender=$("#gender").find("option:selected").text();
            if(gender.indexOf("男") >= 0 ){
                if(str.indexOf("母亲")>=0){
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#identity').val('0');
                    form.render();
                    return false;
                }
            }
            if(gender.indexOf("女") >= 0 ){
                if(str.indexOf("父亲")>=0){
                    layer.msg('性别和称谓不一致！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    $('#identity').val('0');
                    form.render();
                    return false;
                }
            }

        });


        form.on('select(caseType)', function(data){
            var text=$("#caseType").find("option:selected").text();
            if(text=="其他"){
                $("#ortherCaseType").show();
            }else{
                $("#otCaseType").val("");
                $("#ortherCaseType").hide();
            }
        });





        //照片上传
        var map = {};
        var mapsample={};
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
                    mapsample[fileName]=$("#sample"+fileName.substring(0,fileName.indexOf("."))).val();
                });
                this.data={'dataMap': JSON.stringify(map),'caseId':$("#caseId").val(),'sampleMap':JSON.stringify(mapsample)};
            }
            , choose: function (obj) {
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {
                    var text = $(['<span class="imgBox layui-form">'
                        ,'<i class="layui-icon demo-delete">&#x1007;</i>'
                        ,'<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">'
                        ,'<select id="sample'+file.name.substring(0,file.name.indexOf("."))+'"><option value="00"></option>'+options+'</select>'
                        ,'<input type="text" id="fremark'+file.name.substring(0,file.name.indexOf("."))+'" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">'
                        ,'</span>'].join(''));

                    //删除
                    text.find('.demo-delete').on('click', function(){
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
                if(res.code == 0){ //上传成功
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                layer.closeAll('loading'); //关闭loading
            }
            ,allDone: function(obj){ //当文件全部被提交后，才触发
                //alert(obj.successful+"总成功数");
                $('#downFiles').trigger("click");
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


        //添加被鉴定人
        $("#addMemberInfo").click(function () {
            $("#addBtn").show();
            $("#editBtn").hide();
            form.render();

            $("#memberForm").resetForm();//清空表单
            flag="add";
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

        // $("#saveMemberInfo").click(function () {
        //     var regexp=/^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
        //     //取得被鉴定人的信息
        //     var name = $("#name").val();
        //     // if (name == "") {
        //     //     layer.msg('请输入被鉴定人姓名！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //     var gender = $("#gender").val();
        //     // if (gender == "") {
        //     //     layer.msg('请选择被鉴定人性别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //     var nation = $("#nation").val();
        //     // if (nation == "") {
        //     //     layer.msg('请选择被鉴定人民族！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //
        //     var identity = $("#identity").val();
        //     // if (identity == "") {
        //     //     layer.msg('请选择被鉴定人称谓！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //
        //     var birthdate = $("#birthdate").val();
        //     // if (birthdate == "") {
        //     //     layer.msg('请选择被鉴定人生日！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //
        //     var country = $("#country").val();
        //     // if (country == "") {
        //     //     layer.msg('请选择被鉴定人国籍！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //
        //     var phone = $("#phone").val();
        //     // if (phone == "") {
        //     //     layer.msg('请输入被鉴定人联系方式！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //     return false;
        //     // }
        //
        //     if (phone != "") {
        //         if (!(regexp.test(phone))) {
        //             layer.msg('被鉴定人联系方式填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //             return false;
        //         }
        //     }
        //
        //     var cardType = $("#cardType").val();
        //     if (cardType == "") {
        //         layer.msg('请选择被鉴定人证件类型！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //         return false;
        //     }
        //
        //     var idCard = $("#idCard").val();
        //     var cardTypeText = $("select[name='cardType'] option:selected").text();
        //     // if (cardTypeText == "身份证") {
        //     //     var regIdNo =/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|[xX])$/;
        //     //     if(!regIdNo.test(idCard)){
        //     //         layer.msg('被鉴定人证件号码填写有误，请重填！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     //         return false;
        //     //     }
        //     // }
        //
        //     var sampleAddress = $("#sampleAddress").val();
        //     if (sampleAddress == "") {
        //         layer.msg('请输入采样地点！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //         return false;
        //     }
        //     var sampleDate = $("#sampleDate").val();
        //     if (sampleDate == "") {
        //         layer.msg('请选择采样日期！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //         return false;
        //     }
        //
        //
        //     if(flag=="add"){
        //         var text = "";
        //         text += '<tr>';
        //         text += '<input type="hidden" name="birthdate" value="' + birthdate + '">';
        //         text += '<input type="hidden" name="country" value="' + country + '">';
        //
        //         text += '<input type="hidden" name="sampleAddress" value="' + sampleAddress + '">';
        //         text += '<input type="hidden" name="sampleDate" value="' + sampleDate + '">';
        //
        //         text += '<input type="hidden" name="phone" value="' + phone + '">';
        //         text += '<input type="hidden" name="countryName" value="' + $("#country").find("option:selected").text() + '">';
        //         text += '<input type="hidden" name="identityName" value="' + $("#identity").find("option:selected").text() + '">';
        //         text += '<input type="hidden" name="nationName" value="' + $("#nation").find("option:selected").text() + '">';
        //         text += '<input type="hidden" name="genderName" value="' + $("#gender").find("option:selected").text() + '">';
        //         text += '<td>' + name + '<input type="hidden" name="name" value="' + name + '"> </td>';
        //         text += '<td>' + $("#gender").find("option:selected").text() + '<input type="hidden" name="gender" value="' + gender + '"></td>';
        //         text += '<td>' + $("#cardType").find("option:selected").text() + '<input type="hidden" name="cardType" value="' + cardType + '"></td>';
        //         text += '<td>' + idCard + '<input type="hidden" name="idCard" value="' + idCard + '"></td>';
        //         text += '<td>' + $("#identity").find("option:selected").text() + '<input type="hidden" name="identity" value="' + identity + '"></td>';
        //         text += '<td>' + $("#nation").find("option:selected").text() + '<input type="hidden" name="nation" value="' + nation + '"></td>';
        //         // text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="modifyPersion(this);">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,1)">删除</button></td>';
        //         text += '</tr>';
        //
        //
        //         //取得姓名添加到检材信息的人员选型中
        //         var tt='<option value="'+name+'">'+name+'</option>';
        //         $("#personship").append(tt);
        //         form.render();//必须有这句不然不渲染
        //         $("#personTable").append(text);
        //         layer.closeAll();
        //     }else if(flag=="modify"){
        //         tr.find("[name='birthdate']").val(birthdate);
        //         tr.find("[name='country']").val(country);
        //         tr.find("[name='phone']").val(phone);
        //         tr.find("[name='sampleAddress']").val(sampleAddress);
        //         tr.find("[name='sampleDate']").val(sampleDate);
        //
        //         tr.find("[name='countryName']").val($("#country").find("option:selected").text());
        //         tr.find("[name='identityName']").val($("#identity").find("option:selected").text());
        //         tr.find("[name='nationName']").val($("#nation").find("option:selected").text());
        //         tr.find("[name='genderName']").val($("#gender").find("option:selected").text());
        //
        //         var nameText='' + name + '<input type="hidden" name="name" value="' + name + '">';
        //         tr.find("[name='name']").parent().html(nameText);
        //
        //         //修改检材关联人员关系值
        //         $("#personship option[value='"+mrName+"']").remove();
        //
        //         var cc='<option value="'+name+'">'+name+'</option>';
        //         $("#personship").append(cc);
        //         form.render();//必须有这句不然不渲染
        //
        //
        //         var genderText='' + $("#gender").find("option:selected").text() + '<input type="hidden" name="gender" value="' + gender + '">';
        //         tr.find("[name='gender']").parent().html(genderText);
        //
        //         var cardTypeText='' + $("#cardType").find("option:selected").text() + '<input type="hidden" name="cardType" value="' + cardType + '">';
        //         tr.find("[name='cardType']").parent().html(cardTypeText);
        //
        //         var idCardText='' + idCard + '<input type="hidden" name="idCard" value="' + idCard + '">';
        //         tr.find("[name='idCard']").parent().html(idCardText);
        //
        //         var identityText='' + $("#identity").find("option:selected").text() + '<input type="hidden" name="identity" value="' + identity + '">';
        //         tr.find("[name='identity']").parent().html(identityText);
        //
        //         var nationText='' + $("#nation").find("option:selected").text() + '<input type="hidden" name="nation" value="' + nation + '">';
        //         tr.find("[name='nation']").parent().html(nationText);
        //
        //         layer.closeAll();
        //     }
        //
        //
        // });


        //添加检材
        $(".addsamples").click(function () {
            $("#addSampleInfo").show();
            $("#eidtSampleInfo").hide();
            form.render();
            $("#sampleForm").find("input[name='sampleId']").val(" ");
            $("#sampleForm").find("input[name='sampleName']").val(" ");//检材名称
            $("#sampleForm").find("input[name='amount']").val(" ");//检材数量
            $("#sampleForm").find("input[name='samplingTime']").val(" ");////采样时间
            $("#sampleForm").find("input[name='samplePerson']").val(" ");//采样人
            $("#sampleForm").find("[name='samplingAddress']").val(" ");//采样地点

            $("#sampleForm").find("[name='receiveTime']").val(" ");//接收时间
            $("#sampleForm").find("[name='sampleTypeName']").val(" ");//检材类型名称
            $("#sampleForm").find("[name='measurementUnitName']").val(" ");//单位名称
            $("#sampleForm").find("input[name='remark']").val(" ");

            $("#sampleForm select:first option:first").attr("selected",true).siblings("option").attr("selected",false);

            flag="add";
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
        // 保存检材信息
//         $("#addSampleInfo").click(function () {
//
//             var kinship = $("#kinship").val();
//             if (kinship == "") {
//                 layer.msg('请选择亲缘关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var personship = $("#personship").val();
//             if (personship == "") {
//                 layer.msg('请选择人员关系！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var sampleName = $("#sampleName").val();
//             if (sampleName == "") {
//                 layer.msg('请输入检材名称！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//
//           /*  var sampleType = $("#sampleType").val();
//             if (sampleType == "") {
//                 layer.msg('请选择检材类别！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
// */
//
//
//             var amount = $("#amount").val();
//             if (amount == "") {
//                 layer.msg('请输入数量！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var measurementUnit = $("#measurementUnit").val();
//             if (measurementUnit == "") {
//                 layer.msg('请选择数量单位！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var receiveTime = $("#receiveTime").val();
//             if (receiveTime == "") {
//                 layer.msg('请选择接收时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var samplingTime = $("#samplingTime").val();
//             if (samplingTime == "") {
//                 layer.msg('请选择采样时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//
//             var samplePerson = $("#samplePerson").val();
//             if (samplePerson == "") {
//                 layer.msg('请输入采样人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//             var samplingAddress = $("#samplingAddress").val();
//             if (samplingAddress == "") {
//                 layer.msg('请输入采样地点！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
//                 return false;
//             }
//
//
//             var remark = $("#remark").val();
//
//
//             if(flag=="add"){
//                 var text = "";
//                 text += '<tr>';
//                 text += '<input type="hidden" name="remark" value="' + remark + '">';
//                 text += '<input type="hidden" name="sampleTypeName" value="' + $("#sampleType").find("option:selected").text() + '">';
//
//                 text += '<input type="hidden" name="personship" value="' + personship + '">';
//
//                 text += '<input type="hidden" name="kinshipName" value="' + $("#kinship").find("option:selected").text() + '">';
//
//                 text += '<input type="hidden" name="measurementUnitName" value="' + $("#measurementUnit").find("option:selected").text() + '">';
//                 text += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> </td>';
//                 text += '<td>' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '"></td>';
//                 text += '<td>' + amount + '<input type="hidden" name="amount" value="' + amount + '"></td>';
//                 text += '<td>' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '"></td>';
//
//                 text += '<td>' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + kinship + '"> </td>';
//
//                 text += '<td>' + samplingTime + '<input type="hidden" name="samplingTime" value="' + samplingTime + '"> </td>';
//                 text += '<td>' + samplePerson + '<input type="hidden" name="samplePerson" value="' + samplePerson + '"> </td>';
//                 text += '<td>' + samplingAddress + '<input type="hidden" name="samplingAddress" value="' + samplingAddress + '"> </td>';
//
//                 text += '<td>' + receiveTime + '<input type="hidden" name="receiveTime" value="' + receiveTime + '"> </td>';
//                 text += '<td><button class="layui-btn layui-btn-normal layui-btn-sm" type="button" onclick="modifySample(this);">修改</button> <button class="layui-btn layui-btn-normal layui-btn-sm " type="button" onclick="deleteRow(this,2)">删除</button></td>';
//                 text += '</tr>';
//
//                 //取得检材的名称
//                 options+='<option value="'+sampleName+'">'+sampleName+'</option>';
//
//                 $("#samplesTable").append(text);
//                 layer.closeAll();
//             }else if(flag=="modify"){
//                 str.find("[name='remark']").val(remark);
//                 str.find("[name='personship']").val(personship);
//                 str.find("[name='sampleTypeName']").val($("#sampleType").find("option:selected").text());
//
//                 options=options.replace(new RegExp(samName,'g'),''+sampleName+'');//替换字符串
//
//                 str.find("[name='measurementUnitName']").val($("#measurementUnit").find("option:selected").text());
//                 str.find("[name='kinshipName']").val($("#kinship").find("option:selected").text());
//
//                 var sampleNameText='' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"> ';
//                 str.find("[name='sampleName']").parent().html(sampleNameText);
//
//                 var sampleTypeText='' + $("#sampleType").find("option:selected").text() + '<input type="hidden" name="sampleType" value="' + sampleType + '">';
//                 str.find("[name='sampleType']").parent().html(sampleTypeText);
//
//                 var amountText='' + amount + '<input type="hidden" name="amount" value="' + amount + '">';
//                 str.find("[name='amount']").parent().html(amountText);
//
//                 var measurementUnitText='' + $("#measurementUnit").find("option:selected").text() + '<input type="hidden" name="measurementUnit" value="' + measurementUnit + '">';
//                 str.find("[name='measurementUnit']").parent().html(measurementUnitText);
//
//                 var kinshipNameText='' + $("#kinship").find("option:selected").text() + '<input type="hidden" name="kinship" value="' + kinship + '"> ';
//                 str.find("[name='kinship']").parent().html(kinshipNameText);
//
//                 var samplingTimeText='' + samplingTime + '<input type="hidden" name="samplingTime" value="' + samplingTime + '">';
//                 str.find("[name='samplingTime']").parent().html(samplingTimeText);
//
//                 var samplePersonText='' + samplePerson + '<input type="hidden" name="samplePerson" value="' + samplePerson + '">';
//                 str.find("[name='samplePerson']").parent().html(samplePersonText);
//
//                 var samplingAddressText='' + samplingAddress + '<input type="hidden" name="samplingAddress" value="' + samplingAddress + '">';
//                 str.find("[name='samplingAddress']").parent().html(samplingAddressText);
//
//
//                 var receiveTimeText='' + receiveTime + '<input type="hidden" name="receiveTime" value="' + receiveTime + '">';
//                 str.find("[name='receiveTime']").parent().html(receiveTimeText);
//
//                 layer.closeAll();
//
//             }
//
//         });





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
                    text += '<input type="checkbox"  lay-skin="primary" lay-filter="identifyMajor" name="Major" value="' + item.id + '" title="' + item.identificationProject + '">';
                });

                $("#jdxm").append(text);

                /*  $("#pageCase").load(url);*/
                form.render();
            }
        });
    });

}


//删除被鉴定人信息  删除当前行
function deleteRow(obj,f){
    layui.use(['form'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        if(f==1){
            var name=$(obj).parent().siblings().find("input[name='name']").val();
            //删除对应的人员关系option
            $("#personship option[value="+name+"]").remove();
            form.render();
        }
        var sampleName=$(obj).parent().siblings().find("input[name='sampleName']").val();
        options=options.replace(new RegExp("",'g'),''+sampleName+'');//替换字符串
        $(obj).parent().parent().remove();

    });


}



//回显鉴定人信息
var id = "";
function modifyPersion(obj){
    flag="modify";

    $("#memberForm").resetForm();
    id = $(obj).parent().siblings(":first").text();
    var name = $(obj).parent().siblings("name").text();


    $.ajax({
        url:baseurl +"/case/selectAdmissibleReception.html",
        data:{"id":id},
        dataType:"json",
        success:function(data){

            // console.log(data.dacasCaseNews);
            var ids = $("#memberForm").find("input[name='id']").val(data.dacasMemberInfo.id);
            var name = $("#name").val(data.dacasMemberInfo.name);

            $("#gender option").each(function() {
                var gender = $(this).attr("value");
                if(gender == data.dacasMemberInfo.gender){
                    $("#gender option[value='"+data.dacasMemberInfo.gender+"']").attr("selected","selected");//根据值让option选中
                }
            });

            // var nation = $("#nation").val(data.dacasMemberInfo.nation);//民族
            $("#nation option").each(function(){
                var nation = $(this).attr("value");
                if(nation = data.dacasMemberInfo.nation){
                    $("#nation option[value='"+data.dacasMemberInfo.nation+"']").attr("selected","selected");
                }
            });

            //称谓identity
            $("#identity option").each(function(){
                var identity = $(this).attr("value");
                if(identity = data.dacasMemberInfo.identity){
                    $("#identity option[value='"+data.dacasMemberInfo.identity+"']").attr("selected","selected");
                }
            });

            var birthdate = $("#birthdate").val(formartDate(data.dacasMemberInfo.birthdate));//被鉴定人生日
            // var country = $("#country").val(data.dacasMemberInfo.country);//国籍
            $("#country option").each(function(){
                var country = $(this).attr("value");
                if(country = data.dacasMemberInfo.country){
                    $("#nation option[value='"+data.dacasMemberInfo.country+"']").attr("selected","selected");
                }
            });
            var phone =  $("#phone").val(data.dacasMemberInfo.phone);//电话
            var sampleAddress = $("#sampleAddress").val(data.dacasMemberInfo.sampleAddress);
            var sampleDate = $("#sampleDate").val(formartDate(data.dacasMemberInfo.sampleDate));
            //输血史
            $("#transfusionBlood input").each(function(){
                var transfusionBlood = $(this).attr("value");
                if(transfusionBlood = data.dacasMemberInfo.bloodHistory){
                    $("#transfusionBlood input[value='"+data.dacasMemberInfo.bloodHistory+"']").attr("checked","checked");
                }
            });
            //证件类型
            $("#cardType option").each(function(){
                var cardType = $(this).attr("value");
                if(cardType = data.dacasMemberInfo.cardType){
                    $("#cardType option[value='"+data.dacasMemberInfo.cardType+"']").attr("selected","selected");
                }
            });
            var idCard = $("#idCard").val(data.dacasMemberInfo.idCard);//证件号码

            form.render();
        }
    });



    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var $ = layui.jquery;

        $("#addBtn").hide();
        $("#editBtn").show();

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




//更新被鉴定人信息
function updateMemberInfo(){
    // $("#memberForm").resetForm();

    var id = $("#memberForm").find("input[name='id']").val();
    var name = $("#name").val();

    var caseId = $("#caseMemberId").val();

    var gender = $('#gender option:selected').val();
    var genderName = $("#gender option:selected").text();
    // var nation = $("#memberForm").find("select[name='nation']").val();
    var nation = $('#nation option:selected').val();//选中的值
    var nationName = $('#nation option:selected').text();//选中的值

    var identity = $('#identity option:selected').val();
    var identityName = $('#identity option:selected').text();
    var birthdate = $("#memberForm").find("input[name='birthdate']").val();
    // var country = $("#memberForm").find("select[name='country']").val();
    var country = $("#country option:selected").val();
    var countryName = $("#country option:selected").text();
    var phone = $("#memberForm").find("input[name='phone']").val();
    var sampleAddress = $("#memberForm").find("input[name='sampleAddress']").val();
    var sampleDate = $("#memberForm").find("input[name='sampleDate']").val();
    var bloodHistory = $('input:radio[name="bloodHistory"]:checked').val();
    //cardType
    var cardType = $("#cardType option:selected").val();
    var idCard = $("#memberForm").find("input[name='idCard']").val();


    form.render();

    var sampleDataList;
    sampleDataList = {
        "id": id,
        "caseId": caseId,
        "name": name,
        "gender": gender,
        "genderName": genderName,
        "nation": nation,
        "nationName": nationName,
        "identity": identity,
        "identityName":identityName,
        "birthdate": birthdate,
        "country": country,
        "countryName": countryName,
        "phone": phone,
        "sampleAddress": sampleAddress,
        "sampleDate": sampleDate,
        "bloodHistory": bloodHistory,
        "cardType": cardType,
        "idCard": idCard
    };

    $.ajax({
        url:baseurl +"/case/updateAdmissibleMember.html",
        data:{"sampleDataList": JSON.stringify(sampleDataList)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload();

        }
    })

}


//添加鉴定人信息
function addMemberInfoBtn(){

    var id = $("#strmemberId").val();
    var caseMemberId = $("#caseMemberId").val();
    var name = $("#name").val();
    var gender = $('#gender option:selected').val();
    var genderName = $("#gender option:selected").text();

    var nation = $('#nation option:selected').val();
    var nationName = $('#nation option:selected').text();

    var identity = $('#identity option:selected').val();
    var identityName = $('#identity option:selected').text();
    var birthdate = $("#birthdate").val();
    var country = $('#country option:selected').val();
    var countryName = $("#country option:selected").text();
    var phone = $("#phone").val();
    var sampleAddress = $("#sampleAddress").val();
    var sampleDate = $("#sampleDate").val();
    var bloodHistory = $('input:radio[name="bloodHistory"]:checked').val();

    var cardType = $("#cardType option:selected").val();
    var idCard = $("#memberForm").find("input[name='idCard']").val();

    var memberData;
    memberData= {
        "id":id,
        "caseId":caseMemberId,
        "name":name,
        "gender":gender,
        "genderName":genderName,
        "nation":nation,
        "nationName":nationName,
        "identity":identity,
        "identityName":identityName,
        "birthdate":birthdate,
        "country":country,
        "countryName":countryName,
        "phone":phone,
        "sampleAddress":sampleAddress,
        "sampleDate":sampleDate,
        "bloodHistory":bloodHistory,
        "cardType":cardType,
        "idCard":idCard
    };

    $.ajax({
        url:baseurl +"/case/insertAdmissibleMember.html",
        data:{"memberData": JSON.stringify(memberData)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload()

        }
    });
}

//回显检材信息
var sampleId;
function modifySample(obj){
    flag="modify";
    $("#sampleForm").resetForm();

    sampleId = $(obj).parent().siblings(":first").text();


    $.ajax({
        url:baseurl +"/case/selectAdmissibleSample.html",
        data:{"sampleId":sampleId},
        dataType:"json",
        type:'post',
        success:function(data){
            console.log(data.dacasSampleInfo);
            $("#sampleForm").find("input[name='sampleId']").val(data.dacasSampleInfo.sampleId);//检材id
            $("#sampleForm").find("input[name='memberId']").val(data.dacasSampleInfo.memberId);
            $("#sampleForm").find("input[name='caseBaseId']").val(data.dacasSampleInfo.caseBaseId);
            //亲缘关系
            $("#kinship option").each(function(){
                var kinship = $(this).attr("value");
                if(kinship = data.dacasSampleInfo.kinship){
                    $("#kinship option[value='"+data.dacasSampleInfo.kinship+"']").attr("selected","selected");
                }
            });
            // personship人员关系
            $("#personship option").each(function(){
                var personship = $(this).attr("value");
                if(personship = data.dacasSampleInfo.personship){
                    $("#personship option[value='"+data.dacasSampleInfo.personship+"']").attr("selected","selected");
                }
            });
            $("#sampleName").val(data.dacasSampleInfo.sampleName);//检材名称
            $("#sampleType option").each(function(){
                var sampleType = $(this).attr("value");
                if(sampleType = data.dacasSampleInfo.sampleType){
                    $("#sampleType option[value='"+data.dacasSampleInfo.sampleType+"']").attr("selected","selected");
                }
            });
            $("#sampleForm").find("input[name='amount']").val(data.dacasSampleInfo.amount);//检材数量
            $("#measurementUnit option").each(function(){
                var measurementUnit = $(this).attr("value");
                if(measurementUnit = data.dacasSampleInfo.measurementUnit){
                    $("#measurementUnit option[value='"+data.dacasSampleInfo.measurementUnit+"']").attr("selected","selected");
                }
            });
            $("#receiveTime").val(formartDate(data.dacasSampleInfo.receiveTime));//接收时间Format
            $("#samplingTime").val(formartDate(data.dacasSampleInfo.samplingTime));//提取时间
            $("#samplePerson").val(data.dacasSampleInfo.samplePerson);//采样人
            $("#samplingAddress").val(data.dacasSampleInfo.samplingAddress);//采样地点
            $("#remark").val(data.dacasSampleInfo.remark);


            // var measurementUnit = $("#sampleForm").find("input[name='measurementUnit']").val(data.dacasSampleInfo.measurementUnit);//检材数量单位

        }

    });
    form.render();


    layui.use(['layer', 'form'], function () {
        var layer = layui.layer;
        var $ = layui.jquery;

        $("#addSampleInfo").hide();
        $("#eidtSampleInfo").show();

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


//更新检材信息
function updateSampleInfo(){

    sampleId = $("#sampleForm").find("input[name='sampleId']").val();
    var memberId = $("#sampleForm").find("input[name='memberId']").val();
    var caseBaseId = $("#sampleForm").find("input[name='caseBaseId']").val();
    var sampleName = $("#sampleForm").find("input[name='sampleName']").val();//检材名称
    var sampleType = $('#sampleType option:selected').val();//检材类型
    var sampleTypeName = $('#sampleType option:selected').text();//检材类型名称

    var kinship = $('#kinship option:selected').val();//亲缘关系
    var kinshipName = $('#kinshipName option:selected').val();//亲缘关系

    var amount = $("#sampleForm").find("input[name='amount']").val();//检材数量

    var measurementUnit = $("#measurementUnit option:selected").val();//检材数量单位
    var measurementUnitName = $("#measurementUnit option:selected").text();//检材数量单位


    var samplingTime = $("#sampleForm").find("input[name='samplingTime']").val();////采样时间
    var samplePerson = $("#sampleForm").find("input[name='samplePerson']").val();//采样人
    var samplingAddress = $("#sampleForm").find("[name='samplingAddress']").val();//采样地点

    var receiveTime = $("#sampleForm").find("[name='receiveTime']").val();//接收时间
    var remark = $("#sampleForm").find("input[name='remark']").val();


    var personship = $('#personship option:selected').val();//人员关系
    alert(personship);

    var strsampleData;
    strsampleData = {
        "sampleId": sampleId,
        "memberId": memberId,
        "caseBaseId": caseBaseId,
        "sampleName": sampleName,
        "sampleType": sampleType,
        "sampleTypeName": sampleTypeName,
        "kinship": kinship,
        "kinshipName": kinshipName,
        "amount": amount,
        "measurementUnit": measurementUnit,
        "measurementUnitName": measurementUnitName,
        "samplingTime": samplingTime,
        "samplePerson": samplePerson,
        "samplingAddress": samplingAddress,
        "receiveTime": receiveTime,
        "remark": remark,
        "personship":personship
    };

    $.ajax({
        url:baseurl +"/case/updateAdmissibleSample.html",
        data:{"strsampleData": JSON.stringify(strsampleData)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload()

        }
    });
    form.render();

}

//添加检材信息
function addBtnSampleInfo(){

    var sampleIds = $("#sampleId").val();
    var memberId = $("#strmemberId").val();
    var caseBaseId = $("#caseBaseId").val();
    console.log(memberId+caseBaseId);
    var sampleName = $("#sampleForm").find("input[name='sampleName']").val();//检材名称
    // var sampleType = $("#sampleForm").find("input[name='sampleType']").val();//检材类型
    var sampleType = $("#sampleType option:selected").val();
    var sampleTypeName = $("#sampleType option:selected").text();

    // var kinship = $("#sampleForm").find("input[name='kinship']").val();//亲缘关系
    var kinship = $('#kinship option:selected').val();
    var kinshipName = $("#kinship option:selected").text();


    var amount = $("#sampleForm").find("input[name='amount']").val();//检材数量
    // var measurementUnit = $("#sampleForm").find("input[name='measurementUnit']").val();//检材数量单位
    var measurementUnit = $('#measurementUnit option:selected').val();
    var measurementUnitName = $("#measurementUnit option:selected").text();
    var samplingTime = $("#sampleForm").find("input[name='samplingTime']").val();////采样时间
    var samplePerson = $("#sampleForm").find("input[name='samplePerson']").val();//采样人
    var samplingAddress = $("#sampleForm").find("[name='samplingAddress']").val();//采样地点

    var receiveTime = $("#sampleForm").find("[name='receiveTime']").val();//接收时间
    var remark = $("#sampleForm").find("input[name='remark']").val();

    var personship = $('#personship option:selected').val();//被鉴定人

    form.render();

    var sampleValue;
    sampleValue = {
        "sampleId": sampleIds,
        "memberId": memberId,
        "caseBaseId": caseBaseId,
        "sampleName": sampleName,
        "sampleType": sampleType,
        "sampleTypeName": sampleTypeName,
        "kinship": kinship,
        "kinshipName": kinshipName,
        "amount": amount,
        "measurementUnit": measurementUnit,
        "samplingTime": samplingTime,
        "samplePerson": samplePerson,
        "samplingAddress": samplingAddress,
        "receiveTime": receiveTime,
        "measurementUnitName": measurementUnitName,
        "personship": personship,
        "remark": remark
    };
    $.ajax({
        url:baseurl +"/case/addAdmissibleSample.html",
        data:{"sampleValue": JSON.stringify(sampleValue)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload()

        }
    });


}



//更新案件信息和财务信息
function updateAdmissibleCase(obj){

    // 案件数据
    var id = $("#cid").val();
    var appraisalProject = $("#appraisalProject").val();
    var appraisalMajor = $("#appraisalMajor").val();
    var caseContent = $("#caseContent").val();
    var client  = $("#client").val();
    var delegateAt = $("#delegateAt").val();
    var clientCategory = $("#clientCategory").val();
    var censorship = $("#censorship").val();
    var contacts = $("#contacts").val();
    var telephone = $("#telephone").val();
    var contactsAddress = $("#contactsAddress").val();
    /*var proceedings = $("#proceedings").val();*/
    var proceedings = $("input[name='proceedings']:checked").val();;
  /*  var reappraisal = $("#reappraisal").val();*/
    var caseSn = $("#caseSn").val();
    var acceptAt = $("#acceptAt").val();
    var timeLimit = $("#timeLimit").val();
   /* var caseWas = $("#caseWas").val();*/
    var reappraisal = $("#reappraisal").val();
    var appraisalPurpose = $("#appraisalPurpose").val();
    var caseSource = $("#caseSource").val();
    var briefCase = $("#briefCase").val();
    var sampleNumber = $("#sampleNumber").val();
    var maintainPeople = $("#maintainPeople").val();
    var remark = $("#remarks").val();


    var caseType = $("#caseType").val();
    var caseReason = $("#caseReason").val();


    var assignee = $("#assignee").val();

    var appraiser;
    //第一鉴定人
    var appraiser1 = $("#appraiser1").val();
    if(appraiser1){
        appraiser = appraiser1;
    }

    //第二鉴定人
    var appraiser2 = $("#appraiser2").val();
    if(appraiser2){
        appraiser = appraiser1+","+appraiser2;

    }
    //第三鉴定人
    var appraiser3 = $("#appraiser3").val();
    if(appraiser3){
        appraiser = appraiser1+","+appraiser2+","+appraiser3;
    }

    var signatory = $("#signatory").val();

    //文本发送发送方式
    var instrumentsType = $("#instrumentsType").find("input[name='instrumentsType']:checked").val();
    var instrumentsContent = $("#instrumentsContent").val();


    var admissibleCaseInfo;
    admissibleCaseInfo={
        "id":id,
        "appraisalProject":appraisalProject,
        "appraisalMajor":appraisalMajor,
        "caseContent":caseContent,
        "client":client,
        "delegateAt":delegateAt,
        "clientCategory":clientCategory,
        "censorship":censorship,
        "contacts":contacts,
        "telephone":telephone,
        "contactsAddress":contactsAddress,
        "proceedings":proceedings,
        "reappraisal":reappraisal,
        "caseSn":caseSn,
        "acceptAt":acceptAt,
        "timeLimit":timeLimit,
        /*"caseWas":caseWas,*/
        "sampleNumber":sampleNumber,
        "maintainPeople":maintainPeople,
        "remark":remark,
        "caseType":caseType,
        "caseReason":caseReason,
        "appraisalPurpose":appraisalPurpose,
        "caseSource":caseSource,
        "briefCase":briefCase,
        "assignee":assignee,
        "appraiser":appraiser,
        "signatory":signatory,
        "instrumentsType":instrumentsType,
        "instrumentsContent":instrumentsContent
    };


    $.ajax({
        url:baseurl +"/case/updateAdmissibleCase.html",
        data:{"admissibleCaseInfo": JSON.stringify(admissibleCaseInfo)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload()

        }
    });


    //财务数据
    var chargeId = $("#ChargeId").val();
    var caseInfoId =$("#caseInfoId").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var county = $("#county").val();
    var chargeType = $("#chargeType").find("input[type='radio']:checked").val();
    var chargeAmount = $("#chargeAmount").val();
    var remark = $("#remarkes").val();
    var payMethod = $("#payMethod").val();
    var payMethodMsg = $("#payMethodMsg").val();
    var receiptTime = $("#receiptTime").val();
    var invoiceTime = $("#invoiceTime").val();
    var invoiceName = $("#invoiceName").val();

    var chargeData;
    chargeData={
        "id":chargeId,
        "caseInfoId":caseInfoId,
        "province":province,
        "city":city,
        "county":county,
        "chargeType":chargeType,
        "chargeAmount":chargeAmount,
        "remark":remark,
        "payMethod":payMethod,
        "payMethodMsg":payMethodMsg,
        "receiptTime":receiptTime,
        "invoiceTime":invoiceTime,
        "invoiceName":invoiceName
    };

    $.ajax({
        url:baseurl +"/case/updataadmissibleChargeData.html",
        data:{"chargeData": JSON.stringify(chargeData)},
        dataType:"json",
        type:"post",
        success:function(data){
            console.log(data);
            form.render();
            layer.closeAll();
            window.location.reload()

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
