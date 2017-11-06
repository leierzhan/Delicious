package com.zz.ccy.entity;

/**
 * 临时二维码信�?
 * 
 * @author lez
 * @date 2015-07-30
 */
public class WeixinQRCode {
	// 获取的二维码ticket
	private String ticket;
	// 二维码的有效时间，单位为秒，�?��不超�?800
	private int expireSeconds;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
}
