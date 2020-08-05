$(document).ready(function() {


	$("input[name='resetBtn']").click(function() {

		$("select[name='loginname']").get(0).options[0].selected = true;
		$("input[name='name']").val("");
		$("input[name='password']").val("");
	});
	
	$("select[name='companyId']").change(function() {
		$.ajax({
			url : "changeCompany.html",
			type : "GET",
			dataType : 'json',
			data : $("form").serialize(),
			success : function(data) {
				var list = data.employeeList;

				var inhtml = "<option>请选择</option>";
				$("select[name='loginname']").empty();
				for ( var i = 0; i < list.length; i++) {
					// if (list[i].dictKey)
					inhtml += "<option value='" + list[i].loginname + "' title='" + list[i].name + "'>" + list[i].name + "</option>";

				}
				$("select[name='loginname']").attr("class", "select_login");
				$("select[name='loginname']").append(inhtml);
			},
			error : function(data) {
				alert("程序错误，请联系系统管理员!"+data.statusText)
			}
		});
	});
});