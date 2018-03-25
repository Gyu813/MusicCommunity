<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<script type="text/javascript"
		src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("#loginBtn").click(function() {
				
			if ( $("#id").val() == "" ){
				alert("아이디를 입력하세요!");
				$("#id").focus();
				return false;
			}
			
			if ( $("#password").val() == "" ) {
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return false;
			}
			
			$("#loginForm").attr({
				"action": "<c:url value="/login"/>",
				"method": "post"
			}).submit();
			
		});
		
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		
		<form:form modelAttribute="loginForm">
			<div>		
				<input type="text" id="id" name="id" placeholder="아이디를 입력하세요."/>
			</div>
			
			<div>
				<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
			</div>
			
			<div class="login">
				<input type="button" id="loginBtn" value="로그인"/>
			</div>
			
		</form:form>
	</div>
</body>
</html>