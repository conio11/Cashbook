package cashbook.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.model.HashtagDao;
import cashbook.vo.Member;


@WebServlet("/on/hashtagOne")
public class HashtagOneController extends HttpServlet {
	// 캘린더에서 해시태그를 선택했을 때 해당 해시태그의 내용, 날짜, 가격, 카테고리 가져오기
	@Override
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
		System.out.println(memberId + " <-- memberId(HashtagOneGet)");
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
			System.out.println(memberId + " <-- memberId(HashtagOneGet)");
		} else { // 관리자인 경우 관리자 메인 페이지로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/cashbook?msg=" + msg);
			return;
		}
		
		
		String word = request.getParameter("word");
		System.out.println(word + " <-- word(HashtagOneGet)");
		
		HashtagDao hashtagDao = new HashtagDao();
		
		// 현재 페이지 번호
		// 페이지 값 넘어오지 않을 경우 기본 1페이지
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage + " <-- currentPage(HashtagOneGet)");
		
		// 페이지당 출력 행 수 
		int rowPerPage = 10;
		
		// 시작 행 번호 -> 0, 10, 20, ...
		// LIMIT(beginRow, rowPerPage)
		int beginRow = (currentPage - 1) * rowPerPage;
		System.out.println(beginRow + " <-- beginRow(HashtagOneGet)");
		
		// 전체 행 수
		int totalRow = hashtagDao.selectHashtagOneCnt(memberId, word);
		
		// 마지막 페이지
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage += 1;
		}
		System.out.println(totalRow + " <-- totalRow(HashtagOneGet)");
		System.out.println(lastPage + " <-- lastPage(HashtagOneGet)");
		
		// [이전] [다음] 탭 사이 출력 행 수 
		int pagePerPage = 5;
		
		// [이전] 12345 [다음]
		// [이전] 678910 [다음]

		// [이전] [다음] 탭 사이 최소, 최대값
		// minPage: [이전] 12345 [다음] 인 경우 1, maxPage: 5
		int minPage = ((currentPage - 1) / pagePerPage) * pagePerPage + 1;
		int maxPage = minPage + (pagePerPage - 1);
		
		if (maxPage > lastPage) {
			maxPage = lastPage;
		}
		
		System.out.println(minPage + " <-- minPage(HashtagOneGet)");
		System.out.println(maxPage + " <-- maxPage(HashtagOneGet)");
		
		List<Map<String, Object>> list = hashtagDao.selectHashtagOne(memberId, word, beginRow, rowPerPage);
		
		request.setAttribute("loginId", memberId);
		request.setAttribute("word", word);
		request.setAttribute("list", list);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		
		request.getRequestDispatcher("/WEB-INF/view/member/hashtagOne.jsp").forward(request, response);
	}
}
