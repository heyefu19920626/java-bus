package com.heyefu.user.dao;

import com.heyefu.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author tang
 * @since 2020/7/17
 */
@Mapper
@Component
public interface UserDao {
    @Insert("INSERT INTO user(name,password) VALUES(#{name}, #{password})" )
    int insert(User user);
}
