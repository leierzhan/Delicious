package com.zz.ccy.service;
/**
 * @ClassName: OrderService
 * @Description: 订单service
 * @author: David
 * @date: 2017年12月19日 下午2:03:59
 */
public interface OrderService {
    /**
     * 订单处理接口
     * @param userId 用户id
     * @param code 商家二维码code
     * @param universalCount 消费通用币个数
     * @param uniqueCount 消费唯一币个数
     * @return 处理成功返回true,处理失败返回false
     */
	public boolean orderManager(Integer userId, String code,
			Integer universalCount, Integer uniqueCount);

}
