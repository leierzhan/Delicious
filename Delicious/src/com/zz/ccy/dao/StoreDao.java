package com.zz.ccy.dao;

import java.util.List;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.CommentEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.lf.entity.Greens;

/**
 * 
 * @ClassName: StoreDao
 * @Description: 店铺相关
 * @author: David
 * @date: 2017年10月9日 下午2:30:05
 */
public interface StoreDao {
	//获取店铺列表
	public List<StoreInfo> getStoreList();
	//根据店铺id获取店铺图片
	public List<String> getCoverImgs(int storeId);
	//根据店铺id获取对应厨师
	public List<ChefEntity> getChefByStoreId(int storeId);
	//根据店铺id获取对应厨师id集合
	public List<Integer> getChefIdsByStoreId(int storeId);
	//根据店铺id获取对应特色菜
	public List<Greens> getGreensByStoreId(int storeId);
	//根据菜的id追加点赞数
	public void addZan(Integer greenId);
	/**
     * 根据经纬度获取附近店铺
     * @param longitude
     * @param latitude
     * @return
     * 2017-10-10 14:02:30
     */
    public List<StoreInfo> getStoreListByJWD(String longitude, String latitude);
    //根据店铺id获取该店铺评论
    public List<CommentEntity> getMoreCommentByStoreId(int storeId);
    /**
     * 根据最后一条评论id加载后10条数据
     * 2017-10-12 11:43
     * lez
     */
    public List<CommentEntity> getMoreCommentByLastId(Integer storeId,Integer commentId);
    /**
     * 获取最新评论4条
     * @param storeId
     * @return 
     * lez
     */
    public List<CommentEntity> getCommentFour(int storeId);
    /**
     * 根据评论id添加点赞数
     * @param commentId
     */
    public void addCommentZan(Integer commentId);
    /**
     * 根据关键字搜索店铺
     * 2017-10-13 10:03
     * lez
     */
    public List<StoreInfo> getSearchStoreByKeyWord(String keyWord);
    /**
     * 根据店铺id获取店铺信息
     * @param storeId
     * @return
     */
    public StoreInfo getStoreInfoById(int storeId);
    /**
     * 根据厨师id获取厨师信息
     * @param chefId
     * @return
     */
    public ChefEntity getChefInfoById(Integer chefId);
    /**
     * 根据厨师id获取此厨师特色菜 
     * @param chefId
     * @return
     */
    public List<Greens> getGreensByChefId(Integer chefId);

    /**
     * 根据用户信息和店铺信息查询留言
     * @param storeId
     * @param userId
     * @return
     */
    public List<CommentEntity> getCommentByStoreIdAndUserId(Integer storeId,
  		Integer userId);
    //保存评论
    public void saveComment(Integer storeId, Integer userId, String content);
}
