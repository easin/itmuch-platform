package com.itmuch.platform.webservice.client.soap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext.xml")
public class SoapUserClientTest {
    @Resource
    private ISoapUserClient soapUserClient;

    @Test
    public void testSelectById() {

        SoapResult selectById = this.soapUserClient.selectById(11);
        System.out.println(selectById);
    }
}
