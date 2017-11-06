package com.zz.ccy.entity;

public class History {
  private int id;
  private String title;
  private String date;
  private String coverImgUrl;
  private String articleUrl;
  
public String getArticleUrl() {
	return articleUrl;
}
public void setArticleUrl(String articleUrl) {
	this.articleUrl = articleUrl;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public History() {
	super();
}
public void setTitle(String title) {
	this.title = title;
}
public String getDate() {
	return date;
}
public History(int id, String title, String date, String coverImgUrl,
		String articleUrl) {
	super();
	this.id = id;
	this.title = title;
	this.date = date;
	this.coverImgUrl = coverImgUrl;
	this.articleUrl = articleUrl;
}
@Override
public String toString() {
	return "History [id=" + id + ", title=" + title + ", date=" + date
			+ ", coverImgUrl=" + coverImgUrl + ", articleUrl=" + articleUrl
			+ "]";
}
public void setDate(String date) {
	this.date = date;
}
public String getCoverImgUrl() {
	return coverImgUrl;
}
public void setCoverImgUrl(String coverImgUrl) {
	this.coverImgUrl = coverImgUrl;
}
}
