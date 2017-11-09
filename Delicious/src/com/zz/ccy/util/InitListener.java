package com.zz.ccy.util;
import javax.servlet.ServletContextEvent;    
import javax.servlet.ServletContextListener;    
   /**
    * @ClassName: InitListener
    * @Description: �Զ�����token
    * @author: 
    * @date: 2017��9��7�� ����11:23:55
    */
public class InitListener implements ServletContextListener {    
    
    public void contextDestroyed(ServletContextEvent sce) {    
        System.out.println("web exit ... ");    
    }    
    
    public void contextInitialized(ServletContextEvent sce) {  
    
        new Thread(new TokenThread()).start();  
    }    
}    