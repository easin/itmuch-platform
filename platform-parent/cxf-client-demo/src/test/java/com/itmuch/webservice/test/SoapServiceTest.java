package com.itmuch.webservice.test;

import org.junit.Test;

import com.itmuch.webservice.UserResult;
import com.itmuch.webservice.UserService;
import com.itmuch.webservice.UserService_Service;
import com.itmuch.webservice.WSResult;
import com.itmuch.webservice.WsEntitySoap;

public class SoapServiceTest {

    @Test
    public void testSelectById() {
        UserService_Service ss = new UserService_Service();

        UserService userService = ss.getUserSoapServiceImplPort();

        UserResult result = userService.selectById(2);
        System.out.println(result);
    }

    @Test
    public void testUpdateById() {
        WsEntitySoap user = new WsEntitySoap();
        user.setId(2);
        user.setUsername("webservice_test");
        user.setAge(88);

        UserService_Service ss = new UserService_Service();

        UserService userService = ss.getUserSoapServiceImplPort();
        WSResult wsResult = userService.updateById(user);

        System.out.println(wsResult);
    }
}
