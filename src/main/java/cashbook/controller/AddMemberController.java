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

@WebServlet("/off/addMember")
public class AddMemberController extends HttpServlet {
	// addMember.jsp (회원가입 폼)으로 이동
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");

	
		// jsp 페이지로 포워드(디스패치)
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	// 회원가입 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");

		// request.getParameter()
		Member member = new Member();
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		String memberName = request.getParameter("memberName");
		String memberEmail = request.getParameter("memberEmail");
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		member.setMemberName(memberName);
		member.setMemberEmail(memberEmail);
		
		// 회원가입 DAO 호출
		MemberDao memberDao = new MemberDao(); 
		String msg = "";
		int check = memberDao.checkId(memberId);
		System.out.println(check + " <-- check(AddMemberController)");
		if (check == 1) {
			System.out.println("중복된 ID");
			msg = URLEncoder.encode("사용 중인 아이디입니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/off/addMember?msg=" + msg);
			return;
		}
		
		int row = memberDao.insertMember(member);
		System.out.println(row + " <-- row(AddMemberController)");
		if (row == 0) { // 회원가입 실패 시 
			// addMember.jsp view 이동
			response.sendRedirect(request.getContextPath() + "/off/addMember");
			return;
		} else if (row == 1) {
			System.out.println("회원가입 성공");
			// 회원가입 성공 시 login.jsp로 이동 -> controller 리다이렉트
			msg = URLEncoder.encode("회원가입이 완료되었습니다. 로그인 후 이용 가능합니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/off/login?msg=" + msg);
			return;
		} else {
			System.out.println("add member error!");
		}
	}
}