<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Drop Member Step2</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/step2.css"/>"/>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		 
		$("#confirmBtn").click(function() {
			if ( !$("#dropAgree").prop("checked") ) {
				alert("동의를 먼저 해주세요.");
				return false;
			} else {
				$(location).attr("href", "<c:url value="/drop/${member.no}"/>");
			}

		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
			<div class="inform-div">
				<div>
					<legend><h2>탈퇴 안내</h2></legend>
				</div>
				<div>
					<p>사용하고 계신 아이디(${member.id})는 탈퇴할 경우 복구가 불가능합니다.</p>
					<p>탈퇴 후 회원정보 및 게시판형 서비스에 등록한 게시물은 모두 삭제됩니다.</p>
					<div>
						<form name="confirmForm">
							<p>
								<input type="checkbox" id="dropAgree" name="dropAgree"/>
								안내 사항을 모두 확인하였으며, 이에 동의합니다.
							</p>
						</form>
					</div>
					<input type="button" class="btn btn-lg btn-primary btn-block" id="confirmBtn" value="확인"/>
				</div>
			</div>
		</div>
	</div>
</body>
</html>