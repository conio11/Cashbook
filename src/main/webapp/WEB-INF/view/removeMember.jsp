<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>removeMember</title>
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
				
				$("#removeMemberBtn").click(function() {
					if ($("#memberPw").val() == "") {
						alert("비밀번호를 입력해주세요.");	
						$("#memberPw").focus();
					} else {
						$("#removeMember").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<a href="${pageContext.request.contextPath}/calendar" class="btn btn-success">홈 화면으로</a>
			<a href="${pageContext.request.contextPath}/memberOne" class="btn btn-success">이전</a>
			<br>
			<div class="text-center">
				<h1>회원탈퇴</h1>
			</div>
			<br>
			<form method="post" action="${pageContext.request.contextPath}/removeMember" id="removeMember">
				<table class="table table-bordered">
					<tr>
						<td class="text-bg-success text-center">비밀번호 입력</td>
						<td><input type="password" name="memberPw" id="memberPw" class="form-control w-25"></td>
					</tr>
				</table>
				<button type="button" id="removeMemberBtn" class="btn btn-outline-success">회원탈퇴</button>
			</form>
		</div>
	</body>
</html>