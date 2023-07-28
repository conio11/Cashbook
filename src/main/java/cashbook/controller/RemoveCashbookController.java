package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.CashbookDao;
import cashbook.vo.Admin;
import cashbook.vo.Member;

@WebServlet("/on/removeCashbook")
public class RemoveCashbookController extends HttpServlet {
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
			System.out.println(memberId + " <-- memberId(RemoveCashbookGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetYear + " <-- targetYear(RemoveCashbookPost)");
		System.out.println(targetMonth + " <-- targetMonth(RemoveCashbookPost)");
		System.out.println(targetDate + " <-- targetDate(RemoveCashbookPost)");
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + " <-- cashbookNo(RemoveCashbookPost)");
		
		int row = cashbookDao.removeCashbook(cashbookNo);
		System.out.println(row + " <-- row(RemoveCashbookPost)");
		
		// String msg = "";
		if (row == 1) {
			System.out.println("가계부 삭제 성공");
			msg = URLEncoder.encode("삭제되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbookListOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate + "&msg=" + msg);
		} else if (row == 0) {
			System.out.println("가계부 삭제 실패");
			msg = URLEncoder.encode("삭제에 실패했습니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/cashbookListOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate + "&msg=" + msg);
		} else {
			System.out.println("remove cashbook error!");
		}
	}
}