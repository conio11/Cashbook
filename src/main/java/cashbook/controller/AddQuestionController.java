package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.QuestionDao;
import cashbook.vo.Member;
import cashbook.vo.Question;


@WebServlet("/on/addQuestion")
public class AddQuestionController extends HttpServlet {
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
			System.out.println(memberId + " <-- memberId(AddQuestionGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", memberId);
		
		request.getRequestDispatcher("/WEB-INF/view/member/questionMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(AddQuestionPost)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		QuestionDao questionDao = new QuestionDao();
		Question question = new Question();
		
		String questionTitle = request.getParameter("questionTitle");
		String questionContent = request.getParameter("questionContent");
		
		System.out.println(questionTitle + " <-- questionTitle(AddQuestionPost)");
		System.out.println(questionContent + " <-- questionContent(AddQuestionPost)");
		
		question.setMemberId(memberId);
		question.setqTitle(questionTitle);
		question.setqContent(questionContent);
	
		int qNo = questionDao.insertQuestion(question);
		System.out.println(qNo + " <-- qNo(AddQuestionPost)");
		
		if (qNo == 0) { // 입력 실패
			System.out.println("입력 실패");
			msg = URLEncoder.encode("입력에 실패했습니다. 다시 시도해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/questionMember?msg=" + msg);
		} else {
			System.out.println("입력 성공");
			msg = URLEncoder.encode("문의글이 등록되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/questionList?msg=" + msg);
		}
	}
}