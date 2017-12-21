package com.zz.ccy.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.zz.ccy.dao.OrderDao;
/**
 * @ClassName: OrderDaoImpl
 * @Description: ����Dao
 * @author: David
 * @date: 2017��12��19�� ����2:06:03
 */
@Repository
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private SqlSessionFactory sessionFactory;

	public SqlSession getSession() {
		return sessionFactory.openSession();
	}
	@Override
	public boolean orderManager(Integer userId, String code,
			Integer universalCount,Integer uniqueCount){
		//����userId,universalCount,uniqueCount��ѯ�û��Ƿ�����ô����ʳ��
		Map<String,Integer> userInfoMap=new HashMap<String,Integer>();
		userInfoMap.put("userId",userId);
		userInfoMap.put("universalCount",universalCount);
		userInfoMap.put("uniqueCount",uniqueCount);
		List<Object> list=getSession().selectList("com.zz.ccy.mapping.OrderRecordMapper.getMsb",userInfoMap);
		//����code��ȡ����id
		try {
			int storeId=getSession().selectOne("com.zz.ccy.mapping.MerchantInfoMapper.getStoreIdByCode",code);
			if(list.size()>0){
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Map<String,Object> msgMap=new HashMap<String,Object>();
	            msgMap.put("userId",userId);
	            msgMap.put("storeId",storeId);
				msgMap.put("universalCount", universalCount);
				msgMap.put("uniqueCount", uniqueCount);
				msgMap.put("createTime",format.format(new Date()));
				msgMap.put("status",0);
			    getSession().update("com.zz.ccy.mapping.OrderRecordMapper.msgManager",msgMap);
			    //������¼�в���һ������
			    getSession().insert("com.zz.ccy.mapping.OrderRecordMapper.insertOrder",msgMap);
			}else{
				//�Ҳ���
				System.out.println("�Ҳ���");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}  
}
