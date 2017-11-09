package com.zz.ccy.dao;

import com.zz.ccy.entity.BillAndInfo;

/**
 * @ClassName: ArticleDao
 * @Description: �������DAO�ӿ�
 * @author: David
 * @date: 2017��9��9�� ����10:10:01
 */
public interface ArticleDao {
	/**
	 * @param articleContent ��������
	 * @param openId �û�openId
	 * @return
	 */
    public int saveArticleContent(String articleTitle,String articleContent,int openId);
    //����openId��ȡ�û��Ƿ��Ѿ�����
  	public boolean checkUserIsExistByOpenId(String openId);
  	/**
     * �ϴ���Ʊ��������Ϣ
     * @param billAndInfo
     */
	public void saveBillAndInfo(BillAndInfo billAndInfo);
}
