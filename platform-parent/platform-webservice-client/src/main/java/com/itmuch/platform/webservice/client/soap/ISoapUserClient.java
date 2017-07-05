package com.itmuch.platform.webservice.client.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.itmuch.platform.webservice.common.WSResult;
import com.itmuch.platform.webservice.constants.WSConstants;

/**
 * 客户端代码
 * 注意: 必须使用@Webservice注解, 同时指定的namespace必须与服务端对应, 否则会报错.
 * 如果条件允许, 建议直接找服务器端代码编写者提供一份服务器端的接口即可.
 * @author zhouli
 */
@WebService(targetNamespace = WSConstants.NS)
public interface ISoapUserClient {
    SoapResult selectById(@WebParam(name = "id") Integer id);

    WSResult updateById(@WebParam(name = "user") SoapUser user);

}
