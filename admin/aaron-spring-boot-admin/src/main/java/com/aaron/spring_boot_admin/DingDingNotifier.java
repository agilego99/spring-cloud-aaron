package com.aaron.spring_boot_admin;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import reactor.core.publisher.Mono;
import java.util.Map;
/**
 * 服務監控釘釘通知
 * 通過繼承 AbstractStatusChangeNotifier 實現釘釘發送機制
 * @author aaron
 * @date 2019-7-5
 */
public class DingDingNotifier extends AbstractStatusChangeNotifier {
    public DingDingNotifier(InstanceRepository repository) {
        super(repository);
    }
    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
    	String serviceName = instance.getRegistration().getName();
    	String serviceUrl = instance.getRegistration().getServiceUrl();
    	String status = instance.getStatusInfo().getStatus();
    	Map<String, Object> details = instance.getStatusInfo().getDetails();
    	
    	System.err.println("發送至阿里釘釘警報系統");
    	StringBuilder str = new StringBuilder();
    	str.append("【" + serviceName + "】");
    	str.append("【服務地址】" + serviceUrl);
    	str.append("【狀態】" + status);
    	str.append("【詳情】" + JsonUtils.toJson(details));
    	System.err.println(str);
        return Mono.fromRunnable(() -> {
        	DingDingMessageUtil.sendTextMessage(str.toString());
        });
    }
}
