package com.zz.ccy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.ArticleDao;
import com.zz.ccy.entity.BillAndInfo;
import com.zz.ccy.service.ArticleService;
/**
 * @ClassName: ArticleServiceImpl
 * @Description: �������Serviceʵ�� ��
 * @author: David
 * @date: 2017��9��9�� ����10:11:03
 */
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
    private ArticleDao articleDao;
	@Override
	public int saveArticleContent(String articleTitle,String articleContent, int userId) {
		return articleDao.saveArticleContent(articleTitle,articleContent,userId);
	}
	//����openId��ȡ�û��Ƿ��Ѿ�����
	@Override
	public boolean checkUserIsExistByOpenId(String openId) {
		return articleDao.checkUserIsExistByOpenId(openId);
	}
	@Override
	public void saveBillAndInfo(BillAndInfo billAndInfo) {
		articleDao.saveBillAndInfo(billAndInfo);
	}
}
