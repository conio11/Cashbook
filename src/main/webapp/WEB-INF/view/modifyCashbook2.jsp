<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyCashbook</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$("#modifyCashbookBtn").click(function() {
					var checkedRadio = $('input[name="category"]:checked').length;
					if (checkedRadio === 0) {
						alert("수입 또는 지출을 선택해주세요.");
					} else if ($("#price").val() == "") {
						alert("금액을 입력해주세요.");
						$("#price").focus();
					} else if ($("#price").val() < 1) {
						alert("유효한 금액을 입력해주세요.");
						$("#price").val("");
						$("#price").focus();
					} else {
						$("#modifyCashbook").submit();
					}
				});
			});
		</script>
	</head>
	<body>
		<div class="container mt-3">
			<a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-success">홈 화면으로</a>
			<a href="${pageContext.request.contextPath}/on/cashbookListOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-success">이전</a>
			<br>
			<div class="text-center">
				<h1>가계부 수정</h1>
			</div>
			<form method="post" action="${pageContext.request.contextPath}/on/modifyCashbook" id="modifyCashbook">
				<input type="hidden" name="targetYear" value="${targetYear}">
				<input type="hidden" name="targetMonth" value="${targetMonth}">
				<input type="hidden" name="targetDate" value="${targetDate}">
				<input type="hidden" name="cashbookNo" value="${cashbook.cashbookNo}">
				<br>
				<table class="table table-bordered">
					<tr>
						<td class="text-bg-success text-center">날짜</td>
						<td>${targetYear}년 ${targetMonth + 1}월 ${targetDate}일</td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">카테고리</td>
						<td>
							<input type="radio" name="category" value="수입" class="form-check-input"><span>수입</span>&nbsp;
							<input type="radio" name="category" value="지출" class="form-check-input"><span>지출</span>
						</td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">금액</td>
						<td><input type="number" name="price" value="${cashbook.price}" id="price" class="form-control w-25"></td>
					</tr>
					<tr>
						<td class="text-bg-success text-center">메모</td>
						<td><textarea name="memo" cols="50" rows="5" readonly class="form-control w-75">${cashbook.memo}</textarea></td> <!-- textarea는 value 대신 태그 사이에 값 지정  -->
					</tr>
				</table>
				<button type="button" id="modifyCashbookBtn" class="btn btn-outline-success">수정</button>
			</form>
		</div>
	</body>
</html>