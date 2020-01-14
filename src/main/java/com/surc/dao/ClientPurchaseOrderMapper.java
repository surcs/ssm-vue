package com.surc.dao;

import com.surc.entity.ClientPurchaseOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClientPurchaseOrderMapper extends BaseMapper<ClientPurchaseOrder,Long> {
    List<ClientPurchaseOrder> selectCountAllSales(@Param("start") String start,@Param("end") String end);
    List<ClientPurchaseOrder> selectCountSalesByClientId(@Param("id") String id,@Param("start") String start,@Param("end") String end);
    List<ClientPurchaseOrder> selectCountSalesBySalesmanId(@Param("id") long id,@Param("start") String start,@Param("end") String end);
//    List<ClientPurchaseOrder> selectCountAllSales( String start, String end);
//    List<ClientPurchaseOrder> selectCountSalesByClientId( String id, String start, String end);
//    List<ClientPurchaseOrder> selectCountSalesBySalesmanId( long id, String start, String end);

}
