<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drop Member Step1</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/step1.css"/>"/>
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
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
			<div class="verify-div">
				<h3>본인 인증을 위해 비밀번호를 입력해 주세요.</h3>
				<div>
					<form:form modelAttribute="verifyForm">
						<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요."/>
						<br/>
						<input type="button" class="btn btn-lg btn-primary btn-block" id="verifyBtn" value="확인"/>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>