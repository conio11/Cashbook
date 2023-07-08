<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>hashtagOne</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
  		<style>
  			.page-link {
  				color: #198754; /* 부트스트랩5 success 컬러코드*/
			}
			.page-item.active .page-link {
				color: white;
				background-color: #198754;
				border-color: #198754;
			} 
  		</style>
	</head>
	<body>
		<div class="container mt-3">
		<a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-success">홈 화면으로</a>
		<br>
		<div class="text-center">	
			<h1>#${word}</h1>
		</div>
		<br>
		<table class="table table-bordered">
			<tr class="text-bg-success text-center">
				<td>날짜</td>
				<td>카테고리</td>
				<td>금액</td>
				<td>메모</td>
			</tr>
			<c:forEach var="h" items="${list}">
			<tr class="text-center">
				<td>${h.cashbookDate}</td>
				<td>${h.category}</td>
				<td>${h.price}</td>
				<td>${h.memo}</td>
			</tr>
			</c:forEach>
		</table>
		<br>
		
		<ul class="pagination justify-content-center">
		<!-- minPage가 1보다 클 때만 [이전] 탭 출력  -->
		<c:if test="${minPage > 1}">
			<li class="page-item"><a href="${pageContext.request.contextPath}/on/hashtagOne?word=${word}&currentPage=${minPage - pagePerPage}" class="page-link">이전</a></li>
		</c:if>
		
		<!-- [이전], [다음] 탭 내에서 반복 -->
		<c:forEach var="i" begin="${minPage}" end="${maxPage}">
			<c:choose>
				<c:when test="${currentPage == i}"> <!-- 현재 페이지 번호는 링크 없이 표시 -->
					<li class="page-item active"><a class="page-link"><span>${i}</span></a></li>
				</c:when>
				 <c:otherwise> <!-- 현재 페이지가 아닌 번호는 링크로 표시 (클릭 시 해당 번호 페이지로 이동) -->
      				  <li class="page-item"><a href="${pageContext.request.contextPath}/on/hashtagOne?word=${word}&currentPage=${i}" class="page-link">${i}</a></li>
    			</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<!-- [이전] [다음] 탭 사이 가장 큰 숫자가 lastPage보다 작을 때만 [다음] 탭 출력  -->
		<c:if test="${maxPage < lastPage}">
			<li class="page-item"><a href="${pageContext.request.contextPath}/on/hashtagOne?word=${word}&currentPage=${minPage + pagePerPage}" class="page-link">다음</a></li>
		</c:if>
		</ul>
		</div>
	</body>
</html>