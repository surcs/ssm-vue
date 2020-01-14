package com.surc.web.controller;


import com.surc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan("com.surc.util")
public abstract class BaseController <T,ID>{
    @Autowired
    ApplicationContext applicationContext;
    
    public abstract BaseService getService();

    @ResponseBody
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int add(@RequestBody T t){
        getService().insert(t);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public int update(@RequestBody T t){
        getService().update(t);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public int delete(ID id){
        getService().delete(id);
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "/select",method = RequestMethod.POST)
    public T selectById(ID id){
        return (T) getService().selectById((id));
    }

    @ResponseBody
    @RequestMapping(value = "/selectAll",method = RequestMethod.POST)
    public List<T> selectAll(){
        return (List<T>) getService().selectAll();
    }
}
