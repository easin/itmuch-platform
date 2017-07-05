package com.itmuch.cms.webservice.server.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

import com.itmuch.cms.webservice.common.WSResult;

@XmlRootElement(name = "result")
public class RestResult extends WSResult {
    private RestUser user;

    public RestUser getUser() {
        return this.user;
    }

    public void setUser(RestUser user) {
        this.user = user;
    }

    public RestResult() {
    }

    public RestResult(RestUser user) {
        this.user = user;
    }

}
