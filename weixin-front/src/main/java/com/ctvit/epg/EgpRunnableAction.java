package com.ctvit.epg;

import com.ctvit.location.service.PushService;
import com.ctvit.location.service.impl.PushServiceImpl;

public class EgpRunnableAction extends Thread {

	private static final long serialVersionUID = -8281936063939885136L;

	@Override
	public void run() {
		boolean isstop = true;
		PushService reservationpush = new PushServiceImpl();
		EgpRunnableAction ea = new EgpRunnableAction();
		try {
			while (isstop) {
				synchronized (this) {
					Thread.sleep(1000 * 5);
					String msg = reservationpush.pushService();
					// System.out.println("main++:");
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/*
	 * public static void main(String [] args){
	 * 
	 * EgpRunnableAction egpRunnableAction = new EgpRunnableAction(); egpRunnableAction.start(); egpRunnableAction.start(); }
	 */
}
