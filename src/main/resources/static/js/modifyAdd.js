//现堪回显
$("#siteSurveyBox").on('show.bs.modal', function (e) {
    $("#siteSurveyBox").find("input").val($("button[data-target='#siteSurveyBox']").prev().html())
})
// 现堪修改关闭清空
$('#siteSurveyBox').on('hidden.bs.modal', function (e) {
    if ($(this).find("small").length > 0) {
        var form = $("#caseXkNoForm")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
    $("#siteSurveyBox").find("input").val("")
})
//委托书禁用
if ($("[name='consignmentNo']").val() == "委托书编号自动生成") {
    $("[name='consignmentNo']").prop("disabled", true)
}
if ($("#consignmentNos").val() == "委托书编号自动生成") {
    $("#consignmentNos").val("");
}
//委托书切换
$("[name='WtsNoBoxs']").click(function () {
    if ($(".starP").siblings(".form-group").find("small").length > 0) {
        var form = $("#saveform")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
    if ($("input[name='WtsNoBoxs']").is(":checked")) {
        $("input[name='WtsNoBox']").prop("checked", false)
        $("input[name='consignmentNo']").removeAttr("disabled");
        $("input[name='consignmentNo']").val("");
        $("input[name='consignmentNo']").focus();
    } else {
        $("input[name='consignmentNo']").val("委托书编号自动生成").attr("disabled", true);
    }
});
$("input[name='WtsNoBox']").click(function () {
    if ($(".starP").siblings(".form-group").find("small").length > 0) {
        var form = $("#saveform")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
    if ($("input[name='WtsNoBox']").is(":checked")) {
        $("input[name='WtsNoBoxs']").prop("checked", false)
        $("input[name='consignmentNo']").val("");
        $("input[name='consignmentNo']").attr("disabled", true);
        $("input[name='consignmentNo']").blur();
    } else {
        $("input[name='consignmentNo']").val("委托书编号自动生成").attr("disabled", true);
    }
});
//身份证验证正则
function isCardNo(card) {
    var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    return pattern.test(card);
}
//身份证验证
$("#addAuthenticatorBig").find("input[name='idCard']").blur(function () {
    if ($.trim($(this).val()).length > 0 && isCardNo($.trim($(this).val()))) {
        $(this).siblings("small").addClass("hidden")
        $("#verification").addClass("btn-blue").removeClass("btn-gray")
        if ($(this).val().length == 18) {
            if (Number($(this).val().substring(16, 17)) % 2 == 0) {
                $(".personBox").find("input[name='sex'][value='02']").prop("checked", true)
            } else {
                $(".personBox").find("input[name='sex'][value='01']").prop("checked", true)
            }
            var year = Number($(this).val().substring(6, 10))
            var myDate = new Date();
            var Nowyear = Number(myDate.getFullYear())
            $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year).change()
        }
        else if ($(this).val().length == 15) {
            if (Number($(this).val().substring(14, 15)) % 2 == 0) {
                $(".personBox").find("input[name='sex'][value='02']").prop("checked", true)
            } else {
                $(".personBox").find("input[name='sex'][value='01']").prop("checked", true)
            }
            var year = Number("19" + $(this).val().substring(6, 8))
            var myDate = new Date();
            var Nowyear = Number(myDate.getFullYear())
            $("#addAuthenticatorBig").find("input[name='year']").val(Nowyear - year).change()
        }
    } else {
        $("#verification").addClass("btn-gray").removeClass("btn-blue")
        $(this).siblings("small").removeClass("hidden")
    }
})
//被鉴定人添加样本
$("#addAuthenticatorBig").find("input[name='moveCheckbox']").change(function () {
    if ($(this).is(':checked')) {
        $("#addAuthenticatorBig").children().width(1200)
        $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
            "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
        }).fadeIn(800);
    } else {
        $("#addAuthenticatorBig").children().width(480)
        $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
            "display": "none",
        });
    }
})
//被鉴定人更多信息
$("#addAuthenticatorBig").find(".moveBtn").click(function () {
    if ($(this).html() == "添加更多") {
        $("#addAuthenticatorBig").find(".moveInput").removeClass("hidden")
        $(this).html("清除关闭")
    } else {
        $("#addAuthenticatorBig").find(".moveInput").addClass("hidden").find("input").val('')
        $(this).html("添加更多")
    }
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
        "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
    });
})
// 被鉴定人身份证切换
$("#addAuthenticatorBig").find("input[name='noIdCheck']").change(function () {
    if ($("#addAuthenticatorBig").find(".has-error").length > 0) {
        var form = $("#addAuthenticatorBig").find("form")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
    $(this).parents(".personBox").find("input[name='year']").val("")
    $(this).parents(".personBox").find("input[name='sex'][value='01']").prop("checked", true)
    if ($(this).is(":checked")) {
        $("#addAuthenticatorBig").find("input[name='idCard']").val("").prop("disabled", true).siblings("small").addClass("hidden")
        $("#addAuthenticatorBig").find("input[name='noIdCard']").val("").removeClass("hidden")
    } else {
        $("#addAuthenticatorBig").find("input[name='idCard']").val("").prop("disabled", false)
        $("#addAuthenticatorBig").find("input[name='noIdCard']").val("").addClass("hidden")
    }
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
        "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
    });
})
// 被鉴定人亲缘切换
$("#addAuthenticatorBig").find("select[name='personType']").change(function () {
    if ($(this).find("option:selected").text().indexOf("亲属") != -1) {
        var headerHtml = $(this).find("option:selected").text().substring(0, $(this).find("option:selected").text().indexOf("亲属"))
        $("#addAuthenticatorBig").find("select[name='kinship']").parents(".form-group").removeClass("hidden").find("label").html("与" + headerHtml + "亲缘关系")
    } else {
        $("#addAuthenticatorBig").find("select[name='kinship']").val("").parents(".form-group").addClass("hidden")
    }
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
        "height": $("#addAuthenticatorBig").find(".modal-content").eq(0).height() + 2,
    });
})
//被鉴定人照片添加
$(".addphoto").click(function () {
    $(this).siblings("input[type='file']").val("").click()
})
$(".photoFile").change(function (e) {
    var file = e.target.files[0];
    if (file.size / 1024 > 500) {
        $(this).siblings("span").removeClass("hidden")
        $(this).val("")
    } else {
        $(this).siblings("span").addClass("hidden")
        var reader = new FileReader();
        console.log(file.size / 1024)
        reader.readAsDataURL(file); // 读出 base64
        reader.onloadend = function () {
            var dataURL = reader.result;
            $("#personBase").val(dataURL)
        };
        $(this).siblings("button").html("修改")
        $(this).siblings("img").attr("src", URL.createObjectURL($(this)[0].files[0])).attr("alt", e.currentTarget.files[0].name);
    }

})
//添加被鉴定人
$("#openAddAuthenticatorBig").click(function () {
    $("#addAuthenticatorBig").find("input[name='moveCheckbox']").prop("checked", true)
    $("#addAuthenticatorBig").children().width(1200);
    $("#extract1Time").val(Format(new Date(),"yyyy-MM-dd"));
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css({
        "height": 542.6 + 2,
    }).fadeIn(800);
    $("#addAuthenticatorBig").modal("show")
})
//修改被鉴定人
$("#authenticatorTbody").on("click", ".edit", function () {
    $("#addAuthenticatorBig").find("input[name='moveCheckbox']").parents(".form-group ").addClass("hidden")
    var authenticatorTr = $(this).parents("tr"),
        authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(0),
        personType = authenticatorTr.find("input[name='personType']").val(),
        personName = authenticatorTr.find("input[name='personName']").val(),
        sex = authenticatorTr.find("input[name='sex']").val(),
        idCard = authenticatorTr.find("input[name='idCard']").val(),
        noIdCard = authenticatorTr.find("input[name='noIdCard']").val(),
        year = authenticatorTr.find("input[name='year']").val(),
        kinship = authenticatorTr.find("input[name='kinship']").val(),
        personCurrentAddress = authenticatorTr.find("input[name='personCurrentAddress']").val(),
        personHeight = authenticatorTr.find("input[name='personHeight']").val(),
        personWeight = authenticatorTr.find("input[name='personWeight']").val(),
        personBase = authenticatorTr.find("input[name='personBase']").val(),
        index = authenticatorTr.index();
    if (noIdCard) {
        authenticatorBox.find("input[type='checkbox']").prop("checked", true)
        authenticatorBox.find("input[name='noIdCard']").removeClass("hidden")
    }
    if (kinship) {
        authenticatorBox.find("select[name='kinship']").parents(".form-group ").removeClass("hidden")
    }

    if (personBase != "") {
        authenticatorBox.find("img").attr("src", personBase)
        authenticatorBox.find(".addphoto").html("修改")
    } else {
        authenticatorBox.find("img").attr("src", '/img/policeman.png')
        authenticatorBox.find(".addphoto").html("添加照片")
    }
    $("#personBase").val(personBase)
    authenticatorBox.find("select[name='personType']").val(personType)
    authenticatorBox.find("input[name='personName']").val(personName)
    authenticatorBox.find("input[name='sex'][value='" + sex + "']").prop("checked", true)
    authenticatorBox.find("input[name='idCard']").val(idCard)
    authenticatorBox.find("input[name='noIdCard']").val(noIdCard)
    authenticatorBox.find("input[name='year']").val(year)
    authenticatorBox.find("input[name='index']").val(index)
    authenticatorBox.find("input[name='personCurrentAddress']").val(personCurrentAddress)
    authenticatorBox.find("input[name='personHeight']").val(personHeight)
    authenticatorBox.find("input[name='personWeight']").val(personWeight)
    authenticatorBox.find("select[name='kinship']").val(kinship)
    $("#addAuthenticatorBig").modal('show')
})
//添加被鉴定人和样本信息
//全局变量标识
var CLICKTAG = 0;
$(".addAuthenticator").click(function () {

    if (CLICKTAG == 0) {
        CLICKTAG = 1;
        //this.disabled=true;
        $("button[name='qrinfoButton']").attr("disabled",true)
        // 等待2s后重置按钮可用
        setTimeout(function () { CLICKTAG = 0 ; $("button[name='qrinfoButton']").removeAttr("disabled")}, 2000);
    }

    $("#idCard").next().addClass("hidden")
    var form = $("#addAuthenticatorBig").find("form");
    if ($("#addAuthenticatorBig").find("input[name='noIdCheck']").is(":checked")) {
        form.bootstrapValidator({
            live: 'disabled',
            message: 'This value is not valid',
            fields: {
                year: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        },
                        regexp: {
                            regexp: /^[0-9]*$/,
                            message: '格式有误.'
                        },
                    },
                },
                noIdCard: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                personType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                personName: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                sampleType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                extractTime: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                kinship: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
            }
        });
    }
    else {
        form.bootstrapValidator({
            live: 'disabled',
            message: 'This value is not valid',
            fields: {
                year: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        },
                        regexp: {
                            regexp: /^[0-9]*$/,
                            message: '格式有误.'
                        },
                    },
                },
                idCard: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        },
                        regexp: {
                            regexp: /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,
                            message: '身份证输入有误'
                        },
                    }
                },
                personType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                personName: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                sampleType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                extractTime: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                kinship: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
            }
        });
    }
    form.bootstrapValidator('validate');
    if (form.data('bootstrapValidator').isValid()) {
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);

        var authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(0),
            personType = authenticatorBox.find("select[name='personType']").val(),
            personTypeName = authenticatorBox.find("select[name='personType']").find("option:selected").text(),
            personName = authenticatorBox.find("input[name='personName']").val(),
            sex = authenticatorBox.find("input[name='sex']:checked").val(),
            idCard = authenticatorBox.find("input[name='idCard']").val(),
            noIdCard = authenticatorBox.find("input[name='noIdCard']").val(),
            nowId = "",
            kinshipName = "",
            year = authenticatorBox.find("input[name='year']").val(),
            kinship = authenticatorBox.find("select[name='kinship']").val(),
            authenticatorIndex = authenticatorBox.find("input[name='index']").val(),
            personCurrentAddress = authenticatorBox.find("input[name='personCurrentAddress']").val(),
            personHeight = authenticatorBox.find("input[name='personHeight']").val(),
            personWeight = authenticatorBox.find("input[name='personWeight']").val(),
            personBase = $("#personBase").val(),
            timeId = new Date().getTime(),
            personLength = Number($("#authenticatorTbody").children().length) + 1;

        var sexName;
        if (sex == '01') {
            sexName = '男'
        } else if (sex == '02') {
            sexName = '女'
        }
        if (kinship == null) {
            kinshipName = ''
            kinship = ""
        } else {
            kinshipName = authenticatorBox.find("select[name='kinship']").find("option:selected").text()
        }
        if (noIdCard !== "") {
            nowId = '无身份证号(' + noIdCard + ')'
        } else {
            nowId = idCard
        }
        if (authenticatorIndex != "") {
            //修改被鉴定人
            var tr = $("#authenticatorTbody").children("tr").eq(authenticatorIndex)
            tr.find('input[name="personType"]').val(personType)
            tr.find('input[name="personTypeName"]').val(personTypeName)
            tr.find('input[name="personName"]').val(personName)
            tr.find('input[name="sexName"]').val(sexName)
            tr.find('input[name="sex"]').val(sex)
            tr.find('input[name="idCard"]').val(idCard)
            tr.find('input[name="noIdCard"]').val(noIdCard)
            tr.find('input[name="nowId"]').val(nowId)
            tr.find('input[name="year"]').val(year)
            tr.find('input[name="kinship"]').val(kinship)
            tr.find('input[name="kinshipName"]').val(kinshipName)
            tr.find('input[name="personCurrentAddress"]').val(personCurrentAddress)
            tr.find('input[name="personHeight"]').val(personHeight)
            tr.find('input[name="personWeight"]').val(personWeight)
            tr.find('input[name="personBase"]').val(personBase)
            if (tr.find("input[name='personId']").length) {
                $("#personSampleTbody").find("input[name='linkId'][value='" + tr.find("input[name='personId']").val() + "']").parents("tr").find("input[name='personName']").parent().html(personName + '<input type="hidden" name="personName" value="' + personName + '">')
            } else {
                $("#personSampleTbody").find("input[name='timeId'][value='" + tr.find("input[name='timeId']").val() + "']").parents("tr").find("input[name='personName']").parent().html(personName + '<input type="hidden" name="personName" value="' + personName + '">')
            }
            tr.children("td").each(function (i) {
                if (tr.children("td").length - 1 !== i) {
                    var tdInput = $(this).children("input").clone()
                    if (i == 5) {
                        $(this).html(nowId)
                    } else {
                        $(this).html(tdInput.val())
                    }
                    $(this).append(tdInput)
                }
            })
            $('#addAuthenticatorBig').modal('hide')
            return true;
        }
        else if (authenticatorBox.css("display") !== "none") {
            //新添被鉴定人
            var personTr = '<tr>'
            personTr += '<td>' + personLength + '</td>'
            personTr += '<td>' + personTypeName + '<input type="hidden" name="personTypeName" value="' + personTypeName + '"/></td>'
            personTr += '<td>' + personName + '<input type="hidden" name="personName" value="' + personName + '"/></td>'
            personTr += '<td>' + sexName + '<input type="hidden" name="sexName" value="' + sexName + '"/></td>'
            personTr += '<td>' + year + '<input type="hidden" name="year" value="' + year + '"/></td>'
            personTr += '<td>' + nowId + '<input type="hidden" name="idCard" value="' + idCard + '"/><input type="hidden" name="noIdCard" value="' + noIdCard + '"/></td>'
//                    personTr += '<td><div class="showPhoto"></div></td>'
            personTr += '<td>' + kinshipName + '<input type="hidden" name="kinshipName" value="' + kinshipName + '"/></td>'
            personTr += '<td>'
            personTr += '<button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑</button>'
            personTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
            personTr += '<input type="hidden" name="personType" value="' + personType + '"/>'
            personTr += '<input type="hidden" name="sex" value="' + sex + '"/>'
            personTr += '<input type="hidden" name="kinship" value="' + kinship + '"/>'
            personTr += '<input type="hidden" name="personCurrentAddress" value="' + personCurrentAddress + '"/>'
            personTr += '<input type="hidden" name="personHeight" value="' + personHeight + '"/>'
            personTr += '<input type="hidden" name="personWeight" value="' + personWeight + '"/>'
            personTr += '<input type="hidden" name="personBase" value="' + personBase + '"/>'
            personTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
            personTr += '</td>'
            personTr += '</tr>'
            $("#authenticatorTbody").append(personTr)
        }

        var personSampleBox = $("#addAuthenticatorBig").find(".modal-content").eq(1),
            sampleType = personSampleBox.find('select[name="sampleType"]').val(),
            sampleTypeName = personSampleBox.find('select[name="sampleType"]').find("option:selected").text(),
            sampleName = personSampleBox.find('input[name="sampleName"]').val(),
            samplePacking = personSampleBox.find('select[name="samplePacking"]').val(),
            samplePackingName = personSampleBox.find('select[name="samplePacking"]').find("option:selected").text(),
            extractTime = personSampleBox.find('input[name="extractTime"]').val(),
            extractMethod = personSampleBox.find('select[name="extractMethod"]').val(),
            sampleCarrier = personSampleBox.find('select[name="sampleCarrier"]').val(),
            inspectionObjective = personSampleBox.find('input[name="inspectionObjective"]').val(),
            sampleDescribe = personSampleBox.find('textarea[name="sampleDescribe"]').val(),
            sampleIndex = personSampleBox.find('input[name="index"]').val(),
            relationPersonName = personName,
            sampleBase = $("#sampleBase").val(),
            sampleImgSrc = [],
            sampleFileName = [];
        sampleImgSrc = sampleImgSrc.join(",")
        sampleFileName = sampleFileName.join(",")
        if (authenticatorBox.find("input[name='moveCheckbox']").is(':checked')) {
            //添加样本
            var sampleLength = Number($("#personSampleTbody").children().length) + 1
            var extractMethodBox = personSampleBox.find('select[name="extractMethod"]').parent().clone()
            var sampleCarrierBox = personSampleBox.find('select[name="sampleCarrier"]').parent().clone()
            extractMethodBox.find("select").val(extractMethod)
            sampleCarrierBox.find("select").val(sampleCarrier)
            var personSampleTr = '<tr>'
            personSampleTr += '<td>' + sampleLength + '</td>'
            personSampleTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"/></td>'
            personSampleTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"/></td>'
            personSampleTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"/></td>'
            personSampleTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"/></td>'
            personSampleTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"/></td>'
            personSampleTr += '<td></td>'
            personSampleTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"/></td>'
            personSampleTr += '<td>' + relationPersonName + '<input type="hidden" name="personName" value="' + relationPersonName + '"/></td>'
            personSampleTr += '<td>'
            personSampleTr += '<button class="btn-icon btn-icon-blue btn-icon-bianji-blue edit" type="button">编辑</button>'
            personSampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
            personSampleTr += '<input type="hidden" name="sampleType" value="' + sampleType + '"/>'
            personSampleTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '"/>'
            personSampleTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '"/>'
            personSampleTr += '<input type="hidden" name="sampleImgSrc" value="' + sampleImgSrc + '">'
            personSampleTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '"/>'
            personSampleTr += '<input type="hidden" name="sampleBase" value="' + sampleBase + '"/>'
            personSampleTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
            personSampleTr += '</td>'
            personSampleTr += '</tr>'
            $("#personSampleTbody").append(personSampleTr)
            $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(extractMethodBox)
            $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(sampleCarrierBox)
        }
        $('#addAuthenticatorBig').modal('hide')
    }
})
//人员样本照片添加
$("#addAuthenticatorBig").find(".addsamplePhoto").click(function () {
    $(this).siblings("input[type='file']").val("").click()
})
$("#addAuthenticatorBig").find(".samplePhoto").change(function (e) {
    var file = e.target.files[0];
    if (file.size / 1024 > 500) {
        $(this).parents(".samplePhotobox").siblings("span").removeClass("hidden")
        $(this).val("")
    } else {
        $(this).parents(".samplePhotobox").siblings("span").addClass("hidden")
        var reader = new FileReader();
        reader.readAsDataURL(file); // 读出 base64
        reader.onloadend = function () {
            var dataURL = reader.result;
            $("#sampleBase").val(dataURL)
        };
        $.each($(this)[0].files, function (i, item) {
            var newPhoto = '<div class="col-md-2"><img src="' + URL.createObjectURL(item) + '" alt="' + e.currentTarget.files[0].name + '"><i class="fa fa-times-circle removePhoto" aria-hidden="true"></i></div>'
            $("#addAuthenticatorBig").find(".addsamplePhoto").parent().before(newPhoto)
        })
    }
})
$("#addAuthenticatorBig").find(".samplePhotobox").on("click", ".removePhoto", function () {
    var that = $(this)
    $.each($("#addAuthenticatorBig").find(".samplePhoto")[0].files, function (i, item) {
        if (that.siblings('img').attr("src") == URL.createObjectURL(item)) {
            delete $("#addAuthenticatorBig").find(".samplePhoto")[0].files.i
        }
    })
    $("#sampleBase").val("")
    $(this).parents(".col-md-2").eq(0).remove()
})
//添加人员样本
$(".addsampleInfo").click(function () {
    if ($("#authenticatorTbody").children().length > 0) {
        $("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").removeClass('hidden')
        $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", 'none');
        $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", 'block').height("auto");
        $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").removeClass("hidden");
        $("#addAuthenticatorBig").find('select[name="relationPersonName"]').append("<option value=''>请选择关联人员</option>")
        $("#authenticatorTbody").children().each(function () {
            var newOption = '<option timeId="' + $(this).find("input[name='timeId']").val() + '"  linkId="' + $(this).find("input[name='personId']").val() + '" value="' + $(this).find("input[name='personTypeName']").val() + '">' + $(this).find("input[name='personName']").val() + '</option>'
            $("#addAuthenticatorBig").find('select[name="relationPersonName"]').append(newOption)
        })
        $("#addAuthenticatorBig").children().width(720)
        $("#addAuthenticatorBig").modal("show")
    } else {
        alert("请先添加被鉴定人")
    }
})
//修改人员样本
$("#personSampleTbody").on("click", ".edit", function () {
    $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", 'none');
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", 'block').height("auto");
    $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").removeClass("hidden");
    $("#addAuthenticatorBig").children().width(720)
    var authenticatorTr = $(this).parents("tr"),
        authenticatorBox = $("#addAuthenticatorBig").find(".modal-content").eq(1),
        sampleType = authenticatorTr.find("input[name='sampleType']").val(),
        sampleName = authenticatorTr.find("input[name='sampleName']").val(),
        samplePacking = authenticatorTr.find("input[name='samplePacking']").val(),
        extractTime = authenticatorTr.find("input[name='extractTime']").val(),
        extractMethod = authenticatorTr.find("input[name='extractMethod']").val(),
        sampleCarrier = authenticatorTr.find("input[name='sampleCarrier']").val(),
        inspectionObjective = authenticatorTr.find("input[name='inspectionObjective']").val(),
        sampleBase = authenticatorTr.find("input[name='sampleBase']").val(),
        sampleDescribe = authenticatorTr.find("input[name='sampleDescribe']").val(),
        sampleIndex = authenticatorTr.index();

    authenticatorBox.find("select[name='sampleType']").val(sampleType)
    authenticatorBox.find("input[name='sampleName']").val(sampleName)
    authenticatorBox.find("select[name='samplePacking']").val(samplePacking)
    authenticatorBox.find("input[name='extractTime']").val(extractTime)
    authenticatorBox.find("select[name='extractMethod']").find("option[value='" + extractMethod + "']").prop("selected", true)
    authenticatorBox.find("select[name='sampleCarrier']").find("option[value='" + sampleCarrier + "']").prop("selected", true)
    authenticatorBox.find("input[name='inspectionObjective']").val(inspectionObjective)
    authenticatorBox.find("textarea[name='sampleDescribe']").val(sampleDescribe)
    if (sampleBase) {
        var imgBox = ' <div class="col-md-2">'
        imgBox += '<img class="sampleDnaPicture" src="' + sampleBase + '" alt="">'
        imgBox += '<i class="fa fa-times-circle removePhoto" aria-hidden="true"></i>'
        imgBox += ' </div>'
    }
    $("#addAuthenticatorBig").find(".samplePhotobox").children().eq(0).before(imgBox)
    $("#sampleBase").val(sampleBase)
    authenticatorBox.find("input[name='index']").val(sampleIndex)
    $("#addAuthenticatorBig").modal('show')
})
//保存人员样本

