package com.zz.ccy.entity;
/**
 * 
 * @ClassName: ChefEntity
 * @Description: TODO(������һ�仰��������������)
 * @author: David
 * @date: 2017��10��9�� ����5:36:22
 */
public class ChefEntity {
	private int id;
	private int userid;//��Ӧ����id
	private String tel;//��ʦ�绰
	private String name;
	private String headimg;
	private int level;
	private String tags;
	private int status;
	private int collectnum;
	private String timec;
	private String starttime;
	private String endtime;
	public ChefEntity() {
	   super();
    }

	public String getHeadimg() {
		return headimg;
	}
	public int getId() {
			return id;
		}
	public int getLevel() {
		return level;
	}
	public String getName() {
		return name;
	}
	public int getStatus() {
		return status;
	}
	public int getuserid() {
		return userid;
	}
	public String getTags() {
		return tags;
	}
	public String getTel() {
		return tel;
	}
	public String getTimec() {
		return timec;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
public void setId(int id) {
	this.id = id;
}
   public void setLevel(int level) {
	this.level = level;
}
   public void setName(String name) {
	this.name = name;
}
   public void setStatus(int status) {
	this.status = status;
}
   public void setuserid(int userid) {
	this.userid = userid;
}



public ChefEntity(int id, int userid, String tel, String name, String headimg,
		int level, String tags, int status, int collectnum, String timec,
		String starttime, String endtime) {
	super();
	this.id = id;
	this.userid = userid;
	this.tel = tel;
	this.name = name;
	this.headimg = headimg;
	this.level = level;
	this.tags = tags;
	this.status = status;
	this.collectnum = collectnum;
	this.timec = timec;
	this.starttime = starttime;
	this.endtime = endtime;
}

public void setTags(String tags) {
	this.tags = tags;
}
   public void setTel(String tel) {
	this.tel = tel;
}
   public void setTimec(String timec) {
	this.timec = timec;
}

public int getCollectnum() {
	return collectnum;
}
public void setCollectnum(int collectnum) {
	this.collectnum = collectnum;
}

public String getEndtime() {
	return endtime;
}

public void setEndtime(String endtime) {
	this.endtime = endtime;
}

public String getStarttime() {
	return starttime;
}

public void setStarttime(String starttime) {
	this.starttime = starttime;
}
}
