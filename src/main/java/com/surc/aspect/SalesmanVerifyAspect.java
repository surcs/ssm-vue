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
 * 只有销售人员 才能使用接口
 */
@Aspect
@Component
public class SalesmanVerifyAspect {
    @Pointcut("execution(public * com.surc.web.controller.OrderController.selectOrderListBySalesmanId(..))")
    public void orderController(){};

    @Pointcut("execution(public * com.surc.web.controller.ClientPurchaseOrderController.countSalesBySalesmanId(..))")
    public void clientPurchaseOrderController(){};

    @Before("orderController() || clientPurchaseOrderController()")
    public void onlySalesman() throws PositionAuthException {
        User user = (User) SessionGetter.getSession().getAttribute("user");
        if (user.getPosition() != UserPosition.SALESMAN) throw new PositionAuthException("操作用户必须为 销售人员");
    };
}
