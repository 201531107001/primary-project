package com.me.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class TestJredisPool {
	
	@Test
	public void test(){
		
        Jedis jedis = RedisPool.getJedis() ;
        Jedis jedis2 = RedisPool.getJedis() ;
        
        System.out.println("jedis1 == jedis2 " + (jedis == jedis2));
        
        System.out.println("Server is running: " + jedis.ping());
        //string
        System.out.println(jedis.get("name"));
        //hash
        System.out.println(jedis.hgetAll("people"));
        //list
        System.out.println(jedis.lrange("brother", 0, 2));
        //set
        System.out.println(jedis.smembers("sex"));
        //sorted set
        System.out.println(jedis.zrange("score", 0, 3));
    }
}
