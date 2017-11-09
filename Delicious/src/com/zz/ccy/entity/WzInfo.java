package com.zz.ccy.entity;

public class WzInfo {
private int id;
private int userid;
private String author;
private String wzname;
private String articleContent;
private String timec;
private int status;
public int getId() {
	return id;
}
public WzInfo() {
	super();
	// TODO Auto-generated constructor stub
}
public void setId(int id) {
	this.id = id;
}
public int getUserid() {
	return userid;
}
public WzInfo(int id, int userid, String author, String wzname,
		String articleContent, String timec, int status) {
	super();
	this.id = id;
	this.userid = userid;
	this.author = author;
	this.wzname = wzname;
	this.articleContent = articleContent;
	this.timec = timec;
	this.status = status;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getWzname() {
	return wzname;
}
public void setWzname(String wzname) {
	this.wzname = wzname;
}
public String getArticleContent() {
	return articleContent;
}
public void setArticleContent(String articleContent) {
	this.articleContent = articleContent;
}
public String getTimec() {
	return timec;
}
public void setTimec(String timec) {
	this.timec = timec;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
