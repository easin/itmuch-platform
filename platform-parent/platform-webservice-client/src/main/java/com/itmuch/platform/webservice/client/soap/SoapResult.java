package com.itmuch.platform.webservice.client.soap;

import com.itmuch.platform.webservice.common.WSResult;

public class SoapResult extends WSResult {
    private SoapUser user;

    public SoapUser getUser() {
        return this.user;
    }

    public void setUser(SoapUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "SoapResult [user=" + this.user + "]";
    }

}
