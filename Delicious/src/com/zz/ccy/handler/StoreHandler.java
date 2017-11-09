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
	 * 店铺授权  snsapi_base验证回调
	 * @param code
	 * @param map
	 * @return
	 */
	@RequestMapping("/storeOauthBaseH5")
    public String storeOauthBaseH5(@RequestParam("code") String code,RedirectAttributes attr,HttpSession session){
		String openId=(String) session.getAttribute("openId");
		if(openId!=null){
			//根据openid查询用户是否已经在数据库中
			System.out.println("openId不为空");
			if(articleService.checkUserIsExistByOpenId(openId)){
				int userId=userService.getUserIdByOpenId(openId);
				attr.addFlashAttribute("userId",userId);
				//直接跳转到业务页面
				System.out.println("用户id"+userId);
				return "redirect:/storeHandler/storeList";
			}else{
				//再以snsapi_userinfo模式授权
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}
		}else{
			OauthAccessToken accessToken=AdvancedUtil.getOauthAccessToken(code);
			if(accessToken!=null){
				session.setAttribute("openId",accessToken.getOpenId());
			}
			//根据openid查询用户是否已经在数据库中
			if(articleService.checkUserIsExistByOpenId(accessToken.getOpenId())){
				int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
				attr.addFlashAttribute("userId",userId);
				//直接跳转到业务页面
				return "redirect:/storeHandler/storeList";
			}else{
				//再以snsapi_userinfo模式授权
				return "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx5ecac8f9f5725183&redirect_uri=http%3A%2F%2Fwww.cnmjw.com.cn%2FDelicious%2FarticleHandler%2FarticleOauthUserInfoH5&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
			}
		}
	}
	/**
	 * 店铺授权 snsapi_userinfo回调页面
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
		//根据accessToken 和openId获取用户基本信息
		if(accessToken.getAccessToken()!=null){
			WeixinUserInfo userInfo=AdvancedUtil.getUserInfoByAccessToken(accessToken.getAccessToken(),accessToken.getOpenId());
			userService.saveOrUpdateEntity(userInfo);
			int userId=userService.getUserIdByOpenId(accessToken.getOpenId());
			map.put("userId",userId);
			map.put("nickname",userInfo.getNickname());
			attr.addFlashAttribute("userId",userId);
		}else{
			System.out.println("OauthAccessToken为空");
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
    	System.out.println("经纬度:"+longitude+","+latitude+","+position);
    	//城市定位
		return "store_list";
	} 
    /**
     * 根据经纬度获取附近店铺列表
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
    	System.out.println("位置返回到页面："+longitude+","+latitude+",,"+position);
    	return storeList;
    }
    //跳转到店铺详情页面
    @RequestMapping("/storeDetailUI")
    public String storeDetailUI(@RequestParam("storeId") int storeId,@RequestParam("userId") Integer userId,Map<String,Object> map){
    	map.put("storeId",storeId);
    	map.put("userId",userId);
    	//根据店铺id获取店铺信息
    	StoreInfo storeInfo=storeService.getStoreInfoById(storeId);
    	map.put("storeInfo",storeInfo);
    	List<String> coverUrls = storeService.getCoverImgs(storeId);
    	map.put("coverList",coverUrls);
    	//根据店铺id获取厨师
    	List<Integer> chefIdsList=storeService.getChefIdsByStoreId(storeId);
    	//根据厨师id集合获取厨师集合
    	List<ChefEntity> chefEntityList=new ArrayList<ChefEntity>();
    	for(int i=0;i<chefIdsList.size();i++){
    		ChefEntity chefEntity=storeService.getChefInfoById(chefIdsList.get(i));
    		chefEntityList.add(chefEntity);
    	}
    	map.put("chefList", chefEntityList);
        //根据店铺id获取该店铺特色菜
    	List<Greens> greensList=storeService.getGreensByStoreId(storeId);
    	map.put("greenList",greensList);
    	List<CommentEntity> commentList=storeService.getCommentFour(storeId);
    	map.put("commentList",commentList);
    	return "store_detail";
    }
    //跳转到地图定位页面
    @RequestMapping("/storePosition")
    public String storePosition(@RequestParam("address") String address,Map<String,Object> map){
       map.put("address",address);	
       return "map";
    }
    @RequestMapping("/chefDetail")
    public String chefDetail(@RequestParam("chefId") Integer chefId,Map<String,Object> map){
       //根据厨师id查出厨师信息
    	ChefEntity chefEntity=storeService.getChefInfoById(chefId);
       //根据厨师id查出此厨师特色菜	
       List<Greens> greensList=storeService.getGreensByChefId(chefId);
       map.put("chefEntity",chefEntity);
       map.put("greensList",greensList);
       return "chef_detail";	
    }
    @RequestMapping("/foodDetail")
    public String foodDetail(@RequestParam("foodId") Integer foodId){
    	//根据菜id获取菜详细信息
    	
    	return "food_detail";
    }
    /**
     * 根据菜id添加点赞数
     * @param greenId
     */
    @ResponseBody
    @RequestMapping("/addZan")
    public void addZan(@RequestParam("greenId") Integer greenId){
    	storeService.addZan(greenId);
    }
    /**
     * 根据评论id添加点赞数
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
     * 根据最后一条评论id加载后10条数据
     * 2017-10-12 11:43
     * lez
     */
    @ResponseBody
    @RequestMapping("/getMoreCommentByLastId")
    public List<CommentEntity> getMoreCommentByLastId(@RequestParam("storeId") Integer storeId,@RequestParam("commentId") Integer commentId){
    	return storeService.getMoreCommentByLastId(storeId,commentId);
    }
    /**
     * 根据关键字搜索店铺
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
     * 对店铺写评论
     */
    @RequestMapping("/writeComment")
    public String writeComment(@RequestParam("storeId") Integer storeId,@RequestParam("userId") Integer userId,
    		Map<String,Object> map){
    	map.put("storeId",storeId);
    	StoreInfo storeInfo=storeService.getStoreInfoById(storeId);
    	map.put("storeInfo",storeInfo);
    	WeixinUserInfo weixinUserInfo=userService.getUserInfoById(userId);
    	map.put("weixinUserInfo",weixinUserInfo);
    	//把此用户对该店铺的评价查询出来
    	List<CommentEntity> commentEntityList=storeService.getCommentByStoreIdAndUserId(storeId,userId);
    	map.put("commentEntityList", commentEntityList);
    	return "write_comment";
    }
    /**
     * 保存评论
     */
    @ResponseBody
    @RequestMapping("/saveComment")
    public void saveComment(@RequestParam("storeId") Integer storeId,@RequestParam("userId") Integer userId,
    		@RequestParam("content") String content){
    	storeService.saveComment(storeId,userId,content);
    }
}
