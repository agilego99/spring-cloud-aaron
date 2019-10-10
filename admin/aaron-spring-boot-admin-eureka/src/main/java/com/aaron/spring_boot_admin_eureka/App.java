package com.aaron.spring_boot_admin_eureka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import de.codecentric.boot.admin.server.config.AdminServerProperties;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
/**
* Spring Boot Admin Web端示列
*
* @author aaron
*
* @about
*
* @date 2019-7-4
*
*/
// Eureka 啟動類
@EnableDiscoveryClient 
@EnableAdminServer
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Configuration
    public static class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
        private final String adminContextPath;
        public SecurityPermitAllConfig(AdminServerProperties adminServerProperties) {
            this.adminContextPath = adminServerProperties.getContextPath();
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
            successHandler.setTargetUrlParameter("redirectTo");
            // 靜態資源和登錄頁面可以不用認證
            http.authorizeRequests().antMatchers(adminContextPath + "/assets/**").permitAll()
                    .antMatchers(adminContextPath + "/login").permitAll()
                    // 其他請求必須認證
                    .anyRequest().authenticated()
                    // 自定義登錄和退出
                    .and().formLogin()
                    .loginPage(adminContextPath + "/login").successHandler(successHandler).and().logout()
                    .logoutUrl(adminContextPath + "/logout")
                    // 啓用HTTP-Basic，用於Spring Boot Admin Client註冊
                    .and().httpBasic()
                    .and().csrf().disable();
        }
    }
}
