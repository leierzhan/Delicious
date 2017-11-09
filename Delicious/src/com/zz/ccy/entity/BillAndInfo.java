package com.zz.ccy.entity;

public class BillAndInfo {
    private int id;
	private String billPath;//��Ʊ��ַ������ͼƬ��;ƴ��
	private int articleId;
	private String shopName;//��������
	private String shopAddress;//���̵�ַ
	private String shopDesc;//��������
	private String shopCulture;//�����Ļ�
	private String shopPhone;//���̵绰
	private String timec;//�����绰
	private int status;//��Ʊ״̬ 0 Ϊ����  1�ѱ���
	
	public BillAndInfo() {
		super();
	}
	public BillAndInfo(int id, String billPath, Integer articleId, String shopName,
			String shopAddress, String shopDesc, String shopCulture,
			String shopPhone, String timec, int status) {
		super();
		this.id = id;
		this.billPath = billPath;
		this.articleId = articleId;
		this.shopName = shopName;
		this.shopAddress = shopAddress;
		this.shopDesc = shopDesc;
		this.shopCulture = shopCulture;
		this.shopPhone = shopPhone;
		this.timec = timec;
		this.status = status;
	}
	public String getBillPath() {
		return billPath;
	}
	public int getId() {
			return id;
		}
	public String getShopAddress() {
		return shopAddress;
	}
	public String getShopCulture() {
		return shopCulture;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public String getShopName() {
		return shopName;
	}
	public String getShopPhone() {
		return shopPhone;
	}
	public int getStatus() {
		return status;
	}
	public String getTimec() {
		return timec;
	}
	public void setBillPath(String billPath) {
		this.billPath = billPath;
	}
   public void setId(int id) {
	this.id = id;
}
   public void setShopAddress(String shopAddress) {
	this.shopAddress = shopAddress;
}
   public void setShopCulture(String shopCulture) {
	this.shopCulture = shopCulture;
}
   public void setShopDesc(String shopDesc) {
	this.shopDesc = shopDesc;
}
   public void setShopName(String shopName) {
	this.shopName = shopName;
}
   public void setShopPhone(String shopPhone) {
	this.shopPhone = shopPhone;
}
   public void setStatus(int status) {
	this.status = status;
}
   public void setTimec(String timec) {
	this.timec = timec;
}
public int getArticleId() {
	return articleId;
}
public void setArticleId(int articleId) {
	this.articleId = articleId;
}
public BillAndInfo(int id, String billPath, int articleId, String shopName,
		String shopAddress, String shopDesc, String shopCulture,
		String shopPhone, String timec, int status) {
	super();
	this.id = id;
	this.billPath = billPath;
	this.articleId = articleId;
	this.shopName = shopName;
	this.shopAddress = shopAddress;
	this.shopDesc = shopDesc;
	this.shopCulture = shopCulture;
	this.shopPhone = shopPhone;
	this.timec = timec;
	this.status = status;
}
@Override
public String toString() {
	return "BillAndInfo [id=" + id + ", billPath=" + billPath + ", articleId="
			+ articleId + ", shopName=" + shopName + ", shopAddress="
			+ shopAddress + ", shopDesc=" + shopDesc + ", shopCulture="
			+ shopCulture + ", shopPhone=" + shopPhone + ", timec=" + timec
			+ ", status=" + status + "]";
}
}
