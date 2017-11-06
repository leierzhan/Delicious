package com.zz.ccy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @ClassName: WeixinUserInfo
 * @Description: 微信用户的基本信息
 * @author: 
 * @date: 2017年9月7日 上午10:01:22
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class WeixinUserInfo {
	private Integer id;
	// 用户的标识
	private String openId;
	// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
	private int subscribe;
	// 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	private String subscribeTime;

	// 昵称
	private String nickname;
	// 用户的性别（1是男性，2是女性，0是未知）
	private int sex;
	private int age;//年龄
	// 用户所在国家
	private String country;
	// 用户所在省份
	private String province;
	// 用户所在城市
	private String city;
	// 用户的语言，简体中文为zh_CN
	private String language;
	// 用户头像
	private String headImgUrl;
	private int groupId;
	private String address;//用户地址
	private String tel;//电话
	private String brithday;
	private String email;//email
	private String education;//教育程度
	private String remark;//公众号运营者对粉丝的备注
	private String truename;//用户真实姓名
	private String unionId;//只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。
	private int status;
	private String ercode;
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public WeixinUserInfo() {
		super();
	}

	public String getAddress() {
		return address;
	}
	public int getAge() {
		return age;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getEducation() {
		return education;
	}
	public String getEmail() {
		return email;
	}
	public int getGroupId() {
		return groupId;
	}
    public String getHeadImgUrl() {
		return headImgUrl;
	}
    public Integer getId() {
		return id;
	}
    public String getLanguage() {
		return language;
	}
    public String getNickname() {
		return nickname;
	}
    public String getOpenId() {
		return openId;
	}
	public String getProvince() {
		return province;
	}
	public String getRemark() {
		return remark;
	}
    public int getSex() {
		return sex;
	}
    public int getSubscribe() {
		return subscribe;
	}
  
	public String getSubscribeTime() {
		return subscribeTime;
	}
	public String getTel() {
		return tel;
	}
	public String getTruename() {
		return truename;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setTruename(String truename) {
		this.truename = truename;
	}

	public WeixinUserInfo(Integer id, String openId, int subscribe,
			String subscribeTime, String nickname, int sex, int age,
			String country, String province,String brithday, String city, String language,
			String headImgUrl, int groupId, String address, String tel,
			String email, String education, String remark, String truename,
			String unionId, int status, String ercode) {
		super();
		this.id = id;
		this.openId = openId;
		this.subscribe = subscribe;
		this.subscribeTime = subscribeTime;
		this.nickname = nickname;
		this.sex = sex;
		this.age = age;
		this.country = country;
		this.province = province;
		this.brithday=brithday;
		this.city = city;
		this.language = language;
		this.headImgUrl = headImgUrl;
		this.groupId = groupId;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.education = education;
		this.remark = remark;
		this.truename = truename;
		this.unionId = unionId;
		this.status = status;
		this.ercode = ercode;
	}
	@Override
	public String toString() {
		return "WeixinUserInfo [id=" + id + ", openId=" + openId
				+ ", subscribe=" + subscribe + ", subscribeTime="
				+ subscribeTime + ", nickname=" + nickname + ", sex=" + sex
				+ ", age=" + age + ", country=" + country + ", province="
				+ province + ", city=" + city + ", language=" + language
				+ ", headImgUrl=" + headImgUrl + ", groupId=" + groupId
				+ ", address=" + address + ", tel=" + tel + ", brithday="
				+ brithday + ", email=" + email + ", education=" + education
				+ ", remark=" + remark + ", truename=" + truename
				+ ", unionId=" + unionId + ", status=" + status + ", ercode="
				+ ercode + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErcode() {
		return ercode;
	}
	public void setErcode(String ercode) {
		this.ercode = ercode;
	}
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
}

