 package com.zz.ccy.service;

import java.util.List;
import java.util.Map;

import com.zz.ccy.entity.CollectInfo;
import com.zz.ccy.entity.TbUserOpenId;
import com.zz.ccy.entity.UserCorepage;
import com.zz.ccy.entity.UserHeadinfo;
import com.zz.ccy.entity.UserNotice;
import com.zz.ccy.entity.WeixinGroup;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WeixinUserList;
import com.zz.ccy.entity.WzInfo;
/**
 * @author lez
 *
 */
public interface UserService {
	//如果有新关注者，则关注后直接把用户信息保存到数据库
	public int saveOrUpdateEntity(WeixinUserInfo t);
	
	//获取到关注者列表把关注者列表中的openId都保存到数据库
	public void saveUserList(List<String> userList);
	//个人中心页面信息
	/**
	 * 
	 * @param openid
	 * @return
	 *//** 李飞
	* @Title: getUserCorepage 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param openid
	* @param @return    设定文件 
	* @return UserCorepage    返回类型 
	* @throws
	 */
	public UserCorepage getUserCorepage(String openid);
	/**\
	 *
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getUserInfo 
	* @Description: TODO(根据user id查询用户信息) 
	* @param @param userid
	* @param @return    设定文件 
	* @return WeixinUserInfo    返回类型 
	* @throws
	 */
	public WeixinUserInfo getUserInfo(int userid);
	
	//获取nickname为null的用户列表
	public List<String> getUserListNicknameIsNull();
	/**
	 * 
	 * @param info
	 *//** 李飞
	* @Title: updateUserInfo 
	* @Description: TODO(再用) 
	* @param @param info    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	//更新关注着信息
	public void updateUserInfo(WeixinUserInfo info);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getWzList 
	* @Description: TODO(根据用户id查询用户文章 
	* @param @param userid
	* @param @return    设定文件 
	* @return List<WzInfo>    返回类型 
	* @throws
	 */
	public List<WzInfo> getWzList(int userid);
	
	
	public UserHeadinfo getHeadinfoByTel(String tel) ;
	/**
	 * 保存或者更新数据库中订阅号组的数据
	 * 2017年04月24日上午9:56:14
	 * lez
	 */
	public void saveOrUpdateGroups(List<WeixinGroup> groups);
	
	/**
	 * 2015年8月4日下午2:34:33
	 * lez
	 */
	public List<String> getOpenIds();
	
	//获取所有用户信息
	public WeixinUserList getAllUsers();
	
	//保存用户的openId
	public void saveUserOpenId(TbUserOpenId tbUserOpenId);

	//清空TbUserOpenId
	public void deleteAllUser();
	//批量插入TbUserOpenId
	public void insertTbUserOpenIdBatch(List<TbUserOpenId> userOpenIdList);
	//根据用户OpenId获取用户基本信息
	public WeixinUserInfo getUserInfoByOpenId(String openId);
	//批量存储WeixinUserInfo
	public void insertUserInfoBatch(List<WeixinUserInfo> weixinUserInfoList);
	//清空WeiXinUserInfo
	public void deleteAllUserInfo();
	/**
	 * lez
	 * 通过用户手机号和预约号判断预约号和手机号是否匹配 获取用户性别
	 * param:phoneNum 用户手机号
	 * 2017-07-25 10:19
	 */
	public String getUserSexByPhoneNum(Map<String,String> map);
    //根据openId获取用户id
	public int getUserIdByOpenId(String openId);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getUserCollect 
	* @Description: TODO根据用户查询用户收藏
	* @param @param userid
	* @param @return    设定文件 
	* @return List<CollectInfo>    返回类型 
	* @throws
	 */
	public List<CollectInfo> getUserCollect(int userid);
	/**
	 * 
	 * @param map
	 * @return
	 *//** 李飞
	* @Title: updateCollect 
	* @Description: TODO(用户收藏更新) 
	* @param @param map
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateCollect(int userid,int storeid,int chefid,int status);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getUserHistory 
	* @Description: TODO(查询用户足迹列表
	* ) 
	* @param @param userid
	* @param @return    设定文件 
	* @return List<CollectInfo>    返回类型 
	* @throws
	 */
	public List<CollectInfo> getUserHistory(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: updateAllHistory 
	* @Description: TODO(更新所有足迹) 
	* @param @param userid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	
	public int updateAllHistory(int userid);
	
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getUserNoticeList 
	* @Description: TODO(根据用户id查询用户) 
	* @param @param userid
	* @param @return    设定文件 
	* @return List<UserNotice>    返回类型 
	* @throws
	 */
	public List<UserNotice> getUserNoticeList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: updateNoticeStatus 
	* @Description: TODO(更新) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateNoticeStatus(int id,int status);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: updateNoticeByUserid 
	* @Description: TODO(根据userid更新状态) 
	* @param @param userid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int updateNoticeByUserid(int userid);
	/**
	 * 
	 * @param userid
	 *//** 李飞
	* @Title: initUserStatus 
	* @Description: TODO初始化
	* @param @param userid    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	public void initUserStatus(int userid,String ercode);
	/**
	 * 
	 * @return
	 *//** 李飞
	* @Title: getErcode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public  String getErcode();
    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
	public WeixinUserInfo getUserInfoById(Integer userId);

}
