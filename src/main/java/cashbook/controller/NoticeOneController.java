package cashbook.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.NoticeDao;
import cashbook.vo.Admin;
import cashbook.vo.Member;
import cashbook.vo.Notice;


@WebServlet("/onOff/noticeOne")
public class NoticeOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사, 인코딩 설정 -> 필터
		
		// 게시판 상세정보
		// 비로그인 상태 or member는 조회만 가능
		// 삭제, 수정 버튼은 employee 로그인 시에만 보임
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		Object loginInfo = session.getAttribute("loginInfo");
		
		Member member = null;
		String memberId = null;
		Admin admin = null;
		String adminId = null;
		String loginId = null;
		
		if (loginInfo == null) {
			System.out.println("비로그인 상태(NoticeOneGet)");
		} else if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(NoticeOneGet)");
		} else {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(NoticeOneGet)");
		}
		
		if (memberId == null) {
			loginId = adminId;
			System.out.println(loginId + " <-- loginId(NoticeOneGet)");
		} else {
			loginId = memberId;
			System.out.println(loginId + " <-- loginId(NoticeOneGet)");
		}
		
		
		// String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(noticeNo + " <-- noticeNo(NoticeOneGet)");
		
		NoticeDao noticeDao = new NoticeDao();
		Notice notice = noticeDao.selectNoticeOneByNoticeNo(noticeNo);
		
		request.setAttribute("loginId", loginId);
		request.setAttribute("notice", notice);
		
		request.getRequestDispatcher("/WEB-INF/view/noticeOne.jsp").forward(request, response);
		
	}
}