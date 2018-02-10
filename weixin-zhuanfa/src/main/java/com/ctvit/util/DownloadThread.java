package com.ctvit.util;

public class DownloadThread extends Thread{
	
	private String url;
	
	public DownloadThread(String url){
		this.url = url;
	}

	@Override
	public void run() {
		HttpKit.downloadZiyuan(url);
	}
}
