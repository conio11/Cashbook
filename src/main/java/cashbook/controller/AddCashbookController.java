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

@WebServlet("/on/addCashbook")
public class AddCashbookController extends HttpServlet {
	// 입력폼
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
			System.out.println(memberId + " <-- memberId(AddCashbookGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
	
		// request 매개값 - 날짜
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetYear + " <-- targetYear(AddCashbookGet)");
		System.out.println(targetMonth + " <-- targetMonth(AddCashbookGet)");
		System.out.println(targetDate + " <-- targetDate(AddCashbookGet)");
		
		request.setAttribute("loginId", memberId);
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("targetDate", targetDate);
		
		// 나머지 데이터는 입력 폼에서 사용자가 입력
		request.getRequestDispatcher("/WEB-INF/view/member/addCashbook.jsp").forward(request, response);
	}

	// 입력 액션
	@Override
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
			System.out.println(memberId + " <-- memberId(AddCashbookGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		
		Cashbook cashbook = new Cashbook();
		CashbookDao cashbookDao = new CashbookDao();
		
		// request 매개값
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		String memo = request.getParameter("memo");
		
		System.out.println(category + " <-- category(AddCashbookPost)");
		System.out.println(price + " <-- price(AddCashbookPost)");
		System.out.println(memo + " <-- memo(AddCashbookPost)");
		
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth")) + 1;
		int targetDate = Integer.parseInt(request.getParameter("targetDate"));
		
		System.out.println(targetYear + " <-- targetYear(AddCashbookPost)");
		System.out.println(targetMonth + " <-- targetMonth(AddCashbookPost)");
		System.out.println(targetDate + " <-- targetDate(AddCashbookPost)");
		
		String strTargetYear = "" + targetYear;
		String strTargetMonth = "" + targetMonth; 
		String strTargetDate = "" + targetDate; 
		
		if (targetMonth < 10) {
			strTargetMonth = "0" + strTargetMonth;
		}
		
		if (targetDate < 10) {
			strTargetDate = "0" + strTargetDate;
		}
		
		System.out.println(strTargetYear + " <-- strTargetYear(AddCashbookPost)");
		System.out.println(strTargetMonth + " <-- strTargetMonth(AddCashbookPost)");
		System.out.println(strTargetDate + " <-- strTargetDate(AddCashbookPost)");
		
		String cashbookDate = strTargetYear + "-" + strTargetMonth + "-" + strTargetDate;
		System.out.println(cashbookDate + " <-- cashbookDate(AddCashbookPost)");
		
		cashbook.setMemberId(memberId);
		cashbook.setCategory(category);
		cashbook.setCashbookDate(cashbookDate);
		cashbook.setPrice(price);
		cashbook.setMemo(memo);
		
		int cashbookNo = cashbookDao.insertCashbook(cashbook); // 키값 반환 
		System.out.println(cashbookNo + " <-- cashbookNo(AddCashbookPost)");
		
		if (cashbookNo == 0) { // 0 반환 시 입력 실패
			System.out.println("입력 실패");
			msg = URLEncoder.encode("입력에 실패했습니다. 다시 시도해 주세요.", "UTF-8");
			response.sendRedirect(request.getContextPath() + "/on/addCashbook?msg=" + msg);
			return;
		} else {
			System.out.println("입력 성공");
			msg = URLEncoder.encode("입력이 완료되었습니다.", "UTF-8");
			// 입력 성공 -> 해시태그가 있다면 -> 해시태그 추출 -> 해시태그 입력(반복)
			// 해시태그 추출 알고리즘
			// # # 구디 #구디 #자바
			HashtagDao hashtagDao = new HashtagDao(); 
			
			String memo1 = cashbook.getMemo();
			System.out.println(memo1 + " <-- memo1(AddCashbookPost)");
			
			String memo2 = memo1.replace("#", " #"); // "#구디#아카데미" -> " #구디 #아카데미"
			System.out.println(memo2 + " <-- memo2(AddCashbookPost)");
			
			Set<String> set = new HashSet<String>(); // 중복된 해시태그 방지를 위해 set 자료구조 사용
			
			// 해시태그가 여러 개이면 반복해서 입력
			for (String ht : memo2.split(" ")) { // split 된 배열을 Set으로 변경 -> 중복 내용 제거 가능 (#구디 #구디)
				if (ht.contains("#")) {
					String ht2 = ht.replace("#", ""); // # 삭제
					if (ht2.length() > 0) {
						set.add(ht2); // set: 중복값 추가되지 않음
						System.out.println("해시태그 입력 성공(AddCashbookPost)");
					}
				}
			}
			
			for (String s : set) {
				Hashtag hashtag = new Hashtag();
				hashtag.setCashbookNo(cashbookNo);
				hashtag.setWord(s);
				hashtagDao.insertHashtag(hashtag);
			  }	
			
			// redirect -> cashbookController -> forward -> cashbook.jsp

			// redirect -> cashbookListOneListController
			response.sendRedirect(request.getContextPath() + "/on/cashbookListOne?targetYear=" + targetYear + "&targetMonth=" + (targetMonth - 1) + "&targetDate=" + targetDate + "&msg=" + msg);
		}
	}	
}