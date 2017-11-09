package com.zz.ccy.entity;

public class UserMsbRecord {
private int id;
private String fromusername;
private String  tousername;
private int num;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFromusername() {
	return fromusername;
}
public void setFromusername(String fromusername) {
	this.fromusername = fromusername;
}
public String getTousername() {
	return tousername;
}
public void setTousername(String tousername) {
	this.tousername = tousername;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public int getStoreid() {
	return storeid;
}
public void setStoreid(int storeid) {
	this.storeid = storeid;
}
public String getStorename() {
	return storename;
}
public void setStorename(String storename) {
	this.storename = storename;
}
public String getWzname() {
	return wzname;
}
public void setWzname(String wzname) {
	this.wzname = wzname;
}
public String getTimec() {
	return timec;
}
public void setTimec(String timec) {
	this.timec = timec;
}
public UserMsbRecord() {
	super();
	// TODO Auto-generated constructor stub
}
private int type;
private int storeid;
private String storename;
private String wzname;
private String timec;
private int wzid;
private String openId;
private int touserid;
private int fromuserid;
public UserMsbRecord(int id, String fromusername, String tousername, int num,
		int type, int storeid, String storename, String wzname, String timec,
		int wzid, int touserid, int fromuserid,String openId) {
	super();
	this.id = id;
	this.fromusername = fromusername;
	this.tousername = tousername;
	this.num = num;
	this.type = type;
	this.storeid = storeid;
	this.storename = storename;
	this.wzname = wzname;
	this.timec = timec;
	this.wzid = wzid;
	this.touserid = touserid;
	this.fromuserid = fromuserid;
	this.openId=openId;
}
public int getWzid() {
	return wzid;
}
public void setWzid(int wzid) {
	this.wzid = wzid;
}
public int getTouserid() {
	return touserid;
}
public void setTouserid(int touserid) {
	this.touserid = touserid;
}
public int getFromuserid() {
	return fromuserid;
}
public void setFromuserid(int fromuserid) {
	this.fromuserid = fromuserid;
}
public String getOpenId() {
	return openId;
}
public void setOpenId(String openId) {
	this.openId = openId;
}

	
}
