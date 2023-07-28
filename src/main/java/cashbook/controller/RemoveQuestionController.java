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


@WebServlet("/on/removeQuestion")
public class RemoveQuestionController extends HttpServlet {
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
			System.out.println(memberId + " <-- memberId(RemoveQuestionGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		QuestionDao questionDao = new QuestionDao();
		
		int qNo = Integer.parseInt(request.getParameter("qNo"));
		System.out.println(qNo + " <-- qNo(RemoveQuestionGet)");
		
		int row = questionDao.removeMyQuestion(memberId, qNo);
		System.out.println(row + " <-- row(RemoveQuestionGet)");
		
		if (row == 1) {
			System.out.println("문의글 삭제 성공");
			msg = URLEncoder.encode("삭제되었습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/questionList?msg=" + msg);
		} else if (row == 0) {
			System.out.println("문의글 삭제 실패");
			msg = URLEncoder.encode("삭제에 실패했습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/onOff/questionList?msg=" + msg);	
		} else {
			System.out.println("remove question error!");
		}
	}
}