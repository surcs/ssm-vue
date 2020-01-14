package com.surc.service.impl;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.Client;
import com.surc.enums.Sex;
import com.surc.service.ClientService;
import com.surc.util.DateFormatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class ClientServiceImplTest extends BaseTest implements BaseCRUD {
    @Autowired
    private ClientService clientService;

    @Test
    @Override
    public void insert() throws Exception {
        clientService.insert(new Client("service","11","111", Sex.FEMALE, DateFormatUtil.parse("2019/1/3"),null));
    }

    @Test
    @Override
    public void delete() {
        clientService.delete("service");
    }

    @Test
    @Override
    public void update() throws ParseException {
        clientService.update(new Client("service","2","222", Sex.FEMALE,DateFormatUtil.parse("2019/1/3"),null));
    }

    @Test
    @Override
    public void select() {
        System.out.println(clientService.selectById("service"));
    }

    @Override
    public void selectAll() {

    }
}
