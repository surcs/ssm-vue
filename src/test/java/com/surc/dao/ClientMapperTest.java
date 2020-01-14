package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.Client;
import com.surc.enums.Sex;
import com.surc.util.DateFormatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class ClientMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private ClientMapper clientMapper;

    @Test
    @Override
    public void insert() throws ParseException {
        clientMapper.insert(new Client("3","11","111", Sex.FEMALE,DateFormatUtil.parse("2019/1/3"),null));
    }

    @Test
    @Override
    public void delete(){
        clientMapper.delete("1");
    }

    @Test
    @Override
    public void update() throws ParseException {
        clientMapper.update(new Client("1","2","2",Sex.MALE, DateFormatUtil.parse("2019/1/3"),null));
    }

    @Test
    @Override
    public void select(){
        System.out.println(clientMapper.selectById("1"));
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(clientMapper.selectAll());
    }

}
