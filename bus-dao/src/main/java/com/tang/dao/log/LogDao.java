package com.tang.dao.log;

import com.tang.entity.ServiceLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 日志dao
 *
 * @author tang
 * @since 2020/7/17
 */
@Mapper
@Component
public interface LogDao {
    /**
     * 插入用户，并返回用户的id,将id放入user
     *
     * @param log 需要插入的用户
     */
    @Insert("INSERT INTO log(module,operate) VALUES(#{module}, #{operate})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(ServiceLog log);

    /**
     * 查询所有用户
     *
     * @return 所有用户列表
     */
    @Select("select * from log")
    List<ServiceLog> selectAll();
}
