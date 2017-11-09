package com.zz.ccy.entity;

public class StoreMsb {
private int id;
private int num;
private String storename;
private int level;
private String address;
private String tel;
private int userid;
private String imgsurl;
private String starttime;
private int storeid;
public int getStoreid() {
	return storeid;
}
public void setStoreid(int storeid) {
	this.storeid = storeid;
}
private String endtime;
public StoreMsb(int id, int num, String storename, int level, String address,
		String tel, int userid, String starttime, String endtime, int status,String imgsurl,int storeid) {
	super();
	this.id = id;
	this.num = num;
	this.storeid=storeid;
	this.storename = storename;
	this.level = level;
	this.address = address;
	this.tel = tel;
	this.userid = userid;
	this.starttime = starttime;
	this.imgsurl=imgsurl;
	this.endtime = endtime;
	this.status = status;
}
private int status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
public String getStorename() {
	return storename;
}
public void setStorename(String storename) {
	this.storename = storename;
}
public int getLevel() {
	return level;
}
public void setLevel(int level) {
	this.level = level;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
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
public StoreMsb() {
	super();
	// TODO Auto-generated constructor stub
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getImgsurl() {
	return imgsurl;
}
public void setImgsurl(String imgsurl) {
	this.imgsurl = imgsurl;
}
}
