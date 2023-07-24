<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>hashtagOne</title>
	  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				$(".datatable-dropdown").empty();
				$(".datatable-bottom").remove();
				
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
			});
  		</script>
<!--   		<style>
  			.page-link {
  				color: #198754; /* 부트스트랩5 success 컬러코드*/
			}
			.page-item.active .page-link {
				color: white;
				background-color: #198754;
				border-color: #198754;
			} 
  		</style> -->
  		
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
		      <h1>Hashtag</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/calendar">Home</a></li>
		          <li class="breadcrumb-item active">Hashtag</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->
	
		    <section class="section">
		      <div class="row">
		        <div class="col-lg-12">
		
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title">Hashtags</h5>
		              <p></p>
		
		              <!-- Table with stripped rows -->
		              <table class="table datatable">
		                <thead>
		                  <tr>
		                    <th scope="col">날짜</th>
		                    <th scope="col">카테고리</th>
		                    <th scope="col">금액</th>
		          			<th scope="col">메모</th>
		                  </tr>
		                </thead>
		                <tbody>
		           			<c:forEach var="h" items="${list}">
								<tr class="text-center">
									<td>${h.cashbookDate}</td>
									<td>${h.category}</td>
									<td>${h.price}</td>
									<td>${h.memo}</td>
								</tr>
							</c:forEach>
		                </tbody>
		              </table>
		              <br>
		              <!-- End Table with stripped rows -->
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