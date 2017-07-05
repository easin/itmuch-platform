package com.itmuch.platform.webservice.client.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

import com.itmuch.platform.webservice.common.WSResult;

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
