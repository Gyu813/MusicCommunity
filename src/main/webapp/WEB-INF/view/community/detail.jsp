<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail Page</title>
</head>
<body>
	<!-- Whole Wrapper -->
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		
		<h1>${community.title}</h1>
		<h3>${community.memberVO.id}(${community.memberVO.nickname}) | ${community.requestIp}</h3>
		<p>${community.viewCount} | ${community.recommendCount} | ${community.writeDate}</p>
		<hr/>
		<p>${community.body}</p>
		<c:if test="${not empty community.displayFilename}">
			<!-- TODO video 넣기 -->
			<p>${community.displayFilename}</p>
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
		</div>
	</div>
</body>
</html>