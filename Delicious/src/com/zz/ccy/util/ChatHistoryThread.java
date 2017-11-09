package com.zz.ccy.util;

import org.apache.log4j.Logger;
import com.zz.ccy.entity.ChatHistory;
/**
 * @ClassName: ChatHistoryThread
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: 
 * @date: 2017年9月7日 上午9:32:29
 */
public class ChatHistoryThread implements Runnable {  
    private Logger logger=Logger.getLogger(ChatHistory.class);
    public void run() {  
        while (true) {  
//            try {  
//            	//获取accessToken
//                Token token=TokenThread.accessToken;
//            	if (token==null){  
//            		String accessToken=token.getAccessToken();
//            		long startTime=0;
//                    Connection conn=null;
//                    PreparedStatement ps=null;
//        	    	ConnectionDB conndb=new ConnectionDB();
//        	    	conn = conndb.start();
//        	    	try {
//        	    		int maxId=0;
//						ps=conn.prepareStatement("select top 1 * from t_hz_wx_chat_history order by id desc");
//						ResultSet resultSet =ps.executeQuery();
//						while(resultSet.next()){
//							maxId=resultSet.getInt("id");
//							startTime=resultSet.getLong("time");
//						}
//						resultSet.close();
//						AdvancedUtil advancedUtil=new AdvancedUtil();
//						long endTime = System.currentTimeMillis()/1000L;
//						String jsonMsg=advancedUtil.makeChatHistoryMessage(startTime/1000L,endTime,maxId,10000);
//                        List<ChatHistory> chatHistoryList=advancedUtil.getChatHistory(accessToken,jsonMsg);	
//                        logger.info(chatHistoryList);
//                        for(int i=0;i<chatHistoryList.size();i++){
//	                        ps=conn.prepareStatement("insert into t_hz_wx_chat_history(worker,openid,opercode,text,time) values (?,?,?,?,?)");
//	                        ps.setString(1,chatHistoryList.get(i).getWorker());
//	                        ps.setString(2,chatHistoryList.get(i).getOpenid());
//	                        ps.setString(3,chatHistoryList.get(i).getOpercode());
//	                        ps.setString(4,chatHistoryList.get(i).getText());
//	                        ps.setString(5,chatHistoryList.get(i).getTime());
//							ps.execute();
//                        }
//					} catch (Exception e) {
//						e.printStackTrace();
//					}finally{
//						try {
//							ps.close();
//							conn.close();
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//                    //休眠7000秒  
//                    Thread.sleep(23*60*60 * 1000);  
//                } else {  
//                    // 如果access_token为null，60秒后再获取  
//                	logger.info("没有获取到token");
//                    Thread.sleep(60 * 1000);  
//                }  
//            } catch (InterruptedException e) {  
//                try {  
//                    Thread.sleep(60 * 1000);  
//                } catch (InterruptedException e1) {  
//                    e1.printStackTrace();
//                }  
//               e.printStackTrace(); 
//            }  
        }  
    }  
}  