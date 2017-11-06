package com.zz.ccy.menu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.zz.ccy.util.Constant;
import com.zz.ccy.util.MenuUtil;
/**
 * @ClassName: MenuManager
 * @Description: 菜单管理
 * @author: David
 * @date: 2017年9月11日 上午9:30:06
 */
public class MenuManager {
	
	private static Menu getMenu() {
		ViewButton btn11 = new ViewButton();
		btn11.setName("免费吃");
		btn11.setType("view");
		btn11.setUrl("http://www.cnmjw.com.cn/");
		

		
		
		ViewButton usercore = new ViewButton();  
      btn11.setName("♥个人中心");  
      btn11.setType("view");  
      String URL11="http://www.cnmjw.com.cn/Delicious/page/sQ";
      String ENCODING11="utf-8";
      try {
		String str= URLEncoder.encode(URL11, ENCODING11).replace("*","*").replace("~", "~").replace("+"," ");
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constant.appid+"&redirect_uri="+str+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");  

	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
      
      
		ViewButton merchantcore = new ViewButton();  
	      btn11.setName("商户中心");  
	      btn11.setType("view");  
	      String URL3="http://www.cnmjw.com.cn/elicious/merchant/sQ";
	      String ENCODING3="utf-8";
	      try {
			String str= URLEncoder.encode(URL3, ENCODING3).replace("*","*").replace("~", "~").replace("+"," ");
	        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+Constant.appid+"&redirect_uri="+str+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect");  

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	      
	      
	      ComplexButton cb=new ComplexButton();
	      cb.setName("商户");
	      cb.setSub_button(new Button[] {merchantcore});
	      
		Menu menu = new Menu();
		menu.setButton(new Button[] {usercore});

		return menu;
	}
	public static void main(String[] args){
        String accessToken="65AfLfLPKstgCedWyBO0MTPsK7ZxDgnIlj5F3S3iWMGws03DMkfMxFUb5vEmzM5XhrDRMLxNPoQcg7twJeetxftZZe6QGXeSFXQyMX5COikPAYaAEAALJ";
		if (null!= accessToken){
			boolean result = MenuUtil.createMenu(getMenu(),accessToken);
			if (result){
				System.out.println("菜单创建成功");
			}
			else{
				System.out.println("菜单创建失败");
			}
		}
	}
}
