package com.zz.ccy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @ClassName: NewsInfo
 * @Description:群发的图文 需要保存到数据库
 * @author: 
 * @date: 2017年9月7日 上午9:21:31
 */
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class NewsInfo {
	private Integer id;
	private String thumb_media_id;
	private String author;
	private String title;
	private String content_source_url;
	private String content;
	private String digest;
	private int show_cover_pic;
	private String date;
	private String coverImgUrl;
	public String getCoverImgUrl() {
		return coverImgUrl;
	}
	public void setCoverImgUrl(String coverImgUrl) {
		this.coverImgUrl = coverImgUrl;
	}
	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public String getDate() {
		return date;
	}
	public String getDigest() {
		return digest;
	}
	public Integer getId() {
		return id;
	}
	public int getShow_cover_pic() {
		return show_cover_pic;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public String getTitle() {
		return title;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "NewsInfo [id=" + id + ", thumb_media_id=" + thumb_media_id
				+ ", author=" + author + ", title=" + title
				+ ", content_source_url=" + content_source_url + ", content="
				+ content + ", digest=" + digest + ", show_cover_pic="
				+ show_cover_pic + ", date=" + date + ", coverImgUrl="
				+ coverImgUrl +"]";
	}
	public NewsInfo(Integer id, String thumb_media_id, String author,
			String title, String content_source_url, String content,
			String digest, int show_cover_pic, String date, String coverImgUrl) {
		super();
		this.id = id;
		this.thumb_media_id = thumb_media_id;
		this.author = author;
		this.title = title;
		this.content_source_url = content_source_url;
		this.content = content;
		this.digest = digest;
		this.show_cover_pic = show_cover_pic;
		this.date = date;
		this.coverImgUrl = coverImgUrl;
	}
	public NewsInfo() {
	}
}
