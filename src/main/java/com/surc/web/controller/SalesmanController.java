package com.surc.web.controller;

import com.surc.entity.User;
import com.surc.enums.UserPosition;
import com.surc.service.BaseService;
import com.surc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/salesman")
public class SalesmanController extends BaseController<User,Long> {
    @Autowired
    private UserService userService;

    @Override
    public BaseService getService() {
        return userService;
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public int update(@RequestBody User user) {
        if (userService.getUserPosition(user.getId())!= UserPosition.SALESMAN) return 0;
        return super.update(user);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public int delete(Long id) {
        if (userService.getUserPosition(id)!=UserPosition.SALESMAN) return 0;
        return super.delete(id);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public User selectById(Long id) {
        if (userService.getUserPosition(id)!=UserPosition.SALESMAN) return null;
        return super.selectById(id);
    }

    @Override
    @ResponseBody
    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public List<User> selectAll() {
        return userService.selectUserByPosition(UserPosition.SALESMAN);
    }
}
