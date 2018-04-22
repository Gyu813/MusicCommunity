<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail Page</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/bootstrap.min.css"/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/detail.css"/>"/>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.3.1.min.js"/>"></script>
<script type="text/javascript">
	$().ready(function() {
		
		loadReplies(0);
		function loadReplies(scrollTop) {
			$.get("<c:url value="/api/reply/${community.no}"/>", {},
					function(response) {
						for ( var i in response) {
							appendReplies(response[i]);
						}
						$(window).scrollTop(scrollTop);
			});
		}
		
		$("#writeReplyBtn").click(function() {
			$.post("<c:url value="/api/reply/${community.no}"/>",
					$("#writeReplyForm").serialize(),
					function(response) {
						if ( response.status ) {
							$("#parentReplyNo").val(0);
							$("#body").val("");
							$("#createReply").appendTo("#createReplyDiv");
							
							var scrolltop = $(window).scrollTop();
							
							$("#replies").html("");
							loadReplies(scrolltop);
						} else {
							alert("댓글 등록 실패!");
						}
						
			});
		});
		
		$("#replies").on("click", ".rereply", function() {
			var parentReplyNo = $(this).closest(".reply").data("id");
			$("#parentReplyNo").val(parentReplyNo);
			$("#createReply").appendTo($(this).closest(".reply"));
		});
		
	
		$("#replies").on("click", ".love", function() {
			var replyNo = $(this).data("id");
			var that = $(this);
			$.post("<c:url value='/api/love/" + replyNo + "'/>", {},
					function(response) {
						if ( response.response ) {
							var loveCount = response.loveCount;
							$(that).children(".loveCount").text(loveCount);
						} else {
							alert("좋아요 실패!");
						}
						
			});
		});
		
		$("#replies").on("click", ".hate", function() {
			var replyNo = $(this).data("id");
			var that = $(this);
			$.post("<c:url value='/api/hate/" + replyNo + "'/>", {},
					function(response) {
						if ( response.response ) {
							var hateCount = response.hateCount;
							$(that).children(".hateCount").text(hateCount);
						} else {
							alert("싫어요 실패!");
						}
						
			});
		});
		
		$("#replies").on("click", ".remove", function() {
			var replyNo = $(this).data("id");
			var that = $(this);
			var level = $(this).siblings(".writer").data("id");
			var reply = $(this).parent(".reply");
			var removeReplyDiv = $("<div><h3>[이 댓글은 삭제된 댓글입니다!]</h3></div>");
			var result = confirm("정말 삭제하시겠습니까?");
			var nextSiblingLevel = $(this).parent(".reply").next('.reply').children('.writer').data("id");
			if ( result ) { // result가 true일 경우
				if ( level == 1 && nextSiblingLevel == 2 ) { // 주댓글일 경우
					$.post("<c:url value='/api/modifyBody/" + replyNo + "'/>", {},
							function(response) {
								if ( response.response ) {
									$(that).parent(".reply").after(removeReplyDiv);
									$(that).parent(".reply").remove();
								} else {
									alert("댓글삭제 실패!");
								}
					});
				} else { // 대댓글일 경우
					$.post("<c:url value='/api/remove/" + replyNo + "'/>", {},
							function(response) {
								if ( response.response ) {
									$(that).parent(".reply").remove();
								} else {
									alert("댓글삭제 실패!");
								}
					});
				}
			}
		});
		
		
		function appendReplies(reply) {
			if ( reply.level-1 >= 2 ) {
					reply.level = 2;
			}
			var replyDiv = $("<div class='reply' style='padding-left:" + ((reply.level-1) * 50) + "px;' data-id='" + reply.no + "'></div>");
			var removeReplyDiv = $("<div><h3>[이 댓글은 삭제된 댓글입니다!]</h3></div>");
			
			if ( reply.body == "removedReply" ) {
				replyDiv.append(removeReplyDiv);
			} else {
				var nickname = reply.memberVO.nickname + "(" + reply.memberVO.id + ")";
				var top = $("<span class='writer' data-id='" + reply.level + "'>"
							+ "<img src='<c:url value='/profile/" + reply.memberNo + "'/>' width='30px' height='40px'/>"
							+ nickname + "</span><span class='registDate'>" + reply.registDate + "</span>");
				replyDiv.append(top);
				
				var body = $("<div class='body'>" + reply.body + "</div>");
				replyDiv.append(body);
				
				var loveHate = $("<span class='love' data-id='" + reply.no + "'>[좋아요]"
								 + "<span class='loveCount'>" + reply.loveCount + "</span></span>"
								 + " <span class='hate' data-id='" + reply.no + "'>[싫어요]"
								 + "<span class='hateCount'>" + reply.hateCount + "</span></span>");
				replyDiv.append(loveHate);
				
				var registRereply = $("<div class='rereply'>[답글 달기]</div>");
				replyDiv.append(registRereply);
				
				var loginMemberNo = ${sessionScope.__USER__.no};			
				var replyMemberNo = reply.memberNo;
				var removeReply = $("<div class='remove' data-id='" + reply.no + "'>[삭제]</div>");
				
				if ( loginMemberNo == replyMemberNo ) {
					replyDiv.append(removeReply);
				}
			}
			$("#replies").append(replyDiv);
		}
		
		$("#goBackBtn").click(function() {
			$(location).attr("href", "<c:url value="/"/>");
		});
		
		$("#recommendBtn").click(function() {
			$(location).attr("href", "<c:url value="/recommend/${community.no}"/>");
		});
		
		$("#removeBtn").click(function() {
			var result = confirm("정말 삭제하시겠습니까?");
			if ( result ) { // result가 true일 경우
				$(location).attr("href", "<c:url value="/remove/${community.no}"/>");
			}
		});
		
		$("#modifyBtn").click(function() {
			$(location).attr("href", "<c:url value="/modify/${community.no}"/>");
		});
	});
