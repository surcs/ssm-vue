package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.ClientPurchaseOrderMapper;
import com.surc.dao.GoodsMapper;
import com.surc.dao.OrderMapper;
import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.Goods;
import com.surc.service.ClientPurchaseOrderService;
import com.surc.util.TypeGetter;
import com.surc.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ClientPurchaseOrderServiceImpl extends BaseServiceImpl<ClientPurchaseOrder,Long> implements ClientPurchaseOrderService {
    @Autowired
    private ClientPurchaseOrderMapper clientPurchaseOrderMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public BaseMapper getMapper() {
        return clientPurchaseOrderMapper;
    }


    @Override
    public float countAllSales(String start, String end) {
        return countSales(clientPurchaseOrderMapper.selectCountAllSales(start,end));
    }

    @Override
    public float countSalesByClientId(String id, String start, String end) {
        return countSales(clientPurchaseOrderMapper.selectCountSalesByClientId(id,start,end));

    }

    @Override
    public float countSalesBySalesmanId(long id, String start, String end) {
        return countSales(clientPurchaseOrderMapper.selectCountSalesBySalesmanId(id,start,end));
    }

    @Override
    public void sentOutByClientPurchaseOrderId(long id) {
        ClientPurchaseOrder clientPurchaseOrder = clientPurchaseOrderMapper.selectById(id);
        List<Goods> goodsList = clientPurchaseOrder.getGoodsList();
        for (int i = 0; i < goodsList.size(); i++) {

        }
    }

    @Override
    public void closeDispatchOrder(long id) {

    }

    @Override
    public float countSalesByGoodsId(long id, String start, String end) {
        float sum = 0;
        List<ClientPurchaseOrder> clientPurchaseOrders = clientPurchaseOrderMapper.selectCountAllSales(start,end);
        for (ClientPurchaseOrder clientPurchaseOrder:clientPurchaseOrders
             ) {
            List<Goods> goodsList = clientPurchaseOrder.getGoodsList();
            for (Goods goods : goodsList) {
                if (goods.getGoodsId() == id)  sum += goods.getPaid() * goods.getPaid();
            }
        }
        return sum;
    }

    public float countSales(List<ClientPurchaseOrder> clientPurchaseOrders){
        float sum = 0;
        for (ClientPurchaseOrder clientPurchaseOrder:clientPurchaseOrders
        ) {
            sum += clientPurchaseOrder.getSum();
        }
        return sum;
    }
}
