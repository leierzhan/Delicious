package com.zz.ccy.entity;
/**
 * 
 * @ClassName: ChefEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: David
 * @date: 2017年10月9日 下午5:36:22
 */
public class ChefEntity {
	private int id;
	private int userid;//对应店铺id
	private String tel;//厨师电话
	private String name;
	private String headimg;
	private int level;
	private String tags;
	private int status;
	private int collectnum;
	private String timec;
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
   @Override
public String toString() {
	return "ChefEntity [id=" + id + ", userid=" + userid + ", tel=" + tel
			+ ", name=" + name + ", headimg=" + headimg + ", level=" + level
			+ ", tags=" + tags + ", status=" + status + ", collectnum="
			+ collectnum + ", timec=" + timec + "]";
}

public ChefEntity(int id, int userid, String tel, String name, String headimg,
		int level, String tags, int status, int collectnum, String timec) {
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
}
