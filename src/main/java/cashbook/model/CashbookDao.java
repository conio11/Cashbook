package cashbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cashbook.vo.Cashbook;
import cashbook.vo.Member;

public class CashbookDao {
	/*
	// 연도별, 아이디별 1 ~ 12월 수입 내역
	public List<Integer> selectMonthlyIncome(String memberId, int targetYear) {
		List<Integer> monthlyIncome = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT MONTH(cashbook_date) MONTH, SUM(price) monthlyIncome FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND category = '수입' GROUP BY MONTH(cashbook_date) ORDER BY MONTH(cashbook_date)";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int totalIncome = rs.getInt("totalIncome");
				monthlyIncome.add(totalIncome);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return monthlyIncome;
	}
	*/
	
	// 달력에 사용할 연도, 월, 아이디별 지출 내역
	public List<Cashbook> selectCashBookListByMonth(String memberId, int targetYear, int targetMonth) {		
		List<Cashbook> list = new ArrayList<Cashbook>();

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, category, price, cashbook_date cashbookDate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? ORDER BY cashbook_date ASC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setCategory(rs.getString("category"));
				c.setPrice(rs.getInt("price"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				list.add(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	// 달력 상단에 사용할 연도, 월, 아이디별 지출 금액
	public int selectMonthlyExpenses(String memberId, int targetYear, int targetMonth) {
		int expenses = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COALESCE(SUM(price), 0) sum FROM cashbook WHERE category='지출' AND member_id= ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			if (rs.next()) {
				expenses = rs.getInt("sum");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return expenses;
	}
	
	// 달력 상단에 사용할 연도, 월, 아이디별 수입 금액
	public int selectMonthlyIncome(String memberId, int targetYear, int targetMonth) {
		int income = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COALESCE(SUM(price), 0) sum FROM cashbook WHERE category='수입' AND member_id= ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			if (rs.next()) {
				income = rs.getInt("sum");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			
		return income;
	}

	
	// 아이디별, 구매날짜별 지출 상세내역
	public List<Cashbook> selectCashbookListByCashbookDate(String memberId, int targetYear, int targetMonth, int targetDate) {
		List<Cashbook> list = new ArrayList<Cashbook>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, member_id memberId, category, cashbook_date cashbookDate, price, memo, createdate, updatedate FROM cashbook WHERE member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ? ORDER BY createdate DESC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Cashbook c = new Cashbook();
				c.setCashbookNo(rs.getInt("cashbookNo"));
				c.setMemberId(rs.getString("memberId"));
				c.setCategory(rs.getString("category"));
				c.setCashbookDate(rs.getString("cashbookDate"));
				c.setPrice(rs.getInt("price"));
				c.setMemo(rs.getString("memo"));
				c.setCreatedate(rs.getString("createdate"));
				c.setUpdatedate(rs.getString("updatedate"));
				list.add(c);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 아이디별, 구매날짜별 수입금액
	public int selectDailyIncome(String memberId, int targetYear, int targetMonth, int targetDate) {
		int income = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(price) sum FROM cashbook WHERE category = '수입' AND member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			rs = stmt.executeQuery();
			if (rs.next()) {
				income = rs.getInt("sum");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return income;
	}
	
	// 아이디별, 구매날짜별 지출금액
	public int selectDailyExpenses(String memberId, int targetYear, int targetMonth, int targetDate) {
		int income = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(price) sum FROM cashbook WHERE category = '지출' AND member_id = ? AND YEAR(cashbook_date) = ? AND MONTH(cashbook_date) = ? AND DAY(cashbook_date) = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			stmt.setInt(4, targetDate);
			rs = stmt.executeQuery();
			if (rs.next()) {
				income = rs.getInt("sum");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return income;
	}
	
	// 반환값: cashbook_no (키값)
	public int insertCashbook(Cashbook cashbook) {
		int cashbookNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력 후 생성된 키값을 반환받기 위해 필요
		String sql = "INSERT INTO cashbook(member_id, category, cashbook_date, price, memo, updatedate, createdate) VALUES(?, ?, ?, ?, ?, NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, cashbook.getMemberId());
			stmt.setString(2, cashbook.getCategory());
			stmt.setString(3, cashbook.getCashbookDate());
			stmt.setInt(4, cashbook.getPrice());
			stmt.setString(5, cashbook.getMemo());
	
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				cashbookNo = rs.getInt(1);
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cashbookNo;
	}
	
	/*
	// 해새태그별 cashbook 상세내역 예시
	public List<Cashbook> selectCashbookListByTag(String memeberId, String word, int beginRow, int rowPerPage) {
		List<Cashbook> list = new ArrayList<>();

		String sql = "SELECT c.* FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC LIMIT ?, ?";
			
		return list;
	}
	*/
	
	// cashbook 상세내역 조회 (cashbookNo)
	public Cashbook selectCashbookOneByCashbookNo(int cashbookNo) {
		Cashbook cashbook = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cashbook_no cashbookNo, member_id memberId, category, cashbook_date cashbookDate, price, memo, createdate, updatedate FROM cashbook WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);

			System.out.println(stmt);
			rs = stmt.executeQuery();
			while (rs.next()) {
				cashbook = new Cashbook();
				cashbook.setCashbookNo(rs.getInt("cashbookNo"));
				cashbook.setMemberId(rs.getString("memberId"));
				cashbook.setCategory(rs.getString("category"));
				cashbook.setCashbookDate(rs.getString("cashbookDate"));
				cashbook.setPrice(rs.getInt("price"));
				cashbook.setMemo(rs.getString("memo"));
				cashbook.setCreatedate(rs.getString("createdate"));
				cashbook.setUpdatedate(rs.getString("updatedate"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		return cashbook;
	}
	
	
	// cashbook 상세내역 수정
	// 날짜는 수정 불가
	public int modifyCashbook(Cashbook cashbook) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE cashbook SET category = ?, price = ?, memo = ?, updatedate = NOW() WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cashbook.getCategory());
			stmt.setInt(2, cashbook.getPrice());
			stmt.setString(3, cashbook.getMemo());
			stmt.setInt(4, cashbook.getCashbookNo());
			row = stmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return row;
	}
	
	// cashbook 상세내역 삭제
	public int removeCashbook(int cashbookNo) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM cashbook WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			row = stmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
}