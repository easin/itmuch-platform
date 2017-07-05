package com.itmuch.platform.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SolrUtil {

    @Value(value = "${solr.url}")
    private String url;

    private HttpSolrClient client;

    public SolrUtil() {
        this.getClient();
    }

    public synchronized HttpSolrClient getClient() {
        if (this.client == null) {
            if (this.url != null) {
                this.client = new HttpSolrClient(this.url);
            }
        }
        return this.client;
    }

}
