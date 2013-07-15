<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>dairy</title>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="jqueryui/jquery.ui.all.css" />
<link rel="stylesheet" href="css/jquery.loadmask.css" />

<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.tabfont01 {
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}

.font051 {
	font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}

.font201 {
	font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}

.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}

html {
	overflow-x: auto;
	overflow-y: auto;
	border: 0;
}
</style>

<script type="text/javascript" src="jslib/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="jslib/jquery.form.js"></script>
<script type="text/javascript" src="jslib/jquery.loadmask.min.js"></script>
<script type="text/javascript" src="jslib/jquery.validate.min.js"></script>
<script type="text/javascript" src="my97date/WdatePicker.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.core.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.widget.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.button.js"></script>
<script type="text/javascript" src="jslib/jquery.bgiframe-2.1.2.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.mouse.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.draggable.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.position.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.dialog.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.effect.js"></script>
<script type="text/javascript" src="jslib/jquery.ui.resizable.js"></script>
<script type="text/javascript" src="jslib/search.js"></script>
<script type="text/javascript" src="jslib/data.js"></script>
<script type="text/javascript" src="jslib/dairy.js"></script>
<script type="text/javascript" src="jslib/main.js"></script>

<script type="text/javascript">
	function initPage() {
		initPageEvent();
		$('.jBtn').button().css('font-size','12px');
		
		$(".oprationTd").each(function(i, n) {
			if($(n).find(".unSetToLeaderA").length > 0){
				$(n).find(".setToLeaderA").hide();
			}
		});
	}

	function initPageEvent() {
	}

	$(document).ready(function() {
		initPage();
	});
</script>

</head>
<body>
	<div id="pageTitle">
		<table id="pagetitleTable">
			<tr>
				<td width="1024" height="55" background="image/nav04.gif">
					<table width="90%">
						<tr>
							<td width="90%" height="55" align="right"><input type="button" id="searchButton" class="right-button07" value="检索" onClick="searchToggle()" /></td>	<td><font color="#0000CC">刷新</font>&nbsp;&nbsp;&nbsp;<font color="#0000CC">退出</font></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="searchDiv">
		<form id="search_form" action="browse/dairy_select4AuditList" method="post">
			<table id="tableid" align="center">
				<tr>
					<th>连接</th>
					<th>检索项</th>
					<th>运算</th>
					<th>内容</th>
					<th>排序</th>
					<th>操作</th>
				</tr>
				<tr>
					<td><input type="hidden" name="search.logicalopts" value="" />
						<input id="pagenum" type="hidden" name="page.pageNum" value="1" />
					</td>
					<td><select name="search.columns" onChange="change(0,this)">
							<option value="-">---请选择要检索的单元---</option>
							<option value="name">名称</option>
							<option value="check">状态</option>
					</select></td>

					<td><select name="search.operators">
							<option value="eq">=</option>
							<option value="ne">&ne;</option>
							<option value="gt">&gt;</option>
							<option value="ge">&ge;</option>
							<option value="lt">&lt;</option>
							<option value="le">&le;</option>
							<option value="like">like</option>
					</select></td>

					<td id="td_0">
						<input name="search.values" type="text" />
					</td>
					<td>
						<select name="search.orders">
							<option value="none">不排序</option>
							<option value="asc">升序</option>
							<option value="desc">降序</option>
						</select>
					</td>
					<td align="left">
						<input type="button" class="jBtn" value="添加检索项" onClick="addSelect();" /> 
						<input type="button" class="jBtn" value="提交检索内容" onClick="searchIt();" />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="resultTd">
		<!--pageFlagStart-->
		<table id="mainpage" width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td>
					<table id="subtree1" style="DISPLAY:" width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
									<tr>
										<td height="40" class="font42">
											<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
												<tr>
													<td height="20" colspan="13" align="center" bgcolor="#EEEEEE" class="tablestyle_title">申诉</td>
												</tr>
												<tr>
													<td width="10%" align="center" bgcolor="#EEEEEE">名称</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">标题</td>
													<td width="10%" align="center" bgcolor="#EEEEEE">状态</td>
													<td width="20%" align="center" bgcolor="#EEEEEE">查看</td>
													<td width="20%" align="center" bgcolor="#EEEEEE">审核</td>
												</tr>
												<s:if test="objs.size() == 0">
													<tr align="center" id="noResult">
														<td bgcolor="#EEEEEE" colspan="9">没有可显示的信息</td>
													</tr>
												</s:if>
												<s:else>
													<s:iterator value="objs" var="obj" status="status">
														<tr align="center">
															<td bgcolor="#FFFFFF"><s:property value="#obj.name"/></td>
															<td bgcolor="#FFFFFF"><s:property value="#obj.title"/></td>
															<td bgcolor="#FFFFFF">
																<s:if test="%{#obj.check == 0}">
																	未审核
																</s:if>
																<s:elseif test="%{#obj.check == 1}">
																	审核通过
																</s:elseif>
																<s:elseif test="%{#obj.check == 2}">
																	审核不通过
																</s:elseif>
															</td>
															<td class="oprationTd" bgcolor="#FFFFFF">
																<a href="javascript:void(0);" onClick="show('<s:property value="#obj.id" />')">查看</a> 
															</td>
															<td class="oprationTd" bgcolor="#FFFFFF">
																<s:if test="%{#obj.check == 0}">
																	<a href="javascript:void(0);" onclick="auditPass('<s:property value="#obj.id"/>', this)">通过</a> 
																	<a href="javascript:void(0);" onclick="auditNotPass('<s:property value="#obj.id"/>', this)">不通过</a>
																</s:if>
																<s:elseif test="%{#obj.check == 1}">
																	<a href="javascript:void(0);" onclick="auditNotPass('<s:property value="#obj.id"/>', this)">不通过</a>
																</s:elseif>
																<s:elseif test="%{#obj.check == 2}">
																	<a href="javascript:void(0);" onclick="auditPass('<s:property value="#obj.id"/>', this)">通过</a> 
																</s:elseif>
															</td>
														</tr>
													</s:iterator>
												</s:else>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td height="6">
								<img src="image/spacer.gif" width="1" height="1" />
							</td>
						</tr>
						<tr>
							<td height="33">
								<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="right-font08">
									<tr>
										<td width="50%">
											共有 <span class="right-text09"><s:property value="page.rowCount" /></span>条记录 |
											第 <span class="right-text09"><s:property value="page.pageNum" /> / <s:property value="page.lastPage" /></span> 页
										</td>
										<td width="49%" align="right">
											[ <a href="javascript:void(0);" onClick="toThePage(1)" class="right-font08">首页</a>
											| 
											<s:iterator value="page.pages" var="obj" status="stat">
												<s:if test="#obj == page.pageNum">
													<span style="color: red;">
														<s:property value="#obj" />
													</span>
												</s:if>
												<s:else>
													<a href="javascript:void(0);" onClick="toThePage(<s:property value="#obj" />)"><s:property value="#obj" /></a>
												</s:else>
											</s:iterator>
											| 
											<a href="javascript:void(0);" onClick="toThePage(<s:property value="page.lastPage"/>)" class="right-font08">末页</a>]
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!--pageFlagEnd-->
	</div>
	<div id="operationDiv"></div>
</body>
</html>