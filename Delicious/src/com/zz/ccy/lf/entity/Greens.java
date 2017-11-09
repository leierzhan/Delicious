package com.zz.ccy.lf.entity;
/**
 * 
* @ClassName: Greens 
* @Description: TODO  菜信息
* @author 李飞
* @date 2017年10月9日 下午11:19:02 
*
 */
public class Greens {
	private int id;
	private int storeid;
	private int chefid;
	private String imgs;
	private String name;
	private float price;
	private String tags;
	private int zan;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getChefid() {
		return chefid;
	}
	public void setChefid(int chefid) {
		this.chefid = chefid;
	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getTags() {
		return tags;
	}
	public Greens() {
		super();
		// TODO Auto-generated constructor stub
	}
public Greens(int id, int storeid,int chefid, String imgs, String name, float price,
		String tags, int zan, int status) {
	super();
	this.id = id;
	this.storeid=storeid;
	this.chefid = chefid;
	this.imgs = imgs;
	this.name = name;
	this.price = price;
	this.tags = tags;
	this.zan = zan;
	this.status = status;
}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public int getZan() {
		return zan;
	}
	public void setZan(int zan) {
		this.zan = zan;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}

}
