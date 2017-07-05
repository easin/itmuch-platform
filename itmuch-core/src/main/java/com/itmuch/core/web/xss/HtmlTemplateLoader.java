package com.itmuch.core.web.xss;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;

import freemarker.cache.TemplateLoader;

/**
 * 用于freemarker 为全局模板添加escape, 防止XSS攻击
 * @author zhouli
 */
public class HtmlTemplateLoader implements TemplateLoader {

    private static final String HTML_ESCAPE_PREFIX = "<#escape x as x?html>";
    private static final String HTML_ESCAPE_SUFFIX = "</#escape>";

    private final TemplateLoader delegate;

    public HtmlTemplateLoader(TemplateLoader delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object findTemplateSource(String name) throws IOException {
        return this.delegate.findTemplateSource(name);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return this.delegate.getLastModified(templateSource);
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        Reader reader = this.delegate.getReader(templateSource, encoding);
        String templateText = IOUtils.toString(reader);
        return new StringReader(HTML_ESCAPE_PREFIX + templateText + HTML_ESCAPE_SUFFIX);
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
        this.delegate.closeTemplateSource(templateSource);
    }

}