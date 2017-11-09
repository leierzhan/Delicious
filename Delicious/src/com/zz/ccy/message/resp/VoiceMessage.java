package com.zz.ccy.message.resp;

/**
 * 视频消息
 * @author lez
 *
 */
public class VoiceMessage extends BaseMessage {
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
