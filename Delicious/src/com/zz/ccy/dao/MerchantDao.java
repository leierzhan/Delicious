package com.zz.ccy.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.StoretradingRecord;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;

/**
 * 
* @ClassName: MerchantDao 
* @Description: TODO(�̻�) 
* @author ���
* @date 2017��10��10�� ����10:01:27 
*
*/
@Component
public interface MerchantDao {
	
	
	/**
	 * 
	 * 
	 * @param openid
	 * @return
	 *//** ���
	* @Title: isMerchant 
	* @Description: TODO����openid��ѯ�û����̹�ϵ
	* @param @param openid
	* @param @return    �趨�ļ� 
	* @return MerchantInfo    �������� 
	* @throws
	 */
	MerchantInfo isMerchant(int userid);
	
	/**
	 * 
	 * @param openid
	 * @return
	 *//** ���
	* @Title: getUserIdByOpenid 
	* @Description: TODO�����û�openid��ѯ�û�id
	* @param @param openid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */

	
	int getUserIdByOpenid(String openid);
	/**
	 * 
	 * @param openid
	 * @return
	 *//** ���
	* @Title: getUserByOpenid 
	* @Description: TODO-����openid��ѯ�û���Ϣ
	* @param @param openid
	* @param @return    �趨�ļ� 
	* @return WeixinUserInfo    �������� 
	* @throws
	 */
	WeixinUserInfo getUserByOpenid(String openid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getUserStatusById 
	* @Description: TODO(�����û�openid��ѯ�û�״̬
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	int getUserStatusById(String  openid);
/**
 * 
 * @param s
 * @return
 *//** ���
* @Title: addStoreupdate 
* @Description: TODO(��ӵ�����˱����ݣ�
* @param @param s
* @param @return    �趨�ļ� 
* @return int    �������� 
* @throws
 */
	int addStoreupdate(StoreInfoupdate s,String code);
	/**
	 * 
	 * @param c
	 * @return
	 *//** ���
	* @Title: addChef 
	* @Description: TODO��ӳ�ʦ
	* @param @param c
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	int addChef(ChefEntity c);
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
	 * @param g
	 * @return
	 *//** ���
	* @Title: addGreens 
	* @Description: TODO�����ɫ��
	* @param @param g
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	int addGreens(Greens g);
	
	/**
	 * 
	 * @param Userid
	 * @return
	 *//** ���
	* @Title: getStoreInfo 
	* @Description: TODO�����û�id��ѯ������Ϣ
	* @param @param Userid
	* @param @return    �趨�ļ� 
	* @return StoreInfo    �������� 
	* @throws
	 */
	StoreInfo getStoreInfo(int Userid);
	/**
	 * 
	 * @param Userid
	 * @return
	 *//** ���
	* @Title: getStoreInfo 
	* @Description: TODO����еĵ�����Ϣ
	* @param @param Userid
	* @param @return    �趨�ļ� 
	* @return StoreInfo    �������� 
	* @throws
	 */
	StoreInfoupdate getStoreupdate(int Userid);
	/**
	 * 
	 * @param id
	 * @return
	 *//** ���
	* @Title: getStoreInfoById 
	* @Description: TODO(���ݵ���id��ѯ������Ϣ
	* @param @param id
	* @param @return    �趨�ļ� 
	* @return StoreInfo    �������� 
	* @throws
	 */
	StoreInfo getStoreInfoById(int id);
	
	/**
	 * 
	 * @param storeid
	 * @return
	 *//** ���
	* @Title: getChefList 
	* @Description: TODO(���ݵ���id��ѯ��ʦ��Ϣ
	* @param @param storeid
	* @param @return    �趨�ļ� 
	* @return List<ChefEntity>    �������� 
	* @throws
	 */
	List<ChefEntity> getChefList(int storeid);
	/**
	 * 
	 * @param storeid
	 * @return
	 *//** ���
	* @Title: getGreensList 
	* @Description: TODO���ݵ���id��ѯ����Ϣ
	* @param @param storeid
	* @param @return    �趨�ļ� 
	* @return List<Greens>    �������� 
	* @throws
	 */
	List<Greens> getGreensList(int storeid);
	/**
	 * 
	 * @return
	 *//** ���
	* @Title: getRuleList 
	* @Description: TODO(��ѯ���ѹ���
	* @param @return    �趨�ļ� 
	* @return List<Storerule>    �������� 
	* @throws
	 */
	List<Storerule> getRuleList();
	/**
	 * 
	 * @param id
	 * @return
	 *//** ���
	* @Title: getGreensById 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param id
	* @param @return    �趨�ļ� 
	* @return Greens    �������� 
	* @throws
	 */
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
	
	/**
	 * 
	 * @param id
	 * @return
	 *//** ���
	* @Title: delChefById 
	* @Description: TODO ����idɾ����ʦ
	* @param @param id
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	int delChefById(int id);
	
	Jsconfig getJsConfig(int id);
	
	int isMerchantUser(int userid);
	
	int isChef(int userid);
	
	
	ChefEntity getChefByUserid(int userid);
	//�����û�id��ѯ����������Ϣ
	StoreInfo getStoreinfoByuserid(int userid) ;
	
	List<Greens> getGreensListByChefid(int chefid);
	
    
    List<StoretradingRecord> getStoretrading(String timec,int storeid);
	
}