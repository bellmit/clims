$(function () {
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        //执行一个laydate实例
        $('.timeLay').each(function(){
            laydate.render({
                elem: this
                ,position: 'fixed'
                ,format: 'yyyy-MM-dd'
            });
        })
    });
    layui.use(['layer','form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;

        form.on('radio(filter)', function(data){
            console.log(data.elem); //得到radio原始DOM对象
            console.log(data.value); //被点击的radio的value值
            $.ajax({
                url:"getIdentificationProject.html?identificationCategoryId=" + data.value,
                type:"get",
                dataType:"json",
                success: function (data) {
                    $("#jdxm").empty();
                    var text="";
                    $.each(data ,function(index,item){
                        text+='<input type="checkbox" lay-filter="test" lay-skin="primary" name="" value="'+item.id+'" title="'+item.identificationProject+'">';
                    });
                    $("#jdxm").append(text);
                    form.render();
                }
            });
        });

        //添加鉴定要求
        $(".appraisalRequireinfo").click(function () {
            if ($(this).next().hasClass("up")) {
                $(".downBox").slideUp()
                $(this).next().removeClass("up");
            } else {
                $(".downBox").slideDown()
                $(this).next().addClass("up");
            }
        })
        $(".downBox").mouseleave(function () {
            $(this).slideUp()
            $(".appraisalRequireinfo").next().removeClass("up");
        })
        //鉴定项目添加到box
        form.on('checkbox(test)', function (data) {
            var obj = data.elem.title
            var obj1 = data.elem.value
            if (data.elem.checked == true) {
                $(".appraisalRequireinfo").append('<span class="spans" name="' + obj1 +
                    '">' + obj + '</span>')
                console.log($(".appraisalRequireinfo span[name='" + obj + "']"))
            } else {
                $(".appraisalRequireinfo span[name='" + obj + "']").remove();
            }
        });
        //下一步
        $(".next").click(function () {
            var str = $(this).val();
            if (nextStep(str) === "true") {
                $(".layui-tab-title").find(".layui-this").removeClass('layui-this').next().addClass(
                    "layui-this")
                $(".layui-show").removeClass('layui-show').next().addClass(
                    "layui-show")
            }
        })


        var map = {};
        var files;
        var count=0;

        $("#testListAction").on("click",function(){
            if(count==0){
                layer.msg('请先上传文件');
                return;
            }
        });
        var demoListView = $('#demoList')
            ,uploadListIns = upload.render({
            elem: '#testList'
            ,url:baseurl+ '/upload/fileUpload.html'
            ,accept: 'file'
            ,multiple: true
            ,auto: false
            ,number:0
            ,size: 0 //最大允许上传的文件大小kb
            ,method:'post'
            /*,acceptMime: 'image/!*'*/
            ,bindAction: '#testListAction'
            ,before: function(obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                if(count==0){
                    layer.msg('所有已选文件已上传成功！！');
                }else {
                    layer.load(); //上传loading
                }
                $.each(files,function(key,values){
                    var fileName=files[key].name;
                    map[fileName]=$("#fremark"+fileName.substring(0,fileName.indexOf("."))).val();
                });
                this.data={'dataMap': JSON.stringify(map),'caseId': $("#file_case_id").val()};
            }
            ,choose: function(obj){
                 files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                //读取本地文件
                obj.preview(function(index, file, result){
                    count++;
                    var tr = $(['<tr id="upload-'+ index +'">'
                        ,'<td>'+ file.name +'</td>'
                        ,'<td><img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img"></td>'
                        ,'<td><textarea style="width: 100%" id="fremark'+file.name.substring(0,file.name.indexOf("."))+'"></textarea></td>'
                        ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
                        ,'<td>等待上传</td>'
                        ,'<td>'
                        ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
                        ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
                        ,'</td>'
                        ,'</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function(){
                        obj.upload(index, file);
                    });

                    //删除
                    tr.find('.demo-delete').on('click', function(){
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });

                    demoListView.append(tr);
                });
            }
            ,done: function(res, index, upload){
                if(res.code == 0){ //上传成功
                    count--;
                    var tr = demoListView.find('tr#upload-'+ index)
                        ,tds = tr.children();
                    tds.eq(4).html('<span style="color: #5FB878;">上传成功</span>');
                    tds.eq(5).html(''); //清空操作
                    layer.closeAll('loading'); //关闭loading
                   /* tr.remove();*/
                    return delete this.files[index]; //删除文件队列已经上传成功的文件
                }
                this.error(index, upload);
                layer.closeAll('loading'); //关闭loading
            }
            ,error: function(index, upload){
                var tr = demoListView.find('tr#upload-'+ index)
                    ,tds = tr.children();
                tds.eq(4).html('<span style="color: #FF5722;">上传失败</span>');
                tds.eq(5).find('.demo-reload').removeClass('layui-hide'); //显示重传
                layer.closeAll('loading'); //关闭loading
            }
        });


        //照片上传
/*        var uploadListIns = upload.render({
            elem: '#test2',
            url: '/upload/',
            multiple: true,
            number:0,
            accept:'images',
            size: 0, //最大允许上传的文件大小kb
            method:'post',
            acceptMime: 'image/!*',
            drag:false,
            auto: false,
            bindAction: '#testListAction',
            choose: function (obj) {
                //预读本地文件示例，不支持ie8
                var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
                console.log(files)
                obj.preview(function (index, file, result) {
                    $('#demo2').append('<div class="imgbox"><img src="' +
                        result +
                        '" alt="' + file.name + '" index="' + index +
                        '" class="layui-upload-img uploadImg"><i class="layui-icon closeimg">&#x1007;</i> <input type="text" class="layui-input imginput">'
                    )
                    $(".uploadImg").unbind('click').click(function () {
                        if ($(this).css("width") == "92px") {
                            $(this).css({
                                "width": "800px",
                                "height": "800px",
                                "display": "inline-block"
                            })
                            $(this).siblings("input").css("width",
                                "800px")
                        } else {
                            $(this).css({
                                "width": "92px",
                                "height": "92px",
                                "display": "inline-block"
                            })
                            $(this).siblings("input").css("width",
                                "92px")
                        }
                    })
                    $(".closeimg").unbind('click').click(function () {
                        var inx = $(this).prev().attr("index")
                        delete files[inx];
                        uploadListIns.config.elem.next()[0].value =
                            ''
                        $(this).parent().remove();
                        console.log(files)
                    })
                });
            },
            done: function (res) {
                //如果上传失败
                if(res.code > 0){
                    return layer.msg('上传失败');
                }
            }
        });*/
        // 添加被鉴定人
        $(".addpeople").click(function () {
            $("#memberForm").resetForm();//重置表单数据
            $("#case_Id").val($("#addMemberInfo").val());
            //修改表单action
            $("#memberForm").attr('action', "appraiserInfo.html");
            layer.open({
                type: 1,
                title: "添加被鉴定人",
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'LAY_layuipro',
                btnAlign: 'c',
                moveType: 1,
                content: $('.addperson')
            });
        })
        //保存被鉴定人信息
        $("#saveMemberInfo").on("click", function () {
            var genderName = $("#gender option:selected").text();
            var nationName = $("#nation option:selected").text();
            var countryName = $("#country option:selected").text();
            var identityName = $("#identity option:selected").text();
            $("#gender_Name").val(genderName);
            $("#country_Name").val(countryName);
            $("#identity_Name").val(identityName);
            $("#nation_Name").val(nationName);
            $("#memberForm").ajaxSubmit({
                type: "POST",
                dataType: "json",
                data: {"memId": ""},
                success: function (e) {
                    if (e.success == "true") {
                        //添加鉴定人信息成功    进行加载显示鉴定人信息
                        layer.closeAll();
                        getMemberInfoList($("#addMemberInfo").val());
                    }

                }
            });
        });
        // 添加检材
        $(".addsamples").click(function () {
            var caseId=$("#sampleCase_Id").val();
            $("#sampleForm").resetForm();//重置表单数据
            $("#sampleCase_Id").val(caseId);
            //修改表单action
            $("#sampleForm").attr('action', baseurl+"/addDacasSample.html");
            layer.open({
                type: 1,
                title: "添加检材",
                closeBtn: 2,
                area: '800px;',
                shade: 0.8,
                id: 'LAY_layuipro',
                btnAlign: 'c',
                moveType: 1,
                content: $('.samples')
            });
        })
    });


    //下一步进行保存案件信息操作
    function nextStep(str) {
        var msg1 = "false";
        var url = "";
        switch (str) {
            case "1":

                $("#appraisalProject").val($("#jdlb").val());

                var spans = $("#jsxm > span");
                var appraisalMajorData = "";
                for (var i = 0; i < spans.length; i++) {
                    appraisalMajorData += $(spans[i]).attr("name");
                    if (i != spans.length - 1) appraisalMajorData += ',';
                }
                $("#appraisalMajor").val(appraisalMajorData);

                var id=$("#base_caseId").val();
                if(id!=""){
                    $("#myCaseForm").attr('action', "updateCurrencyCase.html");
                    $("#myCaseForm").ajaxSubmit({
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function (e) {
                            if (e== true) {
                                msg1 = "true";
                            } else {
                                alert("案件信息更新失败！！！");
                                return;
                            }
                        }
                    });
                }else{
                        //保存案件信息
                    $("#myCaseForm").ajaxSubmit({
                        type: "post",
                        async: false,
                        dataType: "json",
                        success: function (e) {
                            if (e.success == "true") {
                                $("#base_caseId").val(e.caseId);
                                $("#addMemberInfo").val(e.caseId);
                                $("#sampleCase_Id").val(e.caseId);
                                msg1 = e.success;
                            } else {
                                alert("案件信息录入失败！！！");
                                return;
                            }
                        }
                    });
                }
                break;
            case "2":
                msg1 = "true";
                break;
            case "3":
                 $("#file_case_id").val($("#sampleCase_Id").val());//案件的id
                msg1 = "true";
                break;
            case "4":
                msg1 = "true";
                break;
            default:
                return false;
        }
        return msg1;
    }

     //提交检材信息
    $("#addSampleInfo").on("click",function(){
        $("#sampleForm").ajaxSubmit({
            type: "post",
            async: false,
            dataType: "json",
            success: function (e) {
                if(e.success=="true"){
                    layer.close(layer.index);
                    //加载检材信息列表
                    getSampleInfoList($("#sampleCase_Id").val())
                }else{
                    alert("检材信息录入失败！！！");
                    return;
                }
            }
        });
    });

});


