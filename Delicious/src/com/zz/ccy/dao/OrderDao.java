package com.zz.ccy.dao;

public interface OrderDao {
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
