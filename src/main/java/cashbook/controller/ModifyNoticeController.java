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


@WebServlet("/on/modifyNotice")
public class ModifyNoticeController extends HttpServlet {
	// 공지 수정 jsp 파일로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginMemberId = (String) request.getAttribute("loginMemberId");
		
		String msg = "";
		if (!loginMemberId.startsWith("admin")) {
			// 관리자가 아니면 noticeList로 리다이렉트
			msg = URLEncoder.encode("권한이 없습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/noticeList?msg=" + msg);
			return;
		} 
		
		NoticeDao noticeDao = new NoticeDao();
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		System.out.println(noticeNo + " <-- noticeNo(ModifyNoticeGet)");
		
		Notice notice = new Notice();
		notice = noticeDao.selectNoticeOneByNoticeNo(noticeNo);
		
		request.setAttribute("notice", notice);
		
		request.getRequestDispatcher("/WEB-INF/view/modifyNotice.jsp").forward(request, response);
		
	}

	// 공지 수정 액션
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
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeTitle = request.getParameter("noticeTitle");
		String noticeContent = request.getParameter("noticeContent");
		
		System.out.println(noticeNo + " <-- noticeNo(ModifyNoticePost)");
		System.out.println(noticeTitle + " <-- noticeTitle(ModifyNoticePost)");
		System.out.println(noticeContent + " <-- noticeContent(ModifyNoticePost)");
		
		int row = noticeDao.modifyNotice(noticeTitle, noticeContent, noticeNo);
		System.out.println(row + " <-- row(ModifyNoticePost)");
		
		if (row == 1) {
			System.out.println("공지 수정 성공");
			msg = URLEncoder.encode("수정이 완료되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/onOff/noticeOne?noticeNo=" + noticeNo + "&msg=" + msg);
		} else if (row == 0) {
			System.out.println("공지 수정 실패");
			msg = URLEncoder.encode("수정에 실패했습니다. 다시 시도해 주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/onOff/noticeOne?noticeNo=" + noticeNo + "&msg=" + msg);
		} else {
			System.out.println("modify notice error!");
		}
	}

}