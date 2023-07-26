package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.CashbookDao;
import cashbook.vo.Cashbook;
import cashbook.vo.Member;

@WebServlet("/on/cashbookListOne")
public class CashbookListOneController extends HttpServlet {
	@Override
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
			System.out.println(memberId + " <-- memberId(CashbookListOneGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}

		// targetYear, targetMonth, targetDate 중 하나라도 넘어오지 않았을 경우 calendar 컨트롤러로 리다이렉트
		if (request.getParameter("targetYear") == null
		|| request.getParameter("targetMonth") == null
		|| request.getParameter("targetDate") == null) {
			response.sendRedirect(request.getContextPath() + "/on/calendar");
			return; 
		}
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetYear + " <-- targetYear(CashbookListOneGet)");
		System.out.println(targetMonth + " <-- targetMonth(CashbookListOneGet)");
		System.out.println(targetDate + " <-- targetDate(CashbookListOneGet)");
		
		// 모델값 구하기
		CashbookDao cashbookDao = new CashbookDao();
		List<Cashbook> list = cashbookDao.selectCashbookListByCashbookDate(memberId, targetYear, targetMonth + 1, targetDate);
		int income = cashbookDao.selectDailyIncome(memberId, targetYear, targetMonth + 1, targetDate);
		int expenses = cashbookDao.selectDailyExpenses(memberId, targetYear, targetMonth + 1, targetDate);
		
		System.out.println(income + " <-- income(CashbookListOneGet)");
		System.out.println(expenses + " <-- expenses(CashbookListOneGet)");
		
		// 날짜별 수입, 지출 내역을 출력하는 view
		// view에 넘길 값들을 request 속성에 저장
		request.setAttribute("loginId", memberId);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("list", list);
		request.setAttribute("income", income);
		request.setAttribute("expenses", expenses);
		
		// cashbookListOne.jsp 포워딩
		request.getRequestDispatcher("/WEB-INF/view/member/cashbookListOne.jsp").forward(request, response);
	}
}