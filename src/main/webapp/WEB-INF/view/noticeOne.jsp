<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>noticeOne</title>
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
		<a href="${pageContext.request.contextPath}/onOff/noticeList">이전</a>
		<h1>공지 상세</h1>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${notice.noticeNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${notice.noticeTitle}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${notice.noticeContent}</td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td>${notice.createdate}</td>
			</tr>
			<tr>
				<th>수정일자</th>
				<td>${notice.updatedate}</td>
			</tr>
		</table>
		<c:if test="${fn:startsWith(loginMemberId, 'admin')}"> <!-- 수정, 삭제 버튼 관리자에게만 노출  -->
			<a href="${pageContext.request.contextPath}/on/modifyNotice?noticeNo=${notice.noticeNo}">수정</a>
			<a href="${pageContext.request.contextPath}/on/removeNotice?noticeNo=${notice.noticeNo}">삭제</a>
		</c:if>
	</body>
</html>