package com.ctvit.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheUtils {
	private static Logger logger = Logger.getLogger(MemcacheUtils.class);

	  private static MemCachedClient cachedClient = null;

	  static
	  {
	    logger.info("memcache的client开始初始化.");
	    cachedClient = new MemCachedClient();

	    SockIOPool pool = SockIOPool.getInstance();
	    String memcachedServers = ConfKit.get("memcachedServers");
		  
		String[] servers = memcachedServers.split(",");
		if(servers.length==0){
			System.out.println("no memcached");
		}
	    pool.setServers(servers);

	    pool.setInitConn(5);

	    pool.setMinConn(5);

	    pool.setMaxConn(250);

	    pool.setMaxIdle(10800000L);
	    pool.setMaintSleep(30L);

	    pool.setNagle(false);
	    pool.setSocketTO(3000);
	    pool.setSocketConnectTO(0);

	    pool.setMaintSleep(30L);

	    pool.initialize();
	  }

	  public static MemCachedClient getMemCachedClientInstance()
	  {
	    return cachedClient;
	  }
	  public static void main(String[] args) {
		  List<Map<String,String>> list =new ArrayList<Map<String,String>>();
		  Map<String,String> map = new HashMap<String,String>();
		  map.put("dec", "央视海采：测试11111");
		  map.put("pic", "http://p5.img.cctvpic.com/photoworkspace/2014/02/23/2014022308344157957.jpg");
		  map.put("title", "央视海采：测试11111");
		  map.put("url", "http://m.news.cntv.cn/2013/12/07/ARTI1386397137956738.shtml");
		  Map<String,String> map1 = new HashMap<String,String>();
		  map1.put("dec", "央视海采：测试222222");
		  map1.put("pic", "http://p5.img.cctvpic.com/photoworkspace/2014/02/23/2014022308344157957.jpg");
		  map1.put("title", "央视海采：测试222222");
		  map1.put("url", "http://m.news.cntv.cn/2013/12/07/ARTI1386397137956738.shtml");
		  Map<String,String> map2 = new HashMap<String,String>();
		  map2.put("dec", "央视海采：测试3333333");
		  map2.put("pic", "http://p5.img.cctvpic.com/photoworkspace/2014/02/23/2014022308344157957.jpg");
		  map2.put("title", "央视海采：测试33333");
		  map2.put("url", "http://m.news.cntv.cn/2013/12/07/ARTI1386397137956738.shtml");
		  Map<String,String> map3 = new HashMap<String,String>();
		  map3.put("dec", "央视海采：测试444444");
		  map3.put("pic", "http://p5.img.cctvpic.com/photoworkspace/2014/02/23/2014022308344157957.jpg");
		  map3.put("title", "央视海采：测试444444");
		  map3.put("url", "http://m.news.cntv.cn/2013/12/07/ARTI1386397137956738.shtml");
		  list.add(map);
		  list.add(map1);
		  list.add(map2);
		  list.add(map3);
		    MemCachedClient instance2 = getMemCachedClientInstance();
		    instance2.set("morenshuju",list);
	    instance2.set("90EDF055FC9918819616F95561EF10D5",
		    		"http://news.cntv.cn/2013/01/31/VIDE1359617404179765.shtml13",new Date(60000L));
System.out.println("+++++++++++++++++++++++++++++++++++++++"+System.currentTimeMillis());
SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
System.out.println("0000000000000000000000000"+s.format(new Date()));
s.format(System.currentTimeMillis());
instance2.set("qiangzhifanhui", "false");
instance2.delete("qiangzhifanhui");
System.out.println(EncryptUtils.toMessageDigest("wwww"));
String ss ="<xml><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[欢迎您关注央视新闻微信订阅号，您可以回复任意词语阅读新闻。" +
		"如果需要帮助，请回复0。\n如果需要反馈意见请回复#+留言。]]>" +
		"</Content><ToUserName><![CDATA[%1$s]]></ToUserName><FromUserName>" +
		"<![CDATA[%2$s]]></FromUserName><CreateTime><![CDATA[%3$s]]></CreateTime></xml>";
//E34A8899EF6468B74F8A1048419CCC8B
System.out.println(ss);
System.out.println(ConfKit.get("welcome"));
String ss1 =ConfKit.get("welcome");
System.out.println(ss1);
instance2.set("E34A8899EF6468B74F8A1048419CCC8B", ConfKit.get("nomessage"));
instance2.delete("E34A8899EF6468B74F8A1048419CCC8B");

		    Object object = instance2.get("morenshuju");
		    instance2.delete("morenshuju");
		    instance2.delete(EncryptUtils.toMessageDigest("8888"));
		    for(int i=0;i<4;i++){
		    	System.out.println(i);	
			}
		    System.out.println(object);
		  }
}
