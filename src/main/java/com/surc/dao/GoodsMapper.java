package com.surc.dao;

import com.surc.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GoodsMapper extends BaseMapper<Goods,Long>{
}
