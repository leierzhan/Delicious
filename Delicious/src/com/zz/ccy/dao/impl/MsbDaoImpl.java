package com.zz.ccy.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zz.ccy.dao.MsbDao;
import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserMsbRecord;
/**
 * @ClassName: UserDaoImpl
 * @Description:
 * @author: 
 * @date: 2017年9月7日 上午9:22:06
 */
@Component
public class MsbDaoImpl implements MsbDao{
	private static Logger logger = Logger.getLogger(MsbDaoImpl.class); 
	@Autowired
	private SqlSessionFactory sessionFactory;
	
	public SqlSession getSession(){
	     return sessionFactory.openSession();
	}
	@Override
	public List<UserMsbRecord> getTaskAward(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserMsbMapper.getUsertaskaward",userid);
	}
	@Override
	public List<UserMsbRecord> getDeal(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserMsbMapper.getUserdeal",userid);
	}
	@Override
	public List<UserMsbRecord> getConsume(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserMsbMapper.getUserconsume",userid);

	}
	@Override
	public List<StoreMsb> getStoreMsbList(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserMsbMapper.getStoreMsb",userid);

	}
	@Override
	public List<StoreMsb> getMsbList(int userid) {
		return getSession().selectList("com.zz.ccy.mapping.WeixinUserMsbMapper.getMsb",userid);
	}
	@Override
	public int addMsbDeal(UserMsbRecord um) {
		return getSession().insert("com.zz.ccy.mapping.WeixinUserMsbMapper.addDealMsb",um);
	}
	
}
