<%@page import="com.qf.book.pojo.Record"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>归还</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/userlist.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">还书管理</h1>
	<table class="list">
		<th colspan="6">
			<form action="returns/findByLike" method="post">
				<input type="text" placeholder="按用户名搜索" name="uName"
					id="search-input" class="search-input" /> <input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">Id</td>
			<td>图书书名</td>
			<td>借书用户</td>
			<td>借书时间</td>
			<td>还书时间</td>
			<td>状态</td>
			<td>还书操作</td>
		</tr>

		<c:forEach items="${records }" var="record">
			<tr class="value-tr">
				<td class="value-td" id="id-p">${record.id }</td>
				<td class="value-td">${record.bName }</td>
				<td class="value-td">${record.uName }</td>
				<td class="value-td">${record.borrowDate }</td>
				<td class="value-td">${record.returnDate }</td>
				<td class="value-td">${record.state }</td>
				<td class="value-botton">
					<button type="button" class="update">归还</button>
				</td>
			<tr>
		</c:forEach>
	</table>

	<script type="text/javascript">
		/*处理json时间格式问题  */
		function getDateTime(jsondate) {
			var date = new Date(parseInt(jsondate));
			var year = date.getFullYear();
			var month = date.getMonth() + 1;
			var day = date.getDate();
			var hh = date.getHours();
			var mm = date.getMinutes();
			var ss = date.getSeconds();
			if (month < 10) {
				month = "0" + month;
			}
			if (day < 10) {
				day = "0" + day;
			}
			if (hh < 10) {
				hh = "0" + hh;
			}
			if (mm < 10) {
				mm = "0" + mm;
			}
			if (ss < 10) {
				ss = "0" + ss;
			}
			return year + "-" + month + "-" + day + " " + hh + ":" + mm + ":"
					+ ss + ".0";
		}

		$(".update").click(
				function() {
					var rId = $(this).parents("tr").find("td").eq(0).html();
					var _that = $(this);
					$.ajax({
						type : "get",
						url : "returns/returnBook?rId=" + rId,
						dataType : "json",
						async : true,
						success : function(data) {
							if (data.msg == "success") {
								_that.parents("tr").find("td").eq(0).html(
										data.record.id);
								_that.parents("tr").find("td").eq(1).html(
										data.record.bName);
								_that.parents("tr").find("td").eq(2).html(
										data.record.uName);
								var date1 = getDateTime(data.record.borrowDate);
								_that.parents("tr").find("td").eq(3).html(
										date1);
								var date2 = getDateTime(data.record.returnDate);
								_that.parents("tr").find("td").eq(4).html(
										date2);
								_that.parents("tr").find("td").eq(5).html(
										data.record.state);
							} else {
								alert(data.msg);
							}
						}
					});
				})
	</script>
	<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>