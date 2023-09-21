package com.neu.service;

import com.neu.mapper.UserMapper;
import com.neu.pojo.User;
import com.neu.util.SqlSessionFactoryUtils;
import com.neu.util.UUIDUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserService {

    private SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<User> selectAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.selectAllUser();
        sqlSession.close();
        return userList;
    }

    public User selectUserByName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.selectUserByName(user);
    }

    public int insertUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.insertUser(user);
    }

    public int modifyUser(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.modifyUser(user);
    }

    //删除用户
    public int deleteUserByName(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.deleteUserByName(user);
    }

    //查找所有关注
    public List<User> selectAllFriends(User user){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.selectAllFriends(user);
    }

    //增加关注
    public int addFriends(User user,Integer friendId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.addFriends(user,friendId);
    }

    //删除关注
    public int deleteFriends(User user,Integer friendId){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        sqlSession.close();
        return mapper.deleteFriends(user,friendId);
    }
}
