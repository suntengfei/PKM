<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>绩效考核系统</title>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="jslib/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jslib/jquery.validate.min.js"></script>

<script type="text/javascript">
	function initPage() {

		$("#loginForm").validate({
			rules : {
				"j_username" : {
					required : true,
					maxlength : 25
				},
				"j_password" : {
					required : true,
					maxlength : 25
				}
			},
			messages : {
				"j_username" : {
					required : "必填项",
					maxlength : "最大长度25"
				},
				"j_password" : {
					required : "必填项",
					maxlength : "最大长度25"
				}
			}
		});

	}

	$(document).ready(function() {
		initPage();
	});

	function loginSubmit() {
		$("#loginForm").submit();
	}
</script>
</head>

<body>
	<form action="j_security_check" method="post" name="loginForm" id="loginForm">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="147" background="image/top02.gif">
					<img src="image/top03.gif" width="776" height="147" />
				</td>
			</tr>
		</table>
		<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
			<tr>
				<td width="221">
					<table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
						<tr>
							<td>
								<table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
									<tr>
										<td align="center">
											<img src="image/ico13.gif" width="107" height="97" />
										</td>
									</tr>
									<tr>
										<td height="40" align="center">&nbsp;</td>
									</tr>
								</table>
							</td>
							<td><img src="image/line01.gif" width="5" height="292" /></td>
						</tr>
					</table>
				</td>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td colspan="2" align="center">
								${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}
							</td>
						</tr>
						<tr>
							<td width="31%" height="35" class="login-text02">用户名称：</td>
							<td width="69%"><input name="j_username" id="j_username" type="text" /></td>
						</tr>
						<tr>
							<td height="35" class="login-text02">密 码：</td>
							<td><input name="j_password" id="j_password" type="password" /></td>
						</tr>
						<tr>
							<td height="35">&nbsp;</td>
							<td>
								<input name="Submit2" id="submit" type="submit" class="right-button01" value="确认登陆" /> 
								<input name="Submit232" type="reset" class="right-button02" value="重 置" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>