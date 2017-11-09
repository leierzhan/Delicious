package com.zz.ccy.entity;

public class StoreInfo {
	private int id;
	private String storename;//店铺名称
	private String imgsurl; //店铺图片
	private int userid;
	private int level;//店铺级别
	private String per_capite;//人均
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	private String lunboimg;//轮播图
	private String address;//地址
	private String storerule;//消费规则id
	private String latitude;
	private String longitude;
	private String tel;//电话
	private String calture; //文化
	private String busiiness_licens; //营业执照
	private String distance;
	private int cityid;
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	private int status;//状态
	public StoreInfo() {
		super();
	}
	@Override
	public String toString() {
		return "StoreInfo [id=" + id + ", storename=" + storename
				+ ", imgsurl=" + imgsurl + ", userid=" + userid + ", level="
				+ level + ", per_capite=" + per_capite + ", lunboimg="
				+ lunboimg + ", address=" + address + ", storerule="
				+ storerule + ", latitude=" + latitude + ", longitude="
				+ longitude + ", tel=" + tel + ", calture=" + calture
				+ ", busiiness_licens=" + busiiness_licens + ", distance="
				+ distance + ", cityid=" + cityid + ", status=" + status + "]";
	}
	public StoreInfo(int id, String storename, String imgsurl, int userid,
			int level, String per_capite, String lunboimg, String address,
			String storerule, String latitude, String longitude, String tel,
			String calture, String busiiness_licens, String distance,
			int cityid, int status) {
		super();
		this.id = id;
		this.storename = storename;
		this.imgsurl = imgsurl;
		this.userid = userid;
		this.level = level;
		this.per_capite = per_capite;
		this.lunboimg = lunboimg;
		this.address = address;
		this.storerule = storerule;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tel = tel;
		this.calture = calture;
		this.busiiness_licens = busiiness_licens;
		this.distance = distance;
		this.cityid = cityid;
		this.status = status;
	}
	public String getLunboimg() {
		return lunboimg;
	}
	public void setLunboimg(String lunboimg) {
		this.lunboimg = lunboimg;
	}
	public String getAddress() {
		return address;
	}
	public String getBusiiness_licens() {
		return busiiness_licens;
	}
	public String getCalture() {
		return calture;
	}
	public int getId() {
		return id;
	}
	public String getStorerule() {
		return storerule;
	}
	public void setStorerule(String storerule) {
		this.storerule = storerule;
	}
	public String getImgsurl() {
		return imgsurl;
	}
	public String getLatitude() {
		return latitude;
	}
	public int getLevel() {
		return level;
	}
	public String getLongitude() {
		return longitude;
	}
	public String getPer_capite() {
		return per_capite;
	}
	public int getStatus() {
		return status;
	}
	public String getStorename() {
		return storename;
	}
	public String getTel() {
		return tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBusiiness_licens(String busiiness_licens) {
		this.busiiness_licens = busiiness_licens;
	}
	public void setCalture(String calture) {
		this.calture = calture;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setImgsurl(String imgsurl) {
		this.imgsurl = imgsurl;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setPer_capite(String per_capite) {
		this.per_capite = per_capite;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
}
