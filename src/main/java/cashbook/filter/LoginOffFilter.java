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

import cashbook.vo.Member;

@WebFilter("/off/*") // 로그인 off 상태를 확인하는 모든 서블릿에 적용
public class LoginOffFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("/off/* 로그인 Off 확인");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		Object loginInfo = session.getAttribute("loginInfo");
		
		if (loginInfo != null) {
			HttpServletResponse rep = (HttpServletResponse) response;
			
			if (loginInfo instanceof Member) {
				rep.sendRedirect(req.getContextPath() + "/on/calendar");
				// return;
			} else {
				rep.sendRedirect(req.getContextPath() + "/on/cashbook");
			}
			
			// response.sendRediret(request.getContextPath() + "/login?msg=" + msg);
		}
		
		chain.doFilter(request, response);
	}
}
