package com.ctvit.strategy;



import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctvit.bean.Menus;
import com.ctvit.dao.MenusDao;
import com.ctvit.util.CiphertextUtil;
import com.ctvit.util.ConfKit;
import com.ctvit.util.HttpKit;

/**
 * 签到策略
 * @author tqc
 *
 */
public class SignInStrategy {
	
	protected static Logger log = LoggerFactory.getLogger(SignInStrategy.class);
	
	private String account;
	
	private String materialId;
	
	private String openId;
	
	private MenusDao menusDao = new MenusDao();
	
	public SignInStrategy(String account, String materialId, String openId){
		this.account = account;
		this.materialId = materialId;
		this.openId = openId;
	}
	
	
	/**
	 * 通过素材id和公众号id查询菜单名称是否为签到，如果为签到则调用积分的接口
	 * @return
	 */
	public String run() {
		try{
			Menus menus = menusDao.select(account, materialId);
			System.out.println("men + = "+menus.getName());
			if(menus!=null){
				String name = menus.getName();
				log.info(name);
				if(name.indexOf("签到")!=-1){
					//调用签到的url
					String signUrl = ConfKit.get("signUrl");
					String signCid = ConfKit.get("signCid");
					String signKey = ConfKit.get("signKey");
					String params = "c_id="+signCid+"&seqno="+signCid+System.currentTimeMillis()+this.getRandomNum()+"&user_id="+openId;
					String verify = "&verify=" + CiphertextUtil.encryptMD5(params+"&key="+signKey);
					signUrl = signUrl + "?" + params + verify;
					log.debug("url="+signUrl);
					System.out.println("params==="+params);
					System.out.println("verify==="+verify);
					System.out.println("signUrl==="+signUrl);
					String jsonStr = HttpKit.get(signUrl);
					System.out.println("jsonStr===="+jsonStr);
					log.debug(jsonStr);
					if(!"".equals(jsonStr)){
						String returnStr = "";
						JSONObject jsonObject = JSONObject.fromObject(jsonStr);
						String allScore = jsonObject.getString("usable_score");
						String signScore = jsonObject.getString("sign_score");
						String code = jsonObject.getString("code");
						returnStr = "<xml><ToUserName>%1$s</ToUserName><FromUserName>%2$s</FromUserName><CreateTime>%3$s</CreateTime><MsgType><![CDATA[text]]></MsgType><Content>";
						//当天已签到
						if("201".equals(code)){
							returnStr += "今天您已经签到！您当前积分为" + allScore;
						}else if("200".equals(code)){
							returnStr += "恭喜您获得" + signScore + "积分，感谢您的持续关注！您当前积分为" + allScore;
						}
						returnStr += "</Content></xml>";
						
						log.debug(returnStr);
						return returnStr;
					}
				}
			}
		}catch(Exception e){
			log.error("sign in error", e);
		}
		return "";
	}
	
	/**
	 * 获取四位随机数
	 * @return
	 */
	private int getRandomNum(){
		return (int)(Math.random()*9000+1000);
	}
	
}
