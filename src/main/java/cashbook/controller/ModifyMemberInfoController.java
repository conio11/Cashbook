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


@WebServlet("/on/modifyMemberInfo")
public class ModifyMemberInfoController extends HttpServlet {      
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
			System.out.println(memberId + " <-- memberId(ModifyMemberInfoGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", memberId);
		
		// memberOne.jsp로 이동
		request.getRequestDispatcher("/WEB-INF/view/memberOne.jsp").forward(request, response);
	}

	// 회원정보수정 액션
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
		
		String newName = request.getParameter("newName");
		String newEmail = request.getParameter("newEmail");
		
		System.out.println(newName + " <-- newName(ModifyMemberInfoPost)");
		System.out.println(newEmail + " <-- newEmail(ModifyMemberInfoPost)");
		
		// 모델값 구하기
		MemberDao memberDao = new MemberDao();
		
		int row = memberDao.modifyMemberInfo(memberId, newName, newEmail);
		System.out.println(row + " <-- row(ModifyMemberInfoPost)");
		
		if (row == 1) {
			System.out.println("회원정보 수정 성공");
			msg = URLEncoder.encode("회원정보가 수정되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/memberOne?msg=" + msg);
		} else if (row == 0) {
			System.out.println("회원정보 수정 실패");
			msg = URLEncoder.encode("회원정보가 수정에 실패했습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/memberOne?msg=" + msg);
		} else {
			System.out.println("modify memberInfo error!");
		}
		
	}
}