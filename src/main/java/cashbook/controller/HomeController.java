package cashbook.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cashbook.service.CounterService;


@WebServlet("/off/home")
public class HomeController extends HttpServlet {
	private CounterService counterService = null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 접속자 수
		this.counterService = new CounterService();
		int counter = counterService.getCounter();
		int totalCounter = counterService.getCounterAll();	
		
		// view에 넘겨줄 달력 정보 (모델값)
		
		Calendar firstDay = Calendar.getInstance(); // 오늘 날짜
		Calendar preDay = Calendar.getInstance(); 
		
		// 출력하고자 하는 연도, 월, 일의 기본값은 이번 달 1일
		int targetYear = firstDay.get(Calendar.YEAR);
		int targetMonth = firstDay.get(Calendar.MONTH);
		firstDay.set(Calendar.DATE, 1); // 날짜를 1일로 설정 -> Calendar.DAY_OF_WEEK 사용하여 1일의 요일 구할 수 있음
		
		// 이전 달 값
		preDay.set(Calendar.YEAR, targetYear);
		preDay.set(Calendar.MONTH, targetMonth - 1);
		preDay.set(Calendar.DATE, 1); // 날짜를 1일로 설정
		
		// 출력하고자 하는 연도와 월 값이 모두 넘어왔을 경우 넘어온 값으로 설정 (날짜는 1일)
		if (request.getParameter("targetYear") != null
		&& request.getParameter("targetMonth") != null) {
			targetYear = Integer.parseInt(request.getParameter("targetYear"));
			targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
			
			// API 자동 계산 처리 (12, -1 입력 방지 -> targetMonth: 12 -> 1, 
			// Calendar.MONTH 값으로 12 입력 -> 월1, 연도 + 1
			// Calendar.MONTH 값으로 -1 입력 -> 월 12, 연도 - 1
			
			firstDay.set(Calendar.YEAR, targetYear);
			firstDay.set(Calendar.MONTH, targetMonth); // 자바의 월값은 0 ~ 11 -> view에서 출력 시 + 1
			
			targetYear = firstDay.get(Calendar.YEAR); // 바뀐 연도, 월값 다시 세팅 -> 설정하지 않을 경우 이전, 다음 클릭 시 월값만 바뀌는 오류
			targetMonth = firstDay.get(Calendar.MONTH);
			preDay.set(Calendar.YEAR, targetYear); // 이전 달의 연도, 월값도 다시 세팅 -> 설정하지 않을 경우 이전 월 마지막 날이 항상 30으로 표시되는 오류
			preDay.set(Calendar.MONTH, targetMonth - 1);
		}
		
		// 달력 출력 시 시작 공백 개수 (1일 전 빈칸)
		// 1일이 일요일: 공백 0, 월요일: 공백 1, ... 토요일: 공백 6
		int beginBlank = firstDay.get(Calendar.DAY_OF_WEEK) - 1 ; // 1일 날짜의 요일 (일1, 월2, ... 토7) - 1
		System.out.println(beginBlank + " <-- beginBlank(HomeGet)");		
		
		// 출력되는 월의 마지막 날짜
		int lastDate = firstDay.getActualMaximum(Calendar.DATE); // getActualMaximum 사용 주의
		int preLastDate = preDay.getActualMaximum(Calendar.DATE); 
		
		System.out.println(lastDate + " <-- lastDate(HomeGet)");
		System.out.println(preLastDate + " <-- preLastDate(HomeGet)");
		
		// 마지막 날짜 뒤의 공백 개수 -> 전체 출력 셀(totalCell)의 개수가 7로 나누어 떨어지지 않을 경우 발생
		int endBlank = 0;
		
		if ((beginBlank + lastDate) % 7 != 0) { // 7로 나누어 떨어지지 않을 경우 
			endBlank = 7 - ((beginBlank + lastDate) % 7);
		}
		
		int totalCell =  beginBlank + lastDate + endBlank;
		System.out.println(totalCell + " <-- totalCell(HomeGet)");
		System.out.println(endBlank + " <-- endBlank(HomeGet)");
		
		// 달력을 출력하는 view
		// view에 넘길 값들을 request 속성에 저장
		request.setAttribute("targetYear", targetYear);
		request.setAttribute("targetMonth", targetMonth);
		request.setAttribute("lastDate", lastDate);
		request.setAttribute("totalCell", totalCell);
		request.setAttribute("beginBlank", beginBlank);
		request.setAttribute("endBlank", endBlank);
		request.setAttribute("preLastDate", preLastDate);
		
		request.setAttribute("counter", counter);
		request.setAttribute("totalCounter", totalCounter);
		
		request.getRequestDispatcher("/WEB-INF/view/home.jsp").forward(request, response);
	}
}