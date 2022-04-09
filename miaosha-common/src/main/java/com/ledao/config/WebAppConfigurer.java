package com.ledao.config;

import com.ledao.interceptor.SysInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现跨域
 *
 * @author LeDao
 * @company
 * @create 2022-03-29 22:32
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600);
    }*/

    @Bean
    public SysInterceptor sysInterceptor() {
        return new SysInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //没有登录也不会被拦截的请求
        List<String> patterns=new ArrayList<>();
        patterns.add("/user/login");
        patterns.add("/user/checkUserName");
        patterns.add("/user/checkPhone");
        patterns.add("/user/register");
        patterns.add("/user/loginAuto");
        patterns.add("/rabbitmq/sendInformation");
        registry.addInterceptor(sysInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
