$(function () {

    var commonParameters;

    var mapKist=new Map();
    var pmhMap=new Map();
    var pmhVal=new Map();


    mapKist["YFILER PLUS"]="10,20,25";
    pmhMap["YFILER PLUS"]="buffer,primer,Polymerase MixⅠ,模板,H2O";
    pmhVal["YFILER PLUS-10"]="4,2,0.22,/,3.78";
    pmhVal["YFILER PLUS-20"]="8,4,3,2,3";
    pmhVal["YFILER PLUS-25"]="5,5,1.0,14.0,/";

    mapKist["Identifiler Plus"]="10,25";
    pmhMap["Identifiler Plus"]="Reaction Mix,引物,酶,DNA模板,去离子水";
    pmhVal["Identifiler Plus-10"]="4,2,0.22,/,3.78";
    pmhVal["Identifiler Plus-25"]="5,5,1.0,14.0,/";

    mapKist["GlobalFiler"]="20,25";
    pmhMap["GlobalFiler"]="buffer,primer Set,Taq,模板,H2O";
    pmhVal["GlobalFiler-20"]="8,4,3,2,3";
    pmhVal["GlobalFiler-25"]="5,5,1.0,14.0,/";






    layui.use(['jquery', 'layer', 'form', 'upload', 'element'], function () {
        var element = layui.element;
        var $ = layui.jquery
            , upload = layui.upload
            ,form = layui.form;


        form.on('select(proLotNumber)', function(data){
            var id=data.value;//拿到批号的id
            $.ajax({
                type:"post",
                url : baseurl+'/system/findLotNumberById.html',//目标方法
                dataType : "json",
                data : {"id":id},
                success : function(data) {
                    $("#validityPeriod").val(formartDate(data.validityPeriodTime));
                }
            });
        });

        form.on('select(ampLotNumber)', function(data){
            var id=data.value;//拿到批号的id
            $.ajax({
                type:"post",
                url : baseurl+'/system/findLotNumberById.html',//目标方法
                dataType : "json",
                data : {"id":id},
                success : function(data) {
                    $("#ampvalidityPeriod").val(formartDate(data.validityPeriodTime));
                }
            });
        });

        form.on('select(extractLotNumber)', function(data){
            var id=data.value;//拿到批号的id
            var name=$(data.elem).find('option:selected').text();
            var thisElem=data.elem;
            $.ajax({
                type:"post",
                url : baseurl+'/system/findLotNumberById.html',//目标方法
                dataType : "json",
                data : {"id":id},
                success : function(data) {
                    $(thisElem).find('option:selected').html("");
                    $(thisElem).find('option:selected').html(data.lotNumber+"/"+formartDate(data.validityPeriodTime));
                    form.render();
                }
            });
        });



        //联动
        form.on('select(kit)', function (data) {
            var id=data.value;//拿到试剂盒的id
            $.ajax({
                type:"post",
                url : baseurl+'/system/findAmplificationKitById.html',//目标方法
                dataType : "json",
                data : {"entity.equipmentManagementId":id, "operate" : '1'},
                success : function(data) {
                    $("#kztx").empty();
                    var systemLength = data.length;
                    var text="";
                    if (id != "" && id.length > 0) {
                        for(var i=0;i< systemLength;i++){
                            if(i==0){
                                text+='<input type="radio" checked lay-filter="kzSystem" name="kzSystem" value="'+  data[i].entity.system +'" title="'+ data[i].systemName +'" lay-skin="primary">';
                            }else{
                                text+='<input type="radio" lay-filter="kzSystem" name="kzSystem" value="'+  data[i].entity.system +'" title="'+ data[i].systemName +' "lay-skin="primary">';
                            }
                        }

                        $("#kztx").append(text);

                        if (systemLength > 0) {
                            $.ajax({
                                type:"post",
                                url : baseurl+'/system/findAmplificationKitById.html',//目标方法
                                dataType : "json",
                                data : {"entity.system":data[0].entity.system},
                                success : function(data) {
                                    $("#subagentList").empty();
                                    var text="";
                                    var $sampleTR = $(".sampleAmplification");
                                    var sampleCnt = $sampleTR.length;
                                    for(var j=0;j<data.length;j++){
                                        text+='<div class="layui-col-md4" style="width: 20%!important;"><input type="text" name="subagent" class="layui-input" value="'+ data[j].entity.kitLocus +'"> </div>';
                                        for(var  k = 0;k < sampleCnt;k++) {
                                            $("#sampleTable").find("div[name='subagentDiv']").eq(k).find("input[order='sub"+(j+1)+"']").val(data[j].entity.systemNumber);
                                        }
                                    }

                                    $("#subagentList").append(text);
                                }
                            });
                        }else {
                            $("#kztx").empty();
                            $("#subagentList").empty();
                            $("input[otherName='subagent']").val('');
                            form.render();
                            return;
                        }
                    }else {
                        $("#kztx").empty();
                        $("#subagentList").empty();
                        $("input[otherName='subagent']").val('');
                        form.render();
                        return;
                    }

                    form.render();
                }
            });
        });

        //扩增体系
        form.on('radio(kzSystem)', function(data){
            var id = data.value;
            $.ajax({
                type:"post",
                url : baseurl+'/system/findAmplificationKitById.html',
                dataType : "json",
                data : {"entity.system":id},
                success : function(data) {
                    $("#subagentList").empty();
                    var text="";
                    var $sampleTR = $(".sampleAmplification");
                    var sampleCnt = $sampleTR.length;
                    for(var j=0;j<data.length;j++){
                        text+='<div class="layui-col-md4" style="width: 20%!important;"><input type="text" name="subagent" class="layui-input" value="'+ data[j].entity.kitLocus +'"> </div>';
                        for(var  k = 0;k < sampleCnt;k++) {
                            $("#sampleTable").find("div[name='subagentDiv']").eq(k).find("input[order='sub"+(j+1)+"']").val(data[j].entity.systemNumber);
                        }
                    }
                    $("#subagentList").append(text);

                    form.render();
                }
            });
        });

        /*form.on('select(kit)', function (data) {
            $("#sampleTable").find("div[name='subagentDiv']").first().find("input").val("");
            $("#sampleTable").find("div[name='subagentDiv']").eq(1).find("input").val("");
            var k=data.value;
            var text="";
            var text1="";
            if(k=="0"){
                $("#kztx").empty();
                $("#subagentList").empty();
                form.render();
                return;
            }
            //查找试剂盒下面的扩增体系值
            var amplification=String(mapKist[k]); //获取到了key所对应的value的值！
            var s=amplification.split(",");

            var subagentAtr=String(pmhMap[k]);
            var ss=subagentAtr.split(",");

            $("#kztx").empty();
            $("#subagentList").empty();
            for(var i=0;i< s.length;i++){
                text+='<input type="radio" lay-filter="filter" name="kzSystem" kztx="'+k+'" value="'+s[i]+'" title="'+s[i]+'μl"lay-skin="primary">';
            }
            for(var j=0;j<ss.length;j++){
                text1+='<div class="layui-col-md4" style="width: 20%!important;"><input type="text" name="subagent" class="layui-input" value="'+ss[j]+'"> </div>';
            }
            $("#kztx").append(text);
            $("#subagentList").append(text1);
            form.render();
        });*/

        //扩增体系radio点击事件
       /* form.on('radio(filter)',function(data){
            var ul=data.value;
            var kztx=$(this).attr("kztx");
            var key=kztx+"-"+ul;
            var pm=String(pmhVal[key]);
            var sval=pm.split(",");
            var $sampleTR = $(".sampleAmplification");
            var sampleCnt = $sampleTR.length;
            for(var i=0;i<sval.length;i++){
                for(var  j = 0;j < sampleCnt;j++) {
                    $("#sampleTable").find("div[name='subagentDiv']").eq(j).find("input[order='sub"+(i+1)+"']").val(sval[i]);
                }
            }
        });*/


        //照片下载
        var map = {};
        var mapSn = {};
        var mapFlag = {};
        var sampleSnValue;
        var flagStr;
        var files;
        uploadListIns = upload.render({
            elem: ".addimgBtn"
            , url: baseurl + '/imageUpload.html'
            , multiple: true
            , accept: 'images'
            , auto: false
            , bindAction: '#saveFile'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files, function (key, values) {
                    var fileName = files[key].name;
                    map[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).val();
                    mapSn[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).prev().val();
                    mapFlag[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).next().val();
                });

                this.data = {
                    'dataMap': JSON.stringify(map),
                    'mapSn': JSON.stringify(mapSn),
                    'mapFlag': JSON.stringify(mapFlag)
                };
            }
            , choose: function (obj) {
                //预读本地文件示例，不支持ie8
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                sampleSnValue = this.sampleSn;
                flagStr = this.flagStr;
                //将url 放入text里面  隐藏域  通过  input的id 取其兄弟元素的值  就是对应的检材编号 然后进行拼接数据
                obj.preview(function (index, file, result) {

                    var text = $(['<span class="imgBox">'
                        , '<i class="layui-icon demo-delete">&#x1007;</i>'
                        , '<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img">'
                        , '<input type="hidden"  value="' + sampleSnValue + '">'
                        , '<input type="text" id="fremark' + file.name.substring(0, file.name.indexOf(".")) + '" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input">'
                        , '<input type="hidden"  value="' + flagStr + '">'
                        , '</span>'].join(''));

                    //$(this.document.activeElement).parent().parent().append('<span class="imgBox"><img src="' + result + '" alt="' + file.name + '" class="layui-upload-img"><input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入备注" class="layui-input"></span>\n')
                    //删除
                    text.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        text.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    $(this.document.activeElement).parent().parent().append(text);
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
                /*alert(obj.successful+"总成功数");*/
                /*  alert(obj.total+"总文件"); //得到总文件数
                 alert(obj.successful+"总成功数"); //请求成功的文件数
                 alert(obj.aborted+"失败"); //请求失败的文件数*/
            }

        });
    });


    //更新实验记录
    $("button[name='modifyExamine']").on("click", function () {

        alert("更新操作暂时没完工。。。。");

    });



    //完成
    $("button[name='completeExamine']").on("click", function () {
        //拿到前期实验记录数据


        var batchNumber = $("#batchNumber").val();
        if (batchNumber == "") {
            layer.msg('请输入批号！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            return false;
        }
        var validityPeriod = $("#validityPeriod").val();
        if (validityPeriod == "") {
            layer.msg('请选择有效期！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            return false;
        }


        // var firstSignatory = $("#firstSignatory").val();
        // if (firstSignatory == "") {
        //     layer.msg('请输入第一签字人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     return false;
        // }


        // var secondSignatory = $("#secondSignatory").val();
        // if (secondSignatory == "") {
        //     layer.msg('请输入第二签字人！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
        //     return false;
        // }




        var signatoryAt = $("#signatoryAt").val();
        if (signatoryAt == "") {
            layer.msg('请选择签字时间！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            return false;
        }

        var thermalExpansion = $("#thermalExpansion").val();
        //实验操作人
        var laboratoryNames = $("[name='laboratoryId']").find("option:selected").text();
        //共有参数
        commonParameters = {
            "caseSn":$("#caseSn").val(),
            "caseId": $("#caseId").val(),
            "laboratoryId": $("#laboratoryId").val(),
            "laboratoryName": laboratoryNames,
            // "examineMethod": examineMethodArr.join(','),
            // "guideline": guidelineArr.join(','),
            "batchNumber": batchNumber,
            "validityPeriod": validityPeriod,
            "signatoryAt": signatoryAt
        }


        //检材结果数据
        var examineStr = "[]";
        var examineArray = eval('(' + examineStr + ')');

        $("div[name='sampleList']").each(function (index) {
            var flagStr = $(this).find("[name='flagStr']").val();
            var current_row = $(this).find("[name='count']").val();
            var sampleSn = $(this).find("input[name='sampleSn']").val();
            var testResult = $(this).find("[name='testResult" + current_row + "']:checked").val();

            var examineMethodArr = [];
            //检测方法

            $(this).find("input[name='examineMethodResult']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    examineMethodArr.push($(this).val());
                }
                // if($(this).prop("checked") === false){
                //     layer.msg('请选择检测方法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                //         return false;
                // }
            });

            // if (examineMethodArr.length == 0) {
            //     layer.msg('请选择检测方法！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            //     return false;
            // }

            var guidelineArr = [];
            //参考标准
            $(this).find("input[name='guidelineResult']:checkbox").each(function (index) {
                if ($(this).prop("checked") === true) {
                    guidelineArr.push($(this).val());
                }
            });

            // if (guidelineArr.length == 0) {
            //     layer.msg('请选择参考标准！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
            //     return false;
            // }
            var data = {
                "flagStr": flagStr,
                "sampleSn": sampleSn,
                "testResult": testResult,
                "examineMethodResult": examineMethodArr.join(','),
                "guidelineResult": guidelineArr.join(',')
            };
            examineArray.push(data);
        });

        //保存 数据
        $.ajax({
            url: baseurl + "/saveExamineData.html",
            type: "post",
            dataType: "json",
            data: {"dataMap": JSON.stringify(examineArray), "commonParameters": JSON.stringify(commonParameters)},
            success: function (data) {
                if (data.msg == "true") {
                    $("#examineId").val(data.examineId);//赋值前期实验id
                    $("#saveFile").click();
                    //提交检验记录表数据

                    //检材结果数据
                    var sampleStr = "[]";
                    var sampleArray = eval('(' + sampleStr + ')');


                    var lotNumberValidityArr = [];
                    //获取整个table的tr
                    $('#sampleMethod tr').each(function (i) {
                        var sampleSn = $(this).find("input[otherName='sampleSn']").val();//检材编号

                        if (sampleSn == '阴性' || sampleSn == '阳性') {
                            var data = {
                                "sampleSn": sampleSn
                            };
                            sampleArray.push(data);

                        } else {
                            var templateQuantity = $(this).find("input[otherName='templateQuantity']").val();//模板量

                            debugger;

                            // var extractionMethodArr = [];
                            // //提取方法
                            // $(this).find("input[otherName='extractionMethod']:checkbox").each(function (index) {
                            //     if ($(this).prop("checked") === true) {
                            //         extractionMethodArr.push($(this).val());
                            //     }
                            // });

                            /**
                             * 提取方法：磁珠法，聚苯乙烯二乙烯基苯树脂法
                             * @type {Array}
                             */
                            var extractionMethod = $(this).find("input[otherName='extractionMethod']").val();//磁珠法

                            var vinylBenzeneResin = $(this).find("input[otherName='vinylBenzeneResin']").val();//聚苯乙烯二乙烯基苯树脂法


                            //其他试剂
                            // var otherReagentsMethodArr = [];
                            // $(this).find("input[otherName='otherReagents']:checkbox").each(function (index) {
                            //     if ($(this).prop("checked") === true) {
                            //         otherReagentsMethodArr.push($(this).val());
                            //     }
                            // });
                            var otherReagents = $(this).find("input[otherName='otherReagents']").val();//DTT
                            var pkEnzyme = $(this).find("input[otherName='pkEnzyme']").val();//PK酶


                            //var lotNumberValidity = $(this).find("input[otherName='lotNumberValidity']").val();//试剂批号及有效期
                            var lotNumberValidity = $(this).find("select[otherName='lotNumberValidity']").find("option:selected").text();
                            lotNumberValidityArr.push(lotNumberValidity);

                            //仪器
                            // var instrumentArr = [];
                            // $(this).find("input[otherName='instrument']:checkbox").each(function (index) {
                            //     if ($(this).prop("checked") === true) {
                            //         instrumentArr.push($(this).val());
                            //     }
                            // });
                            var instrument = $(this).find("input[otherName='instrument']").val();//恒温金属浴
                            var centrifuge = $(this).find("select[otherName='centrifuge']").find("option:selected").val();//离心机



                            var thermalExpansion = $("#thermalExpansion").val();
                            //操作人
                            var recordOperatorrName = $("#recordOperatorr").find("option:selected").text();
                            $("#recordOperatorrName").val(recordOperatorrName);
                            var data = {
                                "sampleSn": sampleSn,
                                "templateQuantity": templateQuantity,
                                "thermalExpansion":thermalExpansion,
                                // "extractionMethod": extractionMethodArr.join(','),
                                 //"otherReagents": otherReagentsMethodArr.join(','),
                                "otherReagents": otherReagents,
                                // "instrument": instrumentArr.join(',')
                                "extractionMethod":extractionMethod,
                                "vinylBenzeneResin":vinylBenzeneResin,
                                "pkEnzyme":pkEnzyme,
                                "numberValidity": lotNumberValidity,
                                "instrument":instrument,
                                "centrifuge":centrifuge
                            };
                            sampleArray.push(data);
                        }

                    });


                    $("#numberValidity").val(lotNumberValidityArr.join(','));//试剂批号及有效期

                    //获取子试剂的值
                    var subagentArr = [];
                    $("input[name='subagent']").each(function () {
                        subagentArr.push($(this).val());
                    });

                    $("#kitReagent").val(subagentArr.join(','));


             /*      //扩增仪
                    var thermalExpansion = [];
                    $("input[otherName='thermalSion']:radio").each(function (index) {
                        if ($(this).prop("checked") === true) {
                            thermalExpansion.push($(this).val());
                        } else {

                        }
                    });

                    $("#thermalExpansion").val(thermalExpansion);//扩增仪*/


                    //扩增体系
                    var a = $("input[name='kzSystem']:checked").val();
                    $("#amplificationSystem").val(a);




                    $("div[name='sampleAmplification']").each(function (i) {
                        var current_row = $(this).find("[name='count']").val();
                        var sampleSn = $(this).find("input[otherName='sampleSn']").val();//检材编号

                        var subagentArr1 = [];
                        $(this).find("[otherName='subagent']").each(function () {
                            subagentArr1.push($(this).val());
                        });

                        // var holeNumber = $(this).find("input[otherName='holeNumber']").val();//孔道号
                        // var holeNumber = $(this).find("select[name='holeNumber']:selected").val();//孔道号
                        var holeNumber = $(this).find("select[otherName='holeNumber']").find("option:selected").val();//孔道号


                        var remark = $(this).find("input[otherName='remark']").val();//备注

                        var sampleSystem = $("#strOrganizationSystem").val();

                        if (sampleArray[i].sampleSn == sampleSn) {
                            var temp = sampleArray[i];
                            temp.holeNumber = holeNumber;
                            temp.remark = remark;
                            temp.subagent = subagentArr1.join(',');
                            sampleArray[i] = temp;
                        }

                    });
                    $("#testRecordForm").ajaxSubmit({
                        type: "post",
                        async: false,
                        dataType: "json",
                        data: {"dataMap": JSON.stringify(sampleArray)},
                        success: function (data) {
                            if (data == true) {
                                layer.msg('实验记录数据成功！！', {icon: 1, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            }
                        }
                    });
                } else {
                    alert("前期实验数据录入失败！！！");
                    return;
                }

            }
        });


    });


});

function formartDate(dateTime) {
    var time = new Date(dateTime);
    var y = time.getFullYear();
    var m = time.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = time.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = time.getHours();
    h=h < 10 ? ('0' + h) : h;
    var minute = time.getMinutes();
    minute = minute < 10 ? ('0' + minute) : minute;

    return y + '-' + m + '-' + d+' '+h+':'+minute;
}
