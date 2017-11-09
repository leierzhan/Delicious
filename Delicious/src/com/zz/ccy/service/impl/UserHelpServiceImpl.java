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
	//ע��EventService
	@Autowired
	private EventService eventService;
	@Override
	public String processRequest(Map<String, String> requestMap) {
			// xml��ʽ����Ϣ����
			String respXml = null;
			try {
				// ��Ϣ����
				final String msgType = requestMap.get("MsgType");
				/* �¼����� */
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml =eventService.eventBussiness(fromUserName,requestMap);
				}
				//�ı���Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)){
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.TextEvent(fromUserName,requestMap);
				} 
				//������Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.VoiceEvent(fromUserName, requestMap);
				}
				//ͼƬ��Ϣ
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.ImageEvent(fromUserName, requestMap);
				}
				//��Ƶ��Ϣ
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.VideoEvent(fromUserName, requestMap);
				}
				//����λ����Ϣ
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)){
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.LocationEvent(fromUserName, requestMap);
				}
				//������Ϣ
				else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)){
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.LinkEvent(fromUserName, requestMap);
				}
				//������Ϣ
				else{
					// ���ͷ��ʺ�
					String fromUserName = requestMap.get("FromUserName");
					respXml = eventService.OtherEvent(fromUserName, requestMap, msgType);
				}
			   } catch (Exception e) {
				e.printStackTrace();
			}
			return respXml;
	}
}
