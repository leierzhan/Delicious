package com.zz.ccy.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.CollectInfo;
import com.zz.ccy.entity.Msb;
import com.zz.ccy.entity.TbUserOpenId;
import com.zz.ccy.entity.UserCorepage;
import com.zz.ccy.entity.UserHeadinfo;
import com.zz.ccy.entity.UserNotice;
import com.zz.ccy.entity.WeixinGroup;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WeixinUserList;
import com.zz.ccy.entity.WzInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;

/**
 * 
 * @ClassName: UserDao
 * @Description: TODO(������һ�仰��������������)
 * @author:
 * @date: 2017��9��7�� ����9:31:25
 */
@Component
public interface UserDao {
	// ������¹�ע�ߣ����ע��ֱ�Ӱ��û���Ϣ���浽���ݿ�
	public int saveOrUpdateDyhEntity(WeixinUserInfo t);

	// ��ȡ����ע���б�ѹ�ע���б��е�openId�����浽���ݿ�
	public void saveUserList(List<String> userList);

	// ��������ҳ����Ϣ
	/**
	 * lifei
	 * 
	 * @param openid
	 * @return
	 */
	public UserCorepage getUserCorepage(String openid);

	/**
	 * 
	 * @param userid
	 * @return
	 */
	/**
	 * ���
	 * 
	 * @Title: getUserInfo
	 * @Description: TODO(��ȡ�û���Ϣ����userid)
	 * @param @param userid
	 * @param @return �趨�ļ�
	 * @return WeixinUserInfo ��������
	 * @throws
	 */
	public WeixinUserInfo getUserInfo(int userid);

	/**
	 * 
	 * @param info
	 */
	/**
	 * ���
	 * 
	 * @Title: updateUserInfo
	 * @Description: TODO(������һ�仰�����������������)
	 * @param @param info �趨�ļ�
	 * @return void ��������
	 * @throws
	 */
	// ���¹�ע����Ϣ
	public void updateUserInfo(WeixinUserInfo info);
	
	
	public UserHeadinfo getHeadinfoByTel(String tel);

	// ��ȡnicknameΪnull���û��б�
	public List<String> getUserListNicknameIsNull();

	/**
	 * ������߸������ݿ��ж��ĺ�������� 2017��04��24������9:56:14 lez
	 */
	public void saveOrUpdateGroups(List<WeixinGroup> groups);

	/**
	 * 2015��8��4������2:34:33 lez
	 */
	public List<String> getOpenIds();

	// ��ȡ�����û���Ϣ
	public WeixinUserList getAllUsers();

	// �����û���openId
	public void saveUserOpenId(TbUserOpenId tbUserOpenId);

	// ���TbUserOpenId
	public void deleteAllUser();

	// ��������TbUserOpenId
	public void insertTbUserOpenIdBatch(List<TbUserOpenId> userOpenIdList);

	// �����û�OpenId��ȡ�û�������Ϣ
	public WeixinUserInfo getUserInfoByOpenId(String openId);

	// �����洢WeixinUserInfo
	public void insertUserInfoBatch(List<WeixinUserInfo> weixinUserInfoList);

	// ���WeiXinUserInfo
	public void deleteAllUserInfo();

	/**
	 * lez ͨ���û��ֻ��Ż�ȡ�û��Ա� param:phoneNum �û��ֻ��� 2017-07-25 10:19
	 */
	public String getUserSexByPhoneNum(Map<String, String> map);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getWzList 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<WzInfo>    �������� 
	* @throws
	 */
	public List<WzInfo> getWzList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getUserCollect 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<CollectInfo>    �������� 
	* @throws
	 */
	public List<CollectInfo> getUserCollect(int userid);
	/**
	 * 
	 * @param map
	 * @return
	 *//** ���
	* @Title: updateCollect 
	* @Description: TODO(�����ղ�״̬) 
	* @param @param map
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int updateCollect(Map<String,Integer> map);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getUserHistory 
	* @Description: TODO(��ѯ�û��㼣�б�
	* ) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<CollectInfo>    �������� 
	* @throws
	 */
	public List<CollectInfo> getUserHistory(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: updateAllHistory 
	* @Description: TODO(���������㼣) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int updateAllHistory(int userid);
	
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: getUserNoticeList 
	* @Description: TODO(�����û�id��ѯ�û�) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return List<UserNotice>    �������� 
	* @throws
	 */
	public List<UserNotice> getUserNoticeList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: updateNoticeStatus 
	* @Description: TODO(����) 
	* @param @param id
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int updateNoticeStatus(int id,int status);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** ���
	* @Title: updateNoticeByUserid 
	* @Description: TODO(����userid����״̬) 
	* @param @param userid
	* @param @return    �趨�ļ� 
	* @return int    �������� 
	* @throws
	 */
	public int updateNoticeByUserid(int userid);
	//����openId��ȡ�û�id
	public int getUserIdByOpenId(String openId);
	
	public void initUserStatus(int userid,String ercode);
    //�����û�id��ȡ�û���Ϣ
	public WeixinUserInfo getUserInfoById(Integer userId);
	
	public List<Msb> getUserMsbList(Msb msb);
	
	//����code��ѯ������Ϣ
	public StoreInfoupdate getStoreinfoByCode(String code);
}
