package com.zz.ccy.service;
/**
 * @ClassName: OrderService
 * @Description: ����service
 * @author: David
 * @date: 2017��12��19�� ����2:03:59
 */
public interface OrderService {
    /**
     * ��������ӿ�
     * @param userId �û�id
     * @param code �̼Ҷ�ά��code
     * @param universalCount ����ͨ�ñҸ���
     * @param uniqueCount ����Ψһ�Ҹ���
     * @return ����ɹ�����true,����ʧ�ܷ���false
     */
	public boolean orderManager(Integer userId, String code,
			Integer universalCount, Integer uniqueCount);

}
