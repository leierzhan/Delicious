package com.zz.ccy.entity;

public class Merchant {
	private int id;
	private int userId;
	private int storeId;
	private String ercode;
	private int status;
	private String timec;

	public Merchant() {
		super();
	}

	public Merchant(int id, int userId, int storeId, String ercode, int status,
			String timec) {
		super();
		this.id = id;
		this.userId = userId;
		this.storeId = storeId;
		this.ercode = ercode;
		this.status = status;
		this.timec = timec;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", userId=" + userId + ", storeId="
				+ storeId + ", ercode=" + ercode + ", status=" + status
				+ ", timec=" + timec + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getErcode() {
		return ercode;
	}

	public void setErcode(String ercode) {
		this.ercode = ercode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTimec() {
		return timec;
	}

	public void setTimec(String timec) {
		this.timec = timec;
	}
}
