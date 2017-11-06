package com.zz.ccy.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;


 /**
  * 
 * @ClassName: MerchantService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 李飞
 * @date 2017年10月11日 下午1:28:34 
 *
  */
public interface MerchantService {
	MerchantInfo isMerchant(int userid);
	
	int getUserIdByOpenid(String openid);

	int addStoreupdate(StoreInfoupdate s);
	
	int addChef(ChefEntity c);
	
	int addGreens(Greens g);
	
	int getUserStatusById(String openid);
	StoreInfo getStoreInfo(int Userid);
	StoreInfo getStoreInfoById(int id) ;
	
	List<ChefEntity> getChefList(int storeid);
	
	List<Greens> getGreensList(int storeid);
	List<Storerule> getRuleList();
	WeixinUserInfo getUserByOpenid(String openid);
	StoreInfoupdate getStoreupdate(int userid);
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
      * @param id
      * @return
      *//** 李飞
     * @Title: delChefById 
     * @Description: TODO(删除厨师
     * @param @param id
     * @param @return    设定文件 
     * @return int    返回类型 
     * @throws
      */
     int delChefById(int id);
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
 	
	public Jsconfig getJsConfig(int id) ;
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: isMerchantUser 
	* @Description: TODO(根据用户id查询用户审核状态) 
	* @param @param userid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int isMerchantUser(int userid);
	/**
	 * 
	 * @param userid
	 * @return
	 *//** 李飞
	* @Title: ischef 
	* @Description: TODO(判断厨师) 
	* @param @param userid
	* @param @return    设定文件 
	* @return int    返回类型 
	* @throws
	 */
	public int ischef(int userid);
	
	public String sendCode(String mobilenum) throws ClientProtocolException, IOException;
	
	
}
