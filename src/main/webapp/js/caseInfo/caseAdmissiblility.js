$(function () {

    $("div[name='caseMode']").on("click", function () {
        $("#status").val($(this).attr("status"));
        var majorCode =  $('#majorCodeId').val();
        $("#majorCode").val(majorCode);
        window.location.href=baseurl+"/case/caseAdmissibles.html?"+$("#form").serialize();
    });
});