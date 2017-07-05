package com.itmuch.core.log;

import java.net.UnknownHostException;

import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;

public class MongoDBConnectionSource {

    private volatile DBCollection dbCollection = null;

    private String uri = null;

    private String db = null;

    private String collection = null;

    protected DBCollection getDBCollection() {
        DBCollection dbCollectionHelper = this.dbCollection;
        if (dbCollectionHelper == null) {
            synchronized (this) {
                dbCollectionHelper = this.dbCollection;
                if (dbCollectionHelper == null) {
                    try {
                        final Mongo mongo = new MongoClient(new MongoClientURI(this.uri));
                        this.dbCollection = mongo.getDB(this.db).getCollection(this.collection);
                        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                            @Override
                            public void run() {
                                mongo.close();
                            }
                        }, "mongo shutdown"));
                    } catch (MongoException mongoException) {
                        mongoException.printStackTrace();
                    } catch (UnknownHostException unknownHostException) {
                        unknownHostException.printStackTrace();
                    }
                }
            }
        }
        return this.dbCollection;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

}
