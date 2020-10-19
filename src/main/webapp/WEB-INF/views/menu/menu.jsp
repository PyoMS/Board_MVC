<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- <%@ include file="/WEB-INF/views/layout/header.jsp" %> --%>

<c:url var="saveURL" value="/menu/saveMenu"></c:url>
<c:url var="deleteURL" value="/menu/deleteMenu"></c:url>
<c:url var="updateURL" value="/menu/updateMenu"></c:url>
<c:url var="getMenuListURL" value="/menu/getMenuList"></c:url>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu List</title>
<script>
	$(function(){
		fn_showList(); 
		}
	); 
	
	function fn_showList(){ 
		console.log('fn_showList()');
		var paramData = {}; 
		$.ajax(
			{ 
				url : "${getMenuListURL}" ,
				//url : url,
				type : "POST" , 
				//dataType : "json" , //controller -> jsp
				dataType : "text",
				data : paramData , 
				success : function(result){
					var res = [];
					res = JSON.parse(result);
					console.log(res);
					console.log('result.length : '+res.length);
					if (res.length >= 1){
						//var list = result.menuList; 
						var htmls = ""; 
						$(res).each(function() { 
							htmls += '<tr>'; 
							htmls += '<td>' + this.mid + '</td>'; 
							
							//htmls += '<td>' + e.code + '</td>'; 
							htmls += '<td>'; 
							htmls += '<a href="#" onClick="fn_menuInfo(' + this.mid + ',\'' + this.code +'\',\'' 
									+ this.codename + '\', ' + this.sort_num + ', \'' + this.comment + '\')" >'; 
							htmls += this.code; 
							htmls += '</a>'; 
							htmls += '</td>';
							
							htmls += '<td>' + this.codename + '</td>'; 
							htmls += '<td>' + this.sort_num + '</td>'; 
							htmls += '<td>' + this.comment + '</td>'; 
							htmls += '</tr>'; }); 
					}
					else {
						console.log("조회실패"); 
					} 
					$('#menuList').html(htmls); 
				},
				error: function (request, status, error){
		   			console.log('error!');
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			    }
			}
		); 
	}
	
	$(document).on('click', '#btnSave', function(e){
		e.preventDefault(); 
		console.log('save button');
		var url = "${saveURL}"; 
		if ($("#mid").val() != 0) { url = "${updateURL}"; }

		var paramData = {
			"code" : $('#code').val() , 
			"codename" : $('#codename').val() , 
			"sort_num" : $('#sort_num').val() , 
			"comment" : $('#comment').val()
		}; 
		$.ajax({
			url : url , 
			type : "POST" , 
			dataType : "text" , // 2020.10.08 JSON 시 ajax 타입 오류 발생.. data값이 null값이라 발생하는 듯. -> 서버에서 받을 데이터 타입.
			data : paramData , //서버에 전송할 데이터
			success : 
				function(){ 
					//console.log('result.length : ' + result.length);
					fn_showList(); 
					//초기화 이벤트 호출
					$("#btnInit").trigger("click");
				},
			error: function (request, status, error){
	   			console.log('error!');
				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		}); 
	});
	
	$(document).on('click', '#btnInit', function(e){ 
		$('#mid').val(''); 
		$('#code').val('');
		$('#codename').val('');
		$('#sort_num').val('0');
		$('#comment').val(''); 
	});
	
	//메뉴 정보 셋
	function fn_menuInfo(mid, code, codename, sort_num, comment) {
		$("#mid").val(mid); 
		$("#code").val(code); 
		$("#codename").val(codename); 
		$("#sort_num").val(sort_num); 
		$("#comment").val(comment);
		
		//코드 부분 읽기 모드로 전환 
		$("#code").attr("readonly", true); 
	}
	

	$(document).on('click', '#btnDelete', function(e){ 
		e.preventDefault(); 
		if ($("#code").val() == "") {
			alert("삭제할 코드를 선택해 주세요."); return; 
		} 
		var url = "${deleteURL}"; 
		var paramData = { "code" : $("#code").val() }; 
		$.ajax({
			url : url , 
			type : "POST" , 
			dataType : "text" , // 2020.10.08 btnSave와 동일.
			data : paramData , 
			success : function(result){ 
				fn_showList();  
				
				//삭제 후 셋팅값 초기
				$("#btnInit").trigger("click"); 
			} 
		}); 
	});
		

</script>
<style>
	#paginationBox{
		padding : 10px 0px;
	}
</style>
</head>
<body>
	<article>
	<div class="container">
		<!-- Menu form {s} -->
		<h4 class="mb-3">Menu Info</h4>
		<div>
			<form:form name="form" id="form" role="form" modelAttribute="menuVO" method="post"
			action="${pageContext.request.contextPath}/menu/saveMenu">
				<form:hidden path="mid" id="mid"/>
				
				<div class="row">
					<div class="col-md-4 mb-3">
						<label for="code">Code</label> 
						<form:input path="code" id="code" class="form-control" placeholder="" value="" required="" /> 
						<div class="invalid-feedback"> Valid Code is required. </div>
					</div>
					<div class="col-md-5 mb-3"> 
						<label for="codename">Code name</label> 
						<form:input path="codename" class="form-control" id="codename" placeholder="" value="" required="" /> 
						<div class="invalid-feedback"> Valid Code name is required. </div> 
					</div>
					<div class="col-md-3 mb-3"> 
						<label for="sort_num">Sort</label> 
						<form:input path="sort_num" class="form-control" id="sort_num" placeholder="" required="" /> 
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12 mb-3"> 
						<label for="comment">Comment</label> 
						<form:input path="comment" class="form-control" id="comment" placeholder="" value="" required="" /> 
					</div>
				</div>
			</form:form>
		</div>
		<!-- Menu form {e} -->
		
		<div> 
			<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button> 
			<button type="button" class="btn btn-sm btn-primary" id="btnDelete">삭제</button> 
			<button type="button" class="btn btn-sm btn-primary" id="btnInit">초기화</button> 
		</div>
		<h4 class="mb-3" style="padding-top:15px">Menu List</h4>
		
		<!-- List{s} -->
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<colgroup> 
					<col style="width:10%;" /> 
					<col style="width:15%;" /> 
					<col style="width:15%;" /> 
					<col style="width:10%;" /> 
					<col style="width:auto;" /> 
				</colgroup>
				<thead> 
					<tr> 
						<th>menu id</th> 
						<th>code</th> 
						<th>codename</th> 
						<th>sort</th> 
						<th>command</th> 
					</tr> 
				</thead> 
				<tbody id="menuList"> </tbody>
			</table>
		</div>
		<!-- List{e} -->
	</div>
	</article>
</body>
</html>