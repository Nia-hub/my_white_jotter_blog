package com.nia.wj.config.shiroConfig;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.swing.*;

/**
 * @ClassName ShiroConfiguration
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/5 22:01
 * @VERSION 1.0
 **/
@Configuration
public class ShiroConfiguration {

    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getWjRealm());

        return securityManager;
    }

    @Bean
    public WjRealm getWjRealm() {

        WjRealm wjRealm = new WjRealm();
        wjRealm.setCredentialsMatcher(hashedCredentialsMatcher());

        return wjRealm;
    }

    /**
     * WjRealm中获取了认证信息后会掉这个方法
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {

        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//使用MD5加密策略
        hashedCredentialsMatcher.setHashIterations(2);//迭代两次

        return hashedCredentialsMatcher;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);

        return authorizationAttributeSourceAdvisor;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie rememberMeCookie(){
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setMaxAge(259200);//cookie过期时间单位 秒 3天
        return simpleCookie;
    }
}
