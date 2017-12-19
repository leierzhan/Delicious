package com.zz.ccy.handler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.User;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;
import com.zz.ccy.service.MerchantService;
import com.zz.ccy.service.UserHelpService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.AdvancedUtil;

@RequestMapping("/merchant")
@Controller
public class MerchantListHandler{
	@Autowired
	private UserHelpService userHelpService;
	@Autowired
	private MerchantService mer;
	@Autowired
	private UserService us;
	
	
	/**
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @return
	 *//** 李飞shuage
	* @Title: sQ 
	* @Description: TODO(商家中心进入用户授权处理
	* @param @param code
	* @param @param request
	* @param @param response
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws
	 */
	@RequestMapping("sQ")
	public ModelAndView sQ(@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response){
		System.out.println("页面授权---");
		ModelAndView r=null;    
		System.out.println("111");
		String openid="-------------";
		try{
		Cookie co=	getCookieByName(request, "openid");
		System.out.println("code"+co.equals(null));
		
		if(!co.equals(null)){
			openid= co.getValue();
			r=new ModelAndView("go","page","../merchant/goMerchantSys");
		}else{
			AdvancedUtil au=new AdvancedUtil();
			OauthAccessToken o = au.getOauthAccessToken(code);
			openid =o.getOpenId();
			//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
			Cookie c=new Cookie("openid", openid);
			c.setMaxAge(10);
			response.addCookie(c);
			r=new ModelAndView("go","page","../merchant/goMerchantSys");
		}

	}catch (NullPointerException e){
	
		AdvancedUtil au=new AdvancedUtil();
		OauthAccessToken o = au.getOauthAccessToken(code);
		openid =o.getOpenId();
		//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
		
		Cookie c=new Cookie("openid", openid);
		c.setMaxAge(10);
		response.addCookie(c);
		r=new ModelAndView("go","page","../merchant/goMerchantSys");
	}finally{
		request.getSession().setAttribute("openid", openid);
	}
	
		return r;
	}
	
	
	
