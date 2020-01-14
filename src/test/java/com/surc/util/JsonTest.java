package com.surc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.surc.entity.Goods;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.*;

@RunWith(JUnitPlatform.class)
public class JsonTest {
    private String json;
    private HashMap<Long, Goods> hashMap;

    @Test
    public void toJson() throws JsonProcessingException {
        hashMap = new HashMap<Long, Goods>();
        hashMap.put((long) 1,new Goods(1,"2",3,4,5));
        hashMap.put((long) 2,new Goods(1,"2",3,4,5));
        System.out.println(hashMap);

        List<Goods> list = new LinkedList<>();
        list.add(new Goods(1,"2",3,4,5));
        list.add(new Goods(2,"2",3,4,5));

        List<int[]> list2 = new LinkedList<>();
        list2.add(new int[]{1, 2});
        list2.add(new int[]{3,4});

        System.out.println(JsonUtil.toJson(list2));
        json = JsonUtil.toJson(list);
    }

    @Test
    public void fromJson() throws JsonProcessingException {
        toJson();

        System.out.println();
        System.out.println(JsonUtil.fromJson(json,List.class).get(1));
    }
}
