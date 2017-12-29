package com.zz.ccy.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.StoretradingRecord;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;

/**
 * 
* @ClassName: MerchantDao 
* @Description: TODO(商户) 
* @author 李飞
* @date 2017年10月10日 上午10:01:27 
*
*/
@Component
public interface MerchantDao {
	
	
	/**
	 * 
	 * 
	 * @param openid
	 * @return
	 *//** 李飞
	* @Title: isMerchant 
	* @Description: TODO根据openid查询用户店铺关系
	* @param @param openid
	* @param @return    设定文件 
	* @return MerchantInfo    返回类型 
	* @throws
	 */
	MerchantInfo isMerchant(int userid);
	
	/**
	 * 
	 * @param openid
	 * @return
	 *//** 李飞
	* @Title: getUserIdByOpenid 
	* @Description: TODO根据用户openid查询用户id
	* @param @param openid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */

	
	int getUserIdByOpenid(String openid);
	/**
	 * 
	 * @param openid
	 * @return
	 *//** 李飞
	* @Title: getUserByOpenid 
	* @Description: TODO-根据openid查询用户信息
	* @param @param openid
	* @param @return    设定文件 
	* @return WeixinUserInfo    返回类型 
	* @throws
	 */
	WeixinUserInfo getUserByOpenid(String openid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: getUserStatusById 
	* @Description: TODO(根据用户openid查询用户状态
	* @param @param userid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int getUserStatusById(String  openid);
/**
 * 
 * @param s
 * @return
 *//** 李飞
* @Title: addStoreupdate 
* @Description: TODO(添加店铺审核表数据）
* @param @param s
* @param @return    设定文件 
* @return int    返回类型 
* @throws
 */
	int addStoreupdate(StoreInfoupdate s,String code);
	/**
	 * 
	 * @param c
	 * @return
	 *//** 李飞
	* @Title: addChef 
	* @Description: TODO添加厨师
	* @param @param c
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int addChef(ChefEntity c);
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: getChefById 
	* @Description: TODO(根据厨师id查询厨师
	* @param @param id
	* @param @return    设定文件 
	* @return ChefEntity    返回类型 
	* @throws
	 */
     ChefEntity getChefById(int id);
     /**
      * 
      * @param c
      * @return
      *//** 李飞
     * @Title: updateChefinfo 
     * @Description: TODO(跟新厨师
     * @param @param c
     * @param @return    设定文件 
     * @return int    返回类型 
     * @throws
      */
     int updateChefinfo(ChefEntity c);
	/**
	 * 
	 * @param g
	 * @return
	 *//** 李飞
	* @Title: addGreens 
	* @Description: TODO添加特色菜
	* @param @param g
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int addGreens(Greens g);
	
	/**
	 * 
	 * @param Userid
	 * @return
	 *//** 李飞
	* @Title: getStoreInfo 
	* @Description: TODO根据用户id查询店铺信息
	* @param @param Userid
	* @param @return    设定文件 
	* @return StoreInfo    返回类型 
	* @throws
	 */
	StoreInfo getStoreInfo(int Userid);
	/**
	 * 
	 * @param Userid
	 * @return
	 *//** 李飞
	* @Title: getStoreInfo 
	* @Description: TODO审核中的店铺信息
	* @param @param Userid
	* @param @return    设定文件 
	* @return StoreInfo    返回类型 
	* @throws
	 */
	StoreInfoupdate getStoreupdate(int Userid);
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: getStoreInfoById 
	* @Description: TODO(根据店铺id查询店铺信息
	* @param @param id
	* @param @return    设定文件 
	* @return StoreInfo    返回类型 
	* @throws
	 */
	StoreInfo getStoreInfoById(int id);
	
	/**
	 * 
	 * @param storeid
	 * @return
	 *//** 李飞
	* @Title: getChefList 
	* @Description: TODO(根据店铺id查询厨师信息
	* @param @param storeid
	* @param @return    设定文件 
	* @return List<ChefEntity>    返回类型 
	* @throws
	 */
	List<ChefEntity> getChefList(int storeid);
	/**
	 * 
	 * @param storeid
	 * @return
	 *//** 李飞
	* @Title: getGreensList 
	* @Description: TODO根据店铺id查询菜信息
	* @param @param storeid
	* @param @return    设定文件 
	* @return List<Greens>    返回类型 
	* @throws
	 */
	List<Greens> getGreensList(int storeid);
	/**
	 * 
	 * @return
	 *//** 李飞
	* @Title: getRuleList 
	* @Description: TODO(查询消费规则
	* @param @return    设定文件 
	* @return List<Storerule>    返回类型 
	* @throws
	 */
	List<Storerule> getRuleList();
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: getGreensById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return Greens    返回类型 
	* @throws
	 */
	Greens getGreensById(int id);
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: updateGreensById 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int updateGreensById(Greens g);
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: delGreens 
	* @Description: TODO(根据id删除
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int delGreens(int id);
	
	/**
	 * 
	 * @param id
	 * @return
	 *//** 李飞
	* @Title: delChefById 
	* @Description: TODO 根据id删除厨师
	* @param @param id
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	int delChefById(int id);
	
	Jsconfig getJsConfig(int id);
	
	int isMerchantUser(int userid);
	
	int isChef(int userid);
	
	
	ChefEntity getChefByUserid(int userid);
	//根据用户id查询所属店铺信息
	StoreInfo getStoreinfoByuserid(int userid) ;
	
	List<Greens> getGreensListByChefid(int chefid);
	
    
    List<StoretradingRecord> getStoretrading(String timec,int storeid);
	
}