package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.*;
import cashbook.vo.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// session 유효성 검사
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath() + "/calendar");
			return;
		}
		
		// 쿠키에 저장된 아이디가 있다면 request 속성에 저장
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("memberId") == true) {
					request.setAttribute("memberId", c.getValue());
				}
			}
		}
		
		// get 방식
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// Member member = new Member(memberId, memberPw, null, null);
		
		Member member = new Member();
		
		MemberDao memberDao = new MemberDao();
		Member loginMember = memberDao.selectMemberById(member);
		
		String msg = "";
		if (loginMember == null) { 
			System.out.println("로그인 실패(LoginPost)");
			msg = URLEncoder.encode("아이디 또는 비밀번호를 확인해주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/login?msg=" + msg); // jsp 파일 리다이렉트 불가 (WEB-INF 폴더 내에 위치하기 때문)
			return;
		}
		
		// 로그인 성공 시: session 사용
		HttpSession session = request.getSession();
		System.out.println("로그인 성공(LoginPost)");
		session.setAttribute("loginMember", loginMember);
		
		// memberId를 쿠키(만료기간 설정)에 저장 -> name: memberId
		if (request.getParameter("idSave") != null) {
			Cookie memberIdCookie = new Cookie("memberId", memberId); // 쿠키 하나가 Map // 매개변수 없는 디폴트 생성자 사용 불가 -> new Cookie(String, String) 형태 -> properties
			memberIdCookie.setMaxAge(60 * 60 * 24); // 초단위 // 60 * 60 * 24 -> 1일  
			response.addCookie(memberIdCookie);
		}

		msg = URLEncoder.encode(memberId + "님, 환영합니다.", "UTF-8"); 
		response.sendRedirect(request.getContextPath() + "/calendar?msg=" + msg);
	}
}