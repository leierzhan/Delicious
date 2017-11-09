package com.zz.ccy.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.sf.json.JSONObject;

import com.zz.ccy.entity.Jsconfig;

public class Qianming {
		// 获取签名及ticket定时更新所有也米娜的
	public static Integer updateUrlSignatrue() throws NoSuchAlgorithmException {
		 Connection conn=null;
         PreparedStatement ps=null;
         ConnectionDB conndb=new ConnectionDB();
         conn=conndb.start();
	    String sql = "select url,apis from d_wx_jsconfig";
	    try {
	        ps = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = ps.executeQuery();
	        //int col = rs.getMetaData().getColumnCount();
	        System.out.println("============================");
	        while (rs.next()) {
	        	Jsconfig jc=  getConfig(rs.getString(1), rs.getString(2));
	        	update(jc);
	            System.out.println("");
	        }
	            System.out.println("============================");
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	private static int update(Jsconfig jc) {
	    ConnectionDB conndb=new ConnectionDB();
	    Connection conn = conndb.start();
	    int i = 0;
	    String sql = "update d_wx_jsconfig set noncestr='" + jc.getNonceStr()+""
	    		+"',timestamp='" + jc.getTimestamp() + "',signature='" + jc.getSignature() + "',timec='" + getNowDateTime() + "' where url='" + jc.getUrl()+ "'";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        System.out.println("resutl: " + i);
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	  private static  String getNowDateTime(){
		   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   return df.format(new Date());
	   }
	
	@SuppressWarnings("static-access")
	public static Jsconfig getqming(Jsconfig con) throws SQLException, NoSuchAlgorithmException {
		AdvancedUtil a=new AdvancedUtil();
	String token=TokenThread.accessToken.getAccessToken();
		 JSONObject jsonObject =a.httpRequest("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+token+"&type=jsapi","GET", null); 
		 String tik=jsonObject.getString("ticket");
		 long timestamp=con.getTimestamp();//"1421144444";
		 String  nonce=con.getNonceStr();//"Wm3WZYTPz0wzccnW001";
		 String string1="jsapi_ticket="+tik+"&noncestr="+nonce+"&timestamp="+timestamp+"&url="+con.getUrl();
		 MessageDigest md = MessageDigest.getInstance("SHA-1");
		 
		 byte[] digest =  md.digest(string1.toString().getBytes());
		String signature = byteToStr(digest);
		con.setSignature(signature);
		return con;
	}
	
	public static  Jsconfig getConfig(String url,String apis) throws NoSuchAlgorithmException, SQLException{
		
		Jsconfig c=new Jsconfig();
		long timestamp=System.currentTimeMillis();
		c.setTimestamp(timestamp);
		String noncestr=getRandomString(16);
		c.setNonceStr(noncestr);
		c.setUrl(url);
		c.setAppId(Constant.appid);
		c.setJsApiList(apis);
		
		return getqming(c);
		
	}
	/*
	 * 获取随机数
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
	    String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
	    Random random = new Random();   
	    StringBuffer sb = new StringBuffer();   
	    for (int i = 0; i < length; i++) {   
	        int number = random.nextInt(base.length());   
	        sb.append(base.charAt(number));   
	    }   
	    return sb.toString();   
	 }  
	
	
	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
		String s = new String(tempArr);
		return s;
	}

}