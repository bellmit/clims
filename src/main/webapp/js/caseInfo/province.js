var defaults = {
    s1: 'provid',
    s2: 'cityid',
    s3: 'areaid',
    v1: document.getElementById("province").value,
    v2: document.getElementById("city").value,
    v3: document.getElementById("county").value
};
var $form;
var form;
var $;
var number=0;
layui.define(['jquery', 'form'], function () {
    $ = layui.jquery;
    form = layui.form;
    $form = $('form');
    treeSelect(defaults);
});
function treeSelect(config) {
    config.v1 = config.v1 ? config.v1 : "";
    config.v2 = config.v2 ? config.v2 : "";
    config.v3 = config.v3 ? config.v3 : "";
    $.each(threeSelectData, function (k, v) {
        appendOptionTo($form.find('select[otherName=' + config.s1 + ']'), k, v.val, config.v1);
    });
    form.render();
    cityEvent(config);
    areaEvent(config);
    form.on('select(' + config.s1 + ')', function (data) {
        number=0;
        var proviName= $(data.elem).find("option:selected").text();
        $("#provinceName").val(proviName);
        cityEvent(data);
        form.on('select(' + config.s2 + ')', function (data) {
            number=0;
            var cityName= $(data.elem).find("option:selected").text();
            $("#cityName").val(cityName);
            areaEvent(data);
        });
    });

    function cityEvent(data) {
        $form.find('select[otherName=' + config.s2 + ']').html("");
        config.v1 = data.value ? data.value : config.v1;
        $.each(threeSelectData, function (k, v) {
            if (v.val == config.v1) {
                if (v.items) {
                    $.each(v.items, function (kt, vt) {
                        if(number=='0'){
                            $("#cityName").val(kt);
                        }
                        number++;
                        appendOptionTo($form.find('select[otherName=' + config.s2 + ']'), kt, vt.val, config.v2);
                    });
                }
            }
        });
        form.render();
        number=0;
        config.v2 = $('select[otherName=' + config.s2 + ']').val();
        areaEvent(config);
    }
    function areaEvent(data) {
        $form.find('select[otherName=' + config.s3 + ']').html("");
        config.v2 = data.value ? data.value : config.v2;
        $.each(threeSelectData, function (k, v) {
            if (v.val == config.v1) {
                if (v.items) {
                    $.each(v.items, function (kt, vt) {
                        if (vt.val == config.v2) {
                            $.each(vt.items, function (ka, va) {
                                if(number=='0'){
                                    $("#countyName").val(ka);
                                }
                                number++;
                                appendOptionTo($form.find('select[otherName=' + config.s3 + ']'), ka, va, config.v3);
                            });
                        }
                    });
                }
            }
        });
        form.render();
        form.on('select(' + config.s3 + ')', function (data) {
            var countyName= $(data.elem).find("option:selected").text();
            $("#countyName").val(countyName);
        });
    }
    function appendOptionTo($o, k, v, d) {
        var $opt = $("<option>").text(k).val(v);
        if (v == d) { $opt.attr("selected", "selected") }
        $opt.appendTo($o);
    }
}