package com.surc.web.controller;

import com.surc.entity.User;
import com.surc.enums.UserPosition;
import com.surc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public int loginCheck(@RequestBody Map map, HttpSession httpSession){
        User user = userService.selectById(Long.parseLong((String) map.get("username")));
        if (user!=null&&user.getPassword().equals(map.get("password"))) {
            httpSession.setAttribute("user",user);
            return 1;
        }
        return 0;
    }

    @ResponseBody
    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    public boolean CheckLogin(HttpSession session){
        return session.getAttribute("user") != null;
    }

    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/getPosition",method = RequestMethod.POST)
    public UserPosition getPosition(HttpSession session){
        User user = (User) session.getAttribute("user");
        return user.getPosition();
    }
}
