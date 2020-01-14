package com.surc.service.impl;

import com.surc.dao.BaseMapper;
import com.surc.dao.ClientMapper;
import com.surc.entity.Client;
import com.surc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class ClientServiceImpl extends BaseServiceImpl<Client,String> implements ClientService {
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public BaseMapper getMapper() {
        return clientMapper;
    }
}
