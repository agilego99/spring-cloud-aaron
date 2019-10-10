package org.gordianknot.common.util;

import java.util.Random;

/**
 * 端口工具类
 *
 * @author aaron
 **/
public class ServerPortUtils {
    /**
     * 獲取可用端口，範圍2000-65535
     * @return
     */
    public static int getAvailablePort() {
        int max = 65535;
        int min = 2000;
        Random random = new Random();
        int port = random.nextInt(max)%(max-min+1) + min;
        boolean using = NetUtils.isLoclePortUsing(port);
        if (using) {
            return getAvailablePort();
        } else {
            return port;
        }
    }

}
