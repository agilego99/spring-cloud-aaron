package org.gordianknot.common.base;
import org.gordianknot.common.util.ServerPortUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
/**
 * 啓動參數設置類
 *
 * @author aaron
 **/
public class StartCommand {
 	private Logger logger = LoggerFactory.getLogger(StartCommand.class);
 	public StartCommand(String[] args) {
 		Boolean isServerPort = false;
 		String serverPort = "";
 		if (args != null) {
 			for (String arg : args) {
 				if (StringUtils.hasText(arg) && arg.startsWith("--server.port")) {
 					isServerPort = true;
 					serverPort = arg;
 					break;
 				}
 			}
 		}
 		// 沒有指定端口，則隨機生成一個可用的端口
 		if (!isServerPort) {
 			int port = ServerPortUtils.getAvailablePort();
 			logger.info("current server.port=" + port);
 			System.setProperty("server.port", String.valueOf(port));
 		} else {
 			logger.info("current server.port=" + serverPort.split("=")[1]);
 			System.setProperty("server.port", serverPort.split("=")[1]);
 		}
 	}
 	
}
