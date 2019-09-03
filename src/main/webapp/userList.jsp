<%@page import="com.qf.book.pojo.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户信息管理</title>
<base href="<%=basePath%>">
<link rel="stylesheet" href="css/userlist.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">用户信息</h1>
	<table class="list">
		<th colspan="6">
			<form action="users/findByLike" method="post">
				<input type="text" placeholder="按用户名搜索" name="uName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">ID</td>
			<td>用户名</td>
			<td class="pswd">密码</td>
			<td>联系方式</td>
			<td>Email</td>
			<td>姓名</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${users }" var="user">
			<tr class="value-tr">
				<td class="value-td" id="id-p">${user.id }</td>
				<td class="value-td" >${user.uName }</td>
				<td class="value-td pswd">${user.uPwd }</td>
				<td class="value-td">${user.phone }</td>
				<td class="value-td">${user.email }</td>
				<td class="value-td">${user.name }</td>
				<td class="value-botton">
					<button type="button" class="update">修改</button>
					<button type="button" class="delete">删除</button>
				</td>
			<tr>
		</c:forEach>
	</table>


	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form id="form" class="form">
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th">
				<p>用户</p>
				<input type="text" name="uName" id="uName" class="uName"
					required="required" />
			</div>
			<div class="box-th pswd">
				<p>密码</p>
				<input type="password" name="uPwd" id="uPwd" class="uPwd" />
			</div>
			<div class="box-th">
				<p>手机</p>
				<input type="text" name="phone" id="phone" class="phone"
					required="required" />
			</div>
			<div class="box-th">
				<p>邮箱</p>
				<input type="text" name="email" id="email" class="email"
					required="required" />
			</div>
			<div class="box-th">
				<p>姓名</p>
				<input type="text" name="name" id="name" class="name"
					required="required" />
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
	/*封装关闭表单的方法  */
	function fun() {
		$(".box").css({
			"display" : "none"
		});
	
		$(".box-shadow").css({
			"display" : "none"
		});
	
		/* 清空残留数据 */
		$("#id").val("");
		$("#uName").val("");
		$("#uPwd").val("");
		$("#phone").val("");
		$("#email").val("");
		$("#name").val("");
		$(".form").removeClass("add_form").removeClass("update_form");
	}
	 
	 /*封装打开表单的方法  */
	function open(value){
		if ($(".box").css("display") == "none") {
			
			$(".box").css({
				"display" : "block"
			});
	
			if(value=="add"){
				
				$(".submit").attr({
					"value" : "添加"
				});
			}else{
				$(".submit").attr({
					"value" : "修改"
				});
			}
	
			$(".form").addClass(value+"_form");
			
			$(".box-shadow").css({
				"display" : "block"
			});
		}
	 }
	
	//关闭窗口
	$("#close").click(function() {
		fun();
	});

	/*添加按钮*/
	$("#add").click(function() {
		    open("add");
			//添加,删除用户名上的只读属性
			$("#uName").removeAttr("readOnly");
			$(".add_form").off().submit(function(event) {
				event.preventDefault();
				$.ajax({
					type : "post",
					url : "users/addUser",
					data : $("#form").serialize(),
					dataType : "json",
					async : true,
					success : function(data) {
						if(data.msg=="success"){
							fun();
							$(".list").append("<tr class='value-tr'><td id='id-p' class='value-td'>"+data.user.id+
									"</td><td class='value-td'>"+data.user.uName+"</td><td class='value-td pswd'>"+
									data.user.uPwd+"</td><td class='value-td'>"+data.user.phone +"</td><td class='value-td'>"+
									data.user.email+"</td><td class='value-td'>"+data.user.name+"</td><td class='value-botton'>"+
									"<input type='button' class='update' value='修改'/> "+
									"<input type='button' class='delete' value='删除'/></td></tr>")
						}else{
							alert(data.msg);	
						}
					}
				});
			})
	})

	//点击修改按钮
	$(".list").on("click",".update",function() {
		open("update");
		//获取值
        var id = $(this).parents("tr").find("td").eq(0).html();
		var uName = $(this).parents("tr").find("td").eq(1).html();
		var uPwd = $(this).parents("tr").find("td").eq(2).html();
		var phone = $(this).parents("tr").find("td").eq(3).html();
		var email = $(this).parents("tr").find("td").eq(4).html();
		var name = $(this).parents("tr").find("td").eq(5).html();
		var _that =$(this);
		//先给前端修改页面显示：修改数据
		$("#id").val(id);
		$("#uName").val(uName);
		//设置用户名为只读
		$("#uName").attr("readOnly","readOnly");
		$("#uPwd").val(uPwd);
		$("#phone").val(phone);
		$("#email").val(email);
		$("#name").val(name);
		//修改
		$(".update_form").off().submit(function(evt){
			evt.preventDefault();
			$.ajax({
				type:"post",
				url:"users/updateUser",
				data:$("#form").serialize(),
				dataType:"json",
				success:function(data){
					if(data.msg=="success"){
						fun();
						_that.parents("tr").find("td").eq(0).html(data.user.id);
						_that.parents("tr").find("td").eq(1).html(data.user.uName);
						_that.parents("tr").find("td").eq(2).html(data.user.uPwd);
						_that.parents("tr").find("td").eq(3).html(data.user.phone);
						_that.parents("tr").find("td").eq(4).html(data.user.email);
						_that.parents("tr").find("td").eq(5).html(data.user.name);
					}else{
						alert(data.msg);	
					}
				}
			});
		})
	})

	//删除
	$(".list").on("click",".delete",function() {
		var uId = $(this).parents("tr").find("td").eq(0).html();
		var _that = $(this);
		$.ajax({
			type : "get",
			url : "users/deleteUser?uId=" + uId,
			dataType : "json",
			success : function(data) {
				if(data.msg=="success"){
					_that.parents("tr").remove();
				}else{
					alert(data.msg);	
				}
			}
		});
	})
</script>
<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>