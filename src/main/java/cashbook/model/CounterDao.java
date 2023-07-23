package cashbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CounterDao {
	// 오늘 날짜 첫 번째 접속 -> insert
	public int insertCounter(Connection conn) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO counter VALUES(CURDATE(), 1)"; // 오늘 날짜에 1 설정
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			// 예외를 던져야 함 -> 생성
			// Exception myE = new Exception();
			// throw myE;
			throw new Exception(); // 강제로 예외 발생시킴
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 오늘 날짜 첫 번째 접속이 아니면 -> update
	public int updateCounter(Connection conn) throws Exception {
		int row = 0;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE counter SET counter_num = counter_num + 1 WHERE counter_date = CURDATE()"; // counter_num 1 추가
			stmt = conn.prepareStatement(sql);
			row = stmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			// 예외를 던져야 함 -> 생성
			// Exception myE = new Exception();
			// throw myE;
			throw new Exception(); // 강제로 예외 발생시킴
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	// 오늘 날짜의 카운트 출력
	public int selectCounterCurdate(Connection conn) throws Exception {
		int counter = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT counter_num counterNum FROM counter WHERE counter_date = CURDATE()"; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				counter = rs.getInt("counterNum");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// 예외를 던져야 함 -> 생성
			// Exception myE = new Exception();
			// throw myE;
			throw new Exception(); // 강제로 예외 발생시킴
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return counter;
	}
	
	// 누적 카운터
	public int selectCounterAll(Connection conn) throws Exception {
		int totalCount = 0;
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT SUM(counter_num) totalCount FROM counter"; 
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt("totalCount");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			// 예외를 던져야 함 -> 생성
			// Exception myE = new Exception();
			// throw myE;
			throw new Exception(); // 강제로 예외 발생시킴
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return totalCount;
	}
}

