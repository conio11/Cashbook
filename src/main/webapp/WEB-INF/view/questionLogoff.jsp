<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
	<head>
	    <meta charset="UTF-8">
	 	<meta content="width=device-width, initial-scale=1.0" name="viewport">
		<title>questionLogoff</title>
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
  		<jsp:include page="/WEB-INF/view/inc/logoffHeader.jsp"></jsp:include>
    	<jsp:include page="/WEB-INF/view/inc/logoffSidebar.jsp"></jsp:include>
    	<main id="main" class="main">
	
	    <div class="pagetitle">
	      <h1>Frequently Asked Questions</h1>
	      <nav>
	        <ol class="breadcrumb">
	          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
	          <li class="breadcrumb-item">Pages</li>
	          <li class="breadcrumb-item active">Frequently Asked Questions</li>
	        </ol>
	      </nav>
	    </div><!-- End Page Title -->
	
	    <section class="section faq">
	      <div class="row">
	        <div class="col-lg-12">
	
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
	                      수입, 지출 내역을 정리할 수 있는 가계부 웹사이트입니다. 홈 화면에서 이번 달의 달력과 이달의 해시태그 목록, 이번 달의 수입, 지출 총 금액을 확인할 수 있습니다. 
	                      pre, next 버튼으로 이전 달, 다음 달의 정보도 확인할 수 있습니다. 입력을 원하는 날짜를 클릭하면 가계부를 작성할 수 있습니다.
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
	                      가계부 내역을 입력할 때 메모 부분에 '#'을 입력한 후 내용을 이어서 입력하면 됩니다. 예를 들어 '#간식'을
	                      처음 입력했을 경우(실제 입력 시 '는 포함되지 않습니다), 이달의 해시태그 목록에 '간식(1)'과 같이 표시됩니다. 해시태그 입력 시 띄어쓰기는 하지 말아주세요.   
	                    </div>
	                  </div>
	                </div>
	
	                <div class="accordion-item">
	                  <h2 class="accordion-header">
	                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-3" type="button" data-bs-toggle="collapse">
	                      Et occaecati praesentium aliquam modi incidunt?
	                    </button>
	                  </h2>
	                  <div id="faqsOne-3" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
	                    <div class="accordion-body">
	                      Voluptates magni amet enim perspiciatis atque excepturi itaque est. Sit beatae animi incidunt eum repellat sequi ea saepe inventore. Id et vel et et. Nesciunt itaque corrupti quia ducimus. Consequatur maiores voluptatum fuga quod ut non fuga.
	                    </div>
	                  </div>
	                </div>
	
	                <div class="accordion-item">
	                  <h2 class="accordion-header">
	                    <button class="accordion-button collapsed" data-bs-target="#faqsOne-4" type="button" data-bs-toggle="collapse">
	                      Quo unde eaque vero dolor quis ipsam?
	                    </button>
	                  </h2>
	                  <div id="faqsOne-4" class="accordion-collapse collapse" data-bs-parent="#faq-group-1">
	                    <div class="accordion-body">
	                      Numquam ut reiciendis aliquid. Quia veritatis quasi ipsam sed quo ut eligendi et non. Doloremque sed voluptatem at in voluptas aliquid dolorum.
	                    </div>
	                  </div>
	                </div>
	
	                <div class="accordion-item">
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
	                </div>
	
	              </div>
	
	            </div>
	          </div><!-- End F.A.Q Group 1 -->
	
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