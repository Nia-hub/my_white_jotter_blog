package com.nia.wj.config;

import com.nia.wj.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: wj
 * @description: WebConfigurer配置
 * @author: ganwei
 * @create: 2021-04-04 11:06
 **/
@Configuration
public class MyWebConfigurer implements WebMvcConfigurer {

    /**
     * 获取自定义拦截器
     * @return
     */
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    /**
     * 设置拦截哪些请求，不拦截哪些请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html");
    }

    /**
     * 跨域访问设置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //所有请求都允许跨域
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //所有/api/file/**开头的请求 都会去d:/upload/img/下查找资源
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/upload/img/");
    }
}
