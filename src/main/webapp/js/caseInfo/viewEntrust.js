var str;
var flag;
$(function () {
    layui.use(['layer', 'form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;



        //添加检材信息
        var that;
        $("button[name='addsamples']").on("click",function () {
            that =$(this);
            flag="add";
            $("#sampleForm").attr("action","addSampleInfo.html");
            $("#sampleForm").resetForm();//清空表单
            var caseBaseId=$(this).attr("caseId");
            $("#caseBaseId").val(caseBaseId);

            //检材编号 生成
           /* $("#sampleSn").val($("input[name='casesAcceptNumber']").val()+(parseInt($("#sampleCount").val())+1));*/
            var count=1;
            var index;
            $("#samInfoList").find("tr").each(function (i) {
                if($(this).hasClass("nullSamInfo")){
                    count=1;
                }else{
                    count++;
                }

            });
            if(count<10){
                index="0"+count.toString();
            }else{
                index=count.toString();
            }

            var majorCode = $("input[name='majorCode']").val();
            var casesAcceptNumber=$("input[name='casesAcceptNumber']").val();
            var amout=casesAcceptNumber.substring(casesAcceptNumber.length-5,casesAcceptNumber.length-1);
            var majorCodes = majorCode.substring(0, 1) + majorCode.substring(3, 2);
            var myDate = new Date();//获取系统当前时间
            var year = myDate.getYear(); //获取当前年份(2位)
            var year = year < 2000 ? year + 1900 : year
            var yy = year.toString().substr(2, 2);
            var sampleSn = majorCodes + yy + amout + index;
            $("#sams").val(sampleSn);
            //取得被鉴定人的信息
            $("#personship").empty();
            var newOption = '<option value=""></option>';
            $("#memberList").find("input[name='memberId']").each(function (){
                newOption += '<option value="' + $(this).val() + '">' + $(this).attr("memberName") + '</option>';
            });
            $("#personship").append(newOption);
            form.render();
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


        //删除检材信息
        $("tbody").on("click","a[name='deleteSampleBtn']",function () {
            var memberId=$(this).attr("memberId");
            var caseId=$(this).attr("caseId");
            var sampleId=$(this).attr("value");

            var sampleData={
                "memberId":memberId,
                "caseBaseId":caseId,
                "sampleId":sampleId
            }

            deleteSampleInfo(sampleData,$(this));
        });
        //删除检材信息
        function deleteSampleInfo(sampleData,ele){
            var caseBaseId = $("#caseBaseId").val();
            that=ele;
            $.ajax({
                url: baseurl + "/case/deleteSample.html",
                data: {"sampleData": JSON.stringify(sampleData)},
                dataType: "json",
                type: 'post',
                success: function (data) {
                    window.location.href = baseurl + "/case/editAdmissibleCase.html?caseId=" + caseBaseId + "&caseStatus=" + $("#caseStatus").val()+ "&isStaleDated=" + $("input[name='isStaleDated']").val();
                    //var tbody=that.parents("tbody");
                    //tbody.empty();
                    //var text="";
                    //if(data.length==0){
                    //    text+='<tr>';
                    //    text+='<td colspan="10" style="text-align: center;">';
                    //    text+='<img src="'+baseurl+'/img/noPage.png" alt="">';
                    //    text+='</td>';
                    //    text+='</tr>';
                    //}else{
                    //$.each(data,function (index,um) {
                    //    text+='<tr>';
                    //    text+='<td style="display: none">'+um.sampleId+'</td>';
                    //    text+='<td>'+um.sampleName+'</td>';
                    //    text+='<td>'+um.sampleTypeName+'</td>';
                    //    text+='<td>'+um.amount+'</td>';
                    //    text+='<td>'+um.measurementUnitName+'</td>';
                    //    text+='<td>'+um.kinshipName+'</td>';
                    //    text+='<td>'+formartDate(um.samplingTime)+'</td>';
                    //    text+='<td>'+um.samplePerson+'</td>';
                    //    text+='<td>'+um.samplingAddress+'</td>';
                    //    text+='<td>'+formartDate(um.receiveTime)+'</td>';
                    //    text+='<td>';
                    //    text+='<a class="layui-btn layui-btn-mini" lay-event="edit" name="editSampleBtn" value="'+um.sampleId+'">修改</a>';
                    //
                    //    text+=' <a class="layui-btn layui-btn-mini" lay-event="dele" name="deleteSampleBtn" caseId="'+um.caseBaseId+'"  memberId="'+um.memberId+'" value="'+um.sampleId+'">删除</a>';
                    //
                    //    text+='<button class="layui-btn layui-btn-normal" type="button" lay-event="view" onclick="viewphotoBtn(\''+um.sampleId+'\');" value="'+um.sampleId+'"> 查看照片</button>';
                    //    text+='</td>';
                    //    text+='</tr>';
                    //});
                    //}
                    //var index = layer.index;
                    //layer.close(index);
                    form.render();
                    //tbody.append(text);
                }
            });

        }



        //修改检材信息
        $("tbody").on("click","a[name='editSampleBtn']",function () {
            //取得被鉴定人的信息
            $("#personship").empty();
            var newOption = '<option value=""></option>';
            $("#memberList").find("input[name='memberId']").each(function (){
                newOption += '<option value="' + $(this).val() + '">' + $(this).attr("memberName") + '</option>';
            });
            $("#personship").append(newOption);
            modifySampleInfo($(this).attr("value"), $(this));
            form.render();
        })

        function modifySampleInfo(obj,ele){
            flag="modify";
            that =ele;
            $("#sampleForm").resetForm();//清空表单
            $("#sampleForm").attr("action","updateSampleInfo.html");
            $.ajax({
                url: baseurl + "/case/selectAdmissibleSample.html",
                data: {"sampleId": obj},
                dataType: "json",
                type: 'post',
                success: function (data) {
                    $("#sampleForm").find("input[name='sampleId']").val(data.dacasSampleInfo.sampleId);//检材id
                    $("#sampleForm").find("input[name='memberId']").val(data.dacasSampleInfo.memberId);
                    $("#sampleForm").find("input[name='caseBaseId']").val(data.dacasSampleInfo.caseBaseId);
                    $("#sampleForm").find("input[name='sampleSn']").val(data.dacasSampleInfo.sampleSn);
                    //亲缘关系
                    $("#kinship").val(data.dacasSampleInfo.kinship);
                    //被鉴定人信息
                    $("#personship").val(data.dacasSampleInfo.memberId);
                    $("#sampleName").val(data.dacasSampleInfo.sampleName);//检材名称
                    var code = $("input[name='majorCode']").val();
                    if("FYLC"===code){
                        //查找检材类别
                        $.ajax({
                            url: baseurl+"/selectLcMaterial.html?id=" + data.dacasSampleInfo.sampleName+"&code="+code,
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

                    $("#sampleType").val(data.dacasSampleInfo.sampleType);//检材类别
                    $("#amount").val(data.dacasSampleInfo.amount);//检材数量
                    $("#measurementUnit").val(data.dacasSampleInfo.measurementUnit);

                    $("#receiveTime").val(formartDate(data.dacasSampleInfo.receiveTime));//接收时间Format
                    $("#samplingTime").val(formartDate(data.dacasSampleInfo.samplingTime));//提取时间
                    $("#samplePerson").val(data.dacasSampleInfo.samplePerson);//采样人
                    $("#samplingAddress").val(data.dacasSampleInfo.samplingAddress);//采样地点

                    $("#collectionTime").val(formartDate(data.dacasSampleInfo.collectionTime));//采样时间
                    $("#saveState").val(data.dacasSampleInfo.saveState);
                    $("#siteSampling").val(data.dacasSampleInfo.siteSampling);
                    $("#homeSampling").val(data.dacasSampleInfo.homeSampling);
                    $("#takeSample").val(data.dacasSampleInfo.takeSample);
                    $("#remark").val(data.dacasSampleInfo.remark);
                    form.render();
                }
            });

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


        //保存检材信息
        $("button[name='saveSampleInfo']").on("click",function () {
            var sampleTypeName = $("#sampleType option:selected").text();
            $("#sampleTypeName").val(sampleTypeName);
            var kinshipName = $("#kinship option:selected").text();
            $("#kinshipName").val(kinshipName);
            var measurementUnitName = $("#measurementUnit option:selected").text();
            $("#measurementUnitName").val(measurementUnitName);
            $("#memberId").val($("#personship").val());


            $("#sampleForm").ajaxSubmit({
                type: "post",
                dataType: "json",
                data:"",
                success: function (data) {
                    var caseId= $("#caseBaseId").val();
                    window.location.href = baseurl + "/case/editAdmissibleCase.html?caseId=" + caseId + "&caseStatus=" + $("#caseStatus").val() + "&isStaleDated=" + $("input[name='isStaleDated']").val();

                }
            });

        });

        //新增被鉴定人信息
        $("button[name='addMemberInfo']").on("click",function () {
            //清空数据
            $("#memberForm").resetForm();//清空表单
            var caseId=this.value;//拿到案件的id
            $("button[name='addMember']").val(caseId);
            layer.open({
                type: 1,
                title: "添加被鉴定人",
                skin: 'layui-layer-molv',//layui-layer-lan    layui-layer-molv
                closeBtn: 2,
                area: ['970px', '400px'],
                shade: 0.8,
                id: 'LAY_lay',
                btnAlign: 'c',
                moveType: 0,
                content: $('#memberInfo')
            });

        });


        //普通图片上传
        var photoView = $('#memberPhoto')
            ,uploadInst = upload.render({
            elem: '#addPhoto'
            ,url:baseurl+ '/case/saveMemberPhoto.html'
            ,auto: false
            //,multiple: true
            ,bindAction: '#saveFhoto'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                this.data = {'photoId': $("#memberPhotoId").val()};
            }
            ,choose: function(obj){
                //预读本地文件示例，不支持ie8
                obj.preview(function(index, file, result){
                  $("#memberPhoto").attr('src', result); //图片链接（base64）)
                    form.render()
                });
            }
            ,done: function(res){
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
                return true;
            }
            ,error: function(){
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }
        });



        //添加被鉴定人信息操作

        $("button[name='addMember']").on("click",function () {
            var caseId=$(this).val();
            $("#memberForm").ajaxSubmit({
                type: "post",
                dataType: "json",
                data: {"caseId": caseId},
                success: function (data) {
                    if(data.success=="true"){
                        //成功 上传照片
                        var src=$("#memberPhoto").attr("src");
                        if(!src=="") {
                            $("#memberPhotoId").val(data.memberId);
                            $("#saveFhoto").click();
                            setTimeout("window.location.reload()",1000);
                            //window.location.href = baseurl + "/case/editAdmissibleCase.html?caseId=" + caseId + "&caseStatus=" + $("#caseStatus").val()+ "&isStaleDated=" + $("input[name='isStaleDated']").val();
                        }
                    }}
            });

        });

        $(document).on("click", ".removeChargeItem", function(){
            $(this).parents(".layui-row").remove();
        });

        var mark;
        $("button[name='addFiles']").on("click", function () {
            var id = $(this).val();
            $("#operateId").val(id);
            mark = $(this).attr("mark");
            //上传照片
            layer.open({
                type: 1
                ,
                title: "新增文件" //不显示标题栏
                ,
                skin: 'layui-layer-lan'//layui-layer-lan    layui-layer-molv
                , closeBtn: 1
                ,
                area: '950px'
                ,
                shade: 0.8
                ,
                anim: 5
                ,
                id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,
                btnAlign: 'c'
                ,
                moveType: 1 //拖拽模式，0或者1
                ,
                content: $('#caseFileAdd')
            });
        });


        //照片上传
        var map = {};
        var files;
        var demoListView = $('#demo3')
            , uploadListIns = upload.render({
            elem: '#test3'
            , url: baseurl + '/fileUtil/fileUpload.html'
            , multiple: true
            , accept: 'file'//可选值有：images（图片）、file（所有文件）、video（视频）、audio（音频）
            , auto: false
            , bindAction: '#addpapers'
            , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                $.each(files, function (key, values) {
                    var fileName = files[key].name;
                    map[fileName] = $("#fremark" + fileName.substring(0, fileName.indexOf("."))).val();
                });
                this.data = {'dataMap': JSON.stringify(map), 'id': $("#operateId").val(), 'flag': mark};
            }
            , choose: function (obj) {
                files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列 demo-delete
                obj.preview(function (index, file, result) {

                    var fileSuffix=file.name.substring(file.name.indexOf(".")+1,file.name.length);
                    var stuffText="";
                    if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                        stuffText='<img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style=" width: 200px;height: 170px;margin-bottom: 5px;">';
                    }else {
                        stuffText='<img src="../img/img/word.png" alt="' + file.name + '" class="layui-upload-img" style=" width: 200px;height: 170px;margin-bottom: 5px;"><p class="wordP" title="'+file.name+'">'+file.name+'</p>';
                    }

                    var text = $(['<span class="imgBox layui-form" style="width:200px;padding-left: 10px;margin-bottom: 20px">'
                        , '<i class="layui-icon demo-delete">&#x1007;</i>'
                        , stuffText
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
                demoListView.empty();
                var ul;
                if (mark == 1) {
                    //删除检材下面的照片
                    ul = "/case/findSampleFiles.html";
                } else if (mark == 2) {
                    ul = "/case/queryCaseFile.html";
                }
                $.ajax({
                    url: baseurl + ul,
                    type: "post",
                    dataType: "json",
                    data: {"id": $("#operateId").val()},
                    success: function (data) {
                        if (mark == 1) {
                            $("#viewPhotoInfo").empty();
                        } else if (mark == 2) {
                            $("#caseFile").empty();
                        }
                        var text = "";
                        $.each(data.files, function (index, item) {
                            text += '<div class="layui-inline" style="margin: 10px;">';
                            text += '<i class="layui-icon demo-delete" onclick="del(\'' + $("#operateId").val() + '\',\'' + item.id + '\',\'' + mark + '\')">&#x1007;</i>';
                            text += ' <span class="imgBox imgwh imbox" style="height: auto;">';

                            text+='<a href="'+ baseurl +'/case/downFile.html?filePath='+item.filePath+'&fileName='+item.fileName+'" class="doico"><img src="../img/img/icon/xiazai-yellow.png"></a>';

                            var fileSuffix=item.fileName.substring(item.fileName.indexOf(".")+1,item.fileName.length);
                            if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                                text += '<img src="' + baseurl + '/upload/viewPhoto.html?imgPath=' + item.filePath + '" style=" width: 200px;height: 170px;margin-bottom: 5px;" class="layui-upload-img">';
                            }else{
                                text+='<img src="../img/img/word.png" alt="' + item.fileName + '"  style=" width: 200px;height: 170px;margin-bottom: 5px;"  class="layui-upload-img"><p class="wordP" title="'+item.fileName+'">'+item.fileName+'</p>';
                            }
                            text += '<input type="text" class="layui-input" style="width: 200px;" value="' + (item.fileDepict == null ? "" : item.fileDepict) + '">';
                            text += '</span>';
                            text += ' </div>';
                        });
                        if (mark == 1) {
                            $("#viewPhotoInfo").append(text);
                        } else if (mark == 2) {
                            $("#caseFile").append(text);
                        }
                        var index = layer.index;
                        layer.msg('上传成功！！', {icon: 6, offset: '250px',time: 2000}, function(){
                            //icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                            layer.close(index);
                        });

                        /*  alert(obj.total+"总文件"); //得到总文件数
                         alert(obj.successful+"总成功数"); //请求成功的文件数
                         alert(obj.aborted+"失败"); //请求失败的文件数*/
                    }
                });
            }
        });

    });

});

//查看照片
function viewphotoBtn(sampleId){
    //var sampleId = $(this).attr("value");//拿到用户的id
    $("#addSampleFile").val(sampleId);
    //示范一个公告层
    layer.open({
        type: 1
        , title: "查看文件" //不显示标题栏
        , skin: 'layui-layer-lan'//layui-layer-lan    layui-layer-molv
        , closeBtn: 1
        , area: '970px'
        , shade: 0.8
        , anim: 5
        , id: 'LAY_lay' //设定一个id，防止重复弹出
        , btn: ['关闭']
        , btnAlign: 'c'
        , moveType: 1 //拖拽模式，0或者1
        , content: $('#viewPhoto')
        , success: function (layero) {

            //请求后台数据
            $.ajax({
                url: baseurl + "/case/findSampleFiles.html",
                type: "POST",
                dataType: 'json',
                data: {"id": sampleId},
                success: function (data) {
                    $("#viewPhotoInfo").empty();
                    var text = "";
                  //  var code = $("input[name='majorCode']").val();
                    $.each(data.files, function (index, item) {
                        text += '<div class="layui-inline" style="margin: 10px;">';
                        text += '<i class="layui-icon demo-delete" onclick="del(\'' + sampleId + '\',\'' + item.id + '\',1)">&#x1007;</i>';
                        text += ' <span class="imgBox imgwh imbox">';
                        text+='<a href="'+ baseurl +'/case/downFile.html?filePath='+item.filePath+'&fileName='+item.fileName+'" class="doico"><img src="../img/img/icon/xiazai-yellow.png"></a>';
                        var fileSuffix=item.fileName.substring(item.fileName.indexOf(".")+1,item.fileName.length);
                        if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                            text += '<img src="' + baseurl + '/upload/viewPhoto.html?imgPath=' + item.filePath + '" style=" width: 200px;height: 170px;margin-bottom: 5px;" class="layui-upload-img">';
                        }else{
                            text+='<img src="../img/img/word.png" alt="' + item.fileName + '"  style=" width: 200px;height: 170px;margin-bottom: 5px;"  class="layui-upload-img"><p class="wordP" title="'+item.fileName+'">'+item.fileName+'</p>';
                        }
                        text += '<input type="text" class="layui-input" style="width: 200px;" value="' + (item.fileDepict == null ? "" : item.fileDepict) + '">';
                        text += '</span>';
                        text += ' </div>';
                    });

                    $("#viewPhotoInfo").append(text);

                }
            });

        }
        , cancel: function (index, layero) {
            if (confirm('确定要关闭么')) { //只有当点击confirm框的确定时，该层才会关闭
                layer.close(index)
            }
            return false;
        }
    });
}

//删除检材或者案件下面的照片
    function del(objectId, id, flag) {
        var url;
        layer.alert('', {
            icon: 2, title: '删除确认', content: '您确定要删除这条记录吗？', closeBtn: 1
        }, function (index) {
            if (flag == 1) {
                //删除检材下面的照片
                url = "/deleteSampleFile.html";
            } else if (flag == 2) {
                url = "/case/deleteCaseFile.html";
            }
            $.ajax({
                url: baseurl + url,
                type: "post",
                dataType: "json",
                data: {"id": id, "objectId": objectId},
                success: function (data) {
                    if (data.msg == "true") {
                        layer.msg('删除文件成功！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        if (flag == 1) {
                            $("#viewPhotoInfo").empty();
                        } else if (flag == 2) {
                            $("#caseFile").empty();
                        }

                        var text = "";
                        $.each(data.files, function (index, item) {
                            text += '<div class="layui-inline" style="margin: 10px;">';
                            text += '<i class="layui-icon demo-delete" onclick="del(\'' + objectId + '\',\'' + item.id + '\',\'' + flag + '\')">&#x1007;</i>';
                            text += ' <span class="imgBox imgwh imbox" style="height: auto;">';
                            text+='<a href="'+ baseurl +'/case/downFile.html?filePath='+item.filePath+'&fileName='+item.fileName+'" class="doico"><img src="../img/img/icon/xiazai-yellow.png"></a>';
                            var fileSuffix=item.fileName.substring(item.fileName.indexOf(".")+1,item.fileName.length);
                            if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                                text += '<img src="' + baseurl + '/upload/viewPhoto.html?imgPath=' + item.filePath + '" style=" width: 200px;height: 170px;margin-bottom: 5px;" class="layui-upload-img">';
                            }else{
                                text+='<img src="../img/img/word.png" alt="' + item.fileName + '"  style=" width: 200px;height: 170px;margin-bottom: 5px;"  class="layui-upload-img"><p class="wordP" title="'+item.fileName+'">'+item.fileName+'</p>';
                            }
                            text += '<input type="text" class="layui-input" style="width: 200px;" value="' + (item.fileDepict == null ? "" : item.fileDepict) + '">';
                            text += '</span>';
                            text += ' </div>';
                        });
                        if (flag == 1) {
                            $("#viewPhotoInfo").append(text);
                        } else if (flag == 2) {
                            $("#caseFile").append(text);
                        }

                        layer.close(index);
                    } else {
                        layer.msg('删除文件失败！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        layer.close(index);
                    }
                }
            });
        });

    }






//删除意见书照片
function delSubFile(caseId, id) {
    var  url = "/case/deleteSubmissionFile.html";
    layer.alert('', {
        icon: 2, title: '删除确认', content: '您确定要删除这条记录吗？', closeBtn: 1
    }, function (index) {
        $.ajax({
            url: baseurl + url,
            type: "post",
            dataType: "json",
            data: {"caseId": caseId, "submissionFileId": id},
            success: function (data) {
                if (data.msg == "true") {
                    layer.msg('删除照片成功！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        $("#submissionFile").empty();
                    var text = "";
                    $.each(data.files, function (index, item) {
                        text += '<div class="layui-inline" style="margin: 10px;">';
                        text += '<i class="layui-icon demo-delete" onclick="delSubFile(\'' + caseId + '\',\'' + item.id + '\')">&#x1007;</i>';
                        text += ' <span class="imgBox imgwh imbox" style="height: auto;">';
                        text+='<a href="'+ baseurl +'/case/downFile.html?filePath='+item.filePath+'&fileName='+item.fileName+'" class="doico"><img src="../img/img/icon/xiazai-yellow.png"></a>';
                        var fileSuffix=item.fileName.substring(item.fileName.indexOf(".")+1,item.fileName.length);
                        if("png"===fileSuffix ||"jpg"===fileSuffix ||"jpeg"===fileSuffix ){
                            text += '<img src="' + baseurl + '/upload/viewPhoto.html?imgPath=' + item.filePath + '" style=" width: 200px;height: 170px;margin-bottom: 5px;" class="layui-upload-img">';
                        }else{
                            text+='<img src="../img/img/word.png" alt="' + item.fileName + '"  style=" width: 200px;height: 170px;margin-bottom: 5px;"  class="layui-upload-img"><p class="wordP" title="'+item.fileName+'">'+item.fileName+'</p>';
                        }
                        text += '<input type="text" class="layui-input" style="width: 200px;" value="' + (item.fileDepict == null ? "" : item.fileDepict) + '">';
                        text += '</span>';
                        text += ' </div>';
                    });
                    $("#submissionFile").append(text);

                    layer.close(index);
                } else {
                    layer.msg('删除照片失败！！', {icon: 6, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                    layer.close(index);
                }
            }
        });
    });

}





function formartDate(dateTime) {
    var time = new Date(dateTime);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    return y + "-" + m + "-" + d;
}