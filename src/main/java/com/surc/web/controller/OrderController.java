package com.surc.web.controller;

import com.surc.entity.Goods;
import com.surc.entity.Order;
import com.surc.entity.User;
import com.surc.enums.UserPosition;
import com.surc.service.BaseService;
import com.surc.service.ClientService;
import com.surc.service.OrderService;
import com.surc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController extends BaseController<Order,Long> {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private UserService userService;

    @Override
    public BaseService getService() {
        return orderService;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public int update(@RequestBody Order order) {
        int message = verify(order);
        if ( message != 1) return message;
        if (!orderService.checkStateWhetherCanModifyById(order.getId())) return 0;
        super.update(order);
        return 1;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int add(@RequestBody Order order) {
        int message = verify(order);
        if ( message != 1) return message;
        super.add(order);
        return 1;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public int delete(Long id) {
        if (orderService.checkStateWhetherCanModifyById(id)) return super.delete(id);
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/selectOrderListBySalesmanId",method = RequestMethod.POST)
    public List<Order> selectOrderListBySalesmanId(HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user.getPosition() != UserPosition.SALESMAN) return null;
        else return orderService.selectOrderListBySalesmanId(user.getId());
    }

    public int verify(Order order){
        Iterator iterator = order.getGoodsList().iterator();
        while(iterator.hasNext()){
            Goods goods = (Goods) iterator.next();
            if (goods.getTotal() == 0) iterator.remove();
        }
        if (order.getGoodsList().isEmpty()) return -3;
        if (clientService.selectById(order.getClientEmail())==null) return -1;
        if (userService.selectById(order.getSalesmanId())==null || userService.selectById(order.getSalesmanId()).getPosition()!=UserPosition.SALESMAN) return -2;
        return 1;
    }
}
