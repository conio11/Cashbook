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
		<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>memberOne</title>
		
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
	

<%-- 		 <a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-success">홈 화면으로</a>
		 <br><br>
		 <div class="text-center">
			<h1>회원 상세정보</h1>
		 </div>
		 <br> --%>

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
	                    <th scope="col" class="text-center">아이디</th>
	                    <td>${member.memberId}</td>
	                  </tr>
		       			<tr>
							<th scope="col" class="text-center">이름</th>
							<td>${member.memberName}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">이메일</th>
							<td>${member.memberEmail}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">가입일자</th>
							<td>${member.createdate}</td>
						</tr>
						<tr>
							<th scope="col" class="text-center">정보수정일자</th>
							<td>${member.updatedate}</td>
						</tr>
	             	 </table>
					<a href="${pageContext.request.contextPath}/on/modifyMember" class="btn btn-outline-secondary">회원정보수정</a>
					<a href="${pageContext.request.contextPath}/on/removeMember" class="btn btn-outline-secondary">회원탈퇴</a>
	              <br>
	              <!-- End Table with stripped rows -->
			  
		
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