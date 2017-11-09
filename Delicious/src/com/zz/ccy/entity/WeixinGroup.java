package com.zz.ccy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 公众账号分组信息
 * 
 * @author lez
 * @date 2015-07-30
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class WeixinGroup {
	// 分组id
	private int id;
	// 分组名称
	private String name;
	// 分组内的用户�?
	private int count;
	//主键
	private int gid;

	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "WeixinGroup [id=" + id + ", name=" + name + ", count=" + count
				+ ", gid=" + gid + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
