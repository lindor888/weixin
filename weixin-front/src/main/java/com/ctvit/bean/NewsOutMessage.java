/**
 * 微信公众平台开发模式(JAVA) SDK
 */
package com.ctvit.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 输出图文消息
 * 
 * author yangshijia
 * 
 */
public class NewsOutMessage extends OutMessage {

	private String			MsgType	= "news";
	private Integer			ArticleCount;


	private List<Articles>	Articles;

	public String getMsgType() {
		return MsgType;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public List<Articles> getArticles() {
		return Articles;
	}

	public void setArticles(List<Articles> articles) {
		if (articles != null) {
			if (articles.size() > 10)
				articles = new ArrayList<Articles>(articles.subList(0, 10));

			ArticleCount = articles.size();
		}
		Articles = articles;
	}
}
