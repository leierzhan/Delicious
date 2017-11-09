package com.zz.ccy.entity;

public class TbUserOpenId {

	private Integer id;
	private String openId;
	public TbUserOpenId(Integer id,String openId){
		super();
		this.id = id;
		this.openId = openId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "TbUserOpenId [id=" + id + ", openId=" + openId + "]";
	}
	public TbUserOpenId() {
		super();
	}
}