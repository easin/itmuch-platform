package com.itmuch.cms.webservice.server.soap;

import javax.jws.WebService;

import org.apache.cxf.feature.Features;

import com.itmuch.cms.webservice.common.WSResult;
import com.itmuch.cms.webservice.constants.WSConstants;

/**
 * WebService服务端实现类.
 * 
 * 为演示方便，直接调用了Dao层.客户端实现见功能测试用例.
 * 
 * @author calvin
 */
// serviceName指明WSDL中<wsdl:service>与<wsdl:binding>元素的名称, endpointInterface属性指向Interface类全称.
@WebService(serviceName = "UserService", endpointInterface = "com.itmuch.cms.webservice.server.soap.ISoapUserService", targetNamespace = WSConstants.NS)
// 增加inbound/outbound SOAP内容的日志
@Features(features = "org.apache.cxf.feature.LoggingFeature")
public class SoapUserServiceImpl implements ISoapUserService {

    @Override
    public SoapResult selectById(Integer id) {
        SoapUser user = new SoapUser();
        user.setId(id);
        user.setAge(18);
        user.setUsername("test");
        user.setSex((byte) 1);

        SoapResult result = new SoapResult(user);
        return result;
    }

    @Override
    public WSResult updateById(SoapUser user) {

        // 修改的逻辑...略
        return new WSResult();
    }

}
