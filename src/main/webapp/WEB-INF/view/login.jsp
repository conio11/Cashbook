<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	  <meta charset="utf-8">
	  <meta content="width=device-width, initial-scale=1.0" name="viewport">
	  <title>login</title>
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	  <script>
			$(document).ready(function() {
				const urlParams = new URL(location.href).searchParams;
				const msg = urlParams.get("msg");
				if (msg != null) {
					alert(msg);
				}
				
	/* 			$("#loginBtn").click(function() {
					if ($("#memberId").val() == "") {
						alert("아이디를 입력해주세요.");
						$("#memberId").focus();
					} else if ($("#memberPw").val() == "") {
						alert("비밀번호를 입력해주세요.");
						$("#memberPw").focus();
					} else {
						$("#login").submit();
					}
				}); */
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
	
	  <main>
	    <div class="container">
	
	      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
	        <div class="container">
	          <div class="row justify-content-center">
	            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">
	
	              <div class="d-flex justify-content-center py-4">
	                <a href="index.html" class="logo d-flex align-items-center w-auto">
	                  <img src="assets/img/logo.png" alt="">
	                  <span class="d-none d-lg-block">Cashbook</span>
	                </a>
	              </div><!-- End Logo -->
	
	              <div class="card mb-3">
	
	                <div class="card-body">
	
	                  <div class="pt-4 pb-2">
	                    <h5 class="card-title text-center pb-0 fs-4">로그인</h5>
	                    <p class="text-center small">ID, 패스워드를 입력하세요.</p>
	                  </div>
	
	                  <form method="post" action="${pageContext.request.contextPath}/off/login" id="login" class="row g-3 needs-validation" novalidate>
	
	                    <div class="col-12">
	                      <label for="memberId" class="form-label">ID</label>
	                      <div class="input-group has-validation">
	                       <!--  <span class="input-group-text" id="inputGroupPrepend">@</span> -->
	                        <input type="text" name="memberId" class="form-control" id="memberId" value="${memberId}" required>
	                        <div class="invalid-feedback">ID를 입력하세요.</div>
	                      </div>
	                    </div>
	
	                    <div class="col-12">
	                      <label for="memberPw" class="form-label">Password</label>
	                      <input type="password" name="memberPw" id="memberPw" class="form-control" required>
	                      <div class="invalid-feedback">비밀번호를 입력하세요.</div>
	                    </div>
	
	                    <div class="col-12">
	                      <div class="form-check">
	                        <input class="form-check-input" type="checkbox" name="idSave" value="y" id="idSave">
	                        <label class="form-check-label" for="idSave">ID 저장</label>
	                      </div>
	                    </div>
	                    <div class="col-12">
	                      <button class="btn btn-primary w-100" type="submit" id="loginBtn">로그인</button>
	                    </div>
	                    <div class="col-12">
	                      <p class="small mb-0">회원이 아니신가요?<a href="${pageContext.request.contextPath}/off/addMember">&nbsp;회원가입</a></p>
	                    </div>
	                  </form>
	
	                </div>
	              </div>
	
	              <div class="credits">
	                <!-- All the links in the footer should remain intact. -->
	                <!-- You can delete the links only if you purchased the pro version. -->
	                <!-- Licensing information: https://bootstrapmade.com/license/ -->
	                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/nice-admin-bootstrap-admin-html-template/ -->
	                Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
	              </div>
	
	            </div>
	          </div>
	        </div>
	
	      </section>
	
	    </div>
	  </main><!-- End #main -->
	
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