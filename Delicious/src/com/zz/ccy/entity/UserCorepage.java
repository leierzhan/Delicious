package com.zz.ccy.entity;

public class UserCorepage {
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getUsername() {
		return username;
	}
	public UserCorepage(int userid, String headimg, String username,
			String sex, int msb, int zdmsb,String code, int wzstatus, int scstatus,
			int zjstatus, int tzstatus) {
		super();
		this.userid = userid;
		this.headimg = headimg;
		this.username = username;
		this.sex = sex;
		this.msb = msb;
		this.zdmsb = zdmsb;
		this.code=code;
		this.wzstatus = wzstatus;
		this.scstatus = scstatus;
		this.zjstatus = zjstatus;
		this.tzstatus = tzstatus;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getMsb() {
		return msb;
	}
	public void setMsb(int msb) {
		this.msb = msb;
	}
	public int getZdmsb() {
		return zdmsb;
	}
	public void setZdmsb(int zdmsb) {
		this.zdmsb = zdmsb;
	}
	public int getWzstatus() {
		return wzstatus;
	}
	public void setWzstatus(int wzstatus) {
		this.wzstatus = wzstatus;
	}
	public int getScstatus() {
		return scstatus;
	}
	public void setScstatus(int scstatus) {
		this.scstatus = scstatus;
	}
	public int getZjstatus() {
		return zjstatus;
	}
	public void setZjstatus(int zjstatus) {
		this.zjstatus = zjstatus;
	}
	public int getTzstatus() {
		return tzstatus;
	}
	public void setTzstatus(int tzstatus) {
		this.tzstatus = tzstatus;
	}
	private int userid;
	private String headimg;
	private String username;
	private String sex;
	private int msb;
	private int zdmsb;
	private String code;
	private int wzstatus;
	private int scstatus;
	private int zjstatus;
	private int tzstatus;
	public UserCorepage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
