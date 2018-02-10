package com.ctvit.dao;

import java.util.List;

import com.ctvit.bean.Logger;

public interface LoggerMapper {
	
	void saveLog(Logger log);
	
	List<LoggerMapper> selectLog(Logger log);
	
	LoggerMapper selectLogById(int logId);
	
	void deleteById(int logId);

}
