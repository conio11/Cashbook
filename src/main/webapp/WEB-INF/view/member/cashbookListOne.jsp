<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- JSTL pattern 사용 -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>cashbookListOne</title>
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
		
		<meta content="" name="description">
		<meta content="" name="keywords">
		
		<!-- Favicons -->
		<link href="${pageContext.request.contextPath}/assets/img/favicon.png" rel="icon">
		<link href="${pageContext.request.contextPath}/assets/img/apple-touch-icon.png" rel="apple-touch-icon">
		
		<!-- Google Fonts -->
		<link href="https://fonts.gstatic.com" rel="preconnect">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
		
		<!-- Vendor CSS Files -->
		<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.snow.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/quill/quill.bubble.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/remixicon/remixicon.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/assets/vendor/simple-datatables/style.css" rel="stylesheet">
		
		<!-- Template Main CSS File -->
		<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="/WEB-INF/view/inc/memberHeader.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/view/inc/memberSidebar.jsp"></jsp:include>
		
		<main id="main" class="main">

		    <div class="pagetitle">
		      <h1>Cashbook</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/calendar">Home</a></li>
		          <li class="breadcrumb-item active">Cashbook</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->
	
		    <section class="section">
		      <div class="row">
		        <div class="col-lg-12">
		          <div class="card">
		            <div class="card-body"><br>
		              <h5 class="card-title text-center">${targetYear}년 ${targetMonth + 1}월 ${targetDate}일 가계부</h5>
		              
		              <p>수입: <fmt:formatNumber value="${income}" pattern="#,###"/>원 / 지출: <fmt:formatNumber value="${expenses}" pattern="#,###"/>원</p>
		              <p>잔액: <fmt:formatNumber value="${income - expenses}" pattern="#,###"/>원</p>
		              <!-- Table with stripped rows -->
		              <table class="table table-bordered">
		              	<thead>
		                	<tr>
			                    <th scope="col" class="text-center">카테고리</th>
			                    <th scope="col" class="text-center">금액</th>
			                    <th scope="col" class="text-center">메모</th>
			                    <th scope="col" class="text-center">작성일자</th>
			                    <th scope="col" class="text-center">&nbsp;</th>
			                    <th scope="col" class="text-center">&nbsp;</th>
		                 	 </tr>
	                  	 </thead>
	                   		<tbody>
							<c:forEach var="c" items="${list}">
								<tr class="text-center">
									<td>${c.category}</td>	
									<td>${c.price}</td>
									<td>${c.memo}</td>	
									<td>${fn:substring(c.createdate, 0, 10)}</td>
									<!-- URL에 # 입력 시 오류 발생 가능 -->
									<td><a href="${pageContext.request.contextPath}/on/modifyCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-primary">수정</a></td>
									<td>
										<a href="${pageContext.request.contextPath}/on/removeCashbook?cashbookNo=${c.cashbookNo}&targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-primary">삭제</a></td>			
									</tr>
							</c:forEach>
						   </tbody>
		             	</table>
		             	<div class="text-center">
		             		<a href="${pageContext.request.contextPath}/on/addCashbook?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-primary">새로 입력</a>
		             	</div>
		              <br>
		              <!-- End Table with stripped rows -->
		            </div>
		          </div>
		        </div>
		      </div>
		   </section>
		   <a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-outline-primary">이전</a>
	    </main><!-- End #main -->
		
		<jsp:include page="/WEB-INF/view/inc/footer.jsp"></jsp:include> <!-- webapp 이후부터 경로 작성하기  -->
	
	    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>
		
	    <!-- Vendor JS Files -->
	    <script src="${pageContext.request.contextPath}/assets/vendor/apexcharts/apexcharts.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/chart.js/chart.umd.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/echarts/echarts.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/quill/quill.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/simple-datatables/simple-datatables.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/tinymce/tinymce.min.js"></script>
	    <script src="${pageContext.request.contextPath}/assets/vendor/php-email-form/validate.js"></script>
	
	    <!-- Template Main JS File -->
	    <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
	</body>
</html>