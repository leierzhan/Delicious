package com.zz.ccy.util;

import com.zz.ccy.menu.Menu;

import net.sf.json.JSONObject;
/**
 * �Զ���˵�������
 * @author Mr0727
 *
 */
public class MenuUtil {
	// �˵�������POST��
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// �˵���ѯ��GET��
	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// �˵�ɾ����GET��
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	/**
	 * �����˵�
	 * @param menu �˵�ʵ��
	 * @param accessToken ƾ֤
	 * @return true�ɹ� falseʧ��
	 */
	public static boolean createMenu(Menu menu,String accessToken) {
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		// ���˵�����ת����json�ַ���
		String jsonMenu = JSONObject.fromObject(menu).toString();
		// ����POST���󴴽��˵�
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject =commonUtil.httpsRequest(url,"POST",jsonMenu);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				System.out.println("�����˵�"+errorMsg);
			}
		}
		return result;
	}
	/**
	 * ��ѯ�˵�
	 * @param accessToken ƾ֤
	 * @return
	 */
	public static String getMenu(String accessToken) {
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		// ����GET�����ѯ�˵�
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"GET",null);
		if (null != jsonObject) {
			result = jsonObject.toString();
		}
		return result;
	}
	/**
	 * ɾ���˵�
	 * 
	 * @param accessToken ƾ֤
	 * @return true�ɹ� falseʧ��
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		// ����GET����ɾ���˵�
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
			} else {
				result = false;
				System.out.println("ɾ���˵�ʧ�� errcode:{} errmsg:{}"+errorCode+","+errorMsg);
			}
		}
		return result;
	}
}