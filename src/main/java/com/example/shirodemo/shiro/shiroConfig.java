package com.example.shirodemo.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {
    /**
     * 创建shiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * shiro内置过滤器，可以实现权限相关的拦截
         * 常用过滤器：anno  无需认证
         *              authc 需要认证
         */
        Map<String,String> map = new LinkedHashMap<String,String>(){{
            put("/list","authc");
            put("/login","anon");

            //授权过滤器
            put("/add","perms[user:add]");


        }};
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //修改没有登录，跳转页面
        shiroFilterFactoryBean.setLoginUrl("/login");


        //设置没有权限提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        return shiroFilterFactoryBean;

    }

    /**
     * 创建DefaultWebSecurityManager
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;


    };
    /**
     * 创建Realm
     */
    @Bean
    public UserRealm getRealm(){
        return new UserRealm();
    }
}
