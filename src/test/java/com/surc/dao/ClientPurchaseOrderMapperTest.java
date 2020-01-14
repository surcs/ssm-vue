package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.Goods;
import com.surc.enums.OrderState;
import com.surc.util.DateFormatUtil;
import com.surc.util.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.*;

public class ClientPurchaseOrderMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private ClientPurchaseOrderMapper clientPurchaseOrderMapper;
    private ClientPurchaseOrder clientPurchaseOrder;
    private List<Goods> goodsList;
    @Test
//    @BeforeEach
    public void init() throws ParseException {
        clientPurchaseOrder = new ClientPurchaseOrder();
        clientPurchaseOrder.setOrderId(11);
        clientPurchaseOrder.setAddress("ad");
        clientPurchaseOrder.setDate(DateFormatUtil.parse("2019-12-11"));
        clientPurchaseOrder.setState(OrderState.UNPAID);
        clientPurchaseOrder.setSum(1);

        goodsList = new LinkedList<>();
        goodsList.add(new Goods(1,"1",1,1,1));
        goodsList.add(new Goods(7,"2",2,2,2));

        clientPurchaseOrder.setGoodsList(goodsList);

    }

    @Test
    @Override
    public void insert() {
        clientPurchaseOrderMapper.insert(clientPurchaseOrder);
    }

    @Test
    @Override
    public void delete() {
        clientPurchaseOrderMapper.delete((long) 1);
    }

    @Test
    @Override
    public void update() {
        ClientPurchaseOrder clientPurchaseOrder = clientPurchaseOrderMapper.selectById((long) 2);
        clientPurchaseOrder.setSum(3);
        clientPurchaseOrderMapper.update(clientPurchaseOrder);
    }

    @Test
    @Override
    public void select() {
        ClientPurchaseOrder purchaseOrder1 = clientPurchaseOrderMapper.selectById((long) 1);
        System.out.println(purchaseOrder1.getGoodsList().get(1).getGoodsId());
//        JsonUtil.fromJson(String.valueOf(purchaseOrder1.getGoodsList().get(1)), LinkedHashMap.class);
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(clientPurchaseOrderMapper.selectAll());
    }

    @Test
    public void selectCountAllSales(){
        System.out.println(clientPurchaseOrderMapper.selectCountAllSales("2019-12-12","2019-12-30"));
    }

    @Test
    public void selectCountSalesByClientId(){
        System.out.println(clientPurchaseOrderMapper.selectCountSalesByClientId("e","2019-12-12","2019-12-18"));
    }

    @Test
    public void selectCountSalesBySalesId(){
        System.out.println(clientPurchaseOrderMapper.selectCountSalesBySalesmanId(1,"2019-12-12","2019-12-18"));
    }
}
