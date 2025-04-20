package com.example.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
public class CustomRedisConfig {
  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    // 這裡自訂 host、port、password 的方式要改成用POJO模式
    RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
    config.setHostName("127.0.0.1");
    config.setPort(6379);
    //config.setPassword("your-password");
    return new LettuceConnectionFactory(config);
  }
}
