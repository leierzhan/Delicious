package com.zz.ccy.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zz.ccy.dao.MerchantDao;
import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.UserCorepage;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;
/**
 * 
* @ClassName: MerchantDaoImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 李飞
* @date 2017年10月10日 上午11:05:43 
*
 */
@Component
public class MerchantDaoImpl implements MerchantDao{
	private static Logger logger = Logger.getLogger(MerchantDaoImpl.class); 
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public SqlSession getSession(){
	     return sessionFactory.openSession();
	}

	@Override
	public MerchantInfo isMerchant(int userid) {
		System.out.println("***************"+userid);
		MerchantInfo m= getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.isMerchant",userid);
		//System.out.println("_______---------------"+m.getStoreid());
		return m;
	}

	@Override
	public int getUserIdByOpenid(String openid) {
		UserCorepage ucp=getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.select_usercorepage", openid);
		return ucp.getUserid();
	}

	@Override
	public int addStoreupdate(StoreInfoupdate s) {
		System.out.println("进入添加");
		int i=getSession().insert("com.zz.ccy.mapping.WeixinStoreupdateMapper.addStoreupdate",s);
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("userid", s.getUserid());
		m.put("storeid",s.getId());
		m.put("timec",getNowDateTime());
		//添加和更新用户和店铺关系
		getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.addMerchant",m);
		System.out.println("添加结果："+s.getId());
		return s.getId();
	}

	@Override
	public int addChef(ChefEntity c) {
		return getSession().insert("com.zz.ccy.mapping.ChefInfoMapper.addChef",c);
	}

	@Override
	public int addGreens(Greens g) {
		return getSession().insert("com.zz.ccy.mapping.GreensInfoMapper.addGreens",g);
	}

	@Override
	public StoreInfo getStoreInfo(int userid) {
		
		return  getSession().selectOne("com.zz.ccy.mapping.WeixinStoreMapper.getMerStoreinfo",userid);
	}

	@Override
	public List<ChefEntity> getChefList(int storeid) {
		
		return  getSession().selectList("com.zz.ccy.mapping.ChefInfoMapper.getChefByStoreId",storeid);
	}

	@Override
	public List<Greens> getGreensList(int storeid) {
		// TODO Auto-generated method stub
		return getSession().selectList("com.zz.ccy.mapping.GreensInfoMapper.getGreensByStoreId",storeid);
	}
	@Override
	public List<Storerule> getRuleList() {
		return getSession().selectList("com.zz.ccy.mapping.WeixinStoreupdateMapper.getStoreruleAll");
	}
	@Override
	public StoreInfo getStoreInfoById(int id) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinStoreMapper.getStoreinfo",id);
	}

	@Override
	public int getUserStatusById(String openid) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserStatus",openid);
	}

	@Override
	public WeixinUserInfo getUserByOpenid(String openid) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.getUserinfoByopenid",openid);
	}

	@Override
	public StoreInfoupdate getStoreupdate(int userid) {
		return  getSession().selectOne("com.zz.ccy.mapping.WeixinStoreupdateMapper.getStoreUpdateinfo",userid);
	}

	@Override
	public ChefEntity getChefById(int id) {
		return  getSession().selectOne("com.zz.ccy.mapping.ChefInfoMapper.getChefById",id);
	}

	@Override
	public int updateChefinfo(ChefEntity c) {
		// TODO Auto-generated method stub
		return getSession().update("com.zz.ccy.mapping.ChefInfoMapper.updateChef",c);
	}

	@Override
	public int delChefById(int id) {
	   return getSession().delete("com.zz.ccy.mapping.ChefInfoMapper.delChefById",id);
	   
	}
	   private String getNowDateTime(){
		   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   return df.format(new Date());
	   }

	@Override
	public Greens getGreensById(int id) {
		// TODO Auto-generated method stub
		return getSession().selectOne("com.zz.ccy.mapping.GreensInfoMapper.getGreensById", id);
	}

	@Override
	public int updateGreensById(Greens g) {
		// TODO Auto-generated method stub
		return getSession().update("com.zz.ccy.mapping.GreensInfoMapper.updateGreens",g);
	}

	@Override
	public int delGreens(int id) {
		// TODO Auto-generated method stub
		return getSession().delete("com.zz.ccy.mapping.GreensInfoMapper.delGreens",id);
	}

	@Override
	public Jsconfig getJsConfig(int id) {
		return getSession().selectOne("com.zz.ccy.mapping.WeixinMapper.getJsconfig",id);
	}

	@Override
	public int isMerchantUser(int userid) {
		System.out.println("************userid="+userid+"**********");
	  Integer number= this.getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.isMerchantUser", userid);  
		System.out.println("************number="+number+"**********"); 
	  return number;
		//return getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.",userid);
	}

	@Override
	public int isChef(int userid) {
		//com.zz.ccy.mapping.ChefInfoMapper
		System.out.println("************userid="+userid+"**********");
		Integer number= this.getSession().selectOne("com.zz.ccy.mapping.ChefInfoMapper.ischef", userid);  
	    System.out.println("************return="+number+"**********"); 
		  return number;
	}

	@Override
	public StoreInfo getStoreinfoByuserid(int userid) {
		
		return this.getSession().selectOne("com.zz.ccy.mapping.StoreInfoMapper.getStoreByUserid",userid);
	}

	@Override
	public ChefEntity getChefByUserid(int userid) {
		return this.getSession().selectOne("com.zz.ccy.mapping.ChefInfoMapper.getChefByUserId",userid);
	}
	   
}
