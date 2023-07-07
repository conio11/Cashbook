<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>cashbookListOne</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
			});
		</script>
	</head>
	<body>
		<div class="container mt-3">
		<a href="${pageContext.request.contextPath}/calendar" class="btn btn-success">홈 화면으로</a>
		<br>
		<div class="text-center">
			<h1>${targetYear}년 ${targetMonth + 1}월 ${targetDate}일 가계부</h1>
		</div>
		<br>
		
		<table class="table table-bordered">
			<tr class="text-bg-success text-center">
				<th>카테고리</th>
				<th>금액</th>
				<th>메모</th>
				<th>작성일자</th>
				<th>&nbsp;</th>
				<th>&nbsp;</th>
			</tr>
		<c:forEach var="c" items="${list}">
			<tr class="text-center">
				<td>${c.category}</td>	
				<td>${c.price}</td>
				<td>${c.memo}</td>	
				<td>${fn:substring(c.createdate, 0, 10)}</td>
				<!-- URL에 # 입력 시 오류 발생 가능 -->
				<td><a href="${pageContext.request.contextPath}/modifyCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-success">수정</a></td>
				<td>
					<a href="${pageContext.request.contextPath}/removeCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-success">
						삭제
					</a>
				</td>			
			</tr>
		</c:forEach>
		</table>
		
		<a href="${pageContext.request.contextPath}/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-success">새로 입력</a>
		</div>
	</body>
</html>