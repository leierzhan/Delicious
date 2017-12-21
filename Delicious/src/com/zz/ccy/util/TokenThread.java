package com.zz.ccy.util;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.Token;

public class TokenThread implements Runnable {  
    public static Token accessToken = null;  
    public static OauthAccessToken oauthAccessToken=null;
    public void run() {  
        while (true) {  
            try {  
            	CommonUtil commonUtil = new CommonUtil();
                accessToken =commonUtil.getToken(Constant.appid,Constant.appsecret);
            	if (null != accessToken) {  
                    System.out.println("获取access_token成功，有效时长{}秒 token:{}"+","+accessToken.getExpiresIn()+","+accessToken.getAccessToken());
                    //更新数据库中的accessToken
                    Connection conn=null;
                    PreparedStatement ps=null;
        	    	ConnectionDB conndb=new ConnectionDB();
        	    	conn = conndb.start();
        	    	try {
						ps=conn.prepareStatement("update d_wx_token set accessToken=?,expiresIn=? where id=1");
						ps.setString(1,accessToken.getAccessToken());
						ps.setLong(2,accessToken.getExpiresIn());
						int a =ps.executeUpdate();
						if(a>0){
						
							
							System.out.println("更新token成功");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						try {
							ps.close();
							conn.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
        	    	//更新签名
        	    	Qianming.updateUrlSignatrue();
                    //休眠7000秒  
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);  
                } else {  
                    // 如果access_token为null，60秒后再获取  
                    Thread.sleep(60 * 1000);  
                }  
            } catch (InterruptedException e) {  
                try {  
                    Thread.sleep(60 * 1000);  
                } catch (InterruptedException e1) {  
                    e1.printStackTrace();
                }  
               e.printStackTrace(); 
            } catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        }  
    }  
}  