package com.aaron.apollo_springboot.configservice;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReleaseMessageScanner implements InitializingBean {

	@Autowired
	private NotificationControllerV2 configController;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// 定時任務從數據庫掃描有沒有新的配置發布
		new Thread(() -> {
			for (;;) {
				String result = NotificationControllerV2.queue.poll();
				if (result != null) {
					ReleaseMessage message = new ReleaseMessage();
					message.setMessage(result);
					configController.handleMessage(message);
				}
			}
		}).start();;
	}

}
