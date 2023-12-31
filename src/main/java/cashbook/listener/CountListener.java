package cashbook.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import cashbook.service.CounterService;

@WebListener
public class CountListener implements HttpSessionListener {
	private CounterService counterService;
	
    public void sessionCreated(HttpSessionEvent se)  { 
    	// 생성될 떄마다 현재 접속자 + 1 -> application.attribute
    	System.out.println(se.getSession().getId() + " 의 새로운 세션이 생성되었습니다.");
    	
    	
    	ServletContext application = se.getSession().getServletContext(); // 세션 이벤트만으로 카운터를 불러올 수 없음 -> 세션을 통해 애플리케이션 속성 접근
    	int currentCounter = (Integer) (application.getAttribute("currentCounter"));
    	application.setAttribute("currentCounter", currentCounter + 1);
    	
    	// 오늘 접속자 + 1 -> DB
    	this.counterService = new CounterService();
    	int counter = counterService.getCounter();
    	if (counter == 0) {
    		counterService.addCounter();
    	} else {
    		counterService.modifyCounter();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    	// 소멸될 때마다 현재 접속자 - 1
    	System.out.println(se.getSession().getId() + " 세션이 소멸되었습니다.");
    	
    	ServletContext application = se.getSession().getServletContext(); 
    	int currentCounter = (Integer) (application.getAttribute("currentCounter"));
    	application.setAttribute("currentCounter", currentCounter - 1);
    }
}