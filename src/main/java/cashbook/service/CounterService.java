package cashbook.service;

import java.sql.*;

import cashbook.model.CounterDao;

public class CounterService {
	private CounterDao counterDao;
	
	public void addCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		
		try {
			// conn.getAutoCommit(false); // 현재 트랜잭션 코드X
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			counterDao.insertCounter(conn); // 예외 발생시킴
		} catch (Exception e1) {
			// conn.rollback()
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			}
			
			catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			// conn.commit();
		}
	}
	
	public void modifyCounter() {
		this.counterDao = new CounterDao();
		Connection conn = null;
		
		try {
			// conn.getAutoCommit(false); // 현재 트랜잭션 코드X
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			counterDao.updateCounter(conn); // 예외 발생시킴
		} catch (Exception e1) {
			// conn.rollback()
			e1.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		// conn.commit();
	}
	
	public int getCounter() {
		int counter = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		
		try {
			
			// conn.getAutoCommit(false); // 현재 트랜잭션 코드X
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			counter = counterDao.selectCounterCurdate(conn); // 예외 발생시킴
					
		} catch (Exception e) {
			// conn.rollback()
			e.printStackTrace();
		}  finally {
			try {
				conn.close();
			}
			
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		// conn.commit();
		
		return counter;
	}
	
	public int getCounterAll() {
		int totalCounter = 0;
		this.counterDao = new CounterDao();
		Connection conn = null;
		
		try {
			
			// conn.getAutoCommit(false); // 현재 트랜잭션 코드X
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			totalCounter = counterDao.selectCounterAll(conn); // 예외 발생시킴
					
		} catch (Exception e) {
			// conn.rollback()
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			}
			
			catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		// conn.commit();
		
		return totalCounter;
	}

}
