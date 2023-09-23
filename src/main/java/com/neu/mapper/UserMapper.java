package com.neu.mapper;

import com.neu.pojo.User;

import java.util.List;

public interface UserMapper {

    //查询用户列表
    List<User> selectAllUser();

    //查询用户
    User selectUserByName(User user);

    //通过用户名和密码查询用户信息
    User selectUserByNameAndPassword(User user);

    //创建用户基本信息
    int insertUser(User user);

    //根据id删除用户信息
    int deleteUserByName(User user);

    //编辑用户信息
    int modifyUser(User user);

    //登录
    int login(User user);

    //查找所有关注
    List<User> selectAllFriends(User user);

    //删除关注
    int deleteFriends(User user,Integer friendId);

    //增加关注
    int addFriends(User user,Integer friendId);

    //通过用户id查询用户
    List<User> selectUserById(User user);

    //导入用户
    int importUsers(User user);

}
