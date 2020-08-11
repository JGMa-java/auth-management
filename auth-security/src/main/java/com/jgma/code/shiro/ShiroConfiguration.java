package com.jgma.code.shiro;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created By majg on 2020-08-11
 */
@Configuration
public class ShiroConfiguration {

    @Autowired
    private UserRealm userRealm;

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);

        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
//        设置session过期时间为1小时3600000L(单位：毫秒)，默认为30分钟
        defaultWebSessionManager.setGlobalSessionTimeout(3600000L);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);

        securityManager.setSessionManager(defaultWebSessionManager);
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("authc", new MyShiroFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String,String> map = new HashMap<String, String>();
        // todo 放开所有接口
        map.put("/**", "anon");
        map.put("/sys/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

//        Map<String,String> map = new HashMap<String, String>();
//        //登出
//        map.put("/logout","logout");
//        //对所有用户认证
//        map.put("/**","authc");
//        //登录
//        shiroFilterFactoryBean.setLoginUrl("/sys/login");
//        //首页
//        shiroFilterFactoryBean.setSuccessUrl("/index");
//        //错误页面，认证不通过跳转
//        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //加入注解的使用，不加入这个注解不生效
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
