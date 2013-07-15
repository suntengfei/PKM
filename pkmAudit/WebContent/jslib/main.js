var flag = 0;

$(document).ready(function() {
	$("#searchDiv").hide();
});

function searchToggle() {
	if(flag == 0 ) {
		$("#searchButton").val("取消");
		flag = 1;
	} else {
		$("#searchButton").val("检索");
		flag = 0;
	}
	$("#searchDiv").toggle();
}