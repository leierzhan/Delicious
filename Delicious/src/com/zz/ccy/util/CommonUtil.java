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
 * @Description: ͨ�ù�����
 * @author: 
 * @date: 2017��9��7�� ����9:33:42
 */
@Component
public class CommonUtil {
	// ƾ֤��ȡ��GET��
    public final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	/**
	 * ����http����
	 * 2015��7��28������11:13:33
	 * lez
	 */
	public JSONObject httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		    JSONObject jsonObject = null;  
	        StringBuffer buffer = new StringBuffer();  
	      try {  
	            // ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
	            TrustManager[] tm = { new MyX509TrustManager() };  
	            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	            sslContext.init(null, tm, new java.security.SecureRandom());  
	            // ������SSLContext�����еõ�SSLSocketFactory����
	            SSLSocketFactory ssf = sslContext.getSocketFactory();  
	            URL url = new URL(requestUrl);  
	            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
	            httpUrlConn.setSSLSocketFactory(ssf);  
	            httpUrlConn.setDoOutput(true);  
	            httpUrlConn.setDoInput(true);    
	            httpUrlConn.setUseCaches(false);
	            // ��������ʽ��GET/POST��
	            httpUrlConn.setRequestMethod(requestMethod);  
	             if ("GET".equalsIgnoreCase(requestMethod))  
	                httpUrlConn.connect();  
	                // ����������Ҫ�ύʱ
	              if (null != outputStr) {  
	                OutputStream outputStream = httpUrlConn.getOutputStream();  
	               // ע������ʽ����ֹ��������
	                outputStream.write(outputStr.getBytes("UTF-8"));  
	                outputStream.close();  
	            }  
	            // �����ص�������ת�����ַ���
	            InputStream inputStream = httpUrlConn.getInputStream();  
	            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
	            String str = null;  
	           while ((str = bufferedReader.readLine()) != null) {  
	                buffer.append(str);  
	            }  
	            bufferedReader.close();  
	            inputStreamReader.close();  
	            // �ͷ���Դ
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
	 * ��ȡ�ӿڷ���ƾ֤
	 * 
	 * @param appid ƾ֤
	 * @param appsecret ��Կ
	 * @return
	 */
	public Token getToken(String appid, String appsecret) {
		 JSONObject jsonObject=null;
		 Token token = null;
			String requestUrl = token_url.replace("APPID",appid).replace("APPSECRET", appsecret);
			// ����GET�����ȡƾ֤
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
					// ��ȡtokenʧ��
					System.out.println("��ȡtokenʧ�� errcode:{} errmsg:{}"+jsonObject.getInt("errcode")+","+jsonObject.getString("errmsg"));
				}
			}
			return token;
	} 
	/**
	 * �������������ж��ļ���չ��
	 * 
	 * @param contentType ��������
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
	 * URL���루utf-8��
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
