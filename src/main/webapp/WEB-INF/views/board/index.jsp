<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>board</title>
	
	<!-- 
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	Bootstrap CSS
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" 
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
	
	<style type="text/css">
		body {
			padding-top: 70px;
			padding-bottom: 30px;
		}
	</style>
	 -->
	 <c:url var="getBoardListURL" value="/board/getBoardList"></c:url>
	<script>
		var data = [];
		$(document).on('click', '#btnWriteForm', function(e){
			e.preventDefault(); //html 에서 a 태그나 submit 태그는 고유의 동작이 있다. 페이지를 이동시킨다거나 form 안에 있는 input 등을 전송한다던가 그러한 동작이 있는데 e.preventDefault 는 그 동작을 중단시킨다.
			location.href = "${pageContext.request.contextPath}/board/boardForm"; // 컨텍스트가 바뀌거나 해도 소스 수정없이 처리하기 위해 pageContext.request.contextPath 사용
		});

		//url로 event 발생. -> 여기서 page / range도 같이 넘겨줄 것
		/*
		function fn_contentView(bid){
			var url = "${pageContext.request.contextPath}/board/getBoardContent";
			url = url + "?bid=" + bid;
			location.href = url;
		}
		*/
		
		//2020.09.21 pms
		function fn_contentView(bid, page, range){
			console.log('showReplyList');
			var url = "${pageContext.request.contextPath}/board/getBoardContent";
			url = url + "?bid=" + bid;
			var paramData = { 
					"bid" : bid,
					"page" : page,
					"range" : range
					};
			$.ajax({
	            type: 'POST',
	            url: 'setPageRange.do',
	            data: paramData,
	            dataType: 'json',
	            success: function(result) {
            		location.href = url;
	           	},	   // Ajax success end
	           	error: function (request, status, error){
		   			console.log('error!');
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			});	// Ajax end
		}
		
		//이전 버튼 이벤트
		function fn_prev(page, range, rangeSize) {
				var page = ((range - 2) * rangeSize) + 1;
				var range = range - 1;
				var url = "${pageContext.request.contextPath}/board/getBoardList";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				location.href = url;
			}
		  //페이지 번호 클릭
			function fn_pagination(page, range, rangeSize, searchType, keyword) {
				var url = "${pageContext.request.contextPath}/board/getBoardList";
				url = url + "?page=" + page;
				url = url + "&range=" + range;
				location.href = url;	
			}
			//다음 버튼 이벤트
			function fn_next(page, range, rangeSize) {
				var page = parseInt((range * rangeSize)) + 1;
				var range = parseInt(range) + 1;

				var url = "${pageContext.request.contextPath}/board/getBoardList";
				url = url + "?page=" + page;
				url = url + "&range=" + range;

				location.href = url;
			}
			
			//검색 버튼 이벤트
			$(document).on('click', '#btnSearch', function(e){
				e.preventDefault();
				var url = "${getBoardListURL}";
				url = url + "?searchType=" + $('#searchType').val();
				url = url + "&keyword=" + $('#keyword').val();
				location.href = url;
				console.log(url);
			});
			
			//Enter key function
			function enterkey() {
		        if (window.event.keyCode == 13) {
		        	console.log('press enterKey');
		        	var url = "${getBoardListURL}";
					url = url + "?searchType=" + $('#searchType').val();
					url = url + "&keyword=" + $('#keyword').val();
					location.href = url;
					console.log(url);
		        }
			}



	</script>
</head>

<body>
	<article>
		<div class="container">
			<h2>board list</h2>
			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<colgroup>
						<col style="width:5%;" />
						<col style="width:auto;" />
						<col style="width:15%;" />
						<col style="width:10%;" />
						<col style="width:15%;" />
						<col style="width:15%;" />
					</colgroup>
					<thead>
						<tr>
							<th>NO</th>
							<th>글제목</th>
							<th>작성자</th>
							<th>조회수</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<tbody>
						<c:choose>
							<c:when test="${empty boardList }" >
								<tr><td colspan="5" align="center">데이터가 없습니다.</td></tr>
							</c:when> 
							<c:when test="${!empty boardList}">
								<c:forEach var="list" items="${boardList}">
									<tr>
										<td><c:out value="${list.bid}"/></td>
										<td>
										<!-- href는 동작하지 않게 설정-> href는 주소값으로 이동.. onClick으로 script function을 사용할 수 있도록 설정. 매개변수를 받아야 하므로. -->
											<a href="#" onClick="fn_contentView(<c:out value="${list.bid}"/>,<c:out value="${pagination.page}"/>, <c:out value="${pagination.range}"/>);">
												<c:out value="${list.title}"/>
											</a>
										</td>
										<td><c:out value="${list.reg_id}"/></td>
										<td><c:out value="${list.view_cnt}"/></td>
										<td><c:out value="${list.reg_dt}"/></td>
										<td><c:out value="${list.edit_dt}"/></td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnWriteForm">글쓰기</button>
			</div>
			
			</br>
			<!-- pagination{s} -->
			<div id="paginationBox">
				<ul class="pagination">
					<c:if test="${pagination.prev}">
						<li class="page-item">
						<a class="page-link" href="#" onClick="fn_prev('${pagination.page}', '${pagination.range}', '${pagination.rangeSize}')">Previous</a>
						</li>
					</c:if>
		
					<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" var="idx">
						<li class="page-item <c:out value="${pagination.page == idx ? 'active' : ''}"/> ">
						<a class="page-link" href="#" onClick="fn_pagination('${idx}', '${pagination.range}', '${pagination.rangeSize}')"> ${idx} </a>
						</li>
					</c:forEach>
		
					<c:if test="${pagination.next}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${pagination.range}', 
						'${pagination.range}', '${pagination.rangeSize}')" >Next</a>
						</li>
					</c:if>
				</ul>
			</div>
			<!-- pagination{e} -->
			
			<!-- search{s} -->
		<div class="form-group row justify-content-center">
			<div class="w100" style="padding-right:10px">
				<select class="form-control form-control-sm" name="searchType" id="searchType">
					<option value="title">제목</option>
					<option value="Content">본문</option>
					<option value="reg_id">작성자</option>
				</select>
			</div>
			<div class="w300" style="padding-right:10px">
				<input type="text" class="form-control form-control-sm" name="keyword" id="keyword" onkeyup="enterkey();">
			</div>
			<div>
				<button class="btn btn-sm btn-primary" name="btnSearch" id="btnSearch">검색</button>
			</div>
		</div>
		<!-- search{e} -->

		</div>
	</article>

</body>

</html>
