<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyPage</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/mypage.css"/>"/>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		
		<c:if test="${not empty member.profileFilename}">
			$("#profileFile").closest("div").hide();
		</c:if>
		
		$(".modifyProfile input").click(function() {
			var yChange = $("#yesChange").prop("checked");
			var nChange = $("#noChange").prop("checked");
			
			if ( yChange ) {
				$("input:radio[name='changeProfile']:radio[value='N']").prop("checked", false);
				$("#profileFile").closest("div").show();
			}
			if ( nChange ) {
				$("input:radio[name='changeProfile']:radio[value='Y']").prop("checked", false);
				$("#profileFile").closest("div").hide();
			}
		});
		
		$("#modifyBtn").click(function() {
			
			if ( $("#nickname").val() == "" ) {
				alert("닉네임은 필수 입력사항입니다!");
				$("#nickname").focus();
				return false;
			}
			
			$("#modifyForm").attr({
				"method": "post",
				"action": "<c:url value="/mypage"/>"
			}).submit();
			
		});
		
		$("#dropBtn").click(function() {
			$(location).attr("href", "<c:url value="/drop/step1"/>");
		});
		
	});
</script>
</head>
<body>
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
			
			<section class="write-form">
				<form:form modelAttribute="modifyForm" enctype="multipart/form-data" class="form" role="modify">
					<legend>
						<h3>당신은 우리의 ${member.no}번째 회원이십니다!</h3>
						<h4>가입날짜 : ${member.registDate}</h4>
					</legend>
					
					<div>
						<img src="<c:url value="/profile/${member.no}"/>" width="150px" height="200px"/>
					</div>
					
					<c:if test="${not empty member.profileFilename}">
						프로필을 바꾸시겠습니까?
						<div class="modifyProfile">
							<div>
								<label><input type="radio" id="yesChange" name="changeProfile" value="Y"/>네</label>
							</div>
							<div>
								<label><input type="radio" id="noChange" name="changeProfile" value="N" checked="checked"/>아니오</label>
							</div>
							현재파일 : ${member.profileFilename}
						</div>
					</c:if>
					
					<div>
						<input type="file" id="profileFile" name="profileFile"/>
					</div>
					
					<div>
						아이디 : ${member.id}
					</div>
					
					<div>
						닉네임 : <input type="text" class="form-control" id="nickname" name="nickname" value="${member.nickname}"/>
					</div>
					
					<div>
						자기소개 : <textarea name="selfIntroduction" class="form-control" rows="10" cols="35">${member.selfIntroduction}</textarea>
					</div>
					
					<div>
						<input type="button" class="btn btn-lg btn-primary btn-block" id="modifyBtn" value="수정하기"/>
					</div>
				</form:form>
			</section>
			<div>
				<input type="button" id="dropBtn" value="회원탈퇴하기"/>
			</div>
		</div>
	</div>
</body>
</html>