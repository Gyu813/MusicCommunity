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
		
		var isDupliCheck = false;
		
		$("#dupliConfirmBtn").click(function() {
			var idValue = $("#id").val();
			if ( idValue != "" ) {
				$.post("<c:url value="/api/exists/id"/>", {
					id: idValue
				}, function(response) {
					if ( response.response ) {
						alert("아이디가 이미 있습니다.");
					} else {
						alert("이 아이디는 사용가능합니다.");
						isDupliCheck = true;
					}
				});
			} else {
				alert("아이디를 입력해주세요.");
			}
		});
		
		$("#id").change(function() {
			isDupliCheck = false;
		});
		
		$("#joinBtn").click(function() {
			if ( isDupliCheck == true ) {
				$.post("<c:url value="/api/exists/id"/>", {
					id: $("#id").val()
				}, function(response) {
					if ( response.response ) {
						alert("이 아이디는 사용할 수 없습니다.");
						$("#id").focus();
						return false;
					}
				});
				
				if ( $("#nickname").val() == "" ) {
					alert("닉네임을 입력하세요.");
					$("#nickname").focus();
					return false;
				}
				
				if ( $("#password").val() == "" ) {
					alert("비밀번호를 입력하세요.");
					$("#password").focus();
					return false;
				}
				
				if( $("#password").val() != $("#passwordConfirm").val() ) {
					alert("비밀번호와 비밀번호확인이 같지 않습니다!");
					return false;
				}
				
				$("#joinForm").attr({
					"method": "post",
					"action": "<c:url value="/join"/>"
				}).submit();
				
			} else {
				alert("아이디 중복체크를 먼저 하세요.");
			}
		});
		
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div>
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
		<form:form modelAttribute="joinForm" enctype="multipart/form-data">
		
			<div style="display: inline-block;">	
				<div>
					아이디 <input type="text" id="id" name="id" placeholder="아이디를 입력하세요." value="${joinForm.id}"/>
					<form:errors path="id"/>
				</div>
			</div>
			
			<div style="display: inline-block;">
				<input type="button" id="dupliConfirmBtn" name="dupliConfirmBtn" value="중복확인"/>
			</div>
			
			<div>
				비밀번호<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요." value="${joinForm.password}"/>
				<form:errors path="password"/>
			</div>
			
			<div>
				비밀번호 확인<input type="password" id="passwordConfirm" placeholder="비밀번호를 다시 입력하세요."/>
			</div>
			
			<div>
				닉네임<input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력하세요." value="${joinForm.nickname}"/>
				<form:errors path="nickname"/>
			</div>
			
			<div>
				자기소개 <textarea name="selfIntroduction" rows="10" cols="35"></textarea>
			</div>
			
			<div>
				프로필<input type="file" id="profileFile" name="profileFile"/>
			</div>
			
			<div>
				<input type="button" id="joinBtn" value="가입하기"/>
			</div>
		</form:form>
	</div>

</body>
</html>