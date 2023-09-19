package com.neu.service;

import com.neu.mapper.UserMapper;
import com.neu.pojo.User;
import com.neu.util.UUIDUtil;
import java.util.List;

public class UserService {

    private UserMapper userMapper ;

    List<User> selectAllUser(){
        List<User> result=userMapper.selectAllUser();

        return result;
    }

    //登录
    public List<User> selectAUser(User user){
        List<User> result=userMapper.selectAUser(user);

        return result;
    }

    //创建用户
    public int insertUser(User user){
        user.setUserId(UUIDUtil.getOneUUID());
        int result=userMapper.insertUser(user);

        return result;
    }

    //修改用户
    public int modifyUser(User user){
        int result=userMapper.modifyUser(user);

        return result;
    }

    //删除用户
    public int deleteUserById(User user){
        int result=userMapper.deleteUserById(user);

        return result;
    }

}
