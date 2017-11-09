package com.zz.ccy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @ClassName: WeixinUserInfo
 * @Description: ΢���û��Ļ�����Ϣ
 * @author: 
 * @date: 2017��9��7�� ����10:01:22
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class WeixinUserInfo {
	private Integer id;
	// �û��ı�ʶ
	private String openId;
	// ��ע״̬��1�ǹ�ע��0��δ��ע����δ��עʱ��ȡ����������Ϣ
	private int subscribe;
	// �û���עʱ�䣬Ϊʱ���������û�����ι�ע����ȡ����עʱ��
	private String subscribeTime;

	// �ǳ�
	private String nickname;
	// �û����Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
	private int sex;
	private int age;//����
	// �û����ڹ���
	private String country;
	// �û�����ʡ��
	private String province;
	// �û����ڳ���
	private String city;
	// �û������ԣ���������Ϊzh_CN
	private String language;
	// �û�ͷ��
	private String headImgUrl;
	private int groupId;
	private String address;//�û���ַ
	private String tel;//�绰
	private String brithday;
	private String email;//email
	private String education;//�����̶�
	private String remark;//���ں���Ӫ�߶Է�˿�ı�ע
	private String truename;//�û���ʵ����
	private String unionId;//ֻ�����û������ںŰ󶨵�΢�ſ���ƽ̨�ʺź󣬲Ż���ָ��ֶΡ�
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

