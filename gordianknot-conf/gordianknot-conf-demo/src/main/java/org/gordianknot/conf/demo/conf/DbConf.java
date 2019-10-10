package org.gordianknot.conf.demo.conf;
import org.gordianknot.conf.client.annotation.ConfField;
import org.gordianknot.conf.client.annotation.GordianknotConf;;
@GordianknotConf(system="gordianknot-conf-demo")
public class DbConf {
    @ConfField("超時時間")
    private int timeOut;
    
    @ConfField("最大時間")
    private long maxTime = 100;
    
    public long getMaxTime() {
        return maxTime;
    }
    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }
    public int getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }
    
}
