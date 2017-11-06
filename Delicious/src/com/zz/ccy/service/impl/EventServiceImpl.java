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
	//����UserService
	@Autowired
	private UserService userService;

	//������Ӧ�¼�����ע�¼���ȡ����ע�¼����Զ���˵�����¼�
	@Override
	public String eventBussiness(final String fromUserName,Map<String,String> requestMap){
		TextMessage textMessage = getTextMessage(fromUserName, requestMap);
		String eventType = requestMap.get("Event");
		// ��ע�¼�
	    if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
	    	textMessage.setContent("��ã���ӭ��ע����Ե��");
		    respXml = MessageUtil.messageToXml(textMessage);
			// ��װͼ�Ŀͷ���Ϣ
			// ���Ϳͷ���Ϣ
			//���ݹ�ע�ŵ�fromUserName(OpneId)����ȡ�û��Ļ�����Ϣ�������浽���ݿ�
			new Thread(){
				public void run(){
					AdvancedUtil advancedUtil=new AdvancedUtil();
					String accessToken=TokenThread.accessToken.getAccessToken();
					userService.saveOrUpdateEntity(advancedUtil.getUserInfo(accessToken,fromUserName)); 
				};
			}.start();
	    }// ȡ������	
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
			textMessage.setContent("��л��ע����Ե!");
			respXml = MessageUtil.messageToXml(textMessage);
		}// �Զ���˵�����¼�
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
		  // �¼�KEYֵ���봴���˵�ʱ��keyֵ��Ӧ, ���Ը���eventKey�ж��û�������Ǹ��˵�
		   String eventKey = requestMap.get("EventKey");
		  if(eventKey.equals("helpChoice")){
			  textMessage.setContent("�ͷ��ѽ�ͨ�����������Ϳͷ���ͨ��");
		      respXml = MessageUtil.messageToXml(textMessage);
		  }
		}else{
			textMessage.setContent("�����ǳ�����!");
			respXml = MessageUtil.messageToXml(textMessage);
		}
		return respXml;
	}
	//�ı��¼�
	@Override
	public String TextEvent(String fromUserName,Map<String,String> requestMap){
		//������΢�ź�
		String toUserName = requestMap.get("ToUserName");
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * �����¼�
	 */
	@Override
	public String VoiceEvent(String fromUserName,Map<String,String> requestMap){
		// ������΢�ź�
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
//		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		// ���ı���Ϣ����ת����xml
//		respXml = MessageUtil.messageToXml(voiceMessage);
		return respXml;
	}
	/*
	 * ͼƬ�¼�
	 */
	@Override
	public String ImageEvent(String fromUserName, Map<String, String> requestMap) {
		// ������΢�ź�
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
//		// ���ı���Ϣ����ת����xml
//		respXml = MessageUtil.messageToXml(imageMessage);
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
//		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * video�¼�
	 */
	@Override
	public String VideoEvent(String fromUserName, Map<String, String> requestMap) {
		// ������΢�ź�
		String toUserName = requestMap.get("ToUserName");
//		VideoMessage videoMessage = new VideoMessage();
//		videoMessage.setToUserName(fromUserName);
//		videoMessage.setFromUserName(toUserName);
//		videoMessage.setCreateTime(new Date().getTime());
//		videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
//		Video video = new Video();
//		videoMessage.setVideo(video);
//		// ���ı���Ϣ����ת����xml
//		respXml = MessageUtil.messageToXml(videoMessage);
		BaseMessage baseMessage=new BaseMessage();
		baseMessage.setFromUserName(toUserName);
		baseMessage.setToUserName(fromUserName);
		baseMessage.setCreateTime(new Date().getTime());
		baseMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_CUSTOMER);
//		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageSendToKeFuToXml(baseMessage);
		return respXml;
	}
	/*
	 * ����λ����Ϣ
	 */
	@Override
	public String LocationEvent(String fromUserName, Map<String, String> requestMap) {
		// ������΢�ź�
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		String content="����λ����Ϣ���openId="+fromUserName+"����λ��="+requestMap.get("Location_X")+","+requestMap.get("Location_Y");
		textMessage.setContent(content);
		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}
	/* 
	 *����
	 */
	@Override
	public String LinkEvent(String fromUserName, Map<String, String> requestMap) {
		// ������΢�ź�
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		String content="������Ϣ���openId="+fromUserName+"����="+requestMap.get("Url");
		textMessage.setContent(content);
		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}
	/**
	 * ������Ϣ
	 */
	@Override
	public String MusicEvent(String fromUserName,Map<String, String> requestMap, String msgType) {
		// ������΢�ź�
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
		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageToXml(musicMessage);
		return respXml;
	}
	/*
	 * ����Event
	 */
	@Override
	public String OtherEvent(String fromUserName,Map<String, String> requestMap, String msgType) {
		// ������΢�ź�
		String toUserName = requestMap.get("ToUserName");
		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent("������Ϣ,�������ӭ��,���ǽ��߳�Ϊ������!");
		// ���ı���Ϣ����ת����xml
		respXml = MessageUtil.messageToXml(textMessage);
		return respXml;
	}

	/*
	 * ��װtextMessage
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
	 * ��װnewsMessage
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
