package cashbook.model;

import java.sql.*;
import java.util.*;

import cashbook.vo.Notice;

public class NoticeDao {
	// 전체 게시글 조회
	public List<Notice> selectNoticeAll(int beginRow, int rowPerPage) {
		List<Notice> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT notice_no noticeNo, notice_title noticeTitle, notice_content noticeContent, createdate, updatedate FROM notice ORDER BY notice_no DESC LIMIT ?, ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rs.getInt("noticeNo"));
				notice.setNoticeTitle(rs.getString("noticeTitle"));
				notice.setNoticeContent(rs.getString("noticeContent"));
				notice.setCreatedate(rs.getString("createdate"));
				notice.setUpdatedate(rs.getString("updatedate"));
				list.add(notice);
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
	
	// 전체 게시글 수 
	public int selectNoticeCnt() {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM notice";
		
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
	
	
	// 게시글 작성 (admin)
	// 반환값: notice_no (키값)
	public int insertNotice(Notice notice) {
		int noticeNo = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null; // 입력 후 생성된 키값 반환받을 목적
		String sql = "INSERT INTO notice(notice_title, notice_content, createdate, updatedate) VALUES(?, ?, NOW(), NOW())";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // 키값(auto_increment) 반환
			stmt.setString(1, notice.getNoticeTitle());
			stmt.setString(2, notice.getNoticeContent());
			
			int row = stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				noticeNo = rs.getInt(1);
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
		
		return noticeNo;
	}
	
	// 게시글 상세정보 (noticeNo)
	public Notice selectNoticeOneByNoticeNo(int noticeNo) {
		Notice notice = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT notice_no noticeNo, notice_title noticeTitle, notice_content noticeContent, createdate, updatedate FROM notice WHERE notice_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeNo);
			rs = stmt.executeQuery();
			while (rs.next()) {
				notice = new Notice();
				notice.setNoticeNo(rs.getInt("noticeNo"));
				notice.setNoticeTitle(rs.getString("noticeTitle"));
				notice.setNoticeContent(rs.getString("noticeContent"));
				notice.setCreatedate(rs.getString("createdate"));
				notice.setUpdatedate(rs.getString("updatedate"));
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
		
		return notice;
		
	}
	
	// 게시글 수정 (admin)
	public int modifyNotice(String noticeTitle, String noticeContent, int noticeNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "UPDATE notice SET notice_title = ?, notice_content = ?, updatedate = NOW() WHERE notice_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, noticeTitle);
			stmt.setString(2, noticeContent);
			stmt.setInt(3, noticeNo);
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
	
	// 게시글 삭제 (admin)
	public int removeNotice(int noticeNo) {
		int row = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "DELETE FROM notice WHERE notice_no = ?";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, noticeNo);
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
