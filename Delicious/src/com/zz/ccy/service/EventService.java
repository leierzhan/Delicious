package com.zz.ccy.service;

import java.util.Map;
import com.zz.ccy.message.resp.NewsMessage;
import com.zz.ccy.message.resp.TextMessage;
/**
 * @ClassName: EventService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 
 * @date: 2017年9月7日 上午10:21:40
 */
public interface EventService {
	//各类响应事件，关注事件、取消关注事件、自定义菜单点击事件
	public String eventBussiness(String fromUserName,Map<String, String> requestMap);
	
	//文本事件
	public String TextEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * 语音事件
	 */
	public String VoiceEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * 图片事件
	 */
	public String ImageEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * video事件
	 */
	public String VideoEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * MusicEvent
	 */
	public String MusicEvent(String fromUserName,Map<String, String> requestMap,String msgType);
	/*
	 * 其他Event
	 */
	public String OtherEvent(String fromUserName,Map<String, String> requestMap,String msgType);
	/**
	 * 地理位置消息
	 * 2015年7月28日下午5:47:10
	 * lez
	 */
	public String LocationEvent(String fromUserName, Map<String, String> requestMap) ;
	/**
	 * 链接消息
	 * 2015年7月28日下午5:47:10
	 * lez
	 */
	public String LinkEvent(String fromUserName, Map<String, String> requestMap) ;
	/*
	 * 封装textMessage
	 */
	public TextMessage getTextMessage(String fromUserName,Map<String, String> requestMap);

	/**
	 * 封装NewsMessage
	 * 2015年8月7日上午9:45:57
	 * lez
	 */
	public NewsMessage getNewsMessage(String fromUserName,Map<String, String> requestMap);
}
