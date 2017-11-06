package com.zz.ccy.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;


 /**
  * 
 * @ClassName: MerchantService 
 * @Description: TODO(������һ�仰��������������) 
 * @author ���
 * @date 2017��10��11�� ����1:28:34 
 *
  */
public interface MerchantService {
	MerchantInfo isMerchant(int userid);
	
	int getUserIdByOpenid(String openid);

	int addStoreupdate(StoreInfoupdate s);
	
	int addChef(ChefEntity c);
	
	int addGreens(Greens g);
	
	int getUserStatusById(String openid);
	StoreInfo getStoreInfo(int Userid);
	StoreInfo getStoreInfoById(int id) ;
	
	List<ChefEntity> getChefList(int storeid);
	
	List<Greens> getGreensList(int storeid);
	List<Storerule> getRuleList();
	WeixinUserInfo getUserByOpenid(String openid);
	StoreInfoupdate getStoreupdate(int userid);
	/**
	 * 
	 * @param id
	 * @return
	 *//** ���
	* @Title: getChefById 
	* @Description: TODO(���ݳ�ʦid��ѯ��ʦ
	* @param @param id
	* @param @return    �趨�ļ� 
	* @return ChefEntity    �������� 
	* @throws
	 */
     ChefEntity getChefById(int id);
     /**
      * 
      * @param c
      * @return
      *//** ���
     * @Title: updateChefinfo 
     * @Description: TODO(���³�ʦ
     * @param @param c
     * @param @return    �趨�ļ� 
     * @return int    �������� 
     * @throws
      */
     int updateChefinfo(ChefEntity c);
     /**
      * 
      * @param id
      * @return
      *//** ���
     * @Title: delChefById 
     * @Description: TODO(ɾ����ʦ
     * @param @param id
     * @param @return    �趨�ļ� 
     * @return int    �������� 
     * @throws
      */
     int delChefById(int id);
 	Greens getGreensById(int id);
 	/**
 	 * 
 	 * @param id
 	 * @return
 	 *//** ���
 	* @Title: updateGreensById 
 	* @Description: TODO(������һ�仰�����������������) 
 	* @param @param id
 	* @param @return    �趨�ļ� 
 	* @return int    �������� 
 	* @throws
 	 */
 	int updateGreensById(Greens g);
 	/**
 	 * 
 	 * @param id
 	 * @return
 	 *//** ���
 	* @Title: delGreens 
 	* @Description: TODO(����idɾ��
 	* @param @param id
 	* @param @return    �趨�ļ� 
 	* @return int    �������� 
 	* @throws
 	 */
 	int delGreens(int id);
 	
	public Jsconfig getJsConfig(int id) ;
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: isMerchantUser 
	* @Description: TODO(�����û�id��ѯ�û����״̬) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int isMerchantUser(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: ischef 
	* @Description: TODO(�жϳ�ʦ) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int ischef(int userid);
	
	public String sendCode(String mobilenum) throws ClientProtocolException, IOException;
	
	
}
