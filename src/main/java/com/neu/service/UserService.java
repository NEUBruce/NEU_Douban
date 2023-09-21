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
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.selectAllUser();
            sqlSession.commit();
            sqlSession.close();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User selectUserByName(User user){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User selectUser = mapper.selectUserByName(user);
            sqlSession.commit();
            sqlSession.close();
            return selectUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int insertUser(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.insertUser(user);
            sqlSession.commit();
            sqlSession.close();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int modifyUser(User user){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.modifyUser(user);
            sqlSession.commit();
            sqlSession.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //删除用户
    public int deleteUserByName(User user){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.deleteUserByName(user);
            sqlSession.commit();
            sqlSession.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //查找所有关注
    public List<User> selectAllFriends(User user){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.selectAllFriends(user);
            sqlSession.commit();
            sqlSession.close();
            return userList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //增加关注
    public int addFriends(User user,Integer friendId){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.addFriends(user, friendId);
            sqlSession.commit();
            sqlSession.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    //删除关注
    public int deleteFriends(User user,Integer friendId){
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            int result = mapper.deleteFriends(user, friendId);
            sqlSession.commit();
            sqlSession.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public List<User> recommendUsers(Long userId, int size) {

        return null;

    }

}
