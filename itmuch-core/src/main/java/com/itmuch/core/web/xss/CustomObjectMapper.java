package com.itmuch.core.web.xss;

import java.io.IOException;

import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * 用于responsebody ajax序列化时, escape以防止xss攻击
 * 参考文档:http://www.yihaomen.com/article/java/469.htm
 * @author qianbao
 *
 */
public class CustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -3448961813323784217L;

    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule("HTML XSS Serializer", new Version(1, 0, 0, "FINAL", "com.yihaomen", "ep-jsonmodule"));
        module.addSerializer(new JsonHtmlXssSerializer(String.class));
        this.registerModule(module);
    }

    class JsonHtmlXssSerializer extends JsonSerializer<String> {

        public JsonHtmlXssSerializer(Class<String> string) {
            super();
        }

        @Override
        public Class<String> handledType() {
            return String.class;
        }

        @Override
        public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException,
                JsonProcessingException {
            // System.out.println("value::::::" + value);
            if (value != null) {
                String encodedValue = HtmlUtils.htmlEscape(value.toString());
                jsonGenerator.writeString(encodedValue);
            }
        }
    }
}