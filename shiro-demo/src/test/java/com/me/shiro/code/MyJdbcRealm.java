package com.me.shiro.code;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class MyJdbcRealm extends AuthorizingRealm{
    
    private static Map<String, String> passwordMap = new HashMap<>();
    private static Map<String, String> saltMap = new HashMap<>();
    
    static {
        passwordMap.put("gqm", "a42853d4f89daa0563b051f6c8723e67");
        saltMap.put("gqm", "12069d28be356fef413d32ea7a85d0cc");
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
            throws AuthenticationException {
        String username = (String) token.getPrincipal();
        // 模拟从数据库中获取
        String password = passwordMap.get(username);
        String salt = saltMap.get(username);
        
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(username, password, getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(username+salt)); //盐是用户名+随机数
        return info;
    }

}
