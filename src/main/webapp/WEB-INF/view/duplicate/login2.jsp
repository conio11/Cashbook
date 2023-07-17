<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>login</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#loginBtn").click(function() {
					if ($("#memberId").val() == "") {
						alert("아이디를 입력해주세요.");
						$("#memberId").focus();
					} else if ($("#memberPw").val() == "") {
						alert("비밀번호를 입력해주세요.");
						$("#memberPw").focus();
					} else {
						$("#login").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<a href="${pageContext.request.contextPath}/addMember">회원가입</a>
		<h1>로그인</h1>
		<form method="post" action="${pageContext.request.contextPath}/login" id="login">
			<table>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="memberId" id="memberId" value="${memberId}"></td>
					<td rowspan="2"><button type="button" id="loginBtn">로그인</button></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="memberPw" id="memberPw"></td>
				</tr>
			</table>
			<input type="checkbox" name="idSave" value="y">ID 저장 <!-- 체크가 되어있으면 value 넘어옴  -->
		</form>
	</body>
</html>