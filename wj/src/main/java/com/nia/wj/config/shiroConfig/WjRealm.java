package com.nia.wj.config.shiroConfig;

import com.nia.wj.pojo.User;
import com.nia.wj.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.elasticsearch.index.similarity.SimilarityProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName WjRealm
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/5 21:50
 * @VERSION 1.0
 **/
public class WjRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 重写获取授权信息的方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * (subject.login（）到这)获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //根据 token 中的用户名从数据库中查出user并返回
        String loginName = authenticationToken.getPrincipal().toString();
        User user = userService.getByLoinName(loginName);

        //获取密码、盐等
        String password = user.getPassword();
        String salt = user.getSalt();

        //获取认证信息
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(loginName, password, ByteSource.Util.bytes(salt), getName());
        return simpleAuthenticationInfo;
    }
}
