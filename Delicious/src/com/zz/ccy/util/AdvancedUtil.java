package com.zz.ccy.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.swetake.util.Qrcode;
import com.zz.ccy.entity.ChatHistory;
import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.WeixinGroup;
import com.zz.ccy.entity.WeixinQRCode;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.entity.WeixinUserList;
import com.zz.ccy.message.resp.Article;
/**
 * 高级接口
 * @author lez
 *
 */
public class AdvancedUtil{
	
	
	
	public void gennireQRCode(String path,String content) throws IOException{
		//创建一个对象
		Qrcode qrcode = new Qrcode();
		//设置二维码的纠错能力
		//L 7% M 15% Q 25% H 30%
		qrcode.setQrcodeErrorCorrect('M');
		//以二进制的形式存储
		qrcode.setQrcodeEncodeMode('B');
		//设置二维码版本 40 1=21*21 2=25*25
		qrcode.setQrcodeVersion(7);
		//字符编码
		 
		byte[] bt= new String(content.getBytes("ISO-8859-1"),"UTF-8").getBytes();
		//创建一个图像数据的缓冲区（创建一张纸）
		BufferedImage image= new BufferedImage(140,140,BufferedImage.TYPE_INT_RGB);
		//创建一个笔
		Graphics2D g=image.createGraphics();
		//设置背景颜色为白色
		g.setBackground(Color.white);
		//填充到纸上来
		g.fillRect(0, 0, 140, 140);
		//设置二维码的前景色-黑色
		g.setColor(Color.gray);
		//javac cavaj
		if(bt.length!=0){
		boolean[][] b= qrcode.calQrcode(bt);
		 for(int i=0;i<b.length;i++){
		for(int j=0;j<b.length;j++){
		if(b[j][i]){
		//根据不布尔数组绘制二维码矩形图 
		g.fillRect(j*3 + 2, i*3 + 2, 3, 3);
		}   
		}
		}
		}
		 
		File f =new File(path);
		ImageIO.write(image, "jpg",f); 
		 
		}
		
	
	
	
	/**
	 * 组装文本客服消息
	 * @param openId
	 *            消息发送对象
	 * @param content
	 *            文本消息内容
	 * @return
	 */
	public String makeTextCustomMessage(String openId, String content) {
		// 对消息内容中的双引号进行转义
		content = content.replace("\"", "\\\"");
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
		return String.format(jsonMsg, openId, content);
	}
	/**
	 * 组装图片客服消息
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public String makeImageCustomMessage(String openId, String mediaId) {
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	/**
	 * 组装语音客服消息
	 * @param openId
	 *            消息发送对象
	 * @param mediaId
	 *            媒体文件id
	 * @return
	 */
	public synchronized String makeVoiceCustomMessage(String openId,String mediaId){
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
		return String.format(jsonMsg, openId, mediaId);
	}
	/**
	 * 组装图文客服消息
	 * @param openId 消息发送对象
	 * @param articleList 图文消息列表
	 * @return
	 */
	public String makeNewsCustomMessage(String openId,List<Article> articleList){
		String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
		jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
		// 将jsonMsg中的picUrl替换为picurl
		jsonMsg = jsonMsg.replace("picUrl", "picurl");
		return jsonMsg;
	}
	/**
	 * 组装获取聊天记录
	 * @param openId 消息发送对象
	 * @param 图文消息列表
	 * @return
	 */
	public String makeChatHistoryMessage(long startTime,long endTime,int msgId,int number){
		String jsonMsg = "{\"starttime\":\"%d\",\"endtime\":\"%d\",\"msgid\":\"%d\",\"number\":\"%d\"}";
		jsonMsg = String.format(jsonMsg,startTime,endTime,msgId,number);
		return jsonMsg;
	}
	/**
	 * 创建临时带参二维码
	 * 
	 * @param accessToken 接口访问凭证
	 * @param expireSeconds 二维码有效时间，单位为秒，最大不超过7天
	 * @param sceneId 场景ID
	 * @return WeixinQRCode
	 */
	public WeixinQRCode createTemporaryQRCode(String accessToken,int expireSeconds,int sceneId){
		WeixinQRCode weixinQRCode = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建临时带参二维码
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));
		if (null != jsonObject) {
			try {
				weixinQRCode = new WeixinQRCode();
				weixinQRCode.setTicket(jsonObject.getString("ticket"));
				weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
				System.out.println("创建临时带参二维码成功 ticket:{} expire_seconds:{}"+","+weixinQRCode.getTicket()+","+weixinQRCode.getExpireSeconds());
			} catch (Exception e) {
				weixinQRCode = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("创建临时带参二维码失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return weixinQRCode;
	}

	/**
	 * 创建永久带参二维码
	 * 
	 * @param accessToken 接口访问凭证
	 * @param sceneId 场景ID
	 * @return ticket
	 */
	public static String createPermanentQRCode(String accessToken, int sceneId) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
		// 创建永久带参二维码
		CommonUtil commonUtil =new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
				System.out.println("创建永久带参二维码成功 ticket:{}"+","+ticket);
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("创建永久带参二维码失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return ticket;
	}

	/**
	 * 根据ticket换取二维码
	 * 
	 * @param ticket 二维码ticket
	 * @param savePath 保存路径
	 */
	public String getQRCode(String ticket, String savePath) {
		String filePath = null;
		// 拼接请求地址
		String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
		CommonUtil commonUtil=new CommonUtil();
		requestUrl = requestUrl.replace("TICKET",commonUtil.urlEncodeUTF8(ticket));
		try {
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 将ticket作为文件名
			filePath = savePath + ticket + ".jpg";

			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			System.out.println("根据ticket换取二维码成功，filePath=" + filePath);
		} catch (Exception e) {
			filePath = null;
			System.out.println("根据ticket换取二维码失败：{}"+","+e);
		}
		return filePath;
	}
	/**
	 * 发送客服消息
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
	 * @return true | false
	 */
	public boolean sendCustomMessage(String accessToken, String jsonMsg) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST", jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("客服消息发送成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("客服消息发送失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
		return result;
	}
	/**
	 * 下载媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件标识
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * @return
	 */
	public synchronized String downloadMedia(String accessToken,String mediaId, String savePath,String Student_OpenId) {
		String filePath = null;
		String filename = null;
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"MEDIA_ID", mediaId);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setReadTimeout(70000);
			conn.setConnectTimeout(70000);
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			CommonUtil commonUtil=new CommonUtil();
			String fileExt = commonUtil.getFileExt(conn.getHeaderField("Content-Type"));
			//判断文件类型 决定是否向下走
			if(fileExt.equals(".mp3")||fileExt.equals(".jpg")){
			// 将mediaId作为文件名
			filePath = savePath + mediaId + fileExt;
			filename = mediaId +fileExt;
			BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			}else if(fileExt.equals(".mp4")){
			//返回信息 暂不支持视频会话
			AdvancedUtil advancedUtil=new AdvancedUtil();
			String jsonTextMsg = advancedUtil.makeTextCustomMessage(Student_OpenId,"暂不支持视频会话");
			sendCustomMessage(accessToken, jsonTextMsg);
			}else{
				AdvancedUtil advancedUtil=new AdvancedUtil();
				String jsonTextMsg = advancedUtil.makeTextCustomMessage(Student_OpenId,"消息发送失败，请使用普通话与老师交流。");
				sendCustomMessage(accessToken, jsonTextMsg);
			}
		} catch (Exception e) {
			filePath = null;
			filename=null;
			System.out.println("下载媒体文件失败：{}"+","+e);
		}
		return filename;
	}
	/**
	 * URL 编码
	 */
	public String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取关注者列表
	 * 
	 * @param accessToken
	 *            调用接口凭证
	 * @param nextOpenId
	 *            第一个拉取的openId，不填默认从头开始拉取
	 * @return WeixinUserList
	 */
	public static WeixinUserList getUserList(String accessToken,String nextOpenId) {
		WeixinUserList weixinUserList = null;
		if (null == nextOpenId)
			nextOpenId = "";
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
		// 获取关注者列表
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				weixinUserList = new WeixinUserList();
				weixinUserList.setTotal(jsonObject.getInt("total"));
				weixinUserList.setCount(jsonObject.getInt("count"));
				weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
				JSONObject dataObject = (JSONObject) jsonObject.get("data");
				weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"),List.class));
			} catch (JSONException e) {
				weixinUserList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("获取关注者列表失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return weixinUserList;
	}
	/**
	 * 获取用户基本信息
	 * 2015年7月29日上午9:11:14
	 * lez
	 */
	public WeixinUserInfo getUserInfo(String accessToken,String openId){
		WeixinUserInfo weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken).replace("OPENID", openId);
		// 获取用户信息
		CommonUtil commonUtil = new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				weixinUserInfo = new WeixinUserInfo();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				weixinUserInfo.setLanguage(jsonObject.getString("language"));
				// 用户头像
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			}catch(Exception e) {
				if(0 == weixinUserInfo.getSubscribe()) {
					System.out.println("用户{}已取消关注"+","+weixinUserInfo.getOpenId());
				}else{
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					System.out.println("获取用户信息失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}
	/**
	 * 获取聊天记录
	 * 2017-03-30 14:58:56
	 * lez
	 */
	public List<ChatHistory> getChatHistory(String accessToken,String jsonMsg){
		List<ChatHistory> chatHistoryList = new ArrayList<ChatHistory>();
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/customservice/msgrecord/getmsglist?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
		// 获取用户信息
		CommonUtil commonUtil = new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",jsonMsg);
        try {
        	JSONArray jsonArray=(JSONArray)jsonObject.get("recordlist");
        	if(jsonArray!=null){
        		for(int i=0;i<jsonArray.size();i++){
                    JSONObject temp = (JSONObject)jsonArray.get(i);
                    ChatHistory chatHistory=new ChatHistory();
                    chatHistory.setOpenid(temp.getString("openid"));
                    chatHistory.setOpercode(temp.getString("opercode"));
                    chatHistory.setText(temp.getString("text"));
                    chatHistory.setWorker(temp.getString("worker"));
                    chatHistory.setTime(temp.getLong("time")+"");
                    chatHistoryList.add(chatHistory);
                 }   
        	}
        }catch(Exception e){
        	e.printStackTrace();
       }
		return chatHistoryList;
	}
	 /**
	  * 上传多媒体文件，返回多媒体文件的MediaId
	  * 2015年7月30日上午8:46:54
	  * lez
	  */
	 public static String uploadMedia(String url,String filePath) throws IOException {  
		    String result = null;  
		    File file = new File(filePath);  
		    if (!file.exists() || !file.isFile()) {  
		    throw new IOException("文件不存在");  
		    }  
		    /** 
		    * 第一部分 
		    */  
		    URL urlObj = new URL(url);  
		    // 连接  
		    HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();  
		    /** 
		    * 设置关键值 
		    */  
		    con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
		    con.setDoInput(true);  
		    con.setDoOutput(true);  
		    con.setUseCaches(false); // post方式不能使用缓存  
		    // 设置请求头信息  
		    con.setRequestProperty("Connection", "Keep-Alive");  
		    con.setRequestProperty("Charset", "UTF-8");  
		    // 设置边界  
		    String BOUNDARY = "----------" + System.currentTimeMillis();  
		    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
		    // 请求正文信息  
		    // 第一部分：  
		    StringBuilder sb = new StringBuilder();  
		    sb.append("--"); // 必须多两道线  
		    sb.append(BOUNDARY);  
		    sb.append("\r\n");  
		    sb.append("Content-Disposition: form-data;name=\"file\";filename=\""  
		    + file.getName() + "\"\r\n");  
		    sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		    byte[] head = sb.toString().getBytes("utf-8");  
		    // 获得输出流  
		    OutputStream out = new DataOutputStream(con.getOutputStream());  
		    // 输出表头  
		    out.write(head);  
		    // 文件正文部分  
		    // 把文件已流文件的方式 推入到url中  
		    DataInputStream in = new DataInputStream(new FileInputStream(file));  
		    int bytes = 0;  
		    byte[] bufferOut = new byte[1024];  
		    while ((bytes = in.read(bufferOut)) != -1) {  
		    out.write(bufferOut, 0, bytes);  
		    }  
		    in.close();  
		    // 结尾部分  
		    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
		    out.write(foot);  
		    out.flush();  
		    out.close();  
		    StringBuffer buffer = new StringBuffer();  
		    BufferedReader reader = null;  
		    try {  
		    // 定义BufferedReader输入流来读取URL的响应  
		    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
		    String line = null;  
		    while ((line = reader.readLine()) != null) {  
		    //System.out.println(line);  
		    buffer.append(line);  
		    }  
		    if(result==null){  
		    result = buffer.toString();  
		    }  
		    } catch (IOException e) {  
		    System.out.println("发送POST请求出现异常！" + e);  
		    e.printStackTrace();  
		    throw new IOException("数据读取异常");  
		    } finally {  
		    if(reader!=null){  
		    reader.close();  
		    }  
		    }  
		    JSONObject jsonObj = JSONObject.fromObject(result);  
		    String mediaId = jsonObj.getString("media_id");  
		    return mediaId;  
		    }  
	 
	    /**
		 * 上传图文素材
		 * 2015年7月29日下午12:47:49
		 * lez
		 */
	 public static String uploadNews(String uploadurl,String access_token,String data){
		 HttpClient client = new HttpClient();
         String posturl = String.format("%s?access_token=%s", uploadurl,access_token);
         PostMethod post = new PostMethod(posturl);
         post.setRequestHeader("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:30.0) Gecko/20100101 Firefox/30.0");
         post.setRequestHeader("Host","file.api.weixin.qq.com");
         post.setRequestHeader("Connection", "Keep-Alive");
         post.setRequestHeader("Cache-Control", "no-cache");
         post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");//保证图文不会乱码
         String result = null;
         try{
             post.setRequestBody(data);
             int status = client.executeMethod(post);
             if (status == HttpStatus.SC_OK){
                String responseContent = post.getResponseBodyAsString();
                System.out.println(responseContent);
                JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
                JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
                 if (json.get("errcode") == null){// 正确 { "type":"news", "media_id":"CsEf3ldqkAYJAU6EJeIkStVDSvffUJ54vqbThMgplD-VJXXof6ctX5fI6-aYyUiQ","created_at":1391857799}
                     result = json.get("media_id").getAsString();
                 }
             }
         }
         catch (Exception e){
             e.printStackTrace();
        }
         finally{
             return result;
         }
     }
     /**
      * 根据分组进行图文群发
      * 2015年7月29日下午12:47:49
      * lez
      */
	 public void sendAllByGroup(String accessToken,int groupId,String mediaId,String sendType){
		 String requestUrl="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	     requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
	     // 需要提交的json数据
			
		 JSONObject json11=new JSONObject();
		 json11.put("is_to_all",false);
		 
		 JSONObject json12=new JSONObject();
		 json12.put("group_id",groupId);
		 
		 JSONObject json21=new JSONObject();
		 json21.put("media_id",mediaId);
		 
		 JSONObject json1=new JSONObject();
		 json1.put("filter",json11);
		 json1.put("filter",json12);
		 json1.put("mpnews",json21);
		 json1.put("msgtype",sendType);
		 
		 CommonUtil commonUtil = new CommonUtil();
		 JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST",json1.toString());
		 if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				System.out.println("群发成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("群发失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
	 }
	 /**
	  * 多图文预览接口
	  * 2015年7月29日下午12:47:49
	  * lez
	  */
	 public void previewNews(String accessToken,String mediaId,String openId,String msgtype){
		 String requestUrl="https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	     requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
	     // 需要提交的json数据
	     JSONObject json11=new JSONObject();
		 json11.put("touser",openId);
		 
		 JSONObject json12=new JSONObject();
		 json12.put("media_id",mediaId);
		 
		 json11.put("mpnews", json12);
		 json11.put("msgtype", msgtype);
		 
		 CommonUtil commonUtil = new CommonUtil();
		 JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",json11.toString());
		 if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				System.out.println("多图文预览发送成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("多图文预览发送失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
	 }
	 /**
	  * 文本群发接口
	  * 2015年8月1日下午15:48:49
	  * lez
	  */
	 public void sendText(String accessToken,int groupId,String content){
		 String requestUrl="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	     requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
	     JSONObject json11=new JSONObject();
		 json11.put("is_to_all",false);
		 
		 JSONObject json12=new JSONObject();
		 json12.put("group_id",groupId);
		 
		 JSONObject json21=new JSONObject();
		 json21.put("content",content);
		 
		 JSONObject json1=new JSONObject();
		 json1.put("filter",json11);
		 json1.put("filter",json12);
		 json1.put("text",json21);
		 json1.put("msgtype","text");
		 
		 CommonUtil commonUtil = new CommonUtil();
		 JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",json1.toString());
		 if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				System.out.println("文本群发发送成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("文本群发发送失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
	 }
	 /**
	 * 添加客服账号
	 * 2015年7月29日下午12:47:49
	 * lez
	 */
	public void addKefu(String accessToken,String jsonMsg){
		String requestUrl="https://api.weixin.qq.com/customservice/kfaccount/add?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
		CommonUtil commonUtil = new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST",jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				System.out.println("客服账号添加成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("客服账号添加失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
	}
	/**
	 * 修改客服账号
	 * 2015年7月29日下午12:47:49
	 * lez
	 */
	public void updateKefu(String accessToken,String jsonMsg){
		String requestUrl="https://api.weixin.qq.com/customservice/kfaccount/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken);
		CommonUtil commonUtil = new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl, "POST",jsonMsg);
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				System.out.println("客服账号修改成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("客服账号修改失败 errcode:{} errmsg:{}"+","+ errorCode+","+ errorMsg);
			}
		}
	}
	/**
	 * 创建分组
	 * 2015年7月30日下午3:36:38
	 * lez
	 */
	public JSONObject createGroup(String accessToken,String groupName){
		  String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token="+accessToken;
		  JSONObject j = new JSONObject();
		  JSONObject jsonObject =null;
		  try {
		    j.put("name", "test1");
		    j.put("group",j);
		  CommonUtil commonUtil = new CommonUtil();
		  System.out.println(j);
	      jsonObject = commonUtil.httpsRequest(requestUrl,"POST",j.toString());
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  return jsonObject;
		}
	/**
	 * 查询分组
	 * 2015年7月30日下午4:37:36
	 * lez
	 */
	public static List<WeixinGroup> getGroups(String accessToken) {
		List<WeixinGroup> weixinGroupList = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 查询分组
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"GET",null);
		if (null != jsonObject) {
			try {
				weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"), WeixinGroup.class);
			} catch (JSONException e) {
				weixinGroupList = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("查询分组失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return weixinGroupList;
	}
	/**
	 * 查询用户所在分组名
	 * 
	 * @param accessToken 接口访问凭证
	 * @param groupId 分组id
	 * @param groupName 修改后的分组名
	 * @return true | false
	 */
	public int getGroupId(String accessToken,String openId) {
		int groupId = 0;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonStr=new JSONObject();
		jsonStr.put("openid", openId);
		// 查询分组
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",jsonStr.toString());
		if (null != jsonObject) {
			try {
				groupId=jsonObject.getInt("groupid");
			} catch (JSONException e) {
				groupId = 0;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				System.out.println("查询分组失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return groupId;
	}
	/**
	 * 修改分组名
	 * 
	 * @param accessToken 接口访问凭证
	 * @param groupId 分组id
	 * @param groupName 修改后的分组名
	 * @return true | false
	 */
	public static boolean updateGroup(String accessToken,int groupId,String groupName) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
		// 修改分组名
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData, groupId, groupName));
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("修改分组名成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("修改分组名失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return result;
	}
	   /** 
	30.     * 发起https请求并获取结果 
	31.     *  
	32.     * @param requestUrl 请求地址 
	33.     * @param requestMethod 请求方式（GET、POST） 
	34.     * @param outputStr 提交的数据 
	35.     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
	36.     */  
		public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			//httpUrlConn.connect();
			

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			System.out.println(jsonObject);
		} catch (ConnectException ce) {
			//log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			//log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	/**
	 * 移动用户分组
	 * 
	 * @param accessToken 接口访问凭证
	 * @param openId 用户唯一标示
	 * @param groupId 分组id
	 * @return true | false
	 */
	public static boolean moveGroup(String accessToken,String openId,int groupId) {
		boolean result = false;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData = "{\"openid\":%s, \"to_groupid\": \"%d\"}";
		// 修改分组名
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",String.format(jsonData,openId,groupId));
		if (null != jsonObject) {
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result = true;
				System.out.println("移动用户分组成功 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			} else {
				System.out.println("移动用户分组失败 errcode:{} errmsg:{}"+","+errorCode+","+errorMsg);
			}
		}
		return result;
	}
	/**
	 * 获取关注着列表
	 * 2015年7月30日下午5:45:32
	 * lez
	 */
	public void getUserList(String accessToken){
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
		// 修改分组名
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"POST",null);
		System.out.println(jsonObject);
	}
	/**
	 * 通过code换取网页授权access_token
	 * @param code网页授权获得的code
	 */
	public static OauthAccessToken getOauthAccessToken(String code){
		OauthAccessToken accessToken=new OauthAccessToken();
		String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
		requestUrl = requestUrl.replace("APPID",Constant.appid).replace("SECRET",Constant.appsecret)
				.replace("CODE",code);
		// 发送客服消息
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject=null;
		try {
		    jsonObject = commonUtil.httpsRequest(requestUrl,"GET",null);
			if (null != jsonObject) {
				accessToken.setAccessToken(jsonObject.getString("access_token"));
				accessToken.setExpiresIn(jsonObject.getString("expires_in"));
				accessToken.setOpenId(jsonObject.getString("openid"));
				accessToken.setRefreshToken(jsonObject.getString("refresh_token"));
				accessToken.setScope(jsonObject.getString("scope"));
			}
			return accessToken;
		} catch (Exception e) {
			System.out.println(jsonObject.getString("errcode")+",,"+jsonObject.getString("errmsg"));
		   e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据accesstoken和openid获取用户基本信息
	 * @param args
	 */
	public static WeixinUserInfo getUserInfoByAccessToken(String accessToken,String openId){
		WeixinUserInfo userInfo=new WeixinUserInfo();
		String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
		requestUrl = requestUrl.replace("ACCESS_TOKEN",accessToken).replace("OPENID",openId);
		// 发送客服消息
		CommonUtil commonUtil=new CommonUtil();
		JSONObject jsonObject = commonUtil.httpsRequest(requestUrl,"GET",null);
		if (null != jsonObject) {
			userInfo.setOpenId(jsonObject.getString("openid"));
			userInfo.setNickname(jsonObject.getString("nickname"));
			userInfo.setSex(jsonObject.getInt("sex"));
			userInfo.setProvince(jsonObject.getString("province"));
			userInfo.setCity(jsonObject.getString("city"));
			userInfo.setCountry(jsonObject.getString("country"));
			userInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
		}
		return userInfo;
	}
	public static void main(String args[]) {
		// 第三方用户唯一凭证
		//String appId = "wx7b2911edb9b215d5";
		// 第三方用户唯一凭证密钥
		//String appSecret = "8f93d3c1bc5a4b3ba408543cad3bcc8e";
		// 获取接口访问凭证
		//CommonUtil commonUtil=new CommonUtil();
		//String accessToken =commonUtil.getToken(appId, appSecret).getAccessToken();
		//System.out.println(accessToken);
		//String accessToken ="8XU60eHGyPtsP_ZUsbnk7WzvJJ0-R926FySI1fON9aMYdlnxecbZQ5BOHaXL-c-7hyrbaT2huJagB5rOEZQxIS8uYl4Br23MMtGz6e5iCzQ";
        
		//测试添加客服账号
		AdvancedUtil advancedUtil=new AdvancedUtil();
		//String jsonMsg = "{\"kf_account\":\"002@maijiawangdyh\",\"nickname\":\"小萌\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\"}";
		//advancedUtil.addKefu("DsasEO9aFOc9TfJuhTFYG5-vLqulLJrt0LX_WMJaCiWbTghmcokkxehSk7RI9Go_OxIvhwUgfQHVVfpTthhI4Lf41DTvThKEGmmn84n5Dz0",jsonMsg);
		
		//测试修改客服账号
		//String jsonMsg = "{\"kf_account\":\"002@maijiawangdyh\",\"nickname\":\"志玲\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\"}";
		//advancedUtil.updateKefu("DsasEO9aFOc9TfJuhTFYG5-vLqulLJrt0LX_WMJaCiWbTghmcokkxehSk7RI9Go_OxIvhwUgfQHVVfpTthhI4Lf41DTvThKEGmmn84n5Dz0",jsonMsg);
		
		//测试上传图文素材
		 //构造数据  mediaId="EklJ2V98tU1ESmxANZ4DDEtHdmB0miksY__bj9mcsxl7mbAXlJ1TldLi0c9R7wRX"
		//  图文mediaId   2CTgjCGV8F3aVdf_YMFNyBekwS8WVRSj_8_TNPARqZrDxJq2XhogJJWUPVgV3jFm
		//  内容之所用图片
//		String content="<html><img alt='' src='http://103.22.191.197/weixindyh/images/c.jpg' style='width:100%;'><h1>欢迎来到买家网</h1><span style='color:red'>买家网专为买家服务<br>"
//				+ "微信扫一扫送早餐</span>"
//				+ "<img alt='' src='http://mmbiz.qpic.cn/mmbiz/brzhH1ic7JwCy4xHmlLmw8sGIO3At48fEUyHORgdTUkoicSAS7cbichWhDlJ5ao32bSbiaXqa3EEjYr589nricsCvJQ/0'>"
//				+ "</html>";
//		
//		String accessToken="7oacIwuPl4FF2nHkFi_BMZKrYUO_WAZNM3piZ7JrEiJ1ntjl2WUZqPnwb-gjBvfKTJkg5Hmdfucy6m6Fj7C2Gch8Ay8CuTi6By9syPqmkKI";
//        if (accessToken != null){// token成功获取
//            System.out.println(accessToken);
//            String id ="K4bAf5PyMdqus870YeYBXIUvWQk7eV7MK7g6yaiZyjOBIMQDS75SxHC2a1EyZbEZ"; //uploadImage(UPLOAD_IMAGE_URL, accessToken, "image",file);// java微信接口之三—上传多媒体文件 可获取
//            if (id != null){ 
//                //构造数据
//                Map map = new HashMap();
//                map.put("thumb_media_id","zUpUvmou4nrXE0yDxhoRJmd5afOJkJm7VjJyBHSr0BXw7axBj9oBwsms_JoKRIFm");
//                map.put("author", "");
//                map.put("content_source_url", "http://103.22.191.197/weixindyh/news1.html");
//                map.put("title", "好消息！今天起，这些钱可以省了↓↓↓");
//                map.put("content",content);
//                map.put("digest", null);
//                map.put("show_cover_pic","0");
//
//                Map map2 = new HashMap();
//                map2.put("thumb_media_id","zUpUvmou4nrXE0yDxhoRJmd5afOJkJm7VjJyBHSr0BXw7axBj9oBwsms_JoKRIFm");
//                map2.put("author",null);
//                map2.put("content_source_url", "http://103.22.191.197/weixindyh/news1.html");
//                map2.put("title", "【紧急提醒】已泛滥成灾!去海南当心这种带毒液的红火蚁!");
//                map2.put("content",content);
//                map2.put("digest", null);
//                map2.put("show_cover_pic","0");
//                
//                Map map3 = new HashMap();
//                map3.put("thumb_media_id","ehHPv_mxB8OU8_hJeEPbAeNXibnvUuX0VRG2zEzBGGkNW7GSdk7hLDaU4HvdtAsF");
//                map3.put("author",null);
//                map3.put("content_source_url","http://103.22.191.197/weixindyh/news1.html");
//                map3.put("title", "【创业经】小村里开了家淘宝代购店:一天竟卖出47万!");
//                map3.put("content",content);
//                map3.put("digest", null);
//                map3.put("show_cover_pic","0");
//                
//                Map map4 = new HashMap();
//                map4.put("thumb_media_id","ehHPv_mxB8OU8_hJeEPbAeNXibnvUuX0VRG2zEzBGGkNW7GSdk7hLDaU4HvdtAsF");
//                map4.put("author",null);
//                map4.put("content_source_url","http://103.22.191.197/weixindyh/news1.html");
//                map4.put("title", "【看点】大剧透！《黄金线路》今天开拍 幕后花絮都在这里了☟");
//                map4.put("content",content);
//                map4.put("digest",null);
//                map4.put("show_cover_pic","0");
//                
//                Map map5 = new HashMap();
//                map5.put("thumb_media_id","pgqALvgAWoHBePNT8mExtr-QkNEp45_MytSwPE9WtpS-1ymDJ6Ju2CcFYoGKww14");
//                map5.put("author", "买家网编辑");
//                map5.put("content_source_url","http://103.22.191.197/weixindyh/news1.html");
//                map5.put("title", "【荐读】周末怎么过?8位CEO是这样安排的!");
//                map5.put("content",content);
//                map5.put("digest",null);
//                map5.put("show_cover_pic","0");
//
//
//                Map map6 = new HashMap();
//                List<Map> list = new ArrayList<Map>();
//                list.add(map);
//                list.add(map2);
//                list.add(map3);
//                list.add(map4);
//                list.add(map5);
//                map6.put("articles", list);
//
//                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//                String articleListStr = gson.toJson(map6);
//                System.out.println(articleListStr);
//                String mediaId = uploadNews("https://api.weixin.qq.com/cgi-bin/media/uploadnews",accessToken,articleListStr);
               // if (mediaId != null){
                    //多图文预览接口
//                  advancedUtil.previewNews("j-_CaAJS8hh_nyh563Jru366yUUVYnY9PKQ4gp15zLA8deLLQ9oNORSqCdRpnL6Ku-yLZvZE0hE4Olyi3KZ0ccBSCeUeOc76mixVexMXhgUMTYiADAPWB"
//                		  ,"mHOSPFakqOd7LjWJYpll-P5YQNTjDFxYznpQDz3la5aJXnjgJxUTMn7ssoAAUAvR","oYPrFs-aYWpn1IOnkI1v0hq03uUE","mpnews");
              //  }
//            }
//        }

		
		//测试群发文本
		
		//测试创建分组
      //  JSONObject jsonObject=advancedUtil.createGroup("zD70FsIypClruJ7uMzAzr2DcUJ40Dc36jOZRE_SqMD0pULJJ_2zZpPA8gp_T2T0F7WUvdHMl5kMWqz1FglmvF5JC0UMqUSdM9Llt11IkW-U","老师1");
	//	System.out.println(jsonObject);
	
		//测试查询分组
//		Token token=new Token();
//		List<WeixinGroup> groupList=advancedUtil.getGroups("EPZnfNFoHAXOiWaklS0MZ_f4o0NEDdVENoM8CVQzCHInxDPqR0yfgyvFs-UinbS_Nxkfc38ighFFRTdV8Ro830ojjFUHpfuBr1myFRBMZZM");
//		// 循环输出各分组信息
//		for (WeixinGroup group : groupList) {
//			System.out.println(String.format("ID：%d 名称：%s 用户数：%d", group.getId(), group.getName(), group.getCount()));
//		}
		
		//测试修改分组名
	//	boolean b=advancedUtil.updateGroup("zD70FsIypClruJ7uMzAzr2DcUJ40Dc36jOZRE_SqMD0pULJJ_2zZpPA8gp_T2T0F7WUvdHMl5kMWqz1FglmvF5JC0UMqUSdM9Llt11IkW-U",104,"后勤小组");
	//	if(b){
	//		System.out.println("修改成功");
	//	}else{
	//		System.out.println("修改失败");
	//	}
		
		//测试获取用户所在分组
	//	int id=advancedUtil.getGroupId("zD70FsIypClruJ7uMzAzr2DcUJ40Dc36jOZRE_SqMD0pULJJ_2zZpPA8gp_T2T0F7WUvdHMl5kMWqz1FglmvF5JC0UMqUSdM9Llt11IkW-U","oYPrFs1L-jj_MBQ9YKMeaeJo_71I");
	//	System.out.println(id);
		
		//测试移动用户分组
//		boolean b=advancedUtil.moveGroup("Ce3eAVp37qy2H8pjWe0AtvPthyJvznjj7r-U04luao1m2up4aD4jRWFyLh4K1gdKWSB75OoZMU5kkRnhOkN_MYuY-3TywrNJ6tgZbzyDgVo","oYPrFs1L-jj_MBQ9YKMeaeJo_71I",100);
//		if(b){
//			System.out.println("移动用户分组成功");
//		}else{
//			System.out.println("移动用户分组失败");
//		}
		//测试发送图片消息
		//String jsonMsg = advancedUtil.makeImageCustomMessage("oYPrFs1L-jj_MBQ9YKMeaeJo_71I", "EoRMIBcmRPUOJ8Rdjq0l-oM7x-Ib65nxCwoP9UQbU90b0aVhuyIw_nbQoX4foMSz");
		//advancedUtil.sendCustomMessage("x-J4QN0s6sr8i7G4VrSUUHQ3QsflaXXL7omG7NxWBpMIwoLWI5DAPvs-19mnUUippTYAU_hcSFIz06_tPaLw-PBmOm1TVN4Ec_zCW86eT9o",jsonMsg);
		
		
		//获取关注者列表  
	//	WeixinUserList list=getUserList("YTRVWgMdDjLjlHtc-z0N6DC93hIk3ARYy4D0IsG7B5liIK6WvqPJUpcz94_avYWz_n0tFbD0Js-SJk-xGgVmQet2rW0unBttQA3r2nPZ5qY","");
	//	System.out.println(list);
		
	  //测试按组多图文群发
	  advancedUtil.sendAllByGroup("sE6yaBE-tq4FgaRxAgLhEraU3Bsswipp4SARZ-BjutC8qWSWowuSsBYz4AINyCjYr_LCR1ALim6Ph3m_Liths21GLAB2Bvg8A_5Y2S9QJPUGPPc04IYs2LZs_3d7beatSUSdAAAOQO",0,
			  "mHOSPFakqOd7LjWJYpll-P5YQNTjDFxYznpQDz3la5aJXnjgJxUTMn7ssoAAUAvR","mpnews");
	
	 //按组群发文本
//	String content="欢迎来到买家网欢迎来到买家网欢迎来到买家网欢迎来到买家网欢迎来到买家网";
//	advancedUtil.sendText("K7CnUkdmTJ_8_QL1rZv-mKg06iPl_wMIUFM26A4bHnRn-sHTi1ZCi1NDPYtMuBuGfTlhVMLT-7JJ7kmWxw449u17UmmfxDjJCvB1-ZLzmUQ",
//				  104,content);
		
//		/**
//		 * 发送客服消息（文本消息）
//		 */
		// 组装文本客服消息
//		String jsonTextMsg = advancedUtil.makeTextCustomMessage("oYPrFs1L-jj_MBQ9YKMeaeJo_71I", "我草草草</a>");
//		 发送客服消息
//		advancedUtil.sendCustomMessage("sFAXxx9cwep3AX-RgQvQq8Q2CoXl-yYy8y5WA5WzuGfNqwxBvvui666eZegEJ0AoqNcHectfPXRgr5pvLfLuScuqApFkYpJ5Pi5CrKfsXC9UCf__akwlq_2DKRI10HSXCIEdADAWCY", jsonTextMsg);
//
//		/**
//		 * 发送客服消息（图文消息）
//		 */
//		Article article1 = new Article();
//		article1.setTitle("微信上也能斗地主");
//		article1.setDescription("");
//		article1.setPicUrl("http://116.255.254.216/hanzhu123/download/sub.jpg");
//	    article1.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.html");
//		Article article2 = new Article();
//		article2.setTitle("傲气雄鹰\n80后不得不玩的经典游戏");
//		article2.setDescription("");
//		article2.setPicUrl("http://0.weixinmptest.duapp.com/image/jd.jpg");
//		article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Plane/aoqixiongying.html");
//		List<Article> list = new ArrayList<Article>();
//		list.add(article1);
//		list.add(article2);
//		 组装图文客服消息
//		AdvancedUtil advancedUtil=new AdvancedUtil();
//		String jsonNewsMsg = advancedUtil.makeStudentVoiceCustomMessage("oz6TVjilBW4uNmHk9HueI47Kt1Ks", "vUzEP22sQ9gnAKDjHoaPrN-21Bq6IUbJ0GJc1HLytlseuH9-Tpp5LcfSbu5dOWb-");
//		 发送客服消息
//		advancedUtil.makeTextCustomMessage("oYPrFs1L-jj_MBQ9YKMeaeJo_71I","hahahahahah");
		/**
		 * 上传音频文件 mediaId=xabz_jmOyaakYfomqILBEezO1unj_9X2OKoO1M1vtPZvbhnIqmWKKfkq22F_iEHR
		 */
//		 String filePath = "E:/tomcat/webapps/weixindyh/images/sa.mp3";  
//		 String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=i5qisXjR15MbXSMapxFmc886weeyjXQ_pZCZKWJXrSS-2TmWxwUG3BBYbOE994px1giEq97IsQ0bKOTRpAz-6-YRSi-bmXPmjw55ZvTTL5w&type=voice";  
//		 String result = null;  
//			try {
//				result = advancedUtil.uploadMedia(sendUrl,filePath);
//				System.out.println(result);  
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			/**
			 * 上传临时图片文件 mediaId=9JB_0NLNg6fDyD5hRtqyD00T7gN-W37bh3UV2FOxkbMoArVSze43NlAb3yr3wMZ5
			 *    pgqALvgAWoHBePNT8mExtr-QkNEp45_MytSwPE9WtpS-1ymDJ6Ju2CcFYoGKww14
			 */
//			 String filePath = "C:/Users/Administrator/Desktop/进度条图片/pic2.jpg";  
//			 String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=dSz89eU3lnsXF2wG2wpMAelEH524iwXVcuXhx7fxVk6hc3ZMqCqPrfZKSartf1MkTqFkDxb-6mqReyIhggP8IH0zwSbvo2Rcu28mYJDFDQc&type=image";  
//			 String result = null;  
//				try {
//					result = advancedUtil.uploadMedia(sendUrl,filePath);
//					System.out.println(result);  
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
		/**
		 * 上传临时视频文件 mediaId=YPcFrm2UUmmzagOlXeONoqr1Zwj0hc8Wbgue38ohVLLPcD6gKYYkdSHLRqtD8vu0
		 */
//		 String filePath = "E:/tomcat/webapps/weixindyh/images/vedio.mp4";  
//		 String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=i5qisXjR15MbXSMapxFmc886weeyjXQ_pZCZKWJXrSS-2TmWxwUG3BBYbOE994px1giEq97IsQ0bKOTRpAz-6-YRSi-bmXPmjw55ZvTTL5w&type=video";  
//		 String result = null;  
//			try {
//				result = advancedUtil.uploadMedia(sendUrl,filePath);
//				System.out.println(result);  
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		/**
		 * 上传临时缩略图文件   mediaId=K4bAf5PyMdqus870YeYBXIUvWQk7eV7MK7g6yaiZyjOBIMQDS75SxHC2a1EyZbEZ
		 *   pic.jpg  zUpUvmou4nrXE0yDxhoRJmd5afOJkJm7VjJyBHSr0BXw7axBj9oBwsms_JoKRIFm 
		 *   pic4.jpg HdnPTnsnz6lQxJ7U1yq5IzC32NDV-DgJtwpgnIQZNFqGarbqMcu9d15-Kvg3ZHPF 
		 *   pic5.jpg ehHPv_mxB8OU8_hJeEPbAeNXibnvUuX0VRG2zEzBGGkNW7GSdk7hLDaU4HvdtAsF
		 */ 
//		 String filePath = "C:/Users/Administrator/Desktop/进度条图片/pic5.jpg";  
//		 String sendUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=V1Q6v4EekOFGaLp_2PQVWokkOcJkYht5sb38E-ctpU42qXfN9rnPpZrTE1JbvP_3DLrU23DVEh6oTTnCIeJQrJy25_5noeYhncAGcKu1w1k&type=image";  
//		 String result = null;  
//			try {
//				result = advancedUtil.uploadMedia(sendUrl,filePath);
//				System.out.println(result);  
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		/**
//		 * 下载多媒体文件
//		 */
//		getStudentMedia("sfNVCYY_JRvA9LVqK0lzxLjcOJPvESEtVOI5cxrWyju8ppxf49Oz5qOQcwQ4WSxyimcBEJN_HD1NRva7eV9jCg", "grN-DR-gh1I2XXCEF31ESYUIr6cxDp9yd8lTTokxuNjt75vQDw0MLQ2v1RIyiXGY", "D:/web/download");
////		
////		getStudentMedia("gb4tu_q_JlYWrPOzsysczAjZZRE-XPlHUiXul_syfzHSvlebokWT2yUps7stZCB0giOOunlNMutNo1I500mkYA", "zuNOOIxOFe3Gn5JYRmJnyNrqXl-yCzUhSVMJxXrEEuoZftqBEZsRrblp0PHlheWv", "D:/web/download");
//	//	WeixinMedia weixinMedia = uploadMedia(accessToken, "bbbbbbb", "http://116.255.254.216/xzd/download/aaa.mp3");
////    	System.out.println(weixinMedia.getMediaId());
////		getMedia(accessToken, "s7Gw95nl0aL_APJNEFSpXkTVpC3cNnOdi_EjuGLScfPX4ecRWHowd0sm-zUoGR8i", "D:/download");
//		//测试发送图片
//		String jsonTextMsg = AdvancedUtil.makeTextCustomMessage("oLoafuO_l71THcfZ916GP1LLmVNQ","dhg");
//    	System.out.println("jsonTextMsg="+jsonTextMsg);
//		sendCustomMessage("Oj0DIynbn9oDSEkpiwo48MJy-RFS25vJNv9QJNQPCg2fzweITJkSU2f3fUeGWID0P7ip7s6OlrGroLzBCiZJFw", jsonTextMsg);

//		String jsonTextMsg = AdvancedUtil.makeTextCustomMessage("oz6TVjt4MocIY-QkW_dP8DoXhs9s", "h6qOSUaJCQSzze1X8yhoYqV328RhW8I33cy4bI5V4nV2eLr2-eYqg902r2YVn-Ew");
 //    	sendStudentCustomMessage("J5ogxlTtcMsqH1zZBrbAFw8zBvJmrmHmOB442bouCj0n6Axtm2Ri73DpxdBIwXVZ3L5o6zWA1yoiBNNBOtUcdQ", jsonTextMsg);
		//	}
		//测试创建临时二维码   订阅号无法开启生成带参数的二维码  无法获取用户地理位置
//	WeixinQRCode weixinQRCode = advancedUtil.createTemporaryQRCode("EPZnfNFoHAXOiWaklS0MZ_f4o0NEDdVENoM8CVQzCHInxDPqR0yfgyvFs-UinbS_Nxkfc38ighFFRTdV8Ro830ojjFUHpfuBr1myFRBMZZM",504800,123);
//    System.out.println("weixinQRCode"+weixinQRCode.getTicket());
		
		//测试创建永久二维码
//		String tickt=advancedUtil.createPermanentQRCode("EPZnfNFoHAXOiWaklS0MZ_f4o0NEDdVENoM8CVQzCHInxDPqR0yfgyvFs-UinbS_Nxkfc38ighFFRTdV8Ro830ojjFUHpfuBr1myFRBMZZM", 123);
//	    System.out.println(tickt);
	}
}
