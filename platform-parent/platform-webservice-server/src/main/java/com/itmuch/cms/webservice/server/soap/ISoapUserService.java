package com.itmuch.cms.webservice.server.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.itmuch.cms.webservice.common.WSResult;
import com.itmuch.cms.webservice.constants.WSConstants;

/**
 * JAX-WS2.0的WebService接口定义类.
 * 使用JAX-WS2.0 annotation设置WSDL中的定义.
 * 使用WSResult及其子类类包裹返回结果.
 * 使用DTO传输对象隔绝系统内部领域对象的修改对外系统的影响.
 * @author zhouli
 */
// name 指明wsdl中<wsdl:portType>元素的名称
@WebService(name = "UserService", targetNamespace = WSConstants.NS)
public interface ISoapUserService {
    /**
     * 通过id 查询用户信息
     */
    SoapResult selectById(@WebParam(name = "id") Integer id);

    /**
     * 修改用户 
     * 这边改名叫userParam, 是因为selectById这个方法里面的UserResult里面也有个叫user的,所以要避免重复
     * @param user
     * @return
     */
    WSResult updateById(@WebParam(name = "user") SoapUser user);

}
