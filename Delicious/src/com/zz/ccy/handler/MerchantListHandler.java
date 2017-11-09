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
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.lf.entity.Jsconfig;
import com.zz.ccy.lf.entity.MerchantInfo;
import com.zz.ccy.lf.entity.StoreInfoupdate;
import com.zz.ccy.lf.entity.Storerule;
import com.zz.ccy.service.MerchantService;
import com.zz.ccy.service.UserHelpService;
import com.zz.ccy.util.AdvancedUtil;

@RequestMapping("/merchant")
@Controller
public class MerchantListHandler{
	@Autowired
	private UserHelpService userHelpService;
	@Autowired
	private MerchantService mer;
	
	/**
	 * 
	 * @param code
	 * @param request
	 * @param response
	 * @return
<<<<<<< HEAD
	 *//** ���shuage
	* @Title: sQ 
	* @Description: TODO(�̼����Ľ����û���Ȩ����
	* @param @param code
	* @param @param request
	* @param @param response
	* @param @return    �趨�ļ� 
	* @return ModelAndView    �������� 
	* @throws
	 */
	@RequestMapping("sQ")
	public ModelAndView sQ(@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response){
		System.out.println("ҳ����Ȩ---");
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
	 * ��cookie��װ��Map����
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
	   //��������б�
	   @RequestMapping("goMerchantSystest")
	   private ModelAndView goMerchantSystest(HttpServletRequest request){
		   String openid="ofcpN0rvlmJECR92K0PwT5AIaiUA";
		   System.out.println("openid:"+openid);
		   WeixinUserInfo user=mer.getUserByOpenid(openid);
		   
		   request.getSession().setAttribute("userid",user.getId());
		

		 Map<String,Object> mp=new HashMap<String, Object>();
		 ModelAndView m=null;
		 
		int userstatus= mer.isMerchantUser(user.getId());
		
		//���ز������� 0��������У�1���������ʧ�ܣ�2�������ͨ����3�������û�û���ύ����
		
		if(userstatus==0){
			
		m=new ModelAndView("merchant/shz",mp);
		}else if(userstatus==1){
			//�������ʧ��ԭ��
		mp.put("yy", "��Ϣ����ȷ��");
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
	
	
	   //��������б�
	   @RequestMapping("goMerchantSys")
	   private ModelAndView goMerchantSys(HttpServletRequest request){
		   String openid=(String) request.getSession().getAttribute("openid");
		   System.out.println("openid:"+openid);
		   System.out.println("openid:"+openid);
		   WeixinUserInfo user=mer.getUserByOpenid(openid);
		   
		   request.getSession().setAttribute("userid",user.getId());
		

		 Map<String,Object> mp=new HashMap<String, Object>();
		 ModelAndView m=null;
		 
		int userstatus= mer.isMerchantUser(user.getId());
		
		//���ز������� 0��������У�1���������ʧ�ܣ�2�������ͨ����3�������û�û���ύ����
		
		if(userstatus==0){
			
		m=new ModelAndView("merchant/shz",mp);
		}else if(userstatus==1){
			//�������ʧ��ԭ��
		mp.put("yy", "��Ϣ����ȷ��");
		mp.put("storeid",30);
		m=new ModelAndView("merchant/shsb",mp);
		
		}else if(userstatus==2){
			 Jsconfig js=mer.getJsConfig(1);
			MerchantInfo is= mer.isMerchant(user.getId());
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
			System.out.println("ҳ����Ȩ---");
			ModelAndView r=null;
			String openid="-------------";
			try{
			Cookie co=	getCookieByName(request, "openid");
			System.out.println("code"+co.equals(null));
			
			if(!co.equals(null)){
				openid= co.getValue();
				r=new ModelAndView("go","page","../merchant/goAddCheftest");
			}else{
				AdvancedUtil au=new AdvancedUtil();
				OauthAccessToken o = au.getOauthAccessToken(code);
				openid =o.getOpenId();
				//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
				Cookie c=new Cookie("openid", openid);
				c.setMaxAge(10);
				response.addCookie(c);
				r=new ModelAndView("go","page","../merchant/goAddCheftest");
			}

		}catch (NullPointerException e){
		
			AdvancedUtil au=new AdvancedUtil();
			OauthAccessToken o = au.getOauthAccessToken(code);
			openid =o.getOpenId();
			//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
			
			Cookie c=new Cookie("openid", openid);
			c.setMaxAge(10);
			response.addCookie(c);
			r=new ModelAndView("go","page","../merchant/goAddCheftest");
		}finally{
			request.getSession().setAttribute("openid", openid);
		}
		
			return r;
		}
	   
	   //����ҵõ���
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
	   //����ҵõ���
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
	   
	   
	   //���ݵ���id��ѯ��ʦ��Ϣ
	   @RequestMapping("goChefList")
	   private ModelAndView goChefList(HttpServletRequest request){
			 Map<String,Object> mp=new HashMap<String, Object>();
			   int storeid=(Integer) request.getSession().getAttribute("storeid");
			   System.out.println(storeid+"-------------------storeid");
			   List<ChefEntity> chef= mer.getChefList(storeid);
			  // System.out.println("chefid"+chef.get(0).getId());
			   mp.put("chefs",chef);
		   
		   ModelAndView m=new ModelAndView("merchant/cheflist",mp);
		   return m;
	   }
	   //���ݵ���id��ѯ��ʦ��Ϣ
	   @RequestMapping("goAddCheftest")
	   private ModelAndView goAddCheftest(HttpServletRequest request){
		   String openid="ofcpN0rvlmJECR92K0PwT5AIaiUA";
			 
		 Map<String,Object> mp=new HashMap<String, Object>();
		 
		 int userid= mer.getUserIdByOpenid(openid);
		 
		 request.getSession().setAttribute("userid", userid);

		   ModelAndView m=null;
		 
		 int status=mer.ischef(userid);
		 
		 //�ж�status�������-1��ʾ���û������������0������������1���ͨ��

		 if(status==-1){
			m=new ModelAndView("merchant/chefform");
		 }else if(status==0){
			m=new ModelAndView("merchant/shz");
		 }else{
			 m=new ModelAndView("merchant/chefCore",mp);
		 }
		   return m;
	   }
	   
	   //���ݵ���id��ѯ��ʦ��Ϣ
	   @RequestMapping("goAddChef")
	   private ModelAndView goAddChef(HttpServletRequest request){
		     String openid=(String)request.getSession().getAttribute("openid");
			 
			 Map<String,Object> mp=new HashMap<String, Object>();
			 
			 int userid= mer.getUserIdByOpenid(openid);
			 
			 request.getSession().setAttribute("userid", userid);

			   ModelAndView m=null;
			 
			 int status=mer.ischef(userid);
			 
			 //�ж�status�������-1��ʾ���û������������0������������1���ͨ��

			 if(status==-1){
				m=new ModelAndView("merchant/chefform");
			 }else if(status==0){
				m=new ModelAndView("merchant/shz");
			 }else{
				 m=new ModelAndView("merchant/chefCore",mp);
			 }
			   return m;
	   }
	   //��ȡ�ֻ���֤��
	   //��ӳ�ʦ��Ϣ
	   @ResponseBody
	   @RequestMapping("getPhoneCode")
	   private String  getPhoneCode(String tel,HttpServletRequest request) throws ClientProtocolException, IOException{
			   
		   return mer.sendCode(tel);
	   }
	   
	   
	   //��ӳ�ʦ��Ϣ
	   @ResponseBody
	   @RequestMapping("addChef")
	   private Integer addChef(String imgs,String name,String tel,String tags,HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid"); 
			   ChefEntity c=new ChefEntity(0, userid, tel, name, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, 3, tags,0, 0, getNowDateTime());
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
		   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   ChefEntity c=new ChefEntity(id, storeid, tel, name, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, 3, tags,0, 0, getNowDateTime());
			   int i=0;
			  i= mer.updateChefinfo(c);
		   return i;
	   }
	   //������³�ʦҳ��
	   @RequestMapping("goUpdateChef")
	   private ModelAndView goUpdateChef(int id,HttpServletRequest request){
			   System.out.println(id+"-------------------chefid");
			   ChefEntity c=mer.getChefById(id);
			   System.out.println("chef:%%%%%%%%%55"+c.getHeadimg());
			   ModelAndView m=new ModelAndView("merchant/chefupdateform","chef",c);
		   return m;
	   }
	   //ɾ����ʦ
	   @RequestMapping("delChef")
	   private ModelAndView delChef(int id,HttpServletRequest request){
			   int i=mer.delChefById(id);
			   ModelAndView m=goChefList(request);
		   return m;
	   }
	   
	   
	   
	   
	   
	   //��ҳ����ת
	   @RequestMapping("goPage")
	   private ModelAndView goPage(String url){
		  Jsconfig j= mer.getJsConfig(1);
		   ModelAndView m=new ModelAndView("merchant/"+url,"jsconfig",j);
		   return m;
	   }
	   //��ҳ����ת
	   @RequestMapping("goStoreinfo")
	   private ModelAndView goStoreinfo(HttpServletRequest request){
		   int userid=(Integer) request.getSession().getAttribute("userid");
		   System.out.println(userid);
		 List<Storerule> rule=  mer.getRuleList();
		   
		   ModelAndView m=new ModelAndView("merchant/storeform","rules",rule);
		   return m;
	   }
	   
	   
	   
		//�ϴ�ͼƬ
		@ResponseBody
	    @RequestMapping("uploadGoodsImgs")  
	    public String uploadGoodsImgs(@RequestParam("type") int type,@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {  
	  
	        System.out.println("��ʼ�ϴ�");
	        
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
	             //�ϴ�ϵͳ
	             File targetFile = new File(path1, filename);  
	             if(!targetFile.exists()){  
	                 targetFile.mkdirs();  
	             }  
	       
	             //����  
	             try {  
	                 files[i].transferTo(targetFile);  
	             } catch (Exception e) {  
	                 e.printStackTrace();  
	             } 
	             
//	             System.out.println("�����ļ���"+path1+"/"+filename+"������"+path+filename);
	        	
//	             uts.copyFile(path1+"/"+filename, path+filename);
	             
//	             System.out.println("gid:"+gid+"--goods/imgs/"+filename+"--type"+type+"status:0");
	             
	           
			}
	        
	        
	        ss=ss.substring(0, ss.length()-1);
	        //�������ϴ�	 
	        return ss;  
	    }
		
		@ResponseBody
		@RequestMapping("addStoreupdate")
		public Integer addStoreupdate(HttpServletRequest request,@RequestParam("imgs") String imgs,@RequestParam("name") String name,@RequestParam("renjun") String renjun,@RequestParam("focus") String focus,
				@RequestParam("address") String address,@RequestParam("storerule") int storerule,@RequestParam("tel") String tel,@RequestParam("yimg") String yimg,@RequestParam("pp") String pp){
			
			int userid= (Integer) request.getSession().getAttribute("userid");
			
			String[] ff=focus.split(",");
			
			imgs=imgs.substring(0, imgs.length()-1);
			
			String[] ss=imgs.split(",");
			String lunbo="";
			for (int i = 0; i < ss.length; i++) {
				lunbo+=ss[i];
			}
			
			System.out.println(ss[0]);
			System.out.println(lunbo.substring(0, lunbo.length()-1));
			StoreInfoupdate s=new StoreInfoupdate(0, 0, name,ss[0].toString(), 4, Integer.parseInt(renjun.toString()), address,lunbo.substring(0, lunbo.length()-1), storerule, ff[0], ff[1], tel, pp,yimg, 0, userid, getNowDateTime());
			
			int i=mer.addStoreupdate(s);
			return i;
		}
		
		
		
		/**
		 * 
		 * @return
		 *//** ���
		* @Title: getNowDateTime 
		* @Description: TODO( c�˵�����
		* @param @return    �趨�ļ� 
		* @return String    �������� 
		* @throws
		 */
		
		
		   //��ҳ����ת
		   @RequestMapping("goGreens")
		   private ModelAndView goGreens(HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid");
			 List<Greens> g=  mer.getGreensList(storeid);
			   ModelAndView m=new ModelAndView("merchant/cailist","grs",g);
			   return m;
		   }
		   
		   /**
		    * 
		    * @return
		    *//** ���
		   * @Title: getNowDateTime 
		   * @Description: TODO��Ӳ�
		   * @param @return    �趨�ļ� 
		   * @return String    �������� 
		   * @throws
		    */
		   //��ҳ����ת
		   @RequestMapping("goAddGreens")
		   private ModelAndView goAddGreens(HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   List<ChefEntity> chefs= mer.getChefList(storeid);
			   ModelAndView m=new ModelAndView("merchant/caiform","chefs",chefs);
			   return m;
		   }
		   //��ҳ����ת
		   @RequestMapping("goUpdateGreens")
		   private ModelAndView goUpdateGreens(int id,HttpServletRequest request){
			   System.out.println("**********_________---"+id);
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   List<ChefEntity> chefs= mer.getChefList(storeid);
			   Greens g= mer.getGreensById(id);
			   Map<String,Object> m=new HashMap<String, Object>();
			   m.put("chefs",chefs);
			   m.put("greens",g);
			   ModelAndView mv=new ModelAndView("merchant/caiupdateform",m);
			   return mv;
		   }
		   
		   @ResponseBody
		   @RequestMapping("addGreens")
		   private Integer addGreens(int chefid,String imgs,String name,float price,String tags,HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   System.out.println(storeid+"--------#############################-----------storeid");
			
				   Greens g=new Greens(0, storeid, chefid, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, name, price, tags, 0, 0);
				 int i= mer.addGreens(g);
			   return i;
		   }
		
		   @ResponseBody
		   @RequestMapping("updateGreens")
		   private Integer addGreens(int id,int chefid,String imgs,String name,float price,String tags,HttpServletRequest request){
			   int storeid=(Integer) request.getSession().getAttribute("storeid"); 
			   System.out.println(storeid+"--------#############################-----------storeid");
				   Greens g=new Greens(id, storeid, chefid, "http://www.cnmjw.com.cn/Delicious/merchant_img/"+imgs, name, price, tags, 0, 0);
				 int i= mer.updateGreensById(g);
			   return i;
		   }
		   
		   //��ҳ����ת
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
