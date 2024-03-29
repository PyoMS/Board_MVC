<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<!-- jQuery -->
	<!-- 
	<script src="https://code.jquery.com/jquery-latest.min.js" 
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	 -->
	 <!-- 
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" 
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	-->
	<!-- 2020.09.01 version 3.5.1 pms -->
	<script
	  src="https://code.jquery.com/jquery-3.5.1.js"
	  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	  crossorigin="anonymous">
		console.log('@tiles header');
	</script>
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
	
	<!-- common CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/common/css/common.css">
	
	<!-- 메뉴바  -->
	<!--  
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	  <a class="navbar-brand" href="#">Board Test</a>
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarsExample03">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="#">Link</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link disabled" href="#">Disabled</a>
	      </li>
	      <li class="nav-item dropdown">
	        <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
	        <div class="dropdown-menu" aria-labelledby="dropdown03">
	          <a class="dropdown-item" href="#">Action</a>
	          <a class="dropdown-item" href="#">Another action</a>
	          <a class="dropdown-item" href="#">Something else here</a>
	        </div>
	      </li>
	    </ul>
	    <form class="form-inline my-2 my-md-0">
	      <input class="form-control" type="text" placeholder="Search">
	    </form>
	  </div>
	</nav>
	-->
	<!-- 수정 후 nav 태그 -->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark"> 
		<a class="navbar-brand" href="#">PMS.COM</a> 
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation"> 
			<span class="navbar-toggler-icon"></span> 
		</button> 
		<div class="collapse navbar-collapse" id="navbarsExample03"> 
			<ul class="navbar-nav mr-auto"> 
				<li class="nav-item"> 
					<a class="nav-link" href="${pageContext.request.contextPath}/board/getBoardList">Main</a> 
				</li> 
				<li class="nav-item"> 
					<a class="nav-link" href="${pageContext.request.contextPath}/menu/getMenu">Menu</a> 
				</li> 
				<li class="nav-item"> 
					<a class="nav-link" href="${pageContext.request.contextPath}/login/login">Logout</a> 
				</li> 
			</ul> 
			<form class="form-inline my-2 my-md-0"> 
				<input class="form-control" type="text" placeholder="Search"> 
			</form> 
		</div> 
	</nav>