//全局变量标识
var CLICKTAG1 = 0;
$(".addSampleBtn").click(function () {
    if (CLICKTAG1 == 0) {
        CLICKTAG1 = 1;
        //this.disabled=true;
        $("button[name='addSampButton']").attr("disabled",true)
        // 等待2s后重置按钮可用
        setTimeout(function () { CLICKTAG1 = 0 ; $("button[name='addSampButton']").removeAttr("disabled")}, 2000);
    }
    var form = $("#addAuthenticatorBig").find("form");
    form.bootstrapValidator({
        live: 'disabled',
        message: 'This value is not valid',
        fields: {
            relationPersonName: {
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
            sampleType: {
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
            extractTime: {
                trigger: 'change',
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
        }
    });
    form.bootstrapValidator('validate');
    if (form.data('bootstrapValidator').isValid()) {
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
        var personSampleBox = $("#addAuthenticatorBig").find(".modal-content").eq(1),
            sampleType = personSampleBox.find('select[name="sampleType"]').val(),
            sampleTypeName = personSampleBox.find('select[name="sampleType"]').find("option:selected").text(),
            sampleName = personSampleBox.find('input[name="sampleName"]').val(),
            samplePacking = personSampleBox.find('select[name="samplePacking"]').val(),
            samplePackingName = personSampleBox.find('select[name="samplePacking"]').find("option:selected").text(),
            extractTime = personSampleBox.find('input[name="extractTime"]').val(),
            extractMethod = personSampleBox.find('select[name="extractMethod"]').val(),
            sampleCarrier = personSampleBox.find('select[name="sampleCarrier"]').val(),
            inspectionObjective = personSampleBox.find('input[name="inspectionObjective"]').val(),
            sampleDescribe = personSampleBox.find('textarea[name="sampleDescribe"]').val(),
            sampleIndex = personSampleBox.find('input[name="index"]').val(),
            relationPersonName = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").text(),
            timeId = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").attr("timeId"),
            linkId = personSampleBox.find('select[name="relationPersonName"]').find("option:selected").attr("linkId"),
            sampleBase = $("#sampleBase").val();

        if (sampleIndex != "") {
            var tr = $("#personSampleTbody").children("tr").eq(sampleIndex)
            tr.find('input[name="sampleType"]').val(sampleType)
            tr.find('input[name="sampleTypeName"]').val(sampleTypeName)
            tr.find('input[name="sampleName"]').val(sampleName)
            tr.find('input[name="samplePacking"]').val(samplePacking)
            tr.find('input[name="samplePackingName"]').val(samplePackingName)
            tr.find('input[name="extractTime"]').val(extractTime)
            tr.find('input[name="extractMethod"]').val(extractMethod)
            tr.find('input[name="sampleCarrier"]').val(sampleCarrier)
            tr.find('input[name="inspectionObjective"]').val(inspectionObjective)
            tr.find('input[name="sampleDescribe"]').val(sampleDescribe)
            tr.find('input[name="sampleBase"]').val(sampleBase)
            tr.children("td").each(function (i) {
                if (tr.children("td").length - 1 !== i) {
                    if (i == 6) {
                        $(this).find("select[name='extractMethod']").val(extractMethod)
                        $(this).find("select[name='sampleCarrier']").val(sampleCarrier)
                    }
                    var tdInput = $(this).children("input").clone()
                    $(this).html(tdInput.val())
                    $(this).append(tdInput)
                }
            })
        } else {
            var sampleLength = Number($("#personSampleTbody").children().length) + 1
            var extractMethodBox = personSampleBox.find('select[name="extractMethod"]').parent().clone()
            var sampleCarrierBox = personSampleBox.find('select[name="sampleCarrier"]').parent().clone()
            extractMethodBox.find("select").val(extractMethod)
            sampleCarrierBox.find("select").val(sampleCarrier)
            var personSampleTr = '<tr>'
            personSampleTr += '<td>' + sampleLength + '</td>'
            personSampleTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"/></td>'
            personSampleTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"/></td>'
            personSampleTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"/></td>'
            personSampleTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"/></td>'
            personSampleTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"/></td>'
            personSampleTr += '<td></td>'
            personSampleTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"/></td>'
            personSampleTr += '<td>' + relationPersonName + '<input type="hidden" name="personName" value="' + relationPersonName + '"/></td>'
            personSampleTr += '<td>'
            personSampleTr += '<button class="btn-icon btn-icon-blue btn-icon-bianji-blue edit" type="button">编辑</button>'
            personSampleTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
            personSampleTr += '<input type="hidden" name="sampleType" value="' + sampleType + '"/>'
            personSampleTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '"/>'
            personSampleTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '"/>'
            personSampleTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '"/>'
            personSampleTr += '<input type="hidden" name="sampleBase" value="' + sampleBase + '"/>'
            personSampleTr += '<input type="hidden" name="linkId" value="' + linkId + '"/>'
            personSampleTr += '<input type="hidden" name="timeId" value="' + timeId + '"/>'
            personSampleTr += '</td>'
            personSampleTr += '</tr>'
            $("#personSampleTbody").append(personSampleTr)
            $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(extractMethodBox)
            $("#personSampleTbody").children().eq(sampleLength - 1).find("td").eq(6).append(sampleCarrierBox)
        }
        $('#addAuthenticatorBig').modal('hide')
    }
})
//人员样本名称关联
$("#addAuthenticatorBig").find(".sampleBox").find("#sampleType").change(function () {
    if ($("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").hasClass("hidden")) {
        var values = $("#addAuthenticatorBig").find("#personName").val() + $(this).children(":selected").html()
        $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val(values)
        $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
        if ($("#personType").val()) {
            $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val($("#personType").children(":selected").html() + values)
        }
    } else {
        var values = $("#addAuthenticatorBig").find('select[name="relationPersonName"]').find("option:selected").text() + $(this).children(":selected").html()
        $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val(values)
        $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val($("#addAuthenticatorBig").find('select[name="relationPersonName"]').val() + values)
    }

})
$("#addAuthenticatorBig").find("#personType").change(function () {
    var values = $(this).children(":selected").html() + $("#personName").val()
    if ($("#sampleType").val()) {
        $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values + $("#sampleType").children(":selected").html())
    }
})
$("#addAuthenticatorBig").find("#personName").change(function () {
    var values = $(this).val()
    if ($("#personType").children(":selected").html() !== "请选择人员类型") {
        values = $("#personType").children(":selected").html() + values
    }
    if ($("#sampleType").children(":selected").html() !== "请选择样本类型") {
        values = values + $("#sampleType").children(":selected").html()
        $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).val() + $("#sampleType").children(":selected").html())
    }
    $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
})
$("#addAuthenticatorBig").find('select[name="relationPersonName"]').change(function () {
    var values = $(this).val() + $(this).find("option:selected").text()
    if ($("#sampleType").children(":selected").html() !== "请选择样本类型") {
        $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).find("option:selected").text() + $("#sampleType").children(":selected").html())
        $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values + $("#sampleType").children(":selected").html())
    } else {
        $("#addAuthenticatorBig").find(".sampleBox").find("input[name='sampleName']").val($(this).find("option:selected").text())
        $("#addAuthenticatorBig").find(".sampleBox").find("textarea[name='sampleDescribe']").val(values)
    }
})
$("#addAuthenticatorBig").on('show.bs.modal', function (e) {
    if ($(this).find("input[name='noIdCheck']").is(":checked")) {
        $("#addAuthenticatorBig").find("input[name='idCard']").prop("disabled", true).siblings("small").addClass("hidden")
        $("#addAuthenticatorBig").find("input[name='noIdCard']").removeClass("hidden")
    } else {
        $("#addAuthenticatorBig").find("input[name='idCard']").prop("disabled", false)
        $("#addAuthenticatorBig").find("input[name='noIdCard']").addClass("hidden")
    }
})
//表格下拉框修改保存
$("body").on("change", "select", function () {
    $(this).parents("tr").find("input[name='" + $(this).attr("name") + "']").val($(this).val())
})
//被鉴定人样本关闭清空
$("#addAuthenticatorBig").on('hidden.bs.modal', function (e) {
    if ($("#addAuthenticatorBig").find(".has-error").length > 0) {
        var form = $("#addAuthenticatorBig").find("form")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
    $("#addAuthenticatorBig").find('select[name="relationPersonName"]').children().remove()
    $("#addAuthenticatorBig").find('select[name="relationPersonName"]').parents(".col-md-6").addClass('hidden')
    $("#addAuthenticatorBig").find("input[type='text']").val("")
    $("#addAuthenticatorBig").find("input[type='hidden']").val("")
    $("#addAuthenticatorBig").find(".modal-content").eq(1).find("input[name='inspectionObjective']").val("")
    $("#addAuthenticatorBig").find("input[type='radio'][value='01']").prop("checked", true)
    $("#addAuthenticatorBig").find("input[type='checkbox']:checked").prop("checked", false)
    $("#addAuthenticatorBig").find("input[name='idCard']").prop("disabled", false)
    $("#addAuthenticatorBig").find("input[name='noIdCard']").addClass("hidden")
    $("#photoFile").val("")
    $("#addAuthenticatorBig").find("select").val("")
    $("#addAuthenticatorBig").find("textarea").val("")
    $("#addAuthenticatorBig").find("select[name='kinship']").parents(".form-group").addClass("hidden")
    $("#addAuthenticatorBig").find(".moveInput").addClass("hidden")
    $("#addAuthenticatorBig").find(".modal-content").eq(0).css("display", "block")
    $("#addAuthenticatorBig").find(".modal-content").eq(1).css("display", "none")
    $("#addAuthenticatorBig").find(".modal-content").eq(0).find("img").attr("src", '/img/policeman.png').attr("alt","")
    $("#addAuthenticatorBig").find("input[name='moveCheckbox']").parents(".form-group ").removeClass("hidden")
    $("#addAuthenticatorBig").find(".modal-content").eq(1).find(".modal-footer").addClass("hidden")
    $("#addAuthenticatorBig").find(".moveBtn").html("添加更多")
    $("#addAuthenticatorBig").find(".addphoto").html("添加照片")
    $("#addAuthenticatorBig").find("select[name='samplePacking']").val("01")
    $("#addAuthenticatorBig").find("select[name='extractMethod']").val("01")
    $("#addAuthenticatorBig").find("select[name='sampleCarrier']").val("01")
    $("#addAuthenticatorBig").find("input[name='inspectionObjective']").val("DNA检验")
    $("#addAuthenticatorBig").find(".addsamplePhoto").parent().siblings().remove()
    $("#addAuthenticatorBig").find("input[name='idCard']").siblings().addClass("hidden")
    $(".samplePhoto").parents(".samplePhotobox").siblings("span").addClass("hidden")
    $(".photoFile").siblings("span").addClass("hidden")
    $("#addAuthenticatorBig").children().width(480)
})
//检材照片添加
$("#materialEvidencerlSampleBox").find(".addsamplePhoto").click(function () {
    $(this).siblings("input[type='file']").val("").click()
})
$("#materialEvidencerlSampleBox").find(".samplePhoto").change(function (e) {
    var file = e.target.files[0];
    if (file.size / 1024 > 500) {
        $(this).parents(".samplePhotobox").siblings("span").removeClass("hidden")
        $(this).val("")
    } else {
        $(this).parents(".samplePhotobox").siblings("span").addClass("hidden")
        var reader = new FileReader();
        reader.readAsDataURL(file); // 读出 base64
        reader.onloadend = function () {
            var dataURL = reader.result;
            $("#samplePicture").val(dataURL)
        };
        $.each($(this)[0].files, function (i, item) {
            var newPhoto = '<div class="col-md-2"><img src="' + URL.createObjectURL(item) + '" alt="' + e.currentTarget.files[0].name + '"><i class="fa fa-times-circle removePhoto" aria-hidden="true"></i></div>'
            $("#materialEvidencerlSampleBox").find(".addsamplePhoto").parent().before(newPhoto)
        })
    }
})
$("#materialEvidencerlSampleBox").find(".samplePhotobox").on("click", ".removePhoto", function () {
    var that = $(this)
    $.each($("#materialEvidencerlSampleBox").find(".samplePhoto")[0].files, function (i, item) {
        if (that.siblings('img').attr("src") == URL.createObjectURL(item)) {
            delete  $("#materialEvidencerlSampleBox").find(".samplePhoto")[0].files.i
        }
    })
    $("#samplePicture").val("")
    $(this).parents(".col-md-2").eq(0).remove()
})
//添加检材信息
$(".addMaterialEvidencer").click(function () {
    $("#materialEvidencerlSampleBox").find("select[name='extractMethod']").val("01")
    $("#materialEvidencerlSampleBox").find("select[name='sampleCarrier']").val("01")
    $("#materialEvidencerlSampleBox").find("select[name='samplePacking']").val("01")
    $("#materialEvidencerlSampleBox").find("input[name='inspectionObjective']").val("DNA检验")
    $("#extract2Time").val(Format(new Date(),"yyyy-MM-dd"));
    $("#materialEvidencerlSampleBox").modal('show')
})
// 修改检材信息
$("#materialEvidencerTbody").on("click", ".edit", function () {
    var sampleTypeName = $(this).parents("tr").find('input[name="sampleTypeName"]').val(),
        sampleName = $(this).parents("tr").find("input[name='sampleName']").val(),
        samplePackingName = $(this).parents("tr").find('input[name="samplePackingName"]').val(),
        extractTime = $(this).parents("tr").find('input[name="extractTime"]').val(),
        extractMethod = $(this).parents("tr").find('input[name="extractMethod"]').val(),
        sampleCarrier = $(this).parents("tr").find('input[name="sampleCarrier"]').val(),
        inspectionObjective = $(this).parents("tr").find('input[name="inspectionObjective"]').val(),
        evidenceNo = $(this).parents("tr").find('input[name="evidenceNo"]').val(),
        samplePicture = $(this).parents("tr").find('input[name="samplePicture"]').val(),
        index = $(this).parents("tr").index(),
        sampleDescribe = $(this).parents("tr").find('input[name="sampleDescribe"]').val();

    if (sampleTypeName !== "") {
        $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option:contains('" + sampleTypeName + "')").prop("selected", true)
    } else {
        $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option[value='']").prop("selected", true)
    }
    if (samplePackingName !== "") {
        $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option:contains('" + samplePackingName + "')").prop("selected", true)
    } else {
        $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option[value='']").prop("selected", true)
    }
    if (extractMethod !== "") {
        $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(extractMethod)
    } else {
        $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').children("option[value='']").prop("selected", true)
    }
    if (sampleCarrier !== "") {
        $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(sampleCarrier)
    } else {
        $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').children("option[value='']").prop("selected", true)
    }
    if (samplePicture) {
        var imgBox = ' <div class="col-md-2">'
        imgBox += '<img class="sampleDnaPicture" src="' + samplePicture + '" alt="">'
        imgBox += '<i class="fa fa-times-circle removePhoto" aria-hidden="true"></i>'
        imgBox += ' </div>'
        $("#materialEvidencerlSampleBox").find(".samplePhotobox").children().eq(0).before(imgBox)
    }
    $("#materialEvidencerlSampleBox").find('input[name="extractMethod"]').val(extractMethod)
    $("#materialEvidencerlSampleBox").find('input[name="sampleCarrier"]').val(sampleCarrier)
    $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(extractMethod)
    $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(sampleCarrier)
    $("#materialEvidencerlSampleBox").find('input[name="parentIndex"]').val("")
    $("#materialEvidencerlSampleBox").find('input[name="evidenceNo"]').val(evidenceNo)
    $("#materialEvidencerlSampleBox").find('input[name="sampleName"]').val(sampleName)
    $("#materialEvidencerlSampleBox").find('input[name="extractTime"]').val(extractTime)
    $("#materialEvidencerlSampleBox").find('input[name="inspectionObjective"]').val(inspectionObjective)
    $("#materialEvidencerlSampleBox").find('textarea[name="sampleDescribe"]').val(sampleDescribe)
    $("#materialEvidencerlSampleBox").find('input[name="index"]').val(index)
    $("#samplePicture").val(samplePicture)
    $("#materialEvidencerlSampleBox").modal('show')
})
//保存检材信息
//全局变量标识
var CLICKTAG2 = 0;
$('.addMaterialEvidencerlSample').click(function () {
    if (CLICKTAG2 == 0) {
        CLICKTAG2 = 1;
        //this.disabled=true;
        $("button[name='wzSampleButton']").attr("disabled",true)
        // 等待2s后重置按钮可用
        setTimeout(function () { CLICKTAG2 = 0 ; $("button[name='wzSampleButton']").removeAttr("disabled")}, 2000);
    }
    var sampleTypeName = $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').children("option:selected").html(),
        sampleType = $("#materialEvidencerlSampleBox").find('select[name="sampleType"]').val(),
        sampleName = $("#materialEvidencerlSampleBox").find('input[name="sampleName"]').val(),
        samplePackingName = $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').children("option:selected").html(),
        samplePacking = $("#materialEvidencerlSampleBox").find('select[name="samplePacking"]').val(),
        extractTime = $("#materialEvidencerlSampleBox").find('input[name="extractTime"]').val(),
        extractMethod = $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').val(),
        sampleCarrier = $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').val(),
        inspectionObjective = $("#materialEvidencerlSampleBox").find('input[name="inspectionObjective"]').val(),
        sampleDescribe = $("#materialEvidencerlSampleBox").find('textarea[name="sampleDescribe"]').val(),
        parentIndex = Number($("#materialEvidencerlSampleBox").find('input[name="parentIndex"]').val()),
        samplePicture = $("#samplePicture").val(),
        evidenceNo = $("#materialEvidencerlSampleBox").find('input[name="evidenceNo"]').val(),
        length = $("#materialEvidencerTbody").children().length + 1,
        index = $("#materialEvidencerlSampleBox").find('input[name="index"]').val();


    var extractMethodBox = $("#materialEvidencerlSampleBox").find('select[name="extractMethod"]').parent().clone()
    var sampleCarrierBox = $("#materialEvidencerlSampleBox").find('select[name="sampleCarrier"]').parent().clone()
    extractMethodBox.find("select").val(extractMethod)
    sampleCarrierBox.find("select").val(sampleCarrier)
    var form = $("#materialEvidencerlSampleBox").find('form')
    form.bootstrapValidator({
        live: 'disabled',
        message: 'This value is not valid',
        fields: {
            sampleType: {
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
            sampleName: {
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
            extractTime: {
                trigger: 'change',
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            }, samplePacking: {
                trigger: 'change',
                validators: {
                    notEmpty: {
                        message: "不能为空"
                    }
                }
            },
        }
    });
    form.bootstrapValidator('validate');
    if (form.data('bootstrapValidator').isValid()) {
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
        if (index != "") {
            var tr = $("#materialEvidencerTbody").children("tr").eq(index)
            tr.find('input[name="sampleTypeName"]').val(sampleTypeName)
            tr.find('input[name="sampleType"]').val(sampleType)
            tr.find('input[name="sampleName"]').val(sampleName)
            tr.find('input[name="samplePackingName"]').val(samplePackingName)
            tr.find('input[name="samplePacking"]').val(samplePacking)
            tr.find('input[name="sampleDescribe"]').val(sampleDescribe)
            tr.find('input[name="extractTime"]').val(extractTime)
            tr.find('input[name="inspectionObjective"]').val(inspectionObjective)
            tr.find('input[name="samplePicture"]').val(samplePicture)
            tr.find('input[name="evidenceNo"]').val(evidenceNo)
            tr.find('input[name="extractMethod"]').val(extractMethod)
            tr.find('input[name="sampleCarrier"]').val(sampleCarrier)
            tr.children("td").each(function (i) {
                if (tr.children("td").length - 1 !== i) {
                    var tdInput = $(this).children("input").clone()
                    if (i == 6) {
                        $(this).find("select[name='extractMethod']").val(extractMethod)
                        $(this).find("select[name='sampleCarrier']").val(sampleCarrier)
                    } else {
                        $(this).html(tdInput.val())
                    }
                    $(this).append(tdInput)
                }
            })
        }
        else {
            var Box = '<div class="select"><select class="form-control" required="" name="extractMethod"> <option value="01" selected="">擦</option> <option value="02">吸</option> <option value="03">粘</option> <option value="04">剪</option> <option value="05">刮</option> <option value="06">其他</option> </select> </div>'
            Box += '<div class="select"><select class="form-control" required="" name="sampleCarrier"> <option value="01" selected="">棉签</option> <option value="02">粘取器</option> <option value="03">烟蒂</option> <option value="04">血卡</option><option value="05">实物</option><option value="06">唾液卡</option></select></div>'
            var newTr = '<tr>'
            newTr += '<td>' + length + '</td>'
            newTr += '<td>' + sampleTypeName + '<input type="hidden" name="sampleTypeName" value="' + sampleTypeName + '"></td>'
            newTr += '<td>' + sampleName + '<input type="hidden" name="sampleName" value="' + sampleName + '"></td>'
            newTr += '<td>' + sampleDescribe + '<input type="hidden" name="sampleDescribe" value="' + sampleDescribe + '"></td>'
            newTr += '<td>' + samplePackingName + '<input type="hidden" name="samplePackingName" value="' + samplePackingName + '"></td>'
            newTr += '<td>' + extractTime + '<input type="hidden" name="extractTime" value="' + extractTime + '"></td>'
            newTr += '<td>' + Box + '</td>'
            newTr += '<td>' + inspectionObjective + '<input type="hidden" name="inspectionObjective" value="' + inspectionObjective + '"></td>'
            newTr += '<td>'
            newTr += '<button type="button" class="btn-icon btn-icon-blue btn-icon-bianji-blue edit">编辑 </button>'
            newTr += '<button type="button" class="btn-icon btn-icon-red btn-icon-shanchu-red remove">删除</button>'
            newTr += '<input type="hidden" name="sampleId" value="">'
            newTr += '<input type="hidden" name="sampleType" value="' + sampleType + '">'
            newTr += '<input type="hidden" name="samplePacking" value="' + samplePacking + '">'
            newTr += '<input type="hidden" name="extractMethod" value="' + extractMethod + '">'
            newTr += '<input type="hidden" name="sampleCarrier" value="' + sampleCarrier + '">'
            newTr += '<input type="hidden" name="samplePicture" value="' + samplePicture + '">'
            newTr += '</td>'
            newTr += '</tr>'
            $("#materialEvidencerTbody").append(newTr)
            $("#materialEvidencerTbody").children().eq(length - 1).find("select[name='extractMethod']").val(extractMethod)
            $("#materialEvidencerTbody").children().eq(length - 1).find("select[name='sampleCarrier']").val(sampleCarrier)
        }
        $('#materialEvidencerlSampleBox').modal('hide')
    }
})
//关闭检材清空
$('#materialEvidencerlSampleBox').on('hidden.bs.modal', function (e) {
    var  extractTime=$("input[name='extractTime']").val();
    /*$("#materialEvidencerlSampleBox").find("input[type='text']").val("")*/
    $("#materialEvidencerlSampleBox").find("input[type='hidden']").val("")
    $("#materialEvidencerlSampleBox").find("input[name='sampleName']").val("")
    $("#materialEvidencerlSampleBox").find("select").val("")
    $("#materialEvidencerlSampleBox").find("textarea").val("")
    $("#materialEvidencerlSampleBox").find("select[name='extractMethod']").val("01")
    $("#materialEvidencerlSampleBox").find("select[name='sampleCarrier']").val("01")
    $("#materialEvidencerlSampleBox").find("select[name='samplePacking']").val("01")
    $("#materialEvidencerlSampleBox").find("input[name='inspectionObjective']").val("DNA检验")
    $("#materialEvidencerlSampleBox").find(".samplePhotobox").parent().find("span").addClass("hidden")
  /*  $("#materialEvidencerlSampleBox").find("input[name='extractTime']").val("")*/
    $("#materialEvidencerlSampleBox").find("input[name='extractTime']").val(extractTime)
    $("#materialEvidencerlSampleBox").find(".addsamplePhoto").parent().siblings().remove()
    if ($('#materialEvidencerlSampleBox').find(".has-error").length > 0) {
        var form = $("#materialEvidencerlSampleBox ").find("form")
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
    }
})
var personIds = "";
var sampleIds = "";
//删除被鉴定人信息
$("#authenticatorTbody").on("click", ".remove", function () {
    var personId = $(this).siblings("input[name='personId']").val();
    if (personId != undefined) {
        personIds += "," + personId;
    }
    //$(this).parents("tr").remove()
    if ($(this).parents("tr").find("input[name='personId']").length) {
        $("#personSampleTbody").find("input[name='linkId'][value='" + $(this).parents("tr").find("input[name='personId']").val() + "']").each(function () {
            if ($(this).parents("tr").find("input[name='sampleId']").val() != "") {
                sampleIds += "," + $(this).parents("tr").find("input[name='sampleId']").val()
            }
            $(this).parents("tr").remove()
        })
    } else {
        $("#personSampleTbody").find("input[name='timeId'][value='" + $(this).parents("tr").find("input[name='timeId']").val() + "']").each(function () {
            $(this).parents("tr").remove()
        })
    }
    $("#personSampleTbody").find("tr").each(function (i) {
        $(this).children().eq(0).html(i + 1)
    })
    var trNode = $(this).parent().parent();
    $($(this).parent().parent().nextAll("tr")).each(function () {
        var a = $(this).find("td").eq(0).text();
        $(this).find("td").eq(0).text(a - 1);
    });
    trNode.remove();
});
//删除样本信息
$("#personSampleTbody").on("click", ".remove", function () {
    var sampleId = $(this).siblings("input[name='sampleId']").val();
    if (sampleId != undefined) {
        sampleIds += "," + sampleId;
    }
    console.log(sampleIds)
    //$(this).parents("tr").remove()
    var trNode = $(this).parent().parent();
    $($(this).parent().parent().nextAll("tr")).each(function () {
        var a = $(this).find("td").eq(0).text();
        $(this).find("td").eq(0).text(a - 1);
    });
    trNode.remove();
});
//删除检材信息
var sampleIdWzs = "";
$("#materialEvidencerTbody").on("click", ".remove", function () {
    var sampleIdWz = $(this).siblings("input[name='sampleId']").val();
    if (sampleIdWz != "") {
        sampleIdWzs += "," + sampleIdWz;
    }
    //$(this).parents("tr").remove()
    //删除检材信息，序号自动调整
    var trNode = $(this).parent().parent();
    $($(this).parent().parent().nextAll("tr")).each(function () {
        var a = $(this).find("td").eq(0).text();
        $(this).find("td").eq(0).text(a - 1);
    });
    trNode.remove();
});
//获取委托信息
function getConsignmentInfo() {
    var consignmentInfo = {};

//            consignmentInfo.consignmentNo = $("#consignmentNo").val();
    consignmentInfo.consignmentNo = $("[name='consignmentNo']").val();

    consignmentInfo.areaOrgCode = $("option:selected", "#areaOrgCode").val();//所属辖区code

    consignmentInfo.delegateOrgCode = $("#delegateOrgCode").val();//委托单位code
    consignmentInfo.delegateOrgName = $("#delegateOrgName").val();//委托单位名称
    //委托人信息1
    consignmentInfo.delegator1Id = $("option:selected", "#delegator1Id").val();//委托人1id
    consignmentInfo.delegator1Name = $("option:selected", "#delegator1Id").text();//委托人1
    consignmentInfo.delegator1Position = $("#delegator1Position").val();//职务
    consignmentInfo.delegator1PaperworkType = $("#delegator1PaperworkType").val();//z证件
    consignmentInfo.delegator1PaperworkNo = $("#delegator1PaperworkNo").val();//证件号
    consignmentInfo.delegator1PhoneNumber = $("#delegator1PhoneNumber").val();//电话
    //委托人信息2
    consignmentInfo.delegator2Id = $("option:selected", "#delegator2Id").val();//委托人2id
    consignmentInfo.delegator2Name = $("option:selected", "#delegator2Id").text();//委托人2
    consignmentInfo.delegator2Position = $("#delegator2Position").val();//职务
    consignmentInfo.delegator2PaperworkType = $("#delegator2PaperworkType").val();//z证件
    consignmentInfo.delegator2PaperworkNo = $("#delegator2PaperworkNo").val();//证件号
    consignmentInfo.delegator2PhoneNumber = $("#delegator2PhoneNumber").val();//电话

    //鉴定要求
    var identifyRequirementArr = []
    $(".btn-checkbox").children(".active").each(function (i, item) {
        identifyRequirementArr.push($(this).attr("value"))
    })
    consignmentInfo.identifyRequirement = identifyRequirementArr.join(",")

    //鉴定类别
    consignmentInfo.identifyType = "DNA鉴定";//DNA鉴定

    consignmentInfo.consignmentId = $("#consignmentId").val();//委托id

    console.log(consignmentInfo);
    return consignmentInfo;
}
//获取案件信息
function getCaseInfoDna() {
    var caseInfoDna = {};
    caseInfoDna.entrustBookNo = $("#entrustBookNo").val()//委托书编号
    caseInfoDna.caseName = $("#caseName").val();//案件名称
    caseInfoDna.caseLocation = $("#caseLocation").val();//案发地址
    caseInfoDna.caseDatetime = $("#caseDatetime").val().trim();//案发时间
    caseInfoDna.caseType = $("option:selected", "#caseType").val();//案件类型
    caseInfoDna.caseProperty = $("option:selected", "#caseProperty").val();//案发性质
    caseInfoDna.caseLevel = $("option:selected", "#caseLevel").val();//案发性质
    caseInfoDna.caseBrief = $("#caseBrief").val();//简要案情
    caseInfoDna.caseRemark = $("#caseRemark").val();//其他说明
    caseInfoDna.caseXkNo = $("button[data-target='#siteSurveyBox']").prev().html();//现堪编号

    caseInfoDna.caseId = $("#caseId").val();//案件id

    caseInfoDna.majorNo = $("#majorNo").val();//专业
    caseInfoDna.majorType = $("#majorType").val();//专业类型


    console.log(caseInfoDna)
    return caseInfoDna;
}
//获取被鉴定人信息
function getLimsPersonInfo() {
    var limsPersonInfoArr = new Array();
    $("#authenticatorTbody").find("tr").each(function () {
        var that = $(this)
        limsPersonInfo = {};
        limsPersonInfo.personType = $(this).find("input[name='personType']").val()
        limsPersonInfo.personName = $(this).find("input[name='personName']").val()
        limsPersonInfo.personGender = $(this).find("input[name='sex']").val()
        limsPersonInfo.perosnAge = $(this).find("input[name='year']").val()
        limsPersonInfo.personIdCard = $(this).find("input[name='idCard']").val()
        limsPersonInfo.idCardFlag = limsPersonInfo.personIdCard == "" ? 1 : 0
        limsPersonInfo.noIdCardDesc = $(this).find("input[name='noIdCard']").val()
        limsPersonInfo.relationType = $(this).find("input[name='kinship']").val()
        limsPersonInfo.personCurrentAddress = $(this).find("input[name='personCurrentAddress']").val()
        limsPersonInfo.personHeight = $(this).find("input[name='personHeight']").val()
        limsPersonInfo.personWeight = $(this).find("input[name='personWeight']").val()
        //人员id
        limsPersonInfo.personId = $(this).find("input[name='personId']").val()
        //圖片
        limsPersonInfo.personFrontPicture = $(this).find("input[name='personBase']").val()
        //base64
        limsPersonInfo.sampleInfoDnaList = []
        var linkId = $(this).find("input[name='personId']").val(),
            timeId = $(this).find("input[name='timeId']").val();

        $("#personSampleTbody").find("tr").each(function () {
            var sampleInfoDna = {}
            if ($(this).find("input[name='linkId']").length > 0 && $(this).find("input[name='linkId']").val() == linkId) {
                sampleInfoDna.sampleType = $(this).find("input[name='sampleType']").val()
                sampleInfoDna.sampleName = $(this).find("input[name='sampleName']").val()
                sampleInfoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val()
                sampleInfoDna.samplePacking = $(this).find("input[name='samplePacking']").val()
                sampleInfoDna.extractDatetime = $(this).find("input[name='extractTime']").val().trim();
                sampleInfoDna.extractMethod = $(this).find("input[name='extractMethod']").val()
                sampleInfoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val()
                sampleInfoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val()
                sampleInfoDna.addFlag = $(this).find("input[name='addFlag']").val()
                sampleInfoDna.sampleId = $(this).find("input[name='sampleId']").val()
                sampleInfoDna.sampleDnaPicture = $(this).find("input[name='sampleBase']").val()
                limsPersonInfo.sampleInfoDnaList.push(sampleInfoDna);
            }
            else if ($(this).find("input[name='timeId']").length > 0 && $(this).find("input[name='timeId']").val() == timeId) {
                sampleInfoDna.sampleType = $(this).find("input[name='sampleType']").val()
                sampleInfoDna.sampleName = $(this).find("input[name='sampleName']").val()
                sampleInfoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val()
                sampleInfoDna.samplePacking = $(this).find("input[name='samplePacking']").val()
                sampleInfoDna.extractDatetime = $(this).find("input[name='extractTime']").val().trim();
                sampleInfoDna.extractMethod = $(this).find("input[name='extractMethod']").val()
                sampleInfoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val()
                sampleInfoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val()
                sampleInfoDna.addFlag = $(this).find("input[name='addFlag']").val()
                sampleInfoDna.sampleId = $(this).find("input[name='sampleId']").val()
                sampleInfoDna.sampleDnaPicture = $(this).find("input[name='sampleBase']").val()
                limsPersonInfo.sampleInfoDnaList.push(sampleInfoDna);
            }
        })
        limsPersonInfoArr.push(limsPersonInfo);
    })
    console.log(limsPersonInfoArr)
    return limsPersonInfoArr;
}
//获取检材信息
function getSampleInfoDna() {
    var sampleInfoDnaArr = new Array();
    $("#materialEvidencerTbody").find("tr").each(function () {
        var that = $(this)
        samplefoDna = {};
        samplefoDna.sampleType = $(this).find("input[name='sampleType']").val();
        samplefoDna.sampleName = $(this).find("input[name='sampleName']").val();
        samplefoDna.sampleDesc = $(this).find("input[name='sampleDescribe']").val();
        samplefoDna.samplePacking = $(this).find("input[name='samplePacking']").val();
        samplefoDna.extractDatetime = $(this).find("input[name='extractTime']").val();
        samplefoDna.extractMethod = $(this).find("input[name='extractMethod']").val();
        samplefoDna.sampleCarrier = $(this).find("input[name='sampleCarrier']").val();
        samplefoDna.samplePurpose = $(this).find("input[name='inspectionObjective']").val();
        samplefoDna.sampleIdwz = $(this).find("input[name='sampleId']").val();
        samplefoDna.evidenceNo = "";
        samplefoDna.sampleMaterialPicture = $(this).find("input[name='samplePicture']").val();
        sampleInfoDnaArr.push(samplefoDna);
    })
    return sampleInfoDnaArr;
}
//保存弹出
$(".saveBoxBtn").click(function () {
    $(".clientSelect").css("display", "block")
    var form = $("#saveform ")
    if($("#manualWtsNoBox").is(":checked")){
        form.bootstrapValidator({
            live: 'disabled',
            message: 'This value is not valid',
            fields: {
                consignmentNo: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        },
                        regexp: {
                            regexp: /^[A-Za-z0-9]+$/,
                            message: '格式有误'
                        }
                    }
                },
                identifyRequirement: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "鉴定要求不能为空"
                        }
                    }
                },
                areaOrgCode: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator1Id: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator2Id: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseName: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseLocation: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseDatetime: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator2PhoneNumber: {
                    validators: {
                        regexp: {
                            regexp:  /^1[3456789]\d{9}$/,
                            message: '电话格式有误'
                        }
                    }
                },
                delegator1PhoneNumber: {
                    validators: {
                        regexp: {
                            regexp:  /^1[3456789]\d{9}$/,
                            message: '电话格式有误'
                        }
                    }
                },
                caseType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseProperty: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseBrief: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseLevel: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
            }
        });
    }
  else{
        form.bootstrapValidator({
            live: 'disabled',
            message: 'This value is not valid',
            fields: {
                identifyRequirement: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "鉴定要求不能为空"
                        }
                    }
                },
                areaOrgCode: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator1Id: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator2Id: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseName: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseLocation: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseDatetime: {
                    trigger: 'change',
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                delegator2PhoneNumber: {
                    validators: {
                        regexp: {
                            regexp:  /^1[3456789]\d{9}$/,
                            message: '电话格式有误'
                        }
                    }
                },
                delegator1PhoneNumber: {
                    validators: {
                        regexp: {
                            regexp:  /^1[3456789]\d{9}$/,
                            message: '电话格式有误'
                        }
                    }
                },
                caseType: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseProperty: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseBrief: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
                caseLevel: {
                    validators: {
                        notEmpty: {
                            message: "不能为空"
                        }
                    }
                },
            }
        });
    }
        form.bootstrapValidator('validate');
    scroll(form);
    if (form.data('bootstrapValidator').isValid()) {
        form.data('bootstrapValidator').destroy();
        form.data('bootstrapValidator', null);
        $("#saveBox").modal("show")
        $(".clientSelect").css("display", "none")
    }
})
//验证滚动
function scroll(form) {
    $(form).find("small[data-bv-result='INVALID']").each(function (index, ele) {
        if (index == 0) {
            $("body,html").animate({
                scrollTop: $(this).offset().top - 100
            })
            $(this).prev().focus()
        }
    })
}
// 页面滚动定位
window.onscroll = function () {
    if ($(document).scrollTop() >= 112) {
        $(".fixedBox").removeClass("hidden")
        var currentScroll = $(this).scrollTop()
        var currentSection
        $(".Modular").each(function () {
            var divPosition = $(this).offset().top - 62;
            if (currentScroll > divPosition) {
                currentSection = $(this).find(".panel-heading").children().html()
            }
        })
        $(".fixedBox").find("li").each(function () {
            if ($(this).html() == currentSection) {
                $(this).addClass("now").siblings().removeClass("now")
            }
        })
    } else {
        $(".fixedBox").addClass("hidden")
    }
}
// 顶部导航点击定位
$(".fixedBox").find("li").click(function () {
    var that = $(this)
    $(".Modular").each(function () {
        if (that.html() == $(this).find(".panel-heading").children().html()) {
            $("html,body").animate({
                scrollTop: $(this).offset().top - 60
            }, 500);
        }
    })
    that.addClass("liActive").siblings().removeClass("liActive")
})


