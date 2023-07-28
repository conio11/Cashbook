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

@WebServlet("/off/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		// 쿠키에 저장된 아이디가 있다면 request 속성에 저장
		Cookie[] cookies = request.getCookies();
		
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("loginId") == true) {
					request.setAttribute("loginId", c.getValue());
				}
			}
		}
		
		// get 방식
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginId = request.getParameter("loginId");
		String loginPw = request.getParameter("loginPw");
		System.out.println(loginId + " <-- loginId");
		System.out.println(loginPw + " <-- loginPw");
		
		MemberDao memberDao = new MemberDao();
		AdminDao adminDao = new AdminDao();
		
		// 고객 로그인인지 확인
		Member paramMember = new Member(loginId, loginPw, null, null, null, null);
		Member loginMember = memberDao.selectMemberById(paramMember);
		
		// 관리자 로그인인지 확인
		Admin paramAdmin = new Admin(loginId, loginPw, null);
		Admin loginAdmin = adminDao.selectAdminById(paramAdmin);
		
		// 로그인 실패
		String msg = "";
		if (loginMember == null && loginAdmin == null) { 
			System.out.println("로그인 실패(LoginPost)");
			msg = URLEncoder.encode("아이디 또는 비밀번호를 확인해주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/off/login?msg=" + msg); // jsp 파일 리다이렉트 불가 (WEB-INF 폴더 내에 위치하기 때문)
			return;
		}
		
		HttpSession session = request.getSession();
		if (loginMember != null) { // 회원으로 로그인 시 
			// 로그인 성공 시: session 사용
		
			System.out.println("로그인 성공(LoginPost)");
			session.setAttribute("loginInfo", loginMember);
			
			// memberId를 쿠키(만료기간 설정)에 저장 -> name: loginId
			if (request.getParameter("idSave") != null) {
				System.out.println(request.getParameter("idSave") + " <-- 아이디 저장(LoginPost)");
				Cookie memberIdCookie = new Cookie("loginId", loginId); // 쿠키 하나가 Map // 매개변수 없는 디폴트 생성자 사용 불가 -> new Cookie(String, String) 형태 -> properties
				memberIdCookie.setMaxAge(60 * 60 * 24); // 초단위 // 60 * 60 * 24 -> 1일  
				response.addCookie(memberIdCookie);
			}
			
			msg = URLEncoder.encode(loginId + "님, 환영합니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);

		} else { // 관리자로 로그인 시
			System.out.println("관리자 로그인 성공(LoginPost)");
			session.setAttribute("loginInfo", loginAdmin);
			
			// 관리자 화면으로 리다이렉트 (임시: cashbook)
			msg = URLEncoder.encode(loginId + "님, 관리자로 로그인되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);	
		}
	}
}