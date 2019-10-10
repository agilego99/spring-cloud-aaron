package org.gordianknot.swagger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger.ui", value = "enable", matchIfMissing = true)
@EnableConfigurationProperties(SwaggerListProperties.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class SwaggerAutoConfiguration extends WebMvcConfigurerAdapter {
    protected static final Logger log = LoggerFactory.getLogger(SwaggerAutoConfiguration.class);
    @Autowired
    private SwaggerListProperties props;
    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 註冊多個配置
     * @return
     * @throws Exception
     */
    @Bean
    public Runnable dynamicConfiguration() throws Exception {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        List<SwaggerProperties> list = props.getConfs();
        int index = 0;
        
        for (SwaggerProperties conf : list) {
            //可以添加多個header或參數
            List<Parameter> parameters = new ArrayList<Parameter>();
            ParameterBuilder parameterBuilder = new ParameterBuilder();
            if (conf.isAuthHeader()) {
                 parameterBuilder.parameterType("header") //參數類型支持header, cookie, body, query etc
                 .name("Authorization") //參數名
                 .defaultValue("") //默認值
                 .description("API認證的TOKEN")
                 .modelRef(new ModelRef("string"))//指定參數值的類型
                 .required(false).build(); //非必需，這裡是全局配置，然而在登陸的時候是不用驗證的
                 parameters.add(parameterBuilder.build());
            }
            index++;
            Docket doc = new Docket(DocumentationType.SWAGGER_2).groupName(conf.getGroup()).apiInfo(apiInfo(conf))
                    .genericModelSubstitutes(DeferredResult.class).useDefaultResponseMessages(false)
                    .forCodeGeneration(false).pathMapping("/").select().paths(paths(conf))
                    .build().globalOperationParameters(parameters);
            beanFactory.registerSingleton("docket" + index, doc);
        }
        return null;
    }
    
    
    private Predicate<String> paths(SwaggerProperties conf) {
        List<Predicate<CharSequence>> predis = new ArrayList<Predicate<CharSequence>>();
        String[] ps = conf.getPaths().split(",");
        for (String path : ps) {
            predis.add(Predicates.containsPattern(path));
        }
        return Predicates.or(predis);
    }
    
    private ApiInfo apiInfo(SwaggerProperties conf) {
        return new ApiInfoBuilder()
                .title(conf.getTitle())
                .description(conf.getDescription())
                .termsOfServiceUrl(conf.getTermsOfServiceUrl())
                .contact(conf.getContact())
                .version(conf.getVersion())
                .license(conf.getLicense())
                .licenseUrl(conf.getLicenseUrl())
                .build();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //映射靜態文件目錄，如果還是不行，需要對項目添加server.context-path
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
