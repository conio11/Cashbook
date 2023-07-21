package cashbook.vo;

public class Question {
	private int qNo;
	private String memberId;
	private String qTitle;
	private String qContent;
	private String qStatus;
	private String createdate;
	private String updatedate;
	
	public Question() {
		super();
	}
	
	public Question(int qNo, String memberId, String qTitle, String qContent, String qStatus, String createdate,
			String updatedate) {
		super();
		this.qNo = qNo;
		this.memberId = memberId;
		this.qTitle = qTitle;
		this.qContent = qContent;
		this.qStatus = qStatus;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}
	
	public int getqNo() {
		return qNo;
	}
	public void setqNo(int qNo) {
		this.qNo = qNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getqTitle() {
		return qTitle;
	}
	public void setqTitle(String qTitle) {
		this.qTitle = qTitle;
	}
	public String getqContent() {
		return qContent;
	}
	public void setqContent(String qContent) {
		this.qContent = qContent;
	}
	public String getqStatus() {
		return qStatus;
	}
	public void setqStatus(String qStatus) {
		this.qStatus = qStatus;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	
	@Override
	public String toString() {
		return "Question [qNo=" + qNo + ", memberId=" + memberId + ", qTitle=" + qTitle + ", qContent=" + qContent
				+ ", qStatus=" + qStatus + ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}	
}