package com.neu.util;

import com.neu.mapper.UserMapper;
import com.neu.pojo.Movie;
import com.neu.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImportUsers {

    private static SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            LineNumberReader lineReader = new LineNumberReader(new FileReader(
                    "src/main/webapp/movielens/users.dat"));
            String line = "";
            List<User> movieList = new ArrayList<User>();
            while ((line = lineReader.readLine()) != null) {
                movieList.add(fillUser(line));
            }
            persist(movieList);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void persist(List<User> users) {

        // 获取session
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        for (User user : users) {
            mapper.insertUser(user);
        }

        sqlSession.commit();

        // 销毁资源
        sqlSession.close();
    }

    private static User fillUser(String line) {
        User user = new User();
        String[] userInfo = line.split("::");
        user.setUserId(userInfo[0]);
        user.setGender(userInfo[1]);
        user.setAge(Integer.parseInt(userInfo[2]));
        user.setVocation(userInfo[3]);
        user.setZipCode(userInfo[4]);

        return user;
    }
}
