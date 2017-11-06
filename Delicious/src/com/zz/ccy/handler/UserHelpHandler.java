package com.zz.ccy.handler;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zz.ccy.entity.TbUserOpenId;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WeixinUserList;
import com.zz.ccy.service.UserHelpService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.MessageUtil;
import com.zz.ccy.util.SignUtil;

@RequestMapping("/userHandler")
@Controller
public class UserHelpHandler{
	private static Logger logger=Logger.getLogger(UserHelpHandler.class);
	@Autowired
	private UserHelpService userHelpService;
	@Autowired
	private UserService userService;
	   //主接口
	   @RequestMapping("mainInterface")
	   public String mainInterface(HttpServletRequest request,HttpServletResponse response){
		      try{
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				// 微信加密签名
				String signature = request.getParameter("signature");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				PrintWriter out = response.getWriter();
				// 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (timestamp!=null && SignUtil.checkSignature(signature,timestamp,nonce)){
					String method =request.getMethod();
			        if(method.equals("POST")){
			           // 调用parseXml方法解析请求消息
		    		   Map<String, String> requestMap = MessageUtil.parseXml(request);
				       String respXml=userHelpService.processRequest(requestMap);
				       out.print(respXml);
			           out.close();
			           out = null;
				       }else{
				         out.print(echostr);
				         out.close();
				         out = null;
				       }
			        }else{
			            logger.error("无效访问：signature="+signature+";timestamp="+timestamp+";nonce="+nonce);
			        }
		      } catch (Exception e) {
		    	  e.printStackTrace();
		      }
		      return null;
	   }
	   @ResponseBody
	   @RequestMapping("/getAllUsers")
	   public WeixinUserList getAllUsers(){
		   WeixinUserList userList=userService.getAllUsers();
		   System.out.println(userList.getOpenIdList().size());
		   if(userList.getOpenIdList().size()>0){
			   //把数据库中openid表清空
			   userService.deleteAllUser();
		   }
//		   userService.insertTbUserOpenIdBatch(userList.getOpenIdList());
		   if(userList!=null){
			   List<TbUserOpenId> tbOpenIdList=new ArrayList<TbUserOpenId>();
	           for(int i=0;i<userList.getOpenIdList().size();i++){
	        	   System.out.println(userList.getOpenIdList().get(i));
	        	   TbUserOpenId tbUserOpenId=new TbUserOpenId();
	        	   tbUserOpenId.setOpenId(userList.getOpenIdList().get(i));
	        	   tbOpenIdList.add(tbUserOpenId);
	           }
	           userService.insertTbUserOpenIdBatch(tbOpenIdList);
		   }
		   return null;
	   }
	   @ResponseBody
	   @RequestMapping("/getAllUserInfos")
	   public void getAllUserInfos(){
		   WeixinUserList userList=userService.getAllUsers();
		   List<WeixinUserInfo> weixinUserInfoList=new ArrayList<WeixinUserInfo>();
		   if(userList!=null){
			   //删除用户基本信息表中的数据
			   userService.deleteAllUserInfo();
			   for (int i = 0; i < userList.getOpenIdList().size(); i++) {
				   WeixinUserInfo userInfo=userService.getUserInfoByOpenId(userList.getOpenIdList().get(i));
				   if(userInfo.getOpenId()!=null){
					   weixinUserInfoList.add(userInfo);
				   }
			   }
			   userService.insertUserInfoBatch(weixinUserInfoList);
		   }
	   }
}
