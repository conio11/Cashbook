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
import cashbook.vo.Answer;
import cashbook.vo.Member;
import cashbook.vo.Question;

@WebServlet("/on/myQuestionOne")
public class MyQuestionOneController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		Object loginInfo = session.getAttribute("loginInfo");
		
		String msg = "";
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(MyQuestionOneGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		System.out.println(qNo + " <-- qNo(MyQuestionOneGet)");
		
		QuestionDao questionDao = new QuestionDao();
		Question question = questionDao.selectMyQuestionOne(memberId, qNo);
		
		AnswerDao answerDao = new AnswerDao();
		Answer answer = answerDao.selectAnswerOne(memberId, qNo); 
		
		request.setAttribute("loginId", memberId);
		request.setAttribute("question", question);
		request.setAttribute("answer", answer);
		
		request.getRequestDispatcher("/WEB-INF/view/member/myQuestionOne.jsp").forward(request, response);
	}
}
