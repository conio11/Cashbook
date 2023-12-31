<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<%	
	/*
	int targetYear = (int) request.getAttribute("targetYear");
	int targetMonth = (int) request.getAttribute("targetMonth");
	int lastDate = (int) request.getAttribute("lastDate");
	int totalCell = (int) request.getAttribute("totalCell");
	int beginBlank = (int) request.getAttribute("beginBlank");
	int endBlank = (int) request.getAttribute("endBlank");
	int preLastDate = (int) request.getAttribute("preLastDate");
	
	System.out.println(targetYear + " <-- targetYear(calendar)");
	System.out.println(targetMonth + " <-- targetMonth(calendar)");
	System.out.println(lastDate + " <-- lastDate(calendar)");
	System.out.println(preLastDate + " <-- preLastDate(calendar)");
	System.out.println(totalCell + " <-- totalCell(calendar)");
	System.out.println(beginBlank + " <-- beginBlank(calendar)");
	System.out.println(endBlank + " <-- endBlank(calendar)");
	*/
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>calendar</title>
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
		<!-- 변수 or 반환값 -> EL 사용 $...  -->
		<!--
			 속성값 대신 EL 사용  
			ex) request.getAttribute("targetYear") -- requestScope.targetYear (requestScope 생략 가능)	 
			형변환연산 필요하지 않음 -> EL이 자동으로 처리
	 	-->
		<!-- 자바 코드 (제어문) : JSTL  -->
		<div class="container mt-3">
		<div class="btn-group">
			<a href="${pageContext.request.contextPath}/on/logout" class="btn btn-success">로그아웃</a>
			<a href="${pageContext.request.contextPath}/on/memberOne" class="btn btn-success">회원정보</a>
			<a href="${pageContext.request.contextPath}/onOff/noticeList" class="btn btn-success">공지사항</a>
			<a href="${pageContext.request.contextPath}/onOff/QnAList" class="btn btn-success">QnA</a>
		</div>
		<div class="text-center">
			<h1>${targetYear}년 ${targetMonth + 1}월</h1>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth - 1}" class="btn btn-outline-success">이전 달</a>
			<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth + 1}" class="float-end btn btn-outline-success">다음 달</a>
		</div>
		<br>
		
		<div>
			<h3>이달의 해시태그</h3>
			<div>
				<c:forEach var="m" items="${htList}">
					<a href="${pageContext.request.contextPath}/on/hashtagOne?word=${m.word}" class="btn btn-sm">${m.word}(${m.cnt})</a>
				</c:forEach>
			</div>
		</div>
		
		<table class="table table-bordered">
			<tr>
				<th class="text-bg-success text-center">일</th>
				<th class="text-bg-success text-center">월</th>
				<th class="text-bg-success text-center">화</th>
				<th class="text-bg-success text-center">수</th>
				<th class="text-bg-success text-center">목</th>
				<th class="text-bg-success text-center">금</th>
				<th class="text-bg-success text-center">토</th>
			</tr>
			<tr>
				<c:forEach var="i" begin="0" end="${totalCell - 1}" step="1">
					<c:set var="d" value="${i - beginBlank + 1}"></c:set> <!-- d = i - beginBlank + 1  -->
				
					<c:if test="${i != 0 && i % 7 == 0}">
						</tr><tr>
					</c:if>

					<c:if test="${d < 1}">
						<td style="color: gray;">${preLastDate + d}</td>
					</c:if>
					<c:if test="${d > lastDate}">
						<td style="color: gray;">${d - lastDate}</td>
					</c:if>
					<c:if test="${!(d < 1 || d > lastDate)}">
						<td>
							<div>
								<a href="${pageContext.request.contextPath}/on/cashbookListOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${d}" class="btn">${d}</a>
							</div>
							<c:forEach var="c" items="${list}">
								<c:if test="${d == fn:substring(c.cashbookDate, 8, 10)}">
									<div>
										<c:if test="${c.category == '수입'}">
											<span>+${c.price}</span>  <!--c.getPrice() -->
										</c:if>
										<c:if test="${c.category == '지출'}">
											<span style="color: red;">-${c.price}</span>
										</c:if>
									</div>
								</c:if>
							</c:forEach>
						</td>
					</c:if>
				</c:forEach>
			</tr>
		</table>
		</div>
	</body>
</html>