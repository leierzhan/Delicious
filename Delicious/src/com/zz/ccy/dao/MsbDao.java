package com.zz.ccy.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserMsbRecord;
/**
 * 
* @ClassName: MsbDao 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 李飞
* @date 2017年9月13日 下午5:06:30 
*
 */
@Component
public interface MsbDao {
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
	* @Description: TODO(根据用户的id查询店铺枚士币) 
	* @param @param userid
	* @param @return    设定文件 
	* @return StoreMsb    返回类型 
	* @throws
	 */
	public List<StoreMsb> getStoreMsbList(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getStoreMsbList 
	* @Description: TODO(根据用户的id查询枚士币) 
	* @param @param userid
	* @param @return    设定文件 
	* @return StoreMsb    返回类型 
	* @throws
	 */
	public List<StoreMsb> getMsbList(int userid);
	/**
	 * 
	 * @param um
	 * @return
	 *//** 李飞
	* @Title: addDsbDeal 
	* @Description: TODO(添加用户赠送记录) 
	* @param @param um
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int addMsbDeal(UserMsbRecord um);
	
	
	
	
	
}