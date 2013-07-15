var title = "dairy";

var arrMatch=[
	new Match("name", "", "名称", textfunction),
	new Match("check", "check", "状态", selectfunction)
];

$(document).ready(function(){
	$("#operationDiv").dialog({ 
		autoOpen : false ,
		title : "知识管理",
		height: 400,
		width: 800,
		buttons: {
			"确认" : function() {
				$("#update_form").submit();
			},
			"取消" : function() {
				$(this).dialog("close");
			}
		}
	});

});

function auditPass(aid, aObj) {
	
	var params = "obj.id=" + aid;
	
	$.ajax({
	   type : "POST",
	   url : "update/dairy_auditPass",
	   dataType: "xml",
	   data : params,
	   success : showResponse,
	   error : showError
	});
	
}

function auditNotPass(aid, aObj) {
	
	var params = "obj.id=" + aid;
	
	$.ajax({
	   type : "POST",
	   url : "update/dairy_auditNotPass",
	   dataType: "xml",
	   data : params,
	   success : showResponse,
	   error : showError
	});
	
}

function show(objId) {
	$("#operationDiv").load("browse/dairy_show", {"ids": objId},  function() {
		$("#operationDiv").show();
		$("#operationDiv").dialog("open");
	});
}

function del(objId) {
	$("body").mask("加载中.......");
	$.ajax({
		url: "update/dairy_del",
		data: {"ids":objId},
		type: "POST",
		cache: false,
		dataType : "xml",
		error : showError,
		success: function(responseXML){
			var xmlObj = $(responseXML);
			var msgTxt = xmlObj.find("#msg").text();
			if(msgTxt.indexOf("成功") != -1) {
				msgTxt = xmlObj.find("#successMsg").text();
				$("body").unmask();
				alert(msgTxt);
				searchIt();
			}else {
				msgTxt = xmlObj.find("#errorMsg").text();
				$("body").unmask();
				alert(msgTxt);
			}
		}
	});
}

function submit() {
	$("body").mask("加载中.......");
	var options = {
		success : showResponse,// 当成功提交表单后执行的函数
		error : showError,
		dataType : "xml"
	};
	$("#update_form").ajaxSubmit(options);
}

function showResponse(responseXML, statusText, xhr, form){
	var xmlObj = $(responseXML);
	var msgTxt = xmlObj.find("#msg").text();
	
	if(msgTxt.indexOf("成功") != -1) {
		msgTxt = xmlObj.find("#successMsg").text();
		$("#operationDiv").dialog("close");
		$("#operationDiv").html("");
		$("body").unmask();
		alert(msgTxt);
		searchIt();
	}else {
		msgTxt = xmlObj.find("#errorMsg").text();
		$("body").unmask();
		alert(msgTxt);
	}
}

function showError() {
	$("body").unmask();
	alert("服务器错误！操作失败");
}
