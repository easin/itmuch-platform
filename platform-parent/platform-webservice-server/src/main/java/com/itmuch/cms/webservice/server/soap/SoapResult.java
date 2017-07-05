package com.itmuch.cms.webservice.server.soap;

import javax.xml.bind.annotation.XmlType;

import com.itmuch.cms.webservice.common.WSResult;
import com.itmuch.cms.webservice.constants.WSConstants;

@XmlType(name = "UserResult", namespace = WSConstants.NS)
public class SoapResult extends WSResult {
    private SoapUser user;

    public SoapUser getUser() {
        return this.user;
    }

    public void setUser(SoapUser user) {
        this.user = user;
    }

    public SoapResult() {
    }

    public SoapResult(SoapUser user) {
        this.user = user;
    }

}
