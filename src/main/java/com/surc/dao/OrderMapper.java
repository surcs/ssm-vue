package com.surc.dao;

import com.surc.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order,Long> {
    List<Order> selectOrderListBySalesmanId(long id);
}
