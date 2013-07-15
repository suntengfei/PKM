<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<form id="update_form" action="" method="post" class="cssform">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
											<td colspan="9" align="center" bgcolor="#EEEEEE" class="tablestyle_title">申诉信息</td>
										</tr>
										<tr>
											<td width="15%" align="right" bgcolor="#FFFFFF">编号</td>
											<td width="85%" align="left" bgcolor="#FFFFFF"><s:property value="obj.id"/></td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">名称</td>
											<td bgcolor="#FFFFFF"><s:property value="obj.name"/></td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">标题</td>
											<td bgcolor="#FFFFFF"><s:property value="obj.title"/></td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">内容</td>
											<td bgcolor="#FFFFFF">
												<TEXTAREA rows="2" cols="50"><s:property value="obj.content"/></TEXTAREA>
											</td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">日期</td>
											<td bgcolor="#FFFFFF"><s:property value="obj.time"/></td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">e日期</td>
											<td bgcolor="#FFFFFF"><s:property value="obj.etime"/></td>
										</tr>
										<tr>
											<td align="right" bgcolor="#FFFFFF">状态</td>
											<td bgcolor="#FFFFFF">
												<s:if test="%{obj.check == 0}">
													未审核
												</s:if>
												<s:elseif test="%{obj.check == 1}">
													审核通过
												</s:elseif>
												<s:elseif test="%{obj.check == 2}">
													审核不通过
												</s:elseif>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>