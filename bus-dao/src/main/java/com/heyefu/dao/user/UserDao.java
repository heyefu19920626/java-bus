package com.heyefu.dao.user;

import com.heyefu.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tang
 * @since 2020/7/17
 */
@Mapper
@Component
public interface UserDao {
    /**
     * 插入用户，并返回用户的id,将id放入user
     *
     * @param user 需要插入的用户
     */
    @Insert("INSERT INTO user(name,password) VALUES(#{name}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(User user);

    /**
     * 查询所有用户
     *
     * @return 所有用户列表
     */
    @Select("select * from user")
    List<User> selectAll();
}
