package com.me.shiro.code;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * 密码重试次数限制
 * @author 清明
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    
    public static Map<String, Integer> passwordRetryCache = new HashMap<>();
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        // retry count + 1
        Integer element = passwordRetryCache.get(username);
        if (element == null) {
            element = 1;
            passwordRetryCache.put(username,element);
        }else {
            passwordRetryCache.put(username,passwordRetryCache.get(username)+1);
        }
        
        Integer count = passwordRetryCache.get(username);
        if (count == 5) {
            // if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
