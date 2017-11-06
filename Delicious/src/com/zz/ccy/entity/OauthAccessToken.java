package com.zz.ccy.entity;

public class OauthAccessToken {
  private String accessToken;
  private String expiresIn;
  private String openId;
  private String refreshToken;
  private String scope;
public OauthAccessToken() {
	super();
}
public OauthAccessToken(String accessToken, String expiresIn,
		String refreshToken, String openId, String scope) {
	super();
	this.accessToken = accessToken;
	this.expiresIn = expiresIn;
	this.refreshToken = refreshToken;
	this.openId = openId;
	this.scope = scope;
}
public String getAccessToken() {
	return accessToken;
}
public String getExpiresIn() {
	return expiresIn;
}
public String getOpenId() {
	return openId;
}
public String getRefreshToken() {
	return refreshToken;
}
public String getScope() {
	return scope;
}
public void setAccessToken(String accessToken) {
	this.accessToken = accessToken;
}
public void setExpiresIn(String expiresIn) {
	this.expiresIn = expiresIn;
}
public void setOpenId(String openId) {
	this.openId = openId;
}
public void setRefreshToken(String refreshToken) {
	this.refreshToken = refreshToken;
}
public void setScope(String scope) {
	this.scope = scope;
}
@Override
public String toString() {
	return "OauthAccessToken [accessToken=" + accessToken + ", expiresIn="
			+ expiresIn + ", refreshToken=" + refreshToken + ", openId="
			+ openId + ", scope=" + scope + "]";
}
}
