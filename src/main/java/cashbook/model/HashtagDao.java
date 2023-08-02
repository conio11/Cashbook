package cashbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cashbook.vo.Hashtag;

public class HashtagDao {
	public int insertHashtag(Hashtag hashtag) {
		int row = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO hashtag(cashbook_no, word, updatedate, createdate) VALUES(?, ?, NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // insert 이후 자동 생성된 키 반환을 위해 사용
			stmt.setInt(1, hashtag.getCashbookNo());
			stmt.setString(2, hashtag.getWord());
	
			row = stmt.executeUpdate();
		
		}  catch (Exception e1) {
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
	
	// 해시태그 삭제 (가계부 내역 수정 시)
	public int removeHashtag(int cashbookNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM hashtag WHERE cashbook_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash","root","java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cashbookNo);
			row = stmt.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	
	public List<Map<String, Object>> selectWordCntByMonth(String memberId, int targetYear, int targetMonth) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT word, COUNT(*) cnt FROM hashtag h INNER JOIN cashbook c ON h.cashbook_no = c.cashbook_no WHERE c.member_id = ? AND YEAR(c.cashbook_date) = ? AND MONTH(c.cashbook_date) = ? GROUP BY word ORDER BY COUNT(*) DESC";
			
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, memberId);
			stmt.setInt(2, targetYear);
			stmt.setInt(3, targetMonth);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("word", rs.getString("word"));
				m.put("cnt", rs.getInt("cnt"));
				list.add(m);
			}
		
		}  catch (Exception e1) {
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

		return list;
	}
	
	// 해시태그별 상세내용
	// 내용, 작성일자, 가격, 카테고리 가져오기
	public List<Map<String, Object>> selectHashtagOne(String memberId, String word, int beginRow, int rowPerPage) {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		/*
		SELECT c.memo, c.cashbook_date, c.category, c.price, h.word 
		FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no
		WHERE c.member_id = 'user' AND h.word = '구디'
		ORDER BY c.cashbook_date DESC;
		*/
		
		String sql = "SELECT c.memo memo, c.cashbook_date cashbookDate, c.category category, c.price price, h.word word FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ? ORDER BY c.cashbook_date DESC LIMIT ?, ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, memberId);
			stmt.setString(2, word);
			stmt.setInt(3, beginRow);
			stmt.setInt(4, rowPerPage);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("memo", rs.getString("memo"));
				m.put("cashbookDate", rs.getString("cashbookDate"));
				m.put("category", rs.getString("category"));
				m.put("price", rs.getInt("price"));
				m.put("word", rs.getString("word"));
				list.add(m);
			}
		}  catch (Exception e1) {
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
		
		return list;
	}
	
	// 해시태그별 출력 리스트 행 수
	public int selectHashtagOneCnt(String memberId, String word) {
		int cnt = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
				
		String sql = "SELECT COUNT(*) FROM cashbook c INNER JOIN hashtag h ON c.cashbook_no = h.cashbook_no WHERE c.member_id = ? AND h.word = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql); 
			stmt.setString(1, memberId);
			stmt.setString(2, word);

			rs = stmt.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
		}  catch (Exception e1) {
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
		
		return cnt;
	}
}