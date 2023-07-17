package cashbook.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cashbook.vo.Admin;
import cashbook.vo.Member;


@WebFilter("/on/*") // 로그인 on 상태를 확인하는 모든 서블릿에 적용
public class LoginOnFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("/on/* 로그인 on 확인");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		
		Object loginInfo = session.getAttribute("loginInfo");
		
		String msg = "";
		if (loginInfo == null) {
			msg = URLEncoder.encode("로그인 후 이용 가능합니다.", "UTF-8");
			HttpServletResponse rep = (HttpServletResponse) response;
			rep.sendRedirect(req.getContextPath() + "/off/login?msg=" + msg);
			return;
		}
		
		req.setAttribute("loginInfo", loginInfo);
		
		
		
		/*
		
		Member loginMember = (Member) session.getAttribute("loginInfo");
		String loginMemberId = loginMember != null ? loginMember.getMemberId() : null;
		req.setAttribute("loginMemberId", loginMemberId);
		System.out.println(loginMemberId + " <-- loginMemberId(LoginOnFilter)");
		
	
		
		String msg = "";
		if (session.getAttribute("loginInfo") == null) {
			msg = URLEncoder.encode("로그인 후 이용 가능합니다.", "UTF-8");
			HttpServletResponse rep = (HttpServletResponse) response;
			rep.sendRedirect(req.getContextPath() + "/off/login?msg=" + msg);
			return;
			
			
		}
		*/

		// HttpServletRequest req = (HttpServletRequest) request;
		// HttpSession session = req.getSession();
		// Member loginMember = (Member) session.getAttribute("loginMember");
		// String loginMemberId = loginMember != null ? loginMember.getMemberId() : null;
		//System.out.println(loginMemberId + " <-- loginMemberId");
		
		
		chain.doFilter(request, response);
	}



}
