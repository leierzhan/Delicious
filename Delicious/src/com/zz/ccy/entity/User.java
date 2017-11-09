package com.zz.ccy.entity;

public class User {
	private int id;
	private String phone_num;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", phone_num=" + phone_num + ", password="
				+ password + "]";
	}

	public User(int id, String phone_num, String password) {
		super();
		this.id = id;
		this.phone_num = phone_num;
		this.password = password;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}

	public User() {
		super();
	}

}
