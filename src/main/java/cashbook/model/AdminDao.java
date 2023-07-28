package cashbook.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cashbook.vo.Admin;
import cashbook.vo.Member;

public class AdminDao {
	// 관리자 로그인 메소드
	public Admin selectAdminById(Admin paramAdmin) { // 로그인 성공 시 Admin 객체 반환
		Admin returnAdmin = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/cash", "root", "java1234");
			String sql = "SELECT admin_id adminId FROM admin WHERE admin_id = ? AND admin_pw = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramAdmin.getAdminId());
			stmt.setString(2, paramAdmin.getAdminPw());
			System.out.println(stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				returnAdmin = new Admin();
				returnAdmin.setAdminId(rs.getString("adminId"));
			}
			
		} catch(Exception e1) {
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
		
		return returnAdmin;
		
	}
}
