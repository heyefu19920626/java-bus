package com.tang.enums.handle;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.tang.enums.BaseValueEnum;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * fastjson的自定义反序列化
 *
 * @author he
 * @since 2020-12.10-23:31
 */
public class EnumCovert implements ObjectSerializer, ObjectDeserializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        if (object instanceof BaseValueEnum) {
            BaseValueEnum baseValueEnum = (BaseValueEnum) object;
            serializer.write(baseValueEnum.getValue());
        }
    }

    @Override
    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        return null;
    }

    @Override
    public int getFastMatchToken() {
        return 0;
    }
}