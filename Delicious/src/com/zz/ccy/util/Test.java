package com.zz.ccy.util;

public class Test {

	public static void main(String[] args) {

		
		
	}
	public static String getRandomString() {
	    //Ëæ»ú×Ö·û´®µÄËæ»ú×Ö·û¿â
	    String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    StringBuffer sb = new StringBuffer();
	    int len = KeyString.length();
	    for (int i = 0; i < 6; i++) {
	       sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
	    }
	    String  s=String.valueOf(System.currentTimeMillis())+sb;
	    
	    return s;
	}

}
