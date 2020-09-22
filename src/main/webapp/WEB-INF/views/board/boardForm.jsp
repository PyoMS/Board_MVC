<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<title>Insert title here</title>
		<script>
		$(document).on('click', '#btnSave', function(e){
			e.preventDefault();
			$("#form").submit();
		});
	
		$(document).on('click', '#btnList', function(e){
			e.preventDefault();
			location.href="${pageContext.request.contextPath}/board/getBoardList";
		});
		
		<% 	pageContext.setAttribute("LF", "\n"); 
			pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
	      	pageContext.setAttribute("br", "<br/>"); //br 태그
	      %>
		
		//입력폼에 수정정보 입력
		$(document).ready(function(){
		var mode = '<c:out value="${mode}"/>';
		if ( mode == 'edit'){
			//입력 폼 셋팅
			$("#reg_id").prop('readonly', true);	// .prop : 속성값 가져옴.
			$("input:hidden[name='bid']").val(<c:out value="${boardContent.bid}"/>);
			$("input:hidden[name='mode']").val('<c:out value="${mode}"/>');
			$("#reg_id").val('<c:out value="${boardContent.reg_id}"/>');
			$("#title").val('<c:out value="${boardContent.title}"/>');
			$("#content").val('<c:out value="${fn:replace(boardContent.content, crcn, '<br>')}"/>'); // 2020.09.21 엔터키 입력된 값이 출력될 시 error 발생.
			$("#tag").val('<c:out value="${boardContent.tag}"/>');
		}
	});
		</script>
		
	</head>

	<body>
		<article>
			<div class="container" role="main">
				<h2>board Form</h2>
				<form:form name="form" id="form" role="form" modelAttribute="boardVO" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
					<form:hidden path="bid" />
					<input type="hidden" name="mode" />	<!-- boardVO에는 mode라는 프로퍼티를 갖고 있지 않기 때문에 기본 html 태그 사용 -->
					
					<div class="mb-3">
						<label for="title">제목</label>
						<form:input path="title" type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요"/>
					</div>
					
					<div class="mb-3">
						<label for="reg_id">작성자</label>
						<form:input path="reg_id" type="text" class="form-control" name="reg_id" id="reg_id" placeholder="이름을 입력해 주세요"/>
					</div>
					
					<div class="mb-3">
						<label for="content">내용</label>
						<form:textarea path="content" class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"/>
					</div>
					
					<div class="mb-3">
						<label for="tag">TAG</label>
						<form:input path="tag" type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요"/>
					</div>
				</form:form>
				<div >
					<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
					<button type="button" class="btn btn-sm btn-primary" id="btnList">목록</button>
				</div>
			</div>
		</article>
	</body>


</html>