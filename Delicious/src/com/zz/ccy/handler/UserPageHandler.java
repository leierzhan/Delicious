package com.zz.ccy.handler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.bytecode.stackmap.BasicBlock.Catch;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zz.ccy.entity.CollectInfo;
import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.StoreMsb;
import com.zz.ccy.entity.UserCorepage;
import com.zz.ccy.entity.UserHeadinfo;
import com.zz.ccy.entity.UserMsbRecord;
import com.zz.ccy.entity.UserNotice;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WzInfo;
import com.zz.ccy.service.MsbService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.AdvancedUtil;

@RequestMapping("/page")
@Controller  
public class UserPageHandler{
	private static Logger logger=Logger.getLogger(UserPageHandler.class);
	@Autowired
	private UserService userService;
	@Autowired
	private MsbService ms;
	
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goUserCore 
	   * @Description: TODO(进入个人中♥）
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	   * 
	   * https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&
	   * redirect_uri=http//www.cnmjw.com.cn/Delicious/page/sQ
	   * &response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
	    */
	@RequestMapping("sQ")
	public ModelAndView sQ(@RequestParam("code") String code,HttpServletRequest request,HttpServletResponse response){
		System.out.println("页面授权---");
		ModelAndView r=null;
		String openid="-------------";
		try{
		Cookie co=	getCookieByName(request, "openid");
		System.out.println("code"+co.equals(null));
		if(!co.equals(null)){
			openid= co.getValue();
			r=new ModelAndView("go","page","../page/goUserCoreT");
		}else{
			
		
			AdvancedUtil au=new AdvancedUtil();
			OauthAccessToken o = au.getOauthAccessToken(code);
			openid =o.getOpenId();
			//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
			//
			
			Cookie c=new Cookie("openid", openid);
			c.setMaxAge(10);
			response.addCookie(c);

			r=new ModelAndView("go","page","../page/goUserCoreT");
		}

	}catch (NullPointerException e){
	
		AdvancedUtil au=new AdvancedUtil();
		OauthAccessToken o = au.getOauthAccessToken(code);
		openid =o.getOpenId();
		//openid="o-3GTweC9cIRvDdcvFbmmw9hyWyc";
		//
		Cookie c=new Cookie("openid", openid);
		c.setMaxAge(10);
		response.addCookie(c);
		
		r=new ModelAndView("go","page","../page/goUserCoreT");
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
	/**
	 * 
	 * @param request
	 * @return
	 *//** 李飞
	* @Title: goUserCoretest 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param request
	* @param @return    设定文件 
	* @return ModelAndView    返回类型 
	* @throws
	 */
	
	@RequestMapping("goUserCoretest")
	public ModelAndView goUserCoretest(HttpServletRequest request,HttpServletResponse response) {
		
		ModelAndView m=null;
		

	    String openid="ofcpN0rvlmJECR92K0PwT5AIaiUA";
		request.getSession().setAttribute("openid", openid);
		m=goUserCoreT(request);
		
		return m;
	}
		/**
		 * 
		 * @param request
		 * @return
		 *//** 李飞
		* @Title: goUserCoreT 
		* @Description: TODO(这里用一句话描述这个方法的作用) 
		* @param @param request
		* @param @return    设定文件 
		* @return ModelAndView    返回类型 
		* @throws
		 */
	   @RequestMapping("goUserCoreT")
	   public ModelAndView goUserCoreT(HttpServletRequest request){
		  final String openid=(String) request.getSession().getAttribute("openid");
		  
		  System.out.println("------------------------openid:---"+openid);
		  UserCorepage ucp= userService.getUserCorepage(openid);
		  request.getSession().setAttribute("userid",ucp.getUserid());
		  
		  
		  ModelAndView mv=new ModelAndView("usercore","ucp",ucp);
		  return mv;
	   }
	   //用户扫码进去
	   @RequestMapping("sys")
	   public ModelAndView sys(String code ,HttpServletRequest request){
		   
		  Map<String, Object> map=new HashMap<String, Object>();
		  map.put("code", code);
		  ModelAndView mv=new ModelAndView("user/sys_success",map);
		  return mv;
	   }
	   
	   
	   
	   
	   
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goUserinfo 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @RequestMapping("goUserinfo")
	   public ModelAndView goUserinfo(HttpServletRequest request,HttpServletResponse response){

		  int userid=(Integer) request.getSession().getAttribute("userid");
		  WeixinUserInfo userinfo= userService.getUserInfo(userid);
		  ModelAndView mv=new ModelAndView("user/userinfo","userinfo",userinfo);
		return mv;
	   }
	   /**
	    * 
	    * @param userinfo
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goUserinfo 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param userinfo
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @RequestMapping("updateUserinfo")
	   public ModelAndView goUserinfo(WeixinUserInfo userinfo, HttpServletRequest request,HttpServletResponse response){
		   userService.updateUserInfo(userinfo);
		
		return goUserCoreT(request);
	   }
	   /**
	    * 
	    * @param msb
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMsb 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param msb
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @RequestMapping("goMsb")
	   public ModelAndView goMsb(int msb,HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
			   System.out.println(userid);
			 List<UserMsbRecord> consume=ms.getConsume(userid);
			 List<UserMsbRecord> deal=ms.getDeal(userid);
			 List<UserMsbRecord> task=ms.getTaskAward(userid);
			   Map<String,Object> m=new HashMap<String, Object>();
			   m.put("msb", msb);
			   m.put("consume", consume);
			   m.put("deal", deal);
			   m.put("task", task);
		   ModelAndView mv=new ModelAndView("user/msb",m);
		return mv;
	   }
	   /**
	    * 
	    * @param msb
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goStoreMsb 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param msb
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @RequestMapping("goStoreMsb")
	   public ModelAndView goStoreMsb(int msb,HttpServletRequest request,HttpServletResponse response){
			 int userid=(Integer) request.getSession().getAttribute("userid");
			 List<StoreMsb> sm=ms.getStoreMsbList(userid);
			   Map<String,Object> m=new HashMap<String, Object>();
			   m.put("storemsb", sm);
			   m.put("msb",msb);
		   ModelAndView mv=new ModelAndView("user/storemsb",m);
		return mv;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMsbZs 
	   * @Description: TODO(枚士币赠送页面
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @RequestMapping("goMsbZs")
	   public ModelAndView goMsbZs(HttpServletRequest request,HttpServletResponse response){
			 int userid=(Integer) request.getSession().getAttribute("userid");
			 List<StoreMsb> sm=ms.getMsbList(userid);
			 Map<String,Object> m=new HashMap<String, Object>();
			 m.put("msb", sm);
		     ModelAndView mv=new ModelAndView("user/zsmsb",m);
		return mv;
	   }
	   /**
	    * 
	    * @param tel
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goCheckUser 
	   * @Description: TODO(检查用户是否存在
	   * @param @param tel
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return UserHeadinfo    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("goCheckUser")
	   public UserHeadinfo goCheckUser(String tel,HttpServletRequest request,HttpServletResponse response){
		   return userService.getHeadinfoByTel(tel);
	   }
	   
	   /**
	    * 
	    * @param tel
	    * @param ids
	    * @param nums
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: msbZs 
	   * @Description: TODO枚士币转赠
	   * @param @param tel
	   * @param @param ids
	   * @param @param nums
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return int    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("msbZs")
	   public int  msbZs(String tel,String ids,String nums,HttpServletRequest request,HttpServletResponse response){
			  int re=0;
			  UserHeadinfo u=userService.getHeadinfoByTel(tel);
			  String[] id=ids.split(",");
			  String[] num=nums.split(",");
			  for (int i = 0; i < id.length; i++) {
				  UserMsbRecord um=new UserMsbRecord();
				  um.setTouserid(u.getId());
				  um.setNum(Integer.parseInt(num[i]));
				  um.setStoreid(Integer.parseInt(id[i]));
				  um.setType(0);
				  um.setTimec(getNowDateTime());
				  re=ms.addMsbDeal(um);
			}
		   return re;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMyWz 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("goMyWz")
	   public ModelAndView goMyWz(HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
		List<WzInfo> wzs= userService.getWzList(userid);
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("wzs", wzs);
		ModelAndView mv=new ModelAndView("user/mywz",m);
		return mv;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMyCollect 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("goMyCollect")
	   public ModelAndView goMyCollect(HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
			  System.out.println(userid+"***************************************");
		List<CollectInfo> cs= userService.getUserCollect(userid);
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("collects", cs);
		ModelAndView mv=new ModelAndView("user/mycollect",m);
		return mv;
	   }
	   /**
	    * 
	    * @param storeid
	    * @param chefid
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: cancelCollect 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param storeid
	   * @param @param chefid
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return int    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("cancelCollect")
	   public int cancelCollect(int storeid,int chefid,HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
		int i= userService.updateCollect(userid, storeid, chefid, 1);
		return i;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMyHistory 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   
	   @ResponseBody
	   @RequestMapping("goMyHistory")
	   public ModelAndView goMyHistory(HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
			  System.out.println(userid+"***************************************");
		List<CollectInfo> hs= userService.getUserHistory(userid);
		//System.out.println(cs.size()+","+cs.get(0).getAddress()+cs.get(0).getName()+cs.get(0).getId());
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("historys", hs);
		ModelAndView mv=new ModelAndView("user/myhistory",m);
		return mv;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: clearHistory 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return int    返回类型 
	   * @throws
	    */
	   
	   @ResponseBody
	   @RequestMapping("clearHistory")
	   public int clearHistory(HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
		int i= userService.updateAllHistory(userid);
		//System.out.println(cs.size()+","+cs.get(0).getAddress()+cs.get(0).getName()+cs.get(0).getId());
		return i;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: goMyNotice 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return ModelAndView    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("goMyNotice")
	   public ModelAndView goMyNotice(HttpServletRequest request,HttpServletResponse response){
		int userid=(Integer) request.getSession().getAttribute("userid");
		List<UserNotice> hs= userService.getUserNoticeList(userid);
		//System.out.println(cs.size()+","+cs.get(0).getAddress()+cs.get(0).getName()+cs.get(0).getId());
		Map<String,Object> m=new HashMap<String, Object>();
		m.put("notices", hs);
		ModelAndView mv=new ModelAndView("user/mynotice",m);
		return mv;
	   }
	   /**
	    * 
	    * @param status
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: updateStatus 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param status
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return int    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("updateStatus")
	   public int updateStatus(int status,HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
		int i= userService.updateNoticeStatus(userid, status);
		//System.out.println(cs.size()+","+cs.get(0).getAddress()+cs.get(0).getName()+cs.get(0).getId());
		return i;
	   }
	   /**
	    * 
	    * @param request
	    * @param response
	    * @return
	    *//** 李飞
	   * @Title: clearStatus 
	   * @Description: TODO(这里用一句话描述这个方法的作用) 
	   * @param @param request
	   * @param @param response
	   * @param @return    设定文件 
	   * @return int    返回类型 
	   * @throws
	    */
	   @ResponseBody
	   @RequestMapping("clearNotice")
	   public int clearStatus(HttpServletRequest request,HttpServletResponse response){
			  int userid=(Integer) request.getSession().getAttribute("userid");
		int i= userService.updateNoticeByUserid(userid);
		//System.out.println(cs.size()+","+cs.get(0).getAddress()+cs.get(0).getName()+cs.get(0).getId());
		return i;
	   }
	   
	   
	   private String getNowDateTime(){
		   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   return df.format(new Date());
	   }
	   
	   
	   
	   
}
