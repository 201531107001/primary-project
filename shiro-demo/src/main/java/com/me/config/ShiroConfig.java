package com.me.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.SslFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.h2.util.New;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.me.shiro.MyShiroRealm;

@Configuration
public class ShiroConfig {
	
	@Bean("securityManager")
	public SecurityManager getSecurityManager
	            (@Qualifier("myShiroRealm") AuthorizingRealm authorizingRealm,
	                    CookieRememberMeManager rememberMeManager) {
	    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(authorizingRealm);
	    securityManager.setRememberMeManager(rememberMeManager);
	    return securityManager;
	}
	
	@Bean("myShiroRealm")
	public AuthorizingRealm getAuthorizingRealm(CacheManager cacheManager) {
	    MyShiroRealm realm = new MyShiroRealm();
	    realm.setCachingEnabled(true);
//	    realm.setAuthenticationCachingEnabled(true);
//	    realm.setAuthenticationCacheName("authenticationCacheName");
	    realm.setAuthorizationCachingEnabled(true);
	    realm.setAuthorizationCacheName("authorizationCacheName");
	    realm.setCacheManager(cacheManager);
		return realm;
	}
	
	@Bean("cacheManager")
	public CacheManager getCacheManager() {
	    EhCacheManager cacheManager = new EhCacheManager();
	    cacheManager.setCacheManager(new net.sf.ehcache.CacheManager());
	    cacheManager.setCacheManagerConfigFile("ehcache.xml");
	    return cacheManager;
	}
	
	/**
	 * 权限注解的支持
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
	    AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
	    aasa.setSecurityManager(securityManager);
	    return aasa;
	}
	
	@Bean("rememberMeCookie")
	public SimpleCookie rememberMeCookie() {
	    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
	    simpleCookie.setHttpOnly(true);
	    simpleCookie.setMaxAge(1000);
	    return simpleCookie;
	}
	
	@Bean
	public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie rememberMeCookie) {
	    CookieRememberMeManager manager = new CookieRememberMeManager();
	    manager.setCookie(rememberMeCookie);
	    //cipherKey 是加密 rememberMe Cookie 的密钥；默认 AES 算法
	    manager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
	    return manager;
	}
	
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter() {
	    FormAuthenticationFilter filter = new FormAuthenticationFilter();
	    filter.setRememberMeParam("rememberMe");
	    return filter;
	}
	
	@Bean
	public SslFilter getSslFilter() {
	    SslFilter sslFilter = new SslFilter();
	    sslFilter.setPort(8443);
	    return sslFilter;
	}
	
	public static void main(String[] args) {
	    System.out.println(new String(Base64.decode("4AvVhmFLUs0KTA3Kprsdag==")));
	    System.out.println(new String(Base64.decode(new String(Base64.encode("990219".getBytes())))));
	}
	
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
				@Qualifier("securityManager") SecurityManager securityManager,
				SslFilter sslFilter) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		//去登录的地址
		shiroFilter.setLoginUrl("/gologin");
		//登录成功的跳转地址
		shiroFilter.setSuccessUrl("/pIndex.jsp");
		//验证失败的跳转地址
		shiroFilter.setUnauthorizedUrl("/error.jsp");
		
		Map<String, Filter> fMap = new HashMap<>();
		fMap.put("ssl", sslFilter);
		shiroFilter.setFilters(fMap);
		
		Map<String, String> map = new HashMap<>();
		map.put("/index.jsp", "anon");
		map.put("/gologin", "ssl,anon");
		map.put("/login", "anon");
		map.put("/logout", "anon");
		map.put("/error.jsp", "anon");
		map.put("/gologin", "anon");
		map.put("/dolook", "authc,perms[look]");
		map.put("/doadd", "authc,perms[add]");
		map.put("/doupdate", "authc,perms[update]");
		map.put("/dodelete", "authc,perms[delete]");
		map.put("/dolook", "user,perms[look]");
		shiroFilter.setFilterChainDefinitionMap(map);
		return shiroFilter;
	}
}









