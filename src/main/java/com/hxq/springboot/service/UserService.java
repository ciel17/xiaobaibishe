package com.hxq.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxq.springboot.entity.User;
import com.hxq.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User user) {
//        if(user.getId()==null) {
//            return save(user); //mybatis plus提供的方法，表示插入数据
//        }else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }


//    @Autowired
//    private UserMapper userMapper;
//    public int save(User user){
//        if(user.getId() == null) {//user没有id，则表示新增
//            return userMapper.insert(user);
//        }else {//否则为更新
//            return userMapper.update(user);
//        }


    }


