package com.itmuch.platform.webservice.client.jaxrs;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itmuch.platform.webservice.client.jaxrs.IRestUserClient;
import com.itmuch.platform.webservice.client.jaxrs.RestResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext.xml")
public class RestUserClientTest {
    @Resource
    private IRestUserClient restUserClient;

    @Test
    public void testSelectById() {

        RestResult selectById = this.restUserClient.selectById(11);
        System.out.println(selectById);
    }
}
