<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>board</title>
		<script>
			$(document).ready(function(){
				showReplyList();
				console.log('ready');
			});
			
			//reply List(s)
			function showReplyList(){
				console.log('showReplyList');
				var url = "${pageContext.request.contextPath}/board/getReplyList";
				var paramData = {"bid" : "${boardContent.bid}"};
				console.log('what is boardContent : ${boardContent.bid}');
				$.ajax({
		            type: 'POST',
		            url: url,
		            data: paramData,
		            dataType: 'json',
		            success: function(result) {
			            var htmls = "";
			            console.log('result.length : ' + result.length);
						if(result.length < 1){
							htmls.push("등록된 댓글이 없습니다.");
						} else {
							
			                    $(result).each(function(){
			                     htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';
			                     htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
			                     htmls += '<title>Placeholder</title>';
			                     htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
			                     htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
			                     htmls += '</svg>';
			                     htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
			                     htmls += '<span class="d-block">';
			                     htmls += '<strong class="text-gray-dark">' + this.reg_id + '</strong>';
			                     htmls += '<span style="padding-left: 7px; font-size: 9pt">';
			                     htmls += '<a href="javascript:void(0)" onclick="fn_editReply(' + this.rid + ', \'' + this.reg_id + '\', \'' + this.content + '\' )" style="padding-right:5px">수정</a>';
			                     htmls += '<a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rid + ')" >삭제</a>';
			                     htmls += '</span>';
			                     htmls += '</span>';
			                     htmls += this.content;
			                     htmls += '</p>';
			                     htmls += '</div>';
				                });	//each end
							}
						$("#replyList").html(htmls);
			           },	   // Ajax success end
		           	error: function (request, status, error){
			   			console.log('error!');
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});	// Ajax end
			}
			//reply List(e)
			
			//댓글 저장 버튼 클릭 이벤트
			$(document).on('click', '#btnReplySave', function(e){
				e.preventDefault();
				console.log('@saveButton click');
				var replyContent = $('#content').val();
				var replyReg_id = $('#reg_id').val();
				var paramData = {"content": replyContent
						, "reg_id": replyReg_id
						, "bid":'${boardContent.bid}'
				};
				
				var headers = {"Content-Type" : "application/json"
						, "X-HTTP-Method-Override" : "POST"};
				$.ajax({
					url: "${pageContext.request.contextPath}/board/saveReply"
					//, headers : headers
					, data : paramData
					, type : 'POST'
					, dataType : 'text'
					, success: function(){
						showReplyList();
						$('#content').val('');
						$('#reg_id').val('');
					}
					, error: function (request, status, error){ // Ajax success end
			   			console.log('error!');
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});
			});
			
			//댓글 수정
			function fn_editReply(rid, reg_id, content){
				console.log('fn_editReply');
				var htmls = "";
				htmls += '<div class="media text-muted pt-3" id="rid' + this.rid + '">';
				htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
				htmls += '<title>Placeholder</title>';
				htmls += '<rect width="100%" height="100%" fill="#007bff"></rect>';
				htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
				htmls += '</svg>';
				htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
				htmls += '<span class="d-block">';
				htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';
				htmls += '<span style="padding-left: 7px; font-size: 9pt">';
				htmls += '<a href="javascript:void(0)" onclick="fn_updateReply(' + rid + ', \'' + reg_id + '\')" style="padding-right:5px">저장</a>';
				htmls += '<a href="javascript:void(0)" onClick="showReplyList()">취소<a>';
				htmls += '</span>';
				htmls += '</span>';		
				htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
				htmls += content;
				htmls += '</textarea>';
				htmls += '</p>';
				htmls += '</div>';
				$('#rid' + rid).replaceWith(htmls);
				$('#rid' + rid + ' #editContent').focus();
			}
			
			function fn_updateReply(rid, reg_id){
				var replyEditContent = $('#editContent').val();
				var paramData = JSON.stringify({"content": replyEditContent
						, "rid": rid
				});
				var headers = {"Content-Type" : "application/json"
						, "X-HTTP-Method-Override" : "POST"};
				$.ajax({
					url: "${updateReplyURL}"
					, headers : headers
					, data : paramData
					, type : 'POST'
					, dataType : 'text'
					, success: function(result){
		                                console.log(result);
						showReplyList();
					}, 
					error: function(error){
						console.log("에러 : " + error);
					}
				});
			}
			function fn_deleteReply(rid){
				var paramData = {"rid": rid};
				$.ajax({
					url: "${deleteReplyURL}"
					, data : paramData
					, type : 'POST'
					, dataType : 'text'
					, success: function(result){
						showReplyList();
					}
					, error: function(error){
						console.log("에러 : " + error);
					}
				});
			}
		</script>
		<script type="text/javascript">
			//목록 버튼
			/*
			$(document).on('click', '#btnList', function(e){
				e.preventDefault();
				location.href="${pageContext.request.contextPath}/board/getBoardList"; //TODO 2020.09.18 href로 떨어졌을 때, 이전 page정보 받을 수 있나
			});
			*/
			
			//목록 버튼 대체 function - ajax 2020.09.21 pms
			function btnList(){
				var url = "${pageContext.request.contextPath}/board/getBoardList";
				$.ajax({
		            type: 'POST',
		            url: 'getPageRange.do',
		            //data: paramData,
		            dataType: 'json',
		            success: function(result) {
		            	console.log("test");
		            	url = url + "?page=" + result.page;
		            	url = url + "&range=" + result.range;
		            	location.href = url;
		           	},	   // Ajax success end
		           	error: function (request, status, error){
			   			console.log('error!');
						console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				    }
				});	// Ajax end
			}
			
			//수정 버튼 클릭 이벤트
			$(document).on('click', '#btnUpdate', function(){
				var url = "${pageContext.request.contextPath}/board/editForm"; // controller에서 처리하는 url
				url = url + "?bid="+${boardContent.bid};
				url = url + "&mode=edit";
				location.href = url;
			});
			
			$(document).on('click', '#btnDelete', function(){
				var url = "${pageContext.request.contextPath}/board/deleteBoard";
				url = url + "?bid="+${boardContent.bid};
				location.href = url;
			});

		</script>
	</head>
	<body>
		<article>
			<div class="container" role="main">
				<h2>board Content</h2>
				<div class="bg-white rounded shadow-sm">
					<div class="board_title"><c:out value="${boardContent.title}"/></div>
					<div class="board_info_box">
						<span class="board_author">작성자 : </span>
						<span class="board_author"><c:out value="${boardContent.reg_id}"/></span>
						<span class="board_date"><c:out value="${boardContent.reg_dt}"/></span>
					</div> 
					<div class="board_content">
						<pre><c:out value="${boardContent.content}"/></pre>
					</div>
					<div class="board_tag">TAG : <c:out value="${boardContent.tag}"/></div>
				</div>
				<div style="margin-top : 20px">
					<button type="button" class="btn btn-sm btn-primary" id="btnUpdate">수정</button>
					<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button>
					<button type="button" class="btn btn-sm btn-primary" id="btnList" onClick=btnList();>목록</button>
				</div>
				<!-- Reply Form {s} -->
				<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
					<form:form name="form" id="form" role="form" modelAttribute="replyVO" method="post">
					<form:hidden path="bid" id="bid"/>
					<div class="row">
						<div class="col-sm-10">
							<form:textarea path="content" type="text" id="content" class="form-control" rows="3" placeholder="댓글을 입력해 주세요"></form:textarea>
						</div>
						<div class="col-sm-2">
							<form:input path="reg_id" type="text" class="form-control" id="reg_id" placeholder="댓글 작성자"></form:input>
							<button type="button" class="btn btn-sm btn-primary" id="btnReplySave" style="width: 100%; margin-top: 10px"> 저 장 </button>
						</div>
					</div>
					</form:form>
				</div>
				<!-- Reply Form {e} -->
				
				<!-- Reply List {s}-->
				<div class="my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
					<h6 class="border-bottom pb-2 mb-0">Reply list</h6>
					<div id="replyList"></div>
				</div> 
				<!-- Reply List {e}-->
			</div>
		</article>
	</body>
</html>