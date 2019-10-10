package org.gordianknot.conf.web;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
/**
* Smconf程序啓動入口

* simple manage conf
* @author aaron
*/
@SpringBootApplication
@ServletComponentScan
public class App {
    public static void main(String[] args) {
        //將參數設置到環境變量中
        if (args != null) {
            for (String arg : args) {
                if (arg.startsWith("--")) {
                    String[] strs = arg.split("=");
                    System.setProperty(strs[0].substring(2), strs[1]);
                }
            }
        }
        SpringApplication.run(App.class, args);
    }
}
