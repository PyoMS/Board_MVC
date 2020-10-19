<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> --%>
<%-- <%@ include file="/WEB-INF/views/layout/header.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<script> 
			function fn_btnSignupClick(){ 
				location.href ="${pageContext.request.contextPath}/login/signupForm"; 
			} 
			
			function fn_Login(uid, pwd){
				console.log(uid); console.log(pwd);
				var paramData ={
						"uid" : uid,
						"pwd" : pwd
				}
				$.ajax({
					url : "${pageContext.request.contextPath}/board/getLoginBoardList" ,
					type : "POST", 
					//dataType : "json" , //controller -> jsp
					dataType : "json",
					//data    : JSON.stringify(paramData),
					data : paramData , 
					success : function(result){
						console.log('success');
						alert(result.alert);
// 						if (data.result=="1"){
// 							console.log(1);
// 							dupButton = 1;
// 							alert('사용할 수 있는 아이디 입니다.');
// 						}
// 						else { // result == 0
// 							console.log(0);
// 							dupButton = 0;
// 							alert('사용할 수 없는 아이디 입니다.');
// 							$("#uid").val('');
// 						} 
					},
					error: function (request, status, error){
			   			console.log('error!');
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
			}
			
				
		</script>
	</head>
	
	<body>
		<!-- login form {s} --> 
		<form:form class="form-signin" name="form" id="form" role="form" modelAttribute="userVO"
 		method="POST" action="${pageContext.request.contextPath}/board/getLoginBoardList">
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
	
	
	
	


