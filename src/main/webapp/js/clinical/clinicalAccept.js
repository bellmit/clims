$(function () {
    $("#clickOne2").on("click", function () {
        $("#content2").empty();
        $("#content2").html("dssadasd");

    });

    //状态切换卡
    $("li[name='staticClick']").on("click", function () {
        var flag = $(this).val();
        var conId = $(this).attr("otherName");
        alert($("#page").val());
        /*"/clinicalCase/acceptStatusList.html" + "?" + "page=" + $("#page").val() + "&" + "status=" + flag + "&" + $("#form").serialize()*/
        $.ajax({
            url: baseurl + "/clinicalCase/acceptStatusList.html" + "?"+ "status=" + flag + "&" + $("#form").serialize(),
            type: "get",
            dataType: "json",
            success: function (data) {
                $("#" + conId).empty();//清空状态下 内容
                //赋值
                $("#ytj").text(data.ytjCount);
                $("#jdz").text(data.jdzCount);
                $("#dsc").text(data.dscCount);
                $("#ysc").text(data.yscCount);
                $("#dfh").text(data.dfhCount);
                $("#yfh").text(data.yfhCount);
                $("#all").text(data.allCount);


                var text = "";
                text += '<div style="float: right">';
                text += '<div class="layui-inline">';
                text += '<label class="layui-form-label" style="padding: 9px 5px">总金额</label>';
                text += '<div class="layui-form-mid" style="font-size: 20px;margin-right: 0px;color: #cc0000;">￥</div>';
                text += '<div class="layui-input-inline">';
                text += '<input type="text" name="totalAmount" readonly="readonly" class="layui-input" value="' + data.totalAmount + '" style="width: 100px; background-color: #1E9FFF;"></div>';
                text += '<div class="layui-form-mid layui-word-aux" style="float: right;margin-right: 20px">元</div>';
                text += '</div>';
                text += '<div class="layui-btn-group demoTable layui-btn-normal" style="float: right">';
                text += '<button class="layui-btn layui-btn-normal" id="export" data-type="export">导出</button>';
                text += '</div>';
                text += '</div>';
                text += '<table class="layui-table" lay-size="sm">';
                text += '<thead>';
                text += '<tr>';
                text += '<th>序号</th>';
                text += '<th>案件编号</th>';
                text += '<th>鉴定项目</th>';
                text += '<th>鉴定时限</th>';
                text += '<th>被鉴定人</th>';
                text += '<th>委托方</th>';
                text += '<th>一鉴定人</th>';
                text += '<th>受理人</th>';
                text += '<th>受理时间</th>';
                text += '<th>金额</th>';
                text += '<th>操作</th>';
                text += '</tr>';
                text += '</thead>';
                text += '<tbody id="dacasAdmissibleAccountListBody">';

                $.each(data.dacasClinicalList, function (index, item) {
                    text += '<tr>';
                    text += '<td>' + (index+1) + '</td>';
                    text += '<td>'+item.entity.caseSn+'</td>';
                    text += '<td>'+item.entity.appraisalMajor+'</td>';
                    text += '<td>'+item.entity.appraisalLimit+'</td>';
                    text += '<td>'+item.memberInfo.name+'</td>';
                    text += '<td>'+item.entity.principalName+'</td>';
                    text += '<td>'+item.entity.appraiserOneName+'</td>';
                    text += '<td>'+item.entity.assigneeName+'</td>';
                    text += '<td>'+formartDate(item.entity.acceptAt)+'</td>';
                    text += '<td>'+item.dacasCaseCharge.chargeAmount+'</td>';
                    text += '<td>';
                    text += '<a href="viewClinicalCaseInfo.html?caseId='+item.entity.id+'" target="ifm" class="layui-btn layui-btn-sm">查看详情</a>';
                    text += '<input type="button" name="UpdateChargeBtn" data-url="<%=path%>/case/viewCharge.html?caseId=item.dacasCaseCharge.caseInfoId}"';
                    text += 'class="layui-btn layui-btn-warm layui-btn-sm" style="background-color: #FFB800;" value="更新财务信息">';
                    text += '</td>';
                });
                text += '</tbody>';
                text += '</table>';
                $("#" + conId).append(text);
            }
        });


    });




    function formartDate(dateTime) {
        var time = new Date(dateTime);
        var y = time.getFullYear();//年
        var m = time.getMonth() + 1;//月
        var d = time.getDate();//日
        return y + "-" + m + "-" + d;
    }
});