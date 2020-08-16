package com.tang.service;

import com.tang.dao.log.LogDao;
import com.tang.entity.ServiceLog;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author tang
 * @since 2020-08.12-19:51
 */
@Service
public class TestService {

    @Resource
    LogDao logDao;


    @Transactional(rollbackFor = Exception.class)
    public void insertLog() throws Exception {
        ServiceLog serviceLog = ServiceLog.builder().module("test").operate("operate").build();
        logDao.insert(serviceLog);
        int i = 0;
        throw new IOException("test translate");
    }

}