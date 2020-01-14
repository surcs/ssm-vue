package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.UserMapper;
import com.surc.entity.User;
import com.surc.enums.UserPosition;
import com.surc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Isolation.READ_COMMITTED
 * 允许不可重复读取但不允许脏读取
 * SELECT 命令不会返回尚未提交（Committed） 的数据，也不能返回脏数据
 * 读取数据的事务允许其他事务继续访问该行数据，但是未提交写事务将 会禁止其他事务访问该行
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public BaseMapper getMapper() {
        return userMapper;
    }

    @Override
    public UserPosition getUserPosition(long id) {
        return userMapper.selectById(id).getPosition();
    }

    @Override
    public List<User> selectUserByPosition(UserPosition userPosition) {
            return userMapper.selectUserByPosition(userPosition);
    }
}
