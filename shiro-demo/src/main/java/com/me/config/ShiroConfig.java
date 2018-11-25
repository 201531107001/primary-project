package com.me.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.shiro.MyShiroRealm;

@Configuration
public class ShiroConfig {
	
	@Bean("securityManager")
	public SecurityManager getSecurityManager(@Qualifier("myShiroRealm") AuthorizingRealm authorizingRealm) {
		return new DefaultWebSecurityManager(authorizingRealm);
	}
	
	@Bean("myShiroRealm")
	public AuthorizingRealm getAuthorizingRealm() {
		return new MyShiroRealm();
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
				@Qualifier("securityManager") SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		//去登录的地址
		shiroFilter.setLoginUrl("/gologin");
		//登录成功的跳转地址
		shiroFilter.setSuccessUrl("/pIndex.jsp");
		//验证失败的跳转地址
		shiroFilter.setUnauthorizedUrl("/error.jsp");
		
		Map<String, String> map = new HashMap<>();
		map.put("/index.jsp", "anon");
		map.put("/login", "anon");
		map.put("/logout", "anon");
		map.put("/error.jsp", "anon");
		map.put("/gologin", "anon");
		map.put("/dolook", "authc,perms[look]");
		map.put("/doadd", "authc,perms[add]");
		map.put("/doupdate", "authc,perms[update]");
		map.put("/dodelete", "authc,perms[delete]");
		map.put("/dolook", "authc,perms[look]");
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}
}









