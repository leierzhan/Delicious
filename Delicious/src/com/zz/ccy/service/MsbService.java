 package com.zz.ccy.service;

import java.util.List;

import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserMsbRecord;

/**
 * @author lez
 *
 */
public interface MsbService {
	
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getTaskAward 
	* @Description: TODO查询任务奖励记录
	* @param @param userid
	* @param @return    设定文件 
	* @return List<UserMsbRecord>    返回类型 
	* @throws
	 */
	public List<UserMsbRecord> getTaskAward(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getDeal 
	* @Description: TODO用户转装记录
	* @param @param userid
	* @param @return    设定文件 
	* @return List<UserMsbRecord>    返回类型 
	* @throws
	 */
	public List<UserMsbRecord> getDeal(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getConsume 
	* @Description: TODO用户消费记录
	* @param @param userid
	* @param @return    设定文件 
	* @return List<UserMsbRecord>    返回类型 
	* @throws
	 */
	public List<UserMsbRecord> getConsume(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getStoreMsbList 
	* @Description: TODO(查询指定店铺的美食币) 
	* @param @param userid
	* @param @return    设定文件 
	* @return List<StoreMsb>    返回类型 
	* @throws
	 */
	public List<StoreMsb> getStoreMsbList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getMsbList 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param userid
	* @param @return    设定文件 
	* @return List<StoreMsb>    返回类型 
	* @throws
	 */
	public List<StoreMsb> getMsbList(int userid);
	/**
	 * 
	 * @param um
	 * @return
	 *//** 李飞
	* @Title: addDsbDeal 
	* @Description: TODO(添加赠送记录) 
	* @param @param um
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int addMsbDeal(UserMsbRecord um) ;

}
