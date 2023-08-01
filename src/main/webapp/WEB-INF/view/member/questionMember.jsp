<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>questionMember</title>
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
				
				$("#addQuestionBtn").click(function() {
					if ($("#questionTitle").val() == "") {
						alert("제목을 입력해주세요.");
						$("#questionTitle").focus();
					} else if ($("#questionContent").val() == "") {
						alert("내용을 입력해주세요.");
						$("#questionContent").focus();
					} else {
						$("#addQuestion").submit();
					}
				});
		  	});
  	  </script>
  	  
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
		      <h1>Frequently Asked Questions</h1>
		      <nav>
		        <ol class="breadcrumb">
		          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/on/calendar">Home</a></li>
		          <li class="breadcrumb-item">Pages</li>
		          <li class="breadcrumb-item active">FAQ</li>
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
	                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">FAQ</button>
	                </li>
	                <li class="nav-item">
	                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit">1:1 문의</button>
	                </li>
	                <li class="nav-item">
	                  <button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-table">문의 내역</button>
	                </li>
	              </ul>
	              
	              <div class="tab-content pt-2">
              		 <div class="tab-pane fade show active profile-overview" id="profile-overview">
                	 <!-- F.A.Q Group 1 -->
			          <div class="card">
				            <div class="card-body">
				              <h5 class="card-title">자주 묻는 질문</h5>
				
				              <div class="accordion accordion-flush" id="faq-group-1">
				
				                <div class="accordion-item">
				                  <h2 class="accordion-header">
				                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-1" type="button" data-bs-toggle="collapse">
		                      이 사이트의 주요 기능은 무엇인가요?
		                    </button>
		                  </h2>
		                  <div id="faqsOne-1" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
		                    <div class="accordion-body">
		                      Cashbook은 수입, 지출 내역을 정리할 수 있는 가계부 웹사이트로, 회원가입 및 로그인 후 이용 가능합니다. <br>
		                      홈 화면에서 이번 달의 달력과 이달의 해시태그 목록, 이번 달의 수입, 지출 총 금액을 확인할 수 있습니다. 입력을 원하는 날짜를 클릭하면 해당 날짜별 가계부 목록과 수입/지출 금액을 확인할 수 있으며, 새로운 내역을 입력할 수 있습니다. <br>
		                      1: 1 문의 탭에서 관리자에게 문의를 남길 수 있고, 올해의 통계 탭에서 이번 년도의 총 수입/지출 및 월별 금액을 차트 형식으로 확인할 수 있습니다.
		                    </div>
		                  </div>
		                </div>
		
		                <div class="accordion-item">
		                  <h2 class="accordion-header">
		                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-2" type="button" data-bs-toggle="collapse">
		                      해시태그는 어떻게 입력하고, 어떻게 표시되나요?
		                    </button>
		                  </h2>
		                  <div id="faqsOne-2" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
		                    <div class="accordion-body">
		                      가계부 내역을 입력할 때 메모 부분에 '#'을 입력한 후 내용을 이어서 입력하면 됩니다. 해시태그 입력 시 띄어쓰기는 하지 말아주세요. <br>   
		                      해시태그는 해당 월의 화면에서 #을 제외한 내용과 입력된 개수가 함께 표시됩니다. <br>
		                      ex) #간식 을 처음 입력했을 경우, 이달의 해시태그 목록에 간식(1)과 같이 표시됩니다. 
		                    </div>
		                  </div>
		                </div>
		
		                <div class="accordion-item">
		                  <h2 class="accordion-header">
		                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-3" type="button" data-bs-toggle="collapse">
		                      관리자 모드로 접속 가능한가요?
		                    </button>
		                  </h2>
		                  <div id="faqsOne-3" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
		                    <div class="accordion-body">
		                      아이디: admin, 비밀번호: 1234 를 입력하시면 관리자 페이지를 확인할 수 있습니다.
		                    </div>
		                  </div>
		                </div>
		
		                <div class="accordion-item">
		                  <h2 class="accordion-header">
		                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-4" type="button" data-bs-toggle="collapse">
		                      로그인 상태에서는 접속자 통계를 볼 수 없나요?
		                    </button>
		                  </h2>
		                  <div id="faqsOne-4" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
		                    <div class="accordion-body">
		                      네, 접속자 통계는 비로그인 상태 또는 관리자 로그인 상태에서만 확인 가능합니다.
		                    </div>
		                  </div>
		                </div>
				
				                <!-- <div class="accordion-item">
				                  <h2 class="accordion-header">
				                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-5" type="button" data-bs-toggle="collapse">
				                      Natus sunt quo atque mollitia accusamus?
				                    </button>
				                  </h2>
				                  <div id="faqsOne-5" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
				                    <div class="accordion-body">
				                      Aut necessitatibus maxime quis dolor et. Nihil laboriosam molestiae qui molestias placeat corrupti non quo accusamus. Nemo qui quis harum enim sed. Aliquam molestias pariatur delectus voluptas quidem qui rerum id quisquam. Perspiciatis voluptatem voluptatem eos. Vel aut minus labore at rerum eos.
				                    </div>
				                  </div>
				                </div> -->
				              </div>
				            </div>
				          </div><!-- End F.A.Q Group 1 -->
	               		</div>
	
		                <div class="tab-pane fade profile-edit pt-3" id="profile-edit">
		                    <!-- Profile Edit Form -->
		                    <div class="card">
			              	  <div class="card-body">
			              		  <h5 class="card-title">Question</h5>
			              		  <p></p>
								    <form method="post" action="${pageContext.request.contextPath}/on/addQuestion" id="addQuestion">
						                <!-- Table with stripped rows -->
						                <table class="table table-bordered">
						       			  <tr>
											<th scope="col" class="text-center">제목</th>
											<td><input type="text" name="questionTitle" class="form-control" id="questionTitle"></td>
										  </tr>
										  <tr>
											<th scope="col" class="text-center">내용</th>
											<td><textarea name="questionContent" class="form-control" id="questionContent" cols="60" rows="5"></textarea></td>
										  </tr>
						               </table>
						                <div class="text-center">
						                	<button type="button" class="btn btn-primary" id="addQuestionBtn">입력</button>
					                	</div>
					                </form>	 
		         			   </div>		 
	            		    </div>
	             	    </div>
             	  
	               	    <div class="tab-pane fade profile-edit pt-3" id="profile-table">
	                    <!-- Profile Edit Form -->
	                    <div class="card">
	                 	   <div class="card-body">
		              		   <h5 class="card-title">My Questions</h5>
		              		   <p></p>
							
						  <div style="overflow:auto; height:450px;"> <!-- 스크롤 설정  -->
						  	   <!-- Table with stripped rows -->
				                <table class="table datatable">
				                  <thead>
				                    <tr>
				                      <th scope="col">번호</th>
				                      <th scope="col">제목</th>
				                      <th scope="col">상태</th>
				                      <th scope="col">작성일자</th>
				                    </tr>
				                  </thead>
				                  <tbody>
									  <c:forEach var="q" items="${list}">
									  <tr>
										  <td>${q.qNo}</td>
										  <td><a href="${pageContext.request.contextPath}/on/myQuestionOne?qNo=${q.qNo}">${q.qTitle}</a></td>
										  <td>
											  <c:if test="${q.qStatus eq '답변완료'}">
												  <span style="color: blue;">${q.qStatus}</span>
											  </c:if>
											  <c:if test="${q.qStatus ne '답변완료'}"> <!-- 답변완료가 아닐 경우  -->
		      									    ${q.qStatus}
		    								  </c:if>
										  </td>
										  <td>${q.createdate}</td>
									   </tr>
								  </c:forEach>
				                  </tbody>
				                </table>
			                </div>
			                <br>
			                <!-- End Table with stripped rows -->
		              
		         			 </div>		 
	            		 </div>
	             	   </div>
            		 </div><!-- End Bordered Tabs -->
         		</div>  <!-- <div class="card"> -->
      	    </div> <!-- <div class="card-body pt-3">  -->
      		</div> <!-- <div class="col-lg-12"> -->
	  	</section>
	  	<a href="${pageContext.request.contextPath}/on/calendar" class="btn btn-outline-primary">이전</a>
	  </main>

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