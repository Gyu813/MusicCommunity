<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/login.css"/>"/>
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
	<div class="container">
		<div class="row" id="pwd-container">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<section class="login-form">
		
					<form:form modelAttribute="loginForm" role="login">
						<h1 class="page-title">♬MusicCommunity</h1>
		
						<input type="text" required class="form-control input-lg" id="id" name="id" placeholder="아이디를 입력하세요."/>

						<input type="password" class="form-control input-lg" id="password" name="password" placeholder="비밀번호를 입력하세요."/>

						<hr/>
						
						<div class="login">
							<input type="button" id="loginBtn" class="btn btn-lg btn-primary btn-block" value="로그인"/>
						</div>
						<div>
           					<a href="<c:url value="/"/>">메인으로</a> or <a href="<c:url value="/join"/>">회원가입</a>
          				</div>
					</form:form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>