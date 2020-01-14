package com.surc.util;

import com.surc.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class BeanConfig {
    @Bean
    public Goods goods(){return new Goods();}
    @Bean
    public Client client(){return new Client();}
    @Bean
    public Order order(){return new Order();}
    @Bean
    public DispatchOrder dispatchOrder(){return new DispatchOrder();}
    @Bean
    public PurchaseOrder purchaseOrder(){return new  PurchaseOrder();}
    @Bean
    public ClientPurchaseOrder clientPurchaseOrder(){return new ClientPurchaseOrder();}
}
