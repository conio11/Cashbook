package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.AnswerDao;
import cashbook.vo.Admin;
import cashbook.vo.Answer;
import cashbook.vo.Member;

@WebServlet("/on/answerOne")
public class AnswerOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문의 상세페이지에 답변이 있으면 출력 
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		
		Admin admin = null;
		String adminId = null;
		String loginId = null;
		
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(AnswerOneGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String memberId = request.getParameter("memberId"); 
		
		System.out.println(qNo + " <-- qNo(AnswerOneGet)");
		System.out.println(memberId + " <-- memberId(AnswerOneGet)");
		
		AnswerDao answerDao = new AnswerDao();
		Answer answer = answerDao.selectAnswerOne(memberId, qNo); 

		request.setAttribute("loginId", adminId);
		
		request.setAttribute("answer", answer);
		request.getRequestDispatcher("/WEB-INF/view/admin/questionOne.jsp").forward(request, response);
	}
}