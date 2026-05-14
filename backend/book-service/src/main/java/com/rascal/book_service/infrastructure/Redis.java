package com.rascal.book_service.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Redis {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    public void set(
        String key, Object value, 
        int minute
    ) {
        redisTemplate.opsForValue().set(
            key, value, 
            minute, TimeUnit.MINUTES
        );
        System.out.println("Set new value with key: " + key);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List<Object> mGet(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public void mSet(Map<String, Object> values) {
        redisTemplate.opsForValue().multiSet(values);
    }
    
}
