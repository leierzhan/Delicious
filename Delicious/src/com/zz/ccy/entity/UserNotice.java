package com.zz.ccy.entity;

public class UserNotice {
private int id;
private int userid ;
private int modeid;
private String style;
private String color;
private String content;
private int status;
private String timec;
private String readtimec;
public UserNotice() {
	super();
	// TODO Auto-generated constructor stub
}
public UserNotice(int id, int userid, int modeid, String style, String color,
		String content, int status, String timec, String readtimec) {
	super();
	this.id = id;
	this.userid = userid;
	this.modeid = modeid;
	this.style = style;
	this.color = color;
	this.content = content;
	this.status = status;
	this.timec = timec;
	this.readtimec = readtimec;
}
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
public int getModeid() {
	return modeid;
}
public void setModeid(int modeid) {
	this.modeid = modeid;
}
public String getStyle() {
	return style;
}
public void setStyle(String style) {
	this.style = style;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
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
public String getReadtimec() {
	return readtimec;
}
public void setReadtimec(String readtimec) {
	this.readtimec = readtimec;
}
}
