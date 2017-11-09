package com.zz.ccy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 关注用户列表
 * @author lez
 * 2015�?�?8日下�?:39:19
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class WeixinUserList {
	// 公众账号的�?关注用户�?
	private int total;
	// 获取的OpenID个数
	private int count;
	// OpenID列表
	private List<String> openIdList;
	// 拉取列表的后�?��用户的OPENID
	private String nextOpenId;

	public int getTotal() {
		return total;
	}

	@Override
	public String toString() {
		return "WeixinUserList [total=" + total + ", count=" + count
				+ ", openIdList=" + openIdList + ", nextOpenId=" + nextOpenId
				+ "]";
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<String> getOpenIdList() {
		return openIdList;
	}

	public void setOpenIdList(List<String> openIdList) {
		this.openIdList = openIdList;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}
}
