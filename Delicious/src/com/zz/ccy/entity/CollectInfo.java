package com.zz.ccy.entity;

public class CollectInfo {
	private String name;
	private int id;//收藏记录id
	private int storeid;//店铺id
	private int userid;
	private String tags;
	private String latitude;
	private String longitude;
	private int chefid; //厨师id
	private int cheflevel;//厨师级别
	private String headimg;//厨师头像
	private String cheftel; //厨师电话
	private String imgsurl; //店铺图片
	private String storename;//店铺名称
	private int level;//店铺级别
	private int per_capite;//人均
	private String address;//地址
	private String tel;//电话
	private String calture; //文化
	private String busiiness_licens; //营业执照
	private int status;//状态
	private String timec;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStoreid() {
		return storeid;
	}
	public void setStoreid(int storeid) {
		this.storeid = storeid;
	}
	public CollectInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CollectInfo(int id, int storeid, int chefid, int cheflevel,
			String headimg, String cheftel, String imgsurl, String storename,
			int level, int per_capite, String address, String latitude,String longgitude,
			String tel, String calture, String busiiness_licens, int status,
			String timec,int userid,String name,String tags) {
		super();
		this.tags=tags;
		this.name=name;
		this.id = id;
		this.userid=userid;
		this.storeid = storeid;
		this.chefid = chefid;
		this.cheflevel = cheflevel;
		this.headimg = headimg;
		this.cheftel = cheftel;
		this.imgsurl = imgsurl;
		this.storename = storename;
		this.level = level;
		this.per_capite = per_capite;
		this.address = address;
		this.latitude = latitude;
		this.longitude=longgitude;
		this.tel = tel;
		this.calture = calture;
		this.busiiness_licens = busiiness_licens;
		this.status = status;
		this.timec = timec;
	}
	public int getChefid() {
		return chefid;
	}
	public void setChefid(int chefid) {
		this.chefid = chefid;
	}
	public int getCheflevel() {
		return cheflevel;
	}
	public void setCheflevel(int cheflevel) {
		this.cheflevel = cheflevel;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getCheftel() {
		return cheftel;
	}
	public void setCheftel(String cheftel) {
		this.cheftel = cheftel;
	}
	public String getImgsurl() {
		return imgsurl;
	}
	public void setImgsurl(String imgsurl) {
		this.imgsurl = imgsurl;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPer_capite() {
		return per_capite;
	}
	public void setPer_capite(int per_capite) {
		this.per_capite = per_capite;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCalture() {
		return calture;
	}
	public void setCalture(String calture) {
		this.calture = calture;
	}
	public String getBusiiness_licens() {
		return busiiness_licens;
	}
	public void setBusiiness_licens(String busiiness_licens) {
		this.busiiness_licens = busiiness_licens;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTimec() {
		return timec;
	}
	public void setTimec(String timec) {
		this.timec = timec;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
