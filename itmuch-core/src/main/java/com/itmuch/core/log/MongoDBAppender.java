package com.itmuch.core.log;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Map;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.UnsynchronizedAppenderBase;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;

public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private MongoDBConnectionSource connectionSource = null;

    @Override
    protected void append(ILoggingEvent e) {
        StringBuffer buffer = new StringBuffer();
        // 如果存在异常
        IThrowableProxy throwableProxy = e.getThrowableProxy();
        if (throwableProxy != null) {
            StackTraceElementProxy[] proxies = throwableProxy.getStackTraceElementProxyArray();
            if ((proxies != null) && (proxies.length > 0)) {
                for (StackTraceElementProxy item : proxies) {
                    buffer.append(item).append("<br>");
                }
            }
        }

        String hostAddress = null;
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        }

        BasicDBObjectBuilder objectBuilder = BasicDBObjectBuilder.start().add("timestamp", new Date(e.getTimeStamp()))
                .add("message", e.getFormattedMessage()).add("level", e.getLevel().toString()).add("logger", e.getLoggerName())
                .add("thread", e.getThreadName()).add("ip", hostAddress).append("exception", buffer.toString());
        if (e.hasCallerData()) {
            StackTraceElement st = e.getCallerData()[0];
            String callerData = String.format("%s.%s:%d", st.getClassName(), st.getMethodName(), st.getLineNumber());
            objectBuilder.add("caller", callerData);
        }

        Map<String, String> mdc = e.getMDCPropertyMap();
        if ((mdc != null) && !mdc.isEmpty()) {
            objectBuilder.add("mdc", new BasicDBObject(mdc));
        }
        this.connectionSource.getDBCollection().insert(objectBuilder.get());

    }

    public void setConnectionSource(MongoDBConnectionSource connectionSource) {
        this.connectionSource = connectionSource;
    }

}
