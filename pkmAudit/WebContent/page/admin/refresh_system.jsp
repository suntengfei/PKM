<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="org.springframework.context.ApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="org.springframework.beans.factory.FactoryBean" %>    
<%@ page import="org.springframework.security.web.access.intercept.FilterSecurityInterceptor" %>    
<%@ page import="org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource" %>    

<%
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
	FactoryBean factoryBean = (FactoryBean)ctx.getBean("&filterInvocationSecurityMetadataSource");
	FilterInvocationSecurityMetadataSource fisms = (FilterInvocationSecurityMetadataSource)factoryBean.getObject();
	FilterSecurityInterceptor filter = (FilterSecurityInterceptor)ctx.getBean("filterSecurityInterceptor");
	filter.setSecurityMetadataSource(fisms);
%>

<jsp:forward page="/"/>