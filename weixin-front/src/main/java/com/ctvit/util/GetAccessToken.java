package com.ctvit.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.ctvit.bean.AccessTokenBean;
import com.ctvit.dao.FollowerDao;

public class GetAccessToken {

	private static final String get_AccessToken_url = "http://mb.mtq.tvm.cn/rest/wxaccesstoken?token=b30738df18&weixin_token=%s";
	private FollowerDao followersDao = new FollowerDao();

	public void getToken() {
		try {
			List<AccessTokenBean> list = followersDao.selectAccess();
			for (int i = 0; i < list.size(); i++) {
				AccessTokenBean bean = new AccessTokenBean();
				String url = String.format(get_AccessToken_url, list.get(i).getWeixin_token());
				String xml = HttpKit.get(url);
				String token = this.readxml(xml);
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = dateFormat.format(date);
				bean.setAccess_token(token);
				bean.setGet_time(time);
				bean.setId(list.get(i).getId());
				followersDao.updateAccessToken(bean);
			}
			System.out.println("更新accessToken成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String readxml(String xml) {
		Document doc = null;
		String accessToken = null;
		try {
			doc = DocumentHelper.parseText(xml);
			Element rootEelement = doc.getRootElement();
			accessToken = rootEelement.elementTextTrim("accestoken");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}
}
