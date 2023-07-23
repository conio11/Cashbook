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

@WebServlet("/on/removeNotice")
public class RemoveNoticeController extends HttpServlet {
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
			System.out.println(adminId + " <-- adminId(RemoveNoticeGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		NoticeDao noticeDao = new NoticeDao();
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(noticeNo + " <-- noticeNo(RemoveNoticeGet)");
		
		int row = noticeDao.removeNotice(noticeNo);
		System.out.println(row + " <-- row(RemoveCashbook)");
		
		if (row == 1) {
			System.out.println("공지 삭제 성공");
			msg = URLEncoder.encode("삭제되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);
		} else if (row == 0) {
			System.out.println("공지 삭제 실패");
			msg = URLEncoder.encode("삭제에 실패했습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);	
		} else {
			System.out.println("remove notice error!");
		}
	}
}