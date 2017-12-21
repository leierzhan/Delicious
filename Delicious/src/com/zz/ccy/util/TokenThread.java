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
                    System.out.println("��ȡaccess_token�ɹ�����Чʱ��{}�� token:{}"+","+accessToken.getExpiresIn()+","+accessToken.getAccessToken());
                    //�������ݿ��е�accessToken
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
						
							
							System.out.println("����token�ɹ�");
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
        	    	//����ǩ��
        	    	Qianming.updateUrlSignatrue();
                    //����7000��  
                    Thread.sleep((accessToken.getExpiresIn() - 200) * 1000);  
                } else {  
                    // ���access_tokenΪnull��60����ٻ�ȡ  
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