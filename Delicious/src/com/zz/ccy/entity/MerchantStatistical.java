package com.zz.ccy.entity;

public class MerchantStatistical {
private int universal;
private int unique;
public int getUniversal() {
	return universal;
}
public MerchantStatistical() {
	super();
	// TODO Auto-generated constructor stub
}
public MerchantStatistical(int universal, int unique) {
	super();
	this.universal = universal;
	this.unique = unique;
}
public void setUniversal(int universal) {
	this.universal = universal;
}
public int getUnique() {
	return unique;
}
public void setUnique(int unique) {
	this.unique = unique;
}
}
