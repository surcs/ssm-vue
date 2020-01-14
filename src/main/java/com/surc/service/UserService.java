package com.surc.service;

import com.surc.entity.User;
import com.surc.enums.UserPosition;

import java.util.List;

public interface UserService extends BaseService<User,Long> {
    UserPosition getUserPosition(long id);
    List<User> selectUserByPosition(UserPosition userPosition);
}
