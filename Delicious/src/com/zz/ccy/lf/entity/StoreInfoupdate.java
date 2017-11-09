package com.zz.ccy.lf.entity;
/**
 * 
* @ClassName: StoreInfoupdate 
* @Description: TODO商户提交店铺信息表
* @author 李飞
* @date 2017年10月10日 下午3:40:34 
*
 */
public class StoreInfoupdate {
	private int id;
	private int cityid;
	private String storename;//店铺名称
	private String imgsurl; //店铺图片
	private int level;//店铺级别
	private int per_capite;//人均
	private String address;//地址
	private String lunboimg;//轮播图
	private int storerule;//消费规则id
	private String latitude;
	private String longitude;
	private String tel;//电话
	private String calture; //文化
	private String busiiness_licens; //营业执照
	private int status;//状态
	private int userid; //用户id
	private String timec; //时间
	
	public StoreInfoupdate() {
		super();
	}



	public StoreInfoupdate(int id, int cityid,String storename, String imgsurl, int level,
			int per_capite, String address,String lunboimg, int storerule, String latitude,
			String longitude, String tel, String calture,
			String busiiness_licens, int status, int userid, String timec) {
		super();
		this.id = id;
		this.cityid=cityid;
		this.storename = storename;
		this.imgsurl = imgsurl;
		this.level = level;
		this.per_capite = per_capite;
		this.address = address;
		this.lunboimg=lunboimg;
		this.storerule = storerule;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tel = tel;
		this.calture = calture;
		this.busiiness_licens = busiiness_licens;
		this.status = status;
		this.userid = userid;
		this.timec = timec;
	}

	@Override
	public String toString() {
		return "StoreInfoupdate [id=" + id + ", cityid=" + cityid
				+ ", storename=" + storename + ", imgsurl=" + imgsurl
				+ ", level=" + level + ", per_capite=" + per_capite
				+ ", address=" + address + ", lunboimg=" + lunboimg
				+ ", storerule=" + storerule + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", tel=" + tel + ", calture="
				+ calture + ", busiiness_licens=" + busiiness_licens
				+ ", status=" + status + ", userid=" + userid + ", timec="
				+ timec + "]";
	}



	public StoreInfoupdate(int storerule) {
		super();
		this.storerule = storerule;
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
	public int getStorerule() {
		return storerule;
	}
	public void setStorerule(int storerule) {
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
	public int getPer_capite() {
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
	public void setPer_capite(int per_capite) {
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
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTimec() {
		return timec;
	}
	public void setTimec(String timec) {
		this.timec = timec;
	}

	public int getCityid() {
		return cityid;
	}

	public void setCityid(int cityid) {
		this.cityid = cityid;
	}

	public String getLunboimg() {
		return lunboimg;
	}

	public void setLunboimg(String lunboimg) {
		this.lunboimg = lunboimg;
	}

}
