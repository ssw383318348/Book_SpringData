<%@page import="com.qf.book.vo.Message"%>
<%@page import="com.qf.book.pojo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8" />
<title>图书管理后台</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<div class="book-left">
		<!--左边标题模块-->
		<div class="logoDiv">
			<img src="img/logo.png" class="logoImg" />
			<p class="logoTitle">图书管理</p>
		</div>
		<!--用户模块-->
		<div class="user_name">
			<h4 class="userName">用户:${msg1.user.name}</h4>
			<a href="login.jsp" class="back_login">退出登录</a>
		</div>
		<!--菜单模块-->
		<div class="menu">
			<h4 class="book-title">图书管理</h4>
			<div class="navi book">
				<button class="btn" id="bookKind">图书分类</button>
			</div>
			<div class="navi book">
				<button class="btn" id="bookInfo">图书信息</button>
			</div>
			<div class="navi book">
				<button class="btn" id="borrowBook">借阅管理</button>
			</div>
			<div class="navi book">
				<button class="btn" id="returnBook">还书管理</button>
			</div>
			<h4 class="user-title">用户管理</h4>
			<div class="navi user">
				<button class="btn" id="userInfo">用户信息</button>
			</div>
			<div class="navi user">
				<button class="btn" id="upadtePasw">修改密码</button>
			</div>
		</div>
	</div>
	<div class="book-right">
		<iframe class="iframes" id="iframes" src="home.jsp"></iframe>
	</div>

</body>
<script type="text/javascript">
	/*设置点击动画  slideToggle() 方法通过使用滑动效果（高度变化）来切换元素的可见状态*/
	$(".book-title").click(function() {
		$(".book").slideToggle(500);
	});

	$(".user-title").click(function() {
		$(".user").slideToggle(500);
	});
	/*--------------------------用户模块--------------------------------------------*/
	$("#userInfo").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "users/findAll";
	});

	$("#upadtePasw").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "password.jsp";
	});
	/*--------------------------图书模块--------------------------------------------*/
	$("#bookInfo").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "books/findAll";
	});

	$("#bookKind").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "kinds/findAll";
	});
	
	$("#borrowBook").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "borrow.jsp";
	});

	$("#returnBook").click(function() {
		var iframes = document.getElementById("iframes");
		/* 点击访问 获取用户接口 ，成功跳转 成功页面显示 */
		iframes.src = "returns/findAll";
	});
</script>
<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>
