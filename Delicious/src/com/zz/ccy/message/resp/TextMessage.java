package com.zz.ccy.message.resp;

/**
 * 文本消息类型
 * @author lez
 *
 */
public class TextMessage extends BaseMessage {
	// 回复的消息内�?
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}
