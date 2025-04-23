package org.example.redisson.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;

@Configuration
public class RedisConfig {

    public static final String REDIS_PREFIX = "custom:";

    public static class MyStringRedisSerializer extends StringRedisSerializer {
        @Nullable
        public byte[] serialize(@Nullable String value) {
            value = REDIS_PREFIX + value;
            return super.serialize(value);
        }
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new MyStringRedisSerializer());
        return redisTemplate;
    }
}
