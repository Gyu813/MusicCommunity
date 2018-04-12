<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drop Complete</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#goMain").click(function() {
			$(location).attr("href", "<c:url value="/"/>");
		})
	});
</script>
</head>
<body>
	<div>
		<h1>탈퇴가 완료되었습니다.</h1>
		<input type="button" id="goMain" name="goMain" value="메인으로"/>
	</div>
</body>
</html>