package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.GoodsMapper;
import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.entity.PurchaseOrder;
import com.surc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class GoodsServiceImpl extends BaseServiceImpl<Goods,Long> implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public BaseMapper getMapper() {
        return goodsMapper;
    }

    @Override
    public boolean checkEnoughById(long id) {
        Goods goods = goodsMapper.selectById(id);
        return goods.getInventory() >= goods.getMinInventory();
    }

    @Override
    public boolean checkEnoughToSentById(long id,int number) {
        Goods goods = goodsMapper.selectById(id);
        return goods.getInventory() >= number;
    }

    @Override
    public void addInventory(PurchaseOrder purchaseOrder) {
        Goods goods = goodsMapper.selectById(purchaseOrder.getGoodsId());
        goods.setInventory(goods.getInventory() + purchaseOrder.getNumber()); //修改库存
        goodsMapper.update(goods);
    }

    @Override
    public void closeDispatchOrder(DispatchOrder dispatchOrder) {
        Goods goods = goodsMapper.selectById(dispatchOrder.getGoodsId());
        goods.setInventory(goods.getInventory() - dispatchOrder.getNumber());
        goodsMapper.update(goods);
    }
}
