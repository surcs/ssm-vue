package com.surc.util;

import com.surc.entity.Client;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@RunWith(JUnitPlatform.class)
@SpringJUnitConfig(classes = {BeanConfig.class})
public class BeanConfigTest{
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void clientTest(){
        Client client =(Client) applicationContext.getBean("client");
        client.setPhone("phone");
        System.out.println(client.getPhone());
    }
}
