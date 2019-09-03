<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>图书管理系统</title>
<base href="<%=basePath%>">
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jQuery.js"></script>
<script type="text/javascript" src="js/fun.base.js"></script>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
	jQuery(function($){
		$("#test").click(function(){
			$.ajax({
				url:"users/test",
				success:function(data){
					window.location.href="${pageContext.request.contextPath}/login.jsp";
					if(data.msg == "success"){
						window.location.href="${pageContext.request.contextPath}/login.jsp"
					}else{
						$("#error").css("display","block");
					}
				}
			});
		})
	});
</script>


</head>
	<body>
		<input type ="button" id="test" value="点我啊！"/>
		<span id="error" style ="display:none">用户名或密码错误！</span>
	</body>
</html>