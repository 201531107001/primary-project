package com.spring.primary.ioc.first;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CDPlayConfig2 {
    
    @Bean
    public MediaPlayer getMediaPlayer(CompactDisc cd) {
        return new CDPlayer(cd);
    }
    
    @Bean CompactDisc getCompactDisc() {
        return new SgtPeppers();
    }
}
