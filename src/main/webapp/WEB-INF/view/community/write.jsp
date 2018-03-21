<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Page</title>
<script src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"
		type="text/javascript"></script>
<script type="text/javascript">
	$().ready(function() {
		
	});
</script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		<form:form modelAttribute="writeForm">
			<div>
				제목 : <input type="text" id="title" name="title" placeholder="제목을 입력하세요."/>
			</div>
			
			<div>
				내용 : <textarea rows="10" cols="30" id="body" name="body" placeholder="내용을 입력하세요."></textarea>
			</div>
			
			<div>
				<input type="button" id="writeBtn" value="등록"/>
			</div>
		</form:form>
	</div>
</body>
</html>