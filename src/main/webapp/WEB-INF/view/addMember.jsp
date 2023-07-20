<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!-- JSP 컴파일 시 자바 코드로 변환되는 c: ... (제어 문법) 커스텀 태그  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!-- JSTL substring() 호출 -->
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>addMember</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
  <script>
  	$(document).ready(function() {
		const urlParams = new URL(location.href).searchParams;
		const msg = urlParams.get("msg");
		if (msg != null) {
			alert(msg);
		}
		
		
		$("#addMember").submit(function(event) {  
			// 입력된 아이디 값 가져오기
	    	let memberId = $("#memberId").val();

	    	// "admin"으로 시작하는지 확인
	     	if (memberId.startsWith("admin")) {
	     		alert("사용할 수 없는 아이디입니다.");
		        event.preventDefault(); // 폼 제출을 막음
		        $("#memberId").val("");
		        $("#memberId").focus();
	     	}
	    });
	
	     	 
	    $("#checkId").click(function() {
	    	const memberId = $("#memberId").val(); // memberId 입력 필드의 값 저장
	     	if (memberId.startsWith("admin")) {
	     		alert("사용할 수 없는 아이디입니다.");
	    	  	$("#memberId").val("");
	 	        $("#memberId").focus(); 
	    	} else if (memberId.trim() === "") {
	    		alert("아이디를 입력해주세요.");
	    	} else {
	    		$.ajax({
	    			type: "POST", 
	    			url: "${pageContext.request.contextPath}/off/checkId",
	 	    		data: {memberId: memberId},
	 	    		success: function(response) {
	 	    			if (response == "y") {
	 	    				alert("사용 가능한 아이디입니다.");
	 	    			} else {
	 	    				alert("사용 중인 아이디입니다.");
	 	  	    	  		$("#memberId").val("");
	 	  	 	        	$("#memberId").focus(); 
	 	    			}
 	    		 	}
 	    		 });
    		}
   	 	});
    });
		
		
	/*
		$("#addMemberBtn").click(function() {
			// 입력된 아이디 값 가져오기
            var memberId = $("#memberId").val();
			
            // "admin"으로 시작하는지 확인
            if (memberId.startsWith("admin")) {
                alert("사용할 수 없는 아이디입니다.");
                
            } else {
                $("#addMember").submit();
            }
		});
		
		$("#addMemberBtn").click(function() {
			if ($("#memberId").val() == "") {
				alert("아이디를 입력해주세요.");
				$("#memberId").focus();
			} else if ($("#memberPw").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#memberPw").focus();
			} else if ($("#memberEmail").val() == "") {
				alert("비밀번호를 입력해주세요.");
				$("#memberEmail").focus();		
			} else {
				$("#addMember").submit();
			}
		}); */
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
                <a href="${pageContext.request.contextPath}/off/home" class="logo d-flex align-items-center w-auto">
                  <img src="assets/img/logo.png" alt="">
                  <span class="d-none d-lg-block">Cashbook</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">회원가입</h5>
                    <p class="text-center small">ID, 이메일, 패스워드를 입력해주세요.</p>
                  </div>

                  <form method="post" action="${pageContext.request.contextPath}/off/addMember" id="addMember" class="row g-3 needs-validation" novalidate>
                    <div class="col-12">
                      <label for="memberId" class="form-label">ID</label>
                      <button type="button" class="float-end btn btn-outline-primary btn-sm" id="checkId">중복 확인</button><br>
                      <input type="text" name="memberId" class="form-control" id="memberId" required>
                      <div class="invalid-feedback">ID를 입력하세요.</div>
                    </div>

                    <div class="col-12">
                      <label for="memberEmail" class="form-label">Email</label>
                      <input type="email" name="memberEmail" class="form-control" id="memberEmail" required>
                      <div class="invalid-feedback">이메일을 입력하세요.</div>
                    </div>
                    
                     <div class="col-12">
                      <label for="memberName" class="form-label">Username</label>
                      <input type="text" name="memberName" class="form-control" id="memberName" required>
                      <div class="invalid-feedback">이름을 입력하세요.</div>
                    </div>

                    <div class="col-12">
                      <label for="memberPw" class="form-label">Password</label>
                      <input type="password" name="memberPw" class="form-control" id="memberPw" required>
                      <div class="invalid-feedback">비밀번호를 입력하세요.</div>
                    </div>

                    <div class="col-12">
                      <div class="form-check">
                        <input class="form-check-input" name="terms" type="checkbox" value="" id="acceptTerms" required>
                        <label class="form-check-label" for="acceptTerms">전체 이용약관에 동의합니다. <a href="" data-bs-toggle="modal" data-bs-target="#disablebackdrop">상세 내용</a></label>
                        <div class="invalid-feedback">동의하지 않으면 회원가입이 불가합니다.</div>
                      </div>
                    </div>
                    
                   <!-- Disabled Backdrop Modal -->
	              <div class="modal fade" id="disablebackdrop" tabindex="-1">
	                <div class="modal-dialog">
	                  <div class="modal-content">
	                    <div class="modal-header">
	                      <h5 class="modal-title">이용약관</h5>
	                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	                    </div>
	                    <div class="modal-body">
	                    	- 본인은 만 14세 이상입니다. <br>
	                    	- 개인정보 수집 및 이용 동의 <br>
	                    	- 서비스 이용약관 동의 
	                    </div>
	                    <div class="modal-footer">
	                      <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
	                    </div>
	                  </div>
	                </div>
	              </div><!-- End Disabled Backdrop Modal-->
                    
                    <div class="col-12">
                     <button class="btn btn-primary w-100" type="submit">회원가입</button>
                    </div>
                    <div class="col-12">
                      <p class="small mb-0">회원이신가요? <a href="${pageContext.request.contextPath}/off/login">로그인</a></p>
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