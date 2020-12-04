package com.tang.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tang
 * @since 2020-12.04-23:45
 */
@Getter
@Setter
public class Account {
    private String name;

    private String password;

    @JSONField(serialize = false)
    private MultipartFile file;
}
