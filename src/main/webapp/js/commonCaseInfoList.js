$(function () {
    var flag = true
    $(".move").click(function () {
        if ($(".headerForm").css("height") == "42px") {
            $(".headerForm").css("height", "333px");
            var timeout = setTimeout(function () {
                 $(".headerForm").delay(3100).css("overflow", "visible")
            }, 1100);

        } else {
            $(".headerForm").css("height", "42px");
            $(".headerForm").delay(3100).css("overflow", "hidden")                 
        }
    })
})