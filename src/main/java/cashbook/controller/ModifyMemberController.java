package cashbook.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.MemberDao;
import cashbook.vo.Member;

import java.net.*;

@WebServlet("/on/modifyMember")
public class ModifyMemberController extends HttpServlet {
	// 상세정보 페이지로 이동
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
			System.out.println(memberId + " <-- memberId(ModifyMemberGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", memberId);
		
		// 로그인 상태이면 memberOne.jsp로 이동
		// member 값 넘겨야 하나 memberOneController에서 넘어간 값 공통으로 사용
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
	}
	
	// 비밀번호수정 액션
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
			System.out.println(memberId + " <-- memberId(CashbookListOneGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		
		// Member loginMember = (Member) session.getAttribute("loginMember");
		// String memberId = (String) request.getAttribute("loginMemberId");
		String memberPw = request.getParameter("memberPw");
		
		String newPw1 = request.getParameter("newPw1");
		String newPw2 = request.getParameter("newPw2");
		
		String newPw = null;
		// String msg = "";
		if (newPw1.equals(newPw2)) {
			newPw = newPw1;
		} else {
			System.out.println("새 비밀번호 일치하지 않음");
			msg = URLEncoder.encode("비밀번호 변경 실패. 현재 비밀번호를 확인해주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/modifyMember?msg=" + msg);
			return;
		}
		
		// 모델값 구하기
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.modifyMemberPw(memberId, memberPw, newPw);
		System.out.println(row + " <-- row(ModifyMemberPost)");
		
		if (row == 1) {
			System.out.println("비밀번호 변경 성공");
			// 페이지 이동
			msg = URLEncoder.encode("비밀번호가 변경되었습니다. 다시 로그인 후 이용 가능합니다.", "UTF-8"); 
			session.invalidate(); // 로그아웃
			response.sendRedirect(request.getContextPath() + "/off/login?msg=" + msg);
		} else if (row == 0) {
			System.out.println("비밀번호 변경 실패. 현재 비밀번호 확인");
			msg = URLEncoder.encode("비밀번호 변경 실패. 현재 비밀번호를 확인해주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/memberOne?msg=" + msg);
		} else {
			System.out.println("modify memberPw error!");
		}
	}
}