//检材信息列表数据
function getSampleInfoList(sampleCaseId){
    $.ajax({
        url :baseurl+"/queryDacasSampleList.html",
        type : "POST",
        dataType : 'json',
        data :{"caseId":sampleCaseId},
        success : function(data) {

            $("#sampleInfoList").empty();
            var  text="";
            $.each(data.dacasSampleList ,function(index,item){
                text+="<tr>";
                text+="<td>"+item.sampleId+"</td>";
                text+="<td>"+item.sampleName+"</td>";
                text+="<td>"+item.sampleTypeName+"</td>";
                text+="<td>"+item.status+"</td>";
                text+="<td>"+item.amount+"</td>";
                text+="<td>"+item.measurementUnitName+"</td>";
                text+="<td>"+formartDate(item.receiveTime)+"</td>";
                text+="<td>"+formartDate(item.samplingTime)+"</td>";
                text+="<td>"+item.remark+"</td>";
                text+="<td>";
                text+=" <button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"modifySample(\'"+item.sampleId+"\')\"><i class='layui-icon'></i>编辑</button>";
                text+="<button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"deleteSample(\'"+item.sampleId+"\')\"><i class='layui-icon'></i>删除</button>";
                text+=" </td>";
                text+=" </tr>";
            });
            $("#sampleInfoList").append(text);
        },
        error : function(data) {
        }
    });
}

function modifySample(sampleId) {
    //查一下库
    $.ajax({
        url: baseurl + "/modifySampleInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"sampleId": sampleId},
        success: function (data) {
            if (data.memberInfo != "") {
                $("#addsamples").click();
                //被鉴定人编辑页面填充数据
                $("#sampleId").val(data.sampleId);
                $("#sampleName").val(data.sampleName);
                $("#sampleType").val(data.sampleType);
                $("#status").val(data.status);
                $("#amount").val(data.amount);
                $("#measurementUnit").val(data.measurementUnit);
                $("#receiveTime").val(formartDate(data.receiveTime));
                $("#samplingTime").val(formartDate(data.samplingTime));
                $("#remark").val(data.remark);
                //修改表单action
                $("#sampleForm").attr('action', baseurl+"/submitModify.html");
            }
        },
        error: function (data) {
        }
    })
}


function deleteSample(sampleId){
    $.ajax({
        url:baseurl+ "/deleteSampleInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"sampleId": sampleId},
        success: function (data) {
            if (data == true) {
                getSampleInfoList($("#sampleCase_Id").val())
            } else {
                alert("删除检材信息报错！！！");
            }
        },
        error: function (data) {
        }
    });
}








//拿到被鉴定人列表
function getMemberInfoList(caseid) {
    $.ajax({
        url: "appraiserInfoList.html",
        type: "POST",
        dataType: 'json',
        data: {"caseId": caseid},
        success: function (data) {
            $("#memberInfoList").empty();
            var  text="";
            $.each(data ,function(index,item){
                text+="<tr>";
                text+="<td>"+item.memberSn+"</td>";
                text+="<td>"+item.name+"</td>";
                text+="<td>"+item.genderName+"</td>";
                text+="<td>"+item.idCard+"</td>";
                text+="<td>"+item.phone+"</td>";
                text+="<td>"+item.nationName+"</td>";
               text+="<td>"+item.address+"</td>";
                text+="<td>";
                text+=" <button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"modify(\'"+item.memberId+"\',\'"+caseid+"\')\"><i class='layui-icon'></i>编辑</button>";
                text+="<button class='layui-btn layui-btn-warm layui-btn-sm' onclick=\"deleteMember(\'"+item.memberId+"\',\'"+caseid+"\')\"><i class='layui-icon'></i>删除</button>";
                text+=" </td>";
                text+=" </tr>";
            });
            $("#memberInfoList").append(text);
        },
        error: function (data) {
        }
    });
}
//删除被鉴定人信息
function deleteMember(memberId, caseId) {
    $.ajax({
        url: "deleteAppraiserInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"memberId": memberId},
        success: function (data) {
            if (data == true) {
                getMemberInfoList(caseId);
            } else {
                alert("删除被鉴定人信息报错！！！");
            }
        },
        error: function (data) {
        }
    });
}


//编辑被鉴定人信息
function modify(memberId, caseId) {
//查一下库
    $.ajax({
        url: "modifyAppraiserInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"memId": memberId},
        success: function (data) {
            if (data.memberInfo != "") {
                $("#addMemberInfo").click();
                //被鉴定人编辑页面填充数据
                $("#memberSn").val(data.memberInfo.memberSn);
                $("#name").val(data.memberInfo.name);
                $("#gender").val(data.memberInfo.gender);
                $("#nation").val(data.memberInfo.nation);
                $("#birthdate").val(formartDate(data.memberInfo.birthdate));
                $("#country").val(data.memberInfo.country);
                $("#phone").val(data.memberInfo.phone);
                $("#job").val(data.memberInfo.job);
                $("#identity").val(data.memberInfo.identity);
                $("#idCard").val(data.memberInfo.idCard);
                $("#address").val(data.memberInfo.address);
                //修改表单action
                $("#memberForm").attr('action', "modifyAppraiserInfo.html");
                $("#member_Id").val(memberId);
                $("#addMemberInfo").val(caseId)
            }
        },
        error: function (data) {
        }
    });

}


function  formartDate(dateTime){
    var time= new Date(dateTime);
    var y = time.getFullYear();//年
    var m = time.getMonth() + 1;//月
    var d = time.getDate();//日
    return y+"-"+m+"-"+d;
}



