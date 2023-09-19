package com.neu.mapper;

import com.neu.pojo.User;

import java.util.List;

public interface UserMapper {

    //查询用户列表
    List<User> selectAllUser();

    //查询用户
    User selectUserById(User user);

    //创建用户基本信息
    int insertUser(User user);

    //根据id删除用户信息
    int deleteUserByName(User user);

    //编辑用户信息
    int modifyUser(User user);

    //登录
    int login(User user);
}
