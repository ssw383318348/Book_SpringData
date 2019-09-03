<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>修改密码</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/info.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<div class="box">
		<form  id="form">
			<div class="box-th">
				<p>旧密码</p>
				<input type="password" name="pwd" id="pwd" required="required" /> <span
					id="old"></span>
			</div>
			<div class="box-th">
				<p>新密码</p>
				<input type="password" name="newpwd" id="newpwd" required="required" />
				<span id="newp"></span>
			</div>
			<div class="box-th">
				<p>确认密码</p>
				<input type="password" name="repwd" id="repwd" required="required" />
				<span id="re"></span>
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
</body>
<script type="text/javascript">
		var index = false;
		var index1 = false;
		var index2 = false;
		$("#pwd").change(function() {
			var pwd = $(this).val();
			$.ajax({
				type: "get",
				url: "users/old?pwd=" + pwd,
				dataType: "json",
				success: function(data) {
					if(data.msg == "success") {
						index = true;
						$("#old").css({
							color: "green"
						})
						$("#old").html("ACCPET");
					} else {
						index = false;
						$("#old").css({
							color: "red"
						})
						$("#old").html("ERROR");
					}
				}
			});
		})

		$("#repwd").change(function() {
			var newpwd = $("#newpwd").val();
			var repwd = $(this).val();
			if(newpwd != repwd) {
				index2 = false;
				$("#re").css({
					color: "red"
				})
				$("#re").html("ERROR");
			}else{
				index2=true;
				$("#re").css({
					color: "green"
				})
				$("#re").html("ACCPET");
			}
		})
		
		$("#form").off().submit(function(event){
			event.preventDefault();
			/* alert(index);
			alert(index2); */
			if(index==true&&index2==true){
				$.ajax({
					type:"post",
					url:"users/updatePwd",
					data:$("#form").serialize(),
					dataType:"json",
					success:function(data){
						if(data.msg=="success"){
							top.location.href="login.jsp";
						}else{
							alert(data.msg);
						}
					}
				});
			}
		})
	</script>
<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>