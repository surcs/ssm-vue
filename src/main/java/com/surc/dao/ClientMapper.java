package com.surc.dao;

import com.surc.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ClientMapper extends BaseMapper<Client,String>{
}
