package com.aaron.apollo_springboot.configservice;

public interface ReleaseMessageListener {
	void handleMessage(ReleaseMessage message);
}
