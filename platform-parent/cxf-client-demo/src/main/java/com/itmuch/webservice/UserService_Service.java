package com.itmuch.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.2
 * 2015-09-02T16:07:54.436+08:00
 * Generated source version: 3.1.2
 * 
 */
@WebServiceClient(name = "UserService", 
                  wsdlLocation = "http://localhost:7777/platform/cxf/soap/user?wsdl",
                  targetNamespace = "http://webservice.itmuch.com") 
public class UserService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://webservice.itmuch.com", "UserService");
    public final static QName UserSoapServiceImplPort = new QName("http://webservice.itmuch.com", "UserSoapServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:7777/platform/cxf/soap/user?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(UserService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:7777/platform/cxf/soap/user?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public UserService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public UserService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public UserService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public UserService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public UserService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public UserService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserSoapServiceImplPort")
    public UserService getUserSoapServiceImplPort() {
        return super.getPort(UserSoapServiceImplPort, UserService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns UserService
     */
    @WebEndpoint(name = "UserSoapServiceImplPort")
    public UserService getUserSoapServiceImplPort(WebServiceFeature... features) {
        return super.getPort(UserSoapServiceImplPort, UserService.class, features);
    }

}
