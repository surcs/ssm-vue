package com.surc.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/to")
public class PageController {
    /**
     * 视图获取(通用)
     * @param page
     * @return 视图
     */
    @RequestMapping("{page}")
    public String toPage(@PathVariable String page){
        return page;
    }
}
