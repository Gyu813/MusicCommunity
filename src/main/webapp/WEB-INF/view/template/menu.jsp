<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">


</style>
<div style="display: inline-block; width:550px;">
	<div style="display: inline-block">
		<a href="<c:url value="/"/>">홈으로</a>
	</div>

	<c:if test="${empty sessionScope.__USER__}">
		<div style="display: inline-block; margin-left:390px;">
			<a href="<c:url value="/login"/>">로그인</a>
			<a href="<c:url value="/join"/>">회원가입</a>
		</div>
	</c:if>

	<c:if test="${not empty sessionScope.__USER__}">
		<div style="display: inline-block">
			<span style="color:#FFF; margin-left:5px;">${sessionScope.__USER__.nickname}님, 환영합니다!</span>
			<span style="margin-left:170px;">
				<a href="<c:url value="/mypage"/>">회원정보수정</a>
				<a href="<c:url value="/logout"/>">로그아웃</a>
			</span>
		</div>
	</c:if>
</div>
