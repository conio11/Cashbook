package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.NoticeDao;
import cashbook.vo.Admin;
import cashbook.vo.Notice;


@WebServlet("/on/addNotice")
public class AddNoticeController extends HttpServlet {
	// 공지 입력 jsp 파일로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Admin admin = null;
		String adminId = null;
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(AddNoticeGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", adminId);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/addNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Admin admin = null;
		String adminId = null;
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(addNoticeGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		NoticeDao noticeDao = new NoticeDao();
		Notice notice = new Notice();
		
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		System.out.println(noticeTitle + " <-- noticeTitle(AddNoticePost)");
		System.out.println(noticeContent + " <-- noticeContent(AddNoticePost)");
		
		notice.setNoticeTitle(noticeTitle);
		notice.setNoticeContent(noticeContent);
		
		int noticeNo = noticeDao.insertNotice(notice);
		System.out.println(noticeNo + " <-- noticeNo(AddNoticePost)");
		
		if (noticeNo == 0) { // 입력 실패
			System.out.println("입력 실패");
			msg = URLEncoder.encode("입력에 실패했습니다. 다시 시도해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/addNotice?msg=" + msg);
		} else {
			System.out.println("입력 성공");
			msg = URLEncoder.encode("게시글이 등록되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);
		}
	}

}
