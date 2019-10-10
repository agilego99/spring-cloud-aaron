package org.gordianknot.jdbc.keygen;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 根據機器IP獲取工作進程Id,如果線上機器的IP二進制表示的最後10位不重復,建議使用此種方式
 * ,列如機器的IP為192.168.1.108,二進制表示:11000000 10101000 00000001 01101100
 * ,截取最後10位 01 01101100,轉為十進制364,設置workerId為364.
 *
 */
public final class IPKeyGenerator implements KeyGenerator {
   
    private final DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();
   
    static {
        initWorkerId();
    }
   
    static void initWorkerId() {
        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
        } catch (final UnknownHostException e) {
            throw new IllegalStateException("Cannot get LocalHost InetAddress, please check your network!");
        }
        byte[] ipAddressByteArray = address.getAddress();
        DefaultKeyGenerator.setWorkerId((long) (((ipAddressByteArray[ipAddressByteArray.length - 2] & 0B11) << Byte.SIZE) + (ipAddressByteArray[ipAddressByteArray.length - 1] & 0xFF)));
    }
   
    @Override
    public Number generateKey() {
        return defaultKeyGenerator.generateKey();
    }
}
