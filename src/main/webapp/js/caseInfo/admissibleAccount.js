$(function () {


    var jdlb=$("#jdlb").val();

   if(jdlb!=""){
       var id = jdlb;
       var genre = "";
       layui.use('form', function () {
           var form = layui.form;
           $.ajax({
               url: "getIdentificationProject.html?identificationCategoryId=" + id + "&&genre=" + genre,
               type: "get",
               dataType: "json",
               success: function (data) {
                   $("#appraisalMajor").empty();
                   var text = "";
                   text += '<option value=""></option>';

                   $.each(data.identificationProjectList, function (index, item) {
                       text += ' <option value="' + item.id + '">' + item.identificationProject + '</option>';
                   });

                   $("#appraisalMajor").append(text);

                   $("#appraisalMajor").val($("#jdxm").val());
                   form.render();
               }
           });
       });
   }




    //check:无法绑定事件
    /*$("input[type='radio'][name='PayMethod'][title='其他']").on("click", function () {
     alert(1);
     $("#PayMethodMsg").attr("disabled", false);
     });*/

    $("#exportAll").on("click", function () {
        $("#exporthide").submit();

        /*$.ajax({
         url: "exportToExcel.html",
         type: "POST",
         async: false,
         contentType: "application/json",
         data: JSON.stringify(getParams()),
         success: function (data) {

         layer.msg('操作成功', {icon: 6, offset: '250px', time: 1000});



         },
         error: function (data) {
         layer.msg('接口操作失败', {icon: 2, offset: '250px', time: 1000});
         }*/
    });

    function getParams() {
        var temp = {};

        temp.appraisalProject = $.trim($('#appraisalProject option:selected').val());
        temp.caseSn = $.trim($("#caseSn").val());
        temp.acceptAt = $.trim($("#acceptAt").val());
        temp.acceptAtEnd = $.trim($("#acceptAtEnd").val());
        temp.client = $.trim($("#client").val());


        return temp;
    }


    layui.use(['layer', 'form', 'upload'], function () {
        var layer = layui.layer,
            laydate = layui.laydate,
            form = layui.form;
        var $ = layui.jquery,
            upload = layui.upload;

        form.on('select(appraisalProject)', function (data) {
            var id = data.value;
            var genre = "";
            layui.use('form', function () {
                var form = layui.form;
                $.ajax({
                    url: "getIdentificationProject.html?identificationCategoryId=" + id + "&&genre=" + genre,
                    type: "get",
                    dataType: "json",
                    success: function (data) {
                        $("#appraisalMajor").empty();
                        var text = "";
                        text += '<option value=""></option>';

                        $.each(data.identificationProjectList, function (index, item) {
                            text += ' <option value="' + item.id + '">' + item.identificationProject + '</option>';
                        });

                        $("#appraisalMajor").append(text);
                        form.render();
                    }
                });
            });
        });


        $("input[name='UpdateChargeBtn']").on("click", function () {
            var url = $(this).attr("data-url");
            layer.open({
                type: 2,
                title: '更新财务信息',
                closeBtn: 2,
                area: ['800px', '580px'],
                shade: 0.8,
                id: 'ChargeLayUI',
                //btn: ['确定更新'],
                btnAlign: 'c',
                moveType: 1,
                content: url,
                yes: function (indexN, layero) {
                    /*$.ajax({
                     url: "UpdateCharge.html",
                     type: "POST",
                     async: false,
                     dataType: 'json',
                     contentType: "application/json",
                     data: JSON.stringify(getParams()),
                     success: function (data) {
                     if (data == true) {
                     layer.msg('操作成功', {icon: 6, offset: '250px', time: 1000}, function () {
                     layer.close(index); //如果设定了yes回调，需进行手工关闭
                     location.reload();
                     });
                     } else {
                     layer.msg('操作失败请重试', {icon: 5, offset: '250px', time: 1000}, function () {
                     layer.close(index); //如果设定了yes回调，需进行手工关闭
                     location.reload();
                     });
                     }
                     },
                     error: function (data) {
                     layer.msg('接口操作失败', {icon: 2, offset: '250px', time: 1000});
                     }
                     });*/

                },
                cancel: function (index, layero) {
                    layer.close(index);
                    return false;
                }

            });

            function getParams() {
                var Charge = {};

                Charge.payMethodMsg = $.trim($("#payMethodMsg").val());
                Charge.chargeAmount = $.trim($("#chargeAmount").val());
                Charge.caseInfoId = $.trim($("#dacasCaseChargeCaseInfoId").val());
                Charge.province = $.trim($("#RegionPickByProvince").val());
                Charge.city = $.trim($("#RegionPickByCity").val());
                Charge.county = $.trim($("#RegionPickByCounty").val());
                Charge.chargeType = $.trim($("input[type='radio'][name='ChargeType']:checked").val());
                Charge.payMethod = $.trim($("input[type='radio'][name='PayMethod']:checked").val());
                Charge.remark = $.trim($("#Remark").val());
                Charge.receiptTime = $.trim($("#ReceiptTime").val());
                Charge.invoiceTime = $.trim($("#InvoiceTime").val());
                Charge.invoiceName = $.trim($("#invoiceName").val());

                return Charge;
            }
        });


        form.render();


    });
});