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
import cashbook.model.QuestionDao;
import cashbook.vo.Admin;
import cashbook.vo.Answer;
import cashbook.vo.Question;

@WebServlet("/on/questionOne")
public class QuestionOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		Object loginInfo = session.getAttribute("loginInfo");
		
		String msg = "";
		Admin admin = null;
		String adminId = null;
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(QuestionOneGet)");
		} else { // 회원인 경우 회원 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String memberId = request.getParameter("memberId"); 
		
		System.out.println(qNo + " <-- qNo(QuestionOneGet)");
		System.out.println(memberId + " <-- memberId(QuestionOneGet)");
		
		QuestionDao questionDao = new QuestionDao();
		Question question = questionDao.selectQuestionOne(qNo);
		
		AnswerDao answerDao = new AnswerDao();
		Answer answer = answerDao.selectAnswerOne(memberId, qNo); 
		
		request.setAttribute("loginId", adminId);
		request.setAttribute("question", question);
		request.setAttribute("answer", answer);
		
		request.getRequestDispatcher("/WEB-INF/view/questionOne.jsp").forward(request, response);
	}
}
