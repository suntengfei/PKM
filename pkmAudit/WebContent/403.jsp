<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403</title>
</head>
<body>
	您没有访问权限，请
	<a href="index.jsp">点此返回</a>
</body>
</html>