package com.zz.ccy.service;

import com.zz.ccy.entity.BillAndInfo;

/**
 * @ClassName: ArticleService
 * @Description: �������Service�ӿ�
 * @author: David
 * @date: 2017��9��9�� ����10:10:22
 */
public interface ArticleService {
	/**
	 * @param articleContent ��������
	 * @param openId �û�openId
	 * @return
	 */
    public int saveArticleContent(String articleTitle,String articleContent,int userId);
    /**
     * ����openId��ȡ�û��Ƿ��Ѿ�����
     * @param openId
     * @return
     */
    public boolean checkUserIsExistByOpenId(String openId);
    /**
     * �ϴ���Ʊ��������Ϣ
     * @param billAndInfo
     */
	public void saveBillAndInfo(BillAndInfo billAndInfo);
}
