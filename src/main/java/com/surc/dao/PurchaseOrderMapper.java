package com.surc.dao;

import com.surc.entity.DispatchOrder;
import com.surc.entity.PurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PurchaseOrderMapper extends BaseMapper<PurchaseOrder,Long> {
    List<DispatchOrder> selectToClose();
    List<DispatchOrder> selectToConfirm();
}
