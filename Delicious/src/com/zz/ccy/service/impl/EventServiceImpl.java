package com.zz.ccy.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.message.resp.BaseMessage;
import com.zz.ccy.message.resp.Music;
import com.zz.ccy.message.resp.MusicMessage;
import com.zz.ccy.message.resp.NewsMessage;
import com.zz.ccy.message.resp.TextMessage;
import com.zz.ccy.service.EventService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.AdvancedUtil;
import com.zz.ccy.util.MessageUtil;
import com.zz.ccy.util.TokenThread;

@Service
@Transactional
public class EventServiceImpl implements EventService{
	private static String respXml=null;
	//出入UserService
	@Autowired
	private UserService userService;

	//各类响应事件，关注事件、取消关注事件、自定义菜单点击事件
	@Override
	public String eventBussiness(final String fromUserName,Map<String,String> requestMap){
		TextMessage textMessage = getTextMessage(fromUserName, requestMap);
		String eventType = requestMap.get("Event");
		// 关注事件
	    if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
	    	textMessage.setContent("你好，欢迎关注唇齿缘。");
		    respXml = MessageUtil.messageToXml(textMessage);
			// 组装图文客服消息
			// 发送客服消息
			//根据关注着的fromUserName(OpneId)来获取用户的基本信息，并保存到数据库
			new Thread(){
				public void run(){
					AdvancedUtil advancedUtil=new AdvancedUtil();
					String accessToken=TokenThread.accessToken.getAccessToken();
					userService.saveOrUpdateEntity(advancedUtil.getUserInfo(accessToken,fromUserName)); 
				};
			}.start();
	    }// 取消订阅	
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
			textMessage.setContent("感谢关注唇齿缘!");
			respXml = MessageUtil.messageToXml(textMessage);
		}// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
		  // 事件KEY值，与创建菜单时的key值对应, 可以根据eventKey判断用户点的是那个菜单
		   String eventKey = requestMap.get("EventKey");
		  if(eventKey.equals("helpChoice")){
			  textMessage.setContent("客服已接通，请在输入框和客服沟通。");
		      respXml = MessageUtil.messageToXml(textMessage);
		  }
		}else{
			textMessage.setContent("好像是出错了!");
			respXml = MessageUtil.messageToXml(textMessage);
		}
		return respXml;
	}
	//文本事件
	@Override
	public String TextEvent(String fromUserName,Map<String,String> requestMap){
		//开发者微信号
		String toUserName = requestMap.get("ToUserName");
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * 语音事件
	 */
	@Override
	public String VoiceEvent(String fromUserName,Map<String,String> requestMap){
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
//		VoiceMessage voiceMessage = new VoiceMessage();
//		voiceMessage.setToUserName(fromUserName);
//		voiceMessage.setFromUserName(toUserName);
//		voiceMessage.setCreateTime(new Date().getTime());
//		voiceMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_VOICE);
//		Voice voice=new Voice();
//		voice.setMediaId(requestMap.get("MediaId"));
//		voiceMessage.setVoice(voice);
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
//		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		// 将文本消息对象转换成xml
//		respXml = MessageUtil.messageToXml(voiceMessage);
		return respXml;
	}
	/*
	 * 图片事件
	 */
	@Override
	public String ImageEvent(String fromUserName, Map<String, String> requestMap) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
//		ImageMessage imageMessage = new ImageMessage();
//		imageMessage.setToUserName(fromUserName);
//		imageMessage.setFromUserName(toUserName);
//		imageMessage.setCreateTime(new Date().getTime());
//		imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
//		Image image=new Image();
//		image.setMediaId(requestMap.get("MediaId"));
//		imageMessage.setImage(image);
//         System.out.println("dediaId="+requestMap.get("MediaId"));
//		// 将文本消息对象转换成xml
//		respXml = MessageUtil.messageToXml(imageMessage);
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
//		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * video事件
	 */
	@Override
	public String VideoEvent(String fromUserName, Map<String, String> requestMap) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
//		VideoMessage videoMessage = new VideoMessage();
//		videoMessage.setToUserName(fromUserName);
//		videoMessage.setFromUserName(toUserName);
//		videoMessage.setCreateTime(new Date().getTime());
//		videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
//		Video video = new Video();
//		videoMessage.setVideo(video);
//		// 将文本消息对象转换成xml
//		respXml = MessageUtil.messageToXml(videoMessage);
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
//		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * 地理位置消息
	 */
	@Override
	public String LocationEvent(String fromUserName, Map<String, String> requestMap) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		String content="地理位置消息你的openId="+fromUserName+"地理位置="+requestMap.get("Location_X")+","+requestMap.get("Location_Y");
		textMessage.setContent(content);
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}
	/* 
	 *链接
	 */
	@Override
	public String LinkEvent(String fromUserName, Map<String, String> requestMap) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		String content="链接消息你的openId="+fromUserName+"链接="+requestMap.get("Url");
		textMessage.setContent(content);
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}
	/**
	 * 音乐消息
	 */
	@Override
	public String MusicEvent(String fromUserName,Map<String, String> requestMap, String msgType) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
	    MusicMessage musicMessage = new MusicMessage();
	    musicMessage.setToUserName(fromUserName);
	    musicMessage.setFromUserName(toUserName);
	    musicMessage.setCreateTime(new Date().getTime());
	    musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
	    Music music=new Music();
	    music.setDescription(requestMap.get("Description"));
	    music.setTitle(requestMap.get("Title"));
	    music.setHQMusicUrl(requestMap.get("HQMusicUrl"));
	    music.setMusicUrl(requestMap.get("MusicURL"));
	    music.setThumbMediaId(requestMap.get("ThumbMediaId"));
	    musicMessage.setMusic(music);
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageToXml(musicMessage);
		return respXml;
	}
	/*
	 * 其他Event
	 */
	@Override
	public String OtherEvent(String fromUserName,Map<String, String> requestMap, String msgType) {
		// 开发者微信号
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("其他消息,买家网欢迎你,我们将竭诚为您服务!");
		// 将文本消息对象转换成xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}

	/*
	 * 封装textMessage
	 */
	@Override
	public TextMessage getTextMessage(String fromUserName,Map<String, String> requestMap) {
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(System.currentTimeMillis());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		return textMessage;
	}
	/*
	 * 封装newsMessage
	 */
	@Override
	public NewsMessage getNewsMessage(String fromUserName,Map<String, String> requestMap) {
		String toUserName = requestMap.get("ToUserName");
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setArticleCount(5);
		newsMessage.setCreateTime(System.currentTimeMillis());
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		return newsMessage;
	}
}
