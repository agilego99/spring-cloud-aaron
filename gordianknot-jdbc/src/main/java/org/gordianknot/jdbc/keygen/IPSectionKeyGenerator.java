package org.gordianknot.jdbc.keygen;
import java.net.InetAddress;
import java.net.UnknownHostException;
/**
 * 瀏覽 {@link IPKeyGenerator} workerId生成的規則後，感覺對服務器IP後10位（特別是IPV6）數值比較約束.
 *
 * <p>
 * 有以下優化思路：
 * 因為workerId最大限制是2^10，我們生成的workerId只要滿足小於最大workerId即可。
 * 1.針對IPV4:
 * ....IP最大 255.255.255.255。而（255+255+255+255) < 1024。
 * ....因此採用IP段數值相加即可生成唯一的workerId，不受IP位限制。
 * 2.針對IPV6:
 * ....IP最大ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff
 * ....為了保證相加生成出的workerId < 1024,思路是將每個bit位的後6位相加。這樣在一定程度上也可以滿足workerId不重復的問題。
 * </p>
 * 使用這種IP生成workerId的方法,必須保證IP段相加不能重復
 *
 */
public final class IPSectionKeyGenerator implements KeyGenerator {
   
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
        long workerId = 0L;
        //IPV4
        if (ipAddressByteArray.length == 4) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0xFF;
            }
            //IPV6
        } else if (ipAddressByteArray.length == 16) {
            for (byte byteNum : ipAddressByteArray) {
                workerId += byteNum & 0B111111;
            }
        } else {
            throw new IllegalStateException("Bad LocalHost InetAddress, please check your network!");
        }
        DefaultKeyGenerator.setWorkerId(workerId);
    }
   
    @Override
    public Number generateKey() {
        return defaultKeyGenerator.generateKey();
    }
}
