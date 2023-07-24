<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addCashbook</title>
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#addCashbookBtn").click(function() {
					var checkedRadio = $('input[name="category"]:checked').length; // 버튼 클릭 함수 위에 위치할 경우 항상 0이 되는 현상
					if (checkedRadio === 0) {
						alert("수입 또는 지출을 선택해주세요.");
					} else if ($("#price").val() == "") {
						alert("금액을 입력해주세요.");
						$("#price").focus();
					} else if ($("#price").val() < 1) {
						alert("유효한 금액을 입력해주세요.");
						$("#price").val("");
						$("#price").focus();
					} else if ($("#memo").val() == "") {
						alert("메모를 입력해주세요.");
						$("#memo").focus();
					} else {
						$("#addCashbook").submit();
					}
				});
			});
		</script>
		<style>
		 /* 라디오 버튼들 간의 간격을 조정할 클래스 선택자에 margin 설정 */
		  .radio-margin {
		    margin-right: 10px; 
		  }
		</style>
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
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/cashbookListOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}">Cashbook</a></li>
		          <li class="breadcrumb-item active">Add Cashbook</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->

		    <section class="section">
		      <div class="row">
		        <div class="col-lg-12">
		
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title">Add Cashbook</h5>
		              <p></p>
		             
		              <!-- Table with stripped rows -->
		              <form method="post" action="${pageContext.request.contextPath}/on/addCashbook" id="addCashbook">
		              	  <input type="hidden" name="targetYear" value="${targetYear}">
						  <input type="hidden" name="targetMonth" value="${targetMonth}">
						  <input type="hidden" name="targetDate" value="${targetDate}">
		              
			              <table class="table table-bordered">
			                  <tr>
			                    <th scope="col" class="text-center">날짜</th>
			                    <td>${targetYear}년 ${targetMonth + 1}월 ${targetDate}일</td>
			                  </tr>
				       			<tr>
									<th scope="col" class="text-center">카테고리</th>
									<td>
										<input type="radio" name="category" value="수입" class="form-check-input radio-margin"><span>수입</span>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="radio" name="category" value="지출" class="form-check-input radio-margin"><span>지출</span>
									</td>
								</tr>
								<tr>
									<th scope="col" class="text-center">금액</th>
									<td><input type="number" name="price" id="price" class="form-control"></td>
								</tr>
								<tr>
									<th scope="col" class="text-center">메모</th>
									<td><textarea name="memo" cols="50" rows="5" id="memo" class="form-control"></textarea></td> <!-- textarea는 value 대신 태그 사이에 값 지정  -->
								</tr>
			             	 </table>
			             	 <div class="text-center">
								<button type="button" id="addCashbookBtn" class="btn btn-primary">입력</button>
		              		</div>
		              	</form>
		              <br>
		              <!-- End Table with stripped rows -->

		            </div>
		          </div>
		        </div>
		      </div>
			</section>
			<a href="${pageContext.request.contextPath}/on/cashbookListOne?targetYear=${targetYear}&targetMonth=${targetMonth}&targetDate=${targetDate}" class="btn btn-outline-primary">이전</a>
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