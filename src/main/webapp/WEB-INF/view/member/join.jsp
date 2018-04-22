<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/join.css"/>"/>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
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
					} else {
						
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
					}
				});
			} else {
				alert("아이디 중복체크를 먼저 하세요.");
			}
		});
		
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
				<section class="join-form">
					<form:form modelAttribute="joinForm" enctype="multipart/form-data" class="form" role="join">
						<legend><h1>회원가입 ♬</h1></legend>
						<div class="row">
							<div class="col-xs-6 col-md-6" style="display: inline-block;">	
									아이디 <input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요." value="${joinForm.id}"/>
									<form:errors path="id"/>
							</div>
							
							<div class="col-xs-6 col-md-6" style="display: inline-block;">
								<input type="button" class="btn btn-lg btn-primary btn-block" id="dupliConfirmBtn" name="dupliConfirmBtn" value="중복확인"/>
							</div>
						</div>
						
						<div>
							비밀번호<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요." value="${joinForm.password}"/>
							<form:errors path="password"/>
						</div>
						
						<div>
							비밀번호 확인<input type="password" class="form-control" id="passwordConfirm" placeholder="비밀번호를 다시 입력하세요."/>
						</div>
						
						<div>
							닉네임<input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임을 입력하세요." value="${joinForm.nickname}"/>
							<form:errors path="nickname"/>
						</div>
						
						<div>
							자기소개 <textarea name="selfIntroduction" class="form-control" rows="10" cols="35"></textarea>
						</div>
						<br/>
						<div>
							프로필<input type="file" id="profileFile" name="profileFile"/>
						</div>
						
						<div>
							<input class="btn btn-lg btn-primary btn-block" type="button" id="joinBtn" value="가입하기"/>
						</div>
						<div class="bottom-menu">
           					<a href="<c:url value="/"/>">메인으로</a> or <a href="<c:url value="/login"/>">로그인</a>
          				</div>
					</form:form>
				</section>
			</div>
		</div>
	</div>

</body>
</html>