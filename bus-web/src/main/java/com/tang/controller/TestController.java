package com.tang.controller;

import com.tang.dao.log.LogDao;
import com.tang.dao.config.ConfigDao;
import com.tang.entity.ServiceLog;
import com.tang.entity.Config;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang
 * Create in: 2020-07-12
 * Time: 下午3:24
 **/
@Api(tags = "测试接口")
@RestController
@RequestMapping("test")
public class TestController {
    @Resource
    ConfigDao configDao;

    @Resource
    LogDao logDao;

    @GetMapping("config/add")
    @ResponseBody
    @ApiOperation("添加配置")
    public Config addConfig(Config config) {
        configDao.insert(config);
        return config;
    }

    @GetMapping("config/all")
    @ApiOperation("获取所有配置")
    public List<Config> getConfig() {
        return configDao.selectAll();
    }

    @GetMapping("log/all")
    @ApiOperation("获取所有日志")
    public List<ServiceLog> getLogs() {
        return logDao.selectAll();
    }

    @GetMapping("log/add")
    @ApiOperation("添加日志")
    public ServiceLog addLog(ServiceLog serviceLog) {
        logDao.insert(serviceLog);
        return serviceLog;
    }
}