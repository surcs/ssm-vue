package com.surc.web.controller;

import com.surc.entity.ClientPurchaseOrder;
import com.surc.entity.User;
import com.surc.enums.OrderState;
import com.surc.enums.UserPosition;
import com.surc.service.BaseService;
import com.surc.service.ClientPurchaseOrderService;
import com.surc.service.DispatchOrderService;
import com.surc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 采购单即 合同中用户已付款的部分 可以用来查销售业绩
 */
@Controller
@RequestMapping("/clientPurchaseOrder")
public class ClientPurchaseOrderController extends BaseController<ClientPurchaseOrder,Long>{
    @Autowired
    private ClientPurchaseOrderService clientPurchaseOrderService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DispatchOrderService dispatchOrderService;

    @Override
    public BaseService getService() {
        return this.clientPurchaseOrderService;
    }

    @ResponseBody
    @RequestMapping(value = "/countAllSales",method = RequestMethod.POST)
    public float countAllSales(String start,String end){
        return clientPurchaseOrderService.countAllSales(start,end);
    }

    @ResponseBody
    @RequestMapping(value = "/countSalesByClientId",method = RequestMethod.POST)
    public float countSalesByClientId(String id,String start,String end){
        return clientPurchaseOrderService.countSalesByClientId(id,start,end);
    }

    @ResponseBody
    @RequestMapping(value = "/countSalesByGoodsId",method = RequestMethod.POST)
    public float countSalesByGoodsId(long id,String start,String end){
        return clientPurchaseOrderService.countSalesByGoodsId(id,start,end);
    }

    @ResponseBody
    @RequestMapping(value = "/countSalesBySalesmanId",method = RequestMethod.POST)
    public float countSalesBySalesmanId(String start, String end,HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user.getPosition() != UserPosition.SALESMAN) return -1;
        return clientPurchaseOrderService.countSalesBySalesmanId(user.getId(),start,end);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int add(@RequestBody ClientPurchaseOrder clientPurchaseOrder) {
        int message = verify(clientPurchaseOrder);
        if (message != 1) return message;
        super.add(clientPurchaseOrder);
        orderService.addClientPurchaseOrder(clientPurchaseOrder);
        dispatchOrderService.createByClientPurchaseOrder(clientPurchaseOrder);
        return 1;
    }

    public int verify(ClientPurchaseOrder clientPurchaseOrder){
        if (orderService.selectById(clientPurchaseOrder.getOrderId()) == null) return -1;
        if (orderService.selectById(clientPurchaseOrder.getOrderId()).getState() == OrderState.CLOSE) return -2;
        return 1;
    }
}
