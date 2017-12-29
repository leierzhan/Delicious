package com.zz.ccy.entity;

public class StoretradingRecord {
private int id;
private int user_id;
public StoretradingRecord(int id, int user_id, int store_id,
		int universal_coin, int unique_coin, String openId, String headImgUrl,
		String nickname, String create_time) {
	super();
	this.id = id;
	this.user_id = user_id;
	this.store_id = store_id;
	this.universal_coin = universal_coin;
	this.unique_coin = unique_coin;
	this.openId = openId;
	this.headImgUrl = headImgUrl;
	this.nickname = nickname;
	this.create_time = create_time;
}
public StoretradingRecord() {
	super();
	// TODO Auto-generated constructor stub
}
private int store_id;
private int universal_coin;
private int unique_coin;
private String openId;
private String headImgUrl;
private String nickname;
private String create_time;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getStore_id() {
	return store_id;
}
public void setStore_id(int store_id) {
	this.store_id = store_id;
}
public int getUniversal_coin() {
	return universal_coin;
}
public void setUniversal_coin(int universal_coin) {
	this.universal_coin = universal_coin;
}
public int getUnique_coin() {
	return unique_coin;
}
public void setUnique_coin(int unique_coin) {
	this.unique_coin = unique_coin;
}
public String getOpenId() {
	return openId;
}
public void setOpenId(String openId) {
	this.openId = openId;
}
public String getHeadImgUrl() {
	return headImgUrl;
}
public void setHeadImgUrl(String headImgUrl) {
	this.headImgUrl = headImgUrl;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String create_time) {
	this.create_time = create_time;
}
}
