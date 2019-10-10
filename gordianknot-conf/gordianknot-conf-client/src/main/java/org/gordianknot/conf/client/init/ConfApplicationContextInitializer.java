package org.gordianknot.conf.client.init;
import org.gordianknot.conf.client.util.CommonUtil;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.concurrent.atomic.AtomicBoolean;
/**
 * 配置初始化加載器
 *
 * @author aaron
 * @create 2019-06-15
 **/
public class ConfApplicationContextInitializer implements ApplicationContextInitializer {
    private static AtomicBoolean acBoolean = new AtomicBoolean(false);
    public void initialize(ConfigurableApplicationContext applicationContext) {
        if (acBoolean.compareAndSet(false, true)) {
            //啓動時需要配置來做連接，需要在spring啓動前將一些配置信息加載到環境變量使用,多個包用逗號隔開
            SmconfInit.init(CommonUtil.getConfPackagePaths());
        }
    }
}
