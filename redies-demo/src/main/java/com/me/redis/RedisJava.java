package com.me.redis;

import redis.clients.jedis.Jedis;

public class RedisJava {
	
	static String constr = "192.168.147.128" ;
    public static Jedis getRedis(){
        Jedis jedis = new Jedis(constr) ;
        jedis.auth("990219");
        return jedis ;
    }
    
    public static void main(String[] args){
        Jedis jedis = RedisJava. getRedis() ;
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
