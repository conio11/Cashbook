package cashbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cashbook.vo.Answer;
import cashbook.vo.Question;

public class AnswerDao {
	// 답변 입력
	// 반환값: a_no (키값)
	public int insertAnswer(Answer answer) {
		int aNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력 후 생성된 키값 반환받을 목적
		String sql = "INSERT INTO answer(q_no, member_id, a_content, createdate) VALUES(?, ?, ?, NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 키값(auto_increment) 반환	
			stmt.setInt(1, answer.getqNo());
			stmt.setString(2, answer.getMemberId());
			stmt.setString(3, answer.getaContent());
			
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				aNo = rs.getInt(1);
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
		
		// 답변 입력 후 문의글 상태 변경
		if (aNo > 0) {
			QuestionDao questionDao = new QuestionDao(); 
			questionDao.modifyqStatus(answer.getqNo());
		}
		
		return aNo;
	}
	
	// 답변 삭제
	public int removeAnswer(String memberId, int qNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM answer WHERE q_no = ? AND member_id = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
			stmt.setString(2, memberId);
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
	
	// 문의 상세내역 - 답변 상세내역
	public Answer selectAnswerOne(String memberId, int qNo) {
		Answer answer = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT a_no aNo, q_no qNo, member_id memberId, a_content aContent, createdate FROM answer WHERE q_no = ? AND member_id = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				answer = new Answer();
				answer.setaNo(rs.getInt("aNo"));
				answer.setqNo(rs.getInt("qNo"));
				answer.setMemberId(rs.getString("memberId"));
				answer.setaContent(rs.getString("aContent"));
				answer.setCreatedate(rs.getString("createdate"));
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
		
		return answer;
	}
}