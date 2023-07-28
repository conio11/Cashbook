package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.MemberDao;
import cashbook.vo.Member;

@WebServlet("/on/removeMember")
public class RemoveMemberController extends HttpServlet {
	// 회원 탈퇴 폼으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(removeMemberGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", memberId);
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
	}

	// 탈퇴 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(removeMemberPost)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		// String memberId = (String) request.getAttribute("loginMemberId");
		
		String memberPw = request.getParameter("removeMemberPw");
		System.out.println(memberId + " <-- memberId(removeMemberPost)");
		System.out.println(memberPw + " <-- memberPw(removeMemberPost)");
		
		// 모델값 구하기
		MemberDao memberDao = new MemberDao();
		
		// String msg = "";
		int row = memberDao.deleteMember(memberId, memberPw);
		System.out.println(row + " <-- row(RemoveMemberController)");
		if (row == 1) { // 탈퇴 성공 -> 세션 삭제
			System.out.println("회원 탈퇴 성공");
			msg = URLEncoder.encode("회원 탈퇴가 완료되었습니다.", "UTF-8"); 
			// HttpSession session = request.getSession();
			session.invalidate();
			
			// 로그인 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/off/login?msg=" + msg);
			
		} else if (row == 0) { 
			System.out.println("회원 탈퇴 실패");
			msg = URLEncoder.encode("회원 탈퇴 실패. 정확한 비밀번호를 입력해주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/memberOne?msg=" + msg);
		} else {
			System.out.println("remove member error!");
		}		
	}
}