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

@WebServlet("/on/addAnswer")
public class AddAnswerController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이거 필요 없을거같은데 나중에 보고 삭제하기
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Admin admin = null;
		String adminId = null;
		
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(AddAnswerGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		request.setAttribute("loginId", adminId);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/QuestionOne.jsp").forward(request, response);
		
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
			System.out.println(adminId + " <-- adminId(AddAnswerGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		AnswerDao answerDao = new AnswerDao();
		Answer answer = new Answer();
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String memberId = request.getParameter("memberId");
		String aContent = request.getParameter("aContent");
		
		System.out.println(qNo + " <-- qNo(AddAnswerPost)");
		System.out.println(memberId + " <-- memberId(AddAnswerPost)");
		System.out.println(aContent + " <-- aContent(AddAnswerPost)");
		
		answer.setqNo(qNo);
		answer.setMemberId(memberId);
		answer.setaContent(aContent);
		
		int aNo = answerDao.insertAnswer(answer);
		System.out.println(aNo + " <-- aNo(AddAnswerPost)");
		
		if (aNo == 0) { // 입력 실패
			System.out.println("입력 실패");
			msg = URLEncoder.encode("입력에 실패했습니다. 다시 시도해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/questionOne?qNo=" + qNo + "&memberId=" + memberId + "&msg=" + msg);
		} else {
			System.out.println("입력 성공");
			msg = URLEncoder.encode("답변이 등록되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/questionOne?qNo=" + qNo + "&memberId=" + memberId + "&msg=" + msg);
		}
	}

}
