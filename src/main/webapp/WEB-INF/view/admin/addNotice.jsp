<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addNotice</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#addNoticeBtn").click(function() {
					if ($("#noticeTitle").val() == "") {
						alert("제목을 입력해주세요.");
						$("#noticeTitle").focus();
					} else if ($("#noticeContent").val() == "") {
						alert("내용을 입력해주세요.");
						$("#noticeContent").val("");
						$("#noticeContent").focus();
					} else {
						$("#addNotice").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/on/calendar">홈 화면으로</a>
		<a href="${pageContext.request.contextPath}/onOff/noticeList">이전</a>
		<br>
		<h1>공지 입력</h1>
		<form method="post" action="${pageContext.request.contextPath}/on/addNotice" id="addNotice">
			<table border="1">
				<tr>
					<th>제목</th>
					<td><input type="text" name="noticeTitle" id="noticeTitle"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="noticeContent" id="noticeContent" cols="60" rows="5"></textarea></td>
				</tr>				
			</table>
			<button type="button" id="addNoticeBtn">입력</button>
		</form>
	
	</body>
</html>