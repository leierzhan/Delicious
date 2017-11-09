package com.zz.ccy.dao;

import java.util.List;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.CommentEntity;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.lf.entity.Greens;

/**
 * 
 * @ClassName: StoreDao
 * @Description: �������
 * @author: David
 * @date: 2017��10��9�� ����2:30:05
 */
public interface StoreDao {
	//��ȡ�����б�
	public List<StoreInfo> getStoreList();
	//���ݵ���id��ȡ����ͼƬ
	public List<String> getCoverImgs(int storeId);
	//���ݵ���id��ȡ��Ӧ��ʦ
	public List<ChefEntity> getChefByStoreId(int storeId);
	//���ݵ���id��ȡ��Ӧ��ʦid����
	public List<Integer> getChefIdsByStoreId(int storeId);
	//���ݵ���id��ȡ��Ӧ��ɫ��
	public List<Greens> getGreensByStoreId(int storeId);
	//���ݲ˵�id׷�ӵ�����
	public void addZan(Integer greenId);
	/**
     * ���ݾ�γ�Ȼ�ȡ��������
     * @param longitude
     * @param latitude
     * @return
     * 2017-10-10 14:02:30
     */
    public List<StoreInfo> getStoreListByJWD(String longitude, String latitude);
    //���ݵ���id��ȡ�õ�������
    public List<CommentEntity> getMoreCommentByStoreId(int storeId);
    /**
     * �������һ������id���غ�10������
     * 2017-10-12 11:43
     * lez
     */
    public List<CommentEntity> getMoreCommentByLastId(Integer storeId,Integer commentId);
    /**
     * ��ȡ��������4��
     * @param storeId
     * @return 
     * lez
     */
    public List<CommentEntity> getCommentFour(int storeId);
    /**
     * ��������id��ӵ�����
     * @param commentId
     */
    public void addCommentZan(Integer commentId);
    /**
     * ���ݹؼ�����������
     * 2017-10-13 10:03
     * lez
     */
    public List<StoreInfo> getSearchStoreByKeyWord(String keyWord);
    /**
     * ���ݵ���id��ȡ������Ϣ
     * @param storeId
     * @return
     */
    public StoreInfo getStoreInfoById(int storeId);
    /**
     * ���ݳ�ʦid��ȡ��ʦ��Ϣ
     * @param chefId
     * @return
     */
    public ChefEntity getChefInfoById(Integer chefId);
    /**
     * ���ݳ�ʦid��ȡ�˳�ʦ��ɫ�� 
     * @param chefId
     * @return
     */
    public List<Greens> getGreensByChefId(Integer chefId);

    /**
     * �����û���Ϣ�͵�����Ϣ��ѯ����
     * @param storeId
     * @param userId
     * @return
     */
    public List<CommentEntity> getCommentByStoreIdAndUserId(Integer storeId,
  		Integer userId);
    //��������
    public void saveComment(Integer storeId, Integer userId, String content);
}
