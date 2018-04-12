<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail Page</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#removeBtn").click(function() {
			var result = confirm("정말 삭제하시겠습니까?");
			if ( result ) { // result가 true일 경우
				$(location).attr("href", "<c:url value="/remove/${community.no}"/>");
			}
		});
		
		$("#modifyBtn").click(function() {
			$(location).attr("href", "<c:url value="/modify/${community.no}"/>");
		});
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		
		<h1>${community.title}</h1>
		<c:choose>
			<c:when test="${not empty community.memberVO}">
				<h3>${community.memberVO.id}(${community.memberVO.nickname}) | ${community.requestIp}</h3>
			</c:when>
			<c:otherwise>
				<h3>탈퇴한 회원 | ${community.requestIp}</h3>
			</c:otherwise>
		</c:choose>
		<p>${community.viewCount} | ${community.recommendCount} | ${community.writeDate}</p>
		<hr/>
		<p>${community.body}</p>
		
		<c:if test="${not empty community.displayFilename}">
			<p>파일 : ${community.displayFilename}</p>
			
			<video controls>
				<source src="<c:url value="/play/${community.no}"/>" type="video/mp4">
			</video>
		</c:if>
		
		<hr/>
		
		<div>
			<textarea rows="5" cols="40"></textarea>
		</div>
		
		<input type="button" value="댓글달기"/>
		
		<hr/>
		
		<div>
			<a href="<c:url value="/"/>">뒤로가기</a>
			<a href="<c:url value="/recommend/${community.no}"/>">추천하기</a>
			<c:if test="${community.memberVO.no == sessionScope.__USER__.no}">
				<input type="button" id="modifyBtn" value="글 수정"/>
				<input type="button" id="removeBtn" value="글 삭제"/>
			</c:if>
		</div>
	</div>
</body>
</html>