package com.zz.ccy.entity;

public class Msb {
private int id;
private int userid ;
private int num;
private int storeid;
private int status;
private String code;
public Msb(int id, int userid, int num, int storeid, int status,
		String starttime, String endtime,String code) {
	super();
	this.id = id;
	this.userid = userid;
	this.num = num;
	this.storeid = storeid;
	this.status = status;
	this.starttime = starttime;
	this.endtime = endtime;
	this.setCode(code);
}

@Override
public String toString() {
	return "Msb [id=" + id + ", userid=" + userid + ", num=" + num
			+ ", storeid=" + storeid + ", status=" + status + ", code=" + code
			+ ", starttime=" + starttime + ", endtime=" + endtime + "]";
}

public Msb() {
	super();
	// TODO Auto-generated constructor stub
}
private String starttime;
private String endtime;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public int getStoreid() {
	return storeid;
}
public void setStoreid(int storeid) {
	this.storeid = storeid;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getStarttime() {
	return starttime;
}
public void setStarttime(String starttime) {
	this.starttime = starttime;
}
public String getEndtime() {
	return endtime;
}
public void setEndtime(String endtime) {
	this.endtime = endtime;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}
	
}
