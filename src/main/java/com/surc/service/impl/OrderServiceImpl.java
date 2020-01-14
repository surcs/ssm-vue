package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.OrderMapper;
import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.entity.Order;
import com.surc.enums.OrderState;
import com.surc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class OrderServiceImpl extends BaseServiceImpl<Order,Long> implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public BaseMapper getMapper() {
        return orderMapper;
    }

    @Override
    public void addClientPurchaseOrder(ClientPurchaseOrder clientPurchaseOrder) {
        Order order = orderMapper.selectById(clientPurchaseOrder.getOrderId());
        for (Goods clientPurchaseGoods:clientPurchaseOrder.getGoodsList()
             ) {
            for (Goods orderGoods:order.getGoodsList()
                 ) {
                if (clientPurchaseGoods.getGoodsId() == orderGoods.getGoodsId())
                    orderGoods.setPaid(orderGoods.getPaid()+clientPurchaseGoods.getPaid());
            }
        }
        if (order.getState() == OrderState.UNPAID) order.setState(OrderState.RUN);
        orderMapper.update(order);
    }

    @Override
    public boolean checkStateWhetherCanModifyById(long id) {
        OrderState orderState = orderMapper.selectById(id).getState();
        if (orderState== OrderState.RUN||orderState==OrderState.CLOSE) return false;
        return true;
    }

    @Override
    public List<Order> selectOrderListBySalesmanId(long id) {
        return orderMapper.selectOrderListBySalesmanId(id);
    }

    @Override
    public boolean checkCompleteById(long id) {
        Order order = orderMapper.selectById(id);
        for (Goods goods:order.getGoodsList()
             ) {
            if (goods.getTotal()!=goods.getSend()) return false;
        }
        return true;
    }

    @Override
    public void closeDispatchOrder(DispatchOrder dispatchOrder) {
        Order order = orderMapper.selectById(dispatchOrder.getOrderId());
        for (Goods goods:order.getGoodsList()
        ) {
            if (goods.getGoodsId() == dispatchOrder.getGoodsId()){
                goods.setSend(goods.getSend() + dispatchOrder.getNumber());
                if (checkCompleteById(order.getId()))
                    order.setState(OrderState.CLOSE);
                orderMapper.update(order);
                return;
            }
        }
    }
}
