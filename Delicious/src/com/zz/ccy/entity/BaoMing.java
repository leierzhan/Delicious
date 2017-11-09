package com.zz.ccy.entity;

public class BaoMing {
  private int id;
  private String username;
  private String phone;
  private String seriesNum;
  private int houseId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public BaoMing() {
	super();
}
public BaoMing(int id, String username, String phone, String seriesNum,
		int houseId) {
	super();
	this.id = id;
	this.username = username;
	this.phone = phone;
	this.seriesNum = seriesNum;
	this.houseId = houseId;
}
@Override
public String toString() {
	return "BaoMing [id=" + id + ", username=" + username + ", phone=" + phone
			+ ", seriesNum=" + seriesNum + ", houseId=" + houseId + "]";
}
public void setUsername(String username) {
	this.username = username;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getSeriesNum() {
	return seriesNum;
}
public void setSeriesNum(String seriesNum) {
	this.seriesNum = seriesNum;
}
public int getHouseId() {
	return houseId;
}
public void setHouseId(int houseId) {
	this.houseId = houseId;
}
}
