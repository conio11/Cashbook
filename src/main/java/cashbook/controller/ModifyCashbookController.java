package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.CashbookDao;
import cashbook.model.HashtagDao;
import cashbook.vo.Cashbook;
import cashbook.vo.Hashtag;
import cashbook.vo.Member;

@WebServlet("/on/modifyCashbook")
public class ModifyCashbookController extends HttpServlet {
	// 가계부 수정 jsp 파일로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		// session 유효성 검사
		HttpSession session = request.getSession();
		String msg = "";
		if (session.getAttribute("loginMember") == null) {
			msg = URLEncoder.encode("로그인 후 이용 가능합니다.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/login?msg=" + msg);
			return;
		}
		Member loginMember = (Member) session.getAttribute("loginMember");
		String memberId = loginMember.getMemberId();
		System.out.println(memberId + " <-- memberId(modifyCashbookGet)")
		*/
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		
		String msg = "";
		Object loginInfo = session.getAttribute("loginInfo");
		Member member = null;
		String memberId = null;
		if (loginInfo instanceof Member) {
			member = (Member) loginInfo;
			memberId = member.getMemberId();
			System.out.println(memberId + " <-- memberId(modifyCashbookGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		System.out.println(targetYear + " <-- targetYear(modifyCashbookGet)");
		
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		System.out.println(targetMonth + " <-- targetMonth(modifyCashbookGet)");
		
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		System.out.println(targetDate + " <-- targetDate(modifyCashbookGet)");
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		System.out.println(cashbookNo + " <-- cashbookNo(modifyCashbookGet)");

		Cashbook cashbook = new Cashbook();
		cashbook = cashbookDao.selectCashbookOneByCashbookNo(cashbookNo);
		
		request.setAttribute("loginId", memberId);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		request.setAttribute("cashbook", cashbook);
		
		request.getRequestDispatcher("/WEB-INF/view/member/modifyCashbook.jsp").forward(request, response);
	
	}

	// 가계부 수정 액션
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
			System.out.println(memberId + " <-- memberId(modifyCashbookPost)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
	
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetMonth + " <-- targetMonth(modifyCashbookPost)");
		System.out.println(targetYear + " <-- targetYear(modifyCashbookPost)");
		System.out.println(targetDate + " <-- targetDate(modifyCashbookPost)");
		
		int cashbookNo = Integer.parseInt(request.getParameter("cashbookNo"));
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		System.out.println(cashbookNo + " <-- cashbookNo(modifyCashbookPost)");
		System.out.println(category + " <-- category(modifyCashbookPost)");
		System.out.println(price + " <-- price(modifyCashbookPost)");
		System.out.println(memo + " <-- memo(modifyCashbookPost)");
		
		Cashbook cashbook = new Cashbook();
		cashbook.setCashbookNo(cashbookNo);
		cashbook.setCategory(category);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		CashbookDao cashbookDao = new CashbookDao();
		
		int row = cashbookDao.modifyCashbook(cashbook);
		System.out.println(row + " <-- row(modifyCashbookPost)");
		
		msg = "";
		if (row == 1) { 
			System.out.println("가계부 수정 성공");
			// 기존 해시태그 삭제
			HashtagDao hashtagDao = new HashtagDao();
			hashtagDao.removeHashtag(cashbookNo);
			
			// 삭제 성공 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
			// 해시태그 추출 알고리즘
			String memo1 = cashbook.getMemo();
			System.out.println(memo1 + " <-- memo1(ModifyCashbookPost)");
			
			Set<String> set = new HashSet<String>(); // 중복된 해시태그 방지를 위해 set 자료구조 사용
			
			msg = URLEncoder.encode("수정이 완료되었습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbookListOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate + "&msg=" + msg);
			
			String memo2 = memo1.replace("#", " #"); // "#구디#아카데미" -> " #구디 #아카데미"
			System.out.println(memo2 + " <-- memo2(ModifyCashbookPost)");
			
			// 해시태그가 여러 개이면 반복해서 입력
			for (String ht : memo2.split(" ")) { // split 된 배열을 Set으로 변경 -> 중복 내용 제거 가능 (#구디 #구디)
				if (ht.contains("#")) {
					String ht2 = ht.replace("#", ""); // # 삭제
					if (ht2.length() > 0) {
						set.add(ht2); // set: 중복값 추가되지 않음
						System.out.println("수정된 해시태그 입력 성공(ModifyCashbookPost)");
					}
				}
			}
			
			for (String s : set) {
				Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(s);
				hashtagDao.insertHashtag(hashtag);
			  }	
			
		} else if (row == 0) {
			System.out.println("가계부 수정 실패");
			msg = URLEncoder.encode("수정에 실패했습니다. 다시 시도해 주세요.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbookListOne?targetYear=" + targetYear + "&targetMonth=" + targetMonth + "&targetDate=" + targetDate + "&msg=" + msg);
		} else {
			System.out.println("modify cashbook error!");
		}
	}
}