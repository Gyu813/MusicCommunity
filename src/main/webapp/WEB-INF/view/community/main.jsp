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
					</tr>
					<c:forEach items="${communityList}" var="community">
						<tr>
							<td>${community.no}</td>
							<td>${community.title}</td>
							<td>${community.memberVO.nickname}(${community.memberVO.email})</td>
							<td>${community.writeDate}</td>
							<td>${community.viewCount}</td>
							<td>${community.recommendCount}</td>
						</tr>
					</c:forEach>
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