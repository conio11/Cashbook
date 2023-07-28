package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.CashbookDao;
import cashbook.vo.Member;

@WebServlet("/on/myStatistics")
public class MyStatisticsController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 나의 수입, 지출 통계 화면으로 이동
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(MyStatisticsGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		// view에 넘겨줄 달력(연도) 정보
		Calendar today = Calendar.getInstance(); // 오늘 날짜 정보
		int targetYear = today.get(Calendar.YEAR); // 넘어온 연도가 없을 경우 오늘 날짜의 연도
		
		if (request.getParameter("targetYear") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
		}
		System.out.println(targetYear + " <-- targetYear(MyStatisticsGet)");
		
		CashbookDao cashbookDao = new CashbookDao();
		
		// 월별 수입 내역
		List<Integer> monthlyIncomeList = new ArrayList<>();
		for (int month = 1; month <= 12; month +=1) {
			int monthlyIncome = cashbookDao.selectMonthlyIncome(memberId, targetYear, month);
			monthlyIncomeList.add(monthlyIncome);
		}
		System.out.println(monthlyIncomeList + " <-- monthlyIncomeList(MyStatisticsGet)");
		
		// 월별 지출 내역
		List<Integer> monthlyExpensesList = new ArrayList<>();
		for (int month = 1; month <= 12; month +=1) {
			int monthlyExpenses = cashbookDao.selectMonthlyExpenses(memberId, targetYear, month);
			monthlyExpensesList.add(monthlyExpenses);
		}
		System.out.println(monthlyExpensesList + " <-- monthlyExpensesList(MyStatisticsGet)");
		
		// 총 수입과 총 지출
		int totalIncome = 0;
		int totalExpenses = 0;

		for (int income : monthlyIncomeList) {
		    totalIncome += income;
		}

		for (int expense : monthlyExpensesList) {
		    totalExpenses += expense;
		}
		
		System.out.println(totalIncome + " <-- totalIncome(MyStatisticsGet)");
		System.out.println(totalExpenses + " <-- totalExpenses(MyStatisticsGet)");

		request.setAttribute("totalIncome", totalIncome);
		request.setAttribute("totalExpenses", totalExpenses);

		request.setAttribute("loginId", memberId);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("monthlyIncomeList", monthlyIncomeList);
		request.setAttribute("monthlyExpensesList", monthlyExpensesList);
		
		request.getRequestDispatcher("/WEB-INF/view/member/myStatistics.jsp").forward(request, response);		
	}
}