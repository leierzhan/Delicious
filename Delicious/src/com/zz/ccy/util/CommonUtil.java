package com.zz.ccy.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.springframework.stereotype.Component;

import com.zz.ccy.entity.Token;

import net.sf.json.JSONObject;
/**
 * @ClassName: CommonUtil
 * @Description: 通用工具类
 * @author: 
 * @date: 2017年9月7日 上午9:33:42
 */
@Component
public class CommonUtil {
	// 凭证获取（GET）
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * 发送http请求
	 * 2015年7月28日上午11:13:33
	 * lez
	 */
	public JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
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
	             if ("GET".equalsIgnoreCase(requestMethod))  
	                httpUrlConn.connect();  
	                // 当有数据需要提交时
	              if (null != outputStr) {  
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
	               // 注意编码格式，防止中文乱码
	                outputStream.write(outputStr.getBytes("UTF-8"));  
	                outputStream.close();  
	            }  
	            // 将返回的输入流转换成字符串
	            InputStream inputStream = httpUrlConn.getInputStream();  
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
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
	        } catch (ConnectException ce) {  
	            System.out.println("Weixin server connection timed out.");  
	        } catch (Exception e) {  
	            System.out.println("https request error:{}"+e);  
	        }  
	        return jsonObject;  
	}
	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public Token getToken(String appid, String appsecret) {
		 JSONObject jsonObject=null;
		 Token token = null;
			String requestUrl = token_url.replace("APPID",appid).replace("APPSECRET", appsecret);
			// 发起GET请求获取凭证
			try{
				 jsonObject = httpsRequest(requestUrl,"GET",null);
			}catch(Exception e){
				e.printStackTrace();
			}
			if (null != jsonObject) {
				try {
					token = new Token();
					token.setAccessToken(jsonObject.getString("access_token"));
					token.setExpiresIn(jsonObject.getInt("expires_in"));
				}catch (Exception e) {
					token = null;
					// 获取token失败
					System.out.println("获取token失败 errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+","+jsonObject.getString("errmsg"));
				}
			}
			return token;
	} 
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".mp3";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	/**
	 * URL编码（utf-8）
	 * 
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
