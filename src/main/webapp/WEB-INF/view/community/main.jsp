<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css"/>"/>
<style type="text/css">
	body {
		background: url(http://i.imgur.com/GHr12sH.jpg) no-repeat center center fixed;
		-webkit-background-size: cover;
	    -moz-background-size: cover;
	    -o-background-size: cover;
	    background-size: cover;
		padding-top:50px;
	}
	
	.main-div {
		color: #5d5d5d;
		background: #f2f2f2;
		padding: 26px;
		border-radius: 10px;
		-moz-border-radius: 10px;
		-webkit-border-radius: 10px;
	}
	
	#searchForm {
		text-align: center;
		font-size: 20px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
				<div class="main-div">
					<div>
						전체 게시물 개수 : ${pageExplorer.totalCount}
					</div>
					<table class="table table-striped">
						<tr>
							<th>No.</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
							<th>추천수</th>
						</tr>
						<c:forEach items="${pageExplorer.list}" var="community">
							<tr>
								<td>${community.no}</td>
								<td><a href="<c:url value="/read/${community.no}"/>">${community.title}</a></td>
								<td>
									<c:choose>
										<c:when test="${not empty community.memberVO}">
											<img src="<c:url value="/profile/${community.memberNo}"/>" width="30px" height="40px"/>
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
						<c:if test="${empty pageExplorer.list}">
							<tr>
								<td colspan="5">등록된 게시글이 없습니다.</td>
							</tr>
						</c:if>
					</table>
					<form id="searchForm">
						${pageExplorer.make()}
					</form>
				</div>
	
			<!-- Write Button Wrapper -->
			<div>
				<a href="<c:url value="/write"/>">글쓰기</a>
			</div>
		</div>
	</div>
</body>
</html>