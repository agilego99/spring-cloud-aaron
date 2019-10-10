package org.gordianknot.conf.web.service;

import java.util.List;

import org.gordianknot.conf.web.domain.UpdateLog;
import org.gordianknot.conf.web.repository.UpdateLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateLogServiceImpl implements UpdateLogService {
	
	@Autowired
	private UpdateLogRepository updateLogRepository;
	
	@Override
	public List<UpdateLog> list(String updateObjId) {
		return updateLogRepository.list(updateObjId);
	}

	@Override
	public void save(UpdateLog log) {
		updateLogRepository.save(log);
	}

}
