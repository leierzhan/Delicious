package com.zz.ccy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.OrderDao;
import com.zz.ccy.service.OrderService;

/**
 * @ClassName: OrderService
 * @Description: 订单service
 * @author: David
 * @date: 2017年12月19日 下午2:03:59
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDao orderDao;
	/**
     * 订单处理接口
     * @param userId 用户id
     * @param code 商家二维码code
     * @param universalCount 消费通用币个数
     * @param uniqueCount 消费唯一币个数
     * @return 处理成功返回true,处理失败返回false
     */
	@Override
	public boolean orderManager(Integer userId, String code,
			Integer universalCount, Integer uniqueCount) {
		return orderDao.orderManager(userId,code,universalCount,uniqueCount);
	}
}
