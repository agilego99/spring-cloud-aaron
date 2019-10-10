package com.aaron.fsh.user.conf;
import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;
import org.gordianknot.conf.client.core.SmconfUpdateCallBack;
import org.gordianknot.conf.client.core.rest.Conf;
import org.gordianknot.common.util.JWTUtils;
/**
 * RSA 配置信息 (多個系統共用)
 * @author aaron
 **/
@GordianknotConf(system = "gordianknot-common", env = true, prefix = "rsa")
public class RsaConf implements SmconfUpdateCallBack {
    @ConfField("模modulus")
    private String modulus = "120749774428185480467622030722535804071445078993623309709775427878906293937338059423076695854937532244466465395164541641368876529295825453848760144835049363522545908104302024165873971414491110512342791594610742544380402908598585190494003507524195754273822268813447403290817343077571516216147839402414940310061";
    @ConfField("公鑰指數")
    private String publicExponent = "65537";
    @ConfField("私鑰指數")
    private String privateExponent = "73923469942286919561803730971048133587965873619209827001168953680477872428610977313161774128992838682156392947263251899461404460204267953359475632559803617319478756560848229397545070273747796303141458040475889804016062973476403760709402857872540300632704514872361476749953797952016730690123983122643596231873";
    public String getModulus() {
        return modulus;
    }
    public void setModulus(String modulus) {
        this.modulus = modulus;
    }
    public String getPublicExponent() {
        return publicExponent;
    }
    public void setPublicExponent(String publicExponent) {
        this.publicExponent = publicExponent;
    }
    public String getPrivateExponent() {
        return privateExponent;
    }
    public void setPrivateExponent(String privateExponent) {
        this.privateExponent = privateExponent;
    }
    @Override
    public void reload(Conf conf) {
        // 當修改了privateExponent才進行初始化操作，因為這3個值是一套的，如果修改一個就開始reload會導致正在使用的報錯
        // 所以在修改的時候要按照順序來修改,修改到最後一個的時候才進行reload
        if ("privateExponent".equals(conf.getKey())) {
            JWTUtils.reload(this.modulus, this.privateExponent, this.publicExponent);
        }
    }
}
