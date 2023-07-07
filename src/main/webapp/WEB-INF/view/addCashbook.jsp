<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addCashbook</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#addCashbookBtn").click(function() {
					var checkedRadio = $('input[name="category"]:checked').length; // 버튼 클릭 함수 위에 위치할 경우 항상 0이 되는 현상
					if (checkedRadio === 0) {
						alert("수입 또는 지출을 선택해주세요.");
					} else if ($("#price").val() == "") {
						alert("금액을 입력해주세요.");
						$("#price").focus();
					} else if ($("#price").val() < 1) {
						alert("유효한 금액을 입력해주세요.");
						$("#price").val("");
						$("#price").focus();
					} else if ($("#memo").val() == "") {
						alert("메모를 입력해주세요.");
						$("#memo").focus();
					} else {
						$("#addCashbook").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<a href="${pageContext.request.contextPath}/calendar" class="btn btn-success">홈 화면으로</a>
			<a href="${pageContext.request.contextPath}/cashbookListOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-success">이전</a>
			<br>
			<div class="text-center">
				<h1>가계부 입력</h1>
			</div>
			<form method="post" action="${pageContext.request.contextPath}/addCashbook" id="addCashbook">
				<input type="hidden" name="targetYear" value="${targetYear}">
				<input type="hidden" name="targetMonth" value="${targetMonth}">
				<input type="hidden" name="targetDate" value="${targetDate}">
				<br>
				<table class="table table-bordered">
					<tr>
						<td class="text-bg-success text-center">날짜</td>
						<td>${targetYear}년 ${targetMonth + 1}월 ${targetDate}일</td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">카테고리</td>
						<td>
							<input type="radio" name="category" value="수입" class="form-check-input"><span>수입</span>
							<input type="radio" name="category" value="지출" class="form-check-input"><span>지출</span>
						</td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">금액</td>
						<td><input type="number" name="price" id="price" class="form-control w-25"></td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">메모</td> <!-- 해시태그 -->
						<td><textarea name="memo" cols="50" rows="5" id="memo" class="form-control w-75"></textarea>
					</tr>
				</table>
				<button type="button" id="addCashbookBtn" class="btn btn-outline-success">입력</button>
			</form>
		</div>
	</body>
</html>