package com.zz.ccy.entity;

public class CommentEntity {
    private int id;
    private String comment;
    private int storeId;
    private WeixinUserInfo weixinUserInfo;//���ۺ��û���һ��һ������ϵ
    private int zan;//��������
    private int status;//0������ʾ 1������ʾ
	public CommentEntity() {
		super();
	}
	public int getId() {
		return id;
	}
	public int getStatus() {
		return status;
	}
	public CommentEntity(int id, String comment, int storeId,
			WeixinUserInfo weixinUserInfo, int zan, int status) {
		super();
		this.id = id;
		this.comment = comment;
		this.storeId = storeId;
		this.weixinUserInfo = weixinUserInfo;
		this.zan = zan;
		this.status = status;
	}
	public int getStoreId() {
		return storeId;
	}
	public int getZan() {
		return zan;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	public WeixinUserInfo getWeixinUserInfo() {
		return weixinUserInfo;
	}
	public void setWeixinUserInfo(WeixinUserInfo weixinUserInfo) {
		this.weixinUserInfo = weixinUserInfo;
	}
	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", comment=" + comment
				+ ", storeId=" + storeId + ", weixinUserInfo=" + weixinUserInfo
				+ ", zan=" + zan + ", status=" + status + "]";
	}
}
