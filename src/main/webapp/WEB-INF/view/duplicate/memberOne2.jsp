<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cashbook.model.MemberDao"%>
<%@ page import="cashbook.vo.Member"%>

<%
	// Member m = (Member) request.getAttribute("member"); // request 보낼 때 "member"라는 이름의 member 객체 생성
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>memberOne</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
 		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
  		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
	</head>
	<body>
		<div class="container mt-3">
			<!-- <div class="col-md-6 offset-md-3"> -->
			 <div class="row">
    		 <!-- <div class="col-sm-8"> -->
    		 <div class="row-cols-auto">
			 <a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-success">홈 화면으로</a>
			 <br><br>
			 <div class="text-center">
				<h1>회원 상세정보</h1>
			 </div>
			 <br>
			 
   			
			<table class="table table-bordered">
				<tr>
					<td class="text-bg-success text-center">아이디</td>
					<td>${member.memberId}</td>
				</tr>
				<tr>
					<td class="text-bg-success text-center">이름</td>
					<td>${member.memberName}</td>
				</tr> 
				<tr>
					<td class="text-bg-success text-center">이메일</td>
					<td>${member.memberEmail}</td>
				</tr> 
				<tr>
					<td class="text-bg-success text-center">가입일자</td>
					<td>${member.createdate}</td>
				</tr>
				<tr>
					<td class="text-bg-success text-center">정보수정일자</td>
					<td>${member.updatedate}</td>
				</tr>
			</table>
			<a href="${pageContext.request.contextPath}/on/modifyMember" class="btn btn-outline-success">회원정보수정</a>
			<a href="${pageContext.request.contextPath}/on/removeMember" class="btn btn-outline-success">회원탈퇴</a>
			<!-- </div> -->
				</div>
				</div>
		</div>
	</body>
</html>