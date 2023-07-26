<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- JSTL pattern 사용 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>calendar</title>
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
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
		
		  <!-- =======================================================
		  * Template Name: NiceAdmin
		  * Updated: May 30 2023 with Bootstrap v5.3.0
		  * Template URL: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/
		  * Author: BootstrapMade.com
		  * License: https://bootstrapmade.com/license/
		  ======================================================== -->
	</head>
	<body>
		<!-- 변수 or 반환값 -> EL 사용 $...  -->
		<!--
			 속성값 대신 EL 사용  
			ex) request.getAttribute("targetYear") -- requestScope.targetYear (requestScope 생략 가능)	 
			형변환연산 필요하지 않음 -> EL이 자동으로 처리
	 	-->
		<!-- 자바 코드 (제어문) : JSTL  -->
		
		<jsp:include page="/WEB-INF/view/inc/memberHeader.jsp"></jsp:include>
  		<jsp:include page="/WEB-INF/view/inc/memberSidebar.jsp"></jsp:include>
		
		<main id="main" class="main">
    		<div class="pagetitle">
		      <h1>Home</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item active"><a href="${pageContext.request.contextPath}/on/calendar">Home</a></li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->
		    
		    <section class="section dashboard">
	      		<div class="row">
	
		        <!-- Left side columns -->
		        <div class="col-lg-12"> <!-- 전체 너비 설정 시 col-lg-12 -->
		          <div class="row">
		          
		            <!-- Income Card -->
		            <div class="col-xxl-4 col-md-6">
		              <div class="card info-card revenue-card">
		                <div class="card-body">
		                  <h5 class="card-title">${targetMonth + 1}월 수입</h5>
		                  <div class="d-flex align-items-center">
		                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
		                      <i class="bi bi-currency-dollar"></i>
		                    </div>
		                    <div class="ps-3">
		                      <h6><fmt:formatNumber value="${income}" pattern="#,###"/> 원</h6>
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div><!-- End Income Card -->
	 
		            <!-- Expenses Card -->
		            <div class="col-xxl-4 col-md-6">
		              <div class="card info-card sales-card">
		
		                <div class="card-body">
		                  <h5 class="card-title">${targetMonth + 1}월 지출</h5>
		
		                  <div class="d-flex align-items-center">
		                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
		                      <i class="bi bi-cart"></i>
		                    </div>
		                    <div class="ps-3">
		                      <h6><fmt:formatNumber value="${expenses}" pattern="#,###"/> 원</h6> <!-- value 값의 천의 자리마다 , 사용  -->
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div><!-- End Expenses Card -->

		            <!-- Customers Card -->
		            <div class="col-xxl-4 col-xl-12">
		              <div class="card info-card customers-card">
		                <div class="card-body">
		                  <h5 class="card-title">${targetMonth + 1}월 잔액</h5>
		                  <div class="d-flex align-items-center">
		                    <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
		                      <i class="bi bi-arrow-repeat"></i>
		                    </div>
		                    <div class="ps-3">
		                      <h6><fmt:formatNumber value="${income - expenses}" pattern="#,###"/> 원</h6>
		                    </div>
		                  </div>
		                </div>
		              </div>
		            </div><!-- End Customers Card -->
	
		            <!-- Reports -->
		            <div class="col-12">
		              <div class="card">
		                 <div class="card-body">
		                  <h5 class="card-title"></h5>
			    			<h5 class="card-title text-center">${targetYear}년 ${targetMonth + 1}월</h5>
							<div>
								<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth - 1}" class="btn btn-outline-primary">pre</a>
								<a href="${pageContext.request.contextPath}/on/calendar?targetYear=${targetYear}&targetMonth=${targetMonth + 1}" class="float-end btn btn-outline-primary">next</a>
							</div>
							<br>
							<div>
								<h5 class="card-title">이달의 해시태그</h5>
								<div>
									<c:forEach var="m" items="${htList}">
										<a href="${pageContext.request.contextPath}/on/hashtagOne?word=${m.word}" class="btn btn-sm">${m.word}(${m.cnt})</a>
									</c:forEach>
								</div>
							</div>
							<!-- Bordered Table -->
							<table class="table table-bordered" style="height: 600px;">
								<thead>
									<tr class="text-center">
										<th scope="col" class="text-bg-primary">일</th>
										<th scope="col" class="text-bg-primary">월</th>
										<th scope="col" class="text-bg-primary">화</th>
										<th scope="col" class="text-bg-primary">수</th>
										<th scope="col" class="text-bg-primary">목</th>
										<th scope="col" class="text-bg-primary">금</th>
										<th scope="col" class="text-bg-primary">토</th>
									</tr>
								</thead>
								<tbody>
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
								<!-- End Bordered Table -->
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		    </section> 
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