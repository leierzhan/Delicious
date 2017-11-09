package com.zz.ccy.entity;

public class OpenHouseInfo {
	private int id;
	private String houseName;
    private String address;
    private String img;
    private String openDate;
    private String openCount;
    private String baseInfoUrl;//
    private String moreInfoUrl;
  
  public OpenHouseInfo() {
	super();
}
  public OpenHouseInfo(int id, String houseName, String address, String img,
		String openDate, String openCount, String baseInfoUrl,
		String moreInfoUrl) {
	super();
	this.id = id;
	this.houseName = houseName;
	this.address = address;
	this.img = img;
	this.openDate = openDate;
	this.openCount = openCount;
	this.baseInfoUrl = baseInfoUrl;
	this.moreInfoUrl = moreInfoUrl;
}
public String getImg() {
	return img;
}
public void setImg(String img) {
	this.img = img;
}
public String getAddress() {
	return address;
}
public String getBaseInfoUrl() {
	return baseInfoUrl;
}
public String getHouseName() {
	return houseName;
}
public int getId() {
	return id;
}
public String getMoreInfoUrl() {
	return moreInfoUrl;
}
public String getOpenCount() {
	return openCount;
}
public String getOpenDate() {
	return openDate;
}
public void setAddress(String address) {
	this.address = address;
}
public void setBaseInfoUrl(String baseInfoUrl) {
	this.baseInfoUrl = baseInfoUrl;
}
public void setHouseName(String houseName) {
	this.houseName = houseName;
}
public void setId(int id) {
	this.id = id;
}
public void setMoreInfoUrl(String moreInfoUrl) {
	this.moreInfoUrl = moreInfoUrl;
}
public void setOpenCount(String openCount) {
	this.openCount = openCount;
}
public void setOpenDate(String openDate) {
	this.openDate = openDate;
}
@Override
public String toString() {
	return "OpenHouseInfo [id=" + id + ", houseName=" + houseName
			+ ", address=" + address + ", img=" + img + ", openDate="
			+ openDate + ", openCount=" + openCount + ", baseInfoUrl="
			+ baseInfoUrl + ", moreInfoUrl=" + moreInfoUrl + "]";
}
}
