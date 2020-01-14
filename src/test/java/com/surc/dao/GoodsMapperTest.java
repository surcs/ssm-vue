package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GoodsMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 测试用例：添加商品
     * @return
     */
    @Test
    @Override
    public void insert(){
        Goods goods = new Goods();

        goods.setGoodsName("小骚鸡");
        goods.setInventory(4);
        goods.setMinInventory(2);
        goods.setPrice(1);
        goodsMapper.insert(goods);

        goods.setGoodsName("小蟑螂");
        goods.setInventory(1);
        goods.setMinInventory(1);
        goods.setPrice(2);
        goodsMapper.insert(goods);

        goods.setGoodsName("骚猪");
        goods.setInventory(4);
        goods.setMinInventory(2);
        goods.setPrice(3);
        goodsMapper.insert(goods);
    }

    @Test
    @Override
    public void delete(){ goodsMapper.delete((long) 3);
    }

    @Test
    @Override
    public void update(){
        goodsMapper.update(new Goods(3,"小蟑螂",3,2,1));
    }

    @Test
    @Override
    public void select(){
        System.out.println(goodsMapper.selectById((long) 3));
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(goodsMapper.selectAll());
    }
}
