<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
	#nav > ul {
		padding: 0px;
		margin: 0px;
	}
	
	#nav li {
		display: inline-block;
		margin-left: 15px;
	}
	
	#nav li:FIRST-CHILD {
		margin-left: 0px;
	}
</style>
<div id="nav">
	<ul>
		<li style="display: inline-block">
			<a href="<c:url value="/"/>">홈으로</a>
		</li>

		<c:if test="${empty sessionScope.__USER__}">
			<li>
				<a href="<c:url value="/login"/>">로그인</a>
				<a href="<c:url value="/join"/>">회원가입</a>
			</li>
		</c:if>

		<c:if test="${not empty sessionScope.__USER__}">
			<li>
				${sessionScope.__USER__.nickname}님, 환영합니다!
				<a href="<c:url value="/myPage"/>">회원정보수정</a>
				<a href="<c:url value="/logout"/>">로그아웃</a>
			</li>
		</c:if>
	</ul>
</div>