	public static Cookie getCookieByName(HttpServletRequest request,String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap(request);
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	/**
	 * 将cookie封装到Map里面
	 * @param request
	 * @return
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = request.getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	   //进入店铺列表
	   @RequestMapping("goMerchantSystest")
	   private ModelAndView goMerchantSystest(HttpServletRequest request){
		   String openid="ofcpN0lFLo5P5djxVugirDSztAUY";
		   request.getSession().setAttribute("openid",openid);
		   System.out.println("openid:"+openid);
		   WeixinUserInfo user=mer.getUserByOpenid(openid);
		   
		   request.getSession().setAttribute("userid",user.getId());
		

		 Map<String,Object> mp=new HashMap<String, Object>();
		 ModelAndView m=null;
		 
		int userstatus= mer.isMerchantUser(user.getId());
		//返回参数解释 0代表审核中，1，代表审核失败，2代表审核通过，3代表新用户没有提交申请
		if(userstatus==0){
			
		m=new ModelAndView("merchant/shz",mp);
		}else if(userstatus==1){
			//返回审核失败原因
		mp.put("yy", "信息不正确！");
		mp.put("storeid",30);
		m=new ModelAndView("merchant/shsb",mp);
		
		}else if(userstatus==2){
			 Jsconfig js=mer.getJsConfig(1);
			MerchantInfo is= mer.isMerchant(user.getId());
			request.getSession().setAttribute("storeid",is.getStoreid());
			   StoreInfo s=mer.getStoreInfoById(is.getStoreid());
			   mp.put("jsconfig",js);
		   	   mp.put("store",s);
		   	 m=new ModelAndView("merchant/merchant",mp);
		}else{
			List<Storerule> rs=mer.getRuleList();
	 		 mp.put("isM",2);
	 		 mp.put("rules", rs);
	 	   	 m=new ModelAndView("merchant/storeform",mp);
			
		}
			
		   return m;
	   }
	
	
	   //进入店铺列表
	   @RequestMapping("goMerchantSys")
	   private ModelAndView goMerchantSys(HttpServletRequest request){
		   String openid=(String) request.getSession().getAttribute("openid");
		   System.out.println("openid-------------"+openid+"00000");
		   WeixinUserInfo user=mer.getUserByOpenid(openid);
		   
		   request.getSession().setAttribute("userid",user.getId());
		

		 Map<String,Object> mp=new HashMap<String, Object>();
		 ModelAndView m=null;
		 
		int userstatus= mer.isMerchantUser(user.getId());
		//返回参数解释 0代表审核中，1，代表审核失败，2代表审核通过，3代表新用户没有提交申请
		if(userstatus==0){
			
		m=new ModelAndView("merchant/shz",mp);
		}else if(userstatus==1){
			//返回审核失败原因
		mp.put("yy", "信息不正确！");
		mp.put("storeid",30);
		m=new ModelAndView("merchant/shsb",mp);
		
		}else if(userstatus==2){
			 Jsconfig js=mer.getJsConfig(1);
			MerchantInfo is= mer.isMerchant(user.getId());
			request.getSession().setAttribute("storeid",is.getStoreid());
			   StoreInfo s=mer.getStoreInfoById(is.getStoreid());
			   mp.put("jsconfig",js);
		   	   mp.put("store",s);
		   	 m=new ModelAndView("merchant/merchant",mp);
		}else{
			List<Storerule> rs=mer.getRuleList();
	 		 mp.put("isM",2);
	 		 mp.put("rules", rs);
	 	   	 m=new ModelAndView("merchant/storeform",mp);
			
		}
			
		   return m;
	   }
	   
		@RequestMapping("chefSq")
		public ModelAndView chefSq(@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response){
			System.out.println("页面授权---");
			ModelAndView r=null;
			String openid="-------------";
			try{
			Cookie co=	getCookieByName(request, "openid");
			System.out.println("code"+co.equals(null));
			
			if(!co.equals(null)){
				openid= co.getValue();
				r=new ModelAndView("go","page","../merchant/goAddChef");
			}else{
				AdvancedUtil au=new AdvancedUtil();
				OauthAccessToken o = au.getOauthAccessToken(code);
				openid =o.getOpenId();
				//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
				Cookie c=new Cookie("openid", openid);
				c.setMaxAge(10);
				response.addCookie(c);
				r=new ModelAndView("go","page","../merchant/goAddChef");
			}

		}catch (NullPointerException e){
		
			AdvancedUtil au=new AdvancedUtil();
			OauthAccessToken o = au.getOauthAccessToken(code);
			openid =o.getOpenId();
			//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
			
			Cookie c=new Cookie("openid", openid);
			c.setMaxAge(10);
			response.addCookie(c);
			r=new ModelAndView("go","page","../merchant/goAddChef");
		}finally{
			request.getSession().setAttribute("openid", openid);
		}
		
			return r;
		}
	   
	   //点击我得店铺
	   @RequestMapping("goUpdateStoreinfo")
	   private ModelAndView goUpdateStoreinfo(HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid");

			 Map<String,Object> mp=new HashMap<String, Object>();
			   StoreInfoupdate store= mer.getStoreupdate(userid);
			   System.out.println(store.getId());
			   mp.put("store",store);
		  
		 List<Storerule> rule=  mer.getRuleList();
		 mp.put("rules",rule);
		   
		   ModelAndView m=new ModelAndView("merchant/storeupdateform",mp);
		   return m;
	   }
	   //点击我得店铺
	   @RequestMapping("updateStoreinfo")
	   private ModelAndView updateStoreinfo(int storeid ,int status,HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid");

			 Map<String,Object> mp=new HashMap<String, Object>();
		   if(status==0){
			   System.out.println(userid+"-------------------userid");
			   StoreInfoupdate store= mer.getStoreupdate(userid);
			   System.out.println(store.getId());
			   mp.put("store",store);
		   }else if(status==1){
			 StoreInfo  store= mer.getStoreInfo(userid);
			 mp.put("store",store);
		   }
		  
		 List<Storerule> rule=  mer.getRuleList();
		 mp.put("rules",rule);
		   
		   ModelAndView m=new ModelAndView("merchant/storeupdateform",mp);
		   return m;
	   }
	   
	   
	   //根据店铺id查询厨师信息
	   @RequestMapping("goChefList")
	   private ModelAndView goChefList(HttpServletRequest request){
			 Map<String,Object> mp=new HashMap<String, Object>();
			   int storeid=(Integer) request.getSession().getAttribute("storeid");
			   System.out.println(storeid+"-------------------storeid");
			   List<ChefEntity> chef= mer.getChefList(storeid);
			   mp.put("chefs",chef);
		   
		   ModelAndView m=new ModelAndView("merchant/cheflist",mp);
		   return m;
	   }
	   //根据店铺id查询厨师信息
	   @RequestMapping("goAddCheftest")
	   private ModelAndView goAddCheftest(HttpServletRequest request){
		   String openid="ofcpN0lFLo5P5djxVugirDSztAUY";
			 
		 Map<String,Object> mp=new HashMap<String, Object>();
			 
			 int userid= mer.getUserIdByOpenid(openid);
			 
			 request.getSession().setAttribute("userid", userid);

			   ModelAndView m=null;
			 
			 int status=mer.ischef(userid);
			 
			 //判断status如果等于-1表示新用户进入如果等于0审核中如果等于1审核通过

			 if(status==-1){
				m=new ModelAndView("merchant/chefform");
			 }else if(status==0){
				m=new ModelAndView("merchant/shz");
			 }else{
					StoreInfo store= mer.getStoreinfoByuserid(userid);
					
					WeixinUserInfo user=us.getUserInfoById(userid);
					
					ChefEntity chef=mer.getChefByUserid(userid);
					request.getSession().setAttribute("chefid", chef.getId());
					request.getSession().setAttribute("storeid",store.getId());
					mp.put("chef", chef);
					 mp.put("userinfo", user);
					 mp.put("storeinfo", store);
				 m=new ModelAndView("merchant/chefCore",mp);
			 }
			   return m;
	   }
	   
	   //根据店铺id查询厨师信息
	   @RequestMapping("goAddChef")
	   private ModelAndView goAddChef(HttpServletRequest request){
		     String openid=(String)request.getSession().getAttribute("openid");
			 
 Map<String,Object> mp=new HashMap<String, Object>();
			 
			 int userid= mer.getUserIdByOpenid(openid);
			 
			 request.getSession().setAttribute("userid", userid);

			   ModelAndView m=null;
			 
			 int status=mer.ischef(userid);
			 
			 //判断status如果等于-1表示新用户进入如果等于0审核中如果等于1审核通过

			 if(status==-1){
				m=new ModelAndView("merchant/chefform");
			 }else if(status==0){
				m=new ModelAndView("merchant/shz");
			 }else{
					StoreInfo store= mer.getStoreinfoByuserid(userid);
					
					WeixinUserInfo user=us.getUserInfoById(userid);
					
					ChefEntity chef=mer.getChefByUserid(userid);
					request.getSession().setAttribute("chefid", chef.getId());
					request.getSession().setAttribute("storeid",store.getId());
					mp.put("chef", chef);
					 mp.put("userinfo", user);
					 mp.put("storeinfo", store);
				 m=new ModelAndView("merchant/chefCore",mp);
			 }
			   return m;
	   }
	   //获取手机验证码
	   //添加厨师信息
	   @ResponseBody
	   @RequestMapping("getPhoneCode")
	   private String  getPhoneCode(String tel,HttpServletRequest request) throws ClientProtocolException, IOException{
			   
		   return mer.sendCode(tel);
	   }
	   
	   
	   //添加厨师信息
	   @ResponseBody
	   @RequestMapping("addChef")
	   private Integer addChef(String imgs,String name,String tel,String tags,HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid"); 
			   ChefEntity c=new ChefEntity(0, userid, tel, name, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, 3, tags,0, 0, getNowDateTime(),"","");
			   int i=0;
			  i= mer.addChef(c);
			  if(i>0){
			   		return userid;
			   	}
			  		return i;
	   }
	   @ResponseBody
	   @RequestMapping("updateChef")
	   private Integer updateChef(int id,String imgs,String name,String tel,String tags,HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid"); 
			   ChefEntity c=new ChefEntity(id, userid, tel, name, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, 3, tags,0, 0, getNowDateTime(),"","");
			   int i=0;
			  i= mer.updateChefinfo(c);
		   return i;
	   }
	   //进入更新厨师页面
	   @RequestMapping("goUpdateChef")
	   private ModelAndView goUpdateChef(int id,HttpServletRequest request){
			   System.out.println(id+"-------------------chefid");
			   ChefEntity c=mer.getChefById(id);
			   ModelAndView m=new ModelAndView("merchant/chefupdateform","chef",c);
		   return m;
	   }
	   //删除厨师
	   @RequestMapping("delChef")
	   private ModelAndView delChef(int id,HttpServletRequest request){
			   int i=mer.delChefById(id);
			   ModelAndView m=goChefList(request);
		   return m;
	   }
	   
	   
	   
	   
	   
	   //简单页面跳转
	   @RequestMapping("goPage")
	   private ModelAndView goPage(String url){
		  Jsconfig j= mer.getJsConfig(1);
		   ModelAndView m=new ModelAndView("merchant/"+url,"jsconfig",j);
		   return m;
	   }
	   //简单页面跳转
	   @RequestMapping("goStoreinfo")
	   private ModelAndView goStoreinfo(HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid");
		   System.out.println(userid);
		 List<Storerule> rule=  mer.getRuleList();
		   
		   ModelAndView m=new ModelAndView("merchant/storeform","rules",rule);
		   return m;
	   }
	   
	   
	   
		//上传图片
		@ResponseBody
	    @RequestMapping("uploadGoodsImgs")  
	    public String uploadGoodsImgs(@RequestParam("type") int type,@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {  
	  
	        System.out.println("开始上传");
	        
	        String path1 =request.getSession().getServletContext().getRealPath("merchant_img");  
	       // String path1 = request.getSession().getServletContext().getRealPath("/goods/imgs"); 
	        System.out.println(path1);
	      String ss="";
	        System.out.println(files.length);
	        for (int i = 0; i < files.length; i++) {
	        	 String h = files[i].getOriginalFilename();
	        	 System.out.println(h);
	        	 h=h.substring(h.length()-4,h.length());
	        	 String filename="mer-"+type+"-"+getRandomString(6)+h;
//	           String fileName = new Date().getTime()+".jpg";
	           System.out.println(path1+filename);
	        	 ss+=filename+",";
	             //上传系统
	             File targetFile = new File(path1, filename);  
	             if(!targetFile.exists()){  
	                 targetFile.mkdirs();  
	             }  
	             //保存  
	             try {  
	                 files[i].transferTo(targetFile);  
	             } catch (Exception e) {  
	                 e.printStackTrace();  
	             } 
//	             System.out.println("拷贝文件："+path1+"/"+filename+"拷到："+path+filename);
//	             uts.copyFile(path1+"/"+filename, path+filename);     
//	             System.out.println("gid:"+gid+"--goods/imgs/"+filename+"--type"+type+"status:0");
			}
	        ss=ss.substring(0, ss.length()-1);
	        //服务器上传	 
	        return ss;  
	    }
		@ResponseBody
		@RequestMapping("addStoreupdate")
		public Integer addStoreupdate(HttpServletRequest request,@RequestParam("imgs") String imgs,@RequestParam("name") String name,@RequestParam("renjun") String renjun,@RequestParam("focus") String focus,
				@RequestParam("address") String address,@RequestParam("storerule") int storerule,@RequestParam("tel") String tel,@RequestParam("yimg") String yimg,@RequestParam("pp") String pp) throws IOException{
			int userid= (Integer) request.getSession().getAttribute("userid");
			  String path =request.getSession().getServletContext().getRealPath("ercode");  
			String[] ff=focus.split(",");
			imgs=imgs.substring(0, imgs.length()-1);
			String[] ss=imgs.split(",");
			System.out.println(ss[0]);
			StoreInfoupdate s=new StoreInfoupdate(0, 0, name,ss[0].toString(), 4, Integer.parseInt(renjun.toString()), address,imgs, storerule, ff[0], ff[1], tel, pp,yimg, 0, userid, getNowDateTime());
			
			int i=mer.addStoreupdate(s,path);
			return i;
		}
		
		
		
		/**
		 * 
		 * @return
		 *//** 李飞
		* @Title: getNowDateTime 
		* @Description: TODO( c菜单操作
		* @param @return    设定文件 
		* @return String    返回类型 
		* @throws
		 */
		
		
		   //简单页面跳转
		   @RequestMapping("goGreens")
		   private ModelAndView goGreens(HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid");
			 List<Greens> g=  mer.getGreensList(storeid);
			   ModelAndView m=new ModelAndView("merchant/cailist","grs",g);
			   return m;
		   }
		   //简单页面跳转
		   @RequestMapping("goGreensByChef")
		   private ModelAndView goGreensByChef(HttpServletRequest request){
			   int chefid=(Integer) request.getSession().getAttribute("chefid");
			 List<Greens> g=  mer.getGreensListBuChefid(chefid);
			   ModelAndView m=new ModelAndView("merchant/cailistByChef","grs",g);
			   return m;
		   }
		   //简单页面跳转
		   @RequestMapping("goGreensByChefStore")
		   private ModelAndView goGreensByChefStore(int chefid,HttpServletRequest request){
			 List<Greens> g=  mer.getGreensListBuChefid(chefid);
			   ModelAndView m=new ModelAndView("merchant/cailistByChefStore","grs",g);
			   return m;
		   }
		   
		   /**
		    * 
		    * @return
		    *//** 李飞
		   * @Title: getNowDateTime 
		   * @Description: TODO添加菜
		   * @param @return    设定文件 
		   * @return String    返回类型 
		   * @throws
		    */
		   //简单页面跳转
		   @RequestMapping("goAddGreensByStore")
		   private ModelAndView goAddGreensByStore(HttpServletRequest request){
			   ModelAndView m=new ModelAndView("merchant/caiform");
			   return m;
		   }
		   
		   //简单页面跳转
		   @RequestMapping("goAddGreensByChef")
		   private ModelAndView goAddGreensByChef(HttpServletRequest request){
			  int chefid= (Integer) request.getSession().getAttribute("chefid");
			  int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   List<ChefEntity> chefs= mer.getChefList(storeid);
			   Map<String,Object> m=new HashMap<String, Object>();
			   m.put("chefid", chefid);
			   m.put("chefs", chefs);
			   ModelAndView mp=new ModelAndView("merchant/chefcaiform",m);
			   return mp;
		   }
		   
		   
		   //简单页面跳转
		   @RequestMapping("goUpdateGreens")
		   private ModelAndView goUpdateGreens(int id,HttpServletRequest request){
			   System.out.println("**********_________---"+id);
			   Greens g= mer.getGreensById(id);
			   Map<String,Object> m=new HashMap<String, Object>();
			   m.put("greens",g);
			   ModelAndView mv=new ModelAndView("merchant/caiupdateform",m);
			   return mv;
		   }
		   
		   //简单页面跳转
		   @RequestMapping("goChefUpdateGreens")
		   private ModelAndView goChefUpdateGreens(int id,HttpServletRequest request){
			   System.out.println("**********_________---"+id);
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   List<ChefEntity> chefs= mer.getChefList(storeid);
			   Greens g= mer.getGreensById(id);
			   Map<String,Object> m=new HashMap<String, Object>();
			   
			   m.put("chefs",chefs);
			   m.put("greens",g);
			   ModelAndView mv=new ModelAndView("merchant/chefcaiupdateform",m);
			   return mv;
		   }
		   
		   @ResponseBody
		   @RequestMapping("addGreens")
		   private Integer addGreens(int chefid,String imgs,String name,float price,String tags,HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   System.out.println(storeid+"--------#############################-----------storeid");
			   int status;		
			   if(chefid==0){
			   			status=0;
			   		}else{
			   			status=1;
			   		}
				   Greens g=new Greens(0, storeid, chefid, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, name, price, tags, 0, status);
				 int i= mer.addGreens(g);
			   return i;
		   }
		   
		   @ResponseBody
		   @RequestMapping("updateChefGreens")
		   private Integer updateChefGreens(int id,String imgs,String name,float price,String tags,HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   int chefid=(Integer)request.getSession().getAttribute("chefid");
			   System.out.println(storeid+"--------#############################-----------storeid");
				   Greens g=new Greens(id, storeid,chefid , "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, name, price, tags, 0, 0);
				 int i= mer.updateGreensById(g);
			   return i;
		   }
		
		   @ResponseBody
		   @RequestMapping("updateGreens")
		   private Integer updateGreens(int id,int chefid,String imgs,String name,float price,String tags,HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   System.out.println(storeid+"--------#############################-----------storeid");
				   Greens g=new Greens(id, storeid, chefid, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, name, price, tags, 0, 0);
				 int i= mer.updateGreensById(g);
			   return i;
		   }
		   
		   //简单页面跳转
		   @RequestMapping("delGreens")
		   private ModelAndView delGreens(int id,HttpServletRequest request){
			   System.out.println("**********_________---"+id);
			   mer.delGreens(id);
			   ModelAndView mv=goGreens(request);
			   return mv;
		   }
		
		
		
		
		
		
		   private String getNowDateTime(){
			   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   return df.format(new Date());
		   }
		
		
		
		
		
		
		public String getRandomString(int length) {
			String str = "123456789abcdefghijklmnopqsrtuvwxyz";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < length; ++i) {
				int number = random.nextInt(length);// [0,62)

				sb.append(str.charAt(number));
			}
			return sb.toString();
		}
}
