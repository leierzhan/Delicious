package com.zz.ccy.entity;
/**
 * @ClassName: ArticleInfo
 * @Description: 文章封装
 * @author: David
 * @date: 2017年9月7日 上午9:17:04
 */
 public class ArticleInfo {
   private int id;
   private int userid;
   private int author;
   private String articleTitle;
   public String getArticleTitle() {
	return articleTitle;
   }
   public void setArticleTitle(String articleTitle) {
	this.articleTitle = articleTitle;
   }
   private String articleContent;
   private String timec;
   private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getAuthor() {
		return author;
	}
	public void setAuthor(Integer author) {
		this.author = author;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public String getTimec() {
		return timec;
	}
	public void setTimec(String timec) {
		this.timec = timec;
	}
	public int getStatus() {
		return status;
	}
	public ArticleInfo() {
		super();
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ArticleInfo [id=" + id + ", userid=" + userid + ", author="
				+ author + ", articleTitle=" + articleTitle
				+ ", articleContent=" + articleContent + ", timec=" + timec
				+ ", status=" + status + "]";
	}
	public ArticleInfo(int id, int userid, int author, String articleTitle,
			String articleContent, String timec, int status) {
		super();
		this.id = id;
		this.userid = userid;
		this.author = author;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.timec = timec;
		this.status = status;
	}
   
}
