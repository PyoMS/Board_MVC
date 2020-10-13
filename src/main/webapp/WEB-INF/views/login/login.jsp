<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script> 
			function fn_btnSignupClick(){ 
				location.href ="${pageContext.request.contextPath}/login/signupForm"; 
			} 
		</script>
	</head>
	
	<body>
		<!-- login form {s} --> 
		<form:form class="form-signin" name="form" id="form" role="form" modelAttribute="userVO" 
		method="post" action="${pageContext.request.contextPath}/board/saveBoard"> 
			<div class="text-center mb-4"> 
				<h1 class="h3 mb-3 font-weight-normal">PMS.COM</h1> 
			</div> 
			<div class="form-label-group"> 
				<form:input path="uid" id="uid" class="form-control" placeholder="User ID" required="" autofocus="" />
				<label for="uid" class="sr-only">User ID</label> 
			</div> 
			<div class="form-label-group"> 
				<form:password path="pwd" id="pwd" class="form-control" placeholder="User Password" required="" /> 
				<label for="pwd" class="sr-only">User Password</label> 
			</div> 
			<button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button> 
			<p class="mt-5 mb-3 text-muted text-center">© 2020. PMS. All rights reserved.</p> 
		</form:form> 
		
		<!-- login form {e} -->
		<span style="font-size:11pt;"> 
			<a href="#" onClick="fn_btnSignupClick()">Sign up</a> 
		</span> 
	</body>
</html>
	
	
	
	


