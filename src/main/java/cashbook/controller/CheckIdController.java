package cashbook.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cashbook.model.MemberDao;

import com.google.gson.Gson;



@WebServlet("/off/checkId")
public class CheckIdController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 결과 응답 - JSON 형식
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
	
		// 회원가입 폼에서 입력받은 아이디
		String memberId = request.getParameter("memberId");
		
		// 아이디 중복 확인 로직
		MemberDao memberDao = new MemberDao();
		String msg = "";
		int check = memberDao.checkId(memberId);
		System.out.println(check + " <-- check(CheckIdPost)");
		
		Gson gson = new Gson(); // Google 제공 JSON 라이브러리
		String jsonStr = "";
		if (check == 1) {
			jsonStr = gson.toJson("n"); // 사용 불가 // n이라는 문자열을 JSON 형식으로 변환
		} else {
			jsonStr = gson.toJson("y"); // 사용 가능
		}
		
		PrintWriter out = response.getWriter(); // 응답을 클라이언트로 보내기 위한 PrintWriter 객체 생성
		out.write(jsonStr); // JSON 형식의 응답을 PrintWriter를 통해 클라이언트로 보냄
		out.flush(); // 출력 버퍼 비우기
		out.close(); // 출력 스트림 닫기
	}
}