<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyMember</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg  = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#newPw1").blur(function() {
					let newPw = $(this).val();
					let message = $(newPwMsg);
					if (newPw.length < 4) {
						message.text("4자 이상의 비밀번호를 입력해주세요.");
						$(this).focus();
					} else {
						message.text("");
					}
				});
				
				$("#modifyMemberBtn").click(function() {
					if ($("#memberPw").val() == "") {
						alert("현재 비밀번호를 입력해주세요.");
						$("#memberPw").focus();
					} else if ($("#newPw1").val() == "") {
						alert("새 비밀번호를 입력해주세요.");
						$("#newPw1").focus();
					} else if ($("#newPw2").val() == "") {
						alert("새 비밀번호를 다시 입력해주세요.");
						$("#newPw2").focus();
					} else if ($("#newPw2").val() != $("#newPw1").val()) {
						alert("새 비밀번호가 일치하지 않습니다.");
						$("#newPw2").val(""); // 값 비우기
						$("#newPw2").focus();
					} else {
						$("#modifyMember").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-success">홈 화면으로</a>
			<a href="${pageContext.request.contextPath}/on/memberOne" class="btn btn-success">이전</a>
			<br>
			<div class="text-center">
				<h1>회원정보수정</h1>
			</div>
			<br>
			<form method="post" action="${pageContext.request.contextPath}/on/modifyMember" id="modifyMember">
				<table class="table table-bordered">
					<tr>
						<td class="text-bg-success text-center">현재 비밀번호 입력</td>
						<td><input type="password" name="memberPw" id="memberPw" class="form-control w-25">
					</tr>
					<tr>
						<td class="text-bg-success text-center">새 비밀번호 입력</td>
						<td>
							<input type="password" name="newPw1" id="newPw1" class="form-control w-25">
							<span id="newPwMsg" style="color: red;"></span>				
						</td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">새 비밀번호 다시 입력</td>
						<td><input type="password" name="newPw2" id="newPw2" class="form-control w-25"></td>
					</tr>
				</table>
				<button type="button" id="modifyMemberBtn" class="btn btn-outline-success">비밀번호 수정</button>
			</form>
		</div>
	</body>
</html>