package com.zz.ccy.lf.entity;

public class MerchantInfo {
private int id;
private int userid;
private int storeid;
private String ercode;
private int status;
private String timec;
public MerchantInfo(int id,int userid,int storeid,String ercode, int status, String timec) {
	super();
	this.id = id;
	this.ercode=ercode;
	this.userid=userid;
	this.storeid=storeid;
	this.status = status;
	this.timec = timec;
}
public int getId() {
	return id;
}
public MerchantInfo() {
	super();
	// TODO Auto-generated constructor stub
}
public void setId(int id) {
	this.id = id;
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
public int getStoreid() {
	return storeid;
}
public void setStoreid(int storeid) {
	this.storeid = storeid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getErcode() {
	return ercode;
}
public void setErcode(String ercode) {
	this.ercode = ercode;
}
}
