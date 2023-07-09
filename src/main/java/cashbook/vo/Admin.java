package cashbook.vo;

public class Admin {
	private String adminId;
	private String adminPw;
	private String createdate;
	
	public Admin() {
		super();
	}
	
	public Admin(String adminId, String adminPw, String createdate) {
		super();
		this.adminId = adminId;
		this.adminPw = adminPw;
		this.createdate = createdate;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPw=" + adminPw + ", createdate=" + createdate + "]";
	}	
}