package com.tang.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

/**
 * 日志实体
 *
 * @author heyefu
 * Create in: 2020-07-12
 * Time: 下午1:56
 **/
@Getter
@Setter
@Builder
public class ServiceLog {
    private int id;
    private String module;
    private String operate;

    @Tolerate
    public ServiceLog() {
    }
}