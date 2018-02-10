package com.ctvit.util;



import org.apache.log4j.Logger;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

public class MemcacheUtils {
	
	  private static Logger logger = Logger.getLogger(MemcacheUtils.class);

	  private static MemCachedClient cachedClient = null;

	  static{
	    logger.info("memcache的client开始初始化.");
	    cachedClient = new MemCachedClient();

	    SockIOPool pool = SockIOPool.getInstance();
	    
	    String memcachedServers = ResourceLoader.getInstance().getConfig().getProperty("memcachedServers");
	    
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

	  public static MemCachedClient getMemCachedClientInstance(){
		  return cachedClient;
	  }
}
