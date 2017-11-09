/*
 * @author David
 * @date 2017-04-01
 */
package com.zz.ccy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.UserDao;
import com.zz.ccy.entity.CollectInfo;
import com.zz.ccy.entity.TbUserOpenId;
import com.zz.ccy.entity.UserCorepage;
import com.zz.ccy.entity.UserHeadinfo;
import com.zz.ccy.entity.UserNotice;
import com.zz.ccy.entity.WeixinGroup;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WeixinUserList;
import com.zz.ccy.entity.WzInfo;
import com.zz.ccy.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	//如果有新关注者，则关注后直接把用户信息保存到数据库
	@Override
	public int saveOrUpdateEntity(WeixinUserInfo t){
		int i=0;
		if(t.getOpenId()!=null){
			i=userDao.saveOrUpdateDyhEntity(t);
		}
		return i;
	}
	//获取到关注者列表把关注者列表中的openId都保存到数据库
	@Override
	public void saveUserList(List<String> userList) {
      userDao.saveUserList(userList);		
	}
	//获取nickname为null的用户列表
	@Override
	public List<String> getUserListNicknameIsNull() {
		return userDao.getUserListNicknameIsNull();
	}
	/**
	 * 李飞使用
	 */
	//更新关注着信息
	@Override
	public void updateUserInfo(WeixinUserInfo info) {
		userDao.updateUserInfo(info);
	}
	//保存或者更新组的数据
	@Override
	public void saveOrUpdateGroups(List<WeixinGroup> groups) {
       userDao.saveOrUpdateGroups(groups);
	}
	/**
	 * 获取openid集合
	 */
	@Override
	public List<String> getOpenIds() {
		return userDao.getOpenIds();
	}
	@Override
	public WeixinUserList getAllUsers() {
		return userDao.getAllUsers();
	}
	@Override
	public void saveUserOpenId(TbUserOpenId tbUserOpenId) {
		userDao.saveUserOpenId(tbUserOpenId);
	}
	@Override
	public void deleteAllUser() {
		userDao.deleteAllUser();
	}
	@Override
	public void insertTbUserOpenIdBatch(List<TbUserOpenId> userOpenIdList) {
		userDao.insertTbUserOpenIdBatch(userOpenIdList); 
	}
	@Override
	public WeixinUserInfo getUserInfoByOpenId(String openId) {
		return userDao.getUserInfoByOpenId(openId);
	}
	@Override
	public void deleteAllUserInfo() {
		userDao.deleteAllUserInfo();
	}
	@Override
	public void insertUserInfoBatch(List<WeixinUserInfo> weixinUserInfoList) {
		userDao.insertUserInfoBatch(weixinUserInfoList);
	}
	@Override
	public String getUserSexByPhoneNum(Map<String,String> map) {
		return userDao.getUserSexByPhoneNum(map);
	}
	/*8
	 * (non-Javadoc)
	 * @see com.zz.ccy.service.UserService#getUserCorepage(java.lang.String)
	 */
	@Override
	public UserCorepage getUserCorepage(String openid) {

		return userDao.getUserCorepage(openid);
	}
	/*8
	 * (non-Javadoc)
	 * @see com.zz.ccy.service.UserService#getUserInfo(int)
	 */
	@Override
	public WeixinUserInfo getUserInfo(int userid) {
		return userDao.getUserInfo(userid);
	}
	@Override
	public UserHeadinfo getHeadinfoByTel(String tel) {
		// TODO Auto-generated method stub
		return userDao.getHeadinfoByTel(tel);
	}
	@Override
	public List<WzInfo> getWzList(int userid) {
		return userDao.getWzList(userid);
	}
	@Override
	public List<CollectInfo> getUserCollect(int userid) {
		return userDao.getUserCollect(userid);
	}
	@Override
	public int updateCollect(int userid,int storeid,int chefid,int status) {
		Map<String,Integer> map=new HashMap<String, Integer>();
		map.put("userid",userid);
		map.put("storeid",storeid);
		map.put("chefid",chefid);
		map.put("status",status);
		return userDao.updateCollect(map);
	}
	@Override
	public List<CollectInfo> getUserHistory(int userid) {
		return userDao.getUserHistory(userid);
	}
	
	@Override
	public int updateAllHistory(int userid) {
		// TODO Auto-generated method stub
		return userDao.updateAllHistory(userid);
	}
	@Override
	public List<UserNotice> getUserNoticeList(int userid) {
		return userDao.getUserNoticeList(userid);
	}
	@Override
	public int updateNoticeStatus(int id, int status) {
		// TODO Auto-generated method stub
		return userDao.updateNoticeStatus(id, status);
	}
	@Override
	public int updateNoticeByUserid(int userid) {
		return userDao.updateNoticeByUserid(userid);
	}
	@Override
	public int getUserIdByOpenId(String openId) {
		return userDao.getUserIdByOpenId(openId);
	}
	@Override
	public void initUserStatus(int userid,String ercode) {
		
		userDao.initUserStatus(userid ,ercode);
		
	}
	@Override
	//二维码编号
	public  String getErcode() {
	    //随机字符串的随机字符库
	    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuffer sb = new StringBuffer();
	    int len = KeyString.length();
	    for (int i = 0; i < 6; i++) {
	       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    String  s=String.valueOf(System.currentTimeMillis())+sb;
	    
	    return s;
	}
	
	@Override
	public WeixinUserInfo getUserInfoById(Integer userId) {
		return userDao.getUserInfoById(userId);
	}

  }
 