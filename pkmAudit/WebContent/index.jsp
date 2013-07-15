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
</head>

<frameset rows="59,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="213,*" frameborder="no" border="0" framespacing="0">
		<frame src="left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="" name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
</frameset>
<noframes>
	<body>
	</body>
</noframes>
</html>
