package com.zz.ccy.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zz.ccy.dao.ArticleDao;
import com.zz.ccy.entity.ArticleInfo;
import com.zz.ccy.entity.BillAndInfo;
/**
 * @ClassName: ArticleDaoImpl
 * @Description: 文章相关DAO实现类
 * @author: David
 * @date: 2017年9月9日 下午10:09:24
 */
@Component
public class ArticleDaoImpl implements ArticleDao{
	@Autowired
	private SqlSessionFactory sessionFactory;

	public SqlSession getSession() {
		return sessionFactory.openSession();
	}
	@Override
	public int saveArticleContent(String articleTitle,String articleContent,int userId) {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		ArticleInfo articleInfo=new ArticleInfo();
		articleInfo.setUserid(userId);
		if(articleTitle==null || articleTitle.length()==0){
			articleTitle=format.format(new Date());
		}
		articleInfo.setArticleTitle(articleTitle);
		articleInfo.setArticleContent(articleContent);
		articleInfo.setAuthor(userId);
		articleInfo.setStatus(0);
		articleInfo.setTimec(format.format(new Date()));
		getSession().insert("com.zz.ccy.mapping.ArticleInfoMapper.insertArticleInfo",articleInfo);
		return articleInfo.getId();
	}
	@Override
	public boolean checkUserIsExistByOpenId(String openId) {
		int id=getSession().selectOne("com.zz.ccy.mapping.WeixinUserInfoMapper.checkUserIsExistByOpenId",openId);
		if(id>0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 上传发票及店铺信息
	 * 2017-9-15 15:20:23
	 * David
	 */
	@Override
	public void saveBillAndInfo(BillAndInfo billAndInfo) {
      getSession().insert("com.zz.ccy.mapping.BillAndInfoMapper.insertBillAndInfo",billAndInfo);		
	}
}
