package com.surc.dao;

import com.surc.entity.DispatchOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DispatchOrderMapper extends BaseMapper<DispatchOrder,Long> {
    List<DispatchOrder> selectSalesKeeperUncheck();
    List<DispatchOrder> selectWarehouseKeeperUncheck();
}
