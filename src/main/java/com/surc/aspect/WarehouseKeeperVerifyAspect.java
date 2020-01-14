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
 * 只有仓库管理员 才能使用接口
 */
@Aspect
@Component
public class WarehouseKeeperVerifyAspect {
    @Pointcut("execution(public * com.surc.web.controller.PurchaseOrderController.*(..))")
    public void purchaseOrderController(){};

    @Pointcut("execution(public * com.surc.web.controller.DispatchOrderController.*WarehouseKeeper*(..))")
    public void dispatchOrderController(){};

    @Pointcut("execution(public * com.surc.web.controller.BaseController.*(..))")
    public void baseController(){};

    @Before("purchaseOrderController() || dispatchOrderController()")
    public void onlyWarehouseKeeper() throws PositionAuthException {
        User user = (User) SessionGetter.getSession().getAttribute("user");
        if (user.getPosition() != UserPosition.WAREHOUSE_KEEPER) throw new PositionAuthException("操作用户必须为 仓库管理员");
    }
}
