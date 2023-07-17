package cashbook.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cashbook.vo.Member;


@WebFilter("/onOff/*") // 로그인 여부 관계없이 접속 가능한 모든 서블릿에 적용
public class LoginOnOffFilter extends HttpFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("/onOff/* 접속자 ID 확인");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Object loginInfo = session.getAttribute("loginInfo");
		
		req.setAttribute("loginInfo", loginInfo);
		
		//if (loginInfo == null) {
		//	System.out.println("로그인 X(LoginOnOffFilter)");
		// }
		
		
		/*
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		String loginMemberId = loginMember != null ? loginMember.getMemberId() : null;
		req.setAttribute("loginMemberId", loginMemberId);
		System.out.println(loginMemberId + " <-- loginMemberId");
		*/

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}


}
