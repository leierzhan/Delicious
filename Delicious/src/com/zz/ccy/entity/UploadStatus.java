package com.zz.ccy.entity;

public class UploadStatus {
  private int status;
  private String url;
  
	public UploadStatus() {
		super();
	}
	public UploadStatus(int status, String url) {
		super();
		this.status = status;
		this.url = url;
	}
	@Override
	public String toString() {
		return "UploadStatus [status=" + status + ", url=" + url + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
