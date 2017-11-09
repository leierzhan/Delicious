package com.zz.ccy.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.MerchantDao;
import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;
import com.zz.ccy.service.MerchantService;
import com.zz.ccy.util.Constant;

/**
 * 
* @ClassName: MerchantServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author 李飞
* @date 2017年10月11日 下午1:33:56 
*
 */
@Service
@Transactional
public class MerchantServiceImpl implements MerchantService{
	@Autowired
    private MerchantDao mer;
	@Override
	public MerchantInfo isMerchant(int userid) {
		
		// TODO Auto-generated method stub
		return mer.isMerchant(userid);
	}
	@Override
	public int getUserIdByOpenid(String openid) {
		return mer.getUserIdByOpenid(openid);
	}
	@Override
	public int addStoreupdate(StoreInfoupdate s)  {
		return mer.addStoreupdate(s);
	}
	@Override
	public int addChef(ChefEntity c) {
		return mer.addChef(c);
	}
	@Override
	public int addGreens(Greens g) {
		return mer.addGreens(g);
	}
	@Override
	public StoreInfo getStoreInfo(int Userid) {
		// TODO Auto-generated method stub
		return mer.getStoreInfo(Userid);
	}
	@Override
	public List<ChefEntity> getChefList(int storeid) {
		return mer.getChefList(storeid);
	}
	@Override
	public List<Greens> getGreensList(int storeid) {
		// TODO Auto-generated method stub
		return mer.getGreensList(storeid);
	}
	@Override
	public List<Storerule> getRuleList() {
		// TODO Auto-generated method stub
		return mer.getRuleList();
	}
	@Override
	public StoreInfo getStoreInfoById(int id) {
		return mer.getStoreInfoById(id);
	}
	@Override
	public int getUserStatusById(String  openid) {
		
		return mer.getUserStatusById(openid);
	}
	@Override
	public WeixinUserInfo getUserByOpenid(String openid) {
		// TODO Auto-generated method stub
		return mer.getUserByOpenid(openid);
	}
	@Override
	public StoreInfoupdate getStoreupdate(int userid) {
		return mer.getStoreupdate(userid);
	}
	@Override
	public ChefEntity getChefById(int id) {
		return mer.getChefById(id);
	}
	@Override
	public int updateChefinfo(ChefEntity c) {
		// TODO Auto-generated method stub
		return mer.updateChefinfo(c);
	}
	@Override
	public int delChefById(int id) {
		return mer.delChefById(id);
	}
	@Override
	public Greens getGreensById(int id) {
		// TODO Auto-generated method stub
		return mer.getGreensById(id);
	}
	@Override
	public int updateGreensById(Greens g) {
		// TODO Auto-generated method stub
		return mer.updateGreensById(g);
	}
	@Override
	public int delGreens(int id) {
		// TODO Auto-generated method stub
		return mer.delGreens(id);
	}
	@Override
	public Jsconfig getJsConfig(int id) {
		// TODO Auto-generated method stub
		return mer.getJsConfig(id);
	}
	@Override
	public int isMerchantUser(int userid) {
		return mer.isMerchantUser(userid);
	}
	@Override
	public int ischef(int userid) {
		return  mer.ischef(userid);
	}
	
	@Override
	public String sendCode(String mobilenum) throws ClientProtocolException, IOException {
		String code=getRandomString(4);
		String url="http://10086.dx.hn/sms.aspx?action=send&userid=3865&account=tobecontinuedd&password=1qaz2wsx3edc" +
	            "&mobile="+mobilenum+"&content=【"+Constant.itemname+"】"+code+"(动态验证码),请在90秒内填写&sendTime=&checkcontent=0";
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		client.execute(post);
		System.out.println(code);
		return code;
	}	
	
	public static String getRandomString(int length) { //length表示生成字符串的长度  
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 }
	@Override
	public StoreInfo getStoreinfoByuserid(int userid) {
		// TODO Auto-generated method stub
		return mer.getStoreinfoByuserid(userid);
	}


}
