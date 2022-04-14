package com.khakimova.ipblocker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories
@SpringBootApplication
public class IpBlockerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpBlockerApplication.class, args);
	}

}
