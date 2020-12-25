package com.tang.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.tang.bean.Account;
import com.tang.dao.config.ConfigDao;
import com.tang.dao.log.LogDao;
import com.tang.entity.Config;
import com.tang.entity.ServiceLog;
import com.tang.entity.User;
import com.tang.entity.common.MyPageInfo;
import com.tang.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author tang
 * Create in: 2020-07-12
 * Time: 下午3:24
 **/
@Api(tags = "测试接口")
@RestController
@Slf4j
@RequestMapping("test")
public class TestController {
    @Resource
    ConfigDao configDao;

    @Resource
    LogDao logDao;

    @Value("${bus.password}")
    String password;

    @GetMapping("config/add")
    @ResponseBody
    @ApiOperation("添加配置")
    public Config addConfig(Config config) {
        configDao.insert(config);
        return config;
    }

    @GetMapping("config/all")
    @ApiOperation("获取所有配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", example = "2")
    })
    public MyPageInfo<Config> getConfig(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Config> configs = configDao.selectAll();
        return new MyPageInfo<>(configs);
    }

    @GetMapping("log/all")
    @ApiOperation("获取所有日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", example = "2")
    })
    public MyPageInfo<ServiceLog> getLogs(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new MyPageInfo<>(logDao.selectAll());
    }

    @GetMapping("password")
    public Object test() {
        return new RestResponse<>(password);
    }

    /**
     * 测试同时上传文件和json
     *
     * @param user 用户，为json
     * @param file 文件
     * @return 上传结果
     * @throws IOException 文件流异常
     */
    @PostMapping("upload")
    public RestResponse<Object> upload(@RequestPart("user") User user,
                                       @RequestPart("file") MultipartFile file) throws IOException {
        log.info("user: {}", JSON.toJSON(user));
        log.info("filename: {}", file.getOriginalFilename());
        log.info("filesize: {} k", file.getSize() / 1024);
        return new RestResponse<>(true);
    }

    @PostMapping("uploads")
    @ApiImplicitParams({
            // 有时候对象内部的文件在swagger中上传时为null，需要设置dataType为__file,name值为对象的属性名称
            @ApiImplicitParam(name = "file", paramType = "form", dataType = "__file")
    })
    public RestResponse<Object> uploadObject(Account account) {
        log.info("user: {}, {}", account.getName(), account.getPassword());
        log.info("user: {}", JSON.toJSON(account));
        if (account.getFile() != null) {
            log.info("filename: {}", account.getFile().getOriginalFilename());
            log.info("filesize: {} k", account.getFile().getSize() / 1024);
        } else {
            log.info("file is null");
        }
        return new RestResponse<>(true);
    }
}