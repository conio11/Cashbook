<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> <!-- JSTL pattern 사용 -->
<%@ page import="java.net.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>modifyMember</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg  = urlParams.get("msg");
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
		      <h1>Yearly Statistics</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/calendar">Home</a></li>
		          <li class="breadcrumb-item">Pages</li>
		          <li class="breadcrumb-item active">Yearly Statistics</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->
		
		    <section class="section">
		      <div class="row">
		        <div class="col-lg-6">
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title text-center">${targetYear}년 수입</h5>
		              <h6>총 수입: <fmt:formatNumber value="${totalIncome}" pattern="#,###"/>원</h6>
		              <!-- Line Chart -->
		              <canvas id="lineChart" style="max-height: 400px;"></canvas>
		              <script>
		           		// JSTL을 사용하여 monthlyIncomeList를 가져옴
		            	<c:set var="monthlyIncomeList" value="${monthlyIncomeList}"/>

			            // monthlyIncomeList를 JavaScript 배열로 변환
			            var incomeData = [
			            	<c:forEach items="${monthlyIncomeList}" var="monthlyIncome" varStatus="status">
			                 	${monthlyIncome}
			                 <c:if test="${!status.last}">,</c:if> <!-- 마지막 데이터가 아니면 쉼표(,) 추가 -->
			                </c:forEach>
			              ];
		              
		                document.addEventListener("DOMContentLoaded", () => {
		                  new Chart(document.querySelector('#lineChart'), {
		                    type: 'line',
		                    data: {
		                      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월','12월'],
		                      datasets: [{
		                        label: '',
		                        data: incomeData, // 위에서 생성한 JavaScript 배열을 삽입
		                        fill: false,
		                        borderColor: 'rgb(46, 202, 106)',
		                        tension: 0.1
		                      }]
		                    },
		                    options: {
		                      scales: {
		                        y: {
		                        	 beginAtZero: true,
		                             ticks: {
		                                 callback: function (value, index, values) {
		                                     if (value === 0) return ''; // 0의 개수를 생략
		                                     if (value >= 10000) return value / 10000 + '만'; // 만 단위로 변환
		                                     return value;
		                                 }
		                             }
		                         }
		                      }
		                    }
		                  });
		                });
		              </script>
		              <!-- End Line CHart -->
		            </div>
		          </div>
		        </div>
		
		         <div class="col-lg-6">
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title text-center">${targetYear}년 지출</h5>
		              <h6>총 지출: <fmt:formatNumber value="${totalExpenses}" pattern="#,###"/>원</h6>
		
		              <!-- Line Chart -->
		              <canvas id="lineChart2" style="max-height: 400px;"></canvas>
		              <script>
		           		// JSTL을 사용하여 monthlyExpensesList를 가져옴
		            	<c:set var="monthlyExpensesList" value="${monthlyExpensesList}" />

		              // monthlyExpensesList를 JavaScript 배열로 변환
		              var expensesData = [
		                <c:forEach items="${monthlyExpensesList}" var="monthlyExpenses" varStatus="status">
		                  ${monthlyExpenses}
		                  <c:if test="${!status.last}">,</c:if> <!-- 마지막 데이터가 아니면 쉼표(,) 추가 -->
		                </c:forEach>
		              ];
		              
		                document.addEventListener("DOMContentLoaded", () => {
		                  new Chart(document.querySelector('#lineChart2'), {
		                    type: 'line',
		                    data: {
		                      labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월','12월'],
		                      datasets: [{
		                        label: '',
		                        data: expensesData,
		                        fill: false,
		                        borderColor: 'rgb(65, 84, 242)', 
		                        tension: 0.1
		                      }]
		                    },
		                    options: {
		                      scales: {
		                        y: {
		                        	 beginAtZero: true,
		                             ticks: {
		                                 callback: function (value, index, values) {
		                                     if (value === 0) return ''; // 0의 개수를 생략
		                                     if (value >= 10000) return value / 10000 + '만'; // 만 단위로 변환
		                                     return value;
		                                 }
		                             }
		                        }
		                      }
		                    }
		                  });
		                });
		              </script>
		              <!-- End Line CHart -->
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