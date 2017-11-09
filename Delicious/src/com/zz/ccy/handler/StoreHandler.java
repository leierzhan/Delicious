package com.zz.ccy.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zz.ccy.entity.ChefEntity;
import com.zz.ccy.entity.CommentEntity;
import com.zz.ccy.entity.OauthAccessToken;
import com.zz.ccy.entity.StoreInfo;
import com.zz.ccy.entity.WeixinUserInfo;
import com.zz.ccy.lf.entity.Greens;
import com.zz.ccy.service.ArticleService;
import com.zz.ccy.service.StoreService;
import com.zz.ccy.service.UserService;
import com.zz.ccy.util.AdvancedUtil;

@Controller
@RequestMapping("/storeHandler")
public class StoreHandler {
	@Autowired
	private StoreService storeService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	
	/**
	 * ������Ȩ  snsapi_base��֤�ص�
	 * @param code
	 * @param map
	 * @return
	 */
	@RequestMapping("/storeOauthBaseH5")
    public String storeOauthBaseH5(@RequestParam("code") String code,RedirectAttributes attr,HttpSession session){
		String openId=(String) session.getAttribute("openId");
		if(openId!=null){
			//����openid��ѯ�û��Ƿ��Ѿ������ݿ���
			System.out.println("openId��Ϊ��");
			if(articleService.checkUserIsExistByOpenId(openId)){
				int userId=userService.getUserIdByOpenId(openId);
				attr.addFlashAttribute("userId",userId);
				//ֱ����ת��ҵ��ҳ��
				System.out.println("�û�id"+userId);
				return "redirect:/storeHandler/storeList";
			}else{
				//����snsapi_userinfoģʽ��Ȩ
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}
		}else{
			OauthAccessToken accessToken=AdvancedUtil.getOauthAccessToken(code);
			if(accessToken!=null){
				session.setAttribute("openId",accessToken.getOpenId());
			}
			//����openid��ѯ�û��Ƿ��Ѿ������ݿ���
			if(articleService.checkUserIsExistByOpenId(accessToken.getOpenId())){
				int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
				attr.addFlashAttribute("userId",userId);
				//ֱ����ת��ҵ��ҳ��
				return "redirect:/storeHandler/storeList";
			}else{
				//����snsapi_userinfoģʽ��Ȩ
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}
		}
	}
	/**
	 * ������Ȩ snsapi_userinfo�ص�ҳ��
	 * @param code
	 * @param map
	 * @return
	 */
	@RequestMapping("/storeOauthUserInfoH5")
    public String storeOauthUserInfoH5(@RequestParam("code") String code,Map<String,Object> map,
    		RedirectAttributes attr,HttpSession session){
		OauthAccessToken accessToken=AdvancedUtil.getOauthAccessToken(code);
		if(accessToken!=null){
			session.setAttribute("openId",accessToken.getOpenId());
		}
		//����accessToken ��openId��ȡ�û�������Ϣ
		if(accessToken.getAccessToken()!=null){
			WeixinUserInfo userInfo=AdvancedUtil.getUserInfoByAccessToken(accessToken.getAccessToken(),accessToken.getOpenId());
			userService.saveOrUpdateEntity(userInfo);
			int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
			map.put("userId",userId);
			map.put("nickname",userInfo.getNickname());
			attr.addFlashAttribute("userId",userId);
		}else{
			System.out.println("OauthAccessTokenΪ��");
		}
		return "redirect:/storeHandler/storeList";       
    }
    @RequestMapping("/storeList")
	public String storeList(@ModelAttribute("userId") Integer userId,Map<String,Object> map,HttpSession session){
    	List<StoreInfo> storeList=storeService.getStoreList();
    	map.put("storeList",storeList);
    	map.put("userId",userId);
    	String longitude=(String) session.getAttribute("longitude");
    	String latitude=(String) session.getAttribute("latitude");
    	String position=(String) session.getAttribute("position");
    	map.put("position",position);
    	if(longitude!=null && latitude!=null){
    		map.put("longitude",longitude);
    		map.put("latitude",latitude);
    		map.put("position",position);
    	}
    	System.out.println("��γ��:"+longitude+","+latitude+","+position);
    	//���ж�λ
		return "store_list";
	} 
    /**
     * ���ݾ�γ�Ȼ�ȡ���������б�
     * @param longitude
     * @param latitude
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/getStoreListByJWD")
    public List<StoreInfo> getStoreListByJWD(@RequestParam("longitude") String longitude,@RequestParam("latitude") String latitude,
    		@RequestParam("position") String position,Map<String,Object> map,HttpSession session){
    	List<StoreInfo> storeList=storeService.getStoreListByJWD(longitude,latitude);
    	session.setAttribute("longitude",longitude);
    	session.setAttribute("latitude",latitude);
    	session.setAttribute("position",position);
    	System.out.println("λ�÷��ص�ҳ�棺"+longitude+","+latitude+",,"+position);
    	return storeList;
    }
    //��ת����������ҳ��
    @RequestMapping("/storeDetailUI")
    public String storeDetailUI(@RequestParam("storeId") int storeId,@RequestParam("userId") Integer userId,Map<String,Object> map){
    	map.put("storeId",storeId);
    	map.put("userId",userId);
    	//���ݵ���id��ȡ������Ϣ
    	StoreInfo storeInfo=storeService.getStoreInfoById(storeId);
    	map.put("storeInfo",storeInfo);
    	List<String> coverUrls = storeService.getCoverImgs(storeId);
    	map.put("coverList",coverUrls);
    	//���ݵ���id��ȡ��ʦ
    	List<Integer> chefIdsList=storeService.getChefIdsByStoreId(storeId);
    	//���ݳ�ʦid���ϻ�ȡ��ʦ����
    	List<ChefEntity> chefEntityList=new ArrayList<ChefEntity>();
    	for(int i=0;i<chefIdsList.size();i++){
    		ChefEntity chefEntity=storeService.getChefInfoById(chefIdsList.get(i));
    		chefEntityList.add(chefEntity);
    	}
    	map.put("chefList", chefEntityList);
        //���ݵ���id��ȡ�õ�����ɫ��
    	List<Greens> greensList=storeService.getGreensByStoreId(storeId);
    	map.put("greenList",greensList);
    	List<CommentEntity> commentList=storeService.getCommentFour(storeId);
    	map.put("commentList",commentList);
    	return "store_detail";
    }
    //��ת����ͼ��λҳ��
    @RequestMapping("/storePosition")
    public String storePosition(@RequestParam("address") String address,Map<String,Object> map){
       map.put("address",address);	
       return "map";
    }
    @RequestMapping("/chefDetail")
    public String chefDetail(@RequestParam("chefId") Integer chefId,Map<String,Object> map){
       //���ݳ�ʦid�����ʦ��Ϣ
    	ChefEntity chefEntity=storeService.getChefInfoById(chefId);
       //���ݳ�ʦid����˳�ʦ��ɫ��	
       List<Greens> greensList=storeService.getGreensByChefId(chefId);
       map.put("chefEntity",chefEntity);
       map.put("greensList",greensList);
       return "chef_detail";	
    }
    @RequestMapping("/foodDetail")
    public String foodDetail(@RequestParam("foodId") Integer foodId){
    	//���ݲ�id��ȡ����ϸ��Ϣ
    	
    	return "food_detail";
    }
    /**
     * ���ݲ�id��ӵ�����
     * @param greenId
     */
    @ResponseBody
    @RequestMapping("/addZan")
    public void addZan(@RequestParam("greenId") Integer greenId){
    	storeService.addZan(greenId);
    }
    /**
     * ��������id��ӵ�����
     * @param commentId
     */
    @ResponseBody
    @RequestMapping("/addCommentZan")
    public void addCommentZan(@RequestParam("commentId") Integer commentId){
    	storeService.addCommentZan(commentId);
    }
    @RequestMapping("/moreComment")
    public String moreComment(@RequestParam("storeId") int storeId,Map<String,Object> map){
    	List<CommentEntity> commentList=storeService.getMoreCommentByStoreId(storeId);
    	map.put("commentList",commentList);
    	map.put("storeId",storeId);
    	return "comment";
    }
    /**
     * �������һ������id���غ�10������
     * 2017-10-12 11:43
     * lez
     */
    @ResponseBody
    @RequestMapping("/getMoreCommentByLastId")
    public List<CommentEntity> getMoreCommentByLastId(@RequestParam("storeId") Integer storeId,@RequestParam("commentId") Integer commentId){
    	return storeService.getMoreCommentByLastId(storeId,commentId);
    }
    /**
     * ���ݹؼ�����������
     * 2017-10-13 10:03
     * lez
     */
    @ResponseBody
    @RequestMapping("/getSearchStoreByKeyWord")
    public List<StoreInfo> getSearchStoreByKeyWord(@RequestParam("keyWord") String keyWord){
    	List<StoreInfo> storeInfoList=storeService.getSearchStoreByKeyWord(keyWord);
    	return storeInfoList;
    }
    /**
     * �Ե���д����
     */
    @RequestMapping("/writeComment")
    public String writeComment(@RequestParam("storeId") Integer storeId,@RequestParam("userId") Integer userId,
    		Map<String,Object> map){
    	map.put("storeId",storeId);
    	StoreInfo storeInfo=storeService.getStoreInfoById(storeId);
    	map.put("storeInfo",storeInfo);
    	WeixinUserInfo weixinUserInfo=userService.getUserInfoById(userId);
    	map.put("weixinUserInfo",weixinUserInfo);
    	//�Ѵ��û��Ըõ��̵����۲�ѯ����
    	List<CommentEntity> commentEntityList=storeService.getCommentByStoreIdAndUserId(storeId,userId);
    	map.put("commentEntityList", commentEntityList);
    	return "write_comment";
    }
    /**
     * ��������
     */
    @ResponseBody
    @RequestMapping("/saveComment")
    public void saveComment(@RequestParam("storeId") Integer storeId,@RequestParam("userId") Integer userId,
    		@RequestParam("content") String content){
    	storeService.saveComment(storeId,userId,content);
    }
}
