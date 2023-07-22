<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		
		<title>myQuestionOne</title>
		
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
    	<jsp:include page="/WEB-INF/view/inc/memberHeader.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/view/inc/memberSidebar.jsp"></jsp:include>
		 
		<main id="main" class="main">

		    <div class="pagetitle">
		      <h1>Data Tables</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
		          <li class="breadcrumb-item">Tables</li>
		          <li class="breadcrumb-item active">Data</li>
		        </ol>
		      </nav>
		    </div><!-- End Page Title -->

	    <section class="section">
	      <div class="row">
	        <div class="col-lg-12">
	
	          <div class="card">
	            <div class="card-body">
	              <h5 class="card-title">Datatables</h5>
	              <p>Add lightweight datatables to your project with using the <a href="https://github.com/fiduswriter/Simple-DataTables" target="_blank">Simple DataTables</a> library. Just add <code>.datatable</code> class name to any table you wish to conver to a datatable</p>
	
	              <!-- Table with stripped rows -->
	              <table class="table table-bordered">
	                  <tr>
	                    <th scope="col" class="text-center">번호</th>
	                    <td>${question.qNo}</td>
	                  </tr>
	       			<tr>
						<th scope="col" class="text-center">제목</th>
						<td>${question.qTitle}</td>
					</tr>
					<tr>
						<th scope="col" class="text-center">내용</th>
						<td><textarea class="form-control" id="qContent" cols="60" rows="5" readonly>${question.qContent}</textarea></td>
					</tr>
					<tr>
						<th scope="col" class="text-center">상태</th>
						<td>${question.qStatus}</td>
					</tr>
					<tr>
						<th scope="col" class="text-center">작성일자</th>
						<td>${question.createdate}</td>
					</tr>
					<tr>
						<th scope="col" class="text-center">수정일자</th>
						<td>${question.updatedate}</td>
					</tr>
	
	             	 </table>
	              <br>
	              <!-- End Table with stripped rows -->
			  
				<c:if test="${question.qStatus eq '답변대기'}">
					<a href="${pageContext.request.contextPath}/on/modifyQuestion?qNo=${question.qNo}" class="btn btn-outline-secondary">수정</a>
					<a href="${pageContext.request.contextPath}/on/removeQuestion?qNo=${question.qNo}" class="btn btn-outline-secondary">삭제</a>
				</c:if>
				
	
				<br>
		
	            </div>
	          </div>
	
	        </div>
	      </div>
	    </section>
	    
	    <c:if test="${question.qStatus eq '답변완료'}">
			<section class="section">
		      <div class="row">
		        <div class="col-lg-12">
		          <div class="card">
		            <div class="card-body">
		              <h5 class="card-title">Datatables</h5>
		              <p>Add lightweight datatables to your project with using the <a href="https://github.com/fiduswriter/Simple-DataTables" target="_blank">Simple DataTables</a> library. Just add <code>.datatable</code> class name to any table you wish to conver to a datatable</p>
		              <!-- Table with stripped rows -->

		              <table class="table table-bordered">
		                  <tr>
		                    <th scope="col" class="text-center">답변</th>
		                    <td><textarea name="answerContent" class="form-control" id="questionContent" cols="60" rows="5" readonly>${answer.aContent}</textarea></td>
		                  </tr>
		              </table>
		              <br>
		              <!-- End Table with stripped rows -->
				      <br>
		            </div>
		          </div>
		        </div>
		      </div>
		    </section>
		</c:if>
	
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