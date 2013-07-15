<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>会议管理系统_权限不足</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<style type="text/css">
div.error {
	width: 260px;
	border: 2px solid red;
	background-color: yellow;
	text-align: center;
}
</style>
</head>

<body>
	<h1>Access Denied</h1>
	<hr>
	<div class="error">
		访问被拒绝<br>
		${requestScope['SPRING_SECURITY_403_EXCEPTION'].message}
	</div>
	<hr>
</body>
</html>
