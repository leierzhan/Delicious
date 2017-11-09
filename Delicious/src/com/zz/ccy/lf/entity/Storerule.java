package com.zz.ccy.lf.entity;

public class Storerule {
private  int id;
private int man;
private int di;
private int status;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getMan() {
	return man;
}
public void setMan(int man) {
	this.man = man;
}
public int getDi() {
	return di;
}
@Override
public String toString() {
	return "Storerule [id=" + id + ", man=" + man + ", di=" + di + ", status="
			+ status + "]";
}
public Storerule() {
	super();
	// TODO Auto-generated constructor stub
}
public Storerule(int id, int man, int di, int status) {
	super();
	this.id = id;
	this.man = man;
	this.di = di;
	this.status = status;
}
public void setDi(int di) {
	this.di = di;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
}
