package com.zz.ccy.service;

import com.zz.ccy.entity.BillAndInfo;

/**
 * @ClassName: ArticleService
 * @Description: 文章相关Service接口
 * @author: David
 * @date: 2017年9月9日 下午10:10:22
 */
public interface ArticleService {
	/**
	 * @param articleContent 文章内容
	 * @param openId 用户openId
	 * @return
	 */
    public int saveArticleContent(String articleTitle,String articleContent,int userId);
    /**
     * 根据openId获取用户是否已经存在
     * @param openId
     * @return
     */
    public boolean checkUserIsExistByOpenId(String openId);
    /**
     * 上传发票及店铺信息
     * @param billAndInfo
     */
	public void saveBillAndInfo(BillAndInfo billAndInfo);
}