function Format(now,mask)
    {
        var d = now;
        var zeroize = function (value, length)
        {
            if (!length) length = 2;
            value = String(value);
            for (var i = 0, zeros = ''; i < (length - value.length); i++)
            {
                zeros += '0';
            }
            return zeros + value;
        };

        return mask.replace(/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g, function ($0)
        {
            switch ($0)
            {
                case 'd': return d.getDate();
                case 'dd': return zeroize(d.getDate());
                case 'ddd': return ['Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri', 'Sat'][d.getDay()];
                case 'dddd': return ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'][d.getDay()];
                case 'M': return d.getMonth() + 1;
                case 'MM': return zeroize(d.getMonth() + 1);
                case 'MMM': return ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'][d.getMonth()];
                case 'MMMM': return ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'][d.getMonth()];
                case 'yy': return String(d.getFullYear()).substr(2);
                case 'yyyy': return d.getFullYear();
                case 'h': return d.getHours() % 12 || 12;
                case 'hh': return zeroize(d.getHours() % 12 || 12);
                case 'H': return d.getHours();
                case 'HH': return zeroize(d.getHours());
                case 'm': return d.getMinutes();
                case 'mm': return zeroize(d.getMinutes());
                case 's': return d.getSeconds();
                case 'ss': return zeroize(d.getSeconds());
                case 'l': return zeroize(d.getMilliseconds(), 3);
                case 'L': var m = d.getMilliseconds();
                    if (m > 99) m = Math.round(m / 10);
                    return zeroize(m);
                case 'tt': return d.getHours() < 12 ? 'am' : 'pm';
                case 'TT': return d.getHours() < 12 ? 'AM' : 'PM';
                case 'Z': return d.toUTCString().match(/[A-Z]+$/);
                // Return quoted strings with the surrounding quotes removed
                default: return $0.substr(1, $0.length - 2);
            }
        });
}