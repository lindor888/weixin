package com.ctvit.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * 数据库exception类
 * @author tqc
 *
 */
public class AccessDBException extends RuntimeException {

	private static final long serialVersionUID = 6332933685409295592L;
	protected static Logger log = LoggerFactory.getLogger(AccessDBException.class);
	public AccessDBException() {
		super();
	}

	public AccessDBException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccessDBException(String message) {
		super(message);
	}

	public AccessDBException(Throwable cause) {
		log.error("db error");
	}

}
