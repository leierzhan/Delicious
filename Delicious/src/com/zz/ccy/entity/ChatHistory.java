package com.zz.ccy.entity;
/**
 * 客服会话
 * @author David
 * 2017-04-01
 */
public class ChatHistory {
   private int id;
   private String openid;
   private String opercode;
   private String text;
   private String time;
   private String worker;
   public ChatHistory() {
			super();
   }
   public ChatHistory(int id,String worker,String openid,String opercode,String text,String time) {
			super();
			this.id = id;
			this.worker = worker;
			this.openid = openid;
			this.opercode = opercode;
			this.text = text;
			this.time = time;
	}
	public int getId() {
			return id;
		}
	public String getOpenid() {
		return openid;
	}
	public String getOpercode() {
		return opercode;
	}
	public String getText() {
		return text;
	}
	public String getTime() {
		return time;
	}
	public String getWorker() {
		return worker;
	}
	public void setId(int id) {
		this.id = id;
	}
public void setOpenid(String openid) {
	this.openid = openid;
}
   public void setOpercode(String opercode) {
	this.opercode = opercode;
}
   public void setText(String text) {
	this.text = text;
}
   public void setTime(String time) {
	this.time = time;
}
   public void setWorker(String worker) {
	this.worker = worker;
}
   @Override
	public String toString() {
		return "ChatHistory [id=" + id + ", worker=" + worker + ", openid="
				+ openid + ", opercode=" + opercode + ", text=" + text
				+ ", time=" + time + "]";
	}
}
