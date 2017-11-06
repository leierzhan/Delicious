package com.zz.ccy.util;
import javax.servlet.ServletContextEvent;    
import javax.servlet.ServletContextListener;    
   /**
    * @ClassName: InitListener
    * @Description: 自动更新token
    * @author: 
    * @date: 2017年9月7日 上午11:23:55
    */
public class InitListener implements ServletContextListener {    
    
    public void contextDestroyed(ServletContextEvent sce) {    
        System.out.println("web exit ... ");    
    }    
    
    public void contextInitialized(ServletContextEvent sce) {  
    
        new Thread(new TokenThread()).start();  
    }    
}    