package com.zz.ccy.lf.entity;

public class Jsconfig {
private int id;
private String noncestr;
private String timestamp;
private String apis;
private String signature;
private String timec;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNoncestr() {
	return noncestr;
}
public void setNoncestr(String noncestr) {
	this.noncestr = noncestr;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}
public String getApis() {
	return apis;
}
public void setApis(String apis) {
	this.apis = apis;
}
public String getSignature() {
	return signature;
}
public void setSignature(String signature) {
	this.signature = signature;
}
public String getTimec() {
	return timec;
}
public void setTimec(String timec) {
	this.timec = timec;
}
public Jsconfig(int id, String noncestr, String timestamp, String apis,
		String signature, String timec) {
	super();
	this.id = id;
	this.noncestr = noncestr;
	this.timestamp = timestamp;
	this.apis = apis;
	this.signature = signature;
	this.timec = timec;
}
public Jsconfig() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Jsconfig [id=" + id + ", noncestr=" + noncestr + ", timestamp="
			+ timestamp + ", apis=" + apis + ", signature=" + signature
			+ ", timec=" + timec + "]";
}
}
