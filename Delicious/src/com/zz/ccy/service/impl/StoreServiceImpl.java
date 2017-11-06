package com.zz.ccy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zz.ccy.dao.StoreDao;
import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.CommentEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.service.StoreService;

/**
 * 
 * @ClassName: StoreServiceImpl
 * @Description: 店铺相关
 * @author: David
 * @date: 2017年10月9日 下午2:29:08
 */
@Service
@Transactional
public class StoreServiceImpl implements StoreService{
	@Autowired
    private StoreDao storeDao;
	@Override
	public List<StoreInfo> getStoreList() {
		return storeDao.getStoreList();
	}
	@Override
	public List<String> getCoverImgs(int storeId) {
		return storeDao.getCoverImgs(storeId);
	}
	@Override
	public List<ChefEntity> getChefByStoreId(int storeId) {
		return storeDao.getChefByStoreId(storeId);
	}
	@Override
	public List<Greens> getGreensByStoreId(int storeId) {
		return storeDao.getGreensByStoreId(storeId);
	}
	@Override
	public void addZan(Integer greenId) {
		storeDao.addZan(greenId);
	}
	@Override
	public List<StoreInfo> getStoreListByJWD(String longitude, String latitude) {
		return storeDao.getStoreListByJWD(longitude,latitude);
	}
	@Override
	public List<CommentEntity> getMoreCommentByStoreId(int storeId) {
		return storeDao.getMoreCommentByStoreId(storeId);
	}
	@Override
	public List<CommentEntity> getMoreCommentByLastId(Integer storeId,Integer commentId) {
		return storeDao.getMoreCommentByLastId(storeId,commentId);
	}
	@Override
	public List<CommentEntity> getCommentFour(int storeId) {
		return storeDao.getCommentFour(storeId);
	}
	@Override
	public void addCommentZan(Integer commentId) {
		storeDao.addCommentZan(commentId);
	}
	@Override
	public List<StoreInfo> getSearchStoreByKeyWord(String keyWord) {
		return storeDao.getSearchStoreByKeyWord(keyWord);
	}
	@Override
	public StoreInfo getStoreInfoById(int storeId) {
		return storeDao.getStoreInfoById(storeId);
	}
	@Override
	public ChefEntity getChefInfoById(Integer chefId) {
		return storeDao.getChefInfoById(chefId);
	}
	@Override
	public List<Greens> getGreensByChefId(Integer chefId) {
		return storeDao.getGreensByChefId(chefId);
	}
	@Override
	public List<CommentEntity> getCommentByStoreIdAndUserId(Integer storeId,
			Integer userId) {
		return storeDao.getCommentByStoreIdAndUserId(storeId, userId);
	}
	@Override
	public void saveComment(Integer storeId,Integer userId,String content){
		storeDao.saveComment(storeId, userId, content);
	}
}
