<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				
				$("#newPw1").blur(function() { // 포커스가 제거되었을 때
					let newPw = $(this).val();
					let message = $(newPwMsg);
					if (newPw.length < 4) {
						message.text("4자 이상의 비밀번호를 입력해주세요.");
						$(this).focus();
					} else {
						message.text("");
					}
				});
				
				$("#modifyMemberBtn").click(function() {
					if ($("#memberPw").val() == "") {
						alert("현재 비밀번호를 입력해주세요.");
						$("#memberPw").focus();
					} else if ($("#newPw1").val() == "") {
						alert("새 비밀번호를 입력해주세요.");
						$("#newPw1").focus();
					} else if ($("#newPw2").val() == "") {
						alert("새 비밀번호를 다시 입력해주세요.");
						$("#newPw2").focus();
					} else if ($("#newPw2").val() != $("#newPw1").val()) {
						alert("새 비밀번호가 일치하지 않습니다.");
						$("#newPw2").val(""); // 값 비우기
						$("#newPw2").focus();
					} else {
						$("#modifyMember").submit();
					}
				});
				
				$("#modifyMemberInfoBtn").click(function() {
					if ($("#newName").val() == "") {
						alert("이름을 입력해주세요.");
						$("#newName").focus();
					} else if ($("#newEmail").val() == "") {
						alert("이메일을 입력해주세요.");
						$("#newEmail").focus();
					} else {
						$("#modifyMemberInfo").submit();
					}
				});
				
				$("#removeMemberBtn").click(function() {
					if ($("#removeMemberPw").val() == "") {
						alert("비밀번호를 입력해주세요.");	
						$("#removeMemberPw").focus();
					} else {
						$("#removeMember").submit();
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

		    
		  <section class="section profile">
	        <div class="col-lg-12">
	
	          <div class="card">
	            <div class="card-body pt-3">
	              <!-- Bordered Tabs -->
	              <ul class="nav nav-tabs nav-tabs-bordered">
	
	                <li class="nav-item">
	                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">나의 정보</button>
	                </li>
	
	                <li class="nav-item">
	                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">정보 수정</button>
	                </li>
	
	                <li class="nav-item">
	                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-change-password">비밀번호 변경</button>
	                </li>
	                
	                 <li class="nav-item">
	                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-remove">회원 탈퇴</button>
	                </li>
	
	              </ul>
	              <div class="tab-content pt-2">
	
	                <div class="tab-pane fade show active profile-overview" id="profile-overview">
	                
	                  <h5 class="card-title">Profile Details</h5>
	
	                  <div class="row">
	                    <div class="col-lg-3 col-md-4 label ">아이디</div>
	                    <div class="col-lg-9 col-md-8">${member.memberId}</div>
	                  </div>
	
	                  <div class="row">
	                    <div class="col-lg-3 col-md-4 label">이름</div>
	                    <div class="col-lg-9 col-md-8">${member.memberName}</div>
	                  </div>
	
	                  <div class="row">
	                    <div class="col-lg-3 col-md-4 label">이메일</div>
	                    <div class="col-lg-9 col-md-8">${member.memberEmail}</div>
	                  </div>
	
	                  <div class="row">
	                    <div class="col-lg-3 col-md-4 label">가입일자</div>
	                    <div class="col-lg-9 col-md-8">${member.createdate}</div>
	                  </div>
	
	                  <div class="row">
	                    <div class="col-lg-3 col-md-4 label">정보수정일자</div>
	                    <div class="col-lg-9 col-md-8">${member.updatedate}</div>
	                  </div>
	                </div>
	
	                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
	
	                  <!-- Profile Edit Form -->
	                  <form method="post" action="${pageContext.request.contextPath}/on/modifyMemberInfo" id="modifyMemberInfo">
	                    <div class="row mb-3">
	                      <label for="fullName" class="col-md-4 col-lg-3 col-form-label">아이디</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="text" name="memberId" class="form-control" id="memberId" value="${member.memberId}" disabled="disabled">
	                      </div>
	                    </div>
	                    <div class="row mb-3">
	                      <label for="Country" class="col-md-4 col-lg-3 col-form-label">이름</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="text" name="newName" class="form-control" id="newName" value="${member.memberName}">
	                      </div>
	                    </div>
	                    <div class="row mb-3">
	                      <label for="Address" class="col-md-4 col-lg-3 col-form-label">이메일</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="email" name="newEmail" class="form-control" id="newEmail" value="${member.memberEmail}">
	                      </div>
	                    </div>
	
	                    <div class="text-center">
	                      <button type="button" class="btn btn-primary" id="modifyMemberInfoBtn">정보 수정</button>
	                    </div>
	                  </form><!-- End Profile Edit Form -->
	
	                </div>
	
	
	                <div class="tab-pane fade pt-3" id="profile-change-password">
	                  <!-- Change Password Form -->
	                  <form method="post" action="${pageContext.request.contextPath}/on/modifyMember" id="modifyMember">
	                    <div class="row mb-3">
	                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="password" name="memberPw" id="memberPw" class="form-control">
	                      </div>
	                    </div>
	
	                    <div class="row mb-3">
	                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="password" name="newPw1" id="newPw1" class="form-control">
							<span id="newPwMsg" style="color: red;"></span>	
	                      </div>
	                    </div>
	
	                    <div class="row mb-3">
	                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 다시 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                      	<input type="password" name="newPw2" id="newPw2" class="form-control">                       
	                      </div>
	                    </div>
	
	                    <div class="text-center">
	                      <button type="button" id="modifyMemberBtn" class="btn btn-primary">비밀번호 변경</button>
	                    </div>
	                  </form><!-- End Change Password Form -->
	                </div>
	                
	                 <div class="tab-pane fade pt-3" id="profile-change-password">
	                  <!-- Change Password Form -->
	                  <form method="post" action="${pageContext.request.contextPath}/on/modifyMember" id="modifyMember">
	                    <div class="row mb-3">
	                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">현재 비밀번호 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="password" name="memberPw" id="memberPw" class="form-control">
	                      </div>
	                    </div>
	
	                    <div class="row mb-3">
	                      <label for="newPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="password" name="newPw1" id="newPw1" class="form-control">
							<span id="newPwMsg" style="color: red;"></span>	
	                      </div>
	                    </div>
	
	                    <div class="row mb-3">
	                      <label for="renewPassword" class="col-md-4 col-lg-3 col-form-label">새 비밀번호 다시 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                      	<input type="password" name="newPw2" id="newPw2" class="form-control">                       
	                      </div>
	                    </div>
	
	                    <div class="text-center">
	                      <button type="button" id="modifyMemberBtn" class="btn btn-primary">비밀번호 변경</button>
	                    </div>
	                  </form><!-- End Change Password Form -->
	                  </div>
	                  
   	                 <div class="tab-pane fade pt-3" id="profile-remove">
	                  <!-- Change Password Form -->
	                  <form method="post" action="${pageContext.request.contextPath}/on/removeMember" id="removeMember">
	                    <div class="row mb-3">
	                      <label for="currentPassword" class="col-md-4 col-lg-3 col-form-label">비밀번호 입력</label>
	                      <div class="col-md-8 col-lg-9">
	                        <input type="password" name="removeMemberPw" id="removeMemberPw" class="form-control">
	                      </div>
	                    </div>
	

	
	                    <div class="text-center">
	                      <button type="button" id="removeMemberBtn" class="btn btn-primary">회원 탈퇴</button>
	                    </div>
	                  </form><!-- End Change Password Form -->
	                  </div>
	                
	
	              </div><!-- End Bordered Tabs -->
	
	            
	            
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