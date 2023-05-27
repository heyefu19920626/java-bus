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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author tang
 * Create in: 2020-07-12
 * Time: 下午3:24
 **/
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
    public Config addConfig(Config config) {
        configDao.insert(config);
        return config;
    }

    @GetMapping("config/all")
    public MyPageInfo<Config> getConfig(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Config> configs = configDao.selectAll();
        return new MyPageInfo<>(configs);
    }

    @GetMapping("log/all")
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
    public RestResponse<Object> upload(@RequestPart("user") User user, @RequestPart("file") MultipartFile file) throws IOException {
        log.info("user: {}", JSON.toJSON(user));
        log.info("filename: {}", file.getOriginalFilename());
        log.info("filesize: {} k", file.getSize() / 1024);
        return new RestResponse<>(true);
    }

    @PostMapping("uploads")
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