package cashbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.QuestionDao;
import cashbook.vo.Admin;
import cashbook.vo.Member;
import cashbook.vo.Question;

@WebServlet("/onOff/questionList")
public class QuestionListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문의 게시판 조회
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		Object loginInfo = session.getAttribute("loginInfo");
		
		Member member = null;
		String memberId = null;
		Admin admin = null;
		String adminId = null;
		String loginId = null;
		
		if (loginInfo == null) {
			System.out.println("비로그인 상태(NoticeListGet)");
			request.getRequestDispatcher("/WEB-INF/view/questionLogoff.jsp").forward(request, response);
			return;
		} else if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(QuestionListGet)");
			
			QuestionDao questionDao = new QuestionDao();
			
			List<Question> list = questionDao.selectMyQuestion(memberId);
			
			request.setAttribute("loginId", memberId);
			request.setAttribute("list", list);
			
			request.getRequestDispatcher("/WEB-INF/view/member/questionMember.jsp").forward(request, response);
			return;
		} else {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(QuestionListGet)");
			
			QuestionDao questionDao = new QuestionDao();
			
			// 현재 페이지 번호
			// 페이지 값 넘어오지 않을 경우 기본 1페이지
			int currentPage = 1;
			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			System.out.println(currentPage + " <-- currentPage(QuestionListGet)");
			
			// 페이지 당 출력 행 수 
			int rowPerPage = 5;
			
			// 시작 행 번호 -> 0, 5, 10, 15, ...
			// LIMIT(beginRow, rowPerPage)
			int beginRow = (currentPage - 1) * rowPerPage;
			System.out.println(beginRow + " <-- beginRow(QuestionListGet)");
			
			// 전체 행 수
			int totalRow = questionDao.selectQuestionCnt();
			
			// 마지막 페이지
			int lastPage = totalRow / rowPerPage;
			if (totalRow % rowPerPage != 0) {
				lastPage += 1;
			}
			
			System.out.println(totalRow + " <-- totalRow(QuestionListGet)");
			System.out.println(lastPage + " <-- lastPage(QuestionListGet)");
			
			// [이전] [다음] 탭 사이 출력 행 수 
			int pagePerPage = 5;
			
			// [이전] 12345 [다음]
			// [이전] 678910 [다음]
			// minPage: [이전] 12345 [다음] 인 경우 1, maxPage: 5
			int minPage = ((currentPage - 1) / pagePerPage) * pagePerPage + 1;
			int maxPage = minPage + (pagePerPage - 1);
			
			if (maxPage > lastPage) {
				maxPage = lastPage;
			}
			
			System.out.println(minPage + " <-- minPage(QuestionListGet)");
			System.out.println(maxPage + " <-- maxPage(QuestionListGet)");
			
			List<Question> list = questionDao.selectQuestionAll(beginRow, rowPerPage);
			
			request.setAttribute("loginId", adminId);
			request.setAttribute("list", list);
			
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("lastPage", lastPage);
			request.setAttribute("pagePerPage", pagePerPage);
			request.setAttribute("minPage", minPage);
			request.setAttribute("maxPage", maxPage);
			
			request.getRequestDispatcher("/WEB-INF/view/admin/questionAdmin.jsp").forward(request, response);	
		}
	}
}
