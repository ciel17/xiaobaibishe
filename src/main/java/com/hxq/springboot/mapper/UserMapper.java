package com.hxq.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxq.springboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("SELECT * FROM sys_user")
//    List<User> findall();
//
//    @Insert("INSERT INTO sys_user(username,password,nickname,email,phone,address) " +
//            "VALUES(#{username},#{password},#{nickname},#{email},#{phone},#{address})")
//    int insert(User user);
//
//    //@Update("update sys_user set username=#{username},password=#{password},nickname=#{nickname}," +
//      //      "email=#{email},phone=#{phone},address=#{address} where id=#{id}")
//    int update(User user);
//
//    @Delete("delete from sys_user where id=#{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("SELECT * FROM sys_user where username like #{username} LIMIT #{pageNum},#{pageSize}")
//    List<User> selectPage(Integer pageNum,Integer pageSize,String username);
//
//    @Select("select count(*) from sys_user where username like concat('%',#{username},'%')")
//    Integer selectTotal(String username);
}
