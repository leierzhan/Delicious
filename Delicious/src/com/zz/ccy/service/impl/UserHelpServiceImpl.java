package com.zz.ccy.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.service.EventService;
import com.zz.ccy.service.UserHelpService;
import com.zz.ccy.util.MessageUtil;

@Service
@Transactional
public class UserHelpServiceImpl implements UserHelpService{
	//注入EventService
	@Autowired
	private EventService eventService;
	@Override
	public String processRequest(Map<String, String> requestMap) {
			// xml格式的消息数据
			String respXml = null;
			try {
				// 消息类型
				final String msgType = requestMap.get("MsgType");
				/* 事件推送 */
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml =eventService.eventBussiness(fromUserName,requestMap);
				}
				//文本消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.TextEvent(fromUserName,requestMap);
				} 
				//语音消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.VoiceEvent(fromUserName, requestMap);
				}
				//图片消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.ImageEvent(fromUserName, requestMap);
				}
				//视频消息
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.VideoEvent(fromUserName, requestMap);
				}
				//地理位置消息
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.LocationEvent(fromUserName, requestMap);
				}
				//链接消息
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.LinkEvent(fromUserName, requestMap);
				}
				//其他消息
				else{
					// 发送方帐号
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.OtherEvent(fromUserName, requestMap, msgType);
				}
			   } catch (Exception e) {
				e.printStackTrace();
			}
			return respXml;
	}
}
