<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drop Member Step1</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#verifyBtn").click(function() {
			if ( $("#password").val() == "" ) {
				alert("비밀번호를 입력하세요.");
				$("#password").focus();
				return false;
			}
			
			$("#verifyForm").attr({
				"method": "post",
				"action": "<c:url value="/drop/step2"/>"
			}).submit();
		})
	});
</script>
</head>
<body>
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		<div>
			<p>본인 인증을 위해 비밀번호를 입력해 주세요.</p>
			<div>
				<form:form modelAttribute="verifyForm">
					<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
					<input type="button" id="verifyBtn" value="확인"/>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>