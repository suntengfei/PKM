var autoId = 0; // 这个autoId可以用来生成tr的id

function Match(key, dataKey, val, typefunction) {
	this.key = key;
	this.dataKey = dataKey;
	this.val = val;
	this.typefunction = typefunction;
}

function textfunction(autoId, str) {
	var strloop = '<input name="search.values" type="text"></input>';
	$("#td_" + autoId).html(strloop);
}

function selectfunction(autoId, str) {
	var arr = allData[str];
	var opts = "";
	for ( var i = 0; i < arr.length; i++) {
		opts = opts + '<option value="' + arr[i].val + '">' + arr[i].key
				+ '</option>';
	}
	var strloop = '<select name="search.values">' + opts + '</select>';
	$("#td_" + autoId).html(strloop);
}

function datefunction(autoId, str) {
	var strloop = '<input name="search.values" type="text" onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\'})"></input>';
	$("#td_" + autoId).html(strloop);
}

function addSelect() {
	var retStr;
	var str0 = '<td><select name = "search.logicalopts"><option value="and">AND</option><option value="or">OR</option></select></td>';
	var str1 = "";
	autoId++;
	str1 = '<td><select name = "search.columns" onchange="change(' + autoId
			+ ',this)">' + '<option value="-">---请选择要检索的单元---</option>';
	for (i = 0; i < arrMatch.length; i++) {
		str1 = str1 + '<option value = "' + arrMatch[i].key + '">'
				+ arrMatch[i].val + '</option>';
	}
	str1 = str1 + "</select></td>";
	var str2 = '<td><select name="search.operators">';
	str2 = str2 + '<option value="eq">=</option>';
	str2 = str2 + '<option value="ne">&ne;</option>';
	str2 = str2 + '<option value="gt">&gt;</option>';
	str2 = str2 + '<option value="ge">&ge;</option>';
	str2 = str2 + '<option value="lt">&lt;</option>';
	str2 = str2 + '<option value="le">&le;</option>';
	str2 = str2 + '<option value="like">like</option></select></td>';
	var str3 = '<td id="td_' + autoId + '"><input type="text" name="search.values"></input></td>';
	var str4 = '<td><select name = "search.orders"><option value="none">不排序</option><option value="asc">升序</option><option value="desc">降序</option></select></td>';
	var str5 = "<td align='left'><input class=\"jBtn\" type = \"button\" value=\"删除检索项\" onclick=\"remove(this);\"></input></td>";
	var str = "<tr id=tr_" + autoId + ">" + str0 + str1 + str2 + str3 + str4
			+ str5 + "</tr>";
	$("#tableid").append(str);
	$('.jBtn').button().css('font-size','12px');
}

function change(id, sel) {
	var checkIndex = sel.selectedIndex;
	if (checkIndex == 0) {
		$("#td_" + id).html('<input name="search.values" type="text"/>');
		return;
	}
	checkIndex = checkIndex - 1;
	arrMatch[checkIndex].typefunction(id, arrMatch[checkIndex].dataKey);
}

function remove(id) {
	$(id).parent().parent().remove();
}

function toThePage(pageNumber) {
	$("#pagenum").val(pageNumber);
	searchIt();
}

function searchChange(search) {
	var searchIndex = search.selectedIndex + 1;
	toPage(searchIndex);
}

function searchIt() {
	$("body").mask("正在传输数据，请等待。。。");// 显示loading
	var options = {
		success : searchResponse, // 当成功提交表单之后将会执行的函数
		error : searchError,
		dataType : "html"
	};
	$("#search_form").ajaxSubmit(options);// 用ajax提交表单
}

function searchError(responseXML, statusText, xhr, form) {
	$("body").unmask();
	alert("查询错误，请检查查询条件，重新查询");
}

function searchResponse(responseXML, statusText, xhr, form) {
	var p = responseXML.indexOf(title);
	if (p != -1) {
		var s = responseXML.indexOf('pageFlagStart', p);
		if (s == -1) {
			alert('数据格式错误，缺少pageFlagStart');
			return;
		}
		var p = responseXML.indexOf('pageFlagEnd', s);
		if (p == -1) {
			alert('数据格式错误，缺少pageFlagEnd');
			return;
		}
		$("#resultTd").html(responseXML.substring(s - 4, p + 3));
	} else {
		alert("无法获取数据");
	}
	initPage();
	$("body").unmask();// 隐藏loading
}
