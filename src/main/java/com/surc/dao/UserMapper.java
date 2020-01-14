package com.surc.dao;

import com.surc.entity.User;
import com.surc.enums.UserPosition;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User, Long> {
    List<User> selectUserByPosition(UserPosition userPosition);
}
