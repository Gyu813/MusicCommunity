<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
<script type="text/javascript"
		src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {

	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div style="width: 1000px;">
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>

		<!-- List Wrapper -->
		<div>
			<!-- List Wrapper -->
			<div>
				<table>
					<tr>
						<th>No.</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>추천수</th>
					</tr>
					<c:forEach items="${communityList}" var="community">
						<tr>
							<td>${community.no}</td>
							<td><a href="<c:url value="/read/${community.no}"/>">${community.title}</a></td>
							<td>
								<c:choose>
									<c:when test="${not empty community.memberVO}">
										${community.memberVO.id}(${community.memberVO.nickname})
									</c:when>
									<c:otherwise>
										탈퇴한 회원
									</c:otherwise>
								</c:choose>
							</td>
							<td>${community.writeDate}</td>
							<td>${community.viewCount}</td>
							<td>${community.recommendCount}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty communityList}">
						<tr>
							<td colspan="5">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>
				</table>
			</div>

		</div>

		<!-- Write Button Wrapper -->
		<div>
			<a href="<c:url value="/write"/>">글쓰기</a>
		</div>

	</div>
</body>
</html>