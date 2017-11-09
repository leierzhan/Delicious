package com.zz.ccy.message.req;
/**
 * 图片消息
 * 
 * @author lez
 * @date 2015-07-28
 */
public class ImageMessage extends BaseMessage {
	// 图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}
