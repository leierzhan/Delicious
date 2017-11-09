package com.zz.ccy.entity;

public class HouseEntity {
	private int id;
    private int cityId;
    private String houseName;
	public int getCityId() {
		return cityId;
	}
	public HouseEntity() {
		super();
	}
	public HouseEntity(int cityId, String houseName, int id) {
		super();
		this.cityId = cityId;
		this.houseName = houseName;
		this.id = id;
	}
	public String getHouseName() {
		return houseName;
	}
	public int getId() {
			return id;
		}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
public void setHouseName(String houseName) {
	this.houseName = houseName;
}
   public void setId(int id) {
	this.id = id;
}
   @Override
	public String toString() {
		return "HouseEntity [id=" + id + ", cityId=" + cityId + ", houseName="
				+ houseName + "]";
	}
} 
