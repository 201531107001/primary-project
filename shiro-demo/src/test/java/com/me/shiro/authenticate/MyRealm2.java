package com.me.shiro.authenticate;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealm2 implements Realm{
	private static final Logger logger = LoggerFactory.getLogger(MyRealm2.class);
	
	//返回一个唯一的 Realm 名字
	@Override
	public String getName() {
		return "MyRealm2";
	}

	//判断此 Realm 是否支持此 Token
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	//根据 Token 获取认证信息
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) 
													throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		logger.info("用户{}登录，密码为{}",username,password);
		if("gqm".equals(username)&&"990219".equals(password)) {
			//如果身份认证验证成功，返回一个 AuthenticationInfo 实现；
			return new SimpleAuthenticationInfo("990219@gqm.com", password, getName());
		}else {
			throw new AuthenticationException();
		}
	}

}
