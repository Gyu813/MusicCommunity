<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Page</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		
		$("#joinBtn").click(function() {
			
			if ( $("#id").val() == "" ) {
				alert("아이디를 입력하세요!");
				$("#id").focus();
				return false;
			}
			
			if ( $("#password").val() == "" ) {
				alert("비밀번호를 입력하세요!");
				$("#password").focus();
				return false;
			}
			
			if ( $("#password-confirm").val() == "" ) {
				alert("비밀번호를 다시 입력하세요!");
				$("#password-confirm").focus();
				return false;
			}
			
			if ( $("#nickname").val() == "" ) {
				alert("닉네임을 입력하세요!");
				$("#nickname").focus();
				return false;
			}
			
			$("#joinForm").attr({
				"method": "post",
				"action": "<c:url value="/join"/>"
			}).submit();
			
		});
		
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div>
		<div>
			<a href="<c:url value="/"/>">홈으로</a>
		</div>
		<form:form modelAttribute="joinForm">
			<div style="display: inline-block;">
				아이디 <input type="text" id="id" name="id" placeholder="아이디를 입력하세요."/>
			</div>
			<div style="display: inline-block;">
				<input type="button" id="dupliConfirmBtn" name="dupliConfirmBtn" value="중복확인"/>
			</div>
			<div>
				비밀번호<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
			</div>
			<div>
				비밀번호 확인<input type="password" id="password-confirm" placeholder="비밀번호를 다시 입력하세요."/>
			</div>
			<div>
				닉네임<input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력하세요."/>
			</div>
			<div>
				<input type="button" id="joinBtn" value="가입하기"/>
			</div>
		</form:form>
	</div>

</body>
</html>