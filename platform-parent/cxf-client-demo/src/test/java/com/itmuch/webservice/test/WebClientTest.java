package com.itmuch.webservice.test;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;

import com.itmuch.webservice.test.entity.JaxRsTestResult;
import com.itmuch.webservice.test.entity.TestUser;

/**
 * 使用webclient 测试jax-rs
 * @author zhouli
 */
public class WebClientTest {

    @Test
    public void testGet() {
        WebClient client = WebClient.create("http://localhost:7777/platform/");
        client.path("cxf/jaxrs/user/1").accept("application/xml;charset=UTF-8");
        JaxRsTestResult rs = client.get(JaxRsTestResult.class);
        System.out.println(rs);
    }

    @Test
    public void testPut() {
        WebClient client = WebClient.create("http://localhost:7777/platform/");
        client.path("cxf/jaxrs/user/1").accept("application/xml;charset=UTF-8");

        TestUser user = new TestUser();
        user.setId(1000);
        user.setAge(88);
        user.setUsername("haha");
        user.setSex((byte) 1);

        JaxRsTestResult result = client.put(user, JaxRsTestResult.class);
        System.out.println(result);

    }
}