</script>
</head>
<body>
	<!-- Whole Wrapper -->
	<div class="container">
		<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3 col-lg-6 col-lg-offset-3">
			<jsp:include page="/WEB-INF/view/template/menu.jsp"/>
			
			<div class="datail-div">
				<h1>${community.title}</h1>
				<c:choose>
					<c:when test="${not empty community.memberVO}">
						<h4>${community.memberVO.id}(${community.memberVO.nickname}) [IP]${community.requestIp}</h4>
					</c:when>
					<c:otherwise>
						<h3>탈퇴한 회원 | ${community.requestIp}</h3>
					</c:otherwise>
				</c:choose>
				<p>[조회수]${community.viewCount} [추천수]${community.recommendCount} [작성일]${community.writeDate}</p>
				<hr/>
				<p>${community.body}</p>
				
				<c:if test="${not empty community.displayFilename}">
					<p>파일 : ${community.displayFilename}</p>
					
					<video width="490" controls>
						<source src="<c:url value="/play/${community.no}"/>" type="video/mp4">
					</video>
				</c:if>
				
				<hr/>
				
				<div id="replies"></div>
				<div id="createReplyDiv">
					<div id="createReply">
						<form id="writeReplyForm">
							<input type="hidden" id="parentReplyNo" name="parentReplyNo" value="0"/>
							<div>
								<textarea class="form-control" rows="5" cols="40" id="body" name="body"></textarea>
							</div>
							
							<input type="button" id="writeReplyBtn" value="댓글등록"/>
						</form>
					</div>
				</div>
				
				<hr/>
				
				<div>
					<input type="button" id="goBackBtn" value="뒤로가기"/>
					<input type="button" id="recommendBtn" value="추천하기"/>
					<c:if test="${community.memberVO.no == sessionScope.__USER__.no}">
						<input type="button" id="modifyBtn" value="글 수정"/>
						<input type="button" id="removeBtn" value="글 삭제"/>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>