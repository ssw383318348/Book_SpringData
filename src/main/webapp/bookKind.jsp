<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String basePath = request.getScheme() + "://" + request.getServerName() + 
			":" + request.getServerPort() + request.getContextPath() + "/";%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>图书分类</title>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="css/userlist.css" />
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
</head>

<body>
	<h1 class="title">图书分类</h1>
	<table class="list">
		<tr class="title-tr">
			<td>Id</td>
			<td>类型</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${kinds }" var="kind">
			<tr class="value-tr">
				<td class="value-td">${kind.id }</td>
				<td class="value-td">${kind.type }</td>
				<td class="value-botton">
					<input type="button" class="update" value="修改"> 
					<input type="button" class="delete" value="删除">
				</td>
			</tr>
		</c:forEach>
	</table>

	<button type="button" class="add" id="add">增加</button>
	<div class="box">
		<form  id="form" class="form">
			<button type="button" id="close" class="close">关闭</button>
			<div>
				<p id="id-p">ID</p>
				<input type="text" name="id" id="id" />
			</div>
			<div class="box-th">
				<p>图书类别名</p>
				<input type="text" name="type" id="type" required="required" />
			</div>
			<input type="submit" class="submit" value="提交" />
		</form>
	</div>
	<div class="box-shadow"></div>
</body>
<script type="text/javascript">
$(function(){
     /*封装关闭表单的放法  */
	function fun(){
    	$(".box").css({
 			"display" : "none"
 		});

 		$(".box-shadow").css({
 			"display" : "none"
 		});
 		/* 清空残留数据 */
 		$("#id").val("");
 		$("#type").val("");
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
     
     
     
     
	/*关闭按钮*/
	$("#close").click(function() {
		fun();
	});

	/*添加按钮*/
	$("#add").click(function() {
		    open("add");
			//绑定表单提交
			$(".add_form").off().submit(function(event){
				event.preventDefault();
				$.ajax({
					type:"post",
					url:"kinds/addKind",
					data:$("#form").serialize(),
					dataType:'json',
					async:true,
					success:function(data){
						if(data.msg=="success"){
							fun();
							$(".list").append("<tr class='value-tr'><td class='value-td'>"+data.kind.id+
							"</td><td class='value-td'>"+data.kind.type+"</td><td class='value-botton'>"+
							"<input type='button' class='update' value='修改'/> "+
							"<input type='button' class='delete' value='删除'/></td></tr>");
						}else{
							alert(data.msg);
						}; 
					}
				});
			})
	})

	/*更新按钮*/
	//事件委派
	$(".list").on("click", ".update", function(){
		open("update");
		var id = $(this).parents("tr").find("td").eq(0).html();
		var type = $(this).parents("tr").find("td").eq(1).html();
		var _that = $(this);
		$("#id").val(id);
		$("#type").val(type);
		//绑定按钮提交
		$(".update_form").off().submit(function(event){
			event.preventDefault();
         	$.ajax({
         		type:"post",
         		url:"kinds/updateKind",
         		data:$("#form").serialize(),
         		dataType:'json',
         		async:true,
         		success:function(data){
         			if(data.msg=="success"){
         				 fun();
              			_that.parents("tr").find("td").eq(1).html(data.kind.type);
         			}else{
         				alert(data.msg);
         			}
         		},
         	});
         })
	})
	
	/*删除按钮*/
	$(".list").on("click",".delete",function(){
			var kId =  $(this).parents("tr").find("td").eq(0).html();
			var _that= $(this);
			$.ajax({
				type:"get",
				url:"kinds/deleteKind?kId="+kId,
				dataType:"json",
				async:true,
				success:function(data){
					if(data.msg=="success"){
						fun();
						_that.parents("tr").remove();
					}else{
					alert(data.msg);
					}
				}
			});
		})
});
</script>
<!-- 引入判断是否登录的jsp -->
<%@include file ="existUser.jsp" %>
</html>