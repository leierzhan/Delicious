package com.zz.ccy.dao;

import com.zz.ccy.entity.BillAndInfo;

/**
 * @ClassName: ArticleDao
 * @Description: 文章相关DAO接口
 * @author: David
 * @date: 2017年9月9日 下午10:10:01
 */
public interface ArticleDao {
	/**
	 * @param articleContent 文章内容
	 * @param openId 用户openId
	 * @return
	 */
    public int saveArticleContent(String articleTitle,String articleContent,int openId);
    //根据openId获取用户是否已经存在
  	public boolean checkUserIsExistByOpenId(String openId);
  	/**
     * 上传发票及店铺信息
     * @param billAndInfo
     */
	public void saveBillAndInfo(BillAndInfo billAndInfo);
}
