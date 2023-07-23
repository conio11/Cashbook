<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyNotice</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#modifyNoticeBtn").click(function() {
					if ($("#noticeTitle").val() == "") {
						alert("제목을 입력해주세요.");
						$("#noticeTitle").focus();
					} else if ($("#noticeContent").val() == "") {
						alert("내용을 입력해주세요.");
						$("#noticeContent").focus();
					} else {
						$("#modifyNotice").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/on/calendar">홈 화면으로</a>
		<a href="${pageContext.request.contextPath}/onOff/noticeOne?noticeNo=${notice.noticeNo}">이전</a>
		
		<h1>공지 수정</h1>
		<form method="post" action="${pageContext.request.contextPath}/on/modifyNotice" id="modifyNotice">
			<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
			<table border="1">
				<tr>
					<th>번호</th>
					<td>${notice.noticeNo}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="noticeTitle" id="noticeTitle" value="${notice.noticeTitle}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="noticeContent" cols="60" rows="5" id="noticeContent">${notice.noticeContent}</textarea></td>
				</tr>
			</table>
			<button type="button" id="modifyNoticeBtn">수정</button>
		</form>
	</body>
</html>