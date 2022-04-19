package com.khakimova.ipblocker;

import com.khakimova.ipblocker.config.property.IpRequestProperties;
import com.khakimova.ipblocker.config.property.RedisProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableConfigurationProperties({IpRequestProperties.class, RedisProperties.class})
public class IpBlockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpBlockerApplication.class, args);
	}

}
