<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/write.css"/>"/>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
	
		<c:if test="${mode == 'modify' && not empty community.displayFilename}">
			$("#file").closest("div").hide();
		</c:if>
		
		$(".modifyFile input").click(function() {
			var yChange = $("#yesChange").prop("checked");
			var nChange = $("#noChange").prop("checked");
			
			if ( yChange ) {
				$("input:radio[name='changeFile']:radio[value='N']").prop("checked", false);
				$("#file").closest("div").show();
			}
			if ( nChange ) {
				$("input:radio[name='changeFile']:radio[value='Y']").prop("checked", false);
				$("#file").closest("div").hide();
			}
		});
		
		$("#writeBtn").click(function() {
			
			if ( "${mode}" == "modify" ) {
				var url = "<c:url value="/modify/${community.no}"/>";
			}
			else {
				var url = "<c:url value="/write"/>";
			}
			
			$("#writeForm").attr({
				"method": "post",
				"action": url
			}).submit();
			
		});
	});
</script>
</head>
<body>
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
		<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
			<section class="write-form">
				<form:form modelAttribute="writeForm" enctype="multipart/form-data" class="form" role="write">
					<div>
						제목<input type="text" class="form-control" id="title" name="title" value="${community.title}" placeholder="제목을 입력하세요."/>
					</div>
		
					<div>
						<form:errors path="title"/>
					</div>
					
					<div>
						내용<textarea class="form-control" rows="10" cols="30" id="body" name="body" placeholder="내용을 입력하세요.">${community.body}</textarea>
					</div>
					
					<div>
						<form:errors path="body"/>
					</div>
					
					<c:if test="${mode == 'modify' && not empty community.displayFilename}">
						첨부파일을 바꾸시겠습니까?
						<div class="modifyFile">
							<div>
								<label><input type="radio" id="yesChange" name="changeFile" value="Y"/>네</label>
							</div>
							<div>
								<label><input type="radio" id="noChange" name="changeFile" value="N" checked="checked"/>아니오</label>
							</div>
							현재파일 : ${community.displayFilename}
						</div>
						
					</c:if>
					
					<br/>
					
					<div>
						첨부파일<input type="file" id="file" name="file"/>
					</div>
		
					<div>
						<input type="hidden" id="memberNo" name="memberNo" value="${sessionScope.__USER__.no}"/>
					</div>
					
					<div>
						<input type="button" class="btn btn-lg btn-primary btn-block" id="writeBtn" value="등록"/>
					</div>
				</form:form>
			</section>
		</div>
	</div>
</body>
</html>