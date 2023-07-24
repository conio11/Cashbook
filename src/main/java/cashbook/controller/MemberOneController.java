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

@WebServlet("/on/memberOne")
public class MemberOneController extends HttpServlet {
	@Override
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
			System.out.println(memberId + " <-- memberId(CashbookListOneGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		// String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		// 모델값 구하기(dao 내 메소드 호출)
		MemberDao memberDao = new MemberDao();
		member = memberDao.selectMemberOne(memberId);
		// member 출력하는 (포워딩 대상) memberOne.jsp에도 공유되어야 함
		// request가 공유됨 -> request 안에 넣어 공유
		request.setAttribute("loginId", memberId);
		request.setAttribute("member", member); // request.getAttribute(member)로 가져오기
		
		// memberOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/member/memberOne.jsp").forward(request, response);
	}
}