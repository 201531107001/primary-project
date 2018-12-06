package com.me.shiro.test3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;

public class Main {
	public static void main(String[] args) {
		// 1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化 SecurityManager
		Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory(
				"classpath:shiro-authorizer.ini");
		// 2、得到 SecurityManager 实例 并绑定给 SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);
		// 3、得到 Subject 及创建用户名/密码身份验证 Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("gqm", "990219");
		try {
			// 4、登录，即身份验证
			subject.login(token);
			
//			subject.hasRole("role1");
//			subject.checkPermission("update");
			//subject.checkPermission("update1");
			
			subject.checkPermission("user1:add");
			
//			System.out.println(subject.isPermitted("+user1+2"));//新增权限
//			System.out.println(subject.isPermitted("+user1+8"));//查看权限
//			System.out.println(subject.isPermitted("+user2+10"));//新增及查看
//			System.out.println(subject.isPermitted("+user1+4"));//没有删除权限
//			
//			System.out.println(subject.isPermitted("user1:add"));//新增权限
//			System.out.println(subject.isPermitted("user1:look"));//查看权限
//			System.out.println(subject.isPermitted("user2:add,look"));//新增及查看
//			System.out.println(subject.isPermitted("user2:delete"));//没有删除权限
			
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			System.out.println("身份验证失败");
		}catch (UnauthorizedException e) {
			// 用户没有权限
			System.out.println("用户没有权限");
		}
		if (subject.isAuthenticated()) {
			// 用户已经登录
			System.out.println("用户已经登录");
		}
		// 6、退出
		subject.logout();
	}
}
