package com.surc.dao;

import com.surc.BaseCRUD;
import com.surc.BaseTest;
import com.surc.entity.User;
import com.surc.enums.Sex;
import com.surc.enums.UserPosition;
import com.surc.util.DateFormatUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

public class UserMapperTest extends BaseTest implements BaseCRUD {
    @Autowired
    private UserMapper userMapper;
    private User user;

    /**
     * 测试用例：添加用户
     * @return
     */
    @Test
    @Override
    public void insert() throws ParseException {
        user = new User();

        user.setName("name1");
        user.setPassword("111111");
        user.setSex(Sex.FEMALE);
        user.setEmail("email1");
        user.setPhone("phone1");
        user.setBirth(DateFormatUtil.parse("2019-1-13"));
        user.setPosition(UserPosition.SALESMAN);
        userMapper.insert(user);

        user.setName("name2");
        user.setPassword("111111");
        user.setSex(Sex.FEMALE);
        user.setEmail("email2");
        user.setPhone("phone2");
        user.setBirth(DateFormatUtil.parse("2019-1-14"));
        user.setPosition(UserPosition.SALES_KEEPER);
        userMapper.insert(user);

        user.setName("name3");
        user.setPassword("111111");
        user.setSex(Sex.MALE);
        user.setEmail("email3");
        user.setPhone("phone3");
        user.setBirth(DateFormatUtil.parse("2019-1-15"));
        user.setPosition(UserPosition.WAREHOUSE_KEEPER);
        userMapper.insert(user);
    }

    @Test
    @Override
    public void delete() {
        userMapper.delete((long) 3);
    }

    @Test
    @Override
    public void update() {
        userMapper.update(user);
    }

    @Test
    @Override
    public void select() {
        System.out.println(userMapper.selectById((long)1));
    }

    @Test
    @Override
    public void selectAll() {
        System.out.println(userMapper.selectAll());
    }

    @Test
    public void selectUpdate() throws ParseException {
        user = userMapper.selectById((long)1);
        user.setName("name2");
        user.setPassword("111111");
        user.setSex(Sex.FEMALE);
        user.setEmail("email2");
        user.setPhone("phone2");
        user.setBirth(DateFormatUtil.parse("2019/1/3"));
        user.setPosition(UserPosition.SALES_KEEPER);
        update();
    }
}
