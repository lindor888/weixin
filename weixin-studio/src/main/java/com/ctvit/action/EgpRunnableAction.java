package com.ctvit.action;

import com.opensymphony.xwork2.ActionSupport;

public class EgpRunnableAction extends ActionSupport implements Runnable {
	
    public void run() {
    	 //创建对象
	    boolean isstop = true;
        EgpRunnableAction ea = new EgpRunnableAction();
        Thread t = new Thread(ea);
        //启动
        t.start();
        try{
                 while(isstop){
                          Thread.sleep(1000*3);
                          System.out.println("main:");
                          }
        }catch(Exception e){
      	  
      	
        }
}
}
