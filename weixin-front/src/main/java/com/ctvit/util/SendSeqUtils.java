package com.ctvit.util;

public class SendSeqUtils {
	public static String createSendSeq() {
		return "" + System.currentTimeMillis() + ((int) ((Math.random() * 9 + 1) * 100000));
	}
}
