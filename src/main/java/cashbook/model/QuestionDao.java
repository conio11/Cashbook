package cashbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cashbook.vo.Notice;
import cashbook.vo.Question;

public class QuestionDao {
	// 나의 문의글 조회
	public List<Question> selectMyQuestion(String memberId) {
		List<Question> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT q_no qNo, member_id memberId, q_title qTitle, q_content qContent, q_status qStatus, createdate, updatedate FROM question WHERE member_id = ? ORDER BY q_no DESC";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setqNo(rs.getInt("qNo"));
				question.setMemberId(rs.getString("memberId"));
				question.setqTitle(rs.getString("qTitle"));
				question.setqContent(rs.getString("qContent"));
				question.setqStatus(rs.getString("qStatus"));
				question.setCreatedate(rs.getString("createdate"));
				question.setCreatedate(rs.getString("updatedate"));
				list.add(question);
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
		
		return list;
	}
	
	// 나의 문의글 전체 행 수 
	public int selectMyQuestionCnt(String memberId) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM question WHERE member_id = ?";
		
		try {	
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
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
		
		return row;
	}
	
	// 전체 문의글 조회 (admin)
	public List<Question> selectQuestionAll(int beginRow, int rowPerPage) {
		List<Question> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT q_no qNo, member_id memberId, q_title qTitle, q_content qContent, q_status qStatus, createdate, updatedate FROM question ORDER BY q_no DESC LIMIT ?, ? ";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setqNo(rs.getInt("qNo"));
				question.setMemberId(rs.getString("memberId"));
				question.setqTitle(rs.getString("qTitle"));
				question.setqContent(rs.getString("qContent"));
				question.setqStatus(rs.getString("qStatus"));
				question.setCreatedate(rs.getString("createdate"));
				question.setCreatedate(rs.getString("updatedate"));
				list.add(question);
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
		
		return list;
	}
	
	// 전체 문의글 수
	public int selectQuestionCnt() {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM question";
		
		try {	
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			if (rs.next()) {
				row = rs.getInt(1);
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
		
		return row;
	}
	
	
	// 문의글 입력
	// 반환값: q_no (키값) 
	public int insertQuestion(Question question) {
		int qNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력 후 생성된 키값 반환받을 목적
		String sql = "INSERT INTO question(member_id, q_title, q_content, q_status, createdate, updatedate) VALUES(?, ?, ?, '답변대기', NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 키값(auto_increment) 반환	
			stmt.setString(1, question.getMemberId());
			stmt.setString(2, question.getqTitle());
			stmt.setString(3, question.getqContent());
			
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				qNo = rs.getInt(1);
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
		
		return qNo;
	}
	
	
	// 나의 문의글 상세정보
	public Question selectMyQuestionOne(String memberId, int qNo) {
		Question question = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT q_no qNo, member_id memberId, q_title qTitle, q_content qContent, q_status qStatus, createdate, updatedate FROM question WHERE q_no = ? AND member_id = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
			stmt.setString(2, memberId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				question = new Question();
				question.setqNo(rs.getInt("qNo"));
				question.setMemberId(rs.getString("memberId"));
				question.setqTitle(rs.getString("qTitle"));
				question.setqContent(rs.getString("qContent"));
				question.setqStatus(rs.getString("qStatus"));
				question.setCreatedate(rs.getString("createdate"));
				question.setUpdatedate(rs.getString("updatedate"));
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
		
		return question;
	}
	
	// 문의글 상세정보 (admin)
	public Question selectQuestionOne(int qNo) {
		Question question = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT q_no qNo, member_id memberId, q_title qTitle, q_content qContent, q_status qStatus, createdate, updatedate FROM question WHERE q_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				question = new Question();
				question.setqNo(rs.getInt("qNo"));
				question.setMemberId(rs.getString("memberId"));
				question.setqTitle(rs.getString("qTitle"));
				question.setqContent(rs.getString("qContent"));
				question.setqStatus(rs.getString("qStatus"));
				question.setCreatedate(rs.getString("createdate"));
				question.setUpdatedate(rs.getString("updatedate"));
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
		
		return question;
	}
	
	// 나의 문의글 수정
	public int modifyMyQuestion(String memberId, String qTitle, String qContent, int qNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE question SET q_title = ?, q_content = ?, updatedate = NOW() WHERE q_no = ? AND member_id = ? AND q_status = '답변대기'";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, qTitle);
			stmt.setString(2, qContent);
			stmt.setInt(3, qNo);
			stmt.setString(4, memberId);
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

	// 나의 문의글 삭제
	public int removeMyQuestion(String memberId, int qNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM question WHERE q_no = ? AND member_id = ?";
		
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
	
	// 문의글 상태변경 (관리자 답변 작성 시)
	public int modifyqStatus(int qNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE question SET q_status = '답변완료' WHERE q_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
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
	
	// 문의글 상태변경 (관리자 답변 삭제 시)
	public int modifyqStatus2(int qNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE question SET q_status = '답변대기' WHERE q_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, qNo);
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
