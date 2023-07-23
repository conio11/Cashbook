<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>addNotice</title>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
		<script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
				$("#addNoticeBtn").click(function() {
					if ($("#noticeTitle").val() == "") {
						alert("제목을 입력해주세요.");
						$("#noticeTitle").focus();
					} else if ($("#noticeContent").val() == "") {
						alert("내용을 입력해주세요.");
						$("#noticeContent").val("");
						$("#noticeContent").focus();
					} else {
						$("#addNotice").submit();
					}
				});
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
		<jsp:include page="/WEB-INF/view/inc/adminHeader.jsp"></jsp:include>
    	<jsp:include page="/WEB-INF/view/inc/adminSidebar.jsp"></jsp:include>
    	
		<main id="main" class="main">
			<div class="pagetitle">
		      <h1>Notice</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/cashbook">Home</a></li>
		          <li class="breadcrumb-item">Pages</li>
		          <li><a href="${pageContext.request.contextPath}/onOff/noticeList"">Notice List</a><li>
		          <li class="breadcrumb-item active">Add Notice</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->
		
		    <section class="section">
		      <div class="row">
		        <div class="col-lg-12">
		
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title">Add Notice</h5>
					  <p></p>
					  
					  <form method="post" action="${pageContext.request.contextPath}/on/addNotice" id="addNotice">
		              <!-- Table with stripped rows -->
		              <table class="table table-bordered">
		       			<tr>
							<th scope="col" class="text-center">제목</th>
							<td><input type="text" name="noticeTitle" class="form-control" id="noticeTitle"></td>
						</tr>
						<tr>
							<th scope="col" class="text-center">내용</th>
							<td><textarea name="noticeContent" class="form-control" id="noticeContent" cols="60" rows="5"></textarea></td>
						</tr>
		              </table>
		              <div class="text-center">
		                  <button type="button" class="btn btn-primary" id="addNoticeBtn">입력</button>
		              </div>
		              </form>
		              
		              <!-- End Table with stripped rows -->
		            </div>
		          </div>
		        </div>
		      </div>
		    </section>
		    <a href="${pageContext.request.contextPath}/onOff/noticeList" class="btn btn-outline-primary">이전</a>
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