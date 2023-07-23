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

@WebServlet("/on/modifyQuestion")
public class ModifyQuestionController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문의글 수정 폼으로 이동
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(ModifyQuestionGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		QuestionDao questionDao = new QuestionDao();
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		System.out.println(qNo + " <-- qNo(ModifyQuestionGet)");
		
		Question question = new Question();
		question = questionDao.selectMyQuestionOne(memberId, qNo);
		
		request.setAttribute("loginId", memberId);
		request.setAttribute("question", question);
		
		request.getRequestDispatcher("/WEB-INF/view/member/modifyQuestion.jsp").forward(request, response);
		
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
			System.out.println(memberId + " <-- memberId(ModifyQuestionPost)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		QuestionDao questionDao = new QuestionDao();
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String qTitle = request.getParameter("qTitle");
		String qContent = request.getParameter("qContent");
		
		System.out.println(qNo + " <-- qNo(ModifyQuestionPost)");
		System.out.println(qTitle + " <-- qTitle(ModifyQuestionPost)");
		System.out.println(qContent + " <-- qContent(ModifyQuestionPost)");
		
		int row = questionDao.modifyMyQuestion(memberId, qTitle, qContent, qNo);
		System.out.println(row + " <-- row(ModifyQuestionPost)");
		
		if (row == 1) {
			System.out.println("문의글 수정 성공");
			msg = URLEncoder.encode("수정이 완료되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/myQuestionOne?qNo=" + qNo + "&msg=" + msg);
		} else {
			System.out.println("문의글 수정 실패");
			msg = URLEncoder.encode("수정에 실패했습니다. 다시 시도해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/myQuestionOne?qNo=" + qNo + "&msg=" + msg);
		}
	}
}
