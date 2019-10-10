package org.gordianknot.jdbc.keygen;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 根據機器名最後的數字編號獲取工作進程Id.如果線上機器命名有統一規範,建議使用此種方式.
 * 列如機器的HostName為:dangdang-db-sharding-dev-01(公司名-部門名-服務名-環境名-編號)
 * ,會截取HostName最後的編號01作為workerId.
 **/
public final class HostNameKeyGenerator implements KeyGenerator {
    private final DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
    static {
        initWorkerId();
    }
   
    static void initWorkerId() {
        InetAddress address;
        Long workerId;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        String hostName = address.getHostName();
        try {
            workerId = Long.valueOf(hostName.replace(hostName.replaceAll("\\d+$", ""), ""));
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Wrong hostname:%s, hostname must be end with number!", hostName));
        }
        DefaultKeyGenerator.setWorkerId(workerId);
    }
    @Override
    public Number generateKey() {
        return defaultKeyGenerator.generateKey();
    }
}
