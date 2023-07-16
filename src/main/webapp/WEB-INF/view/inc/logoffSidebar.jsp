<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
  <!-- ======= Sidebar ======= -->
  <aside id="sidebar" class="sidebar">

    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/off/home">
          <i class="bi bi-grid"></i>
          <span>Dashboard</span>
        </a>
      </li><!-- End Dashboard Nav -->
      

      <li class="nav-heading">Pages</li>



      <li class="nav-item">
        <a class="nav-link collapsed" href="">
          <i class="bi bi-question-circle"></i>
          <span>문의 게시판</span>
        </a>
      </li><!-- End F.A.Q Page Nav -->

      <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/onOff/noticeList">
          <i class="bi bi-card-list"></i>
          <span>공지사항</span>
        </a>
      </li><!-- End Register Page Nav -->

           <li class="nav-item">
        <a class="nav-link collapsed" href="${pageContext.request.contextPath}/off/login">
          <i class="bi bi-box-arrow-in-right"></i>
          <span>로그인</span>
        </a>
      </li><!-- End Login Page Nav -->

    </ul>

  </aside><!-- End Sidebar-->