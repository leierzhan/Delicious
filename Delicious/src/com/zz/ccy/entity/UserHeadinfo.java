package com.zz.ccy.entity;

public class UserHeadinfo {
	private int id;
	private String headimgurl;
	private String truename;
	private String tel;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public UserHeadinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public UserHeadinfo(int id, String headimgurl, String truename,String tel) {
		super();
		this.id = id;
		this.headimgurl = headimgurl;
		this.truename = truename;
		this.tel=tel;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
