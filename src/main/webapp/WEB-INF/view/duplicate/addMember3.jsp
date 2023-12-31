<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addMember</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#addMemberBtn").click(function() {
					if ($("#memberId").val() == "") {
						alert("아이디를 입력해주세요.");
						$("#memberId").focus();
					} else if ($("#memberPw").val() == "") {
						alert("비밀번호를 입력해주세요.");
						$("#memberPw").focus();
					} else if ($("#memberEmail").val() == "") {
						alert("비밀번호를 입력해주세요.");
						$("#memberEmail").focus();		
					} else {
						$("#addMember").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/off/login">로그인</a>
		<h1>회원가입</h1>
		<form method="post" action="${pageContext.request.contextPath}/off/addMember" id="addMember">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="memberId" id="memberId"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="memberEmail" id="memberEmail"></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="memberPw" id="memberPw"></td>
				</tr>
			</table>
			<button type="button" id="addMemberBtn">입력</button>
		</form>
	</body>
</html>