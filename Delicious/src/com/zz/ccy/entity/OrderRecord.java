package com.zz.ccy.entity;

public class OrderRecord {
	private int id;
	private int userId;// 消费者id
	private int storeId;// 商家id
	private int universalCount;// 消费通用币数量
	private int uniqueCount;// 消费唯一币数量
	private String createTime;// 消费时间

	public OrderRecord() {
		super();
	}

	public OrderRecord(int id, int userId, int storeId, int universalCount,
			int uniqueCount, String createTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.storeId = storeId;
		this.universalCount = universalCount;
		this.uniqueCount = uniqueCount;
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public int getId() {
		return id;
	}

	public int getStoreId() {
		return storeId;
	}

	public int getUniqueCount() {
		return uniqueCount;
	}

	public int getUniversalCount() {
		return universalCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public void setUniqueCount(int uniqueCount) {
		this.uniqueCount = uniqueCount;
	}

	public void setUniversalCount(int universalCount) {
		this.universalCount = universalCount;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OrderRecord [id=" + id + ", userId=" + userId + ", storeId="
				+ storeId + ", universalCount=" + universalCount
				+ ", uniqueCount=" + uniqueCount + ", createTime=" + createTime
				+ "]";
	}
}
