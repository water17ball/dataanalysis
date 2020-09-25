package cn.wp.bigdata.dataanalysis.config;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 全局跨域设置
 */
@Slf4j
@Configuration
@ConfigurationProperties("cors.global")
public class GlobalCorsConfig {

    @Setter
    private boolean enable;
    @Setter
    private String[] allowedOrigins;
    @Setter
    private String[] allowedMethods;
    @Setter
    private String[] allowedHeaders;


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                if (enable) {
                    log.info("global cors is enabled. allowedOrigins={}, allowedMethods={}, allowedHeaders={}",
                            allowedOrigins, allowedMethods, allowedHeaders);
                    //添加映射路径
                    registry.addMapping("/**")
                            // 允许跨域的域名
                            .allowedOrigins(allowedOrigins)
                            //是否发送Cookie信息
                            .allowCredentials(true)
                            // 请求方法
                            .allowedMethods(allowedMethods)
                            // 头部信息
                            .allowedHeaders(allowedHeaders);
                }
            }
        };
    }
}