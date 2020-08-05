$(function () {

    //初始化表格
    initTab();
    //初始化检材数据
    var specimenNumber=$("#specimenNumber").val();
    init(specimenNumber);


    //返回list

    $("button[name='returnDirectList']").on("click",function(){
        window.location.href = baseurl + "/dnaDirectExpansion.html";
    });

});





function init(specimenNumber){
    $.ajax({
        url: baseurl + "/selectDirectInfo.html",
        type: "POST",
        dataType: 'json',
        data: {"specimenNumber":specimenNumber},
        success: function (data) {
            $.each(data.dacasDirectExpansionList, function (index, item) {
                $("tr:eq(" + item.rowNumber + ") td:eq(" + item.colNumber + ")").find("textarea").text(item.sampleSn);
                if(item.rowNumber==1 && item.colNumber==1){
                    $("tr:eq(" + item.rowNumber + ") td:eq(" + item.colNumber + ")").find("textarea").addClass("border-color");
                    //传数据到后台
                    $.ajax({
                        url: baseurl + "/querySampleInfo.html",
                        type: "POST",
                        dataType: 'json',
                        data: {"sampleSn": item.sampleSn},
                        success: function (data) {
                            if (data.msg == "success") {
                                //为检材进本信息赋值
                                $("#sampleNumber").val(data.result.sampleSn);
                                $("#sampleName").val(data.result.sampleName);

                                $("#holePosition").val(A_Hrr[item.rowNumber - 1] + item.colNumber);
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

            });
        },
        error: function (data) {
        }
    });
}
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