package com.me.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.me.service.ShiroService;

public class MyShiroRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);
	
	@Autowired
	private ShiroService shiroService;

	// 授权器
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 根据自己的需求编写获取授权信息，这里简化代码获取了用户对应的所有权限
		String username = (String) principals.fromRealm(getName()).iterator().next();
		
		if (username != null) {
			List<String> perms = shiroService.getPermissionByUserName(username);
			if (perms != null && !perms.isEmpty()) {
				SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
				for (String each : perms) {
					// 将权限资源添加到用户信息中
					info.addStringPermission(each);
				}
				return info;
			}
		}
		return null;
	}

	// 认证器
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		// 通过表单接收的用户名，调用currentUser.login的时候执行
		String username = token.getUsername();
		logger.info("用户:{}登录",username);
		if (username != null && !"".equals(username)) {
			// 查询密码
			String password = shiroService.getPasswordByUserName(username);
			return new SimpleAuthenticationInfo(username, password, getName());
		}
		return null;
	}

}
