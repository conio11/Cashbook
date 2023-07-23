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

@WebServlet("/on/removeAnswer")
public class RemoveAnswerController extends HttpServlet {
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
			System.out.println(adminId + " <-- adminId(RemoveAnswerGet)");
		} else { // 고객인 경우 고객 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		String memberId = request.getParameter("memberId");
		
		System.out.println(qNo + " <-- qNo(RemoveAnswerGet)");
		System.out.println(memberId + " <-- memberId(RemoveAnswerGet)");
		
		AnswerDao answerDao = new AnswerDao();
		QuestionDao questionDao = new QuestionDao();
		
		int row = answerDao.removeAnswer(memberId, qNo);
		System.out.println(row + " <-- row(RemoveAnswerGet)");
		
		if (row == 1) {
			questionDao.modifyqStatus2(qNo);
			System.out.println("답변 삭제 성공");
			msg = URLEncoder.encode("삭제되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/questionOne?qNo=" + qNo + "&memberId=" + memberId + "&msg=" + msg);
		} else {
			System.out.println("답변 삭제 실패");
			msg = URLEncoder.encode("삭제에 실패했습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/questionOne?qNo=" + qNo + "&memberId=" + memberId + "&msg=" + msg);
		}		
	}
}