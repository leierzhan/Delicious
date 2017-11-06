package com.zz.ccy.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserMsbRecord;
/**
 * 
* @ClassName: MsbDao 
* @Description: TODO(������һ�仰��������������) 
* @author ���
* @date 2017��9��13�� ����5:06:30 
*
 */
@Component
public interface MsbDao {
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getTaskAward 
	* @Description: TODO��ѯ��������¼
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<UserMsbRecord>    �������� 
	* @throws
	 */
	public List<UserMsbRecord> getTaskAward(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getDeal 
	* @Description: TODO�û�תװ��¼
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<UserMsbRecord>    �������� 
	* @throws
	 */
	public List<UserMsbRecord> getDeal(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getConsume 
	* @Description: TODO�û����Ѽ�¼
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<UserMsbRecord>    �������� 
	* @throws
	 */
	public List<UserMsbRecord> getConsume(int userid);
	
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getStoreMsbList 
	* @Description: TODO(�����û���id��ѯ����öʿ��) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return StoreMsb    �������� 
	* @throws
	 */
	public List<StoreMsb> getStoreMsbList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getStoreMsbList 
	* @Description: TODO(�����û���id��ѯöʿ��) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return StoreMsb    �������� 
	* @throws
	 */
	public List<StoreMsb> getMsbList(int userid);
	/**
	 * 
	 * @param um
	 * @return
	 *//** ���
	* @Title: addDsbDeal 
	* @Description: TODO(����û����ͼ�¼) 
	* @param @param um
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int addMsbDeal(UserMsbRecord um);
	
	
	
	
	
}