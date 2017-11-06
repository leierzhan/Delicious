package com.zz.ccy.entity;

public class SignUp {
	private int id;
	private String openId;
	private String username;
	private String sex;
	private String cityName;
	private int age;
	private String phone;
	private String job;
	private String cityId;//报名的城市id 多个城市id用分号隔�?
	private String distinctId;//报名的区域id 多个城市区域用分号隔�?
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	private String timec;
	private int flag;// 0是微信注册，1是网站注�?
	private String serialNum;
	private String initPrice;// 添加人：Allen 注释：首�?

	/**
	 * 修改�?Allen 修改原因：缺少无参构造方法�?
	 * */
	public SignUp() {
		super();
	}
	public SignUp(int id, String openId, String username, String sex,
			String cityName, int age, String phone, String job, String cityId,
			String distinctId, String timec, int flag, String serialNum,
			String initPrice) {
		super();
		this.id = id;
		this.openId = openId;
		this.username = username;
		this.sex = sex;
		this.cityName = cityName;
		this.age = age;
		this.phone = phone;
		this.job = job;
		this.cityId = cityId;
		this.distinctId = distinctId;
		this.timec = timec;
		this.flag = flag;
		this.serialNum = serialNum;
		this.initPrice = initPrice;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDistinctId() {
		return distinctId;
	}

	public void setDistinctId(String distinctId) {
		this.distinctId = distinctId;
	}

	public String getTimec() {
		return timec;
	}

	public void setTimec(String timec) {
		this.timec = timec;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getInitPrice() {
		return initPrice;
	}

	public void setInitPrice(String initPrice) {
		this.initPrice = initPrice;
	}
	@Override
	public String toString() {
		return "SignUp [id=" + id + ", openId=" + openId + ", username="
				+ username + ", sex=" + sex + ", cityName=" + cityName
				+ ", age=" + age + ", phone=" + phone + ", job=" + job
				+ ", cityId=" + cityId + ", distinctId=" + distinctId
				+ ", timec=" + timec + ", flag=" + flag + ", serialNum="
				+ serialNum + ", initPrice=" + initPrice + "]";
	}
}
