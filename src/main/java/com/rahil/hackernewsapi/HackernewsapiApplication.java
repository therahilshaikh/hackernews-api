package com.rahil.hackernewsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@SpringBootApplication
public class HackernewsapiApplication {

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Autowired
    private RedisConnectionFactory redisConnectionFactory;

	// this method clears redis cache on app start
    @EventListener(ApplicationReadyEvent.class)
    public void clearCacheOnStartup() {
        redisConnectionFactory.getConnection().serverCommands().flushAll();;
    }

	public static void main(String[] args) {
		SpringApplication.run(HackernewsapiApplication.class, args);
	}

}
