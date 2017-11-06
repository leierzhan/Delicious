package com.zz.ccy.service;

import java.util.Map;
import com.zz.ccy.message.resp.NewsMessage;
import com.zz.ccy.message.resp.TextMessage;
/**
 * @ClassName: EventService
 * @Description: TODO(������һ�仰��������������)
 * @author: 
 * @date: 2017��9��7�� ����10:21:40
 */
public interface EventService {
	//������Ӧ�¼�����ע�¼���ȡ����ע�¼����Զ���˵�����¼�
	public String eventBussiness(String fromUserName,Map<String, String> requestMap);
	
	//�ı��¼�
	public String TextEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * �����¼�
	 */
	public String VoiceEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * ͼƬ�¼�
	 */
	public String ImageEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * video�¼�
	 */
	public String VideoEvent(String fromUserName,Map<String, String> requestMap);
	/*
	 * MusicEvent
	 */
	public String MusicEvent(String fromUserName,Map<String, String> requestMap,String msgType);
	/*
	 * ����Event
	 */
	public String OtherEvent(String fromUserName,Map<String, String> requestMap,String msgType);
	/**
	 * ����λ����Ϣ
	 * 2015��7��28������5:47:10
	 * lez
	 */
	public String LocationEvent(String fromUserName, Map<String, String> requestMap) ;
	/**
	 * ������Ϣ
	 * 2015��7��28������5:47:10
	 * lez
	 */
	public String LinkEvent(String fromUserName, Map<String, String> requestMap) ;
	/*
	 * ��װtextMessage
	 */
	public TextMessage getTextMessage(String fromUserName,Map<String, String> requestMap);

	/**
	 * ��װNewsMessage
	 * 2015��8��7������9:45:57
	 * lez
	 */
	public NewsMessage getNewsMessage(String fromUserName,Map<String, String> requestMap);
}
