package com.zz.ccy.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zz.ccy.dao.UserDao;
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
import com.zz.ccy.util.AdvancedUtil;
import com.zz.ccy.util.TokenThread;
/**
 * @ClassName: UserDaoImpl
 * @Description:
 * @author: 
 * @date: 2017年9月7日 上午9:22:06
 */
@Component
public class UserDaoImpl implements UserDao{
	private static Logger logger = Logger.getLogger(UserDaoImpl.class); 
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public SqlSession getSession(){
	     return sessionFactory.openSession();
	}
	//如果有新关注者，则关注后直接把用户信息保存到数据库
	@Override
	public int saveOrUpdateDyhEntity(WeixinUserInfo t) {
	 //先查看数据库中是否已经有此用户
	
     int count=getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.checkUserInfoExistByOpenId",t.getOpenId());		
     int a=0;
     if(count>0){
    	 //存在则更新
    	 getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.updateUserInfoExistByOpenId",t);
    
     }else{
    	 //不存在直接插入
    	  getSession().insert("com.zz.ccy.mapping.WeixinUserInfoMapper.insertUserInfo",t);
    	  a=t.getId();
        
        	 logger.info("插入成功");
        	 

     }
     
     return a;
	}

	//获取到关注者列表把关注者列表中的openId都保存到数据库
	@Override
	public void saveUserList(List<String> userList) {
      for(int i=0;i<userList.size();i++){
    	  WeixinUserInfo weixinUserInfo=new WeixinUserInfo();
    	  weixinUserInfo.setOpenId(userList.get(i));
          int result=getSession().insert("com.zz.ccy.mapping.WeixinUserInfoMapper.insertDyhUserInfo",weixinUserInfo);
          if(result>0){
        	  logger.info("插入成功");
          }
      }		
	}
	//获取nickname为null的用户列表
	@Override
	public List<String> getUserListNicknameIsNull() {
		List<String> lists=getSession().selectList("com.zz.ccy.mapping.WeixinUserInfoMapper.getDyhUserListNicknameIsNull");
		return lists;
	}
	//更新关注着信息
	@Override
	public void updateUserInfo(WeixinUserInfo info) {
	   int a=getSession().update("com.zz.ccy.mapping.WeixinUserInfoMapper.updateDyhUserInfo",info);
	   if(a>0){
		   logger.info("用户基本信息更新成功");
	   }else{
		   logger.info("用户基本信息更新失败");
	   }
	}
	/**
	 * 保存或者更新订阅号组的数据
	 */
	@Override
	public void saveOrUpdateGroups(List<WeixinGroup> groups) {
		WeixinGroup group=null;
      for(int i=0;i<groups.size();i++){
    	  group=groups.get(i);
    	  int result=getSession().insert("com.zz.ccy.mapping.WeixinGroupMapper.insertDyhGroup",group);
    	  if(result>0){
    		  logger.info("用户分组插入成功");
    	  }
      }		
	}
	/**
	 * 获取openid集合
	 */
	@Override
	public List<String> getOpenIds() {
	  return getSession().selectList("com.zz.ccy.mapping.WeixinUserInfoMapper.getDyhOpenIdList");
	}
	@Override
	public WeixinUserList getAllUsers() {
		AdvancedUtil advancedUtil=new AdvancedUtil();
		String accessToken=TokenThread.accessToken.getAccessToken();
//		String accessToken="JlK_-ZzMTZ9ZDXuoDoU6QAnARVXj8SCEV9EDXN6CDj2HiiGcuuN9LL6dCB5WbqaId3mr_vz6kWtAfyQaMPqo1Jpnu0zEr8QLZSbIDhrdLi8WTYgAEAZWB";
		WeixinUserList userList=advancedUtil.getUserList(accessToken,null);
		return userList;
	}
	@Override
	public void saveUserOpenId(TbUserOpenId tbUserOpenId){
	   List<TbUserOpenId> tbUserOpenIdList=getSession().selectList("com.zz.ccy.mapping.TbUserOpenIdMapper.getDyhTbUserOpenId",tbUserOpenId.getOpenId());
	   if(tbUserOpenIdList.size()==0){
		   //保存
		   int result=getSession().insert("com.zz.ccy.mapping.TbUserOpenIdMapper.insertDyhTbUserOpenId",tbUserOpenId);
		   if(result>0){
			   logger.info("用户的openid插入成功");
		   }
	   }else{
	   }
	}
	@Override
	public void deleteAllUser() {
		int result=getSession().delete("com.zz.ccy.mapping.TbUserOpenIdMapper.deleteDyhAllTbUserOpenId");
		if(result>0){
			logger.info("所有用户的openid删除成功");
		}
	} 
	//批量插入
	public void insertTbUserOpenIdBatch(List<TbUserOpenId> userOpenIdList) {
		int result=getSession().insert("com.zz.ccy.mapping.TbUserOpenIdMapper.insertDyhTbUserOpenIdBatch",userOpenIdList);
		if(result>0){
			logger.info("批量插入用户openid成功");
		}
	}
	@Override
	public WeixinUserInfo getUserInfoByOpenId(String openId){
//		AdvancedUtil advancedUtil=new AdvancedUtil();
////		String accessToken=TokenThread.accessToken.getAccessToken();
//		String accessToken="tI2OwoS5s-CNFMi3PNp94qDexX86QXQz0ealIMe9sauNp4EDk4_5KEMGDdyxDwHtABVVCEcxWwnw6Pp99qZqBr0gy8KG1s7cVFJZ0AaVyYySi0RqPYgNWADyD4-PFyEJBJXgAFAEMA";
//		return advancedUtil.getUserInfo(accessToken,openId);
		System.out.println(openId);
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserInfoById",openId);
	}
	@Override
	public void insertUserInfoBatch(
		List<WeixinUserInfo> weixinUserInfoList) {
		int result=getSession().insert("com.zz.ccy.mapping.WeixinUserInfoMapper.insertDyhUserInfoBatch",weixinUserInfoList);
		if(result>0){
			logger.info("批量插入用户基本信息成功");
		}
	}
	@Override
	public void deleteAllUserInfo() {
		int result=getSession().delete("com.zz.ccy.mapping.WeixinUserInfoMapper.deleteDyhAllWeiXinUserInfo");
		if(result>0){
			logger.info("删除所有用户基本信息成功");
		}
	}
	@Override
	public String getUserSexByPhoneNum(Map<String,String> map) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserSexByPhoneNum",map);
	}
	@Override
	public UserCorepage getUserCorepage(String openid) {
		UserCorepage ucp=getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.select_usercorepage", openid);
		return ucp;
	}
	@Override
	public WeixinUserInfo getUserInfo(int userid) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserInfo", userid);
	}
	@Override
	public UserHeadinfo getHeadinfoByTel(String tel) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserHeadinfo", tel);
	}
	/**
	 * 文章列表
	 */
	@Override
	public List<WzInfo> getWzList(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserWzMapper.getwzlist",userid);
	}
	@Override
	public List<CollectInfo> getUserCollect(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinStoreMapper.getCollectlist",userid);
	}
	@Override
	public int updateCollect(Map<String, Integer> map) {
		int storeid=map.get("storeid");
		int i=0;
		if(storeid==0){
			i=getSession().update("com.zz.ccy.mapping.WeixinStoreMapper.update_chef_collect",map);
		}else{
			i=getSession().update("com.zz.ccy.mapping.WeixinStoreMapper.update_store_collect",map);

		}
		return i;
	}
	@Override
	public List<CollectInfo> getUserHistory(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinStoreMapper.getHistorylist",userid);
	}
	@Override
	public int updateAllHistory(int userid) {
		return getSession().update("com.zz.ccy.mapping.WeixinStoreMapper.updateHistory",userid);
	}
	@Override
	public int getUserIdByOpenId(String openId) {
		return getSession().selectOne("com.zz.ccy.mapping.ArticleInfoMapper.getUserIdByOpenId",openId);
	}
	@Override
	public int updateNoticeStatus(int id,int status) {
		Map<String,Integer> m =new HashMap<String,Integer>();
		m.put("id",id);
		m.put("status",status);
		return getSession().update("com.zz.ccy.mapping.WeixinNoticeMapper.updateNotice" ,m);
	}
	@Override
	public int updateNoticeByUserid(int userid) {
		return getSession().update("com.zz.ccy.mapping.WeixinNoticeMapper.updateNoticeAll" ,userid);
	}
	@Override
	public List<UserNotice> getUserNoticeList(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinNoticeMapper.getNoticelist" ,userid);
	}
	
	@Override
	public WeixinUserInfo getUserInfoById(Integer userId) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserInfoById",userId);
	}
	/**
	 * 暂时没有用
	 */
	@Override
	public void initUserStatus(int userid,String ercode) {
	        	 Map<String, Object> m=new HashMap<String, Object>();
	        	 m.put("userid", userid);
	        	 m.put("code", ercode);
	        	 getSession().insert("com.zz.ccy.mapping.WeixinUserInfoMapper.addUserCore",m);
	        	 logger.info("插入成功");
	}
	@Override
	public List<Msb> getUserMsbList(Msb msb) {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userid", msb.getUserid());
		map.put("code", msb.getCode());
		System.out.println(msb.getUserid()+"----"+msb.getCode());
		return getSession().selectList("com.zz.ccy.mapping.MsbMapper.getUserMsblist",map);
	}
	@Override
	public StoreInfoupdate getStoreinfoByCode(String code) {
		// TODO Auto-generated method stub
		return getSession().selectOne("com.zz.ccy.mapping.WeixinStoreupdateMapper.getStoreByCode",code);
	}


}
