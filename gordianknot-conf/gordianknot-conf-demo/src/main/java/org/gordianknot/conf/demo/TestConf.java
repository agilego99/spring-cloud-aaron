package org.gordianknot.conf.demo;
import org.gordianknot.conf.demo.conf.Biz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring.xml" })
public class TestConf {
    @Autowired
    private Biz biz;
    //可以在sleep的時候去後台進行值的修改，最後輸出的值是修改後的值
    @Test
    public void test() {
        System.out.println(biz.getMax());
        try {
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(biz.getMax());
    }
}
