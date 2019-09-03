<%@page import="com.qf.book.pojo.Book"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>图书信息</title>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css" href="css/userlist.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">图书信息管理</h1>
	<table class="list">
		<th colspan="9">
			<%--在这里进行与Controller的交互 action：相对路劲--%>
			<form action="books/findByLike" method="post">
				<input type="text" placeholder="按书名名搜索" name="bName"
					id="search-input" class="search-input" />
				<input type="submit"
					id="search-button" value="搜索"></input>
			</form>
		</th>
		<tr class="title-tr">
			<td id="id-p">Id</td>
			<td>分类</td>
			<td>图书书名</td>
			<td>图书作者</td>
			<td>图书简介</td>
			<td>图书图片</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${books }" var="book">
			<tr class="value-tr">
				<td class="value-td" id="id-p">${book.id }</td>
				<td class="value-td">${book.kind.type }</td>
				<td class="value-td">${book.bName }</td>
				<td class="value-td">${book.author }</td>
				<td class="value-td">${book.intro }</td>
				<td class="value-td"><img class="yu" src="${book.address }" /></td>
				<td class="value-botton">
					<input type="button" class="update" value="修改">
					<input type="button" class="delete" value="删除">
				</td>
			<tr>
		</c:forEach>
	</table>
	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<!-- 文件上传的enctype类型必须为：multipart/form-data -->
		<!-- 因为该类型会将form表单中的文件，单选，文本框等内容分层传输，这样服务端才会知道什么是文件，什么是字符串 -->
		<form id="form" class="form" enctype="multipart/form-data">
			<button type="button" class="close" id="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" class="id" />
			</div>
			<div class="box-th">
				<p>分类</p>
				<input type="text" name="type" id="type" class="type"
					required="required" />
			</div>
			<div class="box-th">
				<p>图书书名</p>
				<input type="text" name="bName" id="bName" class="bName"
					required="required" />
			</div>
			<div class="box-th">
				<p>图书作者</p>
				<input type="text" name="author" id="author" class="author"
					required="required" />
			</div>
			<div class="box-th">
				<p>图书简介</p>
				<input type="text" name="intro" id="intro" class="intro"
					required="required" />
			</div>
			<div class="box-th">
				<p>相关图片</p>
				<input type="file" name="file" id="file" class="file"
					 />
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
		$("#type").val("");
		$("#bName").val("");
		$("#author").val("");
		$("#intro").val("");
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
		$(".add_form").off().submit(function(event){
			event.preventDefault();
			var fd = new FormData(document.getElementById("form"));
			$.ajax({
				//form表单携带有文件传输必须设置为post提交
				//为什么？
				type:"post",
				url:"books/addBook",
				data:fd,
				dataType:"json",
				encType:"multipart/form-data",
				contentType:false,
				processData:false,
				success:function(data){
					if(data.msg=="success"){
						fun();
						$(".list").append("<tr class='value-tr'><td class='value-td' id='id-p'>"+data.book.id+
								"</td><td class='value-td'>"+data.book.type+"</td><td class='value-td'>"+
								data.book.bName+"</td><td class='value-td'>"+data.book.author +"</td><td class='value-td'>"+
								data.book.intro+"</td><td class='value-td'><img class='yu' src="+data.book.address+" /></td><td class='value-botton'>"+
								"<input type='button' class='update' value='修改'/> "+
								"<input type='button' class='delete' value='删除'/></td></tr>")
					}else{
						alert(data.msg);
					}
				}
			});
		})
	})

	/*更新按钮*/
	$(".list").on("click",".update",function() {
		open("update");
		var id = $(this).parents("tr").find("td").eq(0).html();
		var type = $(this).parents("tr").find("td").eq(1).html();
		var bName = $(this).parents("tr").find("td").eq(2).html();
		var author = $(this).parents("tr").find("td").eq(3).html();
		var intro = $(this).parents("tr").find("td").eq(4).html();
		var _that =$(this);
		//先给前端修改页面显示：修改数据
		$("#id").val(id);
		$("#type").val(type);
		$("#bName").val(bName);
		$("#author").val(author);
		$("#intro").val(intro);
		$(".update_form").off().submit(function(event){
			event.preventDefault();
			var fd = new FormData(document.getElementById("form"));
			$.ajax({
				type:"post",
				url:"books/updateBook",
				data:fd,
				dataType:"json",
				encType:"multipart/form-data",
				contentType:false,
				processData:false,
				success:function(data){
					if(data.msg=="success"){
						fun();
						_that.parents("tr").find("td").eq(0).html(data.book.id);
						_that.parents("tr").find("td").eq(1).html(data.book.type);
						_that.parents("tr").find("td").eq(2).html(data.book.bName);
						_that.parents("tr").find("td").eq(3).html(data.book.author);
						_that.parents("tr").find("td").eq(4).html(data.book.intro);
						_that.parents("tr").find("td").eq(5).find("img").attr({
							"src":data.book.address
						});
					}else{
						alert(data.msg);
					}
					
				}
			});
		})
	})
	
	/*删除按钮*/
	$(".list").on("click",".delete",function() {
		var bId = $(this).parents("tr").find("td").eq(0).html();
		var _that=$(this);
		$.ajax({
			type : "get",
			url : "books/deleteBook?bId=" + bId,
			dataType : "json",
			async : true,
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