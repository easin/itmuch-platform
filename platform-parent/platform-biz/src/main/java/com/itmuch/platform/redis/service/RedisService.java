package com.itmuch.platform.redis.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import com.itmuch.platform.admin.domain.User;

/**
 * 本类演示了redis的增删改查
 * 参考文档: http://stamen.iteye.com/blog/1907984
 * @author zhouli
 */
@Service
public class RedisService {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 插入/修改值
     * @param key key
     * @param value value
     * @param timeout 超时时间(秒)
     */
    public void set(String key, Object value, Long timeout) {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        this.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 查询
     * @param key
     * @return 查询结果
     */
    public User getByKey(String key) {
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<User>(User.class));
        return (User) this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 单条删除
     * @param key
     */
    public void delByKey(String key) {
        this.redisTemplate.delete(key);
    }

    /**
     * 批量删除
     * @param keys
     */
    public void delByKeys(List<String> keys) {
        this.redisTemplate.delete(keys);
    }

}
