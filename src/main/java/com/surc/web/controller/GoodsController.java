package com.surc.web.controller;

import com.surc.entity.Goods;
import com.surc.service.BaseService;
import com.surc.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController<Goods,Long> {
    @Autowired
    private GoodsService goodsService;
    @Override
    public BaseService getService() {
        return this.goodsService;
    }
}
