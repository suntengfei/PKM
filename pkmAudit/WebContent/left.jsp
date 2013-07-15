<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<title>绩效考核目管理系统</title>
<link rel="stylesheet" href="css/zTreeStyle.css" type="text/css" />
<style>
body {
	background-color: white;
	margin: 0;
	padding: 0;
	text-align: center;
}

div,p,table,th,td {
	list-style: none;
	margin: 0;
	padding: 0;
	color: #333;
	font-size: 12px;
	font-family: dotum, Verdana, Arial, Helvetica, AppleGothic, sans-serif;
}

#testIframe {
	margin-left: 10px;
}
</style>

<script type="text/javascript" src="jslib/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jslib/jquery.ztree.core-3.3.min.js"></script>
<script type="text/javascript">
	var zTree;
	var demoIframe;

	var setting = {
		view : {
			dblClickExpand : false,
			showLine : true,
			selectedMulti : false,
			expandSpeed : ($.browser.msie && parseInt($.browser.version) <= 6) ? ""
					: "fast"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : ""
			}
		},
		callback : {
			beforeClick : function(treeId, treeNode) {
				var zTree = $.fn.zTree.getZTreeObj("tree");
				if (treeNode.isParent) {
					zTree.expandNode(treeNode);
					return false;
				} else {
					demoIframe.attr("src", treeNode.file + ".jsp");
					return true;
				}
			}
		}
	};
	
	var zNodes = [
		{ id : 1, pId : 0, name : "菜单" }
		,{ id : 101, pId : 1, name : "审核", url : "browse/dairy_select4AuditList", target : "mainFrame"}
	];

	$(document).ready(function() {
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
		zTree.selectNode(zTree.getNodeByParam("id", 101));

	});

	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight, htmlH = demoIframe
				.contents().find("html").get(0).scrollHeight, maxH = Math.max(
				bodyH, htmlH), minH = Math.min(bodyH, htmlH), h = demoIframe
				.height() >= maxH ? minH : maxH;
		if (h < 530)
			h = 530;
		demoIframe.height(h);
	}
</script>

</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="207" height="55" background="image/nav04.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="25%" rowspan="2">
							<img src="image/ico02.gif" width="35" height="35" />
						</td>
						<td width="75%" height="22" class="left-font01">
							您好，<span class="left-font02"><s:property value="user.name"/></span>
						</td>
					</tr>
					<tr>
						<td height="22" class="left-font01">
							[&nbsp; <a href="logout.jsp" target="_top" class="left-font01">退出</a>&nbsp;]
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr bgcolor="e2f0f8">
			<td>
				<ul id="tree" class="ztree" style="width: 260px; overflow: auto;"></ul>
			</td>
		</tr>
        <tr>
        <td height="800" bgcolor="e2f0f8"></td>
        </tr>
	</table>
</body>
</html>