<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery-2.1.1.min.js">
<script type="text/javascript" src="js/nohistory.js">
</script>
<script type="text/javascript">
	jQuery(function($) {
		 var user = "${msg1}";
		if (!user) {
			top.location.href= "login.jsp";
		} 
	});
</script>