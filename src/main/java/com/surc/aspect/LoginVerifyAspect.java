package com.surc.aspect;

import com.surc.exception.LoginAuthException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 只有已登录用户 才能进入
 */
@Aspect
@Component
public class LoginVerifyAspect {
    //所有Controller
    @Pointcut("execution(public * com.surc.web.controller.*Controller.*(..))")
    public void allController() {}

    //登录需要的Controller
    @Pointcut("execution(public * com.surc.web.controller.UserController.*Check*(..))")
    public void userLogin(){}

    //模拟采购订单添加 需要的Controller
    @Pointcut("execution(public * com.surc.web.controller.ClientPurchaseOrderController.add(..))")
    public void insertClientPurchaseOrder(){}

    //拦截所有的controller 除了登录需要的 和 模拟采购订单添加的
    @Before("allController() && !userLogin() && !insertClientPurchaseOrder()")
    public void checkLogin() throws LoginAuthException {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session=attr.getRequest().getSession(true);
        if(session.getAttribute("user") == null) throw new LoginAuthException("用户未登录");
    }
}
