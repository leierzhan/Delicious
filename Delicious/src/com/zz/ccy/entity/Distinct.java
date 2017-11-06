package com.zz.ccy.entity;

public class Distinct {
	private int id;
    private City city;
    private String distinctName;
    
	public Distinct() {
		super();
	}
	public Distinct(int id, String distinctName, City city) {
		super();
		this.id = id;
		this.distinctName = distinctName;
		this.city = city;
	}
	public City getCity() {
		return city;
	}
	public String getDistinctName() {
		return distinctName;
	}
	public int getId() {
		return id;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public void setDistinctName(String distinctName) {
		this.distinctName = distinctName;
	}
	public void setId(int id) {
		this.id = id;
	}
	  @Override
	public String toString() {
		return "Distinct [id=" + id + ", distinctName=" + distinctName + ", city="
				+ city + "]";
	}
}
