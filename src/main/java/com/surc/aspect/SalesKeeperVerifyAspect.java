package com.surc.aspect;

import com.surc.entity.User;
import com.surc.enums.UserPosition;
import com.surc.exception.PositionAuthException;
import com.surc.util.SessionGetter;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 只有销售管理员 才能使用接口
 */
@Aspect
@Component
public class SalesKeeperVerifyAspect{
    @Pointcut("execution(public * com.surc.web.controller.SalesmanController.*(..))")
    public void salesmanController(){};
    @Pointcut("execution(public * com.surc.web.controller.OrderController.*(..)) && " +
            "!execution(public * com.surc.web.controller.OrderController.selectOrderListBySalesmanId(..))")
    public void orderController(){};
    @Pointcut("execution(public * com.surc.web.controller.GoodsController.*(..))")
    public void goodsController(){};
    @Pointcut("execution(public * com.surc.web.controller.DispatchOrderController.*(..)) && " +
            "!execution(public * com.surc.web.controller.DispatchOrderController.*WarehouseKeeper*(..))")
    public void dispatchOrderController(){};
    @Pointcut("execution(public * com.surc.web.controller.ClientPurchaseOrderController.*(..)) &&" +
            " !execution(public * com.surc.web.controller.ClientPurchaseOrderController.countSalesBySalesmanId(..)) &&" +
            "!execution(public * com.surc.web.controller.ClientPurchaseOrderController.add(..))")
    public void clientPurchaseOrderController(){};
    @Pointcut("execution(public * com.surc.web.controller.ClientController.*(..))")
    public void clientController(){};
    @Pointcut("execution(public * com.surc.web.controller.BaseController.*(..))")
    public void baseController(){};


    @Before("salesmanController() || orderController() || goodsController() || dispatchOrderController() ||" +
            "clientController() || clientPurchaseOrderController()")
    public void onlySalesKeeper() throws PositionAuthException {
        User user = (User) SessionGetter.getSession().getAttribute("user");
        if (user.getPosition() != UserPosition.SALES_KEEPER) throw new PositionAuthException("操作用户必须为 销售管理员");
    };
}
