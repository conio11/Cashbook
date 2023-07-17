<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>noticeList</title>
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
		<a href="${pageContext.request.contextPath}/on/calendar">홈 화면으로</a>
		<br>
		<h1>공지사항</h1>
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일자</th>
			</tr>
			<c:forEach var="n" items="${list}">
			<tr>
				<td>${n.noticeNo}</td>
				<td><a href="${pageContext.request.contextPath}/onOff/noticeOne?noticeNo=${n.noticeNo}">${n.noticeTitle}</a></td>
				<td>${n.createdate}</td>
			</tr>
			</c:forEach>
		</table>
		
		<br>
		
		<!-- minPage가 1보다 클 때만 [이전] 탭 출력  -->
		<c:if test="${minPage > 1}">
			<a href="${pageContext.request.contextPath}/onOff/noticeList?currentPage=${minPage - pagePerPage}">이전</a>
		</c:if>
		
		<!-- [이전], [다음] 탭 내에서 반복 -->
		<c:forEach var="i" begin="${minPage}" end="${maxPage}">
			<c:choose>
				<c:when test="${currentPage == i}"> <!-- 현재 페이지 번호는 링크 없이 표시 -->
					<span>${i}</span>
				</c:when>
				 <c:otherwise> <!-- 현재 페이지가 아닌 번호는 링크로 표시 (클릭 시 해당 번호 페이지로 이동) -->
      				  <a href="${pageContext.request.contextPath}/onOff/noticeList?currentPage=${i}">${i}</a>
    			</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<!-- [이전] [다음] 탭 사이 가장 큰 숫자가 lastPage보다 작을 때만 [다음] 탭 출력  -->
		<c:if test="${maxPage < lastPage}">
			<a href="${pageContext.request.contextPath}/onOff/noticeList?currentPage=${minPage + pagePerPage}">다음</a>
		</c:if>
		
		<br>
		<c:if test="${fn:startsWith(loginId, 'admin')}"> <!-- 입력 버튼 관리자에게만 노출 -->
			<a href="${pageContext.request.contextPath}/on/addNotice">새 공지 입력</a>
		</c:if>
	</body>
</html>