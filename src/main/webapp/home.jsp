<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <head>
		<meta charset="UTF-8">
		<title>主页</title>
		<base href="<%=basePath%>">
		<link rel="stylesheet" href="css/home.css" />
	</head>
	<body>
		<img src="img/home.jpg" class = "home"/>
	</body>
	<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>