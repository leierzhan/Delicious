package com.zz.ccy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.OrderDao;
import com.zz.ccy.service.OrderService;

/**
 * @ClassName: OrderService
 * @Description: ����service
 * @author: David
 * @date: 2017��12��19�� ����2:03:59
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	/**
     * ��������ӿ�
     * @param userId �û�id
     * @param code �̼Ҷ�ά��code
     * @param universalCount ����ͨ�ñҸ���
     * @param uniqueCount ����Ψһ�Ҹ���
     * @return ����ɹ�����true,����ʧ�ܷ���false
     */
	@Override
	public boolean orderManager(Integer userId, String code,
			Integer universalCount, Integer uniqueCount) {
		return orderDao.orderManager(userId,code,universalCount,uniqueCount);
	}
}
