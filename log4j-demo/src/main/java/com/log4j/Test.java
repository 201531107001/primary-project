package com.log4j;


import org.apache.log4j.Logger; 
public class Test {
	private static final Logger logger = Logger.getLogger(Test.class); 
	
	public static void main(String[] args){
        
        //使用默认的配置信息，不需要写log4j.properties  
        //BasicConfigurator.configure();  
        //设置日志输出级别为info，这将覆盖配置文件中设置的级别  
        //logger.setLevel(Level.DEBUG);  
        //下面的消息将被输出  
        logger.info("this is an "+5+"info");  
        logger.warn("this is a warn");  
        logger.error("this is an error");  
        logger.fatal("this is a fatal");
        logger.debug("this is a debug");
	}
}
