package com.me.config;

import java.util.Collections;

//import org.springframework.cache.CacheManager;

import net.sf.ehcache.CacheManager;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableCaching
public class CacheConfig {
	
//	/**
//	 * 基于最简单的内存map实现，常用的有Ehcache、redis等实现
//	 * @return
//	 */
//	@Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        cacheManager.setCaches(Collections.singletonList(new ConcurrentMapCache("studentCache")));
//        return cacheManager;
//    }
	
	/*--------------------EhCacheCacheManager--------------------*/
	@Bean
	public EhCacheCacheManager cacheCacheManager(CacheManager cacheManager) {
		return new EhCacheCacheManager(cacheManager);
	}
	
	@Bean
	public EhCacheManagerFactoryBean ehcache() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = 
				new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}
	
}
