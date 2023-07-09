package cashbook.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cashbook.model.NoticeDao;
import cashbook.vo.Notice;


@WebServlet("/onOff/noticeOne")
public class NoticeOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 유효성 검사, 인코딩 설정 -> 필터
		
		// 게시판 상세정보
		// 비로그인 상태 or member는 조회만 가능
		// 삭제, 수정 버튼은 employee 로그인 시에만 보임
		
		
		String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(noticeNo + " <-- noticeNo(NoticeOneGet)");
		
		NoticeDao noticeDao = new NoticeDao();
		Notice notice = noticeDao.selectNoticeOneByNoticeNo(noticeNo);
		
		request.setAttribute("loginMemberId", loginMemberId);
		request.setAttribute("notice", notice);
		
		request.getRequestDispatcher("/WEB-INF/view/noticeOne.jsp").forward(request, response);
		
	}
}