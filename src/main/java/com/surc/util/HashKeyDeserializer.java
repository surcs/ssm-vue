package com.surc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surc.entity.Goods;

import java.io.IOException;

public class HashKeyDeserializer extends KeyDeserializer
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserializeKey( final String key,
                                  final DeserializationContext ctxt )
            throws IOException, JsonProcessingException
    {
//        String goodsJson ="\""+key+"\"";
        return objectMapper.readValue(key, Goods.class);
    }
}