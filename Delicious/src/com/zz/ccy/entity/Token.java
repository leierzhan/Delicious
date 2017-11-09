package com.zz.ccy.entity;

/**
 * token
 * @author lez
 * 2016-11-22 9:20:20
 */
public class Token {
	//accessToken
	private String accessToken;
	// 有效时间
	private int expiresIn;
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}