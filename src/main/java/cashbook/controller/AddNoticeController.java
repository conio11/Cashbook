package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cashbook.model.NoticeDao;
import cashbook.vo.Notice;


@WebServlet("/on/addNotice")
public class AddNoticeController extends HttpServlet {
	// 공지 입력 jsp 파일로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		String msg = "";
		if (!loginMemberId.startsWith("admin")) {
			// 관리자가 아니면 noticeList로 리다이렉트
			msg = URLEncoder.encode("권한이 없습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginMemberId", loginMemberId);
		
		request.getRequestDispatcher("/WEB-INF/view/addNotice.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		String msg = "";
		if (!loginMemberId.startsWith("admin")) {
			// 관리자가 아니면 noticeList로 리다이렉트
			msg = URLEncoder.encode("권한이 없습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);
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
