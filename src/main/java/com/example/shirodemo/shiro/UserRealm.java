package com.example.shirodemo.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 执行授权逻辑
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("授权逻辑");
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源的授权字符串
//        info.addStringPermission("user:add");
        Subject subject = SecurityUtils.getSubject();

        //这个principal就是登录方法的第一个参数
        String principal = (String) subject.getPrincipal();
        info.addStringPermission(principal);



        return info;


    }

    /**
     * 执行登录逻辑
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("登录方法进来了");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = "root";
        String password = "root";
        String pers = new String("user:add");
        if (!username.equals(token.getUsername()))
            return null;
        return new SimpleAuthenticationInfo(pers, password, "");
    }
}
