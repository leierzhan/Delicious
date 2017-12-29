package com.zz.ccy.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zz.ccy.dao.StoreDao;
import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.CommentEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.StoretradingRecord;
import com.zz.ccy.lf.entity.Greens;
/**
 * 
 * @ClassName: StoreDaoImpl
 * @Description: 店铺相关
 * @author: David
 * @date: 2017年10月9日 下午2:30:54
 */
@Component
public class StoreDaoImpl implements StoreDao{
	@Autowired
	private SqlSessionFactory sessionFactory;

	public SqlSession getSession() {
		return sessionFactory.openSession();
	}
	@Override
	public List<StoreInfo> getStoreList() {
		return getSession().selectList("com.zz.ccy.mapping.StoreInfoMapper.getStoreList");
	}
	@Override
	public List<String> getCoverImgs(int storeId) {
		List<String> coverList=new ArrayList<String>();
		String urls=getSession().selectOne("com.zz.ccy.mapping.StoreInfoMapper.getStoreCoverUrls",storeId);
		String[] coverArr=urls.split(";");
		for(int i=0;i<coverArr.length;i++){
			coverList.add(coverArr[i]);
		}
		return coverList;
	}
	@Override
	public List<ChefEntity> getChefByStoreId(int storeId) {
		return getSession().selectList("com.zz.ccy.mapping.ChefInfoMapper.getChefByStoreId",storeId);}
	public List<Integer> getChefIdsByStoreId(int storeId) {
		return getSession().selectList("com.zz.ccy.mapping.ChefInfoMapper.getChefIdsByStoreId",storeId);
	}
	@Override
	public List<Greens> getGreensByStoreId(int storeId) {
		return getSession().selectList("com.zz.ccy.mapping.GreensInfoMapper.getGreensByStoreId",storeId);
	}
	@Override
	public void addZan(Integer greenId) {
       int count=getSession().selectOne("com.zz.ccy.mapping.GreensInfoMapper.getGreenZanCountById",greenId);
       Map<String,Integer> map=new HashMap<String,Integer>();
       map.put("greenId",greenId);
       map.put("count",count+1);
       getSession().update("com.zz.ccy.mapping.GreensInfoMapper.updateGreenZanCountById",map);
	}
	@Override
	public List<StoreInfo> getStoreListByJWD(String longitude, String latitude){
		Map<String,String> map=new HashMap<String,String>();
        map.put("longitude",longitude);
        map.put("latitude",latitude);
		return getSession().selectList("com.zz.ccy.mapping.StoreInfoMapper.getStoreListByJWD",map);
	}
	@Override
	public List<CommentEntity> getMoreCommentByStoreId(int storeId) {
		return getSession().selectList("com.zz.ccy.mapping.CommentInfoMapper.getCommentByStoreId",storeId);
	}
	@Override
	public List<CommentEntity> getMoreCommentByLastId(Integer storeId,Integer commentId){
		Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("storeId",storeId);
        map.put("commentId",commentId);
	  return getSession().selectList("com.zz.ccy.mapping.CommentInfoMapper.getMoereCommentByStoreId",map);
	}
	@Override
	public List<CommentEntity> getCommentFour(int storeId) {
		return getSession().selectList("com.zz.ccy.mapping.CommentInfoMapper.getCommentFour",storeId);
	}
	@Override
	public void addCommentZan(Integer commentId) {
		int count=getSession().selectOne("com.zz.ccy.mapping.CommentInfoMapper.getCommentZanCountById",commentId);
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("commentId",commentId);
        map.put("count",count+1);
        getSession().update("com.zz.ccy.mapping.CommentInfoMapper.updateCommentZanCountById",map);	
	}
	@Override
	public List<StoreInfo> getSearchStoreByKeyWord(String keyWord) {
		return getSession().selectList("com.zz.ccy.mapping.StoreInfoMapper.getSearchStoreList","%"+keyWord+"%");
	}
	@Override
	public StoreInfo getStoreInfoById(int storeId) {
		return getSession().selectOne("com.zz.ccy.mapping.StoreInfoMapper.getStoreInfoById",storeId);
	}
	@Override
	public ChefEntity getChefInfoById(Integer chefId) {
		return getSession().selectOne("com.zz.ccy.mapping.ChefInfoMapper.getChefInfoById",chefId);
	}
	@Override
	public List<Greens> getGreensByChefId(Integer chefId) {
		return getSession().selectList("com.zz.ccy.mapping.GreensInfoMapper.getGreensByChefId",chefId);
	}
	@Override
	public List<CommentEntity> getCommentByStoreIdAndUserId(Integer storeId,
			Integer userId) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("storeId", storeId);
		map.put("userId", userId);
		return getSession().selectList("com.zz.ccy.mapping.CommentInfoMapper.getCommentByStoreIdAndUserId",map);
	}
	@Override
	public void saveComment(Integer storeId, Integer userId, String content) {
	  Map<String,Object> map=new HashMap<String,Object>();	
	  map.put("storeId", storeId);
	  map.put("userId", userId);
	  map.put("content", content);
      getSession().insert("com.zz.ccy.mapping.CommentInfoMapper.saveComment",map);		
	}

}
