package com.zz.ccy.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator
 *
 */
public class TimeConvertor {
   public static String convertor(long time){
	   Date dat=new Date(time);  
       GregorianCalendar gc = new GregorianCalendar();   
       gc.setTime(dat);  
       java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
       return format.format(gc.getTime());  
   }
}
