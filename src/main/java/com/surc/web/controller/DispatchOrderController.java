package com.surc.web.controller;

import com.surc.entity.DispatchOrder;
import com.surc.entity.Goods;
import com.surc.entity.User;
import com.surc.enums.OrderState;
import com.surc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/dispatchOrder")
public class DispatchOrderController extends BaseController<DispatchOrder,Long> {
    @Autowired
    private DispatchOrderService dispatchOrderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientPurchaseOrderService clientPurchaseOrderService;

    @Override
    public BaseService getService() {
        return dispatchOrderService;
    }

    @Override
    @ResponseBody
    @RequestMapping("/insert")
    public int add(@RequestBody DispatchOrder dispatchOrder) {
        Goods goods = goodsService.selectById(dispatchOrder.getGoodsId());
        if (goodsService.checkEnoughToSentById(dispatchOrder.getGoodsId(), dispatchOrder.getNumber())) {
            super.add(dispatchOrder);
            if (!goodsService.checkEnoughById(dispatchOrder.getGoodsId()))
                purchaseOrderService.insertPurchaseOrderByIdNumber(goods.getGoodsId(),goods.getMinInventory() * 2);
            return 1;
        }
        purchaseOrderService.insertPurchaseOrderByIdNumber(goods.getGoodsId(),dispatchOrder.getNumber() * 2);
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/selectSalesKeeperUncheck",method = RequestMethod.POST)
    public List<DispatchOrder> selectSalesKeeperUncheck(){
        return dispatchOrderService.selectSalesKeeperUncheck();
    }

    @ResponseBody
    @RequestMapping(value = "/selectWarehouseKeeperUncheck",method = RequestMethod.POST)
    public List<DispatchOrder> selectWarehouseKeeperUncheck(){
        return dispatchOrderService.selectWarehouseKeeperUncheck();
    }

    @ResponseBody
    @RequestMapping(value = "/updateDispatchOrderSalesKeeper",method = RequestMethod.POST)
    public int updateDispatchOrderSalesKeeper(@RequestBody DispatchOrder dispatchOrder, HttpSession session) {
        if (!goodsService.checkEnoughToSentById(dispatchOrder.getGoodsId(), dispatchOrder.getNumber())){
            purchaseOrderService.createById(dispatchOrder.getGoodsId());
            return 0;
        }
        User user = (User) session.getAttribute("user");
        if (user==null) return  0;
        dispatchOrder.setState(OrderState.W_UNCHECK);
        dispatchOrder.setSalesKeeperId(user.getId());
        super.update(dispatchOrder);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/updateDispatchOrderWarehouseKeeper",method = RequestMethod.POST)
    public int updateDispatchOrderWarehouseKeeper(@RequestBody DispatchOrder dispatchOrder, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user==null) return  0;
        dispatchOrder.setState(OrderState.CLOSE);
        dispatchOrder.setWarehouseKeeperId(user.getId());
        super.update(dispatchOrder);
        orderService.closeDispatchOrder(dispatchOrder);
        goodsService.closeDispatchOrder(dispatchOrder);
        if (!goodsService.checkEnoughById(dispatchOrder.getGoodsId())) purchaseOrderService.createById(dispatchOrder.getGoodsId());
        return 1;
    }
}
