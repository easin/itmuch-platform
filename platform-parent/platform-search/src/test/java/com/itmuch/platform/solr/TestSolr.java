package com.itmuch.platform.solr;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:test-applicationContext.xml")
public class TestSolr {
    @Resource
    private SolrUtil solrUtil;

    @Test
    public void testAdd() throws SolrServerException, IOException {
        HttpSolrClient client = this.solrUtil.getClient();

        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", "11177");
        client.add(doc);
        client.commit();
    }

}
