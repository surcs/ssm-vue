package com.surc.web.controller;

import com.surc.entity.Client;
import com.surc.service.BaseService;
import com.surc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController extends BaseController<Client, String> {
    @Autowired
    private ClientService clientService;

    @Override
    public BaseService getService() {
        return this.clientService;
    }
}
