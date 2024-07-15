package com.eight.palette.global.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private String redisPort;

    private static final String REDISSON_PREFIX = "redis://";

    public static final Long WAIT_TIME = 30L;

    public static final Long LEASE_TIME = 10L;

    @Bean
    public RedissonClient redissonClient() {

        Config config = new Config();

        config.useSingleServer()
                .setAddress(REDISSON_PREFIX + redisHost + ":" + redisPort);

        return Redisson.create(config);

    }

}
