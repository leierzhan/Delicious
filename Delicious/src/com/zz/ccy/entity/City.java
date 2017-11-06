package com.zz.ccy.entity;

public class City {
  private int id;
  private String cityname;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getCityname() {
	return cityname;
}
public void setCityname(String cityname) {
	this.cityname = cityname;
}
@Override
public String toString() {
	return "City [id=" + id + ", cityname=" + cityname + "]";
}
public City(int id, String cityname) {
	super();
	this.id = id;
	this.cityname = cityname;
}
public City() {
	super();
}
}
