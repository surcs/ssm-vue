package com.surc.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.Goods;
import com.surc.entity.Order;
import com.surc.enums.OrderState;
import com.surc.util.DateFormatUtil;
import com.surc.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

public class OrderMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private OrderMapper orderMapper;
    private Order order;
    private List<Goods> goodsList;
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @BeforeEach
    public void init() throws ParseException {
        order = new Order();
        order.setState(OrderState.UNPAID);
        order.setClientEmail("1@1.com");
        order.setSalesmanId(15);
        order.setDate(DateFormatUtil.parse("2019-12-3"));
        order.setAddress("a");
        order.setSum(10);

        goodsList = new LinkedList<>();
        goodsList.add(new Goods(1,"1",1,1,1));
        goodsList.add(new Goods(2,"2",2,2,2));

        order.setGoodsList(goodsList);

        String json = JsonUtil.toJson(order);
        System.out.println(JsonUtil.toJson(goodsList));
        try {
            System.out.println(objectMapper.readValue(JsonUtil.toJson(goodsList),new TypeReference<List<Goods>>() {}).get(0).getGoodsId());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(JsonUtil.fromJson(json,Order.class).getGoodsList().get(0).getGoodsId());
    }

    @Test
    @Override
    public void insert() {
        System.out.println(order);
        orderMapper.insert(order);
    }

    @Test
    @Override
    public void delete() {
        orderMapper.delete((long) 3);
    }

    @Test
    @Override
    public void update() {
        order.setId(7);
        orderMapper.update(order);
    }

    @Test
    @Override
    public void select() {
        Order order1 = orderMapper.selectById((long) 8);
        System.out.println(order1);
//        System.out.println(order1.getGoodsList().get(0).getGoodsId());
//        System.out.println("zzz");
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(orderMapper.selectAll());
    }

    @Test
    public void selectOrderListBySalesmanId() {
        List<Order> list = orderMapper.selectOrderListBySalesmanId(2);
        for (Order order:list
             ) {
            System.out.println(order.getSalesmanId());
        }
    }
}
