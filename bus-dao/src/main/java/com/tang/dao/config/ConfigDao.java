package com.tang.dao.config;

import com.tang.entity.Config;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tang
 * @since 2020/7/17
 */
@Mapper
@Component
public interface ConfigDao {
    /**
     * 插入用户，并返回用户的id,将id放入user
     *
     * @param config 需要插入的用户
     */
    @Insert("INSERT INTO config(key,value) VALUES(#{key}, #{value})")
    void insert(Config config);

    /**
     * 查询所有用户
     *
     * @return 所有用户列表
     */
    @Select("select * from config")
    List<Config> selectAll();
}
