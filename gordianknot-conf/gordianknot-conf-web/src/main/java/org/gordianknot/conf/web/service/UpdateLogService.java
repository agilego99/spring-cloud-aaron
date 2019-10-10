package org.gordianknot.conf.web.service;

import java.util.List;

import org.gordianknot.conf.web.domain.UpdateLog;

public interface UpdateLogService {
	
	List<UpdateLog> list(String updateObjId);
	
	void save(UpdateLog log);
	
}
