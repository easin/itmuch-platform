package com.itmuch.platform.redis;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itmuch.platform.admin.domain.User;
import com.itmuch.platform.redis.service.RedisService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext.xml")
public class RedisServiceTest extends AbstractJUnit4SpringContextTests {
    @Resource
    private RedisService redisService;

    @Test
    public void testSet() {
        String key = "cms_key_user_000001";
        User user = new User();
        user.setId(1111111111);
        user.setPassword("dasfdfsdasfdfas");
        user.setDelFlag((byte) 0);
        this.redisService.set(key, user, 1000L);
    }

    @Test
    public void testGet() {
        User user = this.redisService.getByKey("cms_key_user_000001");
        Assert.assertTrue(1111111111 == user.getId());

        User user2 = this.redisService.getByKey("cms_key_user_000002");
        Assert.assertTrue(user2 == null);
    }

}
