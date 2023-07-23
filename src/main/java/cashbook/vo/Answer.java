package cashbook.vo;

public class Answer {
	private int aNo;
	private int qNo;
	private String memberId;
	private String aContent;
	private String createdate;
	private String updatedate;
	
	public Answer() {
		super();
	}
	
	public Answer(int aNo, int qNo, String memberId, String aContent, String createdate, String updatedate) {
		super();
		this.aNo = aNo;
		this.qNo = qNo;
		this.memberId = memberId;
		this.aContent = aContent;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}
	
	public int getaNo() {
		return aNo;
	}
	public void setaNo(int aNo) {
		this.aNo = aNo;
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
	public String getaContent() {
		return aContent;
	}
	public void setaContent(String aContent) {
		this.aContent = aContent;
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
		return "Answer [aNo=" + aNo + ", qNo=" + qNo + ", memberId=" + memberId + ", aContent=" + aContent
				+ ", createdate=" + createdate + ", updatedate=" + updatedate + "]";
	}
}