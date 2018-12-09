package com.me.shiro.authenticate;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.junit.Test;

/**
 * 认证器测试 
 * SecurityManager 接口继承了 Authenticator，
 * a.另外还有一个 ModularRealmAuthenticator实现， 其委托给多个 Realm 进行验证，验证规则通过 
 * AuthenticationStrategy 接口指定，默认提供 的实现：
 * FirstSuccessfulStrategy：只要有一个 Realm 验证成功即可，只返回第一个 Realm 身份验证 成功的认证信息，其他的忽略；
 * AtLeastOneSuccessfulStrategy：只要有一个 Realm 验证成功即可，和
 * FirstSuccessfulStrategy不同，返回所有 Realm 身份验证成功的认证信息；
 * AllSuccessfulStrategy：所有 Realm 验证成功才算成功，且返回所有
 * Realm 身份验证成功的 认证信息，如果有一个失败就失败了。 
 * ModularRealmAuthenticator 默认使用AtLeastOneSuccessfulStrategy 策略
 * 
 * @author 清明
 *
 */
public class AuthenticatorTest {
	@Test
	public void testAllSuccessfulStrategyWithSuccess() {
		login("classpath:shiro-authenticator-all-success.ini");
		Subject subject = SecurityUtils.getSubject();
		// 得到一个身份集合，其包含了 Realm 验证成功的身份信息
		PrincipalCollection principalCollection = subject.getPrincipals();
		System.out.println(principalCollection.asList());
	}
	
	private void login(String configFile) {
		// 1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
		// 2、得到 SecurityManager 实例 并绑定给 SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("gqm", "990219");
		subject.login(token);
	}
}
