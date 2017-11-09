/*
 * @author David
 * @date 2017-04-01
 */
package com.zz.ccy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.MsbDao;
import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserMsbRecord;
import com.zz.ccy.service.MsbService;
@Service
@Transactional
public class MsbServiceImpl implements MsbService{
	@Autowired
	private MsbDao msbDao;

	@Override
	public List<UserMsbRecord> getTaskAward(int userid) {
		// TODO Auto-generated method stub
		return msbDao.getTaskAward(userid);
	}

	@Override
	public List<UserMsbRecord> getDeal(int userid) {
		return msbDao.getDeal(userid);
	}

	@Override
	public List<UserMsbRecord> getConsume(int userid) {
		// TODO Auto-generated method stub
		return msbDao.getConsume(userid);
	}

	@Override
	public List<StoreMsb> getStoreMsbList(int userid) {
		// TODO Auto-generated method stub
		return msbDao.getStoreMsbList(userid);
	}

	@Override
	public List<StoreMsb> getMsbList(int userid) {
		// TODO Auto-generated method stub
		return msbDao.getMsbList(userid);
	}

	@Override
	public int addMsbDeal(UserMsbRecord um) {
		return msbDao.addMsbDeal(um);
	}
	
  }
 