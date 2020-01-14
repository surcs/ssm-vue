package com.surc.util;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.surc.entity.Goods;
import com.surc.util.HashKeyDeserializer;

public class HashJacksonModule extends SimpleModule
{
    public HashJacksonModule()
    {
        addKeyDeserializer(
                Goods.class,
                new HashKeyDeserializer() );
    }
}
