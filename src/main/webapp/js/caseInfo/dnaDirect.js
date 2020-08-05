$(function () {

    var jsonstr = "[]";
    var jsonarray = eval('(' + jsonstr + ')');
    //初始化表格
    initTab();
    layui.use(['layer', 'form', 'upload', 'laydate'], function () {
        //查询数据
        $("button[name='querySample']").on("click", function () {

            var sampleSn = $("#sampleSn").val();//获取检材编号
            if (sampleSn == "") {
                layer.msg('请输入检材编号！！', {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            //传数据到后台
            $.ajax({
                url: baseurl + "/querySampleInfo.html",
                type: "POST",
                dataType: 'json',
                data: {"sampleSn": sampleSn},
                success: function (data) {
                    if (data.msg == "success") {
                        var flag = true;
                        //动态为样板赋值
                        for (var i = 1; i < colNumber; i++) {
                            for (var j = 1; j < rowNumber; j++) {

                                if ($("tr:eq(" + j + ") td:eq(" + i + ")").find("textarea").val() == "") {
                                    $("textarea[class='border-color']").each(function () {
                                        $(this).removeClass("border-color");
                                    });
                                    $("tr:eq(" + j + ") td:eq(" + i + ")").find("textarea").addClass("border-color");
                                    $("tr:eq(" + j + ") td:eq(" + i + ")").find("textarea").text(sampleSn);
                                    //alert("第"+j+"行"+"第"+i+"列");
                                    //为检材进本信息赋值
                                    $("#sampleNumber").val(data.result.sampleSn);
                                    $("#sampleName").val(data.result.sampleName);

                                    $("#holePosition").val(A_Hrr[j - 1] + i);
                                    $("#hole_Position").val(A_Hrr[j - 1] + i);
                                    $("#sampleType").val(data.result.sampleTypeName);
                                    if (data.result.acceptStatus == "1") {
                                        $("#acceptStatus").val("已受理");
                                    }
                                    $("#remark").text(data.result.remark);

                                     var  sampleData = {
                                        "sampleSn": sampleSn,
                                        "colNumber": i,
                                        "rowNumber": j
                                    };
                                    //赋值给jsonarray
                                    jsonarray.push(sampleData);
                                    flag = false;
                                    return;
                                }
                            }
                        }
                        if (flag) {
                            alert("样板已满");
                        }
                    }else{
                        layer.msg(data.msg, {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                        return false;
                    }
                },
                error: function (data) {
                }
            });
        });


        //返回list

        $("button[name='returnDirectList']").on("click",function(){
            window.location.href = baseurl + "/dnaDirectExpansion.html";
        });


        //完成检验
        $("button[name='completeTest']").on("click",function(){

            if(jsonarray.length==0){
                layer.msg("请先添加要检验的检材！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var specimenNumber=$("input[name='specimenNumber']").val();
            if(specimenNumber==""){
                layer.msg("请输入样本编号！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }

            var specimenName=$("input[name='specimenName']").val();
            if(specimenName==""){
                layer.msg("请输入样本表名称！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var kits=$('#kits option:selected').val();//选中的值
            if(kits==0){
                layer.msg("请选择试剂盒！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var thermalExpansion=$('#thermalExpansion option:selected').val();//选中的值
            if(thermalExpansion==0){
                layer.msg("请选择扩增仪！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var sequencer=$('#sequencer option:selected').val();//选中的值
            if(sequencer==0){
                layer.msg("请选择测序仪！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var internalStandard=$("input[name='internalStandard']").val();
            if(internalStandard==""){
                layer.msg("请输入内标！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var aperture=$("input[name='aperture']").val();
            if(aperture==""){
                layer.msg("请输入孔径！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var temperature=$("input[name='temperature']").val();
            if(temperature==""){
                layer.msg("请输入温度！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var humidity=$("input[name='humidity']").val();
            if(humidity==""){
                layer.msg("请输入湿度！！！", {icon: 2, offset: '250px'});//icon 1:对号2：x号 3：？号 4：锁号 5：哭脸 6：笑脸
                return false;
            }
            var remark=$("#dnaRemark").val();

            var holeNumber= $("#hole_Position").val();

            var parameters = {
                "specimenNumber": specimenNumber,
                "specimenName":specimenName,
                "holeNumber":holeNumber,
                "kits": kits,
                "thermalExpansion": thermalExpansion,
                "sequencer": sequencer,
                "internalStandard": internalStandard,
                "aperture": aperture,
                "temperature": temperature,
                "humidity": humidity,
                "remark":remark
            }
            //进行保存直扩检验信息

            $.ajax({
                url: baseurl + "/saveDirectInfo.html",
                type: "POST",
                dataType: 'json',
                data: {"dataMap": JSON.stringify(jsonarray), "parameters": JSON.stringify(parameters)},
                success: function (data) {
                    if(data==true){
                        layer.msg('操作成功！！', {icon: 1, offset: '250px', time: 500}, function () {
                            window.location.href = baseurl + "/dnaDirectExpansion.html";
                        });
                    }

                },
                error: function (data) {
                }
            });




        });



    });
})

var colNumber = 13;//列数
var rowNumber = 9;//行数
var A_Hrr = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
function initTab() {
    $("#dnaTab").empty();
    var text = "";
    //-------表头
    text += '<table class="layui-table" lay-size="sm">';
    text += '<thead>';
    text += '<tr>';
    text += '<th></th>';
    for (var i = 0; i < colNumber - 1; i++) {
        text += ' <th>' + (i + 1) + '</th>';
    }
    text += '</tr>';
    text += '</thead>';
    //-------数据
    text += '<tbody>';
    for (var j = 0; j < rowNumber - 1; j++) {
        text += '<tr>';
        text += ' <td>' + A_Hrr[j] + '</td>';
        for (var k = 0; k < colNumber - 1; k++) {
            text += ' <td><textarea onclick="viewSampleInfo(this);" readonly="readonly">' + "" + '</textarea></td>';
        }
        text += '</tr>';
    }
    text += '</tbody>';
    text += ' </table>';
    $("#dnaTab").append(text);
}

function viewSampleInfo(object){

    $.ajax({
        url: baseurl + "/querySampleInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"sampleSn": $(object).text()},
        success: function (data) {
            if (data.msg == "success") {
                $("textarea[class='border-color']").each(function () {
                    $(this).removeClass("border-color");
                });
                $(object).addClass("border-color");
                //为检材进本信息赋值
                $("#sampleNumber").val(data.result.sampleSn);
                $("#sampleName").val(data.result.sampleName);

                /*   $("#holePosition").val(A_Hrr[item.rowNumber - 1] + item.colNumber);*/
                $("#sampleType").val(data.result.sampleTypeName);
                if (data.result.acceptStatus == "1") {
                    $("#acceptStatus").val("已受理");
                }
                $("#remark").text(data.result.remark);
            }
        },
        error: function (data) {
        }
    });

}