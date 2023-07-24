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

import cashbook.model.MemberDao;
import cashbook.vo.Admin;
import cashbook.vo.Member;


@WebServlet("/on/memberList")
public class MemberListController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 DB 조회
		// 세션 유효성 검사, 인코딩 설정 -> 필터
		// admin 로그인 시에만 조회 가능
		
		// session 인증 검사 코드
		HttpSession session = request.getSession();
		Object loginInfo = session.getAttribute("loginInfo");
		
		String msg = "";
		Admin admin = null;
		String adminId = null;
		
		if (loginInfo instanceof Admin) {
			admin = (Admin) loginInfo;
			adminId = admin.getAdminId();
			System.out.println(adminId + " <-- adminId(MemberListGet)");
		} else { // 고객인 경우 calendar.jsp로 이동
			msg = URLEncoder.encode("접근할 수 없습니다.", "UTF-8"); 
			response.sendRedirect(request.getContextPath() + "/on/calendar?msg=" + msg);
			return;
		}
		
		MemberDao memberDao = new MemberDao();
		
		// 현재 페이지 번호
		// 페이지 값 넘어오지 않을 경우 기본 1페이지
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage + " <-- currentPage(MemberListGet)");
		
		// 페이지 당 출력 행 수 
		int rowPerPage = 5;
		
		// 시작 행 번호 -> 0, 5, 10, 15, ..
		// LIMIT (beginRow, rowPerPage)
		int beginRow = (currentPage - 1) * rowPerPage;
		System.out.println(beginRow + " <-- beginRow(MemberListGet)");
		
		// 전체 행 수 
		int totalRow = memberDao.selectMemberCnt();
		
		// 마지막 페이지
		int lastPage = totalRow / rowPerPage;
		if (totalRow % rowPerPage != 0) {
			lastPage += 1;
		}
		
		System.out.println(totalRow + " <-- totalRow(MemberListGet)");
		System.out.println(lastPage + " <-- lastPage(MemberListGet)");
		
		// [이전] [다음] 탭 사이 출력 행 수
		int pagePerPage = 5;
		
		// [이전] 12345 [다음]
		// [이전] 678910 [다음]
		// minPage: [이전] 12345 [다음]인 경우 1, maxPage: 5
		int minPage = ((currentPage - 1) / pagePerPage) * pagePerPage + 1;
		int maxPage = minPage + (pagePerPage - 1);
		if (maxPage > lastPage) {
			maxPage = lastPage;
		}
		
		System.out.println(minPage + " <-- minPage(MemberListGet)");
		System.out.println(maxPage + " <-- maxPage(MemberListGet)");
		
		List<Member> list = memberDao.selectMemberAll(beginRow, rowPerPage);
		
		request.setAttribute("loginId", adminId);
		request.setAttribute("list", list);
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		request.setAttribute("pagePerPage", pagePerPage);
		request.setAttribute("minPage", minPage);
		request.setAttribute("maxPage", maxPage);
		
		request.getRequestDispatcher("/WEB-INF/view/admin/memberList.jsp").forward(request, response);
		
	}
	
}
