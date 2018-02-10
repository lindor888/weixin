package com.ctvit.location.util;

import junit.framework.TestCase;

public class BaiduMapUtilsTest extends TestCase {

	private static final String ak = "lX2hUCMPoKTHLrQP3gzBg4Z9";

	public void testGeoconv() {
		System.out.println(BaiduMapUtils.geoconv("114.21892734521,29.575429778924;114.21892734521,29.575429778924", ak));
	}

	public void testLocal() {
		System.out.println(BaiduMapUtils.local("114.21892734521,29.575429778924", "酒店", ak));
	}

	public void testSearch() {
		System.out.println(BaiduMapUtils.search("29.575429778924,114.21892734521", "酒店", 10, 0, ak));
	}
}
