package com.heaven.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    @Autowired
//    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    public  void setKey(String key,Integer value){
        redisTemplate.opsForValue().set(key,value);
    }
    public void setTex(String key,Integer value){
        redisTemplate.opsForValue().set(key,value,60, TimeUnit.SECONDS);
    }
    public Boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }
    public Long subKey(String key){
        Long count = redisTemplate.opsForValue().decrement(key);
        return count;
    }
    public void setHash(String name,String key,Object value){
        redisTemplate.opsForHash().put(name,key,value);
    }
    public Object getHash(String name,String key){
        return redisTemplate.opsForHash().get(name, key);
    }
    public Boolean exitHashKey(String name,String key){
        return redisTemplate.opsForHash().hasKey(name,key);
    }
    public List getAllHashValue(String name){
        List<Object> objects = redisTemplate.opsForHash().values(name);
        return objects;
    }
    public void deleteHashValue(String name,String key){
       redisTemplate.opsForHash().delete(name, key);
    }
}
