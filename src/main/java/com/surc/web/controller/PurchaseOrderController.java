package com.surc.web.controller;

import com.surc.entity.DispatchOrder;
import com.surc.entity.PurchaseOrder;
import com.surc.entity.User;
import com.surc.enums.OrderState;
import com.surc.service.BaseService;
import com.surc.service.GoodsService;
import com.surc.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("purchaseOrder")
public class PurchaseOrderController extends BaseController<PurchaseOrder,Long> {
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private GoodsService goodsService;

    @Override
    public BaseService getService() {
        return this.purchaseOrderService;
    }

    @ResponseBody
    @RequestMapping(value = "/checked",method = RequestMethod.POST)
    public void checked(Long purchaseOrderId,Long warehouseKeeperId){
        purchaseOrderService.checkedPurchaseOrder(purchaseOrderId,warehouseKeeperId);
    }

    @ResponseBody
    @RequestMapping(value = "/selectToConfirm",method = RequestMethod.POST)
    public List<DispatchOrder> selectToConfirm(){
        return purchaseOrderService.selectToConfirm();
    }

    @ResponseBody
    @RequestMapping(value = "/selectToClose",method = RequestMethod.POST)
    public List<DispatchOrder> selectToClose(){
        return purchaseOrderService.selectToClose();
    }


    @ResponseBody
    @RequestMapping(value = "/updateToConfirm",method = RequestMethod.POST)
    public int updateToConfirm(@RequestBody PurchaseOrder purchaseOrder, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null) return  0;
        purchaseOrder.setState(OrderState.RUN);
        purchaseOrder.setWarehouseKeeperIdConfirm(user.getId());
        super.update(purchaseOrder);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/updateToClose",method = RequestMethod.POST)
    public int updateToClose(@RequestBody PurchaseOrder purchaseOrder, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user==null) return  0;
        purchaseOrder.setState(OrderState.CLOSE);
        purchaseOrder.setWarehouseKeeperIdClose(user.getId());
        goodsService.addInventory(purchaseOrder);
        super.update(purchaseOrder);
        return 1;
    }
}
