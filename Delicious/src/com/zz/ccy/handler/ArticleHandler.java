package com.zz.ccy.handler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zz.ccy.entity.BillAndInfo;
import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.UploadStatus;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.service.ArticleService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.AdvancedUtil;

/**
 * @ClassName:ArticleHandler
 * @Description:TODO 
 * @author David
 */
@Controller
@RequestMapping("/articleHandler")
public class ArticleHandler {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
    
	@RequestMapping("/articleIntroduce")
	public String articleIntroduce(@ModelAttribute("userId") Integer userId,Map<String,Object> map){
    	//����openId���û�userid��ѯ����
//    	int userId=userService.getUserIdByOpenId(openId);
    	map.put("userId",userId);
		return "article_introduce";
	}
	/**
	 * ��λ�ĵ�д����ҳ��
	 * @return
	 */
	@RequestMapping("/writeArticleUI")
	public String writeArticleUI(@RequestParam("userId") Integer userId,Map<String,Object> map){
	    map.put("userId",userId);	
		return "writeArticleUI";
	}
	/**
	 * ��������
	 * ����ɹ���ת���ϴ���Ʊҳ��
	 */
	@ResponseBody
	@RequestMapping("/saveArticleContent")
	public int saveArticleContent(@RequestParam("articleTitle") String articleTitle,@RequestParam("articleContent") String articleContent,
			@RequestParam("userId") Integer userId,Map<String,Object> map){
		int articleId = articleService.saveArticleContent(articleTitle,articleContent,userId);
		map.put("userId",userId);
	    map.put("articleId",articleId);
	    System.out.println(articleId);
		return articleId;
	}
	/**
	 * ��λ���ϴ���Ʊҳ��
	 * �ϴ���Ʊҳ����Я���� ����id ����Ʊ�Ǻ���ƪ���¹���
	 */
	@RequestMapping("/uploadBillUI")
	public String uploadBillUI(@RequestParam("userId") Integer userId,@RequestParam("articleId") Integer articleId,Map<String,Object> map){
		map.put("articleId",articleId);
	   return "uploadBill";
	}
	/**
	 * ������Ȩ  snsapi_base��֤�ص�
	 * @param code
	 * @param map
	 * @return
	 */
	@RequestMapping("/articleOauthBaseH5")
    public String articleOauthBaseH5(@RequestParam("code") String code,RedirectAttributes attr,
    		Map<String,Object> map,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		Cookie cookie =	getCookieByName(request,"code");
		if(cookie!=null){
			System.out.println("code��Ϊ��...");
		    Cookie openIdCookie = getCookieByName(request,"openId");
		    if(openIdCookie!=null){
		    	System.out.println("openId��Ϊ��");
		    }
			//����openid��ѯ�û��Ƿ��Ѿ������ݿ���
			if(articleService.checkUserIsExistByOpenId(openIdCookie.getValue())){
				int userId=userService.getUserIdByOpenId(openIdCookie.getValue());
				attr.addFlashAttribute("userId",userId);
				//ֱ����ת��ҵ��ҳ��
				return "redirect:/articleHandler/articleIntroduce";
			}else{//����snsapi_userinfoģʽ��Ȩ
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}               
		}else{
			System.out.println("code��...");
			OauthAccessToken accessToken=AdvancedUtil.getOauthAccessToken(code);
			Cookie c=new Cookie("code",code);
			c.setMaxAge(60);
			response.addCookie(c);
			Cookie openIdCookie=new Cookie("openId",accessToken.getOpenId());
			openIdCookie.setMaxAge(60);
			response.addCookie(openIdCookie);
			//����openid��ѯ�û��Ƿ��Ѿ������ݿ���
			if(articleService.checkUserIsExistByOpenId(accessToken.getOpenId())){
				int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
				attr.addFlashAttribute("userId",userId);
				//ֱ����ת��ҵ��ҳ��
				return "redirect:/articleHandler/articleIntroduce";
			}else{//����snsapi_userinfoģʽ��Ȩ
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}               
		}		
    }
	/**
	 * ������Ȩ snsapi_userinfo�ص�ҳ��
	 * @param code
	 * @param map
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/articleOauthUserInfoH5")
    public String articleOauthUserInfoH5(HttpServletRequest request,@RequestParam("code") String code,Map<String,Object> map,RedirectAttributes attr){
		System.out.println("���»�ȡ�û�������Ϣ");
		OauthAccessToken accessToken=AdvancedUtil.getOauthAccessToken(code);
		//����accessToken ��openId��ȡ�û�������Ϣ
		if(accessToken.getAccessToken()!=null){
			WeixinUserInfo userInfo=AdvancedUtil.getUserInfoByAccessToken(accessToken.getAccessToken(),accessToken.getOpenId());
	
			int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
			map.put("userId",userId);
			map.put("nickname",userInfo.getNickname());
			attr.addFlashAttribute("userId",userId);
		}else{
			System.out.println("OauthAccessTokenΪ��");
		}
		return "redirect:/articleHandler/articleIntroduce";       
    }
	/**
	 * ����ҳ���ϴ���Ƭ
	 */
	@ResponseBody
	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	public String uploadImage(@RequestParam("file") MultipartFile image,Map<String,Object> map,HttpServletRequest request){
		String jsonString=null;
		  try{  
			 String fileName=System.currentTimeMillis()+".jpg";
			 String basePath="/upload";
			 String filePathName= request.getSession().getServletContext().getRealPath(basePath);
	         String filePath=filePathName+"\\"+fileName;
	         File f = new File(filePath);    
             if(!f.exists()){    
                f.mkdirs();     
             }  
             image.transferTo(f);
             UploadStatus uploadStatus=new UploadStatus();
             uploadStatus.setStatus(1);
             uploadStatus.setUrl("http://www.cnmjw.com.cn/Delicious/upload/"+fileName);
             
             ObjectMapper objectMapper = new ObjectMapper();
             jsonString=objectMapper.writeValueAsString(uploadStatus);
	       }catch(Exception e){  
		       e.printStackTrace();  
	       }  
		  return jsonString;
	}
	/**
	 * �ϴ���Ʊ��������Ϣ
	 * @param file ���ļ�
	 */
	@RequestMapping("/uploadBillAndInfo")
	public String uploadBillAndInfo(@RequestParam("pictures") MultipartFile[] pictures,@RequestParam("articleId") Integer articleId,@RequestParam(value="shopName",required=true) String shopName,
			@RequestParam(value="shopAddress",required=true) String shopAddress,@RequestParam(value="shopDesc",required=false) String shopDesc,
			@RequestParam(value="shopCulture",required=false) String shopCulture,@RequestParam(value="shopPhone",required=false) String shopPhone,
		HttpServletRequest request){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		BillAndInfo billAndInfo=new BillAndInfo();
		try{  
			for(MultipartFile file:pictures){
				System.out.println("ͼƬ��"+file.getName());
			   String fileName=System.currentTimeMillis()+".jpg";
			   String basePath="/upload/bill/"+articleId+"/";
			   String filePathName= request.getSession().getServletContext().getRealPath(basePath);
	           String filePath=filePathName+"\\"+fileName;
	           File f = new File(filePath);    
               if(!f.exists()){    
                  f.mkdirs();     
               }  
               file.transferTo(f);
               billAndInfo.setBillPath(billAndInfo.getBillPath()+"http://www.cnmjw.com.cn/Delicious/upload/"+fileName+";");
			}
	       }catch(Exception e){  
		       e.printStackTrace();  
	       }  
		billAndInfo.setArticleId(articleId);
		billAndInfo.setShopName(shopName);
		billAndInfo.setShopAddress(shopAddress);
		billAndInfo.setShopDesc(shopDesc);
		billAndInfo.setShopCulture(shopCulture);
		billAndInfo.setShopPhone(shopPhone);
		billAndInfo.setStatus(0);
		billAndInfo.setTimec(format.format(new Date()));
		articleService.saveBillAndInfo(billAndInfo);
		return "billUploadSuccess";
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
	            cookieMap.put(cookie.getName(),cookie);
	        }
	    }
	    return cookieMap;
	}
}
